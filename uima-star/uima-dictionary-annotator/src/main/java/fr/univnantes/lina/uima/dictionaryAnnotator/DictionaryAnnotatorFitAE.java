/** 
c * UIMA dictionary annotator
 * Copyright (C) 2011  Nicolas Hernandez
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
package fr.univnantes.lina.uima.dictionaryAnnotator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;


import org.apache.uima.UimaContext;
import org.apache.uima.analysis_component.AnalysisComponent;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.cas.FSIterator;
import org.apache.uima.cas.Type;
import org.apache.uima.fit.descriptor.ConfigurationParameter;
import org.apache.uima.fit.descriptor.ExternalResource;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.cas.StringArray;
import org.apache.uima.jcas.tcas.Annotation;
import org.apache.uima.resource.ResourceInitializationException;

import fr.univnantes.lina.mlnlp.model.prefixtree.PrefixTree;
import fr.univnantes.lina.uima.common.cas.AnnotationCollectionUtils;
import fr.univnantes.lina.uima.common.cas.AnnotationUtils;
import fr.univnantes.lina.uima.common.cas.FeatureUtils;
import fr.univnantes.lina.uima.common.cas.ResourceUtils;
import fr.univnantes.lina.uima.common.resources.CSVDictionaryResource;

/**
 * Annotate text offsets corresponding to matched expressions at this offsets.
 * The expressions come from a dictionary It is possible to search any feature
 * value loading the resource (a resource can alternatively be loaded either by
 * a resource declaration and bounding or via a parameter setting and
 * consequently a call to a doLoad method parsing the resource, building the
 * prefix tree from a specific part of each entry, recognizing the entries of
 * the resource in a view, removing covered annotations
 * 
 * @author rocheteau,
 * @author hernandez
 * 201406 no more commonAE extension but uimaFit
 * 201201 revised by hernandez to get more abstraction (commonAE), handle csv
 *         resource file, bug correction (last char to tokenize for simple and
 *         compound words, char to int (unicode))
 *         
 */
public class DictionaryAnnotatorFitAE extends  org.apache.uima.fit.component.JCasAnnotator_ImplBase {

	//private static String DEFAULT_DOCUMENT_ANNOTATION = "uima.tcas.DocumentAnnotation";

	

	
	
	/*
	 * PARAMETERS NAMES
	 */

	/**
	 * under which annotation the segmentation should be performed (if several instances, only the first one is considered)
	 */
	public static final String PARAM_COVERING_ANNOTATION = "coveringAnnotation";
	@ConfigurationParameter(name = PARAM_COVERING_ANNOTATION, mandatory = false, defaultValue="uima.tcas.DocumentAnnotation")
	private String coveringAnnotation;
	

	/**
	 * Name of the annotation type to create for each line of the csv files
	 */
	public static final String PARAM_OUTPUT_ANNOTATION = "outputAnnotation";
	@ConfigurationParameter(name = PARAM_OUTPUT_ANNOTATION, mandatory = true)
	private String outputAnnotation;
	
	
	/**
	 * Parameter name for the csv column id and the feature names to associate
	 * with from the OutputAnnotation
	 */
	public static final String PARAM_FEATURES_FROM_COLUMNS = "featuresFromColumnsStringArray";
	@ConfigurationParameter(name = PARAM_FEATURES_FROM_COLUMNS, mandatory = false )
	private String [] featuresFromColumnsStringArray;
	


	/**
	 * Parameter name for associating the CSV column values to a single list
	 * feature It is an alternative or a complementary approach to the
	 * ColumnsToFeatures
	 */
	public static final String PARAM_FEATURES_TO_A_LIST_FEATURE = "arrayFeatureName";
	@ConfigurationParameter(name = PARAM_FEATURES_TO_A_LIST_FEATURE, mandatory = false )
	private String arrayFeatureName;
	
	
	
	/**
	 * Parameter name of the CSV separator by default a tabulation, '\t', but
	 * can be set to comma ',' ...
	 */
	public static final String PARAM_CSV_SEPARATOR = "csvSeparatorString";
	@ConfigurationParameter(name = PARAM_CSV_SEPARATOR, mandatory = false, defaultValue="\t")
	private String csvSeparatorString;
	

