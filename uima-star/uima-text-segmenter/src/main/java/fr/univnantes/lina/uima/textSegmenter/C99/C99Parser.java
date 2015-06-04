package fr.univnantes.lina.uima.textSegmenter.C99;
/** 
 * UIMA Text Segmenter
 * Copyright (C) 2010, 2011  Christine Jacquin, Nicolas Hernandez
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



import fr.univnantes.lina.uima.textSegmenter.UIMARawText;

import uk.ac.man.cs.choif.extend.structure.ContextVector;
import uk.ac.man.cs.choif.nlp.surface.Punctuation;
import uk.ac.man.cs.choif.nlp.surface.WordList;



/**

 * Extends C99Lina and mainly reimplements the normalize methods used in the segmentation procedure (see segment and segmentW)  
 *  
 * @author jacquin, hernandez
 *
 */
public class C99Parser extends C99LINA {

	private Boolean debug = false;
	
	/*
	 * Properties
	 */
	UIMARawText rawText;

	/*
	 * Accessors
	 */
	
	
	/**
	 * @return the rawText
	 */
	protected UIMARawText getRawText() {
		return rawText;
	}

	/*
	 * Methods 
	 */
	/**
	 * 
	 */
	public C99Parser (UIMARawText rawText) {
		this.rawText = rawText;
	}
	
	/** Redefine the method normalize of the super class C99
	 * we write the same code excepted that we use the result of the WST and Snowball component
	 * to obtain the tokens and their associated stem (stored in the tabTokenStem object)
	  * The S parameter is not be used but is coming from the normalize method which is inherited
	* The tabTokenStem array replaces S in the UIMA implementation
	 */
	public  ContextVector[] normalize(final String[][] S) {
		WordList stopword = WordList.stopwordList();
		ContextVector[] v = new ContextVector[rawText.getSentenceArrayOfTokenFeatureArray().length];
		String token, stem;
		for (int i=rawText.getSentenceArrayOfTokenFeatureArray().length; i-->0;) {
			v[i] = new ContextVector();
			for (int j=rawText.getSentenceArrayOfTokenFeatureArray()[i].length; j-->0;) {
				token = rawText.getSentenceArrayOfTokenFeatureArray()[i][j].getToken().toLowerCase();
				// to take into account the behavior of isWord() method
				// for this method,if a "-" is involved in the token, this one is a word
				// so the "-" is a word to for this method
				if (!token.equals("-")){
					if (Punctuation.isWord(token) && !stopword.has(token)) {
						stem = rawText.getSentenceArrayOfTokenFeatureArray()[i][j].getTokenFeature().toLowerCase();
						ContextVector.inc(stem, 1, v[i]);
					}
				}
			}
		}

		return v;

	}


	/** 
	 * Redefine the method normalize of the super class C99
	 * we write the same code excepted that we use the result of the WST and Snowball component
	 * to obtain the tokens and their associated feature (potentially stem) (stored in the sentenceArrayOfTokenFeatureArray object)
	 * The S parameter is not be used but is coming from the normalize method which is inherited
	 * The sentenceArrayOfTokenFeatureArray array replaces S in the UIMA implementation
	*/

	public ContextVector[] normalize(final String[][] S, ContextVector tf) {
		WordList stopword = WordList.stopwordList();
		ContextVector[] v = new ContextVector[rawText.getSentenceArrayOfTokenFeatureArray().length];
		String token, stem;
		for (int i=rawText.getSentenceArrayOfTokenFeatureArray().length; i-->0;) {
			v[i] = new ContextVector();
			for (int j=rawText.getSentenceArrayOfTokenFeatureArray()[i].length; j-->0;) {
				token = rawText.getSentenceArrayOfTokenFeatureArray()[i][j].getToken().toLowerCase();
				// to take into account the behavior of isWord() method
				// for this method,if a "-" is involved in the token, this one is a word
				// so the "-" is a word to for this method
				if (!token.equals("-")){
					if (Punctuation.isWord(token) && !stopword.has(token)) {
						stem = rawText.getSentenceArrayOfTokenFeatureArray()[i][j].getTokenFeature().toLowerCase();
						ContextVector.inc(stem, 1, v[i]);
						ContextVector.inc(stem, 1, tf);
					}
				}
			}
		}

		return v;
	}

}
