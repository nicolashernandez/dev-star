

/* First created by JCasGen Thu May 30 13:40:53 CEST 2013 */
package common.types.ne;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;



/** subcategory values :
vehicule Moyen de transport, 
award Récompense, 
art Oeuvre artistique, 
documentary Production documentaire
 * Updated by JCasGen Thu May 30 13:40:53 CEST 2013
 * XML source: /media/ext4/workspace/uima-workflow/desc/wf/analysis/importTutinArcadeXRCELeMondeCorpus-openNLPPOSTaggerFr-maltparser-AAE.xml
 * @generated */
public class Work extends NamedEntity {
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = JCasRegistry.register(Work.class);
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
  protected Work() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated */
  public Work(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated */
  public Work(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated */  
  public Work(JCas jcas, int begin, int end) {
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

    