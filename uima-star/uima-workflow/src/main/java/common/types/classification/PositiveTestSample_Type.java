
/* First created by JCasGen Thu May 30 13:40:53 CEST 2013 */
package common.types.classification;

import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.cas.impl.CASImpl;
import org.apache.uima.cas.impl.FSGenerator;
import org.apache.uima.cas.FeatureStructure;
import org.apache.uima.cas.impl.TypeImpl;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.impl.FeatureImpl;
import org.apache.uima.cas.Feature;
import common.types.text.Annotation_Type;

/** 
 * Updated by JCasGen Thu May 30 13:40:53 CEST 2013
 * @generated */
public class PositiveTestSample_Type extends Annotation_Type {
  /** @generated */
  @Override
  protected FSGenerator getFSGenerator() {return fsGenerator;}
  /** @generated */
  private final FSGenerator fsGenerator = 
    new FSGenerator() {
      public FeatureStructure createFS(int addr, CASImpl cas) {
  			 if (PositiveTestSample_Type.this.useExistingInstance) {
  			   // Return eq fs instance if already created
  		     FeatureStructure fs = PositiveTestSample_Type.this.jcas.getJfsFromCaddr(addr);
  		     if (null == fs) {
  		       fs = new PositiveTestSample(addr, PositiveTestSample_Type.this);
  			   PositiveTestSample_Type.this.jcas.putJfsFromCaddr(addr, fs);
  			   return fs;
  		     }
  		     return fs;
        } else return new PositiveTestSample(addr, PositiveTestSample_Type.this);
  	  }
    };
  /** @generated */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = PositiveTestSample.typeIndexID;
  /** @generated 
     @modifiable */
  @SuppressWarnings ("hiding")
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("common.types.classification.PositiveTestSample");
 
  /** @generated */
  final Feature casFeat_label;
  /** @generated */
  final int     casFeatCode_label;
  /** @generated */ 
  public String getLabel(int addr) {
        if (featOkTst && casFeat_label == null)
      jcas.throwFeatMissing("label", "common.types.classification.PositiveTestSample");
    return ll_cas.ll_getStringValue(addr, casFeatCode_label);
  }
  /** @generated */    
  public void setLabel(int addr, String v) {
        if (featOkTst && casFeat_label == null)
      jcas.throwFeatMissing("label", "common.types.classification.PositiveTestSample");
    ll_cas.ll_setStringValue(addr, casFeatCode_label, v);}
    
  



  /** initialize variables to correspond with Cas Type and Features
	* @generated */
  public PositiveTestSample_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_label = jcas.getRequiredFeatureDE(casType, "label", "uima.cas.String", featOkTst);
    casFeatCode_label  = (null == casFeat_label) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_label).getCode();

  }
}



    