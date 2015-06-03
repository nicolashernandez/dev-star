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
package fr.univnantes.lina.mlnlp.process.tokenization.email;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import fr.univnantes.lina.mlnlp.model.text.Sentence;
import fr.univnantes.lina.mlnlp.model.text.Text;
import fr.univnantes.lina.mlnlp.model.text.Word;
import fr.univnantes.lina.mlnlp.process.tokenization.SentenceSeparators;
import fr.univnantes.lina.mlnlp.process.tokenization.Tokenizer;
import fr.univnantes.lina.mlnlp.process.tokenization.WordSeparators;

/**
 * Sentence spliter and word tokenizer
 * 
 * the approach is descending first the sentences then the words
 * we use regex to identify separators then no-separators (we correct known wrong separators)
 * and only cut when necessary
 * 
 * we do not work directly on the analyzed text, we list all the separator positions
 * 
 * we return a structure Text made of sentences which in turn are made of words, both with offsets
 * 
 * @author hernandez
 *
 */
public class EmailTokenizer extends Tokenizer{




	final static String WORD_SEPARATOR_PATTERN = "[ \\t]+"; //"[^\\s\\p{Punct}\\d]+"; //"[^\\s\\.:,'\\(\\)!]+";

	final static String HTML_TAG_PATTERN = "</?[:A-Z_a-z][:A-Z_a-z-\\.0-9]*( [^>]*)?/?>"; //"[^\\s\\p{Punct}\\d]+"; //"[^\\s\\.:,'\\(\\)!]+";

	final static String HTML_ENDING_TAG_PATTERN = "^</";
	final static String HTML_STARTING_TAG_PATTERN = "^<[^/]";


	final static String SENTENCE_ENDING_HTML_TAG_PATTERN = "(</(div|DIV|p|P) *>|<(br|BR) */?>)";

	final static String SENTENCE_STARTING_TAG_PATTERN = "<(div|DIV|p|P)[ >]";

	final static String WHITESPACE_OR_NOTHING_PATTERN = "\\s*"; //"[^\\s\\p{Punct}\\d]+"; //"[^\\s\\.:,'\\(\\)!]+";

	final static String QUOTE_LINE_PATTERN = "\n>[^\n]*\n"; //"[^\\s\\p{Punct}\\d]+"; //"[^\\s\\.:,'\\(\\)!]+";

