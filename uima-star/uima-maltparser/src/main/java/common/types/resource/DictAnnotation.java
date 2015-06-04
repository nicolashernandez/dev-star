

/* First created by JCasGen Thu May 30 11:49:30 CEST 2013 */
package common.types.resource;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import common.types.text.Annotation;
import org.apache.uima.jcas.cas.StringArray;


/** 
 * Updated by JCasGen Thu May 30 11:49:30 CEST 2013
 * XML source: /media/ext4/workspace/uima-maltparser/desc/maltParser/wst-openNLPPOSTagger-maltparser.AAE.xml
 * @generated */
public class DictAnnotation extends Annotation {
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = JCasRegistry.register(DictAnnotation.class);
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
  protected DictAnnotation() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated */
  public DictAnnotation(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated */
  public DictAnnotation(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated */  
  public DictAnnotation(JCas jcas, int begin, int end) {
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
  //* Feature: values

  /** getter for values - gets 
   * @generated */
  public StringArray getValues() {
    if (DictAnnotation_Type.featOkTst && ((DictAnnotation_Type)jcasType).casFeat_values == null)
      jcasType.jcas.throwFeatMissing("values", "common.types.resource.DictAnnotation");
    return (StringArray)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((DictAnnotation_Type)jcasType).casFeatCode_values)));}
    
  /** setter for values - sets  
   * @generated */
  public void setValues(StringArray v) {
    if (DictAnnotation_Type.featOkTst && ((DictAnnotation_Type)jcasType).casFeat_values == null)
      jcasType.jcas.throwFeatMissing("values", "common.types.resource.DictAnnotation");
    jcasType.ll_cas.ll_setRefValue(addr, ((DictAnnotation_Type)jcasType).casFeatCode_values, jcasType.ll_cas.ll_getFSRef(v));}    
    
  /** indexed getter for values - gets an indexed value - 
   * @generated */
  public String getValues(int i) {
    if (DictAnnotation_Type.featOkTst && ((DictAnnotation_Type)jcasType).casFeat_values == null)
      jcasType.jcas.throwFeatMissing("values", "common.types.resource.DictAnnotation");
    jcasType.jcas.checkArrayBounds(jcasType.ll_cas.ll_getRefValue(addr, ((DictAnnotation_Type)jcasType).casFeatCode_values), i);
    return jcasType.ll_cas.ll_getStringArrayValue(jcasType.ll_cas.ll_getRefValue(addr, ((DictAnnotation_Type)jcasType).casFeatCode_values), i);}

  /** indexed setter for values - sets an indexed value - 
   * @generated */
  public void setValues(int i, String v) { 
    if (DictAnnotation_Type.featOkTst && ((DictAnnotation_Type)jcasType).casFeat_values == null)
      jcasType.jcas.throwFeatMissing("values", "common.types.resource.DictAnnotation");
    jcasType.jcas.checkArrayBounds(jcasType.ll_cas.ll_getRefValue(addr, ((DictAnnotation_Type)jcasType).casFeatCode_values), i);
    jcasType.ll_cas.ll_setStringArrayValue(jcasType.ll_cas.ll_getRefValue(addr, ((DictAnnotation_Type)jcasType).casFeatCode_values), i, v);}
   
    
  //*--------------*
  //* Feature: value

  /** getter for value - gets 
   * @generated */
  public String getValue() {
    if (DictAnnotation_Type.featOkTst && ((DictAnnotation_Type)jcasType).casFeat_value == null)
      jcasType.jcas.throwFeatMissing("value", "common.types.resource.DictAnnotation");
    return jcasType.ll_cas.ll_getStringValue(addr, ((DictAnnotation_Type)jcasType).casFeatCode_value);}
    
  /** setter for value - sets  
   * @generated */
  public void setValue(String v) {
    if (DictAnnotation_Type.featOkTst && ((DictAnnotation_Type)jcasType).casFeat_value == null)
      jcasType.jcas.throwFeatMissing("value", "common.types.resource.DictAnnotation");
    jcasType.ll_cas.ll_setStringValue(addr, ((DictAnnotation_Type)jcasType).casFeatCode_value, v);}    
  }

    