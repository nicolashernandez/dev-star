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
package fr.univnantes.lina.mlnlp.analysis.text.ruleBasedTokenizer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import fr.univnantes.lina.app.tokenizer.TokenizerCommandLineParametersParser;
import fr.univnantes.lina.javautil.IOUtilities;
import fr.univnantes.lina.javautil.StringUtilities;


/**
 * JAVA tokenizer
 * 
 * The basic (we say natural) way of token segmentation is to annotate as tokens sequences of digit or letters, and 
 * single characters for any other characters except whitespace.
 * It is possible to overwrite the natural way by specifying the "glue" functioning of some ambiguous characters like ',', or '-', or "'"
 * It is possible to overwrite the glue functioning by giving a list of regex tokens  
 * 
 * Works on unicode codepoints.
 * 
 * @author hernandez
 *
 * TODO reminder the Character.getType() function may be a solution to make finer the (natural) tokenization behavior 
 */
public abstract class Tokenizer {


	private Map<Integer,Integer> characterGlueFunctioning ;
	private List<String> stringUnitsList;
	private Map<Integer,Integer> characterOffsetStatus;

	protected final Integer GLUE_NOTHING = 0;
	protected final Integer GLUE_NEXT = 1;
	protected final Integer GLUE_PREVIOUS = 2;
	protected final Integer GLUE_BOTH_SIDES = 3;
	protected final Integer GLUE_LAZY_NEXT = 4; // may glue previous one too
	protected final Integer GLUE_LAZY_PREVIOUS = 5; // may glue next one too

	private Map<Integer,String> characterFunctioning ;


	/**
	 * Default value 
	 * initial value, assumed not correspond to any unicode char, should not be found in a text...
	 */
	private int DEFAULT_INITIAL_NON_UNICODE_CODEPOINT_VALUE = 0;

	protected  Boolean verboseMode = false;

	protected String globalRegexFlag = "";

	/*
	 * local private
	 */
	private boolean wasATokenFound= false;
	private int tokenPreviouslyFoundBegin = -1;
	private StringBuffer currentTokenCoveredText = new StringBuffer("");
	private StringBuffer currentInterTokenString = new StringBuffer("");

	/*
	 * Constructor
	 */

	protected Tokenizer() {
		this.characterGlueFunctioning = new HashMap<Integer,Integer>();
		this.stringUnitsList = new ArrayList<String>();
		this.characterOffsetStatus = new HashMap<Integer,Integer>();
		this.characterFunctioning = new HashMap<Integer,String>();
		characterFunctioning.put(GLUE_NOTHING,"GLUE_NOTHING");
		characterFunctioning.put(GLUE_NEXT,"GLUE_NEXT");
		characterFunctioning.put(GLUE_PREVIOUS,"GLUE_PREVIOUS");
		characterFunctioning.put(GLUE_BOTH_SIDES,"GLUE_BOTH_SIDES");
		characterFunctioning.put(GLUE_LAZY_NEXT,"GLUE_LAZY_NEXT");
		characterFunctioning.put(GLUE_LAZY_PREVIOUS,"GLUE_LAZY_PREVIOUS");
	}

	/*
	 * getter / setter
	 */


	/**
	 * @return the globalRegexFlag
	 */
	public String getGlobalRegexFlag() {
		return globalRegexFlag;
	}

	/**
	 * @param globalRegexFlag the globalRegexFlag to set
	 */
	public void setGlobalRegexFlag(String globalRegexFlag) {
		this.globalRegexFlag = globalRegexFlag;
	}

	/**
	 * 
	 * @return
	 */
	protected  Boolean isVerboseMode() {
		return verboseMode;
	}

	/**
	 * 
	 * @param verbose
	 */
	public void setVerboseMode(Boolean verbose) {
		this.verboseMode = verbose;
	}

	/**
	 * @return the characterOffsetStatus
	 */
	protected Map<Integer, Integer> getCharacterOffsetStatus() {
		return characterOffsetStatus;
	}

