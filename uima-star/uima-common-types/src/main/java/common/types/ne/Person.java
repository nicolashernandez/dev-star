

/* First created by JCasGen Wed May 27 22:41:00 CEST 2015 */
package common.types.ne;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;



/** * person
    * gender
    * number
    * title
    * nationality
    * subcategory:politic,military,administration,religious,aristocrat,other
    * profession
    * animality:human|animal
    * reality:real|fiction
 * Updated by JCasGen Wed May 27 22:41:00 CEST 2015
 * XML source: /media/hernandez-n/ext4/workspace/lina-star/uima-star/uima-common-types/src/main/resources/common/types/commonTS.xml
 * @generated */
public class Person extends NamedEntity {
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = JCasRegistry.register(Person.class);
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
  protected Person() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated
   * @param addr low level Feature Structure reference
   * @param type the type of this Feature Structure 
   */
  public Person(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated
   * @param jcas JCas to which this Feature Structure belongs 
   */
  public Person(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated
   * @param jcas JCas to which this Feature Structure belongs
   * @param begin offset to the begin spot in the SofA
   * @param end offset to the end spot in the SofA 
  */  
  public Person(JCas jcas, int begin, int end) {
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
  //* Feature: title

  /** getter for title - gets Civilité
   * @generated
   * @return value of the feature 
   */
  public String getTitle() {
    if (Person_Type.featOkTst && ((Person_Type)jcasType).casFeat_title == null)
      jcasType.jcas.throwFeatMissing("title", "common.types.ne.Person");
    return jcasType.ll_cas.ll_getStringValue(addr, ((Person_Type)jcasType).casFeatCode_title);}
    
  /** setter for title - sets Civilité 
   * @generated
   * @param v value to set into the feature 
   */
  public void setTitle(String v) {
    if (Person_Type.featOkTst && ((Person_Type)jcasType).casFeat_title == null)
      jcasType.jcas.throwFeatMissing("title", "common.types.ne.Person");
    jcasType.ll_cas.ll_setStringValue(addr, ((Person_Type)jcasType).casFeatCode_title, v);}    
   
    
  //*--------------*
  //* Feature: profession

  /** getter for profession - gets Fonction :
politic politique
military militaire
administrative administrative
religious religieuse
aristocrat aristocratique
   * @generated
   * @return value of the feature 
   */
  public String getProfession() {
    if (Person_Type.featOkTst && ((Person_Type)jcasType).casFeat_profession == null)
      jcasType.jcas.throwFeatMissing("profession", "common.types.ne.Person");
    return jcasType.ll_cas.ll_getStringValue(addr, ((Person_Type)jcasType).casFeatCode_profession);}
    
  /** setter for profession - sets Fonction :
politic politique
military militaire
administrative administrative
religious religieuse
aristocrat aristocratique 
   * @generated
   * @param v value to set into the feature 
   */
  public void setProfession(String v) {
    if (Person_Type.featOkTst && ((Person_Type)jcasType).casFeat_profession == null)
      jcasType.jcas.throwFeatMissing("profession", "common.types.ne.Person");
    jcasType.ll_cas.ll_setStringValue(addr, ((Person_Type)jcasType).casFeatCode_profession, v);}    
   
    
  //*--------------*
  //* Feature: species

  /** getter for species - gets espèce human/animal
   * @generated
   * @return value of the feature 
   */
  public String getSpecies() {
    if (Person_Type.featOkTst && ((Person_Type)jcasType).casFeat_species == null)
      jcasType.jcas.throwFeatMissing("species", "common.types.ne.Person");
    return jcasType.ll_cas.ll_getStringValue(addr, ((Person_Type)jcasType).casFeatCode_species);}
    
  /** setter for species - sets espèce human/animal 
   * @generated
   * @param v value to set into the feature 
   */
  public void setSpecies(String v) {
    if (Person_Type.featOkTst && ((Person_Type)jcasType).casFeat_species == null)
      jcasType.jcas.throwFeatMissing("species", "common.types.ne.Person");
    jcasType.ll_cas.ll_setStringValue(addr, ((Person_Type)jcasType).casFeatCode_species, v);}    
   
    
  //*--------------*
  //* Feature: nationality

  /** getter for nationality - gets 
   * @generated
   * @return value of the feature 
   */
  public String getNationality() {
    if (Person_Type.featOkTst && ((Person_Type)jcasType).casFeat_nationality == null)
      jcasType.jcas.throwFeatMissing("nationality", "common.types.ne.Person");
    return jcasType.ll_cas.ll_getStringValue(addr, ((Person_Type)jcasType).casFeatCode_nationality);}
    
  /** setter for nationality - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setNationality(String v) {
    if (Person_Type.featOkTst && ((Person_Type)jcasType).casFeat_nationality == null)
      jcasType.jcas.throwFeatMissing("nationality", "common.types.ne.Person");
    jcasType.ll_cas.ll_setStringValue(addr, ((Person_Type)jcasType).casFeatCode_nationality, v);}    
  }

    