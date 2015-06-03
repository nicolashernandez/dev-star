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

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Comment;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


/**
 * XML Java Utilities
 * 
 * @author hernandez-n
 * Java XPath (Apache JAXP implementation) performance
 * http://stackoverflow.com/questions/8285003/optimizing-dom-and-xpath-java-code
 * http://stackoverflow.com/questions/6340802/java-xpath-apache-jaxp-implementation-performance
 * -Dorg.apache.xml.dtm.DTMManager=org.apache.xml.dtm.ref.DTMManagerDefault

 * * <p>
 * good java dom tutorial: http://www.exampledepot.com/egs/org.w3c.dom/pkg.html 
 * </p>
 */
public class XMLUtilities {


	/**
	 * Read XML File 2 DOM
	 */
	public static Document readXMLFile2Dom (String fileName) {
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		dbf.setValidating(false);
		//dbf.setNamespaceAware(true);

		DocumentBuilder db = null;
		try {
			db = dbf.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Document d = null;
		try {
			d = db.parse(new File(fileName));
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.err.println("Info: Read, "+fileName+", XML File to dom");

		return d;
	}


	/**
	 * Read XML File from a given dir and merged them to DOM
	 */
	public static Document mergeXMLfiles2Dom (String dirPath) {
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		dbf.setValidating(false);
	//	dbf.equals(obj)setEncoding("UTF-8");
		//dbf.setNamespaceAware(true);


		//- 
		//- Charger le second fichier dans un Document document2

		//- Faire pareil avec document2
		//- enregistrer mergedDocument dans un fichier XML, avec XMLOutputter.

		DocumentBuilder db = null;
		try {
			db = dbf.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		//- Créer un Document mergedDocument vide, lui donner un élément racine <merged>
		Document mergedDocument = db.newDocument();
		Element rootElement = mergedDocument.createElement("files");
		mergedDocument.appendChild(rootElement);

		ArrayList<File> filesList = null;
		try {
			filesList = IOUtilities.retrieveFilesFromDirectory(dirPath);
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		for (File f : filesList) {


			Element fileElement = mergedDocument.createElement("file");
			Attr fileAttribute = mergedDocument.createAttribute("id");
			fileAttribute.setValue(f.getName());
			fileElement.setAttributeNode(fileAttribute);
			rootElement.appendChild(fileElement);
			 


			try {
				Document importedDocument = null;
				//- Charger le fichier dans un Document 
				importedDocument = db.parse(f);
				//- Prendre la racine de document1, appeler detach() dessus pour la retirer de document1, et l'ajouter à l'élément merged de mergedDocument.
				
				
				
				NodeList importedDocumentChildNodes = importedDocument.getChildNodes(); 



				for (int i = 0; i < importedDocumentChildNodes.getLength(); i++) {
					Node importedDocumentChildNode = importedDocumentChildNodes.item(i);
					// Exception in thread "main" org.w3c.dom.DOMException: WRONG_DOCUMENT_ERR: A node is used in a different document than the one that created it.
					// fileElement.appendChild(importedDocumentChildNode);
					// fileElement.appendChild(importedDocumentChildNode.cloneNode(true));
					fileElement.appendChild(mergedDocument.importNode(importedDocumentChildNode, true));
					
					//Node newNode = importedDocumentChildNode.cloneNode(true);
					//TODO
				}

				//	Node kid = mergedDocument.importNode(importedNode, true); //importedNode.getFirstChild();
				//kid = importedNode.removeChild(kid);
				//kid =	mergedDocument.importNode(kid, true);
				// fileElement.appendChild(kid);

				//   kid = mergedDocument.importNode(kid, true);
				//  fileElement.appendChild(kid);

				//fileElement.appendChild(importedNode);
				//mergedDocument.importNode(importedNode, true);

			} catch (SAXException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}



		System.err.println("Info: Read, "+dirPath+", XML File to dom");

		return mergedDocument;
	}

	/**
	 *  write the dom content into xml file
	 * @param indent
	 * @param n
	 */
	public static  void writeDOM2XMLFile (Document domDoc, String fileName) {
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = null;
		try {
			transformer = transformerFactory.newTransformer();
		} catch (TransformerConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// no effect ?
		//		transformer.setOutputProperty(OutputKeys.METHOD, "xml");
		//		transformer.setOutputProperty(OutputKeys.ENCODING, "utf-8");
		//		transformer.setOutputProperty(OutputKeys.STANDALONE , "yes");
		//		transformer.setOutputProperty(OutputKeys.INDENT, "yes");

		DOMSource source = new DOMSource(domDoc);
		StreamResult result = new StreamResult(new File(fileName));

		// Output to console for testing
		// StreamResult result = new StreamResult(System.out);

		try {
			transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
			transformer.setOutputProperty(OutputKeys.STANDALONE, "yes");
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.transform(source, result);
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.err.println("Info: dom document saved in file, "+fileName+" !");
	}

	/**
	 * display a dom (sub) tree 
	 * @param n
	 */
	public static void displayDomTree(Node n) {
		displayDomTree ("", n);
	}

	/**
	 * display a dom (sub) tree 
	 * @param indent
	 * @param n
	 */
	public static void displayDomTree(String indent, Node n) {

		// if TEXT NODE
		if (n.getNodeType() == org.w3c.dom.Node.TEXT_NODE) {
			//System.out.print(indent + n.getNodeValue() );
			System.out.print( n.getNodeValue() );

		}
		// if Comment
		else if (n instanceof Comment) {
			System.out.println(indent + "<!--" + n.getNodeValue() + " -->");
		}
		// if CDATASection
		//else if (n instanceof org.w3c.dom.CDATASection) {
		//	System.out.println(indent + "<!--" + n.getNodeValue() + " -->");
		//}
		// if ProcessingInstruction
		//else if (n instanceof ProcessingInstruction) {
		//	System.out.println(indent + "<" + n.getNodeValue() + " >");
		//}
		// if Element
		else if (n instanceof Element) {
			Element e = ((Element) n);

			// Get all the attributes of an element in a map
			NamedNodeMap attrs = e.getAttributes();

			// Get number of attributes in the element
			int numAttrs = attrs.getLength();

			// Process each attribute
			String attrListString = "";
			for (int i=0; i<numAttrs; i++) {
				Attr attr = (Attr)attrs.item(i);

				// Get attribute name and value
				String attrName = attr.getNodeName();
				String attrValue = attr.getNodeValue();
				attrListString += attrName+"=\""+attrValue+"\" ";
			}

			//
			System.out.print(indent + "<" + e.getTagName() +" "+attrListString);

			//
			NodeList fils = n.getChildNodes();
			// Si empty element
			if (fils.getLength() == 0) {
				System.out.print("/>");
			}
			// Si not empty element
			else {
				System.out.print(">");

				for(int i=0; (i < fils.getLength()); i++) {
					displayDomTree(indent + " ", fils.item(i));
				}
				//System.out.println(indent + "</" + e.getTagName() + ">");
				System.out.println("</" + e.getTagName() + ">");
			}
		}
		// if document root
		// Un document XML est encapsulé dans un objet de type Document 
		// qui peut contenir des objets de type Comment, ProcessingInstruction
		// et l'élément racine du document encapsulé dans un objet de type Element.
		else if (n instanceof Document) {

			NodeList fils = n.getChildNodes();
			for(int i=0; (i < fils.getLength()); i++) {
				displayDomTree(indent, fils.item(i));
			}
		}

	}
	
	

}
