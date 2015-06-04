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
package fr.univnantes.lina.uima.mlnlp.process.tokenization;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.jcas.JCas;

import fr.univnantes.lina.mlnlp.analysis.text.ruleBasedTokenizer.Tokenizer;
import fr.univnantes.lina.uima.common.cas.AnnotationUtils;



/**
 * Interface for creating UIMA annotation resulting from the tokenization

 * @author hernandez
 *
 */
public class UIMAAnnotationTokenizerOutput extends Tokenizer {

	private JCas aJCas;
	private String tokenTypeName;
	private Boolean processSentenceSplitting;
	private Boolean processNonEmptyNewlinesAsSentences;
	private String sentenceTypeName;
	private List<Integer> sentenceEndingTokensEndOffsetList;
	private Set<String> sentenceEndingTokensSet;

	private int lastEndOffset = 0;
	private String contextCoveredText; 
	private final String newlineRegex = "\n";
	private Pattern newlineRegexPattern;

	/*
	 * 
	 * Constructor
	 */

	/**
	 * Constructor
	 * 
	 * @param aJCas
	 * @param tokenTypeName
	 * @param sentenceTypeName
	 * @param processSentenceSplitting
	 * @param sentenceEndingTokens
	 * @param processNonEmptyNewlinesAsSentences
	 */
	public UIMAAnnotationTokenizerOutput(JCas aJCas, String contextCoveredText, String tokenTypeName, String sentenceTypeName, Boolean processSentenceSplitting, String[] sentenceEndingTokens,  Boolean processNonEmptyNewlinesAsSentences) {
		super();
		this.setaJCas(aJCas);
		this.setContextCoveredText(contextCoveredText);
		this.setTokenTypeName(tokenTypeName);
		this.setSentenceTypeName(sentenceTypeName);
		this.setProcessSentenceSplitting(processSentenceSplitting);
		this.setProcessNonEmptyNewlinesAsSentences(processNonEmptyNewlinesAsSentences);
		setSentenceEndingTokensSet(new HashSet<String>(Arrays.asList(sentenceEndingTokens)));
		setSentenceEndingTokensEndOffsetList(new ArrayList<Integer>());
		
		setNewlineRegexPattern(Pattern.compile(getNewlineRegex()));
	}


	/*
	 * 
	 */

	/**
	 * Create word and sentence annotations
	 * @throws AnalysisEngineProcessException 
	 * 
	 */
	@Override
	public void createAnnotation(String coveredText, int begin, int end, String currentInterTokenString)  {
		// sentence annotation
		try{
			if (getProcessSentenceSplitting()) {
				// if the current token is also a sentence ending token (e.g. '.', '!')
				if (getSentenceEndingTokensSet().contains(coveredText)) {
					AnnotationUtils.createAnnotation(this.getaJCas(), this.getSentenceTypeName(), lastEndOffset , end);
					lastEndOffset = end;
				}
				else if (processNonEmptyNewlinesAsSentences)
					//System.err.println("Debug: currentInterTokenString>"+currentInterTokenString+"<");

					if (getNewlineRegexPattern().matcher(currentInterTokenString).find()) {
					//if (currentInterTokenString.matches("\\n")) {
						
						//System.err.println("Debug: coveredText>"+coveredText+"< newline lastEndOffset "+lastEndOffset+" begin-intertok "+(begin - currentInterTokenString.length()));
						if (lastEndOffset != begin - currentInterTokenString.length()) {
							AnnotationUtils.createAnnotation(this.getaJCas(), this.getSentenceTypeName(), lastEndOffset , begin - currentInterTokenString.length());
							lastEndOffset = begin - currentInterTokenString.length();
						}
					}
			}
		}
		catch (AnalysisEngineProcessException e) {
			System.err.println("Error: error creating an annotation "+this.getSentenceTypeName()+" or dynamically between the offsets "+lastEndOffset+" and "+end);
			e.printStackTrace();
		}

		// token annotation
		try{
			AnnotationUtils.createAnnotation(this.getaJCas(), this.getTokenTypeName(), begin , end);
		}
		catch (AnalysisEngineProcessException e) {
			System.err.println("Error: error creating an annotation "+this.getTokenTypeName()+"  dynamically between the offsets "+begin+" and "+end);
			e.printStackTrace();
		}

	}

