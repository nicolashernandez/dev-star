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

import java.lang.reflect.Method;
import java.util.Iterator;

import org.apache.uima.UimaContext;
import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.text.AnnotationIndex;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.tcas.Annotation;
import org.apache.uima.resource.ResourceInitializationException;

import fr.univnantes.lina.mlnlp.process.evaluation.ClassificationScores;
import fr.univnantes.lina.uima.common.cas.AnnotationUtils;
import fr.univnantes.lina.uima.common.cas.FeatureUtils;
import fr.univnantes.lina.uima.common.resources.FeatureModel;
import fr.univnantes.lina.uima.common.resources.ClassificationScoresSharedResource;

/**
 * 
 * No assumption about the order of GoldAnnotation and Test/BaselineAnnotation in the index
 * 
Outcomes are labeled either as positive (p) or negative (n). 
There are four possible outcomes from a binary classifier. 
If the outcome from a prediction is p and the actual value is also p, then it is called a true positive (TP); 
if the outcome from a prediction is p and the actual value is n then it is said to be a false positive (FP). 
when both the prediction outcome and the actual value are n,  then it is said to be a true negative (TN) 
when the prediction outcome is n while the actual value is p, then it is said to be a false negative (FN)  
actual p prediction p -> true positive (TP)
actual n prediction p -> false positive (FP) 
actual n prediction n -> true negative (TN) 
actual p prediction n -> false negative (FN)  


Can be parameterized with various configurations

#-----------------------------------------------------
case (1)
PositiveGoldSamplePath=PositiveGoldAnnotation
NegativeGoldSamplePath=NegativeGoldAnnotation
PositiveTestSamplePath=PositiveTestAnnotation
NegativeTestSamplePath=NegativeTestAnnotation

Positive value is given by the annotation type in the field PositiveGoldSamplePath

#-----------------------------------------------------
case (2)
PositiveGoldSamplePath=GoldAnnotation:label=positive
NegativeGoldSamplePath=
PositiveTestSamplePath=TestAnnotation:label=positive
NegativeTestSamplePath=

The system will infer that GoldAnnotation with a label value which differs from positive will be a negative.
Idem with TestAnnotation
The positive value is given by the declaration of the value for the PositiveGoldSamplePath

This lead to 
PositiveGoldSamplePath=GoldAnnotation:label=positive
NegativeGoldSamplePath=GoldAnnotation:label=
PositiveTestSamplePathPath=TestAnnotation:label=positive
NegativeTestSamplePath=TestAnnotation:label=



#-----------------------------------------------------
case (3)
PositiveGoldSamplePath=GoldAnnotation:label
NegativeGoldSamplePath=
PositiveTestSamplePath=TestAnnotation:label
NegativeTestSamplePath=

No positive value specified 
When there is a match TP++, no match
 * @author hernandez
 */
public class ClassificationScoresAE extends JCasAnnotator_ImplBase {

	final static private String classificationScoresRessourceKey = "ClassificationScoresRessourceKey";
	final static private String positiveGoldSamplePath = "PositiveGoldSamplePath";
	final static private String negativeGoldSamplePath = "NegativeGoldSamplePath";
	final static private String positiveTestSamplePath = "PositiveTestSamplePath";
	final static private String negativeTestSamplePath = "NegativeTestSamplePath";

	/**
	 * 
	 */
	private ClassificationScoresSharedResource aClassificationScoresSharedResource;

	private FeatureModel positiveGoldParam = null;
	private FeatureModel negativeGoldParam = null;
	private FeatureModel positiveTestParam = null;
	private FeatureModel negativeTestParam = null;


	/*
	 * ACCESSORS
	 */

	private void setClassificationScoresSharedResource(ClassificationScoresSharedResource resource) {
		this.aClassificationScoresSharedResource = resource;
	}

	private ClassificationScoresSharedResource getClassificationScoresSharedResource() {
		return this.aClassificationScoresSharedResource;
	}



	/*
	 * 
	 */

