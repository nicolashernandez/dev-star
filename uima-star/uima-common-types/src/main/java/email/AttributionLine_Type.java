
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

/** 
 * Updated by JCasGen Wed May 27 22:43:37 CEST 2015
 * @generated */
public class AttributionLine_Type extends Annotation_Type {
  /** @generated 
   * @return the generator for this type
   */
  @Override
  protected FSGenerator getFSGenerator() {return fsGenerator;}
  /** @generated */
  private final FSGenerator fsGenerator = 
    new FSGenerator() {
      public FeatureStructure createFS(int addr, CASImpl cas) {
  			 if (AttributionLine_Type.this.useExistingInstance) {
  			   // Return eq fs instance if already created
  		     FeatureStructure fs = AttributionLine_Type.this.jcas.getJfsFromCaddr(addr);
  		     if (null == fs) {
  		       fs = new AttributionLine(addr, AttributionLine_Type.this);
  			   AttributionLine_Type.this.jcas.putJfsFromCaddr(addr, fs);
  			   return fs;
  		     }
  		     return fs;
        } else return new AttributionLine(addr, AttributionLine_Type.this);
  	  }
    };
  /** @generated */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = AttributionLine.typeIndexID;
  /** @generated 
     @modifiable */
  @SuppressWarnings ("hiding")
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("email.AttributionLine");
 
  /** @generated */
  final Feature casFeat_quotedText;
  /** @generated */
  final int     casFeatCode_quotedText;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public int getQuotedText(int addr) {
        if (featOkTst && casFeat_quotedText == null)
      jcas.throwFeatMissing("quotedText", "email.AttributionLine");
    return ll_cas.ll_getRefValue(addr, casFeatCode_quotedText);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setQuotedText(int addr, int v) {
        if (featOkTst && casFeat_quotedText == null)
      jcas.throwFeatMissing("quotedText", "email.AttributionLine");
    ll_cas.ll_setRefValue(addr, casFeatCode_quotedText, v);}
    
  



  /** initialize variables to correspond with Cas Type and Features
	 * @generated
	 * @param jcas JCas
	 * @param casType Type 
	 */
  public AttributionLine_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_quotedText = jcas.getRequiredFeatureDE(casType, "quotedText", "email.QuotedText", featOkTst);
    casFeatCode_quotedText  = (null == casFeat_quotedText) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_quotedText).getCode();

  }
}



    