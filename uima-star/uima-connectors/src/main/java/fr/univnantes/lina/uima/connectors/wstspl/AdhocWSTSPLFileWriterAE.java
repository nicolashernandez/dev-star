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

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.Iterator;
import java.util.Set;

import org.apache.uima.SentenceAnnotation;
import org.apache.uima.UimaContext;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.cas.FSIterator;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.text.AnnotationIndex;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.cas.FSArray;
import org.apache.uima.jcas.tcas.Annotation;
import org.apache.uima.resource.ResourceInitializationException;

import fr.univnantes.lina.javautil.IOUtilities;
import fr.univnantes.lina.uima.common.ae.CommonAE;
import fr.univnantes.lina.uima.common.cas.AnnotationCollectionUtils;
import fr.univnantes.lina.uima.common.cas.FeatureUtils;
import fr.univnantes.lina.uima.common.cas.JCasUtils;



/**
 * Mixed of CAS2WSTSPL + ViewWriter, 
 * Create a new view turning into the whitespace-tokenized word  + a sentence per line format 
 * the sentence and the word annotations of a given view
 * And write the result in live
 * if POSFeature is set concatenate to the WordForm the tag value by separating them with posTagSeparatorCharacter
 * join whitespace character separating multi words into multiWordJoinCharacter
 * perform some substitutions over joint multiword to clean up stuff like d'_après or trente_-_et_-_un  '_ -> ' ; _-_ -> - 
 * <p>
 * This AE takes two parameters:
 * <ul>
 * <li><code>InputView</code> - view where are defined the sentence and the word annotations. Optional, by default 
 * <code>_InitialView</code></li>
 * <li><code>OutputView</code> - view to create with words and sentences in the WST format. Mandatory.</li>
 * <li><code>SentenceType</code> - type name of the Sentence annotations to turn into sentences separated by a newline character. Optional, by default <code>org.apache.uima.SentenceAnnotation</code></li>
 * <li><code>WordType</code> - type name of the Word annotations to turn into word separated by a whitespace character. Optional, by default <code>org.apache.uima.TokenAnnotation</code></li>
 * <li><code>WordFormFeature</code> - name of the Word Form feature to consider. Optional, by default <code>coveredText</code>. Be aware that whitespace characters in the form will be turned into SEE multiWordJoinCharacter parameter</li> 
 * <li><code>POSFeature</code> - name of the Word feature where the POS value is stored. Optional, by default <code></code>. If this value is empty, to tag will be added</li> 
 * <li><code>wordTagSeparatorCharacter</code> - character to join the pos to its word. Optional, by default <code>_</code>.</li> 
 * <li><code>multiWordJoinCharacter</code> - character to join whitespace separated multi words. Optional, by default <code>_</code>. Leave empty if you do not want a joint character and use whitespace char</li> 
 * <li><code>substitutionsOverJointedMultiWords</code> -  to clean up stuff like d'_après or trente_-_et_-_un  '_ -> ' ; _-_ -> - . Optional, by default <code></code>. Leave empty if you do not want any substitution</li> 

 * deprecated :<li><code>multiWordJoinCharacterCondition</code> - condition to join characters. Syntax: <code>featureName=value</code>. </li> 

 * </ul>
 * 
 * 
 */
public class AdhocWSTSPLFileWriterAE extends CommonAE {

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

	/**
	 * Parameter name of the POSFeature
	 */
	public static final String PARAM_POS_FEATURE = "TagFeature";

	/**
	 * Parameter name of the feature of the word type 
	 */
	public static final String PARAM_WORD_FORM_FEATURE_NAME = "WordFormFeature";

	/**
	 * Parameter name of the POSTAG_SEPARATOR
	 */
	public static final String PARAM_POSTAG_SEPARATOR_NAME = "wordTagSeparatorCharacter";
	/**
	 * Parameter name of the MULTIWORD_JOIN_CHARACTER
	 */
	public static final String PARAM_MULTIWORD_JOIN_CHARACTER_NAME = "multiWordJoinCharacter";

