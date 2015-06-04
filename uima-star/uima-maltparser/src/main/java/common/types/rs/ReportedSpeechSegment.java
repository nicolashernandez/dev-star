

/* First created by JCasGen Thu May 30 11:49:30 CEST 2013 */
package common.types.rs;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;



/** 
 * Updated by JCasGen Thu May 30 11:49:30 CEST 2013
 * XML source: /media/ext4/workspace/uima-maltparser/desc/maltParser/wst-openNLPPOSTagger-maltparser.AAE.xml
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
  /** @generated  */
  @Override
  public              int getTypeIndexID() {return typeIndexID;}
 
  /** Never called.  Disable default constructor
   * @generated */
  protected ReportedSpeechSegment() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated */
  public ReportedSpeechSegment(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated */
  public ReportedSpeechSegment(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated */  
  public ReportedSpeechSegment(JCas jcas, int begin, int end) {
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
  //* Feature: source

  /** getter for source - gets 
   * @generated */
  public String getSource() {
    if (ReportedSpeechSegment_Type.featOkTst && ((ReportedSpeechSegment_Type)jcasType).casFeat_source == null)
      jcasType.jcas.throwFeatMissing("source", "common.types.rs.ReportedSpeechSegment");
    return jcasType.ll_cas.ll_getStringValue(addr, ((ReportedSpeechSegment_Type)jcasType).casFeatCode_source);}
    
  /** setter for source - sets  
   * @generated */
  public void setSource(String v) {
    if (ReportedSpeechSegment_Type.featOkTst && ((ReportedSpeechSegment_Type)jcasType).casFeat_source == null)
      jcasType.jcas.throwFeatMissing("source", "common.types.rs.ReportedSpeechSegment");
    jcasType.ll_cas.ll_setStringValue(addr, ((ReportedSpeechSegment_Type)jcasType).casFeatCode_source, v);}    
   
    
  //*--------------*
  //* Feature: discourse

  /** getter for discourse - gets 
   * @generated */
  public String getDiscourse() {
    if (ReportedSpeechSegment_Type.featOkTst && ((ReportedSpeechSegment_Type)jcasType).casFeat_discourse == null)
      jcasType.jcas.throwFeatMissing("discourse", "common.types.rs.ReportedSpeechSegment");
    return jcasType.ll_cas.ll_getStringValue(addr, ((ReportedSpeechSegment_Type)jcasType).casFeatCode_discourse);}
    
  /** setter for discourse - sets  
   * @generated */
  public void setDiscourse(String v) {
    if (ReportedSpeechSegment_Type.featOkTst && ((ReportedSpeechSegment_Type)jcasType).casFeat_discourse == null)
      jcasType.jcas.throwFeatMissing("discourse", "common.types.rs.ReportedSpeechSegment");
    jcasType.ll_cas.ll_setStringValue(addr, ((ReportedSpeechSegment_Type)jcasType).casFeatCode_discourse, v);}    
  }

    