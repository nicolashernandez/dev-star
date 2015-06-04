package fr.univnantes.lina.uima.connectors.io;

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
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.Set;

import org.apache.uima.UimaContext;
import org.apache.uima.analysis_component.AnalysisComponent;
import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.analysis_engine.ResultSpecification;
import org.apache.uima.cas.CASException;
import org.apache.uima.cas.CASRuntimeException;
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
 * Serialize the content of the selected views.
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
public class ViewWriterAE extends CommonAE {

	/*
	 * LOCAL PARAMETER NAMES 
	 */


	/**
	 * Parameter name for the path of the output directory to write files.
	 */
	public static final String PARAM_OUTPUT_DIRECTORY = "OutputDirectory";

	public static final String PARAM_PREFIX_OUTPUT_FILE = "OutputFilePrefix";
	public static final String PARAM_SUFFIX_OUTPUT_FILE = "OutputFileSuffix";
	

	/*
	 * CONFIGURATION PARAMETER NAMES VALUES
	 */

	/**
	 * Path of the output directory to write the
	 * files from the context Data Path.
	 */
	public File outputDirectory;

	/**
	 * Name of the path of the output directory to 
	 * write the files from the context Data Path.
	 */
	public String outputDirectorytring;

	/**
	 * List of view names 
	 */
	public String[] viewNamesStringArray = null;

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
	 * Should be the class name
	 */
	//private static String DEFAULT_DOCUMENT_PREFIX = "tmp.";

	/**
	 * Name of the default extension
	 */
	private static String DEFAULT_EXTENSION = ".txt";


	/*
	 * LOCAL VARIABLES
	 */
	private String defaultDocumentPrefix = ""; 
	private String prefixString = "";
	private String suffixString = "";


	//private Calendar cal;

	/**
	 * Get the values of the configuration parameters
	 * 
	 * @see AnalysisComponent#initialize(UimaContext)
	 */
	public void initialize(UimaContext aContext)
			throws ResourceInitializationException {
		// SUPER PARAMETERS
		super.initialize(aContext);

		// CURRENT PARAMETERS
		defaultDocumentPrefix = this.getClass().getName() + ".";

		// Get prefix and suffix for the created file
		prefixString = (String) aContext.getConfigParameterValue(PARAM_PREFIX_OUTPUT_FILE);
		if (prefixString == null) prefixString = "";
		suffixString = (String) aContext.getConfigParameterValue(PARAM_SUFFIX_OUTPUT_FILE);
		if (suffixString == null) suffixString = "";

		
		// Get the output dir name
		outputDirectorytring = (String) aContext.getConfigParameterValue(PARAM_OUTPUT_DIRECTORY);
		outputDirectory = new File(outputDirectorytring);
		if (!outputDirectory.exists()) {
			outputDirectory.mkdirs();
		}

		//cal = new GregorianCalendar(); // donne l'heure a l'instant t 

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

		//String outFileName = JCasSofaViewUtils.getAnArtifactName(inputViewJCas);
		String outFileName = JCasUtils.getAnArtifactName(inputViewJCas,true,prefixString,suffixString);
		
		//String inputViewJCasString = inputViewJCas.getDocumentText();
		//System.out.println("Debug: ViewWriterAE inputViewJCasString " + inputViewJCasString);
		
		//
		//outFileName += "-" +  inputViewJCas.getViewName();

		//
		//outFileName += DEFAULT_EXTENSION;

		// Set the whole path including the path to the directory
		outFileName = outputDirectorytring+"/"+outFileName;


		// Serialize
		IOUtilities.writeStringToFileName( outFileName, JCasUtils.getSofaDataString(inputViewJCas));

		// Here your code

		// La méthode requiert de retourner quelque chose 
		// Le plus simple est de lui retourner l'image d'elle même
		return inputViewJCas.getSofaDataString();
	}


}