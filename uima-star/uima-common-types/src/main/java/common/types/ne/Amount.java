

/* First created by JCasGen Wed May 27 22:41:00 CEST 2015 */
package common.types.ne;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;



/** * amount
    * subCategory:length volume weight temperature area currency speed other
    * amountDigit
    * amountLetter
    * amountUnit
 * Updated by JCasGen Wed May 27 22:41:00 CEST 2015
 * XML source: /media/hernandez-n/ext4/workspace/lina-star/uima-star/uima-common-types/src/main/resources/common/types/commonTS.xml
 * @generated */
public class Amount extends NamedEntity {
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = JCasRegistry.register(Amount.class);
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
  protected Amount() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated
   * @param addr low level Feature Structure reference
   * @param type the type of this Feature Structure 
   */
  public Amount(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated
   * @param jcas JCas to which this Feature Structure belongs 
   */
  public Amount(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated
   * @param jcas JCas to which this Feature Structure belongs
   * @param begin offset to the begin spot in the SofA
   * @param end offset to the end spot in the SofA 
  */  
  public Amount(JCas jcas, int begin, int end) {
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
  //* Feature: amountDigit

  /** getter for amountDigit - gets 
   * @generated
   * @return value of the feature 
   */
  public float getAmountDigit() {
    if (Amount_Type.featOkTst && ((Amount_Type)jcasType).casFeat_amountDigit == null)
      jcasType.jcas.throwFeatMissing("amountDigit", "common.types.ne.Amount");
    return jcasType.ll_cas.ll_getFloatValue(addr, ((Amount_Type)jcasType).casFeatCode_amountDigit);}
    
  /** setter for amountDigit - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setAmountDigit(float v) {
    if (Amount_Type.featOkTst && ((Amount_Type)jcasType).casFeat_amountDigit == null)
      jcasType.jcas.throwFeatMissing("amountDigit", "common.types.ne.Amount");
    jcasType.ll_cas.ll_setFloatValue(addr, ((Amount_Type)jcasType).casFeatCode_amountDigit, v);}    
   
    
  //*--------------*
  //* Feature: amountText

  /** getter for amountText - gets 
   * @generated
   * @return value of the feature 
   */
  public String getAmountText() {
    if (Amount_Type.featOkTst && ((Amount_Type)jcasType).casFeat_amountText == null)
      jcasType.jcas.throwFeatMissing("amountText", "common.types.ne.Amount");
    return jcasType.ll_cas.ll_getStringValue(addr, ((Amount_Type)jcasType).casFeatCode_amountText);}
    
  /** setter for amountText - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setAmountText(String v) {
    if (Amount_Type.featOkTst && ((Amount_Type)jcasType).casFeat_amountText == null)
      jcasType.jcas.throwFeatMissing("amountText", "common.types.ne.Amount");
    jcasType.ll_cas.ll_setStringValue(addr, ((Amount_Type)jcasType).casFeatCode_amountText, v);}    
   
    
  //*--------------*
  //* Feature: amountUnit

  /** getter for amountUnit - gets Unité de la quantité
   * @generated
   * @return value of the feature 
   */
  public String getAmountUnit() {
    if (Amount_Type.featOkTst && ((Amount_Type)jcasType).casFeat_amountUnit == null)
      jcasType.jcas.throwFeatMissing("amountUnit", "common.types.ne.Amount");
    return jcasType.ll_cas.ll_getStringValue(addr, ((Amount_Type)jcasType).casFeatCode_amountUnit);}
    
  /** setter for amountUnit - sets Unité de la quantité 
   * @generated
   * @param v value to set into the feature 
   */
  public void setAmountUnit(String v) {
    if (Amount_Type.featOkTst && ((Amount_Type)jcasType).casFeat_amountUnit == null)
      jcasType.jcas.throwFeatMissing("amountUnit", "common.types.ne.Amount");
    jcasType.ll_cas.ll_setStringValue(addr, ((Amount_Type)jcasType).casFeatCode_amountUnit, v);}    
  }

    