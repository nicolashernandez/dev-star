/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package fr.univnantes.lina.uima.connectors.io;

import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.apache.uima.UIMARuntimeException;
import org.apache.uima.UimaContext;
import org.apache.uima.analysis_component.JCasMultiplier_ImplBase;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.cas.AbstractCas;
import org.apache.uima.cas.ArrayFS;
import org.apache.uima.cas.BooleanArrayFS;
import org.apache.uima.cas.ByteArrayFS;
import org.apache.uima.cas.CAS;
import org.apache.uima.cas.CASException;
import org.apache.uima.cas.CASRuntimeException;
import org.apache.uima.cas.DoubleArrayFS;
import org.apache.uima.cas.FSIndex;
import org.apache.uima.cas.FSIterator;
import org.apache.uima.cas.Feature;
import org.apache.uima.cas.FeatureStructure;
import org.apache.uima.cas.FloatArrayFS;
import org.apache.uima.cas.IntArrayFS;
import org.apache.uima.cas.LongArrayFS;
import org.apache.uima.cas.ShortArrayFS;
import org.apache.uima.cas.SofaFS;
import org.apache.uima.cas.StringArrayFS;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.impl.CASImpl;
import org.apache.uima.cas.impl.LowLevelCAS;
import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.examples.SourceDocumentInformation;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.tcas.Annotation;
import org.apache.uima.resource.ResourceInitializationException;
import org.apache.uima.util.CasCopier;

import fr.univnantes.lina.javautil.DateUtilities;
import fr.univnantes.lina.uima.common.cas.JCasUtils;

/**
 * TODO
 * Tentative de passer à http://www.fabienpoulard.info/post/2011/06/05/Copier-une-annotation-d-une-vue-%C3%A0-une-autre-dans-UIMA
 * Ne fonctionne pas...
 * 
 * Rename the named view to another.
 * The actual process copies the content (sofa and feature structure) of named views to a new one which is set with a target name.
 * A list of from/to views couples is given in parameter.  
 */
public class BrancheViewRenamerAE extends JCasMultiplier_ImplBase {

	/*
	 * PARAMETERS NAMES
	 */

	/**
	 * Name of the FromViewToView Parameter
	 */
	public static final String FROM_VIEW_TO_VIEW_PARAM = "FromViewToView";

	/*
	 * PARAMETERS DEFAULT VALUES
	 */
	/**
	 * Name of the default separator between the FromView and the ToView associated
	 */
	private static String DEFAULT_FROMVIEW_AND_TOVIEW_SEPARATOR = "->";


	/*
	 * LOCAL VARIABLES
	 */
	private Map<String,String> fromViewToViewHashMap ;

	private JCas sourceJCas ;

	private CAS mDestCas;
	private LowLevelCAS mLowLevelDestCas;

	private Boolean renameDone = false;
	private Map mFsMap = new HashMap();

	private String mDoc;

	private String mDocUri;

	private static Boolean debug = false;


	/*
	 * ACCESSORS
	 */


	/**
	 * @return the renameDone
	 */
	private Boolean getRenameDone() {
		return renameDone;
	}

	/**
	 * @param renameDone the renameDone to set
	 */
	private void setRenameDone(Boolean renameDone) {
		this.renameDone = renameDone;
	}

	/**
	 * @return the initialJCas
	 */
	private JCas getSourceJCas() {
		return sourceJCas;
	}

	/**
	 * @param initialJCas the initialJCas to set
	 */
	private void setSourceJCas(JCas initialJCas) {
		this.sourceJCas = initialJCas;
	}


	/*
	 * METHODS 
	 */


	/**
	 * Parameter settings and checking
	 * 
	 */
	public void initialize(UimaContext context) throws ResourceInitializationException {

		// SUPER PARAMETER SETTINGS
		super.initialize(context);

		// CURRENT AE PARAMETER SETTINGS
		// Get the from to view values
		String[] fromViewToViewStringArray =  (String[]) context.getConfigParameterValue(FROM_VIEW_TO_VIEW_PARAM);

		// check if at least one rename request is defined
		if (fromViewToViewStringArray == null) {
			String errmsg = "At least one FromViewToView parameter must be set and the refered views exist !";
			throw new ResourceInitializationException(errmsg, new Object[] {});
		}

		// get the couple to rename and check if the syntax was correct
		fromViewToViewHashMap = new HashMap<String, String>();
		for (int i = 0;  i < fromViewToViewStringArray.length ; i++) {
			String[] aFromViewToViewCouple = fromViewToViewStringArray[i].split(DEFAULT_FROMVIEW_AND_TOVIEW_SEPARATOR);
			if (aFromViewToViewCouple.length == 2) fromViewToViewHashMap.put(aFromViewToViewCouple[1].trim(),aFromViewToViewCouple[0].trim());
			else {
				String errmsg = "Wrong syntax to the "+FROM_VIEW_TO_VIEW_PARAM+" paramater with the following line "+ aFromViewToViewCouple.toString() ;
				throw new ResourceInitializationException(errmsg, new Object[] {});
			}
		}

	}

