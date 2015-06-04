/** 
 * UIMA ml-nlp
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
package fr.univnantes.lina.uima.mlnlp.metrics.scores;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.uima.UimaContext;
import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.cas.ConstraintFactory;
import org.apache.uima.cas.FSConstraint;
import org.apache.uima.cas.FSIntConstraint;
import org.apache.uima.cas.FSIterator;
import org.apache.uima.cas.FSMatchConstraint;
import org.apache.uima.cas.FSStringConstraint;
import org.apache.uima.cas.Feature;
import org.apache.uima.cas.FeaturePath;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.text.AnnotationIndex;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.tcas.Annotation;
import org.apache.uima.resource.ResourceInitializationException;

import fr.univnantes.lina.uima.common.resources.RelevantRetrievedCorrectCounter;
import fr.univnantes.lina.uima.common.resources.RelevantRetrievedCorrectCounterResource;

/**
 * 
 * Assumes Retrieved and relevant annotations are present separately in each CAS
 * Provide P, R for the whole collection not for each CAS (could easily be implemented) (but I do not know how)
 * Do not provide F (could easily be implemented)
 * Based on
 * http://code.google.com/p/uima-sandbox/source/browse/trunk/checker/sources/fr/free/rocheteau/jerome/engines/Checker.java
 * @author rocheteau, hernandez
 */
public class InformationRetrievalScoresAE extends JCasAnnotator_ImplBase {

	//private RelevantRetrievedCorrectCounter aRelevantRetrievedCorrectCounter;

	private RelevantRetrievedCorrectCounterResource aRelevantRetrievedCorrectCounterResource;
	  
	private void setRelevantRetrievedCorrectCounterResource(RelevantRetrievedCorrectCounterResource resource) {
		this.aRelevantRetrievedCorrectCounterResource = resource;
	}
	
	private RelevantRetrievedCorrectCounterResource getRelevantRetrievedCorrectCounterResource() {
		return this.aRelevantRetrievedCorrectCounterResource;
	}
	
	private String testAnnotationType;
	
	private void setTestAnnotationType(String type) {
		this.testAnnotationType = type;
	}
	
	private Type getTestAnnotationType(JCas cas) {
		return cas.getTypeSystem().getType(this.testAnnotationType);
	}
	
	private String goldAnnotationType;
	
	private void setGoldAnnotationType(String type) {
		this.goldAnnotationType = type;
	}
	
	private Type getGoldAnnotationType(JCas cas) {
		return cas.getTypeSystem().getType(this.goldAnnotationType);
	}
	
	private List<String> featureNames;
	
	private void setFeatureNames(String[] names) {
		this.featureNames = new ArrayList<String>();
		for (String name : names) {
			this.featureNames.add(name);
		}
	}
	
	private List<String> getFeatureNames() {
		return this.featureNames;
	}
	
	/**
	 * Check whether the compared features have got the same name and type
	 * @param cas
	 * @param type
	 * @param gold
	 * @throws Exception
	 */
	private void checkFeaturesCompatibility(JCas cas,Type type,Type gold) throws Exception {
		for (String name : this.getFeatureNames()) {
			Feature feature = type.getFeatureByBaseName(name);
			if (feature == null) {
				throw new Exception("The feature name " + name + " doesn't belong to the annotation type " + type.toString() + ".");
			}
			Feature goldFeature = gold.getFeatureByBaseName(name);
			if (goldFeature == null) {
				throw new Exception("The feature name " + name + " doesn't belong to the gold annotation type " + type.toString() + ".");
			}
			String range = feature.getRange().getName();
			String goldRange = goldFeature.getRange().getName();
			if (!range.equals(goldRange)) {
				String message = "The feature name " + name + " ranges over the type " + range.toString() + " according to the annotation type ";
				message += " whereas it ranges over " + goldRange + " according to the gold annotation one.";
				throw new Exception(message);
			}
		}
	}
	
