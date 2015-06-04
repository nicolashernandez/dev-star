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

import fr.univnantes.lina.mlnlp.model.prefixtree.MapPrefixTree;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;















public class CSVDictionaryResource
  extends DictionaryResource
{
  private static final String CSV_COLUMN_SEPARATOR = "\t";
/* 26 */   private Boolean caseSensitive = Boolean.valueOf(true);
  


  public Boolean getCaseSensitive()
  {
/* 32 */     return this.caseSensitive;
  }
  


  public void setCaseSensitive(Boolean caseSensitive)
  {
/* 39 */     this.caseSensitive = caseSensitive;
  }
  



  public CSVDictionaryResource() {}
  



  public CSVDictionaryResource(Boolean caseSensitive)
  {
/* 52 */     setCaseSensitive(caseSensitive);
  }
  




  public void doParse(InputStream inputStream)
    throws Exception
  {
/* 62 */     String delimiter = System.getProperty("line.separator");
/* 63 */     Scanner scanner = new Scanner(inputStream);
/* 64 */     scanner.useDelimiter(delimiter);
/* 65 */     while (scanner.hasNext()) {
/* 66 */       doParseCSVLine(scanner.next());
    }
/* 68 */     scanner.close();
  }
  



  private void doParseCSVLine(String line)
    throws Exception
  {
/* 77 */     if ((line != null) && (!line.isEmpty()) && (!line.startsWith("#")))
    {

/* 80 */       String characters = line.trim();
      

/* 83 */       String[] columns = characters.split("\t");
/* 84 */       ArrayList<String> values = new ArrayList();
/* 85 */       for (int i = 1; i < columns.length; i++) {
/* 86 */         values.add(columns[i]);
      }
/* 88 */       String key = getCaseSensitive().booleanValue() ? columns[0] : columns[0].toLowerCase();
      

/* 91 */       getRoot().add(key, 0, values);
    }
  }
}
