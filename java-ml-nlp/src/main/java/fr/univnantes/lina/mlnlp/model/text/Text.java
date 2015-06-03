/**
 * 
 */
package fr.univnantes.lina.mlnlp.model.text;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author hernandez
 *
 */
public class Text {



	private List<Sentence> sentences  = new ArrayList<Sentence>();;
	private List<Word> words;

	// in terms of word position
	protected Set<Integer> sentenceStartWordPosition = new HashSet<Integer>();
	protected Set<Integer> sentenceEndWordPosition = new HashSet<Integer>();





	public Text( List<Sentence> sentences,Set<Integer> sentenceStartWordPosition, Set<Integer> sentenceEndWordPosition) {
		setSentences(sentences);
		setSentenceStartWordPosition(sentenceStartWordPosition);
		setSentenceEndWordPosition(sentenceEndWordPosition);
	}



	private void setSentences(List<Sentence> sentences) {
		this.sentences = sentences;

	}

	private void setSentenceStartWordPosition(
			Set<Integer> sentenceStartWordPosition) {

	}

	private void setSentenceEndWordPosition(
			Set<Integer> sentenceEndWordPosition) {
		this.sentenceEndWordPosition = sentenceEndWordPosition;

	}


	/**
	 * Get tokens of sentence
	 * @return
	 */
	public  List<Sentence> getSentences () {
		return this.sentences;
	}



	public List<Word> getWords() {
		if (words == null) 
			SentencesToWords();
		return words;
	}


	public void setWords(List<Word> words) {
		this.words= words;
	}


	/**
	 * Get list of sentences made of list of words 
	 * @return
	 */
	public  List<List<Word>> getSentencesOfWords () {
		List<List<Word>> result = new ArrayList<List<Word>>();
		for (Sentence sentence : getSentences()) {
			result.add(sentence.getWords());
		}
		return result;
	}

	
	
	public void SentencesToWords() {
		List<Word> words = new ArrayList<Word>();
		for (Sentence sentence : sentences) {
			for (Word word : sentence.getWords()) {
				words.add(word);
			}
		}
		setWords(words);
	}
}
