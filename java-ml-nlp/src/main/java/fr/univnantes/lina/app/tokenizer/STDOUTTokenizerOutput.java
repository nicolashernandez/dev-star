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
package fr.univnantes.lina.app.tokenizer;

import java.util.HashSet;
import java.util.Set;

import fr.univnantes.lina.mlnlp.analysis.text.ruleBasedTokenizer.Tokenizer;


/**
 * Interface for displaying the tokenization result in command line standard output format 
 * 
 * @author hernandez
 *
 */
public class STDOUTTokenizerOutput extends Tokenizer {

	public static final String WORD_PER_LINE =  "wpl";
	public final String WHITESPACE_SEPARATED_TOKENS_SENTENCE_PER_LINE = "wst-spl";

	private String outputFormat = "" ; //WORD_PER_LINE;
	private Set<String> newlineTokenSet = new HashSet<String>(); 
	private String tokenWhitespaceCharacterReplacement = "";
	private Boolean preserveInputTextLayout = false;
	private Boolean removeRedundantInvisibleCharactersInOutputLayout = false;

	private String outputText = "";
	
	/*
	 * 
	 * Constructor
	 */
	
	/**
	 * Constructor
	 */
	public STDOUTTokenizerOutput() {
		super();
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




	/*
	 * 
	 */

	/**
	 * 
	 */
	@Override
	public void createAnnotation(String coveredText, int begin, int end, String currentInterTokenString) {

		//		if (getVerboseMode()) System.err.println("Debug: currentInterTokenString >"+currentInterTokenString+"<");
		if (isVerboseMode()) System.err.println("Debug: createAnnotation previousNonTokenChar>"+currentInterTokenString+"< coveredText>"+coveredText+"<");

		if (isPreserveInputTextLayout()) 
			if (!isRemoveRedundantInvisibleCharactersInOutputLayout()) 
				System.out.print(currentInterTokenString);
			else outputText += currentInterTokenString;

		if (!getTokenWhitespaceCharacterReplacement().equalsIgnoreCase(""))
			coveredText = coveredText.replaceAll("\\s+", getTokenWhitespaceCharacterReplacement());

		//System.err.println("Debug: getOutputFormat >"+getOutputFormat()+"< wst-spl>"+WHITESPACE_SEPARATED_TOKENS_SENTENCE_PER_LINE+"<");

		if (getOutputFormat().equalsIgnoreCase(WHITESPACE_SEPARATED_TOKENS_SENTENCE_PER_LINE)) {
			//System.err.println("Debug:  WHITESPACE_SEPARATED_TOKENS_SENTENCE_PER_LINE");

			if (getNewlineTokenSet().contains(coveredText)) 		
				if (!isRemoveRedundantInvisibleCharactersInOutputLayout()) 
					System.out.println(coveredText);
				else 
					outputText += coveredText;
			else 
				if (!isRemoveRedundantInvisibleCharactersInOutputLayout()) 
					System.out.print(coveredText+" ");
				else
					outputText += coveredText+" ";
		}
		else {
			// default
			//System.err.println("Debug: WORD_PER_LINE");
			// if (getOutputFormat() == WORD_PER_LINE)
			if (!isRemoveRedundantInvisibleCharactersInOutputLayout()) 
				System.out.println(coveredText+"\t"+begin+"\t"+end);
			else 
				outputText += coveredText+"\t"+begin+"\t"+end;
			if (getNewlineTokenSet().contains(coveredText)) 	
				if (!isRemoveRedundantInvisibleCharactersInOutputLayout()) 
					System.out.println("");
				else 
					outputText += coveredText+" ";

		}


	}


	/**
	 * Sweep the output text by removing redundant newline and whitespace characters  
	 */
	@Override
	public void postTokenization() {
		if (isRemoveRedundantInvisibleCharactersInOutputLayout()) {
			for (String line : outputText.split("\n")) {
				if (!line.trim().equalsIgnoreCase("")) {
					String lineAfter = line.replaceAll("\\s+", " ");
					lineAfter = lineAfter.replaceAll("^\\s+", "");
					lineAfter = lineAfter.replaceAll("\\s+$", "");
					//				if (getVerboseMode()) System.err.println("Debug: line before>"+line+"<");
					//				if (getVerboseMode()) System.err.println("Debug: line after>"+lineAfter+"<");
					System.out.println(lineAfter);
				}
			}
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
/*
		// Loading stringUnitsList
		for (String stringUnitsFilePath : simpleTokenizerParameter.getInputFilePathList()) {
			this.addStringUnitsList(IOUtilities.readTextFileAsStringList(stringUnitsFilePath));

		}
		if (simpleTokenizerParameter.isRegexUnion()) 
			this.setRegexUnion(simpleTokenizerParameter.isRegexUnion());
		
		//if (!simpleTokenizerParameter.getStringUnitsFilePath().equalsIgnoreCase("")) {
		//	simpleTokenizer.setStringUnitsList(IOUtilities.readTextFileAsStringList(simpleTokenizerParameter.getStringUnitsFilePath()));
		//}
		if (!simpleTokenizerParameter.getGlobalRegexFlag().equalsIgnoreCase("")) {
			this.setGlobalRegexFlag(simpleTokenizerParameter.getGlobalRegexFlag());
		}

		// Loading text file options
		if (simpleTokenizerParameter.isParseAsMultilinesInput()) 
			this.setMultiLineInput(simpleTokenizerParameter.isParseAsMultilinesInput());


		// Set characters Glue Functioning
		if (!simpleTokenizerParameter.getCharacterGlueFunctioningMap().isEmpty()) {
			for (int codePoint : simpleTokenizerParameter.getCharacterGlueFunctioningMap().keySet()) {
				this.addCharacterGlueFunctioning(codePoint, simpleTokenizerParameter.getCharacterGlueFunctioningMap().get(codePoint));
			}
		}

		// Set verbose mode for tokenizer
		if (simpleTokenizerParameter.isVerboseMode()) 
			this.setVerboseMode(simpleTokenizerParameter.isVerboseMode());
*/

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



}