	/**
	 * Parameter name of the feature path to search
	 */
	public static final String PARAM_FEATUREPATH_TO_SEARCH = "featurePathToSearch";
	@ConfigurationParameter(name = PARAM_FEATUREPATH_TO_SEARCH, mandatory = false, defaultValue="uima.tcas.DocumentAnnotation:coveredText")
	private String featurePathToSearch;


	
	/**
	 * Parameter name of the feature path to search
	 */
	public static final String PARAM_EXACT_MATCH = "exactMatchBoolean";
	@ConfigurationParameter(name = PARAM_EXACT_MATCH, mandatory = false , defaultValue="false")
	private Boolean exactMatchBoolean;
	
	/**
	 * Parameter name of the case sensitive option
	 */
	public static final String PARAM_CASE_SENSITIVE = "caseSensitiveBoolean";
	@ConfigurationParameter(name = PARAM_CASE_SENSITIVE, mandatory = false , defaultValue="true")
	private Boolean caseSensitiveBoolean;
	
	
	
	/**
	 * Parameter name of AnnotateAnnotationWhereFeaturePathBelongs (see the
	 * descriptor)
	 */
	public static final String PARAM_ANNOTATE_ANNOTATION_AND_NOT_FEATURE_VALUE = "annotateAnnotationWhereFeaturePathBelongs";
	@ConfigurationParameter(name = PARAM_ANNOTATE_ANNOTATION_AND_NOT_FEATURE_VALUE, mandatory = false, defaultValue="false")
	private Boolean annotateAnnotationWhereFeaturePathBelongs;

	
	
	
	/*
	 * PARAMETERS DEFAULT VALUES
	 */

	/**
	 * Name of the default separator between columnId and the feature name
	 * associated
	 */
	private static String DEFAULT_COLUMNID_AND_FEATURENAME_SEPARATOR = "->";



	/*
	 * LOCAL VARIABLES
	 */
	public final static String RESOURCE_CSV_DICT = "resourceCSVDictKey";
	@ExternalResource(key = RESOURCE_CSV_DICT)
	private CSVDictionaryResource resource;
	
	
	
	/**
	 * list couples of csv column id and feature names to associate with from
	 * the OutputAnnotation the syntax is 0 -> begin if the values of the first
	 * column should be set to the begin feature of the OutputAnnotation
	 */
	private HashMap<Integer, String> colIdFeatNameHashMap = null;



	/**
	 * Feature path to search
	 */
	private String annotationToSearch = "";
	private String featureToSearch = "";


	//private Boolean annotateAnnotationWhereFeaturePathBelongs = false;

	/*
	 * GETTER and SETTER
	 */
	private PrefixTree getRootNode() {
		return this.resource.getRoot();
	}

