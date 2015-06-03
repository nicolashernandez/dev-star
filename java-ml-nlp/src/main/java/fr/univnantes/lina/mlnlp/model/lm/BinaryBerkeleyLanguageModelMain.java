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

/**
 * @author hernandez-n
 *
 */
public class BinaryBerkeleyLanguageModelMain {

	static void usage () {
		System.out.println("Usage:");
		System.out.println("\t"+ new BinaryBerkeleyLanguageModelMain().getClass().getName() + " estimateLm <order> <inputTrainFile> <outputLMFile>");
		System.out.println("\t"+ new BinaryBerkeleyLanguageModelMain().getClass().getName() + " scoreSentences <inputLMFile> <inputSentencesFile> <outputScoreFiles>");
		System.out.println("\t"+ new BinaryBerkeleyLanguageModelMain().getClass().getName() + " scoreNgrams <inputLMFile> <inputNgramsFile> <outputScoreFiles>");
		System.exit(1);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		LanguageModel bblm = new BinaryBerkeleyLanguageModel();		

		if (args.length <=0)	usage();	
		int i = 0;
		while (true) {
			if (args[i].equalsIgnoreCase("estimateLm")) {
				if (i+3 >= args.length)	usage();
				estimate(i,args,bblm); i=i+3;
			}
			else 
				if (args[i].equalsIgnoreCase("scoreSentences")) {
					if (i+3 >= args.length)	usage();
					scoreSentences(i,args,bblm);i=i+3;
				}
				else if (args[i].equalsIgnoreCase("scoreNgrams")) {
					if (i+3 >= args.length)	usage();
					scoreNgrams(i,args,bblm);i=i+3;
				} else {
					usage();

				}
			
			i++;
			if (i==args.length) break;
		}

	}


	private static void estimate(int i, String[] args, LanguageModel blm) {
		int order = Integer.valueOf(args[i+1]);
		String inputTrainFile = args[i+2]; 
		String outputLMFile = args[i+3];
		blm.estimateLanguaModel(order, inputTrainFile, outputLMFile);		
	}

	private static void scoreNgrams(int i, String[] args, LanguageModel blm) {
		String inputLMFile = args[i+1]; 
		String inputNgramsFile = args[i+2]; 
		String outputScoreFiles = args[i+3]; 
		blm.loadLanguageModel(inputLMFile);
		blm.scoreNgramFile(blm.getLM(), inputNgramsFile, outputScoreFiles);
	}

	private static void scoreSentences(int i, String[] args, LanguageModel blm) {
		String inputLMFile = args[i+1]; 
		String inputSentencesFile = args[i+2]; 
		String outputScoreFiles = args[i+3]; 
		blm.loadLanguageModel(inputLMFile);
		blm.scoreLogProbSentenceFile(blm.getLM(), inputSentencesFile, outputScoreFiles);
	}



}
