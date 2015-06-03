

/* First created by JCasGen Wed May 27 22:41:00 CEST 2015 */
package common.types.text;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;



/** 
 * Updated by JCasGen Wed May 27 22:41:00 CEST 2015
 * XML source: /media/hernandez-n/ext4/workspace/lina-star/uima-star/uima-common-types/src/main/resources/common/types/commonTS.xml
 * @generated */
public class Sentence extends Annotation {
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = JCasRegistry.register(Sentence.class);
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
  protected Sentence() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated
   * @param addr low level Feature Structure reference
   * @param type the type of this Feature Structure 
   */
  public Sentence(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated
   * @param jcas JCas to which this Feature Structure belongs 
   */
  public Sentence(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated
   * @param jcas JCas to which this Feature Structure belongs
   * @param begin offset to the begin spot in the SofA
   * @param end offset to the end spot in the SofA 
  */  
  public Sentence(JCas jcas, int begin, int end) {
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
  //* Feature: precedingNewlines

  /** getter for precedingNewlines - gets number of preceding newlines character between the current and the previous sentence or the begining of the document.
   * @generated
   * @return value of the feature 
   */
  public int getPrecedingNewlines() {
    if (Sentence_Type.featOkTst && ((Sentence_Type)jcasType).casFeat_precedingNewlines == null)
      jcasType.jcas.throwFeatMissing("precedingNewlines", "common.types.text.Sentence");
    return jcasType.ll_cas.ll_getIntValue(addr, ((Sentence_Type)jcasType).casFeatCode_precedingNewlines);}
    
  /** setter for precedingNewlines - sets number of preceding newlines character between the current and the previous sentence or the begining of the document. 
   * @generated
   * @param v value to set into the feature 
   */
  public void setPrecedingNewlines(int v) {
    if (Sentence_Type.featOkTst && ((Sentence_Type)jcasType).casFeat_precedingNewlines == null)
      jcasType.jcas.throwFeatMissing("precedingNewlines", "common.types.text.Sentence");
    jcasType.ll_cas.ll_setIntValue(addr, ((Sentence_Type)jcasType).casFeatCode_precedingNewlines, v);}    
   
    
  //*--------------*
  //* Feature: followingNewlines

  /** getter for followingNewlines - gets number of following newlines character between the current and the next sentence or end of document.
   * @generated
   * @return value of the feature 
   */
  public int getFollowingNewlines() {
    if (Sentence_Type.featOkTst && ((Sentence_Type)jcasType).casFeat_followingNewlines == null)
      jcasType.jcas.throwFeatMissing("followingNewlines", "common.types.text.Sentence");
    return jcasType.ll_cas.ll_getIntValue(addr, ((Sentence_Type)jcasType).casFeatCode_followingNewlines);}
    
  /** setter for followingNewlines - sets number of following newlines character between the current and the next sentence or end of document. 
   * @generated
   * @param v value to set into the feature 
   */
  public void setFollowingNewlines(int v) {
    if (Sentence_Type.featOkTst && ((Sentence_Type)jcasType).casFeat_followingNewlines == null)
      jcasType.jcas.throwFeatMissing("followingNewlines", "common.types.text.Sentence");
    jcasType.ll_cas.ll_setIntValue(addr, ((Sentence_Type)jcasType).casFeatCode_followingNewlines, v);}    
   
    
  //*--------------*
  //* Feature: segmentStatus

  /** getter for segmentStatus - gets SE if it is a single segment sentence
S if it starts a segment
E if it ends a segment
I if inside
U if undefined
   * @generated
   * @return value of the feature 
   */
  public String getSegmentStatus() {
    if (Sentence_Type.featOkTst && ((Sentence_Type)jcasType).casFeat_segmentStatus == null)
      jcasType.jcas.throwFeatMissing("segmentStatus", "common.types.text.Sentence");
    return jcasType.ll_cas.ll_getStringValue(addr, ((Sentence_Type)jcasType).casFeatCode_segmentStatus);}
    
  /** setter for segmentStatus - sets SE if it is a single segment sentence
S if it starts a segment
E if it ends a segment
I if inside
U if undefined 
   * @generated
   * @param v value to set into the feature 
   */
  public void setSegmentStatus(String v) {
    if (Sentence_Type.featOkTst && ((Sentence_Type)jcasType).casFeat_segmentStatus == null)
      jcasType.jcas.throwFeatMissing("segmentStatus", "common.types.text.Sentence");
    jcasType.ll_cas.ll_setStringValue(addr, ((Sentence_Type)jcasType).casFeatCode_segmentStatus, v);}    
   
    
  //*--------------*
  //* Feature: textTilingStatus

  /** getter for textTilingStatus - gets 
   * @generated
   * @return value of the feature 
   */
  public String getTextTilingStatus() {
    if (Sentence_Type.featOkTst && ((Sentence_Type)jcasType).casFeat_textTilingStatus == null)
      jcasType.jcas.throwFeatMissing("textTilingStatus", "common.types.text.Sentence");
    return jcasType.ll_cas.ll_getStringValue(addr, ((Sentence_Type)jcasType).casFeatCode_textTilingStatus);}
    
  /** setter for textTilingStatus - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setTextTilingStatus(String v) {
    if (Sentence_Type.featOkTst && ((Sentence_Type)jcasType).casFeat_textTilingStatus == null)
      jcasType.jcas.throwFeatMissing("textTilingStatus", "common.types.text.Sentence");
    jcasType.ll_cas.ll_setStringValue(addr, ((Sentence_Type)jcasType).casFeatCode_textTilingStatus, v);}    
  }

    