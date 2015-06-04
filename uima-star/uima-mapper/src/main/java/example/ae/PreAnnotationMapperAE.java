/** 
 * UIMA Type Mapper
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

package example.ae;

import java.util.*;
import org.apache.uima.*;
import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.cas.FSIndex;
import org.apache.uima.cas.FSIterator;
import org.apache.uima.cas.text.AnnotationIndex;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.cas.FSArray;
import org.apache.uima.jcas.cas.StringArray;


import org.apache.uima.TokenAnnotation;
//import org.apache.uima.SentenceAnnotation;
import example.types.source.DiscourseSegment;
import example.types.source.POS;
import example.types.source.Sentence;
import example.types.source.Word;

/**
 * Class illustrating an AE which produces some types as OUTPUT
 * <ul>
 * <li> 
 * </ul>
 * 
 * @author      Nicolas Hernandez
 * @version     %I%, %G%
 * @since       1.0
 */
public class PreAnnotationMapperAE extends JCasAnnotator_ImplBase {


	private static final int UNDEFINED = -1; 

	/**
	 * <ul>
	 * <li> On crée une annotation POS aux mêmes offsets que TokenAnnotation et
	 * on lui ajoute comme valeur de sa feature value la valeur de la feature 
	 * posTag de TokenAnnotation
	 * 
	 * <li> On crée une annotation Word pour chaque TokenAnnotation, et on lui 
	 * ajoute comme features, le nombre de caractères entre les offset de la
	 * TokenAnnotation, setSizeInt, la chaîne de caractères posString qui 
	 * correspond la valeur de la feature posTag de TokenAnnotation, ainsi 
	 * qu'un type complexe de type POS qui a pour valeur sur le POS aux mêmes 
	 * offsets
	 * 
	 * <li> Pour chaque SentenceAnnotation on crée une Sentence avec comme
	 * feature complexe un StringArray avec tous posTag de TokenAnnotation 
	 * comme valeurs les et un FSArray de tous les Word qu'elle subsume
	 * 
	 * <li> On crée une annotation DiscourseSegment qui englobe de séquences de 
	 * phrases Sentence séparées par des sauts de lignes, caractère NEWLINES
	 * S'inspire de 
	 * http://svn.apache.org/viewvc/uima/sandbox/trunk/WhitespaceTokenizer/src/main/java/org/apache/uima/annotator/WhitespaceTokenizer.java?view=markup
	 * 
	 * </ul>
	 */
	public void process(JCas aJCas) throws AnalysisEngineProcessException {

		FSIndex tokenAnnotationFSIdx = aJCas.getAnnotationIndex(TokenAnnotation.type);
		Iterator tokenAnnotationIter = tokenAnnotationFSIdx.iterator();   
		
		//---------------------------------------------------------------------
		// Pour chaque TokenAnnotation on crée aux mêmes offsets un POS, un 
		// Word, ainsi que pour POS une feature simple value et pour Word les 
		// features simples sizeInt, posString et complexe posType qui admet un 
		// objet POS aux mêmes offsets
		while (tokenAnnotationIter.hasNext()) {
			TokenAnnotation tokenAnnotation = (TokenAnnotation)tokenAnnotationIter.next();

			POS pos = new POS(aJCas);
			//pos.addToIndexes();
			pos.setBegin(tokenAnnotation.getBegin());
			pos.setEnd(tokenAnnotation.getEnd());
			pos.setValue(tokenAnnotation.getPosTag());
			pos.addToIndexes();

			Word word = new Word(aJCas);
			word.setBegin(tokenAnnotation.getBegin());
			word.setEnd(tokenAnnotation.getEnd());
			word.setSizeInt(tokenAnnotation.getEnd() - tokenAnnotation.getBegin());
			word.setPosType(pos);
			word.setPosString(tokenAnnotation.getPosTag());
			word.addToIndexes();
		}

		//---------------------------------------------------------------------
		// Pour chaque SentenceAnnotation on test si le caractère suivant est une nouvelle ligne
		// si oui ou si fin du text alors on crée une annotation DiscourseSegment
		//
		FSIndex sentenceFSIdx = aJCas.getAnnotationIndex(SentenceAnnotation.type);
		Iterator sentenceIter = sentenceFSIdx.iterator();   

		char[] textContent = aJCas.getDocumentText().toCharArray(); 
		int discourseSegmentBegin = 0;
		int discourseSegmentEnd = 0;
		int sentencePos=0;

		// Algo: Pour chaque phrase on regarde si elle ferme un segment et par conséquent en débute un nouveau
		// Pour toutes les phrases exceptées la 1ère 
		// Attention ! J'ai observé que la fin de la dernière phrase ne coïncide pas toujours à la fin du texte. 
		while (sentenceIter.hasNext()) {
			SentenceAnnotation sentence = (SentenceAnnotation)sentenceIter.next();

			int beginCharPos= sentence.getBegin();
			char beginChar = textContent[beginCharPos];
			System.out.println("Debug: beginChar=" + beginChar +"< beginCharOffset="+ beginCharPos +"<");

			// Si on n'est pas la 1ère phrase 
			if (sentencePos != 0) {

				// Si la phrase courante débute par un caractère de fin de segment 
				if ( (Character.getType(beginChar) == Character.PARAGRAPH_SEPARATOR) ||  
						(Character.getType(beginChar) == Character.LINE_SEPARATOR) ||
						(beginChar == '\n') || 
						(beginChar == '\r')
				) { 
					// Création d'un discours segment pour la partie de texte couverte jusqu'à présent
					DiscourseSegment discourseSegment = new DiscourseSegment(aJCas);
					discourseSegment.setBegin(discourseSegmentBegin);
					discourseSegment.setEnd(discourseSegmentEnd);
					discourseSegment.addToIndexes();

					// définition de future discourseSegmentStart
					discourseSegmentBegin = beginCharPos;
				}

			}	

			// Incrément du rang des phrases
			sentencePos++;

			// Position de fin possible d'un segment futur 
			discourseSegmentEnd=sentence.getEnd();
		}
		// Fermeture du dernier segment  toujours en cours
		// Création d'un discours segment pour la partie de texte couverte jusqu'à présent
		DiscourseSegment discourseSegment = new DiscourseSegment(aJCas);
		discourseSegment.setBegin(discourseSegmentBegin);
		discourseSegment.setEnd(discourseSegmentEnd);
		discourseSegment.addToIndexes();

		//---------------------------------------------------------------------
		// Pour chaque SentenceAnnotation on crée une Sentence avec comme 
		// feature complexe un StringArray avec tous posTag de TokenAnnotation 
		// comme valeurs les et un FSArray de tous les Word qu'elle subsume

		// Parcours-des-annotations-couvertes-par-une-autre-annotation
		// http://www.fabienpoulard.info/index.php?post/2010/04/01/Parcours-des-annotations-couvertes-par-une-autre-annotation

		// Récupération des index
		AnnotationIndex sentenceAnnotationAnnIdx = (AnnotationIndex) aJCas.getAnnotationIndex(SentenceAnnotation.type);
		AnnotationIndex wordAnnIdx = (AnnotationIndex) aJCas.getAnnotationIndex(Word.type);	
		// 
		FSIterator sentenceAnnotationFSIter = sentenceAnnotationAnnIdx.iterator();

		while (sentenceAnnotationFSIter.hasNext()) {

			SentenceAnnotation sentenceAnnotation = (SentenceAnnotation) sentenceAnnotationFSIter.next();

			// On récupére l'itérateur sur les annotations Word couvertes par SentenceAnnotation
			FSIterator wordSubSentenceAnnotationFSIter = wordAnnIdx.subiterator(sentenceAnnotation);

			//-----------------------------------------------------------------
			// Comment-utiliser-un-type-dont-un-attribut-est-un-tableau-de-types
			// http://www.fabienpoulard.info/index.php?post/2009/07/30/UIMA-:-Comment-utiliser-un-type-dont-un-attribut-est-un-tableau-de-types

			// On récupère dans un tableau ArrayList les word pointés par l'itérateur
			ArrayList<Word> wordArrayLst = new ArrayList<Word>();
			while (wordSubSentenceAnnotationFSIter.hasNext()) {
				// On récupére successivement les différents Word
				Word word = (Word) wordSubSentenceAnnotationFSIter.next();
				//System.out.println("Debug: word features "+word);
				wordArrayLst.add(word);
			}

			// On transforme l'ArrayList en tableau "statique"
			Word[] wordStaticArray = wordArrayLst.toArray(new Word[wordArrayLst.size()]);

			// On crée le FSArray de word et on copie le contenu du tableau statique en son sein
			FSArray wordFSArray = new FSArray(aJCas, wordStaticArray.length);
			wordFSArray.copyFromArray(wordStaticArray, 0, 0, wordStaticArray.length);

			// On crée une instance du type complexe qui encapsule l'FSArray et on lui ajoute
			Sentence sentence = new Sentence(aJCas);
			sentence.setWordArray(wordFSArray);

			//-----------------------------------------------------------------
			// Comment constituer un tableau de type primitif ici StringArray
			String[] stringStaticArray = new String[wordStaticArray.length];
			for (int i = 0; i < wordStaticArray.length; i++) {
				Word word = wordStaticArray[i];
				stringStaticArray[i] = word.getPosString();
			} 

			StringArray stringArray =  new StringArray(aJCas, wordStaticArray.length);
			stringArray.copyFromArray(stringStaticArray, 0, 0, wordStaticArray.length);
			sentence.setStringArray(stringArray);

			//-----------------------------------------------------------------
			sentence.setBegin(sentenceAnnotation.getBegin());
			sentence.setEnd(sentenceAnnotation.getEnd());

			//-----------------------------------------------------------------
			sentence.addToIndexes();

		}

		//---------------------------------------------------------------------
		// Pour chaque DiscourseSegment on ajoute un FSArray de tous les 
		// Sentence qu'il subsume

		// Parcours-des-annotations-couvertes-par-une-autre-annotation
		// http://www.fabienpoulard.info/index.php?post/2010/04/01/Parcours-des-annotations-couvertes-par-une-autre-annotation

		// Récupération des index
		AnnotationIndex discourseSegmentAnnIdx = (AnnotationIndex) aJCas.getAnnotationIndex(DiscourseSegment.type);
		AnnotationIndex sentenceAnnIdx = (AnnotationIndex) aJCas.getAnnotationIndex(Sentence.type);	
		// 
		FSIterator discourseSegmentFSIter = discourseSegmentAnnIdx.iterator();

		while (discourseSegmentFSIter.hasNext()) {

			DiscourseSegment aDiscourseSegment = (DiscourseSegment) discourseSegmentFSIter.next();

			// On récupére l'itérateur sur les annotations Word couvertes par DiscourseSegment
			FSIterator sentenceSubDiscourseSegmentFSIter = sentenceAnnIdx.subiterator(aDiscourseSegment);

			//-----------------------------------------------------------------
			// Comment-utiliser-un-type-dont-un-attribut-est-un-tableau-de-types
			// http://www.fabienpoulard.info/index.php?post/2009/07/30/UIMA-:-Comment-utiliser-un-type-dont-un-attribut-est-un-tableau-de-types

			// On récupère dans un tableau ArrayList les sentence pointés par l'itérateur
			ArrayList<Sentence> sentenceArrayLst = new ArrayList<Sentence>();
			while (sentenceSubDiscourseSegmentFSIter.hasNext()) {
				// On récupére successivement les différents Word
				Sentence sentence = (Sentence) sentenceSubDiscourseSegmentFSIter.next();
				//System.out.println("Debug: sentence features "+sentence);
				sentenceArrayLst.add(sentence);
			}

			// On transforme l'ArrayList en tableau "statique"
			Sentence[] sentenceStaticArray = sentenceArrayLst.toArray(new Sentence[sentenceArrayLst.size()]);

			// On crée le FSArray de sentence et on copie le contenu du tableau statique en son sein
			FSArray sentenceFSArray = new FSArray(aJCas, sentenceStaticArray.length);
			sentenceFSArray.copyFromArray(sentenceStaticArray, 0, 0, sentenceStaticArray.length);

			//-----------------------------------------------------------------
			// On  ajoute le FSArray
			aDiscourseSegment.setSentenceArray(sentenceFSArray);
			//aDiscourseSegment.addToIndexes();

		}

	}

}
