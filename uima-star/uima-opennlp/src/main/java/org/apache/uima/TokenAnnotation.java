

/* First created by JCasGen Tue Jul 05 15:38:27 CEST 2011 */
package org.apache.uima;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import org.apache.uima.jcas.tcas.Annotation;


/** 
 * Updated by JCasGen Fri Oct 14 18:41:42 CEST 2011
 * XML source: /media/MyPassport/current/public/research/UIMA-USER-DEV-ENV/workspace/demo-apache-opennlp-uima/desc/opennlpDemo/tokenizer/FrenchTreebank-XML2CAS-annotationMapper-CAS2WST-ViewWriter-AAE.xml
 * @generated */
public class TokenAnnotation extends Annotation {
  /** @generated
   * @ordered 
   */
  public final static int typeIndexID = JCasRegistry.register(TokenAnnotation.class);
  /** @generated
   * @ordered 
   */
  public final static int type = typeIndexID;
  /** @generated  */
  public              int getTypeIndexID() {return typeIndexID;}
 
  /** Never called.  Disable default constructor
   * @generated */
  protected TokenAnnotation() {}
    
  /** Internal - constructor used by generator 
   * @generated */
  public TokenAnnotation(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated */
  public TokenAnnotation(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated */  
  public TokenAnnotation(JCas jcas, int begin, int end) {
    super(jcas);
    setBegin(begin);
    setEnd(end);
    readObject();
  }   

  /** <!-- begin-user-doc -->
    * Write your own initialization here
    * <!-- end-user-doc -->
  @generated modifiable */
  private void readObject() {}
     
 
    
  //*--------------*
  //* Feature: posTag

  /** getter for posTag - gets 
   * @generated */
  public String getPosTag() {
    if (TokenAnnotation_Type.featOkTst && ((TokenAnnotation_Type)jcasType).casFeat_posTag == null)
      jcasType.jcas.throwFeatMissing("posTag", "org.apache.uima.TokenAnnotation");
    return jcasType.ll_cas.ll_getStringValue(addr, ((TokenAnnotation_Type)jcasType).casFeatCode_posTag);}
    
  /** setter for posTag - sets  
   * @generated */
  public void setPosTag(String v) {
    if (TokenAnnotation_Type.featOkTst && ((TokenAnnotation_Type)jcasType).casFeat_posTag == null)
      jcasType.jcas.throwFeatMissing("posTag", "org.apache.uima.TokenAnnotation");
    jcasType.ll_cas.ll_setStringValue(addr, ((TokenAnnotation_Type)jcasType).casFeatCode_posTag, v);}    
   
    
  //*--------------*
  //* Feature: lemma

  /** getter for lemma - gets Lemma of the word.
   * @generated */
  public String getLemma() {
    if (TokenAnnotation_Type.featOkTst && ((TokenAnnotation_Type)jcasType).casFeat_lemma == null)
      jcasType.jcas.throwFeatMissing("lemma", "org.apache.uima.TokenAnnotation");
    return jcasType.ll_cas.ll_getStringValue(addr, ((TokenAnnotation_Type)jcasType).casFeatCode_lemma);}
    
  /** setter for lemma - sets Lemma of the word. 
   * @generated */
  public void setLemma(String v) {
    if (TokenAnnotation_Type.featOkTst && ((TokenAnnotation_Type)jcasType).casFeat_lemma == null)
      jcasType.jcas.throwFeatMissing("lemma", "org.apache.uima.TokenAnnotation");
    jcasType.ll_cas.ll_setStringValue(addr, ((TokenAnnotation_Type)jcasType).casFeatCode_lemma, v);}    
   
    
  //*--------------*
  //* Feature: cat

  /** getter for cat - gets Grammatical category of the word.
   * @generated */
  public String getCat() {
    if (TokenAnnotation_Type.featOkTst && ((TokenAnnotation_Type)jcasType).casFeat_cat == null)
      jcasType.jcas.throwFeatMissing("cat", "org.apache.uima.TokenAnnotation");
    return jcasType.ll_cas.ll_getStringValue(addr, ((TokenAnnotation_Type)jcasType).casFeatCode_cat);}
    
  /** setter for cat - sets Grammatical category of the word. 
   * @generated */
  public void setCat(String v) {
    if (TokenAnnotation_Type.featOkTst && ((TokenAnnotation_Type)jcasType).casFeat_cat == null)
      jcasType.jcas.throwFeatMissing("cat", "org.apache.uima.TokenAnnotation");
    jcasType.ll_cas.ll_setStringValue(addr, ((TokenAnnotation_Type)jcasType).casFeatCode_cat, v);}    
   
    
  //*--------------*
  //* Feature: subcat

  /** getter for subcat - gets Grammatical subcategory of the word.
   * @generated */
  public String getSubcat() {
    if (TokenAnnotation_Type.featOkTst && ((TokenAnnotation_Type)jcasType).casFeat_subcat == null)
      jcasType.jcas.throwFeatMissing("subcat", "org.apache.uima.TokenAnnotation");
    return jcasType.ll_cas.ll_getStringValue(addr, ((TokenAnnotation_Type)jcasType).casFeatCode_subcat);}
    
  /** setter for subcat - sets Grammatical subcategory of the word. 
   * @generated */
  public void setSubcat(String v) {
    if (TokenAnnotation_Type.featOkTst && ((TokenAnnotation_Type)jcasType).casFeat_subcat == null)
      jcasType.jcas.throwFeatMissing("subcat", "org.apache.uima.TokenAnnotation");
    jcasType.ll_cas.ll_setStringValue(addr, ((TokenAnnotation_Type)jcasType).casFeatCode_subcat, v);}    
   
    
  //*--------------*
  //* Feature: ei

  /** getter for ei - gets ?
   * @generated */
  public String getEi() {
    if (TokenAnnotation_Type.featOkTst && ((TokenAnnotation_Type)jcasType).casFeat_ei == null)
      jcasType.jcas.throwFeatMissing("ei", "org.apache.uima.TokenAnnotation");
    return jcasType.ll_cas.ll_getStringValue(addr, ((TokenAnnotation_Type)jcasType).casFeatCode_ei);}
    
  /** setter for ei - sets ? 
   * @generated */
  public void setEi(String v) {
    if (TokenAnnotation_Type.featOkTst && ((TokenAnnotation_Type)jcasType).casFeat_ei == null)
      jcasType.jcas.throwFeatMissing("ei", "org.apache.uima.TokenAnnotation");
    jcasType.ll_cas.ll_setStringValue(addr, ((TokenAnnotation_Type)jcasType).casFeatCode_ei, v);}    
   
    
  //*--------------*
  //* Feature: ee

  /** getter for ee - gets ?
   * @generated */
  public String getEe() {
    if (TokenAnnotation_Type.featOkTst && ((TokenAnnotation_Type)jcasType).casFeat_ee == null)
      jcasType.jcas.throwFeatMissing("ee", "org.apache.uima.TokenAnnotation");
    return jcasType.ll_cas.ll_getStringValue(addr, ((TokenAnnotation_Type)jcasType).casFeatCode_ee);}
    
  /** setter for ee - sets ? 
   * @generated */
  public void setEe(String v) {
    if (TokenAnnotation_Type.featOkTst && ((TokenAnnotation_Type)jcasType).casFeat_ee == null)
      jcasType.jcas.throwFeatMissing("ee", "org.apache.uima.TokenAnnotation");
    jcasType.ll_cas.ll_setStringValue(addr, ((TokenAnnotation_Type)jcasType).casFeatCode_ee, v);}    
   
    
  //*--------------*
  //* Feature: mph

  /** getter for mph - gets ?
   * @generated */
  public String getMph() {
    if (TokenAnnotation_Type.featOkTst && ((TokenAnnotation_Type)jcasType).casFeat_mph == null)
      jcasType.jcas.throwFeatMissing("mph", "org.apache.uima.TokenAnnotation");
    return jcasType.ll_cas.ll_getStringValue(addr, ((TokenAnnotation_Type)jcasType).casFeatCode_mph);}
    
  /** setter for mph - sets ? 
   * @generated */
  public void setMph(String v) {
    if (TokenAnnotation_Type.featOkTst && ((TokenAnnotation_Type)jcasType).casFeat_mph == null)
      jcasType.jcas.throwFeatMissing("mph", "org.apache.uima.TokenAnnotation");
    jcasType.ll_cas.ll_setStringValue(addr, ((TokenAnnotation_Type)jcasType).casFeatCode_mph, v);}    
                        //*--------------*
  //* Feature: chunkTag

  /** getter for chunkTag - gets 
   * @generated */
  public String getChunkTag() {
    if (TokenAnnotation_Type.featOkTst && ((TokenAnnotation_Type)jcasType).casFeat_chunkTag == null)
      jcasType.jcas.throwFeatMissing("chunkTag", "org.apache.uima.TokenAnnotation");
    return jcasType.ll_cas.ll_getStringValue(addr, ((TokenAnnotation_Type)jcasType).casFeatCode_chunkTag);}
    
  /** setter for chunkTag - sets  
   * @generated */
  public void setChunkTag(String v) {
    if (TokenAnnotation_Type.featOkTst && ((TokenAnnotation_Type)jcasType).casFeat_chunkTag == null)
      jcasType.jcas.throwFeatMissing("chunkTag", "org.apache.uima.TokenAnnotation");
    jcasType.ll_cas.ll_setStringValue(addr, ((TokenAnnotation_Type)jcasType).casFeatCode_chunkTag, v);}    
   
    
}

    