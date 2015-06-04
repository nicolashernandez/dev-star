/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package fr.univnantes.lina.uima.common.ae;

import fr.univnantes.lina.uima.common.cas.AnnotationCollectionUtils;
import org.apache.uima.UimaContext;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.cas.FSIterator;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.tcas.Annotation;
import org.apache.uima.resource.ResourceInitializationException;


public class DuplicateAnnotationRemoverAE
  extends CommonAE
{
  public void initialize(UimaContext aContext)
    throws ResourceInitializationException
  {
/* 69 */     super.initialize(aContext);
  }
  

  protected String processContextAnnotation(JCas inputViewJCas, FSIterator contextAnnotationsFSIter, Annotation contextAnnotation, FSIterator contextualizedInputAnnotationsFSIter, String inputFeatureString, JCas outputViewJCas, String outputAnnotationString, String ouputFeatureString)
    throws AnalysisEngineProcessException
  {
AnnotationCollectionUtils.removeDuplicateAnnotations(inputViewJCas);
    

return contextAnnotation.getCoveredText();
  }
}

