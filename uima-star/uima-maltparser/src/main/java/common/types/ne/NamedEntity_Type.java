
/* First created by JCasGen Thu May 30 11:49:30 CEST 2013 */
package common.types.ne;

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
 * Updated by JCasGen Thu May 30 11:49:30 CEST 2013
 * @generated */
public class NamedEntity_Type extends Annotation_Type {
  /** @generated */
  @Override
  protected FSGenerator getFSGenerator() {return fsGenerator;}
  /** @generated */
  private final FSGenerator fsGenerator = 
    new FSGenerator() {
      public FeatureStructure createFS(int addr, CASImpl cas) {
  			 if (NamedEntity_Type.this.useExistingInstance) {
  			   // Return eq fs instance if already created
  		     FeatureStructure fs = NamedEntity_Type.this.jcas.getJfsFromCaddr(addr);
  		     if (null == fs) {
  		       fs = new NamedEntity(addr, NamedEntity_Type.this);
  			   NamedEntity_Type.this.jcas.putJfsFromCaddr(addr, fs);
  			   return fs;
  		     }
  		     return fs;
        } else return new NamedEntity(addr, NamedEntity_Type.this);
  	  }
    };
  /** @generated */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = NamedEntity.typeIndexID;
  /** @generated 
     @modifiable */
  @SuppressWarnings ("hiding")
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("common.types.ne.NamedEntity");
 
  /** @generated */
  final Feature casFeat_category;
  /** @generated */
  final int     casFeatCode_category;
  /** @generated */ 
  public String getCategory(int addr) {
        if (featOkTst && casFeat_category == null)
      jcas.throwFeatMissing("category", "common.types.ne.NamedEntity");
    return ll_cas.ll_getStringValue(addr, casFeatCode_category);
  }
  /** @generated */    
  public void setCategory(int addr, String v) {
        if (featOkTst && casFeat_category == null)
      jcas.throwFeatMissing("category", "common.types.ne.NamedEntity");
    ll_cas.ll_setStringValue(addr, casFeatCode_category, v);}
    
  
 
  /** @generated */
  final Feature casFeat_gender;
  /** @generated */
  final int     casFeatCode_gender;
  /** @generated */ 
  public String getGender(int addr) {
        if (featOkTst && casFeat_gender == null)
      jcas.throwFeatMissing("gender", "common.types.ne.NamedEntity");
    return ll_cas.ll_getStringValue(addr, casFeatCode_gender);
  }
  /** @generated */    
  public void setGender(int addr, String v) {
        if (featOkTst && casFeat_gender == null)
      jcas.throwFeatMissing("gender", "common.types.ne.NamedEntity");
    ll_cas.ll_setStringValue(addr, casFeatCode_gender, v);}
    
  
 
  /** @generated */
  final Feature casFeat_number;
  /** @generated */
  final int     casFeatCode_number;
  /** @generated */ 
  public String getNumber(int addr) {
        if (featOkTst && casFeat_number == null)
      jcas.throwFeatMissing("number", "common.types.ne.NamedEntity");
    return ll_cas.ll_getStringValue(addr, casFeatCode_number);
  }
  /** @generated */    
  public void setNumber(int addr, String v) {
        if (featOkTst && casFeat_number == null)
      jcas.throwFeatMissing("number", "common.types.ne.NamedEntity");
    ll_cas.ll_setStringValue(addr, casFeatCode_number, v);}
    
  
 
  /** @generated */
  final Feature casFeat_subCategory;
  /** @generated */
  final int     casFeatCode_subCategory;
  /** @generated */ 
  public String getSubCategory(int addr) {
        if (featOkTst && casFeat_subCategory == null)
      jcas.throwFeatMissing("subCategory", "common.types.ne.NamedEntity");
    return ll_cas.ll_getStringValue(addr, casFeatCode_subCategory);
  }
  /** @generated */    
  public void setSubCategory(int addr, String v) {
        if (featOkTst && casFeat_subCategory == null)
      jcas.throwFeatMissing("subCategory", "common.types.ne.NamedEntity");
    ll_cas.ll_setStringValue(addr, casFeatCode_subCategory, v);}
    
  
 
  /** @generated */
  final Feature casFeat_ruleId;
  /** @generated */
  final int     casFeatCode_ruleId;
  /** @generated */ 
  public String getRuleId(int addr) {
        if (featOkTst && casFeat_ruleId == null)
      jcas.throwFeatMissing("ruleId", "common.types.ne.NamedEntity");
    return ll_cas.ll_getStringValue(addr, casFeatCode_ruleId);
  }
  /** @generated */    
  public void setRuleId(int addr, String v) {
        if (featOkTst && casFeat_ruleId == null)
      jcas.throwFeatMissing("ruleId", "common.types.ne.NamedEntity");
    ll_cas.ll_setStringValue(addr, casFeatCode_ruleId, v);}
    
  
 
