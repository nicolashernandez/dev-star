
/* First created by JCasGen Fri Jul 13 17:09:45 CEST 2012 */
package common.types.ext;

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
 * Updated by JCasGen Mon Mar 25 22:57:13 CET 2013
 * @generated */
public class FTBWord_Type extends Annotation_Type {
  /** @generated */
  @Override
  protected FSGenerator getFSGenerator() {return fsGenerator;}
  /** @generated */
  private final FSGenerator fsGenerator = 
    new FSGenerator() {
      public FeatureStructure createFS(int addr, CASImpl cas) {
  			 if (FTBWord_Type.this.useExistingInstance) {
  			   // Return eq fs instance if already created
  		     FeatureStructure fs = FTBWord_Type.this.jcas.getJfsFromCaddr(addr);
  		     if (null == fs) {
  		       fs = new FTBWord(addr, FTBWord_Type.this);
  			   FTBWord_Type.this.jcas.putJfsFromCaddr(addr, fs);
  			   return fs;
  		     }
  		     return fs;
        } else return new FTBWord(addr, FTBWord_Type.this);
  	  }
    };
  /** @generated */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = FTBWord.typeIndexID;
  /** @generated 
     @modifiable */
  @SuppressWarnings ("hiding")
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("common.types.ext.FTBWord");
 
  /** @generated */
  final Feature casFeat_cat;
  /** @generated */
  final int     casFeatCode_cat;
  /** @generated */ 
  public String getCat(int addr) {
        if (featOkTst && casFeat_cat == null)
      jcas.throwFeatMissing("cat", "common.types.ext.FTBWord");
    return ll_cas.ll_getStringValue(addr, casFeatCode_cat);
  }
  /** @generated */    
  public void setCat(int addr, String v) {
        if (featOkTst && casFeat_cat == null)
      jcas.throwFeatMissing("cat", "common.types.ext.FTBWord");
    ll_cas.ll_setStringValue(addr, casFeatCode_cat, v);}
    
  
 
  /** @generated */
  final Feature casFeat_subcat;
  /** @generated */
  final int     casFeatCode_subcat;
  /** @generated */ 
  public String getSubcat(int addr) {
        if (featOkTst && casFeat_subcat == null)
      jcas.throwFeatMissing("subcat", "common.types.ext.FTBWord");
    return ll_cas.ll_getStringValue(addr, casFeatCode_subcat);
  }
  /** @generated */    
  public void setSubcat(int addr, String v) {
        if (featOkTst && casFeat_subcat == null)
      jcas.throwFeatMissing("subcat", "common.types.ext.FTBWord");
    ll_cas.ll_setStringValue(addr, casFeatCode_subcat, v);}
    
  
 
  /** @generated */
  final Feature casFeat_mph;
  /** @generated */
  final int     casFeatCode_mph;
  /** @generated */ 
  public String getMph(int addr) {
        if (featOkTst && casFeat_mph == null)
      jcas.throwFeatMissing("mph", "common.types.ext.FTBWord");
    return ll_cas.ll_getStringValue(addr, casFeatCode_mph);
  }
  /** @generated */    
  public void setMph(int addr, String v) {
        if (featOkTst && casFeat_mph == null)
      jcas.throwFeatMissing("mph", "common.types.ext.FTBWord");
    ll_cas.ll_setStringValue(addr, casFeatCode_mph, v);}
    
  
 
  /** @generated */
  final Feature casFeat_lemma;
  /** @generated */
  final int     casFeatCode_lemma;
  /** @generated */ 
  public String getLemma(int addr) {
        if (featOkTst && casFeat_lemma == null)
      jcas.throwFeatMissing("lemma", "common.types.ext.FTBWord");
    return ll_cas.ll_getStringValue(addr, casFeatCode_lemma);
  }
  /** @generated */    
  public void setLemma(int addr, String v) {
        if (featOkTst && casFeat_lemma == null)
      jcas.throwFeatMissing("lemma", "common.types.ext.FTBWord");
    ll_cas.ll_setStringValue(addr, casFeatCode_lemma, v);}
    
  
 
  /** @generated */
  final Feature casFeat_gender;
  /** @generated */
  final int     casFeatCode_gender;
  /** @generated */ 
  public String getGender(int addr) {
        if (featOkTst && casFeat_gender == null)
      jcas.throwFeatMissing("gender", "common.types.ext.FTBWord");
    return ll_cas.ll_getStringValue(addr, casFeatCode_gender);
  }
  /** @generated */    
  public void setGender(int addr, String v) {
        if (featOkTst && casFeat_gender == null)
      jcas.throwFeatMissing("gender", "common.types.ext.FTBWord");
    ll_cas.ll_setStringValue(addr, casFeatCode_gender, v);}
    
  
 
