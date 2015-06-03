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

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;



/**
 * Java Utilities
 * 
 * @author hernandez-n
 *
 */
public class CollectionUtilities {

	/**
	 * 
	 * @author hernandez
	 *
	 */
	static class HashMapStringKeyComparatorByIntegerValue implements Comparator<String> {

		Map<String, Integer> base;
		public HashMapStringKeyComparatorByIntegerValue(Map<String, Integer> base) {
			this.base = base;
		}

		// Note: this comparator imposes orderings that are inconsistent with equals.    
		public int compare(String a, String b) {
			/*if ((a == null) && (b == null)) return 0;
			if ((a == null)) return 1;
			if ((b == null)) return -1;*/
			if ((base.get(a) == null)) return 1;
			if ((base.get(b) == null)) return -1;
			/*System.out.println("Debug: (base.get("+a+") >= base.get("+b+")");
			System.out.println("Debug: (base.get("+a+") "+base.get(a));
			System.out.println("Debug: (base.get("+b+") "+base.get(b));*/
			if (base.get(a) >= base.get(b)) {
				return -1;
			} else {
				return 1;
			} // returning 0 would merge keys
		}
	}

	/**
	 * Sort an hashmap with string keys by int value 
	 * @return 
	 */
	public static  TreeMap<String,Integer> sortStringIntegerHashMapByValue (Map<String,Integer> anHashMapToSort) {
		//Map<String,Integer> aMWECatintsMap = new HashMap<String,Integer>();
		HashMapStringKeyComparatorByIntegerValue sortedHashMapObject =  new HashMapStringKeyComparatorByIntegerValue(anHashMapToSort);
		TreeMap<String,Integer> aSortedMap = new TreeMap<String,Integer>(sortedHashMapObject);
		//if (anHashMapToSort != null)
		aSortedMap.putAll(anHashMapToSort);
		//else {
		//	System.err.println("Debug: anHashMapToSort.isEmpty()");
		//}
		return aSortedMap;
	} 

	/**
	 * Fusionne deux tableaux de Type T en un seul
	 * */
	public static <T> T[] concat (T[] a, T[] b) {
		final int alen = a.length;
		final int blen = b.length;
		final T[] result = (T[]) java.lang.reflect.Array.
				newInstance(a.getClass().getComponentType(), alen + blen);
		System.arraycopy(a, 0, result, 0, alen);
		System.arraycopy(b, 0, result, alen, blen);
		return result;
	}


	/**
	 * Increment the count of the value for a given key
	 * @param key
	 * @param value
	 */
	public static void incrementKeyValueCounter(
			Map<String, Map<String, Integer>> key2ValueMap, 
			String key, 
			String value) {

		if (key2ValueMap.containsKey(key)) {
			Map<String, Integer> valueMap = key2ValueMap.get(key);
			if (valueMap.containsKey(value))
				key2ValueMap.get(key).put(value, valueMap.get(value)+1);
			else
				key2ValueMap.get(key).put(value, new Integer(1));
		}
		else {
			Map<String, Integer> valueMap = new TreeMap<String, Integer>();
			valueMap.put(value, new Integer(1));					
			key2ValueMap.put(key, valueMap);
		}
	}



	/**
	 * use it carefully ; not sure it works well
	 * 
	 * Increment the value counter of an assumed key
	 * @param key
	 * @param key
	 */
	public static void incrementKeyCounter(
			Map<String, Integer> keyMap, 
			String key) {

		if (keyMap != null) {
			if (keyMap.containsKey(key)) {
				keyMap.put(key, keyMap.get(key)+1);
				//System.err.println("Debug: keyMap != null ; containsKey(key) ; put(key, keyMap.get(key)+1");
			}
			else {
				keyMap.put(key, new Integer(1));
				//System.err.println("Debug: keyMap == null ; ! containsKey(key) ; put(key, new Integer(1)");
			}

		}
		else {
			keyMap = new TreeMap<String, Integer> ();
			keyMap.put(key, new Integer(1));
			//System.err.println("Debug: keyMap == null ; new TreeMap ; put(key, new Integer(1)");
		}
	}

	/**
	 * return The First Form of a sorted Map
	 * 
	 */
	public static String getTheFirstFormOfASortedMap(TreeMap<String, Integer> map) {
		if (map == null) return "";
		if (map.isEmpty()) return "";
		return map.firstKey();
	}

	/**
	 * turn a set into a list
	 * @param aSet
	 * @return
	 */
	public static List<String> stringSetToStringList (Set<String> aSet) {
		List<String> aList = new ArrayList<String>();
		aList.addAll(aSet);
		return aList;
	}

	/**
	 * turn a list into a string
	 * @param keepNullValue TODO
	 * @param aSet
	 * @return
	 */
	public static String stringListToString (List<String> stringList, String separator, Boolean keepNullValue) {
		StringBuffer stringResult = new StringBuffer();
		for (String stringElement : stringList) {
			if ((stringElement == null && keepNullValue) || stringElement != null) {
				stringResult.append(stringElement); 
				stringResult.append(separator); 
			}
		}
		if (stringResult.length()>=separator.length()) 
			stringResult.delete(stringResult.length() - separator.length(), stringResult.length());

		return stringResult.toString();
	}

	/**
	 * Replace a value by a new one in a list at a given position
	 * 
	 * (Collection.replace replace all the given value instances by another one)
	 * @param list
	 * @param position
	 * @param newValue
	 * @return
	 */
	public static List<String> replace (List<String> list, int position, String newValue) {
		if (position < list.size()) {
			List<String> newList = new ArrayList<>();
			for (int i = 0; i < list.size() ; i++)
				if (i != position) newList.add(list.get(i));
				else newList.add(newValue);
			return newList;
		}
		return list;
	}
	
	
	/**
	 * Get the position of element in a list
	 * @param list
	 * @param element
	 * @return
	 */
	public static int getElementPosition(List<String> list, String element){
	    for (int i = 0; i < list.size(); i++)
	    {
	        if (list.get(i).equals(element))
	        {
	            return i;
	        }
	    } 

	    return -1;
	}

}
