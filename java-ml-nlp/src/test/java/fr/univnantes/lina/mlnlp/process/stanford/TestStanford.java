package fr.univnantes.lina.mlnlp.process.stanford;

import static org.junit.Assert.*;

import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.junit.Test;

import edu.stanford.nlp.dcoref.CorefChain;
import edu.stanford.nlp.dcoref.CorefCoreAnnotations.CorefChainAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.NamedEntityTagAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.PartOfSpeechAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.SentencesAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.TextAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.TokensAnnotation;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.semgraph.SemanticGraph;
import edu.stanford.nlp.semgraph.SemanticGraphCoreAnnotations.CollapsedCCProcessedDependenciesAnnotation;
import edu.stanford.nlp.trees.Tree;
import edu.stanford.nlp.trees.TreeCoreAnnotations.TreeAnnotation;
import edu.stanford.nlp.util.CoreMap;

public class TestStanford {

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
			+"Now any guesses as to what's going on, or how to fix it?\n"
			+"\n"
			+"Matthew.\n"
			+"\n"
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
	 * Test from http://nlp.stanford.edu/software/corenlp.shtml
	 */
	@Test
	public void testPipeline() {

		// creates a StanfordCoreNLP object, with POS tagging, lemmatization, NER, parsing, and coreference resolution 
		Properties props = new Properties();
		props.put("annotators", "tokenize, ssplit, pos, lemma, ner, parse, dcoref");
		StanfordCoreNLP pipeline = new StanfordCoreNLP(props);

	

		// create an empty Annotation just with the given text
		Annotation document = new Annotation(text);

		// run all Annotators on this text
		pipeline.annotate(document);

		// these are all the sentences in this document
		// a CoreMap is essentially a Map that uses class objects as keys and has values with custom types
		List<CoreMap> sentences = document.get(SentencesAnnotation.class);

		StringBuffer sentenceStringBuffer = new StringBuffer();
		for(CoreMap sentence: sentences) {
			// traversing the words in the current sentence
			// a CoreLabel is a CoreMap with additional token-specific methods
			for (CoreLabel token: sentence.get(TokensAnnotation.class)) {
				// this is the text of the token
				String word = token.get(TextAnnotation.class);
				// this is the POS tag of the token
				String pos = token.get(PartOfSpeechAnnotation.class);
				// this is the NER label of the token
				String ne = token.get(NamedEntityTagAnnotation.class);    
				sentenceStringBuffer.append(word);
				sentenceStringBuffer.append("/");
				sentenceStringBuffer.append(pos);
				sentenceStringBuffer.append("/");
				sentenceStringBuffer.append(ne);
				sentenceStringBuffer.append(" ");
			}
			System.out.println(sentenceStringBuffer.toString());
			sentenceStringBuffer = new StringBuffer();

			// this is the parse tree of the current sentence
			Tree tree = sentence.get(TreeAnnotation.class);

			// this is the Stanford dependency graph of the current sentence
			SemanticGraph dependencies = sentence.get(CollapsedCCProcessedDependenciesAnnotation.class);
		}

		// This is the coreference link graph
		// Each chain stores a set of mentions that link to each other,
		// along with a method for getting the most representative mention
		// Both sentence and token offsets start at 1!
		Map<Integer, CorefChain> graph = 
				document.get(CorefChainAnnotation.class);
	}

	
	/**
	 * Sentence Split and MorphoWord tokenize
	 * Test from http://nlp.stanford.edu/software/corenlp.shtml
	 */
	@Test
	public void testTokenize() {

		// creates a StanfordCoreNLP object, with sentence and word tokens
		Properties props = new Properties();
		props.put("annotators", "tokenize, ssplit");
		StanfordCoreNLP pipeline = new StanfordCoreNLP(props);

		// create an empty Annotation just with the given text
		Annotation document = new Annotation(text);

		// run all Annotators on this text
		pipeline.annotate(document);

		// these are all the sentences in this document
		// a CoreMap is essentially a Map that uses class objects as keys and has values with custom types
		List<CoreMap> sentences = document.get(SentencesAnnotation.class);

		StringBuffer sentenceStringBuffer = new StringBuffer();
		for(CoreMap sentence: sentences) {
			// traversing the words in the current sentence
			// a CoreLabel is a CoreMap with additional token-specific methods
			for (CoreLabel token: sentence.get(TokensAnnotation.class)) {
				// this is the text of the token
				String word = token.get(TextAnnotation.class);
				
				sentenceStringBuffer.append(word);
				sentenceStringBuffer.append(" ");
			}
			System.out.println(sentenceStringBuffer.toString());
			sentenceStringBuffer = new StringBuffer();
		}

	}

	
}
