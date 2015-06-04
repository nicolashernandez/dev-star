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

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.cas.Feature;
import org.apache.uima.cas.Type;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.cas.StringArray;
import org.apache.uima.jcas.tcas.Annotation;















































public class FeatureUtils
{
  private static final String colon = ":";
  private static final String equalSign = "=";
  public static final String ANNOTATION_TYPE_NAME_KEY = "annotationTypeName";
  public static final String FEATURE_NAME_KEY = "featureName";
  public static final String FEATURE_VALUE_KEY = "featureValue";
  
  public static String buildFeatureGetterMethodName(String featureNameString)
  {
/*  70 */     String featureMethodName = "get" + featureNameString.substring(0, 1).toUpperCase() + featureNameString.substring(1);
/*  71 */     return featureMethodName;
  }
  









  public static Method getFeatureGetterMethod(Class<? extends Annotation> inputAnnotationClass, String inputFeatureString)
    throws AnalysisEngineProcessException
  {
/*  86 */     String getFeatureMethodName = buildFeatureGetterMethodName(inputFeatureString);
    
/*  88 */     Method getFeatureMethod = null;
    try {
/*  90 */       getFeatureMethod = inputAnnotationClass.getMethod(getFeatureMethodName, new Class[0]);
    } catch (SecurityException e) {
/*  92 */       String errmsg = "Error: a SecurityException with getMethod " + getFeatureMethodName + 
/*  93 */         " !";
/*  94 */       throw new AnalysisEngineProcessException(errmsg, 
/*  95 */         new Object[] { getFeatureMethodName }, e);
    }
    catch (NoSuchMethodException e) {
/*  98 */       String errmsg = "Error: NoSuchMethodException getMethod " + getFeatureMethodName + 
/*  99 */         " !";
/* 100 */       throw new AnalysisEngineProcessException(errmsg, 
/* 101 */         new Object[] { getFeatureMethodName }, e);
    }
    
/* 104 */     return getFeatureMethod;
  }
  




  public static String buildFeatureSetterMethodName(String featureNameString)
  {
/* 113 */     String featureMethodName = "set" + featureNameString.substring(0, 1).toUpperCase() + featureNameString.substring(1);
/* 114 */     return featureMethodName;
  }
  








  public static Method getFeatureSetterMethod(Class<?> inputAnnotationClass, String inputFeatureString, Type inputFeatureType)
    throws AnalysisEngineProcessException
  {
/* 128 */     String setFeatureMethodName = buildFeatureSetterMethodName(inputFeatureString);
    



/* 133 */     Method setFeatureMethod = null;
    
    try
    {
/* 137 */       if (inputFeatureType.getName().equalsIgnoreCase("uima.cas.String")) {
/* 138 */         setFeatureMethod = inputAnnotationClass.getMethod(setFeatureMethodName, new Class[] { String.class });
      }
/* 140 */       else if (inputFeatureType.getName().equalsIgnoreCase("uima.cas.Integer")) {
/* 141 */         setFeatureMethod = inputAnnotationClass.getMethod(setFeatureMethodName, new Class[] { Integer.TYPE });
      }
/* 143 */       else if (inputFeatureType.getName().equalsIgnoreCase("uima.cas.Double")) {
/* 144 */         setFeatureMethod = inputAnnotationClass.getMethod(setFeatureMethodName, new Class[] { Double.TYPE });
      }
/* 146 */       else if (inputFeatureType.getName().equalsIgnoreCase("uima.cas.Short")) {
/* 147 */         setFeatureMethod = inputAnnotationClass.getMethod(setFeatureMethodName, new Class[] { Short.TYPE });
      }
/* 149 */       else if (inputFeatureType.getName().equalsIgnoreCase("uima.cas.Long")) {
/* 150 */         setFeatureMethod = inputAnnotationClass.getMethod(setFeatureMethodName, new Class[] { Long.TYPE });
      }
/* 152 */       else if (inputFeatureType.getName().equalsIgnoreCase("uima.cas.Float")) {
/* 153 */         setFeatureMethod = inputAnnotationClass.getMethod(setFeatureMethodName, new Class[] { Float.TYPE });
      }
/* 155 */       else if (inputFeatureType.getName().equalsIgnoreCase("uima.cas.Boolean")) {
/* 156 */         setFeatureMethod = inputAnnotationClass.getMethod(setFeatureMethodName, new Class[] { Boolean.TYPE });
      }
/* 158 */       else if (inputFeatureType.getName().equalsIgnoreCase("uima.cas.Byte")) {
/* 159 */         setFeatureMethod = inputAnnotationClass.getMethod(setFeatureMethodName, new Class[] { Byte.TYPE });
      }
/* 161 */       else if (inputFeatureType.getName().equalsIgnoreCase("uima.cas.StringArray")) {
/* 162 */         setFeatureMethod = inputAnnotationClass.getMethod(setFeatureMethodName, new Class[] { StringArray.class });
      }
      else {
/* 165 */         String errmsg = "Error: unhandled inputFeatureType in UIMAUtilities getSetterMethod :" + inputFeatureType.getName() + 
/* 166 */           " !";
/* 167 */         throw new AnalysisEngineProcessException(errmsg, 
/* 168 */           new Object[] { setFeatureMethodName });
      }
    }
    catch (SecurityException e)
    {
/* 173 */       String errmsg = "Error: a SecurityException with getMethod " + setFeatureMethodName + 
/* 174 */         " !";
/* 175 */       throw new AnalysisEngineProcessException(errmsg, 
/* 176 */         new Object[] { setFeatureMethodName }, e);
    }
    catch (NoSuchMethodException e) {
/* 179 */       String errmsg = "Error: NoSuchMethodException getMethod " + setFeatureMethodName + 
/* 180 */         " !";
/* 181 */       throw new AnalysisEngineProcessException(errmsg, 
/* 182 */         new Object[] { setFeatureMethodName }, e);
    }
    
/* 185 */     return setFeatureMethod;
  }
  











