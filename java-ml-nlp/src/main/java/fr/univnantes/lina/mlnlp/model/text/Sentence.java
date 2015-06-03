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
package fr.univnantes.lina.mlnlp.model.text;

import java.util.ArrayList;
import java.util.List;

/**
 * @author hernandez
 *
 */

/**
 * Sentence representation
 * 
 * TODO everything
 * 
 * coveredText and character offsets ? list of tokens ? token as a superclass for MorphoWord, Word ?
 * 
 * 
 * @author hernandez
 *
 */
public class Sentence {
	//private String coveredText;
	//private int start;
	//private int end;
	private List<Word> words;

	/** Constructor
	 * 
	 * @param coveredText
	 * @param start
	 * @param end
	 */
	/*public Sentence (String coveredText, int start, int end) {
		setCoveredText(coveredText);
		setStart(start);
		setEnd(end);
	}*/

	/** Constructor
	 * 
	 * @param coveredText
	 * @param start
	 * @param end
	 */
	public Sentence (List<Word> subtokens) {
		setWords(subtokens);
	}

	/** Constructor
	 * 
	 * @param coveredText
	 * @param start
	 * @param end
	 */
	/*public Sentence (String coveredText, int start, int end, List<Word> subtokens) {
		setCoveredText(coveredText);
		setStart(start);
		setEnd(end);
		setWords(subtokens);
	}*/



	/**
	 * @param coveredText the coveredText to set
	 */
	/*public void setCoveredText(String coveredText) {
		this.coveredText = coveredText;
	}*/

	/**
	 * @return the start
	 */
	public int getStart() {
		if (getWords().isEmpty()) return -1;
		return getWords().get(0).getStart();
		
	}
	/**
	 * @param start the start to set
	 */
	/*	public void setStart(int start) {
		this.start = start;
	}*/
	/**
	 * @return the end
	 */
	public int getEnd() {
		if (getWords().isEmpty()) return -1;
		return getWords().get(getWords().size()-1).getEnd();
	}
	/**
	 * @param end the end to set
	 */
	/*public void setEnd(int end) {
		this.end = end;
	}*/


	/**
	 * 
	 * @param words
	 */
	public void setWords(List<Word> words) {
		this.words = words;
	}

	/**
	 * 
	 * @param words
	 */
	public List<Word> getWords() {
		return this.words ;
	}

	/**
	 * get
	 * @return
	 */
	public List<String> getWordCoveredTextList () {
		List<String> wordList = new ArrayList<String>();
		for (Word word : getWords()) {
			wordList.add(word.getCoveredText());
		}

		return wordList;
	}

	public String getCoveredText () {
		StringBuffer wordsStringBuffer = new StringBuffer();
		for (Word word : getWords()) {

			wordsStringBuffer.append(word.getCoveredText());
			wordsStringBuffer.append(" ");
		}

		if (wordsStringBuffer.length() >0) 
			wordsStringBuffer.deleteCharAt(wordsStringBuffer.length()-1);
		return wordsStringBuffer.toString();
	}

	/**
	 * get covered text of words in a fine format ; assume that words are not whitespace
	 * @return
	 */
	public String getFineCoveredText () {
		StringBuffer wordsStringBuffer = new StringBuffer();
		for (Word word : getWords()) {
			if (! word.getCoveredText().matches("^\\s+$")) {
				wordsStringBuffer.append(word.getCoveredText());
				wordsStringBuffer.append(" ");
			}
		}
		if (wordsStringBuffer.length() >0) 
			wordsStringBuffer.deleteCharAt(wordsStringBuffer.length()-1);
		return wordsStringBuffer.toString();
	}

	public Boolean isContent () {
		return !getCoveredText().matches("^\\s*$");
	}

	public Boolean isNewLine () {
		//String ct = getCoveredText();
		//System.out.printf("Debug>%s<\n",ct);
		return getCoveredText().matches("^[^\\n]*\\n[^\\n]*$");
	}
	
	public Boolean isDoubleNewLine () {
		return getCoveredText().matches("^[^\\n]*\\n[^\\n]*\\n[^\\n]*$");
	}


}