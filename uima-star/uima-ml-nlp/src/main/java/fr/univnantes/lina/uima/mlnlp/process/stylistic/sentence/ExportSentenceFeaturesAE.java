/** 
 * UIMA ml-nlp
 * Copyright (C) 2010  Nicolas Hernandez
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *     http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package fr.univnantes.lina.uima.mlnlp.process.stylistic.sentence;


import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.cas.text.AnnotationIndex;
import org.apache.uima.examples.SourceDocumentInformation;
import org.apache.uima.fit.component.JCasAnnotator_ImplBase;
import org.apache.uima.fit.descriptor.ConfigurationParameter;
import org.apache.uima.fit.util.CasUtil;
import org.apache.uima.fit.util.JCasUtil;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.tcas.Annotation;
import org.apache.uima.jcas.tcas.DocumentAnnotation;

import common.types.Meta;
import common.types.text.Sentence;
import common.types.text.Token;
import fr.univnantes.lina.javautil.IOUtilities;
import fr.univnantes.lina.mlnlp.model.text.Word;
import fr.univnantes.lina.uima.common.cas.AnnotationUtils;
import fr.univnantes.lina.uima.common.cas.FeatureUtils;

/**
 * Annotator that aims at exporting each sentence with various features
 * with a potential header marked by Meta annotations 
 * 
 */
public class ExportSentenceFeaturesAE extends JCasAnnotator_ImplBase {

	/** Path dir where to save the current CAS with sentence and features */
	public static final String PARAM_OUTPUT_DIR_PATH = "outputDirPath";
	@ConfigurationParameter(name = PARAM_OUTPUT_DIR_PATH, mandatory = true, defaultValue="/tmp")
	private String outputDirPath;

	public static final String PARAM_ERASE_OUTPUT_DIR_CONTENT = "eraseOutputDir";
	@ConfigurationParameter(name = PARAM_ERASE_OUTPUT_DIR_CONTENT, mandatory = false, defaultValue="false")
	private Boolean eraseOutputDir;

	public static final String PARAM_COVERING_ANNOTATION = "coveringAnnotationName";
	@ConfigurationParameter(name = PARAM_COVERING_ANNOTATION, mandatory = false, defaultValue="uima.tcas.DocumentAnnotation")
	private String coveringAnnotationName;

	/** List of comma-separated features names of the sentence to export */
	public static final String PARAM_FEATURES_LIST = "featuresNames";
	@ConfigurationParameter(name = PARAM_FEATURES_LIST, mandatory = false, defaultValue="")
	private String featuresNames;


	//private static String DEFAULT_DOCUMENT_ANNOTATION = "org.apache.uima.jcas.tcas.DocumentAnnotation";

	//public static final String PARAM_OUTPUT_DIR_PATH = "outputDirPath";
	//@ConfigurationParameter(name=PARAM_OUTPUT_DIR_PATH, mandatory=false)
	//private String outputDirPath;


	private static String DEFAULT_SOURCE_DOCUMENT_INFORMATION_ANNOTATION = "org.apache.uima.examples.SourceDocumentInformation";

	private String NEWLINE_PATTERN = "\\n"; 
	private Pattern newlinePattern = Pattern.compile(NEWLINE_PATTERN);
	private String[] featureNameArray ; 

	@Override
	public void initialize(org.apache.uima.UimaContext context) throws org.apache.uima.resource.ResourceInitializationException {

		super.initialize(context);

		//if (outputDirPath != null && !outputDirPath.equalsIgnoreCase("")) 
		if (eraseOutputDir)	{
			System.err.println("Info: the following dir content will be deleted "+outputDirPath);
			IOUtilities.deleteDirectoryContent(outputDirPath, true);
		}

		if (featuresNames != null && !featuresNames.equalsIgnoreCase("")) {
			featureNameArray = featuresNames.split(",");
		}


	};

