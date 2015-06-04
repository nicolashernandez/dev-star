package fr.univnantes.lina.uima.connectors.bio;

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
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

import org.apache.uima.UimaContext;
import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.cas.CAS;
import org.apache.uima.cas.CASException;
import org.apache.uima.cas.FSIterator;
import org.apache.uima.cas.Feature;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.text.AnnotationIndex;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.tcas.Annotation;
import org.apache.uima.resource.ResourceInitializationException;

import fr.univnantes.lina.uima.common.ae.CommonAE;
import fr.univnantes.lina.uima.common.cas.AnnotationCollectionUtils;
import fr.univnantes.lina.uima.common.cas.AnnotationUtils;
import fr.univnantes.lina.uima.common.cas.FeatureUtils;
import fr.univnantes.lina.uima.common.cas.JCasUtils;


/**
 * Update some Word Annotations from a given view with some <code>begin, inside, end</code> information relatively to other some Chunk annotations.  
 * <p>
 * This AE takes the following parameters:
 * <ul>
 * <li><code>InputView</code> - view where the chunk and the word annotations 
 * are present. Optional, by default <code>_InitialView</code></li>
 * <li><code>ChunkAnnotation</code> - type of the Chunk Annotation. 
 * Optional, by default <code>opennlp.uima.Chunk</code>.</li>
 * <li><code>ChunkTypeFeature</code> - feature of the Chunk Annotation where 
 * the type (e.g. NP, VP, PP, AP) of the chunk is specified (e.g. chunkFeature). 
 * Optional, by default <code>chunkType</code>.</li>
 * <li><code>WordAnnotation</code> - type of the Word Annotation covered by the 
 * chunks and whose chunk tag feature should be updated with begin, inside or end value. 
 * Optional, by default <code>opennlp.uima.Token</code>.</li>
 * <li><code>ChunkTagFeature</code> - feature of the Word Annotation where the 
 * chunk tag should be updated with begin, inside or end value. 
 * Optional, by default <code>chunkTag</code>.</li>
 * <li><code>ChunkFamilies</code> -  lists of chunk types which do not cut each other. 
 * In other words, if one of the type of a family already begins a chunk then any following 
 * type of its family will be set to internal. 
 * Each element of the list is separated by a whitespace.    
 * Optional.
 * 
 * Given the type of the current chunk, should both take into consideration the previous type of the current word and the type of the previous word
 * </li>
 * </ul>
 * 
 * 
 */
public class ChunkFamiliesAnnotationToWordAnnotationAE extends CommonAE {

	/*
	 * PARAMETERS NAMES
	 */
	/**
	 * Parameter name of the chunk annotation
	 */
	public static final String PARAM_CHUNK_ANNOTATION = "ChunkAnnotation";

	/**
	 * Parameter name of the chunk type feature
	 */
	public static final String PARAM_CHUNKTYPE_FEATURE = "ChunkTypeFeature";

	/**
	 * Parameter name of the word annotation 
	 */
	public static final String PARAM_WORD_ANNOTATION = "WordAnnotation";

	/**
	 * Parameter name of the fine Chung tag feature
	 */
	public static final String PARAM_FINECHUNKTAG_FEATURE = "fineGrainedChunkTag";

	/**
	 * Parameter name of the coarse Chung tag feature
	 */
	public static final String PARAM_COARSECHUNKTAG_FEATURE = "coarseGrainedChunkTag";

	/**
	 * Parameter name of the embedding path chung tag feature
	 */
	public static final String PARAM_PATHCHUNKTAG_FEATURE = "embeddingPathChunkTag";

	/**
	 * Parameter name of the ChunkFamily
	 */
	public static final String PARAM_CHUNK_FAMILIES = "ChunkFamilies";

	/**
	 * Parameter name of the chunk annotation strategy: 
	 * fine-grained (word inherits from the most embedded chunk annotation), 
	 * rough-grained (word inherits from the most embedding chunk annotation) 
	 * embedding-path (word inherits from the concatenation of the whole embedding chunk annotation path
	 * Default is the fine-grained
	 * 
	 * no parameter anymore but a word feature parameter for each strategy corresponding to the feature name of the chunk value
	 */
	//public static final String PARAM_CHUNK_ANNOTATION_STRATEGY = "ChunkAnnotationStrategy";

