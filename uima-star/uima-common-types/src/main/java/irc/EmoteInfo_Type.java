
/* First created by JCasGen Wed May 27 22:43:37 CEST 2015 */
package irc;

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
 * Updated by JCasGen Wed May 27 22:43:37 CEST 2015
 * @generated */
public class EmoteInfo_Type extends Info_Type {
  /** @generated 
   * @return the generator for this type
   */
  @Override
  protected FSGenerator getFSGenerator() {return fsGenerator;}
  /** @generated */
  private final FSGenerator fsGenerator = 
    new FSGenerator() {
      public FeatureStructure createFS(int addr, CASImpl cas) {
  			 if (EmoteInfo_Type.this.useExistingInstance) {
  			   // Return eq fs instance if already created
  		     FeatureStructure fs = EmoteInfo_Type.this.jcas.getJfsFromCaddr(addr);
  		     if (null == fs) {
  		       fs = new EmoteInfo(addr, EmoteInfo_Type.this);
  			   EmoteInfo_Type.this.jcas.putJfsFromCaddr(addr, fs);
  			   return fs;
  		     }
  		     return fs;
        } else return new EmoteInfo(addr, EmoteInfo_Type.this);
  	  }
    };
  /** @generated */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = EmoteInfo.typeIndexID;
  /** @generated 
     @modifiable */
  @SuppressWarnings ("hiding")
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("irc.EmoteInfo");
 
  /** @generated */
  final Feature casFeat_fromNickname;
  /** @generated */
  final int     casFeatCode_fromNickname;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public String getFromNickname(int addr) {
        if (featOkTst && casFeat_fromNickname == null)
      jcas.throwFeatMissing("fromNickname", "irc.EmoteInfo");
    return ll_cas.ll_getStringValue(addr, casFeatCode_fromNickname);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setFromNickname(int addr, String v) {
        if (featOkTst && casFeat_fromNickname == null)
      jcas.throwFeatMissing("fromNickname", "irc.EmoteInfo");
    ll_cas.ll_setStringValue(addr, casFeatCode_fromNickname, v);}
    
  
 
  /** @generated */
  final Feature casFeat_emote;
  /** @generated */
  final int     casFeatCode_emote;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public String getEmote(int addr) {
        if (featOkTst && casFeat_emote == null)
      jcas.throwFeatMissing("emote", "irc.EmoteInfo");
    return ll_cas.ll_getStringValue(addr, casFeatCode_emote);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setEmote(int addr, String v) {
        if (featOkTst && casFeat_emote == null)
      jcas.throwFeatMissing("emote", "irc.EmoteInfo");
    ll_cas.ll_setStringValue(addr, casFeatCode_emote, v);}
    
  



  /** initialize variables to correspond with Cas Type and Features
	 * @generated
	 * @param jcas JCas
	 * @param casType Type 
	 */
  public EmoteInfo_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_fromNickname = jcas.getRequiredFeatureDE(casType, "fromNickname", "uima.cas.String", featOkTst);
    casFeatCode_fromNickname  = (null == casFeat_fromNickname) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_fromNickname).getCode();

 
    casFeat_emote = jcas.getRequiredFeatureDE(casType, "emote", "uima.cas.String", featOkTst);
    casFeatCode_emote  = (null == casFeat_emote) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_emote).getCode();

  }
}



    