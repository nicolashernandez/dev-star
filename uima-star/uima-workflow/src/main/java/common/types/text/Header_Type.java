
/* First created by JCasGen Thu May 30 13:40:53 CEST 2013 */
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
 * Updated by JCasGen Thu May 30 13:40:53 CEST 2013
 * @generated */
public class Header_Type extends Annotation_Type {
  /** @generated */
  @Override
  protected FSGenerator getFSGenerator() {return fsGenerator;}
  /** @generated */
  private final FSGenerator fsGenerator = 
    new FSGenerator() {
      public FeatureStructure createFS(int addr, CASImpl cas) {
  			 if (Header_Type.this.useExistingInstance) {
  			   // Return eq fs instance if already created
  		     FeatureStructure fs = Header_Type.this.jcas.getJfsFromCaddr(addr);
  		     if (null == fs) {
  		       fs = new Header(addr, Header_Type.this);
  			   Header_Type.this.jcas.putJfsFromCaddr(addr, fs);
  			   return fs;
  		     }
  		     return fs;
        } else return new Header(addr, Header_Type.this);
  	  }
    };
  /** @generated */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = Header.typeIndexID;
  /** @generated 
     @modifiable */
  @SuppressWarnings ("hiding")
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("common.types.text.Header");
 
  /** @generated */
  final Feature casFeat_level;
  /** @generated */
  final int     casFeatCode_level;
  /** @generated */ 
  public int getLevel(int addr) {
        if (featOkTst && casFeat_level == null)
      jcas.throwFeatMissing("level", "common.types.text.Header");
    return ll_cas.ll_getIntValue(addr, casFeatCode_level);
  }
  /** @generated */    
  public void setLevel(int addr, int v) {
        if (featOkTst && casFeat_level == null)
      jcas.throwFeatMissing("level", "common.types.text.Header");
    ll_cas.ll_setIntValue(addr, casFeatCode_level, v);}
    
  



  /** initialize variables to correspond with Cas Type and Features
	* @generated */
  public Header_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_level = jcas.getRequiredFeatureDE(casType, "level", "uima.cas.Integer", featOkTst);
    casFeatCode_level  = (null == casFeat_level) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_level).getCode();

  }
}



    