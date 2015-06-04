/** 
 * UIMA Type Mapper
 * Copyright (C) 2010  Nicolas Hernandez
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

package example.ae;

import java.util.*;
import org.apache.uima.*;
import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.cas.FSIndex;
import org.apache.uima.cas.FSIterator;
import org.apache.uima.cas.text.AnnotationIndex;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.cas.FSArray;
import org.apache.uima.jcas.cas.StringArray;


import org.apache.uima.TokenAnnotation;
//import org.apache.uima.SentenceAnnotation;
import example.types.source.POS;
import example.types.source.Sentence;
import example.types.source.Word;

/**
 * Class illustrating an AE which requires some types as INPUT
 * Ne fait rien mais permet d'importer des types cibles au niveau de son descripteur
 *  
 * <ul>
 * <li> 
 * </ul>
 * 
 * @author      Nicolas Hernandez
 * @version     %I%, %G%
 * @since       1.0
 */
public class PostAnnotationMapperAE extends JCasAnnotator_ImplBase {

	public void initialize(UimaContext context) {

	}

	//
	public void process(JCas aJCas) throws AnalysisEngineProcessException {

		
	}
}
