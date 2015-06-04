

/* First created by JCasGen Thu May 30 11:49:30 CEST 2013 */
package common.types.ne;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;



/** subcategory values :
natural géographique naturel, 
administrative région administrative,
road axe de circulation, 
postAddress adresse postale, 
phoneNumber Numéro de téléphone et 
faxNumber fax, 
email adresse électronique
 * Updated by JCasGen Thu May 30 11:49:30 CEST 2013
 * XML source: /media/ext4/workspace/uima-maltparser/desc/maltParser/wst-openNLPPOSTagger-maltparser.AAE.xml
 * @generated */
public class Location extends NamedEntity {
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = JCasRegistry.register(Location.class);
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
  protected Location() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated */
  public Location(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated */
  public Location(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated */  
  public Location(JCas jcas, int begin, int end) {
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
     
 
    
  //*--------------*
  //* Feature: operator

  /** getter for operator - gets http://en.wikipedia.org/wiki/Location_(geography)
Location, in geography, is a position or point in physical space that something occupies on the Earth's surface or area in the Solar System, or mankinds physically reachable universe.
An absolute location is designated using a specific pairing of latitude and longitude, a Cartesian coordinate grid (e.g.,a Spherical coordinate system), an ellipsoid-based system (e.g., World Geodetic System), or similar methods.
A relative location is the location of a place or area in relation to another site, i.e. "3 miles northwest of Chicago".
   * @generated */
  public String getOperator() {
    if (Location_Type.featOkTst && ((Location_Type)jcasType).casFeat_operator == null)
      jcasType.jcas.throwFeatMissing("operator", "common.types.ne.Location");
    return jcasType.ll_cas.ll_getStringValue(addr, ((Location_Type)jcasType).casFeatCode_operator);}
    
  /** setter for operator - sets http://en.wikipedia.org/wiki/Location_(geography)
Location, in geography, is a position or point in physical space that something occupies on the Earth's surface or area in the Solar System, or mankinds physically reachable universe.
An absolute location is designated using a specific pairing of latitude and longitude, a Cartesian coordinate grid (e.g.,a Spherical coordinate system), an ellipsoid-based system (e.g., World Geodetic System), or similar methods.
A relative location is the location of a place or area in relation to another site, i.e. "3 miles northwest of Chicago". 
   * @generated */
  public void setOperator(String v) {
    if (Location_Type.featOkTst && ((Location_Type)jcasType).casFeat_operator == null)
      jcasType.jcas.throwFeatMissing("operator", "common.types.ne.Location");
    jcasType.ll_cas.ll_setStringValue(addr, ((Location_Type)jcasType).casFeatCode_operator, v);}    
  }

    