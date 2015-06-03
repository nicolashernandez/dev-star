package fr.univnantes.lina.mlnlp.process.evaluation;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;

import fr.univnantes.lina.javautil.IOUtilities;
import fr.univnantes.lina.mlnlp.process.evaluation.CorpusPartitionSplitter;

public class CorpusPartitionSplitterTest {

	@Test
	public void partition10linesInto10PartsTest() {
		String outputDirPath = "/tmp";
		String outputFilePrefix = "part";
		int partitionNumber = 10;
		int linesNumber = 10;
		List<String> corpusLines = new ArrayList<String>();
		for (int i = 0 ; i < linesNumber; i++)
			corpusLines.add(String.valueOf(i));
		CorpusPartitionSplitter.partition(corpusLines, outputDirPath, outputFilePrefix, partitionNumber);
		List<String> part1train = IOUtilities.readTextFileAsStringList(outputDirPath+"/"+outputFilePrefix+"1.train");
		corpusLines.remove(1);
		assertEquals(corpusLines, part1train);
	}
	
	@Test
	public void partition11linesInto10PartsTest() {
		String outputDirPath = "/tmp";
		String outputFilePrefix = "part";
		int partitionNumber = 10;
		int linesNumber = 11;
		List<String> corpusLines = new ArrayList<String>();
		for (int i = 0 ; i < linesNumber; i++)
			corpusLines.add(String.valueOf(i));
		CorpusPartitionSplitter.partition(corpusLines, outputDirPath, outputFilePrefix, partitionNumber);
		List<String> part9train = IOUtilities.readTextFileAsStringList(outputDirPath+"/"+outputFilePrefix+"9.train");
		corpusLines.remove(corpusLines.size()-1);
		corpusLines.remove(corpusLines.size()-1);
		//System.out.println(part9train);
		// System.out.println(corpusLines);

		assertEquals(corpusLines, part9train);
	}
	


	@Rule
	public TestName name = new TestName();

	@Before
	public void printSeparator()
	{
		System.out.println("\n=== " + name.getMethodName() + " =====================");
	}
}
