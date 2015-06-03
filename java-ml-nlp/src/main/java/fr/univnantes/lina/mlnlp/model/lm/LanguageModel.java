/** 
 * 
 * Copyright (C) 2014  Nicolas Hernandez
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
package fr.univnantes.lina.mlnlp.model.lm;

import java.util.List;

public interface LanguageModel {

	/** Estimate with the default smoothing technique a language from the given text and export it in the arpa format */
	void estimateLanguaModel (int order, String textPath, String arpaPath);
	
	/** Load and set a language model */
	void loadLanguageModel(String arpaPath);
	
	/** Load and set a binary language model */
	//void loadBinaryLanguageModel(String binaryPath);
	
	/** Calculate language model score of an n-gram. 
	 * Warning: if you pass in an n-gram of length greater than getLmOrder(), 
	 * this call will silently ignore the extra words of context. 
	 * In other words, if you pass in a 5-gram (endPos-startPos == 5) to a 3-gram model, it will only score the words from startPos + 2 to endPos. */
	Float getNgramLogProb (Object lm, List<String> words);
	
	/** Get the score of probability of a whole sentence (sum of sequence of log proba of ngrams) */
	Float scoreSentence (Object lm, List<String> words);
	
	/** Set the language model */
	void setLM(Object lm);
	
	/** get the language model */
	Object getLM();
	
	/** Get the sentence probability score for each sentence of a list (with words whitespace separated)
	 *  Return a list of scores */
	List<Float> scoreLogProbSentenceList (Object lm, List<String> sentences);
	
	/** Calculate the sentence probability score for each sentence of a file (with words whitespace separated)
	 *  Write the result in a file */
	void scoreLogProbSentenceFile (Object lm, String sentencesFilePath, String scoresFilePath);
	
	/** Get the ngram probability score for each ngram of a list 
	 * (with words whitespace separated)
	 *  Return a list of scores */
	List<Float> scoreLogProbNgramList (Object lm, List<String> ngrams);
	
	/** Calculate the ngram probability score for each ngram of a file 
	 * (with words whitespace separated)
	 *  Write the result in a file */
	void scoreNgramFile (Object lm, String ngramsFilePath, String scoresFilePath);
	
}
