/**
 * 
 */
package workflow.sandbox;

import java.io.FileNotFoundException;
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
public class CPMUtilities {

	/**
	 * Set a Configuration Parameter of a ResourceSpecifier by 
	 * specifying the String name and the String value of the parameter to set 
	 * @param aResourceSpecifier
	 * @param name
	 * @param value
	 */
	public static void setConfigurationParameter(
			ResourceSpecifier aResourceSpecifier, String name, String value) {
		((ResourceMetaData)aResourceSpecifier.getAttributeValue("metaData")).getConfigurationParameterSettings().setParameterValue(name, value);
	}

	/**
	 * Execute a CollectionProcessingManager by specifying its CollectionReader
	 * @param aCPM
	 * @param aCR
	 */
	public static  void processCPM(CollectionProcessingManager aCPM, CollectionReader aCR) {
		try {
			aCPM.process(aCR);
		} catch (ResourceInitializationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	/**
	 * Execute a CasConsumer 
	 * @param jcas
	 * @param aCC
	 */
	public static void processCasConsumer(JCas jcas, CasConsumer aCC) {
		try {
			aCC.processCas(jcas.getCas());
		} catch (ResourceProcessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	

	/**
	 * Execute an AnalysisEngine
	 * @param jcas
	 * @param ae
	 */
	public static void processAnalysisEngine(JCas jcas, AnalysisEngine ae) {
		try {
			ae.process(jcas);
		} catch (AnalysisEngineProcessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Create a JCas combining (merging) the type system of several 
	 * ResourceCreationSpecifiers (cr, ae, cc descriptions)
	 * @param a list of ResourceCreationSpecifier
	 * @return JCas
	 */
	public static JCas createSharedJCas(ResourceCreationSpecifier... descriptions) {

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
	 * Get the AnalysisEngineDescription object described at the given path 
	 * @param aedpath
	 * @return
	 */
	public static AnalysisEngineDescription getAnalysisEngineDescription(String aedpath) {
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
	 * Get the CollectionReaderDescription object described at the given path 
	 * @param collectionReaderAEDPath
	 * @return
	 */
	public static CollectionReaderDescription getCollectionReaderDescription(String collectionReaderAEDPath) {
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
	 * Get the CasConsumerDescription object described at the given path 
	 * @param casConsumerAEDPath
	 * @return
	 */
	public static CasConsumerDescription getCasConsumerDescription(String casConsumerAEDPath) {
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
	 * Produce a CasConsumer from a given descriptor path 
	 * (first get the corresponding resource specifier) 
	 * @param ccd
	 * @return
	 */
	public static CasConsumer produceCasConsumer(
			final String ccd) {
		//get Resource Specifier from XML file

		ResourceSpecifier specifier = getResourceSpecifier(ccd);

		//create AE here
		CasConsumer ae = produceCasConsumer(specifier);
		return ae;
	}

	/**
	 * Produce a AnalysisEngine from a given descriptor path 
	 * (first get the corresponding resource specifier) 
	 * @param aed
	 * @return
	 */
	public static AnalysisEngine produceAnalysisEngine(
			final String aed) {
		//get Resource Specifier from XML file

		ResourceSpecifier specifier = getResourceSpecifier(aed);

		//create AE here
		AnalysisEngine ae = produceAnalysisEngine(specifier);
		return ae;
	}

	/**
	 * Produce a CollectionReader from a given descriptor path 
	 * (first get the corresponding resource specifier) 
	 * @param crd
	 * @return
	 */
	public static CollectionReader produceCollectionReader(
			final String crd) {

		ResourceSpecifier specifier = getResourceSpecifier(crd);

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

		//create CR here
		CollectionReader cr = produceCollectionReader(specifier);
		return cr;
	}

	/**
	 * Produce a CasConsumer from a given resource specifier
	 * @param specifier
	 * @return
	 */
	public static CasConsumer produceCasConsumer(
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
	 *  Produce an AnalysisEngine from a given resource specifier
	 * @param specifier
	 * @return
	 */
	public static AnalysisEngine produceAnalysisEngine(
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
	 * Produce an CollectionReader from a given resource specifier
	 * @param specifier
	 * @return
	 */
	public static CollectionReader produceCollectionReader(
			ResourceSpecifier specifier) {
		CollectionReader cr = null;
		try {
			cr = UIMAFramework.produceCollectionReader(specifier);
		} catch (ResourceInitializationException e) {
			System.err.println(e.getMessage());
			System.err.println(e.getCause());
			
			if (e.getMessage().startsWith("Invalid value for parameter") || e.getCause().toString().contains("No value has been assigned to the mandatory configuration parameter")) {
				System.err.println("Set the required parameter !");
			}
			else 
				if (e.getMessage().startsWith("The Resource Factory does not know how to create a resource of class")) {
					System.err.println("Check if you are using a correct CollectionReader descriptor !");
				}
			
			System.exit(1);
		}
		return cr;
	}

	/**
	 * get Resource Specifier from the XML descriptor file path
	 * @param analysisEngineDescriptor
	 * @return
	 */
	public static ResourceSpecifier getResourceSpecifier(
			final String analysisEngineDescriptor) {
		XMLInputSource in = getXMLInputSource(analysisEngineDescriptor);
		ResourceSpecifier specifier = null;
		try {
			specifier = UIMAFramework.getXMLParser().parseResourceSpecifier(in);
		} 

		catch (InvalidXMLException e) {
			System.err.println("Error: "+e.getMessage());
			System.err.println("Check the content of the specified descriptor !");
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
		return specifier;
	}

	/**
	 * get the XMLInputSource from the XML descriptor file path
	 * @param analysisEngineDescriptor
	 * @return
	 */
	public static XMLInputSource getXMLInputSource(
			final String analysisEngineDescriptor) {
		XMLInputSource in = null;
		try {
			in = new XMLInputSource(analysisEngineDescriptor);
		} catch (IOException e) {
			System.err.println("Error: "+e.getMessage());
			System.err.println("Check if the specified descriptor exists !");
			System.exit(1);

			//e.printStackTrace();
		}
		return in;
	}


	/**
	 * display the annotation index of a given jcas
	 * @param jcas
	 */
	public static void displayAnnotationIndex(JCas jcas) {
		AnnotationIndex<Annotation> annotationIndex = jcas.getAnnotationIndex();
		for (Annotation anAnnotation : annotationIndex) {
			String annotationType = anAnnotation.getClass().getName();
			System.out.println("Annotation type>"+annotationType+"< coveredText >"+anAnnotation.getCoveredText()+"<");

		}
	}

}
