

/* First created by JCasGen Wed Sep 29 23:45:19 CEST 2010 */
package fr.univnantes.lina.uima.types;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import org.apache.uima.jcas.tcas.Annotation;


/** 
 * Updated by JCasGen Sat May 26 21:22:19 CEST 2012
 * XML source: /media/MyPassport/workspace/uima-shell/desc/shell/examples/EXAMPLE-PASS-wst_cas2csv_shellTreetagger_csv2cas_AAE.xml
 * @generated */
public class OneDimensionAnnotation extends Annotation {
  /** @generated
   * @ordered 
   */
  public final static int typeIndexID = JCasRegistry.register(OneDimensionAnnotation.class);
  /** @generated
   * @ordered 
   */
  public final static int type = typeIndexID;
  /** @generated  */
  public              int getTypeIndexID() {return typeIndexID;}
 
  /** Never called.  Disable default constructor
   * @generated */
  protected OneDimensionAnnotation() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated */
  public OneDimensionAnnotation(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated */
  public OneDimensionAnnotation(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated */  
  public OneDimensionAnnotation(JCas jcas, int begin, int end) {
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
  //* Feature: componentId

  /** getter for componentId - gets 
   * @generated */
  public String getComponentId() {
    if (OneDimensionAnnotation_Type.featOkTst && ((OneDimensionAnnotation_Type)jcasType).casFeat_componentId == null)
      jcasType.jcas.throwFeatMissing("componentId", "fr.univnantes.lina.uima.types.OneDimensionAnnotation");
    return jcasType.ll_cas.ll_getStringValue(addr, ((OneDimensionAnnotation_Type)jcasType).casFeatCode_componentId);}
    
  /** setter for componentId - sets  
   * @generated */
  public void setComponentId(String v) {
    if (OneDimensionAnnotation_Type.featOkTst && ((OneDimensionAnnotation_Type)jcasType).casFeat_componentId == null)
      jcasType.jcas.throwFeatMissing("componentId", "fr.univnantes.lina.uima.types.OneDimensionAnnotation");
    jcasType.ll_cas.ll_setStringValue(addr, ((OneDimensionAnnotation_Type)jcasType).casFeatCode_componentId, v);}    
   
    
  //*--------------*
  //* Feature: runId

  /** getter for runId - gets 
   * @generated */
  public String getRunId() {
    if (OneDimensionAnnotation_Type.featOkTst && ((OneDimensionAnnotation_Type)jcasType).casFeat_runId == null)
      jcasType.jcas.throwFeatMissing("runId", "fr.univnantes.lina.uima.types.OneDimensionAnnotation");
    return jcasType.ll_cas.ll_getStringValue(addr, ((OneDimensionAnnotation_Type)jcasType).casFeatCode_runId);}
    
  /** setter for runId - sets  
   * @generated */
  public void setRunId(String v) {
    if (OneDimensionAnnotation_Type.featOkTst && ((OneDimensionAnnotation_Type)jcasType).casFeat_runId == null)
      jcasType.jcas.throwFeatMissing("runId", "fr.univnantes.lina.uima.types.OneDimensionAnnotation");
    jcasType.ll_cas.ll_setStringValue(addr, ((OneDimensionAnnotation_Type)jcasType).casFeatCode_runId, v);}    
  }

    