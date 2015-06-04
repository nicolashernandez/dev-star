/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package fr.univnantes.lina.uima.connectors.xml;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Set;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.apache.uima.UimaContext;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.cas.CASException;
import org.apache.uima.cas.CASRuntimeException;
import org.apache.uima.cas.FSIterator;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.TypeSystem;
import org.apache.uima.jcas.JCas;
import org.apache.uima.resource.ResourceInitializationException;
import org.xml.sax.SAXException;

import fr.univnantes.lina.uima.common.ae.CommonAE;


/**
 * A multi-sofa annotator that does XML detagging. Reads XML data from the input Sofa (named
 * "xmlDocument"); this data can be stored in the CAS as a string or array, or it can be a URI to a
 * remote file. The XML is parsed using the JVM's default parser, and the plain-text content is
 * written to a new sofa called "plainTextDocument".
 */
public class XML2CASAE extends CommonAE { //extends JCasAnnotator_ImplBase  { //extends CasAnnotator_ImplBase {
	/*
	 * Name of  configuration parameters
	 */
	
	public static final String PLAIN_TEXT_OUTPUT_VIEW_PARAM_NAME = "PlainTextOutputView";

	 /* that contains the name of an XML tag that appears in
	 * the input file. Only text that falls within this XML tag will be considered part of the
	 * "document" that it is added to the CAS by this CAS Initializer. If not specified, the entire
	 * file will be considered the document.
	 */
	public static final String PARAM_XMLTAG = "XmlTagContainingText";

	public static final String PARAM_XML_TAGS_TO_UNTAG = "XmlTagsToUntag";
	public static final String PARAM_XML_TAGS_TO_TURN_INTO_ANNOTATION = "XmlTagsToTurnIntoAnnotation";

	/*
	 * LOCAL VARIABLES
	 */
	private SAXParserFactory parserFactory = SAXParserFactory.newInstance();

	private Type sourceDocInfoType;

	private String mXmlTagContainingText = null;
	private String[] XmlTagsToUntag = null;
	private HashMap<String, Integer> XmlTagsToTurnIntoAnnotationHashMap = null;

	private String plainTextOutputViewString = null;


	/*
	 *  Default view name if none are specified by the view parameter
	 */
	//private static String DEFAULT_INPUT_VIEW = "_InitialView";

	/*
	 *  Default output view name 
	 */
	private static String DEFAULT_OUTPUT_VIEW = "plainTextDocument";


	public void initialize(UimaContext aContext) throws ResourceInitializationException {
		
		// Get common config param setting
		super.initialize(aContext);
		
		plainTextOutputViewString = (String) aContext
		.getConfigParameterValue(PLAIN_TEXT_OUTPUT_VIEW_PARAM_NAME);
		// assuming that it does not exist a view with the default output view name 
		if (plainTextOutputViewString == null) {
			plainTextOutputViewString  = DEFAULT_OUTPUT_VIEW; 
		}
		
		if (inputViewStringArray.length > 1) {
			System.err.println("Warning: only the first inputView name will be processed !");
		}
		
		//if (inputViewStringArray[0].equalsIgnoreCase(outputViewString)) {
		//	String errmsg = "The OutputView parameter must be set and the InputView and the OutputView names must be different !";
		//	throw new ResourceInitializationException(errmsg, new Object[] {});
		//	// e.printStackTrace();
		//}
		
		// Get local config param setting
		// Get mXmlTagContainingText
		mXmlTagContainingText  = (String) getContext().getConfigParameterValue(PARAM_XMLTAG);

		// Get XmlTagsToUntag
		XmlTagsToUntag = (String[]) aContext.getConfigParameterValue(PARAM_XML_TAGS_TO_UNTAG);

		// Get XmlTagsToTurnIntoAnnotationStringArray
		String[] XmlTagsToTurnIntoAnnotationStringArray = (String[]) aContext.getConfigParameterValue(PARAM_XML_TAGS_TO_TURN_INTO_ANNOTATION);
		XmlTagsToTurnIntoAnnotationHashMap = new HashMap<String, Integer>() ;
		if (XmlTagsToTurnIntoAnnotationStringArray != null)
			for (int i = 0; i < XmlTagsToTurnIntoAnnotationStringArray.length; i++) {
				XmlTagsToTurnIntoAnnotationHashMap.put(XmlTagsToTurnIntoAnnotationStringArray[i],1);
			}
		
		
	}

	public void typeSystemInit(TypeSystem aTypeSystem) throws AnalysisEngineProcessException {
		sourceDocInfoType = aTypeSystem.getType("org.apache.uima.examples.SourceDocumentInformation");
	}