	/**
	 * Main method for CAS Multiplier, the actual renaming is processed in the next method
	 * 
	 * @see org.apache.uima.analysis_component.AnalysisComponent#process()
	 */
	public void process(JCas aJCas) throws AnalysisEngineProcessException {

		System.err.println("Info: "+this.getClass().getName()+" starts at " + DateUtilities.now());

		if (debug) System.out.println("Debug: ViewViewRenamerper process -------------");

		// only one source cas will be processed 
		setRenameDone(false);

		// make the source cas available for the other methods 
		setSourceJCas(aJCas);

		mDoc = aJCas.getDocumentText();
		// retrieve the filename of the input file from the CAS so that it can be added
		// to each segment
		FSIterator it = aJCas.getAnnotationIndex(SourceDocumentInformation.type).iterator();
		if (it.hasNext()) {
			SourceDocumentInformation fileLoc = (SourceDocumentInformation) it.next();
			mDocUri = fileLoc.getUri();
		} else {
			mDocUri = null;
		}



	}

	/**
	 * Actually only one CAS is assumed to be created
	 * 
	 * @see org.apache.uima.analysis_component.AnalysisComponent#hasNext()
	 */
	public boolean hasNext() throws AnalysisEngineProcessException {
		if (debug) System.out.println("Debug: ViewRenamer hasNext -------------");

		return ! getRenameDone();
	}

