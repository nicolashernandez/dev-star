/** 
 * UIMA Connectors
 * Copyright (C) 2010-12  Nicolas Hernandez
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

package common.types.ext;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.cas.FSArray;
import org.apache.uima.jcas.tcas.Annotation;
import org.xml.sax.Attributes;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;


import fr.univnantes.lina.uima.connectors.types.XMLAttributeAnnotation;
import fr.univnantes.lina.uima.connectors.types.XMLElementAnnotation;


/**
 * Class qui implémente le parser SAX
 *   * Detag
 *   * Création d'annotations aux offsets des characters
 *   
 * @author hernandez
 *
 */
public  class XMLSaxHandler extends DefaultHandler {
	private Boolean debug = false;
	/**
	 * Variables correspondant aux paramètres
	 */
	/** tag particulier dont il faut traiter le contenu textuel
	 * Fonctionne de pair avec insideText
	 * est à true par défaut ou pour l'annotation dont il faut 
	 * traiter le contenu textuel si celle ci est précisée*/
	private String mXmlTagContainingText = null;
	private boolean insideTextTag;
	private HashMap<String, Integer> XmlTagsToTurnIntoAnnotationHashMap;

	/** 
	 * Stockage du contenu textuel des éléments XML construit à l'aide d'append sur character
	 */
	private StringBuffer detaggedText = new StringBuffer();
	private List<XMLElement> elementOffsetStack = new ArrayList<XMLElement>();

	/**
	 * Curseur de la pile d'éléments en cours
	 */
	private int elementOffsetStackCursor = -1;
	/**
	 * Nombre d'éléments dont il faut spécifier le begin
	 */
	private int currentOpenElementWithTheBeginToSet = -1;

	// Du fait de certaines limitations techniques de sax
	// On a choisi de poser les annotations sur le plainText cad le untagged, on a donc plus besoin de XMLDoc begin/end
	//	private int xmlDocBeginCursor = -1;
	//private int xmlDocEndCursor = -1;
	private int currentAnnotationBeginOffsetInDetaggedText = 0;
	private int currentAnnotationEndOffsetInDetaggedText = 0;
	private JCas aJCas = null;




	/**
	 * @return the aJCas
	 */
	protected JCas getaJCas() {
		return aJCas;
	}

	/**
	 * @param aJCas the aJCas to set
	 */
	protected void setaJCas(JCas aJCas) {
		this.aJCas = aJCas;
	}

	/**
	 * Spécifie si l'on tente de positionner le texte relativement aux positions 
	 * des balises dans le XMLDoc
	 * Le problème est que lorsque character retourne un saut de ligne le start 
	 * du character rapporté est initialisé à 0 et l'on perd la position 
	 * relative au text source (le XMLDoc)
	 * Le true est donc à proscrire !!!
	 */
	private Boolean setPlainTextCursorsRelativelyToXmlTagCursors = false;


	/**
	 * Constructeur
	 * Initialise le Handler
	 * 
	 * @param aJCas
	 * @param mXmlTagContainingText
	 * @param XmlTagsToTurnIntoAnnotationHashMap
	 */
	public XMLSaxHandler(JCas aJCas, String mXmlTagContainingText, HashMap XmlTagsToTurnIntoAnnotationHashMap) {
		this.setaJCas(aJCas);
		this.mXmlTagContainingText = mXmlTagContainingText;
		this.XmlTagsToTurnIntoAnnotationHashMap = XmlTagsToTurnIntoAnnotationHashMap;

		// Si l'on n'a pas spécifié de tag particulier dont il faut traiter le contenu textuel 
		// alors on traite tous les tags
		// par défaut insideTextTag devient true
		insideTextTag = (mXmlTagContainingText == null);

	}

	/**
	 * Début de document
	 */
	public void startDocument() throws SAXException {
		if (debug) {
			System.out.println ("------------------------------------------------------------------------------------------");
			System.out.println ("Debug: startDocument");
		}
	}