	/**
	 * 
	 */
	@Override
	public Text splitIntoSentences(int textStart, String text, Boolean whitespaceAsSentence, Boolean whitespaceAsToken) {


		List<Sentence> sentences = new ArrayList<Sentence> ();

		// sentence offsets 
		Set<Integer> separatorOffsets = new TreeSet<Integer>();




		// HTML processing
		// do word tokenize at these points 
		// do sentence tokenization at some of them 
		// sentenceStarting: at the beginning of <div>, and <p>
		// sentenceEnding: at the end of </p>, and at the end of <br/?>, at the end of </div>
		// when there are a sequence of ending tag with one of them as a sentence break, consider only the last one to be the sentence break
		// do not consider a starting when there was a starting tag nearby
		//
		// Je ne peux le lire sur mon PC.</p>
		//</div>
		//</blockquote></div>
		//<p>
		// Je ne peux le lire sur mon PC.</p></div></blockquote></div>
		//<p>
		//
		//...</cite><blockquote><div><p>Faut-il convertir ? 
		// -> 
		//...</cite>
		//<blockquote><div><p>Faut-il convertir ? 
		Set<Integer> tagTokenStartOffset = new TreeSet<Integer>();
		Set<Integer> tagTokenEndOffset = new TreeSet<Integer>();


		Pattern htmlTagPattern = Pattern.compile(HTML_TAG_PATTERN) ;
		Pattern htmlEndingTagPattern = Pattern.compile(HTML_ENDING_TAG_PATTERN) ;  
		Pattern htmlStartingTagPattern = Pattern.compile(HTML_STARTING_TAG_PATTERN) ;  

		Pattern sentenceEndingHtmlTagPattern = Pattern.compile(SENTENCE_ENDING_HTML_TAG_PATTERN) ;  
		Pattern sentenceStartingHtmlTagPattern = Pattern.compile(SENTENCE_STARTING_TAG_PATTERN) ;  
		Pattern whitespaceOrNothing = Pattern.compile(WHITESPACE_OR_NOTHING_PATTERN) ;  


		Matcher m = htmlTagPattern.matcher(text) ;  
		int lastBreak = -1;
		int sentenceStartingOffsetCandidate = -1;
		boolean addSentenceStartingOffsetCandidate = false;
		while (m.find()) {
			// save the token offsets
			tagTokenStartOffset.add(m.start());
			tagTokenEndOffset.add(m.end());

			// is a sentence ending html tag
			Matcher sentenceEndingHtmlTagMatcher = sentenceEndingHtmlTagPattern.matcher(m.group()) ;
			Matcher htmlEndingTagMatcher = htmlEndingTagPattern.matcher(m.group()) ;

			if (sentenceEndingHtmlTagMatcher.find()) {
				separatorOffsets.remove(m.start()); // keep the last one
				separatorOffsets.add(m.end());
			}
			else if (htmlEndingTagMatcher.find()) {
				if (separatorOffsets.contains(m.start())) {
					separatorOffsets.remove(m.start()); // keep the last one
					separatorOffsets.add(m.end());
				}
			}

			// is a sentence starting html tag
			Matcher htmlStartingTagMatcher = htmlStartingTagPattern.matcher(m.group()) ;
			Matcher sentenceStartingHtmlTagMatcher = sentenceStartingHtmlTagPattern.matcher(m.group()) ;


			if (htmlStartingTagMatcher.find()) {
				if (sentenceStartingOffsetCandidate == -1) sentenceStartingOffsetCandidate = m.start();
				//if (!tagTokenEndOffset.contains(m.start)) sentenceStartingOffsetCandidate = -1;
				if (sentenceStartingHtmlTagMatcher.find()) {
					addSentenceStartingOffsetCandidate = true;
				}
			} else {
				if (addSentenceStartingOffsetCandidate) {
					separatorOffsets.add(sentenceStartingOffsetCandidate);
					addSentenceStartingOffsetCandidate = false;
					sentenceStartingOffsetCandidate = -1;
				}
			}

		}
		tagTokenEndOffset.addAll(tagTokenStartOffset);



		/* Empty lines as sentence separator **/
		SentenceSeparators.emptyLineAsSentSep(text, separatorOffsets);

		/* Strong punct at the end of a line as sentence separator */
		SentenceSeparators.strongPunktAtLineEndAsSentSep(text, separatorOffsets);

		/* Line only made of punct as sentence separator */
		SentenceSeparators.lineOnlyMadeOfPunktAsSentSep(text, separatorOffsets);

		/* Strong punct inside a line as sentence separator 
		 * Warning 1607 ... 0419
		 * Paul M. Bucalo
		 * */
		SentenceSeparators.strongPunctInsideALine(text, separatorOffsets);

		/* lineWoStrongPunktEndingButWithNewSentenceStartNextLineAsSentSep		*/
		SentenceSeparators.lineWoStrongPunktEndingButWithNewSentenceStartNextLineAsSentSep(text, separatorOffsets);

		/* etc. not a sentence separator*/
		SentenceSeparators.etcDotNotSentSep(text, separatorOffsets);

		/* second name abbreviation not as a sentence separator */
		SentenceSeparators.sndNameAbbrevNotAsSentSep(text, separatorOffsets);

		/*  first numeric item of a list at the beginning of a document */
		SentenceSeparators.firstNumItemStartingADocNotAsSentSep(text, separatorOffsets);

		/*	numeric Items Starting Line Not As Sentence SentenceSeparators */
		SentenceSeparators.numItemStartingLineNotAsSentSep(text, separatorOffsets);

		/* end of the document as a sentence separator */
		SentenceSeparators.textEndAsSentSep(text, separatorOffsets);


		// Email quote handling and Email attribution line
		// do not segment theses sentences except at the beginning and the end of the lines
		// segment the attribution line
		String PATTERN_QUOTE_ANNOUNCE = "(wrote|schrieb|said|scritto|kirjoitti|escreveu|schreef|skrev|writes|opine|reply|écrit|escribió|έγραψε|scribbled|asked|stated|írta|pisze|写道|replied|replied|pravi|posted|escriure|zapisal\\(a\\)|napísal\\(a\\)|さんは書きました|пишет|пише|suggested|rakstija|geskryf|yazmış|says|saying|reenviado|commented|=E9crit|escribi.|rta|=EDrta|nap.[^ ]sal\\(a\\)|[^ ]crit)"; //[^:]*:";
		Pattern quoteAnnouncePattern = Pattern.compile(PATTERN_QUOTE_ANNOUNCE);

		Pattern quoteLinePattern = Pattern.compile(QUOTE_LINE_PATTERN) ;
		Matcher quoteLineMatcher = quoteLinePattern.matcher(text) ;
		while (quoteLineMatcher.find()) {

			for (int notASentenceSeparator = quoteLineMatcher.start()+1; notASentenceSeparator <= quoteLineMatcher.end() ; notASentenceSeparator++)
				separatorOffsets.remove(notASentenceSeparator);
			separatorOffsets.add(quoteLineMatcher.start()+1);
			separatorOffsets.add(quoteLineMatcher.end());
			int lastNewline = text.lastIndexOf("\n", quoteLineMatcher.start()-1);
			if (lastNewline != -1) {
				String attributionLineCandidate=text.substring(lastNewline, quoteLineMatcher.start()+1);
				//System.out.println("Debug: attributionline candidate>"+attributionLineCandidate);

				Matcher quoteAnnounceMatcher = quoteAnnouncePattern.matcher(attributionLineCandidate);
				if (quoteAnnounceMatcher.find() && !attributionLineCandidate.startsWith("\n>")) {
					//System.out.println("Debug: attributionline >"+attributionLineCandidate);
					separatorOffsets.add(lastNewline+1);
				}
			}
		}

	



		/** Output */
		Iterator<Integer> endOffsetsIterator = separatorOffsets.iterator();
		int sentenceStart = textStart;

		Set<Integer> sentenceStartWordPosition = new HashSet<Integer>();
		Set<Integer> sentenceEndWordPosition = new HashSet<Integer>();
		int position = 0; // first word token
		while(endOffsetsIterator.hasNext()) {

			int sentenceEnd = endOffsetsIterator.next(); 
			//String coveredText = text.substring(sentenceStart, sentenceEnd);
			//System.err.printf("Debug: sentence >%s<\n", coveredText);
			//sentences.add(new Word(coveredText,textStart+sentenceStart,textStart+sentenceEnd));

			List<Word> words = null; //whitespaceOffsetInsideTagNoTokenOffset
			words = tokenizeIntoWords(sentenceStart,text.substring(sentenceStart, sentenceEnd),whitespaceAsToken, null, null);
			//words = tokenizeIntoWords(sentenceStart,text.substring(sentenceStart, sentenceEnd),whitespaceAsToken, whitespaceOffsetInsideTagNoTokenOffset);


			Sentence sentence = new Sentence(words);
			if (!(!whitespaceAsSentence && !sentence.isContent()))  
				sentences.add(sentence);

			sentenceStartWordPosition.add(position);
			sentenceEndWordPosition.add(position+words.size());
			position= position+words.size()+1;
			//System.err.printf("Debug: sentence >%s<\n",sentence.getCoveredText());

			sentenceStart = sentenceEnd ; 
		}

		return new Text(sentences, sentenceStartWordPosition, sentenceEndWordPosition);

	}






