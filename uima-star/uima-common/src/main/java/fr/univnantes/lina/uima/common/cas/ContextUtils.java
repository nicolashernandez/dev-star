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

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.PrintStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.StringTokenizer;
import org.apache.uima.UimaContext;







































public class ContextUtils
{
	/*  54 */   private static final String PATH_SEPARATOR = System.getProperty("path.separator");
















	/**
	 * search the context to find a resource file 
	 * return path and stream
	 * can be used to resolve relative File Path 
	 * 
	 * @param currentClass
	 * @param context
	 * @param fileName
	 * @return
	 */
	public static ContextFile resolveRelativeFilePath(Object currentClass, UimaContext context, String fileName)
	{
		/*  74 */     ArrayList<File> datapathElements = getContextDataPaths(context);

		URL url;
		/*  77 */     if ((url = currentClass.getClass().getClassLoader().getResource(fileName)) != null)
		{


			/*  81 */       InputStream stream = currentClass.getClass().getClassLoader().getResourceAsStream(fileName);
			/*  82 */       ContextFile contextFile = new ContextFile(url.getFile(), stream, fileName);

			/*  84 */       return contextFile;
		}


		/*  88 */     if ((datapathElements == null) || (datapathElements.size() == 0))
		{
			/*  90 */       return null;
		}

		/*  93 */     for (int i = 0; i < datapathElements.size(); i++) {
			/*  94 */       File testFile = new File((File)datapathElements.get(i), fileName);
			/*  95 */       if (testFile.exists())
			{
				InputStream stream;
				try
				{
					/* 100 */           stream = new BufferedInputStream(new FileInputStream(testFile));
				} catch (FileNotFoundException ex) { 
					/* 102 */           return null; }
				/* 104 */         ContextFile contextFile = new ContextFile(testFile.getAbsolutePath(), stream, null);
				/* 105 */         return contextFile;
			}
		}


		/* 110 */     return null;
	}







	/**
	 * Get the list of the datapaths present in the given context 
	 * @param context
	 * @return the list of the datapaths 
	 */
	public static ArrayList<File> getContextDataPaths(UimaContext context)
	{
		/* 122 */     StringTokenizer tokenizer = new StringTokenizer(context.getDataPath(), PATH_SEPARATOR);
		/* 123 */     ArrayList<File> datapathElements = new ArrayList();
		/* 124 */     while (tokenizer.hasMoreTokens())
		{
			/* 126 */       datapathElements.add(new File(tokenizer.nextToken()));
		}
		/* 128 */     return datapathElements;
	}









	public static class ContextFile
	{
		/* 141 */     private String filePath = "";






		private InputStream stream;





		private String fileName;






		public ContextFile(String filePath, InputStream stream, String fileName)
		{
			/* 163 */       System.err.println("INFO: ContextFile path>" + filePath + "<");
			/* 164 */       this.filePath = filePath;
			/* 165 */       this.stream = stream;
			/* 166 */       this.fileName = fileName;
		}





		public String getFilePath()
		{
			/* 175 */       return this.filePath;
		}





		public InputStream getStream()
		{
			/* 184 */       return this.stream;
		}





		public String getFileName()
		{
			/* 193 */       return this.fileName;
		}





		public File getFile()
		{
			/* 202 */       return new File(getFilePath());
		}
	}
}