package fr.univnantes.lina.mlnlp.process.transformation;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;

import fr.univnantes.lina.mlnlp.process.tokenization.characterTypeBasedTokenizer.CharacterTypeBasedWordTokenizer;
import fr.univnantes.lina.mlnlp.process.transformation.TextNormalization;

public class StringNormalizationTest {

	/**
	 * config for processing French and actual French words 
	 */
	@Test
	public void frfrStemTest() {
		String lang = "fr";

		String word = "avions";
		String expectedStem = "avion";
		String actualStem = TextNormalization.stem(word, lang);
		System.out.printf("word  %s actualStem %s \n",word,actualStem);
		assertEquals("actual stem "+actualStem+" of the word "+word+ "is different from the one expected "+expectedStem, expectedStem, actualStem);

		word = "avaient";
		expectedStem = "avaient";
		actualStem = TextNormalization.stem(word, lang);
		System.out.printf("word  %s actualStem %s \n",word,actualStem);
		assertEquals("actual stem "+actualStem+" of the word "+word+ "is different from the one expected "+expectedStem, expectedStem, actualStem);

		word = "mangaient";
		expectedStem = "mang";
		actualStem = TextNormalization.stem(word, lang);
		System.out.printf("word  %s actualStem %s \n",word,actualStem);
		assertEquals("actual stem "+actualStem+" of the word "+word+ "is different from the one expected "+expectedStem, expectedStem, actualStem);

		word = "mangées";
		expectedStem = "mang";
		actualStem = TextNormalization.stem(word, lang);
		System.out.printf("word  %s actualStem %s \n",word,actualStem);
		assertEquals("actual stem "+actualStem+" of the word "+word+ "is different from the one expected "+expectedStem, expectedStem, actualStem);

		word = "mangions";
		expectedStem = "mangion";
		actualStem = TextNormalization.stem(word, lang);
		System.out.printf("word  %s actualStem %s \n",word,actualStem);
		assertEquals("actual stem "+actualStem+" of the word "+word+ "is different from the one expected "+expectedStem, expectedStem, actualStem);

		word = "mangent";
		expectedStem = "mangent";
		actualStem = TextNormalization.stem(word, lang);
		System.out.printf("word  %s actualStem %s \n",word,actualStem);
		assertEquals("actual stem "+actualStem+" of the word "+word+ "is different from the one expected "+expectedStem, expectedStem, actualStem);

	}

	/**
	 * config for processing English and actual French words 
	 */
	@Test
	public void frenStemTest() {

		String lang = "en";
		String word = "avions";
		String expectedStem = "avion";
		String actualStem = TextNormalization.stem(word, lang);
		System.out.printf("word  %s actualStem %s \n",word,actualStem);
		assertEquals("actual stem "+actualStem+" of the word "+word+ "is different from the one expected "+expectedStem, expectedStem, actualStem);

		word = "avaient";
		expectedStem = "avaient";
		actualStem = TextNormalization.stem(word, lang);
		System.out.printf("word  %s actualStem %s \n",word,actualStem);
		assertEquals("actual stem "+actualStem+" of the word "+word+ "is different from the one expected "+expectedStem, expectedStem, actualStem);

		word = "mangaient";
		expectedStem = "mangaient";
		actualStem = TextNormalization.stem(word, lang);
		System.out.printf("word  %s actualStem %s \n",word,actualStem);
		assertEquals("actual stem "+actualStem+" of the word "+word+ "is different from the one expected "+expectedStem, expectedStem, actualStem);

		word = "mangées";
		expectedStem = "mangé";
		actualStem = TextNormalization.stem(word, lang);
		System.out.printf("word  %s actualStem %s \n",word,actualStem);
		assertEquals("actual stem "+actualStem+" of the word "+word+ "is different from the one expected "+expectedStem, expectedStem, actualStem);

		word = "mangions";
		expectedStem = "mangion";
		actualStem = TextNormalization.stem(word, lang);
		System.out.printf("word  %s actualStem %s \n",word,actualStem);
		assertEquals("actual stem "+actualStem+" of the word "+word+ "is different from the one expected "+expectedStem, expectedStem, actualStem);

		word = "mangent";
		expectedStem = "mangent";
		actualStem = TextNormalization.stem(word, lang);
		System.out.printf("word  %s actualStem %s \n",word,actualStem);
		assertEquals("actual stem "+actualStem+" of the word "+word+ "is different from the one expected "+expectedStem, expectedStem, actualStem);

	}

