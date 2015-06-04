

/* First created by JCasGen Thu Sep 30 22:49:27 CEST 2010 */
package fr.univnantes.lina.uima.shell.types;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import fr.univnantes.lina.uima.types.OneDimensionAnnotation;


/** 
 * Updated by JCasGen Sat May 26 21:22:19 CEST 2012
 * XML source: /media/MyPassport/workspace/uima-shell/desc/shell/examples/EXAMPLE-PASS-wst_cas2csv_shellTreetagger_csv2cas_AAE.xml
 * @generated */
public class ShellAnnotation extends OneDimensionAnnotation {
  /** @generated
   * @ordered 
   */
  public final static int typeIndexID = JCasRegistry.register(ShellAnnotation.class);
  /** @generated
   * @ordered 
   */
  public final static int type = typeIndexID;
  /** @generated  */
  public              int getTypeIndexID() {return typeIndexID;}
 
  /** Never called.  Disable default constructor
   * @generated */
  protected ShellAnnotation() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated */
  public ShellAnnotation(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated */
  public ShellAnnotation(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated */  
  public ShellAnnotation(JCas jcas, int begin, int end) {
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
    if (ShellAnnotation_Type.featOkTst && ((ShellAnnotation_Type)jcasType).casFeat_value == null)
      jcasType.jcas.throwFeatMissing("value", "fr.univnantes.lina.uima.shell.types.ShellAnnotation");
    return jcasType.ll_cas.ll_getStringValue(addr, ((ShellAnnotation_Type)jcasType).casFeatCode_value);}
    
  /** setter for value - sets  
   * @generated */
  public void setValue(String v) {
    if (ShellAnnotation_Type.featOkTst && ((ShellAnnotation_Type)jcasType).casFeat_value == null)
      jcasType.jcas.throwFeatMissing("value", "fr.univnantes.lina.uima.shell.types.ShellAnnotation");
    jcasType.ll_cas.ll_setStringValue(addr, ((ShellAnnotation_Type)jcasType).casFeatCode_value, v);}    
  }

    