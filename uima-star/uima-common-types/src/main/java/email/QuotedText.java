

/* First created by JCasGen Wed May 27 22:43:37 CEST 2015 */
package email;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import org.apache.uima.jcas.tcas.Annotation;


/** Consecutive sequence of quoted lines
 * Updated by JCasGen Wed May 27 22:43:37 CEST 2015
 * XML source: /media/hernandez-n/ext4/workspace/lina-star/uima-star/uima-common-types/src/main/resources/common/types/CommonTS.xml
 * @generated */
public class QuotedText extends Annotation {
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = JCasRegistry.register(QuotedText.class);
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
  protected QuotedText() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated
   * @param addr low level Feature Structure reference
   * @param type the type of this Feature Structure 
   */
  public QuotedText(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated
   * @param jcas JCas to which this Feature Structure belongs 
   */
  public QuotedText(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated
   * @param jcas JCas to which this Feature Structure belongs
   * @param begin offset to the begin spot in the SofA
   * @param end offset to the end spot in the SofA 
  */  
  public QuotedText(JCas jcas, int begin, int end) {
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
  //* Feature: attribution

  /** getter for attribution - gets 
   * @generated
   * @return value of the feature 
   */
  public AttributionLine getAttribution() {
    if (QuotedText_Type.featOkTst && ((QuotedText_Type)jcasType).casFeat_attribution == null)
      jcasType.jcas.throwFeatMissing("attribution", "email.QuotedText");
    return (AttributionLine)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((QuotedText_Type)jcasType).casFeatCode_attribution)));}
    
  /** setter for attribution - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setAttribution(AttributionLine v) {
    if (QuotedText_Type.featOkTst && ((QuotedText_Type)jcasType).casFeat_attribution == null)
      jcasType.jcas.throwFeatMissing("attribution", "email.QuotedText");
    jcasType.ll_cas.ll_setRefValue(addr, ((QuotedText_Type)jcasType).casFeatCode_attribution, jcasType.ll_cas.ll_getFSRef(v));}    
  }

    