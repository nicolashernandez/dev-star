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
import java.io.PrintStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Iterator;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.cas.CASException;
import org.apache.uima.cas.CASRuntimeException;
import org.apache.uima.cas.FSIterator;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.TypeSystem;
import org.apache.uima.cas.text.AnnotationIndex;
import org.apache.uima.examples.SourceDocumentInformation;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.tcas.Annotation;

































/**
 * Part of the UIMA Utilities dedicated to handle
 * <p>
 * <ul>
 * <li>jcas</li>
 * <li>sofas </li>
 * <li>and views</li>
 * </ul>
 * </p>
 * 
 * @author hernandez
 *
 */
public class JCasUtils
{
/*  56 */   private static String DEFAULT_SOURCE_DOCUMENT_INFORMATION_ANNOTATION = "org.apache.uima.examples.SourceDocumentInformation";
  



/*  61 */   private static String DEFAULT_DOCUMENT_ANNOTATION = "org.apache.uima.jcas.tcas.DocumentAnnotation";
  



/*  66 */   private static String CURRENTCLASSNAME = "SofaViewUtilities";
  





/**
 * Return the view of a jcas corresponding to the given view name
 * @param aJCas
 * @param viewNameString string
 * @return viewJCas
 * @throws AnalysisEngineProcessException
 */
  public static JCas getView(JCas aJCas, String viewNameString)
    throws AnalysisEngineProcessException
  {
/*  77 */     JCas viewJCas = null;
    try {
/*  79 */       viewJCas = aJCas.getView(viewNameString);
    } catch (CASException exception) {
/*  81 */       String errmsg = "ERROR: The view " + viewNameString + 
/*  82 */         " does not exist in the JCAS!";
/*  83 */       throw new AnalysisEngineProcessException(errmsg, 
/*  84 */         new Object[] { viewNameString }, exception);
    }
    

/*  88 */     return viewJCas;
  }
  










	/**
	 * This method create a view.
	 * 
	 * @param aJCas
	 *            the CAS over which the process is performed
	 * @param outputViewString
	 * @param sofaDataString
	 * @param sofaDataStringTypeMimeString

	 * @throws AnalysisEngineProcessException 
	 */
  public static void createView(JCas aJCas, String outputViewString, String sofaDataString, String sofaDataStringTypeMimeString)
    throws AnalysisEngineProcessException
  {
    try
    {
/* 107 */       aJCas.createView(outputViewString);
/* 108 */       JCas outputView = getView(aJCas, outputViewString);
      
/* 110 */       outputView.setSofaDataString(sofaDataString, sofaDataStringTypeMimeString);
    }
    catch (CASException e)
    {
/* 114 */       String errmsg = "Error: Cannot create the view " + outputViewString + " !";
/* 115 */       throw new AnalysisEngineProcessException(errmsg, 
/* 116 */         new Object[0], e);
    }
  }
  









	/**
	 * Get the type of a given annotation name and check if it exists
	 * @param aJCas
	 * @param annotationString
	 * @return annotationType
	 * @throws AnalysisEngineProcessException
	 */
  public static Type getJCasType(JCas aJCas, String annotationString)
    throws AnalysisEngineProcessException
  {
/* 133 */     Type annotationType = null;
/* 134 */     annotationType = aJCas.getTypeSystem().getType(
/* 135 */       annotationString);
    
/* 137 */     if (annotationType == null) {
/* 138 */       String errmsg = "Error: Type " + annotationString + 
/* 139 */         " is not defined in the Type System !";
/* 140 */       throw new AnalysisEngineProcessException(errmsg, 
/* 141 */         new Object[] { annotationType });
    }
/* 143 */     return annotationType;
  }
  




	/**
	 * Return the sofaDataString of a JCAS corresponding to the given view 
	 * @param aJCas
	 * @return inputSofaDataString
	 * @throws AnalysisEngineProcessException
	 */
  public static String getSofaDataString(JCas aJCas)
    throws AnalysisEngineProcessException
  {
/* 154 */     String inputSofaDataString = null;
/* 155 */     inputSofaDataString = aJCas.getSofaDataString();
    
/* 157 */     if (inputSofaDataString == null) {
/* 158 */       String errmsg = "ERROR: The given view " + aJCas.toString() + 
/* 159 */         " does not contain a sofaDataString!";
/* 160 */       throw new AnalysisEngineProcessException(errmsg, 
/* 161 */         new Object[0]);
    }
/* 163 */     return inputSofaDataString;
  }
  