	/**
	 * Start Element
	 * localName - The local name (without prefix), or the empty string if Namespace processing is not being performed.
	 * qName - The qualified name (with prefix), or the empty string if qualified names are not available.
	 */
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {

		// On débute une balise dont on veut le contenu textuel
		if (qName.equalsIgnoreCase(mXmlTagContainingText)) {
			insideTextTag = true;
		}

		if (debug) {
			for (int indent=0 ; indent < elementOffsetStackCursor +1; indent++) {System.out.print ("--");}
			System.out.print ("Debug: startElement >"+qName+"< ");
			for (int indent=0 ; indent < attributes.getLength() ; indent++) {System.out.print (">"+attributes.getLocalName(indent)+"=\""+attributes.getValue(indent)+"\"< " );}
			System.out.println ();
		}

		// So far, I do not why but accessing here to the attributes values 
		// prevent the case that further in the createAnnotation method, 
		// you obtain a substring of attributes.toString which does not correspond to an attribute value  
		// someway the current solution acts as a clone...
		for (int indent=0 ; indent < attributes.getLength() ; indent++) {
			attributes.getValue(indent);
			//if (attributes.getValue(indent).startsWith("mph=\""))		{ 
			//	System.out.println ("Debug: startElement >"+attributes.getLocalName(indent)+"=\""+attributes.getValue(indent)+"\"< " );
			//}
			//System.out.print (">"+attributes.getLocalName(indent)+"=\""+attributes.getValue(indent)+"\"< " );
		}

		// on stocke des informations concernant l'élément XML courant en l'empilant
		// le problème est que l'on n'a pas accès au begin et end offset d'une balise en même temps 
		// et qu'il faut garder une trace des begins des balises ouvertes lorsque l'on rencontre leur end 
		//if ( attributes.getLength() == 0) attributes = null;
		XMLElement xE = new XMLElement(uri, localName, qName, attributes);
		elementOffsetStack.add(xE);

		elementOffsetStackCursor++;
		currentOpenElementWithTheBeginToSet++;

		// debug
		if (debug) {
			for (int indent=0 ; indent < elementOffsetStackCursor ; indent++) {System.out.print ("--");}
			//System.out.println ("Debug: startElement empile uri>" +uri+"< localName>"+localName+"< qName>"+qName+"< arrayListIndex>"+arrayListIndex+" curentOpenTagToAnnotate>"+curentOpenTagToAnnotate+"<");
			System.out.println ("Debug: startElement tag>"+qName+"< que l'on empile ; il y a >"+
			elementOffsetStackCursor+"< éléments dans la pile ; il y a >"+
					currentOpenElementWithTheBeginToSet+"< balise en attente dont le begin est définir");

		}
	}

	/**
	 *  fin d'élément
	 */
	public void endElement(String uri, String localName, String qName) throws SAXException {
		// debug
		if (debug) {
			for (int indent=0 ; indent < elementOffsetStackCursor ; indent++) {System.out.print ("--");}
			System.out.println ("Debug: endElement tag>"+qName+"<");
		}

		// on quitte la balise qui nous intéressait
		if (qName.equalsIgnoreCase(mXmlTagContainingText)) {
			insideTextTag = false;
		}
		// dépile l'élément au sommet (normalement l'élément ouvrant correspondant)
		XMLElement xE = elementOffsetStack.get(elementOffsetStackCursor);
		elementOffsetStack.remove(elementOffsetStackCursor);
		elementOffsetStackCursor--; // elementOffsetStack.get(elementOffsetStackCursor) is then the direct parent


		// y affecte la fin courante
		// xE.setEnd(xmlDocEndCursor); //xmlDoc
		xE.setEnd(currentAnnotationEndOffsetInDetaggedText); 

		// building the features of the annotation to create
		if (debug) {
			for (int indent=0 ; indent < elementOffsetStackCursor ; indent++) {System.out.print ("--");}
			System.out.println("Debug: endElement depile, setCurrentEnd et on 'apprête à créer l' annotation");
			//for (int indent=0 ; indent < elementOffsetStackCursor ; indent++) {System.out.print ("--");}
			//System.out.print("Debug: endElement attributes: ");
			//for (int a = 0 ; a < xE.getAttributes().getLength() ; a++) 
			//	System.out.print(xE.getAttributes().getLocalName(a)+"=\""+xE.getAttributes().getValue(a)+"\" ");
			//System.out.println("");

			for (int indent=0 ; indent < elementOffsetStackCursor ; indent++) {System.out.print ("--");}
			System.out.print("Debug: endElement simpleAttributeList: ");
			for (int a = 0 ; a < xE.getSimpleAttributeList().size() ; a++) 
				System.out.print(xE.getSimpleAttributeList().get(a).getQualifiedName() +"=\""+xE.getSimpleAttributeList().get(a).getValue()+"\" ");
			System.out.println("");

		}

		// creating the annotation
		// by default (if no XmlTagsToTurnIntoAnnotation has been set) then any annotation are created
		if (XmlTagsToTurnIntoAnnotationHashMap.containsKey(xE.qualifiedName) || XmlTagsToTurnIntoAnnotationHashMap.isEmpty()) {

			//System.err.println("Debug: elementOffsetStackCursor "+ elementOffsetStackCursor);

			createAnnotation (xE, elementOffsetStackCursor != -1 ? elementOffsetStack.get(elementOffsetStackCursor) : null);
			if (debug) System.out.println("Debug: createAnnotation>"+xE.getQualifiedName()+"< begin>"+xE.getBegin() +"< end>" + xE.getEnd());

			// add the current annotation to the parent
			//	elementOffsetStack.get(elementOffsetStackCursor);



		}else {
			if (debug) System.out.println("Debug: do not create annotation >"+xE.getQualifiedName()+"< begin>"+xE.getBegin() +"< end>" + xE.getEnd()+"< because has not a tag name to annotate or because no tag are specified to annotate.");

		}

	}

