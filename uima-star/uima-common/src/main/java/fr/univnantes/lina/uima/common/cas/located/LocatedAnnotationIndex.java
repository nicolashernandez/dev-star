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
package fr.univnantes.lina.uima.common.cas.located;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import org.apache.uima.jcas.tcas.Annotation;





























































public class LocatedAnnotationIndex
  implements LocatedAnnotationIndexInterface
{
  private Map<String, LocatedAnnotation> locatedAnnotationIndexMap;
  
  public LocatedAnnotationIndex()
  {
/*  79 */     setLocatedAnnotationIndex(new TreeMap(new OffsetComparator()));
  }
  



  public LocatedAnnotationIndex(Map<String, LocatedAnnotation> locatedAnnotationIndexMap)
  {
/*  87 */     setLocatedAnnotationIndex(locatedAnnotationIndexMap);
  }
  







  public Map<String, LocatedAnnotation> getLocatedAnnotationIndex()
  {
/*  99 */     return this.locatedAnnotationIndexMap;
  }
  




  public void setLocatedAnnotationIndex(Map<String, LocatedAnnotation> locatedAnnotationMap)
  {
/* 108 */     this.locatedAnnotationIndexMap = locatedAnnotationMap;
  }
  










  public LocatedAnnotation getLocatedAnnotation(Annotation annotation)
  {
/* 123 */     return (LocatedAnnotation)getLocatedAnnotationIndex().get(Offset.getOffset(annotation));
  }
  





  public LocatedAnnotation getLocatedAnnotation(String annotationOffset)
  {
/* 133 */     return (LocatedAnnotation)getLocatedAnnotationIndex().get(annotationOffset);
  }
  





  public boolean existsLocatedAnnotation(String annotationOffset)
  {
/* 143 */     return getLocatedAnnotationIndex().containsKey(annotationOffset);
  }
  






  public Set<String> getOffsetSet()
  {
/* 154 */     return this.locatedAnnotationIndexMap.keySet();
  }
  



  public List<String> getOffsetList()
  {
/* 162 */     List<String> offsetList = new ArrayList();
/* 163 */     offsetList.addAll(getOffsetSet());
/* 164 */     return offsetList;
  }
  



  public LocatedAnnotation getFirst(String annotationTypeName)
  {
/* 172 */     Set<String> offsetSet = this.locatedAnnotationIndexMap.keySet();
    

/* 175 */     for (String offset : offsetSet)
    {


/* 179 */       LocatedAnnotation locatedAnnotation = (LocatedAnnotation)this.locatedAnnotationIndexMap.get(offset);
/* 180 */       if (locatedAnnotation.contains(locatedAnnotation.getCurrent(), annotationTypeName)) { return locatedAnnotation;
      }
    }
/* 183 */     return null;
  }
  



  public LocatedAnnotation getLast(String annotationTypeName)
  {
/* 191 */     Set<String> offsetSet = this.locatedAnnotationIndexMap.keySet();
/* 192 */     List<String> reverseOffsetList = new ArrayList();
/* 193 */     reverseOffsetList.addAll(offsetSet);
/* 194 */     Collections.reverse(reverseOffsetList);
    
/* 196 */     for (String offset : reverseOffsetList)
    {


/* 200 */       LocatedAnnotation locatedAnnotation = (LocatedAnnotation)this.locatedAnnotationIndexMap.get(offset);
/* 201 */       if (locatedAnnotation.contains(locatedAnnotation.getCurrent(), annotationTypeName)) { return locatedAnnotation;
      }
    }
/* 204 */     return null;
  }
  




  public LocatedAnnotation getFirst()
  {
/* 213 */     Set<String> offsetSet = this.locatedAnnotationIndexMap.keySet();
/* 214 */     if (!offsetSet.isEmpty()) {
/* 215 */       Iterator<String> offsetSetIter = offsetSet.iterator();
/* 216 */       return (LocatedAnnotation)this.locatedAnnotationIndexMap.get(offsetSetIter.next());
    }
    
/* 219 */     return null;
  }
  



  public LocatedAnnotation getLast()
  {
/* 227 */     Set<String> offsetSet = this.locatedAnnotationIndexMap.keySet();
    

/* 230 */     if (!offsetSet.isEmpty()) {
/* 231 */       List<String> offsetList = new ArrayList();
/* 232 */       offsetList.addAll(offsetSet);
/* 233 */       return (LocatedAnnotation)this.locatedAnnotationIndexMap.get(offsetList.get(offsetList.size() - 1));
    }
/* 235 */     return null;
  }
}
