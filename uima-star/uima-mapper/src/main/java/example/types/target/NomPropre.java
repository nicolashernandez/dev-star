

/* First created by JCasGen Thu Apr 15 13:48:45 CEST 2010 */
package example.types.target;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;



/** 
 * Updated by JCasGen Mon Mar 28 15:53:10 CEST 2011
 * XML source: /media/MyPassport/current/public/research/UIMA-USER-DEV-ENV/workspace/uima-annotation-mapper/desc/example/post-annotation-mapper_TS.xml
 * @generated */
public class NomPropre extends Nom {
  /** @generated
   * @ordered 
   */
  public final static int typeIndexID = JCasRegistry.register(NomPropre.class);
  /** @generated
   * @ordered 
   */
  public final static int type = typeIndexID;
  /** @generated  */
  public              int getTypeIndexID() {return typeIndexID;}
 
  /** Never called.  Disable default constructor
   * @generated */
  protected NomPropre() {}
    
  /** Internal - constructor used by generator 
   * @generated */
  public NomPropre(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated */
  public NomPropre(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated */  
  public NomPropre(JCas jcas, int begin, int end) {
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

    