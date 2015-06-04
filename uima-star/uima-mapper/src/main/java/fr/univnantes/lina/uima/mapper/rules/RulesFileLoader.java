/** 
 * UIMA Type Mapper
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
package fr.univnantes.lina.uima.mapper.rules;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.apache.commons.jxpath.JXPathContext;
import org.apache.commons.jxpath.JXPathNotFoundException;
import org.apache.commons.jxpath.xml.DocumentContainer;
import org.apache.uima.resource.ResourceAccessException;
import org.apache.uima.resource.ResourceInitializationException;

import fr.univnantes.lina.uima.mapper.rules.DAO.Rule;

/**
 * Class for parsing and loading the rules to process
 * <ul>
 * <li> 
 * </ul>
 * 
 * @author      Nicolas Hernandez
 * @author      Emilien Ripoche, Charles Vukelic, Samuel Ngahane, Jerome Mace et Warren Eslan
 * @version     %I%, %G%
 * @since       1.0
 */
public class RulesFileLoader {

	//private LinkedList<Rule> rules = new LinkedList<Rule>();
	//private ArrayList<Rule> rules = new ArrayList<Rule>();
	
	// Règles
	//private fr.univnantes.lina.uima.mapper.annotation.JAXBRules.Rules aRules = null;

	/**
	 * Build the rules list by parsing the rules file
	 * Use XPath to retrieve each rule information
	 * In turn, 
	 * @param urlRulesFile
	 * @return rules list
	 */
	public RulesFileLoader (File urlRulesFile) {
		//		 public RulesFactory (URL urlRulesFile) {

		//loadRulesFromADHOCFile (urlRulesFile);
		loadRulesFromJAXBFile (urlRulesFile);
	}


	/**
	 * 
	 */
	public static LinkedList<Rule> loadRulesFromADHOCFile (File urlRulesFile) {
		
		LinkedList<Rule> rules = new LinkedList<Rule>();
		
		String sourceTypeStr;
		String targetTypeStr;
		Iterator<String> targetItr;
		String constraint;
		
		//Création d'un DocumentContainer contenant notre fichier Xml
		DocumentContainer monDoc = null;
		try {
			System.out.println("Debug: RulesFileParser - urlRulesFile.toURI().toURL() >"+urlRulesFile.toURI().toURL());

			monDoc = new DocumentContainer(urlRulesFile.toURI().toURL());
		} catch (MalformedURLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		//Création d'un JXPathContext à partir de ce DocumentContainer
		JXPathContext jxpathContext = JXPathContext.newContext(monDoc);

		Iterator itRules = jxpathContext.iterate("//Rule");

		System.out.println("Debug: number of rules "+jxpathContext.selectNodes("//Rule").size());

		int i = 0;
		while(itRules.hasNext())
		{
			i++;
			itRules.next();
			System.out.println("Debug:----------- rule rank "+i+"");

			//SOURCE
			String xpath = "//Rule["+i+"]/Source";
			sourceTypeStr = (String)jxpathContext.getValue(xpath);
			System.out.println("Debug: source "+i+": "+sourceTypeStr);


			//TARGET
			xpath = "//Rule["+i+"]/Target";
			targetItr = jxpathContext.iterate(xpath);
			ArrayList<String> aL = new ArrayList<String>();
			while(targetItr.hasNext()){
				String tgt = targetItr.next();
				System.out.println("Debug: target "+i+": "+tgt);
				aL.add(tgt);
			}

			//CONSTRAINT
			xpath= "//Rule["+i+"]/Constraint";
			try{
				constraint = (String)jxpathContext.getValue(xpath);
				System.out.println("Debug: constraint "+i+": "+constraint);
			}catch(JXPathNotFoundException e){
				constraint = null;
			}

			System.out.println("Debug: number of target "+aL.size());

			//AJOUT A LA LISTE
			rules.add(new Rule(sourceTypeStr,aL,constraint));
		}
		
		return rules;
	}
	
	/**
	 * 
	 */
	public static fr.univnantes.lina.uima.mapper.rules.JAXB.Rules loadRulesFromJAXBFile (File xmlRulesFilePath) {
	
		// Get a stream from path
		InputStream stream = null;
		try {
			stream = new FileInputStream(xmlRulesFilePath);
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		return  loadRulesFromJAXBFile(stream);
	}
	

	/**
	 * 
	 */
	public static fr.univnantes.lina.uima.mapper.rules.JAXB.Rules loadRulesFromJAXBFile (InputStream stream) {
	
		fr.univnantes.lina.uima.mapper.rules.JAXB.Rules rules = null;

		// Get a shared resource
		// http://uima.apache.org/downloads/releaseDocs/2.3.0-incubating/docs/html/tutorials_and_users_guides/tutorials_and_users_guides.html#ugr.tug.aae.accessing_external_resource_files
		//InputStream stream = null;
		//	try {
		//	 stream = (InputStream) getContext().getResourceAsStream("rulesKey");
		//} catch (ResourceAccessException e) {
		//	throw new ResourceInitializationException(e);
		//	}
		
		// Unmarshal
		// http://java.sun.com/developer/technicalArticles/WebServices/jaxb/#unmars
		JAXBContext jc = null;
		try {
			 jc = JAXBContext.newInstance("fr.univnantes.lina.uima.mapper.rules.JAXB");
			 Unmarshaller unmarshaller = jc.createUnmarshaller();
			 rules = (fr.univnantes.lina.uima.mapper.rules.JAXB.Rules)
			    unmarshaller.unmarshal(stream);
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//System.out.println("Debug: rules.getRule().get(0).getId()=" +rules.getRule().get(0).getId());
		
		return  rules;
	}

	/** Singleton de la classe courante */ 
	//public  LinkedList<Rule> getRules() { return rules; }

}
