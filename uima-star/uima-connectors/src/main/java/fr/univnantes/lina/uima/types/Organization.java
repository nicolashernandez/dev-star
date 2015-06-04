

/* First created by JCasGen Thu Mar 17 20:35:41 CET 2011 */
package fr.univnantes.lina.uima.types;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;



/** category values : 
politic politique,
academic éducative, 
business commerciale, 
nonBusiness non commerciale, 
mediaEntertainment de divertissement et média, 
geoAdministrative géo-administrative
 * Updated by JCasGen Thu Mar 17 20:35:41 CET 2011
 * XML source: /media/MyPassport/current/public/research/UIMA-USER-DEV-ENV/workspace/uima-connectors/desc/nerc/NERCTS.xml
 * @generated */
public class Organization extends NamedEntity {
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
  protected Organization() {}
    
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

    