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
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

import org.tartarus.snowball.SnowballProgram;
import org.tartarus.snowball.ext.EnglishStemmer;
import org.tartarus.snowball.ext.FrenchStemmer;

import fr.univnantes.lina.javautil.CharacterUtil;

/**
 * 
 * http://www.regular-expressions.info/unicode.html
 * 
 * 	\d	A digit: [0-9] -> 0
 * 	\p{S}	A symbol -> $
 * [\\p{Sm}||\\p{Sc}||\\p{Sk}||\\p{So}]
	\p{P}	Punctuation: One of !"#$%&'()*+,-./:;<=>?@[\]^_`{|}~ -> .
	[\p{Pd}||\\p{Ps}||\\p{Pe}||\\p{Pi}||\\p{Pf}||\\p{Pc}||\\p{Po}]

	\p{Z} or \p{Separator}: any kind of whitespace or invisible separator. -> .	
	\s	A whitespace character: [ \t\n\x0B\f\r] 
	\p{Lu}	An uppercase letter (simple category) -> A
	\p{Ll}	A lowercase letter (simple category) -> a
	[\p{L}&&[^\p{Lu}]] 	Any letter except an uppercase letter (subtraction)
	\p{L} Any letter
	 \p{M} or \p{Mark}: a character intended to be combined with another character (e.g. accents, umlauts, enclosing boxes, etc.).


 * @author hernandez
 *
 */
public class TextNormalization {

	static final String upperCaseLetter = "A";
	static final String lowercaseLetter = "a";
	static final String symbol = "+";
	static final String number  = "1";

	static final String greek = "I";

	static final String whitespaceCharacter = ".";
	static final String punctuation = ".";
	static final String plusSymbolRegex = "[\\+]";

	static final String upperCaseLetterRegex = "[\\p{Lu}]";
	static final String lowercaseLetterRegex = "[\\p{Ll}]";
	//static final String symbolRegex = "[\\p{Sm}||\\p{Sc}||\\p{Sk}||\\p{So}]"; // math symbols, currency signs, dingbats, box-drawing characters, etc..
	static final String symbolRegex = "[[[\\p{Sm}||\\p{Sc}||\\p{Sk}||\\p{So}]]&&[^+]]"; // math symbols, currency signs, dingbats, box-drawing characters, etc..
	static final String digitRegex  = "[\\p{N}]";
	static final String greekRegex  = "[\\p{InGreek}]";

	static final String whitespaceCharacterRegex = "[\\p{Z}]";
	static final String punctuationRegex = "[\\p{Pd}||\\p{Ps}||\\p{Pe}||\\p{Pi}||\\p{Pf}||\\p{Pc}||\\p{Po}]";


	
	/**
	 * replace each input sequence of a  repeated  character
	 * with a single occurrence of that character
	 * @return the squeezed text       
	 */
	public static String squeezeRepeatedCharacters (String text) {
		char [] chars = text.toCharArray();
		String outputChars = "";

		if (text.equalsIgnoreCase("")) {
			System.err.println("Warning: an empty text to compress ");
		}
		else {
			outputChars = Character.toString(chars[0]);
			if (chars.length >1 )
				for (int i = 0; i < chars.length -1 ; i++) {

					if (chars[i] != chars[i+1])
						outputChars = outputChars+ Character.toString(chars[i+1]);
				}
		}
		return outputChars;
	}


	public static String normalizeNumber (String text) {
		return Pattern.compile(digitRegex).matcher(text).replaceAll(number);

	}
	
	
	/**
	 * translate each character from the input text into a class character
	 * 
	 * @param text
	 * @return the translated text
	 */
	public static String translateToClassCharacter (String text) {
		//		text = Pattern.compile(symbolRegex,java.util.regex.Pattern.UNICODE_CASE).matcher("\\Q"+text+"\\E").replaceAll(symbol);
		text = Pattern.compile(symbolRegex).matcher(text).replaceAll(symbol);

		// bug when it encounters the plus symbol and it attempts to convert it into $; 
		// works when it is into +
		/*Pattern pattern = Pattern.compile("[\\+]");
        Matcher matcher = pattern.matcher("avec 16 % des intentions de vote (+ 3,5 % des voix");
        matcher.replaceAll(symbol);
		 */
		text = Pattern.compile(plusSymbolRegex).matcher(text).replaceAll(symbol);
		text = Pattern.compile(punctuationRegex).matcher(text).replaceAll(punctuation);
		text = Pattern.compile(whitespaceCharacterRegex).matcher(text).replaceAll(whitespaceCharacter);

		text = normalizeNumber(text);
		
		text = Pattern.compile(greekRegex).matcher(text).replaceAll(greek);

		text = Pattern.compile(upperCaseLetterRegex).matcher(text).replaceAll(upperCaseLetter);
		text = Pattern.compile(lowercaseLetterRegex).matcher(text).replaceAll(lowercaseLetter);

		return text;

	}