	/**
	 * on est en présence de characters
	 */
	public void characters(char[] ch, int start, int length) throws SAXException {
		// debug
		if (debug) {
			// affichage de la chaine de caractères ainsi que son début et sa longueur
			for (int indent=0 ; indent < elementOffsetStackCursor ; indent++) System.out.print ("--");
			System.out.print("Debug: characters ch>");
			for (int i =start ; i < start+length  ; i++) {System.out.print(ch[i]);}
			System.out.println("< begin>"+start+"< length>"+length+"<");
		}	

		//System.out.println ("Debug: ch contient >"+ (new String(ch)).trim()+"<");
		// Si l'on ne veut pas le texte relativement au XMLDoc
		// cad si l'on est ok pour supprimer les blancs et notament les sauts de lignes
		//if ((setPlainTextCursorsRelativelyToXmlTagCursors) || (! (new String(ch)).trim().equalsIgnoreCase(""))) {


		// si on se trouve à l'intérieur de la balise que l'on souhaite on ajoute le texte
		// par défaut si aucune balise a été spécifiée cela ajoutera le texte
		if (insideTextTag) {
			detaggedText.append(ch, start, length);
		}

		// on définit le begin et le end du character courant dans la view plainText
		currentAnnotationBeginOffsetInDetaggedText = currentAnnotationEndOffsetInDetaggedText;
		currentAnnotationEndOffsetInDetaggedText += length;


		// on stocke le begin courant dans le XMLDoc
		// seulement utile si l'on veut poser les annotations sur le XMLDoc 
		// (ce n'est pas forcément ce que l'on veut, là on a décidé de travailler sur le plainText)
		// donc on le commente
		// De plus je me demande dans quelle mesure le plainText begin et end ne permettra pas de poser des annotations sur le XMLDoc
		//if (start > xmlDocBeginCursor) {
		//	xmlDocBeginCursor = start;
		//}
		//else {
		//	if (debug) {
		//		for (int indent=0 ; indent < arrayListIndex ; indent++) {System.out.print ("--");}
		//		System.out.println("Debug: characters ERROR currentBegin>"+xmlDocBeginCursor);
		//	}
		//}
		// on stocke la fin courante
		//xmlDocEndCursor = start + length;

		// renseigne le start de toutes les balises ouvrantes rencontrées jusqu'alors
		for (int c= currentOpenElementWithTheBeginToSet ; c >= 0 ; c-- ) {
			// si le début n'est pas renseigné pour l'élément au sommet de la pile alors on le stocke 
			//if (!elementOffsetStack.get(elementOffsetStackCursor - c).isBeginSet()) {

			XMLElement xE = elementOffsetStack.remove(elementOffsetStackCursor -c);
			int currentBegin= xE.getBegin();
			//xE.setBegin (start); // xmlDoc
			// 121002 offset de SENT 3 de lmf300_13000ep.cat.utf8.xml tagged incorrectement annoté ; 
			// peut être du au fait de rencontrer des élements vides w, 
			// start et end se succèdent sans character
			// chercher dans le log debug modifyBegin, SENT
			// patch : on ne modifie pas les balises déjà corrigées
			if (currentBegin == -1) 
				xE.setBegin (currentAnnotationBeginOffsetInDetaggedText);
			elementOffsetStack.add(elementOffsetStackCursor -c, xE);

			if (debug) {
				for (int indent=0 ; indent < elementOffsetStackCursor ; indent++) {System.out.print ("--");}
				//System.out.println ("Debug: characters curentOpenTagToAnnotate>"+curentOpenTagToAnnotate+"< "+XMLElementArrayList.get(arrayListIndex - c).getqName()+" begin is not set; set it ; begin>"+XMLElementArrayList.get(arrayListIndex - c).getBegin()+"< (should be "+start+") currentEnd>"+xmlDocEndCursor+"<");
				//System.out.println ("Debug: characters set begin to name>"+elementOffsetStack.get(elementOffsetStackCursor - c).getqName()+"< begin is not set; set it ; begin>"+elementOffsetStack.get(elementOffsetStackCursor - c).getBegin()+"< (should be "+currentAnnotationBeginOffsetInDetaggedText+") currentEnd>"+elementOffsetStack.get(elementOffsetStackCursor - c).getEnd()+" should be"+currentAnnotationEndOffsetInDetaggedText+"< buffer>"+currentOpenElementWithTheBeginToSet+"< ");
				//System.out.println ("Debug: characters set begin to name>"+elementOffsetStack.get(elementOffsetStackCursor - c).getqName()+"< currentBegin>"+currentBegin+"< newBegin>"+elementOffsetStack.get(elementOffsetStackCursor - c).getBegin()+"< (should be "+currentAnnotationBeginOffsetInDetaggedText+" since we ve just set it) buffer>"+currentOpenElementWithTheBeginToSet+"< ");
				System.out.println ("Debug: characters modifyBegin of>"+elementOffsetStack.get(elementOffsetStackCursor - c).getQualifiedName() 
						+"< from>"+currentBegin
						+" to>"+elementOffsetStack.get(elementOffsetStackCursor - c).getBegin() 
						+"< (should be "+currentAnnotationBeginOffsetInDetaggedText+" since we ve just set it) buffer>"+currentOpenElementWithTheBeginToSet+"< ");
				//System.out.println ("Debug: characters set begin to name>"+elementOffsetStack.get(elementOffsetStackCursor - c).getQualifiedName()+"< newBegin>"+elementOffsetStack.get(elementOffsetStackCursor - c).getBegin()+"< (should be "+currentAnnotationBeginOffsetInDetaggedText+" since we ve just set it) buffer>"+currentOpenElementWithTheBeginToSet+"< ");
			}
			//			}
			// sinon on ne fait rien (on ne change pas des balises déjà renseignées)
			else {

				if (debug) {
					for (int indent=0 ; indent < elementOffsetStackCursor ; indent++) {System.out.print ("--");}
					System.out.println ("Debug: characters curentOpenTagToAnnotate>"+currentOpenElementWithTheBeginToSet+"< "+elementOffsetStack.get(elementOffsetStackCursor - c).getQualifiedName()+" begin is not set; set it ; begin>"+elementOffsetStack.get(elementOffsetStackCursor - c).getBegin()+"< (should be "+currentAnnotationBeginOffsetInDetaggedText+") currentEnd>"+elementOffsetStack.get(elementOffsetStackCursor - c).getEnd()+" should be"+currentAnnotationEndOffsetInDetaggedText+"<");

					//System.out.println ("Debug: characters curentOpenTagToAnnotate>"+curentOpenTagToAnnotate+"< "+XMLElementArrayList.get(arrayListIndex - c).getqName()+" begin is set; begin>"+XMLElementArrayList.get(arrayListIndex-c ).getBegin()+"< currentEnd>"+xmlDocEndCursor+"<");
				}
			}

		}
		//
		currentOpenElementWithTheBeginToSet = -1;

	}
	//	}

