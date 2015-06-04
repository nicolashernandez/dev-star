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
import java.util.Iterator;
import java.util.Vector;

import org.apache.uima.UimaContext;
import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.cas.FSIterator;
import org.apache.uima.cas.text.AnnotationIndex;
import org.apache.uima.examples.SourceDocumentInformation;
import org.apache.uima.fit.descriptor.ConfigurationParameter;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.tcas.Annotation;
import org.apache.uima.resource.ResourceInitializationException;

import common.types.text.Sentence;

import fr.univnantes.lina.javautil.IOUtilities;
import fr.univnantes.lina.uima.common.cas.AnnotationCollectionUtils;
import fr.univnantes.lina.uima.common.cas.AnnotationUtils;
import fr.univnantes.lina.uima.common.cas.JCasUtils;
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
public class JTextTileFitAE extends org.apache.uima.fit.component.JCasAnnotator_ImplBase {



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
	 * Default document source annotation
	 */
	private static String DEFAULT_SOURCE_DOCUMENT_INFORMATION_ANNOTATION = "org.apache.uima.examples.SourceDocumentInformation";


	/**
	 * Default token feature name
	 */
	private final static String DEFAULT_TOKEN_FEATURE_NAME= "coveredText" ; //""; stem


	/**
	 * Default segment annotation type value 
	 */
	//private final static String DEFAULT_SEGMENT_TYPE="fr.univnantes.lina.uima.textSegmenter.types.SegmentAnnotation";
	private final static String DEFAULT_SEGMENT_TYPE="common.types.discourse.Segment";
	/**
	 * Default sentence annotation type value 
	 */
	private final static String DEFAULT_SENTENCE_TYPE="common.types.text.Sentence";

	/*
	 * 
	 */

	/**
	 * under which annotation the segmentation should be performed (if several instances, only the first one is considered)
	 */
	//protected String sentenceAnnotationType = SENTENCE_TYPE;
	public static final String PARAM_COVERING_ANNOTATION = "coveringAnnotationName";
	@ConfigurationParameter(name = PARAM_COVERING_ANNOTATION, mandatory = false)
	private String coveringAnnotationName;
	
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

	public static final String PARAM_STOPWORD_PATH = "stopWordPath";
	@ConfigurationParameter(name = PARAM_STOPWORD_PATH, mandatory = false, defaultValue="")
	private String stopWordPath;

	//
	public static final String PARAM_OUTPUT_DIR = "outputDir";
	@ConfigurationParameter(name = PARAM_OUTPUT_DIR, mandatory = true, defaultValue="/tmp")
	private String outputDir;


	public static final String PARAM_OUTPUT_FORMAT = "outputFormat";
	@ConfigurationParameter(name = PARAM_OUTPUT_FORMAT, mandatory = false, defaultValue="-1")
	private Integer outputFormat;





	public static final String PARAM_DEBUG = "debug";
	@ConfigurationParameter(name = PARAM_DEBUG, mandatory = true, defaultValue="FALSE")
	private Boolean debug ;
	
	public static final String PARAM_SET_SENTENCE_TEXTTILING_STATUS = "setSentenceTextTilingStatus";
	@ConfigurationParameter(name = PARAM_SET_SENTENCE_TEXTTILING_STATUS, mandatory = true, defaultValue="FALSE")
	private Boolean setSentenceTextTilingStatus ;
	

	/**
	 * 
	 */
	protected Stopword stopWord;


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


	private final static String START_LABEL = "S";
	private final static String OTHER_LABEL = "O";

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


	private int numberOfDocWithWindowSizeLargerThanText = 0;
	private int numberOfProcessedDocument = 0;



	/*
	 * Methods 
	 */

	/**
	 * Get the parameter values
	 */
	public void initialize(UimaContext context) throws ResourceInitializationException {

		// super
		super.initialize(context);

		//System.out.println("Debug: this.getStopWord() "+this.getStopWord());

		// current AE parameter
		try {

			this.setStopWord(stopWordPath);
		} catch (Exception e) {
			throw new ResourceInitializationException(e);
		}
	}

