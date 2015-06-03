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
package fr.univnantes.lina.mlnlp.model.lm;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import edu.berkeley.nlp.lm.ArrayEncodedNgramLanguageModel;
import edu.berkeley.nlp.lm.ArrayEncodedProbBackoffLm;
import edu.berkeley.nlp.lm.ConfigOptions;
import edu.berkeley.nlp.lm.StringWordIndexer;
import edu.berkeley.nlp.lm.io.ArpaLmReader;
import edu.berkeley.nlp.lm.io.LmReaders;
import fr.univnantes.lina.javautil.IOUtilities;

/**
 * @author hernandez
 *
 */
public class BinaryBerkeleyLanguageModel extends BerkeleyLanguageModel {

	private ArrayEncodedProbBackoffLm<String> lm;
	
	// binary
	private ArrayEncodedNgramLanguageModel blm; 
	


	/**
	 * The return value should be casted in ArrayEncodedProbBackoffLm<String>
	 */
	@Override
	public  void loadLanguageModel(String binaryPath) {
			 this.blm =(ArrayEncodedNgramLanguageModel) LmReaders.readLmBinary(binaryPath);
		}
	

	@Override
	public Float scoreSentence(Object lm, List<String> sentence) {
		ArrayEncodedNgramLanguageModel<String> lm2 = (ArrayEncodedNgramLanguageModel<String>) lm;
		return lm2.scoreSentence(sentence);
	}


	@Override
	public void setLM(Object lm) {
		this.blm = (ArrayEncodedNgramLanguageModel) lm;
	}


	@Override
	public Object getLM() {
		return this.blm;
	}


	@Override
	public Float getNgramLogProb(Object lm, List<String> words) {
		return ((ArrayEncodedNgramLanguageModel) lm).getLogProb(words);
	}


	@Override
	public List<Float> scoreLogProbSentenceList(Object lm, List<String> sentences) {
		List<Float> scores = new ArrayList<Float>();
		for (String sentence : sentences) 
			scores.add(scoreSentence(lm, Arrays.asList(sentence.split("\\s+"))));
		return scores;
	}


	@Override
	public void scoreLogProbSentenceFile(Object lm, String sentencesFilePath,
			String scoresFilePath) {
		List<Float> scoresFloatList = scoreLogProbSentenceList(lm,IOUtilities.readTextFileAsStringList(sentencesFilePath));
		StringBuffer scoresStringBuffer = new StringBuffer();
		for (Float score : scoresFloatList){
			scoresStringBuffer.append(String.valueOf(score));
			scoresStringBuffer.append("\n");
		}
		IOUtilities.writeToFS(scoresStringBuffer.toString(), scoresFilePath, false);
	}


	@Override
	public List<Float> scoreLogProbNgramList(Object lm, List<String> ngrams) {
		List<Float> scores = new ArrayList<Float>();
		for (String ngram : ngrams) 
			scores.add(getNgramLogProb(lm, Arrays.asList(ngram.split("\\s+"))));
		return scores;
	}


	@Override
	public void scoreNgramFile(Object lm, String ngramsFilePath,
			String scoresFilePath) {
		List<Float> scoresFloatList = scoreLogProbNgramList(lm,IOUtilities.readTextFileAsStringList(ngramsFilePath));
		StringBuffer scoresStringBuffer = new StringBuffer();
		for (Float score : scoresFloatList){
			scoresStringBuffer.append(String.valueOf(score));
			scoresStringBuffer.append("\n");
		}
		IOUtilities.writeToFS(scoresStringBuffer.toString(), scoresFilePath, false);

	}



	
	
}
