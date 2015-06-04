
/* First created by JCasGen Mon Apr 19 15:30:14 CEST 2010 */
package example.types.source;

import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.cas.impl.CASImpl;
import org.apache.uima.cas.impl.FSGenerator;
import org.apache.uima.cas.FeatureStructure;
import org.apache.uima.cas.impl.TypeImpl;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.impl.FeatureImpl;
import org.apache.uima.cas.Feature;
import org.apache.uima.jcas.tcas.Annotation_Type;

/** 
 * Updated by JCasGen Sun Oct 03 09:51:21 CEST 2010
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
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("example.types.source.DiscourseSegment");



  /** @generated */
  final Feature casFeat_sentenceArray;
  /** @generated */
  final int     casFeatCode_sentenceArray;
  /** @generated */ 
  public int getSentenceArray(int addr) {
        if (featOkTst && casFeat_sentenceArray == null)
      jcas.throwFeatMissing("sentenceArray", "example.types.source.DiscourseSegment");
    return ll_cas.ll_getRefValue(addr, casFeatCode_sentenceArray);
  }
  /** @generated */    
  public void setSentenceArray(int addr, int v) {
        if (featOkTst && casFeat_sentenceArray == null)
      jcas.throwFeatMissing("sentenceArray", "example.types.source.DiscourseSegment");
    ll_cas.ll_setRefValue(addr, casFeatCode_sentenceArray, v);}
    
   /** @generated */
  public int getSentenceArray(int addr, int i) {
        if (featOkTst && casFeat_sentenceArray == null)
      jcas.throwFeatMissing("sentenceArray", "example.types.source.DiscourseSegment");
    if (lowLevelTypeChecks)
      return ll_cas.ll_getRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_sentenceArray), i, true);
    jcas.checkArrayBounds(ll_cas.ll_getRefValue(addr, casFeatCode_sentenceArray), i);
  return ll_cas.ll_getRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_sentenceArray), i);
  }
   
  /** @generated */ 
  public void setSentenceArray(int addr, int i, int v) {
        if (featOkTst && casFeat_sentenceArray == null)
      jcas.throwFeatMissing("sentenceArray", "example.types.source.DiscourseSegment");
    if (lowLevelTypeChecks)
      ll_cas.ll_setRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_sentenceArray), i, v, true);
    jcas.checkArrayBounds(ll_cas.ll_getRefValue(addr, casFeatCode_sentenceArray), i);
    ll_cas.ll_setRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_sentenceArray), i, v);
  }
 



  /** initialize variables to correspond with Cas Type and Features
	* @generated */
  public DiscourseSegment_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_sentenceArray = jcas.getRequiredFeatureDE(casType, "sentenceArray", "uima.cas.FSArray", featOkTst);
    casFeatCode_sentenceArray  = (null == casFeat_sentenceArray) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_sentenceArray).getCode();

  }
}



    