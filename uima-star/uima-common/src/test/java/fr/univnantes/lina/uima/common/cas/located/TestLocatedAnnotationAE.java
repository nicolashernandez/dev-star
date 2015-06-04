package fr.univnantes.lina.uima.common.cas.located;

/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.uima.UimaContext;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.cas.CASException;
import org.apache.uima.cas.ConstraintFactory;
import org.apache.uima.cas.FSIterator;
import org.apache.uima.cas.FSMatchConstraint;
import org.apache.uima.cas.FSTypeConstraint;
import org.apache.uima.cas.text.AnnotationIndex;
import org.apache.uima.cas.text.AnnotationTree;
import org.apache.uima.cas.text.AnnotationTreeNode;
import org.apache.uima.examples.SourceDocumentInformation;
import org.apache.uima.fit.util.JCasUtil;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.tcas.Annotation;
import org.apache.uima.resource.ResourceInitializationException;

import sizeof.agent.SizeOfAgent;
import common.types.coref.Coreference;
import common.types.ne.NamedEntity;
import common.types.text.Chunk;
import common.types.text.LexicalUnit;
import common.types.text.Noun;
import common.types.text.Sentence;
import common.types.text.Token;
import common.types.text.Verb;
import fr.univnantes.lina.javautil.DateUtilities;
import fr.univnantes.lina.uima.common.ae.CommonAE;





/**
 * Build a located annotation index from the annotation index of default input view
 * then display the vicinity ("getCurrent", "getSuper", "getSub") of each located annotation
 * 
 * Test with a single sentence document such as echo "Jean pousse la voiture." > phrase.txt
 * and with opennlp processing
 * 
 */
public class TestLocatedAnnotationAE extends CommonAE {

	/*
	 * LOCAL VARIABLES
	 */

	/*
	 * Accessors
	 */


	/*
	 * Methods 
	 */

	/**
	 * Get the parameter values, setting the variables and checking the values 
	 */
	public void initialize(UimaContext aContext) throws ResourceInitializationException {

		// SUPER PARAMETER SETTINGS
		super.initialize(aContext);

		// CURRENT AE PARAMETER SETTINGS


		//try {
		//} catch (Exception e) {
		//	String errmsg = "!";
		//	throw new ResourceInitializationException(errmsg, new Object[] {});
		//}		
	}

