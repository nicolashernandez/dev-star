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

import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;
import org.apache.uima.UIMAFramework;
import org.apache.uima.util.Level;
import org.apache.uima.util.Logger;








public class RelevantRetrievedCorrectCounter
{
/*  19 */   protected Map<String, Float> mMap = new HashMap();
  




  public void addRetrieved(float retrieved)
  {
/*  27 */     this.mMap.put("retrieved", Float.valueOf(((Float)this.mMap.get("retrieved")).floatValue() + retrieved));
  }
  






  public void addRelevant(float relevant)
  {
/*  38 */     this.mMap.put("relevant", Float.valueOf(((Float)this.mMap.get("relevant")).floatValue() + relevant));
  }
  





  public void addCorrect()
  {
/*  48 */     this.mMap.put("correct", Float.valueOf(((Float)this.mMap.get("correct")).floatValue() + 1.0F));
  }
  








  public RelevantRetrievedCorrectCounter()
  {
/*  61 */     if (this.mMap.isEmpty())
    {

/*  64 */       this.mMap.put("retrieved", Float.valueOf(0.0F));
/*  65 */       this.mMap.put("relevant", Float.valueOf(0.0F));
/*  66 */       this.mMap.put("correct", Float.valueOf(0.0F));
/*  67 */       this.mMap.put("enableWritten", Float.valueOf(0.0F));
/*  68 */       this.mMap.put("tp", Float.valueOf(0.0F));
/*  69 */       this.mMap.put("fp", Float.valueOf(0.0F));
/*  70 */       this.mMap.put("tn", Float.valueOf(0.0F));
/*  71 */       this.mMap.put("fn", Float.valueOf(0.0F));
    }
  }
  


  public void clearRelevantRetrievedCorrectCounter()
  {
/*  79 */     this.mMap.clear();
    
/*  81 */     if (this.mMap.isEmpty())
    {

/*  84 */       this.mMap.put("retrieved", Float.valueOf(0.0F));
/*  85 */       this.mMap.put("relevant", Float.valueOf(0.0F));
/*  86 */       this.mMap.put("correct", Float.valueOf(0.0F));
/*  87 */       this.mMap.put("enableWritten", Float.valueOf(0.0F));
/*  88 */       this.mMap.put("tp", Float.valueOf(0.0F));
/*  89 */       this.mMap.put("fp", Float.valueOf(0.0F));
/*  90 */       this.mMap.put("tn", Float.valueOf(0.0F));
/*  91 */       this.mMap.put("fn", Float.valueOf(0.0F));
    }
  }
  








  private float getAccuracy()
  {
/* 105 */     return ((Float)this.mMap.get("correct")).floatValue() / ((Float)this.mMap.get("retrieved")).floatValue();
  }
  
  private String getAccuracyString() {
/* 109 */     return this.mMap.get("correct") + "/" + this.mMap.get("retrieved");
  }
  

















  private float getPrecision()
  {
/* 131 */     return ((Float)this.mMap.get("correct")).floatValue() / ((Float)this.mMap.get("retrieved")).floatValue();
  }
  
  private String getPrecisionString() {
/* 135 */     return this.mMap.get("correct") + "/" + this.mMap.get("retrieved");
  }
  





  private float getRecall()
  {
/* 145 */     return ((Float)this.mMap.get("correct")).floatValue() / ((Float)this.mMap.get("relevant")).floatValue();
  }
  
  private String getRecallString() {
/* 149 */     return this.mMap.get("correct") + "/" + this.mMap.get("relevant");
  }
  




  private float getFMeasure()
  {
/* 158 */     return 2.0F * getRecall() * getPrecision() / (getRecall() + getPrecision());
  }
  
  private String getFMeasureString() {
/* 162 */     return "2 * " + getRecall() + " * " + getPrecision() + " / ( " + getRecall() + " + " + getPrecision() + ")";
  }
  

  private void enableWritten(boolean enabled)
  {
/* 168 */     if (enabled)
/* 169 */       this.mMap.put("enableWritten", Float.valueOf(1.0F)); else {
/* 170 */       this.mMap.put("enableWritten", Float.valueOf(0.0F));
    }
  }
  
  private boolean isEnableWritten()
  {
/* 176 */     if (((Float)this.mMap.get("enableWritten")).floatValue() == 1.0F) return true;
/* 177 */     return false;
  }
  


  public void display(String goldAnnotationType, String testAnnotationType)
  {
/* 184 */     System.err.println("INFO: gold (" + goldAnnotationType + ") vs. test (" + testAnnotationType + ")");
    
    try
    {
/* 188 */       if (!isEnableWritten()) {
/* 189 */         enableWritten(true);
        











/* 202 */         System.err.println("INFO: Evaluation & Relevant & Retrieved & Correct \\\\");
/* 203 */         System.err.println("INFO: Evaluation & " + this.mMap.get("relevant") + " & " + this.mMap.get("retrieved") + " & " + this.mMap.get("correct") + " \\\\");
        






/* 211 */         System.err.println("INFO: Evaluation & Precision & Recall & F-measure \\\\");
/* 212 */         System.err.printf("INFO: Evaluation & %1.2f & %1.2f & %1.2f \\\\\n", new Object[] { Float.valueOf(getPrecision()), Float.valueOf(getRecall()), Float.valueOf(getFMeasure()) });
/* 213 */         System.err.printf("INFO: Evaluation & ($" + getPrecisionString() + "$) & ($" + getRecallString() + "$) & ($" + getFMeasureString() + "$) \\\\\n", new Object[0]);
      }
    }
    catch (Exception e) {
/* 217 */       UIMAFramework.getLogger().log(Level.SEVERE, e.getMessage());
    }
  }
}
