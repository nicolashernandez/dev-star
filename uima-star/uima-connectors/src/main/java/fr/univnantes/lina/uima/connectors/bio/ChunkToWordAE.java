
package fr.univnantes.lina.uima.connectors.bio;

/** 
 * Copyright (C) 2011-2013  Nicolas Hernandez
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.uima.UimaContext;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.cas.FSIterator;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.text.AnnotationIndex;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.tcas.Annotation;
import org.apache.uima.resource.ResourceInitializationException;


import fr.univnantes.lina.uima.common.ae.CommonAE;
import fr.univnantes.lina.uima.common.cas.AnnotationCollectionUtils;
import fr.univnantes.lina.uima.common.cas.AnnotationUtils;
import fr.univnantes.lina.uima.common.cas.FeatureUtils;



/**
 * Transform a chunk annotation to several word annotations 
 * set a feature path of subsumed annotation with the feature path value of a subsuming annotation
 * depending on the position of the subsumed annotation it is possible to prefix the value with BIO prefix
 * 
 */
public class ChunkToWordAE extends CommonAE {

	/*
	 * PARAMETERS NAMES
	 */
	/**
	 * Parameter name of CHUNK_TO_WORD_RULES 
	 */
	public static final String PARAM_CHUNK_TO_WORD_RULES = "ChunkToWordTransformationRules";
	public static final String PARAM_ADD_BIO_PREFIX = "Add_BIO_Prefix";
	/*public static final String PARAM_ADD_INSIDE_BIO_PREFIX = "Add_Inside_BIO_Prefix";
	public static final String PARAM_ADD_OUTSIDE_BIO_PREFIX = "Add_Outside_BIO_Prefix";*/

	/*
	 * DEFAULT VALUES
	 */

	private static final String RULEMEMBERSEPARATOR = "->";
	private static final String SRCTYPE = "srcType";
	private static final String SRCFEATURE= "srcFeature";
	private static final String TGTTYPE= "tgtType";
	private static final String TGTFEATURE= "tgtFeature";

	private static final Boolean DEFAULT_ADD_BIO_PREFIX = false;
	private static final String DEFAULT_BEGIN_BIO_PREFIX = "B-";
	private static final String DEFAULT_INSIDE_BIO_PREFIX = "I-";
	private static final String DEFAULT_OUTSIDE_BIO_PREFIX = "O";
	private static final String FEATUREPATHSEPARATOR = ":";

	/*
	 * LOCAL VARIABLES
	 */
	private List<Map<String,String>> chunkToTagMapList;
	private Boolean addBIOPrefix = false;
	/*private Boolean addInsideBIOPrefix = false;
	private Boolean addOutsideBIOPrefix = false;*/

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
		String [] chunkToTagArray = ((String[]) aContext.getConfigParameterValue(PARAM_CHUNK_TO_WORD_RULES));

		if (chunkToTagArray == null)  {
			String errmsg = "At least one conversion rule should be written. See parameter "+PARAM_CHUNK_TO_WORD_RULES+" !";
			throw new ResourceInitializationException(errmsg, new Object[] {});
		}	

		chunkToTagMapList = new ArrayList<Map<String,String>>();
		for (String rule : chunkToTagArray) {
			String ruleMembers [] = rule.split(RULEMEMBERSEPARATOR);
			String [] srcMember =  ruleMembers[0].split(FEATUREPATHSEPARATOR);
			String [] tgtMember =  ruleMembers[1].split(FEATUREPATHSEPARATOR);

			if (ruleMembers.length != 2 || srcMember.length !=2 || tgtMember.length != 2) throwRuleSyntaxException();	


			Map<String,String> ruleMembersMap = new HashMap<String,String>();
			ruleMembersMap.put(SRCTYPE, srcMember[0].trim());
			ruleMembersMap.put(SRCFEATURE, srcMember[1].trim());
			ruleMembersMap.put(TGTTYPE, tgtMember[0].trim());
			ruleMembersMap.put(TGTFEATURE, tgtMember[1].trim());

			//System.err.println("Debug: ruleMembersMap keySet>"+ruleMembersMap.keySet()+"<");
			//System.err.println("Debug: ruleMembersMap values>"+ruleMembersMap.values()+"<");
			chunkToTagMapList.add(ruleMembersMap);

		}

		addBIOPrefix = ((Boolean) aContext.getConfigParameterValue(PARAM_ADD_BIO_PREFIX));
		if (addBIOPrefix == null) addBIOPrefix = DEFAULT_ADD_BIO_PREFIX;

