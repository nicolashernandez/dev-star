package fr.univnantes.lina.uima.common.ae;

import static org.apache.uima.fit.factory.AnalysisEngineFactory.createEngine;
import static org.apache.uima.fit.factory.AnalysisEngineFactory.createEngineDescription;
import static org.apache.uima.fit.factory.TypeSystemDescriptionFactory.createTypeSystemDescription;
import static org.apache.uima.util.CasCreationUtils.createCas;

import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.cas.text.AnnotationIndex;
import org.apache.uima.fit.util.JCasUtil;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.tcas.Annotation;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;

import common.types.text.Sentence;
import common.types.text.Token;



/**
 * Lists of tests to demonstrate the various ways of accessing annotations 
 * from the annotation index
 * 
 * @author hernandez
 *
 */
public class TestCommonAE {


	public class CommonProcessAnnotator extends CommonAE {
		@Override
		public void process(JCas aJCas) throws AnalysisEngineProcessException {
			AnnotationIndex<Annotation>	anAnnotationIndex = aJCas.getAnnotationIndex();
			// selectAll(jcas) of uimaFIT get all the feature structures (inheriting from TOP), it is so required to filter them

			for (Annotation annotation : anAnnotationIndex) {
				echo(annotation);
			}
		}
	}


	/**
	 * Iterate over the annotation index 
	 * with annotations at the same spans coming up in an undefined order 
	 * @throws Exception
	 */
	@Test
	public void TestCommonProcessAnnotator() throws Exception{

		// Create and populate a CAS
		JCas jcas = createCas(createTypeSystemDescription(), null, null).getJCas();
		populateCas(jcas);

		// Create a pipeline and run the pipeline
		createEngine(createEngineDescription(CommonProcessAnnotator.class)).process(jcas);
	}

	/**
	 * Create a JCas resulting from manual analysis to be used as sample data
	 * Set the sofa string of JCas and add manually some annotations to the index
	 * @param jcas
	 */
	private static void populateCas(JCas jcas) {

		//-- Set text
		String texten = "Verne visited the seaport of Nantes . He saw the captain Nemo getting his boat . ";
		jcas.setDocumentText(texten);

		//-- Add some annotations
		// Tokens
		int i = 0;
		String sep = " ";
		while (texten.indexOf(sep, i) != -1) {
			new Token(jcas, i, texten.indexOf(sep, i)).addToIndexes();
			i = texten.indexOf(sep, i);
		}

		// Sentence
		i = 0;
		sep = ".";
		while (texten.indexOf(sep, i) != -1) {
			new Sentence(jcas, i, texten.indexOf(sep, i)).addToIndexes();
			i = texten.indexOf(sep, i);
		}
	}

	@Rule
	public TestName name = new TestName();

	@Before
	public void printSeparator()
	{
		System.out.println("\n=== " + name.getMethodName() + " =====================");
	}

	public static void echo(Annotation anAnnotation) {
		System.out.printf("type>%s<\t\tcoveredText>%s<\n", 
				anAnnotation.getClass().getSimpleName(), 
				anAnnotation.getCoveredText());
	}

}
