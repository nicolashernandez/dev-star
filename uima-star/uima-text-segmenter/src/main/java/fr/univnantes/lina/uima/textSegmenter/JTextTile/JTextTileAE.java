package fr.univnantes.lina.uima.textSegmenter.JTextTile;

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

import java.util.HashMap;
import java.util.Vector;

import org.apache.uima.UimaContext;
import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.cas.FSIterator;
import org.apache.uima.fit.descriptor.ConfigurationParameter;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.tcas.Annotation;
import org.apache.uima.resource.ResourceInitializationException;

import fr.univnantes.lina.uima.common.cas.AnnotationUtils;
import fr.univnantes.lina.uima.textSegmenter.TextSegmenterAE;
import fr.univnantes.lina.uima.textSegmenter.UIMARawText;
import fr.univnantes.lina.uima.textSegmenter.C99.C99Parser;
import fr.univnantes.lina.uima.textSegmenter.types.SegmentAnnotation;
import uk.ac.man.cs.choif.extend.Debugx;
import uk.ac.man.cs.choif.nlp.surface.Stopword;

import java.io.File;


/**
 * Annotator engine which uses the JTextTile, a Java implementation of Marti 
 * Hearst's TextTiling algorithm from Freddy Choi.
 * Segment text at the discourse level.
 * 
 * @author hernandez
 *
 */
public class JTextTileAE extends TextSegmenterAE {
	
	private Boolean debug = true;

	/*
	 * PARAMETER
	 */

	//private final static String WINDOW_PARAM = "WindowSize";
	//private final static String STEP_PARAM = "StepSize";
	
	/*
	 * DEFAULT VALUE
	 */
	//private final static int WINDOW_DEFAULT = -1;
	//private final static int STEP_DEFAULT = -1 ;
	
	/**
	 * 
	 */
	//private String tokenFeature ;
	
	//private int windowSize;
	public static final String PARAM_WINDOW_SIZE = "windowSize";
	@ConfigurationParameter(name = PARAM_WINDOW_SIZE, mandatory = false, defaultValue="-1")
	private Integer  windowSize;

	//private int stepSize;
	public static final String PARAM_STEP_SIZE = "stepSize";
	@ConfigurationParameter(name = PARAM_STEP_SIZE, mandatory = false, defaultValue="-1")
	private Integer  stepSize;


	/*
	 * Accessors
	 */

	

	/**
	 * @return the window
	 */
	protected int getWindow() {
		return windowSize;
	}

	/**
	 * @param window the window to set
	 */
	protected void setWindow(int window) {
		this.windowSize = window;
	}

	/**
	 * @return the step
	 */
	protected int getStep() {
		return stepSize;
	}

	/**
	 * @param step the step to set
	 */
	protected void setStep(int step) {
		this.stepSize = step;
	}


	/**
	 * @return the tokenFeature
	 */
	//protected String getTokenFeature() {
	//	return tokenFeature;
	//}


	
	

	
	/*
	 * Methods 
	 */
	
	/**
	 * Get the parameter values
	 */
	/*public void initialize(UimaContext context) throws ResourceInitializationException {

		// super
		super.initialize(context);

		//System.out.println("Debug: this.getStopWord() "+this.getStopWord());

		
		// current AE parameter
		try {

			Integer w = (Integer) context.getConfigParameterValue(WINDOW_PARAM);
			if (w == null) w =WINDOW_DEFAULT;
			this.setWindow(w);
			Integer s = (Integer) context.getConfigParameterValue(STEP_PARAM);
			if (s == null) s = STEP_DEFAULT;
			this.setStep(s);

		} catch (Exception e) {
			throw new ResourceInitializationException(e);
		}
	}*/

