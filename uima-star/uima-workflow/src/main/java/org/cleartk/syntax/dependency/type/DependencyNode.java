

/* First created by JCasGen Thu May 30 13:40:53 CEST 2013 */
package org.cleartk.syntax.dependency.type;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import org.apache.uima.jcas.cas.FSArray;
import org.apache.uima.jcas.tcas.Annotation;


/** 
 * Updated by JCasGen Thu May 30 13:40:53 CEST 2013
 * XML source: /media/ext4/workspace/uima-workflow/desc/wf/analysis/importTutinArcadeXRCELeMondeCorpus-openNLPPOSTaggerFr-maltparser-AAE.xml
 * @generated */
public class DependencyNode extends Annotation {
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = JCasRegistry.register(DependencyNode.class);
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
  protected DependencyNode() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated */
  public DependencyNode(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated */
  public DependencyNode(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated */  
  public DependencyNode(JCas jcas, int begin, int end) {
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
  //* Feature: HeadRelations

  /** getter for HeadRelations - gets 
   * @generated */
  public FSArray getHeadRelations() {
    if (DependencyNode_Type.featOkTst && ((DependencyNode_Type)jcasType).casFeat_HeadRelations == null)
      jcasType.jcas.throwFeatMissing("HeadRelations", "org.cleartk.syntax.dependency.type.DependencyNode");
    return (FSArray)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((DependencyNode_Type)jcasType).casFeatCode_HeadRelations)));}
    
  /** setter for HeadRelations - sets  
   * @generated */
  public void setHeadRelations(FSArray v) {
    if (DependencyNode_Type.featOkTst && ((DependencyNode_Type)jcasType).casFeat_HeadRelations == null)
      jcasType.jcas.throwFeatMissing("HeadRelations", "org.cleartk.syntax.dependency.type.DependencyNode");
    jcasType.ll_cas.ll_setRefValue(addr, ((DependencyNode_Type)jcasType).casFeatCode_HeadRelations, jcasType.ll_cas.ll_getFSRef(v));}    
    
  /** indexed getter for HeadRelations - gets an indexed value - 
   * @generated */
  public DependencyRelation getHeadRelations(int i) {
    if (DependencyNode_Type.featOkTst && ((DependencyNode_Type)jcasType).casFeat_HeadRelations == null)
      jcasType.jcas.throwFeatMissing("HeadRelations", "org.cleartk.syntax.dependency.type.DependencyNode");
    jcasType.jcas.checkArrayBounds(jcasType.ll_cas.ll_getRefValue(addr, ((DependencyNode_Type)jcasType).casFeatCode_HeadRelations), i);
    return (DependencyRelation)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefArrayValue(jcasType.ll_cas.ll_getRefValue(addr, ((DependencyNode_Type)jcasType).casFeatCode_HeadRelations), i)));}

  /** indexed setter for HeadRelations - sets an indexed value - 
   * @generated */
  public void setHeadRelations(int i, DependencyRelation v) { 
    if (DependencyNode_Type.featOkTst && ((DependencyNode_Type)jcasType).casFeat_HeadRelations == null)
      jcasType.jcas.throwFeatMissing("HeadRelations", "org.cleartk.syntax.dependency.type.DependencyNode");
    jcasType.jcas.checkArrayBounds(jcasType.ll_cas.ll_getRefValue(addr, ((DependencyNode_Type)jcasType).casFeatCode_HeadRelations), i);
    jcasType.ll_cas.ll_setRefArrayValue(jcasType.ll_cas.ll_getRefValue(addr, ((DependencyNode_Type)jcasType).casFeatCode_HeadRelations), i, jcasType.ll_cas.ll_getFSRef(v));}
   
    
  //*--------------*
  //* Feature: ChildRelations

  /** getter for ChildRelations - gets 
   * @generated */
  public FSArray getChildRelations() {
    if (DependencyNode_Type.featOkTst && ((DependencyNode_Type)jcasType).casFeat_ChildRelations == null)
      jcasType.jcas.throwFeatMissing("ChildRelations", "org.cleartk.syntax.dependency.type.DependencyNode");
    return (FSArray)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((DependencyNode_Type)jcasType).casFeatCode_ChildRelations)));}
    
  /** setter for ChildRelations - sets  
   * @generated */
  public void setChildRelations(FSArray v) {
    if (DependencyNode_Type.featOkTst && ((DependencyNode_Type)jcasType).casFeat_ChildRelations == null)
      jcasType.jcas.throwFeatMissing("ChildRelations", "org.cleartk.syntax.dependency.type.DependencyNode");
    jcasType.ll_cas.ll_setRefValue(addr, ((DependencyNode_Type)jcasType).casFeatCode_ChildRelations, jcasType.ll_cas.ll_getFSRef(v));}    
    
  /** indexed getter for ChildRelations - gets an indexed value - 
   * @generated */
  public DependencyRelation getChildRelations(int i) {
    if (DependencyNode_Type.featOkTst && ((DependencyNode_Type)jcasType).casFeat_ChildRelations == null)
      jcasType.jcas.throwFeatMissing("ChildRelations", "org.cleartk.syntax.dependency.type.DependencyNode");
    jcasType.jcas.checkArrayBounds(jcasType.ll_cas.ll_getRefValue(addr, ((DependencyNode_Type)jcasType).casFeatCode_ChildRelations), i);
    return (DependencyRelation)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefArrayValue(jcasType.ll_cas.ll_getRefValue(addr, ((DependencyNode_Type)jcasType).casFeatCode_ChildRelations), i)));}

  /** indexed setter for ChildRelations - sets an indexed value - 
   * @generated */
  public void setChildRelations(int i, DependencyRelation v) { 
    if (DependencyNode_Type.featOkTst && ((DependencyNode_Type)jcasType).casFeat_ChildRelations == null)
      jcasType.jcas.throwFeatMissing("ChildRelations", "org.cleartk.syntax.dependency.type.DependencyNode");
    jcasType.jcas.checkArrayBounds(jcasType.ll_cas.ll_getRefValue(addr, ((DependencyNode_Type)jcasType).casFeatCode_ChildRelations), i);
    jcasType.ll_cas.ll_setRefArrayValue(jcasType.ll_cas.ll_getRefValue(addr, ((DependencyNode_Type)jcasType).casFeatCode_ChildRelations), i, jcasType.ll_cas.ll_getFSRef(v));}
  }

    