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

import static org.w3c.dom.Node.DOCUMENT_TYPE_NODE;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import fr.univnantes.lina.mlnlp.connectors.tagset.p7t.FTBConst;
import fr.univnantes.lina.mlnlp.model.text.MorphoWord;
import fr.univnantes.lina.mlnlp.process.transformation.RegularForms;

public class FTBWordXMLElementAnalyzed {

	private Element currentElement;


	/**
	 * @return the anElement
	 */
	public Element getElement() {
		return currentElement;
	}

	/**
	 * @param anElement the anElement to set
	 */
	public void setElementAnalyzed(
			Element currentElement) {
		this.currentElement = currentElement;
	}

	/**
	 * 
	 * @param currentElement
	 */
	public FTBWordXMLElementAnalyzed (Element currentElement) {
		setElementAnalyzed(currentElement);
	}


	/**
	 * is the word a compound / a multi-words expression currentElement
	 */
	public Boolean isMultiWords (Element currentElement) {
		NodeList childNodes = currentElement.getChildNodes();

		int  i= 0;
		Boolean foundWChild = false;
		while (i < childNodes.getLength() && !foundWChild) {
			if (childNodes.item(i) instanceof Element) {
				Element aElement = (Element) childNodes.item(i);
				if (aElement.getNodeName().equalsIgnoreCase(FTBConst.WORD_ELEMENT)) {
					foundWChild = true;
				}
				else {
					// some recursion
					foundWChild = isMultiWords (aElement);
				}
			}
			i++;
		}
		return foundWChild;
		//return currentElement.hasChildNodes();
	}

	/**
	 * is part of a multi words expressions currentElement
	 */
	public Boolean isPartOfMultiWords (Element currentElement) {
		
		//System.err.println("Debug: getFirstNamedAncestor TEXTCONTENT " + currentElement.getTextContent());

		// since in the constit corpus some mw contain chunks we have to search not only in the direct parent
		Element parent = (Element) getFirstNamedAncestor (currentElement, FTBConst.WORD_ELEMENT, FTBConst.SENTENCE_ELEMENT);
		if (parent == null) return false;
		String parentLocalName = parent.getNodeName();
		return isMultiWords(parent) && 
				parentLocalName.equalsIgnoreCase(FTBConst.WORD_ELEMENT);
		/*
		Element parent = (Element) currentElement.getParentNode();
		String parentLocalName = parent.getNodeName();
		//System.err.println("Debug: currentElement >"+currentElement.getNodeName()+"< parentLocalName>"+parentLocalName+"<");

		return isMultiWords(parent) && 
				parentLocalName.equalsIgnoreCase(FTBConst.WORD_ELEMENT);
		 */
	}

	/**
	 * search the first ancestor which has a given name, 
	 * stop when encountered or when boundaryName or document root is encountered
	 * return null otherwise
	 * 
	 * @param currentElement
	 * @param localName
	 * @param boundaryName
	 * @return ancestor with localname
	 */
	public Element getFirstNamedAncestor (Element currentElement, String localName, String boundaryName) {

		Node parentNode = currentElement.getParentNode();
		if (parentNode.getNodeType() == DOCUMENT_TYPE_NODE) {
//			System.err.println("Debug: getFirstNamedAncestor STOP DOCUMENT_TYPE_NODE ");
			return null;
		}
		else {
			Element parent = (Element)parentNode;

			String parentLocalName = parent.getNodeName();
			if (parentLocalName.equalsIgnoreCase(localName)) {
//				System.err.println("Debug: getFirstNamedAncestor STOP FOUND "+parentLocalName);
				return parent;
			}
			else if (parentLocalName.equalsIgnoreCase(boundaryName)) {
//				System.err.println("Debug: getFirstNamedAncestor STOP BOUNDARY "+parentLocalName);
				return null;
			} 
			else {
//				System.err.println("Debug: getFirstNamedAncestor parentLocalName "+parentLocalName);
				return getFirstNamedAncestor (parent, localName, boundaryName);
			}
		}

	}


	/**
	 * is part of a graphical unit multi words expression currentElement
	 */
	public Boolean isPartOfGraphicUnitMultiWords (Element currentElement) {
		
		// since in the constit corpus some mw contain chunks we have to search not only in the direct parent
		Element parent = (Element) getFirstNamedAncestor (currentElement, FTBConst.WORD_ELEMENT, FTBConst.SENTENCE_ELEMENT);
		if (parent == null) return false;
		
		return isPartOfMultiWords (currentElement) && isGraphicUnit(parent);

	//	return isPartOfMultiWords (currentElement) && isGraphicUnit((Element)currentElement.getParentNode());
	}

