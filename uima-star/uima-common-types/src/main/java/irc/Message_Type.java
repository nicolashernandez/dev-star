
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
public class Message_Type extends LogLine_Type {
  /** @generated 
   * @return the generator for this type
   */
  @Override
  protected FSGenerator getFSGenerator() {return fsGenerator;}
  /** @generated */
  private final FSGenerator fsGenerator = 
    new FSGenerator() {
      public FeatureStructure createFS(int addr, CASImpl cas) {
  			 if (Message_Type.this.useExistingInstance) {
  			   // Return eq fs instance if already created
  		     FeatureStructure fs = Message_Type.this.jcas.getJfsFromCaddr(addr);
  		     if (null == fs) {
  		       fs = new Message(addr, Message_Type.this);
  			   Message_Type.this.jcas.putJfsFromCaddr(addr, fs);
  			   return fs;
  		     }
  		     return fs;
        } else return new Message(addr, Message_Type.this);
  	  }
    };
  /** @generated */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = Message.typeIndexID;
  /** @generated 
     @modifiable */
  @SuppressWarnings ("hiding")
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("irc.Message");
 
  /** @generated */
  final Feature casFeat_timeMarked;
  /** @generated */
  final int     casFeatCode_timeMarked;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public int getTimeMarked(int addr) {
        if (featOkTst && casFeat_timeMarked == null)
      jcas.throwFeatMissing("timeMarked", "irc.Message");
    return ll_cas.ll_getRefValue(addr, casFeatCode_timeMarked);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setTimeMarked(int addr, int v) {
        if (featOkTst && casFeat_timeMarked == null)
      jcas.throwFeatMissing("timeMarked", "irc.Message");
    ll_cas.ll_setRefValue(addr, casFeatCode_timeMarked, v);}
    
  
 
  /** @generated */
  final Feature casFeat_from;
  /** @generated */
  final int     casFeatCode_from;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public int getFrom(int addr) {
        if (featOkTst && casFeat_from == null)
      jcas.throwFeatMissing("from", "irc.Message");
    return ll_cas.ll_getRefValue(addr, casFeatCode_from);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setFrom(int addr, int v) {
        if (featOkTst && casFeat_from == null)
      jcas.throwFeatMissing("from", "irc.Message");
    ll_cas.ll_setRefValue(addr, casFeatCode_from, v);}
    
  
 
  /** @generated */
  final Feature casFeat_to;
  /** @generated */
  final int     casFeatCode_to;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public int getTo(int addr) {
        if (featOkTst && casFeat_to == null)
      jcas.throwFeatMissing("to", "irc.Message");
    return ll_cas.ll_getRefValue(addr, casFeatCode_to);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setTo(int addr, int v) {
        if (featOkTst && casFeat_to == null)
      jcas.throwFeatMissing("to", "irc.Message");
    ll_cas.ll_setRefValue(addr, casFeatCode_to, v);}
    
  
 
  /** @generated */
  final Feature casFeat_body;
  /** @generated */
  final int     casFeatCode_body;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public int getBody(int addr) {
        if (featOkTst && casFeat_body == null)
      jcas.throwFeatMissing("body", "irc.Message");
    return ll_cas.ll_getRefValue(addr, casFeatCode_body);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setBody(int addr, int v) {
        if (featOkTst && casFeat_body == null)
      jcas.throwFeatMissing("body", "irc.Message");
    ll_cas.ll_setRefValue(addr, casFeatCode_body, v);}
    
  



  /** initialize variables to correspond with Cas Type and Features
	 * @generated
	 * @param jcas JCas
	 * @param casType Type 
	 */
  public Message_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_timeMarked = jcas.getRequiredFeatureDE(casType, "timeMarked", "irc.MsgTime", featOkTst);
    casFeatCode_timeMarked  = (null == casFeat_timeMarked) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_timeMarked).getCode();

 
    casFeat_from = jcas.getRequiredFeatureDE(casType, "from", "irc.MsgFrom", featOkTst);
    casFeatCode_from  = (null == casFeat_from) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_from).getCode();

 
    casFeat_to = jcas.getRequiredFeatureDE(casType, "to", "irc.MsgTo", featOkTst);
    casFeatCode_to  = (null == casFeat_to) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_to).getCode();

 
    casFeat_body = jcas.getRequiredFeatureDE(casType, "body", "irc.MsgBody", featOkTst);
    casFeatCode_body  = (null == casFeat_body) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_body).getCode();

  }
}



    