	/**
	 * config for processing English and actual French words 
	 */
	@Test
	public void getGuessedMorphologicalFlexionsTest() {
		String lang = "en";
		String word = "avions";
		String expectedflexion = "ions";
		String actualGuessedflexion = TextNormalization.getGuessedMorphologicalFlexion(word, null);
		System.out.printf("word  %s actualGuessedflexion %s \n",word,actualGuessedflexion);
		assertEquals("actual flexion "+actualGuessedflexion+" of the word "+word+ "is different from the one expected "+expectedflexion, expectedflexion, actualGuessedflexion);

		word = "avaient";
		expectedflexion = "aient";
		actualGuessedflexion = TextNormalization.getGuessedMorphologicalFlexion(word, null);
		System.out.printf("word  %s actualGuessedflexion %s \n",word,actualGuessedflexion);
		assertEquals("actual flexion "+actualGuessedflexion+" of the word "+word+ "is different from the one expected "+expectedflexion, expectedflexion, actualGuessedflexion);

		word = "mangaient";
		expectedflexion = "aient";
		actualGuessedflexion = TextNormalization.getGuessedMorphologicalFlexion(word, null);
		System.out.printf("word  %s actualGuessedflexion %s \n",word,actualGuessedflexion);
		assertEquals("actual flexion "+actualGuessedflexion+" of the word "+word+ "is different from the one expected "+expectedflexion, expectedflexion, actualGuessedflexion);

		word = "mangées";
		expectedflexion = "ées";
		actualGuessedflexion = TextNormalization.getGuessedMorphologicalFlexion(word, null);
		System.out.printf("word  %s actualGuessedflexion %s \n",word,actualGuessedflexion);
		assertEquals("actual flexion "+actualGuessedflexion+" of the word "+word+ "is different from the one expected "+expectedflexion, expectedflexion, actualGuessedflexion);

		word = "mangions";
		expectedflexion = "ions";
		actualGuessedflexion = TextNormalization.getGuessedMorphologicalFlexion(word, null);
		System.out.printf("word  %s actualGuessedflexion %s \n",word,actualGuessedflexion);
		assertEquals("actual flexion "+actualGuessedflexion+" of the word "+word+ "is different from the one expected "+expectedflexion, expectedflexion, actualGuessedflexion);

		word = "mangent";
		expectedflexion = "ent";
		actualGuessedflexion = TextNormalization.getGuessedMorphologicalFlexion(word, null);
		System.out.printf("word  %s actualGuessedflexion %s \n",word,actualGuessedflexion);
		assertEquals("actual flexion "+actualGuessedflexion+" of the word "+word+ "is different from the one expected "+expectedflexion, expectedflexion, actualGuessedflexion);

		word = "engagement";
		expectedflexion = "ent";
		actualGuessedflexion = TextNormalization.getGuessedMorphologicalFlexion(word, null);
		System.out.printf("word  %s actualGuessedflexion %s \n",word,actualGuessedflexion);
		assertEquals("actual flexion "+actualGuessedflexion+" of the word "+word+ "is different from the one expected "+expectedflexion, expectedflexion, actualGuessedflexion);

	}



	@Test
	public void normalizationTest() {
		String myline = "123 . Ceci est une phrase oui - tokenisée . :-)";
		List<String> words = Arrays.asList(myline.split(" "));

		String language = "fr";
		String normalizationOperation = TextNormalization.LOWERCASE;
		List<String> normalizedWords = TextNormalization.normalize( normalizationOperation, language,
				words, null);
		System.out.printf("%s gives: %s\n",normalizationOperation, normalizedWords);

		normalizationOperation = TextNormalization.STEM;
		System.out.printf("%s gives: %s\n",normalizationOperation, TextNormalization.normalize( normalizationOperation, language,
				words, null));
		
		normalizationOperation = TextNormalization.TOCHARCLASS;
		System.out.printf("%s gives: %s\n",normalizationOperation, TextNormalization.normalize( normalizationOperation, language,
				words, null));
		
		normalizationOperation = TextNormalization.SQUEEZEREPEATEDCHAR;
		System.out.printf("%s gives: %s\n",normalizationOperation, TextNormalization.normalize( normalizationOperation, language,
				words, null));
		
		normalizationOperation = TextNormalization.TONUMBERCLASS;
		System.out.printf("%s gives: %s\n",normalizationOperation, TextNormalization.normalize( normalizationOperation, language,
				words, null));
		
		
		normalizationOperation = TextNormalization.FLEXION;
		System.out.printf("%s gives: %s\n",normalizationOperation, TextNormalization.normalize( normalizationOperation, language,
				words, null));
		
		Set<String> stopwords = new HashSet<String>();
		stopwords.add("ceci");
		stopwords.add("une");
		stopwords.add("non");
		System.out.printf("%s gives: %s\n",normalizationOperation, TextNormalization.normalize( normalizationOperation, language,
				words, stopwords));
		
	}


	@Rule
	public TestName name = new TestName();

	@Before
	public void printSeparator()
	{
		System.out.println("\n=== " + name.getMethodName() + " =====================");
	}
}
