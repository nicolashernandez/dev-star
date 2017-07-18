/*
Copyright 2011 Brian Romanowski. All rights reserved.
Portions Copyright 1999-2002 Carnegie Mellon University.  
Portions Copyright 2002 Sun Microsystems, Inc.  
Portions Copyright 2002 Mitsubishi Electric Research Laboratories.
All Rights Reserved.  Use is subject to license terms.

Redistribution and use in source and binary forms, with or without modification, are
permitted provided that the following conditions are met:

   1. Redistributions of source code must retain the above copyright notice, this list of
      conditions and the following disclaimer.

   2. Redistributions in binary form must reproduce the above copyright notice, this list
      of conditions and the following disclaimer in the documentation and/or other materials
      provided with the distribution.

THIS SOFTWARE IS PROVIDED BY BRIAN ROMANOWSKI ``AS IS'' AND ANY EXPRESS OR IMPLIED
WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND
FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL BRIAN ROMANOWSKI OR
CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON
ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF
ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.

The views and conclusions contained in the software and documentation are those of the
authors.
*/

/*
 * Notes on licensing:
 * This code was written while inspecting the Sphinx 4 edu.cmu.sphinx.util.NISTAlign source code and 
 * the NIST sclite documentation.  To the degree that the source code here is copied from NISTAlign, 
 * it is covered by the copyrights reproduced above.  Brian Romanowski holds the copyright to the rest.
 */

package com.pwnetics.metric;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Computes the word error rate (WER) and other statistics available from an alignment of a hypothesis string and a reference string.
 * The alignment and metrics are intended to be, by default, identical to those of the <a href="http://www.icsi.berkeley.edu/Speech/docs/sctk-1.2/sclite.htm">NIST SCLITE tool</a>.  
 * 
 * <p>This code was written while consulting the Sphinx 4 edu.cmu.sphinx.util.NISTAlign source code.</p> 
 * 
 * @author romanows
 */
public class WordSequenceAligner {

	/** Cost of a substitution string edit operation applied during alignment. 
	 * From edu.cmu.sphinx.util.NISTAlign, which should be referencing the NIST sclite utility settings. */
	public static final int DEFAULT_SUBSTITUTION_PENALTY = 100; // Default was 100
	
	/** Cost of an insertion string edit operation applied during alignment. 
	 * From edu.cmu.sphinx.util.NISTAlign, which should be referencing the NIST sclite utility settings. */
	public static final int DEFAULT_INSERTION_PENALTY = 75;
	
	/** Cost of a deletion string edit operation applied during alignment. 
	 * From edu.cmu.sphinx.util.NISTAlign, which should be referencing the NIST sclite utility settings. */
	public static final int DEFAULT_DELETION_PENALTY = 75;

	/** Substitution penalty for reference-hypothesis string alignment */
	private final int substitutionPenalty;
			 
	/** Insertion penalty for reference-hypothesis string alignment */
	private final int insertionPenalty;
	
