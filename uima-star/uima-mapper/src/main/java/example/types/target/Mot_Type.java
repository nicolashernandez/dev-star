
/* First created by JCasGen Thu Apr 15 13:48:45 CEST 2010 */
package example.types.target;

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
 * Updated by JCasGen Mon Mar 28 15:53:10 CEST 2011
 * @generated */
public class Mot_Type extends Annotation_Type {
  /** @generated */
  protected FSGenerator getFSGenerator() {return fsGenerator;}
  /** @generated */
  private final FSGenerator fsGenerator = 
    new FSGenerator() {
      public FeatureStructure createFS(int addr, CASImpl cas) {
  			 if (Mot_Type.this.useExistingInstance) {
  			   // Return eq fs instance if already created
  		     FeatureStructure fs = Mot_Type.this.jcas.getJfsFromCaddr(addr);
  		     if (null == fs) {
  		       fs = new Mot(addr, Mot_Type.this);
  			   Mot_Type.this.jcas.putJfsFromCaddr(addr, fs);
  			   return fs;
  		     }
  		     return fs;
        } else return new Mot(addr, Mot_Type.this);
  	  }
    };
  /** @generated */
  public final static int typeIndexID = Mot.typeIndexID;
  /** @generated 
     @modifiable */
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("example.types.target.Mot");



  /** @generated */
  final Feature casFeat_pos;
  /** @generated */
  final int     casFeatCode_pos;
  /** @generated */ 
  public String getPos(int addr) {
        if (featOkTst && casFeat_pos == null)
      jcas.throwFeatMissing("pos", "example.types.target.Mot");
    return ll_cas.ll_getStringValue(addr, casFeatCode_pos);
  }
  /** @generated */    
  public void setPos(int addr, String v) {
        if (featOkTst && casFeat_pos == null)
      jcas.throwFeatMissing("pos", "example.types.target.Mot");
    ll_cas.ll_setStringValue(addr, casFeatCode_pos, v);}
    
  
 
  /** @generated */
  final Feature casFeat_size;
  /** @generated */
  final int     casFeatCode_size;
  /** @generated */ 
  public int getSize(int addr) {
        if (featOkTst && casFeat_size == null)
      jcas.throwFeatMissing("size", "example.types.target.Mot");
    return ll_cas.ll_getIntValue(addr, casFeatCode_size);
  }
  /** @generated */    
  public void setSize(int addr, int v) {
        if (featOkTst && casFeat_size == null)
      jcas.throwFeatMissing("size", "example.types.target.Mot");
    ll_cas.ll_setIntValue(addr, casFeatCode_size, v);}
    
  



  /** initialize variables to correspond with Cas Type and Features
	* @generated */
  public Mot_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_pos = jcas.getRequiredFeatureDE(casType, "pos", "uima.cas.String", featOkTst);
    casFeatCode_pos  = (null == casFeat_pos) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_pos).getCode();

 
    casFeat_size = jcas.getRequiredFeatureDE(casType, "size", "uima.cas.Integer", featOkTst);
    casFeatCode_size  = (null == casFeat_size) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_size).getCode();

  }
}



    