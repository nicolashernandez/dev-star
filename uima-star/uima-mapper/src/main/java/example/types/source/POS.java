

/* First created by JCasGen Thu Apr 15 15:16:12 CEST 2010 */
package example.types.source;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import org.apache.uima.jcas.tcas.Annotation;


/** 
 * Updated by JCasGen Sun Oct 03 09:51:21 CEST 2010
 * XML source: /media/MyPassport/current/public/research/UIMA-USER-DEV-ENV/workspace/uima-type-mapper/desc/example/wst-tagger-preTypeMapper-typeMapper-postTypeMapper-AAE.xml
 * @generated */
public class POS extends Annotation {
  /** @generated
   * @ordered 
   */
  public final static int typeIndexID = JCasRegistry.register(POS.class);
  /** @generated
   * @ordered 
   */
  public final static int type = typeIndexID;
  /** @generated  */
  public              int getTypeIndexID() {return typeIndexID;}
 
  /** Never called.  Disable default constructor
   * @generated */
  protected POS() {}
    
  /** Internal - constructor used by generator 
   * @generated */
  public POS(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated */
  public POS(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated */  
  public POS(JCas jcas, int begin, int end) {
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
  //* Feature: value

  /** getter for value - gets 
   * @generated */
  public String getValue() {
    if (POS_Type.featOkTst && ((POS_Type)jcasType).casFeat_value == null)
      jcasType.jcas.throwFeatMissing("value", "example.types.source.POS");
    return jcasType.ll_cas.ll_getStringValue(addr, ((POS_Type)jcasType).casFeatCode_value);}
    
  /** setter for value - sets  
   * @generated */
  public void setValue(String v) {
    if (POS_Type.featOkTst && ((POS_Type)jcasType).casFeat_value == null)
      jcasType.jcas.throwFeatMissing("value", "example.types.source.POS");
    jcasType.ll_cas.ll_setStringValue(addr, ((POS_Type)jcasType).casFeatCode_value, v);}    
  }

    