  public static Object invokeFeatureGetterMethod(Annotation inputAnnotation, Method getFeatureMethod)
    throws AnalysisEngineProcessException
  {
/* 202 */     Object result = null;
    
    try
    {
/* 206 */       result = getFeatureMethod.invoke(inputAnnotation, new Object[0]);
    } catch (IllegalArgumentException e) {
/* 208 */       String errmsg = "Error: IllegalArgumentException invoked " + inputAnnotation + 
/* 209 */         " !";
/* 210 */       throw new AnalysisEngineProcessException(errmsg, 
/* 211 */         new Object[] { inputAnnotation }, e);
    }
    catch (IllegalAccessException e) {
/* 214 */       String errmsg = "Error: IllegalAccessException invoked " + inputAnnotation + 
/* 215 */         " !";
/* 216 */       throw new AnalysisEngineProcessException(errmsg, 
/* 217 */         new Object[] { inputAnnotation }, e);
    }
    catch (InvocationTargetException e) {
/* 220 */       String errmsg = "Error: InvocationTargetException invoked " + inputAnnotation + 
/* 221 */         " !";
/* 222 */       throw new AnalysisEngineProcessException(errmsg, 
/* 223 */         new Object[] { inputAnnotation }, e);
    }
    

/* 227 */     return result;
  }
  













  public static void invokeFeatureSetterMethod(Object t, Type featureType, Method setFeatureMethod, Map<String, Object> featuresHashMap, String featureName)
    throws AnalysisEngineProcessException
  {
    try
    {
/* 248 */       if (featureType.getName().equalsIgnoreCase("uima.cas.String")) {
/* 249 */         setFeatureMethod.invoke(t, new Object[] { (String)featuresHashMap.get(featureName) });
      }
/* 251 */       else if (featureType.getName().equalsIgnoreCase("uima.cas.Integer")) {
/* 252 */         setFeatureMethod.invoke(t, new Object[] { Integer.valueOf(Integer.parseInt((String)featuresHashMap.get(featureName))) });
      }
/* 254 */       else if (featureType.getName().equalsIgnoreCase("uima.cas.Double")) {
/* 255 */         setFeatureMethod.invoke(t, new Object[] { Double.valueOf(Double.parseDouble((String)featuresHashMap.get(featureName))) });
      }
/* 257 */       else if (featureType.getName().equalsIgnoreCase("uima.cas.Short")) {
/* 258 */         setFeatureMethod.invoke(t, new Object[] { Short.valueOf(Short.parseShort((String)featuresHashMap.get(featureName))) });
      }
/* 260 */       else if (featureType.getName().equalsIgnoreCase("uima.cas.Long")) {
/* 261 */         setFeatureMethod.invoke(t, new Object[] { Long.valueOf(Long.parseLong((String)featuresHashMap.get(featureName))) });
      }
/* 263 */       else if (featureType.getName().equalsIgnoreCase("uima.cas.Float")) {
/* 264 */         setFeatureMethod.invoke(t, new Object[] { Float.valueOf(Float.parseFloat((String)featuresHashMap.get(featureName))) });
      }
/* 266 */       else if (featureType.getName().equalsIgnoreCase("uima.cas.Boolean")) {
/* 267 */         setFeatureMethod.invoke(t, new Object[] { Boolean.valueOf(Boolean.parseBoolean((String)featuresHashMap.get(featureName))) });
      }
/* 269 */       else if (featureType.getName().equalsIgnoreCase("uima.cas.Byte")) {
/* 270 */         setFeatureMethod.invoke(t, new Object[] { Byte.valueOf(Byte.parseByte((String)featuresHashMap.get(featureName))) });
      }
/* 272 */       else if (featureType.getName().equalsIgnoreCase("uima.cas.StringArray")) {
/* 273 */         setFeatureMethod.invoke(t, new Object[] { (StringArray)featuresHashMap.get(featureName) });
      }
      else {
/* 276 */         String errmsg = "Error: unhandled inputFeatureType in UIMAUtilities getSetterMethod :" + featureType.getName() + 
/* 277 */           " !";
/* 278 */         throw new AnalysisEngineProcessException(errmsg, 
/* 279 */           new Object[] { featureType.getName() });
      }
    } catch (IllegalArgumentException e) {
/* 282 */       String errmsg = "Error: IllegalArgumentException  !";
/* 283 */       throw new AnalysisEngineProcessException(errmsg, 
/* 284 */         new Object[0], e);
    }
    catch (IllegalAccessException e) {
/* 287 */       String errmsg = "Error: IllegalAccessException  !";
/* 288 */       throw new AnalysisEngineProcessException(errmsg, 
/* 289 */         new Object[0], e);
    }
    catch (InvocationTargetException e) {
/* 292 */       String errmsg = "Error: InvocationTargetException  !";
/* 293 */       throw new AnalysisEngineProcessException(errmsg, 
/* 294 */         new Object[0], e);
    }
    catch (SecurityException e)
    {
/* 298 */       String errmsg = "Error: SecurityException  !";
/* 299 */       throw new AnalysisEngineProcessException(errmsg, 
/* 300 */         new Object[0], e);
    }
  }
  







