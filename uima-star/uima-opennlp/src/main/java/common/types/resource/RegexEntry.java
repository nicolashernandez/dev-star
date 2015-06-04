

/* First created by JCasGen Sat Dec 10 00:59:10 CET 2011 */
package common.types.resource;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import common.types.text.Annotation;
import org.apache.uima.jcas.cas.StringArray;


/** 
 * Updated by JCasGen Sat Dec 10 00:59:12 CET 2011
 * XML source: /media/MyPassport/current/public/research/UIMA-USER-DEV-ENV/workspace/demo-apache-opennlp-uima/desc/opennlp/fr/SentenceDetectorAE.xml
 * @generated */
public class RegexEntry extends Annotation {
  /** @generated
   * @ordered 
   */
  public final static int typeIndexID = JCasRegistry.register(RegexEntry.class);
  /** @generated
   * @ordered 
   */
  public final static int type = typeIndexID;
  /** @generated  */
  public              int getTypeIndexID() {return typeIndexID;}
 
  /** Never called.  Disable default constructor
   * @generated */
  protected RegexEntry() {}
    
  /** Internal - constructor used by generator 
   * @generated */
  public RegexEntry(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated */
  public RegexEntry(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated */  
  public RegexEntry(JCas jcas, int begin, int end) {
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
  //* Feature: values

  /** getter for values - gets 
   * @generated */
  public StringArray getValues() {
    if (RegexEntry_Type.featOkTst && ((RegexEntry_Type)jcasType).casFeat_values == null)
      jcasType.jcas.throwFeatMissing("values", "common.types.resource.RegexEntry");
    return (StringArray)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((RegexEntry_Type)jcasType).casFeatCode_values)));}
    
  /** setter for values - sets  
   * @generated */
  public void setValues(StringArray v) {
    if (RegexEntry_Type.featOkTst && ((RegexEntry_Type)jcasType).casFeat_values == null)
      jcasType.jcas.throwFeatMissing("values", "common.types.resource.RegexEntry");
    jcasType.ll_cas.ll_setRefValue(addr, ((RegexEntry_Type)jcasType).casFeatCode_values, jcasType.ll_cas.ll_getFSRef(v));}    
    
  /** indexed getter for values - gets an indexed value - 
   * @generated */
  public String getValues(int i) {
    if (RegexEntry_Type.featOkTst && ((RegexEntry_Type)jcasType).casFeat_values == null)
      jcasType.jcas.throwFeatMissing("values", "common.types.resource.RegexEntry");
    jcasType.jcas.checkArrayBounds(jcasType.ll_cas.ll_getRefValue(addr, ((RegexEntry_Type)jcasType).casFeatCode_values), i);
    return jcasType.ll_cas.ll_getStringArrayValue(jcasType.ll_cas.ll_getRefValue(addr, ((RegexEntry_Type)jcasType).casFeatCode_values), i);}

  /** indexed setter for values - sets an indexed value - 
   * @generated */
  public void setValues(int i, String v) { 
    if (RegexEntry_Type.featOkTst && ((RegexEntry_Type)jcasType).casFeat_values == null)
      jcasType.jcas.throwFeatMissing("values", "common.types.resource.RegexEntry");
    jcasType.jcas.checkArrayBounds(jcasType.ll_cas.ll_getRefValue(addr, ((RegexEntry_Type)jcasType).casFeatCode_values), i);
    jcasType.ll_cas.ll_setStringArrayValue(jcasType.ll_cas.ll_getRefValue(addr, ((RegexEntry_Type)jcasType).casFeatCode_values), i, v);}
   
    
  //*--------------*
  //* Feature: value

  /** getter for value - gets 
   * @generated */
  public String getValue() {
    if (RegexEntry_Type.featOkTst && ((RegexEntry_Type)jcasType).casFeat_value == null)
      jcasType.jcas.throwFeatMissing("value", "common.types.resource.RegexEntry");
    return jcasType.ll_cas.ll_getStringValue(addr, ((RegexEntry_Type)jcasType).casFeatCode_value);}
    
  /** setter for value - sets  
   * @generated */
  public void setValue(String v) {
    if (RegexEntry_Type.featOkTst && ((RegexEntry_Type)jcasType).casFeat_value == null)
      jcasType.jcas.throwFeatMissing("value", "common.types.resource.RegexEntry");
    jcasType.ll_cas.ll_setStringValue(addr, ((RegexEntry_Type)jcasType).casFeatCode_value, v);}    
  }

    