

/* First created by JCasGen Thu May 30 13:40:53 CEST 2013 */
package common.types.syntax.dependency;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import org.apache.uima.jcas.cas.FSArray;
import org.apache.uima.jcas.tcas.Annotation;


/** The licence below was not explicitely mentionned for the syntax types.
            
org.cleartk.syntax.dependency.type.DependencyNode
org.cleartk.syntax.dependency.type.DependencyRelation
org.cleartk.syntax.dependency.type.TopDependencyNode

Copyright (c) 2007-2008, Regents of the University of Colorado 
All rights reserved.

Redistribution and use in source and binary forms, with or without
modification, are permitted provided that the following conditions are met:

Redistributions of source code must retain the above copyright notice, this list of conditions and the following disclaimer. 
Redistributions in binary form must reproduce the above copyright notice, this list of conditions and the following disclaimer in the documentation and/or other materials provided with the distribution. 
Neither the name of the University of Colorado at Boulder nor the names of its contributors may be used to endorse or promote products derived from this software without specific prior written permission. 

THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE
LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
POSSIBILITY OF SUCH DAMAGE.
 * Updated by JCasGen Thu May 30 13:40:53 CEST 2013
 * XML source: /media/ext4/workspace/uima-workflow/desc/wf/analysis/importTutinArcadeXRCELeMondeCorpus-openNLPPOSTaggerFr-maltparser-AAE.xml
 * @generated */
public class DependencyNode extends Annotation {
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = JCasRegistry.register(DependencyNode.class);
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
  protected DependencyNode() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated */
  public DependencyNode(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated */
  public DependencyNode(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated */  
  public DependencyNode(JCas jcas, int begin, int end) {
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
  //* Feature: HeadRelations

  /** getter for HeadRelations - gets 
   * @generated */
  public FSArray getHeadRelations() {
    if (DependencyNode_Type.featOkTst && ((DependencyNode_Type)jcasType).casFeat_HeadRelations == null)
      jcasType.jcas.throwFeatMissing("HeadRelations", "common.types.syntax.dependency.DependencyNode");
    return (FSArray)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((DependencyNode_Type)jcasType).casFeatCode_HeadRelations)));}
    
  /** setter for HeadRelations - sets  
   * @generated */
  public void setHeadRelations(FSArray v) {
    if (DependencyNode_Type.featOkTst && ((DependencyNode_Type)jcasType).casFeat_HeadRelations == null)
      jcasType.jcas.throwFeatMissing("HeadRelations", "common.types.syntax.dependency.DependencyNode");
    jcasType.ll_cas.ll_setRefValue(addr, ((DependencyNode_Type)jcasType).casFeatCode_HeadRelations, jcasType.ll_cas.ll_getFSRef(v));}    
    
  /** indexed getter for HeadRelations - gets an indexed value - 
   * @generated */
  public DependencyRelation getHeadRelations(int i) {
    if (DependencyNode_Type.featOkTst && ((DependencyNode_Type)jcasType).casFeat_HeadRelations == null)
      jcasType.jcas.throwFeatMissing("HeadRelations", "common.types.syntax.dependency.DependencyNode");
    jcasType.jcas.checkArrayBounds(jcasType.ll_cas.ll_getRefValue(addr, ((DependencyNode_Type)jcasType).casFeatCode_HeadRelations), i);
    return (DependencyRelation)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefArrayValue(jcasType.ll_cas.ll_getRefValue(addr, ((DependencyNode_Type)jcasType).casFeatCode_HeadRelations), i)));}

  /** indexed setter for HeadRelations - sets an indexed value - 
   * @generated */
  public void setHeadRelations(int i, DependencyRelation v) { 
    if (DependencyNode_Type.featOkTst && ((DependencyNode_Type)jcasType).casFeat_HeadRelations == null)
      jcasType.jcas.throwFeatMissing("HeadRelations", "common.types.syntax.dependency.DependencyNode");
    jcasType.jcas.checkArrayBounds(jcasType.ll_cas.ll_getRefValue(addr, ((DependencyNode_Type)jcasType).casFeatCode_HeadRelations), i);
    jcasType.ll_cas.ll_setRefArrayValue(jcasType.ll_cas.ll_getRefValue(addr, ((DependencyNode_Type)jcasType).casFeatCode_HeadRelations), i, jcasType.ll_cas.ll_getFSRef(v));}
   
    
  //*--------------*
  //* Feature: ChildRelations

  /** getter for ChildRelations - gets 
   * @generated */
  public FSArray getChildRelations() {
    if (DependencyNode_Type.featOkTst && ((DependencyNode_Type)jcasType).casFeat_ChildRelations == null)
      jcasType.jcas.throwFeatMissing("ChildRelations", "common.types.syntax.dependency.DependencyNode");
    return (FSArray)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((DependencyNode_Type)jcasType).casFeatCode_ChildRelations)));}
    
  /** setter for ChildRelations - sets  
   * @generated */
  public void setChildRelations(FSArray v) {
    if (DependencyNode_Type.featOkTst && ((DependencyNode_Type)jcasType).casFeat_ChildRelations == null)
      jcasType.jcas.throwFeatMissing("ChildRelations", "common.types.syntax.dependency.DependencyNode");
    jcasType.ll_cas.ll_setRefValue(addr, ((DependencyNode_Type)jcasType).casFeatCode_ChildRelations, jcasType.ll_cas.ll_getFSRef(v));}    
    
  /** indexed getter for ChildRelations - gets an indexed value - 
   * @generated */
  public DependencyRelation getChildRelations(int i) {
    if (DependencyNode_Type.featOkTst && ((DependencyNode_Type)jcasType).casFeat_ChildRelations == null)
      jcasType.jcas.throwFeatMissing("ChildRelations", "common.types.syntax.dependency.DependencyNode");
    jcasType.jcas.checkArrayBounds(jcasType.ll_cas.ll_getRefValue(addr, ((DependencyNode_Type)jcasType).casFeatCode_ChildRelations), i);
    return (DependencyRelation)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefArrayValue(jcasType.ll_cas.ll_getRefValue(addr, ((DependencyNode_Type)jcasType).casFeatCode_ChildRelations), i)));}

  /** indexed setter for ChildRelations - sets an indexed value - 
   * @generated */
  public void setChildRelations(int i, DependencyRelation v) { 
    if (DependencyNode_Type.featOkTst && ((DependencyNode_Type)jcasType).casFeat_ChildRelations == null)
      jcasType.jcas.throwFeatMissing("ChildRelations", "common.types.syntax.dependency.DependencyNode");
    jcasType.jcas.checkArrayBounds(jcasType.ll_cas.ll_getRefValue(addr, ((DependencyNode_Type)jcasType).casFeatCode_ChildRelations), i);
    jcasType.ll_cas.ll_setRefArrayValue(jcasType.ll_cas.ll_getRefValue(addr, ((DependencyNode_Type)jcasType).casFeatCode_ChildRelations), i, jcasType.ll_cas.ll_getFSRef(v));}
  }

    