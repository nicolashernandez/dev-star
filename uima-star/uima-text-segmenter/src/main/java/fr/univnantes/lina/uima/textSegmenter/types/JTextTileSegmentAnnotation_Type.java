
/* First created by JCasGen Tue Mar 08 14:02:41 CET 2011 */
package fr.univnantes.lina.uima.textSegmenter.types;

import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.cas.impl.CASImpl;
import org.apache.uima.cas.impl.FSGenerator;
import org.apache.uima.cas.FeatureStructure;
import org.apache.uima.cas.impl.TypeImpl;
import org.apache.uima.cas.Type;
import org.apache.uima.jcas.tcas.Annotation_Type;

/** Default Segment annotation
 * Updated by JCasGen Tue Mar 08 14:02:41 CET 2011
 * @generated */
public class JTextTileSegmentAnnotation_Type extends Annotation_Type {
  /** @generated */
  protected FSGenerator getFSGenerator() {return fsGenerator;}
  /** @generated */
  private final FSGenerator fsGenerator = 
    new FSGenerator() {
      public FeatureStructure createFS(int addr, CASImpl cas) {
  			 if (JTextTileSegmentAnnotation_Type.this.useExistingInstance) {
  			   // Return eq fs instance if already created
  		     FeatureStructure fs = JTextTileSegmentAnnotation_Type.this.jcas.getJfsFromCaddr(addr);
  		     if (null == fs) {
  		       fs = new JTextTileSegmentAnnotation(addr, JTextTileSegmentAnnotation_Type.this);
  			   JTextTileSegmentAnnotation_Type.this.jcas.putJfsFromCaddr(addr, fs);
  			   return fs;
  		     }
  		     return fs;
        } else return new JTextTileSegmentAnnotation(addr, JTextTileSegmentAnnotation_Type.this);
  	  }
    };
  /** @generated */
  public final static int typeIndexID = JTextTileSegmentAnnotation.typeIndexID;
  /** @generated 
     @modifiable */
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("fr.univnantes.lina.uima.textSegmenter.types.JTextTileSegmentAnnotation");



  /** initialize variables to correspond with Cas Type and Features
	* @generated */
  public JTextTileSegmentAnnotation_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

  }
}



    