	/**
	 * Process input view
	 * @param aJCas 
	 */
	@Override
	public void process(JCas inputViewJCas) throws AnalysisEngineProcessException {

		numberOfProcessedDocument++;
		/*AnnotationIndex<Annotation> 
		//FSIterator<FeatureStructure> 
		annotationIndex = inputViewJCas.getAnnotationIndex(); // getAnnotationIndex();
		for (Annotation annotation : annotationIndex) {
			System.out.println(annotation.getClass().getName()+" "+ annotation.getCoveredText());
		}*/
		//{SourceDocumentInformation sourceDocumentInformation = (SourceDocumentInformation) inputViewJCas.getAnnotationIndex(inputViewJCas.getTypeSystem().getType(DEFAULT_SOURCE_DOCUMENT_INFORMATION_ANNOTATION)).iterator().get();
		//String uri = sourceDocumentInformation.getUri().substring(sourceDocumentInformation.getUri().lastIndexOf(File.separator));
		//System.err.println("Debug: uri "+uri);
		//}

		// Store both an array of Sentence annotations, and an (sentence)ArrayOfTokenFeatureArray in private properties
		UIMARawText aUIMARawText = new UIMARawText(inputViewJCas, coveringAnnotationName, this.getSentenceAnnotationType(), this.getTokenAnnotationType(), this.getTokenFeature());
		//TODO null -> coveringAnnotationName
		// this is required for JTextTile but not for C99
		aUIMARawText.setSuperTextAndSentenceBoundaries();

		//System.out.println("Info: Average of words per sentence: " + text.size()/sentenceArrayOfTokenFeatureArray.length);
		//System.out.println("Info: Average of words per sentence (in AE vector size): " + aUIMARawText.getSentenceArrayOfTokenFeatureArray()[0].length);
		//System.out.println("Info: Average of words per sentence (in AE): " + aUIMARawText.getSentenceArrayOfTokenFeatureArray()[0].length);

		//System.err.println("Debug: aUIMARawText.text.size() "+aUIMARawText.text.size());

		// if there is at least one sentence (text non empty)
		if (aUIMARawText.getSentenceArrayOfTokenFeatureArray().length>0) {

			//System.err.println("Debug: aUIMARawText.getSentenceArrayOfTokenFeatureArray().length "+aUIMARawText.getSentenceArrayOfTokenFeatureArray().length);
			// A simple homemade empirical heuristic
			if (getWindow() <= 0) windowSize = (aUIMARawText.text.size() / aUIMARawText.getSentenceArrayOfTokenFeatureArray().length) * 3;
			if (getStep() <= 0) stepSize = (aUIMARawText.text.size() / aUIMARawText.getSentenceArrayOfTokenFeatureArray().length) ;
			//System.err.println("Debug: windowSize "+windowSize);
			//System.err.println("Debug: stepSize "+stepSize);


			JTextTileParser aJTextTileParsedText = new JTextTileParser(aUIMARawText,windowSize,stepSize,getStopWord());
			//System.out.println("Debug: aJTextTileParsedText.getRawText().text.size()"+aJTextTileParsedText.getRawText().text.size());
			//System.out.println("Debug: aJTextTileParsedText.getRawText().tokens().size()"+aJTextTileParsedText.getRawText().tokens().size());


			// A bit of error checking 
			if (aJTextTileParsedText.getRawText().text.size() <= (this.getWindow() * 2)) {
				SourceDocumentInformation sourceDocumentInformation = (SourceDocumentInformation) inputViewJCas.getAnnotationIndex(inputViewJCas.getTypeSystem().getType(DEFAULT_SOURCE_DOCUMENT_INFORMATION_ANNOTATION)).iterator().get();
				String uri = sourceDocumentInformation.getUri().substring(sourceDocumentInformation.getUri().lastIndexOf(File.separator)+1);
				//System.err.println("Debug: uri "+uri);



				System.err.println("Warning: In "+uri+", the Window size (" + this.getWindow() + " * 2 = " + (this.getWindow() * 2) 
						+ ") larger than collection (" + aJTextTileParsedText.getRawText().text.size() 
						+ "). Leads to a fatal error in (ArrayIndexOutOfBoundsException) in JTextTileLINA.similarityDetermination(JTextTileLINA.java:396).");
				System.err.println("This document will not really be processed (by default the first line is a start segment) ");
				//throw AnalysisEngineProcessException();
				// Caused by: java.lang.ArrayIndexOutOfBoundsException: 38 >= 4
				// at java.util.Vector.elementAt(Vector.java:470)
				//  at fr.univnantes.lina.uima.textSegmenter.JTextTile.JTextTileLINA.similarityDetermination(JTextTileLINA.java:396)
				numberOfDocWithWindowSizeLargerThanText++;

				AnnotationUtils.createAnnotation(inputViewJCas, this.getOutputSegmentAnnotation(), 0, inputViewJCas.getDocumentText().length());


			}
			else {
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
				if (debug) {
					String line = "";
					sentenceTokenEndIndex = ((Integer) sentence.elementAt(0)).intValue();
					for (int j=0; j<sentenceTokenEndIndex; j++) line += (text.elementAt(j) + " ");
					System.out.println(line.trim());
				}

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
			}


			if (setSentenceTextTilingStatus) {
				AnnotationIndex<Annotation> segmentAnnotationIndex = inputViewJCas.getAnnotationIndex(JCasUtils.getJCasType(inputViewJCas, DEFAULT_SEGMENT_TYPE)) ;
				Iterator<Annotation> segmentAnnotationIterator = segmentAnnotationIndex.iterator();
				while (segmentAnnotationIterator.hasNext()) {
					Annotation segmentAnnotation = segmentAnnotationIterator.next();

					Iterator<Annotation> subSegmentAnnotationIterator = AnnotationCollectionUtils.subiterator(inputViewJCas, segmentAnnotation);
					//AnnotationIndex<Annotation> sentenceAnnotationIndex  inputViewJCas.getAnnotationIndex(JCasUtils.getJCasType(inputViewJCas, PARAM_SENTENCE_TYPE)) ;
					//	Iterator<Annotation> sentenceAnnotationIterator = sentenceAnnotationIndex.iterator();
					Boolean firstSegmentSentence = true;
					while (subSegmentAnnotationIterator.hasNext()) {
						Annotation subSegmentAnnotation = subSegmentAnnotationIterator.next();
						if (subSegmentAnnotation.getClass().getName() == DEFAULT_SENTENCE_TYPE) {
							Sentence sentence = (Sentence) subSegmentAnnotation;
							if (firstSegmentSentence) {
								firstSegmentSentence = false;
								sentence.setTextTilingStatus(START_LABEL);
							/*	outputString.append(START_LABEL);
								outputString.append("\t");
								outputString.append(subSegmentAnnotation.getCoveredText().replaceAll("\\s+", " "));
								outputString.append(System.getProperty("line.separator"));*/

							}
							else {
								sentence.setTextTilingStatus(OTHER_LABEL);

								/*outputString.append(OTHER_LABEL);
								outputString.append("\t");
								outputString.append(subSegmentAnnotation.getCoveredText().replaceAll("\\s+", " "));
								outputString.append(System.getProperty("line.separator"));*/

							}
						}
					}

				}
			}
			
			/* Output */
			if (outputFormat >= 0) { 
				// TODO param pour le type de output -1 nooutput 1 xstream 2 sentenceAnnotation B/BE/E/I
				// Get the uri of the current jcas
				SourceDocumentInformation sourceDocumentInformation = (SourceDocumentInformation) inputViewJCas.getAnnotationIndex(inputViewJCas.getTypeSystem().getType(DEFAULT_SOURCE_DOCUMENT_INFORMATION_ANNOTATION)).iterator().get();
				String uri = sourceDocumentInformation.getUri().substring(sourceDocumentInformation.getUri().lastIndexOf(File.separator));
				//MiscUtil.writeToFS(mBoxMessage.toString(), messageSaveDir+"/"+uri, false);
				/*XStream xstream = new XStream(); 
					String xmlHeader = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"+"\n";
					String snippet = xstream.toXML(mBoxMessage);*/
				//System.out.println("Debug: toXML "+ snippet);
				//String xml = xmlHeader+snippet;

				// the following do not escape the whitespace char
				//System.out.println("Debug: escapeXml "+ StringEscapeUtils.escapeXml(snippet));

				// CR/LF/tab http://en.wikipedia.org/wiki/List_of_XML_and_HTML_character_entity_references
				//snippet = snippet.replaceAll("\\n", "&#10;").replaceAll("\\r", "&#13;").replaceAll("\\t", "&#9;");
				//System.out.println("Debug: myescape "+ snippet);

				StringBuffer outputString = new StringBuffer();
				AnnotationIndex<Annotation> segmentAnnotationIndex = inputViewJCas.getAnnotationIndex(JCasUtils.getJCasType(inputViewJCas, DEFAULT_SEGMENT_TYPE)) ;
				Iterator<Annotation> segmentAnnotationIterator = segmentAnnotationIndex.iterator();
				while (segmentAnnotationIterator.hasNext()) {
					Annotation segmentAnnotation = segmentAnnotationIterator.next();
					// TODO export a sentence per line and token whitespace separated ; in addition here assume the input text does not contain \n\n sequences

					Iterator<Annotation> subSegmentAnnotationIterator = AnnotationCollectionUtils.subiterator(inputViewJCas, segmentAnnotation);
					//AnnotationIndex<Annotation> sentenceAnnotationIndex  inputViewJCas.getAnnotationIndex(JCasUtils.getJCasType(inputViewJCas, PARAM_SENTENCE_TYPE)) ;
					//	Iterator<Annotation> sentenceAnnotationIterator = sentenceAnnotationIndex.iterator();
					Boolean firstSegmentSentence = true;
					while (subSegmentAnnotationIterator.hasNext()) {
						Annotation subSegmentAnnotation = subSegmentAnnotationIterator.next();
						if (subSegmentAnnotation.getClass().getName() == DEFAULT_SENTENCE_TYPE)
							if (firstSegmentSentence) {
								firstSegmentSentence = false;
								outputString.append(START_LABEL);
								outputString.append("\t");
								outputString.append(subSegmentAnnotation.getCoveredText().replaceAll("\\s+", " "));
								outputString.append(System.getProperty("line.separator"));

							}
							else {
								outputString.append(OTHER_LABEL);
								outputString.append("\t");
								outputString.append(subSegmentAnnotation.getCoveredText().replaceAll("\\s+", " "));
								outputString.append(System.getProperty("line.separator"));

							}
					}

				}
				//outputString.append(segmentAnnotation.getCoveredText()+"\n");
				//outputString.append("\n");

				//TODO deals when there are some annotation types missing
				/*if (segmentAnnotationIndex.iterator().hasNext()) {

					}*/

				IOUtilities.writeToFS(outputString.toString(), outputDir+File.separator+uri, false);
			}
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



	/**
	 * 
	 * @throws AnalysisEngineProcessException
	 */
	@Override
	public void collectionProcessComplete()  throws AnalysisEngineProcessException {
		// Prints the instance ID to the console - this proves the same instance
		// of the SharedModel is used in both Annotator instances.
		System.err.println("Info: "+getClass().getSimpleName() + ": " + getClass().getName() + '@' + Integer.toHexString(hashCode()));
		System.err.println("Info: "+getClass().getSimpleName() + ": # of processed JCas: "+ numberOfProcessedDocument );
		System.err.println("Info: "+getClass().getSimpleName() + ": # of processed JCas with a window size higher than the text (and so we only one segment by default): "+ numberOfDocWithWindowSizeLargerThanText );


	}


}




