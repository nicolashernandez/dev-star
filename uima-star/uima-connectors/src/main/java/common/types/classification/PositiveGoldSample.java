

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
public class PositiveGoldSample extends Annotation {
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = JCasRegistry.register(PositiveGoldSample.class);
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
  protected PositiveGoldSample() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated */
  public PositiveGoldSample(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated */
  public PositiveGoldSample(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated */  
  public PositiveGoldSample(JCas jcas, int begin, int end) {
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
    if (PositiveGoldSample_Type.featOkTst && ((PositiveGoldSample_Type)jcasType).casFeat_label == null)
      jcasType.jcas.throwFeatMissing("label", "common.types.classification.PositiveGoldSample");
    return jcasType.ll_cas.ll_getStringValue(addr, ((PositiveGoldSample_Type)jcasType).casFeatCode_label);}
    
  /** setter for label - sets  
   * @generated */
  public void setLabel(String v) {
    if (PositiveGoldSample_Type.featOkTst && ((PositiveGoldSample_Type)jcasType).casFeat_label == null)
      jcasType.jcas.throwFeatMissing("label", "common.types.classification.PositiveGoldSample");
    jcasType.ll_cas.ll_setStringValue(addr, ((PositiveGoldSample_Type)jcasType).casFeatCode_label, v);}    
  }

    