	/**
	 * Based on the sentences and the words represented in WST format from a given view <br>
	 * Create the corresponding annotations into the CAS.
	 * 
	 * @return a null content
	 */
	@Override
	protected String processInputView(JCas inputViewJCas,
			FSIterator<Annotation> contextAnnotationsFSIter,
			Set<String> inputAnnotationSet,
			String inputFeatureString, JCas outputViewJCas,
			String outputAnnotationString, String ouputFeatureString)
					throws AnalysisEngineProcessException {

		// --
		JCas testJcas = createAnAnnotatedJCas(inputViewJCas); 


		// -- test uima annotation index
		// to test the cas change to inputViewJCas
		AnnotationIndex<Annotation> annotationIndex = null;
		annotationIndex = (AnnotationIndex<Annotation>) testJcas.getAnnotationIndex();

		AnnotationIndex<Annotation> chunkAnnotationIndex = null;
		chunkAnnotationIndex = (AnnotationIndex<Annotation>) testJcas.getAnnotationIndex(Chunk.type);


		System.out.println("Debug: ---------------------------------------------------------------");
		System.out.println("Debug: ambiguous iteration over an annotation index");
		FSIterator<Annotation> annotationIndexIterator = null;
		annotationIndexIterator = annotationIndex.iterator();
		annotationIndexIterator.moveToFirst();
		Annotation annotation = annotationIndexIterator.get();
		System.out.println("first annotation type>"+annotation.getClass().getName()+"< coveredText>"+annotation.getCoveredText()+"<");
		annotationIndexIterator.moveToNext();
		Annotation firstChunk = null;
		Annotation firstSentence = null;
		Annotation fourthChunk = null;
		Annotation fifthChunk = null;

		while(annotationIndexIterator.isValid()) {
			annotation = annotationIndexIterator.get();
			if ((annotation.getClass().getName().equalsIgnoreCase("common.types.text.Chunk")) && firstChunk == null)  firstChunk =  annotation;
			if ((annotation.getClass().getName().equalsIgnoreCase("common.types.text.Sentence")) && firstSentence == null)  firstSentence =  annotation;
			if ((annotation.getCoveredText().equalsIgnoreCase("the seaport")) && fourthChunk == null)  fourthChunk =  annotation;
			if ((annotation.getCoveredText().equalsIgnoreCase("of Nantes")) && fifthChunk == null)  fifthChunk =  annotation;

			System.out.println("moveToNext annotation type>"+annotation.getClass().getName()+"< coveredText>"+annotation.getCoveredText()+"<");
			annotationIndexIterator.moveToNext();
		}


		//-- testing what returns a subiterator over a typed index when the type to search is absent of the index 

		/*		AnnotationIndex<Annotation> sentenceAnnotationIndex = (AnnotationIndex<Annotation>) testJcas.getAnnotationIndex(Sentence.type);

		FSIterator<Annotation> firstSentenceSubIterator = annotationIndex.subiterator(firstChunk);

		sentenceAnnotationIndex.subiterator(firstChunk);
		while(annotationIndexIterator.isValid()) {
			annotation = annotationIndexIterator.get();
			if ((annotation.getClass().getName().equalsIgnoreCase("common.types.text.Chunk")) && firstChunk == null)  firstChunk =  annotation;
			if ((annotation.getClass().getName().equalsIgnoreCase("common.types.text.Sentence")) && firstChunk == null)  firstSentence =  annotation;

			System.out.println("moveToNext annotation type>"+annotation.getClass().getName()+"< coveredText>"+annotation.getCoveredText()+"<");
			annotationIndexIterator.moveToNext();
		}
		 */	

		//-- 
		testNonAmbiguousIterator(annotationIndex);

		testNonAmbiguousIterator(chunkAnnotationIndex);

		//-- 
		//testNonAmbiguousIteratorOverAnIndexOfAGivenType(testJcas, firstChunk);

		//-- 
		//testNonAmbiguousIteratorInReverseOrder(testJcas);

		//-- 
		testNonAmbiguousIteratorWhereSomeAnnotationsAreConcurrent(annotationIndex);


		//-- 
		testNonAmbiguousSubiterator(annotationIndex, firstSentence);


		//-- subiterating all the chunk annotations
		testChunkSubiterator(testJcas, annotationIndex);

		//-- non ambiguous and strict subiterating all the chunk annotations
		testChunkUnambiguousStrictSubiterator(testJcas, annotationIndex);






		//--
		testTypeSubsumptionConstrainedIterator(testJcas, annotationIndexIterator);


		//--
		testSelectSpecificTypesConstrainedIterator(testJcas, annotationIndexIterator);



		// -- tree navigation
		testAnnotationTreeNavigation(annotationIndex);





		// -- builds a sorted index for testing 
		// means the building of three sub indexes (locatedAnnotationMap, annotationBeginMap and annotationEndMap)
		LocatedAnnotationIndex locatedAnnotationIndex = LocatedAnnotationIndexBuilder.buildsIndex(testJcas);


		// -- Test LocatedAnnotationIndex parsing 
		// displaying the current annotations at the given offsets
		// Look at this code example first
		// give the same results sorted or not sorted, display in the same order
		testLocatedAnnotationIndexParsing(locatedAnnotationIndex);

		// -- Test LocatedAnnotationIndex parsing + toString LocatedAnnotation method 
		// which displays a larger vicinity overview
		// + vicinity in the index with next/previous LocatedAnnotation
		testLocatedAnnotationIndexParsingDisplayingMoreVicinityInformation(locatedAnnotationIndex);


		// -- Test next/previous LocatedAnnotation from a givenLocatedAnnotation containing a Person, Sentence and Lexical Unit
		//testParsingTheIndexAndRelativelyFromLocatedAnnotation(locatedAnnotationIndex);


		// uimaFIT
		// uimaFIT Subiterator
		/*Debug: ---------------------------------------------------------------
Debug: uimaFIT selectCovered (i.e. Subiterator) token of sentence
selectCovered type>common.types.text.Token< coveredText>Verne<
selectCovered type>common.types.text.Token< coveredText>visited<
selectCovered type>common.types.text.Token< coveredText>the<
selectCovered type>common.types.text.Token< coveredText>seaport<
selectCovered type>common.types.text.Token< coveredText>of<
selectCovered type>common.types.text.Token< coveredText>Nantes<
selectCovered type>common.types.text.Token< coveredText>.<
Debug: uimaFIT selectCovered (i.e. Subiterator) chunk of sentence
selectCovered type>common.types.text.Chunk< coveredText>Verne<
selectCovered type>common.types.text.Chunk< coveredText>visited<
selectCovered type>common.types.text.Chunk< coveredText>the seaport of Nantes<
selectCovered type>common.types.text.Chunk< coveredText>the seaport<
selectCovered type>common.types.text.Chunk< coveredText>of Nantes<
		 */
		System.out.println("Debug: ---------------------------------------------------------------");
		System.out.println("Debug: uimaFIT selectCovered (i.e. Subiterator) token of sentence");
		for (Annotation token : JCasUtil.selectCovered(testJcas, Token.class, firstSentence)) {
			System.out.println("selectCovered type>"+token.getClass().getName()+"< coveredText>"+token.getCoveredText()+"<");
		}		
		System.out.println("Debug: uimaFIT selectCovered (i.e. Subiterator) chunk of sentence");

		for (Annotation token : JCasUtil.selectCovered(testJcas, Chunk.class, firstSentence)) {
			System.out.println("selectCovered type>"+token.getClass().getName()+"< coveredText>"+token.getCoveredText()+"<");
		}

		System.out.println("Debug: uimaFIT selectCovered (i.e. Subiterator) token of chunk");

		FSIterator<Annotation> chunkIterator = chunkAnnotationIndex.iterator();
		chunkIterator.moveToFirst();
		annotation = chunkIterator.get();
		System.out.println("first annotation coveredText>"+annotation.getCoveredText()+"< type>"+annotation.getClass().getName()+"< ");
		for (Annotation token : JCasUtil.selectCovered(testJcas, Token.class, annotation)) {
			System.out.println("selectCovered type>"+token.getClass().getName()+"< coveredText>"+token.getCoveredText()+"<");
		}
		chunkIterator.moveToNext();
		while(chunkIterator.isValid()) {
			annotation = chunkIterator.get();
			System.out.println("moveToNext annotation coveredText>"+annotation.getCoveredText()+"< type>"+annotation.getClass().getName()+"<");
			for (Annotation token : JCasUtil.selectCovered(testJcas, Token.class, annotation)) {
				System.out.println("selectCovered type>"+token.getClass().getName()+"< coveredText>"+token.getCoveredText()+"<");
			}
			chunkIterator.moveToNext();
		}
		
		System.out.println("Debug: ---------------------------------------------------------------");
		System.out.println("Debug: uimaFIT selectCovering (i.e.) of token ");
		AnnotationIndex<Annotation> tokenAnnotationIndex = null;
		tokenAnnotationIndex = (AnnotationIndex<Annotation>) testJcas.getAnnotationIndex(Token.type);
		FSIterator<Annotation> tokenIterator = tokenAnnotationIndex.iterator();
		while(tokenIterator.isValid()) {
			annotation = tokenIterator.get();
			System.out.println("moveToNext annotation coveredText>"+annotation.getCoveredText()+"< type>"+annotation.getClass().getName()+"<");
			for (Annotation chunk :  JCasUtil.selectCovering(testJcas, Chunk.class, annotation.getBegin(), annotation.getEnd())) {
				
			
				System.out.println("selectCovered type>"+chunk.getClass().getName()+"< coveredText>"+chunk.getCoveredText()+"<");
			}
				tokenIterator.moveToNext();
		}
		
		// uimaFIT  selectFollowing
		/*
		Debug: ---------------------------------------------------------------
		Debug: uimaFIT selectFollowing chunk from the first chunk (step = 1)
		selectCovered type>common.types.text.Chunk< coveredText>Verne<
		selectCovered type>common.types.text.Chunk< coveredText>visited<
		selectCovered type>common.types.text.Chunk< coveredText>the seaport of Nantes<

		Debug: non-ambiguous iteration over an annotation index
first annotation coveredText>Verne< type>common.types.text.Chunk< 
moveToNext annotation coveredText>visited< type>common.types.text.Chunk<
moveToNext annotation coveredText>the seaport of Nantes< type>common.types.text.Chunk<
		 */
		System.out.println("Debug: ---------------------------------------------------------------");
		System.out.println("Debug: uimaFIT selectFollowing chunk from the first chunk (step = 1)");
		System.out.println("first chunk type>"+firstChunk.getClass().getName()+"< coveredText>"+firstChunk.getCoveredText()+"<");

		List<Chunk> followingChunkList =  JCasUtil.selectFollowing(testJcas, Chunk.class,  firstChunk, 2);
		List<Token> followingTokenList =  JCasUtil.selectFollowing(testJcas, Token.class,  firstChunk, 1);

		while (!followingChunkList.isEmpty()) {

			for (Annotation chunk : followingChunkList)
				System.out.println("selectFollowing chunk type>"+chunk.getClass().getName()+"< coveredText>"+chunk.getCoveredText()+"<");

					//	System.out.println("selectFollowing chunk type>"+followingChunkList.get(0).getClass().getName()+"< coveredText>"+followingChunkList.get(0).getCoveredText()+"<");
					if (!followingTokenList.isEmpty()) 
						System.out.println("selectFollowing token type>"+followingTokenList.get(0).getClass().getName()+"< coveredText>"+followingTokenList.get(0).getCoveredText()+"<");

					followingTokenList =  JCasUtil.selectFollowing(testJcas, Token.class,  followingChunkList.get(0), 2);
					followingChunkList =  JCasUtil.selectFollowing(testJcas, Chunk.class,  followingChunkList.get(0), 1);

		}




		// uimaFIT  selectSingleRelative
		System.out.println("Debug: ---------------------------------------------------------------");
		System.out.println("Debug: uimaFIT selectSingleRelative token 1 from firstChunk");
		System.out.println("first sentence type>"+firstSentence.getClass().getName()+"< coveredText>"+firstSentence.getCoveredText()+"<");

		System.out.println("first chunk type>"+firstChunk.getClass().getName()+"< coveredText>"+firstChunk.getCoveredText()+"<");
		Annotation token = JCasUtil.selectSingleRelative(testJcas, Token.class, firstChunk, 1);
		System.out.println("selectSingleRelative type>"+token.getClass().getName()+"< coveredText>"+token.getCoveredText()+"<");
		token = JCasUtil.selectSingleRelative(testJcas, Token.class, firstChunk, 2);
		System.out.println("selectSingleRelative type>"+token.getClass().getName()+"< coveredText>"+token.getCoveredText()+"<");

		System.out.println("Debug: uimaFIT selectSingleRelative token 1 from fourthChunk");
		System.out.println("fourth chunk type>"+fourthChunk.getClass().getName()+"< coveredText>"+fourthChunk.getCoveredText()+"<");
		token = JCasUtil.selectSingleRelative(testJcas, Token.class, fourthChunk, 1);
		System.out.println("selectSingleRelative type>"+token.getClass().getName()+"< coveredText>"+token.getCoveredText()+"<");
		token = JCasUtil.selectSingleRelative(testJcas, Token.class, fourthChunk, 2);
		System.out.println("selectSingleRelative type>"+token.getClass().getName()+"< coveredText>"+token.getCoveredText()+"<");

		// uimaFIT  selectBetween
		System.out.println("Debug: ---------------------------------------------------------------");
		System.out.println("Debug: uimaFIT selectBetween token between firstChunk and fifthChunk");
		System.out.println("first sentence type>"+firstSentence.getClass().getName()+"< coveredText>"+firstSentence.getCoveredText()+"<");

		System.out.println("first chunk type>"+firstChunk.getClass().getName()+"< coveredText>"+firstChunk.getCoveredText()+"<");
		System.out.println("fith chunk type>"+fourthChunk.getClass().getName()+"< coveredText>"+fifthChunk.getCoveredText()+"<");

		for (Annotation between : JCasUtil.selectBetween(testJcas, Token.class, firstChunk,fifthChunk)) {
			System.out.println("selectCovered type>"+between.getClass().getName()+"< coveredText>"+between.getCoveredText()+"<");
		}


		// measure the performance
		/* FTB sentence, chunk, token, pos feature
10  222 1348 10/10.txt
Ellapsed time 206 ms
AnnotationIndex 		size>252 231 704< size>369<
LocatedAnnotationIndex 	size>252 580 704< size>291<

50 1379 8421 50/50.txt
Debug: LocatedAnnotationIndexBuilder.buildsIndex 
Ellapsed time 2056 ms
AnnotationIndex size>252353728<bytes size>2226<
LocatedAnnotationIndex size>253674528<bytes size>1770<

 // computed without restarting the doc anal
Ellapsed time 4021 ms
AnnotationIndex size>350 211 432< size>2226<
LocatedAnnotationIndex size>351 375 352< size>1770< 

100  2618 15607 100/100.txt
Ellapsed time 6543 ms
AnnotationIndex 		size>252 479 912< size>4193<
LocatedAnnotationIndex 	size>254 831 928< size>3338<

200  4700 27539 200/200.txt
Debug: LocatedAnnotationIndexBuilder.buildsIndex 
Ellapsed time 20693 ms
AnnotationIndex size>252660496<bytes size>7537<
LocatedAnnotationIndex size>256764536<bytes size>6004<
		 */
		System.out.println("Debug: ---------------------------------------------------------------");
		System.out.println("Debug: LocatedAnnotationIndexBuilder.buildsIndex ");

		Date currentDate = DateUtilities.getNow(); 
		LocatedAnnotationIndex inputViewJCaslocatedAnnotationIndex;
		inputViewJCaslocatedAnnotationIndex = LocatedAnnotationIndexBuilder.buildsIndex(inputViewJCas);
		Date previousDate = currentDate;
		currentDate = DateUtilities.getNow();
		System.out.println("Ellapsed time "+ DateUtilities.dateDiff(previousDate,currentDate)+ " ms");

		//http://www.jroller.com/maxim/entry/again_about_determining_size_of
		// VM param -Xmx10140m -javaagent:/home/hernandez/workspace/uima-common/lib/sizeofag.jar
		// lib in the build path (currently in uima-common/lib)
		System.out.println("AnnotationIndex size>"+SizeOfAgent.fullSizeOf(inputViewJCas.getAnnotationIndex())+"<bytes size>"+inputViewJCas.getAnnotationIndex().size()+"<");
		System.out.println("LocatedAnnotationIndex size>"+SizeOfAgent.fullSizeOf(inputViewJCaslocatedAnnotationIndex)+"<bytes size>"+inputViewJCaslocatedAnnotationIndex.getOffsetList().size()+"<");
		//testLocatedAnnotationIndexParsing(inputViewJCaslocatedAnnotationIndex);

		// return a default value, because required, but the outputview should not be used
		return inputViewJCas.getDocumentText();
	}

