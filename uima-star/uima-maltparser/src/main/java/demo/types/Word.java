

/* First created by JCasGen Mon May 23 17:04:51 CEST 2011 */
package demo.types;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import org.apache.uima.jcas.tcas.Annotation;


/** 
 * Updated by JCasGen Mon May 23 17:05:49 CEST 2011
 * XML source: /media/MyPassport/current/public/research/UIMA-USER-DEV-ENV/workspace/demo-frenchTreebank-hmmTrainerTagger-maltparser/desc/FrenchTreebankAE.xml
 * @generated */
public class Word extends Annotation {
  /** @generated
   * @ordered 
   */
  public final static int typeIndexID = JCasRegistry.register(Word.class);
  /** @generated
   * @ordered 
   */
  public final static int type = typeIndexID;
  /** @generated  */
  public              int getTypeIndexID() {return typeIndexID;}
 
  /** Never called.  Disable default constructor
   * @generated */
  protected Word() {}
    
  /** Internal - constructor used by generator 
   * @generated */
  public Word(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated */
  public Word(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated */  
  public Word(JCas jcas, int begin, int end) {
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
  //* Feature: lemma

  /** getter for lemma - gets Lemma of the word.
   * @generated */
  public String getLemma() {
    if (Word_Type.featOkTst && ((Word_Type)jcasType).casFeat_lemma == null)
      jcasType.jcas.throwFeatMissing("lemma", "demo.types.Word");
    return jcasType.ll_cas.ll_getStringValue(addr, ((Word_Type)jcasType).casFeatCode_lemma);}
    
  /** setter for lemma - sets Lemma of the word. 
   * @generated */
  public void setLemma(String v) {
    if (Word_Type.featOkTst && ((Word_Type)jcasType).casFeat_lemma == null)
      jcasType.jcas.throwFeatMissing("lemma", "demo.types.Word");
    jcasType.ll_cas.ll_setStringValue(addr, ((Word_Type)jcasType).casFeatCode_lemma, v);}    
   
    
  //*--------------*
  //* Feature: cat

  /** getter for cat - gets Grammatical category of the word.
   * @generated */
  public String getCat() {
    if (Word_Type.featOkTst && ((Word_Type)jcasType).casFeat_cat == null)
      jcasType.jcas.throwFeatMissing("cat", "demo.types.Word");
    return jcasType.ll_cas.ll_getStringValue(addr, ((Word_Type)jcasType).casFeatCode_cat);}
    
  /** setter for cat - sets Grammatical category of the word. 
   * @generated */
  public void setCat(String v) {
    if (Word_Type.featOkTst && ((Word_Type)jcasType).casFeat_cat == null)
      jcasType.jcas.throwFeatMissing("cat", "demo.types.Word");
    jcasType.ll_cas.ll_setStringValue(addr, ((Word_Type)jcasType).casFeatCode_cat, v);}    
   
    
  //*--------------*
  //* Feature: subcat

  /** getter for subcat - gets Grammatical subcategory of the word.
   * @generated */
  public String getSubcat() {
    if (Word_Type.featOkTst && ((Word_Type)jcasType).casFeat_subcat == null)
      jcasType.jcas.throwFeatMissing("subcat", "demo.types.Word");
    return jcasType.ll_cas.ll_getStringValue(addr, ((Word_Type)jcasType).casFeatCode_subcat);}
    
  /** setter for subcat - sets Grammatical subcategory of the word. 
   * @generated */
  public void setSubcat(String v) {
    if (Word_Type.featOkTst && ((Word_Type)jcasType).casFeat_subcat == null)
      jcasType.jcas.throwFeatMissing("subcat", "demo.types.Word");
    jcasType.ll_cas.ll_setStringValue(addr, ((Word_Type)jcasType).casFeatCode_subcat, v);}    
   
    
  //*--------------*
  //* Feature: ei

  /** getter for ei - gets ?
   * @generated */
  public String getEi() {
    if (Word_Type.featOkTst && ((Word_Type)jcasType).casFeat_ei == null)
      jcasType.jcas.throwFeatMissing("ei", "demo.types.Word");
    return jcasType.ll_cas.ll_getStringValue(addr, ((Word_Type)jcasType).casFeatCode_ei);}
    
  /** setter for ei - sets ? 
   * @generated */
  public void setEi(String v) {
    if (Word_Type.featOkTst && ((Word_Type)jcasType).casFeat_ei == null)
      jcasType.jcas.throwFeatMissing("ei", "demo.types.Word");
    jcasType.ll_cas.ll_setStringValue(addr, ((Word_Type)jcasType).casFeatCode_ei, v);}    
   
    
  //*--------------*
  //* Feature: ee

  /** getter for ee - gets ?
   * @generated */
  public String getEe() {
    if (Word_Type.featOkTst && ((Word_Type)jcasType).casFeat_ee == null)
      jcasType.jcas.throwFeatMissing("ee", "demo.types.Word");
    return jcasType.ll_cas.ll_getStringValue(addr, ((Word_Type)jcasType).casFeatCode_ee);}
    
  /** setter for ee - sets ? 
   * @generated */
  public void setEe(String v) {
    if (Word_Type.featOkTst && ((Word_Type)jcasType).casFeat_ee == null)
      jcasType.jcas.throwFeatMissing("ee", "demo.types.Word");
    jcasType.ll_cas.ll_setStringValue(addr, ((Word_Type)jcasType).casFeatCode_ee, v);}    
   
    
  //*--------------*
  //* Feature: mph

  /** getter for mph - gets ?
   * @generated */
  public String getMph() {
    if (Word_Type.featOkTst && ((Word_Type)jcasType).casFeat_mph == null)
      jcasType.jcas.throwFeatMissing("mph", "demo.types.Word");
    return jcasType.ll_cas.ll_getStringValue(addr, ((Word_Type)jcasType).casFeatCode_mph);}
    
  /** setter for mph - sets ? 
   * @generated */
  public void setMph(String v) {
    if (Word_Type.featOkTst && ((Word_Type)jcasType).casFeat_mph == null)
      jcasType.jcas.throwFeatMissing("mph", "demo.types.Word");
    jcasType.ll_cas.ll_setStringValue(addr, ((Word_Type)jcasType).casFeatCode_mph, v);}    
  }

    