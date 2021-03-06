
/* First created by JCasGen Thu Mar 17 20:35:41 CET 2011 */
package fr.univnantes.lina.uima.types;

import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.cas.impl.CASImpl;
import org.apache.uima.cas.impl.FSGenerator;
import org.apache.uima.cas.FeatureStructure;
import org.apache.uima.cas.impl.TypeImpl;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.impl.FeatureImpl;
import org.apache.uima.cas.Feature;

/** 
 * Updated by JCasGen Thu Mar 17 20:35:41 CET 2011
 * @generated */
public class Person_Type extends NamedEntity_Type {
  /** @generated */
  protected FSGenerator getFSGenerator() {return fsGenerator;}
  /** @generated */
  private final FSGenerator fsGenerator = 
    new FSGenerator() {
      public FeatureStructure createFS(int addr, CASImpl cas) {
  			 if (Person_Type.this.useExistingInstance) {
  			   // Return eq fs instance if already created
  		     FeatureStructure fs = Person_Type.this.jcas.getJfsFromCaddr(addr);
  		     if (null == fs) {
  		       fs = new Person(addr, Person_Type.this);
  			   Person_Type.this.jcas.putJfsFromCaddr(addr, fs);
  			   return fs;
  		     }
  		     return fs;
        } else return new Person(addr, Person_Type.this);
  	  }
    };
  /** @generated */
  public final static int typeIndexID = Person.typeIndexID;
  /** @generated 
     @modifiable */
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("fr.univnantes.lina.uima.types.Person");
 
  /** @generated */
  final Feature casFeat_title;
  /** @generated */
  final int     casFeatCode_title;
  /** @generated */ 
  public String getTitle(int addr) {
        if (featOkTst && casFeat_title == null)
      jcas.throwFeatMissing("title", "fr.univnantes.lina.uima.types.Person");
    return ll_cas.ll_getStringValue(addr, casFeatCode_title);
  }
  /** @generated */    
  public void setTitle(int addr, String v) {
        if (featOkTst && casFeat_title == null)
      jcas.throwFeatMissing("title", "fr.univnantes.lina.uima.types.Person");
    ll_cas.ll_setStringValue(addr, casFeatCode_title, v);}
    
  
 
  /** @generated */
  final Feature casFeat_profession;
  /** @generated */
  final int     casFeatCode_profession;
  /** @generated */ 
  public String getProfession(int addr) {
        if (featOkTst && casFeat_profession == null)
      jcas.throwFeatMissing("profession", "fr.univnantes.lina.uima.types.Person");
    return ll_cas.ll_getStringValue(addr, casFeatCode_profession);
  }
  /** @generated */    
  public void setProfession(int addr, String v) {
        if (featOkTst && casFeat_profession == null)
      jcas.throwFeatMissing("profession", "fr.univnantes.lina.uima.types.Person");
    ll_cas.ll_setStringValue(addr, casFeatCode_profession, v);}
    
  
 
  /** @generated */
  final Feature casFeat_species;
  /** @generated */
  final int     casFeatCode_species;
  /** @generated */ 
  public String getSpecies(int addr) {
        if (featOkTst && casFeat_species == null)
      jcas.throwFeatMissing("species", "fr.univnantes.lina.uima.types.Person");
    return ll_cas.ll_getStringValue(addr, casFeatCode_species);
  }
  /** @generated */    
  public void setSpecies(int addr, String v) {
        if (featOkTst && casFeat_species == null)
      jcas.throwFeatMissing("species", "fr.univnantes.lina.uima.types.Person");
    ll_cas.ll_setStringValue(addr, casFeatCode_species, v);}
    
  



  /** initialize variables to correspond with Cas Type and Features
	* @generated */
  public Person_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_title = jcas.getRequiredFeatureDE(casType, "title", "uima.cas.String", featOkTst);
    casFeatCode_title  = (null == casFeat_title) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_title).getCode();

 
    casFeat_profession = jcas.getRequiredFeatureDE(casType, "profession", "uima.cas.String", featOkTst);
    casFeatCode_profession  = (null == casFeat_profession) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_profession).getCode();

 
    casFeat_species = jcas.getRequiredFeatureDE(casType, "species", "uima.cas.String", featOkTst);
    casFeatCode_species  = (null == casFeat_species) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_species).getCode();

  }
}



    