	// probablement inutile
	// tentative lorsqu'on essayait de récupérer le begin et end des start end element
	public void setDocumentLocator(Locator l) {
		if (debug) System.out.println("Debug: getSystemId()"+l.getSystemId()+"< getPublicId()>"+l.getPublicId()+"< getColumnNumber>"+l.getColumnNumber()+"< getLineNumber>"+l.getLineNumber()+"<");
	}


	/**
	 * 
	 */
	public void ignorableWhitespace(char[] ch, int start, int length) throws SAXException {
		if (debug) {
			for (int indent=0 ; indent < elementOffsetStackCursor ; indent++) {System.out.print ("--");}
			System.out.println ("Debug: ignorableWhitespace >"+new String(ch)+"< ");
		}

		if (insideTextTag) {
			if (setPlainTextCursorsRelativelyToXmlTagCursors) detaggedText.append(ch, start, length);        
		}
	}

	/**
	 * Return the detagged text
	 * 
	 * @return the detagged text
	 */
	public String getDetaggedText() {
		return detaggedText.toString();
	}




	/**
	 * Représentation d'un élément XML en mémoire dans la pile
	 * 
	 * @author hernandez
	 *
	 */
	class XMLElement {

		private String uri ; 
		private String localName ;
		private String qualifiedName ;
		// problem with handling the attributes in memory, up to the sax class 
		// problem occurs when two elements with a same name are unstacked, the second one access to the attributes of the previous one and not its attribute
		// problem solved by using my own data structure to save the attributes
		// private Attributes attributes = null;
		private List<SimpleAttribute> simpleAttributeList;
		private int begin = -1;
		private int end = -1;
		private List<Annotation> children;

