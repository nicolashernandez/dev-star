package com.pwnetics.metric;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import fr.univnantes.lina.javautil.IOUtilities;

public class TestRunWordSequenceAligner {

	/**
	 * Files should have the same number of lines
	 */
	@Test
	public final void lineToLineComparisonTest() {
		//fail("Not yet implemented"); // TODO

		String [] args = new String[5];
		args[0] = "-r";
		args[1] = IOUtilities.getCurrentClassPath(this)	+"/com/pwnetics/metric/data/ms.ml.wst.ref";
		args[2] = "-h";
		args[3] = IOUtilities.getCurrentClassPath(this)	+"/com/pwnetics/metric/data/ms.ml.wst.hyp";
		args[4] = "-v";
		RunWordSequenceAligner.main(args);

	}
	/**
	 * Since the two files do not have the same number of lines, 
	 * force the concatenation to get one line for each
	 * Use case: files with a word per line or files with not the same number of lines
	 */
	@Test
	public final void forceSingleLineComparisonTest() {
		//fail("Not yet implemented"); // TODO

		String [] args = new String[6];
		args[0] = "-r";
		args[1] = IOUtilities.getCurrentClassPath(this)	+"/com/pwnetics/metric/data/ms.wl.ref";
		args[2] = "-h";
		args[3] = IOUtilities.getCurrentClassPath(this)	+"/com/pwnetics/metric/data/ms.wl.hyp";
		args[4] = "-v"; 
		args[5] = "--force-single-line";
		RunWordSequenceAligner.main(args);

	}

	@Before 
	public void echoStdoutSeparator() {
		System.out.println("==============================================================");
	}


}
