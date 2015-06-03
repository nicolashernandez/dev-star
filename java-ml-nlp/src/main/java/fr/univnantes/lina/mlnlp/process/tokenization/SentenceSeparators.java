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
 * static methods to recognize in a string right/wrong sentence separators and save/remove it in/from a set of offsets
 *  
 * @author hernandez
 *
 */
public class SentenceSeparators {


	/**
	 * Empty lines as sentence separator
	 * Used in tokenizers: email
	 * @param text
	 * @param separatorOffsets
	 */
	public static void emptyLineAsSentSep(String text, Set<Integer> separatorOffsets) {
		String EMPTY_LINE_PATTERN = "[^\\n]\\n\\n\\s*[^\\n]"; 
		Pattern sentenceSeparatorPattern = Pattern.compile(EMPTY_LINE_PATTERN);
		Matcher sentenceMatcher = sentenceSeparatorPattern.matcher(text);
		while (sentenceMatcher.find()) {
			separatorOffsets.add(sentenceMatcher.start()+1);
			separatorOffsets.add(sentenceMatcher.end()-1);
		}
	}

	/**
	 * Strong punct at the end of a line as sentence separator 
	 * Used in tokenizers: email
	 * @param text
	 * @param separatorOffsets
	 */
	public static void strongPunktAtLineEndAsSentSep(String text,
			Set<Integer> separatorOffsets) {
		Pattern sentenceSeparatorPattern;
		Matcher sentenceMatcher;
		String STRONG_PUNCT_ENDING_LINE_PATTERN = "[\\.!\\?:]\\n\\s*[^\\s]"; 
		sentenceSeparatorPattern = Pattern.compile(STRONG_PUNCT_ENDING_LINE_PATTERN);
		sentenceMatcher = sentenceSeparatorPattern.matcher(text);
		while (sentenceMatcher.find()) {
			separatorOffsets.add(sentenceMatcher.start()+1);
			separatorOffsets.add(sentenceMatcher.end()-1);
		}
	}


	/** Line only made of punct as sentence separator 
	 * Used in tokenizers: email
	 * @param text
	 * @param separatorOffsets
	 */
	public static void lineOnlyMadeOfPunktAsSentSep(String text,
			Set<Integer> separatorOffsets) {
		Pattern sentenceSeparatorPattern;
		Matcher sentenceMatcher;
		String PUNCT_LINE_PATTERN = "\\n[\\p{Punct} ]+\\n"; 
		sentenceSeparatorPattern = Pattern.compile(PUNCT_LINE_PATTERN);
		sentenceMatcher = sentenceSeparatorPattern.matcher(text);
		while (sentenceMatcher.find()) {
			separatorOffsets.add(sentenceMatcher.start()+1);
			separatorOffsets.add(sentenceMatcher.end());
		}
	}

	/** Strong punct inside a line as sentence separator 
	 * Warning 1607 ... 0419
	 * Paul M. Bucalo
	 * Used in tokenizers: email
	 * @param text
	 * @param separatorOffsets
	 */
	public static  void strongPunctInsideALine(String text,
			Set<Integer> separatorOffsets) {
		Pattern sentenceSeparatorPattern;
		Matcher sentenceMatcher;
		String STRONG_PUNCT_INSIDE_LINE_PATTERN = "[\\.!\\?:] +[^\\s]"; 
		sentenceSeparatorPattern = Pattern.compile(STRONG_PUNCT_INSIDE_LINE_PATTERN);
		sentenceMatcher = sentenceSeparatorPattern.matcher(text);
		while (sentenceMatcher.find()) {
			separatorOffsets.add(sentenceMatcher.start()+1);
			separatorOffsets.add(sentenceMatcher.end()-1);
		}
	}





	/**
	+"Here some text without strong punkt at the end\n"
	+"> Well I also bought a D-Link DWL-G650 and it didn't work with the hoary\n"
	 * Used in tokenizers: email

	 * @param text
	 * @param separatorOffsets
	 */
	public static  void lineWoStrongPunktEndingButWithNewSentenceStartNextLineAsSentSep(String text,
			Set<Integer> separatorOffsets) {
		Pattern sentenceSeparatorPattern;
		Matcher sentenceMatcher;
		String QUOTED_LINE_PRECEDED_BY_A_NON_QUOTED_WO_STRONG_PUNKT_PATTERN = "\\n[^>][^\\n>]+\\n>"; 
		sentenceSeparatorPattern = Pattern.compile(QUOTED_LINE_PRECEDED_BY_A_NON_QUOTED_WO_STRONG_PUNKT_PATTERN);
		sentenceMatcher = sentenceSeparatorPattern.matcher(text);
		while (sentenceMatcher.find()) {
			//System.out.printf("Debug: >%s<\n",sentenceMatcher.group(0));

			separatorOffsets.add(sentenceMatcher.end()-2);
			separatorOffsets.add(sentenceMatcher.end()-1);
		}
	}