	/**
	 * is a simple word (not part of a multi)  currentElement

	 */
	public Boolean isSimpleWord (Element currentElement) {
		return !isPartOfMultiWords(currentElement) && !isMultiWords(currentElement);
	}
	/**
	 * is the word a graphic unit currentElement

	 */
	public Boolean isGraphicUnit (Element currentElement) {
		return getAttributeValue(currentElement,FTBConst.LEMMA_ATTRIBUTE).trim().indexOf(" ") == -1;
	}

	/**
	 * is a regular form currentElement currentWordMorphoSyntaxFeaturesMap
	 */
	public Boolean isNumericRegularForm (Element currentElement) {

		return RegularForms.isNumericRegularForm(getAttributeValue(currentElement,FTBConst.LEMMA_ATTRIBUTE).trim());

	}

	/**
	 * is part of a regular form currentElement currentWordMorphoSyntaxFeaturesMap
	 */
	public Boolean isPartOfNumericRegularForm (Element currentElement) {
		
		// since in the constit corpus some mw contain chunks we have to search not only in the direct parent
		Element parent = (Element) getFirstNamedAncestor (currentElement, FTBConst.WORD_ELEMENT, FTBConst.SENTENCE_ELEMENT);
		if (parent == null) return false;
		return isPartOfMultiWords (currentElement) && isNumericRegularForm(parent);

		//return isPartOfMultiWords (currentElement) && isNumericRegularForm((Element)currentElement.getParentNode());
	}

	/**
	 * 
	 * @param currentElement
	 * @return
	 */
	public Boolean isPartOfAmalgame (Element currentElement) {
		return currentElement.getTextContent().trim().equalsIgnoreCase("");
	}

	/**
	 * belongs to a dictionary (list of multi words adverbs or preposition as UIMA resources) currentElement
	 */
	public Boolean isDictionaryStableForm (Element currentElement) {
		//TODO
		return false;
	}


	/**
	 * is it an automatic segmented token currentElement
	 */
	public Boolean isAutomaticSegmentedToken (Element currentElement) {
		return isGraphicUnit(currentElement) || isNumericRegularForm (currentElement) || isDictionaryStableForm(currentElement);
	}	


