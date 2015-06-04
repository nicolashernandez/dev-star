

/* First created by JCasGen Fri Oct 14 15:49:54 CEST 2011 */
package fr.univnantes.lina.uima.connectors.types;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import org.apache.uima.jcas.cas.FSArray;
import org.apache.uima.jcas.tcas.Annotation;
import org.apache.uima.jcas.cas.StringArray;


/** 
 * Updated by JCasGen Mon Mar 25 21:53:34 CET 2013
 * XML source: /media/MyPassport/workspace/uima-connectors/desc/connectors/corpus/P7T/P7TPlusTagged-XML2CAS-mapTo-sw+mw-WST+SPL-ViewWriter.xml
 * @generated */
public class XMLElementAnnotation extends Annotation {
  /** @generated
   * @ordered 
   */
  public final static int typeIndexID = JCasRegistry.register(XMLElementAnnotation.class);
  /** @generated
   * @ordered 
   */
  public final static int type = typeIndexID;
  /** @generated  */
  public              int getTypeIndexID() {return typeIndexID;}
 
  /** Never called.  Disable default constructor
   * @generated */
  protected XMLElementAnnotation() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated */
  public XMLElementAnnotation(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated */
  public XMLElementAnnotation(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated */  
  public XMLElementAnnotation(JCas jcas, int begin, int end) {
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
  //* Feature: attributes

  /** getter for attributes - gets 
   * @generated */
  public FSArray getAttributes() {
    if (XMLElementAnnotation_Type.featOkTst && ((XMLElementAnnotation_Type)jcasType).casFeat_attributes == null)
      jcasType.jcas.throwFeatMissing("attributes", "fr.univnantes.lina.uima.connectors.types.XMLElementAnnotation");
    return (FSArray)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((XMLElementAnnotation_Type)jcasType).casFeatCode_attributes)));}
    
  /** setter for attributes - sets  
   * @generated */
  public void setAttributes(FSArray v) {
    if (XMLElementAnnotation_Type.featOkTst && ((XMLElementAnnotation_Type)jcasType).casFeat_attributes == null)
      jcasType.jcas.throwFeatMissing("attributes", "fr.univnantes.lina.uima.connectors.types.XMLElementAnnotation");
    jcasType.ll_cas.ll_setRefValue(addr, ((XMLElementAnnotation_Type)jcasType).casFeatCode_attributes, jcasType.ll_cas.ll_getFSRef(v));}    
    
