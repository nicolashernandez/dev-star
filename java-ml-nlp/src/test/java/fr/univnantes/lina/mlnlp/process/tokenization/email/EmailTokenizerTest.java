/**
 * 
 */
package fr.univnantes.lina.mlnlp.process.tokenization.email;

import static org.junit.Assert.*;

import java.util.List;


import org.junit.BeforeClass;
import org.junit.Test;

import fr.univnantes.lina.mlnlp.model.text.Sentence;
import fr.univnantes.lina.mlnlp.model.text.Text;
import fr.univnantes.lina.mlnlp.model.text.Word;

/**
 * @author hernandez
 *
 */
public class EmailTokenizerTest {


	// read some text in the text variable
	String text = "Hi all,\n"
			+"\n"
			+"Can't find a mention of anything like this in bugzilla, and I'm pretty\n"
			+"desperate for an answer.\n"
			+"\n"
			+ "I stumbled across Nautilus' capacity to \"mount\" ftp servers, and spent a\n"
			+"few minutes going \"wow, this is cool\" while working on a customer's\n"
			+"website.\n"
			+"\n"
			+"Then I found that a file I was working on was truncated at exactly the\n"
			+"same spot every time I tried to upload it."
			+"\n"
			+"Odd, I thought, then \"unmounted\" the volume, and tried first lftp, then\n"
			+"gftp to upload the file.  In both programs, all of my customer's files\n"
			+"had apparently disappeared.  An empty directory.\n"
			+"\n"
			+"In rising panic, I tried the website in a browser, and found everything\n"
			+"was still there.  Tried connecting with Nautilus again, and everything\n"
			+"was still there.  Still can't completely upload the file, but that is\n"
			+"now a minor problem compared to my customer's potential reaction next\n"
			+"time they FTP to their site, and find nothing there.\n"
			+"\n"
			+"In particular, this is the 3rd time in the past\n"
			+"month or so that after a series of updates an application or two has \n"
			+"crashed and was caught by \"Bug Buddy\". The other 2 times I made a few \n"
			+"guesses and ultimately submitted my bug reports to Gnome's Bugzilla... \n"
			+"and in both cases the problem was fixed nearly immediately and Ubuntu \n"
			+"reflected that within a couple days... this time, I just don't know \n"
			+"where to submit the bug.\n"
			+"\n"
			+"Now any guesses as to what's going on, or how to fix it?\n"
			+"\n"
			+"Matthew.\n"
			+"\n"
			+"Coming from a Windows world, I've probably been spoiled but..  is there\n"
			+"a fancy, graphical page view application that could be used with Gnome\n" 
			+"/ Ubuntu to see the contents in beforehand?\n"
			+"\n"
			+"Even better, to be able to select 1/2/.. pages per sheet etc.\n" 
			+"dynamically, and then confirm.  For the ultimate tool on this, see\n" 
			+"FinePrint (http://www.pdffactory.com/products/fineprint/index.html) the\n" 
			+"Windows device driver. It's great.\n"
			+"\n"
			+"ps. Even OS X has nothing close to it, unfortunately.  A mere Preview\n"
			+"without the setup changes is not enough.\n"
			+"\n"
			+"On Wed, 2005-06-15 at 20:43 -0400, Paul M. Bucalo wrote:\n"
			+"> Somehow I managed to corrupt a reiserfs partition. Upon boot-up, it\n"
			+"> tells me to use 'reiserfsck --rebuild-tree' because the problem is bad.\n"
			+"> I've been trying to figure out how to do this, including getting to a\n"
			+"> point where this can be done before mounting. <...>\n"
			+"\n"
			+"I have no idea what I did (or didn't do) that fixed the problem. \n"
			+"\n"
			+"Here some text without strong punkt at the end\n"
			+"> Well I also bought a D-Link DWL-G650 and it didn't work with the hoary\n"
			+"> madwifi drivers. Had to update to the latest madwifi drivers manually\n"
			+"> which was a pain. \n"
			+"\n"
			+"> I'm in need of some directions.  I want to run ubuntu for my desktop,\n"
			+"> but the installer doesn't support root or boot on raid.\n"
			+"\n"
			+"It claims not to. It actually does work.\n"
			+"\n"
			+"Create a separate /boot partition for the kernel and initrd to live in\n"
			+"and then install to a / filesystem living on a RAID1 or RAID0 device\n"
			+"that you create during the install. My ubuntu server in the office\n"
			+"currently has 4 active disks doing RAID0+1 and running LVM on top with\n"
			+"another disk about to go in in case 2 happen to fail in quick\n"
			+"succession :-)\n"
			+"\n"
			+"> It seems that there must be a way to convert to raid after the fact, as\n"
			+"> I've seen for some other distros.\n"

