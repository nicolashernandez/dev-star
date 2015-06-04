/** 
 * UIMA ml-nlp
 * Copyright (C) 2010  Nicolas Hernandez
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
import java.util.List;

import org.apache.uima.UimaContext;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.cas.FSIterator;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.tcas.Annotation;
import org.apache.uima.resource.ResourceInitializationException;

import fr.univnantes.lina.app.tokenizer.TokenizerCommandLineParametersParser;
import fr.univnantes.lina.mlnlp.analysis.text.ruleBasedTokenizer.Tokenizer;
import fr.univnantes.lina.mlnlp.analysis.text.ruleBasedTokenizer.TokenizerParametersParser;
import fr.univnantes.lina.uima.common.ae.CommonAE;
import fr.univnantes.lina.uima.common.cas.AnnotationUtils;
import fr.univnantes.lina.uima.common.cas.ContextUtils;
import fr.univnantes.lina.uima.common.cas.ContextUtils.ContextFile;



/** @see Tokenizer to know the main idea 
 * 
 * <p>
 * This AE takes two parameters:
 * <ul>
 * <li><code>TokenType</code> - type name of the token annotation to create. Optional, by default <code>org.apache.uima.TokenAnnotation</code></li>

 * </ul>
 * 
 * TODO  isAClosingCharCodePoint and my be refactored using the Character.getType(codePoint) method
 * 
 * 
 */
public class SimpleTokenizerAE extends CommonAE {

	/*
	 * PARAMETERS NAMES
	 */
	/**
	 * Parameter name of the word type 
	 */
	public static final String PARAM_TOKEN_TYPE = "TokenType";
	public static final String PARAM_SENTENCE_TYPE = "SentenceType";

	public static final String PARAM_PropertiesFilePath = "PropertiesFilePath"; // be considered first to allow overwritting

	public static final boolean DEFAULT_VerboseMode = false;

	public static final String PARAM_CharacterGlueFunctioning = "CharacterGlueFunctionings";

	public static final String PARAM_RegexTokenFilesPath = "RegexTokenFilePath";

	public static final String PARAM_VerboseMode = "VerboseMode";

	public static final String PARAM_ProcessSentenceSplitting = "SentenceSplitting";
	public static final String PARAM_SentenceEndingTokens = "SentenceEndingTokens";
	public static final String PARAM_NonEmptyNewLinesAsNewSentences = "NonEmptyNewlinesAsSentences";


	/*
	 * not implemented 
	 */
	/*public static final String PARAM_OutputFormat = "OutputFormat";
	public static final int DEFAULT_OutputFormat = -1;
	public static final String PARAM_NewlineTokens = "NewlineTokens";
	public static final String PARAM_WhitespaceReplacementCharacter = "WhitespaceReplacementCharacter";
	public static final String PARAM_PreservingInputTextLayout = "PreservingInputTextLayout";
	public static final boolean DEFAULT_PreservingInputTextLayout = false;
	public static final String PARAM_CleanOutput = "CleanOutput";
	public static final boolean DEFAULT_CleanOutput = false;
	private Boolean removeRedundantInvisibleCharactersInOutputLayout = false;


	public static final String PARAM_MultilinesInput = "MultilinesInput";
	public static final boolean DEFAULT_MultilinesInput = false;

	//private String globalRegexFlag = "";
	public static final String PARAM_RegexUnion = "RegexUnion";
	public static final boolean DEFAULT_RegexUnion = false;
		//private Boolean parseInputAsMultilines = false;
	//private Boolean singleLineInput = true;
	 */

	/*
	 * DEFAULT VALUES
	 */
	/**
	 * Default value of the token type
	 */
	public static String DEFAULT_TOKEN_TYPE = "org.apache.uima.TokenAnnotation";
	public static String DEFAULT_SENTENCE_TYPE = "org.apache.uima.SentenceAnnotation";

	/**
	 * Default value 
	 * initial value, assumed not correspond to any unicode char, should not be found in a text...
	 */
	public static int DEFAULT_INITIAL_NON_UNICODE_CODEPOINT_VALUE = 0;


	/*
	 * LOCAL VARIABLES
	 */
	private String tokenTypeName;
	private String sentenceTypeName;


	private List<String> args = new ArrayList<String>();

	private TokenizerParametersParser aTokenizerParameter = null;

	private Boolean processSentenceSplitting = false;
	private String [] sentenceEndingTokens;
	private Boolean processNonEmptyNewlinesAsSentences = false;


	/*
	 * ACCESSORS
	 */


	/*
	 * METHODS 
	 */

