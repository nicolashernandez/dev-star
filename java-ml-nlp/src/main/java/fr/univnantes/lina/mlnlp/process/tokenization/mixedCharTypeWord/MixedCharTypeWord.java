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
package fr.univnantes.lina.mlnlp.process.tokenization.mixedCharTypeWord;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import fr.univnantes.lina.javautil.IOUtilities;

/**
 * Given a training corpus
 * 
 * Identify the ws token with punctuation at the extremities which are words
 * 
 * 1. ws token, count ws-token occurrences, store ws-token ending with punct 
 * 2. for each ws-token ending with punct do
 * 	  	remove ending punct 
 *  	if count (ws-token-wi-punct) >= count (ws-token-wo-punct) then
 *  	  store the sw-token-wi as a vocab word
 * 3. display		  
 * 
tokenizer : en 2 passes : hyp si une forme existe alors existe en nombre remarquable sans ponctuation. 
énumérer toutes les formes wst PB comment distinguer les formes des formes mêlées à la ponctuation. 
parmi ceux ayant ponct fin voire deb,  on la,les supprime, 
si on obtient une forme remarquable et que celle-ci en occ supp,  alors mot du vocab . 
contrainte aussi sur NB occ min. 
des abréviations comme etc. ? on suppose q nbOcc etc. >  nbOcc etc
phase 2 ws token, pour chaque mot si ponct aux extrémités on remove incrémentallement et test si mot du dico. si oui alors on coupe sinon non. quid des oov ?test si anagramme, dist édit de 1, squeeze dun mot du vocab ?... trop coûteux pour un tour tokenizer
phase2 alter. on utilise le vocab pr connaitre les mots avec ponct, on ws token, si mot ponct alors tant que ponct et pas un mot vocab ponct on decoupe, quid du principe quand on connait pas mieux vaut laisser eg des formules math... ? apres phase 1, on essaye de generaliser mot peu freq avec ponct, on ne touche pas aux ponct mais on change any seq lettre par letter+ idem pour digit, eg pour URL. on ajoute au vocab.m
on peut aussi dire que si proportion d lettre est plus importante que symb (attentiin on parlait seulement de ponct forte et semi forte , doute sur les parenthses crochet...) alors on coupe 
en fait phase 1 va aussi considérer "j'avais" comme mot du vocab... ce qui explique des "javais" dans des modalités d'expression plus libres, donc finalement phase 1 a pour but d'identifier le vocab constitué de ponctuation aux extrémités

# [\.er:;!\?]espace
à construire sur corpus moderne
si corpus avec faute, word2vec pour connaitre les variantes puis filtre.

autre hyp si une forme apparaît dans même contexte alors elle est stable mais peut être que des formes avec , vont apparaître. a considéré r alors comme variante.
 *
 * @author hernandez-n
 *
 */
public class MixedCharTypeWord {

	Set<String> startingPunctuationSymbols = new HashSet<>();
	Set<String> endingPunctuationSymbols = new HashSet<>();


	Map<String,Integer> counts = new HashMap<>();

	// word candidate
	Set<String> tokenEndingWiPunct = new HashSet<>();

	// actual word 
	Set<String> wordEndingWiPunct = new HashSet<>();


	int minimalOccurrencesThresholdToBeAWord = 5;
	double minimalConfidenceThresholdToBeAWord = 0.2;
	
