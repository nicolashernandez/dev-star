/** 
 * Copyright (C) 2010-2013  Nicolas Hernandez
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
package fr.univnantes.lina.uima.common.ae;

import fr.univnantes.lina.javautil.DateUtilities;
import fr.univnantes.lina.uima.common.cas.AnnotationCollectionUtils;
import fr.univnantes.lina.uima.common.cas.AnnotationUtils;
import fr.univnantes.lina.uima.common.cas.FeatureUtils;
import fr.univnantes.lina.uima.common.cas.JCasUtils;
import java.io.PrintStream;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import org.apache.uima.UimaContext;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.cas.FSIterator;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.text.AnnotationIndex;
import org.apache.uima.fit.component.JCasAnnotator_ImplBase;
import org.apache.uima.fit.descriptor.ConfigurationParameter;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.tcas.Annotation;
import org.apache.uima.resource.ResourceInitializationException;


/**
 * <p>
 * UIMA Analysis Engine abstract class
 * </p>
 * 
 * <p>
 * <ul>
 * <li>Perform some process on some annotations (also called InputAnnotation)
 * covered by some others (also called ContextAnnotation) which are only present
 * in some views (also called InputView).</li>
 * <li>Create a new view (also called OutputView) or a new annotation (also
 * called OutputAnnotation) to receive the processing result.</li>
 * </ul>
 * </p>
 * 
 * <p>
 * The process has to be implemented (see the abstract method of the class).
 * </p>
 * 
 * <p>
 * The class is type system agnostic and so handle generically the views and the
 * annotations you process. This is made possible by specifying the names of the
 * handled views and annotations by parameters.
 * </p>
 * 
 * @author Nicolas Hernandez
 */
