

/* First created by JCasGen Sat Dec 10 00:59:10 CET 2011 */
package common.types.rs;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;



/** 
 * Updated by JCasGen Sat Dec 10 00:59:12 CET 2011
 * XML source: /media/MyPassport/current/public/research/UIMA-USER-DEV-ENV/workspace/demo-apache-opennlp-uima/desc/opennlp/fr/SentenceDetectorAE.xml
 * @generated */
public class ReportedSpeechDiscourse extends ReportedSpeech {
  /** @generated
   * @ordered 
   */
  public final static int typeIndexID = JCasRegistry.register(ReportedSpeechDiscourse.class);
  /** @generated
   * @ordered 
   */
  public final static int type = typeIndexID;
  /** @generated  */
  public              int getTypeIndexID() {return typeIndexID;}
 
  /** Never called.  Disable default constructor
   * @generated */
  protected ReportedSpeechDiscourse() {}
    
  /** Internal - constructor used by generator 
   * @generated */
  public ReportedSpeechDiscourse(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated */
  public ReportedSpeechDiscourse(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated */  
  public ReportedSpeechDiscourse(JCas jcas, int begin, int end) {
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
     
}

    