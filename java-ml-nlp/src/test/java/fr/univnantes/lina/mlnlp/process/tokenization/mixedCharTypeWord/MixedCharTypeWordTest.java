/**
 * 
 */
package fr.univnantes.lina.mlnlp.process.tokenization.mixedCharTypeWord;


import org.junit.Test;

import fr.univnantes.lina.javautil.IOUtilities;


/**
 * @author hernandez
 *
 */
public class MixedCharTypeWordTest {

	String text = "";
	//String trainPath = "/home/hernandez/data/ubuntu/irc.msgBody/ubuntu-20050101-20141106.msgBody.txt";
	//String trainPath = "/home/hernandez-n/data/ubuntu/email.message.newContent/ubuntu-users-email.new-content.txt";
	String trainPath = "/home/hernandez/data/ubuntu/irc.msgBody/ubuntu-20050101-20141106.msgBody.txt";

	String wordsFilePath = "/tmp/words-ending-wi-punct.txt";

	


	/**
	 * Test method for {@link fr.univnantes.lina.mlnlp.process.tokenization.mixedCharTypeWord.MixedCharTypeWord#identifyWstokenWiPunctAsWord()}.
	 * cat /tmp/words-ending-wi-punct.txt  | cut -f 1 > /tmp/f1 ; cat /tmp/words-ending-wi-punct.txt  | cut -f 2 > /tmp/f2 ;  paste /tmp/f2 /tmp/f1 | sort -gr | less

	 * cat ~/data/ubuntu/email.message.newContent/ubuntu-users-email.new-content.txt | grep ' stuff ' | wc
	 * cat $HOME/data/ubuntu/irc.msgBody/ubuntu-20050101-20141106.msgBody.txt| grep ' stuff ' | wc

	 */
	@Test
	public void testIdentifyWstokenWiPunctAsWord() {
		MixedCharTypeWord mixedCharTypeWord = new MixedCharTypeWord(trainPath);
		
		StringBuffer words = new StringBuffer();
		for (String word : mixedCharTypeWord.getWordEndingWiPunct()) {
			words.append(word); words.append("\n");
			//System.out.println(word);
		}
		IOUtilities.writeToFS(words.toString(), wordsFilePath, false);
		
	}

}
