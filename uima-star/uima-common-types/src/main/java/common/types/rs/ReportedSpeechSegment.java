

/* First created by JCasGen Wed May 27 22:41:00 CEST 2015 */
package common.types.rs;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;



/** 
 * Updated by JCasGen Wed May 27 22:41:00 CEST 2015
 * XML source: /media/hernandez-n/ext4/workspace/lina-star/uima-star/uima-common-types/src/main/resources/common/types/commonTS.xml
 * @generated */
public class ReportedSpeechSegment extends ReportedSpeech {
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = JCasRegistry.register(ReportedSpeechSegment.class);
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
  protected ReportedSpeechSegment() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated
   * @param addr low level Feature Structure reference
   * @param type the type of this Feature Structure 
   */
  public ReportedSpeechSegment(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated
   * @param jcas JCas to which this Feature Structure belongs 
   */
  public ReportedSpeechSegment(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated
   * @param jcas JCas to which this Feature Structure belongs
   * @param begin offset to the begin spot in the SofA
   * @param end offset to the end spot in the SofA 
  */  
  public ReportedSpeechSegment(JCas jcas, int begin, int end) {
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
  //* Feature: source

  /** getter for source - gets 
   * @generated
   * @return value of the feature 
   */
  public String getSource() {
    if (ReportedSpeechSegment_Type.featOkTst && ((ReportedSpeechSegment_Type)jcasType).casFeat_source == null)
      jcasType.jcas.throwFeatMissing("source", "common.types.rs.ReportedSpeechSegment");
    return jcasType.ll_cas.ll_getStringValue(addr, ((ReportedSpeechSegment_Type)jcasType).casFeatCode_source);}
    
  /** setter for source - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setSource(String v) {
    if (ReportedSpeechSegment_Type.featOkTst && ((ReportedSpeechSegment_Type)jcasType).casFeat_source == null)
      jcasType.jcas.throwFeatMissing("source", "common.types.rs.ReportedSpeechSegment");
    jcasType.ll_cas.ll_setStringValue(addr, ((ReportedSpeechSegment_Type)jcasType).casFeatCode_source, v);}    
   
    
  //*--------------*
  //* Feature: discourse

  /** getter for discourse - gets 
   * @generated
   * @return value of the feature 
   */
  public String getDiscourse() {
    if (ReportedSpeechSegment_Type.featOkTst && ((ReportedSpeechSegment_Type)jcasType).casFeat_discourse == null)
      jcasType.jcas.throwFeatMissing("discourse", "common.types.rs.ReportedSpeechSegment");
    return jcasType.ll_cas.ll_getStringValue(addr, ((ReportedSpeechSegment_Type)jcasType).casFeatCode_discourse);}
    
  /** setter for discourse - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setDiscourse(String v) {
    if (ReportedSpeechSegment_Type.featOkTst && ((ReportedSpeechSegment_Type)jcasType).casFeat_discourse == null)
      jcasType.jcas.throwFeatMissing("discourse", "common.types.rs.ReportedSpeechSegment");
    jcasType.ll_cas.ll_setStringValue(addr, ((ReportedSpeechSegment_Type)jcasType).casFeatCode_discourse, v);}    
  }

    