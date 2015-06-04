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
package workflow.sandbox;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;


import org.apache.uima.UIMAFramework;
import org.apache.uima.analysis_engine.AnalysisEngine;
import org.apache.uima.analysis_engine.AnalysisEngineDescription;
import org.apache.uima.analysis_engine.metadata.FixedFlow;
import org.apache.uima.cas.CAS;
import org.apache.uima.collection.CollectionProcessingManager;
import org.apache.uima.collection.CollectionReader;
import org.apache.uima.collection.EntityProcessStatus;
import org.apache.uima.collection.StatusCallbackListener;
import org.apache.uima.resource.ResourceConfigurationException;
import org.apache.uima.resource.ResourceSpecifier;



/**
 * Build and run a CPM from a given CR and a list of AE/CC automatically aggregated
 * 
 * @author Nicolas Hernandez (at univ-nantes.fr)
 * based on 
 * http://uima.apache.org/d/uimaj-2.4.2/tutorials_and_users_guides.html#ugr.tug.application.using_aes
 * http://svn.apache.org/viewvc/uima/uimaj/trunk/uimaj-tools/src/main/java/org/apache/uima/tools/docanalyzer/DocumentAnalyzer.java?view=markup
 * @deprecated
 */
public class BuildAndRunCPMCLI implements StatusCallbackListener{

	final static String whitespaceTokenizerAEName = "whitespaceTokenizer";
	final static String whitespaceTokenizerAEDPath =  "desc/apacheUimaAddons/WhitespaceTokenizer.xml"; 	 //"/home/hernandez/applications/apache-uima-addons-2.3.1-bin/annotator/WhitespaceTokenizer/desc/WhitespaceTokenizer.xml"; 		

	final static String hmmPOSTaggerAEName = "hmmPOSTagger";
	final static String hmmPOSTaggerAEDPath = "desc/apacheUimaAddons/HmmTagger.xml"; //	"desc/apacheUimaAddons/HmmTaggerAggregate.xml"; 

	final static String xmiWriterCCName = "xmiWriter";
	final static String xmiWriterCCDPath = "desc/apacheUimaExamples/cas_consumer/XmiWriterCasConsumer.xml"; 

	final static String fileSystemCRDPath = "desc/apacheUimaExamples/collection_reader/FileSystemCollectionReader.xml";
	final static String tikaFileSystemCRDPath = "desc/wf/tika/FileSystemCollectionReader.xml";

	// textfr
	final static String doc1 = "Jules Verne, né le 8 février 1828 à Nantes en France et mort le 24 mars 1905 à Amiens en France, est un écrivain français dont une grande partie des œuvres sont consacrées à des romans d'aventures et de science-fiction (ou d'anticipation). \n\n" + 
			"En 1863 paraît chez l'éditeur Pierre-Jules Hetzel (1814-1886) son premier roman Cinq semaines en ballon qui connaît un immense succès, au-delà des frontières françaises. Lié à l'éditeur par un contrat de vingt ans, Jules Verne travaillera en fait pendant quarante ans à ses Voyages extraordinaires qui compteront 68 volumes et paraîtront pour une partie d'entre eux dans le Magasin d'éducation et de récréation destiné à la jeunesse. Richement documentés, les romans de Jules Verne se situent aussi bien dans le présent technologique de la deuxième moitié du xixe siècle (Les Enfants du capitaine Grant (1868), Le Tour du monde en quatre-vingts jours (1873), Michel Strogoff (1876), L'Étoile du sud (1884), etc.) que dans un monde imaginaire (De la Terre à la Lune (1865), Vingt mille lieues sous les mers (1870), Robur le conquérant (1886), etc.)\n\n" +
			"L’œuvre de Jules Verne est populaire dans le monde entier et, selon l’Index Translationum, avec un total de 4 223 traductions, il vient au deuxième rang des auteurs les plus traduits en langue étrangère après Agatha Christie. Il est ainsi en 2011 l'auteur de langue française le plus traduit dans le monde. L'année 2005 a été déclarée « année Jules Verne », à l'occasion du centenaire de la mort de l'auteur.";

	// txt en
	final static String doc2 = "Jules Gabriel Verne (French pronunciation: [ʒyl vɛʁn]; February 8, 1828 –\n"+  
			"March 24, 1905) was a French author from Brittany (French: Bretagne) who \n"+  
			"pioneered the science-fiction genre.[1] He is best known for novels such as \n"+  
			"Twenty Thousand Leagues Under the Sea (1870), A Journey to the Center of the\n"+  
			"Earth (1864), and Around the World in Eighty Days (1873). Verne wrote about \n"+  
			"space, air, and underwater travel before air travel and practical submarines \n"+  
			"were invented, and before practical means of space travel had been devised. \n"+  
			"He is the third most translated individual author in the world, according to \n"+  
			"Index Translationum. Some of his books have also been made into films. Verne, \n"+  
			"along with Hugo Gernsback and H. G. Wells, is often popularly referred to as \n"+  
			"the \"Father of Science Fiction\".";


	final static String fileSystemOutputDir = "data/output";

	private static CollectionProcessingManager aCPM;

	private static int numDocsProcessed = 0;

	private static String aCRDPath = "";
	private static List<String> aAEDPathList = new ArrayList<String>();
	private static List<String> aCCDPathList = new ArrayList<String>();
	private static Map<String,Map<String,String>> parameterConfigProcessingElementMap = new HashMap<String,Map<String,String>>();

	private static String aggregateName = "Aggregate Analysis Engine";

	/**
	 * 
	 */
	private static void help() {

		System.out.println("Usage: java BuildAndRunCPMCLI "
				+"[-cr collectionReaderDescriptor [-p name=value]*] "
				+"[-ae analysisEngineDescriptor [-p name=value]*]+ "
				+"[-cc casConsumerDescriptor [-p name=value]*]*");

		System.out.println("Example:" +
				"./bin/buildAndRunCPMCLI+.sh -cp addons/annotator/WhitespaceTokenizer/lib/uima-an-wst.jar:addons/annotator/Tagger/lib/uima-an-tagger.jar "
				+"\n\t-cr examples/descriptors/collection_reader/FileSystemCollectionReader.xml  "
				+"\n\t\t-p InputDirectory=examples/data "
				+"\n\t-ae addons/annotator/WhitespaceTokenizer/desc/WhitespaceTokenizer.xml "
				+"\n\t-ae addons/annotator/Tagger/desc/HmmTagger.xml "
				+"\n\t-cc examples/descriptors/cas_consumer/XmiWriterCasConsumer.xml "
				+"\n\t\t-p OutputDirectory=data/output (the default one is temp-uima-output)"
				+"\n\nor\n"
				+"\n\t-cr addons/annotator/TikaAnnotator/desc/FileSystemCollectionReader.xml"
				+"\n\t\t-p InputDirectory=examples/data/xml "
				+"\n\t\t-p MIME=application/xml (alternative application/pdf)"
				+"\n\t\t-p tikaConfigFile=addons/annotator/TikaAnnotator/desc/tika_config.xml "
				+"\n\t-cc examples/descriptors/cas_consumer/XmiWriterCasConsumer.xml "
				+"\n\t\t-p OutputDirectory=data/output ");
		
		
			
	}

	/**
	 * 
	 */
	private static void error(String message) {
		System.out.println("Error: "+message);
		help();
	}


	/**
	 * @param args
	 */
	public static void main(String[] args) {

		Map<String,String> processingElementParametersMap = null;

		// to associate a parameter definition to a processing element
		String lastProcessingElementDescriptor = "";

		Boolean isCRParam = false;
		Boolean isAEParam = false;

		for (int i= 0 ; i < args.length ; i++) {
			if (args[i].equalsIgnoreCase("-cr")) {
				aCRDPath =  args[++i];
				processingElementParametersMap = new HashMap<String,String>();
				parameterConfigProcessingElementMap.put(aCRDPath,processingElementParametersMap);
				lastProcessingElementDescriptor = aCRDPath;
				isCRParam = true;
			}
			else if (args[i].equalsIgnoreCase("-ae")) {
				String aedPath = args[++i];
				aAEDPathList.add(aedPath);
				processingElementParametersMap = new HashMap<String,String>();
				parameterConfigProcessingElementMap.put(aedPath,processingElementParametersMap);
				lastProcessingElementDescriptor = aedPath;
				isAEParam = true;
			}
			else if (args[i].equalsIgnoreCase("-cc")) {
				String ccdPath = args[++i];
				aCCDPathList.add(ccdPath);
				processingElementParametersMap = new HashMap<String,String>();
				parameterConfigProcessingElementMap.put(ccdPath,processingElementParametersMap);
				lastProcessingElementDescriptor = ccdPath;
				isAEParam = true;
			}
			else if (args[i].equalsIgnoreCase("-p")) {
				String parameter[]=args[++i].split("=");
				(parameterConfigProcessingElementMap.get(lastProcessingElementDescriptor)).put(parameter[0], parameter[1]);
				//TODO ? 
			}
			else if (args[i].equalsIgnoreCase("-h")) {
				help();
				System.exit(1);
			}
			else {
				error("Wrong parameters");
				System.exit(1);
			}
		}
		// Check parameters
		if (!isCRParam) {
			error("Wrong parameters");
			System.exit(1);
		}

		BuildAndRunCPMCLI aBuildAndRunCPM = new BuildAndRunCPMCLI();
		aBuildAndRunCPM.buildAndRunCPM();

	}

	/**
	 * 
	 * @param ars
	 * @return
	 */
	private String getProcessingElementName (ResourceSpecifier ars) {
		int begin = ars.getSourceUrlString().lastIndexOf("/") +1;
		int end = ars.getSourceUrlString().lastIndexOf(".") ;
		if (begin > end) end  = ars.getSourceUrlString().length();
		//System.out.println("Debug: whitespaceTokenizerResourceSpecifier.getSourceUrlString "+ars.getSourceUrlString().substring(begin,end));
		return ars.getSourceUrlString().substring(begin,end);
	}

