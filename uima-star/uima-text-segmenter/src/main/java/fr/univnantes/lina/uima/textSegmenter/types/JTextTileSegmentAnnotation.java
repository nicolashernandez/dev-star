

/* First created by JCasGen Tue Mar 08 14:02:41 CET 2011 */
package fr.univnantes.lina.uima.textSegmenter.types;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import org.apache.uima.jcas.tcas.Annotation;


/** Default Segment annotation
 * Updated by JCasGen Tue Mar 08 14:02:41 CET 2011
 * XML source: /media/MyPassport/current/public/research/UIMA-USER-DEV-ENV/workspace/uima-text-segmenter/desc/textSegmenter/JTextTileAE.xml
 * @generated */
public class JTextTileSegmentAnnotation extends Annotation {
  /** @generated
   * @ordered 
   */
  public final static int typeIndexID = JCasRegistry.register(JTextTileSegmentAnnotation.class);
  /** @generated
   * @ordered 
   */
  public final static int type = typeIndexID;
  /** @generated  */
  public              int getTypeIndexID() {return typeIndexID;}
 
  /** Never called.  Disable default constructor
   * @generated */
  protected JTextTileSegmentAnnotation() {}
    
  /** Internal - constructor used by generator 
   * @generated */
  public JTextTileSegmentAnnotation(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated */
  public JTextTileSegmentAnnotation(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated */  
  public JTextTileSegmentAnnotation(JCas jcas, int begin, int end) {
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

    