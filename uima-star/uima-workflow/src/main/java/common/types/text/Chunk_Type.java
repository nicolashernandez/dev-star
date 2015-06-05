
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
public class Chunk_Type extends Annotation_Type {
  /** @generated */
  @Override
  protected FSGenerator getFSGenerator() {return fsGenerator;}
  /** @generated */
  private final FSGenerator fsGenerator = 
    new FSGenerator() {
      public FeatureStructure createFS(int addr, CASImpl cas) {
  			 if (Chunk_Type.this.useExistingInstance) {
  			   // Return eq fs instance if already created
  		     FeatureStructure fs = Chunk_Type.this.jcas.getJfsFromCaddr(addr);
  		     if (null == fs) {
  		       fs = new Chunk(addr, Chunk_Type.this);
  			   Chunk_Type.this.jcas.putJfsFromCaddr(addr, fs);
  			   return fs;
  		     }
  		     return fs;
        } else return new Chunk(addr, Chunk_Type.this);
  	  }
    };
  /** @generated */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = Chunk.typeIndexID;
  /** @generated 
     @modifiable */
  @SuppressWarnings ("hiding")
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("common.types.text.Chunk");
 
  /** @generated */
  final Feature casFeat_chunkType;
  /** @generated */
  final int     casFeatCode_chunkType;
  /** @generated */ 
  public String getChunkType(int addr) {
        if (featOkTst && casFeat_chunkType == null)
      jcas.throwFeatMissing("chunkType", "common.types.text.Chunk");
    return ll_cas.ll_getStringValue(addr, casFeatCode_chunkType);
  }
  /** @generated */    
  public void setChunkType(int addr, String v) {
        if (featOkTst && casFeat_chunkType == null)
      jcas.throwFeatMissing("chunkType", "common.types.text.Chunk");
    ll_cas.ll_setStringValue(addr, casFeatCode_chunkType, v);}
    
  
 
  /** @generated */
  final Feature casFeat_fct;
  /** @generated */
  final int     casFeatCode_fct;
  /** @generated */ 
  public String getFct(int addr) {
        if (featOkTst && casFeat_fct == null)
      jcas.throwFeatMissing("fct", "common.types.text.Chunk");
    return ll_cas.ll_getStringValue(addr, casFeatCode_fct);
  }
  /** @generated */    
  public void setFct(int addr, String v) {
        if (featOkTst && casFeat_fct == null)
      jcas.throwFeatMissing("fct", "common.types.text.Chunk");
    ll_cas.ll_setStringValue(addr, casFeatCode_fct, v);}
    
  



  /** initialize variables to correspond with Cas Type and Features
	* @generated */
  public Chunk_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_chunkType = jcas.getRequiredFeatureDE(casType, "chunkType", "uima.cas.String", featOkTst);
    casFeatCode_chunkType  = (null == casFeat_chunkType) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_chunkType).getCode();

 
    casFeat_fct = jcas.getRequiredFeatureDE(casType, "fct", "uima.cas.String", featOkTst);
    casFeatCode_fct  = (null == casFeat_fct) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_fct).getCode();

  }
}



    