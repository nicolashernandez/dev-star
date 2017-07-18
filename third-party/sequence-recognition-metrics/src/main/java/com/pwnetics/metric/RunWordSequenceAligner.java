/**
 * Command line interface for the WordSequenceAligner
 * 
 * Apache 2 Licence
 * 
 * N. Hernandez 20130222
 **/ 
package com.pwnetics.metric;



import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.pwnetics.metric.WordSequenceAligner;
import fr.univnantes.lina.javautil.IOUtilities;

/**
 * 
 * @author hernandez
 *
 */
public class RunWordSequenceAligner {


	public static void main(String[] args) {
		boolean verbose = false;
		int pos = 0;

		String refTextPath = "";
		//String refText = "";
		// list of sentences composing the ref text
		List<String> refText = new ArrayList<String>();

		//String hypText = "";
		//String hypTextPath = "";
		List<String> hypTextPathList = new ArrayList<String>();
		// list hyp text made of list of sentences 
		List<List<String>> hypTextList = new ArrayList<List<String>>();

		String wordDelimiter = " ";
		//		String sentenceSeparator = "\n";
		Boolean forceRefAndHypAsSingleLine = false;

		/*
		 * Getting parameters 
		 */

		while (pos < args.length) {
			if(args[pos].equals("-v")) {
				verbose = true;
				++pos;
				System.out.println("Mode verbose");
			}
			else if(args[pos].equals("-r")) {
				++pos;
				if(verbose) System.out.println("Reference text path: "+args[pos]);
				refTextPath = args[pos];
				++pos;
			}
			else if(args[pos].equals("-h")) {
				++pos;
				if(verbose) System.out.println("Hypothesized text path: "+args[pos]);
				//hypTextPath = args[pos];
				hypTextPathList.add(args[pos]);
				++pos;
			}
			else if(args[pos].equals("-d")) {
				++pos;
				if(verbose) System.out.println("Word delimiter: >"+args[pos]+"<");
				wordDelimiter = args[pos];
				++pos;

			} 
			else if(args[pos].equals("--force-single-line")) {
				forceRefAndHypAsSingleLine = true;
				++pos;
				if(verbose) System.out.println("Consider the input stream as a single line");
			}
			else {
				usage("Wrong parameters");
			}
		}
		if (refTextPath.equalsIgnoreCase("") ||  hypTextPathList.size() ==0) 
			usage("A reference and a hypothesis text should at least be defined");
		System.out.println("");


		/*
		 * Loading files
		 */
		long debut = System.currentTimeMillis();

		if (forceRefAndHypAsSingleLine) {
			//refText = readTheWholeTextAsASingleLine(refTextPath);
			String refTextString = IOUtilities.readTextFileAsString(refTextPath);
			refTextString = refTextString.replaceAll(System.getProperty("line.separator"), " ").trim();

			//System.out.println("Debug: path>"+refTextPath+"< text>"+refTextString+"<");

			refText.add(refTextString);
		}
		else
			//	refText = readMultiLines(refTextPath);
			refText = IOUtilities.readTextFileAsStringList(refTextPath) ;



		String hypTextPath ="";
		//try {
		//			if (parseSentences) 
		//				else 
		Iterator<String> hypTextPathListIterator = hypTextPathList.iterator();

		while (hypTextPathListIterator.hasNext()) {
			hypTextPath = hypTextPathListIterator.next();
			if (forceRefAndHypAsSingleLine) {
				String hypTextString = IOUtilities.readTextFileAsString(hypTextPath);
				hypTextString = hypTextString.replaceAll(System.getProperty("line.separator"), " ").trim();
				
				//System.out.println("Debug: path>"+hypTextPath+"< text>"+hypTextString+"<");

				List<String> tmpList = new ArrayList<String>();
				tmpList.add(hypTextString);
				//List<String> tmpList = readTheWholeTextAsASingleLine(hypTextPath);
				hypTextList.add(tmpList);
				//System.out.println("Debug: readHypText size:"+hypTextList.size()+" .get(0):"+ tmpList.get(0));
			}
			else 
				//hypTextList.add(readMultiLines(hypTextPath));
				hypTextList.add(IOUtilities.readTextFileAsStringList(hypTextPath)) ;

			if (hypTextList.get(hypTextList.size()-1).size() != refText.size())
				usage("refText has a different size from hypText "+ hypTextPath+".\nThey should have the same number of lines; or use -nosent parameter");
		}
		/*} catch (IOException ioe) {
			System.out.println("Wrong hypTextPath: "+hypTextPath);
			ioe.printStackTrace();
		}*/

		long fin = System.currentTimeMillis();
		if(verbose) System.out.println("Files  loaded in: "+ ((fin-debut)/1000.0)+" s");

		/*
		 * Alignment
		 */
		debut = System.currentTimeMillis();


		Iterator<List<String>> hypTextListIterator = hypTextList.iterator();

		// hypText index
		int j = 0;

		// for each hypText
		while (hypTextListIterator.hasNext()) {
			List<String> hypText = hypTextListIterator.next();
			WordSequenceAligner werEval = new WordSequenceAligner();

			// add all the lines to align
			List<Alignment> alignments = new ArrayList<Alignment>();

			System.out.println("Aligning "+refTextPath+" with "+hypTextPathList.get(j++));

			// line index
			int i = 0;
			for (String hypLine : hypText) {
				// Reference alignments and stats created with the NIST sclite tool, default settings
				Alignment a = werEval.align(refText.get(i++).split(wordDelimiter), hypLine.split(wordDelimiter));
				alignments.add(a);
				if(verbose) System.out.println(a);
			}
			SummaryStatistics ss = new SummaryStatistics(alignments);
			System.out.println("Statistics summary");
			System.out.println(ss);
			System.out.println("");

		}

		fin = System.currentTimeMillis();
		if(verbose) System.out.println("Alignment processed in: "+ ((fin-debut)/1000.0)+" s");

	}

	/**
	 *
	 */
	private static void usage(String message)  {
		System.out.println();
		System.out.println("Process stopped: "+message);
		System.out.println("Usage: java -r RefFile -h HypFile [-h HypFile]... [--force-single-line]  [-d wordDelimiter] [-v]");
		System.out.println("  --force-single-line (If the reference and the hypothesis files do not have the same number of sentences lines or word perline, then force both to be processed as a single line; \\n is the line delimiter)");	
		System.out.println("  -d wordDelimiter (by default: whitespace character)");
		System.out.println("  -v (verbose)");

		System.exit(1);
	}


	/**
	 * http://stackoverflow.com/questions/326390/how-to-create-a-java-string-from-the-contents-of-a-file
	 * Alternative exists: faster but more memory consuming
	 */
	/*private static List<String> readMultiLines( String filePath ) throws IOException {
		BufferedReader reader = new BufferedReader( new FileReader (filePath));
		String         line = null;
		//StringBuilder  stringBuilder = new StringBuilder();
		//String         ls = System.getProperty("line.separator");
		List<String> lineList = new ArrayList<String>();

		while( ( line = reader.readLine() ) != null ) {
			//stringBuilder.append( line );
			//stringBuilder.append( ls );
			lineList.add(line);
		}
		return lineList;
		//return stringBuilder.toString();
	}*/


}



