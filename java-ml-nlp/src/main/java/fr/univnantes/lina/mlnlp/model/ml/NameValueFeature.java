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

/**
 * 
 * @author hernandez
 *
 */
public class NameValueFeature implements Feature{
	
	final static Double DEFAULT_VALUE = 1.0;
	
	private String name;
	private Object value;
	
	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * set the name and replace all the whitespace character by a non ws char
	 * @param name the name to set
	 * 
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * @return the value
	 */
	public Object getValue() {
		return value;
	}
	
	/**
	 * @param value the value to set
	 */
	public void setValue(Object value) {
		this.value = value;
	}
	
	
	/**
	 * 
	 */
	public NameValueFeature() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * 
	 * @param name
	 * @param value
	 */
	public NameValueFeature(String name) {
		super();
		this.setName(name);
		this.setValue(DEFAULT_VALUE);
	}
	
	/**
	 * @param name
	 * @param value
	 */
	public NameValueFeature(String name, Object value) {
		super();
		this.setName(name);
		this.setValue( value);
	}
	
	
}
