/** 
 * Copyright (C) 2015  Nicolas Hernandez
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
package fr.univnantes.lina.mlnlp.process.disambiguation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import edu.berkeley.nlp.lm.ArrayEncodedProbBackoffLm;

/**
 * @author hernandez
 *
 */
public class DisambiguationUtil {


	/**
	 * generate a list of alternative sentences (each of them is a list of words)
	 * from an ambiguous sentence (list of 1 or several words (i.e. list of string) at each position)
	 * 
	 * WARNING: high cost in processing time !!! 
	 * 
	 * @param ambiguousSentence
	 * @return
	 */
	public static List<List<String>> generateAlternativeSentences(List<List<String>> ambiguousSentence) {
		List<List<String>> alternativeSentences = new ArrayList<List<String>>();
		int wordPos = 0;
		
		//
		if (wordPos < ambiguousSentence.size()) {
			for (String wordCandidate : ambiguousSentence.get(wordPos)) {
				List<String> nextCandidateSentence = new ArrayList<String> ();
				nextCandidateSentence.add(wordCandidate);
				generateAlternativeSentences(ambiguousSentence, wordPos+1, nextCandidateSentence,alternativeSentences);
			}
		}
		//System.out.println("Debug: end of public generateAlternativeSentences");
		return alternativeSentences;
	}
	
	/**
	 * @see 	public static List<List<String>> generateAlternativeSentences(List<List<String>> ambiguousSentence);
	 * @param ambiguousSentence
	 * @param wordPos
	 * @param candidateSentence
	 * @param possibleSentences
	 * @return
	 */
	private static List<String> generateAlternativeSentences(List<List<String>> ambiguousSentence, int wordPos, List<String> candidateSentence, List<List<String>> possibleSentences) {
		if (wordPos < ambiguousSentence.size()) {
			for (String wordCandidate : ambiguousSentence.get(wordPos)) {
				//System.out.printf("Debug: wordCandidate %s wordPos %d\n", wordCandidate, wordPos);
				List<String> nextCandidateSentence = new ArrayList<String> (candidateSentence);
				nextCandidateSentence.add(wordCandidate);
				List<String> sentenceVariation = 
						generateAlternativeSentences(ambiguousSentence, wordPos+1, nextCandidateSentence,possibleSentences);
				if (sentenceVariation.size() == ambiguousSentence.size()) {
						possibleSentences.add(sentenceVariation);
						//System.out.println(possibleSentences);
				}
			}
		}
		//System.out.println("Debug: end of private generateAlternativeSentences "+ambiguousSentence);
		return candidateSentence;
	}
	

	/**
	 * Order the candidates according to a langage model and their sentence score
	 * Assume that all the interpretations have a distinct sentence score
	 * 
	 */
	public static List<List<String>>  rankAlternativeSentences(ArrayEncodedProbBackoffLm<String> lm, List<List<String>> interpretationCandidates) {

		// Compute and sort each interpretation candidate by its sentence score
		Map<Float,List<String>> sortedInterpretations = new TreeMap<Float,List<String>>();
		for (List<String> interpretation : interpretationCandidates) {
			Float score = lm.scoreSentence(interpretation);
			sortedInterpretations.put(score,interpretation);
		}

		//
		List<List<String>> sortedList = new ArrayList<List<String>> ();
		for (Float interpretationScore : sortedInterpretations.keySet()) {
			sortedList.add(sortedInterpretations.get(interpretationScore));
			//System.out.printf("Debug: sentence %s score: %f\n", sortedInterpretations.get(interpretationScore), interpretationScore);
		}
		return 	sortedList;

	}
	

	/**
	 * Disambiguate a list of alternative interpretations by returning the one with the higest score based on a given lm
	 */
	public static List<String> bestAlternativeSentence(List<List<String>> sortedAlternativeInterpretations) {

		return 		sortedAlternativeInterpretations.size() >0 ? sortedAlternativeInterpretations.get(sortedAlternativeInterpretations.size()-1) : new ArrayList<String>();

	}
	
	/**
	 * Disambiguate a list of alternative interpretations by returning the one with the higest score based on a given lm
	 */
	public static List<String> disambiguate(ArrayEncodedProbBackoffLm<String> lm, List<List<String>> interpretationCandidates) {

		return 		rankAlternativeSentences(lm,interpretationCandidates).get(interpretationCandidates.size());

	}
}
