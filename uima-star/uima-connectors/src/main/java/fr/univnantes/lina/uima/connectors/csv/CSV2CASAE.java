package fr.univnantes.lina.uima.connectors.csv;
/** 
 * UIMA Connectors
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


import java.io.IOException;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Set;

import org.apache.uima.UimaContext;
import org.apache.uima.analysis_component.AnalysisComponent;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.cas.FSIterator;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.text.AnnotationIndex;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.tcas.Annotation;
import org.apache.uima.resource.ResourceInitializationException;

import fr.univnantes.lina.uima.common.ae.CommonAE;
import fr.univnantes.lina.uima.common.cas.AnnotationUtils;
import fr.univnantes.lina.uima.common.cas.JCasUtils;

import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVStrategy;

/**
 * Process the jcas as a csv file : 2 use cases
 * 1. the outputview is specified, the annotation to create is in fact assumed to exist and will be updated in the specified view
 * 2. assumes that a specified column contains the text to annotate and the other columns its potential features.  Create an annotation over the given column and instantiates features with a selection of the others
 * if both outputview and column parameters are set, outputview use case is applied.
 * Both can set columns as begin and end features... 
 * <p>
 * </p>
 * @author Nicolas Hernandez
 */
public class CSV2CASAE extends CommonAE {

	/*
	 * LOCAL PARAMETER NAMES 
	 */

	/**
	 * Parameter name for the name of the annotation type to create for each line of the csv files
	 */
	public static final String PARAM_LINE_TO_ANNOTATION = "AnnotationTypeToCreate";

	/**
	 * Parameter name of the column containing the value to annotate
	 */
	public static final String PARAM_COLUMN_TO_ANNOTATE = "ColumnToAnnotate";

	/**
	 * Parameter name for the csv column id and the feature names to 
	 * associate with from the OutputAnnotation
	 * 
	 */
	public static final String PARAM_FEATURES_TO_COLUMNS = "ColumnsForTheFeatures";

	/**
	 * Parameter name of the CSV separator 
	 * by default a tabulation, '\t', but can be set to comma ',' ... 
	 */
	public static final String PARAM_CSV_SEPARATOR = "CSVSeparator";

	/**
	 * Parameter name to the optional value encapsulator
	 */
	public static final String PARAM_VALUE_ENCAPSULATOR = "CSVValueEncapsulatorCharacter";

	/** 
	 * Param name of the comments  
	 * 
	 */
	public static final String PARAM_COMMENT_STARTING_CHARACTER = "CSVCommentStartingCharacter";

	/** 
	 * Param name of the skip the first Line  
	 * 
	 */
	public static final String PARAM_SKIP_FIRST_LINE = "SkipTheFirstLine";

	/** 
	 * Param name of the ForceToUpdateIfAnnotationToCreateAlreadyExists field
	 * 
	 */
	public static final String PARAM_FORCE_TO_UPDATE = "ForceToUpdateIfAnnotationToCreateAlreadyExists";

	/*
	 * DEFAULT VALUES
	 */

	/**
	 * Default separator between columnId and the feature name associated
	 */
	private static String DEFAULT_COLUMNID_AND_FEATURENAME_SEPARATOR = "->";


	/**
	 * Default skip the fist line
	 */
	private static Boolean DEFAULT_SKIP_THE_FIST_LINE = false;


	/**
	 * Default csv separator
	 */
	private static char DEFAULT_CSV_SEPARATOR = '\t';

	/**
	 * Default csv separator
	 */
	private static char DEFAULT_VALUE_ENCAPSULATOR = '"';

	/**
	 * Default comment starting character
	 */
	private static char DEFAULT_COMMENT = '#';


	/*
	 * LOCAL VARIABLES NAMES 
	 */

	/**
	 * Name of the annotation type to create for each line of the csv files
	 */
	public String annotationToCreateString = null;

	/**
	 * list couples of csv column id and feature names to associate with 
	 * the syntax is 
	 * 0 -> begin
	 * In that case the values of the first column should be set to the begin feature of the OutputAnnotation
	 */
	public HashMap<Integer,String> colIdFeatNameHashMap = null; //String[] featuresFromColumnsStringArray = null;

	/**
	 * Column rank to annotate
	 */
	private int columnRankToAnnotate ;

