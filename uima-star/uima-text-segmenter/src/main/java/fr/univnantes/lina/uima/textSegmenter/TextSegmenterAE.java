package fr.univnantes.lina.uima.textSegmenter;

/** 
 * UIMA Text Segmenter
 * Copyright (C) 2010, 2011  Nicolas Hernandez
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


import org.apache.uima.UimaContext;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.fit.component.JCasAnnotator_ImplBase;
import org.apache.uima.fit.descriptor.ConfigurationParameter;
import org.apache.uima.jcas.JCas;
import org.apache.uima.resource.ResourceInitializationException;

import fr.univnantes.lina.uima.common.ae.CommonAE;
import uk.ac.man.cs.choif.nlp.surface.Stopword;

import java.io.File;


/**
 * Annotator engine which acts as an abstract class for text segmenter AE implementation such as C99 and JTextTile
 * Declares common parameters input/output view/annotation/feature and stopword file 
 * 
 * TODO turn the Stopword object as a uima resource
 * 
 * @author hernandez 
 *
 */
public class TextSegmenterAE extends CommonAE {
	//	public class TextSegmenterAE extends JCasAnnotator_ImplBase {


	/*
	 * Properties param and default values 
	 */
	/*private final static String INPUT_SENTENCE_ANNOTATION_PARAM = "InputSentenceAnnotation";
	private final static String INPUT_WORD_ANNOTATION_PARAM = "InputWordAnnotation";
	private final static String INPUT_WORD_FEATURE_ANNOTATION_PARAM = "InputWordFeature";
	private final static String OUTPUT_SEGMENT_ANNOTATION_PARAM = "OutputSegmentAnnotation";
	private final static String STOPWORDFILE_PARAM = "StopWordFile";

	/**
	 * Default sentence annotation type value 
	 */
	//private final static String SENTENCE_TYPE = "org.apache.uima.SentenceAnnotation";

	/**
	 * Default token annotation type value 
	 */
	//private final static String TOKEN_TYPE="org.apache.uima.TokenAnnotation";

	/**
	 * Default token feature name
	 */
	private final static String DEFAULT_TOKEN_FEATURE_NAME= "coveredText" ; //""; stem


	/**
	 * Default segment annotation type value 
	 */
	//private final static String DEFAULT_SEGMENT_TYPE="fr.univnantes.lina.uima.textSegmenter.types.SegmentAnnotation";
	private final static String DEFAULT_SEGMENT_TYPE="common.types.discourse.Segment";


	/*
	 * 
	 */

	/**
	 * 
	 */
	//protected String sentenceAnnotationType = SENTENCE_TYPE;
	public static final String PARAM_SENTENCE_TYPE = "sentenceAnnotationType";
	@ConfigurationParameter(name = PARAM_SENTENCE_TYPE, mandatory = false, defaultValue="org.apache.uima.SentenceAnnotation")
	private String sentenceAnnotationType;


	//protected String tokenAnnotationType = TOKEN_TYPE;
	public static final String PARAM_TOKEN_TYPE = "tokenAnnotationType";
	@ConfigurationParameter(name = PARAM_TOKEN_TYPE, mandatory = false, defaultValue="org.apache.uima.TokenAnnotation")
	private String tokenAnnotationType;
	
	/**
	 * 
	 */
	//protected String tokenFeature ;
	//protected String tokenAnnotationType = TOKEN_TYPE;
	public static final String PARAM_TOKEN_FEATURE = "tokenFeature";
	@ConfigurationParameter(name = PARAM_TOKEN_FEATURE, mandatory = false, defaultValue=DEFAULT_TOKEN_FEATURE_NAME)
	private String tokenFeature;

	/**
	 * 
	 */
	//protected String outputSegmentAnnotation;
	public static final String PARAM_SEGMENT_TYPE = "outputSegmentAnnotation";
	@ConfigurationParameter(name = PARAM_SEGMENT_TYPE, mandatory = false, defaultValue=DEFAULT_SEGMENT_TYPE)
	private String outputSegmentAnnotation;

	//protected String outputSegmentAnnotation;
	public static final String PARAM_STOPWORD_PATH = "stopWordPath";
	@ConfigurationParameter(name = PARAM_STOPWORD_PATH, mandatory = false, defaultValue="")
	private String stopWordPath;
	
	/**
	 * 
	 */
	protected Stopword stopWord;

	/*
	 * Text segmenter specificities
	 */



	/*
	 * Methods 
	 */