	/**
	 * 
	 */
	public MixedCharTypeWord(String trainPath) {
		super();
		this.endingPunctuationSymbols = new HashSet<>();
		endingPunctuationSymbols.add(".");
		endingPunctuationSymbols.add(",");
		endingPunctuationSymbols.add("?");
		endingPunctuationSymbols.add("!");
		endingPunctuationSymbols.add(";");
		endingPunctuationSymbols.add(")");
		endingPunctuationSymbols.add("]");
		endingPunctuationSymbols.add("}");
		endingPunctuationSymbols.add("»");
		endingPunctuationSymbols.add("\"");

		this.startingPunctuationSymbols = new HashSet<>();
		startingPunctuationSymbols.add("(");
		startingPunctuationSymbols.add("[");
		startingPunctuationSymbols.add("{");
		startingPunctuationSymbols.add("«");
		startingPunctuationSymbols.add("\"");

		for (String line : IOUtilities.readTextFileAsStringList(trainPath)) 
			parseText(line);

		//TODO 

		identifyWstokenWiPunctAsWord();
		// comment from here
		/*String[] candidateTokenArray = { ":)", "(II)", ":-)", ":o)", "(0x20)", "(rw)", 
				"Regards,","(c)", "(0xa0000)", 
				"(**)", "e.g.", "Mon,", "Inc.", "lists...", "list.", "etc....", "etc.",
				"(i.e.", "i.e.", "(e.g.,", "e.g.,", "N.B.",  "text/plain;",  "[Y/n]?", "[Y/n]", "&gt;", 
				"charset=ISO-8859-1;", "charset=ISO-8859-1", "text/plain;", "text/plain"

		};
		for (String candidateToken : candidateTokenArray )
			identifyWstokenWiEndingPunctAsWord(candidateToken);
			// comment to here
*/
	}

	/**
	 * 
	 */
	public MixedCharTypeWord(Set<String> punctuationSymbols, String trainPath) {
		super();
		this.endingPunctuationSymbols = new HashSet<>(punctuationSymbols);

		for (String line : IOUtilities.readTextFileAsStringList(trainPath)) 
			parseText(line);
		identifyWstokenWiPunctAsWord();

	}

	private Set<String> getStartingPunctuationSymbols() {
		return this.startingPunctuationSymbols;
	}
	private Set<String> getEndingPunctuationSymbols() {
		return this.endingPunctuationSymbols;
	}

	private Set<String> getTokenEndingWiPunct() {
		return this.tokenEndingWiPunct;
	}

	public Set<String> getWordEndingWiPunct() {
		return this.wordEndingWiPunct;
	}

	private Map<String,Integer> getCounts() {
		return this.counts;
	}

	private int getTokenCount(String token) {
		if (getCounts().containsKey(token))
			return getCounts().get(token);
		return 0;
	}

	private void incCount(String token) {
		if (getCounts().containsKey(token))
			getCounts().put(token, getCounts().get(token) + 1);
		else 
			getCounts().put(token, new Integer(1));
	}

	private void storeTokenEndingWiPunct(String token) {
		getTokenEndingWiPunct().add(token);
	}

	private void storeWordEndingWiPunct(String token) {
		getWordEndingWiPunct().add(token);
	}


	/**
	 * test if a token is ending with a ending punctuation symbol
	 * 
	 * @param token
	 * @return
	 */
	private boolean isEndingWiPunct(String token) {
		boolean is = false;
		for (String punctSym : getEndingPunctuationSymbols()) 
			if (token.endsWith(punctSym)) {is = true; break;}
		return is;
	}


	/**
	 * test if a token is starting with a starting punctuation symbol
	 * 
	 * @param token
	 * @return
	 */
	private boolean isStartingWiPunct(String token) {
		boolean is = false;
		for (String punctSym : getStartingPunctuationSymbols()) 
			if (token.startsWith(punctSym)) {is = true; break;}
		return is;
	}


	/**
	 * Whitespace tokenize (Sentence or any text)
	 * Count token occurrences
	 * List token ending wi punctuations
	 * @param sentence
	 * 
	 */
	private void parseSentence (String sentence) {
		for (String token : sentence.split("\\s+")) {
			incCount(token);
			if (isEndingWiPunct(token))
				storeTokenEndingWiPunct(token);
		}
	}

	/**
	 * split into sentences (in order to reduce the memory load)
	 * then call whitespaceTokeniseSentence
	 * @param text
	 */
	private void parseText (String text) {
		//System.err.println("Info: parseText");
		for (String sentence : text.split("\\n")) {
			parseSentence(sentence);
		}
	}

	/**
	 * remove only one ending char
	 * @param token
	 * @return
	 */
	private String removeOneCharacterEnding(String token) {
		return token.substring(0, token.length()-1);
	}

	/**
	 * remove ending char steps ; with no punctuation constraints steps
	 * @param token
	 * @return
	 */
	private String nextExistingEmbeddedToken(String token) {
		String subtoken = "";
		do {
			subtoken = token.substring(0, token.length()-1);
			token = subtoken;
		} while (getTokenCount(subtoken) == 0 && subtoken.length()>0);
		return subtoken;
	}