	/**
	 * Tokenize a sentence into string list
	 * @param startReference
	 * @param sentence
	 * @param separatorAsToken
	 * @return
	 */
	public List<String> tokenizeIntoStringList(int startReference, String sentence, Boolean separatorAsToken) {
		List<Word> words = tokenizeIntoWords(startReference, sentence, separatorAsToken, null, null);
		List<String> stringList = new ArrayList<String> ();
		for (Word word : words)
			stringList.add(word.getCoveredText());
		return stringList;
	}

	/**
	 * Tokenize a sentence into string array
	 * @param startReference
	 * @param sentence
	 * @param separatorAsToken
	 * @return
	 */
	public String [] tokenizeIntoStringArray (int startReference, String sentence, Boolean separatorAsToken) {
		List<String> stringList =  tokenizeIntoStringList(startReference, sentence, separatorAsToken);

		String[] stringArray = new String[stringList.size()];
		return stringList.toArray(stringArray); 
	}


	/**
	 * Tokenize a sentence into words
	 * 
	 * Assume the sentence without ws at the extremities
	 * in general partterns specifies both constraints at the start and the end of the separator 
	 * to capture start of the next actual sentence and to consider empty lines as sentence
	 * @param sentence to tokenize
	 * @param startReference the sentence start offset to compute the words start/end offsets relatively to the text
	 * @param whitespaceAsToken specify whether the whitespace patterns should be considered as token
	 */
	@Override
	public List<Word> tokenizeIntoWords(int startReference, String sentence, Boolean whitespaceAsToken, Set<Integer> notATokenSeparator, Set<Integer> isATokenSeparator) {

		//String WS_TOKEN_PATTERN = "^\\s+$"; 
		String WS_TOKEN_PATTERN = "\\s+"; 

		Set<Integer> separatorOffsets = new TreeSet<Integer>();

		/* whitespace as word separator */
		WordSeparators.wsAsWordSep( sentence, separatorOffsets);

		/* punkt ending word as word separator */
		WordSeparators.punktEndingWordAsWordSep( sentence, separatorOffsets);

		/* punkt starting word as word separator */
		WordSeparators.punktStartingWordAsSeparator( sentence, separatorOffsets);

		/* punkt ending line as word separator */
		WordSeparators.punktEndingLineAsWordSeparator( sentence, separatorOffsets);

		/*contraction as word separator */
		WordSeparators.contractionAsWordSeparator( sentence, separatorOffsets);

		/* apostrophe as word separator */
		WordSeparators.apostropheAsWordSeparator( sentence, separatorOffsets);


		/*etc. not as word separator*/
		WordSeparators.etcDotNotAsWordSeparator( sentence, separatorOffsets);

		/* Second name abbreviation not as word separator */
		WordSeparators.sndNameAbbrevNotAsWordSeparator( sentence, separatorOffsets);



		/** end of sentence as Word separator*/
		WordSeparators.sentenceEndAsWordSeparator( sentence, separatorOffsets);


		// remove token separators which have been previously identified as wrong separator 
		//if (notATokenSeparator != null) {
		//	separatorOffsets.removeAll(notATokenSeparator);
		//}
		//if (isATokenSeparator != null) {
		//	int min = Collections.min(separatorOffsets);
		//	int max = Collections.max(separatorOffsets);
		//	for (int newSeparator : isATokenSeparator) 
		//		if (newSeparator>min && newSeparator <max)
		//			separatorOffsets.add(newSeparator);
		//}
		// remove separator offset inside tags
		Pattern htmlTagPattern = Pattern.compile(HTML_TAG_PATTERN) ;
		Matcher m = htmlTagPattern.matcher(sentence) ;  
		while (m.find()) {
			for (int notATokenSeparatorOffset= m.start()+1; notATokenSeparatorOffset <m.end() ; notATokenSeparatorOffset++ )
				//	whitespaceOffsetInsideTagNoTokenOffset.add(notATokenSeparatorOffset);
				separatorOffsets.remove(notATokenSeparatorOffset);
			separatorOffsets.add(m.start());
			separatorOffsets.add(m.end());
		}


		/** */
		List<Word> words = new ArrayList<Word> ();
		Iterator<Integer> endOffsetsIterator = separatorOffsets.iterator();
		int wordStart = 0;
		while(endOffsetsIterator.hasNext()) {
			int wordEnd = endOffsetsIterator.next();
			// 			if (separatorAsToken || !(token.getCoveredText().length() != 0 && CharacterUtil.isSeparator(token.getCoveredText().codePointAt(0))))
			String coveredText = sentence.substring(wordStart,wordEnd);
			if (whitespaceAsToken || ! Pattern.matches(WS_TOKEN_PATTERN, coveredText))
				if (startReference+wordStart != startReference+wordEnd) {
					words.add(new Word(coveredText,startReference+wordStart,startReference+wordEnd));
					//System.err.printf("Debug: word >%s<\n",words.get(words.size()-1).getCoveredText());
				}
			wordStart = wordEnd ; 
		}
		return words;

	}

}
