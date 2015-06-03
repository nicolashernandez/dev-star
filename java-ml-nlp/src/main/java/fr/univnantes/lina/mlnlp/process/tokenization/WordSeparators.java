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
package fr.univnantes.lina.mlnlp.process.tokenization;

import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * static methods to recognize in a string right/wrong word separators and save/remove it in/from a set of offsets
 *  
 * @author hernandez
 *
 */
public class WordSeparators {


	/** whitespace as word separator 
	 * Used in tokenizers: email
	 */
	public static void wsAsWordSep(String sentence, Set<Integer> separatorOffsets) {
		String WS_AS_WORD_SEPARATOR_PATTERN = "\\s+"; 
		Pattern wordSeparatorPattern = Pattern.compile(WS_AS_WORD_SEPARATOR_PATTERN);
		Matcher wordMatcher = wordSeparatorPattern.matcher(sentence);
		while (wordMatcher.find()) {
			separatorOffsets.add(wordMatcher.start());
			separatorOffsets.add(wordMatcher.end());
		}
	}

	/** punkt ending word as word separator	 
	 * Used in tokenizers: email
	 */
	public static void punktEndingWordAsWordSep (String sentence, Set<Integer> separatorOffsets) {
		String PUNCT_ENDING_WORD_PATTERN = "[^\\p{Punct}](\\p{Punct}+|…)\\s+";  // … does not work
		Pattern wordSeparatorPattern = Pattern.compile(PUNCT_ENDING_WORD_PATTERN);
		Matcher wordMatcher = wordSeparatorPattern.matcher(sentence);
		while (wordMatcher.find()) {
			separatorOffsets.add(wordMatcher.start()+1);
			//	separatorOffsets.add(wordMatcher.start()+2);
		}
	}

	/** punkt starting word as word separator 	 * Used in tokenizers: email
	 */
	public static void punktStartingWordAsSeparator(String sentence, Set<Integer> separatorOffsets) {
		String PUNCT_STARTING_WORD_PATTERN = "\\s+\\p{Punct}[^\\p{Punct}]"; 
		Pattern wordSeparatorPattern = Pattern.compile(PUNCT_STARTING_WORD_PATTERN);

		Matcher wordMatcher = wordSeparatorPattern.matcher(sentence);
		while (wordMatcher.find()) {
			separatorOffsets.add(wordMatcher.end()-1);
			separatorOffsets.add(wordMatcher.end()-2);
		}
	}

	/** punkt ending line as word separator 
	 * Warning 
	 * :-) 
	 * Lines ending by ...
	 * Used in tokenizers: email
	 */
	public static void punktEndingLineAsWordSeparator(String sentence, Set<Integer> separatorOffsets) {

		String PUNCT_ENDING_LINE_WORD_PATTERN = "[^\\p{Punct}]\\p{Punct}+$"; 
		Pattern wordSeparatorPattern = Pattern.compile(PUNCT_ENDING_LINE_WORD_PATTERN);

		Matcher wordMatcher = wordSeparatorPattern.matcher(sentence);
		while (wordMatcher.find()) {
			separatorOffsets.add(wordMatcher.start()+1);
			separatorOffsets.add(wordMatcher.end());
		}
	}

	/** contraction as word separator 
	 * do not consider don't can't
	 * 	 * Used in tokenizers: email
	 */
	public static void contractionAsWordSeparator(String sentence, Set<Integer> separatorOffsets) {
		String CONTRACTION_WORD_SEP_PATTERN = "\\p{L}'(m|re|s|d|ve|ll)\\s+"; 
		Pattern wordSeparatorPattern = Pattern.compile(CONTRACTION_WORD_SEP_PATTERN,Pattern.CASE_INSENSITIVE);

		Matcher wordMatcher = wordSeparatorPattern.matcher(sentence);
		while (wordMatcher.find()) {
			separatorOffsets.add(wordMatcher.start()+1);
		}
	}
	
	/** apostrophe as word separator 
	 * l'occasionnel
	 * 	 * Used in tokenizers: email
	 */
	public static void  apostropheAsWordSeparator(String sentence, Set<Integer> separatorOffsets) {
		String CONTRACTION_WORD_SEP_PATTERN = " [lctmjs]'\\p{L}"; 
		Pattern wordSeparatorPattern = Pattern.compile(CONTRACTION_WORD_SEP_PATTERN,Pattern.CASE_INSENSITIVE);

		Matcher wordMatcher = wordSeparatorPattern.matcher(sentence);
		while (wordMatcher.find()) {
			separatorOffsets.add(wordMatcher.start()+3);
		}
	}

	/**
	 * etc. P.S. not as word separator
	 * Used in tokenizers: email

	 */
	public static void etcDotNotAsWordSeparator(String sentence, Set<Integer> separatorOffsets) {

		String ACRONYM_PATTERN = "\\setc."; 
		Pattern wordSeparatorPattern = Pattern.compile(ACRONYM_PATTERN,Pattern.CASE_INSENSITIVE);

		Matcher wordMatcher = wordSeparatorPattern.matcher(sentence);
		while (wordMatcher.find()) {
			separatorOffsets.remove(wordMatcher.end()-1);
		}
		ACRONYM_PATTERN = "\\sP.S."; 
		wordSeparatorPattern = Pattern.compile(ACRONYM_PATTERN,Pattern.CASE_INSENSITIVE);

		wordMatcher = wordSeparatorPattern.matcher(sentence);
		while (wordMatcher.find()) {
			separatorOffsets.remove(wordMatcher.end()-1);
		}
	}

	/**
	 * Second name abbreviation not as word separator 
	 * Paul M. Bucalo
	 * Used in tokenizers: email
	 */
	//		String NAME_ABREVIATION_PATTERN = "M\\."; 
	public static void sndNameAbbrevNotAsWordSeparator(String sentence, Set<Integer> separatorOffsets) {
		String NAME_ABREVIATION_PATTERN = "\\s\\p{Lu}\\. +[^\\s]"; 
		Pattern wordSeparatorPattern = Pattern.compile(NAME_ABREVIATION_PATTERN);

		Matcher wordMatcher = wordSeparatorPattern.matcher(sentence);
		while (wordMatcher.find()) {
			//separatorOffsets.remove(wordMatcher.end()-1);
			separatorOffsets.remove(wordMatcher.start()+2);

		}
	}

	/** end of sentence as Word separator	 
	 *  Used in tokenizers: email
	 */
	public static void sentenceEndAsWordSeparator(String sentence, Set<Integer> separatorOffsets) {
		separatorOffsets.add(sentence.length());
	}
}
