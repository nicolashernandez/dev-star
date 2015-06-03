

/* First created by JCasGen Wed May 27 22:41:00 CEST 2015 */
package common.types.syntax.dependency;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import org.apache.uima.jcas.cas.TOP;


/** 
 * Updated by JCasGen Wed May 27 22:41:00 CEST 2015
 * XML source: /media/hernandez-n/ext4/workspace/lina-star/uima-star/uima-common-types/src/main/resources/common/types/commonTS.xml
 * @generated */
public class DependencyRelation extends TOP {
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = JCasRegistry.register(DependencyRelation.class);
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
  protected DependencyRelation() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated
   * @param addr low level Feature Structure reference
   * @param type the type of this Feature Structure 
   */
  public DependencyRelation(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated
   * @param jcas JCas to which this Feature Structure belongs 
   */
  public DependencyRelation(JCas jcas) {
    super(jcas);
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
  //* Feature: Head

  /** getter for Head - gets 
   * @generated
   * @return value of the feature 
   */
  public DependencyNode getHead() {
    if (DependencyRelation_Type.featOkTst && ((DependencyRelation_Type)jcasType).casFeat_Head == null)
      jcasType.jcas.throwFeatMissing("Head", "common.types.syntax.dependency.DependencyRelation");
    return (DependencyNode)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((DependencyRelation_Type)jcasType).casFeatCode_Head)));}
    
  /** setter for Head - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setHead(DependencyNode v) {
    if (DependencyRelation_Type.featOkTst && ((DependencyRelation_Type)jcasType).casFeat_Head == null)
      jcasType.jcas.throwFeatMissing("Head", "common.types.syntax.dependency.DependencyRelation");
    jcasType.ll_cas.ll_setRefValue(addr, ((DependencyRelation_Type)jcasType).casFeatCode_Head, jcasType.ll_cas.ll_getFSRef(v));}    
   
    
  //*--------------*
  //* Feature: Relation

  /** getter for Relation - gets 
   * @generated
   * @return value of the feature 
   */
  public String getRelation() {
    if (DependencyRelation_Type.featOkTst && ((DependencyRelation_Type)jcasType).casFeat_Relation == null)
      jcasType.jcas.throwFeatMissing("Relation", "common.types.syntax.dependency.DependencyRelation");
    return jcasType.ll_cas.ll_getStringValue(addr, ((DependencyRelation_Type)jcasType).casFeatCode_Relation);}
    
  /** setter for Relation - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setRelation(String v) {
    if (DependencyRelation_Type.featOkTst && ((DependencyRelation_Type)jcasType).casFeat_Relation == null)
      jcasType.jcas.throwFeatMissing("Relation", "common.types.syntax.dependency.DependencyRelation");
    jcasType.ll_cas.ll_setStringValue(addr, ((DependencyRelation_Type)jcasType).casFeatCode_Relation, v);}    
   
    
  //*--------------*
  //* Feature: Child

  /** getter for Child - gets 
   * @generated
   * @return value of the feature 
   */
  public DependencyNode getChild() {
    if (DependencyRelation_Type.featOkTst && ((DependencyRelation_Type)jcasType).casFeat_Child == null)
      jcasType.jcas.throwFeatMissing("Child", "common.types.syntax.dependency.DependencyRelation");
    return (DependencyNode)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((DependencyRelation_Type)jcasType).casFeatCode_Child)));}
    
  /** setter for Child - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setChild(DependencyNode v) {
    if (DependencyRelation_Type.featOkTst && ((DependencyRelation_Type)jcasType).casFeat_Child == null)
      jcasType.jcas.throwFeatMissing("Child", "common.types.syntax.dependency.DependencyRelation");
    jcasType.ll_cas.ll_setRefValue(addr, ((DependencyRelation_Type)jcasType).casFeatCode_Child, jcasType.ll_cas.ll_getFSRef(v));}    
  }

    