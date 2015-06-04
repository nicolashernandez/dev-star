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
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import org.apache.uima.cas.text.AnnotationIndex;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.tcas.Annotation;


















































public class LocatedAnnotationIndexBuilder
{
  public static LocatedAnnotationIndex buildsIndex(JCas aJCas)
  {
/*  67 */     LocatedAnnotationIndex locatedAnnotationIndex = new LocatedAnnotationIndex();
/*  68 */     Map<String, LocatedAnnotation> locatedAnnotationIndexMap = locatedAnnotationIndex.getLocatedAnnotationIndex();
    





/*  75 */     Map<Integer, List<Annotation>> annotationBeginMap = new TreeMap();
    




/*  81 */     Map<Integer, List<Annotation>> annotationEndMap = new TreeMap();
    






/*  89 */     AnnotationIndex<Annotation> annotationIndex = null;
/*  90 */     Iterator<Annotation> annotationIndexIterator = null;
/*  91 */     annotationIndex = aJCas.getAnnotationIndex();
/*  92 */     annotationIndexIterator = annotationIndex
/*  93 */       .iterator();
    

/*  96 */     while (annotationIndexIterator.hasNext())
    {

/*  99 */       Annotation annotation = null;
/* 100 */       annotation = 
/* 101 */         (Annotation)annotationIndexIterator.next();
/* 102 */       String annotationOffset = Offset.getOffset(annotation);
/* 103 */       int annotationBegin = annotation.getBegin();
/* 104 */       int annotationEnd = annotation.getEnd();
      



/* 109 */       List<Annotation> currentAnnotationList = null;
/* 110 */       LocatedAnnotation locatedAnnotation = null;
/* 111 */       if (locatedAnnotationIndexMap.containsKey(annotationOffset))
      {

/* 114 */         locatedAnnotation = (LocatedAnnotation)locatedAnnotationIndexMap.get(annotationOffset);
/* 115 */         currentAnnotationList = locatedAnnotation.getCurrent();
      }
      else
      {
/* 119 */         locatedAnnotation = new LocatedAnnotation();
/* 120 */         locatedAnnotation.setBegin(annotationBegin);
/* 121 */         locatedAnnotation.setEnd(annotationEnd);
      }
      
/* 124 */       locatedAnnotation.addCurrent(annotation);
      








/* 134 */       locatedAnnotationIndexMap.put(annotationOffset, locatedAnnotation);
      


/* 138 */       List<Annotation> beginAnnotationList = null;
/* 139 */       if (annotationBeginMap.containsKey(Integer.valueOf(annotationBegin))) {
/* 140 */         beginAnnotationList = (List)annotationBeginMap.get(Integer.valueOf(annotationBegin));
      }
      else {
/* 143 */         beginAnnotationList = new ArrayList();
      }
/* 145 */       beginAnnotationList.add(annotation);
      
/* 147 */       annotationBeginMap.put(Integer.valueOf(annotationBegin), beginAnnotationList);
      


/* 151 */       List<Annotation> endAnnotationList = null;
/* 152 */       if (annotationEndMap.containsKey(Integer.valueOf(annotationEnd))) {
/* 153 */         endAnnotationList = (List)annotationEndMap.get(Integer.valueOf(annotationEnd));
      }
      else {
/* 156 */         endAnnotationList = new ArrayList();
      }
/* 158 */       endAnnotationList.add(annotation);
      
/* 160 */       annotationEndMap.put(Integer.valueOf(annotationEnd), endAnnotationList);
    }
    















/* 178 */     TreeMap<Integer, List<Annotation>> annotationBeginSortedMap = new TreeMap(annotationBeginMap);
/* 179 */     TreeMap<Integer, List<Annotation>> annotationEndSortedMap = new TreeMap(annotationEndMap);
/* 180 */     Set<Integer> beginSortedSet = annotationBeginSortedMap.keySet();
/* 181 */     Set<Integer> endSortedSet = annotationEndSortedMap.keySet();
    

/* 184 */     Set<String> offsetSet = locatedAnnotationIndexMap.keySet();
/* 185 */     int annotationMinBegin = 0;
/* 186 */     int annotationMaxEnd = 0;
    


/* 190 */     List<String> offsetList = new ArrayList();
/* 191 */     offsetList.addAll(offsetSet);
/* 192 */     Set<LocatedAnnotation> localLocatedAnnotationSet; for (int i = 0; i < offsetList.size(); i++) {
/* 193 */       String annotationOffset = (String)offsetList.get(i);
      

/* 196 */       LocatedAnnotation locatedAnnotation = (LocatedAnnotation)locatedAnnotationIndexMap.get(annotationOffset);
/* 197 */       begin = locatedAnnotation.getBegin();
/* 198 */       int end = locatedAnnotation.getEnd();
      

/* 201 */       if (i - 1 > 0) locatedAnnotation.setFirstIndexPreceding((LocatedAnnotation)locatedAnnotationIndexMap.get(offsetList.get(i - 1))); else
/* 202 */         locatedAnnotation.setFirstIndexPreceding(null);
/* 203 */       if (i + 1 < offsetList.size()) {
/* 204 */         locatedAnnotationIndex.getLocatedAnnotation(annotationOffset).setFirstIndexFollowing((LocatedAnnotation)locatedAnnotationIndexMap.get(offsetList.get(i + 1)));
      } else {
/* 206 */         locatedAnnotation.setFirstIndexFollowing(null);
      }
      


/* 211 */       annotationMaxEnd = Math.max(annotationMaxEnd, end);
      





/* 218 */       if ((annotationBeginSortedMap.containsKey(Integer.valueOf(end))) || (annotationBeginSortedMap.higherKey(Integer.valueOf(end)) != null)) {
/* 219 */         int nextBegin = -1;
/* 220 */         if (annotationBeginSortedMap.containsKey(Integer.valueOf(end))) { nextBegin = end;
        } else {
/* 222 */           nextBegin = ((Integer)annotationBeginSortedMap.higherKey(Integer.valueOf(end))).intValue();
        }
        


/* 227 */         Set<LocatedAnnotation> localLocatedAnnotationSet = new HashSet();
/* 228 */         List<Annotation> localAnnotationList = (List)annotationBeginSortedMap.get(Integer.valueOf(nextBegin));
/* 229 */         Iterator<Annotation> localAnnotationListIterator = localAnnotationList.iterator();
/* 230 */         while (localAnnotationListIterator.hasNext()) {
/* 231 */           Annotation localAnnotation = (Annotation)localAnnotationListIterator.next();
/* 232 */           localLocatedAnnotationSet.add(locatedAnnotationIndex.getLocatedAnnotation(localAnnotation));
        }
/* 234 */         List<LocatedAnnotation> aList = new ArrayList();
/* 235 */         aList.addAll(localLocatedAnnotationSet);
/* 236 */         locatedAnnotation.addContiguousFollowing(aList);
      }
      






/* 245 */       if ((annotationEndSortedMap.containsKey(Integer.valueOf(begin))) || (annotationEndSortedMap.lowerKey(Integer.valueOf(begin)) != null)) {
/* 246 */         int previousEnd = -1;
/* 247 */         if (annotationEndSortedMap.containsKey(Integer.valueOf(begin))) { previousEnd = begin;
        } else {
/* 249 */           previousEnd = ((Integer)annotationEndSortedMap.lowerKey(Integer.valueOf(begin))).intValue();
        }
        

/* 253 */         localLocatedAnnotationSet = new HashSet();
/* 254 */         List<Annotation> localAnnotationList = (List)annotationEndSortedMap.get(Integer.valueOf(previousEnd));
/* 255 */         Iterator<Annotation> localAnnotationListIterator = localAnnotationList.iterator();
/* 256 */         while (localAnnotationListIterator.hasNext()) {
/* 257 */           Annotation localAnnotation = (Annotation)localAnnotationListIterator.next();
/* 258 */           localLocatedAnnotationSet.add(locatedAnnotationIndex.getLocatedAnnotation(localAnnotation));
        }
/* 260 */         List<LocatedAnnotation> aList = new ArrayList();
/* 261 */         aList.addAll(localLocatedAnnotationSet);
/* 262 */         locatedAnnotation.addContiguousPreceding(aList);
      }
    }
    
















/* 282 */     LocatedAnnotationInterface topLocatedAnnotation = new LocatedAnnotation();
/* 283 */     topLocatedAnnotation.setBegin(annotationMinBegin);
/* 284 */     topLocatedAnnotation.setEnd(annotationMaxEnd);
    




/* 290 */     Map<String, Integer> offsetAlreadySeen = new TreeMap(new OffsetComparator());
/* 291 */     for (int begin = beginSortedSet.iterator(); begin.hasNext(); 
        


/* 295 */         localLocatedAnnotationSet.hasNext())
    {
/* 291 */       int begin = ((Integer)begin.next()).intValue();
/* 292 */       List<Annotation> offsetAnnotationList = (List)annotationBeginMap.get(Integer.valueOf(begin));
      

/* 295 */       localLocatedAnnotationSet = offsetAnnotationList.iterator(); continue;Annotation annotation = (Annotation)localLocatedAnnotationSet.next();
      

/* 298 */       if (!offsetAlreadySeen.containsKey(Offset.getOffset(annotation)))
      {
/* 300 */         offsetAlreadySeen.put(Offset.getOffset(annotation), Integer.valueOf(1));
        

/* 303 */         searchParent(locatedAnnotationIndex, Offset.getOffset(topLocatedAnnotation), Offset.getOffset(annotation), false);
      }
    }
    



















/* 326 */     return locatedAnnotationIndex;
  }
  