	/**
	 * Get the values of the configuration parameters
	 * 
	 * @see AnalysisComponent#initialize(UimaContext)
	 */
	@Override
	public void initialize(UimaContext aContext)
			throws ResourceInitializationException {

		// SUPER PARAMATERS
		super.initialize(aContext);

		// CURRENT PARAMETERS

		// Get the input csv separator
		//csvSeparatorString = (String) aContext
		//		.getConfigParameterValue(PARAM_CSV_SEPARATOR);
		//if (csvSeparatorString == null) {
		//	csvSeparatorString = DEFAULT_CSV_SEPARATOR;
		//}

		// Get the annotation name
		//annotationToProcessString = (String) aContext
		//		.getConfigParameterValue(PARAM_OUTPUT_ANNOTATION);
		
		
		// annotationFromLineString = (String)
		// aContext.getConfigParameterValue(PARAM_NAME_OUTPUT_ANNOTATION);
		// if (debug)
		// System.out.println("Warning: annotation to create/update : "+
		// annotationToProcessString );

		// Get the features name
		//String[] featuresFromColumnsStringArray = (String[]) aContext
		//		.getConfigParameterValue(PARAM_COLUMNS_TO_FEATURES);
		
		
		colIdFeatNameHashMap = new HashMap<Integer, String>();
		if (featuresFromColumnsStringArray != null)
			for (int i = 0; i < featuresFromColumnsStringArray.length; i++) {
				String[] ColIdFeatNameCouple = featuresFromColumnsStringArray[i]
						.split(DEFAULT_COLUMNID_AND_FEATURENAME_SEPARATOR);
				if (ColIdFeatNameCouple.length == 2)
					colIdFeatNameHashMap.put(
							Integer.parseInt(ColIdFeatNameCouple[0].trim()),
							ColIdFeatNameCouple[1].trim());
				else {
					System.err.println("Warning: Wrong syntax to the PARAM_COLUMNS_TO_FEATURES "
							+ " paramater with the following line "
							+ ColIdFeatNameCouple.toString());
					// String errorMsg =
					// "Warning: Wrong syntax to the "+PARAM_FEATURES_TO_COLUMNS+" paramater with the following line "+
					// ColIdFeatNameCouple.toString();
					// throw new AnnotatorInitializationException(errorMsg,new
					// Object[]{errorMsg});
				}
			}
		// for (int i = 0; i < featuresToColumnsStringArray.length ; i++) {
		// System.out.println("Debug: fc"+ featuresToColumnsStringArray[i] +
		// " i"+ i);
		// }

		//
		//arrayFeatureName = (String) aContext
		//		.getConfigParameterValue(PARAM_FEATURES_TO_A_LIST_FEATURE);

		//exactMatchBoolean = (Boolean) aContext
		//		.getConfigParameterValue(PARAM_EXACT_MATCH);
		//if (exactMatchBoolean == null)
		//	exactMatchBoolean = DEFAULT_EXACT_MATCH_VALUE;

		//annotateAnnotationWhereFeaturePathBelongs = (Boolean) aContext
		//		.getConfigParameterValue(PARAM_ANNOTATE_ANNOTATION_AND_NOT_FEATURE_VALUE);
		//if (annotateAnnotationWhereFeaturePathBelongs == null)
		//	annotateAnnotationWhereFeaturePathBelongs = DEFAULT_ANNOTATE_ANNOTATION_AND_NOT_FEATURE_VALUE;

		// Get the featurepath to search
		//String featurePathToSearch = (String) aContext
		//		.getConfigParameterValue(PARAM_FEATUREPATH_TO_SEARCH);
		//if (featurePathToSearch == null)
		//	featurePathToSearch = DEFAULT_FEATUREPATH_TO_SEARCH;

		annotationToSearch = featurePathToSearch.substring(0,
				featurePathToSearch.lastIndexOf(":"));
		featureToSearch = featurePathToSearch.substring(
				featurePathToSearch.lastIndexOf(":") + 1,
				featurePathToSearch.length());
		// System.out.println("Debug: annotationTypeToSearch>"+annotationToSearch+
		// "< featureToSearch>"+featureToSearch+ "<" );

		
		//caseSensitiveBoolean = (Boolean) aContext
		//		.getConfigParameterValue(PARAM_CASE_SENSITIVE);
		//if (caseSensitiveBoolean == null)
		//	caseSensitiveBoolean = DEFAULT_CASE_SENSITIVE_VALUE;
		
		//
		// FIXED 20161019 after having commented the two following lines we get access here to the resource content
		//this.resource = new CSVDictionaryResource(caseSensitiveBoolean);
		//ResourceUtils.loadAResource(this.resource, aContext,
		//		"DictionaryResource", "DictionaryResourceFile");
		// display the content of the resource
		//System.out.println("Debug: resource.getRoot().toString() \n"+resource.getRoot().toString());
		
		
	}


