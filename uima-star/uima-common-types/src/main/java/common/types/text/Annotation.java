

/* First created by JCasGen Wed May 27 22:41:00 CEST 2015 */
package common.types.text;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import org.apache.uima.jcas.cas.FSArray;


/** 
 * Updated by JCasGen Wed May 27 22:41:00 CEST 2015
 * XML source: /media/hernandez-n/ext4/workspace/lina-star/uima-star/uima-common-types/src/main/resources/common/types/commonTS.xml
 * @generated */
public class Annotation extends common.types.Annotation {
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = JCasRegistry.register(Annotation.class);
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int type = typeIndexID;
  /** @generated
   * @return index of the type  
   */
  @Override
  public              int getTypeIndexID() {return typeIndexID;}
 
  /** Never called.  Disable default constructor
   * @generated */
  protected Annotation() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated
   * @param addr low level Feature Structure reference
   * @param type the type of this Feature Structure 
   */
  public Annotation(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated
   * @param jcas JCas to which this Feature Structure belongs 
   */
  public Annotation(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated
   * @param jcas JCas to which this Feature Structure belongs
   * @param begin offset to the begin spot in the SofA
   * @param end offset to the end spot in the SofA 
  */  
  public Annotation(JCas jcas, int begin, int end) {
    super(jcas);
    setBegin(begin);
    setEnd(end);
    readObject();
  }   

  /** 
   * <!-- begin-user-doc -->
   * Write your own initialization here
   * <!-- end-user-doc -->
   *
   * @generated modifiable 
   */
  private void readObject() {/*default - does nothing empty block */}
     
 
    
  //*--------------*
  //* Feature: annotationList

  /** getter for annotationList - gets example: 
chunk may be made of several other ordered chunks
a multi-word term may be made of several ordered words
   * @generated
   * @return value of the feature 
   */
  public FSArray getAnnotationList() {
    if (Annotation_Type.featOkTst && ((Annotation_Type)jcasType).casFeat_annotationList == null)
      jcasType.jcas.throwFeatMissing("annotationList", "common.types.text.Annotation");
    return (FSArray)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((Annotation_Type)jcasType).casFeatCode_annotationList)));}
    
  /** setter for annotationList - sets example: 
chunk may be made of several other ordered chunks
a multi-word term may be made of several ordered words 
   * @generated
   * @param v value to set into the feature 
   */
  public void setAnnotationList(FSArray v) {
    if (Annotation_Type.featOkTst && ((Annotation_Type)jcasType).casFeat_annotationList == null)
      jcasType.jcas.throwFeatMissing("annotationList", "common.types.text.Annotation");
    jcasType.ll_cas.ll_setRefValue(addr, ((Annotation_Type)jcasType).casFeatCode_annotationList, jcasType.ll_cas.ll_getFSRef(v));}    
    
  /** indexed getter for annotationList - gets an indexed value - example: 
chunk may be made of several other ordered chunks
a multi-word term may be made of several ordered words
   * @generated
   * @param i index in the array to get
   * @return value of the element at index i 
   */
  public Annotation getAnnotationList(int i) {
    if (Annotation_Type.featOkTst && ((Annotation_Type)jcasType).casFeat_annotationList == null)
      jcasType.jcas.throwFeatMissing("annotationList", "common.types.text.Annotation");
    jcasType.jcas.checkArrayBounds(jcasType.ll_cas.ll_getRefValue(addr, ((Annotation_Type)jcasType).casFeatCode_annotationList), i);
    return (Annotation)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefArrayValue(jcasType.ll_cas.ll_getRefValue(addr, ((Annotation_Type)jcasType).casFeatCode_annotationList), i)));}

  /** indexed setter for annotationList - sets an indexed value - example: 
chunk may be made of several other ordered chunks
a multi-word term may be made of several ordered words
   * @generated
   * @param i index in the array to set
   * @param v value to set into the array 
   */
  public void setAnnotationList(int i, Annotation v) { 
    if (Annotation_Type.featOkTst && ((Annotation_Type)jcasType).casFeat_annotationList == null)
      jcasType.jcas.throwFeatMissing("annotationList", "common.types.text.Annotation");
    jcasType.jcas.checkArrayBounds(jcasType.ll_cas.ll_getRefValue(addr, ((Annotation_Type)jcasType).casFeatCode_annotationList), i);
    jcasType.ll_cas.ll_setRefArrayValue(jcasType.ll_cas.ll_getRefValue(addr, ((Annotation_Type)jcasType).casFeatCode_annotationList), i, jcasType.ll_cas.ll_getFSRef(v));}
  }

    