    /** Deletion penalty for reference-hypothesis string alignment */
	private final int deletionPenalty;
	
	

	

	
	/**
	 * Constructor.
	 * Creates an object with default alignment penalties.  
	 */
	public WordSequenceAligner() {
		this(DEFAULT_SUBSTITUTION_PENALTY, DEFAULT_INSERTION_PENALTY, DEFAULT_DELETION_PENALTY);
	}
	
	
	/**
	 * Constructor. 
	 * @param substitutionPenalty substitution penalty for reference-hypothesis string alignment
	 * @param insertionPenalty insertion penalty for reference-hypothesis string alignment
	 * @param deletionPenalty deletion penalty for reference-hypothesis string alignment
	 */
	public WordSequenceAligner(int substitutionPenalty, int insertionPenalty, int deletionPenalty) {
		this.substitutionPenalty = substitutionPenalty;
		this.insertionPenalty = insertionPenalty;
		this.deletionPenalty = deletionPenalty;
	}
	
	
	/**
	 * Produce alignment results for several pairs of sentences.
	 * @see #align(String[], String[])
	 * @param references reference sentences to align with the given hypotheses 
	 * @param hypotheses hypothesis sentences to align with the given references
	 * @return collection of per-sentence alignment results
	 */
	public List<Alignment> align(List<String []> references, List<String []> hypotheses) {
		if(references.size() != hypotheses.size()) {
			throw new IllegalArgumentException();
		}
		if(references.size() == 0) {
			return new ArrayList<Alignment>();
		}
		
		List<Alignment> alignments = new ArrayList<Alignment>();
		Iterator<String[]> refIt = references.iterator();
		Iterator<String[]> hypIt = hypotheses.iterator();
		while(refIt.hasNext()) {
			alignments.add(align(refIt.next(), hypIt.next()));
		}
		return alignments;
	}

	
	/**
	 * Produces {@link Alignment} results from the alignment of the hypothesis words to the reference words.
	 * Alignment is done via weighted string edit distance according to {@link #substitutionPenalty}, {@link #insertionPenalty}, {@link #deletionPenalty}.
	 * 
	 * @param reference sequence of words representing the true sentence; will be evaluated as lowercase.
	 * @param hypothesis sequence of words representing the hypothesized sentence; will be evaluated as lowercase.
	 * @return results of aligning the hypothesis to the reference 
	 */
	public Alignment align(String [] reference, String [] hypothesis) {
		
		/*System.out.println("#-- reference (reply)");
		for (String token : reference) System.out.print(token + " ");
		System.out.println();

		System.out.println("#-- hypothesis (source)");
		for (String token : hypothesis) System.out.print(token + " ");
		System.out.println();*/
		
		// Values representing string edit operations in the backtrace matrix
		final int OK = 0;  
		final int SUB = 1;
		final int INS = 2;
		final int DEL = 3;

		/* 
		 * Next up is our dynamic programming tables that track the string edit distance calculation.
		 * The row address corresponds to an index within the sequence of reference words.
		 * The column address corresponds to an index within the sequence of hypothesis words.
		 * cost[0][0] addresses the beginning of two word sequences, and thus always has a cost of zero.  
		 */
		
		/** cost[3][2] is the minimum alignment cost when aligning the first two words of the reference to the first word of the hypothesis */
		int [][] cost = new int[reference.length + 1][hypothesis.length + 1];
		
		/** 
		 * backtrace[3][2] gives information about the string edit operation that produced the minimum cost alignment between the first two words of the reference to the first word of the hypothesis.
		 * If a deletion operation is the minimum cost operation, then we say that the best way to get to hyp[1] is by deleting ref[2].
		 */
		int [][] backtrace = new int[reference.length + 1][hypothesis.length + 1];
		
		// Initialization
		cost[0][0] = 0;
		backtrace[0][0] = OK;
		
		// First column represents the case where we achieve zero hypothesis words by deleting all reference words.
		for(int i=1; i<cost.length; i++) {
			cost[i][0] = deletionPenalty * i;
			backtrace[i][0] = DEL; 
		}
		
		// First row represents the case where we achieve the hypothesis by inserting all hypothesis words into a zero-length reference.
		for(int j=1; j<cost[0].length; j++) {
			cost[0][j] = insertionPenalty * j;
			backtrace[0][j] = INS; 
		}

		// For each next column, go down the rows, recording the min cost edit operation (and the cumulative cost). 
		for(int i=1; i<cost.length; i++) {
			for(int j=1; j<cost[0].length; j++) {
				int subOp, cs;  // it is a substitution if the words aren't equal, but if they are, no penalty is assigned.
				if(reference[i-1].toLowerCase().equals(hypothesis[j-1].toLowerCase())) {
					subOp = OK;
					cs = cost[i-1][j-1];
				} else {
					subOp = SUB;
					cs = cost[i-1][j-1] + substitutionPenalty;
				}
				int ci = cost[i][j-1] + insertionPenalty;
				int cd = cost[i-1][j] + deletionPenalty;
				
				int mincost = Math.min(cs, Math.min(ci, cd));
				if(cs == mincost) {
					cost[i][j] = cs;
					backtrace[i][j] = subOp;
				} else if(ci == mincost) {
					cost[i][j] = ci;
					backtrace[i][j] = INS;					
				} else {
					cost[i][j] = cd;
					backtrace[i][j] = DEL;					
				}
			}
		}
		
		// Now that we have the minimal costs, find the lowest cost edit to create the hypothesis sequence
		LinkedList<String> alignedReference = new LinkedList<String>();
		LinkedList<String> alignedHypothesis = new LinkedList<String>();
		int numSub = 0;
		int numDel = 0;
		int numIns = 0;
		int i = cost.length - 1;
		int j = cost[0].length - 1;
		while(i > 0 || j > 0) {
			switch(backtrace[i][j]) {
			case OK: alignedReference.add(0, reference[i-1].toLowerCase()); alignedHypothesis.add(0,hypothesis[j-1].toLowerCase()); i--; j--; break;
			case SUB: alignedReference.add(0, reference[i-1].toUpperCase()); alignedHypothesis.add(0,hypothesis[j-1].toUpperCase()); i--; j--; numSub++; break;
			case INS: alignedReference.add(0, null); alignedHypothesis.add(0,hypothesis[j-1].toUpperCase()); j--; numIns++; break;
			case DEL: alignedReference.add(0, reference[i-1].toUpperCase()); alignedHypothesis.add(0,null); i--; numDel++; break;
			}
		}
		
		/*System.out.println("#-- alignedReference LinkedList (reply)");
		System.out.println(alignedReference);
		System.out.println();
		System.out.println("#-- alignedReference LinkedList (source)");
		System.out.println(alignedHypothesis);
		System.out.println();*/
		return new Alignment(alignedReference.toArray(new String[] {}), alignedHypothesis.toArray(new String[] {}), numSub, numIns, numDel);
	}
}
