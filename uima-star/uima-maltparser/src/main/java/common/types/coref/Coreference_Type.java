
/* First created by JCasGen Thu May 30 11:49:29 CEST 2013 */
package common.types.coref;

import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.cas.impl.CASImpl;
import org.apache.uima.cas.impl.FSGenerator;
import org.apache.uima.cas.FeatureStructure;
import org.apache.uima.cas.impl.TypeImpl;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.impl.FeatureImpl;
import org.apache.uima.cas.Feature;
import common.types.text.Annotation_Type;

/** 
 * Updated by JCasGen Thu May 30 11:49:29 CEST 2013
 * @generated */
public class Coreference_Type extends Annotation_Type {
  /** @generated */
  @Override
  protected FSGenerator getFSGenerator() {return fsGenerator;}
  /** @generated */
  private final FSGenerator fsGenerator = 
    new FSGenerator() {
      public FeatureStructure createFS(int addr, CASImpl cas) {
  			 if (Coreference_Type.this.useExistingInstance) {
  			   // Return eq fs instance if already created
  		     FeatureStructure fs = Coreference_Type.this.jcas.getJfsFromCaddr(addr);
  		     if (null == fs) {
  		       fs = new Coreference(addr, Coreference_Type.this);
  			   Coreference_Type.this.jcas.putJfsFromCaddr(addr, fs);
  			   return fs;
  		     }
  		     return fs;
        } else return new Coreference(addr, Coreference_Type.this);
  	  }
    };
  /** @generated */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = Coreference.typeIndexID;
  /** @generated 
     @modifiable */
  @SuppressWarnings ("hiding")
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("common.types.coref.Coreference");
 
  /** @generated */
  final Feature casFeat_id;
  /** @generated */
  final int     casFeatCode_id;
  /** @generated */ 
  public String getId(int addr) {
        if (featOkTst && casFeat_id == null)
      jcas.throwFeatMissing("id", "common.types.coref.Coreference");
    return ll_cas.ll_getStringValue(addr, casFeatCode_id);
  }
  /** @generated */    
  public void setId(int addr, String v) {
        if (featOkTst && casFeat_id == null)
      jcas.throwFeatMissing("id", "common.types.coref.Coreference");
    ll_cas.ll_setStringValue(addr, casFeatCode_id, v);}
    
  
 
  /** @generated */
  final Feature casFeat_corefType;
  /** @generated */
  final int     casFeatCode_corefType;
  /** @generated */ 
  public String getCorefType(int addr) {
        if (featOkTst && casFeat_corefType == null)
      jcas.throwFeatMissing("corefType", "common.types.coref.Coreference");
    return ll_cas.ll_getStringValue(addr, casFeatCode_corefType);
  }
  /** @generated */    
  public void setCorefType(int addr, String v) {
        if (featOkTst && casFeat_corefType == null)
      jcas.throwFeatMissing("corefType", "common.types.coref.Coreference");
    ll_cas.ll_setStringValue(addr, casFeatCode_corefType, v);}
    
  
 
  /** @generated */
  final Feature casFeat_src;
  /** @generated */
  final int     casFeatCode_src;
  /** @generated */ 
  public String getSrc(int addr) {
        if (featOkTst && casFeat_src == null)
      jcas.throwFeatMissing("src", "common.types.coref.Coreference");
    return ll_cas.ll_getStringValue(addr, casFeatCode_src);
  }
  /** @generated */    
  public void setSrc(int addr, String v) {
        if (featOkTst && casFeat_src == null)
      jcas.throwFeatMissing("src", "common.types.coref.Coreference");
    ll_cas.ll_setStringValue(addr, casFeatCode_src, v);}
    
  
 
  /** @generated */
  final Feature casFeat_ptr;
  /** @generated */
  final int     casFeatCode_ptr;
  /** @generated */ 
  public String getPtr(int addr) {
        if (featOkTst && casFeat_ptr == null)
      jcas.throwFeatMissing("ptr", "common.types.coref.Coreference");
    return ll_cas.ll_getStringValue(addr, casFeatCode_ptr);
  }
  /** @generated */    
  public void setPtr(int addr, String v) {
        if (featOkTst && casFeat_ptr == null)
      jcas.throwFeatMissing("ptr", "common.types.coref.Coreference");
    ll_cas.ll_setStringValue(addr, casFeatCode_ptr, v);}
    
  
 
  /** @generated */
  final Feature casFeat_prev;
  /** @generated */
  final int     casFeatCode_prev;
  /** @generated */ 
  public String getPrev(int addr) {
        if (featOkTst && casFeat_prev == null)
      jcas.throwFeatMissing("prev", "common.types.coref.Coreference");
    return ll_cas.ll_getStringValue(addr, casFeatCode_prev);
  }
  /** @generated */    
  public void setPrev(int addr, String v) {
        if (featOkTst && casFeat_prev == null)
      jcas.throwFeatMissing("prev", "common.types.coref.Coreference");
    ll_cas.ll_setStringValue(addr, casFeatCode_prev, v);}
    
  
 
  /** @generated */
  final Feature casFeat_next;
  /** @generated */
  final int     casFeatCode_next;
  /** @generated */ 
  public String getNext(int addr) {
        if (featOkTst && casFeat_next == null)
      jcas.throwFeatMissing("next", "common.types.coref.Coreference");
    return ll_cas.ll_getStringValue(addr, casFeatCode_next);
  }
  /** @generated */    
  public void setNext(int addr, String v) {
        if (featOkTst && casFeat_next == null)
      jcas.throwFeatMissing("next", "common.types.coref.Coreference");
    ll_cas.ll_setStringValue(addr, casFeatCode_next, v);}
    
  



  /** initialize variables to correspond with Cas Type and Features
	* @generated */
  public Coreference_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_id = jcas.getRequiredFeatureDE(casType, "id", "uima.cas.String", featOkTst);
    casFeatCode_id  = (null == casFeat_id) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_id).getCode();

 
    casFeat_corefType = jcas.getRequiredFeatureDE(casType, "corefType", "uima.cas.String", featOkTst);
    casFeatCode_corefType  = (null == casFeat_corefType) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_corefType).getCode();

 
    casFeat_src = jcas.getRequiredFeatureDE(casType, "src", "uima.cas.String", featOkTst);
    casFeatCode_src  = (null == casFeat_src) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_src).getCode();

 
    casFeat_ptr = jcas.getRequiredFeatureDE(casType, "ptr", "uima.cas.String", featOkTst);
    casFeatCode_ptr  = (null == casFeat_ptr) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_ptr).getCode();

 
    casFeat_prev = jcas.getRequiredFeatureDE(casType, "prev", "uima.cas.String", featOkTst);
    casFeatCode_prev  = (null == casFeat_prev) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_prev).getCode();

 
    casFeat_next = jcas.getRequiredFeatureDE(casType, "next", "uima.cas.String", featOkTst);
    casFeatCode_next  = (null == casFeat_next) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_next).getCode();

  }
}



    