  /** @generated */
  final Feature casFeat_number;
  /** @generated */
  final int     casFeatCode_number;
  /** @generated */ 
  public String getNumber(int addr) {
        if (featOkTst && casFeat_number == null)
      jcas.throwFeatMissing("number", "common.types.ext.FTBWord");
    return ll_cas.ll_getStringValue(addr, casFeatCode_number);
  }
  /** @generated */    
  public void setNumber(int addr, String v) {
        if (featOkTst && casFeat_number == null)
      jcas.throwFeatMissing("number", "common.types.ext.FTBWord");
    ll_cas.ll_setStringValue(addr, casFeatCode_number, v);}
    
  
 
  /** @generated */
  final Feature casFeat_case;
  /** @generated */
  final int     casFeatCode_case;
  /** @generated */ 
  public String getCase(int addr) {
        if (featOkTst && casFeat_case == null)
      jcas.throwFeatMissing("case", "common.types.ext.FTBWord");
    return ll_cas.ll_getStringValue(addr, casFeatCode_case);
  }
  /** @generated */    
  public void setCase(int addr, String v) {
        if (featOkTst && casFeat_case == null)
      jcas.throwFeatMissing("case", "common.types.ext.FTBWord");
    ll_cas.ll_setStringValue(addr, casFeatCode_case, v);}
    
  
 
  /** @generated */
  final Feature casFeat_person;
  /** @generated */
  final int     casFeatCode_person;
  /** @generated */ 
  public String getPerson(int addr) {
        if (featOkTst && casFeat_person == null)
      jcas.throwFeatMissing("person", "common.types.ext.FTBWord");
    return ll_cas.ll_getStringValue(addr, casFeatCode_person);
  }
  /** @generated */    
  public void setPerson(int addr, String v) {
        if (featOkTst && casFeat_person == null)
      jcas.throwFeatMissing("person", "common.types.ext.FTBWord");
    ll_cas.ll_setStringValue(addr, casFeatCode_person, v);}
    
  
 
  /** @generated */
  final Feature casFeat_mood;
  /** @generated */
  final int     casFeatCode_mood;
  /** @generated */ 
  public String getMood(int addr) {
        if (featOkTst && casFeat_mood == null)
      jcas.throwFeatMissing("mood", "common.types.ext.FTBWord");
    return ll_cas.ll_getStringValue(addr, casFeatCode_mood);
  }
  /** @generated */    
  public void setMood(int addr, String v) {
        if (featOkTst && casFeat_mood == null)
      jcas.throwFeatMissing("mood", "common.types.ext.FTBWord");
    ll_cas.ll_setStringValue(addr, casFeatCode_mood, v);}
    
  
 
  /** @generated */
  final Feature casFeat_tense;
  /** @generated */
  final int     casFeatCode_tense;
  /** @generated */ 
  public String getTense(int addr) {
        if (featOkTst && casFeat_tense == null)
      jcas.throwFeatMissing("tense", "common.types.ext.FTBWord");
    return ll_cas.ll_getStringValue(addr, casFeatCode_tense);
  }
  /** @generated */    
  public void setTense(int addr, String v) {
        if (featOkTst && casFeat_tense == null)
      jcas.throwFeatMissing("tense", "common.types.ext.FTBWord");
    ll_cas.ll_setStringValue(addr, casFeatCode_tense, v);}
    
  
 
  /** @generated */
  final Feature casFeat_catP;
  /** @generated */
  final int     casFeatCode_catP;
  /** @generated */ 
  public String getCatP(int addr) {
        if (featOkTst && casFeat_catP == null)
      jcas.throwFeatMissing("catP", "common.types.ext.FTBWord");
    return ll_cas.ll_getStringValue(addr, casFeatCode_catP);
  }
  /** @generated */    
  public void setCatP(int addr, String v) {
        if (featOkTst && casFeat_catP == null)
      jcas.throwFeatMissing("catP", "common.types.ext.FTBWord");
    ll_cas.ll_setStringValue(addr, casFeatCode_catP, v);}
    
  
 