	/**
	 * process each inputViewJCas, parse the xml and produce a plainTextView of it
	 *
	 */
	//	@Override
	protected String processInputView(JCas inputViewJCas,
			FSIterator contextAnnotationsFSIter,
			Set<String> inputAnnotationSet,
			String inputFeatureString, JCas outputViewJCas,
			String outputAnnotationString, String ouputFeatureString)
	throws AnalysisEngineProcessException {
		
		//System.out.println("Debug: class>"+this.getClass().getName()+"< now>" + JavaUtilities.now());

		
		//public void process(JCas aJCas) throws AnalysisEngineProcessException {
		// get handle to CAS view containing XML document
		// nh CAS xmlCas = aCAS.getView("xmlDocument");
		//JCas xmlCas = null;
		//try {
		//	xmlCas = aJCas.getView(DEFAULT_INPUT_VIEW);
		//} catch (CASException e1) {
		// TODO Auto-generated catch block
		//	e1.printStackTrace();
		//}
		//InputStream xmlStream = xmlCas.getSofa().getSofaDataStream();
		InputStream xmlStream = inputViewJCas.getSofa().getSofaDataStream();


		// create the plain text view and set its document text
		JCas plainTextView = null;
		try {
			//plainTextView = aJCas.createView(plainTextOutputViewString);
			plainTextView = inputViewJCas.createView(plainTextOutputViewString);
		} catch (CASException e) {
		 //TODO Auto-generated catch block
			e.printStackTrace();
		}

		// parse with detag handler
		//		
		XMLSaxHandler handler = new XMLSaxHandler (plainTextView, mXmlTagContainingText, XmlTagsToTurnIntoAnnotationHashMap);
		//XMLSaxHandler handler = new XMLSaxHandler(outputViewJCas,mXmlTagContainingText, XmlTagsToTurnIntoAnnotationHashMap);
		//MUCSaxHandler handler = new MUCSaxHandler(plainTextView,mXmlTagContainingText, XmlTagsToTurnIntoAnnotationHashMap);
		//DetagHandler handler = new DetagHandler(xmlCas);

		//try {
		SAXParser parser = null;

		try {
			parser = parserFactory.newSAXParser();
			parserFactory.setValidating(false);
		} catch (ParserConfigurationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SAXException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try {
			//xmlStream.setEncoding("UTF-8");
			parser.parse(xmlStream, handler);
		} catch (SAXException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		//} catch (Exception e) {
		//	throw new AnalysisEngineProcessException(e);
		//}

		plainTextView.setDocumentText(handler.getDetaggedText());
		//outputViewJCas.setDocumentText(handler.getDetaggedText());
		try {
			//plainTextView.setDocumentLanguage(aJCas.getView(DEFAULT_INPUT_VIEW).getDocumentLanguage());
			//outputViewJCas.setDocumentLanguage(inputViewJCas.getDocumentLanguage());
			plainTextView.setDocumentLanguage(inputViewJCas.getDocumentLanguage());

		} catch (CASRuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//catch (CASException e) {
		//	// TODO Auto-generated catch block
		//	e.printStackTrace();
		//}
		//SourceDocumentInformation sdi = new SourceDocumentInformation(plainTextView);
		//sdi.setBegin(0);
		//sdi.setEnd(handler.getDetaggedText().length());
		//	sdi.setDocumentSize((int) xmlfile.length());
		//sdi.setLastSegment(true);
		//sdi.setOffsetInSource(0);
		//sdi.setUri(xmlfile.toURI().toURL().toString()); // ugly but necessary : see XMIWriterCasConsumer implementation
		//sdi.addToIndexes();

		// suite au passage de CAS à JCas, le code suivant ne marche plus  
		// Index the SourceDocumentInformation object, if there is one, in the new sofa.
		// This is needed by the SemanticSearchCasIndexer
		//Iterator iter = xmlCas.getAnnotationIndex(sourceDocInfoType).iterator();
		//if (iter.hasNext()) {
		//	FeatureStructure sourceDocInfoFs = (FeatureStructure) iter.next();
		//	plainTextView.getIndexRepository().addFS(sourceDocInfoFs);
		//}

		// Debug mph
		/* 
		try {
			AnnotationIndex<Annotation> aXMLAttributeAnnotationAnnotationIndex = plainTextView.getAnnotationIndex(JCasSofaViewUtils.getJCasType(plainTextView, "fr.univnantes.lina.uima.connectors.types.XMLAttributeAnnotation"));
			//AnnotationIndex<Annotation> aXMLAttributeAnnotationAnnotationIndex = outputViewJCas.getAnnotationIndex(JCasSofaViewUtils.getJCasType(outputViewJCas, "fr.univnantes.lina.uima.connectors.types.XMLAttributeAnnotation"));
			Iterator aXMLAttributeAnnotationAnnotationIndexIter = aXMLAttributeAnnotationAnnotationIndex.iterator();
			String name = "";
			String value = "";
			while (aXMLAttributeAnnotationAnnotationIndexIter.hasNext()) {
				XMLAttributeAnnotation aXMLAttributeAnnotation = (XMLAttributeAnnotation) aXMLAttributeAnnotationAnnotationIndexIter.next();
				//name = aXMLAttributeAnnotation.getAttributeName();
				//value = aXMLAttributeAnnotation.getAttributeValue();
				name = aXMLAttributeAnnotation.getQualifiedName();
				value = aXMLAttributeAnnotation.getValue();
				if (value.startsWith("mph=\"")) {
					System.out.println("Debug: process name="+name+" value="+value);

				}
			}

		} catch (CASRuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (AnalysisEngineProcessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/

		// La méthode requiert de retourner quelque chose 
		// Le plus simple est de lui retourner l'image d'elle même
		return inputViewJCas.getSofaDataString();

	}



}






