
/* First created by JCasGen Thu Apr 15 16:50:58 CEST 2010 */
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
 * Updated by JCasGen Thu Apr 15 16:50:58 CEST 2010
 * @generated */
public class PhraseDebutantParAdvDansStringArray_Type extends Annotation_Type {
  /** @generated */
  protected FSGenerator getFSGenerator() {return fsGenerator;}
  /** @generated */
  private final FSGenerator fsGenerator = 
    new FSGenerator() {
      public FeatureStructure createFS(int addr, CASImpl cas) {
  			 if (PhraseDebutantParAdvDansStringArray_Type.this.useExistingInstance) {
  			   // Return eq fs instance if already created
  		     FeatureStructure fs = PhraseDebutantParAdvDansStringArray_Type.this.jcas.getJfsFromCaddr(addr);
  		     if (null == fs) {
  		       fs = new PhraseDebutantParAdvDansStringArray(addr, PhraseDebutantParAdvDansStringArray_Type.this);
  			   PhraseDebutantParAdvDansStringArray_Type.this.jcas.putJfsFromCaddr(addr, fs);
  			   return fs;
  		     }
  		     return fs;
        } else return new PhraseDebutantParAdvDansStringArray(addr, PhraseDebutantParAdvDansStringArray_Type.this);
  	  }
    };
  /** @generated */
  public final static int typeIndexID = PhraseDebutantParAdvDansStringArray.typeIndexID;
  /** @generated 
     @modifiable */
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("example.types.target.PhraseDebutantParAdvDansStringArray");



  /** initialize variables to correspond with Cas Type and Features
	* @generated */
  public PhraseDebutantParAdvDansStringArray_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

  }
}



    