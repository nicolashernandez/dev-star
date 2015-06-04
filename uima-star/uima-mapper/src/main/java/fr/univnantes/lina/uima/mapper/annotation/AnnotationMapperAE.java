/** 
 * UIMA Annotation Mapper
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

package fr.univnantes.lina.uima.mapper.annotation;
import java.util.*;

import org.apache.uima.UimaContext;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.analysis_engine.annotator.AnnotatorContextException;
import org.apache.uima.cas.FSIterator;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.tcas.Annotation;
import org.apache.uima.resource.ResourceInitializationException;

import java.io.IOException;

import fr.univnantes.lina.uima.common.ae.CommonAE;
import fr.univnantes.lina.uima.common.cas.ContextUtils;
import fr.univnantes.lina.uima.common.cas.ContextUtils.ContextFile;
import fr.univnantes.lina.uima.mapper.rules.RuleProcessor;
import fr.univnantes.lina.uima.mapper.rules.RulesFileLoader;
import fr.univnantes.lina.uima.mapper.rules.JAXB.*;

/**
 * Class for processing the mapping
 * <ul>
 * <li> 
 * </ul>
 * 
 * @author      Nicolas Hernandez
 * @author      Emilien Ripoche, Charles Vukelic, Samuel Ngahane, Jerome Mace et Warren Eslan
 * @version     %I%, %G%
 * @since       1.0
 */
public class AnnotationMapperAE  extends CommonAE { 
	//extends JCasAnnotator_ImplBase {

	/**
	 * Properties param and default values 
	 */
	private static String RULES_FILE_PARAM = "RulesFile";
	private static final String RULE_FILES_PARAM = "RulesFiles";

	/**
	 * Rules to proceed
	 */
	private Rules rules = null;

	/**
	 * @return the rules
	 */
	protected Rules getRules() {
		return rules;
	}


	/**
	 * @param rules the rules to set
	 */
	protected void setRules(Rules rules) {
		this.rules = rules;
	}

	/**
	 * @param list of rules to add
	 */
	protected void addRules(Rules rules) {

		if (this.getRules() == null) {
			this.setRules(rules); 
		}
		else {
			Iterator<Rule> rulesIterator = rules.getRule().iterator();

			while (rulesIterator.hasNext()) {
				this.getRules().getRule().add(rulesIterator.next());	
			}

		}
	}



	/**
	 * TODO Bloquant à retirer : On suppose que le fichier Xml est contenu dans le répertoire resources situé dans le path du projet en s'inspirant de http://svn.apache.org/repos/asf/uima/addons/trunk/RegularExpressionAnnotator/src/main/java/org/apache/uima/annotator/regex/impl/RegExAnnotator.java
	 */
	public void initialize(UimaContext context) throws ResourceInitializationException
	{

		//-- super
		super.initialize(context);

		//-- current AE parameter

		// get configuration parameter settings
		// get parameter ConceptFiles, default is an empty array
		String ruleFilePath = (String) context.getConfigParameterValue(RULES_FILE_PARAM);
		//String[] conceptFileNames = safeGetConfigParameterStringArrayValue(getContext(),
		//    RULE_FILES_PARAM, new String[] {});
		String[] conceptFileNames = null;
		//System.err.println("Debug: ruleFilePath "+ruleFilePath);


		// the parameter RULES_FILE_PARAM is having priority over the parameter RULE_FILES_PARAM
		// Path in the data/class path where to find the rules files.
		// RuleFile parameter has priority over RuleFiles (for retro-compatibility reasons)
		// At least one should be defined.
		if (ruleFilePath != null) conceptFileNames = new String[] {ruleFilePath};
		else conceptFileNames = safeGetConfigParameterStringArrayValue(getContext(),
				RULE_FILES_PARAM, new String[] {});

		for (String cfn : conceptFileNames) {
			System.err.println("INFO: "+this.getClass().getSimpleName()+" - Loading mapping rule file: "+cfn);
		}

		if (conceptFileNames.length == 0 && ruleFilePath == null) {
			String errorMessage = "You should at least define one path by the way of one of the following parameters "+ RULE_FILES_PARAM+" or "+RULE_FILES_PARAM+ "." ;
			System.err.println(errorMessage);
			throw new ResourceInitializationException();
		}


		// try to resolve the concept file names
		// Based on http://svn.apache.org/repos/asf/uima/addons/trunk/RegularExpressionAnnotator/src/main/java/org/apache/uima/annotator/regex/impl/RegExAnnotator.java
		List<ContextFile> cfList = new ArrayList<ContextFile>();
		for (int i = 0; i < conceptFileNames.length; i++) {
			// try to resolve the relative file name with classpath or datapath
			String filename = conceptFileNames[i];


			ContextFile file = ContextUtils.resolveRelativeFilePath(this, context, filename);

			// if the current concept file wasn't found, throw an exception
			if (file == null) {
				String errorMessage = "file resource not found "+ conceptFileNames[i];
				System.err.println(errorMessage);
				throw new ResourceInitializationException();
			}
			cfList.add(file);
		}

		for (ContextFile cf : cfList) {

			this.setRules(RulesFileLoader.loadRulesFromJAXBFile(cf.getStream()));
			try {
				cf.getStream().close();
			} catch (IOException e) {

			}

		}



	}

	/**
	 * @param context
	 * @param param
	 * @param defaultValue
	 * @return returns the boolean parameter value
	 * @throws AnnotatorContextException
	 */
	private static String[] safeGetConfigParameterStringArrayValue(UimaContext context, String param,
			String[] defaultValue) {
		String[] array = (String[]) context.getConfigParameterValue(param);
		if (array != null && array.length > 0) {
			return array;
		}
		return defaultValue;
	}



	/**
	 * Process input view
	 */
	//	@Override
	protected String processInputView(JCas aInputViewJCas,
			FSIterator<Annotation> contextAnnotationsFSIter,
			Set<String> inputAnnotationSet,
			String inputFeatureString, JCas outputViewJCas,
			String outputAnnotationString, String ouputFeatureString)
					throws AnalysisEngineProcessException {

		//System.out.println("Debug: class>"+this.getClass().getName()+"< now>" + JavaUtilities.now());

		// Récupération des règles à traiter
		Iterator<Rule> itRules = this.getRules().getRule().iterator();

		// Récupération d'un index d'annotations 
		//HashMap<String, Integer> annotationHashMap = new HashMap<String, Integer>();
		//ArrayList<FeatureStructure> annotationArrayList = AnnotationCollectionUtils.getAnnotationArray(aInputViewJCas, annotationHashMap);
		// Eventuellement sort de cet index

		// Pour chaque annotation on consulte les règles à traiter

		// Parcours de chaque règle et exécution
		// Attention cet algo n'est applicable que dans le cas où il n'y a q'un seul patternElement 
		// Sinon celui-ci est à revoir
		while(itRules.hasNext()) {
			Rule aRule = itRules.next();
			//System.out.println("Debug: aRule.getId() "+ aRule.getId());
			RuleProcessor.processRule(aRule,aInputViewJCas);

		}

		// La méthode processInputView requiert de retourner quelque chose 
		// Le plus simple est de lui retourner l'image d'elle même
		return aInputViewJCas.getSofaDataString();
	}



}
