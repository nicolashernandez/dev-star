
/* First created by JCasGen Tue Jul 05 15:38:27 CEST 2011 */
package org.apache.uima;

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
 * Updated by JCasGen Fri Oct 14 18:41:42 CEST 2011
 * @generated */
public class TokenAnnotation_Type extends Annotation_Type {
  /** @generated */
  protected FSGenerator getFSGenerator() {return fsGenerator;}
  /** @generated */
  private final FSGenerator fsGenerator = 
    new FSGenerator() {
      public FeatureStructure createFS(int addr, CASImpl cas) {
  			 if (TokenAnnotation_Type.this.useExistingInstance) {
  			   // Return eq fs instance if already created
  		     FeatureStructure fs = TokenAnnotation_Type.this.jcas.getJfsFromCaddr(addr);
  		     if (null == fs) {
  		       fs = new TokenAnnotation(addr, TokenAnnotation_Type.this);
  			   TokenAnnotation_Type.this.jcas.putJfsFromCaddr(addr, fs);
  			   return fs;
  		     }
  		     return fs;
        } else return new TokenAnnotation(addr, TokenAnnotation_Type.this);
  	  }
    };
  /** @generated */
  public final static int typeIndexID = TokenAnnotation.typeIndexID;
  /** @generated 
     @modifiable */
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("org.apache.uima.TokenAnnotation");
 
  /** @generated */
  final Feature casFeat_posTag;
  /** @generated */
  final int     casFeatCode_posTag;
  /** @generated */ 
  public String getPosTag(int addr) {
        if (featOkTst && casFeat_posTag == null)
      jcas.throwFeatMissing("posTag", "org.apache.uima.TokenAnnotation");
    return ll_cas.ll_getStringValue(addr, casFeatCode_posTag);
  }
  /** @generated */    
  public void setPosTag(int addr, String v) {
        if (featOkTst && casFeat_posTag == null)
      jcas.throwFeatMissing("posTag", "org.apache.uima.TokenAnnotation");
    ll_cas.ll_setStringValue(addr, casFeatCode_posTag, v);}
    
  
 
  /** @generated */
  final Feature casFeat_lemma;
  /** @generated */
  final int     casFeatCode_lemma;
  /** @generated */ 
  public String getLemma(int addr) {
        if (featOkTst && casFeat_lemma == null)
      jcas.throwFeatMissing("lemma", "org.apache.uima.TokenAnnotation");
    return ll_cas.ll_getStringValue(addr, casFeatCode_lemma);
  }
  /** @generated */    
  public void setLemma(int addr, String v) {
        if (featOkTst && casFeat_lemma == null)
      jcas.throwFeatMissing("lemma", "org.apache.uima.TokenAnnotation");
    ll_cas.ll_setStringValue(addr, casFeatCode_lemma, v);}
    
  
 
  /** @generated */
  final Feature casFeat_cat;
  /** @generated */
  final int     casFeatCode_cat;
  /** @generated */ 
  public String getCat(int addr) {
        if (featOkTst && casFeat_cat == null)
      jcas.throwFeatMissing("cat", "org.apache.uima.TokenAnnotation");
    return ll_cas.ll_getStringValue(addr, casFeatCode_cat);
  }
  /** @generated */    
  public void setCat(int addr, String v) {
        if (featOkTst && casFeat_cat == null)
      jcas.throwFeatMissing("cat", "org.apache.uima.TokenAnnotation");
    ll_cas.ll_setStringValue(addr, casFeatCode_cat, v);}
    
  
 
  /** @generated */
  final Feature casFeat_subcat;
  /** @generated */
  final int     casFeatCode_subcat;
  /** @generated */ 
  public String getSubcat(int addr) {
        if (featOkTst && casFeat_subcat == null)
      jcas.throwFeatMissing("subcat", "org.apache.uima.TokenAnnotation");
    return ll_cas.ll_getStringValue(addr, casFeatCode_subcat);
  }
  /** @generated */    
  public void setSubcat(int addr, String v) {
        if (featOkTst && casFeat_subcat == null)
      jcas.throwFeatMissing("subcat", "org.apache.uima.TokenAnnotation");
    ll_cas.ll_setStringValue(addr, casFeatCode_subcat, v);}
    
  
 
