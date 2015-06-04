
/* First created by JCasGen Sat Dec 10 00:59:10 CET 2011 */
package common.types.ne;

import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.cas.impl.CASImpl;
import org.apache.uima.cas.impl.FSGenerator;
import org.apache.uima.cas.FeatureStructure;
import org.apache.uima.cas.impl.TypeImpl;
import org.apache.uima.cas.Type;

/** subcategory values :
vehicule Moyen de transport, 
award Récompense, 
art Oeuvre artistique, 
documentary Production documentaire
 * Updated by JCasGen Sat Dec 10 00:59:12 CET 2011
 * @generated */
public class Work_Type extends NamedEntity_Type {
  /** @generated */
  protected FSGenerator getFSGenerator() {return fsGenerator;}
  /** @generated */
  private final FSGenerator fsGenerator = 
    new FSGenerator() {
      public FeatureStructure createFS(int addr, CASImpl cas) {
  			 if (Work_Type.this.useExistingInstance) {
  			   // Return eq fs instance if already created
  		     FeatureStructure fs = Work_Type.this.jcas.getJfsFromCaddr(addr);
  		     if (null == fs) {
  		       fs = new Work(addr, Work_Type.this);
  			   Work_Type.this.jcas.putJfsFromCaddr(addr, fs);
  			   return fs;
  		     }
  		     return fs;
        } else return new Work(addr, Work_Type.this);
  	  }
    };
  /** @generated */
  public final static int typeIndexID = Work.typeIndexID;
  /** @generated 
     @modifiable */
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("common.types.ne.Work");



  /** initialize variables to correspond with Cas Type and Features
	* @generated */
  public Work_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

  }
}



    