	/**
	 * Get and set the resource, 
	 * the Test and the Gold annotation types to compare with, 
	 * eventually their FeaturesNames to compare 
	 * from parameters
	 */
	@Override
	public void initialize(UimaContext context) throws ResourceInitializationException {
		super.initialize(context);
		try {
			
			String resourceKey = (String) context.getConfigParameterValue("ResourceKey");
			if (resourceKey == null) resourceKey = "SharedRelevantRetrievedAndCorrectCounterResource";
			
			//RelevantRetrievedCorrectCounterResource resource = (RelevantRetrievedCorrectCounterResource) context.getResourceObject("SharedRelevantRetrievedAndCorrectCounterResource");
			RelevantRetrievedCorrectCounterResource resource = (RelevantRetrievedCorrectCounterResource)getContext().getResourceObject(resourceKey);
			//RelevantRetrievedCorrectCounterResource resource = (RelevantRetrievedCorrectCounterResource)getContext().getResourceObject("DefaultSharedRelevantRetrievedAndCorrectCounterResource");
			//if (resource == null) System.out.println("Debug: resource == null");
			// la ressource était à null car il ne trouvait pas d'objet avec ce nom là SharedRelevantRetrievedAndCorrectCounterResource
			// le problème a été résolu lorsque j'ai déclaré la resource dependencies directement dans l AE.xml 
			// la méthode load est appelé par chaque AE à l'initialisation ; ainsi c'est à la création qu'on load des external data et non lors de l'appel du load... 
			// 
			
			//DefaultSharedRelevantRetrievedAndCorrectCounterResource
			//if (resource == null) resource = new RelevantRetrievedCorrectCounterResource();

			this.setRelevantRetrievedCorrectCounterResource(resource);
			String annotationType = (String) context.getConfigParameterValue("TestAnnotationType");
			this.setTestAnnotationType(annotationType);
			String goldAnnotationType = (String) context.getConfigParameterValue("GoldAnnotationType");
		//	System.out.println("Debug: goldAnnotationType " +goldAnnotationType);
			this.setGoldAnnotationType(goldAnnotationType);
			String[] names = (String[]) context.getConfigParameterValue("FeatureNames");
			this.setFeatureNames(names);
			
			//aRelevantRetrievedCorrectCounter = new RelevantRetrievedCorrectCounter();
		} catch (Exception e) {
			throw new ResourceInitializationException(e);
		}
	}
	
	/**
	 * Count the number of correct results (intersection of relevant and retrieved)
	 * @param aJCas
	 * @param testAnnotationType
	 * @param goldAnnotationType
	 * @param featureNames
	 * @throws Exception
	 */
	private void countRelevantRetrievedAndTheCorrect(JCas aJCas,Type testAnnotationType,Type goldAnnotationType,List<String> featureNames) throws Exception {
		
		/*
		AnnotationIndex<Annotation> anAnnotationIndex = aJCas.getAnnotationIndex();
		Iterator<Annotation> aAnnotationIterator = anAnnotationIndex.iterator(); 
		while (aAnnotationIterator.hasNext()) { 
			Annotation anAnnotation = (Annotation) aAnnotationIterator.next();
			System.out.println("Debug: " + anAnnotation.getClass().getName());

		}*/
					// Faire quelque chose

		
		// count the relevant 
		AnnotationIndex<Annotation> goldIndex = aJCas.getAnnotationIndex(goldAnnotationType);
		
		int goldIndexSize = 0;
		if (goldIndex != null) goldIndexSize = goldIndex.size();
		//if (this.getRelevantRetrievedCorrectCounterResource() == null) System.out.println("Debug: this.getRelevantRetrievedCorrectCounterResource() == null");
		//System.out.println("Debug: goldIndex.size() " + goldIndex.size());
		this.getRelevantRetrievedCorrectCounterResource().addRelevant(goldIndexSize);
		//aRelevantRetrievedCorrectCounter.addRelevant(goldIndexSize);
		
		// count the retrieved
		AnnotationIndex<Annotation> testIndex = aJCas.getAnnotationIndex(testAnnotationType);
		int testIndexSize = 0;
		if (testIndex != null) testIndexSize = testIndex.size();
		//System.out.println("Debug: testIndex.size() " + testIndex.size());
		this.getRelevantRetrievedCorrectCounterResource().addRetrieved(testIndexSize);
		//aRelevantRetrievedCorrectCounter.addRetrieved(testIndexSize);

		FSIterator<Annotation> testIter = testIndex.iterator();

		/// count the correct
		// intersection of relevant and retrieved
		// For all test annotations 
		while (testIter.hasNext()) {
			Annotation aTestAnnotation = testIter.next();
			// check its presence in the gold index
			FSIterator<Annotation> result = this.filter(aJCas,goldIndex,goldAnnotationType,featureNames,aTestAnnotation);
			/*while (result.hasNext()) {
				Annotation aResult = result.next();
				System.out.println("Debug: aResult "+aResult.getCoveredText());

			}*/
			if (result.hasNext()) {
			//if (result != null) {
				//System.out.println("Debug: test "+aTestAnnotation.getCoveredText());
				// 
				this.getRelevantRetrievedCorrectCounterResource().addCorrect();
				//aRelevantRetrievedCorrectCounter.addCorrect();

			}
		}
	}
	