	/**
	 * Process input view
	 */
	//	@Override
	protected String processInputView(JCas inputViewJCas,
			FSIterator contextAnnotationsFSIter,
			HashMap<String, Integer> inputAnnotationStringHashMap,
			String inputFeatureString, JCas outputViewJCas,
			String outputAnnotationString, String ouputFeatureString)
	throws AnalysisEngineProcessException {
		
		
		// Store both an array of Sentence annotations, and an (sentence)ArrayOfTokenFeatureArray in private properties
		UIMARawText aUIMARawText = new UIMARawText(inputViewJCas,null, this.getSentenceAnnotationType(), this.getTokenAnnotationType(), this.getTokenFeature());
		
		// this is required for JTextTile but not for C99
		aUIMARawText.setSuperTextAndSentenceBoundaries();
		
		//System.out.println("Info: Average of words per sentence: " + text.size()/sentenceArrayOfTokenFeatureArray.length);
		//System.out.println("Info: Average of words per sentence (in AE vector size): " + aUIMARawText.getSentenceArrayOfTokenFeatureArray()[0].length);
		//System.out.println("Info: Average of words per sentence (in AE): " + aUIMARawText.getSentenceArrayOfTokenFeatureArray()[0].length);
		
		// A simple homemade empirical heuristic
		if (getWindow() <= 0) windowSize = (aUIMARawText.text.size() / aUIMARawText.getSentenceArrayOfTokenFeatureArray().length) * 3;
		if (getStep() <= 0) stepSize = (aUIMARawText.text.size() / aUIMARawText.getSentenceArrayOfTokenFeatureArray().length) ;
				
		JTextTileParser aJTextTileParsedText = new JTextTileParser(aUIMARawText,windowSize,stepSize,getStopWord());
		//System.out.println("Debug: aJTextTileParsedText.getRawText().text.size()"+aJTextTileParsedText.getRawText().text.size());
		//System.out.println("Debug: aJTextTileParsedText.getRawText().tokens().size()"+aJTextTileParsedText.getRawText().tokens().size());
	

		// A bit of error checking 
		if (aJTextTileParsedText.getRawText().text.size() <= (this.getWindow() * 2)) {
			System.err.println("Warning: Window size (" + this.getWindow() + " * 2 = " + (this.getWindow() * 2) + ") larger then collection (" + aJTextTileParsedText.getRawText().text.size() + "). Do not know the implications during the proceeding (may lead to a fatal error).");
			//throw AnalysisEngineProcessException();
		}

		if (debug) {System.out.println("Debug: preprocessing");}
		// consider the tokenFeature as the preprocessing result
		// here it is not an optimal implementation could have been done in the  UIMARawText initialization
		aJTextTileParsedText.preprocess();
		
		if (debug) {System.out.println("Debug: Computing similarity scores");}
		aJTextTileParsedText.similarityDetermination();	// Compute similarity scores
		
		if (debug) {System.out.println("Debug: Computing depth scores using the similarity scores");}
		aJTextTileParsedText.depthScore();				// Compute depth scores using the similarity scores
		
		if (debug) {System.out.println("Debug: Identifying boundaries");}
		aJTextTileParsedText.boundaryIdentification();	// Identify the boundaries
		
		if (debug) {System.out.println("Debug: Displaying results");}

		// Annotation of topic segment
		// For each segment then get the begin and the end of respectively the first and the last sentence of the segment 
		Vector <Annotation> sentenceVector = aJTextTileParsedText.getRawText().getSentenceAnnotations();
		Vector text = aJTextTileParsedText.getRawText().text; // The text
		Vector sentence = aJTextTileParsedText.getRawText().boundaries;// c.sentenceBoundaries(); // Sentence boundaries
		Vector seg = aJTextTileParsedText.boundaries ; // Segment boundaries
		int sentenceTokenStartIndex, sentenceTokenEndIndex; // Sentence boundaries
		int segmentBeginOffset = 0, segmentEndOffset = sentenceVector.get(sentenceVector.size()-1).getEnd(); // Sentence boundaries
		
		/* The implicit boundary at the beginning of the file */
		if (debug) {System.out.println("Debug:==========");}

		if (sentence.size()>0) {segmentBeginOffset = sentenceVector.get(0).getBegin();}
		
		/* Print all the sentences */
		for (int i=1; i<sentence.size(); i++) {
			// sentence end offset of the last one and begin offset of the current one
			int previousSentenceEndOffset = sentenceVector.get(i-1).getEnd();
			int currentSentenceBeginOffset = sentenceVector.get(i).getBegin();

			/* Get sentence boundaries */
			// voila comment je comprends la structure sentence
			// sentence de rang i à le rang du dernier token qui la compose (encore plus vrai pour le rang 0 (i=1))
			sentenceTokenStartIndex = ((Integer) sentence.elementAt(i-1)).intValue(); 
			//sentenceTokenEndIndex = ((Integer) sentence.elementAt(i)).intValue();

			/* If start is a topic boundary, print marker */
			if (seg.contains(new Integer(sentenceTokenStartIndex))) { 
				if (debug) {
					System.out.println("Debug:==========");		
				}
				AnnotationUtils.createAnnotation(inputViewJCas, this.getOutputSegmentAnnotation(), segmentBeginOffset, previousSentenceEndOffset);
				segmentBeginOffset = currentSentenceBeginOffset;
				
			}
			
			
			if (debug) {
				String line = "";
				sentenceTokenEndIndex = ((Integer) sentence.elementAt(i)).intValue();
				for (int j=sentenceTokenStartIndex; j<sentenceTokenEndIndex; j++) line += (text.elementAt(j) + " ");
					System.out.println(line.trim());
			}
			/* Print a sentence */
			//line = "";
			//for (int j=sentenceTokenStartIndex; j<sentenceTokenEndIndex; j++) line += (text.elementAt(j) + " ");
			//System.out.println(line.trim());
		}
		AnnotationUtils.createAnnotation(inputViewJCas, this.getOutputSegmentAnnotation(), segmentBeginOffset, sentenceVector.get(sentence.size()-1).getEnd());
		if (debug) {
			System.out.println("Debug:==========");		
		}
		

		// La méthode requiert de retourner quelque chose 
		// Le plus simple est de lui retourner l'image d'elle même
		return inputViewJCas.getSofaDataString();
	}


}




