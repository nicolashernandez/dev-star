
/* First created by JCasGen Wed May 27 22:41:00 CEST 2015 */
package common.types.extml;

import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.cas.impl.CASImpl;
import org.apache.uima.cas.impl.FSGenerator;
import org.apache.uima.cas.FeatureStructure;
import org.apache.uima.cas.impl.TypeImpl;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.impl.FeatureImpl;
import org.apache.uima.cas.Feature;
import org.apache.uima.jcas.tcas.Annotation_Type;

/** 
 * Updated by JCasGen Wed May 27 22:41:00 CEST 2015
 * @generated */
public class XMLAttributeAnnotation_Type extends Annotation_Type {
  /** @generated 
   * @return the generator for this type
   */
  @Override
  protected FSGenerator getFSGenerator() {return fsGenerator;}
  /** @generated */
  private final FSGenerator fsGenerator = 
    new FSGenerator() {
      public FeatureStructure createFS(int addr, CASImpl cas) {
  			 if (XMLAttributeAnnotation_Type.this.useExistingInstance) {
  			   // Return eq fs instance if already created
  		     FeatureStructure fs = XMLAttributeAnnotation_Type.this.jcas.getJfsFromCaddr(addr);
  		     if (null == fs) {
  		       fs = new XMLAttributeAnnotation(addr, XMLAttributeAnnotation_Type.this);
  			   XMLAttributeAnnotation_Type.this.jcas.putJfsFromCaddr(addr, fs);
  			   return fs;
  		     }
  		     return fs;
        } else return new XMLAttributeAnnotation(addr, XMLAttributeAnnotation_Type.this);
  	  }
    };
  /** @generated */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = XMLAttributeAnnotation.typeIndexID;
  /** @generated 
     @modifiable */
  @SuppressWarnings ("hiding")
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("common.types.extml.XMLAttributeAnnotation");
 
  /** @generated */
  final Feature casFeat_uri;
  /** @generated */
  final int     casFeatCode_uri;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public String getUri(int addr) {
        if (featOkTst && casFeat_uri == null)
      jcas.throwFeatMissing("uri", "common.types.extml.XMLAttributeAnnotation");
    return ll_cas.ll_getStringValue(addr, casFeatCode_uri);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setUri(int addr, String v) {
        if (featOkTst && casFeat_uri == null)
      jcas.throwFeatMissing("uri", "common.types.extml.XMLAttributeAnnotation");
    ll_cas.ll_setStringValue(addr, casFeatCode_uri, v);}
    
  
 
  /** @generated */
  final Feature casFeat_qualifiedName;
  /** @generated */
  final int     casFeatCode_qualifiedName;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public String getQualifiedName(int addr) {
        if (featOkTst && casFeat_qualifiedName == null)
      jcas.throwFeatMissing("qualifiedName", "common.types.extml.XMLAttributeAnnotation");
    return ll_cas.ll_getStringValue(addr, casFeatCode_qualifiedName);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setQualifiedName(int addr, String v) {
        if (featOkTst && casFeat_qualifiedName == null)
      jcas.throwFeatMissing("qualifiedName", "common.types.extml.XMLAttributeAnnotation");
    ll_cas.ll_setStringValue(addr, casFeatCode_qualifiedName, v);}
    
  
 
  /** @generated */
  final Feature casFeat_localName;
  /** @generated */
  final int     casFeatCode_localName;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public String getLocalName(int addr) {
        if (featOkTst && casFeat_localName == null)
      jcas.throwFeatMissing("localName", "common.types.extml.XMLAttributeAnnotation");
    return ll_cas.ll_getStringValue(addr, casFeatCode_localName);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setLocalName(int addr, String v) {
        if (featOkTst && casFeat_localName == null)
      jcas.throwFeatMissing("localName", "common.types.extml.XMLAttributeAnnotation");
    ll_cas.ll_setStringValue(addr, casFeatCode_localName, v);}
    
  
 
  /** @generated */
  final Feature casFeat_value;
  /** @generated */
  final int     casFeatCode_value;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public String getValue(int addr) {
        if (featOkTst && casFeat_value == null)
      jcas.throwFeatMissing("value", "common.types.extml.XMLAttributeAnnotation");
    return ll_cas.ll_getStringValue(addr, casFeatCode_value);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setValue(int addr, String v) {
        if (featOkTst && casFeat_value == null)
      jcas.throwFeatMissing("value", "common.types.extml.XMLAttributeAnnotation");
    ll_cas.ll_setStringValue(addr, casFeatCode_value, v);}
    
  
 
  /** @generated */
  final Feature casFeat_element;
  /** @generated */
  final int     casFeatCode_element;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public int getElement(int addr) {
        if (featOkTst && casFeat_element == null)
      jcas.throwFeatMissing("element", "common.types.extml.XMLAttributeAnnotation");
    return ll_cas.ll_getRefValue(addr, casFeatCode_element);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setElement(int addr, int v) {
        if (featOkTst && casFeat_element == null)
      jcas.throwFeatMissing("element", "common.types.extml.XMLAttributeAnnotation");
    ll_cas.ll_setRefValue(addr, casFeatCode_element, v);}
    
  



  /** initialize variables to correspond with Cas Type and Features
	 * @generated
	 * @param jcas JCas
	 * @param casType Type 
	 */
  public XMLAttributeAnnotation_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_uri = jcas.getRequiredFeatureDE(casType, "uri", "uima.cas.String", featOkTst);
    casFeatCode_uri  = (null == casFeat_uri) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_uri).getCode();

 
    casFeat_qualifiedName = jcas.getRequiredFeatureDE(casType, "qualifiedName", "uima.cas.String", featOkTst);
    casFeatCode_qualifiedName  = (null == casFeat_qualifiedName) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_qualifiedName).getCode();

 
    casFeat_localName = jcas.getRequiredFeatureDE(casType, "localName", "uima.cas.String", featOkTst);
    casFeatCode_localName  = (null == casFeat_localName) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_localName).getCode();

 
    casFeat_value = jcas.getRequiredFeatureDE(casType, "value", "uima.cas.String", featOkTst);
    casFeatCode_value  = (null == casFeat_value) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_value).getCode();

 
    casFeat_element = jcas.getRequiredFeatureDE(casType, "element", "common.types.extml.XMLElementAnnotation", featOkTst);
    casFeatCode_element  = (null == casFeat_element) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_element).getCode();

  }
}



    