  /** indexed getter for attributes - gets an indexed value - 
   * @generated */
  public XMLAttributeAnnotation getAttributes(int i) {
    if (XMLElementAnnotation_Type.featOkTst && ((XMLElementAnnotation_Type)jcasType).casFeat_attributes == null)
      jcasType.jcas.throwFeatMissing("attributes", "fr.univnantes.lina.uima.connectors.types.XMLElementAnnotation");
    jcasType.jcas.checkArrayBounds(jcasType.ll_cas.ll_getRefValue(addr, ((XMLElementAnnotation_Type)jcasType).casFeatCode_attributes), i);
    return (XMLAttributeAnnotation)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefArrayValue(jcasType.ll_cas.ll_getRefValue(addr, ((XMLElementAnnotation_Type)jcasType).casFeatCode_attributes), i)));}

  /** indexed setter for attributes - sets an indexed value - 
   * @generated */
  public void setAttributes(int i, XMLAttributeAnnotation v) { 
    if (XMLElementAnnotation_Type.featOkTst && ((XMLElementAnnotation_Type)jcasType).casFeat_attributes == null)
      jcasType.jcas.throwFeatMissing("attributes", "fr.univnantes.lina.uima.connectors.types.XMLElementAnnotation");
    jcasType.jcas.checkArrayBounds(jcasType.ll_cas.ll_getRefValue(addr, ((XMLElementAnnotation_Type)jcasType).casFeatCode_attributes), i);
    jcasType.ll_cas.ll_setRefArrayValue(jcasType.ll_cas.ll_getRefValue(addr, ((XMLElementAnnotation_Type)jcasType).casFeatCode_attributes), i, jcasType.ll_cas.ll_getFSRef(v));}
                                  //*--------------*
  //* Feature: uri

  /** getter for uri - gets 
   * @generated */
  public String getUri() {
    if (XMLElementAnnotation_Type.featOkTst && ((XMLElementAnnotation_Type)jcasType).casFeat_uri == null)
      jcasType.jcas.throwFeatMissing("uri", "fr.univnantes.lina.uima.connectors.types.XMLElementAnnotation");
    return jcasType.ll_cas.ll_getStringValue(addr, ((XMLElementAnnotation_Type)jcasType).casFeatCode_uri);}
    
  /** setter for uri - sets  
   * @generated */
  public void setUri(String v) {
    if (XMLElementAnnotation_Type.featOkTst && ((XMLElementAnnotation_Type)jcasType).casFeat_uri == null)
      jcasType.jcas.throwFeatMissing("uri", "fr.univnantes.lina.uima.connectors.types.XMLElementAnnotation");
    jcasType.ll_cas.ll_setStringValue(addr, ((XMLElementAnnotation_Type)jcasType).casFeatCode_uri, v);}    
   
    
  //*--------------*
  //* Feature: qualifiedName

  /** getter for qualifiedName - gets 
   * @generated */
  public String getQualifiedName() {
    if (XMLElementAnnotation_Type.featOkTst && ((XMLElementAnnotation_Type)jcasType).casFeat_qualifiedName == null)
      jcasType.jcas.throwFeatMissing("qualifiedName", "fr.univnantes.lina.uima.connectors.types.XMLElementAnnotation");
    return jcasType.ll_cas.ll_getStringValue(addr, ((XMLElementAnnotation_Type)jcasType).casFeatCode_qualifiedName);}
    
  /** setter for qualifiedName - sets  
   * @generated */
  public void setQualifiedName(String v) {
    if (XMLElementAnnotation_Type.featOkTst && ((XMLElementAnnotation_Type)jcasType).casFeat_qualifiedName == null)
      jcasType.jcas.throwFeatMissing("qualifiedName", "fr.univnantes.lina.uima.connectors.types.XMLElementAnnotation");
    jcasType.ll_cas.ll_setStringValue(addr, ((XMLElementAnnotation_Type)jcasType).casFeatCode_qualifiedName, v);}    
   
    
  //*--------------*
  //* Feature: localName

  /** getter for localName - gets 
   * @generated */
  public String getLocalName() {
    if (XMLElementAnnotation_Type.featOkTst && ((XMLElementAnnotation_Type)jcasType).casFeat_localName == null)
      jcasType.jcas.throwFeatMissing("localName", "fr.univnantes.lina.uima.connectors.types.XMLElementAnnotation");
    return jcasType.ll_cas.ll_getStringValue(addr, ((XMLElementAnnotation_Type)jcasType).casFeatCode_localName);}
    
  /** setter for localName - sets  
   * @generated */
  public void setLocalName(String v) {
    if (XMLElementAnnotation_Type.featOkTst && ((XMLElementAnnotation_Type)jcasType).casFeat_localName == null)
      jcasType.jcas.throwFeatMissing("localName", "fr.univnantes.lina.uima.connectors.types.XMLElementAnnotation");
    jcasType.ll_cas.ll_setStringValue(addr, ((XMLElementAnnotation_Type)jcasType).casFeatCode_localName, v);}    
   
    
  //*--------------*
  //* Feature: children

  /** getter for children - gets 
   * @generated */
  public FSArray getChildren() {
    if (XMLElementAnnotation_Type.featOkTst && ((XMLElementAnnotation_Type)jcasType).casFeat_children == null)
      jcasType.jcas.throwFeatMissing("children", "fr.univnantes.lina.uima.connectors.types.XMLElementAnnotation");
    return (FSArray)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((XMLElementAnnotation_Type)jcasType).casFeatCode_children)));}
    
  /** setter for children - sets  
   * @generated */
  public void setChildren(FSArray v) {
    if (XMLElementAnnotation_Type.featOkTst && ((XMLElementAnnotation_Type)jcasType).casFeat_children == null)
      jcasType.jcas.throwFeatMissing("children", "fr.univnantes.lina.uima.connectors.types.XMLElementAnnotation");
    jcasType.ll_cas.ll_setRefValue(addr, ((XMLElementAnnotation_Type)jcasType).casFeatCode_children, jcasType.ll_cas.ll_getFSRef(v));}    
    
  /** indexed getter for children - gets an indexed value - 
   * @generated */
  public XMLElementAnnotation getChildren(int i) {
    if (XMLElementAnnotation_Type.featOkTst && ((XMLElementAnnotation_Type)jcasType).casFeat_children == null)
      jcasType.jcas.throwFeatMissing("children", "fr.univnantes.lina.uima.connectors.types.XMLElementAnnotation");
    jcasType.jcas.checkArrayBounds(jcasType.ll_cas.ll_getRefValue(addr, ((XMLElementAnnotation_Type)jcasType).casFeatCode_children), i);
    return (XMLElementAnnotation)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefArrayValue(jcasType.ll_cas.ll_getRefValue(addr, ((XMLElementAnnotation_Type)jcasType).casFeatCode_children), i)));}

  /** indexed setter for children - sets an indexed value - 
   * @generated */
  public void setChildren(int i, XMLElementAnnotation v) { 
    if (XMLElementAnnotation_Type.featOkTst && ((XMLElementAnnotation_Type)jcasType).casFeat_children == null)
      jcasType.jcas.throwFeatMissing("children", "fr.univnantes.lina.uima.connectors.types.XMLElementAnnotation");
    jcasType.jcas.checkArrayBounds(jcasType.ll_cas.ll_getRefValue(addr, ((XMLElementAnnotation_Type)jcasType).casFeatCode_children), i);
    jcasType.ll_cas.ll_setRefArrayValue(jcasType.ll_cas.ll_getRefValue(addr, ((XMLElementAnnotation_Type)jcasType).casFeatCode_children), i, jcasType.ll_cas.ll_getFSRef(v));}
   
    
  //*--------------*
  //* Feature: parent

  /** getter for parent - gets 
   * @generated */
  public XMLElementAnnotation getParent() {
    if (XMLElementAnnotation_Type.featOkTst && ((XMLElementAnnotation_Type)jcasType).casFeat_parent == null)
      jcasType.jcas.throwFeatMissing("parent", "fr.univnantes.lina.uima.connectors.types.XMLElementAnnotation");
    return (XMLElementAnnotation)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((XMLElementAnnotation_Type)jcasType).casFeatCode_parent)));}
    
  /** setter for parent - sets  
   * @generated */
  public void setParent(XMLElementAnnotation v) {
    if (XMLElementAnnotation_Type.featOkTst && ((XMLElementAnnotation_Type)jcasType).casFeat_parent == null)
      jcasType.jcas.throwFeatMissing("parent", "fr.univnantes.lina.uima.connectors.types.XMLElementAnnotation");
    jcasType.ll_cas.ll_setRefValue(addr, ((XMLElementAnnotation_Type)jcasType).casFeatCode_parent, jcasType.ll_cas.ll_getFSRef(v));}    
   
    
}

    