	/**
	 * add a final sentence if the last token is a not ending token
	 */
	@Override
	protected void postTokenization() {
		int lastTokenEndOffset = -1;
		try{
			// sentence annotation
			if (getProcessSentenceSplitting()) {

				//
				lastTokenEndOffset = findLastTokenEndOffset();
				//System.err.println("Debug: lastEndOffset "+lastEndOffset+" lastTokenEndOffset "+lastTokenEndOffset);

				// add a final sentence if the last token is a not ending token
				if (lastEndOffset < lastTokenEndOffset)

					AnnotationUtils.createAnnotation(this.getaJCas(), this.getSentenceTypeName(), lastEndOffset , lastTokenEndOffset);

			}
		}
		catch (AnalysisEngineProcessException e) {
			System.err.println("Error: error creating an annotation "+this.getSentenceTypeName()+" or dynamically between the offsets "+lastEndOffset+" and "+lastTokenEndOffset);
			e.printStackTrace();
		}

	}

	/**
	 * 
	 * @return
	 */
	private int findLastTokenEndOffset() {
		String endingContextCharacterRegex = "\\S\\s*$";
		Pattern p = Pattern.compile(endingContextCharacterRegex);
		Matcher m = p.matcher(getContextCoveredText());
		if  (m.find())
			return m.start() +1;
		return -1;
	}


	/*
	 * getter and setter
	 */

	/**
	 * @return the aJCas
	 */
	public JCas getaJCas() {
		return aJCas;
	}

	/**
	 * @param aJCas the aJCas to set
	 */
	public void setaJCas(JCas aJCas) {
		this.aJCas = aJCas;
	}



	/**
	 * @return the tokenTypeName
	 */
	public String getTokenTypeName() {
		return tokenTypeName;
	}


	/**
	 * @param tokenTypeName the tokenTypeName to set
	 */
	public void setTokenTypeName(String tokenTypeName) {
		this.tokenTypeName = tokenTypeName;
	}



	/**
	 * @return the processSentenceSplitting
	 */
	public Boolean getProcessSentenceSplitting() {
		return processSentenceSplitting;
	}


	/**
	 * @param processSentenceSplitting the processSentenceSplitting to set
	 */
	public void setProcessSentenceSplitting(Boolean processSentenceSplitting) {
		this.processSentenceSplitting = processSentenceSplitting;
	}


	/**
	 * @return the processNonEmptyNewlinesAsSentences
	 */
	public Boolean getProcessNonEmptyNewlinesAsSentences() {
		return processNonEmptyNewlinesAsSentences;
	}


	/**
	 * @param processNonEmptyNewlinesAsSentences the processNonEmptyNewlinesAsSentences to set
	 */
	public void setProcessNonEmptyNewlinesAsSentences(
			Boolean processNonEmptyNewlinesAsSentences) {
		this.processNonEmptyNewlinesAsSentences = processNonEmptyNewlinesAsSentences;
	}


	/**
	 * @return the sentenceTypeName
	 */
	public String getSentenceTypeName() {
		return sentenceTypeName;
	}


	/**
	 * @param sentenceTypeName the sentenceTypeName to set
	 */
	public void setSentenceTypeName(String sentenceTypeName) {
		this.sentenceTypeName = sentenceTypeName;
	}


	/**
	 * @return the sentenceEndingTokensEndOffsetList
	 */
	public List<Integer> getSentenceEndingTokensEndOffsetList() {
		return sentenceEndingTokensEndOffsetList;
	}


	/**
	 * @param sentenceEndingTokensEndOffsetList the sentenceEndingTokensEndOffsetList to set
	 */
	public void setSentenceEndingTokensEndOffsetList(
			List<Integer> sentenceEndingTokensEndOffsetList) {
		this.sentenceEndingTokensEndOffsetList = sentenceEndingTokensEndOffsetList;
	}


	/**
	 * @return the sentenceEndingTokensSet
	 */
	public Set<String> getSentenceEndingTokensSet() {
		return sentenceEndingTokensSet;
	}


	/**
	 * @param sentenceEndingTokensSet the sentenceEndingTokensSet to set
	 */
	public void setSentenceEndingTokensSet(Set<String> sentenceEndingTokensSet) {
		this.sentenceEndingTokensSet = sentenceEndingTokensSet;
	}


	/**
	 * @return the contextCoveredText
	 */
	public String getContextCoveredText() {
		return contextCoveredText;
	}


	/**
	 * @param contextCoveredText the contextCoveredText to set
	 */
	public void setContextCoveredText(String contextCoveredText) {
		this.contextCoveredText = contextCoveredText;
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








}