	private char separatorCharacter;
	private char valueEncapsulatorCharacter;
	private char commentStartingCharacter;

	String colToAnnString = "";
	private boolean beginOffsetPresentAsColumn = false;
	private boolean endOffsetPresentAsColumn = false;

	private boolean skipTheFirstLine = false;

	private boolean forceToUpdateIfAnnotationToCreateAlreadyExists = false;

	private boolean debug = false;


	/**
	 * Get the values of the configuration parameters
	 * 
	 * @see AnalysisComponent#initialize(UimaContext)
	 */
	public void initialize(UimaContext aContext)
			throws ResourceInitializationException {

		// generic parameters
		super.initialize(aContext);

		// Get the input csv separator
		String csvSeparatorString = (String) aContext.getConfigParameterValue(PARAM_CSV_SEPARATOR);
		if (csvSeparatorString == null) {
			separatorCharacter = DEFAULT_CSV_SEPARATOR;
		}
		else {
			separatorCharacter = csvSeparatorString.charAt(0);
		}

		// Get the comment character
		String commentCharacterString = (String) aContext.getConfigParameterValue(PARAM_COMMENT_STARTING_CHARACTER);
		if (commentCharacterString == null) {
			commentStartingCharacter = DEFAULT_COMMENT;
		}
		else {
			commentStartingCharacter = commentCharacterString.charAt(0);
		}

		// Get the value encapsulator
		String valueEncapsulatorString = (String) aContext.getConfigParameterValue(PARAM_VALUE_ENCAPSULATOR);
		if (csvSeparatorString == null) {
			valueEncapsulatorCharacter = DEFAULT_VALUE_ENCAPSULATOR;
		}
		else {
			valueEncapsulatorCharacter = csvSeparatorString.charAt(0);
		}

		// Get the annotation name 
		annotationToCreateString = (String) aContext.getConfigParameterValue(PARAM_LINE_TO_ANNOTATION);	
		//annotationFromLineString = (String) aContext.getConfigParameterValue(PARAM_NAME_OUTPUT_ANNOTATION);		
		if (debug)System.out.println("Warning: annotationFromLineString "+ annotationToCreateString );  
		if (annotationToCreateString == null) {
			String errmsg = "The parameter "+ PARAM_LINE_TO_ANNOTATION+" cannot be empty, please enter a valid Annotation name";
			throw new ResourceInitializationException(errmsg, new Object[] {});
		}


		// Get the columnRankToAnnotate
		colToAnnString = (String)aContext.getConfigParameterValue(PARAM_COLUMN_TO_ANNOTATE);

		//System.out.println("Debug: CSV2CAS outputViewString "+outputViewString);
		//outputViewString="CAS2CSVView";
		//System.out.println("Debug: CSV2CAS outputViewString "+outputViewString);

		// check if at least one rename request is defined
		/*if (colToAnnString == null && outputViewString == null) {
			String errmsg = "Set the parameter "+ PARAM_COLUMN_TO_ANNOTATE+" or the parameter "+ 
					PARAM_NAME_OUTPUT_VIEW + ". If both parameters are set the priority is given for the "+ 
					PARAM_NAME_OUTPUT_VIEW + ". For the "+ PARAM_COLUMN_TO_ANNOTATE+ 
					", please enter a number value corresponding to a possible column rank of the CSV files";
			throw new ResourceInitializationException(errmsg, new Object[] {});
		}
		*/
		if (colToAnnString != null) columnRankToAnnotate = Integer.parseInt(colToAnnString);

		// to force CommonAE to get the outputViewJCas in its process method
		if ( outputView != null) outputType = OUTPUTTYPE_ANNOTATION;

		// In order to force the use of an existing view which contains annotations to update
		// we have to set the CommonAE outputAnnotationString variable with annotationToCreateString
		// in practice do not check if a feature is set
		outputAnnotation = annotationToCreateString;

		// Get the features name
		String[] featuresFromColumnsStringArray =  (String[]) aContext.getConfigParameterValue(PARAM_FEATURES_TO_COLUMNS);
		colIdFeatNameHashMap = new HashMap<Integer, String>();
		for (int i = 0;  i < featuresFromColumnsStringArray.length ; i++) {
			String[] ColIdFeatNameCouple = featuresFromColumnsStringArray[i].split(DEFAULT_COLUMNID_AND_FEATURENAME_SEPARATOR);
			if (ColIdFeatNameCouple.length == 2) {
				colIdFeatNameHashMap.put(Integer.parseInt(ColIdFeatNameCouple[0].trim()), ColIdFeatNameCouple[1].trim());
				// Check the presence of the begin, end columns
				if (ColIdFeatNameCouple[1].trim().equalsIgnoreCase("begin")) beginOffsetPresentAsColumn = true;
				if (ColIdFeatNameCouple[1].trim().equalsIgnoreCase("end")) endOffsetPresentAsColumn = true;

			}
			else {
				if (debug)  System.out.println("Warning: Wrong syntax to the "+PARAM_FEATURES_TO_COLUMNS+" paramater with the following line "+ ColIdFeatNameCouple.toString() );  
				String errmsg = "Wrong syntax to the "+PARAM_FEATURES_TO_COLUMNS+" paramater with the following line "+ ColIdFeatNameCouple.toString();
				throw new ResourceInitializationException(errmsg, new Object[] {});
			}
		}
		//		for (int i = 0;  i < featuresToColumnsStringArray.length ; i++) {
		//			System.out.println("Debug: fc"+ featuresToColumnsStringArray[i] + " i"+ i);  	
		//		}	

		//
		if ((beginOffsetPresentAsColumn && !endOffsetPresentAsColumn) || (!beginOffsetPresentAsColumn && endOffsetPresentAsColumn)) {	 
			String errmsg = "The parameter "+ PARAM_FEATURES_TO_COLUMNS+ " should count both the begin and end features or none";
			throw new ResourceInitializationException(errmsg, new Object[] {});
		} 


		// Get the skipTheFirstLine
		skipTheFirstLine = (Boolean) aContext.getConfigParameterValue(PARAM_SKIP_FIRST_LINE);
		//if (skipTheFirstLine != false && skipTheFirstLine != true ) skipTheFirstLine = DEFAULT_SKIP_THE_FIST_LINE;

		// Get the forceToUpdateIfAnnotationToCreateAlreadyExists
		forceToUpdateIfAnnotationToCreateAlreadyExists = (Boolean) aContext.getConfigParameterValue(PARAM_FORCE_TO_UPDATE);
	}


