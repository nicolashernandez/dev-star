
/* First created by JCasGen Fri Mar 08 15:28:14 CET 2013 */
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

/** features based on French characteristics enumerated by http://aune.lpl.univ-aix.fr/projects/multext/LEX/LEX1.html
 * Updated by JCasGen Thu May 02 23:08:54 CEST 2013
 * @generated */
public class Token_Type extends Annotation_Type {
  /** @generated */
  @Override
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
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = Token.typeIndexID;
  /** @generated 
     @modifiable */
  @SuppressWarnings ("hiding")
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
  final Feature casFeat_cat;
  /** @generated */
  final int     casFeatCode_cat;
  /** @generated */ 
  public String getCat(int addr) {
        if (featOkTst && casFeat_cat == null)
      jcas.throwFeatMissing("cat", "common.types.text.Token");
    return ll_cas.ll_getStringValue(addr, casFeatCode_cat);
  }
  /** @generated */    
  public void setCat(int addr, String v) {
        if (featOkTst && casFeat_cat == null)
      jcas.throwFeatMissing("cat", "common.types.text.Token");
    ll_cas.ll_setStringValue(addr, casFeatCode_cat, v);}
    
  
 
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
    
  
 
  /** @generated */
  final Feature casFeat_stem;
  /** @generated */
  final int     casFeatCode_stem;
  /** @generated */ 
  public String getStem(int addr) {
        if (featOkTst && casFeat_stem == null)
      jcas.throwFeatMissing("stem", "common.types.text.Token");
    return ll_cas.ll_getStringValue(addr, casFeatCode_stem);
  }
  /** @generated */    
  public void setStem(int addr, String v) {
        if (featOkTst && casFeat_stem == null)
      jcas.throwFeatMissing("stem", "common.types.text.Token");
    ll_cas.ll_setStringValue(addr, casFeatCode_stem, v);}
    
  
 
  /** @generated */
  final Feature casFeat_corefType;
  /** @generated */
  final int     casFeatCode_corefType;
  /** @generated */ 
  public String getCorefType(int addr) {
        if (featOkTst && casFeat_corefType == null)
      jcas.throwFeatMissing("corefType", "common.types.text.Token");
    return ll_cas.ll_getStringValue(addr, casFeatCode_corefType);
  }
  /** @generated */    
  public void setCorefType(int addr, String v) {
        if (featOkTst && casFeat_corefType == null)
      jcas.throwFeatMissing("corefType", "common.types.text.Token");
    ll_cas.ll_setStringValue(addr, casFeatCode_corefType, v);}
    
  
 
  /** @generated */
  final Feature casFeat_gender;
  /** @generated */
  final int     casFeatCode_gender;
  /** @generated */ 
  public String getGender(int addr) {
        if (featOkTst && casFeat_gender == null)
      jcas.throwFeatMissing("gender", "common.types.text.Token");
    return ll_cas.ll_getStringValue(addr, casFeatCode_gender);
  }
  /** @generated */    
  public void setGender(int addr, String v) {
        if (featOkTst && casFeat_gender == null)
      jcas.throwFeatMissing("gender", "common.types.text.Token");
    ll_cas.ll_setStringValue(addr, casFeatCode_gender, v);}
    
  
 
  /** @generated */
  final Feature casFeat_number;
  /** @generated */
  final int     casFeatCode_number;
  /** @generated */ 
  public String getNumber(int addr) {
        if (featOkTst && casFeat_number == null)
      jcas.throwFeatMissing("number", "common.types.text.Token");
    return ll_cas.ll_getStringValue(addr, casFeatCode_number);
  }
  /** @generated */    
  public void setNumber(int addr, String v) {
        if (featOkTst && casFeat_number == null)
      jcas.throwFeatMissing("number", "common.types.text.Token");
    ll_cas.ll_setStringValue(addr, casFeatCode_number, v);}
    
  
 