	/**
	 * The actual renaming process 
	 * the code is largely reused from the CasCopier class with some adaptations
	 *
	 * @see org.apache.uima.util.CasCopier 
	 * {@link  http://uima.apache.org/downloads/releaseDocs/2.1.0-incubating/docs/html/tutorials_and_users_guides/tutorials_and_users_guides.html#ugr.tug.cm.developing_multiplier_code}
	 * {@link http://uima.apache.org/d/uimaj-2.3.1/api/org/apache/uima/util/CasCopier.html}
	 * {@link http://svn.apache.org/repos/asf/uima/uimaj/branches/uimaj-2.1.0/uimaj-core/src/main/java/org/apache/uima/util/CasCopier.java}
	 * 
	 * @see org.apache.uima.analysis_component.AnalysisComponent#next()
	 */
	public AbstractCas next() throws AnalysisEngineProcessException {
		if (debug) System.out.println("Debug: ViewRenamer next --------------");

		// set there is no more source CAS to process 
		setRenameDone(true);

		// create the future CAS (automatically the _InitialView will be created)
		JCas futureJCas = getEmptyJCas();

		//		if (debug) {		
		//			Iterator viewIter = null;
		//			try {
		//				viewIter = getSourceJCas().getViewIterator();
		//			} catch (CASException e1) {
		//				// TODO Auto-generated catch block
		//				e1.printStackTrace();
		//			}
		//			while (viewIter.hasNext()) {
		//				JCas aView = ((JCas)viewIter.next());
		//				System.out.println("Debug: ViewRenamer next view from getSourceJCas "+ aView.getViewName());
		//			}
		//		}
		//
		//
		//		if (debug) {
		//			Iterator viewIter = null;
		//			try {
		//				viewIter = futureJCas.getViewIterator();
		//			} catch (CASException e1) {
		//				// TODO Auto-generated catch block
		//				e1.printStackTrace();
		//			}
		//			while (viewIter.hasNext()) {
		//				JCas aView = ((JCas)viewIter.next());
		//				System.out.println("Debug: ViewRenamer next view from getEmptyJCas "+ aView.getViewName());
		//			}}

		// Settings the SourceDocumentInformation
//		if (mDocUri != null) {
//			SourceDocumentInformation sdi = new SourceDocumentInformation(futureJCas);
//			sdi.setUri(mDocUri);
//			sdi.setOffsetInSource(0);
//			sdi.setDocumentSize(mDoc.length());
//			sdi.addToIndexes();
//		}

		// Copy fromView toView
		try {
			//mDestCas = futureJCas.getCas();
			//mLowLevelDestCas = futureJCas.getCas().getLowLevelCAS();

			// full copy of the cas (copy the content and the name)  
			// works fine 
			// CasCopier.copyCas(sourceJCas.getView(getToViewName()).getCas(),mDestCas,true) ;

			// for each fromTo view couples to process
			Iterator<String> toViewStringIterator = fromViewToViewHashMap.keySet().iterator(); 
			while (toViewStringIterator.hasNext()) {

				// get the "from view name" and the "to view name"
				String toViewName = toViewStringIterator.next();
				String fromViewName = (String) fromViewToViewHashMap.get(toViewName);

				// get (in case the ToView is _InitialView) or create a target view
				CAS targetView = getOrCreateView(futureJCas.getCas(), toViewName); 

				if (debug) {
//					System.out.println("Debug: ViewRenamer next fromViewName "+fromViewName+" -> toViewName "+ toViewName +" which should be targetViewName " + targetView.getViewName());
					System.out.println("Debug: ViewRenamer next fromViewName "+fromViewName+" -> toViewName "+ toViewName +" which should be targetViewName " + futureJCas.getViewName());

				}

				// copy sofaData, mimeType and language
				copySofaData ( targetView.getJCas(), fromViewName);
				//copySofaData ( futureJCas.getView(toViewName), fromViewName);


				// now copy indexed FS, but keep track so we don't index anything more than once
				Set indexedFs = new HashSet();
				Iterator indexes = sourceJCas.getView(fromViewName).getCas().getIndexRepository().getIndexes();
				while (indexes.hasNext()) {
					FSIndex index = (FSIndex) indexes.next();

					Iterator iter = index.iterator();
					while (iter.hasNext()) {
						FeatureStructure fs = (FeatureStructure) iter.next();

						if (!indexedFs.contains(fs)) {
							System.out.println("Debug: fs.getClass().getName() "+ fs.getClass().getName());

							if (!(fs.getClass().getName().equalsIgnoreCase("org.apache.uima.jcas.tcas.DocumentAnnotation") || 
								 fs.getClass().getName().equalsIgnoreCase("org.apache.uima.examples.SourceDocumentInformation")) ) {
								//copyAnnotationToView(a, view);
								// To copy the annotation we must process in three steps
								// 1- Clone the annotation from the original view
								FeatureStructure fs2 = (FeatureStructure) fs.clone();
								// 2- Change the Sofa of the cloned featureStructure
								Feature sofaFeature = fs2.getType().getFeatureByBaseName("sofa");
								fs2.setFeatureValue(sofaFeature, futureJCas.getSofa());
								//							fs2.setFeatureValue(sofaFeature, futureJCas.getSofa());

								// 3- Add this annotation to the indexes of the new view
								//fs2.addToIndexes(futureJCas);
								//futureJCas.addFsToIndexes(fs2);
								futureJCas.addFsToIndexes(fs2);
							}
						}
					}
				}
			}

			return futureJCas;

		} catch (Exception e) {
			futureJCas.release();
			throw new AnalysisEngineProcessException(e);
		}

	}

	/**
	 * Determines whether the given FS is the DocumentAnnotation for its view.  
	 * This is more than just a type check; we actually check if it is the one "special"
	 * DocumentAnnotation that CAS.getDocumentAnnotation() would return.
	 */
	private static boolean isDocumentAnnotation(FeatureStructure aFS) {
		if (debug) {
			System.out.println("Debug: ViewRenamerAE isDocumentAnnotation -------------- aFS.toString() "+ aFS.toString());
		}
		return (aFS instanceof AnnotationFS) &&
		aFS.equals(((AnnotationFS)aFS).getView().getDocumentAnnotation());
	}


