/**
 * 
 */
package fr.univnantes.lina.mlnlp.model.alphabet;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Iterator;

import org.junit.Test;

import fr.univnantes.lina.javautil.IOUtilities;
import fr.univnantes.lina.mlnlp.model.alphabet.SimpleAlphabet;

/**
 * @author hernandez
 *
 */
public class AlphabetTest {

	/**
	 * Test method for {@link fr.univnantes.lina.uima.ml.model.Alphabet#encode1(int[])}.
	 */
	/*@Test
	public void testEncode1() {
		Alphabet alphabet = new Alphabet();
		String actualString = "ABC";
		Double expectedCodeResult = 		(65 + 66 *2 + 67 *3) * 3.0;
		Double actualCodeDouble = alphabet.encode1(actualString);
		assertNotNull("Alphabet.encode does not return null", actualCodeDouble);
		assertEquals("Result", expectedCodeResult, actualCodeDouble);
		
	}*/

	/**
	 * Test method for {@link fr.univnantes.lina.uima.ml.model.Alphabet#encode(int[])}.
	 */
	/*@Test
	public void testEncode() {

		fail("Not yet implemented");
	}*/

	/**
	 * Test method for {@link fr.univnantes.lina.uima.ml.model.Alphabet#symbol2code(java.lang.Object)}.
	 */
	/*@Test
	public void testSymbol2code() {
		//System.out.println("Debug: expected string value " + ((65 + 66 *2 + 67 *3) * 3.0));
		assertEquals("Result double symbol2code", (Double)1.0, Alphabet.symbol2code(1.0));
		assertEquals("Result int symbol2code", (Double)1.0, Alphabet.symbol2code(1));
		assertEquals("Result boolean symbol2code", (Double)1.0, Alphabet.symbol2code(true));
		assertEquals("Result String symbol2code", (Double)1194.0, Alphabet.symbol2code("ABC"));
	
		fail("Not yet implemented");
	}*/

	/**
	 * Test method for {@link fr.univnantes.lina.uima.ml.model.Alphabet#addSymbol(java.lang.Object)}.
	 */
	/*@Test
	public void testAddSymbol() {
		fail("Not yet implemented");
	}*/

	/**
	 * Test method for {@link fr.univnantes.lina.mlnlp.model.alphabet.SimpleAlphabet#getSymbolCode(java.lang.Object)}.
	 */
	@Test
	public void testGetSymbolCode() {
		SimpleAlphabet anAlphabet = new SimpleAlphabet();

		ArrayList<String> symbolList = IOUtilities.readFromFileNameToLineArray("/usr/share/dict/words");
		Iterator<String> symbolListIterator = symbolList.iterator();
		
		int numberOfNullCode = 0;
		//String line = "";
		while (symbolListIterator.hasNext()) {
			String symbol = symbolListIterator.next();
			Double code = anAlphabet.getSymbolCode(symbol);
			if (code == null) numberOfNullCode++;
			//line =  symbol + ":" + code + "\n"; 
			//System.out.println("Debug: "+symbol+":"+code);
		}
		//		System.out.println("Debug: done");
		assertEquals("Result ", 0, numberOfNullCode);
		
	}

	

}
