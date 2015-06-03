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
import java.util.List;

import fr.univnantes.lina.javautil.CharacterUtil;
import fr.univnantes.lina.mlnlp.model.text.Word;



/**
 * MorphoWord tokenizer based on the shift of the successive character types.
 * 
 * Usage example
 * String myline = "123.Ceci est une phrase non-tokenisé.";
		List<String> tokenizedLine = new CharacterTypeBasedWordTokenizer(myline).getTokenCoveredTextList();
		System.out.println(tokenizedLine);
 * 
 * @author hernandez
 *
 */
public class CharacterTypeBasedWordTokenizer {

	private  int DEFAULT_INITIAL_NON_UNICODE_CODEPOINT_VALUE = 0;

	private List<Word> tokenList;




	/** Constructor */
	public CharacterTypeBasedWordTokenizer (String text) {
		tokenList = new ArrayList<Word>();
		tokenize(text);
	}
	
	/**
	 * 
	 * @param text
	 * @param separatorAsToken specify if sequence of separator characters should be considered as token
	 * @param sequenceOfSymbolsAsToken segmentEachConsecutivePairOfNonLetterDigitOrSeparator i.e. punctuation
	 */
	public CharacterTypeBasedWordTokenizer (String text, Boolean separatorAsToken, Boolean sequenceOfSymbolsAsToken) {
		tokenList = new ArrayList<Word>();
		tokenize(text,separatorAsToken, sequenceOfSymbolsAsToken);
	}

	/**
	 * @return the tokenList
	 */
	public List<Word> getTokenList() {
		return tokenList;
	}

	/**
	 * @return the token coveredText List
	 */
	public List<String> getTokenCoveredTextList() {
		List<String> coveredTextList = new ArrayList<String>();
		for (Word word : getTokenList()) {
			coveredTextList.add(word.getCoveredText());
		}
		return coveredTextList;
	}

	/**
	 * @return the token coveredText array
	 */
	public String[] getTokenCoveredTextArray() {
		String[] tokenizedSourceLineArray = new String[getTokenCoveredTextList().size()];
		tokenizedSourceLineArray = getTokenCoveredTextList().toArray(tokenizedSourceLineArray);
		return tokenizedSourceLineArray;
	}
	
	/**
	 * @return the token coveredText List
	 */
	public String getWhitespaceSeparatedTokenCoveredTextList() {
		String coveredText = "";
		for (Word word : getTokenList()) {
			coveredText += word.getCoveredText()+ " ";
		}
		return coveredText.trim();
	}
	

	/**
	 * @return the tokenList
	 */
	public void addToken(Word word) {
		getTokenList().add(word);
	}

	/**
	 * @return the tokenList
	 */
	public void  addToken(String coveredText, int start, int end) {
		getTokenList().add(new Word (coveredText, start, end));
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

		// initial value, assumed not correspond to any unicode char, should not be found in a text...
		int previousCharCodePoint = DEFAULT_INITIAL_NON_UNICODE_CODEPOINT_VALUE; 
		// TODO is is possible to start at  the character offset 1 instead of 0
		// and to get before the codepoint of the character 0 to start the process ?

		int start = 0;


		// for each text character 
		for (int index = 0; index < text.length(); index++) {
			//Character aCharacter = new Character(currentChar);
			int currentCharCodePoint = text.codePointAt(index);

			// if consecutive character types are different
			if (CharacterUtil.areDifferent(currentCharCodePoint, previousCharCodePoint)) {
				// if it not the first character, then consider the fact as ending a token
				if ((Character.getType(previousCharCodePoint) != DEFAULT_INITIAL_NON_UNICODE_CODEPOINT_VALUE) && (separatorAsToken || !(CharacterUtil.isSeparator(previousCharCodePoint))) ) 
					if (start != index && index != 0) 
						addToken(text.substring(start, index), start, index);
				start = index;
			}
			else {
				// if sequence of not letter digit or separator char, then for each char we create a token
				// for all others sequence of letter, digit or separator we do not create a token  
				if (!sequenceOfSymbolsAsToken && CharacterUtil.isNotLetterDigitOrSeparator(currentCharCodePoint)) {
					addToken(text.substring(start, index), start, index);
					start = index;
				}
			}

			/*System.err.print("Debug: currChar>"+StringUtilities.codePointToString(currentCharCodePoint)
					+"< codePoint>"+currentCharCodePoint
					+"< type>"+	Character.getType(currentCharCodePoint)
					+"< prevChar>"+StringUtilities.codePointToString(previousCharCodePoint)
					+"< begin>"+tokenPreviouslyFoundBegin
					+"< end>"+index+"<");
			// if current alpha
			if (Character.isLetter(currentCharCodePoint)) System.err.print(" isLetter\n");
			// if current num
			else if (Character.isDigit(currentCharCodePoint)) System.err.print(" isDigit\n");
			// if current separator
			else if (isSeparator(currentCharCodePoint))  System.err.print(" isSpaceOrLineSeparator\n");
			// if current symbol
			else System.err.print(" something else\n");
			 */

			previousCharCodePoint = currentCharCodePoint;

		}
		// end an annotation for the last non whitespace character encountered
		if ((Character.getType(previousCharCodePoint) != DEFAULT_INITIAL_NON_UNICODE_CODEPOINT_VALUE) && (separatorAsToken ||!(CharacterUtil.isSeparator(previousCharCodePoint)))) 
			addToken(text.substring(start, text.length()), start, text.length());

		/*Iterator<Word> tokenListIterator = getTokenList().iterator();
					while (tokenListIterator.hasNext()) {
						System.err.printf("Debug: token >%s<\n", tokenListIterator.next().getCoveredText());
					}*/

	}

}
