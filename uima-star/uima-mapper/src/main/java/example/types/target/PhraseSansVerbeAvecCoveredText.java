

/* First created by JCasGen Fri Apr 16 00:01:40 CEST 2010 */
package example.types.target;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import org.apache.uima.jcas.tcas.Annotation;


/** 
 * Updated by JCasGen Mon Mar 28 15:53:11 CEST 2011
 * XML source: /media/MyPassport/current/public/research/UIMA-USER-DEV-ENV/workspace/uima-annotation-mapper/desc/example/post-annotation-mapper_TS.xml
 * @generated */
public class PhraseSansVerbeAvecCoveredText extends Annotation {
  /** @generated
   * @ordered 
   */
  public final static int typeIndexID = JCasRegistry.register(PhraseSansVerbeAvecCoveredText.class);
  /** @generated
   * @ordered 
   */
  public final static int type = typeIndexID;
  /** @generated  */
  public              int getTypeIndexID() {return typeIndexID;}
 
  /** Never called.  Disable default constructor
   * @generated */
  protected PhraseSansVerbeAvecCoveredText() {}
    
  /** Internal - constructor used by generator 
   * @generated */
  public PhraseSansVerbeAvecCoveredText(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated */
  public PhraseSansVerbeAvecCoveredText(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated */  
  public PhraseSansVerbeAvecCoveredText(JCas jcas, int begin, int end) {
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

    