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
package fr.univnantes.lina.mlnlp.model.alphabet;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import fr.univnantes.lina.javautil.SecurityUtilities;
import fr.univnantes.lina.javautil.StringUtilities;

/**
 * @author hernandez

 * string-typed feature value which are turned into a double-typed code
 * distinct string-typed feature values should have distinct double-typed codes
 * symbol means the feature value
 * 
 * TODO to remove one of the map http://stackoverflow.com/questions/1383797/java-hashmap-how-to-get-key-from-value
 */
public class SimpleAlphabet implements Alphabet {

	/**
	 * unique indice 
	 */
	private double indice;

	/**
	 * when a symbol is encoded, a code results.
	 * allow to check whether the code correspond to the same
	 */
	private Map<Double,Object> code2SymbolMap ;

	/**
	 * allow to store the association symbol to code 
	 * to avoid of computing code at each symbol
	 * assumed faster
	 */
	private Map<Object,Double> symbol2CodeMap ;

	/**
	 * 
	 */
	public SimpleAlphabet() {
		super();
		this.symbol2CodeMap = new HashMap<Object,Double>();
		this.code2SymbolMap = new HashMap<Double,Object>();
		this.indice = 1;
	}

	/**
	 * various ways of encoding
	 * the surest way may be to concatenate each codePoint but takes some room
	 * we prefer a heuristic hoping there is no clash
	 */
	private   Double encode1 (String symbolString)
	{
		int codePointArray[] = StringUtilities.string2CodePoint((String)symbolString);
		Double code = 0.0;
		// encoding algorithm too simple
		// Error: the code 13812.0 generated for symbol zoomer has already been assigned to another symbol arment
		for (int i = 0; i < codePointArray.length ; i++) {
			code = code + codePointArray[i] * (i+1);

		}
		code  = code * codePointArray.length;

		return code ;
	}

	/**
	 * various ways of encoding
	 * the surest way may be to concatenate each codePoint but takes some room
	 */
	private  Double encode2 (int codePointArray[]) {
		Double code = -1.0;

		int firstCharCode = (StringUtilities.string2CodePoint("!"))[0];
		String codeString = "";
		String codeStringMod = "";
		for (int i = 0; i < codePointArray.length ; i++) {
			code = code + (codePointArray[i] - firstCharCode) * (i+1);
			codeString = String.valueOf(codePointArray[i]) + codeString ;
			codeStringMod = String.valueOf(codePointArray[i] % firstCharCode) + codeStringMod;
		}
		Double codeDouble = Double.valueOf(codeString);
		System.err.println("Debug: codeString "+codeString+" code "+ code+" codeDouble "+codeDouble + " codeStringMod "+ codeStringMod);
		//code = Double.valueOf(codeStringMod);
		//System.err.println("Debug: codeStringMod "+codeStringMod+" code "+ Double.valueOf(codeStringMod));
		return code ;
	}

	/**
	 * encoding by md5
	 */
	private Double encode3 (String symbol) {
		Double code = -1.0;

		String md5code = SecurityUtilities.encodeMD5((String)symbol);
		String md5codeAfterReplacement = md5code;

		md5codeAfterReplacement = md5codeAfterReplacement.replaceAll("a", "1");
		md5codeAfterReplacement = md5codeAfterReplacement.replaceAll("b", "2");
		md5codeAfterReplacement = md5codeAfterReplacement.replaceAll("c", "3");
		md5codeAfterReplacement = md5codeAfterReplacement.replaceAll("d", "4");
		md5codeAfterReplacement = md5codeAfterReplacement.replaceAll("e", "5");
		md5codeAfterReplacement = md5codeAfterReplacement.replaceAll("f", "6");
		//System.out.println("Debug: md5code before "+ md5code + " after " + md5codeAfterReplacement);
		code = Double.valueOf(md5codeAfterReplacement);

		return code ;
	}



	/**
	 * simple encoding (assume only asynchronous access)
	 */
	private Double encode (Object symbol) {
		return this.indice++ ;
	}


	/**
	 * convert any symbol to a double code
	 */
	private  Double symbol2code (Object symbol) {
		Double code = null;

		if (symbol instanceof Double) code = (Double) symbol;
		else if (symbol instanceof Integer) {
			code = Double.valueOf((Integer)symbol);
		}
		else if (symbol instanceof Float) {
			code = Double.valueOf((Float)symbol);
		}
		else if  (symbol instanceof Short) {
			code = Double.valueOf((Short)symbol);
		}
		else if (symbol instanceof Long)  {

			code = Double.valueOf((Long)symbol);
		}
		else if (symbol instanceof Boolean) {
			if ((Boolean)symbol) code = Double.valueOf(1.0);
			else code = Double.valueOf(0.0);
		}
		else if (symbol instanceof Character) {
			//code = encode(String.valueOf(symbol));
			//code= encode(String.valueOf(symbol));

		}
		else if (symbol instanceof String) {
			//code= encode((String)symbol);

		}
		else {
			System.err.println("Error: encoding fail since cant identify the type of " + symbol.toString());
		}
		return code;
	}

	/**
	 * 
	 * add a symbol to the alphabet
	 * 
	 */
	private double addSymbol(Object symbol) {
		Double code = symbol2code(symbol);
		if (code == null)  {
			if (symbol instanceof Character) {
				code= encode(String.valueOf(symbol));

			}
			else if (symbol instanceof String) {
				code= encode((String)symbol);
			}


			// if the generated code has already been assigned to a symbol
			if (this.code2SymbolMap.containsKey(code)) {
				System.err.println("Error: the code "+code
						+" generated for symbol "+symbol
						+" has already been assigned to another symbol "+ this.code2SymbolMap.get(code));
				code = null;
			}
			else {
				this.symbol2CodeMap.put(symbol, code);
				this.code2SymbolMap.put(code, symbol);
			}
		}

		return code;
	}

	/**
	 * 
	 * get the code of the corresponding symbol 
	 * if absent, generate and add it to the alphabet
	 * 
	 */
	public Double getSymbolCode(Object symbol) {

		//System.err.println("Error: String.valueOf(symbol) " + String.valueOf(symbol));

		Double code = null;
		// if the code has already been generated for this symbol
		if (this.symbol2CodeMap.containsKey(symbol)) {
			code = this.symbol2CodeMap.get(symbol);
			//System.err.println("Error: this.symbol2CodeMap.get(symbol) " + code);

		}
		else {
			code = addSymbol(symbol);
			//System.err.println("Error: addSymbol(symbol) " + code);

		}
		return code;
	}

	/**
	 * Return the alphabet symbols
	 * @return
	 */
	public Set<Object> getSymbolSet() {
		return this.symbol2CodeMap.keySet();
	}

	/**
	 * Return the code used in the alphabet
	 * @return
	 */
	public Set<Double> getCodeSet() {
		return this.code2SymbolMap.keySet();
	}
	
}
