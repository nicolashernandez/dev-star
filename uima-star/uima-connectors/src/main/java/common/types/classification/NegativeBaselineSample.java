

/* First created by JCasGen Fri Mar 08 15:28:14 CET 2013 */
package common.types.classification;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import common.types.text.Annotation;


/** 
 * Updated by JCasGen Thu May 02 23:08:53 CEST 2013
 * XML source: /media/ext4/workspace/uima-connectors/desc/connectors/corpus/P7T/P7TPlusConstit-XML2CAS-mapper-CAS2OpenNLPChunkTagger-fineGrained-CAS2CSV-ViewWriter-AAE.xml
 * @generated */
public class NegativeBaselineSample extends Annotation {
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = JCasRegistry.register(NegativeBaselineSample.class);
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
  protected NegativeBaselineSample() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated */
  public NegativeBaselineSample(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated */
  public NegativeBaselineSample(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated */  
  public NegativeBaselineSample(JCas jcas, int begin, int end) {
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
  //* Feature: label

  /** getter for label - gets 
   * @generated */
  public String getLabel() {
    if (NegativeBaselineSample_Type.featOkTst && ((NegativeBaselineSample_Type)jcasType).casFeat_label == null)
      jcasType.jcas.throwFeatMissing("label", "common.types.classification.NegativeBaselineSample");
    return jcasType.ll_cas.ll_getStringValue(addr, ((NegativeBaselineSample_Type)jcasType).casFeatCode_label);}
    
  /** setter for label - sets  
   * @generated */
  public void setLabel(String v) {
    if (NegativeBaselineSample_Type.featOkTst && ((NegativeBaselineSample_Type)jcasType).casFeat_label == null)
      jcasType.jcas.throwFeatMissing("label", "common.types.classification.NegativeBaselineSample");
    jcasType.ll_cas.ll_setStringValue(addr, ((NegativeBaselineSample_Type)jcasType).casFeatCode_label, v);}    
  }

    