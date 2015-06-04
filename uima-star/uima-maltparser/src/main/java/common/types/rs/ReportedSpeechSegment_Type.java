
/* First created by JCasGen Thu May 30 11:49:30 CEST 2013 */
package common.types.rs;

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
public class ReportedSpeechSegment_Type extends ReportedSpeech_Type {
  /** @generated */
  @Override
  protected FSGenerator getFSGenerator() {return fsGenerator;}
  /** @generated */
  private final FSGenerator fsGenerator = 
    new FSGenerator() {
      public FeatureStructure createFS(int addr, CASImpl cas) {
  			 if (ReportedSpeechSegment_Type.this.useExistingInstance) {
  			   // Return eq fs instance if already created
  		     FeatureStructure fs = ReportedSpeechSegment_Type.this.jcas.getJfsFromCaddr(addr);
  		     if (null == fs) {
  		       fs = new ReportedSpeechSegment(addr, ReportedSpeechSegment_Type.this);
  			   ReportedSpeechSegment_Type.this.jcas.putJfsFromCaddr(addr, fs);
  			   return fs;
  		     }
  		     return fs;
        } else return new ReportedSpeechSegment(addr, ReportedSpeechSegment_Type.this);
  	  }
    };
  /** @generated */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = ReportedSpeechSegment.typeIndexID;
  /** @generated 
     @modifiable */
  @SuppressWarnings ("hiding")
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("common.types.rs.ReportedSpeechSegment");
 
  /** @generated */
  final Feature casFeat_source;
  /** @generated */
  final int     casFeatCode_source;
  /** @generated */ 
  public String getSource(int addr) {
        if (featOkTst && casFeat_source == null)
      jcas.throwFeatMissing("source", "common.types.rs.ReportedSpeechSegment");
    return ll_cas.ll_getStringValue(addr, casFeatCode_source);
  }
  /** @generated */    
  public void setSource(int addr, String v) {
        if (featOkTst && casFeat_source == null)
      jcas.throwFeatMissing("source", "common.types.rs.ReportedSpeechSegment");
    ll_cas.ll_setStringValue(addr, casFeatCode_source, v);}
    
  
 
  /** @generated */
  final Feature casFeat_discourse;
  /** @generated */
  final int     casFeatCode_discourse;
  /** @generated */ 
  public String getDiscourse(int addr) {
        if (featOkTst && casFeat_discourse == null)
      jcas.throwFeatMissing("discourse", "common.types.rs.ReportedSpeechSegment");
    return ll_cas.ll_getStringValue(addr, casFeatCode_discourse);
  }
  /** @generated */    
  public void setDiscourse(int addr, String v) {
        if (featOkTst && casFeat_discourse == null)
      jcas.throwFeatMissing("discourse", "common.types.rs.ReportedSpeechSegment");
    ll_cas.ll_setStringValue(addr, casFeatCode_discourse, v);}
    
  



  /** initialize variables to correspond with Cas Type and Features
	* @generated */
  public ReportedSpeechSegment_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_source = jcas.getRequiredFeatureDE(casType, "source", "uima.cas.String", featOkTst);
    casFeatCode_source  = (null == casFeat_source) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_source).getCode();

 
    casFeat_discourse = jcas.getRequiredFeatureDE(casType, "discourse", "uima.cas.String", featOkTst);
    casFeatCode_discourse  = (null == casFeat_discourse) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_discourse).getCode();

  }
}



    