	/**
	 * Parameter name of multi words join character condition 
	 * corresponds to a feature path (e.g. isGraphicUnit=false).
Use the multiWordJoinCharacter if this feature path is valid for the current word.
Otherwise (if it s not valid) use no character.
By default, if nothing is specified, it will use the multiWordJoinCharacter.
	 */
	public static final String PARAM_SUBSTITUTION_OVER_JOINTED_MULTIWORD = "substitutionsOverJoinedMultiWords";


	/**
	 * Parameter name of directory to export the data
	 */
	public static final String PARAM_OUTPUT_DIRECTORY = "OutputDirectory";

	/**
	 * Parameter name of directory to export the data
	 */
	public static final String PARAM_SUFFIX = "OutputFileSuffix";
	
	
	/*
	 * PARAMETERS DEFAULT VALUES
	 */
	/**
	 * Default value of the word type
	 */
	public static String DEFAULT_WORD_TYPE = "org.apache.uima.TokenAnnotation";

	/**
	 * Default value of the feature of the word type
	 */
	public static String DEFAULT_WORD_FORM_FEATURE_NAME = "coveredText";


	/**
	 * Default value of the sentence type
	 */
	public static String DEFAULT_SENTENCE_TYPE = "org.apache.uima.SentenceAnnotation";

	/**
	 * Default value of the POS Feature
	 */
	public static String DEFAULT_TAG_FEATURE = "";

	/**
	 * Default sentence end character value
	 */
	public static String DEFAULT_SENTENCE_END_CHARACTER_VALUE =	"\n" ;

	/**
	 * Default OpenNLP separator between word and pos tag
	 */
	public static String DEFAULT_POSTAG_SEPARATOR =	"_" ;


	/**
	 * Default separator character to join multi words 
	 */
	public static String DEFAULT_JOIN_CHARACTER =	"_" ;

	/**
	 * Default multi words join character condition 
	 */
	public static String DEFAULT_SUBSTITIONS_JOINED_MULTIWORDS =	"" ;

	public static String DEFAULT_SUFFIX =	"" ;

	/*
	 * LOCAL VARIABLES
	 */

	private String sentenceTypeName;
	private String wordTypeName;
	private String posFeatureName;
	private String wordFormFeatureName;
	private String posTagSeparator;
	private String multiWordJoinCharacter;
	//private String substitutionOverJointedMultiWords;
	private String substitutionOverJointedMultiWordsArray[];
	private String outputDirectoryName;
	private String suffix;

	private File outputDirectory;

	/*
	 * Accessors
	 */


