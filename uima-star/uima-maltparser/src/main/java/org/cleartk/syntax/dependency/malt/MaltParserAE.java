/*
 * Copyright (c) 2010, Regents of the University of Colorado 
 * All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 * 
 * Redistributions of source code must retain the above copyright notice, this list of conditions and the following disclaimer. 
 * Redistributions in binary form must reproduce the above copyright notice, this list of conditions and the following disclaimer in the documentation and/or other materials provided with the distribution. 
 * Neither the name of the University of Colorado at Boulder nor the names of its contributors may be used to endorse or promote products derived from this software without specific prior written permission. 
 * 
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE. 
 */
package org.cleartk.syntax.dependency.malt;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedSet;

import org.apache.uima.UimaContext;
import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.cas.FSIterator;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.tcas.Annotation;
import org.apache.uima.resource.ResourceInitializationException;
import common.types.syntax.dependency.DependencyNode;
import common.types.syntax.dependency.DependencyRelation;
import common.types.syntax.dependency.TopDependencyNode;
import org.cleartk.util.UIMAUtil;
import org.maltparser.MaltParserService;
import org.maltparser.core.exception.MaltChainedException;
import org.maltparser.core.options.OptionManager;
import org.maltparser.core.symbol.SymbolTable;
import org.maltparser.core.syntaxgraph.DependencyStructure;
	//import org.uimafit.component.JCasAnnotator_ImplBase;
	//import org.uimafit.descriptor.ConfigurationParameter;
	//import org.uimafit.factory.AnalysisEngineFactory;
	//import org.uimafit.factory.ConfigurationParameterFactory;

import fr.univnantes.lina.uima.common.cas.AnnotationCollectionUtils;
import fr.univnantes.lina.uima.common.cas.ContextUtils;
import fr.univnantes.lina.uima.common.cas.FeatureUtils;
import fr.univnantes.lina.uima.common.cas.JCasSofaViewUtils;
import fr.univnantes.lina.uima.common.cas.ContextUtils.ContextFile;
import fr.univnantes.lina.mlnlp.connectors.ftb.FTBPOSTagAnalyser;
/**
 * <br>
 * Copyright (c) 2010, Regents of the University of Colorado <br>
 * All rights reserved.
 * 
 * Modified by: Nicolas Hernandez
 * 
 */
public class MaltParserAE extends JCasAnnotator_ImplBase {

	/** Name of the parameter for the model import path */
	public static String MODEL_FILE_TO_IMPORT_PARAM  = "ModelFileName";

	/**
	 * Name of the parameter for the Sentence Annotation Type 
	 */
	public static String SENTENCE_PARAM  = "SentenceType";

	/**
	 * Name of the parameter for the Token Annotation Type 
	 */
	public static String WORD_PARAM  = "WordType";

	/** 
	 * CONLL FORMAT 
	 * http://ilk.uvt.nl/conll/#dataformat
	 * Field number:	 Field name:	 Description:
1	 ID	 Token counter, starting at 1 for each new sentence.
2	 FORM	 Word form or punctuation symbol.
3	 LEMMA	 Lemma or stem (depending on particular data set) of word form, or an underscore if not available.
4	CPOSTAG	 Coarse-grained part-of-speech tag, where tagset depends on the language.
5	 POSTAG	 Fine-grained part-of-speech tag, where the tagset depends on the language, or identical to the coarse-grained part-of-speech tag if not available.
6	 FEATS	 Unordered set of syntactic and/or morphological features (depending on the particular language), separated by a vertical bar (|), or an underscore if not available.
7	 HEAD	 Head of the current token, which is either a value of ID or zero ('0'). Note that depending on the original treebank annotation, there may be multiple tokens with an ID of zero.
8	 DEPREL	 Dependency relation to the HEAD. The set of dependency relations depends on the particular language. Note that depending on the original treebank annotation, the dependency relation may be meaningfull or simply 'ROOT'.
9	 PHEAD	 Projective head of current token, which is either a value of ID or zero ('0'), or an underscore if not available. Note that depending on the original treebank annotation, there may be multiple tokens an with ID of zero. The dependency structure resulting from the PHEAD column is guaranteed to be projective (but is not available for all languages), whereas the structures resulting from the HEAD column will be non-projective for some sentences of some languages (but is always available).
10	 PDEPREL	 Dependency relation to the PHEAD, or an underscore if not available. The set of dependency relations depends on the particular language. Note that depending on the original treebank annotation, the dependency relation may be meaningfull or simply 'ROOT'.
	 * 
	 * Example
	 * 
//	1       Il      cln     CL      CLS     s=suj   2       suj     _       _
//	2       est     être    V       V       m=ind|n=s|p=3|t=pst     0       root    _       _
//	3       violet  violet  A       ADJ     g=m|n=s|s=qual  2       ats     _       _
//	4       .       .       PONCT   PONCT   s=s     2       ponct   _       _
	 * 
	 */
	/** Name of the parameter for the lemma feature */
	public static String COVEREDTEXT_PARAM  = "CoveredTextFeature";