	/**
	 * Get and set the resource, 
	 * the Test and the Gold instances path to compare with, 
	 * eventually their FeaturesNames to compare 
	 * from parameters
	 */
	@Override
	public void initialize(UimaContext context) throws ResourceInitializationException {

		super.initialize(context);

		try {

			// Get the resource
			ClassificationScoresSharedResource resource = (ClassificationScoresSharedResource)getContext().getResourceObject(classificationScoresRessourceKey);
			// la ressource était à null car il ne trouvait pas d'objet avec ce nom là SharedRelevantRetrievedAndCorrectCounterResource
			// le problème a été résolu lorsque j'ai déclaré la resource dependencies directement dans l AE.xml 
			// la méthode load est appelé par chaque AE à l'initialisation ; ainsi c'est à la création qu'on load des external data et non lors de l'appel du load... 
			this.setClassificationScoresSharedResource(resource);


			// Get parameters definitions
			String positiveGoldSamplePathString = (String) context.getConfigParameterValue(positiveGoldSamplePath);
			positiveGoldParam = new FeatureModel(positiveGoldSamplePathString);

			String negativeGoldSamplePathString = (String) context.getConfigParameterValue(negativeGoldSamplePath);
			if (negativeGoldSamplePathString != null) negativeGoldParam = new FeatureModel(negativeGoldSamplePathString);
			else negativeGoldParam = new FeatureModel(positiveGoldParam.getFeatureType(),positiveGoldParam.getFeatureName(),"");


			String positiveTestSamplePathString = (String) context.getConfigParameterValue(positiveTestSamplePath);
			positiveTestParam = new FeatureModel(positiveTestSamplePathString);

			String negativeTestSamplePathString = (String) context.getConfigParameterValue(negativeTestSamplePath);
			if (negativeTestSamplePathString != null) negativeTestParam = new FeatureModel(negativeTestSamplePathString);
			else negativeTestParam =  new FeatureModel(positiveTestParam.getFeatureType(),positiveTestParam.getFeatureName(),"");


			// Cheching parameters definitions
			/*
			if (positiveGoldParam.getFeatureName().equalsIgnoreCase("")) {
				if (!positiveTestParam.getFeatureName().equalsIgnoreCase("")) {
					System.err.println("Error: the feature names of the "+positiveGoldSamplePath+" and "+positiveTestSamplePath+" should be both defiled or left empty");
					throw new ResourceInitializationException();
				}
			}
			else if (!positiveGoldParam.getFeatureName().equalsIgnoreCase("")) {
				if (positiveTestParam.getFeatureName().equalsIgnoreCase("")) {
					System.err.println("Error: the feature names of the "+positiveGoldSamplePath+" and "+positiveTestSamplePath+" should be both defiled or left empty");
					throw new ResourceInitializationException();
				}
				else {
					if (positiveGoldParam.getFeatureValue().equalsIgnoreCase("")) {
						if (!positiveTestParam.getFeatureValue().equalsIgnoreCase("")) {
							System.err.println("Error: the feature values of the "+positiveGoldSamplePath+" and "+positiveTestSamplePath+" should be both defiled or left empty");
							throw new ResourceInitializationException();
						}
					}
					else if (!positiveGoldParam.getFeatureValue().equalsIgnoreCase("")) {
						if (positiveTestParam.getFeatureValue().equalsIgnoreCase("")) {
							System.err.println("Error: the feature values of the "+positiveGoldSamplePath+" and "+positiveTestSamplePath+" should be both defiled or left empty");
							throw new ResourceInitializationException();
						}
					}
				}
			}
			 */
		} catch (Exception e) {
			throw new ResourceInitializationException(e);
		}
	}


