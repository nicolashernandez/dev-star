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
package fr.univnantes.lina.uima.common.ae;

import fr.univnantes.lina.uima.common.cas.AnnotationCollectionUtils;
import fr.univnantes.lina.uima.common.cas.AnnotationUtils;
import fr.univnantes.lina.uima.common.cas.located.LocatedAnnotation;
import fr.univnantes.lina.uima.common.cas.located.LocatedAnnotationIndexBuilder;
import fr.univnantes.lina.uima.common.cas.located.LocatedAnnotationIndexInterface;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.uima.UimaContext;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.cas.FSIterator;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.TypeSystem;
import org.apache.uima.cas.text.AnnotationIndex;
import org.apache.uima.fit.descriptor.ConfigurationParameter;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.tcas.Annotation;
import org.apache.uima.resource.ResourceInitializationException;







































































public class AnnotationMergerAE
  extends CommonAE
{
  public static final String PARAM_SUBSUMING_ANNOTATION = "SubsumingAnnotation";
  @ConfigurationParameter(name="SubsumingAnnotation", mandatory=true)
  private String subsumingAnnotationName;
  public static final String PARAM_SUBSUMED_ANNOTATION = "SubsumedAnnotation";
  @ConfigurationParameter(name="SubsumedAnnotation", mandatory=true)
  private String subsumedAnnotationName;
  public static final String PARAM_STRICT_OFFSET = "StrictOffset";
  @ConfigurationParameter(name="StrictOffset", mandatory=false, defaultValue={"false"})
  private boolean strictOffset;
  
  public void initialize(UimaContext aContext)
    throws ResourceInitializationException
  {
/* 110 */     super.initialize(aContext);
    

/* 113 */     if (this.subsumedAnnotationName == null) { this.subsumedAnnotationName = this.subsumingAnnotationName;
    }
  }
  








  protected String processContextAnnotation(JCas inputViewJCas, FSIterator<Annotation> contextAnnotationsFSIter, Annotation contextAnnotation, FSIterator<Annotation> contextualizedInputAnnotationsFSIter, String inputFeatureString, JCas outputViewJCas, String outputAnnotationString, String ouputFeatureString)
    throws AnalysisEngineProcessException
  {
/* 128 */     LocatedAnnotationIndexInterface locatedAnnotationIndex = LocatedAnnotationIndexBuilder.buildsIndex(inputViewJCas);
    
/* 130 */     Map<String, LocatedAnnotation> locatedAnnotationSortedMap = locatedAnnotationIndex.getLocatedAnnotationIndex();
    
/* 132 */     Set<String> sortedOffsetSet = locatedAnnotationSortedMap.keySet();
    

/* 135 */     for (String offset : sortedOffsetSet)
    {
/* 137 */       LocatedAnnotation locatedAnnotation = (LocatedAnnotation)locatedAnnotationSortedMap.get(offset);
      


/* 141 */       List<Annotation> currentAnnotations = locatedAnnotation.getCurrent();
/* 142 */       Boolean isCurrentSubsumingAnnotation = Boolean.valueOf(false);
/* 143 */       Boolean isCurrentSubsumedAnnotation = Boolean.valueOf(false);
      
/* 145 */       for (Annotation currentAnnotation : currentAnnotations) {
/* 146 */         if (currentAnnotation.getType().getName().equalsIgnoreCase(this.subsumingAnnotationName)) {
/* 147 */           isCurrentSubsumingAnnotation = Boolean.valueOf(true);
        }
/* 149 */         else if (currentAnnotation.getType().getName().equalsIgnoreCase(this.subsumedAnnotationName)) {
/* 150 */           isCurrentSubsumedAnnotation = Boolean.valueOf(true);
        }
      }
      
/* 154 */       if (isCurrentSubsumingAnnotation.booleanValue())
      {
/* 156 */         AnnotationUtils.createAnnotation(inputViewJCas, outputAnnotationString, locatedAnnotation.getBegin(), locatedAnnotation.getEnd());



      }
/* 161 */       else if (isCurrentSubsumedAnnotation.booleanValue())
      {

/* 164 */         List<LocatedAnnotation> superAnnotations = locatedAnnotation.getContiguousParent();
/* 165 */         Boolean isSuperSubsumingAnnotation = Boolean.valueOf(false);
        
/* 167 */         for (LocatedAnnotation superAnnotation : superAnnotations) {
/* 168 */           if (superAnnotation.containsCurrent(this.subsumingAnnotationName)) {
/* 169 */             isSuperSubsumingAnnotation = Boolean.valueOf(true);
          }
        }
/* 172 */         if (!isSuperSubsumingAnnotation.booleanValue())
        {
/* 174 */           List<LocatedAnnotation> partialPreviousAnnotations = locatedAnnotation.getContiguousPartialPreceding();
/* 175 */           Boolean isPartialPreviousSubsumingAnnotation = Boolean.valueOf(false);
          
/* 177 */           for (LocatedAnnotation partialPreviousAnnotation : partialPreviousAnnotations) {
/* 178 */             if (partialPreviousAnnotation.containsCurrent(this.subsumingAnnotationName)) {
/* 179 */               isPartialPreviousSubsumingAnnotation = Boolean.valueOf(true);
            }
          }
/* 182 */           if (!isPartialPreviousSubsumingAnnotation.booleanValue())
          {
/* 184 */             List<LocatedAnnotation> partialNextAnnotations = locatedAnnotation.getContiguousPartialPreceding();
/* 185 */             Boolean isPartialNextSubsumingAnnotation = Boolean.valueOf(false);
            
/* 187 */             for (LocatedAnnotation partialNextAnnotation : partialNextAnnotations) {
/* 188 */               if (partialNextAnnotation.containsCurrent(this.subsumingAnnotationName)) {
/* 189 */                 isPartialNextSubsumingAnnotation = Boolean.valueOf(true);
              }
            }
/* 192 */             if (!isPartialNextSubsumingAnnotation.booleanValue())
            {
/* 194 */               AnnotationUtils.createAnnotation(inputViewJCas, outputAnnotationString, locatedAnnotation.getBegin(), locatedAnnotation.getEnd());
            }
          }
        }
      }
    }
    











/* 212 */     return contextAnnotation.getCoveredText();
  }
  




  private void doAnnotationMergerBasedOnDistinctSubsumingAndSubsumedAnnotations(JCas inputViewJCas)
    throws AnalysisEngineProcessException
  {
/* 222 */     Map<Annotation, Integer> annotationAlreadySeenMap = new HashMap();
    
/* 224 */     Type subsumingAnnotationType = inputViewJCas.getTypeSystem().getType(this.subsumingAnnotationName);
/* 225 */     AnnotationIndex<Annotation> subsumingAnnotationIndex = inputViewJCas.getAnnotationIndex(subsumingAnnotationType);
    


/* 229 */     FSIterator<Annotation> subsumingAnnotationIterator = subsumingAnnotationIndex.iterator();
    

/* 232 */     while (subsumingAnnotationIterator.hasNext()) {
/* 233 */       Annotation aSubsumingAnnotation = (Annotation)subsumingAnnotationIterator.next();
      

/* 236 */       if (!annotationAlreadySeenMap.containsKey(aSubsumingAnnotation)) {
/* 237 */         int minSubsumedAnnotationBegin = aSubsumingAnnotation.getEnd();
/* 238 */         int maxSubsumedAnnotationEnd = aSubsumingAnnotation.getBegin();
        
/* 240 */         Map<Annotation, Integer> subsumedAnnotationPotentiallyAlreadySeenMap = new HashMap();
        
/* 242 */         FSIterator<Annotation> subsumedAnnotationIterator = AnnotationCollectionUtils.subiterator(inputViewJCas, aSubsumingAnnotation);
        


/* 246 */         while (subsumedAnnotationIterator.hasNext()) {
/* 247 */           Annotation aSubsumedAnnotation = (Annotation)subsumedAnnotationIterator.next();
          




/* 253 */           if (aSubsumedAnnotation.getBegin() < minSubsumedAnnotationBegin) minSubsumedAnnotationBegin = aSubsumedAnnotation.getBegin();
/* 254 */           if (aSubsumedAnnotation.getEnd() > maxSubsumedAnnotationEnd) { maxSubsumedAnnotationEnd = aSubsumedAnnotation.getEnd();
          }
/* 256 */           subsumedAnnotationPotentiallyAlreadySeenMap.put(aSubsumedAnnotation, Integer.valueOf(1));
        }
        



/* 262 */         if ((aSubsumingAnnotation.getBegin() == minSubsumedAnnotationBegin) && (aSubsumingAnnotation.getEnd() == maxSubsumedAnnotationEnd))
        {



/* 267 */           annotationAlreadySeenMap.putAll(subsumedAnnotationPotentiallyAlreadySeenMap);
          

/* 270 */           AnnotationUtils.createAnnotation(inputViewJCas, getOutputAnnotation(), aSubsumingAnnotation.getBegin(), aSubsumingAnnotation.getEnd());
        }
      }
    }
    








/* 283 */     Type subsumedAnnotationType = inputViewJCas.getTypeSystem().getType(this.subsumedAnnotationName);
/* 284 */     System.err.println("Debug: subsumedAnnotationName " + this.subsumedAnnotationName + " subsumingAnnotationName " + this.subsumingAnnotationName);
/* 285 */     AnnotationIndex<Annotation> subsumedAnnotationIndex = inputViewJCas.getAnnotationIndex(subsumedAnnotationType);
    



/* 290 */     FSIterator<Annotation> subsumedAnnotationIterator = subsumedAnnotationIndex.iterator();
/* 291 */     while (subsumedAnnotationIterator.hasNext()) {
/* 292 */       Annotation aSubsumedAnnotation = (Annotation)subsumedAnnotationIterator.next();
/* 293 */       if (!annotationAlreadySeenMap.containsKey(aSubsumedAnnotation)) {
/* 294 */         AnnotationUtils.createAnnotation(inputViewJCas, getOutputAnnotation(), aSubsumedAnnotation.getBegin(), aSubsumedAnnotation.getEnd());
      }
    }
  }
}