	/**
	 * Etc
	 * 
	 * Case to not consider as separator
	 * etc.
	 * P.S .
	 * Used in tokenizers: email

	 * @param text
	 * @param separatorOffsets
	 */
	public static  void etcDotNotSentSep(String text,
			Set<Integer> separatorOffsets) {
		String ACRONYM_PATTERN = "etc.\\s+"; 

		Pattern  sentenceSeparatorPattern = Pattern.compile(ACRONYM_PATTERN,Pattern.CASE_INSENSITIVE);

		Matcher sentenceMatcher = sentenceSeparatorPattern.matcher(text);
		while (sentenceMatcher.find()) {
			separatorOffsets.remove(sentenceMatcher.start()+4);
			separatorOffsets.remove(sentenceMatcher.end());
		}

		ACRONYM_PATTERN = "P\\.S\\.\\s+"; 
		sentenceSeparatorPattern = Pattern.compile(ACRONYM_PATTERN,Pattern.CASE_INSENSITIVE);
		sentenceMatcher = sentenceSeparatorPattern.matcher(text);
		while (sentenceMatcher.find()) {
			separatorOffsets.remove(sentenceMatcher.start()+4);
			separatorOffsets.remove(sentenceMatcher.end());
		}
	}
	/**
	 * second name abbreviation not as a sentence separator
	 * Case to not consider as separator 
	 * Paul M. Bucalo
	 * 	 * Used in tokenizers: email

	 * @param text
	 * @param separatorOffsets
	 */
	public static  void sndNameAbbrevNotAsSentSep(String text,
			Set<Integer> separatorOffsets) {
		//		String NAME_ABREVIATION_PATTERN = "M\\."; 
		String NAME_ABREVIATION_PATTERN = "\\s\\p{Lu}\\. +[^\\s]";
		Pattern sentenceSeparatorPattern = Pattern.compile(NAME_ABREVIATION_PATTERN);

		Matcher sentenceMatcher = sentenceSeparatorPattern.matcher(text);
		while (sentenceMatcher.find()) {
			separatorOffsets.remove(sentenceMatcher.end()-1);
			separatorOffsets.remove(sentenceMatcher.start()+3);

		}
	}
	/**
	 * Case to not consider as separator 
	 * 1. numeric item of a list at the beggining of a document
	 * Used in tokenizers: email

	 * @param text
	 * @param separatorOffsets
	 */
	public static  void firstNumItemStartingADocNotAsSentSep (String text,
			Set<Integer> separatorOffsets) {
		String FIRST_NUM_ITEM_PATTERN = "^\\d+\\. +[^\\s]"; 
		Pattern sentenceSeparatorPattern;
		Matcher sentenceMatcher;
		sentenceSeparatorPattern = Pattern.compile(FIRST_NUM_ITEM_PATTERN);

		sentenceMatcher = sentenceSeparatorPattern.matcher(text);
		while (sentenceMatcher.find()) {
			//System.err.printf("Debug: matched >%s<\n", sentenceMatcher.group(0));
			separatorOffsets.remove(sentenceMatcher.end()-1);
			separatorOffsets.remove(sentenceMatcher.start()+2);

		}
	}
	/**
	 * 	numeric Items Starting Line Not As Sentence SentenceSeparators 
	 *  Used in tokenizers: email

	 * @param text
	 * @param separatorOffsets
	 */
	public static  void numItemStartingLineNotAsSentSep(String text,
			Set<Integer> separatorOffsets) {
		String NUM_ITEM_PATTERN = "\\n\\d+\\. [^\\s]";  
		Pattern sentenceSeparatorPattern;
		Matcher sentenceMatcher;
		sentenceSeparatorPattern = Pattern.compile(NUM_ITEM_PATTERN);
		sentenceMatcher = sentenceSeparatorPattern.matcher(text);
		while (sentenceMatcher.find()) {
			//System.err.printf("Debug: matched >%s<\n", sentenceMatcher.group(0));
			separatorOffsets.remove(sentenceMatcher.end()-1);
			separatorOffsets.remove(sentenceMatcher.end()-2);
			//separatorOffsets.remove(sentenceMatcher.start()+3); // cannot be used because we do not know the number of \\d ; actually we could create as many as cases as digit size... we prefer remove the ' +' before the last char and express a remove from the end

		}
	}

	/** 
	 * end of the document as a sentence separator 
	 * Used in tokenizers: email

	 * @param text
	 * @param separatorOffsets
	 */
	public static  void textEndAsSentSep(String text,
			Set<Integer> separatorOffsets) {


		separatorOffsets.add(text.length());
	}




}
