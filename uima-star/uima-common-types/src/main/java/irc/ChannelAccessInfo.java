

/* First created by JCasGen Wed May 27 22:43:37 CEST 2015 */
package irc;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;



/** 
 * Updated by JCasGen Wed May 27 22:43:37 CEST 2015
 * XML source: /media/hernandez-n/ext4/workspace/lina-star/uima-star/uima-common-types/src/main/resources/common/types/CommonTS.xml
 * @generated */
public class ChannelAccessInfo extends Info {
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = JCasRegistry.register(ChannelAccessInfo.class);
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
  protected ChannelAccessInfo() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated
   * @param addr low level Feature Structure reference
   * @param type the type of this Feature Structure 
   */
  public ChannelAccessInfo(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated
   * @param jcas JCas to which this Feature Structure belongs 
   */
  public ChannelAccessInfo(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated
   * @param jcas JCas to which this Feature Structure belongs
   * @param begin offset to the begin spot in the SofA
   * @param end offset to the end spot in the SofA 
  */  
  public ChannelAccessInfo(JCas jcas, int begin, int end) {
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
  //* Feature: nickname

  /** getter for nickname - gets 
   * @generated
   * @return value of the feature 
   */
  public String getNickname() {
    if (ChannelAccessInfo_Type.featOkTst && ((ChannelAccessInfo_Type)jcasType).casFeat_nickname == null)
      jcasType.jcas.throwFeatMissing("nickname", "irc.ChannelAccessInfo");
    return jcasType.ll_cas.ll_getStringValue(addr, ((ChannelAccessInfo_Type)jcasType).casFeatCode_nickname);}
    
  /** setter for nickname - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setNickname(String v) {
    if (ChannelAccessInfo_Type.featOkTst && ((ChannelAccessInfo_Type)jcasType).casFeat_nickname == null)
      jcasType.jcas.throwFeatMissing("nickname", "irc.ChannelAccessInfo");
    jcasType.ll_cas.ll_setStringValue(addr, ((ChannelAccessInfo_Type)jcasType).casFeatCode_nickname, v);}    
   
    
  //*--------------*
  //* Feature: source

  /** getter for source - gets 
   * @generated
   * @return value of the feature 
   */
  public String getSource() {
    if (ChannelAccessInfo_Type.featOkTst && ((ChannelAccessInfo_Type)jcasType).casFeat_source == null)
      jcasType.jcas.throwFeatMissing("source", "irc.ChannelAccessInfo");
    return jcasType.ll_cas.ll_getStringValue(addr, ((ChannelAccessInfo_Type)jcasType).casFeatCode_source);}
    
  /** setter for source - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setSource(String v) {
    if (ChannelAccessInfo_Type.featOkTst && ((ChannelAccessInfo_Type)jcasType).casFeat_source == null)
      jcasType.jcas.throwFeatMissing("source", "irc.ChannelAccessInfo");
    jcasType.ll_cas.ll_setStringValue(addr, ((ChannelAccessInfo_Type)jcasType).casFeatCode_source, v);}    
   
    
  //*--------------*
  //* Feature: channel

  /** getter for channel - gets 
   * @generated
   * @return value of the feature 
   */
  public String getChannel() {
    if (ChannelAccessInfo_Type.featOkTst && ((ChannelAccessInfo_Type)jcasType).casFeat_channel == null)
      jcasType.jcas.throwFeatMissing("channel", "irc.ChannelAccessInfo");
    return jcasType.ll_cas.ll_getStringValue(addr, ((ChannelAccessInfo_Type)jcasType).casFeatCode_channel);}
    
  /** setter for channel - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setChannel(String v) {
    if (ChannelAccessInfo_Type.featOkTst && ((ChannelAccessInfo_Type)jcasType).casFeat_channel == null)
      jcasType.jcas.throwFeatMissing("channel", "irc.ChannelAccessInfo");
    jcasType.ll_cas.ll_setStringValue(addr, ((ChannelAccessInfo_Type)jcasType).casFeatCode_channel, v);}    
   
    
  //*--------------*
  //* Feature: welcomeLeavingMessage

  /** getter for welcomeLeavingMessage - gets 
   * @generated
   * @return value of the feature 
   */
  public String getWelcomeLeavingMessage() {
    if (ChannelAccessInfo_Type.featOkTst && ((ChannelAccessInfo_Type)jcasType).casFeat_welcomeLeavingMessage == null)
      jcasType.jcas.throwFeatMissing("welcomeLeavingMessage", "irc.ChannelAccessInfo");
    return jcasType.ll_cas.ll_getStringValue(addr, ((ChannelAccessInfo_Type)jcasType).casFeatCode_welcomeLeavingMessage);}
    
  /** setter for welcomeLeavingMessage - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setWelcomeLeavingMessage(String v) {
    if (ChannelAccessInfo_Type.featOkTst && ((ChannelAccessInfo_Type)jcasType).casFeat_welcomeLeavingMessage == null)
      jcasType.jcas.throwFeatMissing("welcomeLeavingMessage", "irc.ChannelAccessInfo");
    jcasType.ll_cas.ll_setStringValue(addr, ((ChannelAccessInfo_Type)jcasType).casFeatCode_welcomeLeavingMessage, v);}    
  }

    