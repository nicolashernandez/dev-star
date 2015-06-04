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
package fr.univnantes.lina.uima.common.resources;

import org.apache.uima.resource.DataResource;
import org.apache.uima.resource.ResourceInitializationException;
import org.apache.uima.resource.SharedResourceObject;


























































public class ClassificationScoresSharedResource
  extends String2IntegerMapModel
  implements ClassificationScoresSharedResource_Interface, SharedResourceObject
{
  private static final String actualPositive = "actualPositive";
  private static final String actualNegative = "actualNegative";
  private static final String truePositive = "truePositive";
  private static final String trueNegative = "trueNegative";
  private static final String falsePositive = "falsePositive";
  private static final String falseNegative = "falseNegative";
  
  public synchronized int getActualPositive()
  {
/*  77 */     return get("actualPositive").intValue();
  }
  


  public synchronized void setActualPositive(int value)
  {
/*  84 */     put("actualPositive", Integer.valueOf(value));
  }
  


  public synchronized int getActualNegative()
  {
/*  91 */     return get("actualNegative").intValue();
  }
  


  public synchronized void setActualNegative(int value)
  {
/*  98 */     put("actualNegative", Integer.valueOf(value));
  }
  


  public synchronized int getTruePositive()
  {
/* 105 */     return get("truePositive").intValue();
  }
  


  public synchronized void setTruePositive(int value)
  {
/* 112 */     put("truePositive", Integer.valueOf(value));
  }
  


  public synchronized int getTrueNegative()
  {
/* 119 */     return get("trueNegative").intValue();
  }
  


  public synchronized void setTrueNegative(int value)
  {
/* 126 */     put("trueNegative", Integer.valueOf(value));
  }
  


  public synchronized int getFalsePositive()
  {
/* 133 */     return get("falsePositive").intValue();
  }
  


  public synchronized void setFalsePositive(int value)
  {
/* 140 */     put("falsePositive", Integer.valueOf(value));
  }
  


  public synchronized int getFalseNegative()
  {
/* 147 */     return get("falseNegative").intValue();
  }
  


  public synchronized void setFalseNegative(int value)
  {
/* 154 */     put("falseNegative", Integer.valueOf(value));
  }
  




  public synchronized void addActualPositive(int value)
  {
/* 163 */     put("actualPositive", Integer.valueOf(get("actualPositive").intValue() + value));
  }
  



  public synchronized void addActualNegative(int value)
  {
/* 171 */     put("actualNegative", Integer.valueOf(get("actualNegative").intValue() + value));
  }
  



  public synchronized void addTruePositive(int value)
  {
/* 179 */     put("truePositive", Integer.valueOf(get("truePositive").intValue() + value));
  }
  



  public synchronized void addTrueNegative(int value)
  {
/* 187 */     put("trueNegative", Integer.valueOf(get("trueNegative").intValue() + value));
  }
  



  public synchronized void addFalsePositive(int value)
  {
/* 195 */     put("falsePositive", Integer.valueOf(get("falsePositive").intValue() + value));
  }
  



  public synchronized void addFalseNegative(int value)
  {
/* 203 */     put("falseNegative", Integer.valueOf(get("falseNegative").intValue() + value));
  }
  
  public void load(DataResource data)
    throws ResourceInitializationException
  {}
}