	/**
	 * 
	 */
	@Override
	public void process(JCas aJCas) throws AnalysisEngineProcessException {
		try {

			/*
			 * Check feature types compatibility
			 */
			Type positiveGoldType = AnnotationUtils.getType(aJCas, positiveGoldParam.getFeatureType());
			// 	equivalent to	Type positiveGoldType = JCasSofaViewUtils.getJCasType(aJCas, positiveGold.getAnnotationTypeName());
			Type positiveTestType = AnnotationUtils.getType(aJCas, positiveTestParam.getFeatureType());
			if (!positiveGoldParam.getFeatureName().equalsIgnoreCase("")) 
				FeatureUtils.checkFeaturesCompatibility(aJCas, positiveGoldType, positiveTestType, positiveGoldParam.getFeatureName(), positiveTestParam.getFeatureName());

			Type negativeGoldType = AnnotationUtils.getType(aJCas, negativeGoldParam.getFeatureType());
			Type negativeTestType = AnnotationUtils.getType(aJCas, negativeTestParam.getFeatureType());
			if (!positiveGoldParam.getFeatureName().equalsIgnoreCase(""))
				FeatureUtils.checkFeaturesCompatibility(aJCas, negativeGoldType, negativeTestType, negativeGoldParam.getFeatureName(), negativeTestParam.getFeatureName());

			/*
			 * get getter method for coming tests 
			 */
			// get the annotation class
			Class<Annotation> positiveGoldAnnotationClass = AnnotationUtils.getAnnotationClass(positiveGoldParam.getFeatureType());
			// get the class constructor
			//Constructor<?> positiveGoldAnnotationClassConstructor = positiveGoldAnnotationClass
			//		.getConstructor(new Class[] { JCas.class });
			// create an object 
			//Object positiveGoldAnnotationObject = null;
			//positiveGoldAnnotationObject = positiveGoldAnnotationClassConstructor.newInstance(new Object[] { aJCas });
			//positiveGoldAnnotationClass.cast(positiveGoldAnnotationObject);						
			// get a method
			Method getPositiveGoldFeatureNameValueMethod = null;
			if (!positiveGoldParam.getFeatureName().equalsIgnoreCase("")) 
				getPositiveGoldFeatureNameValueMethod =FeatureUtils.getFeatureGetterMethod(positiveGoldAnnotationClass, positiveGoldParam.getFeatureName());

			Class<Annotation> positiveTestAnnotationClass = AnnotationUtils.getAnnotationClass(positiveTestParam.getFeatureType());
			Method getPositiveTestFeatureNameValueMethod = null;
			if (!positiveGoldParam.getFeatureName().equalsIgnoreCase("")) 
				getPositiveTestFeatureNameValueMethod = FeatureUtils.getFeatureGetterMethod(positiveTestAnnotationClass, positiveTestParam.getFeatureName());

			/*
			 * actual p prediction p -> true positive (TP)
			 * actual n prediction p -> false positive (FP) 
			 * actual n prediction n -> true negative (TN) 
			 * actual p prediction n -> false negative (FN)  
			 */
			int tp = 0;
			int fp = 0;
			int tn = 0;
			int fn = 0;

			// for each positive gold annotation
			AnnotationIndex<Annotation> positiveGoldIndex = aJCas.getAnnotationIndex(positiveGoldType);
			Iterator<Annotation> positiveGoldIndexIterator = positiveGoldIndex.iterator();
			while (positiveGoldIndexIterator.hasNext()) {
				Annotation positiveGoldAnnotation = positiveGoldIndexIterator.next();

				// search if there is a positive test annotation at the same offsets
				//FSIterator<Annotation> subPositiveGoldAnnotationFSIterator = AnnotationCollectionUtils.subiterator( aJCas,  positiveGoldAnnotation) ;
				AnnotationIndex<Annotation> positiveTestAnnotationAnnotationIndex = aJCas.getAnnotationIndex(positiveTestType);
				Iterator<Annotation> positiveTestAnnotationIterator = positiveTestAnnotationAnnotationIndex.iterator();
				Boolean existsAtTheSameOffsetsAPositiveTestAnnotation = false;
				Annotation positiveTestAnnotation = null;
				//	System.out.println("Debug: ---------------------------------------------------------------------------------------");

				while (positiveTestAnnotationIterator.hasNext()) {
					Annotation aPositiveTestAnnotation = positiveTestAnnotationIterator.next();
					//	System.out.println("Debug: subPositiveGoldAnnotation.getClass().getName() "+ subPositiveGoldAnnotation.getClass().getName());
					//	System.out.println("Annotation subPositiveGoldAnnotation = subPositiveGoldAnnotationFSIterator.next();");

					if (aPositiveTestAnnotation.getClass().getName().equalsIgnoreCase(positiveTestParam.getFeatureType())) {
						//	System.out.println("if (subPositiveGoldAnnotation.getClass().getName().equalsIgnoreCase(positiveTestParam.getAnnotationTypeName())) {");

						if ((positiveGoldAnnotation.getBegin() == aPositiveTestAnnotation.getBegin()) && (positiveGoldAnnotation.getEnd() == aPositiveTestAnnotation.getEnd()) ) {
							existsAtTheSameOffsetsAPositiveTestAnnotation = true;
							positiveTestAnnotation= aPositiveTestAnnotation;
							//		System.out.println("Debug: subPositiveGoldAnnotation.getClass().getName() "+ subPositiveGoldAnnotation.getClass().getName());
						}
					}
				}

				// if there is a positive test annotation at the same offsets
				// this should always be true for case 2 and 3
				// indeed by definition the Negative Gold Annotation have the same type of Positive Gold Annotation, 
				// idem for the Test annotations, 
				// so consequently if Test annotation have been correctly added 
				// they should cover the same text spans as the gold annotations
				if (existsAtTheSameOffsetsAPositiveTestAnnotation) {
					// case 1
					if (positiveGoldParam.getFeatureName().equalsIgnoreCase("")) {
						tp++;
						//System.out.println("Debug: TP "+ positiveGoldAnnotation.getCoveredText());

					}
					// case 2 and 3
					else {
						// invoke the method and get the required object
						String positiveGoldAnnotationFeatureValue = (String) FeatureUtils.invokeFeatureGetterMethod(positiveGoldAnnotation, getPositiveGoldFeatureNameValueMethod);
						// invoke the method and get the required object
						String positiveTestAnnotationFeatureValue = (String) FeatureUtils.invokeFeatureGetterMethod(positiveTestAnnotation, getPositiveTestFeatureNameValueMethod);

						// case 2
						if (!positiveTestParam.getFeatureValue().equalsIgnoreCase("")) {
							if (positiveGoldAnnotationFeatureValue.equalsIgnoreCase(positiveTestAnnotationFeatureValue)) {
								if (positiveGoldAnnotationFeatureValue.equalsIgnoreCase(positiveTestParam.getFeatureValue())) {
									tp++;
									//System.out.println("Debug: TP "+ positiveGoldAnnotation.getCoveredText());

								}
								else {
									tn++;
									//System.out.println("Debug: TN "+ positiveGoldAnnotation.getCoveredText());

								}
							}
							else {
								if (positiveGoldAnnotationFeatureValue.equalsIgnoreCase(positiveTestParam.getFeatureValue())) {
									fn++;
									//System.out.println("Debug: FN "+ positiveGoldAnnotation.getCoveredText());

								}
								else {
									fp++;
									//System.out.println("Debug: FP "+ positiveGoldAnnotation.getCoveredText());

								}

							}

						}
						// case 3
						else {
							if (positiveGoldAnnotationFeatureValue.equalsIgnoreCase(positiveTestAnnotationFeatureValue)) {
								tp++; tn++;
								//System.out.println("Debug: TP TN "+ positiveGoldAnnotation.getCoveredText());

							}
							else {
								fn++; fp++;
								//System.out.println("Debug: FN FP "+ positiveGoldAnnotation.getCoveredText());


							}
						}

					}
				}
				else {
					// case 2 and 3 should never goes there
					if (!positiveGoldParam.getFeatureName().equalsIgnoreCase("")) {
						System.err.println("Error: by definition the Negative Gold Annotation have the same type of Positive Gold Annotation, idem for the Test annotations, so consequently if Test annotation have been correctly added they should cover the same text spans as the gold annotations");
						throw new AnalysisEngineProcessException();
					}
					fn++;
					//System.out.println("Debug: FN "+ positiveGoldAnnotation.getCoveredText());


				}

				//TODO have a look at 	AnnotationUtils.createAnnotation(aJCas, this.type.getName(),	featuresHashMap);

			}


			// particular situation of case 1
			// the other cases assumed positive and negative annotation have the same name but different feature values
			// consequently the other cases are handled above
			if (!positiveGoldParam.getFeatureType().equalsIgnoreCase(negativeGoldParam.getFeatureType())) {
				// for each negative gold annotation 
				AnnotationIndex<Annotation> negativeGoldIndex = aJCas.getAnnotationIndex(negativeGoldType);
				Iterator<Annotation> negativeGoldIndexIterator = negativeGoldIndex.iterator();
				while (negativeGoldIndexIterator.hasNext()) {
					Annotation negativeGoldAnnotation = negativeGoldIndexIterator.next();

					// search if there is a positive test annotation at the same offsets
					//FSIterator<Annotation> subNegativeGoldAnnotationFSIterator = AnnotationCollectionUtils.subiterator( aJCas,  negativeGoldAnnotation) ;
					AnnotationIndex<Annotation> subNegativeTestAnnotationIndex = aJCas.getAnnotationIndex(negativeTestType);
					Iterator<Annotation> subNegativeTestAnnotationFSIterator = subNegativeTestAnnotationIndex.iterator();

					Boolean existsAtTheSameOffsetsANegativeTestAnnotation = false;
					//	Annotation negativeTestAnnotation = null;
					while (subNegativeTestAnnotationFSIterator.hasNext()) {
						Annotation subNegativeTestAnnotation = subNegativeTestAnnotationFSIterator.next();


						//if (subNegativeGoldAnnotation.getClass().getName().equalsIgnoreCase(negativeTestParam.getAnnotationTypeName())) {
							if ((negativeGoldAnnotation.getBegin() == subNegativeTestAnnotation.getBegin()) && (negativeGoldAnnotation.getEnd() == subNegativeTestAnnotation.getEnd()) ) 
								existsAtTheSameOffsetsANegativeTestAnnotation = true;
							//		negativeTestAnnotation= subNegativeGoldAnnotation;
						//}
					}

					// if there is a positive test annotation at the same offsets
					if (existsAtTheSameOffsetsANegativeTestAnnotation) {
						tn++; // fp++;
						//System.out.println("Debug: TN "+ negativeGoldAnnotation.getCoveredText());
					}
					else {
						fp++; // tn++;
						//System.out.println("Debug: FP "+ negativeGoldAnnotation.getCoveredText());

					}
				}

				//
				getClassificationScoresSharedResource().addActualNegative(negativeGoldIndex.size());

			}

			//
			getClassificationScoresSharedResource().addActualPositive(positiveGoldIndex.size());

			//TODO add negative and positive prediction but correspond respectively to fp+fn and tp+tn 

			//
			getClassificationScoresSharedResource().addTruePositive(tp);
			getClassificationScoresSharedResource().addFalseNegative(fn);
			getClassificationScoresSharedResource().addFalsePositive(fp);
			getClassificationScoresSharedResource().addTrueNegative(tn);

			//this.countRelevantRetrievedAndTheCorrect(aJCas,testType,goldType,this.getFeatureNames());
		} catch (Exception e) {
			throw new AnalysisEngineProcessException(e);
		}
	}

