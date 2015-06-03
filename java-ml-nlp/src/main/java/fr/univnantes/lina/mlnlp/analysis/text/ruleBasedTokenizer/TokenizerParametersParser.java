package fr.univnantes.lina.mlnlp.analysis.text.ruleBasedTokenizer;
/** 
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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import fr.univnantes.lina.javautil.IOUtilities;

/**
 * Tokenizer parameters
 *  
 * @author hernandez
 *
 */
public class TokenizerParametersParser {

	/*
	 * Members
	 */
	protected boolean verboseMode = false;
	protected Map<Integer,Integer> characterGlueFunctioningMap = new HashMap<Integer,Integer>(); 

	protected String inputFilePath = "";
	protected String stringUnitsFilePath = "";
	protected String globalRegexFlag = "";
	protected Boolean regexUnion = false;

	protected Set<String> regexFilePathSet = new HashSet<String>(); 
	protected Boolean parseInputAsMultilines = false;
	private String propertiesFilePath = "";


	protected String className = "";

	/**
	 * Constructor
	 * 
	 */
	public TokenizerParametersParser() {
		super();
	}

	/*
	 * GETTER / SETTER
	 */

	public Boolean isRegexUnion() {
		return regexUnion;
	}


	public boolean isVerboseMode() {
		return verboseMode;
	}


	/**
	 * @return the propertyFilePath
	 */
	public String getGlobalRegexFlag() {
		return globalRegexFlag;
	}


	/**
	 * @return the inputFilePathList
	 */
	public Set<String> getInputFilePathList() {
		return regexFilePathSet;
	}

	/**
	 * @return the parseInputAsMultilines
	 */
	public Boolean isParseAsMultilinesInput() {
		return parseInputAsMultilines;
	}

	/**
	 * @param parseInputAsMultilines the parseInputAsMultilines to set
	 */
	public void setParseInputAsMultilines(Boolean parseInputAsMultilines) {
		this.parseInputAsMultilines = parseInputAsMultilines;
	}

	public Map<Integer, Integer> getCharacterGlueFunctioningMap() {
		return characterGlueFunctioningMap;
	}



	/**
	 * @return the propertyFilePath
	 */
	public String getPropertyFilePath() {
		return propertiesFilePath;
	}

	public String getInputFilePath() {
		return inputFilePath;
	}

	public String getStringUnitsFilePath() {
		return stringUnitsFilePath;
	}



	/**
	 * Parse a properties file which contains the declaration of various parameters
	 * 
	 * remove comment line (starting with #) and empty line
	 * split args present in the same line
	 * 
	 * @return the list of parsed args from the properties file
	 */
	public List<String>  parseProperties() {
		List<String> argsList = new ArrayList<String>();
		List<String> propertiesFileArgsList = new ArrayList<String>();
		propertiesFileArgsList = IOUtilities.readTextFileAsStringList(this.getPropertyFilePath());

		for (String propertyLine : propertiesFileArgsList) {
			if (!propertyLine.startsWith("#") && !propertyLine.trim().equalsIgnoreCase("")) {
				//List<String> argsLineTmp = new ArrayList<String>();
				//argsLineTmp.addAll(Arrays.asList(argsTmp.split("\\s+")));
				argsList.addAll(Arrays.asList(propertyLine.split("\\s+")));
			}
		}
		return argsList;
	}




	/**
	 * parse parameters given in an array of strings
	 * (cast into list and call the list parser)
	 * 
	 * @param classname for debugging
	 * @param args
	 */
	public void parseParameters (String className, String [] args) {
		parseParameters( className, Arrays.asList(args));
	}

	/**
	 * parse parameters given in a list of strings
	 * (deals with property file)
	 * 
	 * @param classname for debugging
	 * @param argsList
	 * @return 
	 */
	public List<String> parseParameters (String className, List<String> argsList) {
		this.className = className;

		List<String> totalArgsList = new ArrayList<String>();
		if (argsList.size() != 0)  
			if( (argsList.get(0).equals("-p")) || (argsList.get(0).equals("--properties-file"))){
				if (1 == argsList.size()) usage(className, "Wrong parameters");
				propertiesFilePath = argsList.get(1);
				totalArgsList.addAll(parseProperties());
				//System.err.println("Debug: totalArgsList.toString() "+totalArgsList.toString());

			}
		totalArgsList.addAll(argsList);

		return parseParameters (totalArgsList);

	}

