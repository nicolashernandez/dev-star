package fr.univnantes.lina.uima.mlnlp.app;
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

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.uima.UimaContext;
import org.apache.uima.analysis_component.AnalysisComponent;
import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.jcas.JCas;
import org.apache.uima.resource.ResourceInitializationException;

import fr.univnantes.lina.uima.common.cas.JCasUtils;



/**
 * Concordancer
 */
public class ConcordancerAE extends JCasAnnotator_ImplBase {

	/*
	 * CONFIGURATION PARAMETER NAMES 
	 */

	/**
	 * Parameter name for patterns list
	 */
	public static final String PARAM_PATTERNS = "Patterns";
	private static final String DEFAULT_OUTPUTVIEW_TYPEMIME = "text/plain";
	public static final String PARAM_WINDOW_SIZE = "WindowSize";

	/*
	 * CONFIGURATION PARAMETER NAMES VALUES
	 */

	private Pattern[] mPatterns;

	private String[] patternStrings;

	private int windowSize = 1;

	/*
	 * LOCAL VARIABLES
	 */
	protected String outputViewTypeMimeString = null;

	/**
	 * @see AnalysisComponent#initialize(UimaContext)
	 */
	public void initialize(UimaContext aContext) throws ResourceInitializationException {
		super.initialize(aContext);
		
		// Get config. parameter values
		patternStrings = (String[]) aContext.getConfigParameterValue(PARAM_PATTERNS);
		windowSize = (Integer) aContext.getConfigParameterValue(PARAM_WINDOW_SIZE);

		// compile regular expressions
		mPatterns = new Pattern[patternStrings.length];
		for (int i = 0; i < patternStrings.length; i++) {
			mPatterns[i] = Pattern.compile(patternStrings[i]);
		}
	}

	/**
	 * @see JCasAnnotator_ImplBase#process(JCas)
	 */
	public void process(JCas aJCas) throws AnalysisEngineProcessException {
		// get document text
		String docText = aJCas.getDocumentText();

		// loop over patterns
		for (int i = 0; i < mPatterns.length; i++) {

			String inputViewsConcatenedResults ="";
			Matcher matcher = mPatterns[i].matcher(docText);
			while (matcher.find()) {
				// found one - create annotation
				int inf = matcher.start() - windowSize;
				if (inf <0 ) inf = 0;
				int sup = matcher.end() + windowSize;
				if (sup > docText.length()) sup = docText.length();
				
				//System.out.println("Debug: docText.substring(matcher.start(),matcher.end())"+docText.substring(matcher.start(),matcher.end()));
				
				inputViewsConcatenedResults += 
					"<context>"
					+docText.substring(inf, matcher.start())
					+" ["+ matcher.start()+"] "
					+ 
					docText.substring(matcher.start(),matcher.end())
					+" ["+ matcher.end()+"] " 
					+ docText.substring(matcher.end() , sup) 
					+ "</context>\n"
					;
//				  System.out.println("Debug: docText.substring(matcher.start(),matcher.end())"+    docText.substring(matcher.start(),matcher.end()));

	//			inputViewsConcatenedResults += inputViewsConcatenedResults.trim() + "\n";
			}
		//	System.out.println("Debug: inputViewsConcatenedResults"+    inputViewsConcatenedResults);

			JCasUtils.createView(aJCas, patternStrings[i], inputViewsConcatenedResults, DEFAULT_OUTPUTVIEW_TYPEMIME);

		}
	}

}
