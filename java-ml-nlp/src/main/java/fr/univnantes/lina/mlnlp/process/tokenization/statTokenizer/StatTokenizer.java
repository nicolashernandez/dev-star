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
package fr.univnantes.lina.mlnlp.process.tokenization.statTokenizer;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import org.paukov.combinatorics.Factory;
import org.paukov.combinatorics.Generator;
import org.paukov.combinatorics.ICombinatoricsVector;

import edu.berkeley.nlp.lm.ArrayEncodedProbBackoffLm;
import fr.univnantes.lina.mlnlp.model.lm.BerkeleyLanguageModel;
import fr.univnantes.lina.mlnlp.process.tokenization.characterTypeBasedTokenizer.CharacterTypeBasedWordTokenizer;

public class StatTokenizer {

	final static String tokenSeparator = "<t>";


	/**
	 * @param blm
	 * @param reader
	 * @throws IOException
	 */
	public static void statTokenize(BerkeleyLanguageModel blm,
			BufferedReader reader) throws IOException  {
		String         line = null;

		// for each sentence
		while( ( line = reader.readLine() ) != null ) {

			// tokenize the current sentence on the whitespace characters 
			List<String> wsTokens = Arrays.asList(line.split(" "));


			String tokenizedLine = "";
			List<String> tokens = new ArrayList<String>();

			// for each whitespace token
			for (String wstoken : wsTokens) {

				// for tokenize the current whitespace token on class character breaks 
				// (this tokenization is called simple tokenizer)
				List<String> simpleTokens = new CharacterTypeBasedWordTokenizer(wstoken,false,false).getTokenCoveredTextList();
				System.out.println("Debug: -------------------------------------");
				
				List<String> ngram = new ArrayList<String>();
				ngram.add("<t>");
				System.out.printf("Debug: logprob of %s is %f\n",ngram,((ArrayEncodedProbBackoffLm<String>) blm.getLM()).getLogProb(ngram));
				
				System.out.println(simpleTokens);
				if (simpleTokens.size()>1) {

					// here there are more than one simple token making of the whitespace token
					// here measure the most probable combination of simple tokens
					StatTokenizer.bestTokenPartition(blm, simpleTokens);
				}
				else {
					tokens.add(wstoken);
				}
			}

			for (String word : tokens) tokenizedLine += word+" ";
			tokenizedLine = tokenizedLine.trim() + System.getProperty("line.separator");
			//if (lowercase) tokenizedLine = tokenizedLine.toLowerCase();
			//System.out.printf("%s", tokenizedLine)	;
		}
	}


	/**
	 * Determine the best token partition of the given whitespace token 
	 * When several alternatives with the same score, keep only the first one
	 * @param lm
	 * @param simpleTokens
	 * @return
	 */
	public static List<String> bestTokenPartition(BerkeleyLanguageModel blm, List<String> simpleTokens) {

		// generate all the partition candidate
		List<List<String>> partitionCombinationList = generateTokenPartitionCombinations(simpleTokens);
		//System.out.printf("sentence score: %s %f\n",ngram,blm.scoreSentence(blm.getLM(),ngram));

		// compute the probability score of each candidate
		Map<Float,String> scoredTokenPartitionCombination = new TreeMap<Float,String>();
		for (List<String> partitionCombination : partitionCombinationList) {
			StringBuffer partitionCombinationStringBuffer = new StringBuffer();
			partitionCombinationStringBuffer.append(tokenSeparator+" ");
			for (String partition : partitionCombination) {

				partitionCombinationStringBuffer.append(partition+" ");
				partitionCombinationStringBuffer.append(tokenSeparator+" ");
			}
			String partitionCombinationString = partitionCombinationStringBuffer.toString().trim();
			Float  sentenceScore = blm.scoreSentence(blm.getLM(),Arrays.asList(partitionCombinationString.split(" ")));
			if (!scoredTokenPartitionCombination.containsKey(sentenceScore)) 
				scoredTokenPartitionCombination.put(sentenceScore,partitionCombinationString);
			else 
				System.out.println("Warning: a combination with the same score has already been added.");
		}
		for (Float sentenceScore : scoredTokenPartitionCombination.keySet()) {
			System.out.printf("%f %s\n",sentenceScore, scoredTokenPartitionCombination.get(sentenceScore));				
		}
		System.out.println();
		/*

			for (int i = 0 ; i <= simpleTokens.size() ; i++) {
				List<String> ngram = new ArrayList<String>();
				for (int j = 0 ; j < i; j++) 
					ngram.add(simpleTokens.get(j));
				//			System.out.printf("sentence score: %s %f\n",ngram,lm.getLogProb(ngram));
				System.out.printf("sentence score: %s %f\n",ngram,blm.scoreSentence(blm.getLM(),ngram));
			}*/

		return simpleTokens;

	}

	/**
	 * 
	 * @param simpleTokens
	 * @return
	 */
	public static List<List<String>> generateTokenPartitionCombinations(List<String> simpleTokens) {

		List<List<String>> tokenPartitionCombinations = new ArrayList<List<String>>();
		//String [] wstokens= new String[] { "1994", "[", "ref", "." };

		// Create an instance of the partition generator to generate all
		// possible partitions of 5
		Generator<Integer> partitionGenerator = Factory.createPartitionGenerator(simpleTokens.size());

		// Print the partitions
		for (ICombinatoricsVector<Integer> p : partitionGenerator) {
			//System.out.println(p);

			String [] partitionStringArray = new String[p.getSize()];
			for (int i=0; i< p.getSize() ; i++) {
				partitionStringArray[i] = String.valueOf(p.getValue(i));
			}

			// Create the initial vector of 3 elements (apple, orange, cherry)
			ICombinatoricsVector<String> originalVector = Factory.createVector(partitionStringArray);

			// Create the permutation generator by calling the appropriate method in the Factory class
			Generator<String> permutationGenerator = Factory.createPermutationGenerator(originalVector);

			// Print the result
			for (ICombinatoricsVector<String> perm : permutationGenerator) {
				//System.out.println(perm);
				int parsed = 0;
				List<String> tokenPartitionCombination = new ArrayList<String>();

				for (String partSizeString : perm.getVector()) {
					int partSize = Integer.valueOf(partSizeString);
					//System.out.printf(tokenSeparator+" ");
					//combination.append(tokenSeparator+" ");
					StringBuffer combination = new StringBuffer();
					for (int j = parsed ; j < parsed + partSize ; j++) {
						combination.append(simpleTokens.get(j)+" ");

						//System.out.printf("%s ",simpleTokens.get(j));
					}
					tokenPartitionCombination.add(combination.toString().trim());
					parsed += partSize;
				}
				//System.out.printf(tokenSeparator+" ");
				//System.out.println();
				tokenPartitionCombinations.add(tokenPartitionCombination);
			}
		}

		return tokenPartitionCombinations;
	}

}
