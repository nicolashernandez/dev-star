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
 * Marathe and Hirst 2010's Chainer algorithm
 * 
 * @author hernandez
 *
 */
public class MaratheHirstSimilarityMeasures extends SimilarityMeasures<String>  {

	/** threshold for adding a token to a chain */
	static final Double THRESHOLD_A = 0.1;

	/** threshold for merging two chains */
	static final Double THRESHOLD_M = 0.05;


	/**
	 * Compute the similarity score between two terms
	 * may use external resources
	 * Marathe and Hirst 2010's Chainer algorithm
	 * 
	 * 
	 * @param term1
	 * @param term2
	 * @return
	 */	
	@Override
	public Double measureTermTermSimilarity(String term1, String term2) {
		// TODO
		return null;
	}

	/**
	 * Compute the similarity score between a term and a lexical chain
	 * Marathe and Hirst 2010's Chainer algorithm
	 * 
	 * @param term
	 * @param chain.getLexicalChain()
	 * @return
	 */
	@Override
	public Double measureTermSetSimilarity(String term, Set<String> set) {
		Double simScore = 0.0;

		if (term.equalsIgnoreCase("") || set == null || set.isEmpty()) 
			return simScore;
		for (String chainTerm : set) {
			simScore += measureTermTermSimilarity (term, chainTerm);
		}
		return simScore / set.size();
	}

	/**
	 * Compute the similarity score between two chains
 	 * Marathe and Hirst 2010's Chainer algorithm
	 * 
	 * @param chain
	 * @param chain
	 * @return
	 */
	@Override
	public Double measureSetSetSimilarity(Set<String> set1, Set<String> set2) {
		Double simScore = 0.0;

		if (set1 == null || set2 == null || set1.isEmpty() || set2.isEmpty()) 
			return simScore;
		for (String term1 : set1) 
			for (String term2 : set2) 
				simScore += measureTermTermSimilarity (term1, term2);
		return simScore / (set1.size() * set2.size());
	}

}
