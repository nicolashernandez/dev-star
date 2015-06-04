package workflow.sandbox;

/*******************************************************************************
 * Copyright 2010
 * Ubiquitous Knowledge Processing (UKP) Lab
 * Technische UniversitÃ¤t Darmstadt
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/

import static org.apache.uima.fit.factory.AnalysisEngineFactory.createEngineDescription;
import static org.apache.uima.fit.factory.CollectionReaderFactory.createReaderDescription;

import org.apache.uima.analysis_engine.AnalysisEngineDescription;
import org.apache.uima.collection.CollectionReaderDescription;
import org.apache.uima.fit.component.CasDumpWriter;
import org.apache.uima.fit.pipeline.SimplePipeline;

import de.tudarmstadt.ukp.dkpro.core.dictionaryannotator.DictionaryAnnotator;
import de.tudarmstadt.ukp.dkpro.core.examples.type.Name;
import de.tudarmstadt.ukp.dkpro.core.io.text.TextReader;
import de.tudarmstadt.ukp.dkpro.core.opennlp.OpenNlpPosTagger;
import de.tudarmstadt.ukp.dkpro.core.tokit.BreakIteratorSegmenter;

public class DkproAslTest  {
		public static void main(String[] args)
			throws Exception
		{
			CollectionReaderDescription reader = createReaderDescription(
					TextReader.class,
					//TextReader.PARAM_SOURCE_LOCATION, "/media/ext4/workspace/data/samples/doc/en.text.utf8/",
					TextReader.PARAM_SOURCE_LOCATION, "/media/ext4/workspace/data/samples/doc/fr.text.utf8/",
					TextReader.PARAM_PATTERNS, "[+]*.txt",
					TextReader.PARAM_LANGUAGE, "fr");

			AnalysisEngineDescription tokenizer = createEngineDescription(
					BreakIteratorSegmenter.class);
			
			// where to know the class and its parameter definition
			// http://dkpro-core-asl.googlecode.com/svn/de.tudarmstadt.ukp.dkpro.core-asl/tags/latest-release/apidocs/de/tudarmstadt/ukp/dkpro/core/opennlp/OpenNlpPosTagger.html
			// where we learn where to search for the tagset mapping file
			// /media/ext4/workspace/dkpro-core-asl-svn$ cat de.tudarmstadt.ukp.dkpro.core.opennlp-asl/src/main/java/de/tudarmstadt/ukp/dkpro/core/opennlp/OpenNlpPosTagger.java
			// where the mapping file is
			// ~/.m2/repository/de/tudarmstadt/ukp/dkpro/core/de.tudarmstadt.ukp.dkpro.core.api.lexmorph-asl/1.6.1$ unzip  de.tudarmstadt.ukp.dkpro.core.api.lexmorph-asl-1.6.1.jar -d  /tmp/lexmorph
			AnalysisEngineDescription tagger = createEngineDescription(
					OpenNlpPosTagger.class,
					OpenNlpPosTagger.PARAM_LANGUAGE, "fr"
					,OpenNlpPosTagger.PARAM_MODEL_LOCATION, "/home/hernandez/workspace/lina-star/uima-star/uima-opennlp/src/main/resources/opennlp/models/fr/fr-cat.bin"
					//,OpenNlpPosTagger.PARAM_POS_MAPPING_LOCATION, "/home/hernandez/workspace/lina-star/uima-star/uima-opennlp/src/main/resources/opennlp/models/fr/fr-cat.map"
					,OpenNlpPosTagger.PARAM_VARIANT, "ftb-pos" 
					);

	 		AnalysisEngineDescription nameFinder = createEngineDescription(
					DictionaryAnnotator.class,
					DictionaryAnnotator.PARAM_MODEL_LOCATION, "src/test/resources/dictionaries/names.txt",
					DictionaryAnnotator.PARAM_ANNOTATION_TYPE, Name.class);

			AnalysisEngineDescription writer = createEngineDescription(
					CasDumpWriter.class,
					CasDumpWriter.PARAM_OUTPUT_FILE, "/tmp/target/output.txt");

//			SimplePipeline.runPipeline(reader, tokenizer, nameFinder, writer);
			SimplePipeline.runPipeline(reader, tokenizer, tagger, writer);

		}
	}