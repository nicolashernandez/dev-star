package fr.univnantes.lina.uima.connectors.xml;

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

import java.util.Locale;
import java.util.Set;
import java.util.Vector;

import org.apache.uima.UimaContext;
import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.cas.CAS;
import org.apache.uima.cas.CASException;
import org.apache.uima.cas.FSIterator;
import org.apache.uima.examples.SourceDocumentInformation;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.tcas.Annotation;
import org.apache.uima.resource.ResourceInitializationException;
import org.apache.uima.resource.ResourceProcessException;
import org.apache.uima.util.CasToInlineXml;

import fr.univnantes.lina.javautil.DateUtilities;
import fr.univnantes.lina.uima.common.ae.CommonAE;
import fr.univnantes.lina.uima.common.cas.AnnotationUtils;
import fr.univnantes.lina.uima.common.cas.DocumentAnnotationUtils;
import fr.univnantes.lina.uima.common.cas.UIMAUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;



/**
 * A simple Analysis Engine that generates inline XML and writes it to a file. 
 * UTF-8 encoding is used.
 * <p>
 * This AE takes two parameters:
 * <ul>
 * <li><code>InputView</code> - view to process. Optional, by default 
 * <code>_InitialView</code></li>
 * <li><code>OutputDirectory</code> - path to directory into which output 
 * files will be written</li>
 * </ul>
 * 
 * 
 */
public class CAS2InlineXMLAE extends CommonAE {

	/*
	 * Properties param and default values 
	 */
	/**
	 * Name of configuration parameter that must be set to the path of a directory into which the
	 * output files will be written.
	 */
	public static final String PARAM_OUTPUTDIR = "OutputDirectory";

	/**
	 * Name of configuration parameter that could be set to the file extension
	 */
	public static final String PARAM_FILEEXT = "FileExtension";

	/*
	 * Component specificities
	 */
	private File mOutputDir;
	
	private String fileExtensionName;

	private CasToInlineXml cas2xml;

	private int mDocNum;

	/*
	 * Accessors
	 */


	/*
	 * Methods 
	 */

	/**
	 * Get the parameter values
	 */
	public void initialize(UimaContext context) throws ResourceInitializationException {

		// super
		super.initialize(context);

		// current AE parameter
		//mDocNum = 0;
		try {
			mOutputDir = new File(((String) context.getConfigParameterValue(PARAM_OUTPUTDIR)).trim());
			if (!mOutputDir.exists()) {
				mOutputDir.mkdirs();
			}
			cas2xml = new CasToInlineXml();

		} catch (Exception e) {
			throw new ResourceInitializationException(e);
		}
		
		 fileExtensionName = ((String) context.getConfigParameterValue(PARAM_FILEEXT));
		
		
	}

	/**
	 * Process input view
	 * Processes the CAS which was populated by the TextAnalysisEngines. <br>
	 * In this case, the CAS is converted to XML and written into the output file .
	 */
	@Override
	public String processInputView(JCas inputViewJCas_,
			FSIterator<Annotation> contextAnnotationsFSIter,
			Set<String> inputAnnotationSet, String inputFeatureString,
			JCas outputViewJCas, String outputAnnotationString,
			String ouputFeatureString) throws AnalysisEngineProcessException {
		

		// CAS 2 JCAS
		//JCas inputViewJCas;
		//try {
		//	inputViewJCas = aCAS.getJCas();
		//} catch (CASException e) {
		//	throw new ResourceProcessException(e);
		//}
		//JCAS 2 CAS
		CAS aCAS = inputViewJCas_.getCas();

		File outFile = null;

		// retrieve the filename of the input file from the CAS
		String inFileName = DocumentAnnotationUtils.retrieveSourceDocumentFileName(inputViewJCas_);
		String outFileName  = inFileName;
		if (outFileName == null) {
			outFileName = DateUtilities.buildARandomStringName(this);
		}
		//try {
		//outFile = new File(mOutputDir, outFileName + fileExtensionName);
		outFile = new File(mOutputDir, outFileName );

		//} catch (MalformedURLException e) {
		//	String errmsg = "Error: MalformedURLException !";
		//	throw new AnalysisEngineProcessException(errmsg,
		//			new Object[] { },e);				
		//}

		// convert CAS to xml format and write to output file in UTF-8
		try {
			String xmlAnnotations = cas2xml.generateXML(aCAS);
			FileOutputStream outStream = new FileOutputStream(outFile);
			outStream.write(xmlAnnotations.getBytes("UTF-8"));
			outStream.close();
		} catch (CASException e) {
			throw new AnalysisEngineProcessException(e);
		} catch (IOException e) {
			throw new AnalysisEngineProcessException(e);
		}

		// La méthode requiert de retourner quelque chose 
		// Le plus simple est de lui retourner l'image d'elle même
		return inputViewJCas_.getSofaDataString();
	}

}