		/*addInsideBIOPrefix = ((Boolean) aContext.getConfigParameterValue(PARAM_ADD_INSIDE_BIO_PREFIX));
		if (addInsideBIOPrefix == null) addInsideBIOPrefix = DEFAULT_ADD_BIO_PREFIX;

		addOutsideBIOPrefix = ((Boolean) aContext.getConfigParameterValue(PARAM_ADD_OUTSIDE_BIO_PREFIX));
		if (addOutsideBIOPrefix == null) addOutsideBIOPrefix = DEFAULT_ADD_BIO_PREFIX;
		 */
		//try {
		//} catch (Exception e) {
		//	String errmsg = "!";
		//	throw new ResourceInitializationException(errmsg, new Object[] {});
		//}		
	}

	/**
	 * @param ruleMembers
	 * @throws ResourceInitializationException
	 */
	private void throwRuleSyntaxException()
			throws ResourceInitializationException {
		String errmsg = "Wrong rule syntax. Use: ChunkType:feature "+RULEMEMBERSEPARATOR+" TagType:feature";
		throw new ResourceInitializationException(errmsg, new Object[] {});
	}

	/**
	 * foreach rule
	 *  get the srtType.subiterator tgtType 
	 * 	foreach sub tgt type
	 *		setFeature	tgtFeature wi get srcFeatureValue
	 *		in particular if (B_prefix) the first one prefix the value with B_
	 *					  else (I_prefix) I_
	 * if (O_prefix) 
	 *   foreach tgtType
	 *     if get tgtFeature is not set then set it to O
	 */
	@Override
	protected String processContextAnnotation(JCas inputViewJCas,
			FSIterator contextAnnotationsFSIter, Annotation contextAnnotation,
			FSIterator contextualizedInputAnnotationsFSIter,
			String inputFeatureString, JCas outputViewJCas,
			String outputAnnotationString, String ouputFeatureString)
					throws AnalysisEngineProcessException {

		// for all the rules
		for (Map<String,String> rule : chunkToTagMapList) {

			Type srcType = inputViewJCas.getTypeSystem().getType(rule.get(SRCTYPE));
			AnnotationIndex<Annotation> srcAnnotationIndex = inputViewJCas.getAnnotationIndex(srcType);

			FSIterator<Annotation> srcAnnotationIterator = srcAnnotationIndex.iterator();

			// for all the src annotations
			while (srcAnnotationIterator.hasNext()) {
				Annotation aSrcAnnotation = srcAnnotationIterator.next();

				// to filter when a type has some extension
				// e.g. NamedEntity is extended by Person ; if you specify NamedEntity then you will get both...
				if (aSrcAnnotation.getType().getName().equalsIgnoreCase(rule.get(SRCTYPE))) {

					FSIterator<Annotation> subsumedAnnotationIterator =  AnnotationCollectionUtils.subiterator(inputViewJCas, aSrcAnnotation);

					int bioPosition = 0;
					while (subsumedAnnotationIterator.hasNext()) {
						Annotation aSubsumedAnnotation = subsumedAnnotationIterator.next();
						//Common.types.coref.Coreference 
						if (aSubsumedAnnotation.getType().getName().equalsIgnoreCase(rule.get(TGTTYPE))) {
							//System.out.println("Debug: aSubsumedAnnotation>"+aSubsumedAnnotation.getCoveredText()+"<");
							//LexicalUnit aSubsumedLexicalUnit  = (LexicalUnit) aSubsumedAnnotation ;

							String tgtFeatureValue = "";
							if (addBIOPrefix) {
								if (bioPosition == 0) tgtFeatureValue += DEFAULT_BEGIN_BIO_PREFIX;
								else tgtFeatureValue += DEFAULT_INSIDE_BIO_PREFIX;
							}
							tgtFeatureValue += (String) FeatureUtils.getFeatureValue(aSrcAnnotation, rule.get(SRCFEATURE));
							//System.err.println("Debug: chunkType>"+aSrcAnnotation.getType().getName()+"< tokenType>"+aSubsumedAnnotation.getType().getName()+"< coveredText>"+aSrcAnnotation.getCoveredText()+"< SRCFEATURE>"+rule.get(SRCFEATURE)+"< tgtFeatureValue>"+tgtFeatureValue+"<");
							Map<String,Object> featuresHashMap = new HashMap<String,Object>();
							featuresHashMap.put(rule.get(TGTFEATURE),tgtFeatureValue);
							//System.err.println("Debug: aSubsumedAnnotation>"+aSubsumedAnnotation.getClass().getName()+"<");
							AnnotationUtils.updateAnnotation(inputViewJCas, aSubsumedAnnotation, rule.get(TGTTYPE), featuresHashMap);
							//AnnotationUtils.updateAnnotation(inputViewJCas, aSubsumedLexicalUnit, rule.get(TGTTYPE), featuresHashMap);

							bioPosition++;
						}
					}
				}


			}

			if (addBIOPrefix) {
				// for all the remaining annotations not processed add BIO prefix
				Type tgtType = inputViewJCas.getTypeSystem().getType(rule.get(TGTTYPE));
				AnnotationIndex<Annotation> tgtAnnotationIndex = inputViewJCas.getAnnotationIndex(tgtType);

				FSIterator<Annotation> tgtAnnotationIterator = tgtAnnotationIndex.iterator();
				while (tgtAnnotationIterator.hasNext()) {
					Annotation aTgtAnnotation = tgtAnnotationIterator.next();
					if (((String)FeatureUtils.getFeatureValue(aTgtAnnotation, rule.get(TGTFEATURE))) == null) {
						Map<String,Object> featuresHashMap = new HashMap<String,Object>();
						featuresHashMap.put(rule.get(TGTFEATURE),DEFAULT_OUTSIDE_BIO_PREFIX);
						AnnotationUtils.updateAnnotation(inputViewJCas, aTgtAnnotation, rule.get(TGTTYPE), featuresHashMap);
					}

				}
			}
		}
		//


		//
		return contextAnnotation.getCoveredText();
	}


}




