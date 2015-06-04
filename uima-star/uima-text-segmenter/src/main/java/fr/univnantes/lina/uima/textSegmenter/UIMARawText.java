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


import java.util.Vector;

import org.apache.uima.cas.FSIterator;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.text.AnnotationIndex;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.tcas.Annotation;


import uk.ac.man.cs.choif.nlp.doc.basic.*;

/**
 * Data Structure to store every sentence, the tokens and the associated token feature (stem or other) which are involved in it 
 * as well as a vector of Sentence UIMA Annotations (which can be used to retrieve token position and first/last sentence tokens)
 * 
 * Extends the RawText which holds array of tokens and token rank of begin/end sentences 
 * 
 * @author hernandez
 *
 */
public class UIMARawText extends RawText {

	private static String DEFAULT_DOCUMENT_ANNOTATION = "uima.tcas.DocumentAnnotation";

	private Boolean debug = false;

	/*
	 * Properties
	 */
	/**
	 * Be vigilent RawText owns (Sentence) boundaries and JTextTile owns (Segment) boundaries
	 */

	/**
	 *  This array stores for every sentence, the tokens and the associated token feature (stem or other) which are involved in it .
	 *  sentenceArrayOfTokenFeatureArray => first indices: number of the sentence, second indices: number of the tokenStem
	 */
	private Token sentenceArrayOfTokenFeatureArray[][];

	/**
	 * Vector of Sentence UIMA Annotations (can be used to retrieve token position and first/last sentence tokens)
	 */
	private Vector <Annotation> sentenceAnnotations=new Vector <Annotation>();


	/*
	 * Accessors
	 */
	public Token[][] getSentenceArrayOfTokenFeatureArray() {
		return sentenceArrayOfTokenFeatureArray;
	}

	public void setSentenceArrayOfTokenFeatureArray(Token[][] sentenceArrayOfTokenFeatureArray) {
		this.sentenceArrayOfTokenFeatureArray = sentenceArrayOfTokenFeatureArray;
	}

	public Vector<Annotation> getSentenceAnnotations() {
		return sentenceAnnotations;
	}

	public void setSentenceAnnotations(Vector<Annotation> sentenceAnnotations) {
		this.sentenceAnnotations = sentenceAnnotations;
	}

	/*
	 * Methods
	 */


	/**
	 * Set text and boundaries from super (RawText) 
	 * This is required for JTextTile but not for C99
	 */
	public void setSuperTextAndSentenceBoundaries() {

		/* 2. Define variables */
		int word = 0; // Absolute word count, first word is word 0
		//boundaries.addElement(new Integer(word));


		for (int i=0; i<sentenceArrayOfTokenFeatureArray.length;i++){
			// if it is a boundary of a paragraph sentenceArrayOfTokenFeatureArray[i] will be = to null
			// to be aligned on Choi implementation
			if (sentenceArrayOfTokenFeatureArray[i]!=null){
				for (int j=0; j<sentenceArrayOfTokenFeatureArray[i].length;j++){

					text.addElement(sentenceArrayOfTokenFeatureArray[i][j].getTokenFeature());
					word++;
				}
				boundaries.addElement(new Integer(word));
			}

		}

		if (debug) System.out.println("Info: Average of words per sentence: " + text.size()/sentenceArrayOfTokenFeatureArray.length);
	}

	/**
	 * Return the sentenceArrayOfTokenFeatureArray property of the C99Parser class as an array of array of String 
	 * @return  the sentenceArrayOfTokenFeatureArray property of the C99Parser class as an array of array of String 
	 */
	public String[][] transformSentenceArrayOfTokenFeatureArrayToAnArrayOfArrayOfString(){

		String[][] temp = new String[sentenceArrayOfTokenFeatureArray.length][];
		for (int i=0; i<sentenceArrayOfTokenFeatureArray.length;i++){
			// if it is a boundary of a paragraph tabTokenStem[i] will be = to null
			// to be aligned on Choi implementation
			if (sentenceArrayOfTokenFeatureArray[i]!=null){
				temp[i]=new String[sentenceArrayOfTokenFeatureArray[i].length];
				for (int j=0; j<sentenceArrayOfTokenFeatureArray[i].length;j++){
					temp[i][j]=sentenceArrayOfTokenFeatureArray[i][j].getToken();
				}
			}
		}
		return temp;
	}