	/**
	 * 
	 */
	@Override
	public void process(JCas aJCas) throws AnalysisEngineProcessException {

		// System.out.println("Debug: inputViewJCas.getViewName() "+inputViewJCas.getViewName());

		Type documentType = aJCas.getTypeSystem().getType(coveringAnnotation);
		Annotation documentAnnotation = (Annotation) aJCas.getAnnotationIndex(documentType).iterator().get(); 
		
		FSIterator subContextAnnotationIterator = AnnotationCollectionUtils
				.subiterator(aJCas, documentAnnotation);

		Boolean atLeastOneAnnotationToSearchFoundAmoungTheSubContextAnnotation = false;
		// for each annotation to search under the context annotation
		while (subContextAnnotationIterator.hasNext()) {
			Annotation aSubAnnotation = (Annotation) subContextAnnotationIterator
					.next();

			// System.out.println("Debug: annotationToSearch "+annotationToSearch);
			// System.out.println("Debug: aSubAnnotation.getClass().getName() "+aSubAnnotation.getClass().getName());

			// System.out.println("Debug: annotationToSearch.getClass().getName() "+annotationToSearch.getClass().getName()+" wordTypeName"+
			// wordTypeName);
			if (aSubAnnotation.getClass().getName()
					.equalsIgnoreCase(annotationToSearch)) {
				atLeastOneAnnotationToSearchFoundAmoungTheSubContextAnnotation = true;

				// System.out.println("Debug: if (aSubAnnotation.getClass().getName().equalsIgnoreCase(annotationToSearch)) {");

				// System.out.println("Debug: anAnnotationToSearch.getBegin()>"+aSubAnnotation.getBegin()+
				// "< anAnnotationToSearch.getEnd()>"+aSubAnnotation.getEnd()+
				// "<" );
				//
				String featurePathValue = (String) FeatureUtils
						.getFeatureValue(aSubAnnotation, featureToSearch); // contextAnnotation.getCoveredText();

				// dictionary word recognizer
				this.recognize(aJCas, this.getRootNode(),
						aSubAnnotation.getBegin(), aSubAnnotation.getEnd(),
						featurePathValue);
			}
		}
		if (!atLeastOneAnnotationToSearchFoundAmoungTheSubContextAnnotation) {
			System.err
					.println("WARNING: the specified context annotation "
							+ annotationToSearch
							+ " has not be found in the annotation index. Check the "
							+ PARAM_FEATUREPATH_TO_SEARCH
							+ " parameter. By default the process has been performed on the whole document text of the current view");
			
			//System.err.println("Debug: length>"+aJCas
			//		.getDocumentText().length()+"< getDocumentText>"+aJCas
			//		.getDocumentText()+"<");
			
			//System.err.println("Debug: this.getRootNode()>"+this.getRootNode()+"<");
			
			this.recognize(aJCas, this.getRootNode(), 0, aJCas
					.getDocumentText().length(), aJCas
					.getDocumentText());
			// getView(inputViewJCas.getViewName()).

		}
	
	}

	
	/**
	 * 
	 * @param aJCas
	 * @param root
	 * @param relativeBegin
	 *            TODO
	 * @param relativeEnd
	 *            TODO
	 * @param text
	 * @throws AnalysisEngineProcessException
	 */
	public void recognize(JCas aJCas, PrefixTree root,
			int relativeBegin, int relativeEnd, String text)
			throws AnalysisEngineProcessException {
		
		Type annotationToCreateType = aJCas.getTypeSystem().getType(
				outputAnnotation);

		//
		Map<PrefixTree, Branch> currentExploredBranches = new HashMap<PrefixTree, Branch>();
		// if (debug)
		//System.out.println("Debug: Start a new exploration of the tree with the first char");
		currentExploredBranches.put(root, new Branch(annotationToCreateType,
				0 + relativeBegin));

		int length = text.length();

		// if exactMatchBoolean create only if size to create = length

		// for each character of the text
		for (int index = 0; index < length; index++) {
			// int index = 0;
			// while (index < length) {

			// get the current text character
			int currentCodePoint = text.codePointAt(index);
			
			//System.out.println("Debug: current text character parsed >"+text.substring(index, index+1)+"<");

			// ignoreCase is true 
			if (!caseSensitiveBoolean) currentCodePoint = Character.toLowerCase(currentCodePoint);
			//

			Map<PrefixTree, Branch> nextExploredBranches = new HashMap<PrefixTree, Branch>();
			// if (debug)
			//System.out.println("Debug: for each currently explored branch");
			// au sein de toutes les branches de la ressource qui sont en train d'être parcourues 
			for (PrefixTree currentExploredBranch : currentExploredBranches
					.keySet()) {
				
				//System.out.println("Debug: currentExploredBranch >" +currentExploredBranch.getValues()+"<");

				// on récupère quel seral la prochaine à explorer au départ de ce currentCodePoint
				PrefixTree nextExploredBranch = currentExploredBranch
						.getChild(currentCodePoint);

				//System.out.println("Debug: nextExploredBranch >" +nextExploredBranch.getValues()+"<");

				//
				Branch currentBranch = currentExploredBranches
						.get(currentExploredBranch);

				//
				if (currentExploredBranch.isLeaf()) {
					// if (debug)
					//System.out.println("Debug: The current explored branch node is a leaf, so we create a corresponding annotation");
					currentBranch
							.update((List<List<String>>) currentExploredBranch
									.getValues());

					// for the matched occurrences which do not start at the
					// beginning of the search text, we have to remove the index
					// size at the end
					if (currentBranch.begin != relativeBegin)
						currentBranch.end = currentBranch.end
								- (currentBranch.begin - relativeBegin);

					if (((exactMatchBoolean) && (currentBranch.end
							- currentBranch.begin == length))
							|| (!exactMatchBoolean)) {

						if (annotateAnnotationWhereFeaturePathBelongs) {
							currentBranch.begin = relativeBegin;
							currentBranch.end = relativeEnd;
						}
						currentBranch.fire(aJCas);
					}
				}

				//
				if (nextExploredBranch != null) {
					// if (debug)
					// System.out.println("Debug: More following nodes with this char at this point in the tree");
					// update the end
					currentBranch.update(index + 1);
					// add to next
					nextExploredBranches.put(nextExploredBranch, currentBranch);
				} else {
					// if (debug)
					// System.out.println("Debug: No more following nodes with this char at this point in the tree");
				}

				//
				// if (debug)
				// System.out.println("Debug: Start a new exploration of the tree with the following char");
				nextExploredBranches.put(root, new Branch(
						annotationToCreateType, index + 1 + relativeBegin));

			}
			// if (debug)
			// System.out.println("Debug: Reset the current explored nodes with the next which have children with the matched the char");
			currentExploredBranches.clear();
			currentExploredBranches.putAll(nextExploredBranches);
			// if (!exactMatchBoolean)
			// index ++;
			// else index = length;
		}

		// if (debug)
		// System.out.println("Debug: for the last char, may be need to create annotations");
		for (PrefixTree currentExploredBranch : currentExploredBranches
				.keySet()) {
			//
			Branch currentBranch = currentExploredBranches
					.get(currentExploredBranch);
			//
			if (currentExploredBranch.isLeaf()) {
				// if (debug)
				// System.out.println("Debug: The current explored branch node is a leaf, so we create a corresponding annotation");
				currentBranch.update((List<List<String>>) currentExploredBranch
						.getValues());
				// if (exactMatchBoolean) if (currentBranch.begin)

				// for the matched occurrences which do not start at the
				// beginning of the search text, we have to remove the index
				// size at the end
				if (currentBranch.begin != relativeBegin)
					currentBranch.end = currentBranch.end
							- (currentBranch.begin - relativeBegin);

//				System.out.println("Debug: currentBranch.begin>" + currentBranch.begin + "< currentBranch.end>" +			currentBranch.end +	"< (currentBranch.end - currentBranch.begin)>"+(currentBranch.end - currentBranch.begin) +"< length>" +length + "< ");
				if (((exactMatchBoolean) && ((currentBranch.end - currentBranch.begin) == length))
						|| (!exactMatchBoolean)) {
					if (annotateAnnotationWhereFeaturePathBelongs) {
						currentBranch.begin = relativeBegin;
						currentBranch.end = relativeEnd;
					}
					currentBranch.fire(aJCas);
				}
			}
		}
	}

