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
import java.util.List;

import fr.univnantes.lina.app.tokenizer.TokenizerCommandLineParametersParser;


/**
 * full tokenize algorithm for sentence and word from input given as a list of strings   
 * 
 * @author hernandez
 *
 */
public class WordSentenceListTokenizer {

	
	public static List<List<String>> tokenize (List<String> inputLines, List<String> args) {
		
		
		// generate default args
		if (args==null) {
			args = new ArrayList<String>();
		}

		// set parameters
		String className = WordSentenceListTokenizer.class.getClass().getSimpleName();
		// to use the same configuration as the command line (in particular --ws-to
		TokenizerCommandLineParametersParser aTokenizerParameter = new TokenizerCommandLineParametersParser();
		aTokenizerParameter.parseParameters(className,args);
				
		
		long debut = System.currentTimeMillis();
		long fin;
		if (aTokenizerParameter.isVerboseMode()) 
			System.err.println("Starting settings at: "+ ((debut)/1000.0)+" s");
				
		//
		fin = System.currentTimeMillis();
		if(aTokenizerParameter.isVerboseMode()) System.err.println("Ending settings in: "+ ((fin-debut)/1000.0)+" s");
		if(aTokenizerParameter.isVerboseMode()) System.err.println("------------------------------------------------");
		debut = fin;

		/* --------------------------------------------------------------------
		 * Pre-tokenize (recognize units)
		 */
		WordSentenceListTokenizerOutput aWordSentenceListtokenizer = new WordSentenceListTokenizerOutput();
		aWordSentenceListtokenizer.settings(aTokenizerParameter);

		aWordSentenceListtokenizer.preTokenizeText(inputLines);

		fin = System.currentTimeMillis();
		if(aTokenizerParameter.isVerboseMode()) System.err.println("Ending pre-tokenization in: "+ ((fin-debut)/1000.0)+" s");
		if(aTokenizerParameter.isVerboseMode()) System.err.println("------------------------------------------------");
		debut = fin;


		/* --------------------------------------------------------------------
		 * Tokenize
		 */
		//simpleTokenizer.tokenize(inputText,0,inputText.length());

		aWordSentenceListtokenizer.tokenize(inputLines);

		fin = System.currentTimeMillis();
		if(aTokenizerParameter.isVerboseMode()) System.err.println("Ending Tokenizer in: "+ ((fin-debut)/1000.0)+" s");
		if(aTokenizerParameter.isVerboseMode()) System.err.println("------------------------------------------------");
		debut = fin;


		/* --------------------------------------------------------------------
		 * Post tokenization
		 */

		aWordSentenceListtokenizer.postTokenization();
		
		

		fin = System.currentTimeMillis();
		if(aTokenizerParameter.isVerboseMode()) System.err.println("Ending Post-Tokenizer in: "+ ((fin-debut)/1000.0)+" s");
		if(aTokenizerParameter.isVerboseMode()) System.err.println("------------------------------------------------");

		debut = fin;
		
		return aWordSentenceListtokenizer.getSentenceList();
		
	}
}
