
/* First created by JCasGen Thu Apr 15 15:16:12 CEST 2010 */
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

/** Single token annotation
 * Updated by JCasGen Sun Oct 03 09:51:22 CEST 2010
 * @generated */
public class Word_Type extends Annotation_Type {
  /** @generated */
  protected FSGenerator getFSGenerator() {return fsGenerator;}
  /** @generated */
  private final FSGenerator fsGenerator = 
    new FSGenerator() {
      public FeatureStructure createFS(int addr, CASImpl cas) {
  			 if (Word_Type.this.useExistingInstance) {
  			   // Return eq fs instance if already created
  		     FeatureStructure fs = Word_Type.this.jcas.getJfsFromCaddr(addr);
  		     if (null == fs) {
  		       fs = new Word(addr, Word_Type.this);
  			   Word_Type.this.jcas.putJfsFromCaddr(addr, fs);
  			   return fs;
  		     }
  		     return fs;
        } else return new Word(addr, Word_Type.this);
  	  }
    };
  /** @generated */
  public final static int typeIndexID = Word.typeIndexID;
  /** @generated 
     @modifiable */
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("example.types.source.Word");
 
  /** @generated */
  final Feature casFeat_posString;
  /** @generated */
  final int     casFeatCode_posString;
  /** @generated */ 
  public String getPosString(int addr) {
        if (featOkTst && casFeat_posString == null)
      jcas.throwFeatMissing("posString", "example.types.source.Word");
    return ll_cas.ll_getStringValue(addr, casFeatCode_posString);
  }
  /** @generated */    
  public void setPosString(int addr, String v) {
        if (featOkTst && casFeat_posString == null)
      jcas.throwFeatMissing("posString", "example.types.source.Word");
    ll_cas.ll_setStringValue(addr, casFeatCode_posString, v);}
    
  
 
  /** @generated */
  final Feature casFeat_posType;
  /** @generated */
  final int     casFeatCode_posType;
  /** @generated */ 
  public int getPosType(int addr) {
        if (featOkTst && casFeat_posType == null)
      jcas.throwFeatMissing("posType", "example.types.source.Word");
    return ll_cas.ll_getRefValue(addr, casFeatCode_posType);
  }
  /** @generated */    
  public void setPosType(int addr, int v) {
        if (featOkTst && casFeat_posType == null)
      jcas.throwFeatMissing("posType", "example.types.source.Word");
    ll_cas.ll_setRefValue(addr, casFeatCode_posType, v);}
    
  
 
  /** @generated */
  final Feature casFeat_sizeInt;
  /** @generated */
  final int     casFeatCode_sizeInt;
  /** @generated */ 
  public int getSizeInt(int addr) {
        if (featOkTst && casFeat_sizeInt == null)
      jcas.throwFeatMissing("sizeInt", "example.types.source.Word");
    return ll_cas.ll_getIntValue(addr, casFeatCode_sizeInt);
  }
  /** @generated */    
  public void setSizeInt(int addr, int v) {
        if (featOkTst && casFeat_sizeInt == null)
      jcas.throwFeatMissing("sizeInt", "example.types.source.Word");
    ll_cas.ll_setIntValue(addr, casFeatCode_sizeInt, v);}
    
  



  /** initialize variables to correspond with Cas Type and Features
	* @generated */
  public Word_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_posString = jcas.getRequiredFeatureDE(casType, "posString", "uima.cas.String", featOkTst);
    casFeatCode_posString  = (null == casFeat_posString) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_posString).getCode();

 
    casFeat_posType = jcas.getRequiredFeatureDE(casType, "posType", "example.types.source.POS", featOkTst);
    casFeatCode_posType  = (null == casFeat_posType) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_posType).getCode();

 
    casFeat_sizeInt = jcas.getRequiredFeatureDE(casType, "sizeInt", "uima.cas.Integer", featOkTst);
    casFeatCode_sizeInt  = (null == casFeat_sizeInt) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_sizeInt).getCode();

  }
}



    