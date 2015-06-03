

/* First created by JCasGen Wed May 27 22:43:37 CEST 2015 */
package email;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import org.apache.uima.jcas.tcas.Annotation;


/** 
 * Updated by JCasGen Wed May 27 22:43:37 CEST 2015
 * XML source: /media/hernandez-n/ext4/workspace/lina-star/uima-star/uima-common-types/src/main/resources/common/types/CommonTS.xml
 * @generated */
public class Message extends Annotation {
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
  //* Feature: fromAddress

  /** getter for fromAddress - gets 
   * @generated
   * @return value of the feature 
   */
  public String getFromAddress() {
    if (Message_Type.featOkTst && ((Message_Type)jcasType).casFeat_fromAddress == null)
      jcasType.jcas.throwFeatMissing("fromAddress", "email.Message");
    return jcasType.ll_cas.ll_getStringValue(addr, ((Message_Type)jcasType).casFeatCode_fromAddress);}
    
  /** setter for fromAddress - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setFromAddress(String v) {
    if (Message_Type.featOkTst && ((Message_Type)jcasType).casFeat_fromAddress == null)
      jcasType.jcas.throwFeatMissing("fromAddress", "email.Message");
    jcasType.ll_cas.ll_setStringValue(addr, ((Message_Type)jcasType).casFeatCode_fromAddress, v);}    
   
    
  //*--------------*
  //* Feature: toAddress

  /** getter for toAddress - gets 
   * @generated
   * @return value of the feature 
   */
  public String getToAddress() {
    if (Message_Type.featOkTst && ((Message_Type)jcasType).casFeat_toAddress == null)
      jcasType.jcas.throwFeatMissing("toAddress", "email.Message");
    return jcasType.ll_cas.ll_getStringValue(addr, ((Message_Type)jcasType).casFeatCode_toAddress);}
    
  /** setter for toAddress - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setToAddress(String v) {
    if (Message_Type.featOkTst && ((Message_Type)jcasType).casFeat_toAddress == null)
      jcasType.jcas.throwFeatMissing("toAddress", "email.Message");
    jcasType.ll_cas.ll_setStringValue(addr, ((Message_Type)jcasType).casFeatCode_toAddress, v);}    
   
    
  //*--------------*
  //* Feature: date

  /** getter for date - gets 
   * @generated
   * @return value of the feature 
   */
  public String getDate() {
    if (Message_Type.featOkTst && ((Message_Type)jcasType).casFeat_date == null)
      jcasType.jcas.throwFeatMissing("date", "email.Message");
    return jcasType.ll_cas.ll_getStringValue(addr, ((Message_Type)jcasType).casFeatCode_date);}
    
  /** setter for date - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setDate(String v) {
    if (Message_Type.featOkTst && ((Message_Type)jcasType).casFeat_date == null)
      jcasType.jcas.throwFeatMissing("date", "email.Message");
    jcasType.ll_cas.ll_setStringValue(addr, ((Message_Type)jcasType).casFeatCode_date, v);}    
   
    
  //*--------------*
  //* Feature: id

  /** getter for id - gets 
   * @generated
   * @return value of the feature 
   */
  public String getId() {
    if (Message_Type.featOkTst && ((Message_Type)jcasType).casFeat_id == null)
      jcasType.jcas.throwFeatMissing("id", "email.Message");
    return jcasType.ll_cas.ll_getStringValue(addr, ((Message_Type)jcasType).casFeatCode_id);}
    
  /** setter for id - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setId(String v) {
    if (Message_Type.featOkTst && ((Message_Type)jcasType).casFeat_id == null)
      jcasType.jcas.throwFeatMissing("id", "email.Message");
    jcasType.ll_cas.ll_setStringValue(addr, ((Message_Type)jcasType).casFeatCode_id, v);}    
   
    
  //*--------------*
  //* Feature: subject

  /** getter for subject - gets 
   * @generated
   * @return value of the feature 
   */
  public String getSubject() {
    if (Message_Type.featOkTst && ((Message_Type)jcasType).casFeat_subject == null)
      jcasType.jcas.throwFeatMissing("subject", "email.Message");
    return jcasType.ll_cas.ll_getStringValue(addr, ((Message_Type)jcasType).casFeatCode_subject);}
    
  /** setter for subject - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setSubject(String v) {
    if (Message_Type.featOkTst && ((Message_Type)jcasType).casFeat_subject == null)
      jcasType.jcas.throwFeatMissing("subject", "email.Message");
    jcasType.ll_cas.ll_setStringValue(addr, ((Message_Type)jcasType).casFeatCode_subject, v);}    
   
    
  //*--------------*
  //* Feature: inReplyTo

  /** getter for inReplyTo - gets 
   * @generated
   * @return value of the feature 
   */
  public String getInReplyTo() {
    if (Message_Type.featOkTst && ((Message_Type)jcasType).casFeat_inReplyTo == null)
      jcasType.jcas.throwFeatMissing("inReplyTo", "email.Message");
    return jcasType.ll_cas.ll_getStringValue(addr, ((Message_Type)jcasType).casFeatCode_inReplyTo);}
    
