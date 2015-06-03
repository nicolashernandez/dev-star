

/* First created by JCasGen Wed May 27 22:43:37 CEST 2015 */
package irc;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;



/** 
 * Updated by JCasGen Wed May 27 22:43:37 CEST 2015
 * XML source: /media/hernandez-n/ext4/workspace/lina-star/uima-star/uima-common-types/src/main/resources/common/types/CommonTS.xml
 * @generated */
public class Message extends LogLine {
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = JCasRegistry.register(Message.class);
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
  protected Message() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated
   * @param addr low level Feature Structure reference
   * @param type the type of this Feature Structure 
   */
  public Message(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated
   * @param jcas JCas to which this Feature Structure belongs 
   */
  public Message(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated
   * @param jcas JCas to which this Feature Structure belongs
   * @param begin offset to the begin spot in the SofA
   * @param end offset to the end spot in the SofA 
  */  
  public Message(JCas jcas, int begin, int end) {
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
  //* Feature: timeMarked

  /** getter for timeMarked - gets 
   * @generated
   * @return value of the feature 
   */
  public MsgTime getTimeMarked() {
    if (Message_Type.featOkTst && ((Message_Type)jcasType).casFeat_timeMarked == null)
      jcasType.jcas.throwFeatMissing("timeMarked", "irc.Message");
    return (MsgTime)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((Message_Type)jcasType).casFeatCode_timeMarked)));}
    
  /** setter for timeMarked - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setTimeMarked(MsgTime v) {
    if (Message_Type.featOkTst && ((Message_Type)jcasType).casFeat_timeMarked == null)
      jcasType.jcas.throwFeatMissing("timeMarked", "irc.Message");
    jcasType.ll_cas.ll_setRefValue(addr, ((Message_Type)jcasType).casFeatCode_timeMarked, jcasType.ll_cas.ll_getFSRef(v));}    
   
    
  //*--------------*
  //* Feature: from

  /** getter for from - gets 
   * @generated
   * @return value of the feature 
   */
  public MsgFrom getFrom() {
    if (Message_Type.featOkTst && ((Message_Type)jcasType).casFeat_from == null)
      jcasType.jcas.throwFeatMissing("from", "irc.Message");
    return (MsgFrom)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((Message_Type)jcasType).casFeatCode_from)));}
    
  /** setter for from - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setFrom(MsgFrom v) {
    if (Message_Type.featOkTst && ((Message_Type)jcasType).casFeat_from == null)
      jcasType.jcas.throwFeatMissing("from", "irc.Message");
    jcasType.ll_cas.ll_setRefValue(addr, ((Message_Type)jcasType).casFeatCode_from, jcasType.ll_cas.ll_getFSRef(v));}    
   
    
  //*--------------*
  //* Feature: to

  /** getter for to - gets 
   * @generated
   * @return value of the feature 
   */
  public MsgTo getTo() {
    if (Message_Type.featOkTst && ((Message_Type)jcasType).casFeat_to == null)
      jcasType.jcas.throwFeatMissing("to", "irc.Message");
    return (MsgTo)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((Message_Type)jcasType).casFeatCode_to)));}
    
  /** setter for to - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setTo(MsgTo v) {
    if (Message_Type.featOkTst && ((Message_Type)jcasType).casFeat_to == null)
      jcasType.jcas.throwFeatMissing("to", "irc.Message");
    jcasType.ll_cas.ll_setRefValue(addr, ((Message_Type)jcasType).casFeatCode_to, jcasType.ll_cas.ll_getFSRef(v));}    
   
    
  //*--------------*
  //* Feature: body

  /** getter for body - gets 
   * @generated
   * @return value of the feature 
   */
  public MsgBody getBody() {
    if (Message_Type.featOkTst && ((Message_Type)jcasType).casFeat_body == null)
      jcasType.jcas.throwFeatMissing("body", "irc.Message");
    return (MsgBody)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((Message_Type)jcasType).casFeatCode_body)));}
    
  /** setter for body - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setBody(MsgBody v) {
    if (Message_Type.featOkTst && ((Message_Type)jcasType).casFeat_body == null)
      jcasType.jcas.throwFeatMissing("body", "irc.Message");
    jcasType.ll_cas.ll_setRefValue(addr, ((Message_Type)jcasType).casFeatCode_body, jcasType.ll_cas.ll_getFSRef(v));}    
  }

    