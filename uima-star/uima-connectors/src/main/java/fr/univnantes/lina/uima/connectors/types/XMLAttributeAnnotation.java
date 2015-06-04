

/* First created by JCasGen Fri Oct 14 15:49:54 CEST 2011 */
package fr.univnantes.lina.uima.connectors.types;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import org.apache.uima.jcas.tcas.Annotation;


/** 
 * Updated by JCasGen Mon Mar 25 21:53:34 CET 2013
 * XML source: /media/MyPassport/workspace/uima-connectors/desc/connectors/corpus/P7T/P7TPlusTagged-XML2CAS-mapTo-sw+mw-WST+SPL-ViewWriter.xml
 * @generated */
public class XMLAttributeAnnotation extends Annotation {
  /** @generated
   * @ordered 
   */
  public final static int typeIndexID = JCasRegistry.register(XMLAttributeAnnotation.class);
  /** @generated
   * @ordered 
   */
  public final static int type = typeIndexID;
  /** @generated  */
  public              int getTypeIndexID() {return typeIndexID;}
 
  /** Never called.  Disable default constructor
   * @generated */
  protected XMLAttributeAnnotation() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated */
  public XMLAttributeAnnotation(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated */
  public XMLAttributeAnnotation(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated */  
  public XMLAttributeAnnotation(JCas jcas, int begin, int end) {
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
  //* Feature: uri

  /** getter for uri - gets 
   * @generated */
  public String getUri() {
    if (XMLAttributeAnnotation_Type.featOkTst && ((XMLAttributeAnnotation_Type)jcasType).casFeat_uri == null)
      jcasType.jcas.throwFeatMissing("uri", "fr.univnantes.lina.uima.connectors.types.XMLAttributeAnnotation");
    return jcasType.ll_cas.ll_getStringValue(addr, ((XMLAttributeAnnotation_Type)jcasType).casFeatCode_uri);}
    
  /** setter for uri - sets  
   * @generated */
  public void setUri(String v) {
    if (XMLAttributeAnnotation_Type.featOkTst && ((XMLAttributeAnnotation_Type)jcasType).casFeat_uri == null)
      jcasType.jcas.throwFeatMissing("uri", "fr.univnantes.lina.uima.connectors.types.XMLAttributeAnnotation");
    jcasType.ll_cas.ll_setStringValue(addr, ((XMLAttributeAnnotation_Type)jcasType).casFeatCode_uri, v);}    
   
    
  //*--------------*
  //* Feature: qualifiedName

  /** getter for qualifiedName - gets 
   * @generated */
  public String getQualifiedName() {
    if (XMLAttributeAnnotation_Type.featOkTst && ((XMLAttributeAnnotation_Type)jcasType).casFeat_qualifiedName == null)
      jcasType.jcas.throwFeatMissing("qualifiedName", "fr.univnantes.lina.uima.connectors.types.XMLAttributeAnnotation");
    return jcasType.ll_cas.ll_getStringValue(addr, ((XMLAttributeAnnotation_Type)jcasType).casFeatCode_qualifiedName);}
    
  /** setter for qualifiedName - sets  
   * @generated */
  public void setQualifiedName(String v) {
    if (XMLAttributeAnnotation_Type.featOkTst && ((XMLAttributeAnnotation_Type)jcasType).casFeat_qualifiedName == null)
      jcasType.jcas.throwFeatMissing("qualifiedName", "fr.univnantes.lina.uima.connectors.types.XMLAttributeAnnotation");
    jcasType.ll_cas.ll_setStringValue(addr, ((XMLAttributeAnnotation_Type)jcasType).casFeatCode_qualifiedName, v);}    
   
    
  //*--------------*
  //* Feature: localName

  /** getter for localName - gets 
   * @generated */
  public String getLocalName() {
    if (XMLAttributeAnnotation_Type.featOkTst && ((XMLAttributeAnnotation_Type)jcasType).casFeat_localName == null)
      jcasType.jcas.throwFeatMissing("localName", "fr.univnantes.lina.uima.connectors.types.XMLAttributeAnnotation");
    return jcasType.ll_cas.ll_getStringValue(addr, ((XMLAttributeAnnotation_Type)jcasType).casFeatCode_localName);}
    
  /** setter for localName - sets  
   * @generated */
  public void setLocalName(String v) {
    if (XMLAttributeAnnotation_Type.featOkTst && ((XMLAttributeAnnotation_Type)jcasType).casFeat_localName == null)
      jcasType.jcas.throwFeatMissing("localName", "fr.univnantes.lina.uima.connectors.types.XMLAttributeAnnotation");
    jcasType.ll_cas.ll_setStringValue(addr, ((XMLAttributeAnnotation_Type)jcasType).casFeatCode_localName, v);}    
   
    
  //*--------------*
  //* Feature: value

  /** getter for value - gets 
   * @generated */
  public String getValue() {
    if (XMLAttributeAnnotation_Type.featOkTst && ((XMLAttributeAnnotation_Type)jcasType).casFeat_value == null)
      jcasType.jcas.throwFeatMissing("value", "fr.univnantes.lina.uima.connectors.types.XMLAttributeAnnotation");
    return jcasType.ll_cas.ll_getStringValue(addr, ((XMLAttributeAnnotation_Type)jcasType).casFeatCode_value);}
    
  /** setter for value - sets  
   * @generated */
  public void setValue(String v) {
    if (XMLAttributeAnnotation_Type.featOkTst && ((XMLAttributeAnnotation_Type)jcasType).casFeat_value == null)
      jcasType.jcas.throwFeatMissing("value", "fr.univnantes.lina.uima.connectors.types.XMLAttributeAnnotation");
    jcasType.ll_cas.ll_setStringValue(addr, ((XMLAttributeAnnotation_Type)jcasType).casFeatCode_value, v);}    
   
    
  //*--------------*
  //* Feature: element

  /** getter for element - gets 
   * @generated */
  public XMLElementAnnotation getElement() {
    if (XMLAttributeAnnotation_Type.featOkTst && ((XMLAttributeAnnotation_Type)jcasType).casFeat_element == null)
      jcasType.jcas.throwFeatMissing("element", "fr.univnantes.lina.uima.connectors.types.XMLAttributeAnnotation");
    return (XMLElementAnnotation)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((XMLAttributeAnnotation_Type)jcasType).casFeatCode_element)));}
    
  /** setter for element - sets  
   * @generated */
  public void setElement(XMLElementAnnotation v) {
    if (XMLAttributeAnnotation_Type.featOkTst && ((XMLAttributeAnnotation_Type)jcasType).casFeat_element == null)
      jcasType.jcas.throwFeatMissing("element", "fr.univnantes.lina.uima.connectors.types.XMLAttributeAnnotation");
    jcasType.ll_cas.ll_setRefValue(addr, ((XMLAttributeAnnotation_Type)jcasType).casFeatCode_element, jcasType.ll_cas.ll_getFSRef(v));}    
  }

    