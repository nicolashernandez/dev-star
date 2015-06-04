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

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import org.apache.uima.UimaContext;
import org.apache.uima.analysis_component.AnalysisComponent;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.cas.FSIterator;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.tcas.Annotation;
import org.apache.uima.resource.ResourceInitializationException;

import fr.univnantes.lina.javautil.IOUtilities;
import fr.univnantes.lina.uima.common.ae.CommonAE;
import fr.univnantes.lina.uima.common.cas.AnnotationUtils;
import fr.univnantes.lina.uima.common.cas.DocumentAnnotationUtils;
import fr.univnantes.lina.uima.common.cas.JCasUtils;



/**
 * Deserialize selected features of annotations of a given type 
 * from csv files.
 * 
 *  
 * <p>
 * </p>
 * 
 * 
 * 
 * @author Nicolas Hernandez
 */
public class ExternalCSV2CASAE extends CommonAE {

	/*
	 * PARAMETERS NAMES
	 */

	/**
	 * Parameter name for the path of the input directory to read the
	 * csv files.
	 */
	public static final String PARAM_INPUT_DIR_FOR_CSV = "InputDir";

	/**
	 * Parameter name for the name of the annotation type to create for each line of the csv files
	 */
	public static final String LINE_TO_ANNOTATION_PARAM = "LineToAnnotation";

	/**
	 * Parameter name for the csv column id and the feature names to 
	 * associate with from the OutputAnnotation
	 */
	public static final String PARAM_FEATURES_TO_COLUMNS = "FeaturesFromColumns";

	/**
	 * Parameter name of the CSV separator 
	 * by default a tabulation, '\t', but can be set to comma ',' ... 
	 */
	public static final String CSV_SEPARATOR_PARAM = "CSVSeparator";


	/*
	 * DEFAULT VALUES
	 */
	/**
	 * Name of the default csv extension
	 */
	private static String DEFAULT_CSV_EXTENSION = ".csv";

	/**
	 * Name of the default separator between columnId and the feature name associated
	 */
	private static String DEFAULT_COLUMNID_AND_FEATURENAME_SEPARATOR = "->";

	/**
	 * Name of the default csv separator
	 */
	private static String DEFAULT_CSV_SEPARATOR = "\t";



	/*
	 * LOCAL VARIABLES  
	 */

	/**
	 * Name of the path to the input directory of the 
	 * csv files (from the context Data Path).
	 */
	public String inputCSVDirString;

	/**
	 * Array of each input csv files 
	 */
	//public ArrayList<File> inputCSVFileArrayList;

	/**
	 * HashMap of names of each input csv files without the csv extension
	 */
	public HashMap<String,Integer> inputCSVFileNameHashMap;

	/**
	 * Name of the annotation type to create for each line of the csv files
	 */
	public String annotationToProcessString = null;

	/**
	 * list couples of csv column id and feature names to associate with from the OutputAnnotation 
	 * the syntax is 
	 * 0 -> begin
	 * if the values of the first column should be set to the begin feature of the OutputAnnotation
	 */
	public HashMap<Integer,String> colIdFeatNameHashMap = null; //String[] featuresFromColumnsStringArray = null;

	//
	private Boolean debug = false;

	private String csvSeparatorString = "";

