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
package fr.univnantes.lina.mlnlp.process.tokenization.characterTypeBasedTokenizer;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import fr.univnantes.lina.javautil.CharacterUtil;
import fr.univnantes.lina.mlnlp.model.text.Word;



/**
 * MorphoWord and tokenizer based on the shift of the successive character types.
 * 
 * Usage example
 * String myline = "123.Ceci est une phrase non-tokenisé.";
		List<String> tokenizedLine = new CharacterTypeBasedWordAndSentenceTokenizer(myline).getTokenCoveredTextList();
		System.out.println(tokenizedLine);
 * 
 * @author hernandez
 *
 */
public class CharacterTypeBasedWordAndSentenceTokenizer {

	private  int DEFAULT_INITIAL_NON_UNICODE_CODEPOINT_VALUE = 0;

	private List<List<Word>> sentences  = new ArrayList<List<Word>>();;




	/** Constructor */
	public CharacterTypeBasedWordAndSentenceTokenizer (String text) {
		tokenize(text);
	}

	/**
	 * 
	 * @param text
	 * @param separatorAsToken specify if sequence of separator characters should be considered as token
	 * @param sequenceOfSymbolsAsToken segmentEachConsecutivePairOfNonLetterDigitOrSeparator i.e. punctuation
	 */
	public  CharacterTypeBasedWordAndSentenceTokenizer (String text, Boolean separatorAsToken, Boolean sequenceOfSymbolsAsToken) {
		tokenize(text, separatorAsToken, sequenceOfSymbolsAsToken);

	}

	/**
	 * @return the sentences
	 */
	public List<List<Word>> getSentences() {
		return sentences;
	}

	/**
	 * @return the list of sentences made of coveredText words
	 */
	public List<List<String>> getWordAndSentenceCoveredText() {
		List<List<String>> coveredTextWordAndSentence = new ArrayList<List<String>>();
		for (List<Word> sentence : getSentences()) {
			List<String> coveredSentence = new ArrayList<String>();
			for (Word word : sentence) 
				coveredSentence.add(word.getCoveredText());
			coveredTextWordAndSentence.add(coveredSentence)	;
		}
		return coveredTextWordAndSentence;
	}

	

	/**
	 * @return a sentence per line and words whitespace-separated string
	 */
	public String getWhitespaceSeparatedTokenSentenceCoveredTextList() {
		StringBuffer coveredTextStringBuffer = new StringBuffer();
		
		for (List<Word> sentence : getSentences()) {
			for (int i = 0 ; i < sentence.size() ; i++) { 
				coveredTextStringBuffer.append(sentence.get(i).getCoveredText());
				if (i != sentence.size() -1)  coveredTextStringBuffer.append(" ");
				
			}
			 coveredTextStringBuffer.append(System.getProperty("line.separator"));
		}
		return coveredTextStringBuffer.toString();
	}


	/**
	 * Add a sentence
	 */
	public void addSentence(List<Word> sentence) {
		getSentences().add(sentence);
	}



	/**
	 * tokenize text
	 * sequence of separator characters not considered as token
	 * Symbols in sequence as distinct tokens  
	 * @param text
	 */
	public void tokenize(String text) {
		tokenize(text, false, false); 
	}

	/**
	 * Tokenize text 
	 * @param text
	 * @param separatorAsToken specify if sequence of separator characters should be considered as token
	 * @param sequenceOfSymbolsAsToken segmentEachConsecutivePairOfNonLetterDigitOrSeparator i.e. punctuation
	 * 
	 */
	public void tokenize(String text, Boolean separatorAsToken, Boolean sequenceOfSymbolsAsToken) {

		// set the end of sentence word tokens
		Set<String> strongPunctuationSymbolSet = new HashSet<String> ();
		strongPunctuationSymbolSet.add(".");
		strongPunctuationSymbolSet.add("?");
		strongPunctuationSymbolSet.add("!");
		strongPunctuationSymbolSet.add(";");
		strongPunctuationSymbolSet.add(":");

		// tokenize words
		List<Word> wordTokenizedText = new CharacterTypeBasedWordTokenizer(text, true, sequenceOfSymbolsAsToken).getTokenList(); //getTokenCoveredTextList();

		//
		List<Word> sentence = new ArrayList<Word>();

		for (Word word : wordTokenizedText) {

			// add the current token to the current sentence					
			if (separatorAsToken || !(word.getCoveredText().length() != 0 && CharacterUtil.isSeparator(word.getCoveredText().codePointAt(0))))
				sentence.add(word);


			// is a end sentence token ? 
			// yes if contains \n
			// yes if equal to a given list
			//if (wordToken.getCoveredText().matches(System.getProperty("line.separator")) || strongPunctuationSymbolSet.contains(wordToken)) {
			if (strongPunctuationSymbolSet.contains(word.getCoveredText())) {
				addSentence(sentence);
				sentence = new ArrayList<Word>();
			}

		}
		if (sentence.size() != 0)
				addSentence(sentence);

	}

}