	/**
	 * Get the parameter values, setting the variables and checking the values 
	 */
	public void initialize(UimaContext aContext) throws ResourceInitializationException {

		// SUPER PARAMETER SETTINGS
		super.initialize(aContext);

		// CURRENT AE PARAMETER SETTINGS

		//--  tokenizer parameters
		String value = ((String) aContext.getConfigParameterValue(PARAM_PropertiesFilePath));
		if (value != null) {args.add("-p"); args.add(value);}

		String[] valueArray = ((String[]) aContext.getConfigParameterValue(PARAM_CharacterGlueFunctioning));
		if (valueArray != null) 
			if (valueArray.length !=0) 
				for (String val : valueArray) {
					String str[]= val.split("\\s+");
					args.add("-c"); 
					args.add(str[0]);
					args.add(str[1]);
				}

		valueArray = ((String[]) aContext.getConfigParameterValue(PARAM_RegexTokenFilesPath));
		if (valueArray != null) 
			if (valueArray.length !=0) 
				for (String val : valueArray) {
					args.add("-r"); 
					ContextFile file = ContextUtils.resolveRelativeFilePath(this, aContext, val);
					args.add(file.getFilePath());
				}

		Boolean booleanValue = ((Boolean) aContext.getConfigParameterValue(PARAM_VerboseMode));
		if (booleanValue != null) 
			if (booleanValue) args.add("-v");

		//--  parameters to output sentence endings 
		booleanValue = null;
		booleanValue = ((Boolean) aContext.getConfigParameterValue(PARAM_ProcessSentenceSplitting));
		if (booleanValue != null) 
			processSentenceSplitting = booleanValue;

		sentenceEndingTokens = ((String[]) aContext.getConfigParameterValue(PARAM_SentenceEndingTokens));
		

		booleanValue = null;
		booleanValue = ((Boolean) aContext.getConfigParameterValue(PARAM_NonEmptyNewLinesAsNewSentences));
		if (booleanValue != null) 
			processNonEmptyNewlinesAsSentences = booleanValue;

		if (processSentenceSplitting && (!(processNonEmptyNewlinesAsSentences || sentenceEndingTokens!=null))  ) {
			String errmsg = "To annotation sentences, you have to specify a list ending tokens and/or command to process non empty newlines as sentences !";
			throw new ResourceInitializationException(errmsg, new Object[] {});
		}
			
		

		//-- parameters output annotation types
		tokenTypeName = ((String) aContext.getConfigParameterValue(PARAM_TOKEN_TYPE));
		if (tokenTypeName == null) tokenTypeName = DEFAULT_TOKEN_TYPE;

		sentenceTypeName = ((String) aContext.getConfigParameterValue(PARAM_SENTENCE_TYPE));
		if (sentenceTypeName == null) sentenceTypeName = DEFAULT_SENTENCE_TYPE;



		//-- settings	
		String className = SimpleTokenizerAE.class.getClass().getSimpleName();
		aTokenizerParameter = new TokenizerParametersParser();
		aTokenizerParameter.parseParameters(className,args);
		//aTokenizerParameter.check();
		//System.err.println("TokenizerParameter.check()");




		//try {
		//} catch (Exception e) {
		//	String errmsg = "!";
		//	throw new ResourceInitializationException(errmsg, new Object[] {});
		//}		
	}

	/**
	 * 
	 */
	@Override
	protected String processContextAnnotation(JCas inputViewJCas,
			FSIterator contextAnnotationsFSIter, Annotation contextAnnotation,
			FSIterator contextualizedInputAnnotationsFSIter,
			String inputFeatureString, JCas outputViewJCas,
			String outputAnnotationString, String ouputFeatureString)
					throws AnalysisEngineProcessException {

		List<String> inputLines = new ArrayList<String>();

		/* --------------------------------------------------------------------
		 * Loading input text
		 */
		long debut = System.currentTimeMillis();
		long fin;
		if (aTokenizerParameter.isVerboseMode()) 
			System.err.println("Starting settings at: "+ ((debut)/1000.0)+" s");


		// http://stackoverflow.com/questions/1096621/how-to-read-a-string-line-per-line
		//Scanner scanner = new Scanner(contextAnnotation.getCoveredText());
		//while (scanner.hasNextLine()) {
		//	inputLines.add(scanner.nextLine());
		//}
		inputLines.add(contextAnnotation.getCoveredText());

		//
		fin = System.currentTimeMillis();
		if(aTokenizerParameter.isVerboseMode()) System.err.println("Ending settings in: "+ ((fin-debut)/1000.0)+" s");
		if(aTokenizerParameter.isVerboseMode()) System.err.println("------------------------------------------------");
		debut = fin;

		/* --------------------------------------------------------------------
		 * Pre-tokenize (recognize units)
		 */
		UIMAAnnotationTokenizerOutput aUIMAOutputTokenizer = new UIMAAnnotationTokenizerOutput(inputViewJCas, contextAnnotation.getCoveredText(), tokenTypeName, sentenceTypeName, processSentenceSplitting,sentenceEndingTokens,processNonEmptyNewlinesAsSentences);
		aUIMAOutputTokenizer.settings(aTokenizerParameter);

		aUIMAOutputTokenizer.preTokenizeText(inputLines);

		fin = System.currentTimeMillis();
		if(aTokenizerParameter.isVerboseMode()) System.err.println("Ending pre-tokenization in: "+ ((fin-debut)/1000.0)+" s");
		if(aTokenizerParameter.isVerboseMode()) System.err.println("------------------------------------------------");
		debut = fin;


		/* --------------------------------------------------------------------
		 * Tokenize
		 */
		//simpleTokenizer.tokenize(inputText,0,inputText.length());

		aUIMAOutputTokenizer.tokenize(inputLines);

		fin = System.currentTimeMillis();
		if(aTokenizerParameter.isVerboseMode()) System.err.println("Ending Tokenizer in: "+ ((fin-debut)/1000.0)+" s");
		if(aTokenizerParameter.isVerboseMode()) System.err.println("------------------------------------------------");
		debut = fin;


		/* --------------------------------------------------------------------
		 * Post tokenization
		 */

		aUIMAOutputTokenizer.postTokenization();

		fin = System.currentTimeMillis();
		if(aTokenizerParameter.isVerboseMode()) System.err.println("Ending Post-Tokenizer in: "+ ((fin-debut)/1000.0)+" s");
		if(aTokenizerParameter.isVerboseMode()) System.err.println("------------------------------------------------");

		debut = fin;
		

		// simple word tokenizer
		/*this.tokenize(inputViewJCas,
				contextAnnotation.getCoveredText(), 
				contextAnnotation.getBegin(), 
				contextAnnotation.getEnd());
		 */
		//
		return contextAnnotation.getCoveredText();
	}


}