public class CommonAE
  extends JCasAnnotator_ImplBase
{

	/* PARAMETERS */


	/** View name list to consider as the views to process*/
  public static final String PARAM_INPUT_VIEW_NAME = "InputView";
  @ConfigurationParameter(name="InputView", mandatory=false, defaultValue={"_InitialView"})
  private String[] inputViewStringArray;


	/**
	 * List of annotation names which delimit the text areas
	 * or the covered annotations to process
	 */
  public static final String PARAM_CONTEXT_ANNOTATION_NAME = "ContextAnnotation";
  @ConfigurationParameter(name="ContextAnnotation", mandatory=false, defaultValue={"uima.tcas.DocumentAnnotation"})
  protected String[] contextAnnotationStringArray;
 protected Set<String> contextAnnotationSet = null;
  

	/**
	 * Ordered list of annotation names to consider as the
	 * Feature Structure index to process
	 */
  public static final String PARAM_INPUT_ANNOTATION_NAME = "InputAnnotation";
  


  @ConfigurationParameter(name="InputAnnotation", mandatory=false, defaultValue={""})
/*  102 */   protected String[] inputAnnotationStringArray = null;
/*  103 */   protected Set<String> inputAnnotationSet = null;
  


  public static final String PARAM_INPUT_FEATURE_NAME = "InputFeature";
  

	/** Feature name of the annotations to consider as the token units to be
	 processed and whose string value will be processed*/
  @ConfigurationParameter(name="InputFeature", mandatory=false, defaultValue={"coveredText"})
  private String inputFeature;
  

	/** View name to consider as the view to receive the result ;  to be
	 * created whether OutputAnnotation is empty or simply to edit if
	 * OutputAnnotation is defined */
  public static final String PARAM_OUTPUT_VIEW_NAME = "OutputView";
  


  @ConfigurationParameter(name="OutputView", mandatory=false, defaultValue={""})
  private String outputView;
  
	/** Type mime to consider for storing the result in the sofaDataString*/
  public static final String PARAM_OUTPUT_VIEW_TYPE_MIME = "OutputViewTypeMime";
  

  @ConfigurationParameter(name="OutputViewTypeMime", mandatory=false, defaultValue={"text/plain"})
  private String outputViewTypeMime;
  
	/** Type name of the annotations to create as the analysis result*/
  public static final String PARAM_OUTPUT_ANNOTATION_NAME = "OutputAnnotation";
  

  @ConfigurationParameter(name="OutputAnnotation", mandatory=false, defaultValue={""})
  private String outputAnnotation;
  
	/** Type name of the feature to create as the analysis result*/
  public static final String PARAM_OUTPUT_FEATURE_NAME = "OutputFeature";
  

  @ConfigurationParameter(name="OutputFeature", mandatory=false, defaultValue={""})
  private String outputFeature;
  

private static String INPUTTYPE_ANNOTATION = "annotation";
private static String INPUTTYPE_VIEW = "view";
private static String OUTPUTTYPE_ANNOTATION = "annotation";
private static String OUTPUTTYPE_VIEW = "view";
  




	/**
	 * OutputFeature Feature name of the annotation whose string value will
	 * contain the analysis result
	 */
private String outputFeatureString = null;
private String inputType = "";
protected String outputType = "";
  


  public void initialize(UimaContext aContext)
    throws ResourceInitializationException
  {
super.initialize(aContext);
    
this.contextAnnotationSet = new HashSet();
/*  167 */     String[] arrayOfString; int j; int i; if ((this.contextAnnotationStringArray != null) && 
/*  168 */       (this.contextAnnotationStringArray.length != 0)) {
/*  169 */       j = (arrayOfString = this.contextAnnotationStringArray).length; for (i = 0; i < j; i++) { String c = arrayOfString[i];
/*  170 */         this.contextAnnotationSet.add(c);
      }
    }
    
	
		//if (contextAnnotationSet.isEmpty()) System.err.println("Debug: contextAnnotationSet.isEmpty()");
		//else  System.err.println("Debug: !contextAnnotationSet.isEmpty()"+contextAnnotationSet);



/*  178 */     this.inputAnnotationSet = new HashSet();
    
		// un getConfigParameterValue d'un parameter multivalued vide donne
		// (parfois?) une variable tableau vide mais non null
		// donc ne peut être comparer à null
		// attention on ne peut comparer sa lengh qu'après un
		// getConfigParameterValue sans quoi on obtiendrait un null
		// je décide de faire les 2
		// System.out.println("Debug: après getConfigParameterValue inputAnnotationStringArray"
		// +inputAnnotationStringArray);








/*  189 */     if ((this.inputAnnotationStringArray != null) && 
/*  190 */       (this.inputAnnotationStringArray.length != 0)) {
/*  191 */       j = (arrayOfString = this.inputAnnotationStringArray).length; for (i = 0; i < j; i++) { String inputAnnotationString = arrayOfString[i];
/*  192 */         this.inputAnnotationSet.add(inputAnnotationString);
      }
    }
    

		//		if (((inputFeatureString != null) && (inputAnnotationStringArray == null))
		//				|| ((inputFeatureString != null) && ((inputAnnotationStringArray != null) && (inputAnnotationStringArray.length == 0)))
		//				|| ((inputFeatureString == null) && ((inputAnnotationStringArray != null) && (inputAnnotationStringArray.length != 0)))) {



/*  200 */     if (((this.inputFeature != null) && (this.inputAnnotationSet == null)) || 
/*  201 */       ((this.inputFeature != null) && (this.inputAnnotationSet != null) && (this.inputAnnotationSet.size() == 0)) || (
/*  202 */       (this.inputFeature == null) && (this.inputAnnotationSet != null) && (this.inputAnnotationSet.size() != 0)))
    {
/*  204 */       String errmsg = "Error: If one of the parameter InputAnnotation or InputFeature is defined, both must be !";
      


			// System.out.println("Debug: inputFeatureString " +
			// inputFeatureString + " inputAnnotationStringArray" +
			// inputAnnotationStringArray +":");
			// for (String s : inputAnnotationStringArray) {
			// System.out.print(">"+ s + "<");
			// }System.out.println();






/*  215 */       throw new ResourceInitializationException(errmsg, new Object[0]);
    }
    




		// Ce test intervient après avoir vérifier que le couple
		// Annotation/Feature était bien complet
		//
		// Il est possible de ne pas avoir une méthode dédiée au traitement de
		// vue
		// et de ne pas y faire appel dans la méthode process
		// en utilisant DocumentAnnotation et dans la méthode
		// processContextAnnotation
		// créer un index de InputAnnotation à partir des ContextAnnotation
		// Rajoute le fait que l'on doit gérer un param supplémentaire le
		// ContextFeature
		// Mais si on veut spécifier des ContextAnnotation a proprement parlé et
		// pas des InputView
		// Il faut quand même rajouter ce param et par conséquent il faudra
		// tester sa co-existence avec ContextAnnotation
		// Donc le test et l'affectation suivante n'est là que si on décide de
		// ne pas passer par processInputView en natif
		// On décide de ne pas ajouter le param ContextFeature donc le teste
		// suivante sert pour 2 cas
		//
		// Si inputFeatureString est null c'est que soit ContextAnnotation soit
		// InputView
		// car si InputAnnotation alors exception aurait été levée donc on le
		// laisse par défaut
	

		// outputAnnotationString ET outputFeatureString doivent être
		// initialisés les deux à la fois ou aucun d'eux
		/*if (((outputAnnotationString != null) && (outputFeatureString == null))
				|| ((outputAnnotationString == null) && (outputFeatureString != null))) {
			String errmsg = "Error: If one of the parameter "
				+ PARAM_NAME_OUTPUT_ANNOTATION + " or "
				+ PARAM_NAME_OUTPUT_FEATURE + " is defined, both must be !";
			throw new ResourceInitializationException(errmsg, new Object[] {});
			// e.printStackTrace();
		}*/









/*  255 */     System.err.println("Warning: uima-shell may require the definition of the couple OutputAnnotation and OutputFeature but it is not necessary for uima-word-segmenter so we remove the test");
    



		// Si l'input_type est annotation, alors on va traiter chacune d'elle
     if (this.inputAnnotationStringArray != null) {
      this.inputType = INPUTTYPE_ANNOTATION;
    }
		// Sinon on va traiter le datastring de la vue
    else {
/*  265 */       this.inputType = INPUTTYPE_VIEW;
    }
/*  267 */     if ((this.outputAnnotation != null) && (this.outputFeatureString != null)) {
/*  268 */       this.outputType = OUTPUTTYPE_ANNOTATION;
    } else {
/*  270 */       this.outputType = OUTPUTTYPE_VIEW;
    }

		// String[] patternStrings = (String[])
		// aContext.getConfigParameterValue("Patterns");
		// mLocations = (String[])
		// aContext.getConfigParameterValue("Locations");

		// compile regular expressions
		// mPatterns = new Pattern[patternStrings.length];
		// for (int i = 0; i < patternStrings.length; i++) {
		// mPatterns[i] = Pattern.compile(patternStrings[i]);
		// }

		// dans le process
		// Vérifier que context, input et output AnnotationString si !=null
		// alors ont un type défini dans le TS
		// Vérifier aussi que l'input view existe
  }
  

/**
	 * From the given JCas, process sequentially each InputView. Eventually may
	 * create a view with the concatenated results obtained for each view.
	 * 
	 * Previously the method was intending to route toward the correct
	 * subprocess method depending on the InputType which is either Annotation
	 * or View. Recent modifications led to use the same subprocess method.
	 * Indeed the InputView will be processed indirectly by the
	 * DocumentAnnotation.
	 * 
	 * @param aJCas
	 *            the CAS over which the process is performed
	 * @param inputViewString
	 *            [] List of names of each InputView to process
	 * @param contextAnnotationStringArray
	 *            List of the Context Annotation names to process
	 * @param inputAnnotationStringArray
	 *            List of Input Annotation names to process
	 * @param inputFeature
	 *            Feature name of the inputAnnotation whose String Value will be
	 *            actually processed
	 * @param outputView
	 *            View name to consider as the view to receive the result; to be
	 *            created whether `OutputAnnotation` is empty or simply to edit
	 *            if `OutputAnnotation` is defined
	 * @param outputViewTypeMime
	 *            Type Mime of the view to create
	 * @param outputAnnotation
	 *            Name of the annotation to create as the analysis result
	 * @param ouputFeatureString
	 *            Feature name of the annotation whose string value will contain
	 *            the analysis result
	 * 
	 * @throws AnalysisEngineProcessException
	 * 
	 * @see JCasAnnotator_ImplBase#process(JCas)
	 */
  public void process(JCas aJCas)
    throws AnalysisEngineProcessException
  {


		/*AnnotationIndex<Annotation> 
		//FSIterator<FeatureStructure> 
		annotationIndex = aJCas.getAnnotationIndex(); // getAnnotationIndex();
		for (FeatureStructure annotation : annotationIndex) {
			System.out.println(annotation.getClass().getName());
		}*/
		
		// Bug Fix if no context annotation is present  ; does not seem to work
		/*AnnotationIndex<Annotation> 
		//FSIterator<FeatureStructure> 
		annotationIndex = aJCas.getAnnotationIndex(JCasSofaViewUtils.getJCasType(aJCas, UimaConst.DEFAULT_DOCUMENT_ANNOTATION)); 
		if (!annotationIndex.iterator().hasNext()) {
			new DocumentAnnotation (aJCas, 0, aJCas.getDocumentText().length()).addToIndexes();

		}*/
/*  346 */     Date startDate = DateUtilities.getNow();
/*  347 */     System.err.println("Info: " + getClass().getName() + " starts at " + DateUtilities.stringFormatADate(startDate));
    


/*  351 */     log("-----------------------------------------------------------------------------------------------------------------");
/*  352 */     if (this.inputType.equalsIgnoreCase(INPUTTYPE_ANNOTATION)) {
/*  353 */       log("AnalysisEngine - Process the input annotation of a given type (potentially covered by a context annotation of a given type)");
	// processContextAnnotations(aJCas, inputViewString,
			// contextAnnotationString, inputAnnotationStringArray,
			// inputFeatureString, outputViewString, outputViewTypeMimeString,
			// outputAnnotationString, outputFeatureString);
    }
    else
    {

/*  359 */       log("AnalysisEngine - Process the input view; actually the default ContextAnnotation DocumentAnnotation");
// log("Process the input view");
			// processInputViewType(aJCas, inputViewString,
			// contextAnnotationString, inputAnnotationStringArray,
			// inputFeatureString, outputViewString, outputViewTypeMimeString,
			// outputAnnotationString, outputFeatureString);
    }
    

/*
		 * processInputViews(aJCas, inputViewStringArray,
		 * contextAnnotationStringArray, inputAnnotationStringArray,
		 * inputFeatureString, outputViewString, outputViewTypeMimeString,
		 * outputAnnotationString, outputFeatureString);
		 */

		// JCas aJCas,
		// String[] inputViewStringArray,
		// String[] contextAnnotationStringArray,
		// String[] inputAnnotationStringArray,
		// String inputFeatureString,
		// String outputViewString,
		// String outputViewTypeMimeString,
		// String outputAnnotationString,
		// String outputFeatureString





/*  384 */     log("Getting the Absolute  Context Annotation ");
    
		// Récupère le type de la DEFAULT_CONTEXT_ANNOTATION
/*  387 */     AnnotationIndex<Annotation> absoluteContextAnnotationIndex = null;
/*  388 */     Type absoluteContextAnnotationType = null;
/*  389 */     absoluteContextAnnotationType = JCasUtils.getJCasType(aJCas, 
/*  390 */       "uima.tcas.DocumentAnnotation");
    
// Récupération d'index d'annotations à partir de type d'annotation!
		// soit comme cela
		// AnnotationIndex idxMonType = (AnnotationIndex)
		// cas.getAnnotationIndex(inputAnnotationType);
		// FSIterator monTypeIt = idxMonType.iterator();
		// while (monTypeIt.hasNext()) {
		// On peut le manipuler comme on veut ...
		// }
		// soit comme cela
		// sans reflect
		// FSIndex tokenAnnotationFSIdx =
		// aJCas.getAnnotationIndex(TokenAnnotation.type);
		// avec reflect
		// FSIndex<Annotation> inputAnnotationFSIdx = aJCas
		// .getAnnotationIndex(inputAnnotationType);


/*  408 */     Iterator<Annotation> absoluteContextAnnotationIndexIterator = null;
/*  409 */     absoluteContextAnnotationIndex = aJCas
/*  410 */       .getAnnotationIndex(absoluteContextAnnotationType);
/*  411 */     absoluteContextAnnotationIndexIterator = absoluteContextAnnotationIndex
/*  412 */       .iterator();
    

		// Pour l'absolute context
		// il y a un seul élément à savoir DocumentAnnotation
/*  416 */     Boolean absoluteContextAnnotationIndexIteratorNeverBrowsed = Boolean.valueOf(true);
/*  417 */     while ((absoluteContextAnnotationIndexIterator.hasNext()) && (absoluteContextAnnotationIndexIteratorNeverBrowsed.booleanValue()))
    {
/*  419 */       absoluteContextAnnotationIndexIteratorNeverBrowsed = Boolean.valueOf(false);
			//System.out.println("Debug: CommonAE absoluteContextAnnotationIndexIterator.hasNext()" );

			
			// Context Annotation suivante de même type
			//Annotation absoluteContextAnnotation = null;
			//absoluteContextAnnotation = (Annotation) absoluteContextAnnotationIndexIterator.next();

			// var to concat the results in case of a view as the output type
/*  428 */       String inputViewsConcatenedResults = "";
      
/*  430 */       Boolean atLeastOneInputViewIsEqualToOutputView = Boolean.valueOf(false);
      
/*  432 */       log("For each inputView");
/*  433 */       String[] arrayOfString; int j = (arrayOfString = this.inputViewStringArray).length; for (int i = 0; i < j; i++) { String inputViewString = arrayOfString[i];
        
				/** -- Prepare the view to be processed **/
/*  436 */         log("Getting the inputViewJCas " + inputViewString);
/*  437 */         JCas inputViewJCas = JCasUtils.getView(aJCas, 
/*  438 */           inputViewString);
        
				// On spécifie ici la valeur par défaut de l'outputView
/*  441 */         if (this.outputView == null)
        {	// If no output view is specified, we set it to
					// inputViewString

/*  444 */           this.outputView = inputViewString;
        }
        

/*  448 */         absoluteContextAnnotationIndex = 
/*  449 */           inputViewJCas.getAnnotationIndex(absoluteContextAnnotationType);
/*  450 */         absoluteContextAnnotationIndexIterator = absoluteContextAnnotationIndex
/*  451 */           .iterator();
/*  452 */         Annotation inputViewJCasAbsoluteContextAnnotation = null;
/*  453 */         inputViewJCasAbsoluteContextAnnotation = 
/*  454 */           (Annotation)absoluteContextAnnotationIndexIterator.next();
        		/**
				 * -- In case of the output type is annotation, get the view to
				 * store the result
				 **/
				// si les param annotations sont renseignés alors cela signifie
				// que l'on
				// suppose qu'une vue existe pour accueillir les annotations
				// on effectue ici le getView pour d'éviter de le faire à chaque
				// tour de boucle
				// si l'inputType est annotation

/*  466 */         JCas outputViewJCas = null;
/*  467 */         if (this.outputType.equalsIgnoreCase(OUTPUTTYPE_ANNOTATION)) {
/*  468 */           log("Getting the outputViewJCas");
/*  469 */           outputViewJCas = JCasUtils.getView(aJCas, 
/*  470 */             this.outputView);
        }
        //System.out.println("Debug: CommonAE process aJCas.getViewName() " + aJCas.getViewName());

				//System.out.println("Debug: CommonAE process inputViewJCas.getViewName() " + inputViewJCas.getViewName());

				//System.out.println("Debug: CommonAE outputViewString "+ outputViewString);
				//if (outputViewJCas == null) 			System.out.println("Debug: CommonAE outputViewJCas == null");

				/*
			Iterator sofaIter =  aJCas.getSofaIterator();
			while (sofaIter.hasNext()) {
				Sofa aSofa = (Sofa) sofaIter.next();
				System.out.println("Debug: CommonAE process aSofa.getSofaID() " + aSofa.getSofaID());
			}
				 */
				/*
			Iterator viewIter = null;
			try {
				viewIter = aJCas.getViewIterator();
			} catch (CASException e) {
				e.printStackTrace();
			}
			while (viewIter.hasNext()) {
				JCas aView = (JCas) viewIter.next();
				System.out.println("Debug: CommonAE process aView.getViewName() " + aView.getViewName());
			}*/



/*  498 */         log("Getting the Context Annotation index");
        				// Iterator<Annotation> inputAnnotationIterator = null;
				// inputAnnotationIterator =
				// inputViewJCas.getAnnotationIndex(inputAnnotationType).subiterator(contextAnnotation);
				//System.out.println("Debug: CommonAE process absoluteContextAnnotation " + absoluteContextAnnotation);
				//System.out.println("Debug: CommonAE process absoluteContextAnnotation " + inputViewJCasAbsoluteContextAnnotation);






/*  505 */         FSIterator<Annotation> contextAnnotationsFSIter = null;
        
				//AnnotationUtils.createAnnotation(inputViewJCas, "org.apache.uima.jcas.tcas.DocumentAnnotation", 0, sofaDataString.length());


/*  509 */         contextAnnotationsFSIter = AnnotationCollectionUtils.subiterator(
/*  510 */           inputViewJCas, inputViewJCasAbsoluteContextAnnotation, 
/*  511 */           this.contextAnnotationSet, false);
        
/*  513 */         inputViewsConcatenedResults = inputViewsConcatenedResults + processInputView(inputViewJCas, 
/*  514 */           contextAnnotationsFSIter, this.inputAnnotationSet, 
/*  515 */           this.inputFeature, outputViewJCas, 
/*  516 */           this.outputAnnotation, this.outputFeatureString);
        

/*  519 */         if (inputViewString.equalsIgnoreCase(this.outputView)) { atLeastOneInputViewIsEqualToOutputView = Boolean.valueOf(true);
        }
      }
      
			/** -- Create view **/
			// output_v_string est défini ; potentiellement il est égal à
			// input_v ; normalement la vue n'existe pas et est à créer
/*  524 */       if ((this.outputType.equalsIgnoreCase(OUTPUTTYPE_VIEW)) && (!atLeastOneInputViewIsEqualToOutputView.booleanValue())) {
/*  525 */         log("Creating output view");
        		// ici on suppose que outputViewString ne correspond à aucune
				// vue existante (a fortiori est différent de inputViewString)
				// et que createView génèrera une erreur si la vue existe déjà


/*  529 */         JCasUtils.createView(aJCas, this.outputView, 
/*  530 */           inputViewsConcatenedResults, this.outputViewTypeMime);
      }
/*  532 */       else if ((this.outputType.equalsIgnoreCase(OUTPUTTYPE_VIEW)) && (atLeastOneInputViewIsEqualToOutputView.booleanValue())) {
/*  533 */         System.err.println("Warning: " + getClass().getName() + " outputType.equalsIgnoreCase(OUTPUTTYPE_VIEW) && atLeastOneInputViewIsEqualToOutputView ; The process may work, if it s not the case you may search why because of this warning");
      }
    }
    
/*  537 */     Date endDate = DateUtilities.getNow();
/*  538 */     System.err.println("Info: " + getClass().getName() + " ends at " + DateUtilities.stringFormatADate(endDate) + " after " + DateUtilities.dateDiff(startDate, endDate) + " milliseconds");
  }
  

/**
	 * From the given inputView, process sequentially each ContextAnnotation.
	 * Returns the contatenated results obtained for each Context Annotation.
	 * 
	 * @param inputViewJCas
	 *            the CAS View over which the process is performed
	 * @param contextAnnotationsFSIter
	 *            FSIterator of context Annotations
	 * @param inputAnnotationSet
	 *            List of Input Annotation names to process
	 * @param inputFeatureString
	 *            Feature name of the inputAnnotation whose String Value will be
	 *            actually processed
	 * @param outputViewJCas
	 *            the CAS View to update with potential future created
	 *            annotations
	 * @param outputAnnotationString
	 *            Name of the annotation to create as the analysis result
	 * @param ouputFeatureString
	 *            Feature name of the annotation whose string value will contain
	 *            the analysis result
	 * 
	 * @return Contatenated results obtained for each Context Annotation
	 * 
	 * @throws AnalysisEngineProcessException
	 */
  protected String processInputView(JCas inputViewJCas, FSIterator<Annotation> contextAnnotationsFSIter, Set<String> inputAnnotationSet, String inputFeatureString, JCas outputViewJCas, String outputAnnotationString, String ouputFeatureString)
    throws AnalysisEngineProcessException
  {
/*  578 */     log("AnalysisEngine - processInputView");
    


/*  582 */     String contextAnnotationResultString = "";
    
		// Pour chaque inputAnnotation présent dans le context
/*  585 */     while (contextAnnotationsFSIter.hasNext())
    {


/*  589 */       Annotation contextAnnotation = 
/*  590 */         (Annotation)contextAnnotationsFSIter.next();
      
// Récupère le type d'input annotations
			// Type inputAnnotationType = null;
			// inputAnnotationType = UIMAUtilities.getType(inputViewJCas,
			// inputAnnotationString);
			// Type inputAnnotationType = null;
			// inputAnnotationType = UIMAUtilities.getType(inputViewJCas,
			// inputAnnotationStringHashMap.keySet().iterator().next());

			// Si InputAnnotation n'est pas renseigné
			// alors on traite la valeur String d'une certaine feature de chaque
			// ContextAnnotation
			// Par défaut ContextAnnotation est au moins égal à
			// DocumentAnnotation
			// et possède la feature coveredText
/*  606 */       if (inputAnnotationSet.isEmpty())
      {
/*  608 */         inputAnnotationSet.add(contextAnnotation.getType()
/*  609 */           .getName());
/*  610 */         log("Building an Input Annotations index from the ContextAnnotations");

      }
			// Si InputAnnotation est renseigné
			// alors on construit un iterator ordonné à partir de celles
			// renseignées
      else
      {

/*  616 */         log("Getting the Input Annotations index ");
      }
      



			// Récupération de la liste des inputAnnotation
			// Iterator<Annotation> inputAnnotationIterator = null;
			// inputAnnotationIterator =
			// inputViewJCas.getAnnotationIndex(inputAnnotationType).subiterator(contextAnnotation);
/*  623 */       FSIterator<Annotation> contextualizedInputAnnotationsFSIter = null;
/*  624 */       contextualizedInputAnnotationsFSIter = AnnotationCollectionUtils.subiterator(
/*  625 */         inputViewJCas, contextAnnotation, 
/*  626 */         inputAnnotationSet, false);
      
			// contextAnnotationResultString +=
			// processInputAnnotations(inputViewJCas,
			// contextualizedInputAnnotationsFSIter, inputFeatureString,
			// outputViewJCas, outputAnnotationString, ouputFeatureString);
/*  633 */       contextAnnotationResultString = contextAnnotationResultString + processContextAnnotation(
/*  634 */         inputViewJCas, contextAnnotationsFSIter, 
/*  635 */         contextAnnotation, contextualizedInputAnnotationsFSIter, 
/*  636 */         inputFeatureString, outputViewJCas, outputAnnotationString, ouputFeatureString);
    }
    
/*  639 */     return contextAnnotationResultString;
  }
  



	/**
	 * For a given ContextAnnotation, process each InputAnnotation by analyzing 
	 * the value of its InputFeature
	 * If some OutputAnnotations are specified then they are created and their
	 * OutputFeature is set with the analysis result (Else) it returns the
	 * contatenated results obtained for each InputAnnotation
	 * 
	 * @param inputViewJCas
	 *            the CAS View over which the process is performed
	 * @param contextualizedInputAnnotationsFSIter
	 *            FSIterator of the input Annotations to process which are covered 
	 *            by the contextAnnotation
	 * @param contextAnnotation 
	 *			  the current Context Annotation            
	 * @param inputFeatureString
	 *            Feature name of the Input Annotations whose String Value will
	 *            be actually processed
	 * @param outputViewJCas
	 *            the CAS View to update with potential future created
	 *            annotations
	 * @param outputAnnotationString
	 *            Name of the annotation to create as the analysis result
	 * @param ouputFeatureString
	 *            Feature name of the annotation whose string value will contain
	 *            the analysis result
	 * @return the contatenated results obtained for each InputAnnotation
	 * 
	 * @throws AnalysisEngineProcessException
	 */
  protected String processContextAnnotation(JCas inputViewJCas, FSIterator<Annotation> contextAnnotationsFSIter, Annotation contextAnnotation, FSIterator<Annotation> contextualizedInputAnnotationsFSIter, String inputFeatureString, JCas outputViewJCas, String outputAnnotationString, String ouputFeatureString)
    throws AnalysisEngineProcessException
  {
/*  679 */     log("AnalysisEngine - processContextAnnotation");
    


/*  683 */     String commandResultString = "";
    
		// Pour chaque inputAnnotation présent dans le context
/*  686 */     while (contextualizedInputAnnotationsFSIter.hasNext())
    {

/*  689 */       String commandLocalResultString = processInputAnnotation(inputViewJCas, 
/*  690 */         contextAnnotationsFSIter, 
/*  691 */         contextAnnotation, 
/*  692 */         contextualizedInputAnnotationsFSIter, 
/*  693 */         contextualizedInputAnnotationsFSIter.next(), 
/*  694 */         inputFeatureString, 
/*  695 */         outputViewJCas, 
/*  696 */         outputAnnotationString, 
/*  697 */         ouputFeatureString);
      


			// L'output_type est view
			// On stocke les résultats obtenus pour chaque annotation
			// On copiera le tout dans le sofaDataString en une seule fois
/*  702 */       log("Concating the result");
/*  703 */       commandResultString = commandResultString + commandLocalResultString;
    }
    
/*  706 */     return commandResultString;
  }
  



	/**
	 * Process a given InputAnnotation by analyzing 
	 * the value of its InputFeature
	 * 
	 * @param inputViewJCas
	 *            the CAS View over which the process is performed
	 * @param contextAnnotation 
	 * 	 		  the current Context Annotation    
	 * @param contextualizedInputAnnotationsFSIter
	 *            FSIterator of the input Annotations to process
	 * @param inputFeatureString
	 *            Feature name of the Input Annotations whose String Value will
	 *            be actually processed
	 * @param outputViewJCas
	 *            the CAS View to update with potential future created
	 *            annotations
	 * @param outputAnnotationString
	 *            Name of the annotation to create as the analysis result
	 * @param ouputFeatureString
	 *            Feature name of the annotation whose string value will contain
	 *            the analysis result
	 * @return the contatenated results obtained for each InputAnnotation
	 * 
	 * @throws AnalysisEngineProcessException
	 */
  protected String processInputAnnotation(JCas inputViewJCas, FSIterator<Annotation> contextAnnotationsFSIter, Annotation contextAnnotation, FSIterator<Annotation> contextualizedInputAnnotationsFSIter, Object inputAnnotationObject, String inputFeatureString, JCas outputViewJCas, String outputAnnotationString, String ouputFeatureString)
    throws AnalysisEngineProcessException
  {
/*  744 */     log("AnalysisEngine - processInputAnnotation");
    
		//System.out.println("Debug: CommonAE processInputAnnotation");


		// Récupère le texte à traiter et ses offsets qui pourront
		// éventuellement servir
		// si l'outputType est Annotation
/*  752 */     log("Getting the current annotation to be proceeded");
    
    		// Récupère et cast l'inputAnnotation courante à manipuler
/*  755 */     Class<? extends Object> annotationClass = inputAnnotationObject.getClass();
        		// if (annotationClass != null ) {
/*  757 */     String className = "null";
/*  758 */     className = annotationClass.getName();
		// System.out.println("Debug: class>"+className+"<");
/*  760 */     Class<Annotation> inputAnnotationClass = 
/*  761 */       AnnotationUtils.getAnnotationClass(className);

/*  763 */     Annotation inputAnnotation = (Annotation)inputAnnotationObject;
/*  764 */     inputAnnotationClass.cast(inputAnnotation);
    // System.out.println("inputAnnotationType.getName()>"+inputAnnotation.getType().getName()+"<");
		// System.out.println("inputAnnotation.coveredText>"+inputAnnotation.getCoveredText()+"<");
		// System.out.println("inputAnnotation.begin>"+inputAnnotation.getBegin()+"<");
		// System.out.println("inputAnnotation.end>"+inputAnnotation.getEnd()+"<");

		// Invoque la récupération de la valeur dont l'inputFeatureString
		// est spécifiée pour l'annotation courante






/*  772 */     String inputTextToProcess = "";
    

/*  775 */     inputTextToProcess = FeatureUtils.invokeFeatureGetterMethod(
/*  776 */       inputAnnotation, 
/*  777 */       FeatureUtils.getFeatureGetterMethod(inputAnnotationClass, 
/*  778 */       inputFeatureString)).toString();
    		// log ("Debug: inputTextToProcess>"+inputTextToProcess+"<");



/*  783 */     int beginFeatureValueFromAnnotationToCreate = inputAnnotation
/*  784 */       .getBegin();
/*  785 */     int endFeatureValueFromAnnotationToCreate = inputAnnotation.getEnd();
    
		/** -- Execute and get result **/
/*  788 */     log("Executing and getting the result");
/*  789 */     String commandLocalResultString = "";
/*  790 */     commandLocalResultString = processAnnotationFeatureStringValue(
/*  791 */       inputViewJCas, inputTextToProcess, 
/*  792 */       beginFeatureValueFromAnnotationToCreate, 
/*  793 */       endFeatureValueFromAnnotationToCreate);
    

		// Soit pour chaque annotation en entrée à traiter soit pour la vue
		// en entrée
/*  797 */     if (this.outputType.equalsIgnoreCase(OUTPUTTYPE_ANNOTATION))
    {
/*  799 */       log("Creating output annotation");
/*  800 */       HashMap<String, Object> featuresHashMap = new HashMap();
/*  801 */       featuresHashMap.put("begin", String.valueOf(beginFeatureValueFromAnnotationToCreate));
/*  802 */       featuresHashMap.put("end", String.valueOf(endFeatureValueFromAnnotationToCreate));
/*  803 */       featuresHashMap.put(this.outputFeatureString, commandLocalResultString);
/*  804 */       AnnotationUtils.createAnnotation(outputViewJCas, outputAnnotationString, featuresHashMap);
    }
    






/*  813 */     return commandLocalResultString;
  }
  


/**
	 * This method corresponds to the actual process to perform on the
	 * String Value of a given Feature of a given Annotation
	 * 
	 * By default it echoes the text to process
	 * 
	 * @param inputViewJCas
	 *            the CAS view that will be processed.
	 * @param inputTextToProcess
	 *            the text to process (actually it corresponds to the String
	 *            Value of a given Feature of a given Annotation )
	 * @param beginFeatureValue
	 *            the begin offset of the Annotation whose one Feature is going
	 *            to be processed
	 * @param endFeatureValue
	 *            the end offset of the Annotation whose one Feature is going to
	 *            be processed
	 * 
	 * @throws AnalysisEngineProcessException
	 *             if something wrong happened while processing this CAS view.
	 * 
	 * @return the result of the performed processing
	 */
  protected String processAnnotationFeatureStringValue(JCas inputViewJCas, String inputTextToProcess, int beginFeatureValue, int endFeatureValue)
    throws AnalysisEngineProcessException
  {
/*  847 */     return inputTextToProcess;
  }
  










	/**
	 * This method is invoked when the analysis has to be processed for some
	 * views
	 * 
	 * @deprecated
	 * 
	 * @param aJCas
	 *            the CAS over which the process is performed
	 * @param inputViewString
	 * @param contextAnnotationType
	 *            Type name of the annotations to consider as the context
	 *            annotations in which the process will be performed
	 * @param inputAnnotationType
	 *            Type name of the annotations to consider as the token units to
	 *            be processed
	 * @param inputFeatureString
	 * @param outputViewString
	 * @param outputViewTypeMimeString
	 * @param outputAnnotationString
	 * @param ouputFeatureString
	 * @throws AnalysisEngineProcessException
	 */
  private void processInputViewType(JCas aJCas, String inputViewString, String contextAnnotationString, String[] inputAnnotationStringArray, String inputFeatureString, String outputViewString, String outputViewTypeMimeString, String outputAnnotationString, String ouputFeatureString)
    throws AnalysisEngineProcessException
  {		/** -- Prepare the view to be processed **/
/*  880 */     log("Getting the inputViewJCas");
/*  881 */     JCas inputViewJCas = JCasUtils.getView(aJCas, inputViewString);
    

/**
		 * -- In case of the output type is annotation, get the view to store
		 * the result
		 **/
		// si les param annotations sont renseignés alors cela signifie que l'on
		// suppose qu'une vue existe pour accueillir les annotations
		// on effectue ici le getView pour d'éviter de le faire à chaque tour de
		// boucle
		// si l'inputType est annotation
/*  892 */     JCas outputViewJCas = null;
/*  893 */     String inputTextToProcess = "";
/*  894 */     inputTextToProcess = inputViewJCas.getSofaDataString();
/*  895 */     int beginFeatureValueFromAnnotationToCreate = 0;
/*  896 */     int endFeatureValueFromAnnotationToCreate = inputViewJCas
/*  897 */       .getSofaDataString().length();
    
		/** -- Execute and get result **/
/*  900 */     log("Executing and gettint the result");
/*  901 */     String commandResultString = "";
/*  902 */     commandResultString = processAnnotationFeatureStringValue(
/*  903 */       inputViewJCas, inputTextToProcess, 
/*  904 */       beginFeatureValueFromAnnotationToCreate, 
/*  905 */       endFeatureValueFromAnnotationToCreate);
    

		// Soit pour chaque annotation en entrée à traiter soit pour la vue en
		// entrée
/*  909 */     if (this.outputType.equalsIgnoreCase(OUTPUTTYPE_ANNOTATION))
    {
/*  911 */       log("Getting the outputViewJCas");
/*  912 */       outputViewJCas = JCasUtils.getView(aJCas, outputViewString);
      
/*  914 */       log("Creating output annotation");
      

/*  917 */       HashMap<String, Object> featuresHashMap = new HashMap();
/*  918 */       featuresHashMap.put("begin", String.valueOf(beginFeatureValueFromAnnotationToCreate));
/*  919 */       featuresHashMap.put("end", String.valueOf(endFeatureValueFromAnnotationToCreate));
/*  920 */       featuresHashMap.put(this.outputFeatureString, commandResultString);
/*  921 */       AnnotationUtils.createAnnotation(outputViewJCas, outputAnnotationString, featuresHashMap);

// @deprecated
			//AnnotationUtilities.createAnnotation(outputViewJCas,
			//		outputAnnotationString,
			//		beginFeatureValueFromAnnotationToCreate,
			//		endFeatureValueFromAnnotationToCreate, outputFeatureString,
			//		commandResultString);




    }
    else
    {


	/** -- Create view **/
			// L'output_type est view
			// On stocke les résultats obtenus pour chaque annotation
			// On copiera le tout dans le sofaDataString en une seule fois
			// if (commandResultString == null ) {commandResultString =
			// commandLocalResultString;}
			// else {
			log("Creating output view");
			// ici on suppose que outputViewString ne correspond à aucune vue
			// existante (a fortiori est différent de inputViewString)
			// et que createView génèrera une erreur si la vue existe déjà
      


/*  940 */       JCasUtils.createView(aJCas, outputViewString, 
/*  941 */         commandResultString, outputViewTypeMimeString);
    }
  }
  







	/**
	 * Log messages
	 * 
	 * @param message
	 *            to log
	 */
  protected void log(String message) {}
  








  protected String[] getInputViewStringArray()
  {
/*  965 */     return this.inputViewStringArray;
  }
  


  protected String[] getContextAnnotationStringArray()
  {
/*  972 */     return this.contextAnnotationStringArray;
  }
  


  protected Set<String> getContextAnnotationSet()
  {
/*  979 */     return this.contextAnnotationSet;
  }
  


  protected String[] getInputAnnotationStringArray()
  {
/*  986 */     return this.inputAnnotationStringArray;
  }
  


  protected Set<String> getInputAnnotationSet()
  {
/*  993 */     return this.inputAnnotationSet;
  }
  


  protected String getInputFeature()
  {
/* 1000 */     return this.inputFeature;
  }
  


  protected String getOutputView()
  {
/* 1007 */     return this.outputView;
  }
  


  protected String getOutputViewTypeMime()
  {
/* 1014 */     return this.outputViewTypeMime;
  }
  


  protected String getOutputAnnotation()
  {
/* 1021 */     return this.outputAnnotation;
  }
  



  protected String getOutputFeature()
  {
/* 1029 */     return this.outputFeatureString;
  }
  



  protected void setOutputView(String outputViewString)
  {
/* 1037 */     this.outputView = outputViewString;
  }
  



  protected void setOutputAnnotation(String outputAnnotationString)
  {
/* 1045 */     this.outputAnnotation = outputAnnotationString;
  }
  




  protected void setInputAnnotation(String inputAnnotationString) {}
  



  protected void setInputFeature(String inputFeatureString)
  {
/* 1059 */     this.inputFeature = inputFeatureString;
  }
  




  protected void setInputAnnotationSet(Set<String> inputAnnotationSet)
  {
/* 1068 */     this.inputAnnotationSet = inputAnnotationSet;
  }
}
