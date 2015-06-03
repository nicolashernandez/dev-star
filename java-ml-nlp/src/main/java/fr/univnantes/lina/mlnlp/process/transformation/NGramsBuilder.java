/** 
 * 
 * Copyright (C) 2013  Nicolas Hernandez
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
package fr.univnantes.lina.mlnlp.process.transformation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import fr.univnantes.lina.mlnlp.model.text.NLPTextSerializationConst;

/**
 * NGrams Generator from various data structures :List<String>, String array and String
 * 
 * @author hernandez
 * 
 * TODO generalize from String to Object
 * TODO ngramify from 1 to n and not only at n
 *
 */
public class NGramsBuilder {

	/**
	 * Generate a list of characters from a text
	 * @return List of characters
	 */
	static public List<String> textToCharactersList (String text) {
		List<String> stringCharacterList = new ArrayList<String>();

		for(char c : "abc".toCharArray()) {
			stringCharacterList.add(String.valueOf(c));
		}
		return stringCharacterList;
	}
	
	/**
	 * Generate a list of characters from a wordTokenList
	 * @param wordTokensList
	 * @param wordTokenSeparator useful for turning a sentence of words into characters by considering a delimiter between words
	 * @return List of characters
	 */
	static public List<String> wordTokenListToCharactersList (List<String> wordTokensList, String wordTokenSeparator) {
		List<String> stringCharacterList = new ArrayList<String>();

		for (int i = 0 ; i < wordTokensList.size() ; i++) {
			String wordToken = wordTokensList.get(i);
			stringCharacterList.addAll(textToCharactersList(wordToken));
			// 
			if (!wordTokenSeparator.equalsIgnoreCase("")) {
				if (i != wordTokensList.size() -1) stringCharacterList.add(wordTokenSeparator);
			}
		}
		
		return stringCharacterList;
	}
	
	/**
	 * Generate n-grams of size N with tokens from a List<String> of tokens
	 * http://stackoverflow.com/questions/7591258/fast-n-gram-calculation
	 * @return List of n-grams
	 */
	static public List<String> ngramify (List<String> tokensList, int max) {
		List<String> ngramsList = new ArrayList<String>();

		int tokenListSize = tokensList.size();

		for (int i = 0 ; i < tokenListSize - max+1 ; i++) {
			String ngram = "";
			for (int j = i+max; j < ( tokenListSize < i+max ? tokenListSize+1  : i+ max+1) ; j++ ) {
				for (int k =i; k < j; k++ ) {
					ngram = ngram + NLPTextSerializationConst.WHITESPACE_SEPARATOR + tokensList.get(k);
				}
			}
			ngram = ngram.replaceFirst(NLPTextSerializationConst.WHITESPACE_SEPARATOR, "");
			//System.out.println("Debug: ngram "+ ngram);

			ngramsList.add(ngram);
		}
		return ngramsList;
	}

	/**
	 * Generate n-grams of size N after applying a simple word tokenizer on a given text first
	 * @return List of n-grams
	 */
	static public List<String> ngramify (String tokensString, int max) {
		// simple tokenizer (split by ws character)
		String tokensStringArray[] = tokensString.split("\\s+");
		//System.out.print("Debug: tokensStringArray ");
		//for (String w : tokensStringArray) {
		//	System.out.print(" "+ w);
		//}
		//System.out.println();
		return ngramify(Arrays.asList(tokensStringArray), max);  
	}

	/**
	 * Generate n-grams of size N with tokens from a String Array of tokens
	 * @return List of n-grams
	 */
	static public List<String> ngramify (String tokensStringArray[],  int max) {
		return ngramify(Arrays.asList(tokensStringArray), max);  
	}

	/**
	 * Generate a list of bi-grams from a List<String> of tokens
	 * @return a bi-grams list
	 */
	static public List<String> bigram (List<String> stringList) {  
		return ngramify(stringList, 2);  
	}

	/**
	 * Generate a list of tri-grams from a List<String> of tokens
	 * @return a tri-grams list
	 */
	static public List<String> trigram (List<String> stringList) {  
		return ngramify(stringList, 3);  
	}

	/**
	 * Generate a list of bi-grams from a given text (by simple word tokenizer)
	 * @return a bi-grams list
	 */
	static public List<String> bigram (String string) {  
		return ngramify(string, 2);  
	}

	/**
	 * Generate a list of tri-grams from a given text (by simple word tokenizer)
	 * @return a tri-grams list
	 */
	static public List<String> trigram (String string) {  
		return ngramify(string, 3);  
	}

}