		public XMLElement(String uri, String localName, String qName, Attributes attributes) {
			this.uri = uri;
			this.localName = localName; 
			this.qualifiedName = qName ;
			//this.attributes = attributes;

			this.simpleAttributeList = new ArrayList<SimpleAttribute>();
			for (int a = 0 ; a < attributes.getLength() ; a++) {
				this.simpleAttributeList.add(new SimpleAttribute(attributes.getLocalName(a), attributes.getValue(a)));
				//	System.out.print(attributes.getLocalName(a)+"=\""+attributes.getValue(a)+"\" ");
			}

			this.children = new ArrayList<Annotation>();

		}

		/**
		 * Add a child
		 * @return
		 */
		protected void addChild (Annotation child) {
			this.children.add(child);
		}

		/**
		 * @return the simple attribute list
		 */
		protected List<Annotation> getChildren() {
			return this.children;
		}



		public boolean isBeginSet() {
			return this.begin != -1;
		}

		/**
		 * @return the begin
		 */
		protected int getBegin() {
			return begin;
		}

		/**
		 * @return the end
		 */
		protected int getEnd() {
			return end;
		}

		public void setBegin(int begin) {
			this.begin = begin;
		}

		public void setEnd(int end) {
			this.end = end;
		}

		/**
		 * @return the localName
		 */
		protected String getLocalName() {
			return localName;
		}

		/**
		 * @return the qName
		 */
		protected String getQualifiedName() {
			return qualifiedName;
		}
		/**
		 * @return the uri
		 */
		protected String getUri() {
			return uri;
		}

		/**
		 * @return the attributes
		 */
		//protected Attributes getAttributes() {
		//	return attributes;
		//}

		/**
		 * @return the simple attribute list
		 */
		protected List<SimpleAttribute> getSimpleAttributeList() {
			return this.simpleAttributeList;
		}

	}

	/**
	 * Attribute class
	 * 
	 * Example of attributes definition
	 * xmlns:myns="http://www.w3.org/1999/XSL/Transform" uri=xmlns localname=myns qualifiedname=xmlns:myns
	 * xmlns="http://www.w3.org/1999/XSL/Transform" uri= localname=xmlns qualifiedname=xmlns
	 * myns:localName="value" uri=myns localname=localname qualifiedname=myns:localName
	 * 
	 * @author hernandez
	 *
	 */
	class SimpleAttribute {


