package fr.univnantes.lina.javautil;

import static org.junit.Assert.*;

import org.junit.Test;

public class CharacterUtilTest {

	@Test
	public void isAsciiTest() {
		

		
		String character = "a";
		Boolean expected = true;
		Boolean actual = CharacterUtil.isAscii(character.codePointAt(0));
		assertEquals(character+" is actually considered as "+actual+" which is different from the expected value"+ expected,expected, actual);
		
		character = "é";
		expected = false;
		actual = CharacterUtil.isAscii(character.codePointAt(0));
		assertEquals(character+" is actually considered as "+actual+" which is different from the expected value"+ expected,expected, actual);
	}

}