	/**
	 * Get the name part of the uri stored in the source documentation of the annotation index of the current view
	 * @param aJCas
	 * @return
	 * @throws CASRuntimeException
	 * @throws AnalysisEngineProcessException
	 */
  public static String getArtifactViewName(JCas aJCas)
    throws CASRuntimeException, AnalysisEngineProcessException
  {
/* 174 */     FSIterator<Annotation> sourceDocumentInformationFSIterator = aJCas.getAnnotationIndex(getJCasType(aJCas, 
/* 175 */       DEFAULT_SOURCE_DOCUMENT_INFORMATION_ANNOTATION)).iterator();
/* 176 */     String outFileName = "";
/* 177 */     File inFile = null;
/* 178 */     if (sourceDocumentInformationFSIterator.hasNext()) {
/* 179 */       SourceDocumentInformation theSourceDocumentInformation = (SourceDocumentInformation)sourceDocumentInformationFSIterator.next();
      try
      {
/* 182 */         inFile = new File(new URL(theSourceDocumentInformation.getUri()).getPath());
/* 183 */         outFileName = inFile.getName();
/* 184 */         if (theSourceDocumentInformation.getOffsetInSource() > 0) {
/* 185 */           outFileName = outFileName + "-" + theSourceDocumentInformation.getOffsetInSource();
        }
        

      }
      catch (MalformedURLException e)
      {
/* 192 */         e.printStackTrace();
      }
    }
/* 195 */     return outFileName;
  }
  




  /**
	 * This designation may be clearer than getArtifactName
	 * @param aJCas
	 * @return
	 * @throws AnalysisEngineProcessException 
	 * @throws CASRuntimeException 
	 */
  public String getDocumentName(JCas aJCas)
    throws CASRuntimeException, AnalysisEngineProcessException
  {
/* 206 */     return getTheArtifactName(aJCas);
  }
  




	/**
	 * Get the name of the artifact whatever it appears in any annotation index of associated view
	 * @param aJCas
	 * @return
	 * @throws AnalysisEngineProcessException 
	 * @throws CASRuntimeException 
	 */
  public static String getTheArtifactName(JCas aJCas)
    throws CASRuntimeException, AnalysisEngineProcessException
  {
/* 217 */     String artifactName = "";
    try {
/* 219 */       Iterator<JCas> jCasViewIter = aJCas.getViewIterator();
      
/* 221 */       while (jCasViewIter.hasNext()) {
/* 222 */         JCas aJCasView = (JCas)jCasViewIter.next();
/* 223 */         String currentArtifactViewName = getArtifactViewName(aJCasView);
        

/* 226 */         if ((currentArtifactViewName != null) && 
/* 227 */           (!currentArtifactViewName.equalsIgnoreCase(""))) artifactName = currentArtifactViewName;
      }
    }
    catch (CASException e1)
    {
/* 232 */       e1.printStackTrace();
    }
    
/* 235 */     return artifactName;
  }
  






	/**
	 * Get the name of the artifact whatever it appears in any annotation index of associated view
	 * or create one specifying default prefix and suffix elements
	 * @param aJCas
	 * @return
	 * @throws AnalysisEngineProcessException 
	 * @throws CASRuntimeException 
	 */
  public static String getAnArtifactName(JCas aJCas)
    throws CASRuntimeException, AnalysisEngineProcessException
  {
/* 248 */     return getAnArtifactName(aJCas, Boolean.valueOf(true), "", "");
  }
  






	/**
	 * Get the name of the artifact whatever it appears in any annotation index of associated view
	 * or create one specifying prefix and suffix elements
	 * @param aJCas
	 * @return
	 * @throws AnalysisEngineProcessException 
	 * @throws CASRuntimeException 
	 */
  public static String getAnArtifactName(JCas aJCas, Boolean removeExtension, String prefix, String suffix)
    throws CASRuntimeException, AnalysisEngineProcessException
  {
/* 261 */     String artifactName = getTheArtifactName(aJCas);
    
/* 263 */     if (artifactName != null)
    {
/* 265 */       if (!artifactName.equalsIgnoreCase("")) {
/* 266 */         int lastIndex = artifactName.lastIndexOf(".");
/* 267 */         if (lastIndex != -1)
/* 268 */           artifactName = artifactName.substring(0, lastIndex);
      } else {
/* 270 */         System.err.println("Error: an artifact name not null but empty ; in that case should create A name");
      }
      

    }
    else
    {
/* 277 */       byte[] hash = null;
      try {
/* 279 */         hash = MessageDigest.getInstance("MD5").digest(aJCas.getSofaDataString().getBytes());
      }
      catch (NoSuchAlgorithmException e) {
/* 282 */         e.printStackTrace();
      }
      




/* 289 */       artifactName = "" + System.nanoTime() + '-' + hash;
    }
    



/* 295 */     artifactName = prefix + artifactName + suffix;
    
/* 297 */     return artifactName;
  }



