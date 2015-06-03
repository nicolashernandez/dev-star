/** 
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
package fr.univnantes.lina.mlnlp.analysis.text.ruleBasedTokenizer;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

import fr.univnantes.lina.app.tokenizer.TokenizerCommandLineParametersParser;


/**
 * Interface for displaying the tokenization result in command line standard output format 
 * 
 * @author hernandez
 *
 */
public class WordSentenceListTokenizerOutput extends Tokenizer {

	public static final String WORD_PER_LINE =  "wpl";
	public final String WHITESPACE_SEPARATED_TOKENS_SENTENCE_PER_LINE = "wst-spl";

	private String outputFormat = "" ; //WORD_PER_LINE;
	private Set<String> newlineTokenSet = new HashSet<String>(); 
	private String tokenWhitespaceCharacterReplacement = "";
	private Boolean preserveInputTextLayout = false;
	private Boolean removeRedundantInvisibleCharactersInOutputLayout = false;


	private List<String> wordList = new ArrayList<String>();
	public List<List<String>> sentenceList = new ArrayList<List<String>>();

	private final String newlineRegex = "\n";
	private Pattern newlineRegexPattern;
	//private List<String> postTokenizerList = new ArrayList<String>();


	/*
	 * 
	 * Constructor
	 */

	/**
	 * Constructor
	 */
	public WordSentenceListTokenizerOutput() {
		super();

		setNewlineRegexPattern(Pattern.compile(getNewlineRegex()));
	}

	/*
	 * getter and setter
	 */
	public Boolean isRemoveRedundantInvisibleCharactersInOutputLayout() {
		return removeRedundantInvisibleCharactersInOutputLayout;
	}

	public void setRemoveRedundantInvisibleCharactersInOutputLayout(Boolean removeRedundantInvisibleCharactersInOutputLayout) {
		this.removeRedundantInvisibleCharactersInOutputLayout = removeRedundantInvisibleCharactersInOutputLayout;
	}

	public Boolean isRegexUnion() {
		return regexUnion;
	}

	public void setRegexUnion(Boolean regexUnion) {
		this.regexUnion = regexUnion;
	}

	public Boolean isPreserveInputTextLayout() {
		return preserveInputTextLayout;
	}

	public void setPreserveInputTextLayout(Boolean preserveInputTextLayout) {
		this.preserveInputTextLayout = preserveInputTextLayout;
	}

	public String getTokenWhitespaceCharacterReplacement() {
		return tokenWhitespaceCharacterReplacement;
	}

	public void setTokenWhitespaceCharacterReplacement(
			String tokenWhitespaceCharacterReplacement) {
		this.tokenWhitespaceCharacterReplacement = tokenWhitespaceCharacterReplacement;
	}

	/**
	 * @return the outputFormat
	 */
	public String getOutputFormat() {
		return outputFormat;
	}

	/**
	 * @param outputFormat the outputFormat to set
	 */
	public void setOutputFormat(String outputFormat) {
		this.outputFormat = outputFormat;
	}


	public Set<String> getNewlineTokenSet() {
		return newlineTokenSet;
	}


	public void setNewlineTokenSet(Set<String> newlineTokenSet) {
		this.newlineTokenSet = newlineTokenSet;
	}




	/**
	 * @return the newlineRegex
	 */
	public String getNewlineRegex() {
		return newlineRegex;
	}





	/**
	 * @return the newlineRegexPattern
	 */
	public Pattern getNewlineRegexPattern() {
		return newlineRegexPattern;
	}


	/**
	 * @param newlineRegexPattern the newlineRegexPattern to set
	 */
	public void setNewlineRegexPattern(Pattern newlineRegexPattern) {
		this.newlineRegexPattern = newlineRegexPattern;
	}




	/*
	 * 
	 */

	/**
	 * 
	 */
	@Override
	public void createAnnotation(String coveredText, int begin, int end, String currentInterTokenString) {


		// sentence annotation

		// if the current token is also a sentence ending token (e.g. '.', '!')
		if (getNewlineTokenSet().contains(coveredText)) {
			sentenceList.add(wordList);
			wordList = new ArrayList<String>();
		}
		else 
			if (getNewlineRegexPattern().matcher(currentInterTokenString).find()) {
				//if (currentInterTokenString.matches("\\n")) {
				sentenceList.add(wordList);
				wordList = new ArrayList<String>();

			}


		// token annotation
		//if (!getTokenWhitespaceCharacterReplacement().equalsIgnoreCase(""))
			coveredText = coveredText.replaceAll("\\s+", getTokenWhitespaceCharacterReplacement());
		wordList.add(coveredText);
		//System.out.println("Debug: >"+coveredText+"<");


	}


	/**
	 * Sweep the output text by removing redundant newline and whitespace characters  
	 */
	@Override
	public void postTokenization() {
		if (! wordList.isEmpty() ) {
			sentenceList.add(wordList);
			wordList = new ArrayList<String>();
		}
		
	}



	/**
	 * @param tokenizerParameter
	 * @param simpleTokenizer
	 * @return
	 */
	public void settings(
			TokenizerCommandLineParametersParser tokenizerParameter
			) {

		super.settings(tokenizerParameter);

		// Output configuration. Set output format
		if (!tokenizerParameter.getOutputFormat().equalsIgnoreCase("")) 
			this.setOutputFormat(tokenizerParameter.getOutputFormat());

		// Output configuration. 
		if (!tokenizerParameter.getNewlineTokenSet().isEmpty())
			this.setNewlineTokenSet(tokenizerParameter.getNewlineTokenSet());

		//Output configuration. 
		if (tokenizerParameter.isPreserveInputTextLayout())
			this.setPreserveInputTextLayout(tokenizerParameter.isPreserveInputTextLayout());

		//Output configuration. 
		if (tokenizerParameter.getRemoveRedundantInvisibleCharactersInOutputLayout())
			this.setRemoveRedundantInvisibleCharactersInOutputLayout(tokenizerParameter.getRemoveRedundantInvisibleCharactersInOutputLayout());

		// Output configuration. 
		if (!tokenizerParameter.getTokenWhitespaceCharacterReplacement().equalsIgnoreCase("")) 
			this.setTokenWhitespaceCharacterReplacement(tokenizerParameter.getTokenWhitespaceCharacterReplacement());

		//return inputText;
		//return inputLines;


	}

	/**
	 * @return the sentenceList
	 */
	public List<List<String>> getSentenceList() {
		return sentenceList;
	}



}
