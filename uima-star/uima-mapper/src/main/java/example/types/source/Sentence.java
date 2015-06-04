

/* First created by JCasGen Thu Apr 15 15:16:12 CEST 2010 */
package example.types.source;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import org.apache.uima.jcas.cas.FSArray;
import org.apache.uima.jcas.tcas.Annotation;
import org.apache.uima.jcas.cas.StringArray;


/** 
 * Updated by JCasGen Sun Oct 03 09:51:21 CEST 2010
 * XML source: /media/MyPassport/current/public/research/UIMA-USER-DEV-ENV/workspace/uima-type-mapper/desc/example/wst-tagger-preTypeMapper-typeMapper-postTypeMapper-AAE.xml
 * @generated */
public class Sentence extends Annotation {
  /** @generated
   * @ordered 
   */
  public final static int typeIndexID = JCasRegistry.register(Sentence.class);
  /** @generated
   * @ordered 
   */
  public final static int type = typeIndexID;
  /** @generated  */
  public              int getTypeIndexID() {return typeIndexID;}
 
  /** Never called.  Disable default constructor
   * @generated */
  protected Sentence() {}
    
  /** Internal - constructor used by generator 
   * @generated */
  public Sentence(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated */
  public Sentence(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated */  
  public Sentence(JCas jcas, int begin, int end) {
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
  //* Feature: wordArray

  /** getter for wordArray - gets 
   * @generated */
  public FSArray getWordArray() {
    if (Sentence_Type.featOkTst && ((Sentence_Type)jcasType).casFeat_wordArray == null)
      jcasType.jcas.throwFeatMissing("wordArray", "example.types.source.Sentence");
    return (FSArray)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((Sentence_Type)jcasType).casFeatCode_wordArray)));}
    
  /** setter for wordArray - sets  
   * @generated */
  public void setWordArray(FSArray v) {
    if (Sentence_Type.featOkTst && ((Sentence_Type)jcasType).casFeat_wordArray == null)
      jcasType.jcas.throwFeatMissing("wordArray", "example.types.source.Sentence");
    jcasType.ll_cas.ll_setRefValue(addr, ((Sentence_Type)jcasType).casFeatCode_wordArray, jcasType.ll_cas.ll_getFSRef(v));}    
    
  /** indexed getter for wordArray - gets an indexed value - 
   * @generated */
  public Word getWordArray(int i) {
    if (Sentence_Type.featOkTst && ((Sentence_Type)jcasType).casFeat_wordArray == null)
      jcasType.jcas.throwFeatMissing("wordArray", "example.types.source.Sentence");
    jcasType.jcas.checkArrayBounds(jcasType.ll_cas.ll_getRefValue(addr, ((Sentence_Type)jcasType).casFeatCode_wordArray), i);
    return (Word)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefArrayValue(jcasType.ll_cas.ll_getRefValue(addr, ((Sentence_Type)jcasType).casFeatCode_wordArray), i)));}

  /** indexed setter for wordArray - sets an indexed value - 
   * @generated */
  public void setWordArray(int i, Word v) { 
    if (Sentence_Type.featOkTst && ((Sentence_Type)jcasType).casFeat_wordArray == null)
      jcasType.jcas.throwFeatMissing("wordArray", "example.types.source.Sentence");
    jcasType.jcas.checkArrayBounds(jcasType.ll_cas.ll_getRefValue(addr, ((Sentence_Type)jcasType).casFeatCode_wordArray), i);
    jcasType.ll_cas.ll_setRefArrayValue(jcasType.ll_cas.ll_getRefValue(addr, ((Sentence_Type)jcasType).casFeatCode_wordArray), i, jcasType.ll_cas.ll_getFSRef(v));}
   
    
  //*--------------*
  //* Feature: stringArray

  /** getter for stringArray - gets 
   * @generated */
  public StringArray getStringArray() {
    if (Sentence_Type.featOkTst && ((Sentence_Type)jcasType).casFeat_stringArray == null)
      jcasType.jcas.throwFeatMissing("stringArray", "example.types.source.Sentence");
    return (StringArray)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((Sentence_Type)jcasType).casFeatCode_stringArray)));}
    
  /** setter for stringArray - sets  
   * @generated */
  public void setStringArray(StringArray v) {
    if (Sentence_Type.featOkTst && ((Sentence_Type)jcasType).casFeat_stringArray == null)
      jcasType.jcas.throwFeatMissing("stringArray", "example.types.source.Sentence");
    jcasType.ll_cas.ll_setRefValue(addr, ((Sentence_Type)jcasType).casFeatCode_stringArray, jcasType.ll_cas.ll_getFSRef(v));}    
    
  /** indexed getter for stringArray - gets an indexed value - 
   * @generated */
  public String getStringArray(int i) {
    if (Sentence_Type.featOkTst && ((Sentence_Type)jcasType).casFeat_stringArray == null)
      jcasType.jcas.throwFeatMissing("stringArray", "example.types.source.Sentence");
    jcasType.jcas.checkArrayBounds(jcasType.ll_cas.ll_getRefValue(addr, ((Sentence_Type)jcasType).casFeatCode_stringArray), i);
    return jcasType.ll_cas.ll_getStringArrayValue(jcasType.ll_cas.ll_getRefValue(addr, ((Sentence_Type)jcasType).casFeatCode_stringArray), i);}

  /** indexed setter for stringArray - sets an indexed value - 
   * @generated */
  public void setStringArray(int i, String v) { 
    if (Sentence_Type.featOkTst && ((Sentence_Type)jcasType).casFeat_stringArray == null)
      jcasType.jcas.throwFeatMissing("stringArray", "example.types.source.Sentence");
    jcasType.jcas.checkArrayBounds(jcasType.ll_cas.ll_getRefValue(addr, ((Sentence_Type)jcasType).casFeatCode_stringArray), i);
    jcasType.ll_cas.ll_setStringArrayValue(jcasType.ll_cas.ll_getRefValue(addr, ((Sentence_Type)jcasType).casFeatCode_stringArray), i, v);}
  }

    