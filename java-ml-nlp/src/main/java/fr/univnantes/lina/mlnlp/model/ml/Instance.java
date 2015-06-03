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

import java.util.List;
import java.util.Map;

/**
 * Interface describing the method signatures to
 * handle instances
 * 
 * @author hernandez
 *
 */
public interface Instance {

	public int getSize();
	
	/**
	 * @return the label
	 */
	public String getLabel();


	/**
	 * @param label the label to set
	 */
	public void setLabel(String label);


	/**
	 * @return the features
	 */
	public List<NameValueFeature> getFeatureList();


	/**
	 * @param features the features to set
	 */
	public void setFeatures(List<NameValueFeature> featureList);

	/**
	 * @param add feature
	 */
	public void addFeature(NameValueFeature feature);

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
	public void addFeature(String name);
	
	/**
	 * @param add feature
	 */
	public void addFeature(String name, Object value);

	/**
	 * From a list of names, create features with the names and assign them the default value
	 * @param add 
	 */
	public void addFeatures(List<String> featureNameList);
}
