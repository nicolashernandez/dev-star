

/* First created by JCasGen Fri Mar 08 15:28:14 CET 2013 */
package common.types.ne;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;



/** * amount
    * subCategory:length volume weight temperature area currency speed other
    * amountDigit
    * amountLetter
    * amountUnit
 * Updated by JCasGen Thu May 02 23:08:53 CEST 2013
 * XML source: /media/ext4/workspace/uima-connectors/desc/connectors/corpus/P7T/P7TPlusConstit-XML2CAS-mapper-CAS2OpenNLPChunkTagger-fineGrained-CAS2CSV-ViewWriter-AAE.xml
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
  /** @generated  */
  @Override
  public              int getTypeIndexID() {return typeIndexID;}
 
  /** Never called.  Disable default constructor
   * @generated */
  protected Amount() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated */
  public Amount(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated */
  public Amount(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated */  
  public Amount(JCas jcas, int begin, int end) {
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
  //* Feature: amountDigit

  /** getter for amountDigit - gets 
   * @generated */
  public float getAmountDigit() {
    if (Amount_Type.featOkTst && ((Amount_Type)jcasType).casFeat_amountDigit == null)
      jcasType.jcas.throwFeatMissing("amountDigit", "common.types.ne.Amount");
    return jcasType.ll_cas.ll_getFloatValue(addr, ((Amount_Type)jcasType).casFeatCode_amountDigit);}
    
  /** setter for amountDigit - sets  
   * @generated */
  public void setAmountDigit(float v) {
    if (Amount_Type.featOkTst && ((Amount_Type)jcasType).casFeat_amountDigit == null)
      jcasType.jcas.throwFeatMissing("amountDigit", "common.types.ne.Amount");
    jcasType.ll_cas.ll_setFloatValue(addr, ((Amount_Type)jcasType).casFeatCode_amountDigit, v);}    
   
    
  //*--------------*
  //* Feature: amountText

  /** getter for amountText - gets 
   * @generated */
  public String getAmountText() {
    if (Amount_Type.featOkTst && ((Amount_Type)jcasType).casFeat_amountText == null)
      jcasType.jcas.throwFeatMissing("amountText", "common.types.ne.Amount");
    return jcasType.ll_cas.ll_getStringValue(addr, ((Amount_Type)jcasType).casFeatCode_amountText);}
    
  /** setter for amountText - sets  
   * @generated */
  public void setAmountText(String v) {
    if (Amount_Type.featOkTst && ((Amount_Type)jcasType).casFeat_amountText == null)
      jcasType.jcas.throwFeatMissing("amountText", "common.types.ne.Amount");
    jcasType.ll_cas.ll_setStringValue(addr, ((Amount_Type)jcasType).casFeatCode_amountText, v);}    
   
    
  //*--------------*
  //* Feature: amountUnit

  /** getter for amountUnit - gets Unité de la quantité
   * @generated */
  public String getAmountUnit() {
    if (Amount_Type.featOkTst && ((Amount_Type)jcasType).casFeat_amountUnit == null)
      jcasType.jcas.throwFeatMissing("amountUnit", "common.types.ne.Amount");
    return jcasType.ll_cas.ll_getStringValue(addr, ((Amount_Type)jcasType).casFeatCode_amountUnit);}
    
  /** setter for amountUnit - sets Unité de la quantité 
   * @generated */
  public void setAmountUnit(String v) {
    if (Amount_Type.featOkTst && ((Amount_Type)jcasType).casFeat_amountUnit == null)
      jcasType.jcas.throwFeatMissing("amountUnit", "common.types.ne.Amount");
    jcasType.ll_cas.ll_setStringValue(addr, ((Amount_Type)jcasType).casFeatCode_amountUnit, v);}    
  }

    