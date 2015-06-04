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

/** 
 * Copyright (C) 2011  Nicolas Hernandez
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



import org.apache.uima.UimaContext;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.cas.FSIterator;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.tcas.Annotation;
import org.apache.uima.resource.ResourceInitializationException;

import fr.univnantes.lina.uima.common.ae.CommonAE;
import fr.univnantes.lina.uima.common.cas.AnnotationUtils;



/**
 * Simple tokenizer builds tokens for sequences of digit and letters, and 
 * single token characters for any other characters except whitespace. 
 * Works on unicode codepoints.
 * Phylosophy: cut off on all
 * 
 * <p>
 * This AE takes two parameters:
 * <ul>
 * <li><code>TokenType</code> - type name of the token annotation to create. Optional, by default <code>org.apache.uima.TokenAnnotation</code></li>

 * </ul>
 * TODO  isAClosingCharCodePoint and my be refactored using the Character.getType(codePoint) method
 * 
 * 
 */
public class NaiveSimpleTokenizerAE extends CommonAE {

	/*
	 * PARAMETERS NAMES
	 */
	/**
	 * Parameter name of the word type 
	 */
	public static final String PARAM_TOKEN_TYPE = "TokenType";


	/*
	 * DEFAULT VALUES
	 */
	/**
	 * Default value of the token type
	 */
	public static String DEFAULT_TOKEN_TYPE = "org.apache.uima.TokenAnnotation";

	/**
	 * Default value 
	 * initial value, assumed not correspond to any unicode char, should not be found in a text...
	 */
	public static int DEFAULT_INITIAL_NON_UNICODE_CODEPOINT_VALUE = 0;


	/*
	 * LOCAL VARIABLES
	 */
	private String tokenTypeName;


	/*
	 * ACCESSORS
	 */


	/*
	 * METHODS 
	 */

	/**
	 * Get the parameter values, setting the variables and checking the values 
	 */
	public void initialize(UimaContext aContext) throws ResourceInitializationException {

		// SUPER PARAMETER SETTINGS
		super.initialize(aContext);

		// CURRENT AE PARAMETER SETTINGS

		tokenTypeName = ((String) aContext.getConfigParameterValue(PARAM_TOKEN_TYPE));
		if (tokenTypeName == null) tokenTypeName = DEFAULT_TOKEN_TYPE;



		//try {
		//} catch (Exception e) {
		//	String errmsg = "!";
		//	throw new ResourceInitializationException(errmsg, new Object[] {});
		//}		
	}

	/**
	 * 
	 */
	@Override
	protected String processContextAnnotation(JCas inputViewJCas,
			FSIterator contextAnnotationsFSIter, Annotation contextAnnotation,
			FSIterator contextualizedInputAnnotationsFSIter,
			String inputFeatureString, JCas outputViewJCas,
			String outputAnnotationString, String ouputFeatureString)
					throws AnalysisEngineProcessException {



		// simple word tokenizer
		this.tokenize(inputViewJCas,
				contextAnnotation.getCoveredText(), 
				contextAnnotation.getBegin(), 
				contextAnnotation.getEnd());

		//
		return contextAnnotation.getCoveredText();
	}

