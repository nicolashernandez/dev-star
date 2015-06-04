

/* First created by JCasGen Thu Apr 15 13:48:45 CEST 2010 */
package example.types.target;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import org.apache.uima.jcas.tcas.Annotation;


/** 
 * Updated by JCasGen Mon Mar 28 15:53:10 CEST 2011
 * XML source: /media/MyPassport/current/public/research/UIMA-USER-DEV-ENV/workspace/uima-annotation-mapper/desc/example/post-annotation-mapper_TS.xml
 * @generated */
public class Mot extends Annotation {
  /** @generated
   * @ordered 
   */
  public final static int typeIndexID = JCasRegistry.register(Mot.class);
  /** @generated
   * @ordered 
   */
  public final static int type = typeIndexID;
  /** @generated  */
  public              int getTypeIndexID() {return typeIndexID;}
 
  /** Never called.  Disable default constructor
   * @generated */
  protected Mot() {}
    
  /** Internal - constructor used by generator 
   * @generated */
  public Mot(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated */
  public Mot(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated */  
  public Mot(JCas jcas, int begin, int end) {
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
  //* Feature: pos

  /** getter for pos - gets 
   * @generated */
  public String getPos() {
    if (Mot_Type.featOkTst && ((Mot_Type)jcasType).casFeat_pos == null)
      jcasType.jcas.throwFeatMissing("pos", "example.types.target.Mot");
    return jcasType.ll_cas.ll_getStringValue(addr, ((Mot_Type)jcasType).casFeatCode_pos);}
    
  /** setter for pos - sets  
   * @generated */
  public void setPos(String v) {
    if (Mot_Type.featOkTst && ((Mot_Type)jcasType).casFeat_pos == null)
      jcasType.jcas.throwFeatMissing("pos", "example.types.target.Mot");
    jcasType.ll_cas.ll_setStringValue(addr, ((Mot_Type)jcasType).casFeatCode_pos, v);}    
   
    
  //*--------------*
  //* Feature: size

  /** getter for size - gets 
   * @generated */
  public int getSize() {
    if (Mot_Type.featOkTst && ((Mot_Type)jcasType).casFeat_size == null)
      jcasType.jcas.throwFeatMissing("size", "example.types.target.Mot");
    return jcasType.ll_cas.ll_getIntValue(addr, ((Mot_Type)jcasType).casFeatCode_size);}
    
  /** setter for size - sets  
   * @generated */
  public void setSize(int v) {
    if (Mot_Type.featOkTst && ((Mot_Type)jcasType).casFeat_size == null)
      jcasType.jcas.throwFeatMissing("size", "example.types.target.Mot");
    jcasType.ll_cas.ll_setIntValue(addr, ((Mot_Type)jcasType).casFeatCode_size, v);}    
  }

    