	/*
	 * Methods 
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

		posFeatureName = ((String) aContext.getConfigParameterValue(PARAM_POS_FEATURE));
		if (posFeatureName == null) posFeatureName = DEFAULT_TAG_FEATURE;

		wordFormFeatureName = ((String) aContext.getConfigParameterValue(PARAM_WORD_FORM_FEATURE_NAME));
		if (wordFormFeatureName == null) wordFormFeatureName = DEFAULT_WORD_FORM_FEATURE_NAME;

		posTagSeparator = ((String) aContext.getConfigParameterValue(PARAM_POSTAG_SEPARATOR_NAME));
		if (posTagSeparator == null) posTagSeparator = DEFAULT_POSTAG_SEPARATOR;

		multiWordJoinCharacter = ((String) aContext.getConfigParameterValue(PARAM_MULTIWORD_JOIN_CHARACTER_NAME));
		if (multiWordJoinCharacter == null) multiWordJoinCharacter = DEFAULT_JOIN_CHARACTER;

		substitutionOverJointedMultiWordsArray = ((String[]) aContext.getConfigParameterValue(PARAM_SUBSTITUTION_OVER_JOINTED_MULTIWORD));

		outputDirectoryName = ((String) aContext.getConfigParameterValue(PARAM_OUTPUT_DIRECTORY));
		if (outputDirectoryName == null) {
			String errmsg = "Parameter "+PARAM_OUTPUT_DIRECTORY+" should be defined !";
			throw new ResourceInitializationException(errmsg, new Object[] {});
		}
		outputDirectory = new File(outputDirectoryName);
		if (!outputDirectory.exists()) {
			outputDirectory.mkdirs();
		}
		
		suffix = ((String) aContext.getConfigParameterValue(PARAM_SUFFIX));
		if (suffix == null) suffix = DEFAULT_SUFFIX;


		//if (substitutionOverJointedMultiWordsArray == null) substitutionOverJointedMultiWordsArray = DEFAULT_SUBSTITIONS_JOINED_MULTIWORDS;
		//substitutionOverJointedMultiWordsArray = substitutionOverJointedMultiWords.split("=");
		//try {
		//} catch (Exception e) {
		//	String errmsg = "!";
		//	throw new ResourceInitializationException(errmsg, new Object[] {});
		//}		
	}

	/**
	 * Based on the input view sentence and word annotations, <br>
	 * Create a text made of sentences separated by newline characters and words separated by whitespace characters.
	 * In addition concat the tag to word separated by the dedicated separator.
	 * 
	 * @return the created text to be the dataString of the future created outputView
	 */
	@Override
	protected String processInputView(JCas inputViewJCas,
			FSIterator<Annotation> contextAnnotationsFSIter,
			Set<String> inputAnnotationSet,
			String inputFeatureString, JCas outputViewJCas,
			String outputAnnotationString, String ouputFeatureString)
					throws AnalysisEngineProcessException {

		String outFileName = JCasUtils.getAnArtifactName(inputViewJCas,true,"","");
		String outputFilePath =outputDirectory + System.getProperty ("file.separator")+outFileName;

		Writer out = null;
		//System.out.println("Debug: full Output File Name "+ fileName);

		//try {

			try {
				out = new OutputStreamWriter(new FileOutputStream(outputFilePath),"UTF8");
			} catch (UnsupportedEncodingException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}



			// Set an hashmap with the word type name
			// Will be used for selecting the annotations subsumed by the sentence annotations
			//HashMap<String, Integer> subSentenceAnnotationsHashMap = new HashMap<String, Integer>();
			//subSentenceAnnotationsHashMap.put(wordTypeName, 1);

			//
			String outputText = "";

			// Get an iterator of sentences from the sentenceTypeName
			Type sentenceType = JCasUtils.getJCasType(inputViewJCas, sentenceTypeName);
			AnnotationIndex<Annotation> sentenceAnnotationIndex = (AnnotationIndex<Annotation>) inputViewJCas
					.getAnnotationIndex(sentenceType);
			Iterator<Annotation> sentenceAnnotationIterator  = sentenceAnnotationIndex
					.iterator();

			//int counter = 0;
			//Date startDate = JavaUtilities.getNow();

			// For each sentence annotation 
			while (sentenceAnnotationIterator.hasNext()) {

				// a Sentence annotation
				//Annotation aSentenceAnnotation = (Annotation) sentenceAnnotationIterator			.next();
				
				SentenceAnnotation aSentenceAnnotation = (SentenceAnnotation) sentenceAnnotationIterator
						.next();

				// A constrained subiterator
				//FSIterator wordsAnnotationIterator = AnnotationCollectionUtils.subiterator(
				//		inputViewJCas, aSentenceAnnotation,
				//		subSentenceAnnotationsHashMap, false);
				// Much faster with a simple subiterator
				//FSIterator wordsAnnotationIterator = AnnotationCollectionUtils.subiterator(
				//		inputViewJCas, aSentenceAnnotation);
				FSArray wordsAnnotationIterator = aSentenceAnnotation.getTokenList();
			
				
				// write a new line of word separated by whitespace character 
				String aSentenceString = "";
				int i = 0;
				while (i < wordsAnnotationIterator.size()) {

				//while (wordsAnnotationIterator.hasNext()) {
					
					Annotation aWordAnnotation = (Annotation) wordsAnnotationIterator.get(i);
					//Annotation aWordAnnotation = (Annotation) wordsAnnotationIterator					.next();

					//System.out.println("Debug: aWordAnnotation.getClass().getName() "+aWordAnnotation.getClass().getName()+" wordTypeName"+ wordTypeName);
					if (aWordAnnotation.getClass().getName().equalsIgnoreCase(wordTypeName)) {
						String textWord = (String) FeatureUtils.getFeatureValue(aWordAnnotation, wordFormFeatureName);

						// get the condition to use the mw join character if the condition is specified
						/*Boolean isConditionValid = false;
					if (!substitutionOverJointedMultiWords.equalsIgnoreCase("")) { 
						if (substitutionOverJointedMultiWordsArray[1].equalsIgnoreCase((String) FeatureUtils.getFeatureValue(aWordAnnotation, substitutionOverJointedMultiWordsArray[0]))) {
							isConditionValid = true;
						}
					}
					String currentMultiWordJoinCharacter = "";
					if (isConditionValid) {currentMultiWordJoinCharacter = multiWordJoinCharacter;}
						 */

						// whitespace char
						textWord = textWord.replaceAll("[\n\t]+", " "); // celle_-_ci et commune_mesure 
						//textWord = textWord.replaceAll("[\n\t]+", "");    // celle-ci et communemesure
						textWord = textWord.replaceAll(" +", " ").trim();
						//if (! textWord.equalsIgnoreCase("")) aSentenceString += " " + textWord;
						//					if (! aWordAnnotation.getCoveredText().trim().equalsIgnoreCase("")) aSentenceString += " " + aWordAnnotation.getCoveredText().trim().replaceAll(" ", "-") + DEFAULT_OPENNLP_POSTAG_SEPARATOR + aWordAnnotation.getStringValue(aWordAnnotation.getType().getFeatureByBaseName(posFeatureName));
						textWord = textWord.replaceAll(" ", multiWordJoinCharacter);


						if (substitutionOverJointedMultiWordsArray != null) { 
							for (String s : substitutionOverJointedMultiWordsArray) {
								String [] subRule = s.split(" -> ");
								if (subRule.length == 2) {
									textWord =textWord.replaceAll(subRule[0], subRule[1]);
								}
								else  {
									System.err.println("Warning: wrong value >"+s+"< in parameter "+PARAM_SUBSTITUTION_OVER_JOINTED_MULTIWORD+" ; shoud be: string -> string");
								}
							}
						}

						// add a tag if mentionned
						String posFeatureValue =  "";
						if (!posFeatureName.equalsIgnoreCase("")) {
							posFeatureValue = aWordAnnotation.getStringValue(aWordAnnotation.getType().getFeatureByBaseName(posFeatureName));
							// for processing lemma
							if (posFeatureValue == null) posFeatureValue = "null"; //textWord ;
							else if (posFeatureValue.equalsIgnoreCase("")) posFeatureValue = "null"; 
							posFeatureValue = posFeatureValue.replaceAll(" ", "#:#");
							posFeatureValue = posTagSeparator +posFeatureValue;
						}



						// replace whitespace with joint char
						if (! textWord.equalsIgnoreCase("")) 
							aSentenceString += " " + textWord+ posFeatureValue;
						//aSentenceString += " " + textWord.replaceAll(" ", currentMultiWordJoinCharacter)+ posFeatureValue;


					}
				}
				if (!aSentenceString.equalsIgnoreCase("")) {
					
					String line = aSentenceString.substring(1) + DEFAULT_SENTENCE_END_CHARACTER_VALUE;
					outputText += line;
					// Serialize
					try {
						out.write(line);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					//System.out.println("Debug: s>"+aSentenceString.substring(1)+"<");
				}
				//System.out.println("Debug: s" + counter+" >"+aSentenceString+"<");
				//counter++;

				//if (counter == 100) {
				//	Date endDate = JavaUtilities.getNow();
				//	System.err.println("Info: after 1000 lines : " + JavaUtilities.dateDiff(startDate, endDate));
				//}

			}



			try {
				out.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}


//		} catch (FileNotFoundException ex) {
//			String errmsg = "The file "+outputFilePath+" is not found!";
//			throw new ResourceInitializationException(errmsg, new Object[] {});
//		} catch (IOException ex) {
//			String errmsg = "Exception when trying to write the file "+outputFilePath+"";
//			throw new ResourceInitializationException(errmsg, new Object[] {});
//		}
		// return the outputText of the new outputview
		return outputText;

	}

}




