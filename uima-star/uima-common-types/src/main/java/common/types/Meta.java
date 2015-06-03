

/* First created by JCasGen Wed May 27 22:41:00 CEST 2015 */
package common.types;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import org.apache.uima.jcas.tcas.Annotation;


/** 
 * Updated by JCasGen Wed May 27 22:41:00 CEST 2015
 * XML source: /media/hernandez-n/ext4/workspace/lina-star/uima-star/uima-common-types/src/main/resources/common/types/commonTS.xml
 * @generated */
public class Meta extends Annotation {
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = JCasRegistry.register(Meta.class);
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
  protected Meta() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated
   * @param addr low level Feature Structure reference
   * @param type the type of this Feature Structure 
   */
  public Meta(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated
   * @param jcas JCas to which this Feature Structure belongs 
   */
  public Meta(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated
   * @param jcas JCas to which this Feature Structure belongs
   * @param begin offset to the begin spot in the SofA
   * @param end offset to the end spot in the SofA 
  */  
  public Meta(JCas jcas, int begin, int end) {
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
  //* Feature: name

  /** getter for name - gets 
   * @generated
   * @return value of the feature 
   */
  public String getName() {
    if (Meta_Type.featOkTst && ((Meta_Type)jcasType).casFeat_name == null)
      jcasType.jcas.throwFeatMissing("name", "common.types.Meta");
    return jcasType.ll_cas.ll_getStringValue(addr, ((Meta_Type)jcasType).casFeatCode_name);}
    
  /** setter for name - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setName(String v) {
    if (Meta_Type.featOkTst && ((Meta_Type)jcasType).casFeat_name == null)
      jcasType.jcas.throwFeatMissing("name", "common.types.Meta");
    jcasType.ll_cas.ll_setStringValue(addr, ((Meta_Type)jcasType).casFeatCode_name, v);}    
   
    
  //*--------------*
  //* Feature: content

  /** getter for content - gets 
   * @generated
   * @return value of the feature 
   */
  public String getContent() {
    if (Meta_Type.featOkTst && ((Meta_Type)jcasType).casFeat_content == null)
      jcasType.jcas.throwFeatMissing("content", "common.types.Meta");
    return jcasType.ll_cas.ll_getStringValue(addr, ((Meta_Type)jcasType).casFeatCode_content);}
    
  /** setter for content - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setContent(String v) {
    if (Meta_Type.featOkTst && ((Meta_Type)jcasType).casFeat_content == null)
      jcasType.jcas.throwFeatMissing("content", "common.types.Meta");
    jcasType.ll_cas.ll_setStringValue(addr, ((Meta_Type)jcasType).casFeatCode_content, v);}    
  }

    