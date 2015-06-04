/** 
 * UIMA ml-nlp
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
package fr.univnantes.lina.uima.mlnlp.process.tokenization;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.fit.component.JCasAnnotator_ImplBase;
import org.apache.uima.jcas.JCas;

import common.types.text.Sentence;
import common.types.text.Token;


/**
 * Annotator that segments the text both into sentences and words 
 * 
 * old possibility to filter out some words based on a dictionary given as a StopWordModel resource
 */
public class WhitespaceRegexPatternTokenizerAE extends JCasAnnotator_ImplBase {
	final static String SENTENCE_SEPARATOR_PATTERN = "\\n"; 
	final static String WORD_SEPARATOR_PATTERN = "[ \\t]+"; //"[^\\s\\p{Punct}\\d]+"; //"[^\\s\\.:,'\\(\\)!]+";

	// TODO add http://www.w3.org/TR/html-markup/elements.html
	/*public final static String RES_KEY = "aKey";
	@ExternalResource(key = RES_KEY)
	private StopWordModel stopWords;*/

	@Override
	public void process(JCas aJCas) throws AnalysisEngineProcessException {
		// Prints the instance ID to the console - this proves the same instance
		// of the SharedModel is used in both Annotator instances.
		//System.out.println(getClass().getSimpleName() + ": " + stopWords);

		Pattern sentenceSeparatorPattern = Pattern.compile(SENTENCE_SEPARATOR_PATTERN);
		Pattern wordSeparatorPattern = Pattern.compile(WORD_SEPARATOR_PATTERN);

		Matcher sentenceMatcher = sentenceSeparatorPattern.matcher(aJCas.getDocumentText());
		int sentenceStart = 0;
		while (sentenceMatcher.find()) {
			//if (!stopWords.contains(matcher.group().toLowerCase()))
			//sentenceStart = sentenceMatcher.start();
			new Sentence(aJCas, sentenceStart, sentenceMatcher.start()).addToIndexes(); 
			String sentence = aJCas.getDocumentText().substring(sentenceStart, sentenceMatcher.start());
			//System.err.printf("Debug: sentence >%s<\n", sentence);

			Matcher wordMatcher = wordSeparatorPattern.matcher(sentence);
			//int wordStart = sentenceStart;
			int wordStartInCurrentSentence = 0;
			while (wordMatcher.find()) {
				//if (!stopWords.contains(matcher.group().toLowerCase()))
				//System.err.printf("Debug: word start %d end %d\n", wordMatcher.start(), wordMatcher.end());

				//new Word(aJCas, wordStart, wordStart+wordMatcher.start()).addToIndexes();
				new common.types.text.Token(aJCas, sentenceStart+wordStartInCurrentSentence, sentenceStart+wordMatcher.start()).addToIndexes();

				//String word = sentence.substring(wordStartInCurrentSentence, wordMatcher.start());
				//System.err.printf("Debug: word >%s<\n", word);
				//wordStart += wordMatcher.end();
				wordStartInCurrentSentence = wordMatcher.end();
			}
			sentenceStart = sentenceMatcher.end();
		}
	}
}
