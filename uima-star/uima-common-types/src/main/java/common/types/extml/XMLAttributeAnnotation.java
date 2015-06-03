

/* First created by JCasGen Wed May 27 22:41:00 CEST 2015 */
package common.types.extml;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import org.apache.uima.jcas.tcas.Annotation;


/** 
 * Updated by JCasGen Wed May 27 22:41:00 CEST 2015
 * XML source: /media/hernandez-n/ext4/workspace/lina-star/uima-star/uima-common-types/src/main/resources/common/types/commonTS.xml
 * @generated */
public class XMLAttributeAnnotation extends Annotation {
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = JCasRegistry.register(XMLAttributeAnnotation.class);
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
  protected XMLAttributeAnnotation() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated
   * @param addr low level Feature Structure reference
   * @param type the type of this Feature Structure 
   */
  public XMLAttributeAnnotation(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated
   * @param jcas JCas to which this Feature Structure belongs 
   */
  public XMLAttributeAnnotation(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated
   * @param jcas JCas to which this Feature Structure belongs
   * @param begin offset to the begin spot in the SofA
   * @param end offset to the end spot in the SofA 
  */  
  public XMLAttributeAnnotation(JCas jcas, int begin, int end) {
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
  //* Feature: uri

  /** getter for uri - gets 
   * @generated
   * @return value of the feature 
   */
  public String getUri() {
    if (XMLAttributeAnnotation_Type.featOkTst && ((XMLAttributeAnnotation_Type)jcasType).casFeat_uri == null)
      jcasType.jcas.throwFeatMissing("uri", "common.types.extml.XMLAttributeAnnotation");
    return jcasType.ll_cas.ll_getStringValue(addr, ((XMLAttributeAnnotation_Type)jcasType).casFeatCode_uri);}
    
  /** setter for uri - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setUri(String v) {
    if (XMLAttributeAnnotation_Type.featOkTst && ((XMLAttributeAnnotation_Type)jcasType).casFeat_uri == null)
      jcasType.jcas.throwFeatMissing("uri", "common.types.extml.XMLAttributeAnnotation");
    jcasType.ll_cas.ll_setStringValue(addr, ((XMLAttributeAnnotation_Type)jcasType).casFeatCode_uri, v);}    
   
    
  //*--------------*
  //* Feature: qualifiedName

  /** getter for qualifiedName - gets 
   * @generated
   * @return value of the feature 
   */
  public String getQualifiedName() {
    if (XMLAttributeAnnotation_Type.featOkTst && ((XMLAttributeAnnotation_Type)jcasType).casFeat_qualifiedName == null)
      jcasType.jcas.throwFeatMissing("qualifiedName", "common.types.extml.XMLAttributeAnnotation");
    return jcasType.ll_cas.ll_getStringValue(addr, ((XMLAttributeAnnotation_Type)jcasType).casFeatCode_qualifiedName);}
    
  /** setter for qualifiedName - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setQualifiedName(String v) {
    if (XMLAttributeAnnotation_Type.featOkTst && ((XMLAttributeAnnotation_Type)jcasType).casFeat_qualifiedName == null)
      jcasType.jcas.throwFeatMissing("qualifiedName", "common.types.extml.XMLAttributeAnnotation");
    jcasType.ll_cas.ll_setStringValue(addr, ((XMLAttributeAnnotation_Type)jcasType).casFeatCode_qualifiedName, v);}    
   
    
  //*--------------*
  //* Feature: localName

  /** getter for localName - gets 
   * @generated
   * @return value of the feature 
   */
  public String getLocalName() {
    if (XMLAttributeAnnotation_Type.featOkTst && ((XMLAttributeAnnotation_Type)jcasType).casFeat_localName == null)
      jcasType.jcas.throwFeatMissing("localName", "common.types.extml.XMLAttributeAnnotation");
    return jcasType.ll_cas.ll_getStringValue(addr, ((XMLAttributeAnnotation_Type)jcasType).casFeatCode_localName);}
    
  /** setter for localName - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setLocalName(String v) {
    if (XMLAttributeAnnotation_Type.featOkTst && ((XMLAttributeAnnotation_Type)jcasType).casFeat_localName == null)
      jcasType.jcas.throwFeatMissing("localName", "common.types.extml.XMLAttributeAnnotation");
    jcasType.ll_cas.ll_setStringValue(addr, ((XMLAttributeAnnotation_Type)jcasType).casFeatCode_localName, v);}    
   
    
  //*--------------*
  //* Feature: value

  /** getter for value - gets 
   * @generated
   * @return value of the feature 
   */
  public String getValue() {
    if (XMLAttributeAnnotation_Type.featOkTst && ((XMLAttributeAnnotation_Type)jcasType).casFeat_value == null)
      jcasType.jcas.throwFeatMissing("value", "common.types.extml.XMLAttributeAnnotation");
    return jcasType.ll_cas.ll_getStringValue(addr, ((XMLAttributeAnnotation_Type)jcasType).casFeatCode_value);}
    
  /** setter for value - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setValue(String v) {
    if (XMLAttributeAnnotation_Type.featOkTst && ((XMLAttributeAnnotation_Type)jcasType).casFeat_value == null)
      jcasType.jcas.throwFeatMissing("value", "common.types.extml.XMLAttributeAnnotation");
    jcasType.ll_cas.ll_setStringValue(addr, ((XMLAttributeAnnotation_Type)jcasType).casFeatCode_value, v);}    
   
    
  //*--------------*
  //* Feature: element

  /** getter for element - gets 
   * @generated
   * @return value of the feature 
   */
  public XMLElementAnnotation getElement() {
    if (XMLAttributeAnnotation_Type.featOkTst && ((XMLAttributeAnnotation_Type)jcasType).casFeat_element == null)
      jcasType.jcas.throwFeatMissing("element", "common.types.extml.XMLAttributeAnnotation");
    return (XMLElementAnnotation)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((XMLAttributeAnnotation_Type)jcasType).casFeatCode_element)));}
    
  /** setter for element - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setElement(XMLElementAnnotation v) {
    if (XMLAttributeAnnotation_Type.featOkTst && ((XMLAttributeAnnotation_Type)jcasType).casFeat_element == null)
      jcasType.jcas.throwFeatMissing("element", "common.types.extml.XMLAttributeAnnotation");
    jcasType.ll_cas.ll_setRefValue(addr, ((XMLAttributeAnnotation_Type)jcasType).casFeatCode_element, jcasType.ll_cas.ll_getFSRef(v));}    
  }

    