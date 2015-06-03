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
package fr.univnantes.lina.mlnlp.process.transformation;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import fr.univnantes.lina.mlnlp.process.tokenization.characterTypeBasedTokenizer.CharacterTypeBasedWordTokenizer;

/**
 * @author hernandez
 *
 */
public class TextNormalizerCLI {

	
	/**
	 * cat anyText | java -cp bin nlp.SimpleTokenizerMain  
	 * @param args
	 */
	public static void main(String[] args) {

		final String TOKENIZE ="-tokenize"; 
Boolean tokenize = false;
		String usage= "Usage: java "+ TextNormalizerCLI.class.getSimpleName()
				+ " [-i inputFilePath|stdin] [-lang fr|en|...]"
				+ " ["+TOKENIZE+"]"
				+ " ["+TextNormalization.LOWERCASE+"|"+TextNormalization.STEM+"|"+TextNormalization.FLEXION+"|"+TextNormalization.TONUMBERCLASS+"|"+TextNormalization.TOCHARCLASS+"|"+TextNormalization.SQUEEZEREPEATEDCHAR+" in the given order]*"
						+ " [-stopwords stopwordsFilePath]";
		String intputFilePath = null;
		String stopWordsFilePath = null;

		String language = "fr";
		long debut = System.currentTimeMillis();

		/*if (args.length < 1 ) {
			System.out.println(usage);
			System.exit(0);
		}*/

		int pos = 0;
		
		List<String> normalizationOperations = new ArrayList<String>();
		while (pos < args.length) {
			if(args[pos].equals("-h")) {
				++pos;
				System.out.println(usage);
				System.exit(0);
			}
			else if(args[pos].equals("-lang")) {
				language = args[++pos];
				++pos;
			}
			else if(args[pos].equals("-i")) {
				intputFilePath = args[++pos];
				++pos;
			}
			else if(args[pos].equals(TOKENIZE)) {
				tokenize = true;
				++pos;
			}
			else if(args[pos].equals("-stopwords")) {
				stopWordsFilePath = args[++pos];
				++pos;
			}
			else if(args[pos].equals(TextNormalization.LOWERCASE)) {
				normalizationOperations.add(TextNormalization.LOWERCASE);				
				++pos;
			}
			
			else if(args[pos].equals(TextNormalization.TONUMBERCLASS)) {
				normalizationOperations.add(TextNormalization.TONUMBERCLASS);				
				++pos;
			}
			else if(args[pos].equals(TextNormalization.STEM)) {
				normalizationOperations.add(TextNormalization.STEM);
				++pos;
			}
			else if(args[pos].equals(TextNormalization.FLEXION)) {
				normalizationOperations.add(TextNormalization.FLEXION);				
				++pos;
			}
			else if(args[pos].equals(TextNormalization.TOCHARCLASS)) {
				normalizationOperations.add(TextNormalization.TOCHARCLASS);				
				++pos;
			}
			else if(args[pos].equals(TextNormalization.SQUEEZEREPEATEDCHAR)) {
				normalizationOperations.add(TextNormalization.SQUEEZEREPEATEDCHAR);				
				++pos;
			}
			else {
				System.out.println("Error: unknown arguments");
				System.out.println(usage);
				System.exit(1);
			}
		}

		

		
		BufferedReader textReader;
		Set<String> stopWords = new HashSet<String>();
		
		try {
			
			/* load stopwords */
			if (stopWordsFilePath != null) {
				BufferedReader stopWordsReader = new BufferedReader( new FileReader (stopWordsFilePath));
				String line = null;
				while( ( line = stopWordsReader.readLine() ) != null )
					stopWords.add(line);
			}
			
			
			if (intputFilePath == null)
				textReader = new BufferedReader	(new InputStreamReader(System.in));
			else 
				textReader = new BufferedReader( new FileReader (intputFilePath));
			String         line = null;
			/* For each text line */
			while( ( line = textReader.readLine() ) != null ) {
				List<String> words = new ArrayList<String>();
				if (tokenize) words = new CharacterTypeBasedWordTokenizer(line,false,true).getTokenCoveredTextList();
				// by default simple whitespace tokenizer (means do not change the tokenization)
				else words.addAll(Arrays.asList(line.split(" ")));
				
				/* Normalization */
				for (String normalizationOperation : normalizationOperations) {
					words = new ArrayList<String>(TextNormalization.normalize(normalizationOperation, language, words, stopWords));
				}
				
				String tokenizedLine = "";
				for (String word : words) tokenizedLine += word+" ";
				tokenizedLine = tokenizedLine.trim() + System.getProperty("line.separator");
				System.out.printf("%s", tokenizedLine)	;
			} 
			textReader.close();
		} 
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}


		long fin = System.currentTimeMillis();
		System.err.println("Elapsed time: "+ ((fin-debut)/1000.0)+" s");
	}



}
