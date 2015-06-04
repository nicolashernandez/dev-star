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


















































/**
 * The class aims at building the LocatedAnnotation index.
 * 
 * The buildsIndex method works in three stages:
 * <ol>
 * <li>parsing the annotation of aJCas.getAnnotationIndex() to create the LocatedAnnotation at each offset</li>
 * <li>parsing the locatedAnnotation index to create contiguous Following, Preceding and Partial as indexPreceding and indexFollowing </li>
 * <li>parsing the locatedAnnotation index to create contiguous Parent and Child</li>
 * </ol> 
 * 
 * SearchParent is performed from the largest annotation, and it is recursively called for each child annotation. 
 * 
 * TODO Partial Contiguous Following/Preceding LocatedAnnotation have to be implemented
 * 
 * Warning: the following and the preceding offset depend on the closest annotation 
 * If that one stands for whitespace then a process of filtering should be set up (during the first pass of AnnotationIndex)
 * 
 * @author hernandez
 *
 */
public class LocatedAnnotationIndexBuilder
{

	/**
	 * Builds a LocatedAnnotationIndex from an AnnotationIndex
	 * input aJCas.getAnnotationIndex() ou bien  aJCas.getFSIndexRepository()
	 * @param aJCas containing an AnnotationIndex
	 * @return a build locatedAnnotationIndex from the AnnotationIndex 
	 */
	public static LocatedAnnotationIndex buildsIndex(JCas aJCas)
	{
		/*  67 */     LocatedAnnotationIndex locatedAnnotationIndex = new LocatedAnnotationIndex();
		/*  68 */     Map<String, LocatedAnnotation> locatedAnnotationIndexMap = locatedAnnotationIndex.getLocatedAnnotationIndex();





		/**
		 * Map which associates for each begin offset a list of corresponding annotation
		 * in practice this index is used for building the locatedAnnotationMap sub index
		 * no more used
		 */
		/*  75 */     Map<Integer, List<Annotation>> annotationBeginMap = new TreeMap();




		/**
		 * Map which associates for each end offset a list of corresponding annotation
		 * in practice this index is used for building the locatedAnnotationMap sub index
		 */
		/*  81 */     Map<Integer, List<Annotation>> annotationEndMap = new TreeMap();





		// --------------------------------------------------------------------
		// FIRST PASS of the AnnotationIndex :  
		// builds locatedAnnotationMap LocatedAnnotation current 
		// builds annotationBeginMap
		// builds annotationEndMap

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



			// ---------------------------------------------------------------- 
			// add the annotation to the current set of the locatedAnnotation of locatedAnnotationMap at this offset
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


			// ----------------------------------------------------------------
			// add the annotation to the list of the annotation at this current begin
			/* 138 */       List<Annotation> beginAnnotationList = null;
			/* 139 */       if (annotationBeginMap.containsKey(Integer.valueOf(annotationBegin))) {
				/* 140 */         beginAnnotationList = (List)annotationBeginMap.get(Integer.valueOf(annotationBegin));
			}
			else {
				/* 143 */         beginAnnotationList = new ArrayList();
			}
			/* 145 */       beginAnnotationList.add(annotation);

			/* 147 */       annotationBeginMap.put(Integer.valueOf(annotationBegin), beginAnnotationList);


			// ----------------------------------------------------------------
			// add the annotation to the list of the annotation at this current end
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















		// --------------------------------------------------------------------
		// FIRST PASS of the LocatedAnnotationIndex :  
		// builds locatedAnnotationMap LocatedAnnotation contiguous Following, Preceding and Partial 
		// initialize minBegin and maxEnd
		// Set the indexPreceding and indexFollowing LocatedAnnotation

		/* 178 */     TreeMap<Integer, List<Annotation>> annotationBeginSortedMap = new TreeMap(annotationBeginMap);
		/* 179 */     TreeMap<Integer, List<Annotation>> annotationEndSortedMap = new TreeMap(annotationEndMap);
		/* 180 */     Set<Integer> beginSortedSet = annotationBeginSortedMap.keySet();
		/* 181 */     Set<Integer> endSortedSet = annotationEndSortedMap.keySet();


		/* 184 */     Set<String> offsetSet = locatedAnnotationIndexMap.keySet();
		/* 185 */     int annotationMinBegin = 0;
		/* 186 */     int annotationMaxEnd = 0;


		// parse the LocatedAnnotationIndex by the offset
		//for (String annotationOffset : offsetSet) {
		/* 190 */     List<String> offsetList = new ArrayList<String>();
		/* 191 */     offsetList.addAll(offsetSet);
		/* 192 */     
		for (int i = 0; i < offsetList.size(); i++) {
			/* 193 */       String annotationOffset = (String)offsetList.get(i);


			/* 196 */       LocatedAnnotation locatedAnnotation = (LocatedAnnotation)locatedAnnotationIndexMap.get(annotationOffset);
			/* 197 */       int begin = locatedAnnotation.getBegin();
			/* 198 */       int end = locatedAnnotation.getEnd();

			// Set the previous and next LocatedAnnotation
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
				//what are the annotations of the next locatedAnnotation ?
				// they are the list of annotation referenced by the next begin key in the annotationBeginSortedMap


				/* 227 */         Set<LocatedAnnotation> localLocatedAnnotationSet = new HashSet<LocatedAnnotation>();
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






			// where does the end of the previous locatedAnnotation finish ?
			// it is such that it should be an end of an annotation strictly inferior to the begin the locatedAnnotation and in the same time the nearest
			// in other words in the sorted keys of the annotation end map, it is the greatest key strictly less than the begin of the given locatedAnnotation, or null if there is no such element.
			// FIX done when two tokens are glued, the begin of the latter token is not strictly superior to the end of the former

			/* 245 */       if ((annotationEndSortedMap.containsKey(Integer.valueOf(begin))) || (annotationEndSortedMap.lowerKey(Integer.valueOf(begin)) != null)) {
				/* 246 */         int previousEnd = -1;
				/* 247 */         if (annotationEndSortedMap.containsKey(Integer.valueOf(begin))) { previousEnd = begin;
				} else {
					/* 249 */           previousEnd = ((Integer)annotationEndSortedMap.lowerKey(Integer.valueOf(begin))).intValue();
				}


				/* 253 */         Set<LocatedAnnotation> localLocatedAnnotationSet = new HashSet();
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




		// partialNext
		// PRECOND (partialNext.begin > current.begin)  && (partialNext.begin < current.end) && (partialNext.end > current.end)
		// all the annotation referenced in  annotationBeginSortedMap starting from the first one following in annotationBeginSortedMap and ending when > current.end which respects the PRECOND (partialNext.end > current.end)
		// TODO

		// partialPrevious
		// TODO

	

	// --------------------------------------------------------------------
	// Second PASS of the LocatedAnnotationIndex :  
	// builds locatedAnnotationMap LocatedAnnotation ancestor and descendant

	// Need for a top LocatedAnnotation parent for all locatedAnnotations

		/* 282 */     LocatedAnnotationInterface topLocatedAnnotation = new LocatedAnnotation();
		/* 283 */     topLocatedAnnotation.setBegin(annotationMinBegin);
		/* 284 */     topLocatedAnnotation.setEnd(annotationMaxEnd);





		/* 290 */     Map<String, Integer> offsetAlreadySeen = new TreeMap(new OffsetComparator());
		/* 291 */     for (int begin : beginSortedSet) {
			
	



		//		/* 295 */         localLocatedAnnotationSet.hasNext())
		//{
			/* 291 */ //      int begin = ((Integer)begin.next()).intValue();
			/* 292 */   
			List<Annotation> offsetAnnotationList = (List)annotationBeginMap.get(Integer.valueOf(begin));


			/* 295 */     //  localLocatedAnnotationSet = offsetAnnotationList.iterator(); continue;Annotation annotation = (Annotation)localLocatedAnnotationSet.next();

			// for all annotations at a given begin offset
						for (Annotation annotation : offsetAnnotationList) {
							//System.out.println("\tbegin>"+annotation.getBegin()+"< end>"+annotation.getEnd()+"< >"+annotation.getType().getName()+"< ");

			/* 298 */       if (!offsetAlreadySeen.containsKey(Offset.getOffset(annotation)))
			{
				/* 300 */         offsetAlreadySeen.put(Offset.getOffset(annotation), Integer.valueOf(1));


				/* 303 */         searchParent(locatedAnnotationIndex, Offset.getOffset(topLocatedAnnotation), Offset.getOffset(annotation), false);
			}
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