	/**
	 * TODO should test first + add a size constraint
	 * @param token
	 * @return
	 */
	private String nextExistingEmbeddedTokenEndingWiPunct(String token) {
		String subtoken = "";
		do {
			subtoken = token.substring(0, token.length()-1);
			token = subtoken;
		} while (getTokenCount(subtoken) == 0 && subtoken.length()>0 && isEndingWiPunct(subtoken));
		return subtoken;
	}

	/**
	 * can return empty string, means there is no embedded
	 * can also return string with can in turn have embedded
	 * 
	 * @param token
	 * @return
	 */
	private String nextExistingEmbeddedTokenWiPunctStep(String token) {
		String subtoken = token.substring(0, token.length()-1);
		// at least 2 chars 
		if (subtoken.length() >1) {
			// 2 chars which exists by them selfves
			if (getTokenCount(subtoken) != 0) 
				return subtoken;
			// 
			if (isEndingWiPunct(subtoken)) 
				return nextExistingEmbeddedTokenWiPunctStep(subtoken);  
		}
		return "";
	}

	/**
	 * the first existing token without ending punct or empty string 
	 * @param token
	 * @return
	 */
	private String nextExistingEmbeddedTokenWiPunctStepWoPunctEnding(String token) {
		String subtoken = token.substring(0, token.length()-1);
		if (isEndingWiPunct(subtoken)) 
			return nextExistingEmbeddedTokenWiPunctStepWoPunctEnding(subtoken); 
		// at least 2 chars 
		if (subtoken.length() >1) {
			// 2 chars which exists by them selfves
			if (getTokenCount(subtoken) != 0) 		
				return subtoken;
		}
		return "";
	}

	/**
	 * the first existing token without ending punct or empty string 
	 * @param token
	 * @return
	 */
	private String nextExistingEmbeddedTokenWiPunctStepWoPunct(String token) {
		while (isEndingWiPunct(token)  ) 
			token = token.substring(0, token.length()-1);
		while (isStartingWiPunct(token)  ) 
			token = token.substring(1, token.length());
		// 2 chars which exists by them selfves
		if ((getTokenCount(token) != 0) && (token.length() >1))
			return token;

		return "";
	}

	/**
	 * confidence for a token
	 * 
	 * should be superior to a fixed threshold (e.g. 0.2) to be a word
	 * can be negative
	 * 
	 * @param token
	 * @param subtoken
	 * @return
	 */
	private double getConfidence(int tokenCount, int subtokenCount) {
		return (double) (tokenCount - subtokenCount) / (double) (tokenCount + subtokenCount);  
	}