	/*
	 * PARAMETERS DEFAULT VALUES
	 */
	/**
	 * Default value of the chunk type
	 */
	public static String DEFAULT_CHUNK_TYPE = "opennlp.uima.Chunk";

	/**
	 * Default value of the chunk type feature
	 */
	public static String DEFAULT_CHUNKTYPE_FEATURE = "chunkType";

	/**
	 * Default value of the word type
	 */
	public static String DEFAULT_WORD_TYPE = "opennlp.uima.Token";

	/**
	 * Default value of the word chunk tag Feature
	 */
	public static String DEFAULT_FINE_CHUNKTAG_FEATURE = "fineGrainedChunkTag"; //chunkTag

	/**
	 * Default sentence end character value
	 */
	public static String DEFAULT_SENTENCE_END_CHARACTER_VALUE =	"\n" ;

	/**
	 * Default OpenNLP separator between word and pos tag
	 */
	public static String DEFAULT_OPENNLP_POSTAG_SEPARATOR =	"_" ;

	/**
	 * Default value of chunk annotation strategy
	 */
	public static String DEFAULT_CHUNK_ANNOTATION_STRATEGY = "fine-grained";

	/**
	 * chunk annotation fine grained strategy
	 */
	public final static String FINE_GRAINED_CHUNK_ANNOTATION_STRATEGY = "fine-grained";

	/**
	 * chunk annotation fine rough strategy
	 */
	public final static String ROUGH_GRAINED_CHUNK_ANNOTATION_STRATEGY = "rough-grained";

	/**
	 * chunk annotation fine rough strategy
	 */
	public final static String EMBEDDING_PATH_CHUNK_ANNOTATION_STRATEGY = "embedding-path";

	/*
	 * LOCAL VARIABLES
	 */

	private String chunkTypeName;
	private String chunkTypeFeatureName;
	private String wordTypeName;
	private String chunkFineTagFeatureName;
	private String chunkCoarseTagFeatureName;

	private String chunkPathTagFeatureName;

	private String[] chunkFamilies;
	private List<Map<String,Integer>> chunkFamilyList;
	//private String chunkAnnotationStrategy;

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
		chunkTypeName = ((String) aContext.getConfigParameterValue(PARAM_CHUNK_ANNOTATION));
		if (chunkTypeName == null) chunkTypeName = DEFAULT_CHUNK_TYPE;

		chunkTypeFeatureName = ((String) aContext.getConfigParameterValue(PARAM_CHUNKTYPE_FEATURE));
		if (chunkTypeFeatureName == null) chunkTypeFeatureName = DEFAULT_CHUNKTYPE_FEATURE;

		wordTypeName = ((String) aContext.getConfigParameterValue(PARAM_WORD_ANNOTATION));
		if (wordTypeName == null) wordTypeName = DEFAULT_WORD_TYPE;

		chunkFineTagFeatureName = ((String) aContext.getConfigParameterValue(PARAM_FINECHUNKTAG_FEATURE));
		if (chunkFineTagFeatureName == null) chunkFineTagFeatureName = DEFAULT_FINE_CHUNKTAG_FEATURE;

		chunkCoarseTagFeatureName = ((String) aContext.getConfigParameterValue(PARAM_COARSECHUNKTAG_FEATURE));
		chunkPathTagFeatureName = ((String) aContext.getConfigParameterValue(PARAM_PATHCHUNKTAG_FEATURE));


		chunkFamilies = ((String[]) aContext.getConfigParameterValue(PARAM_CHUNK_FAMILIES));
		if (chunkFamilies != null) {
			chunkFamilyList = new ArrayList<Map<String,Integer>>();
			for (int i = 0; i < chunkFamilies.length ; i++) {
				String aChunkFamily[] = chunkFamilies[i].split(" ");
				Map<String,Integer> aChunkFamilyMap = new HashMap<String,Integer>();
				for (int j = 0; j < aChunkFamily.length ; j++) {
					aChunkFamilyMap.put(aChunkFamily[j],1);
				}
				chunkFamilyList.add(aChunkFamilyMap);
			}
		}