	/**
	 * Process each CAS as having a CSV structure
	 *
	 */
	//	@Override
	protected String processInputView(JCas inputViewJCas,
			FSIterator<Annotation> contextAnnotationsFSIter,
			Set<String> inputAnnotationSet,
			String inputFeatureString, JCas outputViewJCas,
			String outputAnnotationString, String ouputFeatureString)
					throws AnalysisEngineProcessException {

		//System.out.println("Debug: CSV2CAS outputViewJCas.getViewName() " + outputViewJCas.getViewName());


		// parse each line
		String[][] csvData = null;
		try {
			csvData = (new CSVParser(new StringReader(JCasUtils.getSofaDataString(inputViewJCas)), 
					new CSVStrategy(separatorCharacter,valueEncapsulatorCharacter,commentStartingCharacter))).getAllValues();
		} catch (IOException e) {
			String errmsg = "Problem to CSV parse the JCAS. Check the sofaDataString of the jcas view "+ inputViewJCas.getViewName() +" ; " +
					"and the strategy parameters such as the separatorCharacter >"+separatorCharacter+"<, " +
					"the  valueEncapsulatorCharacter >" +valueEncapsulatorCharacter+
					"< and the commentStartingCharacter >"+commentStartingCharacter+"<" ;
			throw new AnalysisEngineProcessException(errmsg, new Object[] {});
		}

		int begin = 0;
		int end = begin;

		int firstLineRank = 0;
		if (skipTheFirstLine) {
			firstLineRank = 1;
			int beginInLine = 0;
			for(int j = 0; j < csvData[0].length; j++) {
				begin +=  csvData[0][j].length() +1;
			}

			//System.out.println("Debug: begin>"+begin+"< end>"+end+"<"); 

		}

		// in order to parse the index of annotations to update in parallel of the csv data lines
		Iterator<Annotation> outputAnnotationIter = null;
		if (outputViewJCas != null) {
			//System.out.println("Debug: CSV2CAS processInputView outputViewJCas != null " + outputViewJCas.getViewName());
			//System.out.println("Debug: CSV2CAS processInputView outputAnnotationString " + outputAnnotationString);

			// Récupère le type de la DEFAULT_CONTEXT_ANNOTATION
			AnnotationIndex<Annotation> outputAnnotationIndex = null;
			Type outputAnnotationType = null;
			outputAnnotationType = JCasUtils.getJCasType(outputViewJCas,
					outputAnnotationString);



			outputAnnotationIndex = (AnnotationIndex<Annotation>) outputViewJCas
					.getAnnotationIndex(outputAnnotationType);
			outputAnnotationIter = outputAnnotationIndex
					.iterator();
			// Pour l'absolute context
			// il y a un seul élément à savoir DocumentAnnotation
			/*while (outputAnnotationIter.hasNext()) {
				Annotation annotationToUpdate = null;
				annotationToUpdate = (Annotation) outputAnnotationIter
				.next();
							System.out.println("Debug: CSV2CAS processInputView annotationToUpdate " + annotationToUpdate.getCoveredText());
			}*/
			// Context Annotation suivante de même type
		}

		// for each line
		for(int i = firstLineRank; i < csvData.length; i++) { 

			// future features to associate with the annotation to create 
			HashMap<String, Object> featuresHashMap = new HashMap<String, Object>();

			// Process the features to associate
			// For each considered column in the parameter file
			Set<Integer> colIdFeatNameKeySet = colIdFeatNameHashMap.keySet();
			Iterator colIdFeatNameKeySetIter = colIdFeatNameKeySet.iterator();
			while (colIdFeatNameKeySetIter.hasNext()){
				int  colId = (Integer) colIdFeatNameKeySetIter.next();
				if (colId <= csvData[i].length) {
					featuresHashMap.put(colIdFeatNameHashMap.get(colId), csvData[i][colId]);
				}
				else {
					if (debug) System.out.println("Warning: The column id "+colId+" of the "+PARAM_FEATURES_TO_COLUMNS+" paramater is out of range of the csvFile");  
					//String errorMsg =  "Warning: Wrong syntax to the "+PARAM_FEATURES_TO_COLUMNS+" paramater with the following line "+ ColIdFeatNameCouple.toString();
					//throw new AnnotatorInitializationException(errorMsg,new Object[]{errorMsg}); 

				}
			}
			if (debug) System.out.println("Debug: annotationFromLineString"+annotationToCreateString);  
			if (debug) System.out.println("Debug: featuresHashMap"+featuresHashMap); 



			if (forceToUpdateIfAnnotationToCreateAlreadyExists) {
				//System.out.println("Debug: CSV2CAS processInputView csvData " + csvData[i][0]);
				// get 
				Annotation annotationToUpdate = null;
				try {
					annotationToUpdate = (Annotation) outputAnnotationIter
							.next();

				} catch (NoSuchElementException e) {
					// TODO: handle exception
					System.err.println("ERROR: you request the updating of some annotations which seem not be available in the specified inputview. Check it.");
				}

				AnnotationUtils.updateAnnotation(outputViewJCas, annotationToUpdate, annotationToCreateString, featuresHashMap);
				//
			}
			else {

				if (colToAnnString != null) {
					if (!(beginOffsetPresentAsColumn && endOffsetPresentAsColumn)) {
						int beginInLine = begin;
						for(int j = 0; j < columnRankToAnnotate; j++) {
							beginInLine += csvData[i][j].length() +1;
						}
						featuresHashMap.put("begin", String.valueOf(beginInLine));
						featuresHashMap.put("end", String.valueOf(beginInLine+csvData[i][columnRankToAnnotate].length()));
					}
				} else {
				}
				//				AnnotationUtils.createAnnotation( inputViewJCas,  annotationToCreateString, featuresHashMap);

				AnnotationUtils.createAnnotation( outputViewJCas,  annotationToCreateString, featuresHashMap);
			}
			//
			int beginInLine = 0;
			for(int j = 0; j < csvData[i].length; j++) {
				beginInLine +=  csvData[i][j].length() +1;
			}
			begin += beginInLine ; 

		}



		// La méthode requiert de retourner quelque chose 
		// Le plus simple est de lui retourner l'image d'elle même
		return inputViewJCas.getSofaDataString();

	}



}