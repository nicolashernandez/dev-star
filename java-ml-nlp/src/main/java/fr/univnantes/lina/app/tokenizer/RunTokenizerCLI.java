/** 
 * Copyright (C) 2013-20..  Nicolas Hernandez
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

/**
 * Command line interface for the WordSequenceAligner
 * 
 * N. Hernandez 20130222
 **/ 
package fr.univnantes.lina.app.tokenizer;


import java.util.ArrayList;
import java.util.List;

import fr.univnantes.lina.javautil.IOUtilities;

/**
 * Run Java simple tokenizer by Command line
 * 
 * @author hernandez
 *
 */
public class RunTokenizerCLI {


	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		String className = RunTokenizerCLI.class.getClass().getSimpleName();

		TokenizerCommandLineParametersParser aTokenizerParameter = new TokenizerCommandLineParametersParser();
		aTokenizerParameter.parseParameters(className,args);
		aTokenizerParameter.check();
		//System.err.println("TokenizerParameter.check()");


		STDOUTTokenizerOutput aCommandLineTokenizer = new STDOUTTokenizerOutput();

		/* --------------------------------------------------------------------
		 * Settings
		 */
		long debut = System.currentTimeMillis();
		long fin;
		if(aTokenizerParameter.isVerboseMode()) System.err.println("Starting settings at: "+ ((debut)/1000.0)+" s");


		//String inputText = simpleTokenizer.settings(simpleTokenizerParameter);
		aCommandLineTokenizer.settings(aTokenizerParameter);
		
		//String inputText = "";
		List<String> inputLines = new ArrayList<String>();
		if (aTokenizerParameter.isParseAsMultilinesInput())
			inputLines.addAll(IOUtilities.readTextFileAsStringList(aTokenizerParameter.getInputFilePath()));
		else { 
			inputLines.add(IOUtilities.readTextFileAsString (aTokenizerParameter.getInputFilePath()));
			
		}
		
		//
		fin = System.currentTimeMillis();
		if(aTokenizerParameter.isVerboseMode()) System.err.println("Ending settings in: "+ ((fin-debut)/1000.0)+" s");
		if(aTokenizerParameter.isVerboseMode()) System.err.println("------------------------------------------------");
		debut = fin;


		/* --------------------------------------------------------------------
		 * Pre-tokenize (recognize units)
		 */
		
		aCommandLineTokenizer.preTokenizeText(inputLines);

		fin = System.currentTimeMillis();
		if(aTokenizerParameter.isVerboseMode()) System.err.println("Ending pre-tokenization in: "+ ((fin-debut)/1000.0)+" s");
		if(aTokenizerParameter.isVerboseMode()) System.err.println("------------------------------------------------");
		debut = fin;


		/* --------------------------------------------------------------------
		 * Tokenize
		 */
		//simpleTokenizer.tokenize(inputText,0,inputText.length());

		aCommandLineTokenizer.tokenize(inputLines);
			
		fin = System.currentTimeMillis();
		if(aTokenizerParameter.isVerboseMode()) System.err.println("Ending Tokenizer in: "+ ((fin-debut)/1000.0)+" s");
		if(aTokenizerParameter.isVerboseMode()) System.err.println("------------------------------------------------");
		debut = fin;

		
		/* --------------------------------------------------------------------
		 * Post tokenization
		 */
		
		aCommandLineTokenizer.postTokenization();
		
		fin = System.currentTimeMillis();
		if(aTokenizerParameter.isVerboseMode()) System.err.println("Ending Post-Tokenizer in: "+ ((fin-debut)/1000.0)+" s");
		if(aTokenizerParameter.isVerboseMode()) System.err.println("------------------------------------------------");

		debut = fin;
	}





}



