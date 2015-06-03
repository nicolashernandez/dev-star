

/* First created by JCasGen Wed May 27 22:43:37 CEST 2015 */
package irc;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;



/** 
 * Updated by JCasGen Wed May 27 22:43:37 CEST 2015
 * XML source: /media/hernandez-n/ext4/workspace/lina-star/uima-star/uima-common-types/src/main/resources/common/types/CommonTS.xml
 * @generated */
public class KnownAsInfo extends Info {
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = JCasRegistry.register(KnownAsInfo.class);
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
  protected KnownAsInfo() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated
   * @param addr low level Feature Structure reference
   * @param type the type of this Feature Structure 
   */
  public KnownAsInfo(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated
   * @param jcas JCas to which this Feature Structure belongs 
   */
  public KnownAsInfo(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated
   * @param jcas JCas to which this Feature Structure belongs
   * @param begin offset to the begin spot in the SofA
   * @param end offset to the end spot in the SofA 
  */  
  public KnownAsInfo(JCas jcas, int begin, int end) {
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
    if (KnownAsInfo_Type.featOkTst && ((KnownAsInfo_Type)jcasType).casFeat_fromNickname == null)
      jcasType.jcas.throwFeatMissing("fromNickname", "irc.KnownAsInfo");
    return jcasType.ll_cas.ll_getStringValue(addr, ((KnownAsInfo_Type)jcasType).casFeatCode_fromNickname);}
    
  /** setter for fromNickname - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setFromNickname(String v) {
    if (KnownAsInfo_Type.featOkTst && ((KnownAsInfo_Type)jcasType).casFeat_fromNickname == null)
      jcasType.jcas.throwFeatMissing("fromNickname", "irc.KnownAsInfo");
    jcasType.ll_cas.ll_setStringValue(addr, ((KnownAsInfo_Type)jcasType).casFeatCode_fromNickname, v);}    
   
    
  //*--------------*
  //* Feature: toNickname

  /** getter for toNickname - gets 
   * @generated
   * @return value of the feature 
   */
  public String getToNickname() {
    if (KnownAsInfo_Type.featOkTst && ((KnownAsInfo_Type)jcasType).casFeat_toNickname == null)
      jcasType.jcas.throwFeatMissing("toNickname", "irc.KnownAsInfo");
    return jcasType.ll_cas.ll_getStringValue(addr, ((KnownAsInfo_Type)jcasType).casFeatCode_toNickname);}
    
  /** setter for toNickname - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setToNickname(String v) {
    if (KnownAsInfo_Type.featOkTst && ((KnownAsInfo_Type)jcasType).casFeat_toNickname == null)
      jcasType.jcas.throwFeatMissing("toNickname", "irc.KnownAsInfo");
    jcasType.ll_cas.ll_setStringValue(addr, ((KnownAsInfo_Type)jcasType).casFeatCode_toNickname, v);}    
  }

    