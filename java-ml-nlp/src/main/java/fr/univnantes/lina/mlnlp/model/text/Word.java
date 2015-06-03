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
package fr.univnantes.lina.mlnlp.model.text;

import java.util.ArrayList;
import java.util.List;

/**
 * @author hernandez
 *
 */

/**
 * Word representation
 * can be a word or a sentence (with words as subtokens) ; 
 * can it be a text with sentences as subtokens ?
 * 
 * coveredText 
 * options
 * + character offsets
 * + subtokens (list of tokens) 
 * 
 * 
 * @author hernandez
 *
 */
public class Word {
	private String coveredText;
	private int start;
	private int end;






	/** Constructor
	 * 
	 * @param coveredText
	 * @param start
	 * @param end
	 */
	public Word (String coveredText, int start, int end) {
		setCoveredText(coveredText);
		setStart(start);
		setEnd(end);
	}

	/** Constructor
	 * 
	 * @param coveredText
	 */
	public Word (String coveredText) {
		setCoveredText(coveredText);
	}

	/**
	 * @return the coveredText
	 */
	public String getCoveredText() {
		return coveredText;
	}
	
	
	/**
	 * @param coveredText the coveredText to set
	 */
	public void setCoveredText(String coveredText) {
		this.coveredText = coveredText;
	}
	/**
	 * @return the start
	 */
	public int getStart() {
		return start;
	}
	/**
	 * @param start the start to set
	 */
	public void setStart(int start) {
		this.start = start;
	}
	/**
	 * @return the end
	 */
	public int getEnd() {
		return end;
	}
	/**
	 * @param end the end to set
	 */
	public void setEnd(int end) {
		this.end = end;
	}

	public Boolean isContent() {
		return !getCoveredText().matches("^\\s*$");
	}
	
}