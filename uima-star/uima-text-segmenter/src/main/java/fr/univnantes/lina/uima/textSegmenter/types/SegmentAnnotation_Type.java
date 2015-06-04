
/* First created by JCasGen Wed Mar 02 17:28:18 CET 2011 */
package fr.univnantes.lina.uima.textSegmenter.types;

import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.cas.impl.CASImpl;
import org.apache.uima.cas.impl.FSGenerator;
import org.apache.uima.cas.FeatureStructure;
import org.apache.uima.cas.impl.TypeImpl;
import org.apache.uima.cas.Type;
import org.apache.uima.jcas.tcas.Annotation_Type;

/** Default text segmentation annotation
 * Updated by JCasGen Fri Dec 09 17:21:14 CET 2011
 * @generated */
public class SegmentAnnotation_Type extends Annotation_Type {
  /** @generated */
  protected FSGenerator getFSGenerator() {return fsGenerator;}
  /** @generated */
  private final FSGenerator fsGenerator = 
    new FSGenerator() {
      public FeatureStructure createFS(int addr, CASImpl cas) {
  			 if (SegmentAnnotation_Type.this.useExistingInstance) {
  			   // Return eq fs instance if already created
  		     FeatureStructure fs = SegmentAnnotation_Type.this.jcas.getJfsFromCaddr(addr);
  		     if (null == fs) {
  		       fs = new SegmentAnnotation(addr, SegmentAnnotation_Type.this);
  			   SegmentAnnotation_Type.this.jcas.putJfsFromCaddr(addr, fs);
  			   return fs;
  		     }
  		     return fs;
        } else return new SegmentAnnotation(addr, SegmentAnnotation_Type.this);
  	  }
    };
  /** @generated */
  public final static int typeIndexID = SegmentAnnotation.typeIndexID;
  /** @generated 
     @modifiable */
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("fr.univnantes.lina.uima.textSegmenter.types.SegmentAnnotation");



  /** initialize variables to correspond with Cas Type and Features
	* @generated */
  public SegmentAnnotation_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

  }
}



    