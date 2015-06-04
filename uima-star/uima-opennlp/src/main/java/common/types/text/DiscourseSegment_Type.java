
/* First created by JCasGen Sat Dec 10 00:59:10 CET 2011 */
package common.types.text;

import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.cas.impl.CASImpl;
import org.apache.uima.cas.impl.FSGenerator;
import org.apache.uima.cas.FeatureStructure;
import org.apache.uima.cas.impl.TypeImpl;
import org.apache.uima.cas.Type;

/** 
 * Updated by JCasGen Sat Dec 10 00:59:12 CET 2011
 * @generated */
public class DiscourseSegment_Type extends Annotation_Type {
  /** @generated */
  protected FSGenerator getFSGenerator() {return fsGenerator;}
  /** @generated */
  private final FSGenerator fsGenerator = 
    new FSGenerator() {
      public FeatureStructure createFS(int addr, CASImpl cas) {
  			 if (DiscourseSegment_Type.this.useExistingInstance) {
  			   // Return eq fs instance if already created
  		     FeatureStructure fs = DiscourseSegment_Type.this.jcas.getJfsFromCaddr(addr);
  		     if (null == fs) {
  		       fs = new DiscourseSegment(addr, DiscourseSegment_Type.this);
  			   DiscourseSegment_Type.this.jcas.putJfsFromCaddr(addr, fs);
  			   return fs;
  		     }
  		     return fs;
        } else return new DiscourseSegment(addr, DiscourseSegment_Type.this);
  	  }
    };
  /** @generated */
  public final static int typeIndexID = DiscourseSegment.typeIndexID;
  /** @generated 
     @modifiable */
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("common.types.text.DiscourseSegment");



  /** initialize variables to correspond with Cas Type and Features
	* @generated */
  public DiscourseSegment_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

  }
}



    