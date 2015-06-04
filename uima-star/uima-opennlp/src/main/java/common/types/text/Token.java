

/* First created by JCasGen Sat Dec 10 00:59:10 CET 2011 */
package common.types.text;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;



/** 
 * Updated by JCasGen Sat Dec 10 00:59:12 CET 2011
 * XML source: /media/MyPassport/current/public/research/UIMA-USER-DEV-ENV/workspace/demo-apache-opennlp-uima/desc/opennlp/fr/SentenceDetectorAE.xml
 * @generated */
public class Token extends Annotation {
  /** @generated
   * @ordered 
   */
  public final static int typeIndexID = JCasRegistry.register(Token.class);
  /** @generated
   * @ordered 
   */
  public final static int type = typeIndexID;
  /** @generated  */
  public              int getTypeIndexID() {return typeIndexID;}
 
  /** Never called.  Disable default constructor
   * @generated */
  protected Token() {}
    
  /** Internal - constructor used by generator 
   * @generated */
  public Token(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated */
  public Token(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated */  
  public Token(JCas jcas, int begin, int end) {
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
    if (Token_Type.featOkTst && ((Token_Type)jcasType).casFeat_lemma == null)
      jcasType.jcas.throwFeatMissing("lemma", "common.types.text.Token");
    return jcasType.ll_cas.ll_getStringValue(addr, ((Token_Type)jcasType).casFeatCode_lemma);}
    
  /** setter for lemma - sets Lemma of the word. 
   * @generated */
  public void setLemma(String v) {
    if (Token_Type.featOkTst && ((Token_Type)jcasType).casFeat_lemma == null)
      jcasType.jcas.throwFeatMissing("lemma", "common.types.text.Token");
    jcasType.ll_cas.ll_setStringValue(addr, ((Token_Type)jcasType).casFeatCode_lemma, v);}    
   
    
  //*--------------*
  //* Feature: mph

  /** getter for mph - gets ?
   * @generated */
  public String getMph() {
    if (Token_Type.featOkTst && ((Token_Type)jcasType).casFeat_mph == null)
      jcasType.jcas.throwFeatMissing("mph", "common.types.text.Token");
    return jcasType.ll_cas.ll_getStringValue(addr, ((Token_Type)jcasType).casFeatCode_mph);}
    
  /** setter for mph - sets ? 
   * @generated */
  public void setMph(String v) {
    if (Token_Type.featOkTst && ((Token_Type)jcasType).casFeat_mph == null)
      jcasType.jcas.throwFeatMissing("mph", "common.types.text.Token");
    jcasType.ll_cas.ll_setStringValue(addr, ((Token_Type)jcasType).casFeatCode_mph, v);}    
   
    
  //*--------------*
  //* Feature: posTag

  /** getter for posTag - gets 
   * @generated */
  public String getPosTag() {
    if (Token_Type.featOkTst && ((Token_Type)jcasType).casFeat_posTag == null)
      jcasType.jcas.throwFeatMissing("posTag", "common.types.text.Token");
    return jcasType.ll_cas.ll_getStringValue(addr, ((Token_Type)jcasType).casFeatCode_posTag);}
    
  /** setter for posTag - sets  
   * @generated */
  public void setPosTag(String v) {
    if (Token_Type.featOkTst && ((Token_Type)jcasType).casFeat_posTag == null)
      jcasType.jcas.throwFeatMissing("posTag", "common.types.text.Token");
    jcasType.ll_cas.ll_setStringValue(addr, ((Token_Type)jcasType).casFeatCode_posTag, v);}    
   
    
  //*--------------*
  //* Feature: chunkTag

  /** getter for chunkTag - gets 
   * @generated */
  public String getChunkTag() {
    if (Token_Type.featOkTst && ((Token_Type)jcasType).casFeat_chunkTag == null)
      jcasType.jcas.throwFeatMissing("chunkTag", "common.types.text.Token");
    return jcasType.ll_cas.ll_getStringValue(addr, ((Token_Type)jcasType).casFeatCode_chunkTag);}
    
  /** setter for chunkTag - sets  
   * @generated */
  public void setChunkTag(String v) {
    if (Token_Type.featOkTst && ((Token_Type)jcasType).casFeat_chunkTag == null)
      jcasType.jcas.throwFeatMissing("chunkTag", "common.types.text.Token");
    jcasType.ll_cas.ll_setStringValue(addr, ((Token_Type)jcasType).casFeatCode_chunkTag, v);}    
   
    
  //*--------------*
  //* Feature: raw

  /** getter for raw - gets 
   * @generated */
  public String getRaw() {
    if (Token_Type.featOkTst && ((Token_Type)jcasType).casFeat_raw == null)
      jcasType.jcas.throwFeatMissing("raw", "common.types.text.Token");
    return jcasType.ll_cas.ll_getStringValue(addr, ((Token_Type)jcasType).casFeatCode_raw);}
    
  /** setter for raw - sets  
   * @generated */
  public void setRaw(String v) {
    if (Token_Type.featOkTst && ((Token_Type)jcasType).casFeat_raw == null)
      jcasType.jcas.throwFeatMissing("raw", "common.types.text.Token");
    jcasType.ll_cas.ll_setStringValue(addr, ((Token_Type)jcasType).casFeatCode_raw, v);}    
   
    
  //*--------------*
  //* Feature: subCat

  /** getter for subCat - gets 
   * @generated */
  public String getSubCat() {
    if (Token_Type.featOkTst && ((Token_Type)jcasType).casFeat_subCat == null)
      jcasType.jcas.throwFeatMissing("subCat", "common.types.text.Token");
    return jcasType.ll_cas.ll_getStringValue(addr, ((Token_Type)jcasType).casFeatCode_subCat);}
    
  /** setter for subCat - sets  
   * @generated */
  public void setSubCat(String v) {
    if (Token_Type.featOkTst && ((Token_Type)jcasType).casFeat_subCat == null)
      jcasType.jcas.throwFeatMissing("subCat", "common.types.text.Token");
    jcasType.ll_cas.ll_setStringValue(addr, ((Token_Type)jcasType).casFeatCode_subCat, v);}    
  }

    