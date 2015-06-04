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

import org.apache.uima.examples.*;

import java.lang.reflect.Field;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Set;

import org.apache.uima.UimaContext;
import org.apache.uima.analysis_component.AnalysisComponent;
import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.analysis_engine.ResultSpecification;
import org.apache.uima.cas.FSIterator;
import org.apache.uima.cas.Type;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.tcas.Annotation;
import org.apache.uima.resource.ResourceInitializationException;

import fr.univnantes.lina.javautil.IOUtilities;
import fr.univnantes.lina.uima.common.ae.CommonAE;
import fr.univnantes.lina.uima.common.cas.AnnotationUtils;
import fr.univnantes.lina.uima.common.cas.FeatureUtils;
import fr.univnantes.lina.uima.common.cas.JCasUtils;
import fr.univnantes.lina.uima.common.cas.UIMAUtils;


/**
 * Serialize selected features of annotations of a given type 
 * to a csv file.
 * 
 * Use the sourceDocumentInformation annotation to get the name of 
 * the source document in order to create an ouput name file with the same name 
 * (use a default name otherwise)
 *  
 * <p>
 * </p>
 * 
 * 
 * 
 * @author Nicolas Hernandez
 */
public class CSVWriterAE extends CommonAE {

	/*
	 * LOCAL PARAMETER NAMES 
	 */

	/**
	 * Parameter name for the path of the output directory to write the
	 * csv files.
	 */
	public static final String PARAM_OUTPUT_DIR_FOR_CSV = "OuputDir";

	/**
	 * Parameter name for the Name of the annotation type whose instances will be turned into lines
	 */
	public static final String PARAM_ANNOTATION_TO_LINE = "AnnotationToLine";

	/**
	 * Parameter name for Ordered list of feature names of the AnnotationToLine
	 * instances whose values should be turned into columns values.
	
	 */
	public static final String PARAM_FEATURES_TO_COLUMNS = "FeaturesToColumns";
	
	/**
	 * Parameter name of the CSV separator 
	 * by default a tabulation, '\t', but can be set to comma ',' ... 
	 */
	public static final String CSV_SEPARATOR_PARAM = "CSVSeparator";

	
	/**
	 * Parameter name of the default value to set when a specified feature has a null value 
	 * If not set, then the value will be 'null' 
	 */
	public static final String DEFAULT_NULL_VALUE_PARAM = "DefaultNullValue";

	/**
	 *  View name to consider as the view to process
	 */
	//private static String PARAM_NAME_INPUT_VIEW = "InputView";


	/*
	 * CONFIGURATION PARAMETER NAMES VALUES
	 */

	/**
	 * Path of the output directory to write the
	 * csv files from the context Data Path.
	 */
	public File outputDirForCSV;

	/**
	 * Name of the path of the output directory to write the
	 * csv files from the context Data Path.
	 */
	public String outputDirForCSVString;

	/**
	 * Name of the annotation type whose instances will be turned into lines
	 */
	public String annotationToLineString = null;

	/**
	 * List of feature names of the AnnotationToLine
	 * instances whose values should be turned into columns values.
	 * Type of the feature should be specified first, then the feature name 
	 * with the ':' as separator. Currently the types int or Integer, String and
	 * Float are supported.
	 * e.g. int:begin, String:coveredText
	 */
	public String[] featuresToColumnsStringArray = null;

	/**
	 * View to process
	 */
	//public String inputViewString = null;


	/*
	 * LOCAL VARIABLES
	 */

	/*
	 *  Default view name if none are specified by the view parameter
	 */
	//private static String DEFAULT_INPUT_VIEW = "_InitialView";

	/**
	 * Name of the default SourceDocumentInformation
	 */
	private static String DEFAULT_SOURCE_DOCUMENT_INFORMATION_ANNOTATION = "org.apache.uima.examples.SourceDocumentInformation";


	/**
	 * Name of the default document in case of absence of source document information annotation in the CAS
	 */
	private static String DEFAULT_DOCUMENT_PREFIX = "cas2csv.";

	/**
	 * Name of the default csv extension
	 */
	private static String DEFAULT_CSV_EXTENSION = ".csv";