	/**
	 * @param characterOffsetStatus the characterOffsetStatus to set
	 */
	protected void addCharacterOffsetStatus(int characterOffset, int status) {
		if (getCharacterOffsetStatus().containsKey(characterOffset)) 
			if (isVerboseMode())
				System.err.println("Warning: setting an already defined character offset >"+characterOffset+"< from>"+characterFunctioning.get(getCharacterOffsetStatus().get(characterOffset))+ "< to>"+characterFunctioning.get(status)+"< (3 -> 1|2 should be normal)");
		getCharacterOffsetStatus().put(characterOffset, status);
	}

	/**
	 * 
	 * @param characterOffset
	 * @return
	 */
	protected Boolean hasCharacterFunctioningAtTheGivenOffset(int characterOffset) {
		return getCharacterOffsetStatus().containsKey(characterOffset);

	}

	protected Integer getCharacterFunctioningAtTheGivenOffset(int characterOffset) {
		return getCharacterOffsetStatus().get(characterOffset);
	}

	protected Integer removeCharacterFunctioningAtTheGivenOffset(int characterOffset) {
		return getCharacterOffsetStatus().remove(characterOffset);
	}


	/**
	 * @return the stringUnitsList
	 */
	public List<String> getStringUnitsList() {
		return stringUnitsList;
	}

	/**
	 * @return the characterGlueFunctioning
	 */
	protected Map<Integer, Integer> getCharacterGlueFunctioning() {
		return characterGlueFunctioning;
	}


	/**
	 * given two contiguous characters, we assume there are natural segmentation 
	 * functioning depending on the class of the characters. 
	 * Four classes of characters are considered: letter, digit, whitespace according to Java, none of the three previous class.  
	 *   - the same class and letters: glue
	 *   - the same class and digits: glue
	 *   - other combinations do not glue
	 * 
	 * Unicode code point
	 *     
	 * glue to the previous/next character
	 * binaryCode	integerCode	
	 * 00	0	do not glue neither the previous nor the next
	 * 01	1	glue the next character
	 * 10	2	glue the previous character
	 * 11	3	glue both
	 * 
	 * priority String unit list > DefaultCharacteristics > Natural
	 * @param character
	 * @param functioning
	 */
	public void addCharacterGlueFunctioning(int codePoint, int functioning) {
		getCharacterGlueFunctioning().put(codePoint, functioning);
	}

	/**
	 * 
	 * @param codePoint
	 * @return
	 */
	protected Boolean hasCharacterGlueFunctioning (int codePoint) {
		return getCharacterGlueFunctioning().containsKey(codePoint);
	}

	/**
	 * all the characters inside of the unit glue both sides (code 3), 
	 * first and last characters glue the next and glue the previous character; respectively (code 1 and code 2)
	 * 
	 * @param stringUnitsList
	 */
	public void addStringUnitsList(List<String> stringUnitsList) {
		for (String stringUnit : stringUnitsList) {
			if (!stringUnit.startsWith("#") && !stringUnit.trim().equalsIgnoreCase("")) getStringUnitsList().add(stringUnit);
		}
		//getStringUnitsList().addAll(stringUnitsList);
	}

	



	/*
	 *  settings
	 */

	/**
	 * Set tokenizer parameters (regex list, how to precess them (union or not, global flag),  character functioning, how to process text input (single line))
	 * @param tokenizerParameters
	 */
	public void settings(TokenizerParametersParser tokenizerParameters) {

		// Loading stringUnitsList
		for (String stringUnitsFilePath : tokenizerParameters.getInputFilePathList()) {
			this.addStringUnitsList(IOUtilities.readTextFileAsStringList(stringUnitsFilePath));

		}
		if (tokenizerParameters.isRegexUnion()) 
			this.setRegexUnion(tokenizerParameters.isRegexUnion());

		//if (!simpleTokenizerParameter.getStringUnitsFilePath().equalsIgnoreCase("")) {
		//	simpleTokenizer.setStringUnitsList(IOUtilities.readTextFileAsStringList(simpleTokenizerParameter.getStringUnitsFilePath()));
		//}
		if (!tokenizerParameters.getGlobalRegexFlag().equalsIgnoreCase("")) {
			this.setGlobalRegexFlag(tokenizerParameters.getGlobalRegexFlag());
		}

		// Loading text file options
		if (tokenizerParameters.isParseAsMultilinesInput()) 
			this.setMultiLineInput(tokenizerParameters.isParseAsMultilinesInput());


		// Set characters Glue Functioning
		if (!tokenizerParameters.getCharacterGlueFunctioningMap().isEmpty()) {
			for (int codePoint : tokenizerParameters.getCharacterGlueFunctioningMap().keySet()) {
				this.addCharacterGlueFunctioning(codePoint, tokenizerParameters.getCharacterGlueFunctioningMap().get(codePoint));
			}
		}

		// Set verbose mode for tokenizer
		if (tokenizerParameters.isVerboseMode()) 
			this.setVerboseMode(tokenizerParameters.isVerboseMode());

	}



