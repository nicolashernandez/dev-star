
/* First created by JCasGen Wed May 27 22:41:00 CEST 2015 */
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
 * Updated by JCasGen Wed May 27 22:41:00 CEST 2015
 * @generated */
public class Sentence_Type extends Annotation_Type {
  /** @generated 
   * @return the generator for this type
   */
  @Override
  protected FSGenerator getFSGenerator() {return fsGenerator;}
  /** @generated */
  private final FSGenerator fsGenerator = 
    new FSGenerator() {
      public FeatureStructure createFS(int addr, CASImpl cas) {
  			 if (Sentence_Type.this.useExistingInstance) {
  			   // Return eq fs instance if already created
  		     FeatureStructure fs = Sentence_Type.this.jcas.getJfsFromCaddr(addr);
  		     if (null == fs) {
  		       fs = new Sentence(addr, Sentence_Type.this);
  			   Sentence_Type.this.jcas.putJfsFromCaddr(addr, fs);
  			   return fs;
  		     }
  		     return fs;
        } else return new Sentence(addr, Sentence_Type.this);
  	  }
    };
  /** @generated */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = Sentence.typeIndexID;
  /** @generated 
     @modifiable */
  @SuppressWarnings ("hiding")
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("common.types.text.Sentence");
 
  /** @generated */
  final Feature casFeat_precedingNewlines;
  /** @generated */
  final int     casFeatCode_precedingNewlines;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public int getPrecedingNewlines(int addr) {
        if (featOkTst && casFeat_precedingNewlines == null)
      jcas.throwFeatMissing("precedingNewlines", "common.types.text.Sentence");
    return ll_cas.ll_getIntValue(addr, casFeatCode_precedingNewlines);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setPrecedingNewlines(int addr, int v) {
        if (featOkTst && casFeat_precedingNewlines == null)
      jcas.throwFeatMissing("precedingNewlines", "common.types.text.Sentence");
    ll_cas.ll_setIntValue(addr, casFeatCode_precedingNewlines, v);}
    
  
 
  /** @generated */
  final Feature casFeat_followingNewlines;
  /** @generated */
  final int     casFeatCode_followingNewlines;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public int getFollowingNewlines(int addr) {
        if (featOkTst && casFeat_followingNewlines == null)
      jcas.throwFeatMissing("followingNewlines", "common.types.text.Sentence");
    return ll_cas.ll_getIntValue(addr, casFeatCode_followingNewlines);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setFollowingNewlines(int addr, int v) {
        if (featOkTst && casFeat_followingNewlines == null)
      jcas.throwFeatMissing("followingNewlines", "common.types.text.Sentence");
    ll_cas.ll_setIntValue(addr, casFeatCode_followingNewlines, v);}
    
  
 
  /** @generated */
  final Feature casFeat_segmentStatus;
  /** @generated */
  final int     casFeatCode_segmentStatus;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public String getSegmentStatus(int addr) {
        if (featOkTst && casFeat_segmentStatus == null)
      jcas.throwFeatMissing("segmentStatus", "common.types.text.Sentence");
    return ll_cas.ll_getStringValue(addr, casFeatCode_segmentStatus);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setSegmentStatus(int addr, String v) {
        if (featOkTst && casFeat_segmentStatus == null)
      jcas.throwFeatMissing("segmentStatus", "common.types.text.Sentence");
    ll_cas.ll_setStringValue(addr, casFeatCode_segmentStatus, v);}
    
  
 
  /** @generated */
  final Feature casFeat_textTilingStatus;
  /** @generated */
  final int     casFeatCode_textTilingStatus;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public String getTextTilingStatus(int addr) {
        if (featOkTst && casFeat_textTilingStatus == null)
      jcas.throwFeatMissing("textTilingStatus", "common.types.text.Sentence");
    return ll_cas.ll_getStringValue(addr, casFeatCode_textTilingStatus);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setTextTilingStatus(int addr, String v) {
        if (featOkTst && casFeat_textTilingStatus == null)
      jcas.throwFeatMissing("textTilingStatus", "common.types.text.Sentence");
    ll_cas.ll_setStringValue(addr, casFeatCode_textTilingStatus, v);}
    
  



  /** initialize variables to correspond with Cas Type and Features
	 * @generated
	 * @param jcas JCas
	 * @param casType Type 
	 */
  public Sentence_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_precedingNewlines = jcas.getRequiredFeatureDE(casType, "precedingNewlines", "uima.cas.Integer", featOkTst);
    casFeatCode_precedingNewlines  = (null == casFeat_precedingNewlines) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_precedingNewlines).getCode();

 
    casFeat_followingNewlines = jcas.getRequiredFeatureDE(casType, "followingNewlines", "uima.cas.Integer", featOkTst);
    casFeatCode_followingNewlines  = (null == casFeat_followingNewlines) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_followingNewlines).getCode();

 
    casFeat_segmentStatus = jcas.getRequiredFeatureDE(casType, "segmentStatus", "uima.cas.String", featOkTst);
    casFeatCode_segmentStatus  = (null == casFeat_segmentStatus) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_segmentStatus).getCode();

 
    casFeat_textTilingStatus = jcas.getRequiredFeatureDE(casType, "textTilingStatus", "uima.cas.String", featOkTst);
    casFeatCode_textTilingStatus  = (null == casFeat_textTilingStatus) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_textTilingStatus).getCode();

  }
}



    