/** 
 * Copyright (C) 2010-20..  Nicolas Hernandez
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
package fr.univnantes.lina.uima.common.cas;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.cas.ConstraintFactory;
import org.apache.uima.cas.FSIntConstraint;
import org.apache.uima.cas.FSIterator;
import org.apache.uima.cas.FSMatchConstraint;
import org.apache.uima.cas.FSTypeConstraint;
import org.apache.uima.cas.Feature;
import org.apache.uima.cas.FeaturePath;
import org.apache.uima.cas.FeatureStructure;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.TypeSystem;
import org.apache.uima.cas.text.AnnotationIndex;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.cas.FSArray;
import org.apache.uima.jcas.tcas.Annotation;









































public class AnnotationCollectionUtils
{
	public static Class retrieveAndCastAnAnnotation(FSIterator annotationToLineFSIterator, Annotation annotationToLine)
			throws AnalysisEngineProcessException
			{
		/*  69 */     Object annotationObject = annotationToLineFSIterator.next();
		/*  70 */     Class annotationClass = annotationObject.getClass();
		/*  71 */     String className = "null";
		/*  72 */     className = annotationClass.getName();

		/*  74 */     Class<Annotation> inputAnnotationClass = AnnotationUtils.getAnnotationClass(className);
		/*  75 */     annotationToLine = (Annotation)annotationObject;
		/*  76 */     inputAnnotationClass.cast(annotationToLine);
		/*  77 */     return inputAnnotationClass;
			}












	public static void removeSubsumedAnnotation(JCas aJCas, String subsumingAnnotation, String subsumedAnnotation, boolean strictOffset)
	{
		/*  93 */     List<Annotation> annotationToRemoveList = new ArrayList();

		/*  95 */     Type subsumingAnnotationType = aJCas.getTypeSystem().getType(subsumingAnnotation);
		/*  96 */     AnnotationIndex<Annotation> subsumingAnnotationIndex = aJCas.getAnnotationIndex(subsumingAnnotationType);

		/*  98 */     Type subsumedAnnotationType = aJCas.getTypeSystem().getType(subsumedAnnotation);
		/*  99 */     AnnotationIndex<Annotation> subsumedAnnotationIndex = aJCas.getAnnotationIndex(subsumedAnnotationType);

		/* 101 */     FSIterator<Annotation> subsumingAnnotationIterator = subsumingAnnotationIndex.iterator();
		FSIterator<Annotation> subsumedAnnotationIterator;
		/* 103 */     while (subsumingAnnotationIterator.hasNext())  {

			/* 104 */       Annotation aSubsumingAnnotation = (Annotation)subsumingAnnotationIterator.next();
			/* 105 */       subsumedAnnotationIterator = subsumedAnnotationIndex.subiterator(aSubsumingAnnotation);
			/* 106 */       while (subsumedAnnotationIterator.hasNext()) {
				/* 107 */    	   Annotation aSubsumedAnnotation = (Annotation)subsumedAnnotationIterator.next();
				/* 108 */     		  if (strictOffset) {
					/* 109 */        		 if ((aSubsumingAnnotation.getBegin() == aSubsumedAnnotation.getBegin()) && (aSubsumingAnnotation.getEnd() == aSubsumedAnnotation.getEnd()))
						/* 110 */           annotationToRemoveList.add(aSubsumedAnnotation);
				} else {
					/* 112 */         annotationToRemoveList.add(aSubsumedAnnotation);
				}
			}
		}

		/* 117 */     for (Annotation remove : annotationToRemoveList) {
			/* 118 */       remove.removeFromIndexes();
		}
	}














	public static void removeDuplicateAnnotations(JCas aJCas)
	{
		/* 137 */     AnnotationIndex<Annotation> anAnnotationIndex = aJCas.getAnnotationIndex();
		/* 138 */     FSIterator<Annotation> anAnnotationIterator = anAnnotationIndex.iterator();

		/* 140 */     while (anAnnotationIterator.hasNext()) {
			/* 141 */       Annotation anAnnotation = (Annotation)anAnnotationIterator.next();
			/* 142 */       removeSubsumedAnnotation(aJCas, anAnnotation.getClass().getName(), anAnnotation.getClass().getName(), true);
		}
	}





























	public static void removeDuplicateAndSubsumedAnnotations(JCas aJCas, String subsumingAnnotation)
	{
		/* 176 */     removeSubsumedAnnotation(aJCas, subsumingAnnotation, subsumingAnnotation, false);
	}



































	public static FSIterator<Annotation> subiterator(JCas aJCas, Annotation contextAnnotation, HashMap<String, ?> inputAnnotationHashMap, boolean isStrict)
			throws AnalysisEngineProcessException
			{
		/* 216 */     return subiterator(aJCas, contextAnnotation, inputAnnotationHashMap.keySet(), 
				/* 217 */       isStrict);
			}

































	public static FSIterator<Annotation> subiterator(JCas aJCas, Annotation contextAnnotation, Set<String> inputAnnotationSet, boolean isStrict)
			throws AnalysisEngineProcessException
			{
		/* 255 */     Type contextAnnotationType = contextAnnotation.getType();


		/* 258 */     ConstraintFactory theConstraints = aJCas.getConstraintFactory();


		/* 261 */     FSIntConstraint beginConstraint = theConstraints.createIntConstraint();
		/* 262 */     if (isStrict) {
			/* 263 */       beginConstraint.eq(contextAnnotation.getBegin());
		}
		else {
			/* 266 */       beginConstraint.geq(contextAnnotation.getBegin() - 1);
		}
		/* 268 */     Feature beginFeature = contextAnnotationType.getFeatureByBaseName("begin");
		/* 269 */     FeaturePath beginPath = aJCas.createFeaturePath();
		/* 270 */     beginPath.addFeature(beginFeature);
		/* 271 */     FSMatchConstraint begin = theConstraints.embedConstraint(beginPath, beginConstraint);



		/* 275 */     FSIntConstraint endConstraint = theConstraints.createIntConstraint();
		/* 276 */     if (isStrict) {
			/* 277 */       endConstraint.eq(contextAnnotation.getEnd());
		} else {
			/* 279 */       endConstraint.leq(contextAnnotation.getEnd() + 1);
		}
		/* 281 */     Feature endFeature = contextAnnotationType.getFeatureByBaseName("end");
		/* 282 */     FeaturePath endPath = aJCas.createFeaturePath();
		/* 283 */     endPath.addFeature(endFeature);
		/* 284 */     FSMatchConstraint end = theConstraints.embedConstraint(endPath, endConstraint);




		/* 289 */     FSTypeConstraint typeConstraint = theConstraints.createTypeConstraint();






		/* 296 */     Iterator<String> keyIter = inputAnnotationSet.iterator();
		/* 297 */     while (keyIter.hasNext()) {
			/* 298 */       String key = (String)keyIter.next();


			/* 301 */       typeConstraint.add(JCasUtils.getJCasType(aJCas, key));
		}
		/* 303 */     FeaturePath typePath = aJCas.createFeaturePath();
		/* 304 */     FSMatchConstraint type = theConstraints.embedConstraint(typePath, typeConstraint);


		/* 307 */     FSMatchConstraint typeAndBeginAndEnd = null;
		/* 308 */     typeAndBeginAndEnd = theConstraints.and(type, theConstraints.and(begin, end));


		/* 311 */     FSIterator<Annotation> filteredIterator = null;
		/* 312 */     filteredIterator = aJCas.createFilteredIterator(aJCas.getAnnotationIndex().iterator(), typeAndBeginAndEnd);

		/* 314 */     return filteredIterator;
			}





























	public static FSIterator<Annotation> subiterator(JCas aJCas, Annotation beginEndAnnotation)
	{
		/* 347 */     AnnotationIndex<Annotation> index = aJCas.getAnnotationIndex();
		/* 348 */     Annotation between = new Annotation(aJCas, beginEndAnnotation.getBegin(), beginEndAnnotation.getEnd());
		/* 349 */     return index.subiterator(between);
	}















	public static ArrayList<FeatureStructure> getAnnotationArray(JCas aJCas, HashMap<String, Integer> annotationHashMap)
			throws AnalysisEngineProcessException
			{
		/* 369 */     ArrayList<FeatureStructure> result = new ArrayList();

		/* 371 */     AnnotationIndex<Annotation> annotationIndex = 
				/* 372 */       aJCas.getAnnotationIndex();
		/* 373 */     FSIterator<Annotation> annotationIndexIterator = annotationIndex.iterator();







		/* 381 */     while (annotationIndexIterator.hasNext())
		{
			/* 383 */       Object annotationObject = annotationIndexIterator.next();
			/* 384 */       Class<? extends Object> annotationClass = annotationObject.getClass();
			/* 385 */       String className = "null";
			/* 386 */       if (annotationClass != null) {
				/* 387 */         className = annotationClass.getName();

				/* 389 */         if ((annotationHashMap.size() == 0) || (annotationHashMap.containsKey(className))) {
					/* 390 */           result.add((FeatureStructure)annotationObject);
				}
			}
		}


		/* 396 */     return result;
			}






	public static FSArray toFSArray(JCas jCas, List<? extends FeatureStructure> fsList)
	{
		/* 406 */     if (fsList == null) {
			/* 407 */       return new FSArray(jCas, 0);
		}
		/* 409 */     FSArray fsArray = new FSArray(jCas, fsList.size());
		/* 410 */     fsArray.copyFromArray((FeatureStructure[])fsList.toArray(new FeatureStructure[fsList.size()]), 0, 0, fsList.size());
		/* 411 */     return fsArray;
	}
}

