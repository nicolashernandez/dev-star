

/* First created by JCasGen Wed May 27 22:43:37 CEST 2015 */
package email;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import org.apache.uima.jcas.tcas.Annotation;


/** 
 * Updated by JCasGen Wed May 27 22:43:37 CEST 2015
 * XML source: /media/hernandez-n/ext4/workspace/lina-star/uima-star/uima-common-types/src/main/resources/common/types/CommonTS.xml
 * @generated */
public class QuotedLine extends Annotation {
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = JCasRegistry.register(QuotedLine.class);
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
  protected QuotedLine() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated
   * @param addr low level Feature Structure reference
   * @param type the type of this Feature Structure 
   */
  public QuotedLine(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated
   * @param jcas JCas to which this Feature Structure belongs 
   */
  public QuotedLine(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated
   * @param jcas JCas to which this Feature Structure belongs
   * @param begin offset to the begin spot in the SofA
   * @param end offset to the end spot in the SofA 
  */  
  public QuotedLine(JCas jcas, int begin, int end) {
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
  //* Feature: depth

  /** getter for depth - gets 
   * @generated
   * @return value of the feature 
   */
  public int getDepth() {
    if (QuotedLine_Type.featOkTst && ((QuotedLine_Type)jcasType).casFeat_depth == null)
      jcasType.jcas.throwFeatMissing("depth", "email.QuotedLine");
    return jcasType.ll_cas.ll_getIntValue(addr, ((QuotedLine_Type)jcasType).casFeatCode_depth);}
    
  /** setter for depth - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setDepth(int v) {
    if (QuotedLine_Type.featOkTst && ((QuotedLine_Type)jcasType).casFeat_depth == null)
      jcasType.jcas.throwFeatMissing("depth", "email.QuotedLine");
    jcasType.ll_cas.ll_setIntValue(addr, ((QuotedLine_Type)jcasType).casFeatCode_depth, v);}    
  }

    