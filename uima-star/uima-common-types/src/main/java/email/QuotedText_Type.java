
/* First created by JCasGen Wed May 27 22:43:37 CEST 2015 */
package email;

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

/** Consecutive sequence of quoted lines
 * Updated by JCasGen Wed May 27 22:43:37 CEST 2015
 * @generated */
public class QuotedText_Type extends Annotation_Type {
  /** @generated 
   * @return the generator for this type
   */
  @Override
  protected FSGenerator getFSGenerator() {return fsGenerator;}
  /** @generated */
  private final FSGenerator fsGenerator = 
    new FSGenerator() {
      public FeatureStructure createFS(int addr, CASImpl cas) {
  			 if (QuotedText_Type.this.useExistingInstance) {
  			   // Return eq fs instance if already created
  		     FeatureStructure fs = QuotedText_Type.this.jcas.getJfsFromCaddr(addr);
  		     if (null == fs) {
  		       fs = new QuotedText(addr, QuotedText_Type.this);
  			   QuotedText_Type.this.jcas.putJfsFromCaddr(addr, fs);
  			   return fs;
  		     }
  		     return fs;
        } else return new QuotedText(addr, QuotedText_Type.this);
  	  }
    };
  /** @generated */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = QuotedText.typeIndexID;
  /** @generated 
     @modifiable */
  @SuppressWarnings ("hiding")
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("email.QuotedText");
 
  /** @generated */
  final Feature casFeat_attribution;
  /** @generated */
  final int     casFeatCode_attribution;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public int getAttribution(int addr) {
        if (featOkTst && casFeat_attribution == null)
      jcas.throwFeatMissing("attribution", "email.QuotedText");
    return ll_cas.ll_getRefValue(addr, casFeatCode_attribution);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setAttribution(int addr, int v) {
        if (featOkTst && casFeat_attribution == null)
      jcas.throwFeatMissing("attribution", "email.QuotedText");
    ll_cas.ll_setRefValue(addr, casFeatCode_attribution, v);}
    
  



  /** initialize variables to correspond with Cas Type and Features
	 * @generated
	 * @param jcas JCas
	 * @param casType Type 
	 */
  public QuotedText_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_attribution = jcas.getRequiredFeatureDE(casType, "attribution", "email.AttributionLine", featOkTst);
    casFeatCode_attribution  = (null == casFeat_attribution) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_attribution).getCode();

  }
}



    