  private static boolean searchParent(LocatedAnnotationIndex locatedAnnotationIndex, String treeFocusOffset, String currentOffset, boolean found)
  {
/* 345 */     if (subsumeLoosely(locatedAnnotationIndex.getLocatedAnnotation(currentOffset), locatedAnnotationIndex.getLocatedAnnotation(treeFocusOffset))) {
/* 346 */       List<LocatedAnnotation> childAnnotationList = locatedAnnotationIndex.getLocatedAnnotation(treeFocusOffset).getContiguousChild();
/* 347 */       Iterator<LocatedAnnotation> childAnnotationListIterator = childAnnotationList.iterator();
/* 348 */       while ((childAnnotationListIterator.hasNext()) && (!found)) {
/* 349 */         LocatedAnnotation annotationChild = (LocatedAnnotation)childAnnotationListIterator.next();
/* 350 */         found = searchParent(locatedAnnotationIndex, Offset.getOffset(annotationChild), currentOffset, found);
      }
/* 352 */       if (!found)
      {




/* 358 */         List<LocatedAnnotation> locatedAnnotationList = new ArrayList();
/* 359 */         locatedAnnotationList.add(locatedAnnotationIndex.getLocatedAnnotation(currentOffset));
        
/* 361 */         locatedAnnotationIndex.getLocatedAnnotation(treeFocusOffset).addContiguousChild(locatedAnnotationList);
        
/* 363 */         locatedAnnotationList = new ArrayList();
/* 364 */         locatedAnnotationList.add(locatedAnnotationIndex.getLocatedAnnotation(treeFocusOffset));
        
/* 366 */         locatedAnnotationIndex.getLocatedAnnotation(currentOffset).setContiguousParent(locatedAnnotationList);
        

/* 369 */         locatedAnnotationIndex.getLocatedAnnotationIndex().put(treeFocusOffset, locatedAnnotationIndex.getLocatedAnnotation(treeFocusOffset));
        
/* 371 */         locatedAnnotationIndex.getLocatedAnnotationIndex().put(currentOffset, locatedAnnotationIndex.getLocatedAnnotation(currentOffset));
      }
/* 373 */       return true;
    }
/* 375 */     if (subsumeStrictly(locatedAnnotationIndex.getLocatedAnnotation(treeFocusOffset), locatedAnnotationIndex.getLocatedAnnotation(currentOffset)))
    {
/* 377 */       locatedAnnotationIndex.getLocatedAnnotation(currentOffset).setContiguousParent(locatedAnnotationIndex.getLocatedAnnotation(treeFocusOffset).getContiguousParent());
      
/* 379 */       List<LocatedAnnotation> locatedAnnotationList = new ArrayList();
/* 380 */       locatedAnnotationList.add(locatedAnnotationIndex.getLocatedAnnotation(treeFocusOffset));
/* 381 */       locatedAnnotationIndex.getLocatedAnnotation(currentOffset).setContiguousChild(locatedAnnotationList);
/* 382 */       locatedAnnotationList = new ArrayList();
/* 383 */       locatedAnnotationList.add(locatedAnnotationIndex.getLocatedAnnotation(currentOffset));
/* 384 */       locatedAnnotationIndex.getLocatedAnnotation(treeFocusOffset).setContiguousParent(locatedAnnotationList);
      


/* 388 */       locatedAnnotationIndex.getLocatedAnnotationIndex().put(treeFocusOffset, locatedAnnotationIndex.getLocatedAnnotation(treeFocusOffset));
      
/* 390 */       locatedAnnotationIndex.getLocatedAnnotationIndex().put(currentOffset, locatedAnnotationIndex.getLocatedAnnotation(currentOffset));
/* 391 */       return true;
    }
    
/* 394 */     return false;
  }
  
  public LocatedAnnotationInterface annotationList2LocatedAnnotation(List<Annotation> annotationList)
  {
/* 399 */     return null;
  }
  








  private static boolean subsumeStrictly(LocatedAnnotationInterface a, LocatedAnnotationInterface b)
  {
/* 412 */     return (a.getBegin() > b.getBegin()) && (a.getEnd() < b.getEnd());
  }
  







  private static boolean subsumeLoosely(LocatedAnnotationInterface a, LocatedAnnotationInterface b)
  {
/* 424 */     return ((a.getBegin() > b.getBegin()) && (a.getEnd() < b.getEnd())) || 
/* 425 */       ((a.getBegin() == b.getBegin()) && (a.getEnd() < b.getEnd())) || (
/* 426 */       (a.getBegin() > b.getBegin()) && (a.getEnd() == b.getEnd()));
  }
}
