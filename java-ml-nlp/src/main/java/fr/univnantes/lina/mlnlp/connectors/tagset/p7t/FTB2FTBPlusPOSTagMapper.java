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

import fr.univnantes.lina.mlnlp.connectors.tagset.TagsetMapper;

/**
 * @author hernandez
 *
 */
public class FTB2FTBPlusPOSTagMapper implements TagsetMapper {




	/**
	 * Map the ftb morpho-syntax features of a word to ftb+ tag 
	 * See Crabbe and Candito TALN'08 for the mapping table
	 * @see fr.univnantes.lina.uima.connectors.corpus.ftb.POSTagsetMapping_Interface#mapTag(java.util.Map)
	 * @return the ftb+ tag 
	 */
	@Override
	public String mapTag (Map<String, Object> wordMorphoSyntaxFeatures) {
		String tag = FTBConst.UTAG; // stands for Unknown or Undefined // default ftb+ tag value
	     
		// sequoia <w cat="V" lemma="aboutir" ei="V1s" ee="V--1s" tense="past" mph="1s">aboutisse</w>
		// rev/ext <w cat="V" catP="U" ee="V--1s" ei="V1s" gu="true" lemma="aboutir" mood="1" mph="1s" mw="false" po="false" poa="false" pogu="false" porf="false" rf="false" sw="true" tense="past">aboutisse</w>

		// 1 cat V mode indicatif
		if (((String)wordMorphoSyntaxFeatures.get(FTBConst.CAT_ATTRIBUTE)).equalsIgnoreCase(FTBConst.VERB_CAT) 
				&& (((String)wordMorphoSyntaxFeatures.get(FTBConst.MOOD_ATTRIBUTE)).equalsIgnoreCase("C") 
						|| ((String)wordMorphoSyntaxFeatures.get(FTBConst.MOOD_ATTRIBUTE)).equalsIgnoreCase("F") 
						|| ((String)wordMorphoSyntaxFeatures.get(FTBConst.MOOD_ATTRIBUTE)).equalsIgnoreCase("I") 
						|| ((String)wordMorphoSyntaxFeatures.get(FTBConst.MOOD_ATTRIBUTE)).equalsIgnoreCase("J") 
						|| ((String)wordMorphoSyntaxFeatures.get(FTBConst.MOOD_ATTRIBUTE)).equalsIgnoreCase("P") ) ) {
			tag = FTBConst.VERBEINDICATIF;
		}
		// 2 cat V mode imperatif		
		else if (((String)wordMorphoSyntaxFeatures.get(FTBConst.CAT_ATTRIBUTE)).equalsIgnoreCase(FTBConst.VERB_CAT) 
				&& (((String)wordMorphoSyntaxFeatures.get(FTBConst.MOOD_ATTRIBUTE)).equalsIgnoreCase("Y")) ) {
			tag = FTBConst.VERBEIMPERATIF;
		}
		// 3 cat V mode impératif		
		else if (((String)wordMorphoSyntaxFeatures.get(FTBConst.CAT_ATTRIBUTE)).equalsIgnoreCase(FTBConst.VERB_CAT) 
				&& (((String)wordMorphoSyntaxFeatures.get(FTBConst.MOOD_ATTRIBUTE)).equalsIgnoreCase("Y")) ) {
			tag = FTBConst.VERBEIMPERATIF;
		}
		// 4 cat V mode infinitif		
		else if (((String)wordMorphoSyntaxFeatures.get(FTBConst.CAT_ATTRIBUTE)).equalsIgnoreCase(FTBConst.VERB_CAT) 
				&& (((String)wordMorphoSyntaxFeatures.get(FTBConst.MOOD_ATTRIBUTE)).equalsIgnoreCase("W")) ) {
			tag = FTBConst.VERBEINFINITIF;
		}	
		// 5 cat V mode subjonctif		
		else if (((String)wordMorphoSyntaxFeatures.get(FTBConst.CAT_ATTRIBUTE)).equalsIgnoreCase(FTBConst.VERB_CAT) 
				&& (((String)wordMorphoSyntaxFeatures.get(FTBConst.MOOD_ATTRIBUTE)).equalsIgnoreCase("S") 
						|| ((String)wordMorphoSyntaxFeatures.get(FTBConst.MOOD_ATTRIBUTE)).equalsIgnoreCase("T")) ) {
			tag = FTBConst.VERBESUBJONCTIF;
		}
		// 6 cat V mode participe passé		
		else if (((String)wordMorphoSyntaxFeatures.get(FTBConst.CAT_ATTRIBUTE)).equalsIgnoreCase(FTBConst.VERB_CAT) 
				&& (((String)wordMorphoSyntaxFeatures.get(FTBConst.MOOD_ATTRIBUTE)).equalsIgnoreCase("K")) ) {
			tag = FTBConst.VERBEPARTICIPEPASSE;
		}	
		// 7 cat V mode participe présent		
		else if (((String)wordMorphoSyntaxFeatures.get(FTBConst.CAT_ATTRIBUTE)).equalsIgnoreCase(FTBConst.VERB_CAT) 
				&& (((String)wordMorphoSyntaxFeatures.get(FTBConst.MOOD_ATTRIBUTE)).equalsIgnoreCase("G")) ) {
			tag = FTBConst.VERBEPARTICIPEPRESENT;
		}
		// 8 cat N  subcat P	
		else if (((String)wordMorphoSyntaxFeatures.get(FTBConst.CAT_ATTRIBUTE)).equalsIgnoreCase(FTBConst.NOUN_CAT) 
				&& (((String)wordMorphoSyntaxFeatures.get(FTBConst.SUBCAT_ATTRIBUTE)).equalsIgnoreCase(FTBConst.PROPER_SUBCAT)) ) {
			tag = FTBConst.NOMPROPRE;
		}	
		// 9 cat N  subcat C	
		else if (((String)wordMorphoSyntaxFeatures.get(FTBConst.CAT_ATTRIBUTE)).equalsIgnoreCase(FTBConst.NOUN_CAT) 
				&& (((String)wordMorphoSyntaxFeatures.get(FTBConst.SUBCAT_ATTRIBUTE)).equalsIgnoreCase(FTBConst.COMMON_SUBCAT)) ) {
			tag = FTBConst.NOMCOMMUN;
		}	

		
		// 10 cat C  subcat P	
		else if (((String)wordMorphoSyntaxFeatures.get(FTBConst.CAT_ATTRIBUTE)).equalsIgnoreCase("C") 
				&& (((String)wordMorphoSyntaxFeatures.get(FTBConst.SUBCAT_ATTRIBUTE)).equalsIgnoreCase("S")) ) {
			tag = FTBConst.CONJONCTIONSUBORDINATION;
		}	
		// 11 cat C subcat C	
		else if (((String)wordMorphoSyntaxFeatures.get(FTBConst.CAT_ATTRIBUTE)).equalsIgnoreCase("C") 
				&& (((String)wordMorphoSyntaxFeatures.get(FTBConst.SUBCAT_ATTRIBUTE)).equalsIgnoreCase("C")) ) {
			tag = FTBConst.CONJONCTIONCOORDINATION;
		}
		// 12 cat CL subcat suj
		else if (((String)wordMorphoSyntaxFeatures.get(FTBConst.CAT_ATTRIBUTE)).equalsIgnoreCase("CL") 
				&& (((String)wordMorphoSyntaxFeatures.get(FTBConst.SUBCAT_ATTRIBUTE)).equalsIgnoreCase("suj")) ) {
			tag = FTBConst.CLITICSUJET;
		}
		
		// 13 cat CL subcat obj
		else if (((String)wordMorphoSyntaxFeatures.get(FTBConst.CAT_ATTRIBUTE)).equalsIgnoreCase("CL") 
				&& (((String)wordMorphoSyntaxFeatures.get(FTBConst.SUBCAT_ATTRIBUTE)).equalsIgnoreCase("obj")) ) {
			tag = FTBConst.CLITICOBJET;
		}
		// 14 cat CL subcat refl
		else if (((String)wordMorphoSyntaxFeatures.get(FTBConst.CAT_ATTRIBUTE)).equalsIgnoreCase("CL") 
				&& (((String)wordMorphoSyntaxFeatures.get(FTBConst.SUBCAT_ATTRIBUTE)).equalsIgnoreCase("refl")) ) {
			tag = FTBConst.CLITICREFLEXIF;
		}
		// 15 cat P subcat 
		else if (((String)wordMorphoSyntaxFeatures.get(FTBConst.CAT_ATTRIBUTE)).equalsIgnoreCase("P") ) {
			tag = FTBConst.PREPOSITION;
		}	
		// 16 and 17 cat P ; it is necessary to look at the context (next w cat D/PRO)
		// 18 cat I subcat 
		else if (((String)wordMorphoSyntaxFeatures.get(FTBConst.CAT_ATTRIBUTE)).equalsIgnoreCase("I") ) {
			tag = FTBConst.INTERJECTION;
		}
		// 19 cat ET subcat 
		else if (((String)wordMorphoSyntaxFeatures.get(FTBConst.CAT_ATTRIBUTE)).equalsIgnoreCase("ET") ) {
			tag = FTBConst.ETRANGER;
		}
		// 20 cat PONCT subcat 
		else if (((String)wordMorphoSyntaxFeatures.get(FTBConst.CAT_ATTRIBUTE)).equalsIgnoreCase("PONCT") ) {
			tag = FTBConst.PONCTUATION;
		}
		// 21 cat A subcat int
		else if (((String)wordMorphoSyntaxFeatures.get(FTBConst.CAT_ATTRIBUTE)).equalsIgnoreCase("A") 
				&& (((String)wordMorphoSyntaxFeatures.get(FTBConst.SUBCAT_ATTRIBUTE)).equalsIgnoreCase("int")) ) {
			tag = FTBConst.ADJINT;
		}
		// 22 cat A subcat not int
		else if (((String)wordMorphoSyntaxFeatures.get(FTBConst.CAT_ATTRIBUTE)).equalsIgnoreCase("A") 
				&& (!((String)wordMorphoSyntaxFeatures.get(FTBConst.SUBCAT_ATTRIBUTE)).equalsIgnoreCase("int")) ) {
			tag = FTBConst.ADJNOTINT;
		}
		

		
		// 23 cat ADV subcat int
		else if (((String)wordMorphoSyntaxFeatures.get(FTBConst.CAT_ATTRIBUTE)).equalsIgnoreCase("ADV") 
				&& (((String)wordMorphoSyntaxFeatures.get(FTBConst.SUBCAT_ATTRIBUTE)).equalsIgnoreCase("int")) ) {
			tag = FTBConst.ADVINT;
		}
		// 24 cat ADV subcat not int
		else if (((String)wordMorphoSyntaxFeatures.get(FTBConst.CAT_ATTRIBUTE)).equalsIgnoreCase("ADV") 
				&& (!((String)wordMorphoSyntaxFeatures.get(FTBConst.SUBCAT_ATTRIBUTE)).equalsIgnoreCase("int")) ) {
			tag = FTBConst.ADVNOTINT;
		}
		// 25 cat PRO subcat int
		else if (((String)wordMorphoSyntaxFeatures.get(FTBConst.CAT_ATTRIBUTE)).equalsIgnoreCase("PRO") 
				&& (((String)wordMorphoSyntaxFeatures.get(FTBConst.SUBCAT_ATTRIBUTE)).equalsIgnoreCase("int")) ) {
			tag = FTBConst.PROINT;
		}
		// 26 cat PRO subcat rel
		else if (((String)wordMorphoSyntaxFeatures.get(FTBConst.CAT_ATTRIBUTE)).equalsIgnoreCase("PRO") 
				&& (((String)wordMorphoSyntaxFeatures.get(FTBConst.SUBCAT_ATTRIBUTE)).equalsIgnoreCase("rel")) ) {
			tag = FTBConst.PROREL;
		}
		// 27 cat PRO subcat not (int|rel)
		else if (((String)wordMorphoSyntaxFeatures.get(FTBConst.CAT_ATTRIBUTE)).equalsIgnoreCase("PRO") 
				&& !(((String)wordMorphoSyntaxFeatures.get(FTBConst.SUBCAT_ATTRIBUTE)).equalsIgnoreCase("int") 
						||((String)wordMorphoSyntaxFeatures.get(FTBConst.SUBCAT_ATTRIBUTE)).equalsIgnoreCase("rel")
						) ) {
			tag = FTBConst.PRONOTINTNOTREL;
		}
		// 28 cat D subcat int
		else if (((String)wordMorphoSyntaxFeatures.get(FTBConst.CAT_ATTRIBUTE)).equalsIgnoreCase("D") 
				&& (((String)wordMorphoSyntaxFeatures.get(FTBConst.SUBCAT_ATTRIBUTE)).equalsIgnoreCase("int")) ) {
			tag = FTBConst.DETINT;
		}
		// 29 cat D subcat not int
		else if (((String)wordMorphoSyntaxFeatures.get(FTBConst.CAT_ATTRIBUTE)).equalsIgnoreCase("D") 
				&& (!((String)wordMorphoSyntaxFeatures.get(FTBConst.SUBCAT_ATTRIBUTE)).equalsIgnoreCase("int")) ) {
			tag = FTBConst.DETNOTINT;
		}
		// si amalgame fusionné a priori P+D 
		else if (((String)wordMorphoSyntaxFeatures.get(FTBConst.CAT_ATTRIBUTE)).equalsIgnoreCase(FTBConst.PREPPlusDET_CAT) ) {
			tag = FTBConst.PREPPlusDET_CAT;
		}
		// si amalgame fusionné a priori P+PRO
		else if (((String)wordMorphoSyntaxFeatures.get(FTBConst.CAT_ATTRIBUTE)).equalsIgnoreCase(FTBConst.PREPPlusDET_CAT)) {
			tag = FTBConst.PREPPlusDET_CAT;
			
		} 
		// 9' cat N  subcat card : million, huit, 2001 non native ftb2ftb+ rule in the paper 
		else if (((String)wordMorphoSyntaxFeatures.get(FTBConst.CAT_ATTRIBUTE)).equalsIgnoreCase(FTBConst.NOUN_CAT) 
				&& (((String)wordMorphoSyntaxFeatures.get(FTBConst.SUBCAT_ATTRIBUTE)).equalsIgnoreCase(FTBConst.CARD_SUBCAT)) ) {
			tag = FTBConst.NOMCOMMUN;
		}	
		// not from the paper 2008 but the README of the melt tagger install
		else if (((String)wordMorphoSyntaxFeatures.get(FTBConst.CAT_ATTRIBUTE)).equalsIgnoreCase(FTBConst.PREFIX_CAT)) {
			tag = FTBConst.PREFIX;
		}
		
		else {
			
			System.err.print("Warning: ftb 2 ftb+ - unknown mapping case - ");
			for (String k : wordMorphoSyntaxFeatures.keySet()) {
				System.err.print(" "+k+"='"+wordMorphoSyntaxFeatures.get(k)+"'");
			}
			System.err.println("");
			
//			System.err.println("Warning: ftb 2 ftb+ - unknown mapping case - lemma >"+ ((String)wordMorphoSyntaxFeatures.get(FTBConst.LEMMA_ATTRIBUTE))+ "< cat >"+ ((String)wordMorphoSyntaxFeatures.get(FTBConst.CAT_ATTRIBUTE))+ "< subcat >"+ ((String)wordMorphoSyntaxFeatures.get(FTBConst.SUBCAT_ATTRIBUTE)) + "< mph >"+ ((String)wordMorphoSyntaxFeatures.get(FTBConst.MPH_ATTRIBUTE)));
			// By default return the cat value ; tag = ((String)wordMorphoSyntaxFeatures.get(FTBXMLSchema.CAT_ATTRIBUTE);
		}
		return tag;
	}


}
