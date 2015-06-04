

/* First created by JCasGen Tue Jul 05 15:38:26 CEST 2011 */
package opennlp.uima;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import org.apache.uima.jcas.tcas.Annotation;


/** 
 * Updated by JCasGen Wed Jun 12 13:50:33 CEST 2013
 * XML source: /media/ext4/workspace/uima-opennlp/desc/opennlp/fr/OrganizationNameFinder.xml
 * @generated */
public class Organization extends Annotation {
  /** @generated
   * @ordered 
   */
  public final static int typeIndexID = JCasRegistry.register(Organization.class);
  /** @generated
   * @ordered 
   */
  public final static int type = typeIndexID;
  /** @generated  */
  public              int getTypeIndexID() {return typeIndexID;}
 
  /** Never called.  Disable default constructor
   * @generated */
  protected Organization() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated */
  public Organization(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated */
  public Organization(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated */  
  public Organization(JCas jcas, int begin, int end) {
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

    