  /** @generated */
  final Feature casFeat_ei;
  /** @generated */
  final int     casFeatCode_ei;
  /** @generated */ 
  public String getEi(int addr) {
        if (featOkTst && casFeat_ei == null)
      jcas.throwFeatMissing("ei", "org.apache.uima.TokenAnnotation");
    return ll_cas.ll_getStringValue(addr, casFeatCode_ei);
  }
  /** @generated */    
  public void setEi(int addr, String v) {
        if (featOkTst && casFeat_ei == null)
      jcas.throwFeatMissing("ei", "org.apache.uima.TokenAnnotation");
    ll_cas.ll_setStringValue(addr, casFeatCode_ei, v);}
    
  
 
  /** @generated */
  final Feature casFeat_ee;
  /** @generated */
  final int     casFeatCode_ee;
  /** @generated */ 
  public String getEe(int addr) {
        if (featOkTst && casFeat_ee == null)
      jcas.throwFeatMissing("ee", "org.apache.uima.TokenAnnotation");
    return ll_cas.ll_getStringValue(addr, casFeatCode_ee);
  }
  /** @generated */    
  public void setEe(int addr, String v) {
        if (featOkTst && casFeat_ee == null)
      jcas.throwFeatMissing("ee", "org.apache.uima.TokenAnnotation");
    ll_cas.ll_setStringValue(addr, casFeatCode_ee, v);}
    
  
 
  /** @generated */
  final Feature casFeat_mph;
  /** @generated */
  final int     casFeatCode_mph;
  /** @generated */ 
  public String getMph(int addr) {
        if (featOkTst && casFeat_mph == null)
      jcas.throwFeatMissing("mph", "org.apache.uima.TokenAnnotation");
    return ll_cas.ll_getStringValue(addr, casFeatCode_mph);
  }
  /** @generated */    
  public void setMph(int addr, String v) {
        if (featOkTst && casFeat_mph == null)
      jcas.throwFeatMissing("mph", "org.apache.uima.TokenAnnotation");
    ll_cas.ll_setStringValue(addr, casFeatCode_mph, v);}
    
  



  /** @generated */
  final Feature casFeat_chunkTag;
  /** @generated */
  final int     casFeatCode_chunkTag;
  /** @generated */ 
  public String getChunkTag(int addr) {
        if (featOkTst && casFeat_chunkTag == null)
      jcas.throwFeatMissing("chunkTag", "org.apache.uima.TokenAnnotation");
    return ll_cas.ll_getStringValue(addr, casFeatCode_chunkTag);
  }
  /** @generated */    
  public void setChunkTag(int addr, String v) {
        if (featOkTst && casFeat_chunkTag == null)
      jcas.throwFeatMissing("chunkTag", "org.apache.uima.TokenAnnotation");
    ll_cas.ll_setStringValue(addr, casFeatCode_chunkTag, v);}
    
  
 
  /** initialize variables to correspond with Cas Type and Features
	* @generated */
  public TokenAnnotation_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_posTag = jcas.getRequiredFeatureDE(casType, "posTag", "uima.cas.String", featOkTst);
    casFeatCode_posTag  = (null == casFeat_posTag) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_posTag).getCode();

 
    casFeat_chunkTag = jcas.getRequiredFeatureDE(casType, "chunkTag", "uima.cas.String", featOkTst);
    casFeatCode_chunkTag  = (null == casFeat_chunkTag) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_chunkTag).getCode();

 
    casFeat_lemma = jcas.getRequiredFeatureDE(casType, "lemma", "uima.cas.String", featOkTst);
    casFeatCode_lemma  = (null == casFeat_lemma) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_lemma).getCode();

 
    casFeat_cat = jcas.getRequiredFeatureDE(casType, "cat", "uima.cas.String", featOkTst);
    casFeatCode_cat  = (null == casFeat_cat) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_cat).getCode();

 
    casFeat_subcat = jcas.getRequiredFeatureDE(casType, "subcat", "uima.cas.String", featOkTst);
    casFeatCode_subcat  = (null == casFeat_subcat) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_subcat).getCode();

 
    casFeat_ei = jcas.getRequiredFeatureDE(casType, "ei", "uima.cas.String", featOkTst);
    casFeatCode_ei  = (null == casFeat_ei) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_ei).getCode();

 
    casFeat_ee = jcas.getRequiredFeatureDE(casType, "ee", "uima.cas.String", featOkTst);
    casFeatCode_ee  = (null == casFeat_ee) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_ee).getCode();

 
    casFeat_mph = jcas.getRequiredFeatureDE(casType, "mph", "uima.cas.String", featOkTst);
    casFeatCode_mph  = (null == casFeat_mph) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_mph).getCode();

  }
}



    