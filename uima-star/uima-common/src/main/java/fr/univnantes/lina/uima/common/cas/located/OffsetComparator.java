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

import java.util.Comparator;














































































public class OffsetComparator
  implements Comparator<String>
{
  public int compare(String arg0, String arg1)
  {
/*  87 */     int b0 = Offset.getBegin(arg0).intValue();
/*  88 */     int e0 = Offset.getEnd(arg0).intValue();
/*  89 */     int b1 = Offset.getBegin(arg1).intValue();
/*  90 */     int e1 = Offset.getEnd(arg1).intValue();
    
/*  92 */     if ((b0 == b1) && (e0 == e1)) return 0;
/*  93 */     if ((b0 == b1) && (e0 < e1)) return 1;
/*  94 */     if ((b0 == b1) && (e0 > e1)) return -1;
/*  95 */     if ((b0 < b1) && (e0 == e1)) return -1;
/*  96 */     if ((b0 < b1) && (e0 < e1)) return -1;
/*  97 */     if ((b0 < b1) && (e0 > e1)) return -1;
/*  98 */     if ((b0 > b1) && (e0 == e1)) return 1;
/*  99 */     if ((b0 > b1) && (e0 < e1)) return 1;
/* 100 */     if ((b0 > b1) && (e0 > e1)) { return 1;
    }
    
/* 103 */     return 0;
  }
}