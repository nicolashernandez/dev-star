

/* First created by JCasGen Thu Apr 15 15:16:12 CEST 2010 */
package example.types.source;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import org.apache.uima.jcas.tcas.Annotation;


/** Single token annotation
 * Updated by JCasGen Sun Oct 03 09:51:22 CEST 2010
 * XML source: /media/MyPassport/current/public/research/UIMA-USER-DEV-ENV/workspace/uima-type-mapper/desc/example/wst-tagger-preTypeMapper-typeMapper-postTypeMapper-AAE.xml
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
  //* Feature: posString

  /** getter for posString - gets contains part-of-speech of a
								corresponding token
   * @generated */
  public String getPosString() {
    if (Word_Type.featOkTst && ((Word_Type)jcasType).casFeat_posString == null)
      jcasType.jcas.throwFeatMissing("posString", "example.types.source.Word");
    return jcasType.ll_cas.ll_getStringValue(addr, ((Word_Type)jcasType).casFeatCode_posString);}
    
  /** setter for posString - sets contains part-of-speech of a
								corresponding token 
   * @generated */
  public void setPosString(String v) {
    if (Word_Type.featOkTst && ((Word_Type)jcasType).casFeat_posString == null)
      jcasType.jcas.throwFeatMissing("posString", "example.types.source.Word");
    jcasType.ll_cas.ll_setStringValue(addr, ((Word_Type)jcasType).casFeatCode_posString, v);}    
   
    
  //*--------------*
  //* Feature: posType

  /** getter for posType - gets 
   * @generated */
  public POS getPosType() {
    if (Word_Type.featOkTst && ((Word_Type)jcasType).casFeat_posType == null)
      jcasType.jcas.throwFeatMissing("posType", "example.types.source.Word");
    return (POS)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((Word_Type)jcasType).casFeatCode_posType)));}
    
  /** setter for posType - sets  
   * @generated */
  public void setPosType(POS v) {
    if (Word_Type.featOkTst && ((Word_Type)jcasType).casFeat_posType == null)
      jcasType.jcas.throwFeatMissing("posType", "example.types.source.Word");
    jcasType.ll_cas.ll_setRefValue(addr, ((Word_Type)jcasType).casFeatCode_posType, jcasType.ll_cas.ll_getFSRef(v));}    
   
    
  //*--------------*
  //* Feature: sizeInt

  /** getter for sizeInt - gets 
   * @generated */
  public int getSizeInt() {
    if (Word_Type.featOkTst && ((Word_Type)jcasType).casFeat_sizeInt == null)
      jcasType.jcas.throwFeatMissing("sizeInt", "example.types.source.Word");
    return jcasType.ll_cas.ll_getIntValue(addr, ((Word_Type)jcasType).casFeatCode_sizeInt);}
    
  /** setter for sizeInt - sets  
   * @generated */
  public void setSizeInt(int v) {
    if (Word_Type.featOkTst && ((Word_Type)jcasType).casFeat_sizeInt == null)
      jcasType.jcas.throwFeatMissing("sizeInt", "example.types.source.Word");
    jcasType.ll_cas.ll_setIntValue(addr, ((Word_Type)jcasType).casFeatCode_sizeInt, v);}    
  }

    