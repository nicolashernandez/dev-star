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

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.uima.cas.Type;
import org.apache.uima.jcas.tcas.Annotation;




























































public class LocatedAnnotation
  implements LocatedAnnotationInterface
{
  private static final boolean Annotation = false;
  private int begin;
  private int end;
  private List<Annotation> current;
  private List<LocatedAnnotation> contiguousParent;
  private List<LocatedAnnotation> contiguousChild;
  private List<LocatedAnnotation> contiguousFollowingList;
  private List<LocatedAnnotation> contiguousPrecedingList;
  private List<LocatedAnnotation> partialFollowing;
  private List<LocatedAnnotation> partialPreceding;
  private LocatedAnnotation firstIndexFollowing;
  private LocatedAnnotation firstIndexPreceding;
  
  public LocatedAnnotation()
  {
/*   88 */     setCurrent(new ArrayList());
/*   89 */     setContiguousParent(new ArrayList());
/*   90 */     setContiguousChild(new ArrayList());
/*   91 */     setContiguousFollowing(new ArrayList());
/*   92 */     setContiguousPreceding(new ArrayList());
/*   93 */     setContiguousPartialFollowing(new ArrayList());
/*   94 */     setContiguousPartialPreceding(new ArrayList());
  }
  


  public LocatedAnnotation(Annotation a)
  {
/*  101 */     setBegin(a.getBegin());
/*  102 */     setEnd(a.getEnd());
/*  103 */     new LocatedAnnotation();
  }
  


  public int getBegin()
  {
/*  110 */     return this.begin;
  }
  


  public void setBegin(int begin)
  {
/*  117 */     this.begin = begin;
  }
  


  public int getEnd()
  {
/*  124 */     return this.end;
  }
  


  public void setEnd(int end)
  {
/*  131 */     this.end = end;
  }
  


  public void setCurrent(List<Annotation> current)
  {
/*  138 */     this.current = current;
  }
  


  public void setContiguousParent(List<LocatedAnnotation> parent)
  {
/*  145 */     this.contiguousParent = parent;
  }
  
  public void setContiguousChild(List<LocatedAnnotation> child)
  {
/*  150 */     this.contiguousChild = child;
  }
  








  public void setContiguousFollowing(List<LocatedAnnotation> following)
  {
/*  163 */     this.contiguousFollowingList = following;
  }
  


  public void setContiguousPreceding(List<LocatedAnnotation> previous)
  {
/*  170 */     this.contiguousPrecedingList = previous;
  }
  


  public void setContiguousPartialFollowing(List<LocatedAnnotation> partialFollowing)
  {
/*  177 */     this.partialFollowing = partialFollowing;
  }
  


  public void setContiguousPartialPreceding(List<LocatedAnnotation> partialPrevious)
  {
/*  184 */     this.partialPreceding = partialPrevious;
  }
  


  public void addCurrent(Annotation a)
  {
/*  191 */     this.current.add(a);
  }
  


























































  public void addCurrent(List<Annotation> annotationList)
  {
/*  254 */     this.current.addAll(annotationList);
  }
  





  public void addContiguousFollowing(List<LocatedAnnotation> locatedAnnotationList)
  {
/*  264 */     this.contiguousFollowingList.addAll(locatedAnnotationList);
  }
  




  public void addContiguousPreceding(List<LocatedAnnotation> annotationList)
  {
/*  273 */     this.contiguousPrecedingList.addAll(annotationList);
  }
  



  public void addContiguousParent(List<LocatedAnnotation> annotationList)
  {
/*  281 */     this.contiguousParent.addAll(annotationList);
  }
  
  public void addContiguousChild(List<LocatedAnnotation> annotationList) {
/*  285 */     this.contiguousChild.addAll(annotationList);
  }
  



  public void addContiguousPartialPreceding(List<LocatedAnnotation> annotationList)
  {
/*  293 */     this.partialPreceding.addAll(annotationList);
  }
  




  public void addContiguousPartialFollowing(List<LocatedAnnotation> annotationList)
  {
/*  302 */     this.partialFollowing.addAll(annotationList);
  }
  






  public List<Annotation> getCurrent()
  {
/*  313 */     return this.current;
  }
  



  public List<LocatedAnnotation> getContiguousPreceding()
  {
/*  321 */     return this.contiguousPrecedingList;
  }
  



  public List<LocatedAnnotation> getContiguousFollowing()
  {
/*  329 */     return this.contiguousFollowingList;
  }
  



  public List<LocatedAnnotation> getContiguousParent()
  {
/*  337 */     return this.contiguousParent;
  }
  
  public List<LocatedAnnotation> getContiguousChild() {
/*  341 */     return this.contiguousChild;
  }
  



  public List<LocatedAnnotation> getContiguousPartialPreceding()
  {
/*  349 */     return this.partialPreceding;
  }
  



  public List<LocatedAnnotation> getContiguousPartialFollowing()
  {
/*  357 */     return this.partialFollowing;
  }
  



  public List<LocatedAnnotation> getContiguousAllPreceding()
  {
/*  365 */     List<LocatedAnnotation> concat = new ArrayList(this.contiguousPrecedingList);
/*  366 */     concat.addAll(this.partialPreceding);
/*  367 */     return concat;
  }
  



  public List<LocatedAnnotation> getContiguousAllFollowing()
  {
/*  375 */     List<LocatedAnnotation> concat = new ArrayList(this.contiguousFollowingList);
/*  376 */     concat.addAll(this.partialFollowing);
/*  377 */     return concat;
  }
  



  public boolean isEmpty(List<?> aLA)
  {
/*  385 */     return aLA.isEmpty();
  }
  





  public boolean isEmptyCurrent()
  {
/*  395 */     return isEmpty(getCurrent());
  }
  



  public boolean isEmptyContiguousPreceding()
  {
/*  403 */     return isEmpty(getContiguousPreceding());
  }
  




  public boolean isEmptyContiguousFollowing()
  {
/*  412 */     return isEmpty(getContiguousFollowing());
  }
  



  public boolean isEmptyContiguousParent()
  {
/*  420 */     return isEmpty(getContiguousParent());
  }
  




  public boolean isEmptyContiguousChild()
  {
/*  429 */     return isEmpty(getContiguousChild());
  }
  




  public boolean isEmptyContiguousPartialPreceding()
  {
/*  438 */     return isEmpty(getContiguousPartialPreceding());
  }
  



  public boolean isEmptyContiguousPartialFollowing()
  {
/*  446 */     return isEmpty(getContiguousPartialFollowing());
  }
  



  public boolean isEmptyContiguousAllPreceding()
  {
/*  454 */     return (isEmptyContiguousPartialPreceding()) && (isEmptyContiguousPreceding());
  }
  




  public boolean isEmptyContiguousAllFollowing()
  {
/*  463 */     return (isEmptyContiguousPartialFollowing()) && (isEmptyContiguousFollowing());
  }
  






  public boolean contains(List<Annotation> annotationList, Annotation annotation)
  {
/*  474 */     int listSize = annotationList.size();
/*  475 */     int counter = 0;
/*  476 */     boolean contains = false;
/*  477 */     while ((counter < listSize) && (!contains)) {
/*  478 */       if (annotationList.get(counter) == annotation) {
/*  479 */         contains = true;
      }
/*  481 */       counter++;
    }
/*  483 */     return contains;
  }
  




  public boolean contains(List<Annotation> annotationList, String annotationTypeName)
  {
/*  492 */     int listSize = annotationList.size();
/*  493 */     int counter = 0;
/*  494 */     boolean contains = false;
    
/*  496 */     while ((counter < listSize) && (!contains))
    {

/*  499 */       if (((Annotation)annotationList.get(counter)).getClass().getName().equalsIgnoreCase(annotationTypeName))
      {
/*  501 */         contains = true;
      }
/*  503 */       counter++;
    }
    
/*  506 */     return contains;
  }
  



  public boolean containsCurrent(Annotation annotation)
  {
/*  514 */     return contains(getCurrent(), annotation);
  }
  



  public boolean containsCurrent(String annotationTypeName)
  {
/*  522 */     return contains(getCurrent(), annotationTypeName);
  }
  



  public boolean containsContiguousPreceding(Annotation a)
  {
/*  530 */     Iterator<LocatedAnnotation> contiguousPrecedingIterator = getContiguousPreceding().iterator();
/*  531 */     while (contiguousPrecedingIterator.hasNext()) {
/*  532 */       LocatedAnnotationInterface current = (LocatedAnnotationInterface)contiguousPrecedingIterator.next();
/*  533 */       if (contains(current.getCurrent(), a)) return true;
    }
/*  535 */     return false;
  }
  




  public boolean containsContiguousFollowing(Annotation a)
  {
/*  544 */     Iterator<LocatedAnnotation> contiguousFollowingIterator = getContiguousFollowing().iterator();
/*  545 */     while (contiguousFollowingIterator.hasNext()) {
/*  546 */       LocatedAnnotationInterface current = (LocatedAnnotationInterface)contiguousFollowingIterator.next();
/*  547 */       if (contains(current.getCurrent(), a)) return true;
    }
/*  549 */     return false;
  }
  




  public boolean containsContiguousParent(Annotation a)
  {
/*  558 */     Iterator<LocatedAnnotation> contiguousIterator = getContiguousParent().iterator();
/*  559 */     while (contiguousIterator.hasNext()) {
/*  560 */       LocatedAnnotationInterface current = (LocatedAnnotationInterface)contiguousIterator.next();
/*  561 */       if (contains(current.getCurrent(), a)) return true;
    }
/*  563 */     return false;
  }
  




  public boolean containsContiguousChild(Annotation a)
  {
/*  572 */     Iterator<LocatedAnnotation> contiguousIterator = getContiguousChild().iterator();
/*  573 */     while (contiguousIterator.hasNext()) {
/*  574 */       LocatedAnnotationInterface current = (LocatedAnnotationInterface)contiguousIterator.next();
/*  575 */       if (contains(current.getCurrent(), a)) return true;
    }
/*  577 */     return false;
  }
  




  public boolean containsContiguousPartialPreceding(Annotation a)
  {
/*  586 */     Iterator<LocatedAnnotation> contiguousPartialPrecedingIterator = getContiguousPartialPreceding().iterator();
/*  587 */     while (contiguousPartialPrecedingIterator.hasNext()) {
/*  588 */       LocatedAnnotationInterface current = (LocatedAnnotationInterface)contiguousPartialPrecedingIterator.next();
/*  589 */       if (contains(current.getCurrent(), a)) return true;
    }
/*  591 */     return false;
  }
  




  public boolean containsContiguousPartialFollowing(Annotation a)
  {
/*  600 */     Iterator<LocatedAnnotation> contiguousPartialFollowingIterator = getContiguousPartialFollowing().iterator();
/*  601 */     while (contiguousPartialFollowingIterator.hasNext()) {
/*  602 */       LocatedAnnotationInterface current = (LocatedAnnotationInterface)contiguousPartialFollowingIterator.next();
/*  603 */       if (contains(current.getCurrent(), a)) return true;
    }
/*  605 */     return false;
  }
  




  public boolean containsAllPreceding(Annotation a)
  {
/*  614 */     Iterator<LocatedAnnotation> contiguousAllPrecedingIterator = getContiguousAllPreceding().iterator();
/*  615 */     while (contiguousAllPrecedingIterator.hasNext()) {
/*  616 */       LocatedAnnotationInterface current = (LocatedAnnotationInterface)contiguousAllPrecedingIterator.next();
/*  617 */       if (contains(current.getCurrent(), a)) return true;
    }
/*  619 */     return false;
  }
  




  public boolean containsAllFollowing(Annotation a)
  {
/*  628 */     Iterator<LocatedAnnotation> contiguousAllFollowingIterator = getContiguousAllFollowing().iterator();
/*  629 */     while (contiguousAllFollowingIterator.hasNext()) {
/*  630 */       LocatedAnnotationInterface current = (LocatedAnnotationInterface)contiguousAllFollowingIterator.next();
/*  631 */       if (contains(current.getCurrent(), a)) return true;
    }
/*  633 */     return false;
  }
  





  public LocatedAnnotation getFirstIndexFollowing()
  {
/*  643 */     return this.firstIndexFollowing;
  }
  




  public LocatedAnnotation getFirstIndexPreceding()
  {
/*  652 */     return this.firstIndexPreceding;
  }
  






  public LocatedAnnotation getFirstIndexFollowing(String annotationTypeName)
  {
/*  663 */     LocatedAnnotation neighborLocatedAnnotation = getFirstIndexFollowing();
/*  664 */     Boolean found = Boolean.valueOf(false);
/*  665 */     while ((neighborLocatedAnnotation != null) && (!found.booleanValue())) {
/*  666 */       if (neighborLocatedAnnotation.containsCurrent(annotationTypeName)) found = Boolean.valueOf(true); else
/*  667 */         neighborLocatedAnnotation = neighborLocatedAnnotation.getFirstIndexFollowing();
    }
/*  669 */     if (found.booleanValue()) return neighborLocatedAnnotation;
/*  670 */     return null;
  }
  





  public LocatedAnnotation getFirstIndexPreceding(String annotationTypeName)
  {
/*  680 */     LocatedAnnotation neighborLocatedAnnotation = getFirstIndexPreceding();
/*  681 */     Boolean found = Boolean.valueOf(false);
/*  682 */     while ((neighborLocatedAnnotation != null) && (!found.booleanValue())) {
/*  683 */       if (neighborLocatedAnnotation.containsCurrent(annotationTypeName)) found = Boolean.valueOf(true); else
/*  684 */         neighborLocatedAnnotation = neighborLocatedAnnotation.getFirstIndexPreceding();
    }
/*  686 */     if (found.booleanValue()) return neighborLocatedAnnotation;
/*  687 */     return null;
  }
  






  private LocatedAnnotation getFirstAncestorByContiguous(LocatedAnnotation currentLocatedAnnotation, String annotationTypeName)
  {
/*  698 */     if (!currentLocatedAnnotation.isEmptyContiguousParent())
    {
/*  700 */       Iterator localIterator = currentLocatedAnnotation.getContiguousParent().iterator(); if (localIterator.hasNext()) { LocatedAnnotation localLocatedAnnotation = (LocatedAnnotation)localIterator.next();
/*  701 */         if (localLocatedAnnotation.containsCurrent(annotationTypeName)) {
/*  702 */           return localLocatedAnnotation;
        }
        
/*  705 */         return getFirstAncestorByContiguous(localLocatedAnnotation, annotationTypeName);
      }
    }
    
/*  709 */     return null;
  }
  






  public LocatedAnnotation getFirstAncestorByContiguous(String annotationTypeName)
  {
/*  720 */     return getFirstAncestorByContiguous(this, annotationTypeName);
  }
  





  private LocatedAnnotation getFirstDescendantByContiguous(LocatedAnnotation currentLocatedAnnotation, String annotationTypeName)
  {
/*  730 */     if (!currentLocatedAnnotation.isEmptyContiguousChild()) {
/*  731 */       Iterator localIterator = currentLocatedAnnotation.getContiguousChild().iterator(); if (localIterator.hasNext()) { LocatedAnnotation localLocatedAnnotation = (LocatedAnnotation)localIterator.next();
/*  732 */         if (localLocatedAnnotation.containsCurrent(annotationTypeName)) {
/*  733 */           return localLocatedAnnotation;
        }
        
/*  736 */         return getFirstDescendantByContiguous(localLocatedAnnotation, annotationTypeName);
      }
    }
    
/*  740 */     return null;
  }
  





  public LocatedAnnotation getFirstDescendantByContiguous(String annotationTypeName)
  {
/*  750 */     return getFirstDescendantByContiguous(this, annotationTypeName);
  }
  







  private List<LocatedAnnotation> getDescendant(LocatedAnnotation locatedAnnotation, String annotationTypeName)
  {
/*  762 */     List<LocatedAnnotation> matchedLocatedAnnotationList = new ArrayList();
/*  763 */     if (!isEmptyContiguousChild()) {
/*  764 */       for (LocatedAnnotation contiguousChild : locatedAnnotation.getContiguousChild())
      {
/*  766 */         if (contiguousChild.containsCurrent(annotationTypeName)) { matchedLocatedAnnotationList.add(contiguousChild);
        } else
/*  768 */           matchedLocatedAnnotationList.addAll(getDescendant(contiguousChild, annotationTypeName));
      }
    }
/*  771 */     return matchedLocatedAnnotationList;
  }
  









  public List<LocatedAnnotation> getDescendant(String annotationTypeName)
  {
/*  785 */     return getDescendant(this, annotationTypeName);
  }
  



  public List<LocatedAnnotation> getDescendantOrSelf(String annotationTypeName)
  {
/*  793 */     if (containsCurrent(annotationTypeName)) {
/*  794 */       List<LocatedAnnotation> matchedLocatedAnnotationList = new ArrayList();
/*  795 */       matchedLocatedAnnotationList.add(this);
/*  796 */       return matchedLocatedAnnotationList;
    }
/*  798 */     return getDescendant(this, annotationTypeName);
  }
  






  private List<LocatedAnnotation> getAncestor(LocatedAnnotation locatedAnnotation, String annotationTypeName)
  {
/*  809 */     List<LocatedAnnotation> matchedLocatedAnnotationList = new ArrayList();
/*  810 */     if (!isEmptyContiguousParent()) {
/*  811 */       for (LocatedAnnotation contiguousParent : locatedAnnotation.getContiguousParent())
      {
/*  813 */         if (contiguousParent.containsCurrent(annotationTypeName)) { matchedLocatedAnnotationList.add(contiguousParent);
        } else
/*  815 */           matchedLocatedAnnotationList.addAll(getAncestor(contiguousParent, annotationTypeName));
      }
    }
/*  818 */     return matchedLocatedAnnotationList;
  }
  




  public List<LocatedAnnotation> getAncestor(String annotationTypeName)
  {
/*  827 */     return getAncestor(this, annotationTypeName);
  }
  



  public List<LocatedAnnotation> getAncestorOrSelf(String annotationTypeName)
  {
/*  835 */     if (containsCurrent(annotationTypeName)) {
/*  836 */       List<LocatedAnnotation> matchedLocatedAnnotationList = new ArrayList();
/*  837 */       matchedLocatedAnnotationList.add(this);
/*  838 */       return matchedLocatedAnnotationList;
    }
/*  840 */     return getAncestor(this, annotationTypeName);
  }
  



  public LocatedAnnotation getFirstAncestorOrSelfByContiguous(String annotationTypeName)
  {
/*  848 */     if (containsCurrent(annotationTypeName)) return this;
/*  849 */     return getFirstAncestorByContiguous(this, annotationTypeName);
  }
  



  public LocatedAnnotation getFirstDescendantOrSelfByContiguous(String annotationTypeName)
  {
/*  857 */     if (containsCurrent(annotationTypeName)) return this;
/*  858 */     return getFirstDescendantByContiguous(this, annotationTypeName);
  }
  




  public LocatedAnnotation getFirstAncestorByIndex(String annotationTypeName)
  {
/*  867 */     LocatedAnnotation neighborLocatedAnnotation = getFirstIndexPreceding();
/*  868 */     Boolean found = Boolean.valueOf(false);
/*  869 */     Boolean mayBeFound = Boolean.valueOf(true);
    
/*  871 */     while ((neighborLocatedAnnotation != null) && (!found.booleanValue()) && (mayBeFound.booleanValue())) {
/*  872 */       if (neighborLocatedAnnotation.containsCurrent(annotationTypeName)) {
/*  873 */         if ((neighborLocatedAnnotation.getBegin() > getBegin()) || (neighborLocatedAnnotation.getEnd() < getEnd())) {
/*  874 */           mayBeFound = Boolean.valueOf(false);
        } else
/*  876 */           found = Boolean.valueOf(true);
/*  877 */       } else neighborLocatedAnnotation = neighborLocatedAnnotation.getFirstIndexPreceding();
    }
/*  879 */     if (found.booleanValue()) return neighborLocatedAnnotation;
/*  880 */     return null;
  }
  




  public LocatedAnnotation getFirstAncestorOrSelfByIndex(String annotationTypeName)
  {
/*  889 */     if (containsCurrent(annotationTypeName)) return this;
/*  890 */     return getFirstAncestorByIndex(annotationTypeName);
  }
  







  public LocatedAnnotation getFirstDescendantByIndex(String annotationTypeName)
  {
/*  902 */     return getFirstIndexFollowing(annotationTypeName);
  }
  




  public LocatedAnnotation getFirstDescendantOrSelfByIndex(String annotationTypeName)
  {
/*  911 */     if (containsCurrent(annotationTypeName)) return this;
/*  912 */     return getFirstDescendantByIndex(annotationTypeName);
  }
  




  public String getOffset()
  {
/*  921 */     if (this != null) return Offset.getOffset(this);
/*  922 */     return "";
  }
  



  public String getCoveredText()
  {
/*  930 */     if (!isEmptyCurrent()) return ((Annotation)getCurrent().get(0)).getCoveredText();
/*  931 */     return "";
  }
  






  public String toString()
  {
/*  942 */     String result = "offset>" + getOffset() + "< coveredText>" + getCoveredText() + "<";
/*  943 */     List<Annotation> annotationList = getCurrent();
/*  944 */     for (Annotation annotation : annotationList) {
/*  945 */       result = result + "\t >" + annotation.getType().getName() + "<";
    }
    


/*  950 */     return result;
  }
  






  public String currentAndVicinityToString()
  {
/*  961 */     String result = toString();
    

/*  964 */     List<LocatedAnnotation> givenLocatedAnnotationList = null;
/*  965 */     String[] annotationListNames = { "getContiguousParent", "getContiguousChild", "getContiguousPreceding", "getContiguousFollowing" };
    try {
      String[] arrayOfString1;
/*  968 */       int j = (arrayOfString1 = annotationListNames).length; for (int i = 0; i < j; i++) { String annotationListName = arrayOfString1[i];
/*  969 */         Method getMethod = getClass().getMethod(annotationListName, new Class[0]);
/*  970 */         givenLocatedAnnotationList = (List)getMethod.invoke(this, new Object[0]);
        
/*  972 */         result = result + "\n\t " + annotationListName + ":";
/*  973 */         for (LocatedAnnotation locatedAnnotation : givenLocatedAnnotationList) {
/*  974 */           result = result + "\n\t\t " + locatedAnnotation.toString();
        }
        

/*  978 */         if (givenLocatedAnnotationList.isEmpty()) { result = result + "\n\t\tnull";
        }
      }
/*  981 */       result = result + "\n";
/*  982 */       if (getFirstIndexPreceding() != null) {
/*  983 */         result = result + "\t getFirstIndexPreceding offset>" + getFirstIndexPreceding().getOffset() + "< coveredText>" + getFirstIndexPreceding().getCoveredText() + "<\n";
      }
      
/*  986 */       if (getFirstIndexFollowing() != null) {
/*  987 */         result = result + "\t getFirstIndexFollowing offset>" + getFirstIndexFollowing().getOffset() + "< coveredText>" + getFirstIndexFollowing().getCoveredText() + "<\n";
      }
      

    }
    catch (SecurityException e)
    {
/*  994 */       e.printStackTrace();
    }
    catch (NoSuchMethodException e) {
/*  997 */       e.printStackTrace();
    }
    catch (IllegalArgumentException e) {
/* 1000 */       e.printStackTrace();
    }
    catch (IllegalAccessException e) {
/* 1003 */       e.printStackTrace();
    }
    catch (InvocationTargetException e) {
/* 1006 */       e.printStackTrace();
    }
/* 1008 */     return result;
  }
  


  public void setFirstIndexFollowing(LocatedAnnotation firstIndexFollowing)
  {
/* 1015 */     this.firstIndexFollowing = firstIndexFollowing;
  }
  


  public void setFirstIndexPreceding(LocatedAnnotation firstIndexPreceding)
  {
/* 1022 */     this.firstIndexPreceding = firstIndexPreceding;
  }
}
