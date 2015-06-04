

/* First created by JCasGen Tue Oct 02 16:21:38 CEST 2012 */
package common.types.ext;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import org.apache.uima.jcas.tcas.Annotation;


/** 
 * Updated by JCasGen Mon Mar 25 22:57:13 CET 2013
 * XML source: /media/MyPassport/workspace/uima-connectors/desc/connectors/corpus/P7T/P7TPlusTagged-XML2CAS-mapTo-sw+mw-WST+SPL-ViewWriter.xml
 * @generated */
public class FTBSentence extends Annotation {
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = JCasRegistry.register(FTBSentence.class);
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
  protected FTBSentence() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated */
  public FTBSentence(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated */
  public FTBSentence(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated */  
  public FTBSentence(JCas jcas, int begin, int end) {
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
     
}

    