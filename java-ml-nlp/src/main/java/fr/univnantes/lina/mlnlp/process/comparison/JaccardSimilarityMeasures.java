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
import java.util.LinkedHashSet;
import java.util.Set;



/**
 * Jaccard symetric similarity of sets S and T is |S ∩ T|/|S ∪ T|
 * (Jaccard, 1912), (Monostori et al, 2002), (Lyon et al, 2004), (Stein et Eissen, 2006) 
 * 
 * @author hernandez
 * @param <T>
 *
 */
public class JaccardSimilarityMeasures<T> extends SimilarityMeasures<T>  {



	/**
	 * compare two sets
	 * Jaccard symetric similarity of sets S and T is |S ∩ T|/|S ∪ T|
	 * (Jaccard, 1912), (Monostori et al, 2002), (Lyon et al, 2004), (Stein et Eissen, 2006) 
	 * 
	 * @param Set1 
	 * @param Set2
	 * @return
	 */
	@Override
	public  Double measureSetSetSimilarity(Set<T> l1, Set<T> l2) {
		Set<Object> intersection = new HashSet<Object>(l1);

		//System.out.printf("Debug: before is %s jaccard similar to %s\n",l1.toString(),l2.toString());
		intersection.retainAll(l2);
		//System.out.printf("Debug: after  is %s jaccard similar to %s\n",l1.toString(),l2.toString());

		//System.out.println("Debug: |l1|"+ l1.size());
		//System.out.println("Debug: |l2|"+ l2.size());
		//System.out.println("Debug: |l1 ∩ l2|"+ intersection.size());
		return (double) ((double) intersection.size() / (double) (l1.size() + l2.size() - intersection.size()));
	}



}
