

/* First created by JCasGen Wed Mar 02 17:28:18 CET 2011 */
package fr.univnantes.lina.uima.textSegmenter.types;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import org.apache.uima.jcas.tcas.Annotation;


/** Default text segmentation annotation
 * Updated by JCasGen Fri Dec 09 17:21:14 CET 2011
 * XML source: /media/MyPassport/current/public/research/UIMA-USER-DEV-ENV/workspace/uima-text-segmenter/desc/textSegmenter/TextSegmenterTS.xml
 * @generated */
public class SegmentAnnotation extends Annotation {
  /** @generated
   * @ordered 
   */
  public final static int typeIndexID = JCasRegistry.register(SegmentAnnotation.class);
  /** @generated
   * @ordered 
   */
  public final static int type = typeIndexID;
  /** @generated  */
  public              int getTypeIndexID() {return typeIndexID;}
 
  /** Never called.  Disable default constructor
   * @generated */
  protected SegmentAnnotation() {}
    
  /** Internal - constructor used by generator 
   * @generated */
  public SegmentAnnotation(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated */
  public SegmentAnnotation(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated */  
  public SegmentAnnotation(JCas jcas, int begin, int end) {
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

    