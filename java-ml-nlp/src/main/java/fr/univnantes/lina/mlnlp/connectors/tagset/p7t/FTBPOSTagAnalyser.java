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

import java.util.Map;

import fr.univnantes.lina.mlnlp.connectors.tagset.TagsetAnalyser;

/**
 * Implementation of the TagsetAnalyser for the ftb
 * 
 * @author hernandez
 *
 */
public class FTBPOSTagAnalyser implements TagsetAnalyser {


	/*
	 * LOCAL VARIABLES
	 */
	/**
	 * MOOD Short
	 */
	private static String ind = "ind"; 
	private static String part = "part";
	private static String subj = "subj";
	private static String inf = "inf";
	private static String imp = "imp";

	/**
	 * TENSE Short
	 */
	private static String cond = "cond"; 
	private static String fut = "fut"; 
	private static String pst = "pst";
	private static String impft = "impft";
	private static String past = "past";

	/**
	 * 		//  bonsai (candito coling10) input malt
		//  51 m=ind
	    //  13 m=inf
	    //  19 m=part
	    //  3 m=subj
		//
	 * @return
	 */
	static public String moodOfMood (String mood) {
		// C présent conditionnel		conditionnel		conditionnel	?			V				ind			cond		present
		if (mood.equalsIgnoreCase("C")) return ind;
		// F futur indicatif			indicatif			futur			V							ind			fut			futur
		else 	if (mood.equalsIgnoreCase("F")) return ind;
		// G participe présent			participe présent	présent			VPR							part		pst			present
		else 	if (mood.equalsIgnoreCase("G")) return part;
		// I imparfait indicatif		indicatif			passé			V							ind			impft		imparfait
		else 	if (mood.equalsIgnoreCase("I")) return ind;
		// J passé simple indicatif		indicatif			passé			V							ind			past		passé simple
		else 	if (mood.equalsIgnoreCase("J")) return ind;
		// K participe passé			participe passé		passé			VPP							part		past		passé
		else 	if (mood.equalsIgnoreCase("K")) return part;
		// P présent indicatif			indicatif			présent			V							ind			pst			présent
		else 	if (mood.equalsIgnoreCase("P")) return ind;
		// S présent subjonctif			subjonctif			présent			VS							subj		pst			present
		else 	if (mood.equalsIgnoreCase("S")) return subj;
		// T imparfait subjonctif		subjonctif			passé			VS							subj		past		imparfait
		else 	if (mood.equalsIgnoreCase("T")) return subj;
		// W infinitif					infinitif			unknown			VINF						inf						?
		else 	if (mood.equalsIgnoreCase("W")) return inf;
		// Y impératif					impératif			présent			VIMP						imp			pst		présent
		else 	if (mood.equalsIgnoreCase("Y")) return imp;
		return "";
	}

	/**
	 * 		//  bonsai (candito coling10) input malt

			//  2 t=cond
		    //  5 t=fut
		    //  4 t=impft
		    //  19 t=past
		    //  42 t=pst
	 * @return
	 */

	static public String tenseOfMood (String mood) {
		// C présent conditionnel		conditionnel		conditionnel	?			V				ind			cond		present
		if (mood.equalsIgnoreCase("C")) return cond;
		// F futur indicatif			indicatif			futur			V							ind			fut			futur
		else 	if (mood.equalsIgnoreCase("F")) return fut;
		// G participe présent			participe présent	présent			VPR							part		pst			present
		else 	if (mood.equalsIgnoreCase("G")) return pst;
		// I imparfait indicatif		indicatif			passé			V							ind			impft		imparfait
		else 	if (mood.equalsIgnoreCase("I")) return impft;
		// J passé simple indicatif		indicatif			passé			V							ind			past		passé simple
		else 	if (mood.equalsIgnoreCase("J")) return past;
		// K participe passé			participe passé		passé			VPP							part		past		passé
		else 	if (mood.equalsIgnoreCase("K")) return past;
		// P présent indicatif			indicatif			présent			V							ind			pst			présent
		else 	if (mood.equalsIgnoreCase("P")) return pst;
		// S présent subjonctif			subjonctif			présent			VS							subj		pst			present
		else 	if (mood.equalsIgnoreCase("S")) return pst;
		// T imparfait subjonctif		subjonctif			passé			VS							subj		past		imparfait
		else 	if (mood.equalsIgnoreCase("T")) return past;
		// W infinitif					infinitif			unknown			VINF						inf						?
		else 	if (mood.equalsIgnoreCase("W")) return "";
		// Y impératif					impératif			présent			VIMP						imp			pst		présent
		else 	if (mood.equalsIgnoreCase("Y")) return pst;
		return "";
	}

