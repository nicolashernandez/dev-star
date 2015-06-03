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
package fr.univnantes.lina.mlnlp.process.evaluation;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import fr.univnantes.lina.javautil.IOUtilities;


/**
 * @author hernandez
 *
 */
public class CorpusPartitionSplitter {
	static String trainSuffix = ".train";
	static String testSuffix = ".test";

	public static void partition(List<String> corpusLines, String outputDirPath, String outputFilePrefix, int partitionNumber) {

		int numberOfLinesPerPartition = corpusLines.size() / partitionNumber;
		int numberOfCreatedPartitions = 0;
		int currentPartitionLineIndex = 0;

		String currentPartitionContent = "";
		for (String line : corpusLines) {
			if (!line.endsWith(System.getProperty("line.separator"))) 
				line = line + System.getProperty("line.separator");
			if ((currentPartitionLineIndex >= numberOfLinesPerPartition) 
					&& (numberOfCreatedPartitions != partitionNumber-1)){
				String testOutputFileName = outputDirPath+"/"+ outputFilePrefix+numberOfCreatedPartitions+testSuffix;
				//System.out.printf("Debug: numberOfPartitionCreated %d testOutputFileName %s currentPartitionContent %s \n",numberOfCreatedParts, testOutputFileName, currentPartitionContent);
				IOUtilities.writeToFS(currentPartitionContent, testOutputFileName, false);
				currentPartitionContent = "";
				numberOfCreatedPartitions++;
				currentPartitionLineIndex = 0;
			}
			currentPartitionContent += line; // + System.getProperty("line.separator");
			currentPartitionLineIndex++;
		}
		if (!currentPartitionContent.equalsIgnoreCase("")) {
			String testOutputFileName = outputDirPath+"/"+ outputFilePrefix+numberOfCreatedPartitions+testSuffix;
			//System.out.printf("Debug: numberOfPartitionCreated %d testOutputFileName %s currentPartitionContent %s \n",numberOfCreatedParts, testOutputFileName, currentPartitionContent);
			IOUtilities.writeToFS(currentPartitionContent, testOutputFileName, false);
			numberOfCreatedPartitions++;
		}


		for (int i = 0 ; i<numberOfCreatedPartitions; i++ ) {
			String trainOutputFileName = outputDirPath+"/"+ outputFilePrefix+i+trainSuffix;
			IOUtilities.deleteFile(trainOutputFileName);
			
			for (int j = 0 ; j<numberOfCreatedPartitions; j++ ) {
				if (i != j) {
					String testFileName = outputDirPath+"/"+ outputFilePrefix+j+testSuffix;
					List<String> testLines = IOUtilities.readTextFileAsStringList(testFileName);
					IOUtilities.writeToFS(testLines, trainOutputFileName, true);
				}
			}
		}
		
		System.err.println("Info: average number of lines per partition "+numberOfLinesPerPartition);
		System.err.printf("Info: number of created partitions %d\n",numberOfCreatedPartitions);
	}

	/**
	 * Split a corpus file into partitions
	 * Lines are considered as the elementary unit
	 * 
	 * @param corpusFilePath
	 * @param outputDirPath
	 * @param partitionNumber
	 */
	public static void partition(String corpusFilePath, String outputDirPath, String outputFilePrefix, int partitionNumber) {

		List<String> corpusLines = IOUtilities.readTextFileAsStringList(corpusFilePath);

		partition(corpusLines, outputDirPath, outputFilePrefix, partitionNumber);


	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		String usage= "Usage: java "+ CorpusPartitionSplitter.class.getSimpleName()+ " [-i inputFilePath|stdin] [-d outputDir] [-n partition number]";
		String intputFilePath = null;
		String outputDirPath = null;
		int partitionNumber = -1;
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
			else if(args[pos].equals("-d")) {
				outputDirPath = args[++pos];
				++pos;

			}
			else if(args[pos].equals("-n")) {
				partitionNumber = Integer.valueOf(args[++pos]);
				++pos;
			} else
				printError(usage, "Error: unknown arguments");
		}

		if ((outputDirPath == null) || (partitionNumber == -1))
			printError(usage, "Error: unknown arguments");

		List<String> corpusLines = new ArrayList<String>();
		BufferedReader reader;
		try {
			if (intputFilePath == null)
				reader = new BufferedReader	(new InputStreamReader(System.in));
			else 
				reader = new BufferedReader( new FileReader (intputFilePath));
			String         line = null;
			while( ( line = reader.readLine() ) != null ) {
				corpusLines.add(line);

			} 
			reader.close();
		} 
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}

		partition(corpusLines, outputDirPath, "part", partitionNumber);



		long fin = System.currentTimeMillis();
		System.err.println("Elapsed time: "+ ((fin-debut)/1000.0)+" s");
	}

	/**
	 * @param usage
	 * @param message TODO
	 */
	private static void printError(String usage, String message) {
		{
			System.err.println(message);
			System.err.println(usage);
			System.exit(1);
		}
	}


}
