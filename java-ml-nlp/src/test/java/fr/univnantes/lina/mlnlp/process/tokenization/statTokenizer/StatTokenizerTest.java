package fr.univnantes.lina.mlnlp.process.tokenization.statTokenizer;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;

import org.junit.Test;

import fr.univnantes.lina.mlnlp.model.lm.BerkeleyLanguageModel;

/**
 * 
 * @author hernandez-n
 *
 */
public class StatTokenizerTest extends StatTokenizer {

	/**
	 * 
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	@Test
	public void testStatTokenize() throws FileNotFoundException, IOException {
		//fr/univnantes/lina/mlnlp/process/tokenization/statTokenizer/
		String lmFileName = "train.pre.arpa" ;
		String testFileName = "test";
		
		URL lmUrl= getClass().getResource(lmFileName);
		URL testUrl= getClass().getResource(testFileName);
		//		System.out.println(lmUrl.getFile());

		BerkeleyLanguageModel blm = new BerkeleyLanguageModel();
		blm.loadLanguageModel(lmUrl.getFile());
		
		statTokenize(blm,new BufferedReader( new FileReader (testUrl.getFile())));
	}

}