	/**
	 * Name of the default separator between type and feature name
	 */
	private static String DEFAULT_TYPE_AND_FEATURENAME_SEPARATOR = ":";

	/**
	 * Name of the default csv separator
	 */
	private static String DEFAULT_CSV_SEPARATOR = "\t"; // \t

	private String csvSeparatorString = "";

	private String defaultNullValueString = "";
	
	/**
	 * Get the values of the configuration parameters
	 * 
	 * @see AnalysisComponent#initialize(UimaContext)
	 */
	public void initialize(UimaContext aContext)
	throws ResourceInitializationException {
		// generic AE parameters
		super.initialize(aContext);

		// current AE parameter

		//System.out.println("Debug: aContext.getDataPath() "+aContext.getDataPath());
		//System.out.println("Debug: aContext.getDataPath()"+aContext.getResourceFilePath(arg0);

		// Get the input view
		// If no input view is specified, we use the default (i.e. _InitialView)
		// taken in charge by uima-common
		//inputViewString = (String) aContext.getConfigParameterValue(PARAM_NAME_INPUT_VIEW);
		//if (inputViewString == null) {
		//	inputViewString = DEFAULT_INPUT_VIEW;
		//}

		// Get the output dir name
		outputDirForCSVString = (String) aContext.getConfigParameterValue(PARAM_OUTPUT_DIR_FOR_CSV);
		outputDirForCSV = new File(outputDirForCSVString);
		if (!outputDirForCSV.exists()) {
			outputDirForCSV.mkdirs();
		}

		// Get the annotation name 
		annotationToLineString = (String) aContext.getConfigParameterValue(PARAM_ANNOTATION_TO_LINE);		

		// Get the features name
		featuresToColumnsStringArray =  (String[]) aContext.getConfigParameterValue(PARAM_FEATURES_TO_COLUMNS);
		//		for (int i = 0;  i < featuresToColumnsStringArray.length ; i++) {
		//			System.out.println("Debug: fc"+ featuresToColumnsStringArray[i] + " i"+ i);  	
		//		}	

		// Get the input csv separator
		csvSeparatorString  = (String) aContext.getConfigParameterValue(CSV_SEPARATOR_PARAM);
		//System.out.println("Debug: before null test csvSeparatorString>"+csvSeparatorString+"<");
		if (csvSeparatorString == null) {
			csvSeparatorString = DEFAULT_CSV_SEPARATOR;
		}
		//else csvSeparatorString = DEFAULT_CSV_SEPARATOR.replaceAll(DEFAULT_CSV_SEPARATOR, csvSeparatorString);
	//	else csvSeparatorString = DEFAULT_CSV_SEPARATOR.replaceAll(DEFAULT_CSV_SEPARATOR, "\t");
		else 		csvSeparatorString = csvSeparatorString.substring(1, csvSeparatorString.length()-1);
		//csvSeparatorString = csvSeparatorString ;	
		//System.out.println("Debug: after null test csvSeparatorString>"+csvSeparatorString+"<");
			//"\\s" // whitespace character
			//"\t" // tabulation character
		
		// Get the default null value to set in case of null value with the feature
		defaultNullValueString  = (String) aContext.getConfigParameterValue(DEFAULT_NULL_VALUE_PARAM);
		
		
	}


