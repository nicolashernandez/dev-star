/** 
 * UIMA Annotation Mapper
 * Copyright (C) 2010  Nicolas Hernandez
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

package fr.univnantes.lina.uima.mapper.rules.DAO;
import java.util.*;

/**
 * Class for representing Rules
 * <ul>
 * <li> 
 * </ul>
 * 
 * @author      Nicolas Hernandez
 * @author      Emilien Ripoche, Charles Vukelic, Samuel Ngahane, Jerome Mace et Warren Eslan
 * @since       1.0
 */
public class Rule {
	
	private ArrayList<String> conditions = null;
	private ArrayList<String> actions = null;

	private String sourceType = "";
	private ArrayList<String> targetType = null;
	private String constraint = "";
	
	public Rule(String sourceType, ArrayList<String> targetType, String constraint) {
		super();
		this.sourceType = sourceType;
		this.targetType = targetType;
		this.constraint = constraint;
	}

	public String getSourceType() {
		return sourceType;
	}

	public void setSourceType(String sourceType) {
		this.sourceType = sourceType;
	}

	public ArrayList<String> getTargetType() {
		return targetType;
	}

	public void setTargetType(ArrayList<String> targetType) {
		this.targetType = targetType;
	}
	
	public String getConstraint() {
		return constraint;
	}

	public void setConstraint(String constraint) {
		this.constraint = constraint;
	}
}
