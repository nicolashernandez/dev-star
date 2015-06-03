package fr.univnantes.lina.app.tokenizer;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.junit.Test;

import com.pwnetics.metric.WordSequenceAligner;
import com.pwnetics.metric.Alignment;
import com.pwnetics.metric.SummaryStatistics;

import fr.univnantes.lina.javautil.IOUtilities;
import fr.univnantes.lina.mlnlp.analysis.text.ruleBasedTokenizer.WordSentenceListTokenizer;


public class TokenizerTest { // extends TestCase {

	final String wordDelimiter = " ";

	/*@Test
	public void test() throws Exception{
		fail("Not yet implemented");
	}*/

	/**
	 * 
	 */
	@Test
	public void TestWordSentenceListTokenizer()  throws Exception {
		System.out.println("---------------------------------------------------");

		// --
		// hack the get a relative path 
		ClassLoader loader = Test.class.getClassLoader();
		String projectHome = (loader.getResource(".")+"").replaceAll("file:", "");
		//System.out.println(loader.getResource("."));
		//System.out.println(loader.getResource("fr/univnantes/lina/app/tokenizer/TextOutputTokenizerTest.class"));

		//projectHome = "";
		//String rawTextFilePath= projectHome+ "exempleText-fr-raw.txt";
		//String refTextFilePath = projectHome+ "exempleText-fr-reference-wst-spl.txt";
		String rawTextFilePath= "exempleText-fr-raw.txt";
		String refTextFilePath = "exempleText-fr-reference-wst-spl.txt";
		System.out.println("Debug: Raw reference file: "+rawTextFilePath);
		System.out.println("Debug: Tok reference file: "+refTextFilePath);

		// --
		System.out.println("Debug: Transform refText into a single whitespace separated line");
		String refTextString = IOUtilities.readTextFileAsString(refTextFilePath);
		refTextString = refTextString.replaceAll(System.getProperty("line.separator"), wordDelimiter).trim() + System.getProperty("line.separator");


		// -- tokenizer
		System.out.println("Debug: Tokenize rawText with WordSentenceListTokenizer");
//		String propertiesFilePath = projectHome+"/resources/simpleTokenizer/simpleTokenizer.fr.wpl.properties";
		String propertiesFilePath = projectHome+"simpleTokenizer.fr.wst-spl.properties";
		System.out.println("Debug: Properties file: "+propertiesFilePath);	
		List<String> args = new ArrayList<String>();
		args.add ("--properties-file");
		args.add(propertiesFilePath);
		List<String> hypLine = new ArrayList<String>();
		hypLine.add(IOUtilities.readTextFileAsString(rawTextFilePath));
		List<List<String>> wordSentenceList = WordSentenceListTokenizer.tokenize(hypLine,args);
		//readTextFileAsASingleLine
		// 
		int wordNumber = 0;
		String hypTextString = "";
		for (List<String> sentence : wordSentenceList) {
			for (String word : sentence) {
				hypTextString += word + wordDelimiter;
				//System.out.println(word);
				wordNumber++;
			}
			//	hypTextString += wordDelimiter;

		}
		hypTextString = hypTextString.trim() + System.getProperty("line.separator");


		System.out.println("Debug: #sentence "+wordSentenceList.size()+" #word "+wordNumber+ " hypTextString>"+hypTextString+"<");

		//-- computer WER
		System.out.println("Debug: Compute WER");

		WordSequenceAligner werEval = new WordSequenceAligner();

		// add all the lines to align
		List<Alignment> alignments = new ArrayList<Alignment>();

		// Reference alignments and stats created with the NIST sclite tool, default settings

		Alignment a = werEval.align(refTextString.split(wordDelimiter), hypTextString.split(wordDelimiter));
		alignments.add(a);

		SummaryStatistics ss = new SummaryStatistics(alignments);
		System.out.println("Statistics summary");
		System.out.println(ss);
		System.out.println("");

		//-- for diff
		String refPathDiff = createTmpFile("refText", refTextString.replaceAll(wordDelimiter, System.lineSeparator()));

		String hypPathDiff = createTmpFile("hypText", hypTextString.replaceAll(wordDelimiter, System.lineSeparator()));

		//System.out.println("ref>"+refPathDiff+"< hyp>"+hypPathDiff+"<");
		System.out.println("diff -y "+refPathDiff+" "+hypPathDiff);

	}

	/**
	 * @param prefix
	 * @param text
	 */
	private String createTmpFile(String prefix, String text) {
		String outputPath = null;
		
			outputPath = IOUtilities.createTempTextFile(prefix, "", text);
		
		return outputPath;
	}


