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

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.cas.FSIterator;
import org.apache.uima.cas.text.AnnotationIndex;
import org.apache.uima.examples.SourceDocumentInformation;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.tcas.Annotation;































public class DocumentAnnotationUtils
{
/* 45 */   static String DEFAULT_SOURCE_DOCUMENT_INFORMATION_ANNOTATION = "org.apache.uima.examples.SourceDocumentInformation";
  






  public static File retrieveSourceDocumentFile(JCas aJCas)
    throws AnalysisEngineProcessException
  {
/* 56 */     FSIterator<Annotation> sourceDocumentInformationFSIterator = aJCas.getAnnotationIndex(JCasUtils.getJCasType(aJCas, 
/* 57 */       DEFAULT_SOURCE_DOCUMENT_INFORMATION_ANNOTATION)).iterator();
/* 58 */     File inFile = null;
/* 59 */     if (sourceDocumentInformationFSIterator.hasNext()) {
/* 60 */       SourceDocumentInformation theSourceDocumentInformation = (SourceDocumentInformation)sourceDocumentInformationFSIterator.next();
      try
      {
/* 63 */         inFile = new File(new URL(theSourceDocumentInformation.getUri()).getPath());

      }
      catch (MalformedURLException e)
      {
/* 68 */         String errmsg = "Error: MalformedURLException !";
/* 69 */         throw new AnalysisEngineProcessException(errmsg, 
/* 70 */           new Object[0], e);
      }
    }
    

/* 75 */     return inFile;
  }
  






  public static String retrieveSourceDocumentFileName(JCas aJCas)
    throws AnalysisEngineProcessException
  {
/* 87 */     File inFile = retrieveSourceDocumentFile(aJCas);
/* 88 */     return inFile.getName();
  }
}