	/**
	 *  Store both an array of Sentence annotations, and an (sentence)ArrayOfTokenFeatureArray in private properties
	 *  A TokenFeature is a couple of value the coveredText and some feature (potentially stem but could also be coveredText
	 *  
	 *  @param aJCas
	 * @param documentTypeStr TODO
	 * @param sentenceTypeStr string name of the Sentence Annotation type
	 * @param tokenTypeStr		string name of the Word Annotation type
	 * @param tokenFeature		string name of the feature of the Word Annotation 
	 *  
	 */
	public UIMARawText(JCas aJCas, String documentTypeStr, String sentenceTypeStr, String tokenTypeStr, String tokenFeature) {

		if (documentTypeStr == null || documentTypeStr.equalsIgnoreCase(""))
			documentTypeStr = DEFAULT_DOCUMENT_ANNOTATION;
		//System.out.printf("Debug: documentTypeStr >%s<\n",documentTypeStr);
		Type documentType = aJCas.getTypeSystem().getType(documentTypeStr);
		Type sentenceType = aJCas.getTypeSystem().getType(sentenceTypeStr);
		Type tokenType = aJCas.getTypeSystem().getType(tokenTypeStr);
		//Type paragraphType = aJCas.getTypeSystem().getType(paragraphTypeStr);



		Annotation documentAnnotation = (Annotation) aJCas.getAnnotationIndex(documentType).iterator().get(); 
		//List<Sentence> sentences = JCasUtil.selectCovered(aJCas, Sentence.class, documentAnnotation);

		//System.out.printf("Debug: documentAnnotation.getCoveredText() >%s<\n",documentAnnotation.getCoveredText());

		// collecting index concerning sentence
		AnnotationIndex<Annotation> indexSentences = aJCas.getAnnotationIndex(sentenceType);
		// collecting index concerning token
		AnnotationIndex<Annotation> indexTokens = aJCas.getAnnotationIndex(tokenType);
		// collecting index concerning paragraph
		//	AnnotationIndex<Annotation> indexParagraphs = aJCas.getAnnotationIndex(paragraphType);

		//FSIterator<Annotation> iteratorSentences = indexSentences.iterator();
		FSIterator<Annotation> iteratorSentences = indexSentences.subiterator(documentAnnotation);

		int i=0;

		// we give the size of tabTokenStem
		this.sentenceArrayOfTokenFeatureArray=new Token[indexSentences.size()][];

		//if (debug) 
		//System.out.println("Debug: indexSentences.size() "+indexSentences.size());
		//System.out.println("Debug: indexTokens.size() "+indexTokens.size());
		/*
		try {
			Class<Annotation> tokenClass = UIMAUtilities.getClass(tokenTypeStr);
			Method tokenFeatureGetterMethod = UIMAUtilities.getAGetterMethod(tokenClass,tokenFeature);
		} catch (AnalysisEngineProcessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Method getTokenFeatureMethod = null;
		Object tokenFeatureObject = null;
		try {
			Object[] args = null;

			Class<Annotation> tokenClass = (Class<Annotation>) Class
			.forName(tokenTypeStr);

			// Génére le constructeur de la classe de l'annotation à créer
			Constructor<?> tgtConstr = tokenClass
			.getConstructor(new Class[] { JCas.class });

			// Crée une annotation du type target

			tokenFeatureObject = tgtConstr.newInstance(new Object[] { aJCas });
			tokenClass.cast(tokenFeatureObject);

			getTokenFeatureMethod = UIMAUtilities.getAGetterMethod(tokenClass, tokenFeature) ;
			//Method getTokenFeatureMethod = tokenClass.getMethod("setEnd", String.class);


		}
		catch (Exception e) {
			// TODO: handle exception
		}*/

		while (iteratorSentences.hasNext()) {
			Annotation sentence = iteratorSentences.next();

			//System.out.println("Debug: paragraphe: "+paragraph.getBegin()+" "+paragraph.getEnd()+" "+paragraph.getCoveredText());
			//FSIterator<Annotation> iteratorSentenceInParagraph = indexSentences.subiterator(paragraph);

			//	while (iteratorSentenceInParagraph.hasNext()) {			
			//	Annotation sentence = iteratorSentenceInParagraph.next();
			//	System.out.println("Debug: sentence:"+sentence.getBegin()+" "+sentence.getEnd()+" "+sentence.getCoveredText());
			// to keep the sentence annotation for the theme segment annotation
			sentenceAnnotations.add(sentence);

			FSIterator<Annotation> iteratorTokensInSentence = indexTokens.subiterator(sentence);

			
			// Sentence should not be without words !!!!
			if (!iteratorTokensInSentence.hasNext()) { 


				Vector <Token> vecteurToken=new Vector <Token>();
				while (iteratorTokensInSentence.hasNext()) {

					Annotation tokenSentence = iteratorTokensInSentence.next();	
					String s1=tokenSentence.getCoveredText();
					//				System.out.println("Debug: C99Parser - tokenFeature>"+tokenFeature+"<");

					//	String s2=tokenSentence.getStringValue(tokenType.getFeatureByBaseName(tokenFeature));
					//				String s2=tokenSentence.getStringValue(tokenType.getFeatureByBaseName("stem"));
					// Ajouts à l'annotation du type target
					String s2 = null;
					if (tokenFeature.equalsIgnoreCase("coveredText")) {s2 = s1;} 
					else {s2=tokenSentence.getStringValue(tokenType.getFeatureByBaseName(tokenFeature));}
					/*try {
					//Object result = (Object) getTokenFeatureMethod.invoke(tokenFeatureObject);
					//s2 = (String) getTokenFeatureMethod.invoke(tokenFeatureObject);

				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}*/

					vecteurToken.add(new Token(s1,s2));				
				}

				if (debug) for (Token t:vecteurToken ){
					System.out.println("Debug: tokenFeature: "+t);
				}

				this.sentenceArrayOfTokenFeatureArray[i]=new Token[vecteurToken.size()];


				vecteurToken.toArray(sentenceArrayOfTokenFeatureArray[i]);	

				if (debug) {
					System.out.println("Debug: i="+i+" "+sentenceArrayOfTokenFeatureArray[i]);

				}
				i++;	
			}
			else {
				//System.out.println("Debug: sentence without words ");
			}
		}
		//i++;

	}		


}
