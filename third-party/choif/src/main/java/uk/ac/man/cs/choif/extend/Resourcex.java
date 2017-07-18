package uk.ac.man.cs.choif.extend;

/**
 * Central place for defining external resources
 * Creation date: (09/23/99 16:28:37)
 * @author: Freddy Choi
 */
public class Resourcex {
	
	public static java.net.URL getResource(String name) {
		return ClassLoader.getSystemClassLoader().getResource(name);
	}
	
	/** User's home directory */
	// public static String home = "/home/M97/mphil/choif/";

	/** Host address of all RMI processes */
	public final static java.net.URL rmiHost = RMIx.ermintrude;

	/** Home directory of resource files */
	
	public static String Stopword_defaultResourceFile = "STOPWORD.list";

	/*
	public static String OxfordAdvanced_defaultResourceFile = getPath("OxfordAdvanced.dat");
	public static String PosTagger_defaultResourceFile = getPath("QTAG.tagset");
	public static String Compound_defaultResourceFile = getPath("Compound.list");
	public static String Heading_defaultResourceFile = getPath("Heading.dat");
	public static String Pattern_defaultResourceFile = getPath("Pattern.junk");
	*/
}
