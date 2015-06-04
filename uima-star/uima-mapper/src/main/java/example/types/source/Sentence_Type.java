
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

/** 
 * Updated by JCasGen Sun Oct 03 09:51:22 CEST 2010
 * @generated */
public class Sentence_Type extends Annotation_Type {
  /** @generated */
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
  public final static int typeIndexID = Sentence.typeIndexID;
  /** @generated 
     @modifiable */
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("example.types.source.Sentence");
 
  /** @generated */
  final Feature casFeat_wordArray;
  /** @generated */
  final int     casFeatCode_wordArray;
  /** @generated */ 
  public int getWordArray(int addr) {
        if (featOkTst && casFeat_wordArray == null)
      jcas.throwFeatMissing("wordArray", "example.types.source.Sentence");
    return ll_cas.ll_getRefValue(addr, casFeatCode_wordArray);
  }
  /** @generated */    
  public void setWordArray(int addr, int v) {
        if (featOkTst && casFeat_wordArray == null)
      jcas.throwFeatMissing("wordArray", "example.types.source.Sentence");
    ll_cas.ll_setRefValue(addr, casFeatCode_wordArray, v);}
    
   /** @generated */
  public int getWordArray(int addr, int i) {
        if (featOkTst && casFeat_wordArray == null)
      jcas.throwFeatMissing("wordArray", "example.types.source.Sentence");
    if (lowLevelTypeChecks)
      return ll_cas.ll_getRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_wordArray), i, true);
    jcas.checkArrayBounds(ll_cas.ll_getRefValue(addr, casFeatCode_wordArray), i);
  return ll_cas.ll_getRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_wordArray), i);
  }
   
  /** @generated */ 
  public void setWordArray(int addr, int i, int v) {
        if (featOkTst && casFeat_wordArray == null)
      jcas.throwFeatMissing("wordArray", "example.types.source.Sentence");
    if (lowLevelTypeChecks)
      ll_cas.ll_setRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_wordArray), i, v, true);
    jcas.checkArrayBounds(ll_cas.ll_getRefValue(addr, casFeatCode_wordArray), i);
    ll_cas.ll_setRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_wordArray), i, v);
  }
 
 
  /** @generated */
  final Feature casFeat_stringArray;
  /** @generated */
  final int     casFeatCode_stringArray;
  /** @generated */ 
  public int getStringArray(int addr) {
        if (featOkTst && casFeat_stringArray == null)
      jcas.throwFeatMissing("stringArray", "example.types.source.Sentence");
    return ll_cas.ll_getRefValue(addr, casFeatCode_stringArray);
  }
  /** @generated */    
  public void setStringArray(int addr, int v) {
        if (featOkTst && casFeat_stringArray == null)
      jcas.throwFeatMissing("stringArray", "example.types.source.Sentence");
    ll_cas.ll_setRefValue(addr, casFeatCode_stringArray, v);}
    
   /** @generated */
  public String getStringArray(int addr, int i) {
        if (featOkTst && casFeat_stringArray == null)
      jcas.throwFeatMissing("stringArray", "example.types.source.Sentence");
    if (lowLevelTypeChecks)
      return ll_cas.ll_getStringArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_stringArray), i, true);
    jcas.checkArrayBounds(ll_cas.ll_getRefValue(addr, casFeatCode_stringArray), i);
  return ll_cas.ll_getStringArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_stringArray), i);
  }
   
  /** @generated */ 
  public void setStringArray(int addr, int i, String v) {
        if (featOkTst && casFeat_stringArray == null)
      jcas.throwFeatMissing("stringArray", "example.types.source.Sentence");
    if (lowLevelTypeChecks)
      ll_cas.ll_setStringArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_stringArray), i, v, true);
    jcas.checkArrayBounds(ll_cas.ll_getRefValue(addr, casFeatCode_stringArray), i);
    ll_cas.ll_setStringArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_stringArray), i, v);
  }
 



  /** initialize variables to correspond with Cas Type and Features
	* @generated */
  public Sentence_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_wordArray = jcas.getRequiredFeatureDE(casType, "wordArray", "uima.cas.FSArray", featOkTst);
    casFeatCode_wordArray  = (null == casFeat_wordArray) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_wordArray).getCode();

 
    casFeat_stringArray = jcas.getRequiredFeatureDE(casType, "stringArray", "uima.cas.StringArray", featOkTst);
    casFeatCode_stringArray  = (null == casFeat_stringArray) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_stringArray).getCode();

  }
}



    