		private String uri;
		private String localName ;
		private String qualifiedName ;
		private String value;

		private SimpleAttribute(String qualifiedName, String value) {
			int separatorUriPosition = qualifiedName.indexOf(":");
			if (separatorUriPosition != -1) this.uri = qualifiedName.substring(0, separatorUriPosition);
			else this.uri = null;
			if (separatorUriPosition != -1) this.localName = qualifiedName.substring(separatorUriPosition,qualifiedName.length());
			else this.localName =  qualifiedName;
			this.qualifiedName = qualifiedName ;
			this.value= value;
		}

		/**
		 * @return the name
		 */
		protected String getQualifiedName() {
			return qualifiedName;
		}

		/**
		 * @return the value
		 */
		protected String getValue() {
			return value;
		}

		/**
		 * @return the uri
		 */
		protected String getUri() {
			return uri;
		}

		/**
		 * @return the localName
		 */
		protected String getLocalName() {
			return localName;
		}


	}


	/**
	 * Create a generic Annotation
	 * @param parent TODO
	 * 
	 * 
	 */
	public void createAnnotation(XMLElement xE, XMLElement xParent) {

		//		public void createAnnotation (JCas aJCas, XMLElement xE) {
		XMLElementAnnotation xEAnnot = new XMLElementAnnotation(this.getaJCas());

		if (debug) {
			// Avec le coveredText le substring plante si begin ou end sont négatifs... ce qui est normal donc je ne l'affichage pas
			//		System.out.println("Debug: createAnnotation tag()>"+xE.getqName()+"< begin()>"+xE.getBegin()+"< end()"+xE.getEnd()+"< coveredText>"+this.getaJCas().getDocumentText().substring(xE.getBegin(), xE.getEnd())+"<");

			System.out.println("Debug: createAnnotation tag()>"+xE.getQualifiedName()+"< begin()>"+xE.getBegin()+"< end()"+xE.getEnd()+"<");
		}

		if ((xE.getBegin() == -1) && (xE.getEnd() !=-1)) {
			// nous sommes en présence d'un élément vide
			xE.setBegin(xE.getEnd());
			if (debug) System.out.println("Debug: il s'agit d'un élément vide, on fixe le begin à >"+xE.getEnd()+"<");

		}

		if ((xE.getBegin() != -1) && (xE.getEnd() !=-1)) {
			// si l'on est bien en présence d'une annotation
			// normalement le test devrait toujours est validé mais sait on jamais...

			// Set the offsets
			xEAnnot.setBegin(xE.getBegin());
			xEAnnot.setEnd(xE.getEnd());

			// Set the uri
			xEAnnot.setUri(xE.getUri());

			// Set the local name
			xEAnnot.setLocalName(xE.getLocalName());

			// Set the qualified Name
			xEAnnot.setQualifiedName(xE.getQualifiedName());
			//				xEAnnot.setElementName(xE.getQualifiedName());

			// Create array of attribute names and values as string  
			//StringArray attNameArray = new StringArray(this.getaJCas(), xE.getSimpleAttributeList().size()) ; 
			//StringArray attValueArray = new StringArray(this.getaJCas(), xE.getSimpleAttributeList().size()) ; 


			// Create FSArray of attribute
			//ArrayList<XMLAttributeAnnotation> attributesArrayList = new ArrayList<XMLAttributeAnnotation>();
			//FSArray attributeFSArray = new FSArray(this.getaJCas(), xE.getAttributes().getLength());

			//FSArray attributeFSArray = null;
			//if (xE.getSimpleAttributeList().size()>=1) 
			FSArray	attributeFSArray = new FSArray(this.getaJCas(), xE.getSimpleAttributeList().size());

			//if (debug) {
			//System.out.print ("Debug: createAnnotation ");
			//for (int indent=0 ; indent < xE.getAttributes().getLength() ; indent++) {
			//	if (xE.getAttributes().getValue(indent).startsWith("mph=\"")) System.out.println ("Debug: createAnnotation  >"+xE.getAttributes().getLocalName(indent)+"=\""+xE.getAttributes().getValue(indent)+"\"< " );
			//System.out.print (">"+xE.getAttributes().getLocalName(indent)+"=\""+xE.getAttributes().getValue(indent)+"\"< " );

			//}
			//System.out.println ();
			//}

			// Create attributeString
			//String attributesString = "";

			// Set the attributes
			//for (int a = 0 ; a < xE.getAttributes().getLength() ; a++) {
			for (int a = 0 ; a < xE.getSimpleAttributeList().size() ; a++) {

				// test de TM
				//if (xE.getAttributes().getLocalName(a).equalsIgnoreCase("cat")) {
				//attNameArray.set(a, xE.getSimpleAttributeList().get(a).getQualifiedName());
				//attValueArray.set(a, xE.getSimpleAttributeList().get(a).getValue());
				//attributesString += xE.getSimpleAttributeList().get(a).getQualifiedName() +"=\"" + xE.getSimpleAttributeList().get(a).getValue()+"\","; 
				XMLAttributeAnnotation xAAnnot = new XMLAttributeAnnotation(this.getaJCas());
				xAAnnot.setBegin(xE.getBegin());
				xAAnnot.setEnd(xE.getEnd());

				// Set element (name/annotation) of the attribute
				//xAAnnot.setElementName(xE.getQualifiedName());
				// performed by the parent for this child
				xAAnnot.setElement(xEAnnot);

				// Set attribute qualifiedName, uri and localName
				String qualifiedName =  xE.getSimpleAttributeList().get(a).getQualifiedName();
				String uri =  xE.getSimpleAttributeList().get(a).getUri();
				String localName =  xE.getSimpleAttributeList().get(a).getLocalName();
				//xAAnnot.setAttributeName(xE.getAttributes().getLocalName(a));
				//xAAnnot.setAttributeName(name);
				xAAnnot.setQualifiedName(qualifiedName);
				xAAnnot.setUri(uri);
				xAAnnot.setLocalName(localName);

				// set value
				String value = xE.getSimpleAttributeList().get(a).getValue();
				//xAAnnot.setAttributeValue(value);
				xAAnnot.setValue(value);

				//attributesArrayList.add(xAAnnot);
				// when tested with the FrenchTreeBank this case should never be verified
				// In the startElement method, there is a bit more explanation
				if (debug)  if (value.startsWith("mph=\"")) {
					System.out.println("Debug: createAnnotation before addToIndexes name="+qualifiedName+" value="+value);

				}
				xAAnnot.addToIndexes(this.getaJCas());
				attributeFSArray.set(a, xAAnnot);
				//xAAnnot.
				//}
			}

			//			if (!attributesString.equalsIgnoreCase("")) attributesString = attributesString.substring(0, attributesString.length()-1);
			//			attributesString = "^" +attributesString + "$";
			//			xEAnnot.setAttributesString(attributesString);

			// currently unnecessary since xpath cannot target it 
			//xEAnnot.setAttributeNames(attNameArray);
			//xEAnnot.setAttributeValues(attValueArray);
			xEAnnot.setAttributes(attributeFSArray);

			//System.err.println("Debug: xE.qualifiedName "+ xE.qualifiedName);
			//if (xParent != null) System.err.println("Debug: xParent.qualifiedName "+ xParent.qualifiedName);

			// specify that the current annotation is a child of the parent 
			if (xParent != null) xParent.addChild(xEAnnot);

			// for each child of the current annotation    
			// add it in my FSArray children
			// set its parent feature with me
			FSArray childrenFSArray = new FSArray(aJCas, xE.getChildren().size());
			for (int a = 0 ; a < xE.getChildren().size() ; a++) {
				childrenFSArray.set(a,xE.getChildren().get(a));
				XMLElementAnnotation xEA = (XMLElementAnnotation) xE.getChildren().get(a);
				xEA.setParent(xEAnnot);
			}
			xEAnnot.setChildren(childrenFSArray);

			// add the current created annotation to the index
			xEAnnot.addToIndexes(this.getaJCas()); 

		}
	}	
}