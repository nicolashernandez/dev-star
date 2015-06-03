/** 
 * 
 * Copyright (C) 2013  Nicolas Hernandez
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

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class RegularForms {

	/**
	 * numberString and number , ordinal String too
	 * number "^\\d+( \\d+)*(,\\d+)?$"
	 */
	public static final String numberPattern = "^(\\d+|une?|deux|trois|quatre|cinq|six|sept|huit|neuf|z(e|é)ro|dix|onze|douze|treize|quatorze|quinze|seize|vingts?|trentes?|quarantes?|cinquantes?|soixantes?|septentes?|huitantes?|nonentes?|cents?|milles?)((-|et|sur|,|\\.| )(\\d+|une?|deux|trois|quatre|cinq|six|sept|huit|neuf|z(e|é)ro|dix|onze|douze|treize|quatorze|quinze|seize|vingts?|trentes?|quarantes?|cinquantes?|soixantes?|septentes?|huitantes?|nonentes?|cents?|milles?|milliers?|millions?|milliards?|billions?|billiards?|trillions?|trilliards?|quadrilliards?|demi|tiers|quart|uni(è|e)me|deuxi(è|e)me|troisi(è|e)me|quatri(è|e)me|cinqui(è|e)me|sixi(è|e)me|septi(è|e)me|huiti(è|e)me|neuvi(è|e)me|dixi(è|e)me|vingti(è|e)me|trentri(è|e)me|quaranti(è|e)me|cinquanti(è|e)me|soixanti(è|e)me|septenti(è|e)me|huitanti(è|e)me|nonenti(è|e)me|onzi(è|e)me|douzi(è|e)me|treizi(è|e)me|quatorzi(è|e)me|quinzi(è|e)me|seizi(è|e)me|dix-septi(è|e)me|dix-huiti(è|e)me|dix-neuvi(è|e)me|centi(è|e)me|milli(è|e)me|millioni(è|e)me|milliardi(è|e)me))*$";
	
	/**
	 * is a regular form 
	 * @param candidateForm
	 * @param predefinedPattern
	 * @return
	 */
	 public static Boolean isRegularForm (String candidateForm, String predefinedPattern) {
		Pattern pattern = Pattern.compile(predefinedPattern, Pattern.CASE_INSENSITIVE);
		// In case you would like to ignore case sensitivity you could use this
		// statement
		// Pattern pattern = Pattern.compile("\\s+", Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(candidateForm);
		// Check 
		if (matcher.find()) return true;
		return false;	
	}
	
	/**
	 * is a numeric regular form 
	 * @param candidateForm
	 * @return
	 */
	public static Boolean isNumericRegularForm (String candidateForm) {
		return isRegularForm(candidateForm,numberPattern);	
	}
}