  /** setter for inReplyTo - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setInReplyTo(String v) {
    if (Message_Type.featOkTst && ((Message_Type)jcasType).casFeat_inReplyTo == null)
      jcasType.jcas.throwFeatMissing("inReplyTo", "email.Message");
    jcasType.ll_cas.ll_setStringValue(addr, ((Message_Type)jcasType).casFeatCode_inReplyTo, v);}    
   
    
  //*--------------*
  //* Feature: mime

  /** getter for mime - gets 
   * @generated
   * @return value of the feature 
   */
  public String getMime() {
    if (Message_Type.featOkTst && ((Message_Type)jcasType).casFeat_mime == null)
      jcasType.jcas.throwFeatMissing("mime", "email.Message");
    return jcasType.ll_cas.ll_getStringValue(addr, ((Message_Type)jcasType).casFeatCode_mime);}
    
  /** setter for mime - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setMime(String v) {
    if (Message_Type.featOkTst && ((Message_Type)jcasType).casFeat_mime == null)
      jcasType.jcas.throwFeatMissing("mime", "email.Message");
    jcasType.ll_cas.ll_setStringValue(addr, ((Message_Type)jcasType).casFeatCode_mime, v);}    
   
    
  //*--------------*
  //* Feature: encoding

  /** getter for encoding - gets 
   * @generated
   * @return value of the feature 
   */
  public String getEncoding() {
    if (Message_Type.featOkTst && ((Message_Type)jcasType).casFeat_encoding == null)
      jcasType.jcas.throwFeatMissing("encoding", "email.Message");
    return jcasType.ll_cas.ll_getStringValue(addr, ((Message_Type)jcasType).casFeatCode_encoding);}
    
  /** setter for encoding - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setEncoding(String v) {
    if (Message_Type.featOkTst && ((Message_Type)jcasType).casFeat_encoding == null)
      jcasType.jcas.throwFeatMissing("encoding", "email.Message");
    jcasType.ll_cas.ll_setStringValue(addr, ((Message_Type)jcasType).casFeatCode_encoding, v);}    
   
    
  //*--------------*
  //* Feature: position

  /** getter for position - gets 
   * @generated
   * @return value of the feature 
   */
  public int getPosition() {
    if (Message_Type.featOkTst && ((Message_Type)jcasType).casFeat_position == null)
      jcasType.jcas.throwFeatMissing("position", "email.Message");
    return jcasType.ll_cas.ll_getIntValue(addr, ((Message_Type)jcasType).casFeatCode_position);}
    
  /** setter for position - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setPosition(int v) {
    if (Message_Type.featOkTst && ((Message_Type)jcasType).casFeat_position == null)
      jcasType.jcas.throwFeatMissing("position", "email.Message");
    jcasType.ll_cas.ll_setIntValue(addr, ((Message_Type)jcasType).casFeatCode_position, v);}    
   
    
  //*--------------*
  //* Feature: isInitial

  /** getter for isInitial - gets 
   * @generated
   * @return value of the feature 
   */
  public boolean getIsInitial() {
    if (Message_Type.featOkTst && ((Message_Type)jcasType).casFeat_isInitial == null)
      jcasType.jcas.throwFeatMissing("isInitial", "email.Message");
    return jcasType.ll_cas.ll_getBooleanValue(addr, ((Message_Type)jcasType).casFeatCode_isInitial);}
    
  /** setter for isInitial - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setIsInitial(boolean v) {
    if (Message_Type.featOkTst && ((Message_Type)jcasType).casFeat_isInitial == null)
      jcasType.jcas.throwFeatMissing("isInitial", "email.Message");
    jcasType.ll_cas.ll_setBooleanValue(addr, ((Message_Type)jcasType).casFeatCode_isInitial, v);}    
   
    
  //*--------------*
  //* Feature: fromPersonal

  /** getter for fromPersonal - gets 
   * @generated
   * @return value of the feature 
   */
  public String getFromPersonal() {
    if (Message_Type.featOkTst && ((Message_Type)jcasType).casFeat_fromPersonal == null)
      jcasType.jcas.throwFeatMissing("fromPersonal", "email.Message");
    return jcasType.ll_cas.ll_getStringValue(addr, ((Message_Type)jcasType).casFeatCode_fromPersonal);}
    
  /** setter for fromPersonal - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setFromPersonal(String v) {
    if (Message_Type.featOkTst && ((Message_Type)jcasType).casFeat_fromPersonal == null)
      jcasType.jcas.throwFeatMissing("fromPersonal", "email.Message");
    jcasType.ll_cas.ll_setStringValue(addr, ((Message_Type)jcasType).casFeatCode_fromPersonal, v);}    
   
    
  //*--------------*
  //* Feature: toPersonal

  /** getter for toPersonal - gets 
   * @generated
   * @return value of the feature 
   */
  public String getToPersonal() {
    if (Message_Type.featOkTst && ((Message_Type)jcasType).casFeat_toPersonal == null)
      jcasType.jcas.throwFeatMissing("toPersonal", "email.Message");
    return jcasType.ll_cas.ll_getStringValue(addr, ((Message_Type)jcasType).casFeatCode_toPersonal);}
    
  /** setter for toPersonal - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setToPersonal(String v) {
    if (Message_Type.featOkTst && ((Message_Type)jcasType).casFeat_toPersonal == null)
      jcasType.jcas.throwFeatMissing("toPersonal", "email.Message");
    jcasType.ll_cas.ll_setStringValue(addr, ((Message_Type)jcasType).casFeatCode_toPersonal, v);}    
  }

    