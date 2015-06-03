
/* First created by JCasGen Wed May 27 22:43:37 CEST 2015 */
package email;

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
 * Updated by JCasGen Wed May 27 22:43:37 CEST 2015
 * @generated */
public class Message_Type extends Annotation_Type {
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
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("email.Message");
 
  /** @generated */
  final Feature casFeat_fromAddress;
  /** @generated */
  final int     casFeatCode_fromAddress;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public String getFromAddress(int addr) {
        if (featOkTst && casFeat_fromAddress == null)
      jcas.throwFeatMissing("fromAddress", "email.Message");
    return ll_cas.ll_getStringValue(addr, casFeatCode_fromAddress);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setFromAddress(int addr, String v) {
        if (featOkTst && casFeat_fromAddress == null)
      jcas.throwFeatMissing("fromAddress", "email.Message");
    ll_cas.ll_setStringValue(addr, casFeatCode_fromAddress, v);}
    
  
 
  /** @generated */
  final Feature casFeat_toAddress;
  /** @generated */
  final int     casFeatCode_toAddress;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public String getToAddress(int addr) {
        if (featOkTst && casFeat_toAddress == null)
      jcas.throwFeatMissing("toAddress", "email.Message");
    return ll_cas.ll_getStringValue(addr, casFeatCode_toAddress);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setToAddress(int addr, String v) {
        if (featOkTst && casFeat_toAddress == null)
      jcas.throwFeatMissing("toAddress", "email.Message");
    ll_cas.ll_setStringValue(addr, casFeatCode_toAddress, v);}
    
  
 
  /** @generated */
  final Feature casFeat_date;
  /** @generated */
  final int     casFeatCode_date;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public String getDate(int addr) {
        if (featOkTst && casFeat_date == null)
      jcas.throwFeatMissing("date", "email.Message");
    return ll_cas.ll_getStringValue(addr, casFeatCode_date);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setDate(int addr, String v) {
        if (featOkTst && casFeat_date == null)
      jcas.throwFeatMissing("date", "email.Message");
    ll_cas.ll_setStringValue(addr, casFeatCode_date, v);}
    
  
 
  /** @generated */
  final Feature casFeat_id;
  /** @generated */
  final int     casFeatCode_id;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public String getId(int addr) {
        if (featOkTst && casFeat_id == null)
      jcas.throwFeatMissing("id", "email.Message");
    return ll_cas.ll_getStringValue(addr, casFeatCode_id);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setId(int addr, String v) {
        if (featOkTst && casFeat_id == null)
      jcas.throwFeatMissing("id", "email.Message");
    ll_cas.ll_setStringValue(addr, casFeatCode_id, v);}
    
  
 
  /** @generated */
  final Feature casFeat_subject;
  /** @generated */
  final int     casFeatCode_subject;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public String getSubject(int addr) {
        if (featOkTst && casFeat_subject == null)
      jcas.throwFeatMissing("subject", "email.Message");
    return ll_cas.ll_getStringValue(addr, casFeatCode_subject);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setSubject(int addr, String v) {
        if (featOkTst && casFeat_subject == null)
      jcas.throwFeatMissing("subject", "email.Message");
    ll_cas.ll_setStringValue(addr, casFeatCode_subject, v);}
    
  
 
  /** @generated */
  final Feature casFeat_inReplyTo;
  /** @generated */
  final int     casFeatCode_inReplyTo;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public String getInReplyTo(int addr) {
        if (featOkTst && casFeat_inReplyTo == null)
      jcas.throwFeatMissing("inReplyTo", "email.Message");
    return ll_cas.ll_getStringValue(addr, casFeatCode_inReplyTo);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setInReplyTo(int addr, String v) {
        if (featOkTst && casFeat_inReplyTo == null)
      jcas.throwFeatMissing("inReplyTo", "email.Message");
    ll_cas.ll_setStringValue(addr, casFeatCode_inReplyTo, v);}
    
  
 
  /** @generated */
  final Feature casFeat_mime;
  /** @generated */
  final int     casFeatCode_mime;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public String getMime(int addr) {
        if (featOkTst && casFeat_mime == null)
      jcas.throwFeatMissing("mime", "email.Message");
    return ll_cas.ll_getStringValue(addr, casFeatCode_mime);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setMime(int addr, String v) {
        if (featOkTst && casFeat_mime == null)
      jcas.throwFeatMissing("mime", "email.Message");
    ll_cas.ll_setStringValue(addr, casFeatCode_mime, v);}
    
  
 
  /** @generated */
  final Feature casFeat_encoding;
  /** @generated */
  final int     casFeatCode_encoding;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public String getEncoding(int addr) {
        if (featOkTst && casFeat_encoding == null)
      jcas.throwFeatMissing("encoding", "email.Message");
    return ll_cas.ll_getStringValue(addr, casFeatCode_encoding);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setEncoding(int addr, String v) {
        if (featOkTst && casFeat_encoding == null)
      jcas.throwFeatMissing("encoding", "email.Message");
    ll_cas.ll_setStringValue(addr, casFeatCode_encoding, v);}
    
  
 
  /** @generated */
  final Feature casFeat_position;
  /** @generated */
  final int     casFeatCode_position;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public int getPosition(int addr) {
        if (featOkTst && casFeat_position == null)
      jcas.throwFeatMissing("position", "email.Message");
    return ll_cas.ll_getIntValue(addr, casFeatCode_position);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setPosition(int addr, int v) {
        if (featOkTst && casFeat_position == null)
      jcas.throwFeatMissing("position", "email.Message");
    ll_cas.ll_setIntValue(addr, casFeatCode_position, v);}
    
  
 
  /** @generated */
  final Feature casFeat_isInitial;
  /** @generated */
  final int     casFeatCode_isInitial;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public boolean getIsInitial(int addr) {
        if (featOkTst && casFeat_isInitial == null)
      jcas.throwFeatMissing("isInitial", "email.Message");
    return ll_cas.ll_getBooleanValue(addr, casFeatCode_isInitial);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setIsInitial(int addr, boolean v) {
        if (featOkTst && casFeat_isInitial == null)
      jcas.throwFeatMissing("isInitial", "email.Message");
    ll_cas.ll_setBooleanValue(addr, casFeatCode_isInitial, v);}
    
  
 
  /** @generated */
  final Feature casFeat_fromPersonal;
  /** @generated */
  final int     casFeatCode_fromPersonal;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public String getFromPersonal(int addr) {
        if (featOkTst && casFeat_fromPersonal == null)
      jcas.throwFeatMissing("fromPersonal", "email.Message");
    return ll_cas.ll_getStringValue(addr, casFeatCode_fromPersonal);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setFromPersonal(int addr, String v) {
        if (featOkTst && casFeat_fromPersonal == null)
      jcas.throwFeatMissing("fromPersonal", "email.Message");
    ll_cas.ll_setStringValue(addr, casFeatCode_fromPersonal, v);}
    
  
 
  /** @generated */
  final Feature casFeat_toPersonal;
  /** @generated */
  final int     casFeatCode_toPersonal;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public String getToPersonal(int addr) {
        if (featOkTst && casFeat_toPersonal == null)
      jcas.throwFeatMissing("toPersonal", "email.Message");
    return ll_cas.ll_getStringValue(addr, casFeatCode_toPersonal);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setToPersonal(int addr, String v) {
        if (featOkTst && casFeat_toPersonal == null)
      jcas.throwFeatMissing("toPersonal", "email.Message");
    ll_cas.ll_setStringValue(addr, casFeatCode_toPersonal, v);}
    
  



  /** initialize variables to correspond with Cas Type and Features
	 * @generated
	 * @param jcas JCas
	 * @param casType Type 
	 */
  public Message_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_fromAddress = jcas.getRequiredFeatureDE(casType, "fromAddress", "uima.cas.String", featOkTst);
    casFeatCode_fromAddress  = (null == casFeat_fromAddress) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_fromAddress).getCode();

 
    casFeat_toAddress = jcas.getRequiredFeatureDE(casType, "toAddress", "uima.cas.String", featOkTst);
    casFeatCode_toAddress  = (null == casFeat_toAddress) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_toAddress).getCode();

 
    casFeat_date = jcas.getRequiredFeatureDE(casType, "date", "uima.cas.String", featOkTst);
    casFeatCode_date  = (null == casFeat_date) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_date).getCode();

 
    casFeat_id = jcas.getRequiredFeatureDE(casType, "id", "uima.cas.String", featOkTst);
    casFeatCode_id  = (null == casFeat_id) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_id).getCode();

 
    casFeat_subject = jcas.getRequiredFeatureDE(casType, "subject", "uima.cas.String", featOkTst);
    casFeatCode_subject  = (null == casFeat_subject) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_subject).getCode();

 
    casFeat_inReplyTo = jcas.getRequiredFeatureDE(casType, "inReplyTo", "uima.cas.String", featOkTst);
    casFeatCode_inReplyTo  = (null == casFeat_inReplyTo) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_inReplyTo).getCode();

 
    casFeat_mime = jcas.getRequiredFeatureDE(casType, "mime", "uima.cas.String", featOkTst);
    casFeatCode_mime  = (null == casFeat_mime) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_mime).getCode();

 
    casFeat_encoding = jcas.getRequiredFeatureDE(casType, "encoding", "uima.cas.String", featOkTst);
    casFeatCode_encoding  = (null == casFeat_encoding) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_encoding).getCode();

 
    casFeat_position = jcas.getRequiredFeatureDE(casType, "position", "uima.cas.Integer", featOkTst);
    casFeatCode_position  = (null == casFeat_position) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_position).getCode();

 
    casFeat_isInitial = jcas.getRequiredFeatureDE(casType, "isInitial", "uima.cas.Boolean", featOkTst);
    casFeatCode_isInitial  = (null == casFeat_isInitial) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_isInitial).getCode();

 
    casFeat_fromPersonal = jcas.getRequiredFeatureDE(casType, "fromPersonal", "uima.cas.String", featOkTst);
    casFeatCode_fromPersonal  = (null == casFeat_fromPersonal) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_fromPersonal).getCode();

 
    casFeat_toPersonal = jcas.getRequiredFeatureDE(casType, "toPersonal", "uima.cas.String", featOkTst);
    casFeatCode_toPersonal  = (null == casFeat_toPersonal) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_toPersonal).getCode();

  }
}



    