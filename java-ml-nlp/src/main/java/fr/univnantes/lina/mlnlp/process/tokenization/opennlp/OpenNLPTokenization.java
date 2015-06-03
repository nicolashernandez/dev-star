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
package fr.univnantes.lina.mlnlp.process.tokenization.opennlp;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import opennlp.tools.sentdetect.SentenceDetectorME;
import opennlp.tools.sentdetect.SentenceModel;
import opennlp.tools.tokenize.Tokenizer;
import opennlp.tools.tokenize.TokenizerME;
import opennlp.tools.tokenize.TokenizerModel;

public class OpenNLPTokenization {



	/**
	 * @throws FileNotFoundException
	 */
	public static String [] opennlpTokenize(String tokenModelPath,  String[] sentences){
		// To instantiate the TokenizerME (the learnable tokenizer) a Word Model must be created first. The following code sample shows how a model can be loaded.
		InputStream modelIn = null;
		try {
			modelIn = new FileInputStream(tokenModelPath);
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		TokenizerModel model = null;
		try {
			model = new TokenizerModel(modelIn);
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		finally {
			if (modelIn != null) {
				try {
					modelIn.close();
				}
				catch (IOException e) {
				}
			}
		}

		// After the model is loaded the TokenizerME can be instantiated.
		Tokenizer tokenizer = new TokenizerME(model);

		// The tokenizer offers two tokenize methods, both expect an input String object which contains the untokenized text. If possible it should be a sentence, but depending on the training of the learnable tokenizer this is not required. The first returns an array of Strings, where each String is one token.
		// The output will be an array with these tokens.
		// "An input sample sentence." -> "An", "input", "sample", "sentence", "."
		List<String> textList = new ArrayList<String>();
		
		//StringBuffer text = new StringBuffer();
		for (String sentence : sentences) {
			System.out.println("Debug: "+sentence);
			String tokens[] = tokenizer.tokenize(sentence);
			for (String token: tokens) {
				//text.append(str)
				textList.add(token);
				textList.add(" ");
			}
			//if (tokens.length > 0) textList.remove(textList.size()-1);
			//textList.add(System.getProperty("line.separator"));
			textList.add("\n");

		}
		String[] textArray = textList.toArray(new String[textList.size()]);
		
		// The second method, tokenizePos returns an array of Spans, each Span contain the begin and end character offsets of the token in the input String.
		// The tokenSpans array now contain 5 elements. To get the text for one span call Span.getCoveredText which takes a span and the input text.
		// Span tokenSpans[] = tokenizer.tokenizePos("An input sample sentence.");		



		// The TokenizerME is able to output the probabilities for the detected tokens. The getTokenProbabilities method must be called directly after one of the tokenize methods was called.
		// The tokenProbs array now contains one double value per token, the value is between 0 and 1, where 1 is the highest possible probability and 0 the lowest possible probability.
		// TokenizerME tokenizer = ...
		// String tokens[] = tokenizer.tokenize(...);
		// double tokenProbs[] = tokenizer.getTokenProbabilities();
		return textArray;
	}


	/**
	 * @return
	 */
	public static String [] opennlpSentenceDetector(String sentModelPath, String text) {
		// To instantiate the Sentence Detector the sentence model must be loaded first.
		InputStream modelIn = null;
		try {
			modelIn = new FileInputStream(sentModelPath);
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		SentenceModel model = null;
		try {
			model = new SentenceModel(modelIn);
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		finally {
			if (modelIn != null) {
				try {
					modelIn.close();
				}
				catch (IOException e) {
				}
			}
		}

		// After the model is loaded the SentenceDetectorME can be instantiated.
		SentenceDetectorME sentenceDetector = new SentenceDetectorME(model);


		// The Sentence Detector can output an array of Strings, where each String is one sentence.
		// The result array now contains two entries. The first String is "First sentence." and the second String is "Second sentence." 
		// The whitespace before, between and after the input String is removed. 
		String sentences[] = sentenceDetector.sentDetect(text);

		// The API also offers a method which simply returns the span of the sentence in the input string.
		// The result array again contains two entries. The first span beings at index 2 and ends at 17. The second span begins at 18 and ends at 34. The utility method Span.getCoveredText
		// Span sentences[] = sentenceDetector.sentPosDetect("  First sentence. Second sentence. ");

		return sentences;
	}

}
