

/* First created by JCasGen Wed May 27 22:43:37 CEST 2015 */
package irc;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;



/** 
 * Updated by JCasGen Wed May 27 22:43:37 CEST 2015
 * XML source: /media/hernandez-n/ext4/workspace/lina-star/uima-star/uima-common-types/src/main/resources/common/types/CommonTS.xml
 * @generated */
public class EmoteInfo extends Info {
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = JCasRegistry.register(EmoteInfo.class);
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
  protected EmoteInfo() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated
   * @param addr low level Feature Structure reference
   * @param type the type of this Feature Structure 
   */
  public EmoteInfo(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated
   * @param jcas JCas to which this Feature Structure belongs 
   */
  public EmoteInfo(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated
   * @param jcas JCas to which this Feature Structure belongs
   * @param begin offset to the begin spot in the SofA
   * @param end offset to the end spot in the SofA 
  */  
  public EmoteInfo(JCas jcas, int begin, int end) {
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
  //* Feature: fromNickname

  /** getter for fromNickname - gets 
   * @generated
   * @return value of the feature 
   */
  public String getFromNickname() {
    if (EmoteInfo_Type.featOkTst && ((EmoteInfo_Type)jcasType).casFeat_fromNickname == null)
      jcasType.jcas.throwFeatMissing("fromNickname", "irc.EmoteInfo");
    return jcasType.ll_cas.ll_getStringValue(addr, ((EmoteInfo_Type)jcasType).casFeatCode_fromNickname);}
    
  /** setter for fromNickname - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setFromNickname(String v) {
    if (EmoteInfo_Type.featOkTst && ((EmoteInfo_Type)jcasType).casFeat_fromNickname == null)
      jcasType.jcas.throwFeatMissing("fromNickname", "irc.EmoteInfo");
    jcasType.ll_cas.ll_setStringValue(addr, ((EmoteInfo_Type)jcasType).casFeatCode_fromNickname, v);}    
   
    
  //*--------------*
  //* Feature: emote

  /** getter for emote - gets 
   * @generated
   * @return value of the feature 
   */
  public String getEmote() {
    if (EmoteInfo_Type.featOkTst && ((EmoteInfo_Type)jcasType).casFeat_emote == null)
      jcasType.jcas.throwFeatMissing("emote", "irc.EmoteInfo");
    return jcasType.ll_cas.ll_getStringValue(addr, ((EmoteInfo_Type)jcasType).casFeatCode_emote);}
    
  /** setter for emote - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setEmote(String v) {
    if (EmoteInfo_Type.featOkTst && ((EmoteInfo_Type)jcasType).casFeat_emote == null)
      jcasType.jcas.throwFeatMissing("emote", "irc.EmoteInfo");
    jcasType.ll_cas.ll_setStringValue(addr, ((EmoteInfo_Type)jcasType).casFeatCode_emote, v);}    
  }

    