	/**
	 * Build and run a CPM
	 */
	private  void buildAndRunCPM() {
		List<ResourceSpecifier> resourceSpecifierList = new ArrayList<ResourceSpecifier>();

		// make a list of the resource specifiers from each ae and cc descriptor path,
		// set the possible configuration parameter 
		for (String aAEDPath : aAEDPathList) {
			ResourceSpecifier aResourceSpecifier = CPMUtilities.getResourceSpecifier(aAEDPath);
			resourceSpecifierList.add(aResourceSpecifier);
			if (!parameterConfigProcessingElementMap.get(aAEDPath).isEmpty()) {
				Set<String> parameterKeySet = parameterConfigProcessingElementMap.get(aAEDPath).keySet();
				for (String key : parameterKeySet) {
					CPMUtilities.setConfigurationParameter(aResourceSpecifier,key, parameterConfigProcessingElementMap.get(aAEDPath).get(key));
				}
			}
		}
		for (String aCCDPath : aCCDPathList) {
			ResourceSpecifier aResourceSpecifier = CPMUtilities.getResourceSpecifier(aCCDPath);
			resourceSpecifierList.add(aResourceSpecifier);
			if (!parameterConfigProcessingElementMap.get(aCCDPath).isEmpty()) {
				Set<String> parameterKeySet = parameterConfigProcessingElementMap.get(aCCDPath).keySet();
				for (String key : parameterKeySet) {
					CPMUtilities.setConfigurationParameter(aResourceSpecifier,key, parameterConfigProcessingElementMap.get(aCCDPath).get(key));
				} 
			}
		}

		// create an aggregate AE that includes the user's AE descriptor, 
		// followed by the XMI Writer CAS Consumer, using fixed flow.
		// We use an aggregate AE here, rather than just adding the CAS Consumer to the CPE, so 
		// that we can support the user's AE being a CAS Multiplier and we can specify sofa mappings.
		AnalysisEngineDescription aggregateAED = UIMAFramework.getResourceSpecifierFactory()
				.createAnalysisEngineDescription();
		aggregateAED.setPrimitive(false);
		String[] processingElementNameStringArray = new String[resourceSpecifierList.size()];
		int i = 0;
		for (ResourceSpecifier aRS : resourceSpecifierList) {
			String processingElementName = getProcessingElementName(aRS);
			aggregateAED.getDelegateAnalysisEngineSpecifiersWithImports().put(processingElementName, aRS);
			processingElementNameStringArray[i++] = processingElementName;
		}

		// Flow
		FixedFlow flow = UIMAFramework.getResourceSpecifierFactory().createFixedFlow();      
		flow.setFixedFlow(processingElementNameStringArray);          
		//flow.setFixedFlow(new String[] { whitespaceTokenizerAEName, hmmPOSTaggerAEName, xmiWriterCCName });          

		aggregateAED.getAnalysisEngineMetaData().setName(aggregateName);
		aggregateAED.getAnalysisEngineMetaData().setFlowConstraints(flow);
		aggregateAED.getAnalysisEngineMetaData().getOperationalProperties().setMultipleDeploymentAllowed(
				false);  

		// create CPM instance that will drive processing
		aCPM = UIMAFramework.newCollectionProcessingManager();

		// initialize the cr, ae, cc
		// instantiate AE
		// keep this a local variable - so it doesn't hang on to the ae object
		//   preventing gc (some ae objects are huge)
		AnalysisEngine aggregateAE = CPMUtilities.produceAnalysisEngine(aggregateAED);
			//TODO clean up
		//		ResourceSpecifier fileSystemCRResourceSpecifier = getResourceSpecifier(fileSystemCRDPath);tikaFileSystemCRDPath
		ResourceSpecifier crResourceSpecifier = CPMUtilities.getResourceSpecifier(aCRDPath);
		// set the possible configuration parameter 
		if (!parameterConfigProcessingElementMap.get(aCRDPath).isEmpty()) {
			Set<String> parameterKeySet = parameterConfigProcessingElementMap.get(aCRDPath).keySet();
			for (String key : parameterKeySet) {
				CPMUtilities.setConfigurationParameter(crResourceSpecifier,key, parameterConfigProcessingElementMap.get(aCRDPath).get(key));
			} 
		}
		//		setConfigurationParameter(fileSystemCRResourceSpecifier,"InputDirectory", fileSystemInputDir);
		//		setConfigurationParameter(fileSystemCRResourceSpecifier,"MIME", "text/plain");
		// application/xml application/pdf text/html
		//setConfigurationParameter(crResourceSpecifier,"InputDirectory", "/home/hernandez/workspace/data/samples/doc/fr.pdf");
		//setConfigurationParameter(crResourceSpecifier,"MIME", "application/pdf");

		//setConfigurationParameter(crResourceSpecifier,"tikaConfigFile", "desc/wf/tika/tika_config.xml");

		CollectionReader aCR = CPMUtilities.produceCollectionReader(crResourceSpecifier);

		// set the CPM
		try {
			aCPM.setAnalysisEngine(aggregateAE);
		} catch (ResourceConfigurationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		// register callback listener
		aCPM.addStatusCallbackListener(BuildAndRunCPMCLI.this);

		// Process (in separate thread)
		CPMUtilities.processCPM(aCPM, aCR);

	}


	/**
	 * called when the cpm end thanks to "implements StatusCallbackListener" + 
	 * 		// register callback listener
		aCPM.addStatusCallbackListener(BuildAndRunCPMCLI.this);
	 */
	public void collectionProcessComplete() {

		System.out.println("Number of processed documents: " + numDocsProcessed + "");
		System.out.println("CPM Performance Report "+aCPM.getPerformanceReport().toString());

		// if everything works, output performance stats and print them to a
		// pane. Allow users to open generated files.
		//    showAnalysisResults(new AnalysisEnginePerformanceReports(mCPM.getPerformanceReport()),          outputDirectory);
	}

	@Override
	public void aborted() {
	}

	@Override
	public void batchProcessComplete() {

	}

	@Override
	public void initializationComplete() {
	}

	@Override
	public void paused() {
	}

	@Override
	public void resumed() {
	}

	/**
	 * called after each document process ending thanks to "implements StatusCallbackListener"
	 */
	@Override
	public void entityProcessComplete(CAS cas, EntityProcessStatus aStatus) {
		// if an error occurred, display error
		if (aStatus.isException()) {
			System.err.println("Error: entityProcessComplete"+ aStatus.toString());
			// CPM will stop itself on error, we don't need to call
			// mCPM.stop(). In fact it causes a hang to do so, since
			// this code is callback code is executing within a CPM thread.
		}
		// increment the number of documents processed 
		numDocsProcessed++;
	}
}