       /*
        * Based on the possible prence of DKPro or Apache UIMA meta data
        * 
        * return empty string if no value is available

        * 18 juillet 2017 the following code is uncertain ; it results from an attempt to retreive an accidentaly deleted old uncommited code...
         * the method may be incomplete...
        */
       public static String getMetaDataDocumentName (JCas aJCas) {
        System.out.println("WARNING: JCasUtils getMetaDataDocumentName code has been accidentaly deleted ");
       
       String DEFAULT_DKPRO_DOCUMENTMETADATA = "de.tudarmstadt.ukp.dkpro.core.api.metadata.type.DocumentMetaData";
       String DEFAULT_SOURCE_DOCUMENT_INFORMATION_ANNOTATION = "org.apache.uima.examples.SourceDocumentInformation";
       String documentFileName =  "";
       // DKPro representation
       de.tudarmstadt.ukp.dkpro.core.api.metadata.type.DocumentMetaData documentMetatData = null;
       // Apache UIMA representation
       org.apache.uima.examples.SourceDocumentInformation sourceDocumentInformation = null;

       if (aJCas.getAnnotationIndex(aJCas.getTypeSystem().getType(DEFAULT_DKPRO_DOCUMENTMETADATA)).iterator().isValid()) {     

               documentMetatData = 
                               (de.tudarmstadt.ukp.dkpro.core.api.metadata.type.DocumentMetaData) aJCas.getAnnotationIndex(aJCas.getTypeSystem().getType(DEFAULT_DKPRO_DOCUMENTMETADATA)).iterator().get();
               //System.out.println("Debug: dkpro documentTitle: "+ documentMetatData.getDocumentTitle());
               documentFileName = documentMetatData.getDocumentTitle();
       }
       else {
               if (aJCas.getAnnotationIndex(aJCas.getTypeSystem().getType(DEFAULT_SOURCE_DOCUMENT_INFORMATION_ANNOTATION)).iterator().isValid()) {
                       sourceDocumentInformation = (SourceDocumentInformation) aJCas.getAnnotationIndex(aJCas.getTypeSystem().getType(DEFAULT_SOURCE_DOCUMENT_INFORMATION_ANNOTATION)).iterator().get();
                       if (sourceDocumentInformation.getUri().lastIndexOf(File.separator) == -1)  documentFileName = sourceDocumentInformation.getUri();
                       else documentFileName = sourceDocumentInformation.getUri().substring(sourceDocumentInformation.getUri().lastIndexOf(File.separator));

       if (aJCas.getAnnotationIndex(aJCas.getTypeSystem().getType(DEFAULT_DKPRO_DOCUMENTMETADATA)).iterator().isValid()) {     

               documentMetatData = 
                               (de.tudarmstadt.ukp.dkpro.core.api.metadata.type.DocumentMetaData) aJCas.getAnnotationIndex(aJCas.getTypeSystem().g
etType(DEFAULT_DKPRO_DOCUMENTMETADATA)).iterator().get();
               //System.out.println("Debug: dkpro documentTitle: "+ documentMetatData.getDocumentTitle());
               documentFileName = documentMetatData.getDocumentTitle();
       }
       else {
               if (aJCas.getAnnotationIndex(aJCas.getTypeSystem().getType(DEFAULT_SOURCE_DOCUMENT_INFORMATION_ANNOTATION)).iterator().isValid()) {
                       sourceDocumentInformation = (SourceDocumentInformation) aJCas.getAnnotationIndex(aJCas.getTypeSystem().getType(DEFAULT_SOUR
CE_DOCUMENT_INFORMATION_ANNOTATION)).iterator().get();
                       if (sourceDocumentInformation.getUri().lastIndexOf(File.separator) == -1)  documentFileName = sourceDocumentInformation.get
Uri();
                       else documentFileName = sourceDocumentInformation.getUri().substring(sourceDocumentInformation.getUri().lastIndexOf(File.se
parator));
                       //System.out.println("Debug: Apache UIMA documentTitle: "+ documentFileName);
               }
       } }
    } 
   }


}
