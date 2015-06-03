/** 
 * Copyright (C) 2015  Nicolas Hernandez
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *     http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package fr.univnantes.lina.mlnlp.process.IRindexSearch;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopScoreDocCollector;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.RAMDirectory;
import org.apache.lucene.util.Version;

import fr.univnantes.lina.javautil.IOUtilities;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;


/**
 * given a sorted list of sentences,
 * filter them based on text similarity measure
 * 
 * based on http://www.lucenetutorial.com/lucene-in-5-minutes.html
 * 
 * 
 * @author hernandez
 *
 */
public class SlimFast {


	public static void main(String[] args) throws IOException, ParseException {

		// -1. get the file path of sentences to index and the output file path to write in the remaining sentences 
		String sentencesInputFilePath = "/home/hernandez/workspace/14/taln14-building-comparable-corpus/tmp/corpusTarget.pos.sortedByPpl"; // args[0];
		String sentencesOutputFilePath = "/tmp/corpusTarget.pos.sortedByPpl.slimfast"; //args[1];
		IOUtilities.deleteFile(sentencesOutputFilePath);
		
		System.err.println("Loading the sentences...");
		List<String> sentenceList =  IOUtilities.readTextFileAsStringList(sentencesInputFilePath);
		Map<Integer,String> sentenceMap = new TreeMap<Integer,String>();
		for (int i = 0 ; i < sentenceList.size() ; i++) {
			sentenceMap.put(i,stripText(sentenceList.get(i)));
		}

		// 0. Specify the analyzer for tokenizing text.
		//    The same analyzer should be used for indexing and searching
		StandardAnalyzer analyzer = new StandardAnalyzer(Version.LUCENE_46);

		// 1. create the index
		System.err.println("Indexing the sentences...");
		Directory index = new RAMDirectory();
		IndexWriterConfig config = new IndexWriterConfig(Version.LUCENE_46, analyzer);

		IndexWriter w = new IndexWriter(index, config);
		for(Integer i : sentenceMap.keySet()) {
			addDoc(w, sentenceMap.get(i), String.valueOf(i));
		}
		w.close();

		// 2. initial query
		//String initialSentence = sentenceList.get(0);
		//sentenceMap.remove(0);
		
		//Set<String> blackListedSentenceId = new HashSet<String>();
		System.err.println("Slim fast the sentences...");
		while (!sentenceMap.isEmpty()) {

			Integer nextId = sentenceMap.keySet().iterator().next();
			
			String selectedSentence = sentenceMap.get(nextId);
			sentenceMap.remove(Integer.valueOf(nextId));
			IOUtilities.writeToFS(selectedSentence+"\n", sentencesOutputFilePath, true);
			
			// the "title" arg specifies the default field to use
			// when no field is explicitly specified in the query.
			Query q = new QueryParser(Version.LUCENE_46, "text", analyzer).parse(selectedSentence);

			// 3. search
			int hitsPerPage = 10;
			IndexReader reader = DirectoryReader.open(index);
			IndexSearcher searcher = new IndexSearcher(reader);
			TopScoreDocCollector collector = TopScoreDocCollector.create(hitsPerPage, true);
			searcher.search(q, collector);
			ScoreDoc[] hits = collector.topDocs().scoreDocs;


			// 4. remove all the similar sentences 
			/// System.out.println("Found " + hits.length + " hits.");
			for(int i=0;i<hits.length;++i) {
				int docId = hits[i].doc;
				Document d = searcher.doc(docId);
				//System.out.println((i + 1) + ". " + d.get("id") + "\t" + d.get("text"));
				//blackListedSentenceId.add(d.get("id"));
				sentenceMap.remove(Integer.valueOf(d.get("id")));
			}

			
			// reader can only be closed when there
			// is no need to access the documents any more.
			reader.close();
		}


	}

	private static void addDoc(IndexWriter w, String text, String id) throws IOException {
		Document doc = new Document();
		doc.add(new TextField("text", text, Field.Store.YES));
		// use a string field for isbn because we don't want it tokenized
		doc.add(new StringField("id", id, Field.Store.YES));
		w.addDocument(doc);
	}
	
	/**
	 * return a text with lucene query characters escaped  
	 * @param text
	 * @return
	 */
	private static String stripText (String text) {
		return text.replaceAll("\\(+", "OBRA").replaceAll("\\)+", "CBRA")
				.replaceAll("\\[+", "OBRA").replaceAll("\\]+", "CBRA")
				.replaceAll("\\{+", "OBRA").replaceAll("\\}+", "CBRA")
				.replaceAll("\\*+", "SYMSTAR")
				.replaceAll("\\?+", "SYMQUEST")
				.replaceAll("\'+", "SYMQUOT")
				.replaceAll("\"+", "SYMQUOT")
				.replaceAll("\\/+", "SYMANTISLASH")
				.replaceAll("-+", "SYMDASH")
				.replaceAll("\\\\", "SYMSLASH")
				;
	}
	
}
