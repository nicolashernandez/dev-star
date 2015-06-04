/** 
 * Copyright (C) 2010-20..  Nicolas Hernandez
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *     http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package fr.univnantes.lina.uima.common.cas.located;

import java.util.List;
import org.apache.uima.jcas.tcas.Annotation;

public abstract interface LocatedAnnotationInterface
{
  public abstract boolean isEmpty(List<?> paramList);
  
  public abstract boolean contains(List<Annotation> paramList, Annotation paramAnnotation);
  
  public abstract boolean contains(List<Annotation> paramList, String paramString);
  
  public abstract int getBegin();
  
  public abstract void setBegin(int paramInt);
  
  public abstract int getEnd();
  
  public abstract void setEnd(int paramInt);
  
  public abstract void setCurrent(List<Annotation> paramList);
  
  public abstract void addCurrent(Annotation paramAnnotation);
  
  public abstract void addCurrent(List<Annotation> paramList);
  
  public abstract List<Annotation> getCurrent();
  
  public abstract boolean isEmptyCurrent();
  
  public abstract boolean containsCurrent(Annotation paramAnnotation);
  
  public abstract boolean containsCurrent(String paramString);
  
  public abstract String getOffset();
  
  public abstract String getCoveredText();
  
  public abstract String toString();
  
  public abstract String currentAndVicinityToString();
  
  public abstract void setContiguousParent(List<LocatedAnnotation> paramList);
  
  public abstract void setContiguousChild(List<LocatedAnnotation> paramList);
  
  public abstract void setContiguousFollowing(List<LocatedAnnotation> paramList);
  
  public abstract void setContiguousPreceding(List<LocatedAnnotation> paramList);
  
  public abstract void setContiguousPartialFollowing(List<LocatedAnnotation> paramList);
  
  public abstract void setContiguousPartialPreceding(List<LocatedAnnotation> paramList);
  
  public abstract void addContiguousFollowing(List<LocatedAnnotation> paramList);
  
  public abstract void addContiguousPreceding(List<LocatedAnnotation> paramList);
  
  public abstract void addContiguousParent(List<LocatedAnnotation> paramList);
  
  public abstract void addContiguousChild(List<LocatedAnnotation> paramList);
  
  public abstract void addContiguousPartialPreceding(List<LocatedAnnotation> paramList);
  
  public abstract void addContiguousPartialFollowing(List<LocatedAnnotation> paramList);
  
  public abstract List<LocatedAnnotation> getContiguousPreceding();
  
  public abstract List<LocatedAnnotation> getContiguousFollowing();
  
  public abstract List<LocatedAnnotation> getContiguousParent();
  
  public abstract List<LocatedAnnotation> getContiguousChild();
  
  public abstract List<LocatedAnnotation> getContiguousPartialPreceding();
  
  public abstract List<LocatedAnnotation> getContiguousPartialFollowing();
  
  public abstract List<LocatedAnnotation> getContiguousAllPreceding();
  
  public abstract List<LocatedAnnotation> getContiguousAllFollowing();
  
  public abstract boolean isEmptyContiguousPreceding();
  
  public abstract boolean isEmptyContiguousFollowing();
  
  public abstract boolean isEmptyContiguousParent();
  
  public abstract boolean isEmptyContiguousChild();
  
  public abstract boolean isEmptyContiguousPartialPreceding();
  
  public abstract boolean isEmptyContiguousPartialFollowing();
  
  public abstract boolean isEmptyContiguousAllPreceding();
  
  public abstract boolean isEmptyContiguousAllFollowing();
  
  public abstract boolean containsContiguousPreceding(Annotation paramAnnotation);
  
  public abstract boolean containsContiguousFollowing(Annotation paramAnnotation);
  
  public abstract boolean containsContiguousParent(Annotation paramAnnotation);
  
  public abstract boolean containsContiguousChild(Annotation paramAnnotation);
  
  public abstract boolean containsContiguousPartialPreceding(Annotation paramAnnotation);
  
  public abstract boolean containsContiguousPartialFollowing(Annotation paramAnnotation);
  
  public abstract boolean containsAllPreceding(Annotation paramAnnotation);
  
  public abstract boolean containsAllFollowing(Annotation paramAnnotation);
  
  public abstract LocatedAnnotation getFirstIndexFollowing();
  
  public abstract LocatedAnnotation getFirstIndexPreceding();
  
  public abstract LocatedAnnotation getFirstIndexFollowing(String paramString);
  
  public abstract LocatedAnnotation getFirstIndexPreceding(String paramString);
  
  public abstract LocatedAnnotation getFirstAncestorByIndex(String paramString);
  
  public abstract LocatedAnnotation getFirstAncestorOrSelfByIndex(String paramString);
  
  public abstract LocatedAnnotation getFirstDescendantByIndex(String paramString);
  
  public abstract LocatedAnnotation getFirstDescendantOrSelfByIndex(String paramString);
  
  public abstract LocatedAnnotation getFirstAncestorByContiguous(String paramString);
  
  public abstract LocatedAnnotation getFirstAncestorOrSelfByContiguous(String paramString);
  
  public abstract LocatedAnnotation getFirstDescendantByContiguous(String paramString);
  
  public abstract LocatedAnnotation getFirstDescendantOrSelfByContiguous(String paramString);
  
  public abstract List<LocatedAnnotation> getDescendant(String paramString);
  
  public abstract List<LocatedAnnotation> getDescendantOrSelf(String paramString);
  
  public abstract List<LocatedAnnotation> getAncestor(String paramString);
  
  public abstract List<LocatedAnnotation> getAncestorOrSelf(String paramString);
  
  public abstract void setFirstIndexFollowing(LocatedAnnotation paramLocatedAnnotation);
  
  public abstract void setFirstIndexPreceding(LocatedAnnotation paramLocatedAnnotation);
}


/* Location:              /home/hernandez-n/Bureau/uima-common-0.0.1-SNAPSHOT.jar!/fr/univnantes/lina/uima/common/cas/located/LocatedAnnotationInterface.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */