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

import java.io.PrintStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.cas.Feature;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.TypeSystem;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.tcas.Annotation;


















































public class AnnotationUtils
{
/*  68 */   private static String CURRENTCLASSNAME = "AnnotationUtilities";
  












  public static Class<Annotation> getAnnotationClass(String annotationString)
    throws AnalysisEngineProcessException
  {
/*  85 */     Class<Annotation> annotationClass = null;
    try {
/*  87 */       annotationClass = 
/*  88 */         Class.forName(annotationString);
    } catch (ClassNotFoundException e) {
/*  90 */       String errmsg = "Error: Class " + annotationString + 
/*  91 */         " not found !";
/*  92 */       throw new AnalysisEngineProcessException(errmsg, 
/*  93 */         new Object[] { annotationString }, e);
    }
    



/*  99 */     return annotationClass;
  }
  









  public static Object newJCasObjectClassInstance(Class aObjectClass, JCas aJCas)
    throws AnalysisEngineProcessException
  {
/* 114 */     Constructor aClassConstructor = null;
    try {
/* 116 */       aClassConstructor = aObjectClass.getConstructor(new Class[] { JCas.class });
    } catch (SecurityException e) {
/* 118 */       String errmsg = "Error: " + CURRENTCLASSNAME + " getObject SecurityException !";
/* 119 */       throw new AnalysisEngineProcessException(errmsg, 
/* 120 */         new Object[0], e);
    }
    catch (NoSuchMethodException e) {
/* 123 */       String errmsg = "Error: " + CURRENTCLASSNAME + " getObject NoSuchMethodException !";
/* 124 */       throw new AnalysisEngineProcessException(errmsg, 
/* 125 */         new Object[0], e);
    }
    

/* 129 */     Object aConstructedObject = null;
    try {
/* 131 */       aConstructedObject = aClassConstructor.newInstance(new Object[] { aJCas });
    } catch (IllegalArgumentException e) {
/* 133 */       String errmsg = "Error: " + CURRENTCLASSNAME + " getObject IllegalArgumentException !";
/* 134 */       throw new AnalysisEngineProcessException(errmsg, 
/* 135 */         new Object[0], e);
    }
    catch (InstantiationException e) {
/* 138 */       String errmsg = "Error: " + CURRENTCLASSNAME + " getObject InstantiationException !";
/* 139 */       throw new AnalysisEngineProcessException(errmsg, 
/* 140 */         new Object[0], e);
    }
    catch (IllegalAccessException e) {
/* 143 */       String errmsg = "Error: " + CURRENTCLASSNAME + " getObject IllegalAccessException !";
/* 144 */       throw new AnalysisEngineProcessException(errmsg, 
/* 145 */         new Object[0], e);
    }
    catch (InvocationTargetException e) {
/* 148 */       String errmsg = "Error: " + CURRENTCLASSNAME + " getObject InvocationTargetException !";
/* 149 */       throw new AnalysisEngineProcessException(errmsg, 
/* 150 */         new Object[0], e);
    }
    

/* 154 */     return aConstructedObject;
  }
  











  public static Object castObjectWithAGivenClass(Class aObjectClass, Object aConstructedObject)
    throws AnalysisEngineProcessException
  {
/* 171 */     aObjectClass.cast(aConstructedObject);
/* 172 */     return aConstructedObject;
  }
  






  public static Object getCastedObjectOfAnnotationType(JCas aJCas, String annotationTypeName)
    throws AnalysisEngineProcessException
  {
/* 184 */     Class<Annotation> annotationClass = getAnnotationClass(annotationTypeName);
/* 185 */     Object annotationObject = newJCasObjectClassInstance(annotationClass, aJCas);
/* 186 */     return castObjectWithAGivenClass(annotationClass, annotationObject);
  }
  



































































































































































  public static Object createAnnotation(JCas aJCas, String annotationNameToCreate, int beginFeatureValue, int endFeatureValue)
    throws AnalysisEngineProcessException
  {
/* 355 */     Object t = null;
    try {
/* 357 */       Object[] args = null;
      
/* 359 */       Class<Annotation> TgtClass = 
/* 360 */         Class.forName(annotationNameToCreate);
      

/* 363 */       Constructor<?> tgtConstr = TgtClass
/* 364 */         .getConstructor(new Class[] { JCas.class });
      


/* 368 */       t = tgtConstr.newInstance(new Object[] { aJCas });
/* 369 */       TgtClass.cast(t);
      
/* 371 */       Method addToIndexes = TgtClass.getMethod("addToIndexes", 
/* 372 */         new Class[0]);
      
/* 374 */       Method setBegin = TgtClass.getMethod("setBegin", new Class[] { Integer.TYPE });
/* 375 */       Method setEnd = TgtClass.getMethod("setEnd", new Class[] { Integer.TYPE });
      

/* 378 */       setBegin.invoke(t, new Object[] { Integer.valueOf(beginFeatureValue) });
/* 379 */       setEnd.invoke(t, new Object[] { Integer.valueOf(endFeatureValue) });
      

/* 382 */       if (beginFeatureValue < endFeatureValue) {
/* 383 */         addToIndexes.invoke(t, args);
      }
    } catch (IllegalArgumentException e) {
/* 386 */       String errmsg = "Error: IllegalArgumentException  !";
/* 387 */       throw new AnalysisEngineProcessException(errmsg, 
/* 388 */         new Object[0], e);
    }
    catch (IllegalAccessException e) {
/* 391 */       String errmsg = "Error: IllegalAccessException  !";
/* 392 */       throw new AnalysisEngineProcessException(errmsg, 
/* 393 */         new Object[0], e);
    }
    catch (InvocationTargetException e) {
/* 396 */       String errmsg = "Error: InvocationTargetException  !";
/* 397 */       throw new AnalysisEngineProcessException(errmsg, 
/* 398 */         new Object[0], e);
    }
    catch (ClassNotFoundException e) {
/* 401 */       String errmsg = "Error: ClassNotFoundException  !";
/* 402 */       throw new AnalysisEngineProcessException(errmsg, 
/* 403 */         new Object[0], e);
    }
    catch (SecurityException e) {
/* 406 */       String errmsg = "Error: SecurityException  !";
/* 407 */       throw new AnalysisEngineProcessException(errmsg, 
/* 408 */         new Object[0], e);
    }
    catch (NoSuchMethodException e) {
/* 411 */       String errmsg = "Error: NoSuchMethodException  !";
/* 412 */       throw new AnalysisEngineProcessException(errmsg, 
/* 413 */         new Object[0], e);
    }
    catch (InstantiationException e) {
/* 416 */       String errmsg = "Error: InstantiationException  !";
/* 417 */       throw new AnalysisEngineProcessException(errmsg, 
/* 418 */         new Object[0], e);
    }
    
/* 421 */     return t;
  }
  















  public static Object createAnnotation(JCas aJCas, String annotationNameToCreate, Map<String, Object> featuresHashMap)
    throws AnalysisEngineProcessException
  {
/* 442 */     Object t = null;
    try {
/* 444 */       Object[] args = null;
      
/* 446 */       Class<Annotation> annotationClass = 
/* 447 */         Class.forName(annotationNameToCreate);
      

/* 450 */       Constructor<?> annotationClassConstructor = annotationClass
/* 451 */         .getConstructor(new Class[] { JCas.class });
      


/* 455 */       t = annotationClassConstructor.newInstance(new Object[] { aJCas });
/* 456 */       annotationClass.cast(t);
      

/* 459 */       Method addToIndexes = annotationClass.getMethod("addToIndexes", 
/* 460 */         new Class[0]);
      



/* 465 */       Type annotationType = JCasUtils.getJCasType(aJCas, annotationNameToCreate);
      
/* 467 */       Iterator featureHashMapKeySetIterator = featuresHashMap.keySet().iterator();
/* 468 */       while (featureHashMapKeySetIterator.hasNext())
      {

/* 471 */         String featureName = (String)featureHashMapKeySetIterator.next();
        






/* 479 */         Feature featureFeature = null;
        
/* 481 */         Type featureType = null;
        try {
/* 483 */           featureFeature = annotationType.getFeatureByBaseName(featureName);
/* 484 */           featureType = featureFeature.getRange();
        } catch (NullPointerException e) {
/* 486 */           System.err.println("Error: featureType " + featureType + " is not present in the Type System. Check if the feature type you are specifying is correct.");
        }
        



/* 492 */         Method setFeatureMethod = null;
        
        try
        {
/* 496 */           setFeatureMethod = FeatureUtils.getFeatureSetterMethod(annotationClass, featureName, featureType);
        } catch (NullPointerException e) {
/* 498 */           System.err.println("Error: featureType " + featureName + " is not present in the Type System. Check if the feature type you are specifying is correct.");
        }
        
/* 501 */         FeatureUtils.invokeFeatureSetterMethod(t, featureType, setFeatureMethod, featuresHashMap, featureName);
      }
      



/* 507 */       addToIndexes.invoke(t, args);
    }
    catch (IllegalArgumentException e) {
/* 510 */       String errmsg = "Error: IllegalArgumentException  !";
/* 511 */       throw new AnalysisEngineProcessException(errmsg, 
/* 512 */         new Object[0], e);
    }
    catch (IllegalAccessException e) {
/* 515 */       String errmsg = "Error: IllegalAccessException  !";
/* 516 */       throw new AnalysisEngineProcessException(errmsg, 
/* 517 */         new Object[0], e);
    }
    catch (InvocationTargetException e) {
/* 520 */       String errmsg = "Error: InvocationTargetException  !";
/* 521 */       throw new AnalysisEngineProcessException(errmsg, 
/* 522 */         new Object[0], e);
    }
    catch (ClassNotFoundException e) {
/* 525 */       String errmsg = "Error: ClassNotFoundException  !";
/* 526 */       throw new AnalysisEngineProcessException(errmsg, 
/* 527 */         new Object[0], e);
    }
    catch (SecurityException e) {
/* 530 */       String errmsg = "Error: SecurityException  !";
/* 531 */       throw new AnalysisEngineProcessException(errmsg, 
/* 532 */         new Object[0], e);
    }
    catch (NoSuchMethodException e) {
/* 535 */       String errmsg = "Error: NoSuchMethodException  !";
/* 536 */       throw new AnalysisEngineProcessException(errmsg, 
/* 537 */         new Object[0], e);
    }
    catch (InstantiationException e) {
/* 540 */       String errmsg = "Error: InstantiationException  !";
/* 541 */       throw new AnalysisEngineProcessException(errmsg, 
/* 542 */         new Object[0], e);
    }
    catch (NullPointerException e)
    {
/* 546 */       String errmsg = "Error: NullPointerException  ! is the type " + annotationNameToCreate + " present in your type system ?";
/* 547 */       throw new AnalysisEngineProcessException(errmsg, 
/* 548 */         new Object[0], e);
    }
    
/* 551 */     return t;
  }
  













