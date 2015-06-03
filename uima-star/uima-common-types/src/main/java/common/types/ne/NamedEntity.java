

/* First created by JCasGen Wed May 27 22:41:00 CEST 2015 */
package common.types.ne;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import common.types.text.Annotation;


/** 
 * Updated by JCasGen Wed May 27 22:41:00 CEST 2015
 * XML source: /media/hernandez-n/ext4/workspace/lina-star/uima-star/uima-common-types/src/main/resources/common/types/commonTS.xml
 * @generated */
public class NamedEntity extends Annotation {
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = JCasRegistry.register(NamedEntity.class);
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
  protected NamedEntity() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated
   * @param addr low level Feature Structure reference
   * @param type the type of this Feature Structure 
   */
  public NamedEntity(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated
   * @param jcas JCas to which this Feature Structure belongs 
   */
  public NamedEntity(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated
   * @param jcas JCas to which this Feature Structure belongs
   * @param begin offset to the begin spot in the SofA
   * @param end offset to the end spot in the SofA 
  */  
  public NamedEntity(JCas jcas, int begin, int end) {
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
  //* Feature: category

  /** getter for category - gets person personne
fonction profession
organisation organization
lieu place
production work
date date
montant amount
   * @generated
   * @return value of the feature 
   */
  public String getCategory() {
    if (NamedEntity_Type.featOkTst && ((NamedEntity_Type)jcasType).casFeat_category == null)
      jcasType.jcas.throwFeatMissing("category", "common.types.ne.NamedEntity");
    return jcasType.ll_cas.ll_getStringValue(addr, ((NamedEntity_Type)jcasType).casFeatCode_category);}
    
  /** setter for category - sets person personne
fonction profession
organisation organization
lieu place
production work
date date
montant amount 
   * @generated
   * @param v value to set into the feature 
   */
  public void setCategory(String v) {
    if (NamedEntity_Type.featOkTst && ((NamedEntity_Type)jcasType).casFeat_category == null)
      jcasType.jcas.throwFeatMissing("category", "common.types.ne.NamedEntity");
    jcasType.ll_cas.ll_setStringValue(addr, ((NamedEntity_Type)jcasType).casFeatCode_category, v);}    
   
    
  //*--------------*
  //* Feature: gender

  /** getter for gender - gets m masculine,
f feminine, 
n neutrum
   * @generated
   * @return value of the feature 
   */
  public String getGender() {
    if (NamedEntity_Type.featOkTst && ((NamedEntity_Type)jcasType).casFeat_gender == null)
      jcasType.jcas.throwFeatMissing("gender", "common.types.ne.NamedEntity");
    return jcasType.ll_cas.ll_getStringValue(addr, ((NamedEntity_Type)jcasType).casFeatCode_gender);}
    
  /** setter for gender - sets m masculine,
f feminine, 
n neutrum 
   * @generated
   * @param v value to set into the feature 
   */
  public void setGender(String v) {
    if (NamedEntity_Type.featOkTst && ((NamedEntity_Type)jcasType).casFeat_gender == null)
      jcasType.jcas.throwFeatMissing("gender", "common.types.ne.NamedEntity");
    jcasType.ll_cas.ll_setStringValue(addr, ((NamedEntity_Type)jcasType).casFeatCode_gender, v);}    
   
    
  //*--------------*
  //* Feature: number

  /** getter for number - gets s singular,
p plural
   * @generated
   * @return value of the feature 
   */
  public String getNumber() {
    if (NamedEntity_Type.featOkTst && ((NamedEntity_Type)jcasType).casFeat_number == null)
      jcasType.jcas.throwFeatMissing("number", "common.types.ne.NamedEntity");
    return jcasType.ll_cas.ll_getStringValue(addr, ((NamedEntity_Type)jcasType).casFeatCode_number);}
    
  /** setter for number - sets s singular,
p plural 
   * @generated
   * @param v value to set into the feature 
   */
  public void setNumber(String v) {
    if (NamedEntity_Type.featOkTst && ((NamedEntity_Type)jcasType).casFeat_number == null)
      jcasType.jcas.throwFeatMissing("number", "common.types.ne.NamedEntity");
    jcasType.ll_cas.ll_setStringValue(addr, ((NamedEntity_Type)jcasType).casFeatCode_number, v);}    
   
    
  //*--------------*
  //* Feature: subCategory

  /** getter for subCategory - gets See the different EN extensions
   * @generated
   * @return value of the feature 
   */
  public String getSubCategory() {
    if (NamedEntity_Type.featOkTst && ((NamedEntity_Type)jcasType).casFeat_subCategory == null)
      jcasType.jcas.throwFeatMissing("subCategory", "common.types.ne.NamedEntity");
    return jcasType.ll_cas.ll_getStringValue(addr, ((NamedEntity_Type)jcasType).casFeatCode_subCategory);}
    
  /** setter for subCategory - sets See the different EN extensions 
   * @generated
   * @param v value to set into the feature 
   */
  public void setSubCategory(String v) {
    if (NamedEntity_Type.featOkTst && ((NamedEntity_Type)jcasType).casFeat_subCategory == null)
      jcasType.jcas.throwFeatMissing("subCategory", "common.types.ne.NamedEntity");
    jcasType.ll_cas.ll_setStringValue(addr, ((NamedEntity_Type)jcasType).casFeatCode_subCategory, v);}    
   
    
  //*--------------*
  //* Feature: ruleId

  /** getter for ruleId - gets identifiant de la rêgle
   * @generated
   * @return value of the feature 
   */
  public String getRuleId() {
    if (NamedEntity_Type.featOkTst && ((NamedEntity_Type)jcasType).casFeat_ruleId == null)
      jcasType.jcas.throwFeatMissing("ruleId", "common.types.ne.NamedEntity");
    return jcasType.ll_cas.ll_getStringValue(addr, ((NamedEntity_Type)jcasType).casFeatCode_ruleId);}
    
  /** setter for ruleId - sets identifiant de la rêgle 
   * @generated
   * @param v value to set into the feature 
   */
  public void setRuleId(String v) {
    if (NamedEntity_Type.featOkTst && ((NamedEntity_Type)jcasType).casFeat_ruleId == null)
      jcasType.jcas.throwFeatMissing("ruleId", "common.types.ne.NamedEntity");
    jcasType.ll_cas.ll_setStringValue(addr, ((NamedEntity_Type)jcasType).casFeatCode_ruleId, v);}    
   
    
  //*--------------*
  //* Feature: value

  /** getter for value - gets The content of the named entity, since the rules can recognize also the context.
   * @generated
   * @return value of the feature 
   */
  public String getValue() {
    if (NamedEntity_Type.featOkTst && ((NamedEntity_Type)jcasType).casFeat_value == null)
      jcasType.jcas.throwFeatMissing("value", "common.types.ne.NamedEntity");
    return jcasType.ll_cas.ll_getStringValue(addr, ((NamedEntity_Type)jcasType).casFeatCode_value);}
    
  /** setter for value - sets The content of the named entity, since the rules can recognize also the context. 
   * @generated
   * @param v value to set into the feature 
   */
  public void setValue(String v) {
    if (NamedEntity_Type.featOkTst && ((NamedEntity_Type)jcasType).casFeat_value == null)
      jcasType.jcas.throwFeatMissing("value", "common.types.ne.NamedEntity");
    jcasType.ll_cas.ll_setStringValue(addr, ((NamedEntity_Type)jcasType).casFeatCode_value, v);}    
   
    
  //*--------------*
  //* Feature: annotatorId

  /** getter for annotatorId - gets 
   * @generated
   * @return value of the feature 
   */
  public String getAnnotatorId() {
    if (NamedEntity_Type.featOkTst && ((NamedEntity_Type)jcasType).casFeat_annotatorId == null)
      jcasType.jcas.throwFeatMissing("annotatorId", "common.types.ne.NamedEntity");
    return jcasType.ll_cas.ll_getStringValue(addr, ((NamedEntity_Type)jcasType).casFeatCode_annotatorId);}
    
  /** setter for annotatorId - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setAnnotatorId(String v) {
    if (NamedEntity_Type.featOkTst && ((NamedEntity_Type)jcasType).casFeat_annotatorId == null)
      jcasType.jcas.throwFeatMissing("annotatorId", "common.types.ne.NamedEntity");
    jcasType.ll_cas.ll_setStringValue(addr, ((NamedEntity_Type)jcasType).casFeatCode_annotatorId, v);}    
   
    
  //*--------------*
  //* Feature: status

  /** getter for status - gets 
   * @generated
   * @return value of the feature 
   */
  public String getStatus() {
    if (NamedEntity_Type.featOkTst && ((NamedEntity_Type)jcasType).casFeat_status == null)
      jcasType.jcas.throwFeatMissing("status", "common.types.ne.NamedEntity");
    return jcasType.ll_cas.ll_getStringValue(addr, ((NamedEntity_Type)jcasType).casFeatCode_status);}
    
  /** setter for status - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setStatus(String v) {
    if (NamedEntity_Type.featOkTst && ((NamedEntity_Type)jcasType).casFeat_status == null)
      jcasType.jcas.throwFeatMissing("status", "common.types.ne.NamedEntity");
    jcasType.ll_cas.ll_setStringValue(addr, ((NamedEntity_Type)jcasType).casFeatCode_status, v);}    
   
    
  //*--------------*
  //* Feature: language

  /** getter for language - gets 
   * @generated
   * @return value of the feature 
   */
  public String getLanguage() {
    if (NamedEntity_Type.featOkTst && ((NamedEntity_Type)jcasType).casFeat_language == null)
      jcasType.jcas.throwFeatMissing("language", "common.types.ne.NamedEntity");
    return jcasType.ll_cas.ll_getStringValue(addr, ((NamedEntity_Type)jcasType).casFeatCode_language);}
    
  /** setter for language - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setLanguage(String v) {
    if (NamedEntity_Type.featOkTst && ((NamedEntity_Type)jcasType).casFeat_language == null)
      jcasType.jcas.throwFeatMissing("language", "common.types.ne.NamedEntity");
    jcasType.ll_cas.ll_setStringValue(addr, ((NamedEntity_Type)jcasType).casFeatCode_language, v);}    
  }

    