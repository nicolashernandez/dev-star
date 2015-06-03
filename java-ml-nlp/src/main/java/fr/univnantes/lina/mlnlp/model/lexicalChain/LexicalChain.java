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
package fr.univnantes.lina.mlnlp.model.lexicalChain;

import java.util.HashSet;
import java.util.Set;

import fr.univnantes.lina.mlnlp.process.comparison.SimilarityMeasures;

/**
 * @author hernandez
 *
 */
public class LexicalChain  {

	public Set<String> lexicalChains;

	public Double threshold;
	
	private static final Double DEFAULT_THRESHOLD = 0.5;
	/**
	 * @return the threshold
	 */
	public Double getThreshold() {
		return threshold;
	}

	/**
	 * @param threshold the threshold to set
	 */
	public void setThreshold(Double threshold) {
		this.threshold = threshold;
	}

	public LexicalChain() {
		super();
		this.lexicalChains = new HashSet<String>();
		setThreshold(DEFAULT_THRESHOLD);

	}

	public LexicalChain(Set<String> lexicalChain) {
		super();
		this.lexicalChains = new HashSet<String>(lexicalChain);
		setThreshold(DEFAULT_THRESHOLD);

	}
	
	public LexicalChain(LexicalChain lexicalChain) {
		super();
		this.lexicalChains = new HashSet<String>(lexicalChain.getLexicalChain());
		setThreshold(DEFAULT_THRESHOLD);

	}

	public Set<String> getLexicalChain() {
		return this.lexicalChains;
	}

	public void addLexicalChain(LexicalChain lexicalChain) {
		getLexicalChain().addAll(lexicalChain.getLexicalChain());
	}

	public void addItem(String item) {
		getLexicalChain().add(item);
	}

	public int size () {
		return getLexicalChain().size();
	}
	
	public Boolean isEmpty () {
		return size() == 0;
	}

	public Boolean isSimilar(LexicalChain otherLexicalChain, SimilarityMeasures<String> similarityMeasure) {
		return isSimilar(otherLexicalChain, similarityMeasure, getThreshold());
	}
	
	public Boolean isSimilar(LexicalChain otherLexicalChain, SimilarityMeasures<String> similarityMeasure, Double threshold) {

		if (this.compare(otherLexicalChain, similarityMeasure) > threshold) return true;
		return false;
	}




	/**
	 * compare two lexical chains
	 * 
	 * @param otherLexicalChain
	 * @param similarityMeasure 
	 * @return
	 */
	public Double compare(LexicalChain otherLexicalChain, SimilarityMeasures<String> similarityMeasure) {
		
		return similarityMeasure.measureSetSetSimilarity(getLexicalChain(), otherLexicalChain.getLexicalChain());
		//return (setSimilarityMeasures.containmentSimilarity(getLexicalChain(), otherLexicalChain.getLexicalChain()) + setSimilarityMeasures.containmentSimilarity(otherLexicalChain.getLexicalChain(),getLexicalChain()))/2 ;
	}


	/**
	 * Both equals and hashCode should be overrided to be used by collection comparison method
	 */
	@Override
	public boolean equals(Object obj) {
		//System.out.printf("Debug: is %s equals to %s\n",this.toString(),obj.toString());
		if (obj==this) 
			return true;
		if (obj instanceof LexicalChain)
			return this.isSimilar((LexicalChain) obj, null);
		return false;
	}

	@Override
	public int hashCode() {
		//System.out.printf("Debug: is %s hashCode\n",this.toString());

		// the hashcode of set is the sum of the hashcode of the elements
		// even if they share elements they always have a different hashcode
		// a priori,  hashcode is called many times and before equals... 
		// so to perform the intersection of similar without using the hashcode 
		// we will have to overwrite retainall or write our own

		return getLexicalChain().hashCode();
	}

	@Override
	public String toString ( ){
		return getLexicalChain().toString();  
	}



}
