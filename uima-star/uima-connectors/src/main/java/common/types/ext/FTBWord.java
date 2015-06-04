

/* First created by JCasGen Fri Jul 13 17:09:45 CEST 2012 */
package common.types.ext;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import org.apache.uima.jcas.tcas.Annotation;


/** 
 * Updated by JCasGen Mon Mar 25 22:57:13 CET 2013
 * XML source: /media/MyPassport/workspace/uima-connectors/desc/connectors/corpus/P7T/P7TPlusTagged-XML2CAS-mapTo-sw+mw-WST+SPL-ViewWriter.xml
 * @generated */
public class FTBWord extends Annotation {
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = JCasRegistry.register(FTBWord.class);
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int type = typeIndexID;
  /** @generated  */
  @Override
  public              int getTypeIndexID() {return typeIndexID;}
 
  /** Never called.  Disable default constructor
   * @generated */
  protected FTBWord() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated */
  public FTBWord(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated */
  public FTBWord(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated */  
  public FTBWord(JCas jcas, int begin, int end) {
    super(jcas);
    setBegin(begin);
    setEnd(end);
    readObject();
  }   

  /** <!-- begin-user-doc -->
    * Write your own initialization here
    * <!-- end-user-doc -->
  @generated modifiable */
  private void readObject() {/*default - does nothing empty block */}
     
 
    
  //*--------------*
  //* Feature: cat

  /** getter for cat - gets 
   * @generated */
  public String getCat() {
    if (FTBWord_Type.featOkTst && ((FTBWord_Type)jcasType).casFeat_cat == null)
      jcasType.jcas.throwFeatMissing("cat", "common.types.ext.FTBWord");
    return jcasType.ll_cas.ll_getStringValue(addr, ((FTBWord_Type)jcasType).casFeatCode_cat);}
    
  /** setter for cat - sets  
   * @generated */
  public void setCat(String v) {
    if (FTBWord_Type.featOkTst && ((FTBWord_Type)jcasType).casFeat_cat == null)
      jcasType.jcas.throwFeatMissing("cat", "common.types.ext.FTBWord");
    jcasType.ll_cas.ll_setStringValue(addr, ((FTBWord_Type)jcasType).casFeatCode_cat, v);}    
   
    
  //*--------------*
  //* Feature: subcat

  /** getter for subcat - gets 
   * @generated */
  public String getSubcat() {
    if (FTBWord_Type.featOkTst && ((FTBWord_Type)jcasType).casFeat_subcat == null)
      jcasType.jcas.throwFeatMissing("subcat", "common.types.ext.FTBWord");
    return jcasType.ll_cas.ll_getStringValue(addr, ((FTBWord_Type)jcasType).casFeatCode_subcat);}
    
  /** setter for subcat - sets  
   * @generated */
  public void setSubcat(String v) {
    if (FTBWord_Type.featOkTst && ((FTBWord_Type)jcasType).casFeat_subcat == null)
      jcasType.jcas.throwFeatMissing("subcat", "common.types.ext.FTBWord");
    jcasType.ll_cas.ll_setStringValue(addr, ((FTBWord_Type)jcasType).casFeatCode_subcat, v);}    
   
    
  //*--------------*
  //* Feature: mph

  /** getter for mph - gets 
   * @generated */
  public String getMph() {
    if (FTBWord_Type.featOkTst && ((FTBWord_Type)jcasType).casFeat_mph == null)
      jcasType.jcas.throwFeatMissing("mph", "common.types.ext.FTBWord");
    return jcasType.ll_cas.ll_getStringValue(addr, ((FTBWord_Type)jcasType).casFeatCode_mph);}
    
  /** setter for mph - sets  
   * @generated */
  public void setMph(String v) {
    if (FTBWord_Type.featOkTst && ((FTBWord_Type)jcasType).casFeat_mph == null)
      jcasType.jcas.throwFeatMissing("mph", "common.types.ext.FTBWord");
    jcasType.ll_cas.ll_setStringValue(addr, ((FTBWord_Type)jcasType).casFeatCode_mph, v);}    
   
    
  //*--------------*
  //* Feature: lemma

  /** getter for lemma - gets 
   * @generated */
  public String getLemma() {
    if (FTBWord_Type.featOkTst && ((FTBWord_Type)jcasType).casFeat_lemma == null)
      jcasType.jcas.throwFeatMissing("lemma", "common.types.ext.FTBWord");
    return jcasType.ll_cas.ll_getStringValue(addr, ((FTBWord_Type)jcasType).casFeatCode_lemma);}
    
  /** setter for lemma - sets  
   * @generated */
  public void setLemma(String v) {
    if (FTBWord_Type.featOkTst && ((FTBWord_Type)jcasType).casFeat_lemma == null)
      jcasType.jcas.throwFeatMissing("lemma", "common.types.ext.FTBWord");
    jcasType.ll_cas.ll_setStringValue(addr, ((FTBWord_Type)jcasType).casFeatCode_lemma, v);}    
   
    
  //*--------------*
  //* Feature: gender

  /** getter for gender - gets 
   * @generated */
  public String getGender() {
    if (FTBWord_Type.featOkTst && ((FTBWord_Type)jcasType).casFeat_gender == null)
      jcasType.jcas.throwFeatMissing("gender", "common.types.ext.FTBWord");
    return jcasType.ll_cas.ll_getStringValue(addr, ((FTBWord_Type)jcasType).casFeatCode_gender);}
    
  /** setter for gender - sets  
   * @generated */
  public void setGender(String v) {
    if (FTBWord_Type.featOkTst && ((FTBWord_Type)jcasType).casFeat_gender == null)
      jcasType.jcas.throwFeatMissing("gender", "common.types.ext.FTBWord");
    jcasType.ll_cas.ll_setStringValue(addr, ((FTBWord_Type)jcasType).casFeatCode_gender, v);}    
   
    
  //*--------------*
  //* Feature: number

  /** getter for number - gets 
   * @generated */
  public String getNumber() {
    if (FTBWord_Type.featOkTst && ((FTBWord_Type)jcasType).casFeat_number == null)
      jcasType.jcas.throwFeatMissing("number", "common.types.ext.FTBWord");
    return jcasType.ll_cas.ll_getStringValue(addr, ((FTBWord_Type)jcasType).casFeatCode_number);}
    
  /** setter for number - sets  
   * @generated */
  public void setNumber(String v) {
    if (FTBWord_Type.featOkTst && ((FTBWord_Type)jcasType).casFeat_number == null)
      jcasType.jcas.throwFeatMissing("number", "common.types.ext.FTBWord");
    jcasType.ll_cas.ll_setStringValue(addr, ((FTBWord_Type)jcasType).casFeatCode_number, v);}    
   
    
  //*--------------*
  //* Feature: case

  /** getter for case - gets 
   * @generated */
  public String getCase() {
    if (FTBWord_Type.featOkTst && ((FTBWord_Type)jcasType).casFeat_case == null)
      jcasType.jcas.throwFeatMissing("case", "common.types.ext.FTBWord");
    return jcasType.ll_cas.ll_getStringValue(addr, ((FTBWord_Type)jcasType).casFeatCode_case);}
    
  /** setter for case - sets  
   * @generated */
  public void setCase(String v) {
    if (FTBWord_Type.featOkTst && ((FTBWord_Type)jcasType).casFeat_case == null)
      jcasType.jcas.throwFeatMissing("case", "common.types.ext.FTBWord");
    jcasType.ll_cas.ll_setStringValue(addr, ((FTBWord_Type)jcasType).casFeatCode_case, v);}    
   
    
  //*--------------*
  //* Feature: person

  /** getter for person - gets 
   * @generated */
  public String getPerson() {
    if (FTBWord_Type.featOkTst && ((FTBWord_Type)jcasType).casFeat_person == null)
      jcasType.jcas.throwFeatMissing("person", "common.types.ext.FTBWord");
    return jcasType.ll_cas.ll_getStringValue(addr, ((FTBWord_Type)jcasType).casFeatCode_person);}
    
  /** setter for person - sets  
   * @generated */
  public void setPerson(String v) {
    if (FTBWord_Type.featOkTst && ((FTBWord_Type)jcasType).casFeat_person == null)
      jcasType.jcas.throwFeatMissing("person", "common.types.ext.FTBWord");
    jcasType.ll_cas.ll_setStringValue(addr, ((FTBWord_Type)jcasType).casFeatCode_person, v);}    
   
    
  //*--------------*
  //* Feature: mood

  /** getter for mood - gets 
   * @generated */
  public String getMood() {
    if (FTBWord_Type.featOkTst && ((FTBWord_Type)jcasType).casFeat_mood == null)
      jcasType.jcas.throwFeatMissing("mood", "common.types.ext.FTBWord");
    return jcasType.ll_cas.ll_getStringValue(addr, ((FTBWord_Type)jcasType).casFeatCode_mood);}
    
  /** setter for mood - sets  
   * @generated */
  public void setMood(String v) {
    if (FTBWord_Type.featOkTst && ((FTBWord_Type)jcasType).casFeat_mood == null)
      jcasType.jcas.throwFeatMissing("mood", "common.types.ext.FTBWord");
    jcasType.ll_cas.ll_setStringValue(addr, ((FTBWord_Type)jcasType).casFeatCode_mood, v);}    
   
    
  //*--------------*
  //* Feature: tense

  /** getter for tense - gets 
   * @generated */
  public String getTense() {
    if (FTBWord_Type.featOkTst && ((FTBWord_Type)jcasType).casFeat_tense == null)
      jcasType.jcas.throwFeatMissing("tense", "common.types.ext.FTBWord");
    return jcasType.ll_cas.ll_getStringValue(addr, ((FTBWord_Type)jcasType).casFeatCode_tense);}
    
  /** setter for tense - sets  
   * @generated */
  public void setTense(String v) {
    if (FTBWord_Type.featOkTst && ((FTBWord_Type)jcasType).casFeat_tense == null)
      jcasType.jcas.throwFeatMissing("tense", "common.types.ext.FTBWord");
    jcasType.ll_cas.ll_setStringValue(addr, ((FTBWord_Type)jcasType).casFeatCode_tense, v);}    
   
    
  //*--------------*
  //* Feature: catP

  /** getter for catP - gets 
   * @generated */
  public String getCatP() {
    if (FTBWord_Type.featOkTst && ((FTBWord_Type)jcasType).casFeat_catP == null)
      jcasType.jcas.throwFeatMissing("catP", "common.types.ext.FTBWord");
    return jcasType.ll_cas.ll_getStringValue(addr, ((FTBWord_Type)jcasType).casFeatCode_catP);}
    
  /** setter for catP - sets  
   * @generated */
  public void setCatP(String v) {
    if (FTBWord_Type.featOkTst && ((FTBWord_Type)jcasType).casFeat_catP == null)
      jcasType.jcas.throwFeatMissing("catP", "common.types.ext.FTBWord");
    jcasType.ll_cas.ll_setStringValue(addr, ((FTBWord_Type)jcasType).casFeatCode_catP, v);}    
   
    
  //*--------------*
  //* Feature: mw

  /** getter for mw - gets 
   * @generated */
  public String getMw() {
    if (FTBWord_Type.featOkTst && ((FTBWord_Type)jcasType).casFeat_mw == null)
      jcasType.jcas.throwFeatMissing("mw", "common.types.ext.FTBWord");
    return jcasType.ll_cas.ll_getStringValue(addr, ((FTBWord_Type)jcasType).casFeatCode_mw);}
    
  /** setter for mw - sets  
   * @generated */
  public void setMw(String v) {
    if (FTBWord_Type.featOkTst && ((FTBWord_Type)jcasType).casFeat_mw == null)
      jcasType.jcas.throwFeatMissing("mw", "common.types.ext.FTBWord");
    jcasType.ll_cas.ll_setStringValue(addr, ((FTBWord_Type)jcasType).casFeatCode_mw, v);}    
   
    
  //*--------------*
  //* Feature: po

  /** getter for po - gets 
   * @generated */
  public String getPo() {
    if (FTBWord_Type.featOkTst && ((FTBWord_Type)jcasType).casFeat_po == null)
      jcasType.jcas.throwFeatMissing("po", "common.types.ext.FTBWord");
    return jcasType.ll_cas.ll_getStringValue(addr, ((FTBWord_Type)jcasType).casFeatCode_po);}
    
  /** setter for po - sets  
   * @generated */
  public void setPo(String v) {
    if (FTBWord_Type.featOkTst && ((FTBWord_Type)jcasType).casFeat_po == null)
      jcasType.jcas.throwFeatMissing("po", "common.types.ext.FTBWord");
    jcasType.ll_cas.ll_setStringValue(addr, ((FTBWord_Type)jcasType).casFeatCode_po, v);}    
   
    
  //*--------------*
  //* Feature: sw

  /** getter for sw - gets 
   * @generated */
  public String getSw() {
    if (FTBWord_Type.featOkTst && ((FTBWord_Type)jcasType).casFeat_sw == null)
      jcasType.jcas.throwFeatMissing("sw", "common.types.ext.FTBWord");
    return jcasType.ll_cas.ll_getStringValue(addr, ((FTBWord_Type)jcasType).casFeatCode_sw);}
    
  /** setter for sw - sets  
   * @generated */
  public void setSw(String v) {
    if (FTBWord_Type.featOkTst && ((FTBWord_Type)jcasType).casFeat_sw == null)
      jcasType.jcas.throwFeatMissing("sw", "common.types.ext.FTBWord");
    jcasType.ll_cas.ll_setStringValue(addr, ((FTBWord_Type)jcasType).casFeatCode_sw, v);}    
   
    
  //*--------------*
  //* Feature: rf

  /** getter for rf - gets 
   * @generated */
  public String getRf() {
    if (FTBWord_Type.featOkTst && ((FTBWord_Type)jcasType).casFeat_rf == null)
      jcasType.jcas.throwFeatMissing("rf", "common.types.ext.FTBWord");
    return jcasType.ll_cas.ll_getStringValue(addr, ((FTBWord_Type)jcasType).casFeatCode_rf);}
    
  /** setter for rf - sets  
   * @generated */
  public void setRf(String v) {
    if (FTBWord_Type.featOkTst && ((FTBWord_Type)jcasType).casFeat_rf == null)
      jcasType.jcas.throwFeatMissing("rf", "common.types.ext.FTBWord");
    jcasType.ll_cas.ll_setStringValue(addr, ((FTBWord_Type)jcasType).casFeatCode_rf, v);}    
   
    
  //*--------------*
  //* Feature: pn

  /** getter for pn - gets // mon, notre
   * @generated */
  public String getPn() {
    if (FTBWord_Type.featOkTst && ((FTBWord_Type)jcasType).casFeat_pn == null)
      jcasType.jcas.throwFeatMissing("pn", "common.types.ext.FTBWord");
    return jcasType.ll_cas.ll_getStringValue(addr, ((FTBWord_Type)jcasType).casFeatCode_pn);}
    
  /** setter for pn - sets // mon, notre 
   * @generated */
  public void setPn(String v) {
    if (FTBWord_Type.featOkTst && ((FTBWord_Type)jcasType).casFeat_pn == null)
      jcasType.jcas.throwFeatMissing("pn", "common.types.ext.FTBWord");
    jcasType.ll_cas.ll_setStringValue(addr, ((FTBWord_Type)jcasType).casFeatCode_pn, v);}    
   
    
  //*--------------*
  //* Feature: ee

  /** getter for ee - gets 
   * @generated */
  public String getEe() {
    if (FTBWord_Type.featOkTst && ((FTBWord_Type)jcasType).casFeat_ee == null)
      jcasType.jcas.throwFeatMissing("ee", "common.types.ext.FTBWord");
    return jcasType.ll_cas.ll_getStringValue(addr, ((FTBWord_Type)jcasType).casFeatCode_ee);}
    
  /** setter for ee - sets  
   * @generated */
  public void setEe(String v) {
    if (FTBWord_Type.featOkTst && ((FTBWord_Type)jcasType).casFeat_ee == null)
      jcasType.jcas.throwFeatMissing("ee", "common.types.ext.FTBWord");
    jcasType.ll_cas.ll_setStringValue(addr, ((FTBWord_Type)jcasType).casFeatCode_ee, v);}    
   
    
  //*--------------*
  //* Feature: catint

  /** getter for catint - gets 
   * @generated */
  public String getCatint() {
    if (FTBWord_Type.featOkTst && ((FTBWord_Type)jcasType).casFeat_catint == null)
      jcasType.jcas.throwFeatMissing("catint", "common.types.ext.FTBWord");
    return jcasType.ll_cas.ll_getStringValue(addr, ((FTBWord_Type)jcasType).casFeatCode_catint);}
    
  /** setter for catint - sets  
   * @generated */
  public void setCatint(String v) {
    if (FTBWord_Type.featOkTst && ((FTBWord_Type)jcasType).casFeat_catint == null)
      jcasType.jcas.throwFeatMissing("catint", "common.types.ext.FTBWord");
    jcasType.ll_cas.ll_setStringValue(addr, ((FTBWord_Type)jcasType).casFeatCode_catint, v);}    
   
    
  //*--------------*
  //* Feature: gu

  /** getter for gu - gets 
   * @generated */
  public String getGu() {
    if (FTBWord_Type.featOkTst && ((FTBWord_Type)jcasType).casFeat_gu == null)
      jcasType.jcas.throwFeatMissing("gu", "common.types.ext.FTBWord");
    return jcasType.ll_cas.ll_getStringValue(addr, ((FTBWord_Type)jcasType).casFeatCode_gu);}
    
  /** setter for gu - sets  
   * @generated */
  public void setGu(String v) {
    if (FTBWord_Type.featOkTst && ((FTBWord_Type)jcasType).casFeat_gu == null)
      jcasType.jcas.throwFeatMissing("gu", "common.types.ext.FTBWord");
    jcasType.ll_cas.ll_setStringValue(addr, ((FTBWord_Type)jcasType).casFeatCode_gu, v);}    
  }

    