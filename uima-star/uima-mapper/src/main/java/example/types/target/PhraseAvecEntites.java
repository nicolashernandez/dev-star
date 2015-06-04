

/* First created by JCasGen Thu Apr 15 13:48:45 CEST 2010 */
package example.types.target;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import org.apache.uima.jcas.tcas.Annotation;


/** 
 * Updated by JCasGen Thu Apr 15 16:18:23 CEST 2010
 * XML source: /media/MyPassport/current/public/research/ACTIVITES/RSRCH_DVPT_WRKSPCE/0_UIMA-USER-DEV-ENV/workspace/uima-type-mapper/desc/example/post-type-mapper-example_AE.xml
 * @generated */
public class PhraseAvecEntites extends Annotation {
  /** @generated
   * @ordered 
   */
  public final static int typeIndexID = JCasRegistry.register(PhraseAvecEntites.class);
  /** @generated
   * @ordered 
   */
  public final static int type = typeIndexID;
  /** @generated  */
  public              int getTypeIndexID() {return typeIndexID;}
 
  /** Never called.  Disable default constructor
   * @generated */
  protected PhraseAvecEntites() {}
    
  /** Internal - constructor used by generator 
   * @generated */
  public PhraseAvecEntites(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated */
  public PhraseAvecEntites(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated */  
  public PhraseAvecEntites(JCas jcas, int begin, int end) {
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

    