	/**
	 * @param testJcas
	 * @param annotationIndex
	 * 
	 * Debug: ---------------------------------------------------------------
Debug: subiterating all the chunk annotations
annotation to subiterate type>common.types.text.Chunk< coveredText>Verne<
moveToNext subAnnotation type>org.apache.uima.examples.SourceDocumentInformation< coveredText><
annotation to subiterate type>common.types.text.Chunk< coveredText>visited<
annotation to subiterate type>common.types.text.Chunk< coveredText>the seaport of Nantes<
moveToNext subAnnotation type>common.types.text.Chunk< coveredText>the seaport<
moveToNext subAnnotation type>common.types.text.Token< coveredText>the<
moveToNext subAnnotation type>common.types.coref.Coreference< coveredText>the<
moveToNext subAnnotation type>common.types.text.Token< coveredText>seaport<
moveToNext subAnnotation type>common.types.coref.Coreference< coveredText>seaport<
moveToNext subAnnotation type>common.types.text.Chunk< coveredText>of Nantes<
moveToNext subAnnotation type>common.types.text.Token< coveredText>of<
moveToNext subAnnotation type>common.types.coref.Coreference< coveredText>of<
moveToNext subAnnotation type>common.types.text.Token< coveredText>Nantes<
moveToNext subAnnotation type>common.types.ne.NamedEntity< coveredText>Nantes<
moveToNext subAnnotation type>common.types.coref.Coreference< coveredText>Nantes<
annotation to subiterate type>common.types.text.Chunk< coveredText>the seaport<
moveToNext subAnnotation type>common.types.text.Token< coveredText>the<
moveToNext subAnnotation type>common.types.coref.Coreference< coveredText>the<
moveToNext subAnnotation type>common.types.text.Token< coveredText>seaport<
moveToNext subAnnotation type>common.types.coref.Coreference< coveredText>seaport<
annotation to subiterate type>common.types.text.Chunk< coveredText>of Nantes<
moveToNext subAnnotation type>common.types.text.Token< coveredText>of<
moveToNext subAnnotation type>common.types.coref.Coreference< coveredText>of<
moveToNext subAnnotation type>common.types.text.Token< coveredText>Nantes<
moveToNext subAnnotation type>common.types.ne.NamedEntity< coveredText>Nantes<
moveToNext subAnnotation type>common.types.coref.Coreference< coveredText>Nantes<
	 */
	private void testChunkSubiterator(JCas testJcas,
			AnnotationIndex<Annotation> annotationIndex) {
		System.out.println("Debug: ---------------------------------------------------------------");

		System.out.println("Debug: ambiguous subiterating all the chunk annotations");
		AnnotationIndex<Annotation> chunkAnnotationIndex = (AnnotationIndex<Annotation>) testJcas.getAnnotationIndex(Chunk.type);
		for (Annotation aChunk : chunkAnnotationIndex) {
			FSIterator<Annotation> chunkSubIterator = annotationIndex.subiterator(aChunk);
			System.out.println("annotation to subiterate type>"+aChunk.getClass().getName()+"< coveredText>"+aChunk.getCoveredText()+"<");

			while(chunkSubIterator.isValid()) {
				Annotation subAnnotation = chunkSubIterator.get();

				System.out.println("moveToNext subAnnotation type>"+subAnnotation.getClass().getName()+"< coveredText>"+subAnnotation.getCoveredText()+"<");
				chunkSubIterator.moveToNext();
			}
		}
	}