	/**
	 * Search the presence of the test features values among the annotations of the gold annotation index
	 * @param aJCas
	 * @param goldIndex
	 * @param gold
	 * @param featureNames
	 * @param aTestAnnotation
	 * @return
	 * @throws Exception
	 */
	private FSIterator<Annotation> filter(JCas aJCas, AnnotationIndex<Annotation> goldIndex, Type gold, List<String> featureNames, Annotation aTestAnnotation) throws Exception {
		ConstraintFactory factory = aJCas.getConstraintFactory();
		FSMatchConstraint constraint = null;
		// For each feature name
		for (String name : featureNames) {
			//System.out.println("Debug: feature name "+name);
			Feature aTestAnnotationTypeFeature = aTestAnnotation.getType().getFeatureByBaseName(name);
			Feature aGoldAnnotationTypeFeature = gold.getFeatureByBaseName(name);
			FeaturePath path = aJCas.createFeaturePath();
			path.addFeature(aGoldAnnotationTypeFeature);
			
			String featureRange = aGoldAnnotationTypeFeature.getRange().getName();
			if (featureRange.equals("uima.cas.String")) {
				String value = aTestAnnotation.getStringValue(aTestAnnotationTypeFeature);
				FSStringConstraint primitive = factory.createStringConstraint();
				primitive.equals(value);
				constraint = this.getConstraint(factory,constraint,primitive,path,value);
			} else if (featureRange.equals("uima.cas.Integer")) {
				Integer value = aTestAnnotation.getIntValue(aTestAnnotationTypeFeature);
				FSIntConstraint primitive = factory.createIntConstraint();
				// Without the following line the constraint is not complete 
				// Types were only considered not the values.
				primitive.eq(value);
				constraint = this.getConstraint(factory,constraint,primitive,path,value);
			} else {
				throw new Exception("Error in "+ this.getClass().getName() +": Not yet implemented for " + featureRange);
			}
			
			
			/*FSIntConstraint endConstraint = theConstraints.createIntConstraint();
			if (isStrict) {
				endConstraint.eq(contextAnnotation.getEnd());
			} else {
				endConstraint.leq(contextAnnotation.getEnd()+1);
			}
			Feature endFeature = contextAnnotationType.getFeatureByBaseName("end");
			FeaturePath endPath = aJCas.createFeaturePath();
			endPath.addFeature(endFeature);
			FSMatchConstraint end = theConstraints.embedConstraint(endPath, endConstraint);*/
		}
		return aJCas.createFilteredIterator(goldIndex.iterator(),constraint);
	}

	private FSMatchConstraint getConstraint(ConstraintFactory factory,FSMatchConstraint constraint, FSConstraint primitive,FeaturePath path,Object value) {
		primitive.equals(value);
		FSMatchConstraint match = factory.embedConstraint(path,primitive);
		if (constraint == null) {
			constraint = match;
		} else {
			constraint = factory.and(constraint,match);
		}
		return constraint;
	}
	
	/**
	 * 
	 */
	@Override
	public void process(JCas aJCas) throws AnalysisEngineProcessException {
		try {
			Type test = this.getTestAnnotationType(aJCas);
			Type gold = this.getGoldAnnotationType(aJCas);
			this.checkFeaturesCompatibility(aJCas,test,gold);
			this.countRelevantRetrievedAndTheCorrect(aJCas,test,gold,this.getFeatureNames());
		} catch (Exception e) {
			throw new AnalysisEngineProcessException(e);
		}
	}
	
	/**
	 * 
	 */
	@Override
	public void collectionProcessComplete () throws AnalysisEngineProcessException {
		//System.err.println("Debug: "+this.getClass().getName() +"  collectionProcessComplete");
		aRelevantRetrievedCorrectCounterResource.display(goldAnnotationType,testAnnotationType);
		//aRelevantRetrievedCorrectCounter.display();
	}
	
	
}
