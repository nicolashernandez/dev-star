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
package fr.univnantes.lina.uima.common.cas;

import fr.univnantes.lina.uima.common.resources.CSVDictionaryResource;
import java.io.File;
import java.io.PrintStream;
import org.apache.uima.UimaContext;
import org.apache.uima.resource.ResourceAccessException;
import org.apache.uima.resource.ResourceInitializationException;


































public class ResourceUtils
{
/* 45 */   private static String CURRENTCLASSNAME = "ResourceUtilities";
  











  public static void loadAResource(CSVDictionaryResource aResource, UimaContext aContext, String aDictionaryResource, String aDictionaryResourceFile)
    throws ResourceInitializationException
  {
    try
    {
/* 63 */       loadAResourceObject(aResource, (CSVDictionaryResource)aContext.getResourceObject(aDictionaryResource));
      

/* 66 */       loadAResourceFromFilePath(aResource, (String)aContext.getConfigParameterValue(aDictionaryResourceFile));
    }
    catch (ResourceAccessException e) {
/* 69 */       throw new ResourceInitializationException(e);
    }
  }
  



  public static void loadAResourceObject(CSVDictionaryResource aResource, CSVDictionaryResource with)
  {
/* 78 */     aResource = with;
  }
  




  public static void loadAResourceFromFilePath(CSVDictionaryResource aResource, String path)
    throws ResourceInitializationException
  {
/* 88 */     if (path != null) {
/* 89 */       System.err.println("Info: ResourceUtils loading dictionary from >" + path + "<");
/* 90 */       File file = new File(path);
      try {
/* 92 */         aResource.doLoad(file);
      } catch (Exception e) {
/* 94 */         throw new ResourceInitializationException(e);
      }
    }
  }
}