	/**
	 * Analyse the ftb morpho-syntax features of a word
	 * See ftb "Guide des mots simples et composés" for the tag meaning (annexes)
	 * Take in parameters String variables which corresponds to the  morpho-syntax description of a tag set for a word
	 * 
	 * INPUT
	 * 	FTBXMLSchema.CAT_ATTRIBUTE
	 *  FTBXMLSchema.MPH_ATTRIBUTE
	 * 
	 * OUTPUT
	 *  FTBXMLSchema.MOOD_ATTRIBUTE (code both mood and tense)
	 *  FTBXMLSchema.GENDER_ATTRIBUTE
	 *  FTBXMLSchema.NUMBER_ATTRIBUTE
	 *  FTBXMLSchema.PERSON_ATTRIBUTE
	 *  FTBXMLSchema.POSSNUMBER_ATTRIBUTE
	 * 
	 * @see fr.univnantes.lina.uima.connectors.corpus.ftb.POSTagsetMapping_Interface#analyseTag(java.lang.Object[])
	 * @return a map of detailed morpho-syntax features
	 */
	@Override
	public Map<String, Object> analyseTag (Map<String, Object> wordMorphoSyntaxFeatures) {


		wordMorphoSyntaxFeatures.put(FTBConst.MOOD_ATTRIBUTE, "");
		wordMorphoSyntaxFeatures.put(FTBConst.GENDER_ATTRIBUTE, "");
		wordMorphoSyntaxFeatures.put(FTBConst.NUMBER_ATTRIBUTE, "");
		wordMorphoSyntaxFeatures.put(FTBConst.PERSON_ATTRIBUTE, "");
		wordMorphoSyntaxFeatures.put(FTBConst.POSSNUMBER_ATTRIBUTE, ""); // mon, notre


		// Get the Verb features
		// V verb
		// mode							ftb+ mode			nh mode			ftb+ tag	nh ftb+ tag		mood short candito	tense short candito	tense (based on mode)
		// C présent conditionnel		conditionnel		conditionnel	?			V				ind			cond		present
		// F futur indicatif			indicatif			futur			V							ind			fut			futur
		// G participe présent			participe présent	présent			VPR							part		pst			present
		// I imparfait indicatif		indicatif			passé			V							ind			impft		imparfait
		// J passé simple indicatif		indicatif			passé			V							ind			past		passé simple
		// K participe passé			participe passé		passé			VPP							part		past		passé
		// P présent indicatif			indicatif			présent			V							ind			pst			présent
		// S présent subjonctif			subjonctif			présent			VS							subj		pst			present
		// T imparfait subjonctif		subjonctif			passé			VS							subj		past		imparfait
		// W infinitif					infinitif			unknown			VINF						inf						?
		// Y impératif					impératif			présent			VIMP						imp			pst		présent
		// * Ai regardé les data/lexicon dans https://gforge.inria.fr/frs/download.php/27240/melt-0.6.tar.gz
		// person 1, 2, 3
		// number s, p
		// gender m, f
		//
		//  bonsai (candito coling10) input malt
		//  51 m=ind
		//  13 m=inf
		//  19 m=part
		//  3 m=subj
		//
		//  2 t=cond
		//  5 t=fut
		//  4 t=impft
		//  19 t=past
		//  42 t=pst
		//
		//
		if (((String)wordMorphoSyntaxFeatures.get(FTBConst.CAT_ATTRIBUTE)).equalsIgnoreCase("V")) {	
			// Get the mode
			if (!((String)wordMorphoSyntaxFeatures.get(FTBConst.MPH_ATTRIBUTE)).equalsIgnoreCase("")) 
				wordMorphoSyntaxFeatures.put(FTBConst.MOOD_ATTRIBUTE, ((String)wordMorphoSyntaxFeatures.get(FTBConst.MPH_ATTRIBUTE)).substring(0, 1)); 

			// Get the person 
			// Get the number
			if ((((String)wordMorphoSyntaxFeatures.get(FTBConst.MPH_ATTRIBUTE)).length() == 3) 
					&& (((String)wordMorphoSyntaxFeatures.get(FTBConst.MOOD_ATTRIBUTE)).equalsIgnoreCase("P") 
							|| ((String)wordMorphoSyntaxFeatures.get(FTBConst.MOOD_ATTRIBUTE)).equalsIgnoreCase("F") 
							|| ((String)wordMorphoSyntaxFeatures.get(FTBConst.MOOD_ATTRIBUTE)).equalsIgnoreCase("J") 
							|| ((String)wordMorphoSyntaxFeatures.get(FTBConst.MOOD_ATTRIBUTE)).equalsIgnoreCase("C") 
							|| ((String)wordMorphoSyntaxFeatures.get(FTBConst.MOOD_ATTRIBUTE)).equalsIgnoreCase("I") 
							|| ((String)wordMorphoSyntaxFeatures.get(FTBConst.MOOD_ATTRIBUTE)).equalsIgnoreCase("S")
							||((String) wordMorphoSyntaxFeatures.get(FTBConst.MOOD_ATTRIBUTE)).equalsIgnoreCase("T")
							|| ((String)wordMorphoSyntaxFeatures.get(FTBConst.MOOD_ATTRIBUTE)).equalsIgnoreCase("Y")) ) {
				wordMorphoSyntaxFeatures.put(FTBConst.PERSON_ATTRIBUTE,((String)wordMorphoSyntaxFeatures.get(FTBConst.MPH_ATTRIBUTE)).substring(1, 2)); // 1, 2, 3
				wordMorphoSyntaxFeatures.put(FTBConst.NUMBER_ATTRIBUTE,((String)wordMorphoSyntaxFeatures.get(FTBConst.MPH_ATTRIBUTE)).substring(2, 3)); // s, p
			}

			// Get the gender
			// Get the number
			if ((((String)wordMorphoSyntaxFeatures.get(FTBConst.MPH_ATTRIBUTE)).length() == 3) 
					&& (((String)wordMorphoSyntaxFeatures.get(FTBConst.MOOD_ATTRIBUTE)).equalsIgnoreCase("K"))) {
				wordMorphoSyntaxFeatures.put(FTBConst.GENDER_ATTRIBUTE,((String)wordMorphoSyntaxFeatures.get(FTBConst.MPH_ATTRIBUTE)).substring(1, 2)); // m, f
				wordMorphoSyntaxFeatures.put(FTBConst.NUMBER_ATTRIBUTE,((String)wordMorphoSyntaxFeatures.get(FTBConst.MPH_ATTRIBUTE)).substring(2, 3)); // s, p
			}

			//System.out.println("Debug: cat "+cat+" subcat "+subcat+" mph "+mph+ " mode "
			//		+mode+" person "+person+" gender "+gender+" number "+number);
		}
		// Get the Noun features
		else if (((String)wordMorphoSyntaxFeatures.get(FTBConst.CAT_ATTRIBUTE)).equalsIgnoreCase("N")) {	
			// subcat P Proper C Common
			if (((String)wordMorphoSyntaxFeatures.get(FTBConst.MPH_ATTRIBUTE)).length() == 2) { 
				wordMorphoSyntaxFeatures.put(FTBConst.GENDER_ATTRIBUTE,((String)wordMorphoSyntaxFeatures.get(FTBConst.MPH_ATTRIBUTE)).substring(0, 1)); // m, f
				wordMorphoSyntaxFeatures.put(FTBConst.NUMBER_ATTRIBUTE,((String)wordMorphoSyntaxFeatures.get(FTBConst.MPH_ATTRIBUTE)).substring(1, 2)); // s, p
			}
		}
		// Get the C conjonction features
		else if (((String)wordMorphoSyntaxFeatures.get(FTBConst.CAT_ATTRIBUTE)).equalsIgnoreCase("C")) {				
			// subcat S subordinatin C Coordination
		}
		// Get the CL clitic features		
		else if (((String)wordMorphoSyntaxFeatures.get(FTBConst.CAT_ATTRIBUTE)).equalsIgnoreCase("CL")) {
			// subcat refl, suj, obj 
			if (((String)wordMorphoSyntaxFeatures.get(FTBConst.MPH_ATTRIBUTE)).length() == 3) {
				wordMorphoSyntaxFeatures.put(FTBConst.PERSON_ATTRIBUTE,((String)wordMorphoSyntaxFeatures.get(FTBConst.MPH_ATTRIBUTE)).substring(0, 1)); // 1, 2, 3
				wordMorphoSyntaxFeatures.put(FTBConst.GENDER_ATTRIBUTE,((String)wordMorphoSyntaxFeatures.get(FTBConst.MPH_ATTRIBUTE)).substring(1, 2)); // m, f
				wordMorphoSyntaxFeatures.put(FTBConst.NUMBER_ATTRIBUTE,((String)wordMorphoSyntaxFeatures.get(FTBConst.MPH_ATTRIBUTE)).substring(2, 3)); // s, p
			}
		}
		// Get the P preposition features		
		else if (((String)wordMorphoSyntaxFeatures.get(FTBConst.CAT_ATTRIBUTE)).equalsIgnoreCase("P")) {
			// no subcat
		}		
		// Get the I Interjection  features		
		else if (((String)wordMorphoSyntaxFeatures.get(FTBConst.CAT_ATTRIBUTE)).equalsIgnoreCase("I")) {
			// no subcat 
		}	
		// Get the PONCT features		
		else if (((String)wordMorphoSyntaxFeatures.get(FTBConst.CAT_ATTRIBUTE)).equalsIgnoreCase("PONCT")) {
			// subcat S, W
		}
		// Get the Foreign word features		
		else if (((String)wordMorphoSyntaxFeatures.get(FTBConst.CAT_ATTRIBUTE)).equalsIgnoreCase("ET")) {
			// no subcat 
		}
		// Get the Adjective features		
		else if (((String)wordMorphoSyntaxFeatures.get(FTBConst.CAT_ATTRIBUTE)).equalsIgnoreCase("A")) {
			// subcat ind, qual, ord
			if (((String)wordMorphoSyntaxFeatures.get(FTBConst.MPH_ATTRIBUTE)).length() == 2) {
				wordMorphoSyntaxFeatures.put(FTBConst.GENDER_ATTRIBUTE,((String)wordMorphoSyntaxFeatures.get(FTBConst.MPH_ATTRIBUTE)).substring(0, 1)); // m, f
				wordMorphoSyntaxFeatures.put(FTBConst.NUMBER_ATTRIBUTE,((String)wordMorphoSyntaxFeatures.get(FTBConst.MPH_ATTRIBUTE)).substring(1, 2)); // s, p
			}
		}
		// Get the Adverbe features		
		else if (((String)wordMorphoSyntaxFeatures.get(FTBConst.CAT_ATTRIBUTE)).equalsIgnoreCase("ADV")) {
			// subcat int, neg
		}
		// Get the prefix features		
		else if (((String)wordMorphoSyntaxFeatures.get(FTBConst.CAT_ATTRIBUTE)).equalsIgnoreCase("PREF")) {
			// no subcat
		}
		// Get the pronoun features		
		else if (((String)wordMorphoSyntaxFeatures.get(FTBConst.CAT_ATTRIBUTE)).equalsIgnoreCase("PRO")) {
			// subcat rel, dem, ind, int, pers, poss, refl, neg
			if (((String)wordMorphoSyntaxFeatures.get(FTBConst.MPH_ATTRIBUTE)).length() == 3) {
				wordMorphoSyntaxFeatures.put(FTBConst.PERSON_ATTRIBUTE,((String)wordMorphoSyntaxFeatures.get(FTBConst.MPH_ATTRIBUTE)).substring(0, 1)); // 1, 2, 3
				wordMorphoSyntaxFeatures.put(FTBConst.GENDER_ATTRIBUTE,((String)wordMorphoSyntaxFeatures.get(FTBConst.MPH_ATTRIBUTE)).substring(1, 2)); // m, f
				wordMorphoSyntaxFeatures.put(FTBConst.NUMBER_ATTRIBUTE,((String)wordMorphoSyntaxFeatures.get(FTBConst.MPH_ATTRIBUTE)).substring(2, 3)); // s, p
			}
		}
		// Get the Determinant features		
		else if (((String)wordMorphoSyntaxFeatures.get(FTBConst.CAT_ATTRIBUTE)).equalsIgnoreCase("D")) {
			// subcat def, poss, dem, ind, card, excl, int, neg, part
			if (((String)wordMorphoSyntaxFeatures.get(FTBConst.MPH_ATTRIBUTE)).length() == 2) {
				wordMorphoSyntaxFeatures.put(FTBConst.GENDER_ATTRIBUTE,((String)wordMorphoSyntaxFeatures.get(FTBConst.MPH_ATTRIBUTE)).substring(0, 1)); // m, f
				wordMorphoSyntaxFeatures.put(FTBConst.NUMBER_ATTRIBUTE,((String)wordMorphoSyntaxFeatures.get(FTBConst.MPH_ATTRIBUTE)).substring(1, 2)); // s, p
			}
			else if (((String)wordMorphoSyntaxFeatures.get(FTBConst.MPH_ATTRIBUTE)).length() == 4) {
				wordMorphoSyntaxFeatures.put(FTBConst.PERSON_ATTRIBUTE,((String)wordMorphoSyntaxFeatures.get(FTBConst.MPH_ATTRIBUTE)).substring(0, 1)); // 1, 2, 3
				wordMorphoSyntaxFeatures.put(FTBConst.GENDER_ATTRIBUTE,((String)wordMorphoSyntaxFeatures.get(FTBConst.MPH_ATTRIBUTE)).substring(1, 2)); // m, f
				wordMorphoSyntaxFeatures.put(FTBConst.NUMBER_ATTRIBUTE,((String)wordMorphoSyntaxFeatures.get(FTBConst.MPH_ATTRIBUTE)).substring(2, 3)); // s, p
				wordMorphoSyntaxFeatures.put(FTBConst.POSSNUMBER_ATTRIBUTE,((String)wordMorphoSyntaxFeatures.get(FTBConst.MPH_ATTRIBUTE)).substring(3, 4)); // s, p
				// D-poss-1mss MON ami
				// D-poss-2fps TES fleurs
				// D-poss-1msp NOTRE chien
				// D-poss-2fpp VOS fleurs
			}
		}
		// Get P+D 
		//<w amalgame="yes" cat="P+D" ee="P+D-def-ms" ei="P+Ddefms" lemma="de le" mph="ms" subcat="def">du</w>
		else if (((String)wordMorphoSyntaxFeatures.get(FTBConst.CAT_ATTRIBUTE)).equalsIgnoreCase("P+D")) {
			// subcat def, poss, dem, ind, card, excl, int, neg, part
			if (((String)wordMorphoSyntaxFeatures.get(FTBConst.MPH_ATTRIBUTE)).length() == 2) {
				wordMorphoSyntaxFeatures.put(FTBConst.GENDER_ATTRIBUTE,((String)wordMorphoSyntaxFeatures.get(FTBConst.MPH_ATTRIBUTE)).substring(0, 1)); // m, f
				wordMorphoSyntaxFeatures.put(FTBConst.NUMBER_ATTRIBUTE,((String)wordMorphoSyntaxFeatures.get(FTBConst.MPH_ATTRIBUTE)).substring(1, 2)); // s, p
			}

		}
		// Get P+PRO
		else if (((String)wordMorphoSyntaxFeatures.get(FTBConst.CAT_ATTRIBUTE)).equalsIgnoreCase("P+PRO")) {
			// subcat def, poss, dem, ind, card, excl, int, neg, part
			if (((String)wordMorphoSyntaxFeatures.get(FTBConst.MPH_ATTRIBUTE)).length() == 3) {
				wordMorphoSyntaxFeatures.put(FTBConst.PERSON_ATTRIBUTE,((String)wordMorphoSyntaxFeatures.get(FTBConst.MPH_ATTRIBUTE)).substring(0, 1)); // 1, 2, 3
				wordMorphoSyntaxFeatures.put(FTBConst.GENDER_ATTRIBUTE,((String)wordMorphoSyntaxFeatures.get(FTBConst.MPH_ATTRIBUTE)).substring(1, 2)); // m, f
				wordMorphoSyntaxFeatures.put(FTBConst.NUMBER_ATTRIBUTE,((String)wordMorphoSyntaxFeatures.get(FTBConst.MPH_ATTRIBUTE)).substring(2, 3)); // s, p
			}

		}
		// Unknwon cat value	
		else {

			System.err.print("Warning: ftb tag analysis - unknown cat value ");
			for (String k : wordMorphoSyntaxFeatures.keySet()) {
				System.err.print(" "+k+"='"+wordMorphoSyntaxFeatures.get(k)+"'");
			}
			System.err.println("");

			/*System.err.println("2. Warning: ftb tag analysis - unknown cat value (is it a word ? may be a part of mw) - cat >"+wordMorphoSyntaxFeatures.get(FTBConst.CAT_ATTRIBUTE)+"< subcat >"+wordMorphoSyntaxFeatures.get(FTBConst.SUBCAT_ATTRIBUTE)+"< mph >"+wordMorphoSyntaxFeatures.get(FTBConst.MPH_ATTRIBUTE)+ "< mode >"
					+wordMorphoSyntaxFeatures.get(FTBConst.MOOD_ATTRIBUTE)+"< person >"+wordMorphoSyntaxFeatures.get(FTBConst.PERSON_ATTRIBUTE)
					+"< gender >"+wordMorphoSyntaxFeatures.get(FTBConst.GENDER_ATTRIBUTE)
					+"< number >"+wordMorphoSyntaxFeatures.get(FTBConst.NUMBER_ATTRIBUTE));
			 */
		}

		//
		return wordMorphoSyntaxFeatures;
	}




}