	/*
	 * processing code point
	 */

	/**
	 * Test if the given codePoint corresponds to an unicode char
	 * Used for initializing the process when no text char has been parsed yet
	 * @param codePoint
	 * @return
	 */
	private boolean isUndefined(int codePoint) {
		return (codePoint==DEFAULT_INITIAL_NON_UNICODE_CODEPOINT_VALUE);
	}

	/**
	 * 
	 * @param wasATokenFound
	 * @param currentCharCodePoint
	 * @param previousCharCodePoint
	 * @return isAClosingCharCodePoint
	 */
	private static boolean isAClosingCharCodePoint(boolean wasATokenFound, int currentCharCodePoint, int previousCharCodePoint) {
		return
				(wasATokenFound) &&
				//	( (Character.isLetter(currentCharCodePoint) && Character.isDigit(previousCharCodePoint))
				//	|| (Character.isLetter(previousCharCodePoint) && Character.isDigit(currentCharCodePoint))
				( (Character.isLetter(currentCharCodePoint) && !Character.isLetter(previousCharCodePoint))
						|| (Character.isDigit(currentCharCodePoint) && !Character.isDigit(previousCharCodePoint))
						//						|| (Character.isSpaceChar(currentCharCodePoint)) // && Character.isLetterOrDigit(previousChar)) 
						|| (isSpaceOrLineSeparator(currentCharCodePoint)) // && Character.isLetterOrDigit(previousChar)) 

						// this following condition is taken in charge by the following following condition
						//|| ((!Character.isSpaceChar(currentChar) && !Character.isLetterOrDigit(currentChar)) && Character.isLetterOrDigit(previousChar)) 
						//						|| (!Character.isSpaceChar(currentCharCodePoint) && !Character.isLetterOrDigit(currentCharCodePoint)) 
						|| (!isSpaceOrLineSeparator(currentCharCodePoint) && !Character.isLetterOrDigit(currentCharCodePoint)) 

						)	
						;

	}

	/**
	 * 
	 * @param wasATokenFound
	 * @param currentCharCodePoint
	 * @param previousCharCodePoint
	 * @return isAnOpenningCharCodePoint
	 */
	private boolean isAnOpeningCharCodePoint(boolean wasATokenFound, int currentCharCodePoint, int previousCharCodePoint) {
		/*if (getVerboseMode()) 
			System.err.println("Debug: codePoint>"+currentCharCodePoint
					+"< isSpaceChar >"+Character.isSpaceChar(currentCharCodePoint)
					+"< isSpaceOrLineSeparator>"+isSpaceOrLineSeparator(currentCharCodePoint)+"<");
		 */
		return( (!wasATokenFound) &&
				(
						(Character.isLetter(currentCharCodePoint) &&  (!Character.isLetter(previousCharCodePoint) || isUndefined(previousCharCodePoint)))
						|| (Character.isDigit(currentCharCodePoint) &&  (!Character.isDigit(previousCharCodePoint) || isUndefined(previousCharCodePoint)))
						//						|| (!Character.isLetter(currentCharCodePoint) && !Character.isDigit(currentCharCodePoint) && !Character.isSpaceChar(currentCharCodePoint)) 
						|| (!Character.isLetter(currentCharCodePoint) && !Character.isDigit(currentCharCodePoint) && !isSpaceOrLineSeparator(currentCharCodePoint)) 
						)
				)
				//&& (!Character.isSpaceChar(currentCharCodePoint))
				;
	}