	/**
	 * Get the parameter values
	 */
	public void initialize(UimaContext context) throws ResourceInitializationException {

		// generic AE parameters
		super.initialize(context);

		// current AE parameter
		try {
		/*	String sentenceParam = (String) context.getConfigParameterValue(INPUT_SENTENCE_ANNOTATION_PARAM);
			if (sentenceParam != null) this.setSentenceAnnotationType(sentenceParam);
			String wordParam = (String) context.getConfigParameterValue(INPUT_WORD_ANNOTATION_PARAM);
			if (wordParam != null) this.setTokenAnnotationType(wordParam);

			String tokenFeatureName = (String) context.getConfigParameterValue(INPUT_WORD_FEATURE_ANNOTATION_PARAM);
			if (wordParam != null) { 
				//System.out.println("Debug: setTokenFeature with PARAMETER VALUE "+ tokenFeatureName);
				this.setTokenFeature(tokenFeatureName);
				//System.out.println("Debug: tokenFeatureName"+ tokenFeatureName);
			} 
			else{
				//System.out.println("Debug: setTokenFeature with DEFAULT "+ DEFAULT_TOKEN_FEATURE_NAME);
				this.setTokenFeature(DEFAULT_TOKEN_FEATURE_NAME);
				//System.out.println("Debug: tokenFeatureName = DEFAULT_TOKEN_FEATURE_NAME "+ DEFAULT_TOKEN_FEATURE_NAME);
			}

			//System.out.println("Debug: getTokenFeature() "+this.getTokenFeature());

			String outputSegmentAnnotation = (String) context.getConfigParameterValue(OUTPUT_SEGMENT_ANNOTATION_PARAM);
			if (outputSegmentAnnotation != null) { this.setOutputSegmentAnnotation(outputSegmentAnnotation);} else
			{this.setOutputSegmentAnnotation(DEFAULT_SEGMENT_TYPE);}
		 	*/
			//String stopWordPath = (String) context.getConfigParameterValue(STOPWORDFILE_PARAM);
			this.setStopWord(stopWordPath);

		} catch (Exception e) {
			throw new ResourceInitializationException(e);
		}
	}

	

	/*
	 * Accessors
	 */


	/**
	 * @return the sentenceAnnotationType
	 */
	public String getSentenceAnnotationType() {
		return sentenceAnnotationType;
	}

	/**
	 * @param sentenceAnnotationType the sentenceAnnotationType to set
	 */
	public void setSentenceAnnotationType(String sentenceAnnotationType) {
		this.sentenceAnnotationType = sentenceAnnotationType;
	}

	/**
	 * @return the tokenAnnotationType
	 */
	public String getTokenAnnotationType() {
		return tokenAnnotationType;
	}

	/**
	 * @param tokenAnnotationType the tokenAnnotationType to set
	 */
	public void setTokenAnnotationType(String tokenAnnotationType) {
		this.tokenAnnotationType = tokenAnnotationType;
	}

	/**
	 * @return the outputSegmenAnnotation
	 */
	protected String getOutputSegmentAnnotation() {
		return outputSegmentAnnotation;
	}

	/**
	 * @param outputSegmenAnnotation the outputSegmenAnnotation to set
	 */
	protected void setOutputSegmentAnnotation(String outputSegmentAnnotation) {
		this.outputSegmentAnnotation = outputSegmentAnnotation;
	}

	protected void setStopWord(File file) throws Exception {
		if (file.exists()) {
			if (file.isFile()) {
				this.stopWord = new Stopword(file);
			} else {
				String msg= "This file " + file;
				msg += " should have been a normal file instead of a directory.";
				throw new Exception (msg);
			}
		} else {
			String msg= "This file " + file + " doesn't exist.";
			throw new Exception (msg);
		}
	}

	protected void setStopWord(String path) throws Exception {
		File file ;
		// if a stop word path have been set
		if ((path != null) && (!path.equalsIgnoreCase(""))){
			file= new File(path);
		} 
		else file = File.createTempFile(this.getClass().getName(), "tmp");
		this.setStopWord(file);
		// if not (it means an empty list)
		//else this.stopWord = (fr.univnantes.lina.uima.textSegmenter.Stopword) new Hashtable();

	}

	protected Stopword getStopWord() {
		return this.stopWord;
	}


	/**
	 * @return the tokenFeature
	 */
	protected String getTokenFeature() {
		return tokenFeature;
	}

	/**
	 * @param tokenFeature the tokenFeature to set
	 */
	protected void setTokenFeature(String tokenFeature) {
		this.tokenFeature = tokenFeature;
	}






}




