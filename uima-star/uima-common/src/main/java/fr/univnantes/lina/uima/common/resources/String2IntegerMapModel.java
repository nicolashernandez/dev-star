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

import java.util.HashMap;
import java.util.Map;

































public class String2IntegerMapModel
{
  private static final int initialValue = 0;
  private static final int incrementValue = 1;
/*  42 */   private Map<String, Integer> map = new HashMap();
  



  private synchronized Map<String, Integer> getMap()
  {
/*  49 */     return this.map;
  }
  




  protected synchronized void put(String key, Integer value)
  {
/*  58 */     getMap().put(key, value);
  }
  




  protected synchronized Integer get(String key)
  {
/*  67 */     if (!getMap().containsKey(key)) put(key, Integer.valueOf(0));
/*  68 */     return (Integer)getMap().get(key);
  }
  





  protected synchronized void inc(String key, Integer value)
  {
/*  78 */     put(key, Integer.valueOf(get(key).intValue() + value.intValue()));
  }
  



  protected synchronized void incPlusOne(String key)
  {
/*  86 */     inc(key, Integer.valueOf(1));
  }
  





  protected synchronized void dec(String key, Integer value)
  {
/*  96 */     put(key, Integer.valueOf(((Integer)getMap().get(key)).intValue() - value.intValue()));
  }
  




  protected synchronized void decMinusOne(String key)
  {
/* 105 */     dec(key, Integer.valueOf(1));
  }
}