  public static Object getFeatureValue(Annotation anAnnotation, String aFeatureName)
    throws AnalysisEngineProcessException
  {
/* 314 */     Method anAnnotationFeatureGetterMethod = getFeatureGetterMethod(anAnnotation.getClass(), aFeatureName);
/* 315 */     return invokeFeatureGetterMethod(anAnnotation, anAnnotationFeatureGetterMethod);
  }
  








  public static void checkFeaturesCompatibility(JCas aJCas, Type goldType, Type testType, String goldFeatureName, String testFeatureName)
    throws Exception
  {
/* 329 */     Feature testFeature = testType.getFeatureByBaseName(testFeatureName);
/* 330 */     if (testFeature == null) {
/* 331 */       throw new Exception("The feature name " + testFeatureName + " doesn't belong to the annotation type " + testType.toString() + ".");
    }
    
/* 334 */     Feature goldFeature = goldType.getFeatureByBaseName(goldFeatureName);
/* 335 */     if (goldFeature == null) {
/* 336 */       throw new Exception("The feature name " + goldFeatureName + " doesn't belong to the gold annotation type " + testType.toString() + ".");
    }
    
/* 339 */     String testRange = testFeature.getRange().getName();
/* 340 */     String goldRange = goldFeature.getRange().getName();
/* 341 */     if (!testRange.equals(goldRange)) {
/* 342 */       String message = "The feature  " + goldFeatureName + " ranges over the type " + testRange;
/* 343 */       message = message + "is not compatible with the feature  " + testFeatureName + " which ranges over " + goldRange + ".";
/* 344 */       throw new Exception(message);
    }
  }
  














  public static Map<String, String> parseFeature(String featureString)
  {
/* 364 */     int colonPosition = featureString.indexOf(":");
    
/* 366 */     if (!featureString.equalsIgnoreCase("")) {
/* 367 */       String annotationTypeName = featureString;
/* 368 */       String featureName = "";
/* 369 */       String featureValue = "";
      
/* 371 */       if (colonPosition != -1) {
/* 372 */         annotationTypeName = featureString.substring(0, colonPosition);
/* 373 */         featureName = featureString.substring(colonPosition, featureString.length());
        
/* 375 */         int equalSignPosition = featureName.indexOf("=");
/* 376 */         if (equalSignPosition != -1) {
/* 377 */           featureName = featureName.substring(0, equalSignPosition);
/* 378 */           featureValue = featureName.substring(equalSignPosition, featureName.length());
        }
      }
      

/* 383 */       Map<String, String> featureTuple = new HashMap();
/* 384 */       featureTuple.put("annotationTypeName", annotationTypeName);
      
/* 386 */       featureTuple.put("featureName", featureName);
      
/* 388 */       featureTuple.put("featureValue", featureValue);
      
/* 390 */       return featureTuple;
    }
/* 392 */     return null;
  }
}
