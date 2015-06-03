package fr.univnantes.lina.app.tokenizer;
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

import java.util.HashSet;
import java.util.List;
import java.util.Set;


import fr.univnantes.lina.mlnlp.analysis.text.ruleBasedTokenizer.TokenizerParametersParser;

/**
 * Parse command line parameters 
 * @author hernandez
 *
 */
public class TokenizerCommandLineParametersParser extends TokenizerParametersParser{


	/*
	 * Members
	 */
	private String outputFormat = STDOUTTokenizerOutput.WORD_PER_LINE; // wpl (default) | wst-spl
	private Set<String> newlineTokenSet = new HashSet<String>(); 
	private String tokenWhitespaceCharacterReplacement = "";
	private Boolean preserveInputTextLayout = false;
	private Boolean removeRedundantInvisibleCharactersInOutputLayout = false;
	private Boolean sweepOutput =false ;


	/*
	 * Constructors 
	 */

	/**
	 * 
	 */
	public TokenizerCommandLineParametersParser() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * Constructor 
	 * 
	 * @param className
	 * @param args
	 */
/*	public TokenizerCommandLineParametersParser(String className,
			String [] args) {
		super(className, args);
		initialize();
		parseParameters(args);
		
	}*/
	

	/**
	 * Constructor 
	 * 
	 * @param className
	 * @param args
	 */
	/*public TokenizerCommandLineParametersParser(String className,
			List<String> args) {
		super(className, args);
		initialize();
		parseParameters(args);
	}*/

	/**
	 * 
	 */
	/*private void initialize() {
		outputFormat = ""; // wpl | wst-spl
		newlineTokenSet = new HashSet<String>(); 
		tokenWhitespaceCharacterReplacement = "";
		preserveInputTextLayout = false;
		removeRedundantInvisibleCharactersInOutputLayout = false;
		sweepOutput =false ;
	}*/
	
	/*
	 * GETTER / SETTER
	 */

	public Boolean getRemoveRedundantInvisibleCharactersInOutputLayout() {
		return removeRedundantInvisibleCharactersInOutputLayout;
	}


	/**
	 * @return the cleanOutput
	 */
	public Boolean getSweepOutput() {

		return sweepOutput;
	}

	public String getOutputFormat() {
		return outputFormat;
	}


	public Set<String> getNewlineTokenSet() {
		return newlineTokenSet;
	}

	public String getTokenWhitespaceCharacterReplacement() {
		return tokenWhitespaceCharacterReplacement;
	}


	public Boolean isPreserveInputTextLayout() {
		return preserveInputTextLayout;
	}

	


	/**
	 * Parse command line parameters
	 * (both for the tokenizer and the output configuration)
	 * 
	 * @param args
	 */
	public List<String> parseParameters (String className, List<String> args) {

		// Parse parameters specific to the tokenizer
		//System.err.println("Debug: Parse parameters specific to the tokenizer");

		// get remaining parameters from
		List<String> argsList= super.parseParameters(className,args);
		//System.err.println("Debug: Parse parameters specific to the output");

		// Parse parameters specific to the output 
		//System.err.println("Debug: argsList.toString() "+argsList.toString());
		/*
		 * Getting parameters 
		 */
		
		int pos = 0;
		while (pos < argsList.size()) {
			if ((argsList.get(pos).equals("-o")) || (argsList.get(pos).equals("--output-format"))) {
				++pos;
				if (pos == argsList.size()) usage(className, "Wrong parameters");
				outputFormat = argsList.get(pos);

				++pos;
			}
			else if ( (argsList.get(pos).equals("-n")) || (argsList.get(pos).equals("--newline-token")))  {
				++pos;
				if (pos == argsList.size()) usage(className, "Wrong parameters");
				newlineTokenSet.add(argsList.get(pos));
				++pos;
			}
			else if(argsList.get(pos).equals("--ws-to")) {
				++pos;
				if (pos == argsList.size()) usage(className, "Wrong parameters");
				tokenWhitespaceCharacterReplacement = argsList.get(pos);
				++pos;
			}
			else if(argsList.get(pos).equals("--preserve-layout")) {
				preserveInputTextLayout = true;
				++pos;
			}
			else if ((argsList.get(pos).equals("-s")) || (argsList.get(pos).equals("--sweep-output"))){
				removeRedundantInvisibleCharactersInOutputLayout = true;
				++pos;
			}
			else if(argsList.get(pos).equals("--multi-lines-input")) {
				parseInputAsMultilines = false;
				++pos;
			}
			else {
				
				pos++;
				usage(className, "Wrong parameters");
			}
		}
		
		return argsList;


	}

	/**
	 * Check the validity of parameters
	 * and display also the set values 
	 */
	public void check () {
		
		super.check();
		if(verboseMode) {
			
			if(!outputFormat.equalsIgnoreCase("")) System.err.println("Parameter: Output Format: "+outputFormat);
			if(!newlineTokenSet.isEmpty()) System.err.println("Parameter: Append a newLine character after the following tokens: "+newlineTokenSet.toString());
			if(preserveInputTextLayout) System.err.println("Parameter: Preserve input text layout");
			if(removeRedundantInvisibleCharactersInOutputLayout) System.err.println("Parameter: Remove redundant invisible characters in output layout");
			if(!tokenWhitespaceCharacterReplacement.equalsIgnoreCase("")) System.err.println("Parameter: Replace token whitespace characters with: "+tokenWhitespaceCharacterReplacement);
		}

		if ((removeRedundantInvisibleCharactersInOutputLayout) && (outputFormat.equalsIgnoreCase(STDOUTTokenizerOutput.WORD_PER_LINE)) )
			usage(className, "--output-format with a wpl and --sweep-output parameters can not be used in the same time.");
		
		
		if (this.getInputFilePath().equalsIgnoreCase("")) 
			usage(className, "A least an input file should be specified");
		//System.out.println("");
	}

	/**
	 * Usage
	 * 
	 * @param className 
	 * @param message
	 */
	public  void usage(String className, String message)  {
		
		super.usage(message, message);
		
		System.err.println("  -o outputFormat\n\t(Output configuration. wpl| a word per newline with offsets (default), wst-spl| whitespace separated tokens and a sentence per line. )");
		System.err.println("  -n newlineToken\n\t(Output configuration. Word to be considered as a newline separator.)");
		System.err.println("  --ws-to character\n\t(Output configuration. Replace all token whitespace characters with the specified character.)");
		System.err.println("  --preserve-layout\n\t(Output configuration. Preserve the input text layout (newlines and whitespace character separators).)");
		System.err.println("  -s\n\t(Output configuration. Sweep text to output: remove redundant empty lines and whitespace characters between tokens. Slower and more memory consumer)");
		System.err.println();
		System.exit(1);
	}
}
