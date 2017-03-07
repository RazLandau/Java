package il.ac.tau.cs.sw1.ex5;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

/*
 * This is a Bigram Language Model for a given vocabulary,
 * providing various statistic analysis options
 */
public class BigramModel {
	// constants
	public static final int MAX_VOCABULARY_SIZE = 15000;
	public static final String SOME_NUM = "some_num";
	public static final int WORD_NOT_IN_VOCABULARY = -1;

	// class members
	private String[] vocabulary;
	private int[][] bigramCounts;

	public int buildModelFromFile(String fileName) throws IOException {
		buildVocabulary(fileName);
		buildBigramCounts(fileName);
		return vocabulary.length;
	}

	/*
	 * @pre: the language model is initialized
	 * 
	 * @pre: fileName is a valid path
	 */
	public boolean saveModelToFile(String fileName) throws IOException {
		BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileName));
		bufferedWriter.write(vocabulary.length + " words" + "\n");
		for (int i = 0; i < bigramCounts.length; i++) {
			String succeeding = vocabulary[i];
			for (int j = 0; j < bigramCounts[0].length; j++) {
				String proceeding = vocabulary[j];
				if (bigramCounts[i][j] != 0) {
					int count = bigramCounts[i][j];
					bufferedWriter.write(succeeding + "," + proceeding + ":" + count + "\n");
				}

			}
		}
		bufferedWriter.close();
		return true;
	}

	/*
	 * @pre: fileName is a valid path that contains a model with a legal format
	 */
	public int loadModelFromFile(String fileName) throws IOException {
		loadVocabulary(fileName);
		loadBigramCounts(fileName);
		return vocabulary.length;
	}

	/*
	 * @pre: word is in lowercase
	 * 
	 * @pre: the language model is initialized
	 * 
	 * @post: $ret = -1 if word is not in vocabulary, otherwise $ret = the index
	 * of word in vocabulary
	 */
	public int getWordIndex(String word) {
		for (int i = 0; i < vocabulary.length; i++) {
			if (vocabulary[i] != null && vocabulary[i].equals(word)) {
				return i;
			}
		}
		return WORD_NOT_IN_VOCABULARY;
	}

	/*
	 * @pre: word1, word2 are in lowercase
	 * 
	 * @pre: the language model is initialized
	 * 
	 * @post: $ret = the count for the bigram <word1, word2>. if one of the
	 * words does not exist in the vocabulary, $ret = 0
	 */
	public int getBigramCount(String word1, String word2) {
		int i = getWordIndex(word1);
		int j = getWordIndex(word2);
		if (i < 0 || j < 0) {
			return 0;
		}
		return bigramCounts[i][j];
	}

	/*
	 * @pre word is in vocabulary
	 * 
	 * @pre: the language model is initialized
	 * 
	 * @post $ret = the word with the lowest vocabulary index that appears most
	 * fequently after word (if a bigram starting with word was never seen, $ret
	 * will be null
	 */
	public String getMostFrequentProceeding(String word) {
		String result = null;
		int max = 0;
		int i = getWordIndex(word);
		for (int j = 0; j < bigramCounts[0].length; j++) {
			String proceeding = vocabulary[j];
			int count = bigramCounts[i][j];
			if (count > max) {
				result = proceeding;
				max = count;
			}
		}
		return result;
	}

	/*
	 * @pre: n > 1, word is in lowercase
	 * 
	 * @pre: the language model is initialized
	 * 
	 * @post: if word is in the vocabulary, $ret is a sentence of at most n
	 * words, starting with word. otherwise, $ret = ""
	 */
	public String buildSentence(String word, int n) {
		if (getWordIndex(word) < 0) {
			return "";
		}
		if (n == 1 || getMostFrequentProceeding(word) == null) {
			return word;
		}
		return word + " " + buildSentence(getMostFrequentProceeding(word), n - 1);
	}

	/*
	 * @pre: word is in vocabulary
	 * 
	 * @pre: the language model is initialized
	 * 
	 * @post: $ret = w implies that w is the word with the largest
	 * cosineSimilarity(vector for word, vector for w) among all the other words
	 * in vocabulary
	 */
	public String getClosestWord(String word) {
		int word_index = getWordIndex(word);
		int[] word_vector = bigramCounts[word_index];
		String result = null;
		double max = Double.NEGATIVE_INFINITY;
		for (int i = 0; i < bigramCounts.length; i++) {
			if (i == word_index) {
				continue;
			}
			String s = vocabulary[i];
			double count = calcCosineSim(bigramCounts[i], word_vector);
			if (count > max) {
				max = count;
				result = s;
			}
		}
		return result;
	}

	/*
	 * @pre: arr1.length = arr2.legnth post if arr1 or arr2 are only filled with
	 * zeros, $ret = 0, otherwise
	 */
	public static double calcCosineSim(int[] arr1, int[] arr2) {
		double numerator = 0;
		double denominator1 = 0;
		double denominator2 = 0;
		for (int i = 0; i < arr1.length; i++) {
			numerator += arr1[i] * arr2[i];
			denominator1 += Math.pow(arr1[i], 2);
			denominator2 += Math.pow(arr2[i], 2);

		}
		if (denominator1 == 0 || denominator2 == 0) {
			return 0;
		}
		return numerator / (Math.sqrt(denominator1) * Math.sqrt(denominator2));
	}

	/*****************************************************************************************************/
	/**********************
	 * helper functions
	 *************************************************************/
	/*****************************************************************************************************/

	/*
	 * @param word
	 * 
	 * @return
	 * "some_num" if word is a number
	 * lowercase(word) if word contains at least 1 letter
	 * null otherwise
	 */
	private String wordToValid(String word) {
		if (wordAllNums(word)) {
			return "some_num";
		}
		if (wordContainsLetter(word)) {
			return word.toLowerCase();
		}
		return null;
	}

	/*
	 * @param word
	 * 
	 * @return true if word is a number, false otherwise
	 */
	private boolean wordAllNums(String word) {
		for (char c : word.toCharArray()) {
			if (c < '0' || c > '9') {
				return false;
			}
		}
		return true;
	}

	/*
	 * @param word
	 * 
	 * @return true if word contains at least 1 letter, false otherwise
	 */
	private boolean wordContainsLetter(String word) {
		for (char c : word.toCharArray()) {
			if ((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z')) {
				return true;
			}
		}
		return false;
	}

	/*
	 * Initialize class member vocablary with 15,000 first legal words from given file
	 * by order of appearance and without repetitions, where legal word:
	 * 1. If word contains at least 1 letter = lowercase(word)
	 * 2. If word is str(int) = "some_num"
	 * 
	 * @param fileName
	 */
	private void buildVocabulary(String fileName) throws IOException {
		vocabulary = new String[MAX_VOCABULARY_SIZE];
		Scanner scanner = new Scanner(new File(fileName));
		int count = 0;
		while (scanner.hasNext() && count < MAX_VOCABULARY_SIZE) {
			String word = wordToValid(scanner.next());
			if (word != null && getWordIndex(word) == WORD_NOT_IN_VOCABULARY) {
				vocabulary[count++] = word;
			}
		}
		scanner.close();
		vocabulary = Arrays.copyOf(vocabulary, count);
	}

	/*
	 * Initialize class member bigramCounts from given file, where:
	 * bigramCounts[x][y] = number of times vocabulary[y] appeared right after vocabulary[x]
	 * 
	 * @param fileName
	 */
	private void buildBigramCounts(String fileName) throws IOException {
		bigramCounts = new int[vocabulary.length][vocabulary.length];
		Scanner scanner = new Scanner(new File(fileName));
		String succeeding, proceeding;
		if (scanner.hasNext()) {
			proceeding = wordToValid(scanner.next());
			while (scanner.hasNext()) {
				succeeding = proceeding;
				proceeding = wordToValid(scanner.next());
				int i = getWordIndex(succeeding);
				int j = getWordIndex(proceeding);
				if (i != WORD_NOT_IN_VOCABULARY && j != WORD_NOT_IN_VOCABULARY) {
					bigramCounts[i][j]++;
				}
			}
		}
		scanner.close();
	}

	/*
	 * Loads given file data to initialize class memeber vocabulary 
	 */
	private void loadVocabulary(String fileName) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));
		String firstLine = bufferedReader.readLine();
		int size = Integer.parseInt(firstLine.split(" ")[0]);
		vocabulary = new String[size];
		int i = 0;
		String line;
		while ((line = bufferedReader.readLine()) != null) {
			String word = line.split(",")[0];
			if (getWordIndex(word) == WORD_NOT_IN_VOCABULARY) {
				vocabulary[i++] = word;
			}
		}
		bufferedReader.close();
	}

	/*
	 * Loads given file data to initialize class memeber bigramCounts 
	 */
	private void loadBigramCounts(String fileName) throws IOException {
		bigramCounts = new int[vocabulary.length][vocabulary.length];
		String line;
		BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));
		bufferedReader.readLine();
		while ((line = bufferedReader.readLine()) != null) {
			String[] splitLine = line.split(",|:");
			String succeeding = splitLine[0];
			String proceeding = splitLine[1];
			int count = Integer.parseInt(splitLine[2]);
			bigramCounts[getWordIndex(succeeding)][getWordIndex(proceeding)] = count;
		}
		bufferedReader.close();
	}
}