	/**
	 * assuming the method called only wi token ending wi punct

	 * 
	 * @param token
	 */
	private void identifyWstokenWiEndingPunctAsWord(String token) {
		

		// the token itself should occurs a minimal number of times
		int tokenCount = getTokenCount(token) ;
		if (tokenCount > minimalOccurrencesThresholdToBeAWord) {
			/*//String embeddedToken = nextExistingEmbeddedToken(token);
			//String embeddedToken = nextExistingEmbeddedTokenEndingWiPunct(token);
			String _nextExistingEmbeddedTokenWiPunctStep = nextExistingEmbeddedTokenWiPunctStep(token);
			int _nextExistingEmbeddedTokenWiPunctStepTokenCount = 0;
			if (!_nextExistingEmbeddedTokenWiPunctStep.equalsIgnoreCase("")) 
				_nextExistingEmbeddedTokenWiPunctStepTokenCount = getTokenCount(_nextExistingEmbeddedTokenWiPunctStep);
			double confidence = getConfidence(tokenCount,_nextExistingEmbeddedTokenWiPunctStepTokenCount);
			System.out.print("Debug: token>"+token+"< occurs "+tokenCount
					+" nextSub>"+_nextExistingEmbeddedTokenWiPunctStep+"< occurs "+_nextExistingEmbeddedTokenWiPunctStepTokenCount+ " confidence>"+confidence+"<");

			//if (tokenCount >= _nextExistingEmbeddedTokenWiPunctStepTokenCount) { 
			if (confidence > minimalConfidenceThresholdToBeAWord) {
				storeWordEndingWiPunct(token+"\t"+_nextExistingEmbeddedTokenWiPunctStepTokenCount);
				System.out.println(" WORD");
			}
			else 
				System.out.println();
			//if (isEndingWiPunct(_nextExistingEmbeddedTokenWiPunctStep)) 
			//	identifyWstokenWiPunctAsWord(_nextExistingEmbeddedTokenWiPunctStep);


			String _nextExistingEmbeddedTokenWiPunctStepWoPunctEnding = nextExistingEmbeddedTokenWiPunctStepWoPunctEnding(token);
			int _nextExistingEmbeddedTokenWiPunctStepWoPunctEndingTokenCount = 0;
			if (!_nextExistingEmbeddedTokenWiPunctStepWoPunctEnding.equalsIgnoreCase("")) 
				_nextExistingEmbeddedTokenWiPunctStepWoPunctEndingTokenCount = getTokenCount(_nextExistingEmbeddedTokenWiPunctStepWoPunctEnding);
			confidence = getConfidence(tokenCount,_nextExistingEmbeddedTokenWiPunctStepWoPunctEndingTokenCount);
			System.out.print("Debug: token>"+token+"< occurs "+tokenCount
					+" nextSubWoEndingPunct>"+_nextExistingEmbeddedTokenWiPunctStepWoPunctEnding+"< occurs "+_nextExistingEmbeddedTokenWiPunctStepWoPunctEndingTokenCount
					+ " confidence>"+confidence+"<");

			if (confidence > minimalConfidenceThresholdToBeAWord) {
				storeWordEndingWiPunct(token+"\t"+_nextExistingEmbeddedTokenWiPunctStepWoPunctEnding);
				System.out.println(" WORD");
			}
			else 
				System.out.println();
*/
			String _nextExistingEmbeddedTokenWiPunctStepWoPunct = nextExistingEmbeddedTokenWiPunctStepWoPunct(token);
			int _nextExistingEmbeddedTokenWiPunctStepWoPunctTokenCount = 0;
			if (!_nextExistingEmbeddedTokenWiPunctStepWoPunct.equalsIgnoreCase("")) 
				_nextExistingEmbeddedTokenWiPunctStepWoPunctTokenCount = getTokenCount(_nextExistingEmbeddedTokenWiPunctStepWoPunct);
			double confidence = getConfidence(tokenCount,_nextExistingEmbeddedTokenWiPunctStepWoPunctTokenCount);
			//System.out.print("Debug: token>"+token+"< occurs "+tokenCount
			//		+" nextSubWoPunct>"+_nextExistingEmbeddedTokenWiPunctStepWoPunct+"< occurs "+_nextExistingEmbeddedTokenWiPunctStepWoPunctTokenCount
			//		+ " confidence>"+confidence+"<");

			if (confidence > minimalConfidenceThresholdToBeAWord) {
				storeWordEndingWiPunct(token+"\t"+tokenCount);
				//storeWordEndingWiPunct(token);
				//System.out.println(" WORD");
			}
			//else 
			//	System.out.println();
			//System.out.println("------------------------------------------------------");


		}
	}

	/*
	 * for each ws-token ending with punct do
	 * 	  	remove ending punct 
	 *  	if count (ws-token-wi-punct) >= count (ws-token-wo-punct) then
	 *  	  store the sw-token-wi as a vocab word
	 *  
	 *  La fin. ! c("fin.") 	>= c("fin")		donc fin. n'est pas un word
	 *  :-).  	! c(":-).") 	>= c(":-)")		donc :-). n'est pas un word
	 *  etc., 	! c("etc.,") 	>= c("etc.")	donc etc., n'est pas un word mais etc. isEndingWithPunct so we continue
	 *  etc., 	  c("etc.") 	>= c("etc")		donc etc. est un word
	 *  
	 *  assume that no embedded words are possible e.g 'abcd' is a word and 'ab' too  
	 */
	private void identifyWstokenWiPunctAsWord () {
		System.err.println("Info: identifyWstokenWiPunctAsWord");
		for (String token : getTokenEndingWiPunct()) 
			identifyWstokenWiEndingPunctAsWord(token);

	}

}
