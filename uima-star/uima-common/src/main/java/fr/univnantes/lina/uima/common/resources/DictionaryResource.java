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
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import org.apache.uima.resource.DataResource;
import org.apache.uima.resource.ResourceInitializationException;
import org.apache.uima.resource.SharedResourceObject;
























public abstract class DictionaryResource
  implements SharedResourceObject
{
  protected MapPrefixTree root;
  protected boolean loaded;
/* 39 */   protected boolean caseSensitive = true;
  
  public DictionaryResource() {
/* 42 */     setLoaded(false);
/* 43 */     setRoot();
  }
  

  private void setRoot()
  {
/* 49 */     this.root = new MapPrefixTree();
  }
  
  private void setLoaded(boolean enabled) {
/* 53 */     this.loaded = enabled;
  }
  
  public MapPrefixTree getRoot() {
/* 57 */     return this.root;
  }
  
  private boolean isLoaded() {
/* 61 */     return this.loaded;
  }
  
  public void load(DataResource data)
    throws ResourceInitializationException
  {
    try
    {
/* 69 */       if (!isLoaded()) {
/* 70 */         setLoaded(true);
/* 71 */         doParse(data.getInputStream());
      }
    } catch (Exception e) {
/* 74 */       throw new ResourceInitializationException(e);
    }
  }
  
  public void doLoad(File file) throws Exception {
/* 79 */     InputStream inputStream = new FileInputStream(file);
/* 80 */     doParse(inputStream);
  }
  
  public abstract void doParse(InputStream paramInputStream)
    throws Exception;
}
