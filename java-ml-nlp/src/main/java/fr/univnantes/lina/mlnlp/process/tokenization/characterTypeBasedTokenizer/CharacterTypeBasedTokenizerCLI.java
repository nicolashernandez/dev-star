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
package fr.univnantes.lina.mlnlp.process.tokenization.characterTypeBasedTokenizer;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;


public class CharacterTypeBasedTokenizerCLI {

	/**
	 * java -cp bin nlp.SimpleTokenizer -i data/corpusTextos-train-norm.txt
	 * @param args
	 */
	public static void main(String[] args) {

		String usage= "Usage: java "+ CharacterTypeBasedTokenizerCLI.class.getSimpleName()+ " [-i inputFilePath|stdin] [-lc] [-separator-as-token] [-sequence-of-symbols-as-token]";
		String intputFilePath = null;
		Boolean lowercase= false;
		Boolean separatorAsToken = false;
		Boolean sequenceOfSymbolsAsToken = false;
		
		long debut = System.currentTimeMillis();

		/*if (args.length < 1 ) {
			System.out.println(usage);
			System.exit(0);
		}*/

		int pos = 0;
		while (pos < args.length) {
			if(args[pos].equals("-h")) {
				++pos;
				System.out.println(usage);
				System.exit(0);
			}
			else if(args[pos].equals("-i")) {
				intputFilePath = args[++pos];
				++pos;

			}
			else if(args[pos].equals("-lc")) {
				lowercase = true;
				++pos;

			}
			else if(args[pos].equals("-separator-as-token")) {
				separatorAsToken = true;
				++pos;
			}
			else if(args[pos].equals("-sequence-of-symbols-as-token")) {
				sequenceOfSymbolsAsToken = true;
				++pos;
			}			
			else {
				System.out.println("Error: unknown arguments");
				System.out.println(usage);
				System.exit(1);
			}
		}

		BufferedReader reader;
		try {
			if (intputFilePath == null)
				reader = new BufferedReader	(new InputStreamReader(System.in));
			else 
				reader = new BufferedReader( new FileReader (intputFilePath));
			String         line = null;
			while( ( line = reader.readLine() ) != null ) {
				List<String> words = new CharacterTypeBasedWordTokenizer(line,separatorAsToken,sequenceOfSymbolsAsToken).getTokenCoveredTextList();
				String tokenizedLine = "";
				for (String word : words) tokenizedLine += word+" ";
				tokenizedLine = tokenizedLine.trim() + System.getProperty("line.separator");
				if (lowercase) tokenizedLine = tokenizedLine.toLowerCase();
				System.out.printf("%s", tokenizedLine)	;
			} 
			reader.close();
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