	/**
	 * Temporary structure to handle the range of text currently matched in the
	 * tree
	 */
	private class Branch {

		private int begin;
		private int end;
		private Type type;
		private List<List<String>> valuesList;

		public Branch(Type type, int begin) {
			this(type, begin, begin);
		}

		public Branch(Type type, int begin, int end) {
			this.type = type;
			this.begin = begin;
			this.end = end;
		}

		public void update(int end) {
			this.end = this.begin + end;
		}

		public void update(List<List<String>> valuesList) {
			this.valuesList = valuesList;
		}

		/**
		 * Launch the creation of the annotations
		 * 
		 * @param aJCas
		 */
		public void fire(JCas aJCas) throws AnalysisEngineProcessException {

			
			if (this.begin < this.end) {
				 //System.out.println("Debug: Creating an annotation " );

				// patch
				// if (this.begin != relativeBegin) this.end = this.end -
				// (this.begin -relativeBegin);

				// a distinct annotation will be created for each list of values
				// and in any case, even if there is no list of values, an
				// annotation will be created
				if (this.valuesList.isEmpty()) {
					ArrayList<String> currentValues = new ArrayList<String>();
					this.create(aJCas, currentValues);
				} else {
					Iterator<List<String>> values = this.valuesList.iterator();
					while (values.hasNext()) {
						this.create(aJCas, values.next());
					}
				}
			} else {
				//System.out.println("Debug: Attempt for creating an annotation but this.begin >= this.end");

			}
		}

