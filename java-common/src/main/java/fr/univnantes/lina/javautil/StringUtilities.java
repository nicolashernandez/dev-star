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
package fr.univnantes.lina.javautil;


public class StringUtilities  {

	private int DEFAULT_INITIAL_NON_UNICODE_CODEPOINT_VALUE = 0;

	
	/**
	 * Build a random String name based on the current System Time in milliseconds
	 * (could be more sure with Calendar?) and with the name of the calling Class 
	 * if the parameter value is <code>this</code>. Leave to <code>null</code> for 
	 * the other case.
	 */
	public static String buildARandomStringName(Object obj) {
				
				if (obj == null) {
					return Long.toString(System.currentTimeMillis());
				}
				else { 
					return obj.getClass().getName()+ "-" + Long.toString(System.currentTimeMillis());
				}
			}

	/**
	 * Convert unicode CodePoint to String
	 */
	public static String codePointToString(int codePoint) {
		char[] currentChars = Character.toChars(codePoint);
		String currentString = "";
		for (int i = 0 ; i < currentChars.length ; i++) {
			currentString += currentChars[i];
		}
		return currentString;
	}
	
	/**
	 * Convert a string to an array of CodePoint
	 * Partially based on http://stackoverflow.com/questions/1029897/comparing-a-char-to-a-code-point
	 * 
	 * @param currentString
	 * @return
	 */
	public static int[] string2CodePoint (String currentString) {
		int codePointArray[] = new int[currentString.length()];
		int sequenceIndex = 0;
		int arrayIndex = 0;
		while (sequenceIndex < currentString.length()) {
			int codePoint = currentString.codePointAt(sequenceIndex++);
			if (Character.charCount(codePoint) == 2) {
				sequenceIndex++;
			}
			codePointArray[arrayIndex++] = codePoint;
		}
		return codePointArray;	
	}
	
	/**
	 * In case of overwriting the toString method of object class
	 * but to still access to its original returned value
	 * http://docs.oracle.com/javase/7/docs/api/java/lang/Object.html#toString()
	 * 
	 * @param obj
	 * @return string representation
	 */
	public static String anyObjectToString (Object obj) {
	 return obj.getClass().getName() + '@' + Integer.toHexString(obj.hashCode());
	}
	
	


}