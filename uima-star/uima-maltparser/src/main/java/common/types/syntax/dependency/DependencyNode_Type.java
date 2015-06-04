
/* First created by JCasGen Thu May 30 11:49:30 CEST 2013 */
package common.types.syntax.dependency;

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
 * Updated by JCasGen Thu May 30 11:49:30 CEST 2013
 * @generated */
public class DependencyNode_Type extends Annotation_Type {
  /** @generated */
  @Override
  protected FSGenerator getFSGenerator() {return fsGenerator;}
  /** @generated */
  private final FSGenerator fsGenerator = 
    new FSGenerator() {
      public FeatureStructure createFS(int addr, CASImpl cas) {
  			 if (DependencyNode_Type.this.useExistingInstance) {
  			   // Return eq fs instance if already created
  		     FeatureStructure fs = DependencyNode_Type.this.jcas.getJfsFromCaddr(addr);
  		     if (null == fs) {
  		       fs = new DependencyNode(addr, DependencyNode_Type.this);
  			   DependencyNode_Type.this.jcas.putJfsFromCaddr(addr, fs);
  			   return fs;
  		     }
  		     return fs;
        } else return new DependencyNode(addr, DependencyNode_Type.this);
  	  }
    };
  /** @generated */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = DependencyNode.typeIndexID;
  /** @generated 
     @modifiable */
  @SuppressWarnings ("hiding")
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("common.types.syntax.dependency.DependencyNode");
 
  /** @generated */
  final Feature casFeat_HeadRelations;
  /** @generated */
  final int     casFeatCode_HeadRelations;
  /** @generated */ 
  public int getHeadRelations(int addr) {
        if (featOkTst && casFeat_HeadRelations == null)
      jcas.throwFeatMissing("HeadRelations", "common.types.syntax.dependency.DependencyNode");
    return ll_cas.ll_getRefValue(addr, casFeatCode_HeadRelations);
  }
  /** @generated */    
  public void setHeadRelations(int addr, int v) {
        if (featOkTst && casFeat_HeadRelations == null)
      jcas.throwFeatMissing("HeadRelations", "common.types.syntax.dependency.DependencyNode");
    ll_cas.ll_setRefValue(addr, casFeatCode_HeadRelations, v);}
    
   /** @generated */
  public int getHeadRelations(int addr, int i) {
        if (featOkTst && casFeat_HeadRelations == null)
      jcas.throwFeatMissing("HeadRelations", "common.types.syntax.dependency.DependencyNode");
    if (lowLevelTypeChecks)
      return ll_cas.ll_getRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_HeadRelations), i, true);
    jcas.checkArrayBounds(ll_cas.ll_getRefValue(addr, casFeatCode_HeadRelations), i);
	return ll_cas.ll_getRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_HeadRelations), i);
  }
   
  /** @generated */ 
  public void setHeadRelations(int addr, int i, int v) {
        if (featOkTst && casFeat_HeadRelations == null)
      jcas.throwFeatMissing("HeadRelations", "common.types.syntax.dependency.DependencyNode");
    if (lowLevelTypeChecks)
      ll_cas.ll_setRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_HeadRelations), i, v, true);
    jcas.checkArrayBounds(ll_cas.ll_getRefValue(addr, casFeatCode_HeadRelations), i);
    ll_cas.ll_setRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_HeadRelations), i, v);
  }
 
 
  /** @generated */
  final Feature casFeat_ChildRelations;
  /** @generated */
  final int     casFeatCode_ChildRelations;
  /** @generated */ 
  public int getChildRelations(int addr) {
        if (featOkTst && casFeat_ChildRelations == null)
      jcas.throwFeatMissing("ChildRelations", "common.types.syntax.dependency.DependencyNode");
    return ll_cas.ll_getRefValue(addr, casFeatCode_ChildRelations);
  }
  /** @generated */    
  public void setChildRelations(int addr, int v) {
        if (featOkTst && casFeat_ChildRelations == null)
      jcas.throwFeatMissing("ChildRelations", "common.types.syntax.dependency.DependencyNode");
    ll_cas.ll_setRefValue(addr, casFeatCode_ChildRelations, v);}
    
   /** @generated */
  public int getChildRelations(int addr, int i) {
        if (featOkTst && casFeat_ChildRelations == null)
      jcas.throwFeatMissing("ChildRelations", "common.types.syntax.dependency.DependencyNode");
    if (lowLevelTypeChecks)
      return ll_cas.ll_getRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_ChildRelations), i, true);
    jcas.checkArrayBounds(ll_cas.ll_getRefValue(addr, casFeatCode_ChildRelations), i);
	return ll_cas.ll_getRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_ChildRelations), i);
  }
   
  /** @generated */ 
  public void setChildRelations(int addr, int i, int v) {
        if (featOkTst && casFeat_ChildRelations == null)
      jcas.throwFeatMissing("ChildRelations", "common.types.syntax.dependency.DependencyNode");
    if (lowLevelTypeChecks)
      ll_cas.ll_setRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_ChildRelations), i, v, true);
    jcas.checkArrayBounds(ll_cas.ll_getRefValue(addr, casFeatCode_ChildRelations), i);
    ll_cas.ll_setRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_ChildRelations), i, v);
  }
 



  /** initialize variables to correspond with Cas Type and Features
	* @generated */
  public DependencyNode_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_HeadRelations = jcas.getRequiredFeatureDE(casType, "HeadRelations", "uima.cas.FSArray", featOkTst);
    casFeatCode_HeadRelations  = (null == casFeat_HeadRelations) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_HeadRelations).getCode();

 
    casFeat_ChildRelations = jcas.getRequiredFeatureDE(casType, "ChildRelations", "uima.cas.FSArray", featOkTst);
    casFeatCode_ChildRelations  = (null == casFeat_ChildRelations) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_ChildRelations).getCode();

  }
}



    