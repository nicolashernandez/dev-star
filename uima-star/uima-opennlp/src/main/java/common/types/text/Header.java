

/* First created by JCasGen Sat Dec 10 00:59:10 CET 2011 */
package common.types.text;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;



/** 
 * Updated by JCasGen Sat Dec 10 00:59:12 CET 2011
 * XML source: /media/MyPassport/current/public/research/UIMA-USER-DEV-ENV/workspace/demo-apache-opennlp-uima/desc/opennlp/fr/SentenceDetectorAE.xml
 * @generated */
public class Header extends Annotation {
  /** @generated
   * @ordered 
   */
  public final static int typeIndexID = JCasRegistry.register(Header.class);
  /** @generated
   * @ordered 
   */
  public final static int type = typeIndexID;
  /** @generated  */
  public              int getTypeIndexID() {return typeIndexID;}
 
  /** Never called.  Disable default constructor
   * @generated */
  protected Header() {}
    
  /** Internal - constructor used by generator 
   * @generated */
  public Header(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated */
  public Header(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated */  
  public Header(JCas jcas, int begin, int end) {
    super(jcas);
    setBegin(begin);
    setEnd(end);
    readObject();
  }   

  /** <!-- begin-user-doc -->
    * Write your own initialization here
    * <!-- end-user-doc -->
  @generated modifiable */
  private void readObject() {}
     
 
    
  //*--------------*
  //* Feature: level

  /** getter for level - gets 
   * @generated */
  public int getLevel() {
    if (Header_Type.featOkTst && ((Header_Type)jcasType).casFeat_level == null)
      jcasType.jcas.throwFeatMissing("level", "common.types.text.Header");
    return jcasType.ll_cas.ll_getIntValue(addr, ((Header_Type)jcasType).casFeatCode_level);}
    
  /** setter for level - sets  
   * @generated */
  public void setLevel(int v) {
    if (Header_Type.featOkTst && ((Header_Type)jcasType).casFeat_level == null)
      jcasType.jcas.throwFeatMissing("level", "common.types.text.Header");
    jcasType.ll_cas.ll_setIntValue(addr, ((Header_Type)jcasType).casFeatCode_level, v);}    
  }

    