  /** @generated */
  final Feature casFeat_mw;
  /** @generated */
  final int     casFeatCode_mw;
  /** @generated */ 
  public String getMw(int addr) {
        if (featOkTst && casFeat_mw == null)
      jcas.throwFeatMissing("mw", "common.types.ext.FTBWord");
    return ll_cas.ll_getStringValue(addr, casFeatCode_mw);
  }
  /** @generated */    
  public void setMw(int addr, String v) {
        if (featOkTst && casFeat_mw == null)
      jcas.throwFeatMissing("mw", "common.types.ext.FTBWord");
    ll_cas.ll_setStringValue(addr, casFeatCode_mw, v);}
    
  
 
  /** @generated */
  final Feature casFeat_po;
  /** @generated */
  final int     casFeatCode_po;
  /** @generated */ 
  public String getPo(int addr) {
        if (featOkTst && casFeat_po == null)
      jcas.throwFeatMissing("po", "common.types.ext.FTBWord");
    return ll_cas.ll_getStringValue(addr, casFeatCode_po);
  }
  /** @generated */    
  public void setPo(int addr, String v) {
        if (featOkTst && casFeat_po == null)
      jcas.throwFeatMissing("po", "common.types.ext.FTBWord");
    ll_cas.ll_setStringValue(addr, casFeatCode_po, v);}
    
  
 
  /** @generated */
  final Feature casFeat_sw;
  /** @generated */
  final int     casFeatCode_sw;
  /** @generated */ 
  public String getSw(int addr) {
        if (featOkTst && casFeat_sw == null)
      jcas.throwFeatMissing("sw", "common.types.ext.FTBWord");
    return ll_cas.ll_getStringValue(addr, casFeatCode_sw);
  }
  /** @generated */    
  public void setSw(int addr, String v) {
        if (featOkTst && casFeat_sw == null)
      jcas.throwFeatMissing("sw", "common.types.ext.FTBWord");
    ll_cas.ll_setStringValue(addr, casFeatCode_sw, v);}
    
  
 
  /** @generated */
  final Feature casFeat_rf;
  /** @generated */
  final int     casFeatCode_rf;
  /** @generated */ 
  public String getRf(int addr) {
        if (featOkTst && casFeat_rf == null)
      jcas.throwFeatMissing("rf", "common.types.ext.FTBWord");
    return ll_cas.ll_getStringValue(addr, casFeatCode_rf);
  }
  /** @generated */    
  public void setRf(int addr, String v) {
        if (featOkTst && casFeat_rf == null)
      jcas.throwFeatMissing("rf", "common.types.ext.FTBWord");
    ll_cas.ll_setStringValue(addr, casFeatCode_rf, v);}
    
  
 
  /** @generated */
  final Feature casFeat_pn;
  /** @generated */
  final int     casFeatCode_pn;
  /** @generated */ 
  public String getPn(int addr) {
        if (featOkTst && casFeat_pn == null)
      jcas.throwFeatMissing("pn", "common.types.ext.FTBWord");
    return ll_cas.ll_getStringValue(addr, casFeatCode_pn);
  }
  /** @generated */    
  public void setPn(int addr, String v) {
        if (featOkTst && casFeat_pn == null)
      jcas.throwFeatMissing("pn", "common.types.ext.FTBWord");
    ll_cas.ll_setStringValue(addr, casFeatCode_pn, v);}
    
  
 
  /** @generated */
  final Feature casFeat_ee;
  /** @generated */
  final int     casFeatCode_ee;
  /** @generated */ 
  public String getEe(int addr) {
        if (featOkTst && casFeat_ee == null)
      jcas.throwFeatMissing("ee", "common.types.ext.FTBWord");
    return ll_cas.ll_getStringValue(addr, casFeatCode_ee);
  }
  /** @generated */    
  public void setEe(int addr, String v) {
        if (featOkTst && casFeat_ee == null)
      jcas.throwFeatMissing("ee", "common.types.ext.FTBWord");
    ll_cas.ll_setStringValue(addr, casFeatCode_ee, v);}
    
  
 
  /** @generated */
  final Feature casFeat_catint;
  /** @generated */
  final int     casFeatCode_catint;
  /** @generated */ 
  public String getCatint(int addr) {
        if (featOkTst && casFeat_catint == null)
      jcas.throwFeatMissing("catint", "common.types.ext.FTBWord");
    return ll_cas.ll_getStringValue(addr, casFeatCode_catint);
  }
  /** @generated */    
  public void setCatint(int addr, String v) {
        if (featOkTst && casFeat_catint == null)
      jcas.throwFeatMissing("catint", "common.types.ext.FTBWord");
    ll_cas.ll_setStringValue(addr, casFeatCode_catint, v);}
    
  
 
