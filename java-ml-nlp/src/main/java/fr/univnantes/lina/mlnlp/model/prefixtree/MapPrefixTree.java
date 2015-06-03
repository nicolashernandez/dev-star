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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fr.univnantes.lina.javautil.DateUtilities;


/**
 * Basic data structure of a tree: the node
 * No tree structure exists but a reference to a root node
 * Some of the nodes are leaves (not always the terminal ones) and contains a list of string values   
 * 
 * @author rocheteau, (revised by hernandez)
 *
 */
public class MapPrefixTree implements PrefixTree {

	private MapPrefixTree parent;
	private Map<Integer,MapPrefixTree> children;

	/**
	 * Is a final node of a branch corresponding to the last character of an entry
	 */
	private boolean leaf;
	
	/**
	 * Associated values (from CSV columns) 
	 * to the branch defined by the path from the root node to the current node 
	 */
	private List<List<String>> valuesList;

	public MapPrefixTree() {
		this.setChildren();
		this.setLeaf(false);
	}

	/*
	 * Setter 
	 */
	public void setParent(MapPrefixTree node) {
		this.parent = node;
	}

	private void setChildren() {
		//this.children = new HashMap<Character,Node_Impl>();
		this.children = new HashMap<Integer,MapPrefixTree>();

	}

	public void setLeaf(boolean leaf) {
		this.leaf = leaf;
	}

	public void setValues (ArrayList<String> values) {
		if (this.valuesList == null) this.valuesList = new ArrayList<List<String>>();
		List<String> currentValues = new ArrayList<String>();
		currentValues = values;
		this.valuesList.add(currentValues);
		//this.values = new ArrayList<String>();
		//this.values = values;
	}

	/*
	 * Getter
	 */
	private MapPrefixTree getParent() {
		return this.parent;
	}

	//private Map<Character,Node_Impl> getChildren() {
	private Map<Integer,MapPrefixTree> getChildren() {

		return this.children;
	}

	/**
	 * An implementation of getChild
	 * Define the way to access and test if a character (char Character or codePoint) is present in the children
	 * @see fr.univnantes.lina.mlnlp.model.prefixtree.PrefixTree Interface 
	 */
	@Override
	public MapPrefixTree getChild(Character character) {
		return this.getChildren().get(character);
	}

	/**
	 * An implementation of getChild
	 * Define the way to access and test if a character (char Character or codePoint) is present in the children
	 * @see fr.univnantes.lina.mlnlp.model.prefixtree.PrefixTree Interface 
	 */
	@Override
	public MapPrefixTree getChild(int codePoint) {
		//if (forceToLowerCase) return Character.toLowerCase(this.getChildren().get(codePoint));
		return this.getChildren().get(codePoint);
	}	
	
	public List<List<String>> getValues () {
	//	public List<String> getValues () {
		//return this.values;
		return this.valuesList;
	}


	/*
	 * 
	 */
	@Override
	public boolean isLeaf() {
		return this.leaf;
	}

	/**
	 * An implementation of the add method to add a new node to the tree
	 * Recursive method which parses the string of the key
	 * 
	 * @param characters the string whose characters have to be added to the tree as nodes
	 * @param index cursor which points to the current character parsed in the characters string
	 * @param values list of values to associate with a leaf node
	 * @see fr.univnantes.lina.mlnlp.model.prefixtree.PrefixTree Interface 
	 */
	@Override
	public void add(String characters, int index, ArrayList<String> values) {
		int length = characters.length(); 
		if (index < length) {
			//char ch = characters.charAt(index);
			//Character character = new Character(ch);
			int currentCodePoint = characters.codePointAt(index);

			//Node_Impl node = this.getChild(character);
			MapPrefixTree node = this.getChild(currentCodePoint);

			// the current node character has never been followed by the current character parsed of the string
			if (node == null) {
				// create a new child node for this character
				node = new MapPrefixTree();
				//this.getChildren().put(character,node);
				this.getChildren().put(currentCodePoint,node);

			}

			if (!node.isLeaf()) {
				// declare the current child node as a potential leaf 
				// may reset an existing leaf... means duplicate entries
				node.setLeaf((index + 1) == length);
			}

			if (node.isLeaf() && ((index + 1) == length)) {
				// set the array of values 
				node.setValues(values);
			}

			if (node.getParent() == null) {
				// link the child Node to its parent
				node.setParent(this);   
			}

			// recursively call the add method with the following characters 
			node.add(characters, index + 1, values);
		}
	}



	/*
	 * 
	 */
	@Override
	public String toString() {
		String string = "";                     
		//for (Character character : this.getChildren().keySet()) {
		for (Integer charCodePoint : this.getChildren().keySet()) {

			MapPrefixTree node = this.getChild(charCodePoint);
			for (int index = 0; index < node.deep(); index++) {
				string += " ";
			}
			;
			//string += character.toString();
			string += DateUtilities.codePointToString(charCodePoint);
			if (node.isLeaf()) {
				string += "*";
			}
			string += "\n";
			string += node.toString();
		}
		return string;
	}

	public int deep() {
		MapPrefixTree parent = this.getParent();
		if (parent == null) {
			return 0;
		} else {
			return parent.deep() + 1;
		}
	}

}