	/**
	 * Copies an FS from the source CAS to the destination CAS. Also copies any referenced FS, except
	 * that previously copied FS will not be copied again.
	 * 
	 * @param aFS
	 *          the FS to copy. Must be contained within the source CAS.
	 * @param aFromViewName TODO
	 * @return the copy of <code>aFS</code> in the target CAS.
	 */
	public FeatureStructure copyFs(FeatureStructure aFS, String aFromViewName) {
		if (debug) { System.out.println("Debug: ViewRenamerAE copyFs ----------------------");}
		//FS must be in the source CAS
		//assert ((CASImpl)aFS.getCAS()).getBaseCAS() == ((CASImpl)getSourceJCas().getCas()).getBaseCAS();
		try {
			assert ((CASImpl)aFS.getCAS()).getBaseCAS() == ((CASImpl)getSourceJCas().getView(aFromViewName).getCas()).getBaseCAS();
		} catch (CASException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// check if we already copied this FS
		FeatureStructure copy = (FeatureStructure) mFsMap.get(aFS);
		if (copy != null)
			return copy;

		// get the type of the FS
		Type srcType = aFS.getType();

		// Certain types need to be handled specially

		// Sofa - cannot be created by normal methods.  Instead, we return the Sofa with the
		// same Sofa ID in the target CAS.  If it does not exist it will be created.
		if (aFS instanceof SofaFS) {
			String sofaId = ((SofaFS)aFS).getSofaID();

			//return getOrCreateView(mDestCas, sofaId).getSofa();
			try {
				return getOrCreateView(getSourceJCas().getView(aFromViewName).getCas(), sofaId).getSofa();
			} catch (CASException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}


		// DocumentAnnotation - instead of creating a new instance, reuse the automatically created
		// instance in the destination view. 
		if (isDocumentAnnotation(aFS)) {    

			String viewName = ((AnnotationFS)aFS).getView().getViewName();

			// nh
			// is not yet recreated so cannot be used
			//CAS destView = mDestCas.getView(viewName);
			CAS destView = null;
			try {
				destView = getSourceJCas().getView(viewName).getCas();

			} catch (CASException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			FeatureStructure destDocAnnot = destView.getDocumentAnnotation();
			//

			if (destDocAnnot != null) {
				copyFeatures(aFS, destDocAnnot, aFromViewName);
			}
			return destDocAnnot;
		}

		// Arrays - need to be created a populated differently than "normal" FS
		if (aFS.getType().isArray()) {
			copy = copyArray(aFS, aFromViewName);
			mFsMap.put(aFS, copy);
			return copy;
		}

		// create a new FS of the same type in the target CAS
		// TODO: could optimize type lookup if type systems are identical
		Type destType = mDestCas.getTypeSystem().getType(srcType.getName());
		if (destType == null) {
			throw new UIMARuntimeException(UIMARuntimeException.TYPE_NOT_FOUND_DURING_CAS_COPY,
					new Object[] { srcType.getName() });
		}
		//We need to use the LowLevel CAS interface to create the FS, because the usual
		//CAS.createFS() call doesn't allow us to create subtypes of AnnotationBase from
		//a base CAS.  In any case we don't need the Sofa reference to be automatically
		//set because we'll set it manually when in the copyFeatures method. 
		int typeCode = mLowLevelDestCas.ll_getTypeSystem().ll_getCodeForType(destType);
		int destFsAddr = mLowLevelDestCas.ll_createFS(typeCode);
		FeatureStructure destFs = mDestCas.getLowLevelCAS().ll_getFSForRef(destFsAddr);

		// add to map so we don't try to copy this more than once
		mFsMap.put(aFS, destFs);

		copyFeatures(aFS, destFs, aFromViewName);
		return destFs;
	}


	/**
	 * Copy feature values from one FS to another. For reference-valued features, this does a deep
	 * copy.
	 * 
	 * @param aSrcFS
	 *          FeatureStructure to copy from
	 * @param aDestFS
	 *          FeatureStructure to copy to
	 * @param aFromViewName TODO
	 */
	private void copyFeatures(FeatureStructure aSrcFS, FeatureStructure aDestFS, String aFromViewName) {
		if (debug) System.out.println("Debug: ViewRenamerAE copyFeatures -------");
		// set feature values
		Type srcType = aSrcFS.getType();
		Type destType = aDestFS.getType();
		Iterator srcFeatIter = srcType.getFeatures().iterator();
		while (srcFeatIter.hasNext()) {
			Feature srcFeat = (Feature) srcFeatIter.next();


			// TODO: could optimize type lookup if type systems are identical
			Feature destFeat;
			if (destType == aSrcFS.getType()) {
				// sharing same type system, so destFeat == srcFeat
				destFeat = srcFeat;
			} else {
				// not sharing same type system, so do a name loop up in destination type system
				destFeat = destType.getFeatureByBaseName(srcFeat.getShortName());
				if (destFeat == null) {
					throw new UIMARuntimeException(UIMARuntimeException.TYPE_NOT_FOUND_DURING_CAS_COPY,
							new Object[] { srcFeat.getName() });
				}
			}

			// copy primitive values using their string representation
			// TODO: could be optimized but this code would be very messy if we have to
			// enumerate all possible primitive types. Maybe LowLevel CAS API could help?
			if (srcFeat.getRange().isPrimitive()) {
				aDestFS.setFeatureValueFromString(destFeat, aSrcFS.getFeatureValueAsString(srcFeat));
			} else {
				// recursive copy
				if (debug) System.out.println("Debug: ViewRenamerAE copyFeatures srcFeat.getName()  "+ srcFeat.getName() );
				// nh
				if (!srcFeat.getName().equalsIgnoreCase("uima.cas.AnnotationBase:sofa")) {

					FeatureStructure refFS = aSrcFS.getFeatureValue(srcFeat);
					if (debug) System.out.println("Debug: ViewRenamerAE copyFeatures refFS.toString() "+ refFS.toString());

					if (refFS != null) {
						FeatureStructure copyRefFs = copyFs(refFS, aFromViewName);
						if (debug) System.out.println("Debug: ViewRenamerAE copyFeatures refFS.toString() "+ refFS.toString());

						aDestFS.setFeatureValue(destFeat, copyRefFs);
					}
				}
			}
		}
	}

	/**
	 * @param aFromViewName TODO
	 * @param arrayFS
	 * @return
	 */
	private FeatureStructure copyArray(FeatureStructure aSrcFs, String aFromViewName) {
		// TODO: there should be a way to do this without enumerating all the array types!
		if (aSrcFs instanceof StringArrayFS) {
			StringArrayFS arrayFs = (StringArrayFS) aSrcFs;
			int len = arrayFs.size();
			StringArrayFS destFS = mDestCas.createStringArrayFS(len);
			for (int i = 0; i < len; i++) {
				destFS.set(i, arrayFs.get(i));
			}
			return destFS;
		}
		if (aSrcFs instanceof IntArrayFS) {
			IntArrayFS arrayFs = (IntArrayFS) aSrcFs;
			int len = arrayFs.size();
			IntArrayFS destFS = mDestCas.createIntArrayFS(len);
			for (int i = 0; i < len; i++) {
				destFS.set(i, arrayFs.get(i));
			}
			return destFS;
		}
		if (aSrcFs instanceof ByteArrayFS) {
			ByteArrayFS arrayFs = (ByteArrayFS) aSrcFs;
			int len = arrayFs.size();
			ByteArrayFS destFS = mDestCas.createByteArrayFS(len);
			for (int i = 0; i < len; i++) {
				destFS.set(i, arrayFs.get(i));
			}
			return destFS;
		}
		if (aSrcFs instanceof ShortArrayFS) {
			ShortArrayFS arrayFs = (ShortArrayFS) aSrcFs;
			int len = arrayFs.size();
			ShortArrayFS destFS = mDestCas.createShortArrayFS(len);
			for (int i = 0; i < len; i++) {
				destFS.set(i, arrayFs.get(i));
			}
			return destFS;
		}
		if (aSrcFs instanceof LongArrayFS) {
			LongArrayFS arrayFs = (LongArrayFS) aSrcFs;
			int len = arrayFs.size();
			LongArrayFS destFS = mDestCas.createLongArrayFS(len);
			for (int i = 0; i < len; i++) {
				destFS.set(i, arrayFs.get(i));
			}
			return destFS;
		}
		if (aSrcFs instanceof FloatArrayFS) {
			FloatArrayFS arrayFs = (FloatArrayFS) aSrcFs;
			int len = arrayFs.size();
			FloatArrayFS destFS = mDestCas.createFloatArrayFS(len);
			for (int i = 0; i < len; i++) {
				destFS.set(i, arrayFs.get(i));
			}
			return destFS;
		}
		if (aSrcFs instanceof DoubleArrayFS) {
			DoubleArrayFS arrayFs = (DoubleArrayFS) aSrcFs;
			int len = arrayFs.size();
			DoubleArrayFS destFS = mDestCas.createDoubleArrayFS(len);
			for (int i = 0; i < len; i++) {
				destFS.set(i, arrayFs.get(i));
			}
			return destFS;
		}
		if (aSrcFs instanceof BooleanArrayFS) {
			BooleanArrayFS arrayFs = (BooleanArrayFS) aSrcFs;
			int len = arrayFs.size();
			BooleanArrayFS destFS = mDestCas.createBooleanArrayFS(len);
			for (int i = 0; i < len; i++) {
				destFS.set(i, arrayFs.get(i));
			}
			return destFS;
		}
		if (aSrcFs instanceof ArrayFS) {
			ArrayFS arrayFs = (ArrayFS) aSrcFs;
			int len = arrayFs.size();
			ArrayFS destFS = mDestCas.createArrayFS(len);
			for (int i = 0; i < len; i++) {
				FeatureStructure srcElem = arrayFs.get(i);
				if (srcElem != null) {
					FeatureStructure copyElem = copyFs(arrayFs.get(i), aFromViewName);
					destFS.set(i, copyElem);
				}
			}
			return destFS;
		}
		assert false; // the set of array types should be exhaustive, so we should never get here
		return null;
	}

	/**
	 * Gets the named view; if the view doesn't exist it will be created.
	 */
	private static CAS getOrCreateView(CAS aCas, String aViewName) {
		//TODO: there should be some way to do this without the try...catch
		try {
			return aCas.getView(aViewName); 
		}
		catch(CASRuntimeException e) {
			//create the view
			return aCas.createView(aViewName); 
		}
	}  

	/**
	 * Copy the sofa data, the mimeType and the DocumentLanguage
	 * 
	 * @param targetView
	 * @param aFromViewName 
	 */
	public void copySofaData (JCas targetView, String aFromViewName) {
		try {
			String sofaMime = sourceJCas.getView(aFromViewName).getCas().getSofa().getSofaMime();

			if (sourceJCas.getView(aFromViewName).getCas().getDocumentText() != null) {
				targetView.setSofaDataString(sourceJCas.getView(aFromViewName).getCas().getDocumentText(), sofaMime);
				if (debug) System.out.println("Debug: ViewRenamer next targetView.setSofaDataString(sourceJCas.getView(getFromViewName()).getCas().getDocumentText(), sofaMime);");
			} else if (sourceJCas.getView(aFromViewName).getCas().getSofaDataURI() != null) {
				targetView.setSofaDataURI(sourceJCas.getView(aFromViewName).getCas().getSofaDataURI(), sofaMime);
				if (debug) System.out.println("Debug: ViewRenamer next targetView.setSofaDataURI(sourceJCas.getView(getFromViewName()).getCas().getSofaDataURI(), sofaMime);");
			} else if (sourceJCas.getView(aFromViewName).getCas().getSofaDataArray() != null) {
				//targetView.setSofaDataArray(copier.copyFs(sourceJCas.getView(getFromViewName()).getCas().getSofaDataArray()), sofaMime);
				targetView.setSofaDataArray(copyFs(sourceJCas.getView(aFromViewName).getCas().getSofaDataArray(), aFromViewName), sofaMime);
				if (debug) System.out.println("Debug: ViewRenamer next targetView.setSofaDataArray(copyFs(sourceJCas.getView(getFromViewName()).getCas().getSofaDataArray()), sofaMime);");
			}
			// nh
			// je suppose q le Sofa (et ses traits mimeType, sofaString... ) et le DocumentAnnotation (et son trait sofa et langage) sont définis à ce moment là et que l'on a pas besoin de le faire
			targetView.setDocumentLanguage(sourceJCas.getView(aFromViewName).getCas().getDocumentLanguage());
		} catch (CASException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}

	/**
	 * copy annotation
	 * @param annotation
	 * @param view
	 * @return
	 */
	public static Annotation copyAnnotationToView(Annotation annotation, JCas view) {
		// To copy the annotation we must process in three steps
		// 1- Clone the annotation from the original view
		Annotation a2 = (Annotation) annotation.clone();
		// 2- Change the Sofa of the cloned annotation
		Feature sofaFeature = a2.getType().getFeatureByBaseName("sofa");
		a2.setFeatureValue(sofaFeature, view.getSofa());
		// 3- Add this annotation to the indexes of the new view
		a2.addToIndexes(view);
		return a2;
	}

}