  /** @generated */
  final Feature casFeat_gu;
  /** @generated */
  final int     casFeatCode_gu;
  /** @generated */ 
  public String getGu(int addr) {
        if (featOkTst && casFeat_gu == null)
      jcas.throwFeatMissing("gu", "common.types.ext.FTBWord");
    return ll_cas.ll_getStringValue(addr, casFeatCode_gu);
  }
  /** @generated */    
  public void setGu(int addr, String v) {
        if (featOkTst && casFeat_gu == null)
      jcas.throwFeatMissing("gu", "common.types.ext.FTBWord");
    ll_cas.ll_setStringValue(addr, casFeatCode_gu, v);}
    
  



  /** initialize variables to correspond with Cas Type and Features
	* @generated */
  public FTBWord_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_cat = jcas.getRequiredFeatureDE(casType, "cat", "uima.cas.String", featOkTst);
    casFeatCode_cat  = (null == casFeat_cat) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_cat).getCode();

 
    casFeat_subcat = jcas.getRequiredFeatureDE(casType, "subcat", "uima.cas.String", featOkTst);
    casFeatCode_subcat  = (null == casFeat_subcat) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_subcat).getCode();

 
    casFeat_mph = jcas.getRequiredFeatureDE(casType, "mph", "uima.cas.String", featOkTst);
    casFeatCode_mph  = (null == casFeat_mph) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_mph).getCode();

 
    casFeat_lemma = jcas.getRequiredFeatureDE(casType, "lemma", "uima.cas.String", featOkTst);
    casFeatCode_lemma  = (null == casFeat_lemma) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_lemma).getCode();

 
    casFeat_gender = jcas.getRequiredFeatureDE(casType, "gender", "uima.cas.String", featOkTst);
    casFeatCode_gender  = (null == casFeat_gender) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_gender).getCode();

 
    casFeat_number = jcas.getRequiredFeatureDE(casType, "number", "uima.cas.String", featOkTst);
    casFeatCode_number  = (null == casFeat_number) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_number).getCode();

 
    casFeat_case = jcas.getRequiredFeatureDE(casType, "case", "uima.cas.String", featOkTst);
    casFeatCode_case  = (null == casFeat_case) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_case).getCode();

 
    casFeat_person = jcas.getRequiredFeatureDE(casType, "person", "uima.cas.String", featOkTst);
    casFeatCode_person  = (null == casFeat_person) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_person).getCode();

 
    casFeat_mood = jcas.getRequiredFeatureDE(casType, "mood", "uima.cas.String", featOkTst);
    casFeatCode_mood  = (null == casFeat_mood) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_mood).getCode();

 
    casFeat_tense = jcas.getRequiredFeatureDE(casType, "tense", "uima.cas.String", featOkTst);
    casFeatCode_tense  = (null == casFeat_tense) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_tense).getCode();

 
    casFeat_catP = jcas.getRequiredFeatureDE(casType, "catP", "uima.cas.String", featOkTst);
    casFeatCode_catP  = (null == casFeat_catP) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_catP).getCode();

 
    casFeat_mw = jcas.getRequiredFeatureDE(casType, "mw", "uima.cas.String", featOkTst);
    casFeatCode_mw  = (null == casFeat_mw) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_mw).getCode();

 
    casFeat_po = jcas.getRequiredFeatureDE(casType, "po", "uima.cas.String", featOkTst);
    casFeatCode_po  = (null == casFeat_po) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_po).getCode();

 
    casFeat_sw = jcas.getRequiredFeatureDE(casType, "sw", "uima.cas.String", featOkTst);
    casFeatCode_sw  = (null == casFeat_sw) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_sw).getCode();

 
    casFeat_rf = jcas.getRequiredFeatureDE(casType, "rf", "uima.cas.String", featOkTst);
    casFeatCode_rf  = (null == casFeat_rf) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_rf).getCode();

 
    casFeat_pn = jcas.getRequiredFeatureDE(casType, "pn", "uima.cas.String", featOkTst);
    casFeatCode_pn  = (null == casFeat_pn) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_pn).getCode();

 
    casFeat_ee = jcas.getRequiredFeatureDE(casType, "ee", "uima.cas.String", featOkTst);
    casFeatCode_ee  = (null == casFeat_ee) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_ee).getCode();

 
    casFeat_catint = jcas.getRequiredFeatureDE(casType, "catint", "uima.cas.String", featOkTst);
    casFeatCode_catint  = (null == casFeat_catint) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_catint).getCode();

 
    casFeat_gu = jcas.getRequiredFeatureDE(casType, "gu", "uima.cas.String", featOkTst);
    casFeatCode_gu  = (null == casFeat_gu) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_gu).getCode();

  }
}



    