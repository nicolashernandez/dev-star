/**
 * 
 */
package fr.univnantes.lina.mlnlp.process.transformation;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.junit.Test;

import fr.univnantes.lina.mlnlp.process.transformation.NGramsBuilder;

/**
 * @author hernandez
 *
 */
public class NGramsTest {

	/**
	 * Test method for {@link fr.univnantes.lina.mlnlp.process.transformation.NGramsBuilder#ngramify(java.util.List, int, int)}.
	 */
	@Test
	public void testNgramifyList() {
		String[] words = {"Jules", "Vernes", "est", "né", "à", "Nantes", "en", "1910", "."};  
		List<String> tokenList = Arrays.asList(words);
		//TODO fail("Not yet implemented");
	}
	
	/**
	 * Test method for {@link fr.univnantes.lina.mlnlp.process.transformation.NGramsBuilder#ngramify(java.util.List, int, int)}.
	 */
	@Test
	public void testNgramifyString() {
		String sentence = "Jules Vernes   est né à   Nantes en 1828  .";
		
		String[] expectedBigrams = {"Jules Vernes", "Vernes est", "est né", "né à", "à Nantes", "Nantes en", "en 1828", "1828 ."};  
		List<String> sentenceBigrams = NGramsBuilder.bigram(sentence);
		assertArrayEquals(expectedBigrams, sentenceBigrams.toArray());
		
		String[] expectedTrigrams = {"Jules Vernes est", "Vernes est né", "est né à", "né à Nantes", "à Nantes en", "Nantes en 1828", "en 1828 ."};  
		List<String> sentenceTrigrams = NGramsBuilder.trigram(sentence);
		assertArrayEquals(expectedTrigrams, sentenceTrigrams.toArray());
/*		Iterator<String> sentenceBigramsIterator = sentenceBigrams.iterator();
		while (sentenceBigramsIterator.hasNext()) {
			System.out.print(" "+ sentenceBigramsIterator.next());
		}
		System.out.println();
*/		
	}
	
	
}
