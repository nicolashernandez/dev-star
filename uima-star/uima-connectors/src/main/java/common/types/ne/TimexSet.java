

/* First created by JCasGen Fri Mar 08 15:28:14 CET 2013 */
package common.types.ne;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;



/** TIMEX3 André Bittar
 * Updated by JCasGen Thu May 02 23:08:54 CEST 2013
 * XML source: /media/ext4/workspace/uima-connectors/desc/connectors/corpus/P7T/P7TPlusConstit-XML2CAS-mapper-CAS2OpenNLPChunkTagger-fineGrained-CAS2CSV-ViewWriter-AAE.xml
 * @generated */
public class TimexSet extends NamedEntity {
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = JCasRegistry.register(TimexSet.class);
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
  protected TimexSet() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated */
  public TimexSet(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated */
  public TimexSet(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated */  
  public TimexSet(JCas jcas, int begin, int end) {
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

    