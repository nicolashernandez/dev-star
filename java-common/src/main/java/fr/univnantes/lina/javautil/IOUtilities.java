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

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;



public class IOUtilities {

	/**
	 * Utility method to read a given text from a file
	 * fileName doit intégrer le nom du répertoire dans le filename"/"
	 * @deprecated should be replaced by readTextFileAsStringList
	 * @throws IOException 
	 */
	public static ArrayList<String> readFromFileNameToLineArray(String fileName) {
		//System.out.println("Debug: full input File Name "+ fileName);
		ArrayList<String> lines = new ArrayList<String>();

		// Open the file that is the first 
		// command line parameter
		FileInputStream fstream = null;
		try {
			fstream = new FileInputStream(fileName);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// Get the object of DataInputStream
		DataInputStream in = new DataInputStream(fstream);
		BufferedReader br = new BufferedReader(new InputStreamReader(in));
		String strLine; 
		//Read File Line By Line
		try {
			while ((strLine = br.readLine()) != null)   {
				lines.add(strLine);
				// Print the content on the console
				//System.out.println (strLine);
			}
			//Close the input stream
			in.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return lines;
	}

	/**
	 * http://stackoverflow.com/questions/326390/how-to-create-a-java-string-from-the-contents-of-a-file
	 * Alternative exists: Slower but less memory consuming
	 */
	public  static String readTextFileAsString(String path){

		FileInputStream stream = null;
		try {
			File f = new File(path);
			if (!f.exists()) { 

				System.err.println("Error: the path >"+path+"< is not correct. Please check it");
				System.exit(1);
			}
			stream = new FileInputStream(f);
			stream.close();
		} catch (FileNotFoundException e) {			
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		//System.err.println("Info: loading text data from >"+path+"<");


		BufferedReader reader = null;
		try {
			reader = new BufferedReader( new FileReader (path));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		StringBuilder builder = new StringBuilder();
		String aux = "";
		try {
			while ((aux = reader.readLine()) != null) {
				builder.append(aux);builder.append("\n");
			}
			reader.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		return builder.toString();
	}





	/**
	 * 
	 * @param is
	 * @return
	 */
	public static List<String> readTextFileAsStringList( InputStream is ) {
		BufferedReader reader;
		List<String> lineList = new ArrayList<String>();
		try {
			reader = new BufferedReader(new InputStreamReader(is));

			String         line = null;
			//StringBuilder  stringBuilder = new StringBuilder();
			//String         ls = System.getProperty("line.separator");

			while( ( line = reader.readLine() ) != null ) {
				//stringBuilder.append( line );
				//stringBuilder.append( ls );
				lineList.add(line);

			} 
			reader.close();
		} 
		catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return lineList;
		//return stringBuilder.toString();
	}

	
	
	/**
	 * http://stackoverflow.com/questions/326390/how-to-create-a-java-string-from-the-contents-of-a-file
	 * Alternative exists: faster but more memory consuming
	 */
	public static List<String> readTextFileAsStringList( String filePath ) {
		BufferedReader reader;
		List<String> lineList = new ArrayList<String>();
		try {
			reader = new BufferedReader( new FileReader (filePath));

			String         line = null;
			//StringBuilder  stringBuilder = new StringBuilder();
			//String         ls = System.getProperty("line.separator");

			while( ( line = reader.readLine() ) != null ) {
				//stringBuilder.append( line );
				//stringBuilder.append( ls );
				lineList.add(line);

			} 
			reader.close();
		} 
		catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return lineList;
		//return stringBuilder.toString();
	}

	public static BufferedReader readTextAsBufferedReader(String textPath) {
		//final InputStream is = (file.equals("-")) ? System.in : (file.endsWith(".gz") ? new GZIPInputStream(new FileInputStream(file)): new FileInputStream(file));
		InputStream is = null;
		try {
			is = new FileInputStream(textPath); // goldTextPath
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		BufferedReader reader = new BufferedReader(new InputStreamReader(new BufferedInputStream(is)));
		return reader;
	}
	
	
	public static Set<String> readTextLinesAsSet(String textPath) {
		Set<String> ref = new HashSet<>();
		//final InputStream is = (file.equals("-")) ? System.in : (file.endsWith(".gz") ? new GZIPInputStream(new FileInputStream(file)): new FileInputStream(file));
		InputStream refIs = null;
		try {
			refIs = new FileInputStream(textPath);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		BufferedReader refReader = new BufferedReader(new InputStreamReader(new BufferedInputStream(refIs)));
		
		String         line = null;

		try {
			while( ( line = refReader.readLine() ) != null ) {
				//stringBuilder.append( line );
				//stringBuilder.append( ls );
				ref.add(line.trim());

			}		
			refReader.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 

		
		return ref;
	}

	public IOUtilities() {
		super();
	}

	/**
	 * Create a temporary text file with a text given in parameter and return its absolute path
	 * @param prefixTmpFile
	 * @param suffixTmpFile
	 * @param text
	 * @return fileAbsPathStrg
	 * @throws IOException 
	 */
	public static String createTempTextFile (String prefixTmpFile, String suffixTmpFile, String text)  {
		String fileAbsPathStrg = null;
		File file = null;
		try {
			file = File.createTempFile(prefixTmpFile, suffixTmpFile);

			fileAbsPathStrg = file.getAbsolutePath();

			FileWriter fstream;

			fstream = new FileWriter(fileAbsPathStrg);

			BufferedWriter out = new BufferedWriter(fstream);
			out.write(text);
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return fileAbsPathStrg;
	}

	/**
	 * Utility method to write a given text to a file
	 * fileName doit intégrer le nom du répertoire +"/"
	 */
	public static boolean writeStringArrayToFileName(String fileName, String[] lines  ) {

		Writer out = null;
		//System.out.println("Debug: full Output File Name "+ fileName);

		try {

			out = new OutputStreamWriter(new FileOutputStream(fileName),"UTF8");

			for (int l = 0 ;  l< lines.length ; l++) {
				//System.out.println("Debug: current line "+ lines[l]);

				out.write(lines[l]);
			}
			out.close();
		} catch (FileNotFoundException ex) {
			return (false);
		} catch (IOException ex) {
			return (false);
		}
		return (true);

	}

	/**
	 * Utility method to write a given text to a file
	 * fileName doit intégrer le nom du répertoire +"/"
	 */
	public static boolean writeStringToFileName(String fileName, String line  ) {

		Writer out = null;
		//System.out.println("Debug: full Output File Name "+ fileName);

		try {

			out = new OutputStreamWriter(new FileOutputStream(fileName),"UTF8");

			out.write(line);

			out.close();
		} catch (FileNotFoundException ex) {
			return (false);
		} catch (IOException ex) {
			return (false);
		}
		return (true);

	}



	/**
	 * print a string to the file system
	 * @param text
	 * @param filename
	 * @param append 
	 */
	public static void writeToFS (String text, String filename, Boolean append) {
		PrintWriter out = null;

		int dirPathEnd = filename.lastIndexOf(File.separator);
		String dirPath = "";
		if (dirPathEnd != -1) {
			dirPath = filename.substring(0, dirPathEnd);
			createDir(dirPath); 
		}
		try {
			out = new PrintWriter(new FileOutputStream(
					new File(filename), 
					append));
			//			out = new PrintWriter(filename);

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		out.print(text);
		out.close();
	}

	public static void writeToFS (List<String> lines, String filename, Boolean append) {
		PrintWriter out = null;
		try {
			out = new PrintWriter(new FileOutputStream(
					new File(filename), 
					append));
			//			out = new PrintWriter(filename);

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (String line : lines)
			out.println(line);
		out.close();
	}



	/**
	 * Utility method to read a given text from a file
	 * fileName doit intégrer le nom du répertoire dans le filename"/"
	 * @throws IOException 
	 */
	public static ArrayList<String> readFromFileToLineArray(File file){
		System.out.println("Debug: full input File Name "+ file);
		ArrayList<String> lines = new ArrayList<String>();

		// Open the file that is the first 
		// command line parameter
		FileInputStream fstream = null;
		try {
			fstream = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// Get the object of DataInputStream
		DataInputStream in = new DataInputStream(fstream);
		BufferedReader br = new BufferedReader(new InputStreamReader(in));
		String strLine; 
		//Read File Line By Line
		try {
			while ((strLine = br.readLine()) != null)   {
				lines.add(strLine);
				// Print the content on the console
				//System.out.println (strLine);
			}
			//Close the input stream
			in.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return lines;
	}


	/**
	 * Simple shell command execute
	 * @param command 
	 * @return
	 */
	public static String executeCommand(String [] command) {
		StringBuffer output = new StringBuffer();
		Process p;
		try {
			p = Runtime.getRuntime().exec(command);
			p.waitFor();
			BufferedReader reader = 
					new BufferedReader(new InputStreamReader(p.getInputStream(),Charset.defaultCharset()));
			String line = "";			
			while ((line = reader.readLine())!= null) {
				output.append(line + "\n");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return output.toString();

	}




	/**
	 * Retrieves a list of files (not the directories) from a given directory 
	 * @throws FileNotFoundException 
	 */
	public static ArrayList<File> retrieveFilesFromDirectory(String directoryName) throws FileNotFoundException {

		File inputDirFile = new File(directoryName);
		if (!inputDirFile.exists()) {
			String errorMsg =    "The input dir,  " + directoryName + ", is not valid." ;
			throw new FileNotFoundException  (errorMsg); 
			//MESSAGE_DIGEST, errorMsg,
			//new Object[]{inputCSVDirString});
		}

		//get list of files (not subdirectories) in the specified directory
		ArrayList<File> inputFileArrayList = new ArrayList<File>();
		File[] files = inputDirFile.listFiles();
		for (int i = 0; i < files.length; i++) {
			if (!files[i].isDirectory()) {
				inputFileArrayList.add(files[i]);  
			}
		}
		return inputFileArrayList;
	}

	/**
	 * Get the path in the file system of the given class object
	 * @param classObject
	 * @return
	 */
	public static String getCurrentClassPath(
			Object classObject) {
		return classObject.getClass().getProtectionDomain().getCodeSource().getLocation().getPath();
	}

	/**
	 * Delete a directory content 
	 * recursively or not 
	 * @param directoryPath
	 */
	static public void deleteDirectoryContent(String directoryPath, Boolean recursive) 	{
		File file = new File( directoryPath );
		if( file.exists() )  {
			File[] files = file.listFiles();
			for( int i = 0 ; i < files.length ; i++ )  {
				if( files[ i ].isDirectory() )   {
					if (recursive) 
						deleteDirectoryContent( file+"\\"+files[ i ], recursive);
				}
				files[ i ].delete();
			}
		}
		else {System.err.println("Warning: the following directory path does not exist "+ directoryPath);
		}
	}







	/**
	 * Delete a file
	 * @param filePath
	 */
	static public void deleteFile(String filePath) 	{
		File file = new File( filePath );
		if( file.exists() ) 
			file.delete();

		else System.err.println("Warning: the following file path does not exist "+ filePath);

	}


	/**foreach (char c in new string(Path.GetInvalidFileNameChars()))
      {
          Console.Write((int)c);
          Console.Write(",");
      }**/
	final static int[] illegalChars = {34, 60, 62, 124, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 58, 42, 63, 92, 47};
	static {
		Arrays.sort(illegalChars);
	}

	/**
	 * It is usually best to create a temporary file using a secure method such as File.createTempFile()
	 * http://stackoverflow.com/questions/1155107/is-there-a-cross-platform-java-method-to-remove-filename-special-chars
	 * @param badFileName
	 * @return
	 */
	public static String cleanFileName(String badFileName) {
		StringBuilder cleanName = new StringBuilder();
		for (int i = 0; i < badFileName.length(); i++) {
			int c = (int)badFileName.charAt(i);
			if (Arrays.binarySearch(illegalChars, c) < 0) {
				cleanName.append((char)c);
			}
		}
		return cleanName.toString();
	}

	/**
	 * Create directory
	 * @param dirPath
	 * @return
	 */
	public static void createDir(String dirPath) {

		File theDir = new File(dirPath);

		// if the directory does not exist, create it
		if (!theDir.exists()) {
			System.err.println("Info: creating directory: " + dirPath);
			boolean result = theDir.mkdir();  

			if(!result) {    
				System.err.println("Error: problem when creating directory: " + dirPath);  
			}
		}
	}

}