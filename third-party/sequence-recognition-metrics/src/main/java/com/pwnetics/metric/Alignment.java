
package com.pwnetics.metric;

/**
 * Result of an alignment.
 * Has a {@link #toString()} method that pretty-prints human-readable metrics.
 *  
 * @author romanows
 */ 
public class Alignment {
	/** Reference words, with null elements representing insertions in the hypothesis sentence and upper-cased words representing an alignment mismatch */
	public final String [] reference;

	/** Hypothesis words, with null elements representing deletions (missing words) in the hypothesis sentence and upper-cased words representing an alignment mismatch */
	public final String [] hypothesis;


	/** Number of word substitutions made in the hypothesis with respect to the reference */
	public final int numSubstitutions;
	
	/** Number of word insertions (unnecessary words present) in the hypothesis with respect to the reference */
	public final int numInsertions;
	
	/** Number of word deletions (necessary words missing) in the hypothesis with respect to the reference */
	public final int numDeletions;

	
	/** Reference words, 
	 * with null elements representing insertions in the hypothesis sentence and upper-cased words representing an alignment mismatch 
	 *
	 * @return the reference
	 */
	public String[] getReference() {
		return reference;
	}
	
	
	/**
	 * Hypothesis words, 
	 * with null elements representing deletions (missing words) in the hypothesis sentence and upper-cased words representing an alignment mismatch 
	 * 
	 * @return the hypothesis
	 */
	public String[] getHypothesis() {
		return hypothesis;
	}

	
	/**
	 * Constructor.
	 * @param reference reference words, with null elements representing insertions in the hypothesis sentence
	 * @param hypothesis hypothesis words, with null elements representing deletions (missing words) in the hypothesis sentence
	 * @param numSubstitutions Number of word substitutions made in the hypothesis with respect to the reference 
	 * @param numInsertions Number of word insertions (unnecessary words present) in the hypothesis with respect to the reference
	 * @param numDeletions Number of word deletions (necessary words missing) in the hypothesis with respect to the reference
	 */
	public Alignment(String [] reference, String [] hypothesis, int numSubstitutions, int numInsertions, int numDeletions) {
		if(reference == null || hypothesis == null || reference.length != hypothesis.length || numSubstitutions < 0 || numInsertions < 0 || numDeletions < 0) {
			throw new IllegalArgumentException();
		}
		this.reference = reference;
		this.hypothesis = hypothesis;
		this.numSubstitutions = numSubstitutions;
		this.numInsertions = numInsertions;
		this.numDeletions = numDeletions;
	}
	
	/**
	 * Number of word correct words in the aligned hypothesis with respect to the reference.
	 * @return number of word correct words 
	 */
	public int getNumCorrect() {
		return getHypothesisLength() - (numSubstitutions + numInsertions);  // Substitutions are mismatched and not correct, insertions are extra words that aren't correct
	}
	
	/** @return true when the hypothesis exactly matches the reference */
	public boolean isSentenceCorrect() {
		return numSubstitutions == 0 && numInsertions == 0 && numDeletions == 0;
	}
	
	/**
	 * Get the length of the original reference sequence.
	 * This is not the same as {@link #reference}.length(), because that member variable may have null elements 
	 * inserted to mark hypothesis insertions.
	 * 
	 * @return the length of the original reference sequence
	 */
	public int getReferenceLength() {
		return reference.length - numInsertions;
	}
	
	/**
	 * Get the length of the original hypothesis sequence.
	 * This is not the same as {@link #hypothesis}.length(), because that member variable may have null elements 
	 * inserted to mark hypothesis deletions.
	 * 
	 * @return the length of the original hypothesis sequence
	 */
	public int getHypothesisLength() {
		return hypothesis.length - numDeletions;
	}
	
	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder ref = new StringBuilder();
		StringBuilder hyp = new StringBuilder();
		ref.append("REF:\t");
		hyp.append("HYP:\t");
		for(int i=0; i<reference.length; i++) {
			if(reference[i] == null) {
				for(int j=0; j<hypothesis[i].length(); j++) {
					ref.append("*");
				}
			} else {
				ref.append(reference[i]);
			}
			
			if(hypothesis[i] == null) {
				for(int j=0; j<reference[i].length(); j++) {
					hyp.append("*");
				}
			} else {
				hyp.append(hypothesis[i]);
			}

			if(i != reference.length - 1) {
				ref.append(" ");
				hyp.append(" ");
			}
		}
		
		StringBuilder sb = new StringBuilder();
		sb.append("\t");
		sb.append("#seq").append("\t");
		sb.append("#ref").append("\t");
		sb.append("#hyp").append("\t");
		sb.append("#cor").append("\t");
		sb.append("#sub").append("\t");
		sb.append("#ins").append("\t");
		sb.append("#del").append("\t");
		sb.append("acc").append("\t");
		sb.append("WER").append("\t");
		sb.append("# seq cor").append("\t");

		sb.append("\n");
		sb.append("STATS:\t");
		sb.append(1).append("\t");
		sb.append(getReferenceLength()).append("\t");
		sb.append(getHypothesisLength()).append("\t");
		sb.append(getNumCorrect()).append("\t");
		sb.append(numSubstitutions).append("\t");
		sb.append(numInsertions).append("\t");
		sb.append(numDeletions).append("\t");
		sb.append(getNumCorrect() / (float) getReferenceLength()).append("\t");
		sb.append((float)((numSubstitutions + numInsertions + numDeletions) / (float) getReferenceLength())).append("\t");
		sb.append(isSentenceCorrect() ? 1 : 0);

		sb.append("\n");
		sb.append("-----\t");
		sb.append("-----\t");
		sb.append("-----\t");
		sb.append("-----\t");
		sb.append("-----\t");
		sb.append("-----\t");
		sb.append("-----\t");
		sb.append("-----\t");
		sb.append("-----\t");
		sb.append("-----\t");
		sb.append("-----\t");

		sb.append("\n");
		sb.append(ref).append("\n").append(hyp);
		
		return sb.toString();
	}
}

