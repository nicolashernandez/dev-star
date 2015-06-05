/** 
 * 
 * Copyright (C) 2015-20..  Nicolas Hernandez
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

/**
 * most of the method can be invoked by
 * 
 * String character = "a";
 * method(character.codePointAt(0));
 * 
 * @author hernandez
 *
 */
public class CharacterUtil {



	/**
	 * 
	 * @param currentCharCodePoint
	 * @param previousCharCodePoint
	 * @return
	 */
	static public  Boolean areDifferent (int currentCharCodePoint, int previousCharCodePoint) {
		return ((Character.isLetter(currentCharCodePoint) && !Character.isLetter(previousCharCodePoint)) 
				||	(Character.isDigit(currentCharCodePoint) && !Character.isDigit(previousCharCodePoint))
				||	(isSeparator(currentCharCodePoint) && !isSeparator(previousCharCodePoint))
				||	(isNotLetterDigitOrSeparator(currentCharCodePoint) && !isNotLetterDigitOrSeparator(previousCharCodePoint)));
	}


	/**
	 * is Symbol
	 * @param codePoint
	 * @return
	 */
	static public  Boolean isNotLetterDigitOrSeparator (int codePoint) {
		return !(Character.isLetter(codePoint) || Character.isDigit(codePoint) || isSeparator(codePoint));

	}
	/**
	 * 
	 * @param codePoint
	 * @return
	 */
	static public  Boolean isSeparator (int codePoint) {
		/*if (getVerboseMode())
			System.err.println("Debug: reminder Character.LINE_SEPARATOR type>"+Character.LINE_SEPARATOR
				+"< Character.SPACE_SEPARATOR>"+Character.SPACE_SEPARATOR
				+"< Character.PARAGRAPH_SEPARATOR>"+Character.PARAGRAPH_SEPARATOR
				+"< Character.CONTROL>"+Character.CONTROL

				+"<");
		 */
		return ((Character.getType(codePoint) == Character.LINE_SEPARATOR) 
				|| (Character.getType(codePoint) == Character.SPACE_SEPARATOR)
				|| (Character.getType(codePoint) == Character.PARAGRAPH_SEPARATOR)
				|| (Character.getType(codePoint) == Character.CONTROL)
				);
	}

	/**
	 * 
	 * @param codePoint
	 * @return
	 */
	static public Boolean isAscii (int codePoint) {
		return codePoint < 128;
	}

	/** String [] vocals = {"a", "e", "i", "o", "u", "y", "A", "E", "I", "O", "U", "Y" };
	for (String vocal : vocals) 
		System.out.println(vocal+" : "+vocal.codePointAt(0));
	a : 97
	e : 101
	i : 105
	o : 111
	u : 117
	y : 121
	A : 65
	E : 69
	I : 73
	O : 79
	U : 85
	Y : 89 
	 * **/
	static public Boolean isAsciiVocal (int codePoint) {
		int [] asciiVocalCodepointArray = {65, 69, 73, 79, 85, 89, 97, 101, 105, 111, 117, 121};

		int asciiVocalCodepointIndex = 0;
		Boolean isAsciiVocal = false;
		while (asciiVocalCodepointIndex < asciiVocalCodepointArray.length && !isAsciiVocal) {
			if (asciiVocalCodepointArray[asciiVocalCodepointIndex] == codePoint) isAsciiVocal = true;
			asciiVocalCodepointIndex++;
		}
		return isAsciiVocal;
	}

	/**
	 * Strong assumption: an alphabetic character which is not an ascii character is assume to be a vocal...
	 * @param codePoint
	 * @return
	 */
	static public Boolean isVocal (int codePoint) {
		if (isAscii(codePoint)) 
			if (isAsciiVocal(codePoint)) return true;
			else return false; // alphabetic or not
		if (Character.isAlphabetic(codePoint)) return true;
		return false;
				
	}

	/**
	 * 
	 * @param codePoint
	 * @return
	 */
	static public Boolean isPunct (int codePoint) {
		//TODO
		return null;
	}
	
}
