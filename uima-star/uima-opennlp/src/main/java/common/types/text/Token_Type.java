
/* First created by JCasGen Sat Dec 10 00:59:10 CET 2011 */
package common.types.text;

import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.cas.impl.CASImpl;
import org.apache.uima.cas.impl.FSGenerator;
import org.apache.uima.cas.FeatureStructure;
import org.apache.uima.cas.impl.TypeImpl;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.impl.FeatureImpl;
import org.apache.uima.cas.Feature;

/** 
 * Updated by JCasGen Sat Dec 10 00:59:12 CET 2011
 * @generated */
public class Token_Type extends Annotation_Type {
  /** @generated */
  protected FSGenerator getFSGenerator() {return fsGenerator;}
  /** @generated */
  private final FSGenerator fsGenerator = 
    new FSGenerator() {
      public FeatureStructure createFS(int addr, CASImpl cas) {
  			 if (Token_Type.this.useExistingInstance) {
  			   // Return eq fs instance if already created
  		     FeatureStructure fs = Token_Type.this.jcas.getJfsFromCaddr(addr);
  		     if (null == fs) {
  		       fs = new Token(addr, Token_Type.this);
  			   Token_Type.this.jcas.putJfsFromCaddr(addr, fs);
  			   return fs;
  		     }
  		     return fs;
        } else return new Token(addr, Token_Type.this);
  	  }
    };
  /** @generated */
  public final static int typeIndexID = Token.typeIndexID;
  /** @generated 
     @modifiable */
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("common.types.text.Token");
 
  /** @generated */
  final Feature casFeat_lemma;
  /** @generated */
  final int     casFeatCode_lemma;
  /** @generated */ 
  public String getLemma(int addr) {
        if (featOkTst && casFeat_lemma == null)
      jcas.throwFeatMissing("lemma", "common.types.text.Token");
    return ll_cas.ll_getStringValue(addr, casFeatCode_lemma);
  }
  /** @generated */    
  public void setLemma(int addr, String v) {
        if (featOkTst && casFeat_lemma == null)
      jcas.throwFeatMissing("lemma", "common.types.text.Token");
    ll_cas.ll_setStringValue(addr, casFeatCode_lemma, v);}
    
  
 
  /** @generated */
  final Feature casFeat_mph;
  /** @generated */
  final int     casFeatCode_mph;
  /** @generated */ 
  public String getMph(int addr) {
        if (featOkTst && casFeat_mph == null)
      jcas.throwFeatMissing("mph", "common.types.text.Token");
    return ll_cas.ll_getStringValue(addr, casFeatCode_mph);
  }
  /** @generated */    
  public void setMph(int addr, String v) {
        if (featOkTst && casFeat_mph == null)
      jcas.throwFeatMissing("mph", "common.types.text.Token");
    ll_cas.ll_setStringValue(addr, casFeatCode_mph, v);}
    
  
 
  /** @generated */
  final Feature casFeat_posTag;
  /** @generated */
  final int     casFeatCode_posTag;
  /** @generated */ 
  public String getPosTag(int addr) {
        if (featOkTst && casFeat_posTag == null)
      jcas.throwFeatMissing("posTag", "common.types.text.Token");
    return ll_cas.ll_getStringValue(addr, casFeatCode_posTag);
  }
  /** @generated */    
  public void setPosTag(int addr, String v) {
        if (featOkTst && casFeat_posTag == null)
      jcas.throwFeatMissing("posTag", "common.types.text.Token");
    ll_cas.ll_setStringValue(addr, casFeatCode_posTag, v);}
    
  
 
  /** @generated */
  final Feature casFeat_chunkTag;
  /** @generated */
  final int     casFeatCode_chunkTag;
  /** @generated */ 
  public String getChunkTag(int addr) {
        if (featOkTst && casFeat_chunkTag == null)
      jcas.throwFeatMissing("chunkTag", "common.types.text.Token");
    return ll_cas.ll_getStringValue(addr, casFeatCode_chunkTag);
  }
  /** @generated */    
  public void setChunkTag(int addr, String v) {
        if (featOkTst && casFeat_chunkTag == null)
      jcas.throwFeatMissing("chunkTag", "common.types.text.Token");
    ll_cas.ll_setStringValue(addr, casFeatCode_chunkTag, v);}
    
  
 
  /** @generated */
  final Feature casFeat_raw;
  /** @generated */
  final int     casFeatCode_raw;
  /** @generated */ 
  public String getRaw(int addr) {
        if (featOkTst && casFeat_raw == null)
      jcas.throwFeatMissing("raw", "common.types.text.Token");
    return ll_cas.ll_getStringValue(addr, casFeatCode_raw);
  }
  /** @generated */    
  public void setRaw(int addr, String v) {
        if (featOkTst && casFeat_raw == null)
      jcas.throwFeatMissing("raw", "common.types.text.Token");
    ll_cas.ll_setStringValue(addr, casFeatCode_raw, v);}
    
  
 
  /** @generated */
  final Feature casFeat_subCat;
  /** @generated */
  final int     casFeatCode_subCat;
  /** @generated */ 
  public String getSubCat(int addr) {
        if (featOkTst && casFeat_subCat == null)
      jcas.throwFeatMissing("subCat", "common.types.text.Token");
    return ll_cas.ll_getStringValue(addr, casFeatCode_subCat);
  }
  /** @generated */    
  public void setSubCat(int addr, String v) {
        if (featOkTst && casFeat_subCat == null)
      jcas.throwFeatMissing("subCat", "common.types.text.Token");
    ll_cas.ll_setStringValue(addr, casFeatCode_subCat, v);}
    
  



  /** initialize variables to correspond with Cas Type and Features
	* @generated */
  public Token_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_lemma = jcas.getRequiredFeatureDE(casType, "lemma", "uima.cas.String", featOkTst);
    casFeatCode_lemma  = (null == casFeat_lemma) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_lemma).getCode();

 
    casFeat_mph = jcas.getRequiredFeatureDE(casType, "mph", "uima.cas.String", featOkTst);
    casFeatCode_mph  = (null == casFeat_mph) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_mph).getCode();

 
    casFeat_posTag = jcas.getRequiredFeatureDE(casType, "posTag", "uima.cas.String", featOkTst);
    casFeatCode_posTag  = (null == casFeat_posTag) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_posTag).getCode();

 
    casFeat_chunkTag = jcas.getRequiredFeatureDE(casType, "chunkTag", "uima.cas.String", featOkTst);
    casFeatCode_chunkTag  = (null == casFeat_chunkTag) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_chunkTag).getCode();

 
    casFeat_raw = jcas.getRequiredFeatureDE(casType, "raw", "uima.cas.String", featOkTst);
    casFeatCode_raw  = (null == casFeat_raw) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_raw).getCode();

 
    casFeat_subCat = jcas.getRequiredFeatureDE(casType, "subCat", "uima.cas.String", featOkTst);
    casFeatCode_subCat  = (null == casFeat_subCat) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_subCat).getCode();

  }
}



    