	/**
	 * process each CAS and export to a CSV file a specific annotation type as 
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

		//public void process(JCas aJCas) throws AnalysisEngineProcessException {

		// Get the view  to process
		//JCas inputViewJCas = JCasSofaViewUtils.getView(aJCas,inputViewString);

		// Retrieve the filename of the input file from the CAS
		// and set the output name of the csv file
		FSIterator<Annotation> sourceDocumentInformationFSIterator = inputViewJCas.getAnnotationIndex(JCasUtils.getJCasType(inputViewJCas,
				DEFAULT_SOURCE_DOCUMENT_INFORMATION_ANNOTATION)).iterator();
		String outFileName = "";
		File inFile = null;
		if (sourceDocumentInformationFSIterator.hasNext()) {
			SourceDocumentInformation theSourceDocumentInformation = (SourceDocumentInformation) sourceDocumentInformationFSIterator.next();

			try {
				inFile = new File(new URL(theSourceDocumentInformation.getUri()).getPath());
				outFileName = inFile.getName();
				if (theSourceDocumentInformation.getOffsetInSource() > 0) {
					outFileName += ("_" + theSourceDocumentInformation.getOffsetInSource());
				}
				//outFile = new File(outputDirForCSV, outFileName);
				//System.out.println("Debug: outputDirForCSV "+ outputDirForCSVString+ " outFileName "+outFileName);  	

			} catch (MalformedURLException e) {
				// invalid URL, use default processing below
				e.printStackTrace();
			}
		}
		
		// If SourceDocumentInformation is not present in the CAS then create a 
		if (inFile == null) {
			byte[] hash      = null;
			try {
				hash= MessageDigest.getInstance("MD5").digest(inputViewJCas.getSofaDataString().getBytes());
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			outFileName = DEFAULT_DOCUMENT_PREFIX + hash;
			//inFile = new File(outputDirForCSV, DEFAULT_DOCUMENT_PREFIX + hash );   
			//System.out.println("Debug: outputDirForCSV "+ outputDirForCSVString+ " outFileName "+DEFAULT_DOCUMENT_PREFIX + hash + DEFAULT_CSV_EXTENSION);  	
		}
		outFileName += DEFAULT_CSV_EXTENSION;


		FSIterator<Annotation> annotationToLineFSIterator =  inputViewJCas.getAnnotationIndex(JCasUtils.getJCasType(inputViewJCas, annotationToLineString)).iterator();

		//DataOutputStream dos = null;
		String[] lines = new String[inputViewJCas.getAnnotationIndex(JCasUtils.getJCasType(inputViewJCas, annotationToLineString)).size()];
		int l = 0;
		while (annotationToLineFSIterator.hasNext()) {

			// Annotation annotationToLine = null;
			// Récupère et cast l'annotationToLine courante à manipuler
			Object annotationObject = annotationToLineFSIterator.next();
			Class  annotationClass = annotationObject.getClass();
			String className = "null";
			className = annotationClass.getName();
			//System.out.println("Debug: class>"+className+"<");
			Class<Annotation> inputAnnotationClass = AnnotationUtils.getAnnotationClass(className);
			Annotation annotationToLine = (Annotation) annotationObject;
			inputAnnotationClass.cast(annotationToLine);
			//Class<Annotation> inputAnnotationClass = UIMAUtilities.retrieveAndCastAnAnnotation(annotationToLineFSIterator, annotationToLine);

			String columns = "";

			for (String featureToColumn : featuresToColumnsStringArray) {

				String featureToColumnValueString = "";

				// In case of null as feature name then do not consider this column
				//if (!featureToColumn.equalsIgnoreCase("null")) {
				Object featureToColumnValueObject = null;
				featureToColumnValueObject = FeatureUtils.invokeFeatureGetterMethod(annotationToLine,
						FeatureUtils.getFeatureGetterMethod(inputAnnotationClass,featureToColumn));	

				if (featureToColumnValueObject != null) 
					featureToColumnValueString = featureToColumnValueObject.toString();
				else if (!defaultNullValueString.equalsIgnoreCase("")) featureToColumnValueString = defaultNullValueString;
				else featureToColumnValueString = "null";
				// Dans certains cas la valeur peut être null mais pas pour toutes donc on laisse null

				if (columns.equalsIgnoreCase("")) {
					//System.out.println("Debug: featureToColumnValueString >"+featureToColumnValueString +"<");
					columns = ""+ featureToColumnValueString;}
				else {columns += csvSeparatorString +featureToColumnValueString;}
				//}

			}
			lines[l] = "";
			lines[l] += (""+columns + "\n");

			l++;

		}
		// Set the whole path including the path to the directory
		outFileName = outputDirForCSVString+"/"+outFileName;

		// Serialize
		IOUtilities.writeStringArrayToFileName( outFileName,  lines);

		// Here your code

		// La méthode requiert de retourner quelque chose 
		// Le plus simple est de lui retourner l'image d'elle même
		return inputViewJCas.getSofaDataString();
	}


}