  /** @generated */
  final Feature casFeat_case;
  /** @generated */
  final int     casFeatCode_case;
  /** @generated */ 
  public String getCase(int addr) {
        if (featOkTst && casFeat_case == null)
      jcas.throwFeatMissing("case", "common.types.text.Token");
    return ll_cas.ll_getStringValue(addr, casFeatCode_case);
  }
  /** @generated */    
  public void setCase(int addr, String v) {
        if (featOkTst && casFeat_case == null)
      jcas.throwFeatMissing("case", "common.types.text.Token");
    ll_cas.ll_setStringValue(addr, casFeatCode_case, v);}
    
  
 
  /** @generated */
  final Feature casFeat_person;
  /** @generated */
  final int     casFeatCode_person;
  /** @generated */ 
  public String getPerson(int addr) {
        if (featOkTst && casFeat_person == null)
      jcas.throwFeatMissing("person", "common.types.text.Token");
    return ll_cas.ll_getStringValue(addr, casFeatCode_person);
  }
  /** @generated */    
  public void setPerson(int addr, String v) {
        if (featOkTst && casFeat_person == null)
      jcas.throwFeatMissing("person", "common.types.text.Token");
    ll_cas.ll_setStringValue(addr, casFeatCode_person, v);}
    
  
 
  /** @generated */
  final Feature casFeat_mood;
  /** @generated */
  final int     casFeatCode_mood;
  /** @generated */ 
  public String getMood(int addr) {
        if (featOkTst && casFeat_mood == null)
      jcas.throwFeatMissing("mood", "common.types.text.Token");
    return ll_cas.ll_getStringValue(addr, casFeatCode_mood);
  }
  /** @generated */    
  public void setMood(int addr, String v) {
        if (featOkTst && casFeat_mood == null)
      jcas.throwFeatMissing("mood", "common.types.text.Token");
    ll_cas.ll_setStringValue(addr, casFeatCode_mood, v);}
    
  
 
  /** @generated */
  final Feature casFeat_tense;
  /** @generated */
  final int     casFeatCode_tense;
  /** @generated */ 
  public String getTense(int addr) {
        if (featOkTst && casFeat_tense == null)
      jcas.throwFeatMissing("tense", "common.types.text.Token");
    return ll_cas.ll_getStringValue(addr, casFeatCode_tense);
  }
  /** @generated */    
  public void setTense(int addr, String v) {
        if (featOkTst && casFeat_tense == null)
      jcas.throwFeatMissing("tense", "common.types.text.Token");
    ll_cas.ll_setStringValue(addr, casFeatCode_tense, v);}
    
  
 
  /** @generated */
  final Feature casFeat_catP;
  /** @generated */
  final int     casFeatCode_catP;
  /** @generated */ 
  public String getCatP(int addr) {
        if (featOkTst && casFeat_catP == null)
      jcas.throwFeatMissing("catP", "common.types.text.Token");
    return ll_cas.ll_getStringValue(addr, casFeatCode_catP);
  }
  /** @generated */    
  public void setCatP(int addr, String v) {
        if (featOkTst && casFeat_catP == null)
      jcas.throwFeatMissing("catP", "common.types.text.Token");
    ll_cas.ll_setStringValue(addr, casFeatCode_catP, v);}
    
  
 
  /** @generated */
  final Feature casFeat_coarseGrainedChunkTag;
  /** @generated */
  final int     casFeatCode_coarseGrainedChunkTag;
  /** @generated */ 
  public String getCoarseGrainedChunkTag(int addr) {
        if (featOkTst && casFeat_coarseGrainedChunkTag == null)
      jcas.throwFeatMissing("coarseGrainedChunkTag", "common.types.text.Token");
    return ll_cas.ll_getStringValue(addr, casFeatCode_coarseGrainedChunkTag);
  }
  /** @generated */    
  public void setCoarseGrainedChunkTag(int addr, String v) {
        if (featOkTst && casFeat_coarseGrainedChunkTag == null)
      jcas.throwFeatMissing("coarseGrainedChunkTag", "common.types.text.Token");
    ll_cas.ll_setStringValue(addr, casFeatCode_coarseGrainedChunkTag, v);}
    
  
 
