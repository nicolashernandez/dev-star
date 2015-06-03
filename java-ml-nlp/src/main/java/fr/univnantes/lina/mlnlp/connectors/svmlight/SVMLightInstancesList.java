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
package fr.univnantes.lina.mlnlp.connectors.svmlight;

import java.util.Iterator;
import java.util.List;

import fr.univnantes.lina.mlnlp.model.alphabet.SimpleAlphabet;
import fr.univnantes.lina.mlnlp.model.ml.NameValueFeature;
import fr.univnantes.lina.mlnlp.model.ml.FeatureListInstance;
import fr.univnantes.lina.mlnlp.model.ml.ListInstances;

/**
 * 
 * @author hernandez
 * @param <T>
 *
 */
public class SVMLightInstancesList<T> extends ListInstances<T> {

	final static String DEFAULT_CHAR = "_";
	final static String reservedChar[]  = {"\\s+", ":", "\"", "\'", "\\\\" };

	//private List<Instance> instancesList;
	private SimpleAlphabet alphabet;

	/**
	 * 
	 */
	public void setInstancesList(ListInstances<T> instancesList) {
		toSVMLighFormat(instancesList);
	}
	
	/**
	 * @return the alphabet
	 */
	public SimpleAlphabet getAlphabet() {
		return alphabet;
	}

	/**
	 * @param alphabet the alphabet to set
	 */
	public void setAlphabet(SimpleAlphabet alphabet) {
		this.alphabet = alphabet;
	}


	/**
	 * SVMLight data 
	 */
	public void toSVMLighFormat(ListInstances<T> instancesList) {
		Iterator<T> instancesIterator = instancesList.getInstancesList().iterator();

		while (instancesIterator.hasNext()) {
			T instance = instancesIterator.next();

			SVMLightInstance svmLightFormatInstance = new SVMLightInstance();

			svmLightFormatInstance.setLabel(((FeatureListInstance) instance).getLabel());

			List<NameValueFeature> featuresList = ((FeatureListInstance) instance).getFeatureList();
			Iterator<NameValueFeature> featuresListIterator = featuresList.iterator();
			while (featuresListIterator.hasNext()) {
				NameValueFeature feature = featuresListIterator.next();

				NameValueFeature svmLightFormatFeature = new NameValueFeature();

				String name = feature.getName().trim();
				for (int i=0; i < reservedChar.length ; i++) {
					name = name.replaceAll(reservedChar[i], DEFAULT_CHAR);
				}

				Double value = this.getAlphabet().getSymbolCode(feature.getValue());

				if (!name.equalsIgnoreCase("") && value != null) {
					svmLightFormatFeature.setName(name);
					//System.err.println("Error: feature.getName() " + feature.getName());
					//System.err.println("Error: feature.getValue() " + feature.getValue());
					//System.err.println("Error: this.getAlphabet() " + this.getAlphabet());
					//System.err.println("Error: alphabet.getSymbolCode(feature.getValue()) " + this.getAlphabet().getSymbolCode(feature.getValue()));

					svmLightFormatFeature.setValue( value);
					svmLightFormatInstance.addFeature(svmLightFormatFeature);
				}
			}
			if (svmLightFormatInstance.getSize() > 0) this.getInstancesList().add((T) svmLightFormatInstance);
		}
	}

	/**
	 * SVMLight format [2,3]
<target> <feature>:<value> <feature>:<value> ... <feature>:<value> # <info>
	[2] http://mallet.cs.umass.edu/import.php
	[3] http://svmlight.joachims.org/
	 * @return
	 */
	public String toString() {
		Iterator<T> instancesIterator = this.getInstancesList().iterator();
		String instancesListString = "";
		while (instancesIterator.hasNext()) {
			T instance = instancesIterator.next();
			instancesListString = instancesListString + instance.toString() + "\n";
		}
		return instancesListString;
	}

	/**
	 * Default constructor
	 */
	public SVMLightInstancesList() {
		super();
		this.setAlphabet(new SimpleAlphabet());
	}

	/**
	 * Constructor from an existing alphabet
	 * Can be used for building test instances from train instances 
	 * @param alphabet
	 */
	public SVMLightInstancesList(SimpleAlphabet alphabet) {
		super();
		//System.err.println("Debug: SVMLightInstancesList(Alphabet alphabet)");
		this.setAlphabet(alphabet);

	}

	/**
	 * Constructor from an existing instances list.
	 * @param instancesList
	 */
	public SVMLightInstancesList(ListInstances<T> instancesList) {
		super();
		//System.err.println("Debug: SVMLightInstancesList(InstancesList<?> instancesList)");
		this.setAlphabet(new SimpleAlphabet());
		this.toSVMLighFormat(instancesList);

	}
}
