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

import org.apache.uima.jcas.tcas.Annotation;


























































public class Offset
{
  static final String SEPARATOR = " ";
  
  private static final String buildOffset(int begin, int end)
  {
/* 68 */     return begin + " " + end;
  }
  
  public static String getOffset(Annotation a) {
/* 72 */     return buildOffset(a.getBegin(), a.getEnd());
  }
  
  public static String getOffset(LocatedAnnotationInterface a) {
/* 76 */     return buildOffset(a.getBegin(), a.getEnd());
  }
  
  public static boolean haveSameOffset(Annotation a, Annotation b) {
/* 80 */     return (a.getBegin() == b.getBegin()) && (a.getEnd() == b.getEnd());
  }
  
  public static final Integer getBegin(String offset) {
/* 84 */     String[] offsetDetails = offset.split(" ");
/* 85 */     return Integer.valueOf(offsetDetails[0]);
  }
  
  public static final Integer getEnd(String offset) {
/* 89 */     String[] offsetDetails = offset.split(" ");
/* 90 */     return Integer.valueOf(offsetDetails[1]);
  }
}
