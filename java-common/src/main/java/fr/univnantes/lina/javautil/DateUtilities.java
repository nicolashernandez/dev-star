/** 
 * 
 * Copyright (C) 2013  Nicolas Hernandez
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

package fr.univnantes.lina.javautil;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;



/**
 * Java Utilities
 * 
 * @author hernandez-n
 *
 */
public class DateUtilities extends StringUtilities {


	public static final String DATE_FORMAT_NOW = "yyyy-MM-dd HH:mm:ss";
	public static final long MILISECOND_PER_DAY = 24 * 60 * 60 * 1000; 

	/**
	 * 
	 * @return the current Date
	 */
	public static Date getNow() {
	    Calendar cal = Calendar.getInstance();
	    return cal.getTime();

	  }
	
	/**
	 * 
	 * @return a Date as a formatted String
	 * 
	 */
	public static String stringFormatADate(Date aDate) {
		    SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_NOW);
	    
	    return sdf.format(aDate.getTime());
	    // formats
//	    System.out.println(DateUtils.now("dd MMMMM yyyy"));
//	     System.out.println(DateUtils.now("yyyyMMdd"));
//	     System.out.println(DateUtils.now("dd.MM.yy"));
//	     System.out.println(DateUtils.now("MM/dd/yy"));
//	     System.out.println(DateUtils.now("yyyy.MM.dd G 'at' hh:mm:ss z"));
//	     System.out.println(DateUtils.now("EEE, MMM d, ''yy"));
//	     System.out.println(DateUtils.now("h:mm a"));
//	     System.out.println(DateUtils.now("H:mm:ss:SSS"));
//	     System.out.println(DateUtils.now("K:mm a,z"));
//	     System.out.println(DateUtils.now("yyyy.MMMMM.dd GGG hh:mm aaa"));
	  }
	
	/**
	 * 
	 * @return display the current Date
	 * 
	 */
	public static String now() {
	    Calendar cal = Calendar.getInstance();
	        
	    return stringFormatADate (Calendar.getInstance().getTime());
	    // formats
//	    System.out.println(DateUtils.now("dd MMMMM yyyy"));
//	     System.out.println(DateUtils.now("yyyyMMdd"));
//	     System.out.println(DateUtils.now("dd.MM.yy"));
//	     System.out.println(DateUtils.now("MM/dd/yy"));
//	     System.out.println(DateUtils.now("yyyy.MM.dd G 'at' hh:mm:ss z"));
//	     System.out.println(DateUtils.now("EEE, MMM d, ''yy"));
//	     System.out.println(DateUtils.now("h:mm a"));
//	     System.out.println(DateUtils.now("H:mm:ss:SSS"));
//	     System.out.println(DateUtils.now("K:mm a,z"));
//	     System.out.println(DateUtils.now("yyyy.MMMMM.dd GGG hh:mm aaa"));
	  }

	/**
	 * 
	 * @return the diff between two dates expressed in milliseconds (long)
	 */
	public static long dateDiff(Date startDate, Date endDate) {
//		System.out.println("Debug: startDate.getTime()"+startDate.getTime());
//		System.out.println("Debug: endDate.getTime()"+endDate.getTime());
//		System.out.println("Debug: diff"+ (endDate.getTime()- startDate.getTime()));
		//Math.round(Math.abs((endDate.getTime()- startDate.getTime())/MILISECOND_PER_DAY))
		return 	endDate.getTime()- startDate.getTime();
		 
	}


}
