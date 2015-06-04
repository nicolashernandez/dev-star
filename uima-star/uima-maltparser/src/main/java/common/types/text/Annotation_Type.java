
/* First created by JCasGen Thu May 30 11:49:30 CEST 2013 */
package common.types.text;

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
 * Updated by JCasGen Thu May 30 11:49:30 CEST 2013
 * @generated */
public class Annotation_Type extends common.types.Annotation_Type {
  /** @generated */
  @Override
  protected FSGenerator getFSGenerator() {return fsGenerator;}
  /** @generated */
  private final FSGenerator fsGenerator = 
    new FSGenerator() {
      public FeatureStructure createFS(int addr, CASImpl cas) {
  			 if (Annotation_Type.this.useExistingInstance) {
  			   // Return eq fs instance if already created
  		     FeatureStructure fs = Annotation_Type.this.jcas.getJfsFromCaddr(addr);
  		     if (null == fs) {
  		       fs = new Annotation(addr, Annotation_Type.this);
  			   Annotation_Type.this.jcas.putJfsFromCaddr(addr, fs);
  			   return fs;
  		     }
  		     return fs;
        } else return new Annotation(addr, Annotation_Type.this);
  	  }
    };
  /** @generated */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = Annotation.typeIndexID;
  /** @generated 
     @modifiable */
  @SuppressWarnings ("hiding")
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("common.types.text.Annotation");
 
  /** @generated */
  final Feature casFeat_annotationList;
  /** @generated */
  final int     casFeatCode_annotationList;
  /** @generated */ 
  public int getAnnotationList(int addr) {
        if (featOkTst && casFeat_annotationList == null)
      jcas.throwFeatMissing("annotationList", "common.types.text.Annotation");
    return ll_cas.ll_getRefValue(addr, casFeatCode_annotationList);
  }
  /** @generated */    
  public void setAnnotationList(int addr, int v) {
        if (featOkTst && casFeat_annotationList == null)
      jcas.throwFeatMissing("annotationList", "common.types.text.Annotation");
    ll_cas.ll_setRefValue(addr, casFeatCode_annotationList, v);}
    
   /** @generated */
  public int getAnnotationList(int addr, int i) {
        if (featOkTst && casFeat_annotationList == null)
      jcas.throwFeatMissing("annotationList", "common.types.text.Annotation");
    if (lowLevelTypeChecks)
      return ll_cas.ll_getRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_annotationList), i, true);
    jcas.checkArrayBounds(ll_cas.ll_getRefValue(addr, casFeatCode_annotationList), i);
	return ll_cas.ll_getRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_annotationList), i);
  }
   
  /** @generated */ 
  public void setAnnotationList(int addr, int i, int v) {
        if (featOkTst && casFeat_annotationList == null)
      jcas.throwFeatMissing("annotationList", "common.types.text.Annotation");
    if (lowLevelTypeChecks)
      ll_cas.ll_setRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_annotationList), i, v, true);
    jcas.checkArrayBounds(ll_cas.ll_getRefValue(addr, casFeatCode_annotationList), i);
    ll_cas.ll_setRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_annotationList), i, v);
  }
 



  /** initialize variables to correspond with Cas Type and Features
	* @generated */
  public Annotation_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_annotationList = jcas.getRequiredFeatureDE(casType, "annotationList", "uima.cas.FSArray", featOkTst);
    casFeatCode_annotationList  = (null == casFeat_annotationList) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_annotationList).getCode();

  }
}



    