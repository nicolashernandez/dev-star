

/* First created by JCasGen Thu Mar 17 20:35:41 CET 2011 */
package fr.univnantes.lina.uima.types;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;



/** 
 * Updated by JCasGen Thu Mar 17 20:35:41 CET 2011
 * XML source: /media/MyPassport/current/public/research/UIMA-USER-DEV-ENV/workspace/uima-connectors/desc/nerc/NERCTS.xml
 * @generated */
public class Person extends NamedEntity {
  /** @generated
   * @ordered 
   */
  public final static int typeIndexID = JCasRegistry.register(Person.class);
  /** @generated
   * @ordered 
   */
  public final static int type = typeIndexID;
  /** @generated  */
  public              int getTypeIndexID() {return typeIndexID;}
 
  /** Never called.  Disable default constructor
   * @generated */
  protected Person() {}
    
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
  private void readObject() {}
     
 
    
  //*--------------*
  //* Feature: title

  /** getter for title - gets Civilité
   * @generated */
  public String getTitle() {
    if (Person_Type.featOkTst && ((Person_Type)jcasType).casFeat_title == null)
      jcasType.jcas.throwFeatMissing("title", "fr.univnantes.lina.uima.types.Person");
    return jcasType.ll_cas.ll_getStringValue(addr, ((Person_Type)jcasType).casFeatCode_title);}
    
  /** setter for title - sets Civilité 
   * @generated */
  public void setTitle(String v) {
    if (Person_Type.featOkTst && ((Person_Type)jcasType).casFeat_title == null)
      jcasType.jcas.throwFeatMissing("title", "fr.univnantes.lina.uima.types.Person");
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
      jcasType.jcas.throwFeatMissing("profession", "fr.univnantes.lina.uima.types.Person");
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
      jcasType.jcas.throwFeatMissing("profession", "fr.univnantes.lina.uima.types.Person");
    jcasType.ll_cas.ll_setStringValue(addr, ((Person_Type)jcasType).casFeatCode_profession, v);}    
   
    
  //*--------------*
  //* Feature: species

  /** getter for species - gets espèce human/animal
   * @generated */
  public String getSpecies() {
    if (Person_Type.featOkTst && ((Person_Type)jcasType).casFeat_species == null)
      jcasType.jcas.throwFeatMissing("species", "fr.univnantes.lina.uima.types.Person");
    return jcasType.ll_cas.ll_getStringValue(addr, ((Person_Type)jcasType).casFeatCode_species);}
    
  /** setter for species - sets espèce human/animal 
   * @generated */
  public void setSpecies(String v) {
    if (Person_Type.featOkTst && ((Person_Type)jcasType).casFeat_species == null)
      jcasType.jcas.throwFeatMissing("species", "fr.univnantes.lina.uima.types.Person");
    jcasType.ll_cas.ll_setStringValue(addr, ((Person_Type)jcasType).casFeatCode_species, v);}    
  }

    