	private void testChunkUnambiguousStrictSubiterator(JCas testJcas,
			AnnotationIndex<Annotation> annotationIndex) {
		System.out.println("Debug: ---------------------------------------------------------------");

		System.out.println("Debug: unambiguous and strict subiterating all the chunk annotations");
		AnnotationIndex<Annotation> chunkAnnotationIndex = (AnnotationIndex<Annotation>) testJcas.getAnnotationIndex(Chunk.type);
		for (Annotation aChunk : chunkAnnotationIndex) {
			FSIterator<Annotation> chunkSubIterator = annotationIndex.subiterator(aChunk,false,true);
			System.out.println("annotation to subiterate type>"+aChunk.getClass().getName()+"< coveredText>"+aChunk.getCoveredText()+"<");

			while(chunkSubIterator.isValid()) {
				Annotation subAnnotation = chunkSubIterator.get();

				System.out.println("moveToNext subAnnotation type>"+subAnnotation.getClass().getName()+"< coveredText>"+subAnnotation.getCoveredText()+"<");
				chunkSubIterator.moveToNext();
			}
		}
	}

	/**
	 * @param inputViewJCas
	 * @return
	 */
	private JCas createAnAnnotatedJCas(JCas inputViewJCas) {
		String TEXT_MIME_TYPE = "text/plain";
		String testViewName= "_TestView";
		String testSofaDataString = "Verne visited the seaport of Nantes.\n";
		JCas testJcas = null;
		try {
			inputViewJCas.createView(testViewName);
			testJcas = inputViewJCas.getView(testViewName);
			testJcas.setSofaDataString(testSofaDataString, TEXT_MIME_TYPE );

			// created by default... make it explose the treeAnnotation
			/*DocumentAnnotation documentAnnotation = new DocumentAnnotation (testJcas);
			documentAnnotation.setBegin(0);
			documentAnnotation.setEnd(testSofaDataString.length());
			documentAnnotation.addToIndexes();*/

			SourceDocumentInformation sourceDocumentInformation = new SourceDocumentInformation (testJcas);
			sourceDocumentInformation.setBegin(0);
			sourceDocumentInformation.setEnd(0);
			sourceDocumentInformation.addToIndexes();


			Sentence sentence = new Sentence (testJcas);
			sentence.setBegin(0);
			sentence.setEnd(testSofaDataString.length()-1);
			sentence.addToIndexes();

			Token token = new Token (testJcas);	token.setBegin(0);	token.setEnd(5);token.addToIndexes(); 	// Verne
			token = new Token (testJcas); token.setBegin(6);token.setEnd(13);token.addToIndexes(); 			// visited
			token = new Token (testJcas); 	token.setBegin(14);	token.setEnd(17); token.addToIndexes(); 	// the
			token = new Token (testJcas); 	token.setBegin(18);	token.setEnd(25); token.addToIndexes(); 	// seaport
			token = new Token (testJcas); 	token.setBegin(26);	token.setEnd(28); token.addToIndexes(); 	// of
			token = new Token (testJcas); 	token.setBegin(29);	token.setEnd(35); token.addToIndexes(); 	// Nantes
			token = new Token (testJcas); 	token.setBegin(35);	token.setEnd(36); token.addToIndexes(); 	// .

			Noun noun = new Noun (testJcas);	noun.setBegin(0);	noun.setEnd(5);noun.addToIndexes(); 	// Verne
			noun = new Noun (testJcas); 	noun.setBegin(18);	noun.setEnd(25); noun.addToIndexes(); 	// seaport
			noun = new Noun (testJcas); 	noun.setBegin(29);	noun.setEnd(35); noun.addToIndexes(); 	// Nantes

			Verb verb = new Verb (testJcas); verb.setBegin(6);verb.setEnd(13);verb.addToIndexes(); 			// visited

			Coreference coref = new Coreference (testJcas);	coref.setBegin(0);	coref.setEnd(5);coref.addToIndexes(); 	// Verne
			coref = new Coreference (testJcas); coref.setBegin(6);coref.setEnd(13);coref.addToIndexes(); 			// visited
			coref = new Coreference (testJcas); 	coref.setBegin(14);	coref.setEnd(17); coref.addToIndexes(); 	// the
			coref = new Coreference (testJcas); 	coref.setBegin(18);	coref.setEnd(25); coref.addToIndexes(); 	// seaport
			coref = new Coreference (testJcas); 	coref.setBegin(26);	coref.setEnd(28); coref.addToIndexes(); 	// of
			coref = new Coreference (testJcas); 	coref.setBegin(29);	coref.setEnd(35); coref.addToIndexes(); 	// Nantes
			coref = new Coreference (testJcas); 	coref.setBegin(35);	coref.setEnd(36); coref.addToIndexes(); 	// .

			Chunk chunk = new Chunk (testJcas); chunk.setBegin(0); 	chunk.setEnd(5); chunk.addToIndexes();	// Verne
			chunk = new Chunk (testJcas); chunk.setBegin(6);chunk.setEnd(13);chunk.addToIndexes();			// visited
			chunk = new Chunk (testJcas); chunk.setBegin(14);chunk.setEnd(35);	chunk.addToIndexes();		// the seaport of Nantes
			chunk = new Chunk (testJcas); chunk.setBegin(14);chunk.setEnd(25);	chunk.addToIndexes();		// the seaport
			chunk = new Chunk (testJcas); chunk.setBegin(26);chunk.setEnd(35);	chunk.addToIndexes();		// of Nantes

			NamedEntity namedEntity = new NamedEntity (testJcas); namedEntity.setBegin(0); 	namedEntity.setEnd(5); namedEntity.addToIndexes();	// Verne
			namedEntity = new NamedEntity (testJcas); 	namedEntity.setBegin(29);	namedEntity.setEnd(35); namedEntity.addToIndexes(); 	// Nantes


		} catch (CASException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return testJcas;
	}

	/**
	 * @param annotationIndex
	 */
	private void testAnnotationTreeNavigation(
			AnnotationIndex<Annotation> annotationIndex) {
		FSIterator<Annotation> annotationIndexIterator;
		Annotation annotation;
		/*
		 * Debug: 
first annotation type>org.apache.uima.jcas.tcas.DocumentAnnotation< coveredText>Jean pousse la voiture.
<
Parent type>org.apache.uima.jcas.tcas.DocumentAnnotation< coveredText>Jean pousse la voiture.
<
--Child type>common.types.text.Sentence< coveredText>Jean pousse la voiture.<
--Parent type>common.types.text.Sentence< coveredText>Jean pousse la voiture.<
----Child type>common.types.text.Chunk< coveredText>Jean<
----Parent type>common.types.text.Chunk< coveredText>Jean<
------Child type>common.types.text.LexicalUnit< coveredText>Jean<
------Parent type>common.types.text.LexicalUnit< coveredText>Jean<
--------Child type>org.apache.uima.examples.SourceDocumentInformation< coveredText><
--------Parent type>org.apache.uima.examples.SourceDocumentInformation< coveredText><
----Child type>common.types.text.Chunk< coveredText>pousse<
----Parent type>common.types.text.Chunk< coveredText>pousse<
------Child type>common.types.text.LexicalUnit< coveredText>pousse<
------Parent type>common.types.text.LexicalUnit< coveredText>pousse<
----Child type>common.types.text.Chunk< coveredText>la voiture<
----Parent type>common.types.text.Chunk< coveredText>la voiture<
------Child type>common.types.text.LexicalUnit< coveredText>la<
------Parent type>common.types.text.LexicalUnit< coveredText>la<
------Child type>common.types.text.LexicalUnit< coveredText>voiture<
------Parent type>common.types.text.LexicalUnit< coveredText>voiture<
----Child type>common.types.text.LexicalUnit< coveredText>.<
----Parent type>common.types.text.LexicalUnit< coveredText>.<
Debug: ------------------------------------------------------------------
		 */
		System.out.println("Debug: ---------------------------------------------------------------");
		System.out.println("Debug: tree navigation");

		annotationIndexIterator = annotationIndex.iterator();
		annotationIndexIterator.moveToFirst();
		annotation = annotationIndexIterator.get();
		System.out.println("first annotation type>"+annotation.getClass().getName()+"< coveredText>"+annotation.getCoveredText()+"<");

		AnnotationTree<Annotation>	annotationTree = annotationIndex.tree(annotation);
		exploreAnnotationTreeNode(annotationTree.getRoot(),0);
	}

	/**
	 * exploreAnnotationTreeNode
	 * @param annotationTree
	 * @param depth
	 */
	private void exploreAnnotationTreeNode (AnnotationTreeNode<Annotation> annotationTree, Integer depth) {
		Annotation root = annotationTree.get();
		for (int i =0 ; i < depth ; i++) System.out.print("-");
		System.out.println("Parent type>"+root.getClass().getName()+"< coveredText>"+root.getCoveredText()+"<");

		Iterator<AnnotationTreeNode<Annotation>> annotationTreeIterator = annotationTree.getChildren().iterator();
		depth += 2;
		while (annotationTreeIterator.hasNext()) {
			AnnotationTreeNode<Annotation> child = annotationTreeIterator.next();
			for (int i =0 ; i < depth ; i++) System.out.print("-");
			//System.out.println("Child type>"+child.get().getClass().getName()+"< coveredText>"+child.get().getCoveredText()+"<");

			exploreAnnotationTreeNode(child,depth);
		}
	}

	/**
	 * @param inputViewJCas
	 * @param annotationIndexIterator
	 */
	private void testTypeSubsumptionConstrainedIterator(JCas inputViewJCas,
			FSIterator<Annotation> annotationIndexIterator) {
		Annotation annotation;
		// -- constrained iterator chunk which words occurs at the same offsets 
		System.out.println("Debug: ---------------------------------------------------------------");
		System.out.println("Debug: constrained iterator chunk which words occurs at the same offsets (do not work since none is the surtype of the other) ; works with tcas.annotation");
		// 
		// Start by getting the constraint factory from the CAS.
		ConstraintFactory cf = inputViewJCas.getConstraintFactory();

		// To specify a path to an item to test, you start by
		// creating an empty path.
		//FeaturePath path = cas.createFeaturePath();

		// Add POS feature to path, creating one-element path.
		//path.addFeature(posFeat);

		// You can extend the chain arbitrarily by adding additional
		// features.

		// Create a new type constraint.  

		// Type constraints will check that structures
		// they match against have a type at least as specific
		// as the type specified in the constraint.
		FSTypeConstraint chunkConstraint = cf.createTypeConstraint();

		// Set the type (by default it is TOP).
		// This succeeds if the type being tested by this constraint
		// is nounType or a subtype of nounType.
		chunkConstraint.add(inputViewJCas.getTypeSystem().getType("common.types.text.Chunk"));


		// Embed the noun constraint under the pos path.
		// This means, associate the test with the path, so it tests the
		// proper value.

		// The result is a test which will
		// match a feature structure that has a posFeat defined
		// which has a value which is an instance of a nounType or
		// one of its subtypes.
		//FSMatchConstraint embeddedNoun = cf.embedConstraint(path, chunkConstraint);

		// Create a type constraint for token (or a subtype of it)
		FSTypeConstraint uimaTCasAnnotationConstraint = cf.createTypeConstraint();

		// Set the type.
		uimaTCasAnnotationConstraint.add(inputViewJCas.getTypeSystem().getType("uima.tcas.Annotation"));

		// Create the final constraint by conjoining the two constraints.
		FSMatchConstraint chunkAndLexicalUnitCons = cf.and(chunkConstraint, uimaTCasAnnotationConstraint);

		// Create a filtered iterator from some annotation iterator.
		FSIterator uimaTCasAnnotationUnitIterator = inputViewJCas.createFilteredIterator(annotationIndexIterator, chunkAndLexicalUnitCons);

		uimaTCasAnnotationUnitIterator.moveToFirst();

		annotation = (Annotation) uimaTCasAnnotationUnitIterator.get();
		System.out.println("first chunkAndLexicalUnitIterator annotation type>"+annotation.getClass().getName()+"< coveredText>"+annotation.getCoveredText()+"<");
		uimaTCasAnnotationUnitIterator.moveToNext();

		while(uimaTCasAnnotationUnitIterator.isValid()) {
			annotation =  (Annotation) uimaTCasAnnotationUnitIterator.get();

			System.out.println("moveToNext chunkAndLexicalUnitIterator annotation type>"+annotation.getClass().getName()+"< coveredText>"+annotation.getCoveredText()+"<");
			uimaTCasAnnotationUnitIterator.moveToNext();
		}
	}


	/**
	 * @param inputViewJCas
	 * @param annotationIndexIterator
	 */
	private void testSelectSpecificTypesConstrainedIterator(JCas inputViewJCas,
			FSIterator<Annotation> annotationIndexIterator) {
		Annotation annotation;
		// -- constrained iterator chunk which words occurs at the same offsets 
		System.out.println("Debug: ---------------------------------------------------------------");
		System.out.println("Debug: constrained iterator over two types (noun and verb) without inheritance relations");

		// Start by getting the constraint factory from the CAS.
		ConstraintFactory cf = inputViewJCas.getConstraintFactory();

		// Create a new type constraint.  

		// Type constraints will check that structures
		// they match against have a type at least as specific
		// as the type specified in the constraint.
		FSTypeConstraint nounConstraint = cf.createTypeConstraint();

		// Set the type (by default it is TOP).
		// This succeeds if the type being tested by this constraint
		// is nounType or a subtype of nounType.
		nounConstraint.add(inputViewJCas.getTypeSystem().getType("common.types.text.Noun"));


		FSTypeConstraint verbConstraint = cf.createTypeConstraint();

		// Set the type (by default it is TOP).
		// This succeeds if the type being tested by this constraint
		// is nounType or a subtype of nounType.
		verbConstraint.add(inputViewJCas.getTypeSystem().getType("common.types.text.Verb"));



		// Create the final constraint by conjoining the two constraints.
		FSMatchConstraint nounOrVerbConstraint = cf.or(verbConstraint, nounConstraint);

		// Create a filtered iterator from some annotation iterator.
		FSIterator uimaTCasAnnotationUnitIterator = inputViewJCas.createFilteredIterator(annotationIndexIterator, nounOrVerbConstraint);

		uimaTCasAnnotationUnitIterator.moveToFirst();

		annotation = (Annotation) uimaTCasAnnotationUnitIterator.get();
		System.out.println("first nounOrVerbConstraint annotation type>"+annotation.getClass().getName()+"< coveredText>"+annotation.getCoveredText()+"<");
		uimaTCasAnnotationUnitIterator.moveToNext();

		while(uimaTCasAnnotationUnitIterator.isValid()) {
			annotation =  (Annotation) uimaTCasAnnotationUnitIterator.get();

			System.out.println("moveToNext nounOrVerbConstraint annotation type>"+annotation.getClass().getName()+"< coveredText>"+annotation.getCoveredText()+"<");
			uimaTCasAnnotationUnitIterator.moveToNext();
		}
	}


	/**
	 * @param annotationIndex
	 */
	private void testNonAmbiguousIterator(
			AnnotationIndex<Annotation> annotationIndex) {
		FSIterator<Annotation> annotationIndexIterator;
		Annotation annotation;
		System.out.println("Debug: ---------------------------------------------------------------");
		System.out.println("Debug: non-ambiguous iteration over an annotation index");
		annotationIndexIterator = annotationIndex.iterator(false);
		annotationIndexIterator.moveToFirst();
		annotation = annotationIndexIterator.get();
		System.out.println("first annotation coveredText>"+annotation.getCoveredText()+"< type>"+annotation.getClass().getName()+"< ");
		annotationIndexIterator.moveToNext();
		while(annotationIndexIterator.isValid()) {
			annotation = annotationIndexIterator.get();
			System.out.println("moveToNext annotation coveredText>"+annotation.getCoveredText()+"< type>"+annotation.getClass().getName()+"<");
			annotationIndexIterator.moveToNext();
		}
	}

	/**
	 * @param annotationIndex
	 * @param firstSentence
	 */
	private void testNonAmbiguousSubiterator(
			AnnotationIndex<Annotation> annotationIndex,
			Annotation firstSentence) {
		FSIterator<Annotation> annotationIndexIterator;
		Annotation annotation;
		System.out.println("Debug: ---------------------------------------------------------------");
		System.out.println("Debug: non-ambiguous subiterator over an annotation index");
		/**
		 * Debug: non-ambiguous subiterator over an annotation index
move to first sentence annotation coveredText>Jean pousse la voiture.< type>common.types.text.Sentence< 
moveToNext ambiguous annotationIndexSubiterator coveredText>Jean< type>common.types.text.Chunk<
moveToNext ambiguous annotationIndexSubiterator coveredText>Jean< type>common.types.text.LexicalUnit<
moveToNext ambiguous annotationIndexSubiterator coveredText>< type>org.apache.uima.examples.SourceDocumentInformation<
moveToNext ambiguous annotationIndexSubiterator coveredText>pousse< type>common.types.text.Chunk<
moveToNext ambiguous annotationIndexSubiterator coveredText>pousse< type>common.types.text.LexicalUnit<
moveToNext ambiguous annotationIndexSubiterator coveredText>la voiture< type>common.types.text.Chunk<
moveToNext ambiguous annotationIndexSubiterator coveredText>la< type>common.types.text.LexicalUnit<
moveToNext ambiguous annotationIndexSubiterator coveredText>voiture< type>common.types.text.LexicalUnit<
moveToNext ambiguous annotationIndexSubiterator coveredText>.< type>common.types.text.LexicalUnit<
move to first sentence annotation coveredText>Jean pousse la voiture.< type>common.types.text.Sentence< 
moveToNext non ambiguous annotationIndexSubiterator coveredText>Jean< type>common.types.text.Chunk<
moveToNext non ambiguous annotationIndexSubiterator coveredText>pousse< type>common.types.text.Chunk<
moveToNext non ambiguous annotationIndexSubiterator coveredText>la voiture< type>common.types.text.Chunk<
moveToNext non ambiguous annotationIndexSubiterator coveredText>.< type>common.types.text.LexicalUnit<
		 */
		annotationIndexIterator = annotationIndex.iterator();

		annotationIndexIterator.moveTo(firstSentence);
		annotation = annotationIndexIterator.get();
		System.out.println("move to first sentence annotation coveredText>"+annotation.getCoveredText()+"< type>"+annotation.getClass().getName()+"< ");

		FSIterator<Annotation> annotationIndexSubiterator = annotationIndex.subiterator(annotation);
		while(annotationIndexSubiterator.isValid()) {
			annotation = annotationIndexSubiterator.get();
			System.out.println("moveToNext ambiguous annotationIndexSubiterator coveredText>"+annotation.getCoveredText()+"< type>"+annotation.getClass().getName()+"<");
			annotationIndexSubiterator.moveToNext();
		}


		annotationIndexIterator.moveTo(firstSentence);
		annotation = annotationIndexIterator.get();
		System.out.println("move to first sentence annotation coveredText>"+annotation.getCoveredText()+"< type>"+annotation.getClass().getName()+"< ");
		annotationIndexSubiterator = annotationIndex.subiterator(annotation,false,true);
		while(annotationIndexSubiterator.isValid()) {
			annotation = annotationIndexSubiterator.get();
			System.out.println("moveToNext non ambiguous annotationIndexSubiterator coveredText>"+annotation.getCoveredText()+"< type>"+annotation.getClass().getName()+"<");
			annotationIndexSubiterator.moveToNext();
		}
	}

	/**
	 * @param inputViewJCas
	 * @param firstChunk
	 */
	private void testNonAmbiguousIteratorOverAnIndexOfAGivenType(JCas inputViewJCas,
			Annotation firstChunk) {
		AnnotationIndex<Annotation> annotationIndex;
		FSIterator<Annotation> annotationIndexIterator;
		Annotation annotation;
		System.out.println("Debug: ---------------------------------------------------------------");
		System.out.println("Debug: moveTo+ non-ambiguous iteration over an annotation index (of Chunk.type)");
		annotationIndex = (AnnotationIndex<Annotation>) inputViewJCas.getAnnotationIndex(Chunk.type);

		annotationIndexIterator = annotationIndex.iterator(false);
		annotationIndexIterator.moveTo(firstChunk);
		annotation = annotationIndexIterator.get();
		System.out.println("moveTo annotation (first chunk firstly met) coveredText>"+annotation.getCoveredText()+"< type>"+annotation.getClass().getName()+"< ");
		annotationIndexIterator.moveToNext();
		while(annotationIndexIterator.isValid()) {
			annotation = annotationIndexIterator.get();
			System.out.println("moveToNext annotation coveredText>"+annotation.getCoveredText()+"< type>"+annotation.getClass().getName()+"<");
			annotationIndexIterator.moveToNext();
		}
	}

	/**
	 * @param inputViewJCas
	 */
	private void testNonAmbiguousIteratorInReverseOrder(JCas inputViewJCas) {
		AnnotationIndex<Annotation> annotationIndex;
		FSIterator<Annotation> annotationIndexIterator;
		Annotation annotation;
		System.out.println("Debug: ---------------------------------------------------------------");
		System.out.println("Debug: create word at the same offset + moveToFirst then moveToNext + moveToLast+ reverse non-ambiguous iteration over an annotation index (of LexicalUnit.type) + ambiguous" );

		// 12 22 covering multiple lexical unit exactly
		// 12 20 partial covering but with begin exact
		LexicalUnit newLexicalUnit = new LexicalUnit(inputViewJCas);
		newLexicalUnit.setBegin(12);
		newLexicalUnit.setEnd(20);
		inputViewJCas.addFsToIndexes(newLexicalUnit);

		/*
		 // to determiner the effect of a late add to index 
		 // result observed indicates that has no effect, takes always the most covering 
		 * Debug: create word at the same offset + moveToFirst then moveToNext + moveToLast+ reverse non-ambiguous iteration over an annotation index (of LexicalUnit.type) + ambiguous
moveTo first coveredText>Jean< type>common.types.text.LexicalUnit< 
moveToNext annotation coveredText>pousse< type>common.types.text.LexicalUnit<
moveToNext annotation coveredText>la voiture< type>common.types.text.LexicalUnit<
moveToNext annotation coveredText>.< type>common.types.text.LexicalUnit<
moveTo last coveredText>.< type>common.types.text.LexicalUnit< 
moveToPrevious annotation coveredText>la voiture< type>common.types.text.LexicalUnit<
moveToPrevious annotation coveredText>pousse< type>common.types.text.LexicalUnit<
moveToPrevious annotation coveredText>Jean< type>common.types.text.LexicalUnit<
moveTo first coveredText>Jean< type>common.types.text.LexicalUnit< 
moveToNext annotation coveredText>pousse< type>common.types.text.LexicalUnit<
moveToNext annotation coveredText>po< type>common.types.text.LexicalUnit<
moveToNext annotation coveredText>usse< type>common.types.text.LexicalUnit<
moveToNext annotation coveredText>la voiture< type>common.types.text.LexicalUnit<
moveToNext annotation coveredText>la< type>common.types.text.LexicalUnit<
moveToNext annotation coveredText>voiture< type>common.types.text.LexicalUnit<
moveToNext annotation coveredText>.< type>common.types.text.LexicalUnit<
moveTo last coveredText>.< type>common.types.text.LexicalUnit< 
moveToPrevious annotation coveredText>voiture< type>common.types.text.LexicalUnit<
moveToPrevious annotation coveredText>la< type>common.types.text.LexicalUnit<
moveToPrevious annotation coveredText>la voiture< type>common.types.text.LexicalUnit<
moveToPrevious annotation coveredText>usse< type>common.types.text.LexicalUnit<
moveToPrevious annotation coveredText>po< type>common.types.text.LexicalUnit<
moveToPrevious annotation coveredText>pousse< type>common.types.text.LexicalUnit<
moveToPrevious annotation coveredText>Jean< type>common.types.text.LexicalUnit<
		 */
		/*

		  newLexicalUnit = new LexicalUnit(inputViewJCas);
		newLexicalUnit.setBegin(5);
		newLexicalUnit.setEnd(7);
		inputViewJCas.addFsToIndexes(newLexicalUnit);

		newLexicalUnit = new LexicalUnit(inputViewJCas);
		newLexicalUnit.setBegin(7);
		newLexicalUnit.setEnd(11);
		inputViewJCas.addFsToIndexes(newLexicalUnit);*/

		// overlapping LexicalUnit with identical end 3 11
		// fully overlapping LexicalUnit  3 7
		// non ambiguous lexical
		/*LexicalUnit newLexicalUnit = new LexicalUnit(inputViewJCas);
		newLexicalUnit.setBegin(3);
		newLexicalUnit.setEnd(11);
		inputViewJCas.addFsToIndexes(newLexicalUnit);*/

		annotationIndex = (AnnotationIndex<Annotation>) inputViewJCas.getAnnotationIndex(LexicalUnit.type);

		annotationIndexIterator = annotationIndex.iterator(false);

		annotationIndexIterator.moveToFirst();
		annotation = annotationIndexIterator.get();
		System.out.println("moveTo first coveredText>"+annotation.getCoveredText()+"< type>"+annotation.getClass().getName()+"< ");
		annotationIndexIterator.moveToNext();
		while(annotationIndexIterator.isValid()) {
			annotation = annotationIndexIterator.get();
			System.out.println("moveToNext annotation coveredText>"+annotation.getCoveredText()+"< type>"+annotation.getClass().getName()+"<");
			annotationIndexIterator.moveToNext();
		}

		annotationIndexIterator.moveToLast();
		annotation = annotationIndexIterator.get();
		System.out.println("moveTo last coveredText>"+annotation.getCoveredText()+"< type>"+annotation.getClass().getName()+"< ");
		annotationIndexIterator.moveToPrevious();
		while(annotationIndexIterator.isValid()) {
			annotation = annotationIndexIterator.get();
			System.out.println("moveToPrevious annotation coveredText>"+annotation.getCoveredText()+"< type>"+annotation.getClass().getName()+"<");
			annotationIndexIterator.moveToPrevious();
		}

		annotationIndexIterator = annotationIndex.iterator();

		annotationIndexIterator.moveToFirst();
		annotation = annotationIndexIterator.get();
		System.out.println("moveTo first coveredText>"+annotation.getCoveredText()+"< type>"+annotation.getClass().getName()+"< ");
		annotationIndexIterator.moveToNext();
		while(annotationIndexIterator.isValid()) {
			annotation = annotationIndexIterator.get();
			System.out.println("moveToNext annotation coveredText>"+annotation.getCoveredText()+"< type>"+annotation.getClass().getName()+"<");
			annotationIndexIterator.moveToNext();
		}

		annotationIndexIterator.moveToLast();
		annotation = annotationIndexIterator.get();
		System.out.println("moveTo last coveredText>"+annotation.getCoveredText()+"< type>"+annotation.getClass().getName()+"< ");
		annotationIndexIterator.moveToPrevious();
		while(annotationIndexIterator.isValid()) {
			annotation = annotationIndexIterator.get();
			System.out.println("moveToPrevious annotation coveredText>"+annotation.getCoveredText()+"< type>"+annotation.getClass().getName()+"<");
			annotationIndexIterator.moveToPrevious();
		}

		newLexicalUnit.removeFromIndexes();
	}



	/**
	 * @param annotationIndex
	 */
	private void testNonAmbiguousIteratorWhereSomeAnnotationsAreConcurrent(
			AnnotationIndex<Annotation> annotationIndex) {
		FSIterator<Annotation> annotationIndexIterator;
		Annotation annotation;
		System.out.println("Debug: ---------------------------------------------------------------");
		System.out.println("Debug: non-ambiguous iteration over an annotation index where only concurrent annotations are kept");


		System.out.println("Debug: removing some annotations (org.apache.uima.jcas.tcas.DocumentAnnotation and common.types.text.Sentence)");
		annotationIndexIterator = annotationIndex.iterator();
		annotationIndexIterator.moveToFirst();
		annotation = annotationIndexIterator.get();
		List<Annotation> annotationToRemoveList = new ArrayList<Annotation>();
		annotationToRemoveList.add(annotation);
		System.out.println("will remove annotation coveredText>"+annotation.getCoveredText()+"< type>"+annotation.getClass().getName()+"<");	


		annotationIndexIterator.moveToNext();
		while(annotationIndexIterator.isValid()) {
			annotation = annotationIndexIterator.get();
			if (annotation.getClass().getName().equalsIgnoreCase("org.apache.uima.jcas.tcas.DocumentAnnotation") 
					|| annotation.getClass().getName().equalsIgnoreCase("common.types.text.Sentence")) {
				annotationToRemoveList.add(annotation);
				System.out.println("will remove annotation coveredText>"+annotation.getCoveredText()+"< type>"+annotation.getClass().getName()+"<");	
			} else {
				System.out.println("will keep   annotation coveredText>"+annotation.getCoveredText()+"< type>"+annotation.getClass().getName()+"<");	
			}

			annotationIndexIterator.moveToNext();
		}

		for (Annotation annotationToRemove : annotationToRemoveList) {
			annotationToRemove.removeFromIndexes();
		}
		//annotationIndex = (AnnotationIndex<Annotation>) inputViewJCas.getAnnotationIndex();

		System.out.println("Debug: non ambiguous iteration  ");
		annotationIndexIterator = annotationIndex.iterator(false);
		annotationIndexIterator.moveToFirst();
		annotation = annotationIndexIterator.get();
		System.out.println("moveToFirst annotation coveredText>"+annotation.getCoveredText()+"< type>"+annotation.getClass().getName()+"<");

		annotationIndexIterator.moveToNext();
		while(annotationIndexIterator.isValid()) {
			annotation = annotationIndexIterator.get();
			System.out.println("moveToNext annotation coveredText>"+annotation.getCoveredText()+"< type>"+annotation.getClass().getName()+"<");
			annotationIndexIterator.moveToNext();
		}



		for (Annotation annotationToRemove : annotationToRemoveList) {
			annotationToRemove.addToIndexes();
		}
	}

	/**
	 * Test parsing of LocatedAnnotationIndex
	 * parse of the locatedAnnotationMap  index 
	 * and displays all the current annotations present at each couple of distinct offsets 
	 */
	private final void testLocatedAnnotationIndexParsing(LocatedAnnotationIndex locatedAnnotationIndex) {

		System.out.println("Debug: ------------------------------------------------------------------");
		System.out.println("Debug: parse LocatedAnnotationIndex and display the current annotations "); 

		for (String offset : locatedAnnotationIndex.getOffsetSet()) {
			System.out.println(locatedAnnotationIndex.getLocatedAnnotation(offset)); 
		}
	}


	/**
	 * Test parsing of LocatedAnnotationIndex
	 * parse of the locatedAnnotationMap sub index 
	 * and displays vicinity annotations present at each couple of distinct offsets
	 * 
	 */
	private final void testLocatedAnnotationIndexParsingDisplayingMoreVicinityInformation(LocatedAnnotationIndex locatedAnnotationIndex) {

		System.out.println("Debug: ------------------------------------------------------------------");
		System.out.println("Debug: parse LocatedAnnotationIndex and display the current and the vicinity annotations "); 

		for (String offset : locatedAnnotationIndex.getOffsetSet()) {
			System.out.println(locatedAnnotationIndex.getLocatedAnnotation(offset).currentAndVicinityToString()); 
		}
	}






	/**
	 * Test get first/last LocatedAnnotation containing a given annotation type name
	 */
	private final void testParsingTheIndexAndRelativelyFromLocatedAnnotation(LocatedAnnotationIndexInterface locatedAnnotationIndex) {



		System.out.println("Debug: -------------------------------------------------------------------");
		System.out.println("Debug: getting the first lexical unit by various approach");

		LocatedAnnotation firstLocatedAnnotation = locatedAnnotationIndex.getFirst();
		System.out.println("Debug: First LocatedAnnotation in the index"+firstLocatedAnnotation);

		String annotationTypeName = "common.types.text.LexicalUnit";
		LocatedAnnotation followingLocatedAnnotationOfAGivenType = firstLocatedAnnotation.getFirstIndexFollowing(annotationTypeName);
		System.out.println("Debug: first Following LocatedAnnotation in the index with the type >"+annotationTypeName+"< from first LocatedAnnotation in the index: >"+followingLocatedAnnotationOfAGivenType.getCoveredText()+"<");

		firstLocatedAnnotation = locatedAnnotationIndex.getFirst(annotationTypeName);
		System.out.println("Debug: First LocatedAnnotation in the index with the type >"+annotationTypeName+"< :>"+firstLocatedAnnotation+"<");



		System.out.println("Debug: -------------------------------------------------------------------");
		System.out.println("Debug: parsing the lexical unit in right order and displaying Ancestors");


		int i = 0;
		while (followingLocatedAnnotationOfAGivenType != null) {
			System.out.println("Debug: firstFollowing LocatedAnnotation "+i+" with the type >"+annotationTypeName+"<  >"+followingLocatedAnnotationOfAGivenType.getCoveredText()+"<");

			annotationTypeName = "common.types.text.Chunk";
			LocatedAnnotationInterface superLocatedAnnotationOfAGivenType = followingLocatedAnnotationOfAGivenType.getFirstAncestorByIndex(annotationTypeName);
			if (superLocatedAnnotationOfAGivenType != null) 
				System.out.println("Debug: getFirstAncestorByIndex LocatedAnnotation "+i+" with the type >"+annotationTypeName+"< >"+superLocatedAnnotationOfAGivenType.getCoveredText()+"<");

			superLocatedAnnotationOfAGivenType = followingLocatedAnnotationOfAGivenType.getFirstAncestorByContiguous(annotationTypeName);
			if (superLocatedAnnotationOfAGivenType != null) 
				System.out.println("Debug: getFirstAncestorByContiguous LocatedAnnotation "+i+" with the type >"+annotationTypeName+"< >"+superLocatedAnnotationOfAGivenType.getCoveredText()+"<");

			LocatedAnnotationInterface ancestorOrSelfLocatedAnnotationOfAGivenType = followingLocatedAnnotationOfAGivenType.getFirstAncestorOrSelfByIndex(annotationTypeName);
			if (ancestorOrSelfLocatedAnnotationOfAGivenType != null) 
				System.out.println("Debug: getFirstAncestorOrSelfByIndex LocatedAnnotation "+i+" with the type >"+annotationTypeName+"<  >"+ancestorOrSelfLocatedAnnotationOfAGivenType.getCoveredText()+"<");


			annotationTypeName = "common.types.text.LexicalUnit";
			followingLocatedAnnotationOfAGivenType = followingLocatedAnnotationOfAGivenType.getFirstIndexFollowing(annotationTypeName);
			i++;
		}

		System.out.println("Debug: -------------------------------------------------------------------");
		System.out.println("Debug: the lexical units descendants of the first sentence");

		annotationTypeName = "common.types.text.Sentence";
		LocatedAnnotation locatedAnnotationOfAGivenType = locatedAnnotationIndex.getFirst().getFirstIndexFollowing(annotationTypeName);
		System.out.println("Debug: getFirstIndexFollowing with the type >"+annotationTypeName+"<  >"+locatedAnnotationOfAGivenType.getCoveredText()+"<");


		annotationTypeName = "common.types.text.LexicalUnit";

		List<LocatedAnnotation> descendantLocatedAnnotationOfAGivenType = locatedAnnotationOfAGivenType.getDescendant(annotationTypeName);
		if (descendantLocatedAnnotationOfAGivenType.isEmpty()) 	System.out.println("Debug: getDescendant is empty<");

		for (LocatedAnnotation descendant : descendantLocatedAnnotationOfAGivenType) {
			System.out.println("Debug: getDescendant LocatedAnnotation  with the type >"+annotationTypeName+"<  >"+descendant.getCoveredText()+"<");

		}


		System.out.println("Debug: -------------------------------------------------------------------");
		System.out.println("Debug: parsing the chunk in reverse order");

		LocatedAnnotation lastLocatedAnnotation = locatedAnnotationIndex.getLast();
		System.out.println("Debug: Last LocatedAnnotation "+lastLocatedAnnotation);

		annotationTypeName = "common.types.text.Chunk";
		LocatedAnnotation previousLocatedAnnotationOfAGivenType = lastLocatedAnnotation.getFirstIndexPreceding(annotationTypeName);

		while (previousLocatedAnnotationOfAGivenType != null) {
			System.out.println("Debug: previous LocatedAnnotation "+i+" with the type >"+annotationTypeName+"<  >"+previousLocatedAnnotationOfAGivenType.getCoveredText()+"<");	
			previousLocatedAnnotationOfAGivenType = previousLocatedAnnotationOfAGivenType.getFirstIndexPreceding(annotationTypeName);
			i--;
		}

	}






}