	/**
	 * 
	 * @param codePoint
	 * @return
	 */
	private static Boolean isSpaceOrLineSeparator (int codePoint) {
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






	/*
	 * 
	 */

	protected Boolean multiLineInput = false;

	protected Boolean regexUnion = false;


	/*
	 * getter and setter
	 */

	public Boolean isRegexUnion() {
		return regexUnion;
	}

	public void setRegexUnion(Boolean regexUnion) {
		this.regexUnion = regexUnion;
	}


	protected void setMultiLineInput(Boolean multiLineInput) {
		this.multiLineInput  = multiLineInput;

	}



	/*
	 * 
	 */
	/**
	 * Pre-process text by recognizing given string units defined as regex
	 * (main pretokenize method)
	 * 
	 * @param text
	 */
	public void preTokenizeText(List<String> lines) {
		if (isRegexUnion()) preTokenizeTextWiARegexUnion(lines);
		else preTokenizeTextWiAPatternList(lines);
	}


	/**
	 * A super regex is built from the union of all the regex 
	 * @param lines
	 */
	public void preTokenizeTextWiARegexUnion(List<String> lines) {	
		if (isVerboseMode()) System.err.println("Pre-Tokenize Text Wi A Regex Union");
		if (!getStringUnitsList().isEmpty()) {
			String regexUnion = buildARegexUnion(this.getGlobalRegexFlag(),getStringUnitsList());
			Pattern p = Pattern.compile(regexUnion);
			int begin = 0;
			int end = 0;
			for (String  line : lines) {
				if (isVerboseMode()) System.err.println("Debug: line>"+line+"<");

				Matcher m = p.matcher(line);
				end += line.length();
				while (m.find()) {
					int foundBegin = -1;
					int foundEnd = -1;
					// with buildSuperRegex we do not use group !
					foundBegin = m.start() + begin;
					foundEnd = m.end() + begin;

					String foundText = m.group();
					if (isVerboseMode())
						System.err.println("Debug: groupCount>"+m.groupCount()+"< foundBegin>"+foundBegin+"< foundEnd>"+foundEnd+"< foundText>"+foundText+"<");

					//System.out.println("Debug: group>"+m.group(0)+"<");

					Boolean canAddThisToken = true;

					for (int i =foundBegin ; i < foundEnd ; i++)
						if (hasCharacterFunctioningAtTheGivenOffset(i)) canAddThisToken =  false;

					if (canAddThisToken
							// the previous loop add basically character status at the extremities
							//&& !hasCharacterOffsetStatus(foundBegin) 
							//&& !hasCharacterOffsetStatus(foundEnd-1)
							) {

						for (int i = foundBegin ; i < foundEnd ; i++) 
						{
							if (isVerboseMode()) System.err.println("Debug: addCharacterOffsetStatus>"+i+"<");
							addCharacterOffsetStatus(i,GLUE_BOTH_SIDES);
						}
						addCharacterOffsetStatus(foundBegin,GLUE_NEXT);
						addCharacterOffsetStatus(foundEnd-1,GLUE_PREVIOUS);
					}
				}
				begin = end;
			}
		}
	}

	/**
	 * 
	 */
	public void preTokenizeText(String line) {
		if (!getStringUnitsList().isEmpty()) {
			String superRegex = buildARegexUnion(this.getGlobalRegexFlag(),getStringUnitsList());
			Pattern p = Pattern.compile(superRegex);
			Matcher m = p.matcher(line);

			while (m.find()) {
				/*Boolean canAddThisToken = true;

				for (int i = m.start() ; i < m.end() ; i++)
					if (hasCharacterOffsetStatus(i)) canAddThisToken =  false;

				if (canAddThisToken
											// the previous loop add basically character status at the extremities

						//&& !hasCharacterOffsetStatus(m.start()) 
						//&& !hasCharacterOffsetStatus(m.end()-1)
						 ) {*/
				for (int i = m.start() ; i < m.end() ; i++)
					addCharacterOffsetStatus(i,GLUE_BOTH_SIDES);
				addCharacterOffsetStatus(m.start(),GLUE_NEXT);
				addCharacterOffsetStatus(m.end()-1,GLUE_PREVIOUS);
				//}
			}
		}

	}


	/**
	 * Pretokenize text with a list of regex 
	 * (the alternative is an union of regex)
	 * 
	 * @param lines
	 */
	public void preTokenizeTextWiAPatternList(List<String> lines) {
		if (isVerboseMode()) System.err.println("Pre-Tokenize Text Wi A Regex Pattern list");

		if (!getStringUnitsList().isEmpty()) {
			List<Pattern> patternList = new ArrayList<Pattern>(); 

			// pattern compilation
			for (String regex : getStringUnitsList()) {
				patternList.add(Pattern.compile(regex));
			}

			int begin = 0;
			int end = 0;
			for (String  line : lines) {
				end += line.length();
				for (Pattern p : patternList) {
					Matcher m = p.matcher(line);

					// when a pattern match more than the selected group, start the next find from the group end
					// seems a bad idea : the process remains stoke in infinite loop
					//int startFrom = 0;
					//while (m.find(startFrom)) {
						while (m.find()) {

						int foundBegin = -1;
						int foundEnd = -1;
						//  Each rules should  define at least one group 
						if (m.groupCount() == 0) {
							foundBegin = m.start() +  begin;
							foundEnd = m.end() +  begin;
						}
						else {
							foundBegin = m.start(1) +  begin;
							foundEnd = m.end(1) +  begin;
						}
						//startFrom = foundEnd - begin;

						// Check if a pretoken has already be anotated
						Boolean canAddThisToken = true;
						for (int i = foundBegin ; i < foundEnd ; i++)
							if (hasCharacterFunctioningAtTheGivenOffset(i)) canAddThisToken =  false;

						// if there is no pretoken at these offsets
						if (canAddThisToken
								// the previous loop add basically character status at the extremities
								//&& !hasCharacterOffsetStatus(foundBegin) 
								//&& !hasCharacterOffsetStatus(foundEnd-1)
								) {

							for (int i = foundBegin ; i < foundEnd ; i++) 
								addCharacterOffsetStatus(i,GLUE_BOTH_SIDES);
							addCharacterOffsetStatus(foundBegin,GLUE_NEXT);
							addCharacterOffsetStatus(foundEnd-1,GLUE_PREVIOUS);
							
							if (isVerboseMode()) {
								System.err.println("Debug: line>"+line.substring(m.start(), m.end())+"<");
								System.err.print("Debug: >-");
								for (int i = foundBegin ; i < foundEnd ; i++)
									System.err.print(characterFunctioning.get(getCharacterFunctioningAtTheGivenOffset(i))+"-");
								System.err.print("<");
								System.err.println("");
							}
						}
					}
				}
				begin = end;
			}
		}
	}

	/**
	 * Build a single regex from all the stringUnitsList
	 * @param stringUnitsList
	 * @return
	 */
	private String buildARegexUnion (String globalRegexFlag, List<String> stringUnitsList) {
		String superRegex = "";
		for (String stringUnit : stringUnitsList) {
			superRegex += stringUnit +"|";
		}
		if (!superRegex.equalsIgnoreCase("")) 
			superRegex = superRegex.substring(0, superRegex.length()-1);

		if (!globalRegexFlag.equalsIgnoreCase(""))
			superRegex = "("+globalRegexFlag+superRegex+")"; 


		if (isVerboseMode()) System.err.println("Debug: regex union>"+superRegex+"<");

		return superRegex;
	}

	/**
	 * Depending on CharacterGlueFunctioning definition, and the presence of definition at the current/next offset
	 * set the status
	 * Used for taking into account characterGlueFunctioning
	 * @param currentCharCodePoint
	 * @param index
	 * @param length
	 */
	private void settingCharacterFunctioningAtAGivenOffset(int currentCharCodePoint,
			int index, int length) {
		//  character glue functioning
		if (!hasCharacterFunctioningAtTheGivenOffset(index) && hasCharacterGlueFunctioning(currentCharCodePoint)) {
			int characterGlueFunctioning = getCharacterGlueFunctioning().get(currentCharCodePoint);
			if (isVerboseMode())
				System.err.print( " characterGlueFunctioning >"+characterGlueFunctioning+"<");

			//TODO should also consider neighborhood (the next character for glue_next and both) 
			// e.g. prud'home 
			// problem; when 2 consecutive opposite functioning : the first one (the current) prevails over the next one, 
			// ok for character functioning but if its from a list functioning ? this one is more important
			if (characterGlueFunctioning == GLUE_NOTHING) 
				addCharacterOffsetStatus(index,GLUE_NOTHING);
			else if (characterGlueFunctioning == GLUE_NEXT) {
				addCharacterOffsetStatus(index,GLUE_NEXT);
				// add some contraints on the next char; this one shouldn t have constraint from string units tagging
				if ((index-1 < length) && !hasCharacterFunctioningAtTheGivenOffset(index+1)) 
					addCharacterOffsetStatus(index+1,GLUE_LAZY_PREVIOUS);
			}
			else if (characterGlueFunctioning == GLUE_PREVIOUS) 
				addCharacterOffsetStatus(index,GLUE_PREVIOUS);
			else if (characterGlueFunctioning == GLUE_BOTH_SIDES) {
				addCharacterOffsetStatus(index,GLUE_BOTH_SIDES);
				// add some constraints on the next char; this one shouldn't have constraint from string units tagging
				//if ((index-1 < length) && !hasCharacterFunctioningAtTheGivenOffset(index+1)) 
				// I removed && !hasCharacterFunctioningAtTheGivenOffset(index+1) for processing quelqu'un
					if ((index-1 < length)) 	
					addCharacterOffsetStatus(index+1,GLUE_LAZY_PREVIOUS);
				//if (index-1 < length)
				//	System.err.println("Debug: charFunct at index+1 is >"+characterFunctioning.get(getCharacterFunctioningAtTheGivenOffset(index+1))+"<");
			}
		}
	}

	/**
	 * tokenization depending on previously recognized string units or character functioning definition
	 * (the former prevails over the latter)
	 * 
	 * @param begin
	 * @param index
	 * @param currentCharCodePoint
	 */
	private void adaptativeTokenization(int begin, int index,
			int currentCharCodePoint) {
		int currentCharacterOffsetStatus = getCharacterFunctioningAtTheGivenOffset(index);
		if (isVerboseMode())
			System.err.println(" charOffsetStatus >"+characterFunctioning.get(currentCharacterOffsetStatus)+"<");

		if (currentCharacterOffsetStatus == GLUE_NOTHING) {
			if (wasATokenFound) {
				createAnnotation(currentTokenCoveredText.toString().toString(), begin + tokenPreviouslyFoundBegin, begin+index, currentInterTokenString.toString());
				wasATokenFound = false;
				tokenPreviouslyFoundBegin = -1;
				currentInterTokenString = new StringBuffer("");
			}
		} 
		else if (currentCharacterOffsetStatus == GLUE_NEXT) {
			if (wasATokenFound) {
				createAnnotation(currentTokenCoveredText.toString().toString(), begin + tokenPreviouslyFoundBegin, begin+index, currentInterTokenString.toString());
				wasATokenFound = false;
				tokenPreviouslyFoundBegin = -1;
				currentInterTokenString = new StringBuffer("");

			}
			if (!wasATokenFound) {
				wasATokenFound = true;
				tokenPreviouslyFoundBegin = index;
				currentTokenCoveredText = new StringBuffer("");
				currentTokenCoveredText.appendCodePoint(currentCharCodePoint);
			}
		} 
		else if (currentCharacterOffsetStatus == GLUE_LAZY_NEXT) {
			if (!wasATokenFound) {
				wasATokenFound = true;
				tokenPreviouslyFoundBegin = index;
				currentTokenCoveredText = new StringBuffer("");
				currentTokenCoveredText.appendCodePoint(currentCharCodePoint);
			}
		} 
		else if (currentCharacterOffsetStatus == GLUE_PREVIOUS) 
		{

			if (wasATokenFound) {
				// TODO check at the extremity ; +1 means that the current char is included
				currentTokenCoveredText.appendCodePoint(currentCharCodePoint);
				createAnnotation(currentTokenCoveredText.toString(), begin + tokenPreviouslyFoundBegin, begin+index+1, currentInterTokenString.toString());
				wasATokenFound = false;
				tokenPreviouslyFoundBegin = -1;
				currentInterTokenString = new StringBuffer("");
			}
		} 
		else if ((currentCharacterOffsetStatus == GLUE_BOTH_SIDES) 
				|| (currentCharacterOffsetStatus == GLUE_LAZY_PREVIOUS) ){
			// do nothing
			currentTokenCoveredText.appendCodePoint(currentCharCodePoint);
		}
	}


	/**
	 * natural tokenization depending on the property of consecutive characters
	 * 
	 * @param begin
	 * @param index
	 * @param previousCharCodePoint
	 * @param currentCharCodePoint
	 */
	private void naturalTokenization(int begin, int index,
			int previousCharCodePoint, int currentCharCodePoint) {
		if (isVerboseMode()) 
			System.err.println("");

		if (isAClosingCharCodePoint(wasATokenFound,currentCharCodePoint,previousCharCodePoint)){
			//System.out.println("Debug: simpleToken>"+text.substring(tokenPreviouslyFoundBegin, index)+"< begin>"+tokenPreviouslyFoundBegin+"< end>"+index+"<");
			createAnnotation(currentTokenCoveredText.toString(), begin + tokenPreviouslyFoundBegin, begin+index, currentInterTokenString.toString());

			wasATokenFound = false;
			tokenPreviouslyFoundBegin = -1;
			if (isVerboseMode()) {
				System.err.println("Debug: closing a current token with previousCodePoint>"+previousCharCodePoint+"< currentCodePoint>"+currentCharCodePoint+"<");
				//System.err.println("------------------------------------------------");
			}
			currentInterTokenString = new StringBuffer("");

		}

		if (wasATokenFound)
			currentTokenCoveredText.appendCodePoint(currentCharCodePoint);
		//else 
		//currentInterTokenString.appendCodePoint(currentCharCodePoint);
		// opening is not an alternative to the close, a char can close and being the starter of a new token 
		// e.g. '[' in 'Industrie[10]' token 'Industrie' '[' '10'
		// open an annotation 
		if (isAnOpeningCharCodePoint(wasATokenFound,currentCharCodePoint,previousCharCodePoint)){

			if (isVerboseMode()) {
				//System.err.println("------------------------------------------------");
				System.err.println("Debug: starting a new token with previousCodePoint>"+previousCharCodePoint+"< currentCodePoint>"+currentCharCodePoint+"<");
			}
			//System.err.println("Debug: simpleToken>"+text.substring(tokenPreviouslyFoundBegin, index)+"< begin>"+tokenPreviouslyFoundBegin+"< end>"+index+"<");
			wasATokenFound = true;
			tokenPreviouslyFoundBegin = index;
			currentTokenCoveredText = new StringBuffer("");
			currentTokenCoveredText.appendCodePoint(currentCharCodePoint);
		}


	}



	/**
	 * Tokenize a text given as list of string
	 * by calling the tokenize string method on each single line
	 *  
	 * TODO not working yet
	 */
	public void tokenize(List<String> lines) {
		int begin = 0;
		int end = 0;
		String linesString = "";
		if (lines.size() >1 ) {System.err.println("Warning: you are currently using the tokenize method to process more than one line, it is known that it does not work yet");}
		for (String  line : lines) {
			//TODO processing multilines may  require to append a char at the end of the line...
			linesString += line ; //+ System.lineSeparator();
			/*end += line.length();
			if (getVerboseMode()) 
				System.err.println("Debug: line>"+line+"< begin>"+begin+"< end>"+end+"<" );
//			tokenize(line, begin, end);
			tokenize(line, 0, line.length());

			begin = end;
			//end = 0;

			 */
		}
		tokenize(linesString, 0, linesString.length());
	}

	/**
	 * Tokenize text parsing each character codepoint deciding to create annotation
	 * taking into account characterfunctioning at the given offset 
	 * and potentially previously recognized string units by a pre-tokenize process
	 * 
	 * no return value, but the createAnnotation method should be overwritten. 
	 * @param text
	 * @param begin
	 * @param end
	 * 
	 */
	public void tokenize(String text, int begin, int end) {
		int length = end; //text.length();

		// initial value, assumed not correspond to any unicode char, should not be found in a text...
		int previousCharCodePoint = DEFAULT_INITIAL_NON_UNICODE_CODEPOINT_VALUE; 
		// TODO is is possible to start at  the character offset 1 instead of 0
		// and to get before the codepoint of the character 0 to start the process ?

		// for each text character 
		for (int index = begin; index < length; index++) {
			//Character aCharacter = new Character(currentChar);
			int currentCharCodePoint = text.codePointAt(index);

			if (isVerboseMode()) 
				System.err.print("Debug: currChar>"+StringUtilities.codePointToString(currentCharCodePoint)
						+"< codePoint>"+currentCharCodePoint+"< type>"+	Character.getType(currentCharCodePoint)
						+"< prevChar>"+StringUtilities.codePointToString(previousCharCodePoint)
						+"< begin>"+tokenPreviouslyFoundBegin+"< end>"+index
						+"< hasCharOffsetStatus>"+hasCharacterFunctioningAtTheGivenOffset(index)
						+"< hasCharGlueFunct>"+hasCharacterGlueFunctioning(currentCharCodePoint)+"<");

			// setting offset status depending on character functioning definition 
			settingCharacterFunctioningAtAGivenOffset(currentCharCodePoint, index, length);

			// to correct such wrong effect from functioning definition 
			// anniv'_
			// the glue effect should not be considered with ws characters
			// that s why we remove it 
			// but be careful to preserve 4 567
			if (hasCharacterFunctioningAtTheGivenOffset(index) 
					&& isSpaceOrLineSeparator(currentCharCodePoint) 
					&& (getCharacterFunctioningAtTheGivenOffset(index) == GLUE_LAZY_PREVIOUS)) {
				//if (isVerboseMode()) System.err.print("Debug: removeCharacterOffsetStatus>"+index+"<");
				removeCharacterFunctioningAtTheGivenOffset(index);
			}

			// from the string units list or the character functioning definition 
			if (hasCharacterFunctioningAtTheGivenOffset(index)) {
				// adaptative behavior depending on recognized regex or character functioning at the current offset
				adaptativeTokenization(begin, index,
						currentCharCodePoint); 
			}
			else {
				// natural  behavior
				naturalTokenization(begin, index,
						previousCharCodePoint, currentCharCodePoint);
			}
			previousCharCodePoint = currentCharCodePoint;

			if (isSpaceOrLineSeparator(currentCharCodePoint)) currentInterTokenString.appendCodePoint(currentCharCodePoint);
			if(isVerboseMode()) System.err.println("------------------------------------------------");
		}


		// end an annotation for the last non whitespace character encountered
		if (isAClosingCharCodePoint(wasATokenFound,previousCharCodePoint,DEFAULT_INITIAL_NON_UNICODE_CODEPOINT_VALUE)){
			//System.out.println("Debug: simpleToken>"+text.substring(tokenPreviouslyFoundBegin, index)+"< begin>"+tokenPreviouslyFoundBegin+"< end>"+index+"<");
			createAnnotation(currentTokenCoveredText.toString(), begin + tokenPreviouslyFoundBegin, begin+text.length(), currentInterTokenString.toString());
			wasATokenFound = false;
			tokenPreviouslyFoundBegin = -1;
		}
	}




	/**
	 * create annotation covering the given begin and end offsets
	 * @param coveredText 
	 * @param begin
	 * @param end
	 * @param currentInterTokenString 
	 */
	protected  abstract void createAnnotation(String coveredText, int begin, int end, String currentInterTokenString);

	/**
	 * post processing for creating some remaining annotation or sweeping the output...
	 */
	protected abstract void postTokenization() ;






}