  /** @generated */
  final Feature casFeat_embeddingPathChunkTag;
  /** @generated */
  final int     casFeatCode_embeddingPathChunkTag;
  /** @generated */ 
  public String getEmbeddingPathChunkTag(int addr) {
        if (featOkTst && casFeat_embeddingPathChunkTag == null)
      jcas.throwFeatMissing("embeddingPathChunkTag", "common.types.text.Token");
    return ll_cas.ll_getStringValue(addr, casFeatCode_embeddingPathChunkTag);
  }
  /** @generated */    
  public void setEmbeddingPathChunkTag(int addr, String v) {
        if (featOkTst && casFeat_embeddingPathChunkTag == null)
      jcas.throwFeatMissing("embeddingPathChunkTag", "common.types.text.Token");
    ll_cas.ll_setStringValue(addr, casFeatCode_embeddingPathChunkTag, v);}
    
  
 
  /** @generated */
  final Feature casFeat_fineGrainedChunkTag;
  /** @generated */
  final int     casFeatCode_fineGrainedChunkTag;
  /** @generated */ 
  public String getFineGrainedChunkTag(int addr) {
        if (featOkTst && casFeat_fineGrainedChunkTag == null)
      jcas.throwFeatMissing("fineGrainedChunkTag", "common.types.text.Token");
    return ll_cas.ll_getStringValue(addr, casFeatCode_fineGrainedChunkTag);
  }
  /** @generated */    
  public void setFineGrainedChunkTag(int addr, String v) {
        if (featOkTst && casFeat_fineGrainedChunkTag == null)
      jcas.throwFeatMissing("fineGrainedChunkTag", "common.types.text.Token");
    ll_cas.ll_setStringValue(addr, casFeatCode_fineGrainedChunkTag, v);}
    
  



  /** initialize variables to correspond with Cas Type and Features
	* @generated */
  public Token_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_lemma = jcas.getRequiredFeatureDE(casType, "lemma", "uima.cas.String", featOkTst);
    casFeatCode_lemma  = (null == casFeat_lemma) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_lemma).getCode();

 
    casFeat_mph = jcas.getRequiredFeatureDE(casType, "mph", "uima.cas.String", featOkTst);
    casFeatCode_mph  = (null == casFeat_mph) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_mph).getCode();

 
    casFeat_cat = jcas.getRequiredFeatureDE(casType, "cat", "uima.cas.String", featOkTst);
    casFeatCode_cat  = (null == casFeat_cat) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_cat).getCode();

 
    casFeat_raw = jcas.getRequiredFeatureDE(casType, "raw", "uima.cas.String", featOkTst);
    casFeatCode_raw  = (null == casFeat_raw) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_raw).getCode();

 
    casFeat_subCat = jcas.getRequiredFeatureDE(casType, "subCat", "uima.cas.String", featOkTst);
    casFeatCode_subCat  = (null == casFeat_subCat) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_subCat).getCode();

 
    casFeat_stem = jcas.getRequiredFeatureDE(casType, "stem", "uima.cas.String", featOkTst);
    casFeatCode_stem  = (null == casFeat_stem) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_stem).getCode();

 
    casFeat_corefType = jcas.getRequiredFeatureDE(casType, "corefType", "uima.cas.String", featOkTst);
    casFeatCode_corefType  = (null == casFeat_corefType) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_corefType).getCode();

 
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

 
    casFeat_coarseGrainedChunkTag = jcas.getRequiredFeatureDE(casType, "coarseGrainedChunkTag", "uima.cas.String", featOkTst);
    casFeatCode_coarseGrainedChunkTag  = (null == casFeat_coarseGrainedChunkTag) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_coarseGrainedChunkTag).getCode();

 
    casFeat_embeddingPathChunkTag = jcas.getRequiredFeatureDE(casType, "embeddingPathChunkTag", "uima.cas.String", featOkTst);
    casFeatCode_embeddingPathChunkTag  = (null == casFeat_embeddingPathChunkTag) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_embeddingPathChunkTag).getCode();

 
    casFeat_fineGrainedChunkTag = jcas.getRequiredFeatureDE(casType, "fineGrainedChunkTag", "uima.cas.String", featOkTst);
    casFeatCode_fineGrainedChunkTag  = (null == casFeat_fineGrainedChunkTag) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_fineGrainedChunkTag).getCode();

  }
}



    