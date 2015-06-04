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


import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.cas.Type;
import org.apache.uima.examples.SourceDocumentInformation;
import org.apache.uima.fit.component.JCasAnnotator_ImplBase;
import org.apache.uima.fit.descriptor.ConfigurationParameter;
import org.apache.uima.fit.util.JCasUtil;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.tcas.Annotation;
import org.apache.uima.jcas.tcas.DocumentAnnotation;

import common.types.text.Sentence;
/**
 * Annotator that aims at describing each sentence with various stylistic features 
 * 
 */
public class SentenceStyleDecriptionAE extends JCasAnnotator_ImplBase {


	private static String DEFAULT_SOURCE_DOCUMENT_INFORMATION_ANNOTATION = "org.apache.uima.examples.SourceDocumentInformation";
	//private static String DEFAULT_DOCUMENT_ANNOTATION = "org.apache.uima.jcas.tcas.DocumentAnnotation";
	private static String DEFAULT_DOCUMENT_ANNOTATION = "uima.tcas.DocumentAnnotation";

	private String NEWLINE_PATTERN = "\\n"; 
	private Pattern newlinePattern = Pattern.compile(NEWLINE_PATTERN);

	/*@Override
	public void initialize(UimaContext aContext) throws ResourceInitializationException {

		// SUPER PARAMETER SETTINGS
		super.initialize(aContext);

	}*/

	@Override
	public void process(JCas aJCas) throws AnalysisEngineProcessException {

		// Get the uri of the current jcas
		//SourceDocumentInformation sourceDocumentInformation = (SourceDocumentInformation) aJCas.getAnnotationIndex(aJCas.getTypeSystem().getType(DEFAULT_SOURCE_DOCUMENT_INFORMATION_ANNOTATION)).iterator().get();
		//System.out.printf("Debug: sourceDocumentInformation.getUri(): >%s<\n",sourceDocumentInformation.getUri());
		//String uri = "";
		//if (sourceDocumentInformation.getUri().lastIndexOf(File.separator) == -1)  uri = sourceDocumentInformation.getUri();
		//else uri = sourceDocumentInformation.getUri().substring(sourceDocumentInformation.getUri().lastIndexOf(File.separator));

		/*Iterator<Annotation> annotationsIterator = aJCas.getAnnotationIndex().iterator(); 
		while (annotationsIterator.hasNext()) {
			System.out.println("Debug: "+(annotationsIterator.next()).getType().getName());
		}*/
		
		Type type = aJCas.getTypeSystem().getType(DEFAULT_DOCUMENT_ANNOTATION);
		DocumentAnnotation documentAnnotation = (DocumentAnnotation) aJCas.getAnnotationIndex(type).iterator().get(); 
		List<Sentence> sentences = JCasUtil.selectCovered(aJCas, Sentence.class, documentAnnotation);

		for (int i = 0 ; i < sentences.size() ; i++ ) {
			Sentence currentSentence = sentences.get(i);	
			int from = 0;
			int to = documentAnnotation.getEnd();
			
			/** set the number of preceding newlines */
			if (i == 0) {
				from = documentAnnotation.getBegin();
				to = currentSentence.getBegin();
			}
			else {
				from = sentences.get(i-1).getEnd();
				to = currentSentence.getBegin();
			}
			currentSentence.setPrecedingNewlines(countNewLines(documentAnnotation.getCoveredText().substring(from, to)));
			
			/** set the number of following newlines */
			if (i +1 == sentences.size()) {
				from = currentSentence.getEnd();
				to = documentAnnotation.getEnd();
			}
			else {
				from = currentSentence.getEnd();
				to = sentences.get(i+1).getBegin();
			}
			currentSentence.setFollowingNewlines(countNewLines(documentAnnotation.getCoveredText().substring(from, to)));
			

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