  /** @generated */
  final Feature casFeat_value;
  /** @generated */
  final int     casFeatCode_value;
  /** @generated */ 
  public String getValue(int addr) {
        if (featOkTst && casFeat_value == null)
      jcas.throwFeatMissing("value", "common.types.ne.NamedEntity");
    return ll_cas.ll_getStringValue(addr, casFeatCode_value);
  }
  /** @generated */    
  public void setValue(int addr, String v) {
        if (featOkTst && casFeat_value == null)
      jcas.throwFeatMissing("value", "common.types.ne.NamedEntity");
    ll_cas.ll_setStringValue(addr, casFeatCode_value, v);}
    
  
 
  /** @generated */
  final Feature casFeat_annotatorId;
  /** @generated */
  final int     casFeatCode_annotatorId;
  /** @generated */ 
  public String getAnnotatorId(int addr) {
        if (featOkTst && casFeat_annotatorId == null)
      jcas.throwFeatMissing("annotatorId", "common.types.ne.NamedEntity");
    return ll_cas.ll_getStringValue(addr, casFeatCode_annotatorId);
  }
  /** @generated */    
  public void setAnnotatorId(int addr, String v) {
        if (featOkTst && casFeat_annotatorId == null)
      jcas.throwFeatMissing("annotatorId", "common.types.ne.NamedEntity");
    ll_cas.ll_setStringValue(addr, casFeatCode_annotatorId, v);}
    
  
 
  /** @generated */
  final Feature casFeat_status;
  /** @generated */
  final int     casFeatCode_status;
  /** @generated */ 
  public String getStatus(int addr) {
        if (featOkTst && casFeat_status == null)
      jcas.throwFeatMissing("status", "common.types.ne.NamedEntity");
    return ll_cas.ll_getStringValue(addr, casFeatCode_status);
  }
  /** @generated */    
  public void setStatus(int addr, String v) {
        if (featOkTst && casFeat_status == null)
      jcas.throwFeatMissing("status", "common.types.ne.NamedEntity");
    ll_cas.ll_setStringValue(addr, casFeatCode_status, v);}
    
  
 
  /** @generated */
  final Feature casFeat_language;
  /** @generated */
  final int     casFeatCode_language;
  /** @generated */ 
  public String getLanguage(int addr) {
        if (featOkTst && casFeat_language == null)
      jcas.throwFeatMissing("language", "common.types.ne.NamedEntity");
    return ll_cas.ll_getStringValue(addr, casFeatCode_language);
  }
  /** @generated */    
  public void setLanguage(int addr, String v) {
        if (featOkTst && casFeat_language == null)
      jcas.throwFeatMissing("language", "common.types.ne.NamedEntity");
    ll_cas.ll_setStringValue(addr, casFeatCode_language, v);}
    
  



  /** initialize variables to correspond with Cas Type and Features
	* @generated */
  public NamedEntity_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_category = jcas.getRequiredFeatureDE(casType, "category", "uima.cas.String", featOkTst);
    casFeatCode_category  = (null == casFeat_category) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_category).getCode();

 
    casFeat_gender = jcas.getRequiredFeatureDE(casType, "gender", "uima.cas.String", featOkTst);
    casFeatCode_gender  = (null == casFeat_gender) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_gender).getCode();

 
    casFeat_number = jcas.getRequiredFeatureDE(casType, "number", "uima.cas.String", featOkTst);
    casFeatCode_number  = (null == casFeat_number) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_number).getCode();

 
    casFeat_subCategory = jcas.getRequiredFeatureDE(casType, "subCategory", "uima.cas.String", featOkTst);
    casFeatCode_subCategory  = (null == casFeat_subCategory) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_subCategory).getCode();

 
    casFeat_ruleId = jcas.getRequiredFeatureDE(casType, "ruleId", "uima.cas.String", featOkTst);
    casFeatCode_ruleId  = (null == casFeat_ruleId) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_ruleId).getCode();

 
    casFeat_value = jcas.getRequiredFeatureDE(casType, "value", "uima.cas.String", featOkTst);
    casFeatCode_value  = (null == casFeat_value) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_value).getCode();

 
    casFeat_annotatorId = jcas.getRequiredFeatureDE(casType, "annotatorId", "uima.cas.String", featOkTst);
    casFeatCode_annotatorId  = (null == casFeat_annotatorId) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_annotatorId).getCode();

 
    casFeat_status = jcas.getRequiredFeatureDE(casType, "status", "uima.cas.String", featOkTst);
    casFeatCode_status  = (null == casFeat_status) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_status).getCode();

 
    casFeat_language = jcas.getRequiredFeatureDE(casType, "language", "uima.cas.String", featOkTst);
    casFeatCode_language  = (null == casFeat_language) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_language).getCode();

  }
}



    