	/**
	 * Element (e.g. AttributesArray) to Map 
	 */
	public MorphoWord analyze() {
		//Map<String,Object> currentWordMorphoSyntaxFeaturesMap = new HashMap<String, Object>();

		MorphoWord currentWordFeaturesMap = new MorphoWord();
		// Get the attribute name and values of 
		// In particular for cat/catint subcat mph

		currentWordFeaturesMap.getfeatures().put(FTBConst.CAT_ATTRIBUTE, "");
		currentWordFeaturesMap.getfeatures().put(FTBConst.SUBCAT_ATTRIBUTE, "");
		currentWordFeaturesMap.getfeatures().put(FTBConst.MPH_ATTRIBUTE, "");
		currentWordFeaturesMap.getfeatures().put(FTBConst.LEMMA_ATTRIBUTE, "");

		currentWordFeaturesMap.getfeatures().put(FTBConst.MOOD_ATTRIBUTE, "");
		currentWordFeaturesMap.getfeatures().put(FTBConst.TENSE_ATTRIBUTE, "");

		currentWordFeaturesMap.getfeatures().put(FTBConst.CASEF_ATTRIBUTE, "");
		currentWordFeaturesMap.getfeatures().put(FTBConst.GENDER_ATTRIBUTE, "");
		currentWordFeaturesMap.getfeatures().put(FTBConst.NUMBER_ATTRIBUTE, "");
		currentWordFeaturesMap.getfeatures().put(FTBConst.POSSNUMBER_ATTRIBUTE, "");

		currentWordFeaturesMap.getfeatures().put(FTBConst.isPartOfMultiWords, "");
		currentWordFeaturesMap.getfeatures().put(FTBConst.isGraphicUnit, "");
		currentWordFeaturesMap.getfeatures().put(FTBConst.isPartOfGraphicUnitMultiWords, "");
		currentWordFeaturesMap.getfeatures().put(FTBConst.isRegularForm, "");
		currentWordFeaturesMap.getfeatures().put(FTBConst.isPartOfRegularFormMultiwords, "");
		currentWordFeaturesMap.getfeatures().put(FTBConst.isPartOfAmalgame, "");
		currentWordFeaturesMap.getfeatures().put(FTBConst.ftbPlusCat, "");

		currentWordFeaturesMap.getfeatures().put(FTBConst.coverText, currentElement.getTextContent());
		//currentWordFeaturesMap.getWordMorphoSyntaxFeaturesMap().put(FTBConst.begin, String.valueOf(currentElement.getBegin()));
		//currentWordFeaturesMap.getWordMorphoSyntaxFeaturesMap().put(FTBConst.end, String.valueOf(currentElement.getEnd()));


		// is multiWords
		currentWordFeaturesMap.getfeatures().put(FTBConst.isMultiWords, String.valueOf(isMultiWords(currentElement)));

		// is part of
		currentWordFeaturesMap.getfeatures().put(FTBConst.isPartOfMultiWords, String.valueOf(isPartOfMultiWords(currentElement)));

		// is part of graphic Unit MultiWords
		currentWordFeaturesMap.getfeatures().put(FTBConst.isPartOfGraphicUnitMultiWords, String.valueOf(isPartOfGraphicUnitMultiWords(currentElement)));

		// is simpleWord
		currentWordFeaturesMap.getfeatures().put(FTBConst.isSingleWord, String.valueOf(isSimpleWord(currentElement)));

		// is graphical Unit
		currentWordFeaturesMap.getfeatures().put(FTBConst.isGraphicUnit, String.valueOf(isGraphicUnit(currentElement)));

		// is regular form
		currentWordFeaturesMap.getfeatures().put(FTBConst.isRegularForm, String.valueOf(isNumericRegularForm(currentElement)));

		// is part of regular form multiword
		currentWordFeaturesMap.getfeatures().put(FTBConst.isPartOfRegularFormMultiwords, String.valueOf(isPartOfNumericRegularForm(currentElement)));



		// is second part of amalgame
		currentWordFeaturesMap.getfeatures().put(FTBConst.isPartOfAmalgame, String.valueOf(isPartOfAmalgame(currentElement)));


		//
		String currentAttributeValue = "";
		if (currentElement.hasAttribute(FTBConst.CAT_ATTRIBUTE)) currentAttributeValue  =  currentElement.getAttribute(FTBConst.CAT_ATTRIBUTE);
		else currentAttributeValue = currentElement.getAttribute(FTBConst.CATINT_ATTRIBUTE);
		currentWordFeaturesMap.getfeatures().put(FTBConst.CAT_ATTRIBUTE, currentAttributeValue);

		currentWordFeaturesMap.getfeatures().put(FTBConst.MPH_ATTRIBUTE, currentElement.getAttribute(FTBConst.MPH_ATTRIBUTE));
		currentWordFeaturesMap.getfeatures().put(FTBConst.SUBCAT_ATTRIBUTE, currentElement.getAttribute(FTBConst.SUBCAT_ATTRIBUTE));
		currentWordFeaturesMap.getfeatures().put(FTBConst.LEMMA_ATTRIBUTE, currentElement.getAttribute(FTBConst.LEMMA_ATTRIBUTE));

		currentWordFeaturesMap.getfeatures().put(FTBConst.ftbPlusCat, currentElement.getAttribute(FTBConst.ftbPlusCat));

		//getElement
		/*System.out.println("Debug: currentXMLAnnotation >"+currentWordFeaturesMap.getWordMorphoSyntaxFeaturesMap().get(FTBConst.LEMMA_ATTRIBUTE)
				+"< cat >"+currentWordFeaturesMap.getWordMorphoSyntaxFeaturesMap().get(FTBConst.CAT_ATTRIBUTE)
				+"< subcat >"+currentWordFeaturesMap.getWordMorphoSyntaxFeaturesMap().get(FTBConst.SUBCAT_ATTRIBUTE)
				+"< mph >"+currentWordFeaturesMap.getWordMorphoSyntaxFeaturesMap().get(FTBConst.MPH_ATTRIBUTE));*/
		return currentWordFeaturesMap;

	}




	/**
	 * get the attribute value corresponding to a given attribute name
	 */
	public String getAttributeValue(Element currentElement, String name) {

		return currentElement.getAttribute(name);

		/*	int i = 0;
		Boolean found = false ;
		String result = "" ; //null; empty string correspond to no value !
		NamedNodeMap atts = currentElement.getAttributes();

		while (i < atts.getLength() && !found) {
			Node att   = currentElement.getAttributes().item(i);
			String currentAttributeQualifiedName = att.getLocalName();

			if (currentAttributeQualifiedName.equalsIgnoreCase(name)) {
				found = true;
				result = att.getLocalName();
			}
			i++;
		}
		return result;
		 */
	}



}
