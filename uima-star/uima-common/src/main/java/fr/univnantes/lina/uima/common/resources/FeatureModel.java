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

import fr.univnantes.lina.uima.common.cas.FeatureUtils;
import java.util.Map;
























public class FeatureModel
{
/*  31 */   private String featureType = "";
  



/*  36 */   private String featureName = "";
  



/*  41 */   private String featureValue = "";
  



  public synchronized String getFeatureType()
  {
/*  48 */     return this.featureType;
  }
  


  public synchronized void setFeatureType(String featureType)
  {
/*  55 */     this.featureType = featureType;
  }
  


  public synchronized String getFeatureName()
  {
/*  62 */     return this.featureName;
  }
  


  public void setFeatureName(String featureName)
  {
/*  69 */     this.featureName = featureName;
  }
  


  public synchronized String getFeatureValue()
  {
/*  76 */     return this.featureValue;
  }
  


  public synchronized void setFeatureValue(String featureValue)
  {
/*  83 */     this.featureValue = featureValue;
  }
  



  public synchronized Boolean hasFeatureType()
  {
/*  91 */     if (this.featureType == null) return Boolean.valueOf(false);
/*  92 */     return Boolean.valueOf(!this.featureType.equalsIgnoreCase(""));
  }
  


  public Boolean hasFeatureName()
  {
/*  99 */     if (this.featureName == null) return Boolean.valueOf(false);
/* 100 */     return Boolean.valueOf(!this.featureName.equalsIgnoreCase(""));
  }
  


  public Boolean hasFeatureValue()
  {
/* 107 */     if (this.featureValue == null) return Boolean.valueOf(false);
/* 108 */     return Boolean.valueOf(!this.featureValue.equalsIgnoreCase(""));
  }
  







  public FeatureModel() {}
  






  public FeatureModel(String featureValueString)
    throws Exception
  {
/* 129 */     Map<String, String> aFeatureTupleMap = FeatureUtils.parseFeature(featureValueString);
/* 130 */     if (aFeatureTupleMap == null)
/* 131 */       throw new Exception("featureValueString to parse should not be null !");
/* 132 */     setFeatureType((String)aFeatureTupleMap.get("annotationTypeName"));
/* 133 */     setFeatureName((String)aFeatureTupleMap.get("featureName"));
/* 134 */     setFeatureValue((String)aFeatureTupleMap.get("featureValue"));
  }
  



  public FeatureModel(String annotationTypeName, String featureName, String featureValue)
  {
/* 142 */     setFeatureType(annotationTypeName);
/* 143 */     setFeatureName(featureName);
/* 144 */     setFeatureValue(featureValue);
  }
}

