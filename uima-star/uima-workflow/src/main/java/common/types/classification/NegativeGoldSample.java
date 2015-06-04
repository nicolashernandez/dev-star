

/* First created by JCasGen Thu May 30 13:40:53 CEST 2013 */
package common.types.classification;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import common.types.text.Annotation;


/** 
 * Updated by JCasGen Thu May 30 13:40:53 CEST 2013
 * XML source: /media/ext4/workspace/uima-workflow/desc/wf/analysis/importTutinArcadeXRCELeMondeCorpus-openNLPPOSTaggerFr-maltparser-AAE.xml
 * @generated */
public class NegativeGoldSample extends Annotation {
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = JCasRegistry.register(NegativeGoldSample.class);
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
  protected NegativeGoldSample() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated */
  public NegativeGoldSample(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated */
  public NegativeGoldSample(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated */  
  public NegativeGoldSample(JCas jcas, int begin, int end) {
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
    if (NegativeGoldSample_Type.featOkTst && ((NegativeGoldSample_Type)jcasType).casFeat_label == null)
      jcasType.jcas.throwFeatMissing("label", "common.types.classification.NegativeGoldSample");
    return jcasType.ll_cas.ll_getStringValue(addr, ((NegativeGoldSample_Type)jcasType).casFeatCode_label);}
    
  /** setter for label - sets  
   * @generated */
  public void setLabel(String v) {
    if (NegativeGoldSample_Type.featOkTst && ((NegativeGoldSample_Type)jcasType).casFeat_label == null)
      jcasType.jcas.throwFeatMissing("label", "common.types.classification.NegativeGoldSample");
    jcasType.ll_cas.ll_setStringValue(addr, ((NegativeGoldSample_Type)jcasType).casFeatCode_label, v);}    
  }

    