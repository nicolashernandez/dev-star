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
import java.util.Map;
import java.util.Set;
import org.apache.uima.jcas.tcas.Annotation;

public abstract interface LocatedAnnotationIndexInterface
{
  public abstract Map<String, LocatedAnnotation> getLocatedAnnotationIndex();
  
  public abstract void setLocatedAnnotationIndex(Map<String, LocatedAnnotation> paramMap);
  
  public abstract LocatedAnnotation getLocatedAnnotation(Annotation paramAnnotation);
  
  public abstract LocatedAnnotationInterface getLocatedAnnotation(String paramString);
  
  public abstract boolean existsLocatedAnnotation(String paramString);
  
  public abstract Set<String> getOffsetSet();
  
  public abstract List<String> getOffsetList();
  
  public abstract LocatedAnnotation getFirst(String paramString);
  
  public abstract LocatedAnnotation getLast(String paramString);
  
  public abstract LocatedAnnotation getFirst();
  
  public abstract LocatedAnnotation getLast();
}


/* Location:              /home/hernandez-n/Bureau/uima-common-0.0.1-SNAPSHOT.jar!/fr/univnantes/lina/uima/common/cas/located/LocatedAnnotationIndexInterface.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */