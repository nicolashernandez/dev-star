/**
 * 
 */
package workflow.sandbox;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.uima.TokenAnnotation;
import org.apache.uima.UIMAFramework;
import org.apache.uima.analysis_engine.AnalysisEngine;
import org.apache.uima.analysis_engine.AnalysisEngineDescription;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.analysis_engine.metadata.FixedFlow;
import org.apache.uima.cas.CAS;
import org.apache.uima.cas.CASException;
import org.apache.uima.cas.text.AnnotationIndex;
import org.apache.uima.collection.CasConsumer;
import org.apache.uima.collection.CasConsumerDescription;
import org.apache.uima.collection.CollectionProcessingManager;
import org.apache.uima.collection.CollectionReader;
import org.apache.uima.collection.CollectionReaderDescription;
import org.apache.uima.collection.EntityProcessStatus;
import org.apache.uima.collection.StatusCallbackListener;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.tcas.Annotation;
import org.apache.uima.resource.ResourceConfigurationException;
import org.apache.uima.resource.ResourceCreationSpecifier;
import org.apache.uima.resource.ResourceInitializationException;
import org.apache.uima.resource.ResourceProcessException;
import org.apache.uima.resource.ResourceSpecifier;
import org.apache.uima.resource.metadata.ResourceMetaData;
import org.apache.uima.util.CasCreationUtils;
import org.apache.uima.util.InvalidXMLException;
import org.apache.uima.util.XMLInputSource;


/**
 * @author hernandez
 *http://uima.apache.org/d/uimaj-2.4.2/tutorials_and_users_guides.html#ugr.tug.application.using_aes
 */
public class CreateharedCasAndRunAEandCC implements StatusCallbackListener{

	final static String whitespaceTokenizerAEName = "whitespaceTokenizer";
	final static String whitespaceTokenizerAEDPath =  "desc/apacheUimaAddons/WhitespaceTokenizer.xml"; 	 //"/home/hernandez/applications/apache-uima-addons-2.3.1-bin/annotator/WhitespaceTokenizer/desc/WhitespaceTokenizer.xml"; 		

	final static String hmmPOSTaggerAEName = "hmmPOSTagger";
	final static String hmmPOSTaggerAEDPath = "desc/apacheUimaAddons/HmmTagger.xml"; //	"desc/apacheUimaAddons/HmmTaggerAggregate.xml"; 

	final static String xmiWriterCCName = "xmiWriter";
	final static String xmiWriterCCDPath = "desc/apacheUIMAExamples/cas_consumer/XmiWriterCasConsumer.xml"; 

	final static String fileSystemCRDPath = "desc/apacheUIMAExamples/collection_reader/FileSystemCollectionReader.xml";
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

	final static String tokenAnnotationType = "org.apache.uima.TokenAnnotation";

	final static String fileSystemInputDir = "/home/hernandez/workspace/data/samples/corpus/fr.text.utf8";
	final static String fileSystemOutputDir = "data/output";

	private static CollectionProcessingManager aCPM;

	private static int numDocsProcessed = 0;



	/**
	 * @param args
	 */
	public static void main(String[] args) {


		/*
		 * 3.2.1. Instantiating an Analysis Engine
		 * 3.2.2. Analyzing Text Documents
		 */

		// instantiateAEandAnalyseDoc();

		/*
		 * 3.2.6. Using Multiple Analysis Engines and Creating Shared CASes
		 */
		//createdSharedCASCannotRunWiCR();

		/*
		 * 	http://svn.apache.org/viewvc/uima/uimaj/trunk/uimaj-tools/src/main/java/org/apache/uima/tools/docanalyzer/DocumentAnalyzer.java?view=markup
		 */
		CreateharedCasAndRunAEandCC aMyFirstUIMAApplication = new CreateharedCasAndRunAEandCC();
		aMyFirstUIMAApplication.buildAndRunCPM();

	}

	/**
	 * 	http://svn.apache.org/viewvc/uima/uimaj/trunk/uimaj-tools/src/main/java/org/apache/uima/tools/docanalyzer/DocumentAnalyzer.java?view=markup
	 */
	private  void buildAndRunCPM() {

		ResourceSpecifier whitespaceTokenizerResourceSpecifier = getResourceSpecifier(whitespaceTokenizerAEDPath);

		ResourceSpecifier hmmPOSTaggerResourceSpecifier = getResourceSpecifier(hmmPOSTaggerAEDPath);

		ResourceSpecifier xmiWriterResourceSpecifier = getResourceSpecifier(xmiWriterCCDPath);
		//((ResourceMetaData)xmiWriterResourceSpecifier.getAttributeValue("metaData")).getConfigurationParameterSettings().setParameterValue("OutputDirectory", fileSystemOutputDir);
		setConfigurationParameter(xmiWriterResourceSpecifier,"OutputDirectory", fileSystemOutputDir);

		// create an aggregate AE that includes the user's AE descriptor, 
		// followed by the XMI Writer CAS Consumer, using fixed flow.
		// We use an aggregate AE here, rather than just adding the CAS Consumer to the CPE, so 
		// that we can support the user's AE being a CAS Multiplier and we can specify sofa mappings.
		AnalysisEngineDescription aggregateAED = UIMAFramework.getResourceSpecifierFactory()
				.createAnalysisEngineDescription();
		aggregateAED.setPrimitive(false);
		aggregateAED.getDelegateAnalysisEngineSpecifiersWithImports().put(whitespaceTokenizerAEName, whitespaceTokenizerResourceSpecifier);
		aggregateAED.getDelegateAnalysisEngineSpecifiersWithImports().put(hmmPOSTaggerAEName, hmmPOSTaggerResourceSpecifier);
		aggregateAED.getDelegateAnalysisEngineSpecifiersWithImports().put(xmiWriterCCName, xmiWriterResourceSpecifier);
		// Flow
		FixedFlow flow = UIMAFramework.getResourceSpecifierFactory().createFixedFlow();      
		flow.setFixedFlow(new String[] { whitespaceTokenizerAEName, hmmPOSTaggerAEName, xmiWriterCCName });          

		aggregateAED.getAnalysisEngineMetaData().setName("AggregateAEName");
		aggregateAED.getAnalysisEngineMetaData().setFlowConstraints(flow);
		aggregateAED.getAnalysisEngineMetaData().getOperationalProperties().setMultipleDeploymentAllowed(
				false);  

		// create CPM instance that will drive processing
		aCPM = UIMAFramework.newCollectionProcessingManager();

		// initialize the cr, ae, cc
		// instantiate AE
		// keep this a local variable - so it doesn't hang on to the ae object
		//   preventing gc (some ae objects are huge)
		AnalysisEngine aggregateAE = produceAnalysisEngine(aggregateAED);
		
//		ResourceSpecifier fileSystemCRResourceSpecifier = getResourceSpecifier(fileSystemCRDPath);
		ResourceSpecifier fileSystemCRResourceSpecifier = getResourceSpecifier(tikaFileSystemCRDPath);
//		setConfigurationParameter(fileSystemCRResourceSpecifier,"InputDirectory", fileSystemInputDir);
//		setConfigurationParameter(fileSystemCRResourceSpecifier,"MIME", "text/plain");
		// application/xml application/pdf text/html
		setConfigurationParameter(fileSystemCRResourceSpecifier,"InputDirectory", "/home/hernandez/workspace/data/samples/doc/fr.pdf");
		setConfigurationParameter(fileSystemCRResourceSpecifier,"MIME", "application/pdf");

		setConfigurationParameter(fileSystemCRResourceSpecifier,"tikaConfigFile", "desc/wf/tika/tika_config.xml");
		 
		CollectionReader fileSystemCR = produceCollectionReader(fileSystemCRResourceSpecifier);

		// set the CPM
		try {
			aCPM.setAnalysisEngine(aggregateAE);
		} catch (ResourceConfigurationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}


		// register callback listener
		aCPM.addStatusCallbackListener(CreateharedCasAndRunAEandCC.this);

		// Process (in separate thread)
		processCPM(fileSystemCR);

		//

	}

	/**
	 * @param aResourceSpecifier
	 */
	private void setConfigurationParameter(
			ResourceSpecifier aResourceSpecifier, String name, String value) {
		((ResourceMetaData)aResourceSpecifier.getAttributeValue("metaData")).getConfigurationParameterSettings().setParameterValue(name, value);
	}

