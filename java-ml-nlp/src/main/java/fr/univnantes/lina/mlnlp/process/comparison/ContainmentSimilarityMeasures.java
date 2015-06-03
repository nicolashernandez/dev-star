/** 
 * Copyright (C) 2015  Nicolas Hernandez
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
package fr.univnantes.lina.mlnlp.process.comparison;

import java.util.HashSet;
import java.util.Set;

public class ContainmentSimilarityMeasures<T>  extends SimilarityMeasures<T>  {

	/**
	 * Compute the similarity score between two sets
	 * 
	 * @param set1
	 * @param set2
	 * @return
	 */
	@Override
	public Double measureSetSetSimilarity (Set<T> set1, Set<T> set2){
		return containmentSimilarity(set1,set2, true);
	}
	
	/**
	 * compare two sets
	 * Containment asymetric similarity is S contained in T is |S ∩ T|/|S|
	 * (Yang et Callan 2005), (Monostori et al, 2002)
	 * @param strictEqual should be true if an element can only be counted once, false otherwise
	 * @param Set1 
	 * @param Set2
	 * 
	 * @return
	 */
	public  Double containmentSimilarity(Set<T> l1, Set<T> l2, Boolean strictEqual) {
		// Set retainAll requires both equals and hashcode	
		// the hashcode of a set is the sum of the hashcode of the elements
		// even if they share elements they will always have a different hashcode
		// so to perform the intersection of similar without using the hashcode 
		// we will have to overwrite retainall or write our own
		//Set<T> intersection = new HashSet<T>(l1);
		//intersection.retainAll(l2);
		//	return (double) ((double) intersection.size() / (double) (l1.size()));

		//System.out.printf("Debug: before intersect, is %s contained in %s ?\n",l1.toString(),l2.toString());	

		Double intersection = intersectionSize(l1,l2, strictEqual);
		Double score = (double) ((double) intersection / (double) (l1.size()));		
		//System.out.printf("Debug: after intersect, |∩| = %f, l1.size = %d, containScore = %f\n",intersection, l1.size(), score);
		return score;

	}
	
	/**
	 * retain the number of element in common between two sets 
	 * normalized by the number distinct associations
	 * since an element can be equal to several ones
	 * 
	 * @param l1
	 * @param l2
	 * @param strictEqual should be true when there is a bijective equivalence relation between the two sets
	 * @return
	 */
	private  Double intersectionSize (final Set<T> l1, final Set<T> l2, Boolean strictEqual) {
		
		String type = "";
		Set<T> l = new HashSet<T>();
		Double numberOfDistinctAssociation = 0.0;
		
		for (T element1 : l1) {
			for (T element2 : l2) {
				if (element1.equals(element2)) {
					
					l.add(element1);
					//l.add(element2); // since they are equals because of a similarity relation
					numberOfDistinctAssociation++;
					type = element1.getClass().getSimpleName();
							
				}
			}
		}
		//System.out.printf("Debug: type %s, #intersect=%d, #l1=%d, #actualLinks=%f\n", type, l.size(), l1.size(), numberOfDistinctAssociation);
		if (strictEqual) return l.size() *1.0;
		return l.size()/(Double) numberOfDistinctAssociation;
				
		
	}
	
}