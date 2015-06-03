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
package fr.univnantes.lina.mlnlp.process.tokenization;

import java.util.List;
import java.util.Set;

import fr.univnantes.lina.mlnlp.model.text.Text;
import fr.univnantes.lina.mlnlp.model.text.Word;

/**
 * Both for sentence splitting and word tokenization
 * @author hernandez
 *
 */
public abstract class Tokenizer {


	

	/**
	 * @param whitespaceAsSentence TODO
	 * @param whitespaceAsToken TODO
	 * 
	 */
	public abstract Text splitIntoSentences (int textStart, String text, Boolean whitespaceAsSentence, Boolean whitespaceAsToken);
	
	/**
	 * @param notATokenSeparator TODO
	 * @param isATokenSeparator TODO
	 * */
	public abstract List<Word> tokenizeIntoWords(int sentenceStart, String sentence, Boolean wsAsToken, Set<Integer> notATokenSeparator, Set<Integer> isATokenSeparator);

	//public abstract Text splitHTMLTextIntoSentences(int textStart, String text,
	//		Boolean whitespaceAsSentence, Boolean whitespaceAsToken);
	
	 
}
