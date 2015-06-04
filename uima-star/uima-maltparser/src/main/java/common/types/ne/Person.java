

/* First created by JCasGen Thu May 30 11:49:30 CEST 2013 */
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
 * Updated by JCasGen Thu May 30 11:49:30 CEST 2013
 * XML source: /media/ext4/workspace/uima-maltparser/desc/maltParser/wst-openNLPPOSTagger-maltparser.AAE.xml
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
  /** @generated  */
  @Override
  public              int getTypeIndexID() {return typeIndexID;}
 
  /** Never called.  Disable default constructor
   * @generated */
  protected Person() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated */
  public Person(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated */
  public Person(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated */  
  public Person(JCas jcas, int begin, int end) {
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
  //* Feature: title

  /** getter for title - gets Civilité
   * @generated */
  public String getTitle() {
    if (Person_Type.featOkTst && ((Person_Type)jcasType).casFeat_title == null)
      jcasType.jcas.throwFeatMissing("title", "common.types.ne.Person");
    return jcasType.ll_cas.ll_getStringValue(addr, ((Person_Type)jcasType).casFeatCode_title);}
    
  /** setter for title - sets Civilité 
   * @generated */
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
   * @generated */
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
   * @generated */
  public void setProfession(String v) {
    if (Person_Type.featOkTst && ((Person_Type)jcasType).casFeat_profession == null)
      jcasType.jcas.throwFeatMissing("profession", "common.types.ne.Person");
    jcasType.ll_cas.ll_setStringValue(addr, ((Person_Type)jcasType).casFeatCode_profession, v);}    
   
    
  //*--------------*
  //* Feature: species

  /** getter for species - gets espèce human/animal
   * @generated */
  public String getSpecies() {
    if (Person_Type.featOkTst && ((Person_Type)jcasType).casFeat_species == null)
      jcasType.jcas.throwFeatMissing("species", "common.types.ne.Person");
    return jcasType.ll_cas.ll_getStringValue(addr, ((Person_Type)jcasType).casFeatCode_species);}
    
  /** setter for species - sets espèce human/animal 
   * @generated */
  public void setSpecies(String v) {
    if (Person_Type.featOkTst && ((Person_Type)jcasType).casFeat_species == null)
      jcasType.jcas.throwFeatMissing("species", "common.types.ne.Person");
    jcasType.ll_cas.ll_setStringValue(addr, ((Person_Type)jcasType).casFeatCode_species, v);}    
   
    
  //*--------------*
  //* Feature: nationality

  /** getter for nationality - gets 
   * @generated */
  public String getNationality() {
    if (Person_Type.featOkTst && ((Person_Type)jcasType).casFeat_nationality == null)
      jcasType.jcas.throwFeatMissing("nationality", "common.types.ne.Person");
    return jcasType.ll_cas.ll_getStringValue(addr, ((Person_Type)jcasType).casFeatCode_nationality);}
    
  /** setter for nationality - sets  
   * @generated */
  public void setNationality(String v) {
    if (Person_Type.featOkTst && ((Person_Type)jcasType).casFeat_nationality == null)
      jcasType.jcas.throwFeatMissing("nationality", "common.types.ne.Person");
    jcasType.ll_cas.ll_setStringValue(addr, ((Person_Type)jcasType).casFeatCode_nationality, v);}    
  }

    