	/**
	 * sort of normalization
	 * both translate and squeeze
	 * @return the squeezed translated text
	 */
	public static String translateToClassCharactersAndSqueezeRepeatedOnes (String text) {
		/*byte[] b = text.getBytes();
		try {
			text = new String(b, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/

		/*String in = text ;
		System.err.print("Debug: in "+in);
		String replaceText = replace(text);
		System.err.print(" replaceText "+replaceText);
		String compressText = compress(replaceText);
		System.err.println(" compressText "+ compressText);*/
		//return compressText; 
		return squeezeRepeatedCharacters(translateToClassCharacter(text));
	}



	/**
	 * Snowball Tartarus stemming 
	 * 
	 * Based on 
	 * http://snowball.tartarus.org/download.php
	 * in particular the maven packaged version available under lucene
	 * http://mvnrepository.com/artifact/org.apache.lucene/lucene-snowball/3.0.3
	 * 
	 * @param words
	 * @param language
	 * @return the translated text
	 */
	public static String stem (String word, String language) {

		//if (language)
		SnowballProgram stemmer = null;
		if (language.toLowerCase().startsWith("fr"))
			stemmer = new FrenchStemmer();
		else 
			stemmer = new EnglishStemmer();
		stemmer.setCurrent(word);
		stemmer.stem();
		return stemmer.getCurrent();
	}



	/**
	 * Only process words ending by alphabetic characters (at least as many as the maximal suffix length)
	 * Return the word in any other case
	 * @param maximalSuffixLength
	 * @param word
	 * @return
	 */
	public static String getSuffix (int maximalSuffixLength, String word) {
		if (word.length() <= maximalSuffixLength) return word;
		String suffix = word.substring(word.length()-maximalSuffixLength, word.length()) ;
		String regex = "^\\p{L}+$";
		if (!suffix.matches(regex)) return word;
		return suffix;
	}

	/**
	 * Only process words ending by alphabetic characters (at least as many as the maximal suffix length)
	 * Return the word in any other case
	 * @param word
	 * @param stopWords 
	 * @return
	 */
	public static String getGuessedMorphologicalFlexion(String word) {
		String suffix = "";
		
		// the word is ending by vocals
		int index = word.length()-1;
		while ((index >= 0) && CharacterUtil.isVocal(word.codePointAt(index))) 
			index--;

		// the word is ending by vocals then by consonants
		if (index == word.length()-1) {
			// parse the consonants
			while ((index >= 0) && Character.isAlphabetic(word.codePointAt(index)) && !CharacterUtil.isVocal(word.codePointAt(index))) 
				index--;
			// parse the vocals
			while ((index >= 0) && Character.isAlphabetic(word.codePointAt(index)) && CharacterUtil.isVocal(word.codePointAt(index))) 
				index--;
		}
		if (index != word.length()-1) return word.substring(index+1, word.length());
		return word;
	}
	
	/**
	 * Only process words ending by alphabetic characters (at least as many as the maximal suffix length)
	 * Return the word in any other case
	 * @param word
	 * @param stopWords 
	 * @return
	 */
	public static String getGuessedMorphologicalFlexion(String word, Set<String> stopWords) {
		String suffix = "";
		
		if (stopWords != null && stopWords.contains(word.trim().toLowerCase())) return word;
		else return getGuessedMorphologicalFlexion(word);
	}
	
	public static final String LOWERCASE= "-lc";
	public static final String STEM= "-stem";
	public static final String FLEXION= "-flexion";
	public static final String TOCHARCLASS= "-toCharClass";
	public static final String TONUMBERCLASS= "-toNumberClass";

	public static final String SQUEEZEREPEATEDCHAR= "-squeezeRepeatedChar";
	
	/**
	 * @param normalizationOperation
	 * @param language
	 * @param words
	 * @param stopwords TODO
	 * @return
	 */
	public static List<String> normalize(String normalizationOperation, String language,
			List<String> words, Set<String> stopwords) {
		List<String> normalizedWords = new ArrayList<String>();
		if (normalizationOperation.equalsIgnoreCase(LOWERCASE)) {
			for (String word : words)
				normalizedWords.add(word.toLowerCase());
		}
		else if (normalizationOperation.equalsIgnoreCase(STEM)) {
			for (String word : words)
				normalizedWords.add(TextNormalization.stem(word, language));
		}
		else if (normalizationOperation.equalsIgnoreCase(FLEXION)) {
			for (String word : words)
				normalizedWords.add(TextNormalization.getGuessedMorphologicalFlexion(word, stopwords));
		}
		else if (normalizationOperation.equalsIgnoreCase(TOCHARCLASS)) {
			for (String word : words)
				normalizedWords.add(TextNormalization.translateToClassCharacter(word));
		}
		else if (normalizationOperation.equalsIgnoreCase(TONUMBERCLASS)) {
			for (String word : words)
				normalizedWords.add(TextNormalization.normalizeNumber(word));
		}
		else if (normalizationOperation.equalsIgnoreCase(SQUEEZEREPEATEDCHAR)) {
			for (String word : words)
				normalizedWords.add(TextNormalization.squeezeRepeatedCharacters(word));
		}
		else return words;
		return normalizedWords;
	}


}