	/** Name of the parameter for the lemma feature */
	public static String LEMMA_PARAM  = "LemmaFeature";
	/** Name of the parameter for the coarseGrainedPosTag feature name */
	public static String COARSEPOS_PARAM  = "CoarseGrainedPosFeature";
	/** Name of the parameter for the fineGrainedPosTag feature name */
	public static String FINEPOS_PARAM  = "FineGrainedPosFeature";
	/** Name of the parameter for the mood feature */
	public static String MOOD_PARAM  = "MoodFeature";
	/** Name of the parameter for the gender feature */
	public static String GENDER_PARAM  = "GenderFeature";
	/** Name of the parameter for the number feature */
	public static String NUMBER_PARAM  = "NumberFeature";
	/** Name of the parameter for the person feature */
	public static String PERSON_PARAM  = "PersonFeature";
	/** Name of the parameter for the tense feature */
	public static String TENSE_PARAM  = "TenseFeature";
	/** Name of the parameter for the subcat feature */
	public static String SUBCAT_PARAM  = "SubcatFeature";


	/**
	 * 
	 */
	public static String DEFAULT_COVEREDTEXT  = "coveredText";



	/** The path to the file where the model will be read */
	//private String modelFileName;
	private ContextFile contextualizedModelFile;
	private String sentenceType;
	private String wordType;
	private String coveredTextFeature;

	private String lemmaFeature;
	private String coarseGrainedPosFeature;
	private String fineGrainedPosFeature;
	private String moodFeature;
	private String genderFeature;
	private String numberFeature;
	private String personFeature;
	private String tenseFeature;
	private String subcatFeature;

	//public static final String ENGMALT_RESOURCE_NAME = "/models/engmalt.linear.mco";


	//public static AnalysisEngineDescription getDescription() throws ResourceInitializationException {
	//	// get the resource path and strip the ".mco" suffix
	//	String fileName = MaltParser.class.getResource(ENGMALT_RESOURCE_NAME).getFile();
	//	String fileBase = fileName.substring(0, fileName.length() - 4);
	//	return getDescription(fileBase);
	//}

	//public static AnalysisEngineDescription getDescription(String modelFileName)
	//throws ResourceInitializationException {
	//	return AnalysisEngineFactory.createPrimitiveDescription(
	//			MaltParser.class,
	//			DependencyComponents.TYPE_SYSTEM_DESCRIPTION,
	//			PARAM_MODEL_FILE_NAME,
	//			modelFileName);
	//}

	//@ConfigurationParameter(description = "The path to the model file, without the .mco suffix.", mandatory = true)
	//private String modelFileName;

	//public static final String PARAM_MODEL_FILE_NAME = ConfigurationParameterFactory
	//.createConfigurationParameterName(MaltParser.class, "modelFileName");

	private MaltParserService service;

	@Override
	public void initialize(UimaContext context) throws ResourceInitializationException {
		super.initialize(context);

		String currentModelFileName = 
				(String) context.getConfigParameterValue(MODEL_FILE_TO_IMPORT_PARAM);

		contextualizedModelFile = ContextUtils.resolveRelativeFilePath(this, context, currentModelFileName);
		
		if (contextualizedModelFile == null) {
			// in that case, the multiple deployment cannot be allowed 
			throw new ResourceInitializationException(new Exception("Could not find the model file to load."));
		}
		

		this.sentenceType = (String) context.getConfigParameterValue(SENTENCE_PARAM);
		this.wordType = (String) context.getConfigParameterValue(WORD_PARAM);
		this.coveredTextFeature = (String) context.getConfigParameterValue(COVEREDTEXT_PARAM);
		if (this.coveredTextFeature == null) this.coveredTextFeature = DEFAULT_COVEREDTEXT;
		this.lemmaFeature = (String) context.getConfigParameterValue(LEMMA_PARAM);
		this.coarseGrainedPosFeature = (String) context.getConfigParameterValue(COARSEPOS_PARAM);
		this.fineGrainedPosFeature = (String) context.getConfigParameterValue(FINEPOS_PARAM);
		this.moodFeature = (String) context.getConfigParameterValue(MOOD_PARAM);
		this.genderFeature = (String) context.getConfigParameterValue(GENDER_PARAM);
		this.numberFeature = (String) context.getConfigParameterValue(NUMBER_PARAM);
		this.personFeature = (String) context.getConfigParameterValue(PERSON_PARAM);
		this.tenseFeature = (String) context.getConfigParameterValue(TENSE_PARAM);
		this.subcatFeature = (String) context.getConfigParameterValue(SUBCAT_PARAM);

		try {
			OptionManager.instance().loadOptionDescriptionFile();
			OptionManager.instance().getOptionDescriptions().generateMaps();
			this.service = new MaltParserService();
			File modelFile = contextualizedModelFile.getFile();

			String fileName = modelFile.getName();
			String workingDirectory = modelFile.getParent();
			String command = String.format("-c %s -m parse -w %s", fileName, workingDirectory);
			this.service.initializeParserModel(command);
		} catch (MaltChainedException e) {
			throw new ResourceInitializationException(e);
		}
	}

