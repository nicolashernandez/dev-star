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
package fr.univnantes.lina.mlnlp.model.text;

import java.util.HashMap;
import java.util.Map;

import fr.univnantes.lina.mlnlp.connectors.tagset.p7t.FTBConst;

/**
 * Object to represent a word
 * 
 * @author hernandez
 *
 */
public class MorphoWord {


	private Map<String,Object> currentWordMorphoSyntaxFeaturesMap = null;
	

	/**
	 * @return the currentWordMorphoSyntaxFeaturesMap
	 */
	public Map<String, Object> getfeatures() {
		return currentWordMorphoSyntaxFeaturesMap;
	}

	/**
	 * @param currentWordMorphoSyntaxFeaturesMap the currentWordMorphoSyntaxFeaturesMap to set
	 */
	public void setCurrentWordMorphoSyntaxFeaturesMap(
			Map<String, Object> currentWordMorphoSyntaxFeaturesMap) {
		this.currentWordMorphoSyntaxFeaturesMap = currentWordMorphoSyntaxFeaturesMap;
	}


	/**
	 * 
	 */
	public MorphoWord() {
		setCurrentWordMorphoSyntaxFeaturesMap(new HashMap<String, Object>());
	}




	/**
	 * is the word a compound / a multi-words expression currentWordMorphoSyntaxFeaturesMap
	 */
	public Boolean isMultiWords () {
		return Boolean.valueOf((String)getfeatures().get(FTBConst.isMultiWords));
	}




	/**
	 * is part of a multi words expressions currentWordMorphoSyntaxFeaturesMap
	 */
	public Boolean isPartOfMultiWords () {
		return Boolean.valueOf((String)getfeatures().get(FTBConst.isPartOfMultiWords));

	}


	/**
	 * is part of a graphical unit multi words expressions currentWordMorphoSyntaxFeaturesMap
	 */
	public Boolean isPartOfGraphicUnitMultiWords () {
		return Boolean.valueOf((String)getfeatures().get(FTBConst.isPartOfGraphicUnitMultiWords));

	}
	
	/**
	 * is a simple word (not part of a multi) currentWordMorphoSyntaxFeaturesMap
	 */
	public Boolean isSingleWord () {
		return Boolean.valueOf((String)getfeatures().get(FTBConst.isSingleWord));

	}


	/**
	 * is the word a graphic unit currentWordMorphoSyntaxFeaturesMap
	 */
	public Boolean isGraphicUnit () {
		return Boolean.valueOf((String)getfeatures().get(FTBConst.isGraphicUnit));

	}

	/**
	 * is a regular form currentWordMorphoSyntaxFeaturesMap
	 */
	public Boolean isNumericRegularForm () {
		return Boolean.valueOf((String)getfeatures().get(FTBConst.isRegularForm));
	}

	/**
	 * is part of a numeric regular form multi words expressions currentWordMorphoSyntaxFeaturesMap
	 */
	public Boolean isPartOfRegularFormMultiWords () {
		return Boolean.valueOf((String)getfeatures().get(FTBConst.isPartOfRegularFormMultiwords));

	}


	/**
	 * belongs to a dictionary (list of multi words adverbs or preposition as UIMA resources) currentWordMorphoSyntaxFeaturesMap
	 * 
	 */
	public Boolean isDictionaryStableForm () {
		//TODO
		return false;
	}


	/**
	 * is the part of an amalgame (e.g. du, au) currentXmlElementAnnotation
	 * 
	<w cat="P" ee="P" ei="P" lemma="à"/>
	<w cat="PRO" ee="PRO-rel-3mp" ei="PROR3mp" lemma="lequel" mph="3mp" subcat="rel">auxquels</w>

    <w cat="P" ee="P" ei="P" lemma="à">au</w>
    <w cat="D" ee="D-def-ms" ei="Dms" lemma="le" mph="ms" subcat="def"/>

	 */
	


	/**
	 * is the second part of an amalgame (e.g. du, au) currentWordMorphoSyntaxFeaturesMap
	 */
	public Boolean isPartOfAmalgame  () {
		return Boolean.valueOf((String)getfeatures().get(FTBConst.isPartOfAmalgame));
	}
	
	/**
	 * 
	 */
	public void display() {
		System.out.print("Debug: wordStructure ");
		for (String k : getfeatures().keySet()) {
			System.out.print(" "+k+"='"+getfeatures().get(k)+"'");
		}
		System.out.println("");

	}

	

}
