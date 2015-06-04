
/* First created by JCasGen Sat Dec 10 00:59:10 CET 2011 */
package common.types.resource;

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
 * Updated by JCasGen Sat Dec 10 00:59:12 CET 2011
 * @generated */
public class DictEntry_Type extends Annotation_Type {
  /** @generated */
  protected FSGenerator getFSGenerator() {return fsGenerator;}
  /** @generated */
  private final FSGenerator fsGenerator = 
    new FSGenerator() {
      public FeatureStructure createFS(int addr, CASImpl cas) {
  			 if (DictEntry_Type.this.useExistingInstance) {
  			   // Return eq fs instance if already created
  		     FeatureStructure fs = DictEntry_Type.this.jcas.getJfsFromCaddr(addr);
  		     if (null == fs) {
  		       fs = new DictEntry(addr, DictEntry_Type.this);
  			   DictEntry_Type.this.jcas.putJfsFromCaddr(addr, fs);
  			   return fs;
  		     }
  		     return fs;
        } else return new DictEntry(addr, DictEntry_Type.this);
  	  }
    };
  /** @generated */
  public final static int typeIndexID = DictEntry.typeIndexID;
  /** @generated 
     @modifiable */
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("common.types.resource.DictEntry");
 
  /** @generated */
  final Feature casFeat_values;
  /** @generated */
  final int     casFeatCode_values;
  /** @generated */ 
  public int getValues(int addr) {
        if (featOkTst && casFeat_values == null)
      jcas.throwFeatMissing("values", "common.types.resource.DictEntry");
    return ll_cas.ll_getRefValue(addr, casFeatCode_values);
  }
  /** @generated */    
  public void setValues(int addr, int v) {
        if (featOkTst && casFeat_values == null)
      jcas.throwFeatMissing("values", "common.types.resource.DictEntry");
    ll_cas.ll_setRefValue(addr, casFeatCode_values, v);}
    
   /** @generated */
  public String getValues(int addr, int i) {
        if (featOkTst && casFeat_values == null)
      jcas.throwFeatMissing("values", "common.types.resource.DictEntry");
    if (lowLevelTypeChecks)
      return ll_cas.ll_getStringArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_values), i, true);
    jcas.checkArrayBounds(ll_cas.ll_getRefValue(addr, casFeatCode_values), i);
  return ll_cas.ll_getStringArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_values), i);
  }
   
  /** @generated */ 
  public void setValues(int addr, int i, String v) {
        if (featOkTst && casFeat_values == null)
      jcas.throwFeatMissing("values", "common.types.resource.DictEntry");
    if (lowLevelTypeChecks)
      ll_cas.ll_setStringArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_values), i, v, true);
    jcas.checkArrayBounds(ll_cas.ll_getRefValue(addr, casFeatCode_values), i);
    ll_cas.ll_setStringArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_values), i, v);
  }
 
 
  /** @generated */
  final Feature casFeat_value;
  /** @generated */
  final int     casFeatCode_value;
  /** @generated */ 
  public String getValue(int addr) {
        if (featOkTst && casFeat_value == null)
      jcas.throwFeatMissing("value", "common.types.resource.DictEntry");
    return ll_cas.ll_getStringValue(addr, casFeatCode_value);
  }
  /** @generated */    
  public void setValue(int addr, String v) {
        if (featOkTst && casFeat_value == null)
      jcas.throwFeatMissing("value", "common.types.resource.DictEntry");
    ll_cas.ll_setStringValue(addr, casFeatCode_value, v);}
    
  



  /** initialize variables to correspond with Cas Type and Features
	* @generated */
  public DictEntry_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_values = jcas.getRequiredFeatureDE(casType, "values", "uima.cas.StringArray", featOkTst);
    casFeatCode_values  = (null == casFeat_values) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_values).getCode();

 
    casFeat_value = jcas.getRequiredFeatureDE(casType, "value", "uima.cas.String", featOkTst);
    casFeatCode_value  = (null == casFeat_value) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_value).getCode();

  }
}



    