	/**
	 * Get the values of the configuration parameters
	 * 
	 * @see AnalysisComponent#initialize(UimaContext)
	 */
	/* (non-Javadoc)
	 * @see fr.univnantes.lina.uima.util.AnalysisEngine#initialize(org.apache.uima.UimaContext)
	 */
	public void initialize(UimaContext aContext)
	throws ResourceInitializationException {

		// SUPER PARAMATERS
		super.initialize(aContext);


		// CURRENT PARAMETERS
		// Get the input csv dir name
		inputCSVDirString  = (String) aContext.getConfigParameterValue(PARAM_INPUT_DIR_FOR_CSV);

		// Get the files from the dir
		ArrayList<File> inputCSVFileArrayList = null;
		try {
			inputCSVFileArrayList  = IOUtilities.retrieveFilesFromDirectory(inputCSVDirString);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (inputCSVFileArrayList.size() == 0) {
			System.err.println("Warning: the input csv dir "+inputCSVDirString +" does not contain any csv files");
		}

		// Get the input csv separator
		csvSeparatorString  = (String) aContext.getConfigParameterValue(CSV_SEPARATOR_PARAM);
		if (csvSeparatorString == null) {
			csvSeparatorString = DEFAULT_CSV_SEPARATOR;
		}

		//mEncoding = (String)getConfigParameterValue(PARAM_ENCODING);
		//mDocumentTextXmlTagName = (String)getConfigParameterValue(PARAM_XMLTAG);
		//mLanguage = (String)getConfigParameterValue(PARAM_LANGUAGE);
		//mCurrentIndex = 0; 

		// Get the names of the files present in the input csv dir (without the csv extension)  
		inputCSVFileNameHashMap = new HashMap<String, Integer>();
		for (int i = 0; i < inputCSVFileArrayList.size(); i++) {
			String csvFileName = inputCSVFileArrayList.get(i).getName().substring(0, inputCSVFileArrayList.get(i).getName().length() - DEFAULT_CSV_EXTENSION.length() );
			inputCSVFileNameHashMap.put(csvFileName, 1);
			if (debug) System.out.println("Debug: csvFileName present in the csv dir "+inputCSVDirString+" : " + csvFileName);  	
		}


		//load each csv file for debugging 
//		if (debug) 
//			for (int i = 0; i < inputCSVFileArrayList.size(); i++) {
//				ArrayList<String> csvLines = JavaUtilities.readFromFileToLineArray(inputCSVFileArrayList.get(i));
//				Iterator<String> csvLinesIterator = csvLines.iterator();
//				while (csvLinesIterator.hasNext()) {
//					String csvLine = csvLinesIterator.next();
//					System.out.println("Debug: csvLine "+ csvLine);  	
//				}
//			}

		// Get the annotation name 
		annotationToProcessString = (String) aContext.getConfigParameterValue(LINE_TO_ANNOTATION_PARAM);	
		//annotationFromLineString = (String) aContext.getConfigParameterValue(PARAM_NAME_OUTPUT_ANNOTATION);		
		if (debug) System.out.println("Warning: annotation to create/update : "+ annotationToProcessString );  


		// Get the features name
		String[] featuresFromColumnsStringArray =  (String[]) aContext.getConfigParameterValue(PARAM_FEATURES_TO_COLUMNS);
		colIdFeatNameHashMap = new HashMap<Integer, String>();
		for (int i = 0;  i < featuresFromColumnsStringArray.length ; i++) {
			String[] ColIdFeatNameCouple = featuresFromColumnsStringArray[i].split(DEFAULT_COLUMNID_AND_FEATURENAME_SEPARATOR);
			if (ColIdFeatNameCouple.length == 2) colIdFeatNameHashMap.put(Integer.parseInt(ColIdFeatNameCouple[0].trim()), ColIdFeatNameCouple[1].trim());
			else {
				System.err.println("Warning: Wrong syntax to the "+PARAM_FEATURES_TO_COLUMNS+" paramater with the following line "+ ColIdFeatNameCouple.toString() );  
				//String errorMsg =  "Warning: Wrong syntax to the "+PARAM_FEATURES_TO_COLUMNS+" paramater with the following line "+ ColIdFeatNameCouple.toString();
				//throw new AnnotatorInitializationException(errorMsg,new Object[]{errorMsg}); 
			}
		}
		//		for (int i = 0;  i < featuresToColumnsStringArray.length ; i++) {
		//			System.out.println("Debug: fc"+ featuresToColumnsStringArray[i] + " i"+ i);  	
		//		}	

	}


	/**
	 * Process each CAS and import from a CSV file a specific annotation type as 
	 * lines and selected features as column
	 *
	 */
	//	@Override
	protected String processInputView(JCas inputViewJCas,
			FSIterator<Annotation> contextAnnotationsFSIter,
			Set<String> inputAnnotationSet,
			String inputFeatureString, JCas outputViewJCas,
			String outputAnnotationString, String ouputFeatureString)
	throws AnalysisEngineProcessException {

		// retrieve the filename of the input file from the CAS
		// and set the output name of the csv file
		String inFileName = DocumentAnnotationUtils.retrieveSourceDocumentFileName(inputViewJCas);
		if (inFileName != null) {

			// If it exists a csv file name (minus its csv extension) corresponding to the current jcas document name then process 
			if (inputCSVFileNameHashMap.containsKey(inFileName)) {
				//				System.out.println("Debug: inputCSVFileNameHashMap "+ inFileName);  	
				String fullInFileName = inputCSVDirString + "/" + inFileName +  DEFAULT_CSV_EXTENSION;
				//System.out.println("Debug: full csv file name "+ fullInFileName);  	

				// Test if the annotation to process is already present in the CAS
				// if yes, then the process must be an update
				// if no, then the process must be a creation
				FSIterator annotationToProcessIterator = inputViewJCas.getAnnotationIndex(JCasUtils.getJCasType(inputViewJCas, annotationToProcessString)).iterator();
				boolean updateAnnotation = false;  
				if (annotationToProcessIterator.hasNext()) {
					updateAnnotation = true;
				}

				ArrayList<String> csvLines = IOUtilities.readFromFileNameToLineArray(fullInFileName);
				Iterator<String> csvLinesIterator = csvLines.iterator();

				// Get the features to set 
				// And create/update the annotation
				// For each csv lines
				while (csvLinesIterator.hasNext()) {

					String csvLine = csvLinesIterator.next();
					if (debug) System.out.println("Debug: current csvLine "+ csvLine);  

					String[] csvColumns = csvLine.split(csvSeparatorString);
					HashMap<String, Object> featuresHashMap = new HashMap<String, Object>();

					// For each considered column in the descriptor file
					// Get the features to set 
					Set<Integer> colIdFeatNameKeySet = colIdFeatNameHashMap.keySet();
					Iterator colIdFeatNameKeySetIter = colIdFeatNameKeySet.iterator();
					while (colIdFeatNameKeySetIter.hasNext()){
						int  colId = (Integer) colIdFeatNameKeySetIter.next();
						if (colId <= csvColumns.length) {
							featuresHashMap.put(colIdFeatNameHashMap.get(colId), csvColumns[colId]);
						}
						else {
							if (debug) System.out.println("Warning: The column id "+colId+" of the "+PARAM_FEATURES_TO_COLUMNS+" paramater is out of range of the csvFile");  
							//String errorMsg =  "Warning: Wrong syntax to the "+PARAM_FEATURES_TO_COLUMNS+" paramater with the following line "+ ColIdFeatNameCouple.toString();
							//throw new AnnotatorInitializationException(errorMsg,new Object[]{errorMsg}); 

						}
					}
					if (debug) System.out.println("Debug: annotationFromLineString"+annotationToProcessString);  
					if (debug) System.out.println("Debug: featuresHashMap"+featuresHashMap);  


					/*if (updateAnnotation) {
						Object annotationToProcessObject = null;
						if (annotationToProcessIterator.hasNext()) {
							
							Class<Annotation> annotationClass = null;
							try {
								annotationClass = (Class<Annotation>) Class
								.forName(annotationToProcessString);
							} catch (ClassNotFoundException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							
							annotationToProcessObject = annotationToProcessIterator.next();
							annotationClass.cast(annotationToProcessObject);
							
							AnnotationUtils.updateAnnotation( inputViewJCas,  annotationToProcessObject, featuresHashMap);
						}

					}
					*/
					
					if (updateAnnotation) {
						if (annotationToProcessIterator.hasNext()) {
							AnnotationUtils.updateAnnotation( inputViewJCas,  annotationToProcessIterator.next(), annotationToProcessString, featuresHashMap);
						}
					}
					else 
						AnnotationUtils.createAnnotation( inputViewJCas,  annotationToProcessString, featuresHashMap);
				}
			}
			else {
				System.err.println("Warning: No CSV file with the extension "+DEFAULT_CSV_EXTENSION+" is present in the directory "+inputCSVDirString + " for the file " + inFileName);
			}

		}

		// La méthode requiert de retourner quelque chose 
		// Le plus simple est de lui retourner l'image d'elle même
		return inputViewJCas.getSofaDataString();

	}



}