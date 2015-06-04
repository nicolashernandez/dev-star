
/* First created by JCasGen Thu Mar 17 20:35:41 CET 2011 */
package fr.univnantes.lina.uima.types;

import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.cas.impl.CASImpl;
import org.apache.uima.cas.impl.FSGenerator;
import org.apache.uima.cas.FeatureStructure;
import org.apache.uima.cas.impl.TypeImpl;
import org.apache.uima.cas.Type;

/** subcategory values :
politic politique 
military militaire 
adm administrative
religious religieuse
aristocrat aristocratique
 * Updated by JCasGen Thu Mar 17 20:35:41 CET 2011
 * @generated */
public class Profession_Type extends NamedEntity_Type {
  /** @generated */
  protected FSGenerator getFSGenerator() {return fsGenerator;}
  /** @generated */
  private final FSGenerator fsGenerator = 
    new FSGenerator() {
      public FeatureStructure createFS(int addr, CASImpl cas) {
  			 if (Profession_Type.this.useExistingInstance) {
  			   // Return eq fs instance if already created
  		     FeatureStructure fs = Profession_Type.this.jcas.getJfsFromCaddr(addr);
  		     if (null == fs) {
  		       fs = new Profession(addr, Profession_Type.this);
  			   Profession_Type.this.jcas.putJfsFromCaddr(addr, fs);
  			   return fs;
  		     }
  		     return fs;
        } else return new Profession(addr, Profession_Type.this);
  	  }
    };
  /** @generated */
  public final static int typeIndexID = Profession.typeIndexID;
  /** @generated 
     @modifiable */
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("fr.univnantes.lina.uima.types.Profession");



  /** initialize variables to correspond with Cas Type and Features
	* @generated */
  public Profession_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

  }
}



    