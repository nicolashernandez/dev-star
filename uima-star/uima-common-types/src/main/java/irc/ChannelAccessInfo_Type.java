
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
public class ChannelAccessInfo_Type extends Info_Type {
  /** @generated 
   * @return the generator for this type
   */
  @Override
  protected FSGenerator getFSGenerator() {return fsGenerator;}
  /** @generated */
  private final FSGenerator fsGenerator = 
    new FSGenerator() {
      public FeatureStructure createFS(int addr, CASImpl cas) {
  			 if (ChannelAccessInfo_Type.this.useExistingInstance) {
  			   // Return eq fs instance if already created
  		     FeatureStructure fs = ChannelAccessInfo_Type.this.jcas.getJfsFromCaddr(addr);
  		     if (null == fs) {
  		       fs = new ChannelAccessInfo(addr, ChannelAccessInfo_Type.this);
  			   ChannelAccessInfo_Type.this.jcas.putJfsFromCaddr(addr, fs);
  			   return fs;
  		     }
  		     return fs;
        } else return new ChannelAccessInfo(addr, ChannelAccessInfo_Type.this);
  	  }
    };
  /** @generated */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = ChannelAccessInfo.typeIndexID;
  /** @generated 
     @modifiable */
  @SuppressWarnings ("hiding")
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("irc.ChannelAccessInfo");
 
  /** @generated */
  final Feature casFeat_nickname;
  /** @generated */
  final int     casFeatCode_nickname;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public String getNickname(int addr) {
        if (featOkTst && casFeat_nickname == null)
      jcas.throwFeatMissing("nickname", "irc.ChannelAccessInfo");
    return ll_cas.ll_getStringValue(addr, casFeatCode_nickname);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setNickname(int addr, String v) {
        if (featOkTst && casFeat_nickname == null)
      jcas.throwFeatMissing("nickname", "irc.ChannelAccessInfo");
    ll_cas.ll_setStringValue(addr, casFeatCode_nickname, v);}
    
  
 
  /** @generated */
  final Feature casFeat_source;
  /** @generated */
  final int     casFeatCode_source;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public String getSource(int addr) {
        if (featOkTst && casFeat_source == null)
      jcas.throwFeatMissing("source", "irc.ChannelAccessInfo");
    return ll_cas.ll_getStringValue(addr, casFeatCode_source);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setSource(int addr, String v) {
        if (featOkTst && casFeat_source == null)
      jcas.throwFeatMissing("source", "irc.ChannelAccessInfo");
    ll_cas.ll_setStringValue(addr, casFeatCode_source, v);}
    
  
 
  /** @generated */
  final Feature casFeat_channel;
  /** @generated */
  final int     casFeatCode_channel;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public String getChannel(int addr) {
        if (featOkTst && casFeat_channel == null)
      jcas.throwFeatMissing("channel", "irc.ChannelAccessInfo");
    return ll_cas.ll_getStringValue(addr, casFeatCode_channel);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setChannel(int addr, String v) {
        if (featOkTst && casFeat_channel == null)
      jcas.throwFeatMissing("channel", "irc.ChannelAccessInfo");
    ll_cas.ll_setStringValue(addr, casFeatCode_channel, v);}
    
  
 
  /** @generated */
  final Feature casFeat_welcomeLeavingMessage;
  /** @generated */
  final int     casFeatCode_welcomeLeavingMessage;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public String getWelcomeLeavingMessage(int addr) {
        if (featOkTst && casFeat_welcomeLeavingMessage == null)
      jcas.throwFeatMissing("welcomeLeavingMessage", "irc.ChannelAccessInfo");
    return ll_cas.ll_getStringValue(addr, casFeatCode_welcomeLeavingMessage);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setWelcomeLeavingMessage(int addr, String v) {
        if (featOkTst && casFeat_welcomeLeavingMessage == null)
      jcas.throwFeatMissing("welcomeLeavingMessage", "irc.ChannelAccessInfo");
    ll_cas.ll_setStringValue(addr, casFeatCode_welcomeLeavingMessage, v);}
    
  



  /** initialize variables to correspond with Cas Type and Features
	 * @generated
	 * @param jcas JCas
	 * @param casType Type 
	 */
  public ChannelAccessInfo_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_nickname = jcas.getRequiredFeatureDE(casType, "nickname", "uima.cas.String", featOkTst);
    casFeatCode_nickname  = (null == casFeat_nickname) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_nickname).getCode();

 
    casFeat_source = jcas.getRequiredFeatureDE(casType, "source", "uima.cas.String", featOkTst);
    casFeatCode_source  = (null == casFeat_source) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_source).getCode();

 
    casFeat_channel = jcas.getRequiredFeatureDE(casType, "channel", "uima.cas.String", featOkTst);
    casFeatCode_channel  = (null == casFeat_channel) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_channel).getCode();

 
    casFeat_welcomeLeavingMessage = jcas.getRequiredFeatureDE(casType, "welcomeLeavingMessage", "uima.cas.String", featOkTst);
    casFeatCode_welcomeLeavingMessage  = (null == casFeat_welcomeLeavingMessage) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_welcomeLeavingMessage).getCode();

  }
}



    