	@Override
	public void collectionProcessComplete() throws AnalysisEngineProcessException {
		super.collectionProcessComplete();
		try {
			this.service.terminateParserModel();
		} catch (MaltChainedException e) {
			throw new AnalysisEngineProcessException(e);
		}
	}

	
	
	@Override
	public void process(JCas jCas) throws AnalysisEngineProcessException {

		FSIterator<Annotation> sentenceFSIterator = null;

		sentenceFSIterator 
		= jCas.getAnnotationIndex(JCasSofaViewUtils.getJCasType(jCas, sentenceType)).iterator();

		while (sentenceFSIterator.hasNext()) {
			Annotation sentenceAnnotation = sentenceFSIterator.next();
			FSIterator<Annotation> subSentenceAnnotationIterator = AnnotationCollectionUtils.subiterator(jCas, sentenceAnnotation); 
			List<Annotation> wordList = new ArrayList<Annotation> ();
			
			// convert tokens into MaltParser input array
			List<String> inputStrings = new ArrayList<String>();
			int lineNo = -1;
			while (subSentenceAnnotationIterator.hasNext()) {
				Annotation aSubsumedAnnotation = subSentenceAnnotationIterator.next();
				if (aSubsumedAnnotation.getType().getName().equalsIgnoreCase(wordType)) {
					
					wordList.add(aSubsumedAnnotation);
					
					FeatureUtils.getFeatureValue(aSubsumedAnnotation, coveredTextFeature);
					lineNo += 1;
					String coveredText = (String) FeatureUtils.getFeatureValue(aSubsumedAnnotation, coveredTextFeature);
					String lemma = "_";
					if (lemmaFeature != null) lemma = (String) FeatureUtils.getFeatureValue(aSubsumedAnnotation, lemmaFeature);
					String coarseGrainedPos = "_";
					if (coarseGrainedPosFeature != null) coarseGrainedPos = (String) FeatureUtils.getFeatureValue(aSubsumedAnnotation, coarseGrainedPosFeature);
					String fineGrainedPos = "_";
					if (fineGrainedPosFeature != null) fineGrainedPos = (String) FeatureUtils.getFeatureValue(aSubsumedAnnotation, fineGrainedPosFeature);
					
					String mph= "";
					
					// TODO right now mood and tense are inferred from mood feature, should be viven as a feature value
					//if (moodFeature != null) mood = "m="+(String) FeatureUtils.getFeatureValue(aSubsumedAnnotation, moodFeature);

					
					if (moodFeature != null) mph += "m="+FTBPOSTagAnalyser.moodOfMood ((String) FeatureUtils.getFeatureValue(aSubsumedAnnotation, moodFeature)) + "|";
					if (genderFeature != null) mph += "g="+(String) FeatureUtils.getFeatureValue(aSubsumedAnnotation, genderFeature)+ "|";
					if (numberFeature != null) mph += "n="+(String) FeatureUtils.getFeatureValue(aSubsumedAnnotation, numberFeature)+ "|";
					if (personFeature != null) mph += "p="+(String) FeatureUtils.getFeatureValue(aSubsumedAnnotation, personFeature)+ "|";
					if (moodFeature != null) mph += "t="+FTBPOSTagAnalyser.tenseOfMood ((String) FeatureUtils.getFeatureValue(aSubsumedAnnotation, moodFeature))+ "|";
		
					//String tense = "";
					//if (tenseFeature != null) tense = "t="+(String) FeatureUtils.getFeatureValue(aSubsumedAnnotation, tenseFeature);
					if (subcatFeature != null) mph += "s="+(String) FeatureUtils.getFeatureValue(aSubsumedAnnotation, subcatFeature)+ "|";
					
					// TODO mood to mood+tense
					if (!mph.equalsIgnoreCase("")) mph = mph.substring(0, mph.length()-1);
					
					// line format is %1$d\t%2$s\t%3$s\t%4$s\t%5$s\t%6$s
					//				1       Il      cln     CL      CLS     s=suj   2       suj     _       _
					//				2       est     être    V       V       m=ind|n=s|p=3|t=pst     0       root    _       _
					//				3       violet  violet  A       ADJ     g=m|n=s|s=qual  2       ats     _       _
					//				4       .       .       PONCT   PONCT   s=s     2       ponct   _       _
					//
					// line format is <index>\t<word>\t_\t<pos>\t<pos>\t_
					//String lineFormat = "%1$d\t%2$s\t_\t%3$s\t%3$s\t_";
					//					inputStrings.add(String.format(lineFormat, lineNo + 1, text, pos));
					String lineFormat = "%1$d\t%2$s\t%3$s\t%4$s\t%5$s\t%6$s";
					inputStrings.add(String.format(lineFormat, lineNo + 1, coveredText, lemma, coarseGrainedPos, fineGrainedPos, mph));
					
	
				}
			}

			try {
				// parse with MaltParser
				String[] input = inputStrings.toArray(new String[inputStrings.size()]);
				//System.out.println("Debug: DependencyStructure graph = this.service.parse(input);"+ inputStrings);

//				System.out.print("Debug: ");
//				for (int i = 0 ; i < input.length ; i++ ) {
//					System.out.print(">"+input[i]+"<");
//				}
//				System.out.println();

				if (inputStrings.size() != 0) {
					DependencyStructure graph = this.service.parse(input);

					// convert MaltParser structure into annotations
					Map<Integer, DependencyNode> nodes = new HashMap<Integer, DependencyNode>();
					SortedSet<Integer> tokenIndices = graph.getTokenIndices();
					for (int i : tokenIndices) {
						org.maltparser.core.syntaxgraph.node.DependencyNode maltNode = graph.getTokenNode(i);
						//Token token = tokens.get(maltNode.getIndex() - 1);
						Annotation token = wordList.get(maltNode.getIndex() - 1);
						DependencyNode node;
						if (maltNode.getHead().getIndex() != 0) {
							node = new DependencyNode(jCas, token.getBegin(), token.getEnd());
						} else {
							node = new TopDependencyNode(jCas, token.getBegin(), token.getEnd());
						}
						nodes.put(i, node);
					}

					// add relation annotations
					Map<DependencyNode, List<DependencyRelation>> headRelations;
					headRelations = new HashMap<DependencyNode, List<DependencyRelation>>();
					Map<DependencyNode, List<DependencyRelation>> childRelations;
					childRelations = new HashMap<DependencyNode, List<DependencyRelation>>();
					SymbolTable table = graph.getSymbolTables().getSymbolTable("DEPREL");
					for (int i : tokenIndices) {
						org.maltparser.core.syntaxgraph.node.DependencyNode maltNode = graph.getTokenNode(i);
						int headIndex = maltNode.getHead().getIndex();
						if (headIndex != 0) {
							String label = maltNode.getHeadEdge().getLabelSymbol(table);
							DependencyNode node = nodes.get(i);
							DependencyNode head = nodes.get(headIndex);
							DependencyRelation rel = new DependencyRelation(jCas);
							rel.setHead(head);
							rel.setChild(node);
							rel.setRelation(label);
							rel.addToIndexes();
							if (!headRelations.containsKey(node)) {
								headRelations.put(node, new ArrayList<DependencyRelation>());
							}
							headRelations.get(node).add(rel);
							if (!childRelations.containsKey(head)) {
								childRelations.put(head, new ArrayList<DependencyRelation>());
							}
							childRelations.get(head).add(rel);
						}
					}

					// finalize nodes: add links between nodes and relations
					for (DependencyNode node : nodes.values()) {
						node.setHeadRelations(UIMAUtil.toFSArray(jCas, headRelations.get(node)));
						node.setChildRelations(UIMAUtil.toFSArray(jCas, childRelations.get(node)));
						node.addToIndexes();
					}
				}

			} catch (MaltChainedException e) {
				throw new AnalysisEngineProcessException(e);
			}
		}

	}
}
