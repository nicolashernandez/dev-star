

/* First created by JCasGen Thu Mar 17 20:35:41 CET 2011 */
package fr.univnantes.lina.uima.types;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;



/** subcategory values :
age Age, 
duration Durée, 
temperature Température,  
length Longueur, 
Surface Aire et surface, 
Volume Volume, 
Weight Poids, 
Speed Vitesse, 
Money Valeur monétaire, 
Other Autre
 * Updated by JCasGen Thu Mar 17 20:35:41 CET 2011
 * XML source: /media/MyPassport/current/public/research/UIMA-USER-DEV-ENV/workspace/uima-connectors/desc/nerc/NERCTS.xml
 * @generated */
public class Amount extends NamedEntity {
  /** @generated
   * @ordered 
   */
  public final static int typeIndexID = JCasRegistry.register(Amount.class);
  /** @generated
   * @ordered 
   */
  public final static int type = typeIndexID;
  /** @generated  */
  public              int getTypeIndexID() {return typeIndexID;}
 
  /** Never called.  Disable default constructor
   * @generated */
  protected Amount() {}
    
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
  private void readObject() {}
     
 
    
  //*--------------*
  //* Feature: amountDigit

  /** getter for amountDigit - gets 
   * @generated */
  public float getAmountDigit() {
    if (Amount_Type.featOkTst && ((Amount_Type)jcasType).casFeat_amountDigit == null)
      jcasType.jcas.throwFeatMissing("amountDigit", "fr.univnantes.lina.uima.types.Amount");
    return jcasType.ll_cas.ll_getFloatValue(addr, ((Amount_Type)jcasType).casFeatCode_amountDigit);}
    
  /** setter for amountDigit - sets  
   * @generated */
  public void setAmountDigit(float v) {
    if (Amount_Type.featOkTst && ((Amount_Type)jcasType).casFeat_amountDigit == null)
      jcasType.jcas.throwFeatMissing("amountDigit", "fr.univnantes.lina.uima.types.Amount");
    jcasType.ll_cas.ll_setFloatValue(addr, ((Amount_Type)jcasType).casFeatCode_amountDigit, v);}    
   
    
  //*--------------*
  //* Feature: amountText

  /** getter for amountText - gets 
   * @generated */
  public String getAmountText() {
    if (Amount_Type.featOkTst && ((Amount_Type)jcasType).casFeat_amountText == null)
      jcasType.jcas.throwFeatMissing("amountText", "fr.univnantes.lina.uima.types.Amount");
    return jcasType.ll_cas.ll_getStringValue(addr, ((Amount_Type)jcasType).casFeatCode_amountText);}
    
  /** setter for amountText - sets  
   * @generated */
  public void setAmountText(String v) {
    if (Amount_Type.featOkTst && ((Amount_Type)jcasType).casFeat_amountText == null)
      jcasType.jcas.throwFeatMissing("amountText", "fr.univnantes.lina.uima.types.Amount");
    jcasType.ll_cas.ll_setStringValue(addr, ((Amount_Type)jcasType).casFeatCode_amountText, v);}    
   
    
  //*--------------*
  //* Feature: amountUnit

  /** getter for amountUnit - gets Unité de la quantité
   * @generated */
  public String getAmountUnit() {
    if (Amount_Type.featOkTst && ((Amount_Type)jcasType).casFeat_amountUnit == null)
      jcasType.jcas.throwFeatMissing("amountUnit", "fr.univnantes.lina.uima.types.Amount");
    return jcasType.ll_cas.ll_getStringValue(addr, ((Amount_Type)jcasType).casFeatCode_amountUnit);}
    
  /** setter for amountUnit - sets Unité de la quantité 
   * @generated */
  public void setAmountUnit(String v) {
    if (Amount_Type.featOkTst && ((Amount_Type)jcasType).casFeat_amountUnit == null)
      jcasType.jcas.throwFeatMissing("amountUnit", "fr.univnantes.lina.uima.types.Amount");
    jcasType.ll_cas.ll_setStringValue(addr, ((Amount_Type)jcasType).casFeatCode_amountUnit, v);}    
  }

    