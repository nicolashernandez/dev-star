package fr.univnantes.lina.uima.mlnlp.analysis.lexical;
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


import java.util.ArrayList;
import java.util.HashMap;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.uima.UimaContext;
import org.apache.uima.analysis_component.AnalysisComponent;
import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.cas.FSIterator;

import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.tcas.Annotation;
import org.apache.uima.resource.ResourceInitializationException;

import fr.univnantes.lina.uima.common.cas.AnnotationUtils;
import fr.univnantes.lina.uima.common.cas.JCasUtils;


/**
 * Search for any occurrences of some annotation covered texts
 * It is assumed that these annotations have been created w.r.t some contexts 
 * and that any occurrences have the same properties even in non pre listed contexts.
 *  
 * Guichard Le_plus_grand_télescope_du_monde_bientôt_en_service_au_Mexique
 * 
 * Eventuellement pour améliorer cet analyseur
 * * en param permettre de passer des règles de réécritures des variantes
 * * ou bien de spécifier des regexp à ga et à dr à considérer comme variante
 * <p>
 * </p>
 * 
 * 
 * 
 * @author Nicolas Hernandez
 */
public class VariantRecognizerAE extends JCasAnnotator_ImplBase {

	/*
	 * CONFIGURATION PARAMETER NAMES 
	 */

	/**
	 * Parameter name for Input Annotations
	 */
	public static final String PARAM_INPUT_ANNOTATION = "InputAnnotations";


	/*
	 * CONFIGURATION PARAMETER NAMES VALUES
	 */

	/**
	 * Name of the annotation type whose coveredText has to be looked for
	 */
	public String[] annotations = null;

	/**
	 * Memory of occurrences already searched
	 */
	public HashMap<String, Integer> patternHashMap = new HashMap<String, Integer>();

	/**
	 * Memory of occurrences matched with its properties
	 */
	public ArrayList<HashMap> occurrencesArrayOfHashMap = null;

	/*
	 * LOCAL VARIABLES
	 */


	/**
	 * Name of the default DOCUMENT_ANNOTATION
	 */
	//private static String DEFAULT_DOCUMENT_ANNOTATION = "uima.tcas.DocumentAnnotation";

	/**
	 * Value of the regexp extremities
	 */
	static final String regexpDelimiter = "[\\p{Punct}\\p{Space}]";

	/**
	 * Get the values of the configuration parameters
	 * 
	 * @see AnalysisComponent#initialize(UimaContext)
	 */
	public void initialize(UimaContext aContext)
	throws ResourceInitializationException {
		super.initialize(aContext);

		// Get config. parameter values
		annotations = (String[]) aContext.getConfigParameterValue(PARAM_INPUT_ANNOTATION);

	}


	/**
	 * process each CAS and export to a CSV file a specific annotation type as 
	 * lines and selected features as column
	 *
	 */
	public void process(JCas aJCas) throws AnalysisEngineProcessException {

		// get document text
		String docText = aJCas.getDocumentText();

		// for each considered annotation type
		for (String annotation : annotations) {

			// initialize
			occurrencesArrayOfHashMap = new ArrayList<HashMap>();
			
			// retrieve the corresponding annotations
			FSIterator<Annotation> currentAnnotationFSIterator = aJCas.getAnnotationIndex(JCasUtils.getJCasType(aJCas,
					annotation)).iterator();
	
			// then for each one 
			while (currentAnnotationFSIterator.hasNext()) {
				Annotation currentAnnotation = (Annotation) currentAnnotationFSIterator.next();

				// look for any occurrences
				String occurrenceString = currentAnnotation.getCoveredText();
				

				//System.out.println("Debug: occurrenceString>"+occurrenceString+"<");

				if (!patternHashMap.containsKey(occurrenceString)) {
					//System.out.println("Debug: !patternHashMap.containsKey(occurrenceString)><");

					//+regexpDelimiter+occurrenceString+regexpDelimiter;
					String occurrenceStringExtended = "\\Q" +occurrenceString + "\\E"; //"((\\p{Lu}\\.|\\p{Lu}(\\p{Ll}|-|')+)\\s+)?"+occurrenceString;
					Pattern currentPattern = Pattern.compile(occurrenceStringExtended);

					Matcher matcher = currentPattern.matcher(docText);
					// found one - create annotation
					if (matcher.find()) {
						//System.out.println("Debug: found one - create annotation>"+occurrenceString+"<");

						//UIMAUtilities.createAnnotation(aJCas, annotation, matcher.start(), matcher.end());
						HashMap<String, String> occurrenceHashMap= new HashMap<String, String>();
						occurrenceHashMap.put("type", annotation);
						occurrenceHashMap.put("begin", String.valueOf(matcher.start()));
						occurrenceHashMap.put("end", String.valueOf(matcher.end()));
						//occurrenceHashMap.put("value", String.valueOf(matcher.end()));

						occurrencesArrayOfHashMap.add(occurrenceHashMap);
						//getContext().getLogger().log(Level.FINEST, "Found: " + annotation);
					}
				}

				// keep in mind that this occurrence has already been searched 
				patternHashMap.put(occurrenceString, 1);

			}
			
			//  Here we create the annotation in order to avoid concurrent access
			for (HashMap<String, String> occurrenceHashMap : occurrencesArrayOfHashMap) {
				//System.out.println("Debug: Here we create occurrenceHashMap.get(type)>"+occurrenceHashMap.get("type")+"< occurrenceHashMap.get(begin)>"+occurrenceHashMap.get("begin"));

				AnnotationUtils.createAnnotation(aJCas, occurrenceHashMap.get("type"), Integer.valueOf(occurrenceHashMap.get("begin")),Integer.valueOf(occurrenceHashMap.get("end")));
			}
		

		}

	}


}