/** 
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
package fr.univnantes.lina.uima.mapper.rules;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.jxpath.JXPathContext;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.text.AnnotationIndex;
import org.apache.uima.jcas.JCas;

//import fr.univnantes.lina.uima.mapper.annotation.DAORules.Rule;

import fr.univnantes.lina.uima.common.cas.AnnotationUtils;
import fr.univnantes.lina.uima.common.cas.JCasUtils;
import fr.univnantes.lina.uima.mapper.rules.JAXB.*;

public class RuleProcessor {

	private static boolean debug = false; 

	/**
	 * Evaluate Xpath Constraint over a given object 
	 * @param xPathConstraint
	 * @return true if the constraint is validated
	 * @throws AnalysisEngineProcessException
	 */
	public static List<JXPathContext> evaluateXPathConstraint (JXPathContext jxpathContext, String xPathConstraint) throws AnalysisEngineProcessException {
		List<JXPathContext> selectNodes = new ArrayList<JXPathContext>();
		if (xPathConstraint != "") {
			selectNodes = jxpathContext.selectNodes(xPathConstraint);}
		else {
			System.err.println("Error: RuleProcessor - evaluateXPathConstraint - empty constraint (probably an error, check you rules file");
		}
		return selectNodes;
	}

	/**
	 * Evaluate Xpath Constraint over a given object 
	 * @param aConstructedObject
	 * @param xPathConstraint
	 * @return true if the constraint is validated
	 * @throws AnalysisEngineProcessException
	 */
	public static boolean isAValidXPathConstraint (JXPathContext jxpathContext, String xPathConstraint) throws AnalysisEngineProcessException {
		// (!jxpathContext.selectNodes(constraint).isEmpty()) 
		if(((xPathConstraint!=null)&&(evaluateXPathConstraint(jxpathContext,xPathConstraint).size()!=0)) || xPathConstraint==null){
			//System.out.println("Debug: true :");
			return true;

		}	else {
			//System.out.println("Debug: false :");
			return false;

		}
	}

	/**
	 * Process a rule at a time 
	 * 
	 * @param aRule
	 * @param aInputViewJCas
	 * @throws AnalysisEngineProcessException
	 */
	public static void processRule (Rule aRule, JCas aInputViewJCas) throws AnalysisEngineProcessException {

		// Récupération du pattern element courant de la ProcessedRule
		// ProcessedRule extend Rule
		// Récupération du type correspondant au nom du pattern element courant 
		// Avant il ne pouvait y avoir qu'un seul patternElement, 
		// on les récupérait donc tous et pour chacun
		// on récupérait et évaluer la contrainte
		// si elle était validée ou n'existait pas alors on ajoutait un target sur les begin et end du patternElement courant
		// En fait 2 algos différents
		// On parcours un index composés des éléments des règles et on maintient un curseur sur l élément où l'on est rendu pour chque règle
		// Pour chaque élément d'un pattern on récupère et on valide si bien précédent (grâce à une autre structure qui garde une trace de cela), pour les quantifiers ca devraient être ok mais pour les sets ; peut être plus complexe car pls parcousr 



		//Récupération du type du patternElement source
		String sourceTypeStr = aRule.getPattern().getPatternElement().get(0).getType();
		Type typeSrc = JCasUtils.getJCasType(aInputViewJCas, sourceTypeStr);

		//Récupération de l'ensemble/index des annotations du type source
		AnnotationIndex idxSrc = (AnnotationIndex)aInputViewJCas.getAnnotationIndex(typeSrc);
		//Création d'un itérateur sur cet index
		Iterator itSrc = idxSrc.iterator();

		//Récupération de la contrainte
		//String constraint = (String)aRule.getConstraint();
		String constraint =  aRule.getPattern().getPatternElement().get(0).getConstraint().get(0).getContent();

		if (debug) {
			System.out.println("Debug: ------------------------");
			System.out.println("Debug: rule id = " +aRule.getId());
			System.out.println("Debug: from :"+ sourceTypeStr);
			System.out.println("Debug: validating :"+ constraint);
		}

		//EXECUTION NORMALE


		//Tant qu'il y a des annotations de type source
		while(itSrc.hasNext())
		{

			// Génération de la classe correspondante à la String sourceTypeStr 
			Class SrcClass = AnnotationUtils.getAnnotationClass(sourceTypeStr);
			// Génération de l'objet en invoquant le constructeur de la classes
			Object aConstructedObject = AnnotationUtils.newJCasObjectClassInstance(SrcClass, aInputViewJCas);

			//Création de l'annotation courante
			aConstructedObject = itSrc.next();
			AnnotationUtils.castObjectWithAGivenClass(SrcClass, aConstructedObject);	//On caste l'objet en type de SrcClass

			//On place l'environnement sur l'annotation source courante
			JXPathContext jxpathContext = JXPathContext.newContext(aConstructedObject);

			if (isAValidXPathConstraint(jxpathContext, constraint)) {

				//Récupération du début de l'annotation courante
				int begin = (Integer)jxpathContext.getValue("begin");

				//Récupération de la fin de l'annotation courante
				int end = (Integer)jxpathContext.getValue("end");

				//Récupération du Covered text de l'annotation courante
				//String coveredText = (String)jxpathContext.getValue("coveredText");

				//System.out.println("Debug: annotation courante begin "+begin+" end "+end+" coveredText "+coveredText);

				// for all the annotations to create (called targetTypeStr)
				List<Create> targetList =  aRule.getPattern().getPatternElement().get(0).getCreate();
				if (debug) System.out.println("Debug: toCreate.size :"+ targetList.size());
				for (int i=0; i<targetList.size();i++){
					String targetTypeStr = targetList.get(i).getType();

					// set features
					HashMap<String, Object> featureHashMap = new HashMap<String, Object>();
					featureHashMap.put("begin", String.valueOf(begin));
					featureHashMap.put("end", String.valueOf(end));
					List<SetFeature> featureList =  aRule.getPattern().getPatternElement().get(0).getCreate().get(i).getSetFeature();
					for (int j=0; j<featureList.size();j++){

						String featureConstraint = featureList.get(j).getConstraint();

						if (isAValidXPathConstraint(jxpathContext, featureConstraint)) {
							if (debug) {
								System.out.println("Debug: setFeature name >"+ featureList.get(j).getName() + "< value >"+featureList.get(j).getValue()+"< features");
								System.out.println("Debug: jxpathContext.getValue(featureList.get(j).getValue()) >"+jxpathContext.getValue(featureList.get(j).getValue()) +"<");
							}
							String value = "";
							try {
								value = (String) jxpathContext.getValue(featureList.get(j).getValue());
							}
							catch ( org.apache.commons.jxpath.JXPathNotFoundException e) {
								// in order to process faster (without display)
								//System.err.println("Warning: JXPathNotFoundException: No value for xpath: "+featureList.get(j).getValue());
								value = "";
							}
							featureHashMap.put(featureList.get(j).getName(),value );
						}
						else if (debug) {
							System.out.println("Debug: do not setFeature name >"+ featureList.get(j).getName() + "< because constraint >"+featureConstraint+"< is not valid");
						}
						// N'ai pas la bonne approche pour récupérer les valeurs, ne récupère que les noeuds
						//List<JXPathContext> featureValueJXPathContextList = evaluateXPathConstraint(jxpathContext, featureList.get(j).getValue());
						//for (int k=0; k<featureValueJXPathContextList.size() ; k++ ) {
						//System.out.println("Debug: featureValueJXPathContext >"+featureValueJXPathContextList.get(k) +"<");
						//}
					}
					if (debug) System.out.println("Debug: create : "+ targetTypeStr + " with "+featureList.size()+" features");
					AnnotationUtils.createAnnotation(aInputViewJCas, targetTypeStr, featureHashMap);
				}

				// for all the annotations to update (called updateTypeList)
				// TODO search the annotations to updates
				/*List<Update> updateTypeList =  aRule.getPattern().getPatternElement().get(0).getUpdate();
				if (debug) System.out.println("Debug: toUpdate.size :"+ updateTypeList.size());
				for (int i=0; i<updateTypeList.size();i++){
					String targetTypeStr = updateTypeList.get(i).getType();

					// set features
					HashMap<String, Object> featureHashMap = new HashMap<String, Object>();
					featureHashMap.put("begin", String.valueOf(begin));
					featureHashMap.put("end", String.valueOf(end));
					List<SetFeature> featureList =  aRule.getPattern().getPatternElement().get(0).getCreate().get(i).getSetFeature();
					for (int j=0; j<featureList.size();j++){

						String featureConstraint = featureList.get(j).getConstraint();

						if (isAValidXPathConstraint(jxpathContext, featureConstraint)) {
							if (debug) {
								System.out.println("Debug: setFeature name >"+ featureList.get(j).getName() + "< value >"+featureList.get(j).getValue()+"< features");
								System.out.println("Debug: jxpathContext.getValue(featureList.get(j).getValue()) >"+jxpathContext.getValue(featureList.get(j).getValue()) +"<");
							}
							String value = "";
							try {
								value = (String) jxpathContext.getValue(featureList.get(j).getValue());
							}
							catch ( org.apache.commons.jxpath.JXPathNotFoundException e) {
								// in order to process faster (without display)
								//System.err.println("Warning: JXPathNotFoundException: No value for xpath: "+featureList.get(j).getValue());
								value = "";
							}
							featureHashMap.put(featureList.get(j).getName(),value );
						}
						else if (debug) {
							System.out.println("Debug: do not setFeature name >"+ featureList.get(j).getName() + "< because constraint >"+featureConstraint+"< is not valid");
						}
						// N'ai pas la bonne approche pour récupérer les valeurs, ne récupère que les noeuds
						//List<JXPathContext> featureValueJXPathContextList = evaluateXPathConstraint(jxpathContext, featureList.get(j).getValue());
						//for (int k=0; k<featureValueJXPathContextList.size() ; k++ ) {
						//System.out.println("Debug: featureValueJXPathContext >"+featureValueJXPathContextList.get(k) +"<");
						//}
					}
					if (debug) System.out.println("Debug: create : "+ targetTypeStr + " with "+featureList.size()+" features");
					AnnotationUtils.updateAnnotation(aInputViewJCas, annotationToUpdate, annotationToProcessString, featuresHashMap);
				}
				*/


			}
			/*else {

				//Récupération du début de l'annotation courante
				int begin = (Integer)jxpathContext.getValue("begin");

				//Récupération de la fin de l'annotation courante
				int end = (Integer)jxpathContext.getValue("end");

				//Récupération du Covered text de l'annotation courante
				//String coveredText = (String)jxpathContext.getValue("coveredText");

				//System.out.println("Debug: annotation courante begin "+begin+" end "+end+" coveredText "+coveredText);

				// pour tous les annotations à créer (appelées targetTypeStr)
				List<Create> targetList =  aRule.getPattern().getPatternElement().get(0).getCreate();
				if (debug) System.out.println("Debug: toCreate.size :"+ targetList.size());
				for (int i=0; i<targetList.size();i++){
					String targetTypeStr = targetList.get(i).getType();

					// Ajout des features
					HashMap<String, String> featureHashMap = new HashMap<String, String>();
					featureHashMap.put("begin", String.valueOf(begin));
					featureHashMap.put("end", String.valueOf(end));
					List<SetFeature> featureList =  aRule.getPattern().getPatternElement().get(0).getCreate().get(i).getSetFeature();
					for (int j=0; j<featureList.size();j++){
						if (debug) {
							System.out.println("Debug: setFeature name >"+ featureList.get(j).getName() + "< value >"+featureList.get(j).getValue()+"< features");

							System.out.println("Debug: jxpathContext.getValue(featureList.get(j).getValue()) >"+jxpathContext.getValue(featureList.get(j).getValue()) +"<");
						}
						featureHashMap.put(featureList.get(j).getName(),"null" );

						// N'ai pas la bonne approche pour récupérer les valeurs, ne récupère que les noeuds
						//List<JXPathContext> featureValueJXPathContextList = evaluateXPathConstraint(jxpathContext, featureList.get(j).getValue());
						//for (int k=0; k<featureValueJXPathContextList.size() ; k++ ) {
						//System.out.println("Debug: featureValueJXPathContext >"+featureValueJXPathContextList.get(k) +"<");
						//}
					}

					if (debug) System.out.println("Debug: create : "+ targetTypeStr + " with "+featureList.size()+" features");

					UIMAUtilities.createAnnotation(aInputViewJCas, targetTypeStr, featureHashMap);

				}


			}*/

		}

	}





}