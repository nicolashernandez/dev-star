package fr.univnantes.lina.uima.connectors.wstspl;

/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.uima.SentenceAnnotation;
import org.apache.uima.TokenAnnotation;
import org.apache.uima.UimaContext;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.cas.FSIterator;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.tcas.Annotation;
import org.apache.uima.resource.ResourceInitializationException;
//import org.cleartk.util.UIMAUtil;




import fr.univnantes.lina.uima.common.ae.CommonAE;
import fr.univnantes.lina.uima.common.cas.AnnotationCollectionUtils;
import fr.univnantes.lina.uima.common.cas.AnnotationUtils;
import fr.univnantes.lina.uima.common.cas.JCasUtils;




/**
 * Load into the CAS, the sentence and the word annotations represented in WST format within a given view 
 * <p>
 * This AE takes two parameters:
 * <ul>
 * <li><code>InputView</code> - view where are defined the sentence and the word annotations. Optional, by default 
 * <code>_InitialView</code></li>
 * <li><code>SentenceType</code> - type name of the Sentence annotations to create for each text part separated by a newline character. Optional, by default <code>org.apache.uima.SentenceAnnotation</code></li>
 * <li><code>WordType</code> - type name of the Word annotations to create for each text part separated by a whitespace character. Optional, by default <code>org.apache.uima.TokenAnnotation</code></li>
 * <li><code>WordTagFeature</code> - tag feature name of the Word annotations to set if specified. Optional, by default <code>org.apache.uima.TokenAnnotation</code></li>
 * <li><code>WordTagSeparator</code> - character which separates the word and the tag. Optional, by default <code>_</code></li>

 * </ul>
 * 
 * 
 */
public class AdhocWSTSPL2CASAE extends CommonAE {

	/*
	 * PARAMETERS NAMES
	 */
	/**
	 * Parameter name of the sentence type
	 */
	public static final String PARAM_SENTENCE_TYPE = "SentenceType";

	/**
	 * Parameter name of the word type 
	 */
	public static final String PARAM_WORD_TYPE = "WordType";

	public static final String PARAM_WORDTAGFEAT_TYPE = "WordTagFeature";

	public static final String PARAM_WORDSEP = "WordSeparator";


	public static final String PARAM_WORDTAGSEP = "WordTagSeparator";

	/**
	 * Parameter name for creating sentences 
	 */
	public static final String PARAM_CREATE_SENTENCES = "CreateSentences";

	/**
	 * Parameter name for creating words 
	 */
	public static final String PARAM_CREATE_WORDS= "CreateWords";


	/*
	 * PARAMETERS DEFAULT VALUES
	 */
	/**
	 * Default value of the word type
	 */
	public static String DEFAULT_WORD_TYPE = "org.apache.uima.TokenAnnotation";

	/**
	 * Default value of the sentence type
	 */
	public static String DEFAULT_SENTENCE_TYPE = "org.apache.uima.SentenceAnnotation";

	/**
	 * Default sentence end character value
	 */
	public static String DEFAULT_SENTENCE_END_CHARACTER_VALUE =	"\n" ;

	public static String DEFAULT_WORD_TAG_SEP =	"_" ;

	public static String DEFAULT_WORD_SEP =	" " ;


	/*
	 * LOCAL VARIABLES
	 */

	private String sentenceTypeName;
	private String wordTypeName;
	private String wordTagFeat;
	private String wordTagSep;
	private String wordSep;


	private Boolean createSentences ;
	private Boolean createWords ;
	private Pattern patternWordTagSep;

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
		sentenceTypeName = ((String) aContext.getConfigParameterValue(PARAM_SENTENCE_TYPE));
		if (sentenceTypeName == null) sentenceTypeName = DEFAULT_SENTENCE_TYPE;

		wordTypeName = ((String) aContext.getConfigParameterValue(PARAM_WORD_TYPE));
		if (wordTypeName == null) wordTypeName = DEFAULT_WORD_TYPE;

		wordSep = ((String) aContext.getConfigParameterValue(PARAM_WORDSEP));
		if (wordSep == null) wordSep = DEFAULT_WORD_SEP;

		wordTagSep = ((String) aContext.getConfigParameterValue(PARAM_WORDTAGSEP));
		if (wordTagSep == null) wordTagSep = DEFAULT_WORD_TAG_SEP;

		
		wordTagFeat = ((String) aContext.getConfigParameterValue(PARAM_WORDTAGFEAT_TYPE));
		if (wordTagFeat != null) {
			wordTagSep = wordTagSep+"[^"+wordTagSep+"]+$";
			patternWordTagSep = Pattern.compile(wordTagSep);
		}
		


		createSentences = ((Boolean) aContext.getConfigParameterValue(PARAM_CREATE_SENTENCES));
		if (createSentences == null) createSentences = true;
		createWords = ((Boolean) aContext.getConfigParameterValue(PARAM_CREATE_WORDS));
		if (createWords == null) createWords = true;