		//chunkAnnotationStrategy = ((String) aContext.getConfigParameterValue(PARAM_CHUNK_ANNOTATION_STRATEGY));
		//if (chunkAnnotationStrategy == null) chunkAnnotationStrategy = DEFAULT_CHUNK_ANNOTATION_STRATEGY;

		//try {
		//} catch (Exception e) {
		//	String errmsg = "!";
		//	throw new ResourceInitializationException(errmsg, new Object[] {});
		//}		
	}

	/**
	 * The actual process
	 * 
	 * @return the created text to be the dataString of the future created outputView
	 */
	@Override
	protected String processInputView(JCas inputViewJCas,
			FSIterator<Annotation> contextAnnotationsFSIter,
			Set<String> inputAnnotationSet,
			String inputFeatureString, 
			JCas outputViewJCas,
			String outputAnnotationString, 
			String ouputFeatureString)
					throws AnalysisEngineProcessException {

		// Set an hashmap with the word type name
		// Will be used for selecting the annotations subsumed by the sentence annotations
		HashMap<String, Integer> subSentenceAnnotationsHashMap = new HashMap<String, Integer>();
		subSentenceAnnotationsHashMap.put(wordTypeName, 1);

		// Get an iterator of chunk from the chunkTypeName
		Type chunkType = JCasUtils.getJCasType(inputViewJCas, chunkTypeName);
		AnnotationIndex<Annotation> chunkAnnotationIndex = (AnnotationIndex<Annotation>) inputViewJCas
				.getAnnotationIndex(chunkType);
		Iterator<Annotation> chunkAnnotationIterator  = chunkAnnotationIndex
				.iterator();

		//int counter = 0;
		//Date startDate = JavaUtilities.getNow();
		int lastEmbeddingChunkAnnotationEndOffset  = -1;
		List<List<String>> chunkStack = new ArrayList<List<String>>();

		// For each chunk annotation
		// Fine-grained chunk annotation
		// the algorithm assumes that the chunk annotation are ordered
		// consequently the word inherits its value from the closest subsuming chunk
		while (chunkAnnotationIterator.hasNext()) {

			// a chunk annotation
			Annotation aChunkAnnotation = (Annotation) chunkAnnotationIterator
					.next();

			int currentEmbeddingChunkAnnotationBeginOffset = aChunkAnnotation.getBegin();
			int currentEmbeddingChunkAnnotationEndOffset = aChunkAnnotation.getEnd();

			//if (chunkAnnotationStrategy.equalsIgnoreCase(ROUGH_GRAINED_CHUNK_ANNOTATION_STRATEGY)) {
			if (chunkCoarseTagFeatureName != null) {
				// if the previous embedding chunk annotation does not cover the text anymore
				if (currentEmbeddingChunkAnnotationEndOffset >= lastEmbeddingChunkAnnotationEndOffset) {
					lastEmbeddingChunkAnnotationEndOffset = currentEmbeddingChunkAnnotationEndOffset;

					String chunkTypeValue = (String) FeatureUtils.getFeatureValue(aChunkAnnotation,  chunkTypeFeatureName);

					assignAChunkTagBIOValue(inputViewJCas, aChunkAnnotation, chunkCoarseTagFeatureName, chunkTypeValue);
				}
			}
			//else 
			// embedding-path
			//if (chunkAnnotationStrategy.equalsIgnoreCase(EMBEDDING_PATH_CHUNK_ANNOTATION_STRATEGY)) 
			if (chunkPathTagFeatureName != null) {
			

				String chunkTypeValue = (String) FeatureUtils.getFeatureValue(aChunkAnnotation,  chunkTypeFeatureName);


				// push
				List<String> endOffsetChunkAnnotationAssociation = new ArrayList<String>();
				endOffsetChunkAnnotationAssociation.add(String.valueOf(currentEmbeddingChunkAnnotationEndOffset));
				endOffsetChunkAnnotationAssociation.add(chunkTypeValue);
				chunkStack.add(endOffsetChunkAnnotationAssociation);

				// pop
				// we check if the end offset of each previous chunk annotation is superior to the current begin
				// if not we removed it by not copying it to the next structure 
				List<List<String>> futureChunkStack  = new ArrayList<List<String>>();
				for (List<String> currentEndOffsetChunkAnnotationAssociation : chunkStack) {
					if (Integer.valueOf(currentEndOffsetChunkAnnotationAssociation.get(0)) >= currentEmbeddingChunkAnnotationBeginOffset) {
						futureChunkStack.add (currentEndOffsetChunkAnnotationAssociation);
					}
				}
				chunkStack.clear();
				chunkStack.addAll(futureChunkStack);

				// concat
				String concatenedChunkTypeFeatureName = "";
				for (List<String> currentEndOffsetChunkAnnotationAssociation : chunkStack) {
					concatenedChunkTypeFeatureName += "_"+currentEndOffsetChunkAnnotationAssociation.get(1);
				}
				if (concatenedChunkTypeFeatureName.length() >1) concatenedChunkTypeFeatureName = concatenedChunkTypeFeatureName.substring(1);
				else concatenedChunkTypeFeatureName = null;

				assignAChunkTagBIOValue(inputViewJCas, aChunkAnnotation, chunkPathTagFeatureName, concatenedChunkTypeFeatureName);
			}
			//else 
			//  fine-grained 
			{
				String chunkTypeValue = (String) FeatureUtils.getFeatureValue(aChunkAnnotation,  chunkTypeFeatureName);
				assignAChunkTagBIOValue(inputViewJCas, aChunkAnnotation, chunkFineTagFeatureName, chunkTypeValue);
			}

		}

		// Smoothing phase
		// Set all the subsequent words belonging to a same family to I and to the type of the first word
		// check if some chunk families have been defined
		if (chunkFamilyList != null) {

			Type wordType = JCasUtils.getJCasType(inputViewJCas, wordTypeName);
			AnnotationIndex<Annotation> wordAnnotationIndex = (AnnotationIndex<Annotation>) inputViewJCas
					.getAnnotationIndex(wordType);
			Iterator<Annotation> wordAnnotationIterator  = wordAnnotationIndex
					.iterator();

			String previousChunkType = "";
			// For each word annotation
			while (wordAnnotationIterator.hasNext()) {

				// a word annotation
				Annotation aWordAnnotation = (Annotation) wordAnnotationIterator
						.next();

				//System.out.print("Debug: w>"+aWordAnnotation.getCoveredText()+"<");

				String currentChunkTag = (String) FeatureUtils.getFeatureValue(aWordAnnotation,  chunkFineTagFeatureName);

				// check if the current word annotation have been its feature set, some word may not have one						
				if (currentChunkTag != null) {
					String currentChunkType =currentChunkTag.substring(2);

					//System.out.print(" chunkTag>"+currentChunkTag+"<");

					// check if the previous and the current one occur in the same family
					Iterator<Map<String,Integer>> chunkFamilyListIterator = chunkFamilyList.iterator();
					boolean occurInTheSameFamily = false;  
					while (chunkFamilyListIterator.hasNext() && !occurInTheSameFamily) {
						Map<String,Integer> aChunkFamilyMap = chunkFamilyListIterator.next();
						//System.out.println("Debug: value previously set>"+chunkTagValue.substring(2)+"< value of the current chunk>"+chunkTypeValue+"<");
						if (aChunkFamilyMap.containsKey(currentChunkType) 
								&& aChunkFamilyMap.containsKey(previousChunkType)) {
							occurInTheSameFamily = true;
						}

					}
					if (occurInTheSameFamily ) {

						// && (!currentChunkType.equalsIgnoreCase(previousChunkType))
						HashMap<String, Object> newInsideFeaturesHashMap = new HashMap<String, Object>();
						newInsideFeaturesHashMap.put(chunkFineTagFeatureName, "I"+"-"+ previousChunkType);
						AnnotationUtils.updateAnnotation(inputViewJCas, aWordAnnotation, wordTypeName, newInsideFeaturesHashMap);
						// previousChunkType remains the same than the first one encountered of this family
						//System.out.println(" corrected in>I-"+ previousChunkType+"<");

					}
					else {
						// are not in the same Family
						previousChunkType = currentChunkType;
						//System.out.println(" not corrected");

					}

				}
				else {  
					// the current word has its feature not set
					previousChunkType = "";
					//System.out.println("");
				}


			}

		}


		// return something, not important here since there is no view created
		return inputViewJCas.getDocumentText();
	}

	/**
	 * Assign a chunk tag value (wi B and I prefix) for all the words covered by the chunk annotation
	 * @param inputViewJCas
	 * @param aChunkAnnotation
	 * @param chunkTagFeatureName TODO
	 * @param newChunkTypeValue either got from the current chunk annotation or given from the past processing like for embedding path
	 * @throws AnalysisEngineProcessException
	 */
	private void assignAChunkTagBIOValue(JCas inputViewJCas, Annotation aChunkAnnotation, String chunkTagFeatureName, String chunkTypeValue)
			throws AnalysisEngineProcessException {
		//Method chunkTypeFeatureGetterMethod = FeatureUtils.getFeatureGetterMethod(AnnotationUtils.getAnnotationClass(chunkTypeName), chunkTypeFeatureName);
		//String chunkTypeValue= (String) FeatureUtils.invokeFeatureGetterMethod(aChunkAnnotation, chunkTypeFeatureGetterMethod);


		//String chunkTypeValue = (String) FeatureUtils.getFeatureValue(aChunkAnnotation,  chunkTypeFeatureName);

		//if (newChunkTypeValue != null) chunkTypeValue = newChunkTypeValue;

		//chunkFineTagFeatureName
		HashMap<String, Object> beginFeaturesHashMap = new HashMap<String, Object>();
		beginFeaturesHashMap.put(chunkTagFeatureName, "B"+"-"+ chunkTypeValue);

		HashMap<String, Object> insideFeaturesHashMap = new HashMap<String, Object>();
		insideFeaturesHashMap.put(chunkTagFeatureName, "I"+"-"+ chunkTypeValue);

		// A constrained subiterator
		//FSIterator wordsAnnotationIterator = AnnotationCollectionUtils.subiterator(
		//		inputViewJCas, aSentenceAnnotation,
		//		subSentenceAnnotationsHashMap, false);
		// Much faster with a simple subiterator
		FSIterator wordsAnnotationIterator = AnnotationCollectionUtils.subiterator(
				inputViewJCas, aChunkAnnotation);

		Feature f;

		// write a new line of word separated by whitespace character 
		String aChunkString = "";
		int wordRankInsideTheChunk = 0;
		while (wordsAnnotationIterator.hasNext()) {
			Annotation aWordAnnotation = (Annotation) wordsAnnotationIterator
					.next();

			//System.out.println("Debug: aWordAnnotation.getClass().getName() "+aWordAnnotation.getClass().getName()+" wordTypeName"+ wordTypeName);
			if (aWordAnnotation.getClass().getName().equalsIgnoreCase(wordTypeName))  {  
				// update the word annotation
				//if (! aWordAnnotation.getCoveredText().trim().equalsIgnoreCase("")) aChunkString += " " + aWordAnnotation.getCoveredText().trim().replaceAll(" ", "-") + DEFAULT_OPENNLP_POSTAG_SEPARATOR + aWordAnnotation.getStringValue(aWordAnnotation.getType().getFeatureByBaseName(chunkTagFeatureName));
				//	FeatureUtils.getFeatureGetterMethod(InputAnnotationClass, inputFeatureString)

				//String textWord = (String) FeatureUtils.getFeatureValue(aWordAnnotation, "coveredText");
				//textWord = textWord.replaceAll("[\n\t]+", " ");
				//textWord = textWord.replaceAll(" +", " ").trim();
				//if (! textWord.equalsIgnoreCase("")) {

				// if not set yet by a subsuming annotation
				if (wordRankInsideTheChunk == 0)
					// set a begin
					AnnotationUtils.updateAnnotation(inputViewJCas, aWordAnnotation, wordTypeName, beginFeaturesHashMap);
				else
					// set an Inside
					AnnotationUtils.updateAnnotation(inputViewJCas, aWordAnnotation, wordTypeName, insideFeaturesHashMap);
				wordRankInsideTheChunk++;
				//}
			}
		}
	}

}




