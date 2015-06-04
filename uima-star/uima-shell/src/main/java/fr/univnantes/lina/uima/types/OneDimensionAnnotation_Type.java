
/* First created by JCasGen Wed Sep 29 23:45:19 CEST 2010 */
package fr.univnantes.lina.uima.types;

import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.cas.impl.CASImpl;
import org.apache.uima.cas.impl.FSGenerator;
import org.apache.uima.cas.FeatureStructure;
import org.apache.uima.cas.impl.TypeImpl;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.impl.FeatureImpl;
import org.apache.uima.cas.Feature;
import org.apache.uima.jcas.tcas.Annotation_Type;

/** 
 * Updated by JCasGen Sat May 26 21:22:19 CEST 2012
 * @generated */
public class OneDimensionAnnotation_Type extends Annotation_Type {
  /** @generated */
  protected FSGenerator getFSGenerator() {return fsGenerator;}
  /** @generated */
  private final FSGenerator fsGenerator = 
    new FSGenerator() {
      public FeatureStructure createFS(int addr, CASImpl cas) {
  			 if (OneDimensionAnnotation_Type.this.useExistingInstance) {
  			   // Return eq fs instance if already created
  		     FeatureStructure fs = OneDimensionAnnotation_Type.this.jcas.getJfsFromCaddr(addr);
  		     if (null == fs) {
  		       fs = new OneDimensionAnnotation(addr, OneDimensionAnnotation_Type.this);
  			   OneDimensionAnnotation_Type.this.jcas.putJfsFromCaddr(addr, fs);
  			   return fs;
  		     }
  		     return fs;
        } else return new OneDimensionAnnotation(addr, OneDimensionAnnotation_Type.this);
  	  }
    };
  /** @generated */
  public final static int typeIndexID = OneDimensionAnnotation.typeIndexID;
  /** @generated 
     @modifiable */
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("fr.univnantes.lina.uima.types.OneDimensionAnnotation");
 
  /** @generated */
  final Feature casFeat_componentId;
  /** @generated */
  final int     casFeatCode_componentId;
  /** @generated */ 
  public String getComponentId(int addr) {
        if (featOkTst && casFeat_componentId == null)
      jcas.throwFeatMissing("componentId", "fr.univnantes.lina.uima.types.OneDimensionAnnotation");
    return ll_cas.ll_getStringValue(addr, casFeatCode_componentId);
  }
  /** @generated */    
  public void setComponentId(int addr, String v) {
        if (featOkTst && casFeat_componentId == null)
      jcas.throwFeatMissing("componentId", "fr.univnantes.lina.uima.types.OneDimensionAnnotation");
    ll_cas.ll_setStringValue(addr, casFeatCode_componentId, v);}
    
  
 
  /** @generated */
  final Feature casFeat_runId;
  /** @generated */
  final int     casFeatCode_runId;
  /** @generated */ 
  public String getRunId(int addr) {
        if (featOkTst && casFeat_runId == null)
      jcas.throwFeatMissing("runId", "fr.univnantes.lina.uima.types.OneDimensionAnnotation");
    return ll_cas.ll_getStringValue(addr, casFeatCode_runId);
  }
  /** @generated */    
  public void setRunId(int addr, String v) {
        if (featOkTst && casFeat_runId == null)
      jcas.throwFeatMissing("runId", "fr.univnantes.lina.uima.types.OneDimensionAnnotation");
    ll_cas.ll_setStringValue(addr, casFeatCode_runId, v);}
    
  



  /** initialize variables to correspond with Cas Type and Features
	* @generated */
  public OneDimensionAnnotation_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_componentId = jcas.getRequiredFeatureDE(casType, "componentId", "uima.cas.String", featOkTst);
    casFeatCode_componentId  = (null == casFeat_componentId) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_componentId).getCode();

 
    casFeat_runId = jcas.getRequiredFeatureDE(casType, "runId", "uima.cas.String", featOkTst);
    casFeatCode_runId  = (null == casFeat_runId) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_runId).getCode();

  }
}



    