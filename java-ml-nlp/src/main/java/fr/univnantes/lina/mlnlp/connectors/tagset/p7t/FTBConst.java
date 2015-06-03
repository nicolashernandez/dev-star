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
package fr.univnantes.lina.mlnlp.connectors.tagset.p7t;

public class FTBConst {
	
	/**
	 * other
	 */
	public static final String coverText = "text";

	public static final String begin = "begin";
	public static final String end = "end";

	/**
	 * Element
	 */
	public static final String WORD_ELEMENT = "w";
	public static final String SENTENCE_ELEMENT = "SENT";

	/**
	 * Attribute 
	 */
	public static final String COMPOUND_ATTRIBUTE = "compound";
	public static final String CAT_ATTRIBUTE = "cat";
	public static final String CATINT_ATTRIBUTE = "catint";
	public static final String SUBCAT_ATTRIBUTE = "subcat";
	public static final String LEMMA_ATTRIBUTE = "lemma";
	public static final String MPH_ATTRIBUTE = "mph";
	public static final String EE_ATTRIBUTE  = "ee";
	public static final String EI_ATTRIBUTE  = "ei";
	public static final String NEXT_ATTRIBUTE  = "next";
	public static final String PREV_ATTRIBUTE  = "prev";
	public static final String WARNING_ATTRIBUTE  = "warning";
	public static final String ID_ATTRIBUTE  = "id";
	
	// add by revision
	public static final String AMALGAME_ATTRIBUTE = "amalgame";

	/**
	 * Analyzed attributes
	 */
	public static final String GENDER_ATTRIBUTE = "gender";
	public static final String NUMBER_ATTRIBUTE = "number";
	public static final String MOOD_ATTRIBUTE = "mood";
	public static final String PERSON_ATTRIBUTE = "person";
	public static final String POSSNUMBER_ATTRIBUTE = "pn";
	public static final String TENSE_ATTRIBUTE = "tense";
	public static final String CASEF_ATTRIBUTE = "case";

	public static String uCatValue = "U"; // undefined or unknown value
	
	public static String isMultiWords = "mw";
	public static String isPartOfMultiWords = "po";
	public static String isPartOfGraphicUnitMultiWords = "pogu";
	public static String isSingleWord = "sw";
	public static String isGraphicUnit = "gu";
	public static String isRegularForm = "rf";
	public static String isPartOfRegularFormMultiwords = "porf";

	public static String isPartOfAmalgame = "poa";
	
	/**
	 * FTB cat values
	 */
	public static final String PREPOSITION_CAT  ="P";
	public static final String DETERMINER_CAT  ="D";
	public static final String PREPPlusDET_CAT  ="P+D";
	public static final String PREPPlusPRO_CAT  ="P+PRO";
	public static final String NOUN_CAT  ="N";
	public static final String PREFIX_CAT  ="PREF";
	public static final String VERB_CAT  ="V";

	/**
	 * FTB subcat values
	 */
	public static final String COMMON_SUBCAT  ="C";
	public static final String PROPER_SUBCAT  ="P";
	public static final String CARD_SUBCAT  ="card";
	
	
	
	/**
	 * FTB PLUS ATTRIBUTES
	 */
	public static final String ftbPlusCat = "catP";
	
	
	/**
	 * FTB PLUS TAG VALUES
	 * 
	 * dans le README de l'install disponible ici https://gforge.inria.fr/projects/lingwb
ADJ        adjective
ADJWH      interrogative adjective
ADV        adverb
ADVWH      interrogative adverb
CC         coordination conjunction
CLO        object clitic pronoun
CLR        reflexive clitic pronoun
CLS        subject clitic pronoun
CS         subordination conjunction
DET        determiner
DETWH      interrogative determiner
ET         foreign word
I          interjection
NC         common noun
NPP        proper noun
P          preposition
P+D        preposition+determiner amalgam
P+PRO      prepositon+pronoun amalgam
PONCT      punctuation mark
PREF       prefix
PRO        full pronoun
PROREL     relative pronoun
PROWH      interrogative pronoun
V          indicative or conditional verb form
VIMP       imperative verb form
VINF       infinitive verb form
VPP        past participle
VPR        present participle
VS         subjunctive verb form

	 */
	public static final String UTAG = "U";  // stands for Unknown or Undefined
	
	
	
	public static final String VERBEINDICATIF = "V"; 
	public static final String VERBEIMPERATIF = "VIMP"; 
	public static final String VERBEINFINITIF = "VINF"; 
	public static final String VERBESUBJONCTIF = "VS"; 
	public static final String VERBEPARTICIPEPASSE = "VPP"; 
	public static final String VERBEPARTICIPEPRESENT = "VPR"; 
	public static final String NOMPROPRE = "NPP"; 
	public static final String NOMCOMMUN = "NC"; 
	public static final String CONJONCTIONCOORDINATION = "CC"; 
	public static final String CONJONCTIONSUBORDINATION = "CS"; 
	public static final String CLITICSUJET = "CLS"; 
	public static final String CLITICOBJET = "CLO"; 
	public static final String CLITICREFLEXIF = "CLR"; 
	public static final String PREPOSITION = "P"; 
	public static final String INTERJECTION = "I"; 
	public static final String ETRANGER = "ET"; 
	public static final String PONCTUATION = "PONCT"; 
	public static final String ADJINT = "ADJWH"; 
	public static final String ADJNOTINT = "ADJ"; 
	public static final String ADVINT = "ADVWH"; 
	public static final String ADVNOTINT = "ADV";
	public static final String PROINT = "PROWH";
	public static final String PROREL = "PROREL";
	public static final String PRONOTINTNOTREL = "PRO";
	public static final String DETINT = "DETWH";
	public static final String DETNOTINT = "DET";
	public static final String PREFIX = "PREF"; // not from the paper 2008 but the README of the melt tagger install

	

}
