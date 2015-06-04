package fr.univnantes.lina.uima.textSegmenter.JTextTile;
/** 
 * UIMA Text Segmenter
 * Copyright (C) 2010, 2011  Nicolas Hernandez
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


import java.util.Vector;


import fr.univnantes.lina.uima.textSegmenter.UIMARawText;
import uk.ac.man.cs.choif.nlp.surface.Stopword;



/**

 * Extends JTextTileLINA and mainly reimplements the normalize (preprocess) methods used in the segmentation procedure   
 *  
 * @author jacquin, hernandez
 *
 */
public class JTextTileParser extends JTextTileLINA {

	private Boolean debug = false;
	
	/*
	 * Properties
	 */
	private UIMARawText rawText;

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
	public JTextTileParser (UIMARawText rawText, int windowSize, int stepSize, Stopword stopWords) {
	
		this.rawText = rawText;
		this.C = rawText;
		this.S = stopWords;
		this.w = windowSize;
		this.s = stepSize;
		
		// In the JTextTileLina similarityDetermination() method C seems to be a vectors of word token despite its definition at the global level of the class.
		// It is the same use in preprocess
		// And that s all the place where it is used
		//this.C = new Sentences (); 
		//rawText.text;
	}
	
	
	/** Redefine the method preprocess of the super class JTextTile
	  * considers the tokenFeature as the preprocessing result
	 * here it is not an optimal implementation could have been done in the  UIMARawText initialization
	 */
	public void preprocess() {
		Vector text = C.tokens(); // Text of the collection
		String token; // A token

		/* Construct a dictionary of tokens */
		for (int i=text.size(); i-->0;) {
			token = (String) text.elementAt(i);
			stemOf.put(token, new String(token));
			//System.out.println("Construct a dictionary of tokens "+i);

		}
		
		for (int i=rawText.getSentenceArrayOfTokenFeatureArray().length; i-->0;) {
			System.out.println("i "+i);
			for (int j=rawText.getSentenceArrayOfTokenFeatureArray()[i].length; j-->0;) {
				System.out.println("j "+ j);
				stemOf.put(rawText.getSentenceArrayOfTokenFeatureArray()[i][j].getToken(), rawText.getSentenceArrayOfTokenFeatureArray()[i][j].getTokenFeature());
			}
		}
		//for (int i=rawText.getSentenceArrayOfTokenFeatureArray().length; i-->0;) {
		//	for (int j=rawText.getSentenceArrayOfTokenFeatureArray()[i].length; j-->0;) {
		//		stemOf.put(rawText.getSentenceArrayOfTokenFeatureArray()[i][j].getToken(), rawText.getSentenceArrayOfTokenFeatureArray()[i][j].getTokenFeature());
		//	}
		//}
		

	}


}
