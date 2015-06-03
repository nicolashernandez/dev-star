package fr.univnantes.lina.mlnlp.model.lexicalChain;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;

import fr.univnantes.lina.mlnlp.model.lexicalChain.LexicalChain;
import fr.univnantes.lina.mlnlp.process.comparison.AverageContainmentSimilarityMeasures;
import fr.univnantes.lina.mlnlp.process.comparison.ContainmentSimilarityMeasures;
import fr.univnantes.lina.mlnlp.process.comparison.JaccardSimilarityMeasures;

public class TestLexicalChain {

	LexicalChain lc1 = null;
	LexicalChain lc2 =  null;
	LexicalChain lc3  =  null;
	LexicalChain lc4  =  null;
	ContainmentSimilarityMeasures<String> setSimilarityMeasures = null;
	JaccardSimilarityMeasures<String> jaccardSimilarityMeasure = null;
	ContainmentSimilarityMeasures<String> containmentSimilarityMeasures = null;
	AverageContainmentSimilarityMeasures<String> averageContainmentSimilarityMeasures = null;

	@Before 
	public void initialize() {
		lc1 = new LexicalChain();
		lc1.addItem("père");
		lc1.addItem("noël");
		lc1.addItem("santa");
		lc1.addItem("sapin");
		lc1.addItem("neige");
		lc1.addItem("vinasse");

		lc2 = new LexicalChain();
		lc2.addItem("père");
		lc2.addItem("fouettard");
		lc2.addItem("martinet");
		lc2.addItem("enfant");
		lc2.addItem("vinasse");
		lc2.addItem("vieille");

		lc3 = new LexicalChain();
		lc3.addItem("père");
		lc3.addItem("noël");

		lc4 = new LexicalChain();
		lc4.addItem("père");

		setSimilarityMeasures = new ContainmentSimilarityMeasures<String>(); 
		jaccardSimilarityMeasure = new JaccardSimilarityMeasures<String>();
		containmentSimilarityMeasures = new ContainmentSimilarityMeasures<String>();
		averageContainmentSimilarityMeasures = new AverageContainmentSimilarityMeasures<String>();
	}

	@Test
	public void testComparisonIdenticalSets() {

		System.out.printf("jaccardSimilarity %s with %s gives %f\n", lc1, lc1,  jaccardSimilarityMeasure.measureSetSetSimilarity(lc1.getLexicalChain(), lc1.getLexicalChain()));
		System.out.printf("containmentSimilarity %s with %s gives %f\n", lc1, lc1,  containmentSimilarityMeasures.measureSetSetSimilarity(lc1.getLexicalChain(), lc1.getLexicalChain()));
		System.out.printf("containmentSimilarity %s with %s gives %f\n", lc1,  lc1, containmentSimilarityMeasures.measureSetSetSimilarity(lc1.getLexicalChain(), lc1.getLexicalChain()));
		System.out.printf("averageContainmentSimilarity %s with %s gives %f\n", lc1,  lc1, averageContainmentSimilarityMeasures.measureSetSetSimilarity(lc1.getLexicalChain(), lc1.getLexicalChain()));
		
		/*System.out.printf("jaccardSimilarity %s with %s gives %f\n", lc1, lc1,  jaccardSimilarityMeasure.measureSetSetSimilarity(lc1.getLexicalChain(), lc1.getLexicalChain()));
		System.out.printf("containmentSimilarity %s with %s gives %f\n", lc1, lc1,  setSimilarityMeasures.containmentSimilarity(lc1.getLexicalChain(), lc1.getLexicalChain(), true));
		System.out.printf("containmentSimilarity %s with %s gives %f\n", lc1,  lc1, setSimilarityMeasures.containmentSimilarity(lc1.getLexicalChain(), lc1.getLexicalChain(), true));
		System.out.printf("averageContainmentSimilarity %s with %s gives %f\n", lc1,  lc1, (setSimilarityMeasures.containmentSimilarity(lc1.getLexicalChain(), lc1.getLexicalChain(), true)+setSimilarityMeasures.containmentSimilarity(lc1.getLexicalChain(), lc1.getLexicalChain(), true))/2);*/

		assertTrue(lc1.isSimilar(lc1, averageContainmentSimilarityMeasures));

	}

	@Test
	public void testComparisonDistinctSetsOfSameSize() {
		System.out.printf("jaccardSimilarity %s with %s gives %f\n", lc1, lc2,  jaccardSimilarityMeasure.measureSetSetSimilarity(lc1.getLexicalChain(), lc2.getLexicalChain()));
		System.out.printf("containmentSimilarity %s with %s gives %f\n", lc1, lc2,  containmentSimilarityMeasures.measureSetSetSimilarity(lc1.getLexicalChain(), lc2.getLexicalChain()));
		System.out.printf("containmentSimilarity %s with %s gives %f\n", lc2,  lc1, containmentSimilarityMeasures.measureSetSetSimilarity(lc2.getLexicalChain(), lc1.getLexicalChain()));
		System.out.printf("averageContainmentSimilarity %s with %s gives %f\n", lc1,  lc2, averageContainmentSimilarityMeasures.measureSetSetSimilarity(lc1.getLexicalChain(), lc2.getLexicalChain()));
		
		assertFalse(lc1.isSimilar(lc2, averageContainmentSimilarityMeasures));
	}

	@Test
	public void testComparisonContainedSets() {
		System.out.printf("jaccardSimilarity %s with %s gives %f\n", lc1, lc3,  jaccardSimilarityMeasure.measureSetSetSimilarity(lc1.getLexicalChain(), lc3.getLexicalChain()));
		System.out.printf("containmentSimilarity %s with %s gives %f\n", lc1, lc3,  containmentSimilarityMeasures.measureSetSetSimilarity(lc1.getLexicalChain(), lc3.getLexicalChain()));
		System.out.printf("containmentSimilarity %s with %s gives %f\n", lc3,  lc1, containmentSimilarityMeasures.measureSetSetSimilarity(lc3.getLexicalChain(), lc1.getLexicalChain()));
		System.out.printf("averageContainmentSimilarity %s with %s gives %f\n", lc1,  lc3, averageContainmentSimilarityMeasures.measureSetSetSimilarity(lc1.getLexicalChain(), lc3.getLexicalChain()));		

		assertTrue(lc1.isSimilar(lc3, averageContainmentSimilarityMeasures));
	}

	@Test
	public void testComparisonContainedSetsOfOneElement() {
		System.out.printf("jaccardSimilarity %s with %s gives %f\n", lc1, lc4,  jaccardSimilarityMeasure.measureSetSetSimilarity(lc1.getLexicalChain(), lc4.getLexicalChain()));
		System.out.printf("containmentSimilarity %s with %s gives %f\n", lc1, lc4,  containmentSimilarityMeasures.measureSetSetSimilarity(lc1.getLexicalChain(), lc4.getLexicalChain()));
		System.out.printf("containmentSimilarity %s with %s gives %f\n", lc4,  lc1, containmentSimilarityMeasures.measureSetSetSimilarity(lc4.getLexicalChain(), lc1.getLexicalChain()));
		System.out.printf("averageContainmentSimilarity %s with %s gives %f\n", lc1,  lc4, averageContainmentSimilarityMeasures.measureSetSetSimilarity(lc1.getLexicalChain(), lc4.getLexicalChain()));		

		assertTrue(lc1.isSimilar(lc4, averageContainmentSimilarityMeasures));

	}
	

	@Rule
	public TestName name = new TestName();

	@Before
	public void printSeparator()
	{
		System.out.println("\n=== " + name.getMethodName() + " =====================");
	}


}