	/**
	 * Parse parameters given in a list of strings
	 * (effective parsing process)
	 * 
	 * @param argsList	
	 * @return the remaining parameters to parse  at an overrinding level
	 */
	protected List<String> parseParameters (List<String> argsList) {

		List<String> remainingArgsList = new ArrayList<String>();
		/*
		 * Getting parameters 
		 */
		int pos = 0;
		while (pos < argsList.size()) {
			if(argsList.get(pos).equals("-v")) {
				verboseMode = true;
				++pos;
			}
			else if ((argsList.get(pos).equals("-i")) || (argsList.get(pos).equals("--input-file"))) {
				++pos;
				if (pos == argsList.size()) usage(className, "Wrong parameters");
				inputFilePath = argsList.get(pos);
				//				System.err.println("Debug: inputFilePath "+ inputFilePath);
				++pos;
			}

			else if ((argsList.get(pos).equals("-r")) || (argsList.get(pos).equals("--regex-file"))) {
				++pos;
				if (pos == argsList.size()) usage(className, "Wrong parameters");
				regexFilePathSet.add(argsList.get(pos));
				++pos;
			}
			else if(argsList.get(pos).equals("--regex-union")) {
				regexUnion = true;
				++pos;
			}
			else if ((argsList.get(pos).equals("-g")) || (argsList.get(pos).equals("--global-flag")) ) {

				++pos;
				if (pos == argsList.size()) usage(className, "Wrong parameters");
				globalRegexFlag = argsList.get(pos);
				++pos;
			}
			/*else if(argsList.get(pos).equals("-dict")) {
			++pos;
			if(verbose) System.err.println("String units list file path: "+argsList.get(pos));
			stringUnitsFilePath = argsList.get(pos);
			++pos;
			areRegex= false;
		}*/
			else if ((argsList.get(pos).equals("-c")) || (argsList.get(pos).equals("--codepoint-functioning")) ){
				++pos;
				if (pos == argsList.size()) usage(className, "Wrong parameters");
				String codePointString = argsList.get(pos);
				++pos;
				if (pos == argsList.size()) usage(className, "Wrong parameters");
				//if(verboseMode) System.err.println("CodePoint: >"+codePointString+"< functioning: "+argsList.get(pos));
				int codepoint = codePointString.codePointAt(0);
				//if(!characterGlueFunctioningMap.isEmpty()) System.out.println("Parameter: Define character functioning for character codePoint: >"+codePointString+"< functioning: "+argsList.get(pos)+" codePointString length: >"+codePointString.length()+ " codepoint "+codepoint);
				characterGlueFunctioningMap.put(codepoint,Integer.valueOf(argsList.get(pos)));
				++pos;
			}
			else if(argsList.get(pos).equals("--multi-lines-input")) {
				parseInputAsMultilines = true;
				++pos;
			}
			else if( (argsList.get(pos).equals("-p")) || (argsList.get(pos).equals("--properties-file"))){
				++pos;
				if (pos == argsList.size()) usage(className, "Wrong parameters");
				propertiesFilePath = argsList.get(pos);
				//argsList.addAll(parseProperties());
				++pos;
			}

			else {
				remainingArgsList.add(argsList.get(pos));

				++pos;
				//usage(className, "Wrong parameters");
			}

		}
		return remainingArgsList;


	}

	/**
	 * 
	 */
	public void check () {
		if(verboseMode) {
			System.err.println("Parameter: Mode verbose");

			if (!getPropertyFilePath().equalsIgnoreCase("")) System.err.println("Parameter: Use the following properties file path: "+getPropertyFilePath());
			if(!inputFilePath.equalsIgnoreCase("")) System.err.println("Parameter: Input file path: "+inputFilePath);
			if (isParseAsMultilinesInput()) System.err.println("Parameter: Reading Input file as multiple lines (warning not stable)");
			else System.err.println("Parameter: Reading Input file as a single line");


			if(parseInputAsMultilines) System.err.println("Parameter: Process input as a single line");
			if(!characterGlueFunctioningMap.isEmpty()) System.err.println("Parameter: Define character functioning for character codePoint: >"+characterGlueFunctioningMap.keySet().toString()+"< functioning: >"+characterGlueFunctioningMap.values().toString()+"<"); // codePointString length: >"+codePointString.length()+ " codepoint "+codepoint);

			if(!regexFilePathSet.isEmpty()) System.err.println("Parameter: Regex list file path: "+regexFilePathSet.toString());
			if(regexUnion) System.err.println("Parameter: Compile an union of regex as a single pattern");

			if(!globalRegexFlag.equalsIgnoreCase("")) System.err.println("Parameter: Global regex flag: "+globalRegexFlag);

		}

		if (this.getInputFilePath().equalsIgnoreCase("")) 
			usage(className, "A least an input file should be specified");
		//System.out.println("");
	}

	/**
	 * @param className 
	 * @param message
	 *
	 */
	public  void usage(String className, String message)  {
		System.err.println();
		System.err.println("Process stopped: "+message);
		System.err.println();

		System.err.println("Usage: java "+className+" -i inputFile [-r regexListFile]... [-c character functioning]..." +
				" [--multi-line-input]" +
				" [--regex-union] [-g regexFlag]" +
				" [-v]");
		System.err.println();
		System.err.println("  -i inputFile\n\t(Path to the file to tokenize)");
		System.err.println();
		//System.err.println("  --single-line-input\n\t(The default is to process independently the multiple lines of the input text. Has consequences on the interpretation of regex expressions and the time processing.)");
		System.err.println("  --multi-lines-input\n\t(The default is to process the input text lines as a single line. Not stable.)");

		//[-o outputFilePath] 
		//System.err.println("  -o outputFilePath (resulting file path. Stdout is used by default)");
		System.err.println();
		System.err.println("  -r regexListFile\n\tLists regex expressions which should match at least 2 characters length strings not to split." +
				"\n\tThe regex should be ordered by a decreasing priority order." +
				"\n\tBy default (if not --regex-union parameter used) the process will consider the whole matched sequence as the annotation except if you use groups. In that case the first one will be annotated." +
				"\n\tAccepts empty and bash comment lines.)");
		// [-d dictListFile]... 
		System.err.println();
		System.err.println("  --regex-union\n\t(Compile the union of the regex as a single pattern. Should be faster but lead to lose some expressivity)");
		System.err.println("  -g regexFlag\n\t(When using a union regex, offer the possibility to specify a global flag such as ?iu:)");

		System.err.println();
		//System.err.println("  -d dictListFile (list of literal string expressions which should match at least 2 characters length strings not to split. Not implemented.)");
		System.err.println("  -c character functioning\n\t(Functioning 0-4: 0. current character glues nothing, 1. glues next, 2. glues previous, 3. glues both )");
		System.err.println();

		System.err.println("  -p propertyFile\n\t(File containing all these declarations. Should be the first parameter to be considered. Following ones may override some definitions. )");
		System.err.println();

		System.err.println("  -v\n\t(verbose)");

		//System.exit(1);
	}
}