		if (!createSentences && !createWords) {
			String errmsg = "Both "+PARAM_CREATE_SENTENCES+" and "+PARAM_CREATE_WORDS+" parameters cannot be set to false together !";
			throw new ResourceInitializationException(errmsg, new Object[] {});
		}

		//try {
		//} catch (Exception e) {
		//	String errmsg = "!";
		//	throw new ResourceInitializationException(errmsg, new Object[] {});
		//}		
	}

	/**
	 * Based on the sentences and the words represented in WST format from a given view <br>
	 * Create the corresponding annotations into the CAS.
	 * 
	 * @return a null content
	 */
	@Override
	protected String processInputView(JCas inputViewJCas,
			FSIterator<Annotation> contextAnnotationsFSIter,
			Set<String> inputAnnotationSet,
			String inputFeatureString, JCas outputViewJCas,
			String outputAnnotationString, String ouputFeatureString)
					throws AnalysisEngineProcessException {

		//System.out.println("Debug: class>"+this.getClass().getName()+"< now>" + JavaUtilities.now());

		// Set the initial position of the first sentence and word 
		int sentenceBegin = 0;
		int wordBegin = 0;

		// Get the sofaDataString
		// Tokenize the sofaDataString with newline separator 
		// http://www.java-examples.com/java-stringtokenizer---specify-delimiter-example
		StringTokenizer textStringTokenizer = new StringTokenizer(JCasUtils.getSofaDataString(inputViewJCas), DEFAULT_SENTENCE_END_CHARACTER_VALUE);

		// For each line 
		while(textStringTokenizer.hasMoreTokens()) {
			String aSentence = textStringTokenizer.nextToken();
			//System.out.println("Debug: aSentence "+aSentence+ " begin "+sentenceBegin+" end "+ (sentenceBegin + aSentence.length()));
			//	Create a sentence annotation
			SentenceAnnotation aSentenceAnnotation = null;
			if (createSentences) aSentenceAnnotation = (SentenceAnnotation) AnnotationUtils.createAnnotation(inputViewJCas, sentenceTypeName, sentenceBegin, sentenceBegin + aSentence.length());
			
			 List<TokenAnnotation> tokenList =   new ArrayList<TokenAnnotation>();
			 // TODO étendre le type SentenceAnnotation avec un FS et l'ajouter
			 // faire la même chose à la sortie du tagger
			//	node.setHeadRelations(UIMAUtil.toFSArray(jCas, headRelations.get(node)));
			
			// 	Tokenize the line with whitespace separator
			StringTokenizer sentenceStringTokenizer = new StringTokenizer(aSentence, wordSep);

			// 	For each whitespace separated token  
			while(sentenceStringTokenizer.hasMoreTokens()) {
				String aWord = sentenceStringTokenizer.nextToken();
				//System.out.println("Debug: aWord "+aWord+ " begin "+wordBegin+" end "+ (wordBegin + aWord.length()));
				//		Create an word annotation
				if (patternWordTagSep!= null) {
					Matcher matcher = patternWordTagSep.matcher(aWord);
					if (matcher.find()){
						Map<String,Object> featuresHashMap = new HashMap<String, Object>();
						featuresHashMap.put("begin",String.valueOf(wordBegin));
						int start = matcher.start();
						int curEnd= wordBegin + start;
						featuresHashMap.put("end",String.valueOf(curEnd));
						//System.out.println("Debug: wordTagFeat>"+wordTagFeat+"< aWord.substring>"+aWord.substring(wordBegin + matcher.start()+1)+"<");
						String wordTagVal = aWord.substring(start+1);
						featuresHashMap.put(wordTagFeat,wordTagVal);

						TokenAnnotation aToken;
						if (createWords) { aToken = (TokenAnnotation) AnnotationUtils.createAnnotation(inputViewJCas, wordTypeName, featuresHashMap);
							tokenList.add(aToken);
						}
						//
						//if (createWords) AnnotationUtils.createAnnotation(inputViewJCas, wordTypeName, wordBegin, wordBegin + matcher.start());

					}
				}
				else if (createWords) AnnotationUtils.createAnnotation(inputViewJCas, wordTypeName, wordBegin, wordBegin + aWord.length());
				wordBegin = wordBegin + aWord.length()+ 1;
			}
			sentenceBegin = sentenceBegin + aSentence.length() + 1;
			wordBegin = sentenceBegin;	
			
			if (aSentenceAnnotation != null) aSentenceAnnotation.setTokenList(AnnotationCollectionUtils.toFSArray(inputViewJCas, tokenList));
		}

		// return a default value, because required, but the outputview should not be used
		return inputViewJCas.getDocumentText();
	}

}




