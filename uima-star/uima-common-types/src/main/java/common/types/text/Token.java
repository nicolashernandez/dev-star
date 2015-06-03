

/* First created by JCasGen Wed May 27 22:41:00 CEST 2015 */
package common.types.text;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;



/** features based on French characteristics enumerated by http://aune.lpl.univ-aix.fr/projects/multext/LEX/LEX1.html
 * Updated by JCasGen Wed May 27 22:41:00 CEST 2015
 * XML source: /media/hernandez-n/ext4/workspace/lina-star/uima-star/uima-common-types/src/main/resources/common/types/commonTS.xml
 * @generated */
public class Token extends Annotation {
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = JCasRegistry.register(Token.class);
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int type = typeIndexID;
  /** @generated
   * @return index of the type  
   */
  @Override
  public              int getTypeIndexID() {return typeIndexID;}
 
  /** Never called.  Disable default constructor
   * @generated */
  protected Token() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated
   * @param addr low level Feature Structure reference
   * @param type the type of this Feature Structure 
   */
  public Token(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated
   * @param jcas JCas to which this Feature Structure belongs 
   */
  public Token(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated
   * @param jcas JCas to which this Feature Structure belongs
   * @param begin offset to the begin spot in the SofA
   * @param end offset to the end spot in the SofA 
  */  
  public Token(JCas jcas, int begin, int end) {
    super(jcas);
    setBegin(begin);
    setEnd(end);
    readObject();
  }   

  /** 
   * <!-- begin-user-doc -->
   * Write your own initialization here
   * <!-- end-user-doc -->
   *
   * @generated modifiable 
   */
  private void readObject() {/*default - does nothing empty block */}
     
 
    
  //*--------------*
  //* Feature: lemma

  /** getter for lemma - gets Lemma of the word.
   * @generated
   * @return value of the feature 
   */
  public String getLemma() {
    if (Token_Type.featOkTst && ((Token_Type)jcasType).casFeat_lemma == null)
      jcasType.jcas.throwFeatMissing("lemma", "common.types.text.Token");
    return jcasType.ll_cas.ll_getStringValue(addr, ((Token_Type)jcasType).casFeatCode_lemma);}
    
  /** setter for lemma - sets Lemma of the word. 
   * @generated
   * @param v value to set into the feature 
   */
  public void setLemma(String v) {
    if (Token_Type.featOkTst && ((Token_Type)jcasType).casFeat_lemma == null)
      jcasType.jcas.throwFeatMissing("lemma", "common.types.text.Token");
    jcasType.ll_cas.ll_setStringValue(addr, ((Token_Type)jcasType).casFeatCode_lemma, v);}    
   
    
  //*--------------*
  //* Feature: mph

  /** getter for mph - gets ?
   * @generated
   * @return value of the feature 
   */
  public String getMph() {
    if (Token_Type.featOkTst && ((Token_Type)jcasType).casFeat_mph == null)
      jcasType.jcas.throwFeatMissing("mph", "common.types.text.Token");
    return jcasType.ll_cas.ll_getStringValue(addr, ((Token_Type)jcasType).casFeatCode_mph);}
    
  /** setter for mph - sets ? 
   * @generated
   * @param v value to set into the feature 
   */
  public void setMph(String v) {
    if (Token_Type.featOkTst && ((Token_Type)jcasType).casFeat_mph == null)
      jcasType.jcas.throwFeatMissing("mph", "common.types.text.Token");
    jcasType.ll_cas.ll_setStringValue(addr, ((Token_Type)jcasType).casFeatCode_mph, v);}    
   
    
  //*--------------*
  //* Feature: cat

  /** getter for cat - gets 
   * @generated
   * @return value of the feature 
   */
  public String getCat() {
    if (Token_Type.featOkTst && ((Token_Type)jcasType).casFeat_cat == null)
      jcasType.jcas.throwFeatMissing("cat", "common.types.text.Token");
    return jcasType.ll_cas.ll_getStringValue(addr, ((Token_Type)jcasType).casFeatCode_cat);}
    
  /** setter for cat - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setCat(String v) {
    if (Token_Type.featOkTst && ((Token_Type)jcasType).casFeat_cat == null)
      jcasType.jcas.throwFeatMissing("cat", "common.types.text.Token");
    jcasType.ll_cas.ll_setStringValue(addr, ((Token_Type)jcasType).casFeatCode_cat, v);}    
   
    
  //*--------------*
  //* Feature: raw

  /** getter for raw - gets 
   * @generated
   * @return value of the feature 
   */
  public String getRaw() {
    if (Token_Type.featOkTst && ((Token_Type)jcasType).casFeat_raw == null)
      jcasType.jcas.throwFeatMissing("raw", "common.types.text.Token");
    return jcasType.ll_cas.ll_getStringValue(addr, ((Token_Type)jcasType).casFeatCode_raw);}
    
  /** setter for raw - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setRaw(String v) {
    if (Token_Type.featOkTst && ((Token_Type)jcasType).casFeat_raw == null)
      jcasType.jcas.throwFeatMissing("raw", "common.types.text.Token");
    jcasType.ll_cas.ll_setStringValue(addr, ((Token_Type)jcasType).casFeatCode_raw, v);}    
   
    
  //*--------------*
  //* Feature: subCat

  /** getter for subCat - gets 
   * @generated
   * @return value of the feature 
   */
  public String getSubCat() {
    if (Token_Type.featOkTst && ((Token_Type)jcasType).casFeat_subCat == null)
      jcasType.jcas.throwFeatMissing("subCat", "common.types.text.Token");
    return jcasType.ll_cas.ll_getStringValue(addr, ((Token_Type)jcasType).casFeatCode_subCat);}
    
  /** setter for subCat - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setSubCat(String v) {
    if (Token_Type.featOkTst && ((Token_Type)jcasType).casFeat_subCat == null)
      jcasType.jcas.throwFeatMissing("subCat", "common.types.text.Token");
    jcasType.ll_cas.ll_setStringValue(addr, ((Token_Type)jcasType).casFeatCode_subCat, v);}    
   
    
  //*--------------*
  //* Feature: stem

  /** getter for stem - gets (based on Sandbox Snowball Annotator definition)
   * @generated
   * @return value of the feature 
   */
  public String getStem() {
    if (Token_Type.featOkTst && ((Token_Type)jcasType).casFeat_stem == null)
      jcasType.jcas.throwFeatMissing("stem", "common.types.text.Token");
    return jcasType.ll_cas.ll_getStringValue(addr, ((Token_Type)jcasType).casFeatCode_stem);}
    
  /** setter for stem - sets (based on Sandbox Snowball Annotator definition) 
   * @generated
   * @param v value to set into the feature 
   */
  public void setStem(String v) {
    if (Token_Type.featOkTst && ((Token_Type)jcasType).casFeat_stem == null)
      jcasType.jcas.throwFeatMissing("stem", "common.types.text.Token");
    jcasType.ll_cas.ll_setStringValue(addr, ((Token_Type)jcasType).casFeatCode_stem, v);}    
   
    
  //*--------------*
  //* Feature: corefType

  /** getter for corefType - gets type de coreference (coref or not) see Tutin corpus
   * @generated
   * @return value of the feature 
   */
  public String getCorefType() {
    if (Token_Type.featOkTst && ((Token_Type)jcasType).casFeat_corefType == null)
      jcasType.jcas.throwFeatMissing("corefType", "common.types.text.Token");
    return jcasType.ll_cas.ll_getStringValue(addr, ((Token_Type)jcasType).casFeatCode_corefType);}
    
  /** setter for corefType - sets type de coreference (coref or not) see Tutin corpus 
   * @generated
   * @param v value to set into the feature 
   */
  public void setCorefType(String v) {
    if (Token_Type.featOkTst && ((Token_Type)jcasType).casFeat_corefType == null)
      jcasType.jcas.throwFeatMissing("corefType", "common.types.text.Token");
    jcasType.ll_cas.ll_setStringValue(addr, ((Token_Type)jcasType).casFeatCode_corefType, v);}    
   
    
  //*--------------*
  //* Feature: gender

  /** getter for gender - gets 
   * @generated
   * @return value of the feature 
   */
  public String getGender() {
    if (Token_Type.featOkTst && ((Token_Type)jcasType).casFeat_gender == null)
      jcasType.jcas.throwFeatMissing("gender", "common.types.text.Token");
    return jcasType.ll_cas.ll_getStringValue(addr, ((Token_Type)jcasType).casFeatCode_gender);}
    
  /** setter for gender - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setGender(String v) {
    if (Token_Type.featOkTst && ((Token_Type)jcasType).casFeat_gender == null)
      jcasType.jcas.throwFeatMissing("gender", "common.types.text.Token");
    jcasType.ll_cas.ll_setStringValue(addr, ((Token_Type)jcasType).casFeatCode_gender, v);}    
   
    
  //*--------------*
  //* Feature: number

  /** getter for number - gets 
   * @generated
   * @return value of the feature 
   */
  public String getNumber() {
    if (Token_Type.featOkTst && ((Token_Type)jcasType).casFeat_number == null)
      jcasType.jcas.throwFeatMissing("number", "common.types.text.Token");
    return jcasType.ll_cas.ll_getStringValue(addr, ((Token_Type)jcasType).casFeatCode_number);}
    
  /** setter for number - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setNumber(String v) {
    if (Token_Type.featOkTst && ((Token_Type)jcasType).casFeat_number == null)
      jcasType.jcas.throwFeatMissing("number", "common.types.text.Token");
    jcasType.ll_cas.ll_setStringValue(addr, ((Token_Type)jcasType).casFeatCode_number, v);}    
   
    
  //*--------------*
  //* Feature: case

  /** getter for case - gets 
   * @generated
   * @return value of the feature 
   */
  public String getCase() {
    if (Token_Type.featOkTst && ((Token_Type)jcasType).casFeat_case == null)
      jcasType.jcas.throwFeatMissing("case", "common.types.text.Token");
    return jcasType.ll_cas.ll_getStringValue(addr, ((Token_Type)jcasType).casFeatCode_case);}
    
  /** setter for case - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setCase(String v) {
    if (Token_Type.featOkTst && ((Token_Type)jcasType).casFeat_case == null)
      jcasType.jcas.throwFeatMissing("case", "common.types.text.Token");
    jcasType.ll_cas.ll_setStringValue(addr, ((Token_Type)jcasType).casFeatCode_case, v);}    
   
    
  //*--------------*
  //* Feature: person

  /** getter for person - gets 
   * @generated
   * @return value of the feature 
   */
  public String getPerson() {
    if (Token_Type.featOkTst && ((Token_Type)jcasType).casFeat_person == null)
      jcasType.jcas.throwFeatMissing("person", "common.types.text.Token");
    return jcasType.ll_cas.ll_getStringValue(addr, ((Token_Type)jcasType).casFeatCode_person);}
    
  /** setter for person - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setPerson(String v) {
    if (Token_Type.featOkTst && ((Token_Type)jcasType).casFeat_person == null)
      jcasType.jcas.throwFeatMissing("person", "common.types.text.Token");
    jcasType.ll_cas.ll_setStringValue(addr, ((Token_Type)jcasType).casFeatCode_person, v);}    
   
    
  //*--------------*
  //* Feature: mood

  /** getter for mood - gets 
   * @generated
   * @return value of the feature 
   */
  public String getMood() {
    if (Token_Type.featOkTst && ((Token_Type)jcasType).casFeat_mood == null)
      jcasType.jcas.throwFeatMissing("mood", "common.types.text.Token");
    return jcasType.ll_cas.ll_getStringValue(addr, ((Token_Type)jcasType).casFeatCode_mood);}
    
  /** setter for mood - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setMood(String v) {
    if (Token_Type.featOkTst && ((Token_Type)jcasType).casFeat_mood == null)
      jcasType.jcas.throwFeatMissing("mood", "common.types.text.Token");
    jcasType.ll_cas.ll_setStringValue(addr, ((Token_Type)jcasType).casFeatCode_mood, v);}    
   
    
  //*--------------*
  //* Feature: tense

  /** getter for tense - gets 
   * @generated
   * @return value of the feature 
   */
  public String getTense() {
    if (Token_Type.featOkTst && ((Token_Type)jcasType).casFeat_tense == null)
      jcasType.jcas.throwFeatMissing("tense", "common.types.text.Token");
    return jcasType.ll_cas.ll_getStringValue(addr, ((Token_Type)jcasType).casFeatCode_tense);}
    
  /** setter for tense - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setTense(String v) {
    if (Token_Type.featOkTst && ((Token_Type)jcasType).casFeat_tense == null)
      jcasType.jcas.throwFeatMissing("tense", "common.types.text.Token");
    jcasType.ll_cas.ll_setStringValue(addr, ((Token_Type)jcasType).casFeatCode_tense, v);}    
   
    
  //*--------------*
  //* Feature: catP

  /** getter for catP - gets feature to accept a ftb+ tag
   * @generated
   * @return value of the feature 
   */
  public String getCatP() {
    if (Token_Type.featOkTst && ((Token_Type)jcasType).casFeat_catP == null)
      jcasType.jcas.throwFeatMissing("catP", "common.types.text.Token");
    return jcasType.ll_cas.ll_getStringValue(addr, ((Token_Type)jcasType).casFeatCode_catP);}
    
  /** setter for catP - sets feature to accept a ftb+ tag 
   * @generated
   * @param v value to set into the feature 
   */
  public void setCatP(String v) {
    if (Token_Type.featOkTst && ((Token_Type)jcasType).casFeat_catP == null)
      jcasType.jcas.throwFeatMissing("catP", "common.types.text.Token");
    jcasType.ll_cas.ll_setStringValue(addr, ((Token_Type)jcasType).casFeatCode_catP, v);}    
   
    
  //*--------------*
  //* Feature: coarseGrainedChunkTag

  /** getter for coarseGrainedChunkTag - gets 
   * @generated
   * @return value of the feature 
   */
  public String getCoarseGrainedChunkTag() {
    if (Token_Type.featOkTst && ((Token_Type)jcasType).casFeat_coarseGrainedChunkTag == null)
      jcasType.jcas.throwFeatMissing("coarseGrainedChunkTag", "common.types.text.Token");
    return jcasType.ll_cas.ll_getStringValue(addr, ((Token_Type)jcasType).casFeatCode_coarseGrainedChunkTag);}
    
  /** setter for coarseGrainedChunkTag - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setCoarseGrainedChunkTag(String v) {
    if (Token_Type.featOkTst && ((Token_Type)jcasType).casFeat_coarseGrainedChunkTag == null)
      jcasType.jcas.throwFeatMissing("coarseGrainedChunkTag", "common.types.text.Token");
    jcasType.ll_cas.ll_setStringValue(addr, ((Token_Type)jcasType).casFeatCode_coarseGrainedChunkTag, v);}    
   
    
  //*--------------*
  //* Feature: embeddingPathChunkTag

  /** getter for embeddingPathChunkTag - gets 
   * @generated
   * @return value of the feature 
   */
  public String getEmbeddingPathChunkTag() {
    if (Token_Type.featOkTst && ((Token_Type)jcasType).casFeat_embeddingPathChunkTag == null)
      jcasType.jcas.throwFeatMissing("embeddingPathChunkTag", "common.types.text.Token");
    return jcasType.ll_cas.ll_getStringValue(addr, ((Token_Type)jcasType).casFeatCode_embeddingPathChunkTag);}
    
  /** setter for embeddingPathChunkTag - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setEmbeddingPathChunkTag(String v) {
    if (Token_Type.featOkTst && ((Token_Type)jcasType).casFeat_embeddingPathChunkTag == null)
      jcasType.jcas.throwFeatMissing("embeddingPathChunkTag", "common.types.text.Token");
    jcasType.ll_cas.ll_setStringValue(addr, ((Token_Type)jcasType).casFeatCode_embeddingPathChunkTag, v);}    
   
    
  //*--------------*
  //* Feature: fineGrainedChunkTag

  /** getter for fineGrainedChunkTag - gets 
   * @generated
   * @return value of the feature 
   */
  public String getFineGrainedChunkTag() {
    if (Token_Type.featOkTst && ((Token_Type)jcasType).casFeat_fineGrainedChunkTag == null)
      jcasType.jcas.throwFeatMissing("fineGrainedChunkTag", "common.types.text.Token");
    return jcasType.ll_cas.ll_getStringValue(addr, ((Token_Type)jcasType).casFeatCode_fineGrainedChunkTag);}
    
  /** setter for fineGrainedChunkTag - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setFineGrainedChunkTag(String v) {
    if (Token_Type.featOkTst && ((Token_Type)jcasType).casFeat_fineGrainedChunkTag == null)
      jcasType.jcas.throwFeatMissing("fineGrainedChunkTag", "common.types.text.Token");
    jcasType.ll_cas.ll_setStringValue(addr, ((Token_Type)jcasType).casFeatCode_fineGrainedChunkTag, v);}    
  }

    