			+"-- \n"
			+"Alma Technology\n"
			+"http://www.almatech.net.au\n"
			+"(02) 6658 1607 ... 0419 242 316\n"
			+"--\n"
			+"Make the switch to a safer, better web browsing experience:\n"
			+"http://www.mozilla.org/products/firefox/\n"
			+"http://www.switch2firefox.com/\n"
			+"http://www.spreadfirefox.com/"; // Add your text here!\n";

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	/**
	 * Test of the sentence splitting + the get fine sentence covered text
	 */
	@Test
	public void splitIntoSentencesTestWithWhitespaceSentences() {
		EmailTokenizer tokenizer = new EmailTokenizer();

		Text myText = tokenizer.splitIntoSentences(0, text, true, true);
		List<Sentence> sentences = myText.getSentences();

		for (Sentence sentence : sentences) {
			if (sentence.isContent()) 
				System.out.printf("Sentence>%s<\n",sentence.getFineCoveredText());
			else if (sentence.isNewLine())   System.out.printf("%s\n","NEWLINE");
			else if (sentence.isDoubleNewLine())   System.out.printf("%s\n","NEWLINE\nNEWLINE");
			else System.out.printf("%s\n","INLINE"); // System.out.printf("Debug>%s<\n",sentence.getCoveredText());

		}
	}

	/**
	 * Test of the sentence splitting + the get fine sentence covered text
	 */
	@Test
	public void splitIntoSentencesTestWithoutWhitespaceSentences() {
		EmailTokenizer tokenizer = new EmailTokenizer();

		Text myText = tokenizer.splitIntoSentences(0, text, false, true);
		List<Sentence> sentences = myText.getSentences();

		for (Sentence sentence : sentences) {
			if (sentence.isContent()) 
				System.out.printf("Sentence>%s<\n",sentence.getFineCoveredText());
			else if (sentence.isNewLine())   System.out.printf("%s\n","NEWLINE");
			else if (sentence.isDoubleNewLine())   System.out.printf("%s\n","NEWLINE\nNEWLINE");
			else System.out.printf("%s\n","INLINE"); // System.out.printf("Debug>%s<\n",sentence.getCoveredText());

		}
	}

	/**
	 * Test of the text tokenization without sentence splitting
	 */
	@Test
	public void tokenizeIntoWordsTest() {
		EmailTokenizer tokenizer = new EmailTokenizer();

		List<Word> words = tokenizer.tokenizeIntoWords(0, text, true, null, null);
		for (Word word : words) {
			if (word.isContent()) 
				System.out.printf("Word >%s<\n",word.getCoveredText());
			//	else System.out.printf("%s\n","EMPTY WORD"); // System.out.printf("Debug>%s<\n",sentence.getCoveredText());

		}
	}

	/**
	 * Comparison of the tokenization after sentence splitting and without sentence splitging
	 */
	@Test
	public void getWordsTest() {
		EmailTokenizer tokenizer = new EmailTokenizer();

		Text myText = tokenizer.splitIntoSentences(0, text, false, true);
		List<Word> words1= myText.getWords();
		List<Word> words2 = tokenizer.tokenizeIntoWords(0, text, true, null, null);


		for (int i = 0; i < Math.min(words1.size(), words2.size()) ; i++)
			System.out.printf("Word >%d< >%s< >%s<\n",i,words1.get(i).getCoveredText(),words2.get(i).getCoveredText());
		System.out.printf("Text size >%d< >%d<\n",words1.size(), words2.size());
	}

}
