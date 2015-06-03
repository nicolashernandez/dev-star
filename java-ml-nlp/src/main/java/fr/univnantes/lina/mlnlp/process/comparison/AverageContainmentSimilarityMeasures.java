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

import java.util.Set;

public class AverageContainmentSimilarityMeasures<T>  extends SimilarityMeasures<T>  {

	/**
	 * Compute the average containment similarity score between two sets
	 * By NH 
	 * 
	 * @param set1
	 * @param set2
	 * @return
	 */
	@Override
	public Double measureSetSetSimilarity (Set<T> set1, Set<T> set2){
		ContainmentSimilarityMeasures<T> containmentSimilarityMeasures = new ContainmentSimilarityMeasures<T> ();
		
		//System.out.println("----------------");
		Double part1 = containmentSimilarityMeasures.measureSetSetSimilarity(set1, set2);
		//System.out.println("----------------");
		Double part2 = containmentSimilarityMeasures.measureSetSetSimilarity(set2, set1);
		//System.out.println("----------------");
		Double average = (part1 + part2) /2;
		//System.out.printf("Debug: lc score of %s in %s is %f\n",getLexicalChain().toString(),otherLexicalChain.getLexicalChain(),part1);
		//System.out.printf("Debug: lc score of %s in %S is %f\n",otherLexicalChain.getLexicalChain().toString(),getLexicalChain(),part2);
		//System.out.printf("Debug: lc average is %f\n",average);
		return average;

	}
	
}