	/**
	 * @param fileSystemCR
	 */
	private void processCPM(CollectionReader fileSystemCR) {
		try {
			aCPM.process(fileSystemCR);
		} catch (ResourceInitializationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 
	 */
	private static void createdSharedCASAndRunAEandCC() {

		AnalysisEngineDescription whitespaceTokenizerAED = getAnalysisEngineDescription(whitespaceTokenizerAEDPath);
		AnalysisEngineDescription hmmPOSTaggerAED = getAnalysisEngineDescription(hmmPOSTaggerAEDPath);
		CasConsumerDescription xmiWriterCCD = getCasConsumerDescription(xmiWriterCCDPath);

		JCas jcas = createSharedJCas(whitespaceTokenizerAED,hmmPOSTaggerAED,xmiWriterCCD);
		/*
		 * // display the types of the given jcas
		java.util.Iterator<Type> typeIterator =  jcas.getTypeSystem().getTypeIterator();
		while (typeIterator.hasNext()) {
			Type type = typeIterator.next();
			System.out.println("Debug: type>"+type.getName()+"<");
		}*/

		// initialize the cr, ae, cc
		// cr cannot be used because works only wi cpm
		AnalysisEngine whitespaceTokenizerAE = produceAnalysisEngine(whitespaceTokenizerAEDPath);
		AnalysisEngine hmmPOSTaggerAE = produceAnalysisEngine(hmmPOSTaggerAEDPath);
		//	CasConsumer xmiCasConsumerAE = produceCasConsumer(xmiWriterCCDPath);
		ResourceSpecifier xmiWriterResourceSpecifier = getResourceSpecifier(xmiWriterCCDPath);
		((ResourceMetaData)xmiWriterResourceSpecifier.getAttributeValue("metaData")).getConfigurationParameterSettings().setParameterValue("OutputDirectory", fileSystemOutputDir);
		CasConsumer xmiCasConsumerAE = produceCasConsumer(xmiWriterResourceSpecifier);

		// set the content of a jcas
		jcas.setDocumentText(doc2);

		// analyze a document
		processAnalysisEngine(jcas, whitespaceTokenizerAE);
		//displayAnnotationIndex(jcas);
		//displayTokenAnnotation(jcas);
		//jcas.reset();

		processAnalysisEngine(jcas, hmmPOSTaggerAE);

		processCasConsumer(jcas, xmiCasConsumerAE);

		//
		//displayTokenAnnotation(jcas);

		//done
		jcas.reset();
		whitespaceTokenizerAE.destroy();
		hmmPOSTaggerAE.destroy();
		xmiCasConsumerAE.destroy();

	}


	/**
	 * @param jcas
	 * @param ae
	 */
	private static void processCasConsumer(JCas jcas, CasConsumer ae) {
		try {
			ae.processCas(jcas.getCas());
		} catch (ResourceProcessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	

	/**
	 * @param jcas
	 * @param ae
	 */
	private static void processAnalysisEngine(JCas jcas, AnalysisEngine ae) {
		try {
			ae.process(jcas);
		} catch (AnalysisEngineProcessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * create a JCas combining (merging) the type system of several AE
	 * @return JCas
	 */
	private static JCas createSharedJCas(ResourceCreationSpecifier... descriptions) {

		List<ResourceCreationSpecifier> descriptionList = new ArrayList<ResourceCreationSpecifier>();

		for (ResourceCreationSpecifier description : descriptions) {
			descriptionList.add(description);
		}

		// Combine the type systems
		CAS cas = null;
		try {
			cas = CasCreationUtils.createCas(descriptionList);
		} catch (ResourceInitializationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// (optional, if using the JCas interface) 
		JCas jcas = null;
		try {
			jcas = cas.getJCas();
		} catch (CASException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jcas;
	}

	/**
	 * 
	 */
	private static AnalysisEngineDescription getAnalysisEngineDescription(String aedpath) {
		AnalysisEngineDescription aed = null;
		try {
			aed = UIMAFramework.getXMLParser().parseAnalysisEngineDescription(getXMLInputSource(aedpath));
		} catch (InvalidXMLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return aed;
	}


	/**
	 * 
	 */
	private static CollectionReaderDescription getCollectionReaderDescription(String collectionReaderAEDPath) {
		CollectionReaderDescription crDesc = null;
		try {
			crDesc = UIMAFramework.getXMLParser().parseCollectionReaderDescription(getXMLInputSource(collectionReaderAEDPath));
		} catch (InvalidXMLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return crDesc;
	}


	/**
	 * 
	 */
	private static CasConsumerDescription getCasConsumerDescription(String casConsumerAEDPath) {
		CasConsumerDescription crDesc = null;
		try {
			crDesc = UIMAFramework.getXMLParser().parseCasConsumerDescription(getXMLInputSource(casConsumerAEDPath));
		} catch (InvalidXMLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return crDesc;
	}


	/**
	 * 
	 */
	private static void instantiateAEandAnalyseDoc() {
		/*
		 * 3.2.1. Instantiating an Analysis Engine
		 */

		AnalysisEngine whitespaceTokenizerAE = produceAnalysisEngine(whitespaceTokenizerAEDPath);
		AnalysisEngine hmmPOSTaggerAE = produceAnalysisEngine(hmmPOSTaggerAEDPath);


		/*
		 * 3.2.2. Analyzing Text Documents
		 */

		//create a JCas, given an Analysis Engine (ae)
		JCas jcas = null;
		try {
			//jcas = whitespaceTokenizerAE.newJCas(); // impossible since its hmmPOSTagger ts extends whitespaceTokenizer ts.
			jcas = hmmPOSTaggerAE.newJCas();
		} catch (ResourceInitializationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// set the content of the doc to process
		jcas.setDocumentText(doc2);

		// analyze a document
		processAnalysisEngine(jcas, whitespaceTokenizerAE);
		processAnalysisEngine(jcas, hmmPOSTaggerAE);

		// do Something With Results
		displayTokenAnnotation(jcas);

		//done
		jcas.reset();
		whitespaceTokenizerAE.destroy();
		hmmPOSTaggerAE.destroy();

	}


	/**
	 * @param whitespaceTokenizerAEDPath
	 * @return
	 */
	private static CasConsumer produceCasConsumer(
			final String aed) {
		//get Resource Specifier from XML file

		ResourceSpecifier specifier = getResourceSpecifier(aed);

		//create AE here
		CasConsumer ae = produceCasConsumer(specifier);
		return ae;
	}

	/**
	 * @param whitespaceTokenizerAEDPath
	 * @return
	 */
	private static AnalysisEngine produceAnalysisEngine(
			final String aed) {
		//get Resource Specifier from XML file

		ResourceSpecifier specifier = getResourceSpecifier(aed);

		//create AE here
		AnalysisEngine ae = produceAnalysisEngine(specifier);
		return ae;
	}

	/**
	 * @param aed
	 * @return
	 */
	private static CollectionReader produceCollectionReader(
			final String aed) {
		//get Resource Specifier from XML file

		ResourceSpecifier specifier = getResourceSpecifier(aed);

		/*
		for (Object obj :specifier.listAttributes()) {

			//System.out.println("Debug: attribute class>"+obj.getClass().getName()+"<");
			NameClassPair aNameClassPair = (NameClassPair) obj;
			System.out.println("Debug: NameClassPair name>"+aNameClassPair.getName()+"< class>"+aNameClassPair.getClassName()+"<");
	}*/
		/* Debug: NameClassPair name>externalResourceDependencies< class>[Lorg.apache.uima.resource.ExternalResourceDependency;<
		 * Debug: NameClassPair name>frameworkImplementation< class>java.lang.String<
		 * Debug: NameClassPair name>implementationName< class>java.lang.String<
		 * Debug: NameClassPair name>metaData< class>org.apache.uima.resource.metadata.ResourceMetaData<
		 * Debug: NameClassPair name>resourceManagerConfiguration< class>org.apache.uima.resource.metadata.ResourceManagerConfiguration<
		 */


		/*
		// Setting the parameter for  
		ResourceMetaData aResourceMetaData = (ResourceMetaData) specifier.getAttributeValue("metaData");
		String inputDirectoryAttributeValue = (String) aResourceMetaData.getConfigurationParameterSettings().getParameterValue("InputDirectory");	
		System.out.println("Debug: inputDirectoryAttributeValue>"+inputDirectoryAttributeValue+"<");

		aResourceMetaData.getConfigurationParameterSettings().setParameterValue("InputDirectory", fileSystemInputDir);
		inputDirectoryAttributeValue = (String) aResourceMetaData.getConfigurationParameterSettings().getParameterValue("InputDirectory");	
		System.out.println("Debug: inputDirectoryAttributeValue>"+inputDirectoryAttributeValue+"<");
		 */

		//create CR here
		CollectionReader cr = produceCollectionReader(specifier);
		return cr;
	}

	/**
	 * @param specifier
	 * @return
	 */
	private static CasConsumer produceCasConsumer(
			ResourceSpecifier specifier) {
		CasConsumer cc = null;
		try {
			cc = UIMAFramework.produceCasConsumer(specifier);
		} catch (ResourceInitializationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cc;
	}

	/**
	 * @param specifier
	 * @return
	 */
	private static AnalysisEngine produceAnalysisEngine(
			ResourceSpecifier specifier) {
		AnalysisEngine ae = null;
		try {
			ae = UIMAFramework.produceAnalysisEngine(specifier);
		} catch (ResourceInitializationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ae;
	}

	/**
	 * @param specifier
	 * @return
	 */
	private static CollectionReader produceCollectionReader(
			ResourceSpecifier specifier) {
		CollectionReader cr = null;
		try {
			cr = UIMAFramework.produceCollectionReader(specifier);
		} catch (ResourceInitializationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cr;
	}

	/**
	 * get Resource Specifier from XML file
	 * @param analysisEngineDescriptor
	 * @return
	 */
	private static ResourceSpecifier getResourceSpecifier(
			final String analysisEngineDescriptor) {
		XMLInputSource in = getXMLInputSource(analysisEngineDescriptor);
		ResourceSpecifier specifier = null;
		try {
			specifier = UIMAFramework.getXMLParser().parseResourceSpecifier(in);


		} catch (InvalidXMLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return specifier;
	}

	/**
	 * @param analysisEngineDescriptor
	 * @return
	 */
	private static XMLInputSource getXMLInputSource(
			final String analysisEngineDescriptor) {
		XMLInputSource in = null;
		try {
			in = new XMLInputSource(analysisEngineDescriptor);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return in;
	}


	/**
	 * @param jcas
	 */
	private static void displayAnnotationIndex(JCas jcas) {
		AnnotationIndex<Annotation> annotationIndex = jcas.getAnnotationIndex();
		for (Annotation anAnnotation : annotationIndex) {
			String annotationType = anAnnotation.getClass().getName();
			System.out.println("Annotation type>"+annotationType+"< coveredText >"+anAnnotation.getCoveredText()+"<");

		}
	}

	/**
	 * @param jcas
	 */
	private static void displayTokenAnnotation(JCas jcas) {
		AnnotationIndex<Annotation> annotationIndex = jcas.getAnnotationIndex();
		for (Annotation anAnnotation : annotationIndex) {
			String annotationType = anAnnotation.getClass().getName();
			//System.out.println("Annotation type>"+annotationType+"< coveredText >"+anAnnotation.getCoveredText()+"<");
			if (annotationType.equalsIgnoreCase(tokenAnnotationType)) {
				TokenAnnotation aTokenAnnotation = (TokenAnnotation) anAnnotation;
				System.out.println("aTokenAnnotation coveredText >"+aTokenAnnotation.getCoveredText()+"< POS>"+aTokenAnnotation.getPosTag()+"<");
			}
		}
	}

	/**
	 * called when the cpm end thanks to "implements StatusCallbackListener" + 
	 * 		// register callback listener
		aCPM.addStatusCallbackListener(BuildAndRunCPMCLI.this);
	 */
	public void collectionProcessComplete() {

		System.out.println("CPM Performance Report "+aCPM.getPerformanceReport().toString());
		
		// if everything works, output performance stats and print them to a
		// pane. Allow users to open generated files.
		//    showAnalysisResults(new AnalysisEnginePerformanceReports(mCPM.getPerformanceReport()),          outputDirectory);
	}

	@Override
	public void aborted() {
		// TODO Auto-generated method stub

	}

	@Override
	public void batchProcessComplete() {
		// TODO Auto-generated method stub

	}

	@Override
	public void initializationComplete() {
		// TODO Auto-generated method stub

	}

	@Override
	public void paused() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resumed() {
		// TODO Auto-generated method stub

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
		// increment the number of documents processed and update the
		// ProgressMonitor
		numDocsProcessed++;
		//			    System.out.println("Processed " + numDocsProcessed + " of " + numDocs + " documents. Namely "+cas.getSofaDataURI());
		System.out.println("Processed documents so far: " + numDocsProcessed + "");


	}


}
