package fr.univnantes.lina.uima.textSegmenter.C99;

/** 
 * UIMA Text Segmenter
 * Copyright (C) 2010, 2011  Christine Jacquin,  Nicolas Hernandez
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
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.cas.FSIterator;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.tcas.Annotation;
import org.apache.uima.resource.ResourceInitializationException;

import fr.univnantes.lina.uima.common.cas.AnnotationUtils;
import fr.univnantes.lina.uima.textSegmenter.TextSegmenterAE;
import fr.univnantes.lina.uima.textSegmenter.UIMARawText;


import uk.ac.man.cs.choif.extend.Debugx;



/**
 * Annotator engine which uses the C99 algorithm and its java implementation 
 * from Choi to segment text at the discourse level 
 * 
 * @author jacquin, hernandez 
 *
 */
public class C99FitAE extends TextSegmenterAE {
	
	private Boolean debug = false;
	
	/*
	 * Param and default values
	 */
	private final static String TERMFREQUENCYWEIGHT_PARAM = "TermFrequencyWeight";
	private final static String NUMBEROFSEGMENTS_PARAM = "NumberOfSegments";
	private final static String RANKINGMASKSIZE_PARAM = "RankingMaskSize";
	
	/*
	 * Properties
	 */
	private int segment;
	
	private int rankingMask;

	private Boolean tfWeight;
	
	/*
	 * Accessors
	 */


	private void setSegment(Integer segment) {
		this.segment = segment;
	}

	private int getSegment() {
		return this.segment;
	}


	private void setRankingMask(Integer rankingMask) {
		this.rankingMask = rankingMask;
	}

	private int getRankingMask() {
		return this.rankingMask;
	}

	public Boolean getTfWeight() {
		return tfWeight;
	}

	public void setTfWeight(Boolean tfWeight) {
		this.tfWeight = tfWeight;
	}


	
	/*
	 * Methods 
	 */
	
	/**
	 * Get the parameter values
	 */
	public void initialize(UimaContext context) throws ResourceInitializationException {

		// super
		super.initialize(context);

		// current AE parameter
		try {


			Integer segment = (Integer) context.getConfigParameterValue(NUMBEROFSEGMENTS_PARAM);
			this.setSegment(segment);
			Integer rankingMask = (Integer) context.getConfigParameterValue(RANKINGMASKSIZE_PARAM);
			this.setRankingMask(rankingMask);
			Boolean tfWeight = (Boolean) context.getConfigParameterValue(TERMFREQUENCYWEIGHT_PARAM);
			this.setTfWeight(tfWeight);


		} catch (Exception e) {
			throw new ResourceInitializationException(e);
		}
	}

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
		C99Parser aC99ParsedText = new C99Parser(aUIMARawText);

		// Return the sentenceArrayOfTokenFeatureArray property of the C99Parser class as an array of array of String 
		String D[][]=aC99ParsedText.getRawText().transformSentenceArrayOfTokenFeatureArrayToAnArrayOfArrayOfString();
		//Word[][] sentenceArrayOfTokenFeatureArray = rawText.getSentenceArrayOfTokenFeatureArray();

		if (debug) for (int i=0;i<D.length;i++){
			// according to Choi implementation, this a paragraph limit		
			if (D[i]!=null){
				for (int j=0;j<D[i].length;j++){
					System.out.println("Debug: "+i+" "+j+" "+D[i][j]);
				}
			}

		}

		// use of C99 class of Choi to compute the result of the segmentation process
		// for Choi algorithm, a sentence = a line of the text file in entry
		// components of S=> first one: segments, second one=> sentences, third one: tokens
		String[][][] S = (this.getTfWeight() ? aC99ParsedText.segmentW(D, this.getSegment(), this.getRankingMask()) : aC99ParsedText.segment(D, this.getSegment(), this.getRankingMask()));
		if (debug) Debugx.msg("C99", "Ready.");


		//					//	 Print output 
		//					final String sep = "==========";
		//					String lineTmp;		
		//					for (int i=0, ie=S.length; i<ie; i++) {
		//						System.out.println(sep);
		//						for (int j=0, je=S[i].length; j<je; j++) {
		//							
		//							lineTmp = "";
		//							for (int k=0, ke=S[i][j].length; k<ke; k++) {
		//								lineTmp += (S[i][j][k] + " ");
		//								
		//							}
		//							
		//							
		//							System.out.println(lineTmp);
		//						}
		//					}
		//					System.out.println(sep);
		//					

		//annotation of thematic segment

        // For each segment then get the begin and the end of respectively the first and the last sentence of the segment 
		Vector <Annotation> sentenceVector = aC99ParsedText.getRawText().getSentenceAnnotations();
		int index=0;
		for (int i=0; i<S.length; i++){
			int begin = sentenceVector.get(index).getBegin();
			for (int j=0; j<S[i].length; j++){
				// white line are stored in S, so for mapping S and the paragraph annotation we must don't take care of them.
				if (! ((S[i][j].length==1) && (S[i][j][0]==""))){
					index++;
				}
			}
			AnnotationUtils.createAnnotation(inputViewJCas, this.getOutputSegmentAnnotation(), begin, sentenceVector.get(index-1).getEnd());

		}

		// La méthode requiert de retourner quelque chose 
		// Le plus simple est de lui retourner l'image d'elle même
		return inputViewJCas.getSofaDataString();
	}


}




