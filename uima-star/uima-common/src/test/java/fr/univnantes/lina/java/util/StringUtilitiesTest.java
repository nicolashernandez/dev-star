package fr.univnantes.lina.java.util;
/**
 * 
 */



import static org.junit.Assert.*;

import org.junit.Test;

import fr.univnantes.lina.javautil.StringUtilities;

/**
 * @author hernandez
 * http://www.vogella.com/articles/JUnit/article.html
 *
 */
public class StringUtilitiesTest {

	/**
	 * Test method for {@link fr.univnantes.lina.javautil.StringUtilities#buildARandomStringName(java.lang.Object)}.
	 */
	@Test
	public void testBuildARandomStringName() {
	//TODO	fail("Not yet implemented");
	}

	/**
	 * Test method for {@link fr.univnantes.lina.javautil.StringUtilities#codePointToString(int)}.
	 */
	@Test
	public void testCodePointToString() {
	//TODO	fail("Not yet implemented");
	}

	/**
	 * Test method for {@link fr.univnantes.lina.javautil.StringUtilities#string2CodePoint(java.lang.String)}.
	 */
	@Test
	public void testString2CodePoint() {
		// StringUtilities tester = new StringUtilities();
		String sample = "A" + "\uD835\uDD0A"
				+ "B" + "C";
		
		int codePointArray[] = new int[sample.length()];
		codePointArray[0] = 65;
		codePointArray[1] = 120074 ;
		codePointArray[2] = 66 ;
		codePointArray[3] = 67 ;
		
		//assertEquals("Result", codePointArray, StringUtilities.string2CodePoint(sample));
		assertArrayEquals(codePointArray, StringUtilities.string2CodePoint(sample));
	}

}