	/**
	 * prefer to use TestWordSentenceListTokenizer
	 * uncomment to run it
	 */
	//@Test
	public void TestTokenizationFrExec() {
		System.out.println("---------------------------------------------------");
		// --
		// hack the get a relative path 
		ClassLoader loader = Test.class.getClassLoader();
		String projectHome = (loader.getResource(".")+"").replaceAll("file:", "");
		//System.out.println(loader.getResource("."));
		//System.out.println(loader.getResource("fr/univnantes/lina/app/tokenizer/TextOutputTokenizerTest.class"));
		//projectHome = "";
		String rawTextFilePath= projectHome+ "exempleText-fr-raw.txt";
		String refTextFilePath = projectHome+ "exempleText-fr-reference-wst-spl.txt";
		String hypothesisFilePath = "/tmp/hypothesis-fr-wpl.txt";

		System.out.println("Debug: Raw reference file: "+rawTextFilePath);
		System.out.println("Debug: Tok reference file: "+refTextFilePath);

		// --
		System.out.println("Debug: Transform refText into a single whitespace separated line");
		String refTextString = IOUtilities.readTextFileAsString(refTextFilePath);
		refTextString = refTextString.replaceAll("\n", " ").trim() + "\n";
		String refTextSingleLineFilePath = "";

	
			refTextSingleLineFilePath = IOUtilities.createTempTextFile("refText", "", refTextString);
		

		// --
		System.out.println("Debug: Tokenize rawText with a wpl output");
		Process theProcess = null;
		String propertiesFilePath = projectHome+"simpleTokenizer.fr.wpl.properties";
		System.out.println("Debug: Properties file: "+propertiesFilePath);	
		System.out.println("Debug: Hypothesis generated file: "+hypothesisFilePath);


		try	{
			//IOUtilities.createTempTextFile("", "", refTextString);
			String tokenizeRawTextCommand = "java  -cp bin fr/univnantes/lina/app/tokenizer/RunTokenizerCommandLine --properties-file "
					+propertiesFilePath+" --input-file "+rawTextFilePath+" -v 2>/tmp/log | cut -f 1"; // > " + hypothesisFilePath;

			theProcess = Runtime.getRuntime().exec(makeBashCommand(tokenizeRawTextCommand));
		}
		catch(IOException e)
		{
			System.err.println("Error on exec() method");
			e.printStackTrace();  
		}

		String hypothesisText = "";
		try {
			BufferedReader inStream = null;
			inStream = new BufferedReader(
					new InputStreamReader( theProcess.getInputStream() ));  
			//System.out.println(inStream.readLine());
			String line = "";
			while ((line=inStream.readLine()) != null) {
				hypothesisText += line +  System.getProperty("line.separator");
			}
		}
		catch(IOException e)
		{
			System.err.println("Error on inStream.readLine()");
			e.printStackTrace();  
		}
		String hypTextSingleLineFilePath = "";

	
			hypTextSingleLineFilePath = IOUtilities.createTempTextFile("hypText", "", hypothesisText);
		

		// --
		System.out.println("Debug: Compute WER");
		try	{
			String computeWERCommand = "java -cp bin fr.univnantes.lina.app.eval.RunWordSequenceAligner -r "
					+refTextSingleLineFilePath+" -h "+hypTextSingleLineFilePath+ " --force-single-line  | tr -s '\t' ' ' | column -s' ' -t";

			theProcess = Runtime.getRuntime().exec(makeBashCommand(computeWERCommand));
		}
		catch(IOException e)
		{
			System.err.println("Error on exec() method");
			e.printStackTrace();  
		}
		try {
			BufferedReader inStream = null;
			inStream = new BufferedReader(
					new InputStreamReader( theProcess.getInputStream() ));  
			//System.out.println(inStream.readLine());
			String line = "";
			while ((line=inStream.readLine()) != null) {
				System.out.println(line);
			}
		}
		catch(IOException e)
		{
			System.err.println("Error on inStream.readLine()");
			e.printStackTrace();  
		}

		// -- 
	}

	/**
	 * 
	 * @param command
	 */
	private String[] makeBashCommand (String command) {
		String[] cmd = new String [3];
		cmd[0] = "/bin/bash";
		cmd[1] = "-c";
		cmd[2] = command;
		return cmd;	
	}

	/**
	 * 
	 * Il est possible de définir une classe main() dans une classe de tests qui va se charger d'exécuter les tests.
	 * http://www.jmdoudoux.fr/java/dej/chap-junit.htm
	 * @param args
	 */
	public static void main(String[] args) {
		 

		junit.textui.TestRunner.run(new TestSuite(TokenizerTest.class));

	}
}
