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
package fr.univnantes.lina.uima.mlnlp.process.tokenization;

import java.util.AbstractList;
import java.util.Iterator;
import java.util.Vector;

import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.cas.text.AnnotationIndex;
import org.apache.uima.examples.SourceDocumentInformation;
import org.apache.uima.fit.component.JCasAnnotator_ImplBase;
import org.apache.uima.fit.descriptor.ConfigurationParameter;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.tcas.Annotation;


import fr.univnantes.lina.javautil.IOUtilities;
import fr.univnantes.lina.uima.common.cas.JCasUtils;


/**
 * Export on the file system a text segmented into discourse segments, sentences and tokens 
 */
public class TokenizationCC extends JCasAnnotator_ImplBase {

	public static final String DEFAULT_SEGMENT_ANNOTATION ="common.types.discourse.Segment";

	public static final String PARAM_SEGMENT_ANNOTATION = "segmentAnnotation";
	@ConfigurationParameter(name = PARAM_SEGMENT_ANNOTATION, mandatory = false, defaultValue=DEFAULT_SEGMENT_ANNOTATION)
	private String segmentAnnotation;

	public static final String PARAM_SENTENCE_ANNOTATION = "sentenceAnnotation";
	@ConfigurationParameter(name = PARAM_SENTENCE_ANNOTATION, mandatory = false, defaultValue="common.types.text.Sentence")
	private String sentenceAnnotation;

	public static final String PARAM_TOKEN_ANNOTATION = "tokenAnnotation";
	@ConfigurationParameter(name = PARAM_TOKEN_ANNOTATION, mandatory = false, defaultValue="common.types.text.Token")
	private String tokenAnnotation;

	public static final String PARAM_OUTPUT_DIR = "outputDir";
	@ConfigurationParameter(name = PARAM_OUTPUT_DIR, mandatory = true, defaultValue="/tmp")
	private String outputDir;


	private static String DEFAULT_SOURCE_DOCUMENT_INFORMATION_ANNOTATION = "org.apache.uima.examples.SourceDocumentInformation";

	private int processedJCasSize = 0;




	@Override
	public void initialize(org.apache.uima.UimaContext context) throws org.apache.uima.resource.ResourceInitializationException {

		super.initialize(context);
		//TODO require createDir if does not exist
		if (outputDir != null && !outputDir.equalsIgnoreCase("")) {
			System.err.println("Info: the following dir content will be deleted "+outputDir);
			IOUtilities.deleteDirectoryContent(outputDir, true);

			//if (mappingFile != null && !mappingFile.equalsIgnoreCase("")) {
			//	System.err.println("Info: the following file will be deleted "+mappingFile);
			//	deleteFile(mappingFile);
			//}
		}
	};


	@Override
	public void process(JCas aJCas) throws AnalysisEngineProcessException {
		// Prints the instance ID to the console - this proves the same instance
		// of the SharedModel is used in both Annotator instances.
		//System.out.println(getClass().getSimpleName() + ": " + wordCounter);

		processedJCasSize++;


		// Get the uri of the current jcas
		SourceDocumentInformation sourceDocumentInformation = (SourceDocumentInformation) aJCas.getAnnotationIndex(aJCas.getTypeSystem().getType(DEFAULT_SOURCE_DOCUMENT_INFORMATION_ANNOTATION)).iterator().get();
		String uri = sourceDocumentInformation.getUri().substring(sourceDocumentInformation.getUri().lastIndexOf("/"));
		//MiscUtil.writeToFS(mBoxMessage.toString(), messageSaveDir+"/"+uri, false);
		/*XStream xstream = new XStream(); 
			String xmlHeader = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"+"\n";
			String snippet = xstream.toXML(mBoxMessage);*/
		//System.out.println("Debug: toXML "+ snippet);
		//String xml = xmlHeader+snippet;

		// the following do not escape the whitespace char
		//System.out.println("Debug: escapeXml "+ StringEscapeUtils.escapeXml(snippet));

		// CR/LF/tab http://en.wikipedia.org/wiki/List_of_XML_and_HTML_character_entity_references
		//snippet = snippet.replaceAll("\\n", "&#10;").replaceAll("\\r", "&#13;").replaceAll("\\t", "&#9;");
		//System.out.println("Debug: myescape "+ snippet);

		StringBuffer outputString = new StringBuffer();
		AnnotationIndex<Annotation> segmentAnnotationIndex = aJCas.getAnnotationIndex(JCasUtils.getJCasType(aJCas, DEFAULT_SEGMENT_ANNOTATION)) ;
		Iterator<Annotation> segmentAnnotationIterator = segmentAnnotationIndex.iterator();
		while (segmentAnnotationIterator.hasNext()) {
			Annotation segmentAnnotation = segmentAnnotationIterator.next();
			// TODO export a sentence per line and token whitespace separated ; in addition here assume the input text does not contain \n\n sequences

			/*AnnotationIndex<Annotation> sentenceAnnotationIndex = aJCas.getAnnotationIndex(JCasUtils.getJCasType(aJCas, DEFAULT_SEGMENT_ANNOTATION)) ;
				Iterator<Annotation> sentenceAnnotationIterator = sentenceAnnotationIndex.iterator();
				while (sentenceAnnotationIterator.hasNext()) {
					Annotation sentenceAnnotation = sentenceAnnotationIterator.next();

				}*/
			outputString.append(segmentAnnotation.getCoveredText()+"\n");
			outputString.append("\n");
		}
		//TODO deals when there are some annotation types missing
		/*if (segmentAnnotationIndex.iterator().hasNext()) {

			}*/

		IOUtilities.writeToFS(outputString.toString(), outputDir+"/"+uri, false);

	}



	@Override
	public void collectionProcessComplete()  throws AnalysisEngineProcessException {
		// Prints the instance ID to the console - this proves the same instance
		// of the SharedModel is used in both Annotator instances.
		/*System.err.println("Info: "+getClass().getSimpleName() + ": " + getClass().getName() + '@' + Integer.toHexString(hashCode()));
		System.err.println("Info: "+getClass().getSimpleName() + ": # of processed JCas: "+ processedJCasSize );
		System.err.println("Info: "+getClass().getSimpleName() + ": # of JCas which leads to a null mBoxMessage object: "+ nullMBoxMessageObjectSize );
		System.err.println("Info: "+getClass().getSimpleName() + ": # of not null mBoxMessage objects with a null messageId: "+ nullMessageIdSize );
		System.err.println("Info: "+getClass().getSimpleName() + ": # of messages without inReplyTo but fixed by subject similarity: "+ inReplyToFixedBySubjectSimilarity );
		System.err.println("Info: "+getClass().getSimpleName() + ": # of threads: "+ mBox.getInitialThreadMessages().size() );
		 */

	}



}
