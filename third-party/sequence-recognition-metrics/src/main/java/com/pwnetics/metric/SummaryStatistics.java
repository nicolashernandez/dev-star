package com.pwnetics.metric;

import java.util.Collection;

/**
 * Collects several alignment results.
 * Has a {@link #toString()} method that pretty-prints a human-readable summary metrics for the collection of results.
 *  
 * @author romanows
 */
public class SummaryStatistics {
	/** Number of correct words in the aligned hypothesis with respect to the reference */
	private int numCorrect;

	/** Number of word substitutions made in the hypothesis with respect to the reference */
	private int numSubstitutions;
	
	/** Number of word insertions (unnecessary words present) in the hypothesis with respect to the reference */
	private int numInsertions;
	
	/** Number of word deletions (necessary words missing) in the hypothesis with respect to the reference */
	private int numDeletions;
	
	/** Number of hypotheses that exactly match the associated reference */
	private int numSentenceCorrect;

	/** Total number of words in the reference sequences */
	private int numReferenceWords;
	
	/** Total number of words in the hypothesis sequences */
	private int numHypothesisWords;
	
	/** Number of sentences */
	private int numSentences;
	
	
	/**
	 * Constructor.
	 * @param alignments collection of alignments
	 */
	public SummaryStatistics(Collection<Alignment> alignments) {
		for(Alignment a : alignments) {
			add(a);
		}
	}
	
	/**
	 * Add a new alignment result
	 * @param alignment result to add
	 */
	public void add(Alignment alignment) {
		numCorrect += alignment.getNumCorrect();
		numSubstitutions += alignment.numSubstitutions;
		numInsertions += alignment.numInsertions;
		numDeletions += alignment.numDeletions;
		numSentenceCorrect += alignment.isSentenceCorrect() ? 1 : 0;
		numReferenceWords += alignment.getReferenceLength();
		numHypothesisWords += alignment.getHypothesisLength();
		numSentences++;
	}
	
	public int getNumSentences() {
		return numSentences;
	}

	public int getNumReferenceWords() {
		return numReferenceWords;
	}
	
	public int getNumHypothesisWords() {
		return numHypothesisWords;
	}
	
	public float getCorrectRate() {
		return numCorrect / (float) numReferenceWords;
	}
	
	public float getSubstitutionRate() {
		return numSubstitutions / (float) numReferenceWords;
	}

	public float getDeletionRate() {
		return numDeletions / (float) numReferenceWords;
	}

	public float getInsertionRate() {
		return numInsertions / (float) numReferenceWords;
	}
	
	/** @return the word error rate of this collection */
	public float getWordErrorRate() {
		return (numSubstitutions + numDeletions + numInsertions) / (float) numReferenceWords;
	}
	
	/** @return the sentence error rate of this collection */
	public float getSentenceErrorRate() {
		return (numSentences - numSentenceCorrect) / (float) numSentences;
	}
	
	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("#seq").append("\t");
		sb.append("#ref").append("\t");
		sb.append("#hyp").append("\t");
		sb.append("cor").append("\t");
		sb.append("sub").append("\t");
		sb.append("ins").append("\t");
		sb.append("del").append("\t");
		sb.append("WER").append("\t");
		sb.append("SER").append("\t");
		sb.append("\n");

		sb.append(numSentences).append("\t");
		sb.append(numReferenceWords).append("\t");
		sb.append(numHypothesisWords).append("\t");
		sb.append(getCorrectRate()).append("\t");
		sb.append(getSubstitutionRate()).append("\t");
		sb.append(getInsertionRate()).append("\t");
		sb.append(getDeletionRate()).append("\t");
		sb.append(getWordErrorRate()).append("\t");
		sb.append(getSentenceErrorRate());
		return sb.toString();
	}
}
