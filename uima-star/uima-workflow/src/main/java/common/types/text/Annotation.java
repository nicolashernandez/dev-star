

/* First created by JCasGen Thu May 30 13:40:53 CEST 2013 */
package common.types.text;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import org.apache.uima.jcas.cas.FSArray;


/** 
 * Updated by JCasGen Thu May 30 13:40:53 CEST 2013
 * XML source: /media/ext4/workspace/uima-workflow/desc/wf/analysis/importTutinArcadeXRCELeMondeCorpus-openNLPPOSTaggerFr-maltparser-AAE.xml
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
  /** @generated  */
  @Override
  public              int getTypeIndexID() {return typeIndexID;}
 
  /** Never called.  Disable default constructor
   * @generated */
  protected Annotation() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated */
  public Annotation(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated */
  public Annotation(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated */  
  public Annotation(JCas jcas, int begin, int end) {
    super(jcas);
    setBegin(begin);
    setEnd(end);
    readObject();
  }   

  /** <!-- begin-user-doc -->
    * Write your own initialization here
    * <!-- end-user-doc -->
  @generated modifiable */
  private void readObject() {/*default - does nothing empty block */}
     
 
    
  //*--------------*
  //* Feature: annotationList

  /** getter for annotationList - gets example: 
chunk may be made of several other ordered chunks
a multi-word term may be made of several ordered words
   * @generated */
  public FSArray getAnnotationList() {
    if (Annotation_Type.featOkTst && ((Annotation_Type)jcasType).casFeat_annotationList == null)
      jcasType.jcas.throwFeatMissing("annotationList", "common.types.text.Annotation");
    return (FSArray)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((Annotation_Type)jcasType).casFeatCode_annotationList)));}
    
  /** setter for annotationList - sets example: 
chunk may be made of several other ordered chunks
a multi-word term may be made of several ordered words 
   * @generated */
  public void setAnnotationList(FSArray v) {
    if (Annotation_Type.featOkTst && ((Annotation_Type)jcasType).casFeat_annotationList == null)
      jcasType.jcas.throwFeatMissing("annotationList", "common.types.text.Annotation");
    jcasType.ll_cas.ll_setRefValue(addr, ((Annotation_Type)jcasType).casFeatCode_annotationList, jcasType.ll_cas.ll_getFSRef(v));}    
    
  /** indexed getter for annotationList - gets an indexed value - example: 
chunk may be made of several other ordered chunks
a multi-word term may be made of several ordered words
   * @generated */
  public Annotation getAnnotationList(int i) {
    if (Annotation_Type.featOkTst && ((Annotation_Type)jcasType).casFeat_annotationList == null)
      jcasType.jcas.throwFeatMissing("annotationList", "common.types.text.Annotation");
    jcasType.jcas.checkArrayBounds(jcasType.ll_cas.ll_getRefValue(addr, ((Annotation_Type)jcasType).casFeatCode_annotationList), i);
    return (Annotation)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefArrayValue(jcasType.ll_cas.ll_getRefValue(addr, ((Annotation_Type)jcasType).casFeatCode_annotationList), i)));}

  /** indexed setter for annotationList - sets an indexed value - example: 
chunk may be made of several other ordered chunks
a multi-word term may be made of several ordered words
   * @generated */
  public void setAnnotationList(int i, Annotation v) { 
    if (Annotation_Type.featOkTst && ((Annotation_Type)jcasType).casFeat_annotationList == null)
      jcasType.jcas.throwFeatMissing("annotationList", "common.types.text.Annotation");
    jcasType.jcas.checkArrayBounds(jcasType.ll_cas.ll_getRefValue(addr, ((Annotation_Type)jcasType).casFeatCode_annotationList), i);
    jcasType.ll_cas.ll_setRefArrayValue(jcasType.ll_cas.ll_getRefValue(addr, ((Annotation_Type)jcasType).casFeatCode_annotationList), i, jcasType.ll_cas.ll_getFSRef(v));}
  }

    