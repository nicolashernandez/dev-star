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

import java.util.List;
import java.util.Set;

/**
 * @author hernandez
 *
 */
public class InformationRetrievalScores {
	

	/**
	 * Information Retrieval metric
	 * Precision = correct  / retrieved
	 * 
	 * Classification metric
	 * #truePositive / (#truePositive + #falsePositive)
	 * 
	 * truePositive = correct
	 * falsePositive = retrieved - truePositive
	 * 
	 * Recall = correct  / relevant
	 * or truePositive / truePositive + falseNegative
	 * falseNegative = relevant - truePositive = relevant - correct
	 * 
	 * trueNegative = not relevant not correct// (all - relevant) 
	 * 
	 * @return
	 */
	static public double precision (int correct, int retrieved) {
		return (double) (((double)correct)/ ((double)retrieved));
	}


	/**
	 * Recall = correct  / relevant
	 * = tp / tp + fn
	 * @return
	 */
	static public double recall (int correct, int relevant) {
		return  (double)((double)correct / (double)relevant);
	}
	
	/**
	 * F measure = 2 precision . recall / (precision + recall)
	 * @return
	 */
	static public double fMeasure  (int correct, int retrieved, int relevant) {
		return (double) (2 * recall(correct, relevant) * precision(correct,retrieved) / (recall(correct, relevant) + precision(correct,retrieved)));
	}
	
	
	/**
	 * Average precision
	 * 
	 * the position 0 is assumed to be the top best position for a correct result
	 * 
	 * http://en.wikipedia.org/wiki/Information_retrieval#Average_precision
	 * And here some examples
	 * http://www.kaggle.com/c/FacebookRecruiting/details/Evaluation
	 * https://www.cl.cam.ac.uk/teaching/2006/InfoRtrv/lec3.2.pdf (slide 29)
	 * 
	 * @param correctPositionSet set of positions where a correct has been found in an ordered list of retrieved results
	 * @param retrieved	number of retrieved results (i.e. the actual number of returned result by the system)
	 * @param precisionRank average precision computed if we had retrieved this number of result 
	 * @return
	 */
	public static double averagePrecision (Set<Integer> correctPositionSet, int retrieved, int precisionRank) {
		double ap = 0.0;
		// sum of encountered corrects at this position when decreasing parsing the retrieved result
		int correctAtThisPosition = 0; 
		for (int position=0 ; position < Math.min(retrieved, precisionRank); position++) {
			double precision = 0.0;
			if (correctPositionSet.contains(position)) {
				correctAtThisPosition++;
				precision = (double)correctAtThisPosition/(double)(position+1);
				ap += precision;
				//System.out.printf("Debug: position %d, correctAtThisPosition %d, precision %.3f, ap %.3f\n",position, correctAtThisPosition, precision, ap);
			}
		}
		if (correctAtThisPosition == 0) return 0.0;
		//System.out.println();
		return ap/(double)correctAtThisPosition ;//Math.min(retrieved, precisionRank);
	}
	
	/**
	 * MAP Mean Average Precision
	 * 
	 * mean of AP obtained for distinct queries or individual tests (word alignment)
	 * 
	 * @return
	 */
	public static double map (List<Double> averagePrecisionSet){
		Double map = 0.0;
		for (Double averagePrecision : averagePrecisionSet)	
			map += averagePrecision;
		return map/averagePrecisionSet.size();
	}
}