		/**
		 * Create an annotation (at least a sequence of two nodes including the
		 * root node and the final node is a leaf)
		 * 
		 * @param aJCas
		 */
		public void create(JCas aJCas, List<String> values)
				throws AnalysisEngineProcessException {

			// For each considered column in the descriptor file
			// Get the features to set
			HashMap<String, Object> featuresHashMap = new HashMap<String, Object>();

			// Add begin and end features
			featuresHashMap.put("begin", Integer.toString(this.begin));
			featuresHashMap.put("end", Integer.toString(this.end));

			// Add a StringArray feature with all the values
			if (arrayFeatureName != null) {
				// List<String> valuesStringArray = new ArrayList<String>();

				// http://objectmix.com/apache/682381-fslists.html
				// In UIMA There's support for the fixed-length arrays, but not
				// for the variable length lists.
				// in fact the UIMA API gives you all the functionality you need
				// for coding what you need with these structures.

				String[] valuesClassicStringArray = new String[values.size()];
				Iterator<String> valuesIterator = values.iterator();
				int i = 0;
				while (valuesIterator.hasNext()) {
					String valueString = valuesIterator.next();
					// System.out.println("Debug: valuesIterator.next()>"+valueString+"<");
					valuesClassicStringArray[i] = valueString;
					i++;
				}
				// System.out.println("Debug: this.values.size()>"+this.values.size()+"< valuesClassicStringArray.length>"+valuesClassicStringArray.length+"<");
				StringArray valuesStringArray = new StringArray(aJCas, i);
				valuesStringArray.copyFromArray(valuesClassicStringArray, 0, 0,
						i);
				featuresHashMap.put(arrayFeatureName, valuesStringArray);
			}

			//
			Set<Integer> colIdFeatNameKeySet = colIdFeatNameHashMap.keySet();
			Iterator<Integer> colIdFeatNameKeySetIter = colIdFeatNameKeySet
					.iterator();
			// if (debug) if (this.values.size() != 0)
			// {System.out.println("Debug: the values to set are >"+this.values+"<");
			// }

			// if (debug) if (colIdFeatNameKeySetIter.hasNext())
			// {System.out.println("Debug: some colums have been assigned to some features by parameters");
			// }
			while (colIdFeatNameKeySetIter.hasNext()) {
				int colId = (Integer) colIdFeatNameKeySetIter.next() - 1;
				if (colId < values.size()) {
					// if (debug)
					// {System.out.println("Debug: the current temporary expression has a value >"+this.values.get(colId)+"< for that feature>"+colIdFeatNameHashMap.get(colId+1));
					// }
					featuresHashMap.put(colIdFeatNameHashMap.get(colId + 1),
							values.get(colId));
				} else {
					// if (debug)
					// {System.out.println("Debug: the current temporary expression has not a value for that ");
					// }
					// if (debug)
					// System.out.println("Warning: The column id "+colId+" of the "+PARAM_FEATURES_TO_COLUMNS+" paramater is out of range of the current resource line");
					// String errorMsg =
					// "Warning: Wrong syntax to the "+PARAM_FEATURES_TO_COLUMNS+" paramater with the following line "+
					// ColIdFeatNameCouple.toString();
					// throw new AnnotatorInitializationException(errorMsg,new
					// Object[]{errorMsg});
				}
			}

			// if (debug)
			//System.out.println("Debug: this.type.getName()"+this.type.getName());
			// if (debug)
			//System.out.println("Debug: featuresHashMap"+featuresHashMap);
			//System.out.println("Debug: this.type.getName() >"+this.type.getName()+"<");
					 // featuresHashMap>"+ featuresHashMap.keySet().toString()+"<");

			//System.err.println("Debug: this.type.getName() >"+this.type.getName()+"< featuresHashMap>"+ featuresHashMap.keySet().toString()+"<");
			if (this.type == null) 
				System.err.println("WARNING: attempt to create an annotation with a type null (means does not exist); check the specified annotation type names.");
			AnnotationUtils.createAnnotation(aJCas, this.type.getName(),
					featuresHashMap);
			//

		}

	}

}