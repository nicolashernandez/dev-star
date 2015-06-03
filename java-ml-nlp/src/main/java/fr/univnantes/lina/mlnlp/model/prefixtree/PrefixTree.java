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
package fr.univnantes.lina.mlnlp.model.prefixtree;

import java.util.ArrayList;
import java.util.List;

public interface PrefixTree {


	public boolean isLeaf();

	/**
	 * Get a character from a Character char object
	 * The implementation of this method corresponds to the way a character is searched among the children which are stored in a Map
	 * The simple implementation is a get(key) method. 
	 * If you wish to be case sensitive you should reimplement this method. 
	 * @param c
	 * @return
	 */
	public PrefixTree getChild(Character c);

	/**
	 * Get a character from its Unicode code point
	 * The implementation of this method corresponds to the way a character is searched among the children which are stored in a Map
	 * The simple implementation is a get(key) method. 
	 * If you wish to be case sensitive you should reimplement this method. 
	 *  
	 * @param c
	 * @return
	 */
	public PrefixTree getChild(int c);

	/**
	 * Add a new node to the tree
	 * Recursive method which parses a string key
	 * 
	 * @param characters the string whose characters have to be added to the tree as nodes
	 * @param index cursor which points to the current character parsed in the characters string
	 * @param values list of values to associate with a leaf node
	 */
	public void add(String characters,int index,ArrayList<String> values);
	
	//	public List<String> getValues();
	public List<List<String>> getValues();


}