	/**
	 * Simple tokenizer
	 * @param aJCas
	 * @param text
	 * @param begin TODO
	 * @param end TODO
	 * @throws AnalysisEngineProcessException 
	 */
	public void tokenize(JCas aJCas, String text, int begin, int end) throws AnalysisEngineProcessException {

		int length = text.length();
		boolean wasATokenFound= false;
		int tokenPreviouslyFoundBegin = -1;

		// initial value, assumed not correspond to any unicode char, should not be found in a text...
		int previousCharCodePoint = DEFAULT_INITIAL_NON_UNICODE_CODEPOINT_VALUE; 
		// TODO is is possible to start at  the character offset 1 instead of 0
		// and to get before the codepoint of the character 0 to start the process ?
		

		// for each text character 
		for (int index = 0; index < length; index++) {
			//Character aCharacter = new Character(currentChar);
			int currentCharCodePoint = text.codePointAt(index);

			//System.out.println("Debug: currentChar>"+JavaUtilities.codePointToString(currentCharCodePoint)+"< type>"+	Character.getType(currentCharCodePoint)+"< previous>"+JavaUtilities.codePointToString(previousCharCodePoint)+"< begin>"+tokenPreviouslyFoundBegin+"< end>"+index+"<");

			// end an annotation 
			if (isAClosingCharCodePoint(wasATokenFound,currentCharCodePoint,previousCharCodePoint)){
				//System.out.println("Debug: simpleToken>"+text.substring(tokenPreviouslyFoundBegin, index)+"< begin>"+tokenPreviouslyFoundBegin+"< end>"+index+"<");
				AnnotationUtils.createAnnotation(aJCas, tokenTypeName, begin + tokenPreviouslyFoundBegin, begin+index);

				wasATokenFound = false;
				tokenPreviouslyFoundBegin = -1;
			}

			// open an annotation 
			if (isAnOpeningCharCodePoint(wasATokenFound,currentCharCodePoint,previousCharCodePoint)){
				//System.out.println("Debug: simpleToken>"+text.substring(tokenPreviouslyFoundBegin, index)+"< begin>"+tokenPreviouslyFoundBegin+"< end>"+index+"<");
				wasATokenFound = true;
				tokenPreviouslyFoundBegin = index;
			}

			previousCharCodePoint = currentCharCodePoint;
		}


		// end an annotation for the last non whitespace character encountered
		if (isAClosingCharCodePoint(wasATokenFound,previousCharCodePoint,DEFAULT_INITIAL_NON_UNICODE_CODEPOINT_VALUE)){
			//System.out.println("Debug: simpleToken>"+text.substring(tokenPreviouslyFoundBegin, index)+"< begin>"+tokenPreviouslyFoundBegin+"< end>"+index+"<");
			AnnotationUtils.createAnnotation(aJCas, tokenTypeName, begin + tokenPreviouslyFoundBegin, begin+text.length());
			//wasATokenFound = false;
			//tokenPreviouslyFoundBegin = -1;
		}

	}


	/**
	 * Test if the given codePoint corresponds to an unicode char
	 * Used for initializing the process when no text char has been parsed yet
	 * @param codePoint
	 * @return
	 */
	private boolean isUndefined(int codePoint) {
		return (codePoint==DEFAULT_INITIAL_NON_UNICODE_CODEPOINT_VALUE);
	}

	/**
	 * 
	 * @param wasATokenFound
	 * @param currentCharCodePoint
	 * @param previousCharCodePoint
	 * @return isAClosingCharCodePoint
	 */
	private boolean isAClosingCharCodePoint(boolean wasATokenFound, int currentCharCodePoint, int previousCharCodePoint) {
		return
				(wasATokenFound) &&
				//	( (Character.isLetter(currentCharCodePoint) && Character.isDigit(previousCharCodePoint))
				//	|| (Character.isLetter(previousCharCodePoint) && Character.isDigit(currentCharCodePoint))
				( (Character.isLetter(currentCharCodePoint) && !Character.isLetter(previousCharCodePoint))
						|| (Character.isDigit(currentCharCodePoint) && !Character.isDigit(previousCharCodePoint))
						|| (Character.isSpaceChar(currentCharCodePoint)) // && Character.isLetterOrDigit(previousChar)) 
						// this following condition is taken in charge by the following following condition
						//|| ((!Character.isSpaceChar(currentChar) && !Character.isLetterOrDigit(currentChar)) && Character.isLetterOrDigit(previousChar)) 
						|| (!Character.isSpaceChar(currentCharCodePoint) && !Character.isLetterOrDigit(currentCharCodePoint)) 
						)	
						;

	}

	/**
	 * 
	 * @param wasATokenFound
	 * @param currentCharCodePoint
	 * @param previousCharCodePoint
	 * @return isAnOpenningCharCodePoint
	 */
	private boolean isAnOpeningCharCodePoint(boolean wasATokenFound, int currentCharCodePoint, int previousCharCodePoint) {
		return( (!wasATokenFound) &&
				(
						(Character.isLetter(currentCharCodePoint) &&  (!Character.isLetter(previousCharCodePoint) || isUndefined(previousCharCodePoint)))
						|| (Character.isDigit(currentCharCodePoint) &&  (!Character.isDigit(previousCharCodePoint) || isUndefined(previousCharCodePoint)))
						|| (!Character.isLetter(currentCharCodePoint) && !Character.isDigit(currentCharCodePoint) && !Character.isSpaceChar(currentCharCodePoint)) 
						)
				)	;
	}

}