	@Override
	public void process(JCas aJCas) throws AnalysisEngineProcessException {

		// Get the uri of the current jcas
		SourceDocumentInformation sourceDocumentInformation = (SourceDocumentInformation) aJCas.getAnnotationIndex(aJCas.getTypeSystem().getType(DEFAULT_SOURCE_DOCUMENT_INFORMATION_ANNOTATION)).iterator().get();
		//System.out.printf("Debug: sourceDocumentInformation.getUri(): >%s<\n",sourceDocumentInformation.getUri());
		String uri = "";
		if (sourceDocumentInformation.getUri().lastIndexOf(File.separator) == -1)  uri = sourceDocumentInformation.getUri();
		else uri = sourceDocumentInformation.getUri().substring(sourceDocumentInformation.getUri().lastIndexOf(File.separator));

		Annotation coveringAnnotation = (Annotation)  aJCas.getAnnotationIndex(AnnotationUtils.getType(aJCas, coveringAnnotationName)).iterator().get(); 
		//DocumentAnnotation documentAnnotation = (DocumentAnnotation) aJCas.getAnnotationIndex(aJCas.getTypeSystem().getType(DEFAULT_DOCUMENT_ANNOTATION)).iterator().get(); 
		List<Sentence> sentences = JCasUtil.selectCovered(aJCas, Sentence.class, coveringAnnotation);

		//AnnotationIndex<Annotation> sentencesAnnotationIndex = aJCas.getAnnotationIndex(common.types.text.Sentence.type);
		//Iterator<Annotation> sentencesAnnotationIndexIterator = sentencesAnnotationIndex.iterator();


		/** export */
		StringBuffer output = new StringBuffer();

		//
		AnnotationIndex<Annotation> metaAnnotationIndex = aJCas.getAnnotationIndex(common.types.Meta.type);
		Iterator<Annotation> metaAnnotationIndexIterator = metaAnnotationIndex.iterator();

		while (metaAnnotationIndexIterator.hasNext()) {
			Meta meta = (Meta) metaAnnotationIndexIterator.next();
			output.append(meta.getContent());

		}

		for (Sentence sentence : sentences) {

			List<Token> words = JCasUtil.selectCovered(aJCas, Token.class, sentence);

			// the words
			StringBuffer wordsStringBuffer = new StringBuffer();
			for (Token word : words) {
				if (! word.getCoveredText().matches("^\\s+$")) {
					wordsStringBuffer.append(word.getCoveredText());
					wordsStringBuffer.append(" ");
				}
			}
			if (wordsStringBuffer.length() >0) 
				wordsStringBuffer.deleteCharAt(wordsStringBuffer.length()-1);
			output.append(wordsStringBuffer);
			output.append("\t");


			// the features

			if (featureNameArray != null) 	
				for (int i = 0 ; i < featureNameArray.length ; i ++) {
					//Method getMethod = FeatureUtils.getFeatureGetterMethod(AnnotationUtils.getAnnotationClass("common.types.text.Sentence"), featureNameArray[0]);
					Method getMethod = FeatureUtils.getFeatureGetterMethod(Sentence.class, featureNameArray[i]);
					output.append(FeatureUtils.invokeFeatureGetterMethod(sentence, getMethod));
					output.append("\t");
				}

			/*output.append(sentence.getPrecedingNewlines());
			output.append("\t");
			output.append(sentence.getFollowingNewlines());
			output.append("\t");
			output.append(sentence.getTextTilingStatus());
			output.append("\t");
			output.append(sentence.getSegmentStatus());
			 */
			//
			output.append("\n");
		}



		if (outputDirPath != null) {
			/*output.insert(0, "#\t"+ replyMessage.getId() +"\t"+ replyMessage.getMime() +"\t"+ replyMessage.getEncoding() +"\t"+  replyMessage.getIsInitial() +"\t"+ replyMessage.getFromAddress()+"\t"+ replyMessage.getFromPersonal()+"\t"+ replyMessage.getToAddress()+"\t"+ replyMessage.getToPersonal() + "\n");
			output.insert(0, "#\t"+ originalMessage.getId() +"\t"+ originalMessage.getMime() +"\t"+ originalMessage.getEncoding() +"\t"+  originalMessage.getIsInitial() +"\t"+ originalMessage.getFromAddress()+"\t"+ originalMessage.getFromPersonal()+"\t"+ originalMessage.getToAddress()+"\t"+ originalMessage.getToPersonal() + "\n");
			output.insert(0, "#\tmessageId\tmime\tencoding\tisInitial\tfromAddress\tfromPersonal\ttoAddress\ttoPersonal\n");
			output.insert(0, "#\t"+ outputDirPath+"/"+sourceDocumentInformation.getUri() + "\n");*/

			//System.out.println("outputPath:"+ outputDirPath+"/"+sourceDocumentInformation.getUri());

			//System.out.println("content:\n"+output);
			IOUtilities.writeToFS(output.toString(), outputDirPath+"/"+sourceDocumentInformation.getUri(), false);
		}


	}

	/**
	 * Count the number of newline characters in the given text
	 * @param text
	 * @return
	 */
	private int countNewLines (String text) {
		int counter = 0;

		Matcher textMatcher = newlinePattern.matcher(text);
		while (textMatcher.find()) {
			counter++;
		}
		return counter;
	}
}
