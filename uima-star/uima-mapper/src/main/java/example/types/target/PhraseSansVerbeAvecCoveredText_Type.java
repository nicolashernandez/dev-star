
/* First created by JCasGen Fri Apr 16 00:01:40 CEST 2010 */
package example.types.target;

import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.cas.impl.CASImpl;
import org.apache.uima.cas.impl.FSGenerator;
import org.apache.uima.cas.FeatureStructure;
import org.apache.uima.cas.impl.TypeImpl;
import org.apache.uima.cas.Type;
import org.apache.uima.jcas.tcas.Annotation_Type;

/** 
 * Updated by JCasGen Mon Mar 28 15:53:11 CEST 2011
 * @generated */
public class PhraseSansVerbeAvecCoveredText_Type extends Annotation_Type {
  /** @generated */
  protected FSGenerator getFSGenerator() {return fsGenerator;}
  /** @generated */
  private final FSGenerator fsGenerator = 
    new FSGenerator() {
      public FeatureStructure createFS(int addr, CASImpl cas) {
  			 if (PhraseSansVerbeAvecCoveredText_Type.this.useExistingInstance) {
  			   // Return eq fs instance if already created
  		     FeatureStructure fs = PhraseSansVerbeAvecCoveredText_Type.this.jcas.getJfsFromCaddr(addr);
  		     if (null == fs) {
  		       fs = new PhraseSansVerbeAvecCoveredText(addr, PhraseSansVerbeAvecCoveredText_Type.this);
  			   PhraseSansVerbeAvecCoveredText_Type.this.jcas.putJfsFromCaddr(addr, fs);
  			   return fs;
  		     }
  		     return fs;
        } else return new PhraseSansVerbeAvecCoveredText(addr, PhraseSansVerbeAvecCoveredText_Type.this);
  	  }
    };
  /** @generated */
  public final static int typeIndexID = PhraseSansVerbeAvecCoveredText.typeIndexID;
  /** @generated 
     @modifiable */
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("example.types.target.PhraseSansVerbeAvecCoveredText");



  /** initialize variables to correspond with Cas Type and Features
	* @generated */
  public PhraseSansVerbeAvecCoveredText_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

  }
}



    