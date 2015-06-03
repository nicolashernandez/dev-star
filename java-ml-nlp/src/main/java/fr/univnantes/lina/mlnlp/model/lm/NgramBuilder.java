/** 
 * Copyright (C) 2015  Nicolas Hernandez
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
package fr.univnantes.lina.mlnlp.model.lm;

import java.util.ArrayList;
import java.util.List;

public class NgramBuilder {

	

	/**
	 * Return a "ngram" (list of words) made of "contextSize" words 
	 * to the right side of the word "wordPosition" in "words" 
	 * 
	 * @param wordPosition
	 * @param contextSize
	 * @param words
	 * @return
	 */
	public static  List<String> buildRightContextNgram(int wordPosition, int contextSize,
			List<String> words) {
		List<String> ngram;
		ngram = new ArrayList<>();
		if (wordPosition > words.size() - contextSize ) {

			for (int i=wordPosition; i< words.size(); i++) 
				ngram.add(words.get(i));
			//	ngrams.add("</s>");
		}
		else {
			for (int i=wordPosition; i< wordPosition+contextSize; i++) 
				ngram.add(words.get(i));
		}
		return ngram;
	}

	/**
	 *  Return a "ngram" (list of words) made of "contextSize" words 
	 * both from the left side and ight side of the word "wordPosition" in "words" 
	 * 
	 * @param wordCounter
	 * @param contextSize context +-res words arround the focus word
	 * @param words
	 * @return
	 */
	public static  List<String> buildCenteredContextNgram(int wordCounter, int contextSize, 
			List<String> words) {
		List<String> ngrams;
		ngrams = new ArrayList<>();
		if (wordCounter < contextSize ) {
			//	ngrams.add("<s>");
			for (int i=0; i<= wordCounter; i++) 
				ngrams.add(words.get(i));
		}
		else {
			for (int i=wordCounter-contextSize+1; i<= wordCounter; i++) 
				ngrams.add(words.get(i));
		}
		if (wordCounter > words.size() - contextSize ) {

			for (int i=wordCounter+1; i< words.size(); i++) 
				ngrams.add(words.get(i));
			//		ngrams.add("</s>");
		}
		else {
			for (int i=wordCounter+1; i< wordCounter+contextSize; i++) 
				ngrams.add(words.get(i));
		}
		return ngrams;
	}

	
	/**
	 * Return a "ngram" (list of words) made of "contextSize" words 
	 * to the left side of the word "wordPosition" in "words" 
	 * 
	 * @param wordPosition
	 * @param contextSize
	 * @param words
	 * @return
	 */
	public static List<String> buildLeftContextNgram(int wordCounter, int contextSize,
			List<String> words) {
		List<String> ngrams = new ArrayList<>();
		if (wordCounter < contextSize ) {
			//ngrams.add("<s>"); // TODO log score of -99
			for (int i=0; i<= wordCounter; i++) 
				ngrams.add(words.get(i));
		}
		else {
			for (int i=wordCounter-contextSize+1; i<= wordCounter; i++) 
				ngrams.add(words.get(i));
		}
		return ngrams;
	}

}
