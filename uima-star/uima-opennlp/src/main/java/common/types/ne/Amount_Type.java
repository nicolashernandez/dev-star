
/* First created by JCasGen Sat Dec 10 00:59:10 CET 2011 */
package common.types.ne;

import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.cas.impl.CASImpl;
import org.apache.uima.cas.impl.FSGenerator;
import org.apache.uima.cas.FeatureStructure;
import org.apache.uima.cas.impl.TypeImpl;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.impl.FeatureImpl;
import org.apache.uima.cas.Feature;

/** * amount
    * subCategory:length volume weight temperature area currency speed other
    * amountDigit
    * amountLetter
    * amountUnit
 * Updated by JCasGen Sat Dec 10 00:59:12 CET 2011
 * @generated */
public class Amount_Type extends NamedEntity_Type {
  /** @generated */
  protected FSGenerator getFSGenerator() {return fsGenerator;}
  /** @generated */
  private final FSGenerator fsGenerator = 
    new FSGenerator() {
      public FeatureStructure createFS(int addr, CASImpl cas) {
  			 if (Amount_Type.this.useExistingInstance) {
  			   // Return eq fs instance if already created
  		     FeatureStructure fs = Amount_Type.this.jcas.getJfsFromCaddr(addr);
  		     if (null == fs) {
  		       fs = new Amount(addr, Amount_Type.this);
  			   Amount_Type.this.jcas.putJfsFromCaddr(addr, fs);
  			   return fs;
  		     }
  		     return fs;
        } else return new Amount(addr, Amount_Type.this);
  	  }
    };
  /** @generated */
  public final static int typeIndexID = Amount.typeIndexID;
  /** @generated 
     @modifiable */
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("common.types.ne.Amount");
 
  /** @generated */
  final Feature casFeat_amountDigit;
  /** @generated */
  final int     casFeatCode_amountDigit;
  /** @generated */ 
  public float getAmountDigit(int addr) {
        if (featOkTst && casFeat_amountDigit == null)
      jcas.throwFeatMissing("amountDigit", "common.types.ne.Amount");
    return ll_cas.ll_getFloatValue(addr, casFeatCode_amountDigit);
  }
  /** @generated */    
  public void setAmountDigit(int addr, float v) {
        if (featOkTst && casFeat_amountDigit == null)
      jcas.throwFeatMissing("amountDigit", "common.types.ne.Amount");
    ll_cas.ll_setFloatValue(addr, casFeatCode_amountDigit, v);}
    
  
 
  /** @generated */
  final Feature casFeat_amountText;
  /** @generated */
  final int     casFeatCode_amountText;
  /** @generated */ 
  public String getAmountText(int addr) {
        if (featOkTst && casFeat_amountText == null)
      jcas.throwFeatMissing("amountText", "common.types.ne.Amount");
    return ll_cas.ll_getStringValue(addr, casFeatCode_amountText);
  }
  /** @generated */    
  public void setAmountText(int addr, String v) {
        if (featOkTst && casFeat_amountText == null)
      jcas.throwFeatMissing("amountText", "common.types.ne.Amount");
    ll_cas.ll_setStringValue(addr, casFeatCode_amountText, v);}
    
  
 
  /** @generated */
  final Feature casFeat_amountUnit;
  /** @generated */
  final int     casFeatCode_amountUnit;
  /** @generated */ 
  public String getAmountUnit(int addr) {
        if (featOkTst && casFeat_amountUnit == null)
      jcas.throwFeatMissing("amountUnit", "common.types.ne.Amount");
    return ll_cas.ll_getStringValue(addr, casFeatCode_amountUnit);
  }
  /** @generated */    
  public void setAmountUnit(int addr, String v) {
        if (featOkTst && casFeat_amountUnit == null)
      jcas.throwFeatMissing("amountUnit", "common.types.ne.Amount");
    ll_cas.ll_setStringValue(addr, casFeatCode_amountUnit, v);}
    
  



  /** initialize variables to correspond with Cas Type and Features
	* @generated */
  public Amount_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_amountDigit = jcas.getRequiredFeatureDE(casType, "amountDigit", "uima.cas.Float", featOkTst);
    casFeatCode_amountDigit  = (null == casFeat_amountDigit) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_amountDigit).getCode();

 
    casFeat_amountText = jcas.getRequiredFeatureDE(casType, "amountText", "uima.cas.String", featOkTst);
    casFeatCode_amountText  = (null == casFeat_amountText) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_amountText).getCode();

 
    casFeat_amountUnit = jcas.getRequiredFeatureDE(casType, "amountUnit", "uima.cas.String", featOkTst);
    casFeatCode_amountUnit  = (null == casFeat_amountUnit) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_amountUnit).getCode();

  }
}



    