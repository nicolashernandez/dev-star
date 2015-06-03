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

import fr.univnantes.lina.mlnlp.model.ml.NameValueFeature;
import fr.univnantes.lina.mlnlp.model.ml.FeatureListInstance;
import fr.univnantes.lina.mlnlp.model.text.NLPTextSerializationConst;

/**
 * @author hernandez
 *
 */
public class SVMLightInstance extends FeatureListInstance {

		
		
		/**
		 * SVMLight format [2,3]
<target> <feature>:<value> <feature>:<value> ... <feature>:<value> # <info>
		[2] http://mallet.cs.umass.edu/import.php
		[3] http://svmlight.joachims.org/
		 * @return
		 */
		public String toString() {
			String line = getLabel();
			Iterator<NameValueFeature> featureIterator = getFeatureList().iterator();
			while (featureIterator.hasNext()) {
				NameValueFeature feature = featureIterator.next();
				line = line + NLPTextSerializationConst.WHITESPACE_SEPARATOR + feature.getName() +":"+ String.valueOf(feature.getValue());
			}
			return line;
		}
		
		
}
