package fr.univnantes.lina.mlnlp.process.tokenization.characterTypeBasedTokenizer;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import fr.univnantes.lina.mlnlp.process.tokenization.characterTypeBasedTokenizer.CharacterTypeBasedWordTokenizer;

public class SimpleTokenizerTest {

	@Test
	public void testSimpleTokenizerStringBooleanBoolean() {
		String myline = "123.Ceci est une phrase non-tokenisée. :-)";
		
		// by default (implicit) : whitespace are not tokens and symbol characters are individual tokens
		List<String> tokenizedLine = new CharacterTypeBasedWordTokenizer(myline).getTokenCoveredTextList();
		System.out.println(tokenizedLine);
		
		// by default (explicit) : whitespace are not tokens and symbol characters are individual tokens     
		tokenizedLine = new CharacterTypeBasedWordTokenizer(myline,false,false).getTokenCoveredTextList();
		System.out.println(tokenizedLine);
		
		// whitespace are not tokens and consecutive symbols are tokens
		tokenizedLine = new CharacterTypeBasedWordTokenizer(myline,false,true).getTokenCoveredTextList();
		System.out.println(tokenizedLine);
		
		// whitespace are tokens and symbol characters are individual tokens     
		tokenizedLine = new CharacterTypeBasedWordTokenizer(myline,true,false).getTokenCoveredTextList();
		System.out.println(tokenizedLine);
		
		// whitespace are tokens and consecutive symbols are tokens
		tokenizedLine = new CharacterTypeBasedWordTokenizer(myline,true,true).getTokenCoveredTextList();
		System.out.println(tokenizedLine);
	}
	
	

}
