/** 
 * 
 * Copyright (C) 2013  Nicolas Hernandez
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
package fr.univnantes.lina.mlnlp.process.evaluation;

/**
 * score metrics used for (binary) classification
 * 
 * http://en.wikipedia.org/wiki/Binary_classification
 * http://en.wikipedia.org/wiki/Accuracy_and_precision#In_binary_classification
 * http://spokenlanguageprocessing.blogspot.fr/2011/12/evaluating-multi-class-classification.html
 * http://en.wikipedia.org/wiki/Receiver_Operating_Characteristic
 * 
 * @author hernandez
 *
 */
public class ClassificationScores {


	public static final String POSITIVE_LABEL = "positive";
	public static final String NEGATIVE_LABEL = "negative";


	/**
	 * actualPositive = truePositive + falseNegative
	 * @return actualPositive
	 */
	static public double actualPositive (int tp, int fn) {
		return (double)((double)tp + (double)fn);
	}
	
	/**
	 * actualNegative = trueNegative + falsePositive
	 * @return actualNegative
	 */
	static public double actualNegative(int tn, int fp) {
		return (double)((double)tn + (double)fp);
	}
	
	/**
	 * precision = truePositive / (truePositive + falsePositive)
	 * @return precision
	 */
	static public double precision (int tp, int fp) {
		return (double)((double)tp / ((double)tp + (double)fp));
	}


	/**
	 * recall = truePositive / truePositive + falseNegative
	 * @return recall
	 */
	static public double recall (int tp, int fn) {
		return  (double)((double)tp / ((double)tp + (double)fn));
	}

	/**
	 * fMeasure = 2 precision . recall / (precision + recall)
	 * @return fMeasure
	 */
	static public double fMeasure  (int tp, int fp, int fn) {
		return (double) (2 * recall(tp, fn) * precision(tp,fp) / (recall(tp, fn) + precision(tp,fp)));
	}


	/**
	 * Accuracy = (truePositive + trueNegative) / (truePositive + trueNegative + falsePositive + falseNegative)
	 * Proportion of true results (both true positives and true negatives) in the population 
	 * http://en.wikipedia.org/wiki/Accuracy_and_precision#In_binary_classification
	 * Accuracy = (#truePositive + #trueNegative) / (#truePositive + #trueNegative + #falsePositive + #falseNegative)
	 * @return
	 */
	static public double accuracy (int tp, int tn, int fp, int fn) {
		return (double)(((double)tp + (double)tn) / ((double)(tp + tn + fp + fn)));
	}


}
