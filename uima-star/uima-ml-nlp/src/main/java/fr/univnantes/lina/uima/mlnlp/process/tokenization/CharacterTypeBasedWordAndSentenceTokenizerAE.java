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

import java.io.File;
import java.util.Iterator;
import java.util.List;

import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.examples.SourceDocumentInformation;
import org.apache.uima.fit.component.JCasAnnotator_ImplBase;
import org.apache.uima.fit.descriptor.ConfigurationParameter;
import org.apache.uima.jcas.JCas;

import common.types.text.Sentence;
import fr.univnantes.lina.javautil.IOUtilities;
import fr.univnantes.lina.mlnlp.process.tokenization.characterTypeBasedTokenizer.CharacterTypeBasedWordAndSentenceTokenizer;

/**
 * Annotator that segments the text both into sentences and words with a character type based approach
 * 
 */
public class CharacterTypeBasedWordAndSentenceTokenizerAE extends JCasAnnotator_ImplBase {

	//
	public static final String PARAM_OUTPUT_DIR = "outputDir";
	@ConfigurationParameter(name = PARAM_OUTPUT_DIR, mandatory = true, defaultValue="/tmp")
	private String outputDir;


	/** separatorAsToken specify if sequence of separator characters should be considered as token */
	public static final String PARAM_SEPARATOR_AS_TOKEN = "separatorAsToken";
	@ConfigurationParameter(name=PARAM_SEPARATOR_AS_TOKEN, mandatory=false, defaultValue="false")
	private Boolean separatorAsToken;
	

	/**  sequenceOfSymbolsAsToken segmentEachConsecutivePairOfNonLetterDigitOrSeparator i.e. punctuation*/
	public static final String PARAM_SEQUENCE_OF_SYMBOL_AS_TOKEN = "sequenceOfSymbolsAsToken";
	@ConfigurationParameter(name=PARAM_SEQUENCE_OF_SYMBOL_AS_TOKEN, mandatory=false, defaultValue="false")
	private Boolean sequenceOfSymbolsAsToken;
	
	private static String DEFAULT_SOURCE_DOCUMENT_INFORMATION_ANNOTATION = "org.apache.uima.examples.SourceDocumentInformation";

	
	@Override
	public void process(JCas aJCas) throws AnalysisEngineProcessException {
				
		//{SourceDocumentInformation sourceDocumentInformation = (SourceDocumentInformation) aJCas.getAnnotationIndex(aJCas.getTypeSystem().getType(DEFAULT_SOURCE_DOCUMENT_INFORMATION_ANNOTATION)).iterator().get();
		//String uri = sourceDocumentInformation.getUri().substring(sourceDocumentInformation.getUri().lastIndexOf(File.separator));
		//System.err.println("Debug: uri "+uri);
		//}
		CharacterTypeBasedWordAndSentenceTokenizer tokenizedText = new CharacterTypeBasedWordAndSentenceTokenizer(aJCas.getDocumentText(), separatorAsToken, sequenceOfSymbolsAsToken);
		List<List< fr.univnantes.lina.mlnlp.model.text.Word>> wordsAndSentencesList = tokenizedText.getSentences();
		
		for (List< fr.univnantes.lina.mlnlp.model.text.Word> sentence : wordsAndSentencesList) {
			int sentenceStart = -1;
			for ( fr.univnantes.lina.mlnlp.model.text.Word word : sentence) {
				new common.types.text.Token(aJCas, word.getStart(), word.getEnd()).addToIndexes();
				if (sentenceStart == -1) sentenceStart =  word.getStart();
			}
			if (sentence.size() != 0)
				new Sentence(aJCas, sentence.get(0).getStart(),sentence.get(sentence.size()-1).getEnd()).addToIndexes(); 

		}
		
		//System.err.printf("Debug: #sentences %d\n",wordsAndSentencesList.size());
		String tokenizedTextString = tokenizedText.getWhitespaceSeparatedTokenSentenceCoveredTextList();

		/* Output */
		if (!outputDir.equalsIgnoreCase("")) { 
			// Get the uri of the current jcas
			SourceDocumentInformation sourceDocumentInformation = (SourceDocumentInformation) aJCas.getAnnotationIndex(aJCas.getTypeSystem().getType(DEFAULT_SOURCE_DOCUMENT_INFORMATION_ANNOTATION)).iterator().get();
			String uri = sourceDocumentInformation.getUri().substring(sourceDocumentInformation.getUri().lastIndexOf(File.separator));
			//MiscUtil.writeToFS(mBoxMessage.toString(), messageSaveDir+"/"+uri, false);
			/*XStream xstream = new XStream(); 
				String xmlHeader = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"+"\n";
				String snippet = xstream.toXML(mBoxMessage);*/
			//System.out.println("Debug: toXML "+ snippet);
			//String xml = xmlHeader+snippet;

			// the following do not escape the whitespace char
			//System.out.println("Debug: escapeXml "+ StringEscapeUtils.escapeXml(snippet));

			// CR/LF/tab http://en.wikipedia.org/wiki/List_of_XML_and_HTML_character_entity_references
			//snippet = snippet.replaceAll("\\n", "&#10;").replaceAll("\\r", "&#13;").replaceAll("\\t", "&#9;");
			//System.out.println("Debug: myescape "+ snippet);

			IOUtilities.writeToFS(tokenizedTextString, outputDir+File.separator+uri, false);
		}
	}
}