  public static void updateAnnotation(JCas aJCas, Object annotationToUpdate, String annotationToProcessString, Map<String, Object> featuresHashMap)
    throws AnalysisEngineProcessException
  {
    try
    {
/* 572 */       Object[] args = null;
      
/* 574 */       Class<Annotation> annotationClass = 
/* 575 */         Class.forName(annotationToUpdate.getClass().getName());
      
/* 577 */       annotationClass = 
/* 578 */         Class.forName(annotationToProcessString);
      
/* 580 */       annotationClass.cast(annotationToUpdate);
      



/* 585 */       Type annotationType = JCasUtils.getJCasType(aJCas, annotationToUpdate.getClass().getName());
      
/* 587 */       Iterator featureHashMapKeySetIterator = featuresHashMap.keySet().iterator();
/* 588 */       while (featureHashMapKeySetIterator.hasNext())
      {

/* 591 */         String featureName = (String)featureHashMapKeySetIterator.next();
        

/* 594 */         String setFeatureMethodName = FeatureUtils.buildFeatureSetterMethodName(featureName);
        


/* 598 */         Feature featureFeature = annotationType.getFeatureByBaseName(featureName);
/* 599 */         Type featureType = featureFeature.getRange();
        

/* 602 */         Method setFeatureMethod = FeatureUtils.getFeatureSetterMethod(annotationClass, featureName, featureType);
        

/* 605 */         FeatureUtils.invokeFeatureSetterMethod(annotationToUpdate, featureType, setFeatureMethod, featuresHashMap, featureName);
      }
    }
    catch (IllegalArgumentException e) {
/* 609 */       String errmsg = "Error: IllegalArgumentException  !";
/* 610 */       throw new AnalysisEngineProcessException(errmsg, 
/* 611 */         new Object[0], e);
    }
    catch (ClassNotFoundException e) {
/* 614 */       String errmsg = "Error: ClassNotFoundException  !";
/* 615 */       throw new AnalysisEngineProcessException(errmsg, 
/* 616 */         new Object[0], e);
    }
    catch (SecurityException e) {
/* 619 */       String errmsg = "Error: SecurityException  !";
/* 620 */       throw new AnalysisEngineProcessException(errmsg, 
/* 621 */         new Object[0], e);
    }
  }
  






  public static Type getType(JCas aJCas, String annotationTypeName)
  {
/* 633 */     return aJCas.getTypeSystem().getType(annotationTypeName);
  }
}

