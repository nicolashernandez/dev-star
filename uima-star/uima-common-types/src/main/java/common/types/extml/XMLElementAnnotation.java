

/* First created by JCasGen Wed May 27 22:41:00 CEST 2015 */
package common.types.extml;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import org.apache.uima.jcas.cas.FSArray;
import org.apache.uima.jcas.tcas.Annotation;


/** 
 * Updated by JCasGen Wed May 27 22:41:00 CEST 2015
 * XML source: /media/hernandez-n/ext4/workspace/lina-star/uima-star/uima-common-types/src/main/resources/common/types/commonTS.xml
 * @generated */
public class XMLElementAnnotation extends Annotation {
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = JCasRegistry.register(XMLElementAnnotation.class);
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
  protected XMLElementAnnotation() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated
   * @param addr low level Feature Structure reference
   * @param type the type of this Feature Structure 
   */
  public XMLElementAnnotation(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated
   * @param jcas JCas to which this Feature Structure belongs 
   */
  public XMLElementAnnotation(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated
   * @param jcas JCas to which this Feature Structure belongs
   * @param begin offset to the begin spot in the SofA
   * @param end offset to the end spot in the SofA 
  */  
  public XMLElementAnnotation(JCas jcas, int begin, int end) {
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
    if (XMLElementAnnotation_Type.featOkTst && ((XMLElementAnnotation_Type)jcasType).casFeat_uri == null)
      jcasType.jcas.throwFeatMissing("uri", "common.types.extml.XMLElementAnnotation");
    return jcasType.ll_cas.ll_getStringValue(addr, ((XMLElementAnnotation_Type)jcasType).casFeatCode_uri);}
    
  /** setter for uri - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setUri(String v) {
    if (XMLElementAnnotation_Type.featOkTst && ((XMLElementAnnotation_Type)jcasType).casFeat_uri == null)
      jcasType.jcas.throwFeatMissing("uri", "common.types.extml.XMLElementAnnotation");
    jcasType.ll_cas.ll_setStringValue(addr, ((XMLElementAnnotation_Type)jcasType).casFeatCode_uri, v);}    
   
    
  //*--------------*
  //* Feature: qualifiedName

  /** getter for qualifiedName - gets 
   * @generated
   * @return value of the feature 
   */
  public String getQualifiedName() {
    if (XMLElementAnnotation_Type.featOkTst && ((XMLElementAnnotation_Type)jcasType).casFeat_qualifiedName == null)
      jcasType.jcas.throwFeatMissing("qualifiedName", "common.types.extml.XMLElementAnnotation");
    return jcasType.ll_cas.ll_getStringValue(addr, ((XMLElementAnnotation_Type)jcasType).casFeatCode_qualifiedName);}
    
  /** setter for qualifiedName - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setQualifiedName(String v) {
    if (XMLElementAnnotation_Type.featOkTst && ((XMLElementAnnotation_Type)jcasType).casFeat_qualifiedName == null)
      jcasType.jcas.throwFeatMissing("qualifiedName", "common.types.extml.XMLElementAnnotation");
    jcasType.ll_cas.ll_setStringValue(addr, ((XMLElementAnnotation_Type)jcasType).casFeatCode_qualifiedName, v);}    
   
    
  //*--------------*
  //* Feature: localName

  /** getter for localName - gets 
   * @generated
   * @return value of the feature 
   */
  public String getLocalName() {
    if (XMLElementAnnotation_Type.featOkTst && ((XMLElementAnnotation_Type)jcasType).casFeat_localName == null)
      jcasType.jcas.throwFeatMissing("localName", "common.types.extml.XMLElementAnnotation");
    return jcasType.ll_cas.ll_getStringValue(addr, ((XMLElementAnnotation_Type)jcasType).casFeatCode_localName);}
    
  /** setter for localName - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setLocalName(String v) {
    if (XMLElementAnnotation_Type.featOkTst && ((XMLElementAnnotation_Type)jcasType).casFeat_localName == null)
      jcasType.jcas.throwFeatMissing("localName", "common.types.extml.XMLElementAnnotation");
    jcasType.ll_cas.ll_setStringValue(addr, ((XMLElementAnnotation_Type)jcasType).casFeatCode_localName, v);}    
   
    
  //*--------------*
  //* Feature: children

  /** getter for children - gets 
   * @generated
   * @return value of the feature 
   */
  public FSArray getChildren() {
    if (XMLElementAnnotation_Type.featOkTst && ((XMLElementAnnotation_Type)jcasType).casFeat_children == null)
      jcasType.jcas.throwFeatMissing("children", "common.types.extml.XMLElementAnnotation");
    return (FSArray)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((XMLElementAnnotation_Type)jcasType).casFeatCode_children)));}
    
  /** setter for children - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setChildren(FSArray v) {
    if (XMLElementAnnotation_Type.featOkTst && ((XMLElementAnnotation_Type)jcasType).casFeat_children == null)
      jcasType.jcas.throwFeatMissing("children", "common.types.extml.XMLElementAnnotation");
    jcasType.ll_cas.ll_setRefValue(addr, ((XMLElementAnnotation_Type)jcasType).casFeatCode_children, jcasType.ll_cas.ll_getFSRef(v));}    
    
  /** indexed getter for children - gets an indexed value - 
   * @generated
   * @param i index in the array to get
   * @return value of the element at index i 
   */
  public XMLElementAnnotation getChildren(int i) {
    if (XMLElementAnnotation_Type.featOkTst && ((XMLElementAnnotation_Type)jcasType).casFeat_children == null)
      jcasType.jcas.throwFeatMissing("children", "common.types.extml.XMLElementAnnotation");
    jcasType.jcas.checkArrayBounds(jcasType.ll_cas.ll_getRefValue(addr, ((XMLElementAnnotation_Type)jcasType).casFeatCode_children), i);
    return (XMLElementAnnotation)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefArrayValue(jcasType.ll_cas.ll_getRefValue(addr, ((XMLElementAnnotation_Type)jcasType).casFeatCode_children), i)));}

  /** indexed setter for children - sets an indexed value - 
   * @generated
   * @param i index in the array to set
   * @param v value to set into the array 
   */
  public void setChildren(int i, XMLElementAnnotation v) { 
    if (XMLElementAnnotation_Type.featOkTst && ((XMLElementAnnotation_Type)jcasType).casFeat_children == null)
      jcasType.jcas.throwFeatMissing("children", "common.types.extml.XMLElementAnnotation");
    jcasType.jcas.checkArrayBounds(jcasType.ll_cas.ll_getRefValue(addr, ((XMLElementAnnotation_Type)jcasType).casFeatCode_children), i);
    jcasType.ll_cas.ll_setRefArrayValue(jcasType.ll_cas.ll_getRefValue(addr, ((XMLElementAnnotation_Type)jcasType).casFeatCode_children), i, jcasType.ll_cas.ll_getFSRef(v));}
   
    
  //*--------------*
  //* Feature: parent

  /** getter for parent - gets 
   * @generated
   * @return value of the feature 
   */
  public XMLElementAnnotation getParent() {
    if (XMLElementAnnotation_Type.featOkTst && ((XMLElementAnnotation_Type)jcasType).casFeat_parent == null)
      jcasType.jcas.throwFeatMissing("parent", "common.types.extml.XMLElementAnnotation");
    return (XMLElementAnnotation)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((XMLElementAnnotation_Type)jcasType).casFeatCode_parent)));}
    
  /** setter for parent - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setParent(XMLElementAnnotation v) {
    if (XMLElementAnnotation_Type.featOkTst && ((XMLElementAnnotation_Type)jcasType).casFeat_parent == null)
      jcasType.jcas.throwFeatMissing("parent", "common.types.extml.XMLElementAnnotation");
    jcasType.ll_cas.ll_setRefValue(addr, ((XMLElementAnnotation_Type)jcasType).casFeatCode_parent, jcasType.ll_cas.ll_getFSRef(v));}    
   
    
  //*--------------*
  //* Feature: attributes

  /** getter for attributes - gets 
   * @generated
   * @return value of the feature 
   */
  public FSArray getAttributes() {
    if (XMLElementAnnotation_Type.featOkTst && ((XMLElementAnnotation_Type)jcasType).casFeat_attributes == null)
      jcasType.jcas.throwFeatMissing("attributes", "common.types.extml.XMLElementAnnotation");
    return (FSArray)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((XMLElementAnnotation_Type)jcasType).casFeatCode_attributes)));}
    
  /** setter for attributes - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setAttributes(FSArray v) {
    if (XMLElementAnnotation_Type.featOkTst && ((XMLElementAnnotation_Type)jcasType).casFeat_attributes == null)
      jcasType.jcas.throwFeatMissing("attributes", "common.types.extml.XMLElementAnnotation");
    jcasType.ll_cas.ll_setRefValue(addr, ((XMLElementAnnotation_Type)jcasType).casFeatCode_attributes, jcasType.ll_cas.ll_getFSRef(v));}    
    
  /** indexed getter for attributes - gets an indexed value - 
   * @generated
   * @param i index in the array to get
   * @return value of the element at index i 
   */
  public XMLAttributeAnnotation getAttributes(int i) {
    if (XMLElementAnnotation_Type.featOkTst && ((XMLElementAnnotation_Type)jcasType).casFeat_attributes == null)
      jcasType.jcas.throwFeatMissing("attributes", "common.types.extml.XMLElementAnnotation");
    jcasType.jcas.checkArrayBounds(jcasType.ll_cas.ll_getRefValue(addr, ((XMLElementAnnotation_Type)jcasType).casFeatCode_attributes), i);
    return (XMLAttributeAnnotation)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefArrayValue(jcasType.ll_cas.ll_getRefValue(addr, ((XMLElementAnnotation_Type)jcasType).casFeatCode_attributes), i)));}

  /** indexed setter for attributes - sets an indexed value - 
   * @generated
   * @param i index in the array to set
   * @param v value to set into the array 
   */
  public void setAttributes(int i, XMLAttributeAnnotation v) { 
    if (XMLElementAnnotation_Type.featOkTst && ((XMLElementAnnotation_Type)jcasType).casFeat_attributes == null)
      jcasType.jcas.throwFeatMissing("attributes", "common.types.extml.XMLElementAnnotation");
    jcasType.jcas.checkArrayBounds(jcasType.ll_cas.ll_getRefValue(addr, ((XMLElementAnnotation_Type)jcasType).casFeatCode_attributes), i);
    jcasType.ll_cas.ll_setRefArrayValue(jcasType.ll_cas.ll_getRefValue(addr, ((XMLElementAnnotation_Type)jcasType).casFeatCode_attributes), i, jcasType.ll_cas.ll_getFSRef(v));}
  }

    