	/**
	 * 
	 */
	@Override
	public void collectionProcessComplete () throws AnalysisEngineProcessException {
		//TODO compute classification scores based on aClassificationScoresSharedResource (with a boolean already displayed ?)

		int ap = getClassificationScoresSharedResource().getActualPositive();
		int an = getClassificationScoresSharedResource().getActualNegative();
		int tp = getClassificationScoresSharedResource().getTruePositive();
		int fp = getClassificationScoresSharedResource().getFalsePositive();
		int tn = getClassificationScoresSharedResource().getTrueNegative();
		int fn = getClassificationScoresSharedResource().getFalseNegative();
		
		double p = ClassificationScores.precision(tp, fp);
		double r = ClassificationScores.recall(tp, fn);
		double fm = ClassificationScores.fMeasure(tp, fp, fn);
		double a = ClassificationScores.accuracy(tp, tn, fp, fn);

		double pn = ClassificationScores.precision(tn, fn);
		double rn = ClassificationScores.recall(tn, fp);
		double fmn = ClassificationScores.fMeasure(tn, fn, fp);
		
		System.err.println("INFO: &  ap &  an &  tp &  tn &  fp &  fn &  P(y) &  R(y) &  F(y) &  P(n) &  R(n) &  F(n) &  Acc. \\\\");
		System.err.format("INFO: & %3d & %3d & %3d & %3d & %3d & %3d & %3.3f & %3.3f & %3.3f & %3.3f & %3.3f & %3.3f & %3.3f \\\\%n", ap, an, tp, tn, fp, fn, p, r, fm, pn, rn, fmn, a);

		
		/*
		System.err.println("Info:\n"
		+"actual p prediction p -> true positive (TP)\n"
		+"actual n prediction p -> false positive (FP)\n"
		+"actual n prediction n -> true negative (TN)\n"
		+"actual p prediction n -> false negative (FN)");
		System.err.println("Info:");
		System.err.println("Info: sample size \t"+(ap+an));
		System.err.println("Info:");

		System.err.println("Info:             \t& Condition as determined by Gold standard &\\\\");
		System.err.println("Info:----------------------------------------------------------------------------------------------------");
		System.err.println("Info:             \t& actual positive & actual negative &\\\\");
		System.err.println("Info:             \t&\t "+ap+" \t&\t "+an+" \t&\\\\");
		System.err.println("Info:----------------------------------------------------------------------------------------------------");
		System.err.println("Info: test outcome\t& true positive & false positive & Positive predictive value or Precision\\\\");
		System.err.println("Info:             \t&\t "+tp+" \t&\t "+fp+" \t&\\\\");
		System.err.println("Info:               -------------------------------------------------------------------------------------");
		System.err.println("Info:             \t& false negative & true negative & Negative predictive value\\\\");
		System.err.println("Info:             \t&\t "+fn+" \t&\t "+tn+" \t&\\\\");
		System.err.println("Info:----------------------------------------------------------------------------------------------------");
		System.err.println("Info:             \t& Sensitivity or recall & Specificity (or its complement, Fall-Out) & Accuracy\\\\");
		System.err.println("Info:----------------------------------------------------------------------------------------------------");
		System.err.println("Info:");
*/

	}

	/**
	 * 
	 * @param n
	 * @return
	 */
	private String getWhitespaces(int n) {
		if (n < 0 ) return "";
		int i = 0;
		String toReturn = "";
		while (i < n) {
			i++;
			toReturn += " ";
		}
		return toReturn;
	}

}
