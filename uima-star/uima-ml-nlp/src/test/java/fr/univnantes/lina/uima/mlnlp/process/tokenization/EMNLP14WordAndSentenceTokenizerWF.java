/**
 * 
 */
package fr.univnantes.lina.uima.mlnlp.process.tokenization;

import static org.apache.uima.fit.factory.AnalysisEngineFactory.createEngineDescription;
import static org.apache.uima.fit.factory.CollectionReaderFactory.createReaderDescription;

import java.net.URL;

import org.apache.uima.analysis_engine.AnalysisEngineDescription;
import org.apache.uima.collection.CollectionReaderDescription;
import org.apache.uima.fit.pipeline.SimplePipeline;
import org.apache.uima.tools.components.FileSystemCollectionReader;

import fr.univnantes.lina.mlnlp.process.tokenization.characterTypeBasedTokenizer.CharacterTypeBasedWordAndSentenceTokenizer;
import fr.univnantes.lina.uima.mlnlp.process.tokenization.CharacterTypeBasedWordAndSentenceTokenizerAE;
import fr.univnantes.lina.uima.mlnlp.process.tokenization.WhitespaceRegexPatternTokenizerAE;



/**
 * Build a resource object representing a mbox file
 * And digest each mbox message
 * 
Info: MBoxResourceBuilderAE: emailAnalyzer.mbox.wf.MBoxResourceBuilderAE@1cf983d0
Info: MBoxResourceBuilderAE: # of processed JCas: 39249
Info: MBoxResourceBuilderAE: # of JCas which leads to a null mBoxMessage object: 0
Info: MBoxResourceBuilderAE: # of not null mBoxMessage objects with a null messageId: 19
Info: MBoxResourceBuilderAE: # of messages without inReplyTo but fixed by subject similarity: 717
Info: MBoxResourceBuilderAE: # of threads: 8485
Info: MboxReaderCR - # of created JCas: 39249
Info: MboxReaderCR - # of message with a null id: 19
Info: MboxReaderCR - # of created JCas: 39249
Info: MboxReaderCR - # of message with a null id: 19
 */
public class EMNLP14WordAndSentenceTokenizerWF {

	
	final static String LANG = "en";
	final static String ENCODING = "utf-8"; 
	
	//	final static String HOME = "/home/hernandez/workspace/data/ubuntu-users";
	//	final static String NAME = "ubuntu-users.mbox";
	//	final static String LANG = "en";
//	final static String ENCODING = "utf-8"; 
	
	public static void main(String[] args) throws Exception {
		//System.out.println("Debug: user.dir "+System.getProperty("user.dir"));
		
		//"fr/univnantes/lina/uima/textSegmenter/space-exploration.txt"
		// "../space-exploration.txt" // relatif à la classe test
		//String dataFileName = "../data";
		//URL dataUrl=  EMNLP14WordAndSentenceTokenizerWF.class.getResource(dataFileName);
		//System.out.println("Debug: dataUrl "+dataUrl);
		String dataPath; //= dataUrl.getFile();
		dataPath = "/home/hernandez-n/workspace/14/coling14-email-segmentation/data/bc3-labelled-multiFiles";
		dataPath = "/home/hernandez-n/workspace/data/ubuntu-users/email.message.tagged.segmColex.input";
		dataPath = "/media/ext4/workspace/data/ubuntu-users/email.message.starter-wi-inlineReplying.txt";
		// Creation of the external resource description
/*
		ExternalResourceDescription mBoxResourceDesc = createExternalResourceDescription(MBoxModel_Impl.class,
				new File("mbox.bin"));
*/

		// Declare annotators
		// And bind external resource to each Annotator individually
/*
		AnalysisEngineDescription aed4 = createEngineDescription(MBoxResourceBuilderAE.class,
				MBoxResourceBuilderAE.PARAM_MESSAGE_SAVE_DIR, HOME + "/email.message",
				MBoxResourceBuilderAE.PARAM_RESOURCE_SAVE_FILE, HOME + "/email.digest/email-mbox.res",
				MBoxResourceBuilderAE.RES_KEY, mBoxResourceDesc);
*/
		/*AnalysisEngineDescription wstd = createEngineDescription(WhitespaceTokenizer.class ,
				//WhitespaceTokenizer.SENTENCE_ANNOTATION_NAME, "org.apache.uima.SentenceAnnotation",
				//WhitespaceTokenizer.TOKEN_ANNOTATION_NAME, "org.apache.uima.TokenAnnotation",
				//WhitespaceTokenizer.TOKEN_TYPE_FEATURE_NAME, "coveredText"
				WhitespaceTokenizer.SENTENCE_ANNOTATION_NAME, "common.types.text.Sentence",
				WhitespaceTokenizer.TOKEN_ANNOTATION_NAME, "common.types.Token"
				); */
		AnalysisEngineDescription tokenizerd = createEngineDescription(
				//WhitespaceRegexPatternTokenizerAE.class
				CharacterTypeBasedWordAndSentenceTokenizerAE.class,
				CharacterTypeBasedWordAndSentenceTokenizerAE.PARAM_OUTPUT_DIR, "/media/ext4/workspace/data/ubuntu-users/email.message.starter-wi-inlineReplying.tok"
				);
/*		
		AnalysisEngineDescription texttilingd = createEngineDescription(JTextTileFitAE.class,
				JTextTileFitAE.PARAM_SENTENCE_TYPE, "common.types.text.Sentence",
				JTextTileFitAE.PARAM_TOKEN_TYPE, "common.types.text.Token",
				JTextTileFitAE.PARAM_SEGMENT_TYPE, "common.types.discourse.Segment",
				//JTextTileFitAE.PARAM_OUTPUT_DIR, "/tmp/output",
				//JTextTileFitAE.PARAM_OUTPUT_DIR, "/home/hernandez-n/workspace/14/coling14-email-segmentation/data/bc3-labelled-multiFiles-textTiling",
				JTextTileFitAE.PARAM_OUTPUT_DIR, "/media/ext4/workspace/data/ubuntu-users/email.message.starter-wi-inlineReplying.textTiling",
				JTextTileFitAE.PARAM_OUTPUT_FORMAT, 1,
				JTextTileFitAE.PARAM_DEBUG, false
				); 
	*/	
		//AnalysisEngineDescription outputd = createEngineDescription(TokenizationCC.class,
		//		TokenizationCC.PARAM_OUTPUT_DIR, "/tmp/output"
		//		);
		
		// Check the external resource was injected
		AnalysisEngineDescription aaed = createEngineDescription(tokenizerd); //, texttilingd); //
		//AnalysisEngine ae = createEngine(aaed);


		// Creation of the collection reader description 



		// je n'ai pas 1263377117.8388.2.camel@amd5000 + des pb d'indentation pourquoi ?
		CollectionReaderDescription crd = createReaderDescription(FileSystemCollectionReader.class,
				FileSystemCollectionReader.PARAM_INPUTDIR, dataPath, 
				FileSystemCollectionReader.PARAM_LANGUAGE, LANG,
				FileSystemCollectionReader.PARAM_ENCODING, ENCODING);


		// Run the pipeline
		SimplePipeline.runPipeline(crd,aaed); //aaed
	}


}
