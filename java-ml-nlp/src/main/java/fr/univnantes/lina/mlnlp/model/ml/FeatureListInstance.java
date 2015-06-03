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
package fr.univnantes.lina.mlnlp.model.ml;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author hernandez
 *
 */
public class FeatureListInstance implements Instance{

		private String label;
		private List<NameValueFeature> featureList;
		
		/**
		 * @return the size (number of features)
		 */
		public int getSize() {
			return this.featureList.size();
		}
		
		/**
		 * @return the label
		 */
		public String getLabel() {
			return label;
		}


		/**
		 * @param label the label to set
		 */
		public void setLabel(String label) {
			this.label = label;
		}


		/**
		 * @return the features
		 */
		public List<NameValueFeature> getFeatureList() {
			return featureList;
		}


		/**
		 * @param features the features to set
		 */
		public void setFeatures(List<NameValueFeature> featureList) {
			this.featureList = featureList;
		}

		/**
		 * @param add feature
		 */
		public void addFeature(NameValueFeature feature) {
			this.featureList.add(feature);
		}

		/**
		 * @param add feature
		 */
		/*public void addFeature(String name, Alphabet alphabet, Object value) {
			Feature aFeature = new Feature(name, alphabet, value);
			this.featureList.add(aFeature);
		}*/
		
		/**
		 * @param add feature
		 */
		public void addFeature(String name) {
			NameValueFeature aFeature = new NameValueFeature(name);
			this.featureList.add(aFeature);
		}
		
		/**
		 * @param add feature
		 */
		public void addFeature(String name, Object value) {
			NameValueFeature aFeature = new NameValueFeature(name, value);
			this.featureList.add(aFeature);
		}

		
		/**
		 * From a set of names, create features with the names and assign them the default value
		 * @param add 
		 */
		public void addFeatures(Set<String> featureNameSet) {
			Iterator<String> featureNameSetIterator = featureNameSet.iterator();
			while (featureNameSetIterator.hasNext()) {
				String featureName = featureNameSetIterator.next();
				this.addFeature(featureName);
			}
		}
		
		
		/**
		 * From a list of names, create a set of features with the names and assign them the default value
		 * @param add 
		 */
		public void addFeaturesListAsASet(List<String> featureNameList) {
			Set<String> featureNameSet = new HashSet<String>(featureNameList);
			addFeatures(featureNameSet);
		}
		
		/**
		 * From a map of names/scores, create features with the names and the score values
		 * @param add 
		 */
		public void addFeatures(Map<String,Double> featureNameValueMap) {
			
			Iterator<String> featureNameMapKeySetIterator = featureNameValueMap.keySet().iterator();
			while (featureNameMapKeySetIterator.hasNext()) {
				String featureName = featureNameMapKeySetIterator.next();
				this.addFeature(featureName,featureNameValueMap.get(featureName));
			}
		}
		
		/**
		 * From a list of names, create features with the names 
		 * and assign them values corresponding to the occurrence count
		 * @param add 
		 */
		public void addFeatures(List<String> featureNameList) {
			
			Map<String,Double> featureNameValueMap = new HashMap<String,Double>();
			
			Iterator<String> featureNameListIterator = featureNameList.iterator();
			while (featureNameListIterator.hasNext()) {
				String featureName = featureNameListIterator.next();
				if (!featureNameValueMap.containsValue(featureName)) 
					featureNameValueMap.put(featureName, new Double(1.0));
				else
					featureNameValueMap.put(featureName, (featureNameValueMap.get(featureName) + 1.0));
			}
			addFeatures(featureNameValueMap);
		}
		
		/**
		 * Add a list of existing features from a given instance
		 * TODO should  attempt to merge scores of identical features  ? boolean no, but double yes
		 * @param add 
		 */
		public void addFeatures(FeatureListInstance instance) {
			
			Iterator<NameValueFeature> instanceFeatureListIterator = instance.getFeatureList().iterator();
			while (instanceFeatureListIterator.hasNext()) {
				NameValueFeature feature = instanceFeatureListIterator.next();
				addFeature(feature);
			}
		}
		
		/**
		 * 
		 */
		public FeatureListInstance() {
			super();
			// TODO Auto-generated constructor stub
			label = "";
			featureList = new ArrayList<NameValueFeature>();
		}
		
		/**
		 * 
		 * @param label
		 * @param features
		 */
		public FeatureListInstance(String label, List<NameValueFeature> featureList) {
			super();
			this.label = label;
			this.featureList = featureList;
		}
		
		
		
}
