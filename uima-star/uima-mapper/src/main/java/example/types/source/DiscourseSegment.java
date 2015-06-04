

/* First created by JCasGen Mon Apr 19 15:30:14 CEST 2010 */
package example.types.source;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import org.apache.uima.jcas.cas.FSArray;
import org.apache.uima.jcas.tcas.Annotation;


/** 
 * Updated by JCasGen Sun Oct 03 09:51:21 CEST 2010
 * XML source: /media/MyPassport/current/public/research/UIMA-USER-DEV-ENV/workspace/uima-type-mapper/desc/example/wst-tagger-preTypeMapper-typeMapper-postTypeMapper-AAE.xml
 * @generated */
public class DiscourseSegment extends Annotation {
  /** @generated
   * @ordered 
   */
  public final static int typeIndexID = JCasRegistry.register(DiscourseSegment.class);
  /** @generated
   * @ordered 
   */
  public final static int type = typeIndexID;
  /** @generated  */
  public              int getTypeIndexID() {return typeIndexID;}
 
  /** Never called.  Disable default constructor
   * @generated */
  protected DiscourseSegment() {}
    
  /** Internal - constructor used by generator 
   * @generated */
  public DiscourseSegment(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated */
  public DiscourseSegment(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated */  
  public DiscourseSegment(JCas jcas, int begin, int end) {
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
  //* Feature: sentenceArray

  /** getter for sentenceArray - gets 
   * @generated */
  public FSArray getSentenceArray() {
    if (DiscourseSegment_Type.featOkTst && ((DiscourseSegment_Type)jcasType).casFeat_sentenceArray == null)
      jcasType.jcas.throwFeatMissing("sentenceArray", "example.types.source.DiscourseSegment");
    return (FSArray)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((DiscourseSegment_Type)jcasType).casFeatCode_sentenceArray)));}
    
  /** setter for sentenceArray - sets  
   * @generated */
  public void setSentenceArray(FSArray v) {
    if (DiscourseSegment_Type.featOkTst && ((DiscourseSegment_Type)jcasType).casFeat_sentenceArray == null)
      jcasType.jcas.throwFeatMissing("sentenceArray", "example.types.source.DiscourseSegment");
    jcasType.ll_cas.ll_setRefValue(addr, ((DiscourseSegment_Type)jcasType).casFeatCode_sentenceArray, jcasType.ll_cas.ll_getFSRef(v));}    
    
  /** indexed getter for sentenceArray - gets an indexed value - 
   * @generated */
  public Sentence getSentenceArray(int i) {
    if (DiscourseSegment_Type.featOkTst && ((DiscourseSegment_Type)jcasType).casFeat_sentenceArray == null)
      jcasType.jcas.throwFeatMissing("sentenceArray", "example.types.source.DiscourseSegment");
    jcasType.jcas.checkArrayBounds(jcasType.ll_cas.ll_getRefValue(addr, ((DiscourseSegment_Type)jcasType).casFeatCode_sentenceArray), i);
    return (Sentence)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefArrayValue(jcasType.ll_cas.ll_getRefValue(addr, ((DiscourseSegment_Type)jcasType).casFeatCode_sentenceArray), i)));}

  /** indexed setter for sentenceArray - sets an indexed value - 
   * @generated */
  public void setSentenceArray(int i, Sentence v) { 
    if (DiscourseSegment_Type.featOkTst && ((DiscourseSegment_Type)jcasType).casFeat_sentenceArray == null)
      jcasType.jcas.throwFeatMissing("sentenceArray", "example.types.source.DiscourseSegment");
    jcasType.jcas.checkArrayBounds(jcasType.ll_cas.ll_getRefValue(addr, ((DiscourseSegment_Type)jcasType).casFeatCode_sentenceArray), i);
    jcasType.ll_cas.ll_setRefArrayValue(jcasType.ll_cas.ll_getRefValue(addr, ((DiscourseSegment_Type)jcasType).casFeatCode_sentenceArray), i, jcasType.ll_cas.ll_getFSRef(v));}
  }

    