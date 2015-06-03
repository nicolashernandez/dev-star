

/* First created by JCasGen Wed May 27 22:41:00 CEST 2015 */
package common.types.coref;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import common.types.text.Annotation;


/** 
 * Updated by JCasGen Wed May 27 22:41:00 CEST 2015
 * XML source: /media/hernandez-n/ext4/workspace/lina-star/uima-star/uima-common-types/src/main/resources/common/types/commonTS.xml
 * @generated */
public class Coreference extends Annotation {
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = JCasRegistry.register(Coreference.class);
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int type = typeIndexID;
  /** @generated
   * @return index of the type  
   */
  @Override
  public              int getTypeIndexID() {return typeIndexID;}
 
  /** Never called.  Disable default constructor
   * @generated */
  protected Coreference() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated
   * @param addr low level Feature Structure reference
   * @param type the type of this Feature Structure 
   */
  public Coreference(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated
   * @param jcas JCas to which this Feature Structure belongs 
   */
  public Coreference(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated
   * @param jcas JCas to which this Feature Structure belongs
   * @param begin offset to the begin spot in the SofA
   * @param end offset to the end spot in the SofA 
  */  
  public Coreference(JCas jcas, int begin, int end) {
    super(jcas);
    setBegin(begin);
    setEnd(end);
    readObject();
  }   

  /** 
   * <!-- begin-user-doc -->
   * Write your own initialization here
   * <!-- end-user-doc -->
   *
   * @generated modifiable 
   */
  private void readObject() {/*default - does nothing empty block */}
     
 
    
  //*--------------*
  //* Feature: id

  /** getter for id - gets 
   * @generated
   * @return value of the feature 
   */
  public String getId() {
    if (Coreference_Type.featOkTst && ((Coreference_Type)jcasType).casFeat_id == null)
      jcasType.jcas.throwFeatMissing("id", "common.types.coref.Coreference");
    return jcasType.ll_cas.ll_getStringValue(addr, ((Coreference_Type)jcasType).casFeatCode_id);}
    
  /** setter for id - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setId(String v) {
    if (Coreference_Type.featOkTst && ((Coreference_Type)jcasType).casFeat_id == null)
      jcasType.jcas.throwFeatMissing("id", "common.types.coref.Coreference");
    jcasType.ll_cas.ll_setStringValue(addr, ((Coreference_Type)jcasType).casFeatCode_id, v);}    
   
    
  //*--------------*
  //* Feature: corefType

  /** getter for corefType - gets 
   * @generated
   * @return value of the feature 
   */
  public String getCorefType() {
    if (Coreference_Type.featOkTst && ((Coreference_Type)jcasType).casFeat_corefType == null)
      jcasType.jcas.throwFeatMissing("corefType", "common.types.coref.Coreference");
    return jcasType.ll_cas.ll_getStringValue(addr, ((Coreference_Type)jcasType).casFeatCode_corefType);}
    
  /** setter for corefType - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setCorefType(String v) {
    if (Coreference_Type.featOkTst && ((Coreference_Type)jcasType).casFeat_corefType == null)
      jcasType.jcas.throwFeatMissing("corefType", "common.types.coref.Coreference");
    jcasType.ll_cas.ll_setStringValue(addr, ((Coreference_Type)jcasType).casFeatCode_corefType, v);}    
   
    
  //*--------------*
  //* Feature: src

  /** getter for src - gets 
   * @generated
   * @return value of the feature 
   */
  public String getSrc() {
    if (Coreference_Type.featOkTst && ((Coreference_Type)jcasType).casFeat_src == null)
      jcasType.jcas.throwFeatMissing("src", "common.types.coref.Coreference");
    return jcasType.ll_cas.ll_getStringValue(addr, ((Coreference_Type)jcasType).casFeatCode_src);}
    
  /** setter for src - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setSrc(String v) {
    if (Coreference_Type.featOkTst && ((Coreference_Type)jcasType).casFeat_src == null)
      jcasType.jcas.throwFeatMissing("src", "common.types.coref.Coreference");
    jcasType.ll_cas.ll_setStringValue(addr, ((Coreference_Type)jcasType).casFeatCode_src, v);}    
   
    
  //*--------------*
  //* Feature: ptr

  /** getter for ptr - gets 
   * @generated
   * @return value of the feature 
   */
  public String getPtr() {
    if (Coreference_Type.featOkTst && ((Coreference_Type)jcasType).casFeat_ptr == null)
      jcasType.jcas.throwFeatMissing("ptr", "common.types.coref.Coreference");
    return jcasType.ll_cas.ll_getStringValue(addr, ((Coreference_Type)jcasType).casFeatCode_ptr);}
    
  /** setter for ptr - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setPtr(String v) {
    if (Coreference_Type.featOkTst && ((Coreference_Type)jcasType).casFeat_ptr == null)
      jcasType.jcas.throwFeatMissing("ptr", "common.types.coref.Coreference");
    jcasType.ll_cas.ll_setStringValue(addr, ((Coreference_Type)jcasType).casFeatCode_ptr, v);}    
   
    
  //*--------------*
  //* Feature: prev

  /** getter for prev - gets 
   * @generated
   * @return value of the feature 
   */
  public String getPrev() {
    if (Coreference_Type.featOkTst && ((Coreference_Type)jcasType).casFeat_prev == null)
      jcasType.jcas.throwFeatMissing("prev", "common.types.coref.Coreference");
    return jcasType.ll_cas.ll_getStringValue(addr, ((Coreference_Type)jcasType).casFeatCode_prev);}
    
  /** setter for prev - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setPrev(String v) {
    if (Coreference_Type.featOkTst && ((Coreference_Type)jcasType).casFeat_prev == null)
      jcasType.jcas.throwFeatMissing("prev", "common.types.coref.Coreference");
    jcasType.ll_cas.ll_setStringValue(addr, ((Coreference_Type)jcasType).casFeatCode_prev, v);}    
   
    
  //*--------------*
  //* Feature: next

  /** getter for next - gets 
   * @generated
   * @return value of the feature 
   */
  public String getNext() {
    if (Coreference_Type.featOkTst && ((Coreference_Type)jcasType).casFeat_next == null)
      jcasType.jcas.throwFeatMissing("next", "common.types.coref.Coreference");
    return jcasType.ll_cas.ll_getStringValue(addr, ((Coreference_Type)jcasType).casFeatCode_next);}
    
  /** setter for next - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setNext(String v) {
    if (Coreference_Type.featOkTst && ((Coreference_Type)jcasType).casFeat_next == null)
      jcasType.jcas.throwFeatMissing("next", "common.types.coref.Coreference");
    jcasType.ll_cas.ll_setStringValue(addr, ((Coreference_Type)jcasType).casFeatCode_next, v);}    
  }

    