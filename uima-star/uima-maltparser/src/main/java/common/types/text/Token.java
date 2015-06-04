

/* First created by JCasGen Thu May 30 11:49:30 CEST 2013 */
package common.types.text;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;



/** features based on French characteristics enumerated by http://aune.lpl.univ-aix.fr/projects/multext/LEX/LEX1.html
 * Updated by JCasGen Thu May 30 11:49:30 CEST 2013
 * XML source: /media/ext4/workspace/uima-maltparser/desc/maltParser/wst-openNLPPOSTagger-maltparser.AAE.xml
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
  /** @generated  */
  @Override
  public              int getTypeIndexID() {return typeIndexID;}
 
  /** Never called.  Disable default constructor
   * @generated */
  protected Token() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated */
  public Token(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated */
  public Token(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated */  
  public Token(JCas jcas, int begin, int end) {
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
  //* Feature: lemma

  /** getter for lemma - gets Lemma of the word.
   * @generated */
  public String getLemma() {
    if (Token_Type.featOkTst && ((Token_Type)jcasType).casFeat_lemma == null)
      jcasType.jcas.throwFeatMissing("lemma", "common.types.text.Token");
    return jcasType.ll_cas.ll_getStringValue(addr, ((Token_Type)jcasType).casFeatCode_lemma);}
    
  /** setter for lemma - sets Lemma of the word. 
   * @generated */
  public void setLemma(String v) {
    if (Token_Type.featOkTst && ((Token_Type)jcasType).casFeat_lemma == null)
      jcasType.jcas.throwFeatMissing("lemma", "common.types.text.Token");
    jcasType.ll_cas.ll_setStringValue(addr, ((Token_Type)jcasType).casFeatCode_lemma, v);}    
   
    
  //*--------------*
  //* Feature: mph

  /** getter for mph - gets ?
   * @generated */
  public String getMph() {
    if (Token_Type.featOkTst && ((Token_Type)jcasType).casFeat_mph == null)
      jcasType.jcas.throwFeatMissing("mph", "common.types.text.Token");
    return jcasType.ll_cas.ll_getStringValue(addr, ((Token_Type)jcasType).casFeatCode_mph);}
    
  /** setter for mph - sets ? 
   * @generated */
  public void setMph(String v) {
    if (Token_Type.featOkTst && ((Token_Type)jcasType).casFeat_mph == null)
      jcasType.jcas.throwFeatMissing("mph", "common.types.text.Token");
    jcasType.ll_cas.ll_setStringValue(addr, ((Token_Type)jcasType).casFeatCode_mph, v);}    
   
    
  //*--------------*
  //* Feature: cat

  /** getter for cat - gets 
   * @generated */
  public String getCat() {
    if (Token_Type.featOkTst && ((Token_Type)jcasType).casFeat_cat == null)
      jcasType.jcas.throwFeatMissing("cat", "common.types.text.Token");
    return jcasType.ll_cas.ll_getStringValue(addr, ((Token_Type)jcasType).casFeatCode_cat);}
    
  /** setter for cat - sets  
   * @generated */
  public void setCat(String v) {
    if (Token_Type.featOkTst && ((Token_Type)jcasType).casFeat_cat == null)
      jcasType.jcas.throwFeatMissing("cat", "common.types.text.Token");
    jcasType.ll_cas.ll_setStringValue(addr, ((Token_Type)jcasType).casFeatCode_cat, v);}    
   
    
  //*--------------*
  //* Feature: raw

  /** getter for raw - gets 
   * @generated */
  public String getRaw() {
    if (Token_Type.featOkTst && ((Token_Type)jcasType).casFeat_raw == null)
      jcasType.jcas.throwFeatMissing("raw", "common.types.text.Token");
    return jcasType.ll_cas.ll_getStringValue(addr, ((Token_Type)jcasType).casFeatCode_raw);}
    
  /** setter for raw - sets  
   * @generated */
  public void setRaw(String v) {
    if (Token_Type.featOkTst && ((Token_Type)jcasType).casFeat_raw == null)
      jcasType.jcas.throwFeatMissing("raw", "common.types.text.Token");
    jcasType.ll_cas.ll_setStringValue(addr, ((Token_Type)jcasType).casFeatCode_raw, v);}    
   
    
  //*--------------*
  //* Feature: subCat

  /** getter for subCat - gets 
   * @generated */
  public String getSubCat() {
    if (Token_Type.featOkTst && ((Token_Type)jcasType).casFeat_subCat == null)
      jcasType.jcas.throwFeatMissing("subCat", "common.types.text.Token");
    return jcasType.ll_cas.ll_getStringValue(addr, ((Token_Type)jcasType).casFeatCode_subCat);}
    
  /** setter for subCat - sets  
   * @generated */
  public void setSubCat(String v) {
    if (Token_Type.featOkTst && ((Token_Type)jcasType).casFeat_subCat == null)
      jcasType.jcas.throwFeatMissing("subCat", "common.types.text.Token");
    jcasType.ll_cas.ll_setStringValue(addr, ((Token_Type)jcasType).casFeatCode_subCat, v);}    
   
    
  //*--------------*
  //* Feature: stem

  /** getter for stem - gets (based on Sandbox Snowball Annotator definition)
   * @generated */
  public String getStem() {
    if (Token_Type.featOkTst && ((Token_Type)jcasType).casFeat_stem == null)
      jcasType.jcas.throwFeatMissing("stem", "common.types.text.Token");
    return jcasType.ll_cas.ll_getStringValue(addr, ((Token_Type)jcasType).casFeatCode_stem);}
    
  /** setter for stem - sets (based on Sandbox Snowball Annotator definition) 
   * @generated */
  public void setStem(String v) {
    if (Token_Type.featOkTst && ((Token_Type)jcasType).casFeat_stem == null)
      jcasType.jcas.throwFeatMissing("stem", "common.types.text.Token");
    jcasType.ll_cas.ll_setStringValue(addr, ((Token_Type)jcasType).casFeatCode_stem, v);}    
   
    
  //*--------------*
  //* Feature: corefType

  /** getter for corefType - gets type de coreference (coref or not) see Tutin corpus
   * @generated */
  public String getCorefType() {
    if (Token_Type.featOkTst && ((Token_Type)jcasType).casFeat_corefType == null)
      jcasType.jcas.throwFeatMissing("corefType", "common.types.text.Token");
    return jcasType.ll_cas.ll_getStringValue(addr, ((Token_Type)jcasType).casFeatCode_corefType);}
    
  /** setter for corefType - sets type de coreference (coref or not) see Tutin corpus 
   * @generated */
  public void setCorefType(String v) {
    if (Token_Type.featOkTst && ((Token_Type)jcasType).casFeat_corefType == null)
      jcasType.jcas.throwFeatMissing("corefType", "common.types.text.Token");
    jcasType.ll_cas.ll_setStringValue(addr, ((Token_Type)jcasType).casFeatCode_corefType, v);}    
   
    
  //*--------------*
  //* Feature: gender

  /** getter for gender - gets 
   * @generated */
  public String getGender() {
    if (Token_Type.featOkTst && ((Token_Type)jcasType).casFeat_gender == null)
      jcasType.jcas.throwFeatMissing("gender", "common.types.text.Token");
    return jcasType.ll_cas.ll_getStringValue(addr, ((Token_Type)jcasType).casFeatCode_gender);}
    
  /** setter for gender - sets  
   * @generated */
  public void setGender(String v) {
    if (Token_Type.featOkTst && ((Token_Type)jcasType).casFeat_gender == null)
      jcasType.jcas.throwFeatMissing("gender", "common.types.text.Token");
    jcasType.ll_cas.ll_setStringValue(addr, ((Token_Type)jcasType).casFeatCode_gender, v);}    
   
    
  //*--------------*
  //* Feature: number

  /** getter for number - gets 
   * @generated */
  public String getNumber() {
    if (Token_Type.featOkTst && ((Token_Type)jcasType).casFeat_number == null)
      jcasType.jcas.throwFeatMissing("number", "common.types.text.Token");
    return jcasType.ll_cas.ll_getStringValue(addr, ((Token_Type)jcasType).casFeatCode_number);}
    
  /** setter for number - sets  
   * @generated */
  public void setNumber(String v) {
    if (Token_Type.featOkTst && ((Token_Type)jcasType).casFeat_number == null)
      jcasType.jcas.throwFeatMissing("number", "common.types.text.Token");
    jcasType.ll_cas.ll_setStringValue(addr, ((Token_Type)jcasType).casFeatCode_number, v);}    
   
    
  //*--------------*
  //* Feature: case

  /** getter for case - gets 
   * @generated */
  public String getCase() {
    if (Token_Type.featOkTst && ((Token_Type)jcasType).casFeat_case == null)
      jcasType.jcas.throwFeatMissing("case", "common.types.text.Token");
    return jcasType.ll_cas.ll_getStringValue(addr, ((Token_Type)jcasType).casFeatCode_case);}
    
  /** setter for case - sets  
   * @generated */
  public void setCase(String v) {
    if (Token_Type.featOkTst && ((Token_Type)jcasType).casFeat_case == null)
      jcasType.jcas.throwFeatMissing("case", "common.types.text.Token");
    jcasType.ll_cas.ll_setStringValue(addr, ((Token_Type)jcasType).casFeatCode_case, v);}    
   
    
  //*--------------*
  //* Feature: person

  /** getter for person - gets 
   * @generated */
  public String getPerson() {
    if (Token_Type.featOkTst && ((Token_Type)jcasType).casFeat_person == null)
      jcasType.jcas.throwFeatMissing("person", "common.types.text.Token");
    return jcasType.ll_cas.ll_getStringValue(addr, ((Token_Type)jcasType).casFeatCode_person);}
    
  /** setter for person - sets  
   * @generated */
  public void setPerson(String v) {
    if (Token_Type.featOkTst && ((Token_Type)jcasType).casFeat_person == null)
      jcasType.jcas.throwFeatMissing("person", "common.types.text.Token");
    jcasType.ll_cas.ll_setStringValue(addr, ((Token_Type)jcasType).casFeatCode_person, v);}    
   
    
  //*--------------*
  //* Feature: mood

  /** getter for mood - gets 
   * @generated */
  public String getMood() {
    if (Token_Type.featOkTst && ((Token_Type)jcasType).casFeat_mood == null)
      jcasType.jcas.throwFeatMissing("mood", "common.types.text.Token");
    return jcasType.ll_cas.ll_getStringValue(addr, ((Token_Type)jcasType).casFeatCode_mood);}
    
  /** setter for mood - sets  
   * @generated */
  public void setMood(String v) {
    if (Token_Type.featOkTst && ((Token_Type)jcasType).casFeat_mood == null)
      jcasType.jcas.throwFeatMissing("mood", "common.types.text.Token");
    jcasType.ll_cas.ll_setStringValue(addr, ((Token_Type)jcasType).casFeatCode_mood, v);}    
   
    
  //*--------------*
  //* Feature: tense

  /** getter for tense - gets 
   * @generated */
  public String getTense() {
    if (Token_Type.featOkTst && ((Token_Type)jcasType).casFeat_tense == null)
      jcasType.jcas.throwFeatMissing("tense", "common.types.text.Token");
    return jcasType.ll_cas.ll_getStringValue(addr, ((Token_Type)jcasType).casFeatCode_tense);}
    
  /** setter for tense - sets  
   * @generated */
  public void setTense(String v) {
    if (Token_Type.featOkTst && ((Token_Type)jcasType).casFeat_tense == null)
      jcasType.jcas.throwFeatMissing("tense", "common.types.text.Token");
    jcasType.ll_cas.ll_setStringValue(addr, ((Token_Type)jcasType).casFeatCode_tense, v);}    
   
    
  //*--------------*
  //* Feature: catP

  /** getter for catP - gets feature to accept a ftb+ tag
   * @generated */
  public String getCatP() {
    if (Token_Type.featOkTst && ((Token_Type)jcasType).casFeat_catP == null)
      jcasType.jcas.throwFeatMissing("catP", "common.types.text.Token");
    return jcasType.ll_cas.ll_getStringValue(addr, ((Token_Type)jcasType).casFeatCode_catP);}
    
  /** setter for catP - sets feature to accept a ftb+ tag 
   * @generated */
  public void setCatP(String v) {
    if (Token_Type.featOkTst && ((Token_Type)jcasType).casFeat_catP == null)
      jcasType.jcas.throwFeatMissing("catP", "common.types.text.Token");
    jcasType.ll_cas.ll_setStringValue(addr, ((Token_Type)jcasType).casFeatCode_catP, v);}    
   
    
  //*--------------*
  //* Feature: coarseGrainedChunkTag

  /** getter for coarseGrainedChunkTag - gets 
   * @generated */
  public String getCoarseGrainedChunkTag() {
    if (Token_Type.featOkTst && ((Token_Type)jcasType).casFeat_coarseGrainedChunkTag == null)
      jcasType.jcas.throwFeatMissing("coarseGrainedChunkTag", "common.types.text.Token");
    return jcasType.ll_cas.ll_getStringValue(addr, ((Token_Type)jcasType).casFeatCode_coarseGrainedChunkTag);}
    
  /** setter for coarseGrainedChunkTag - sets  
   * @generated */
  public void setCoarseGrainedChunkTag(String v) {
    if (Token_Type.featOkTst && ((Token_Type)jcasType).casFeat_coarseGrainedChunkTag == null)
      jcasType.jcas.throwFeatMissing("coarseGrainedChunkTag", "common.types.text.Token");
    jcasType.ll_cas.ll_setStringValue(addr, ((Token_Type)jcasType).casFeatCode_coarseGrainedChunkTag, v);}    
   
    
  //*--------------*
  //* Feature: embeddingPathChunkTag

  /** getter for embeddingPathChunkTag - gets 
   * @generated */
  public String getEmbeddingPathChunkTag() {
    if (Token_Type.featOkTst && ((Token_Type)jcasType).casFeat_embeddingPathChunkTag == null)
      jcasType.jcas.throwFeatMissing("embeddingPathChunkTag", "common.types.text.Token");
    return jcasType.ll_cas.ll_getStringValue(addr, ((Token_Type)jcasType).casFeatCode_embeddingPathChunkTag);}
    
  /** setter for embeddingPathChunkTag - sets  
   * @generated */
  public void setEmbeddingPathChunkTag(String v) {
    if (Token_Type.featOkTst && ((Token_Type)jcasType).casFeat_embeddingPathChunkTag == null)
      jcasType.jcas.throwFeatMissing("embeddingPathChunkTag", "common.types.text.Token");
    jcasType.ll_cas.ll_setStringValue(addr, ((Token_Type)jcasType).casFeatCode_embeddingPathChunkTag, v);}    
   
    
  //*--------------*
  //* Feature: fineGrainedChunkTag

  /** getter for fineGrainedChunkTag - gets 
   * @generated */
  public String getFineGrainedChunkTag() {
    if (Token_Type.featOkTst && ((Token_Type)jcasType).casFeat_fineGrainedChunkTag == null)
      jcasType.jcas.throwFeatMissing("fineGrainedChunkTag", "common.types.text.Token");
    return jcasType.ll_cas.ll_getStringValue(addr, ((Token_Type)jcasType).casFeatCode_fineGrainedChunkTag);}
    
  /** setter for fineGrainedChunkTag - sets  
   * @generated */
  public void setFineGrainedChunkTag(String v) {
    if (Token_Type.featOkTst && ((Token_Type)jcasType).casFeat_fineGrainedChunkTag == null)
      jcasType.jcas.throwFeatMissing("fineGrainedChunkTag", "common.types.text.Token");
    jcasType.ll_cas.ll_setStringValue(addr, ((Token_Type)jcasType).casFeatCode_fineGrainedChunkTag, v);}    
  }

    