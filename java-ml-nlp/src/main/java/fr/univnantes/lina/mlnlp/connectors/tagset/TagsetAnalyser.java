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
package fr.univnantes.lina.mlnlp.connectors.tagset;

import java.util.Map;

/**
 * Interface describing the method signatures to analyse a tagset
 * (e.g. a POS tagset)
 * 
 * @author hernandez
 *
 */
public interface TagsetAnalyser {

	/**
	 * Take in parameters String variables which corresponds to the description 
	 * tags of a textual unit
	 * (e.g. a morpho-syntax description of a tagset for a word)
	 * Return a map of detailed morpho-syntax features
	*/
	public Map<String, Object> analyseTag (Map<String, Object> tagFeaturesMap);

}
