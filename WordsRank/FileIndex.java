package il.ac.tau.cs.sw1.ex8.wordsRank;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import il.ac.tau.cs.sw1.ex8.histogram.HashMapHistogram;

/**
 * Main data structure
 * @author Raz
 *
 */
public class FileIndex {

	public static final int UNRANKED_CONST = 20;

	private HashMap<String, HashMapHistogram<String>> index = new HashMap<String, HashMapHistogram<String>>();
	private HashSet<String> words = new HashSet<String>();

	/*
	 * @pre: the directory is no empty, and contains only readable text files
	 * @post: initialized members
	 */
	public void indexDirectory(String folderPath) {
		File folder = new File(folderPath);
		File[] listFiles = folder.listFiles();
		for (File file : listFiles) {
			// for every file in the folder
			if (file.isFile()) {
				HashMapHistogram<String> histogram = new HashMapHistogram<String>();
				try {
					histogram.addAll(FileUtils.readAllTokens(file));
					words.addAll(FileUtils.readAllTokens(file));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				index.put(file.getName(), histogram);
			}
		}
	}

	/**
	 * @param filename
	 * @param word
	 * @pre: the index is initialized
	 * @return number of appearances of word in filename
	 * @throws FileIndexException
	 */
	public int getCountInFile(String filename, String word)
			throws FileIndexException {
		if (!index.containsKey(filename)) {
			throw new FileIndexException("Error! File not in index");
		}
		word = word.toLowerCase();
		return index.get(filename).getCountForItem(word);
	}

	/**
	 * 
	 * @param filename
	 * @param word
	 * @pre: the index is initialized
	 * @return rank of word in filename, where:
	 * rank = order of word in filename by appearances
	 * (e.g: rank(most frequent word) = 1)
	 * @throws FileIndexException
	 */
	public int getRankForWordInFile(String filename, String word)
			throws FileIndexException {
		if (!index.containsKey(filename)) {
			throw new FileIndexException("Error! File not in index");
		}
		int rank = 0;
		for (String key : index.get(filename)) {
			rank++;
			if (key.equals(word)) {
				break;
			}
		}
		return rank != index.get(filename).size() ? rank
				: rank + UNRANKED_CONST;
	}
	
	/**
	 * 
	 * @param word
	 * @pre: the index is initialized
	 * @return avenrage rank of word in index
	 */
	public int getAverageRankForWord(String word) {
		return getRankForWordByType(word, RankedWord.rankType.average);
	}
	
	/**
	 * 
	 * @param k
	 * @return all words in index so that averageRank(word) < k, where:
	 * averageRank = average of all ranks of word in index
	 */
	public List<String> getWordsBelowAverageRank(int k) {
		return getWordsBelowByRankType(k, RankedWord.rankType.average);
	}
	
	/**
	 * 
	 * @param k
	 * @return all words in index so that minRank(word) < k, where:
	 * minRank = min of all ranks of word in index
	 */
	public List<String> getWordsBeloweMinRank(int k) {
		return getWordsBelowByRankType(k, RankedWord.rankType.min);
	}
	
	/**
	 * 
	 * @param k
	 * @return all words in index so that minRank(word) < k, where:
	 * minRank = max of all ranks of word in index
	 */
	public List<String> getWordsBelowMaxRank(int k) {
		return getWordsBelowByRankType(k, RankedWord.rankType.max);
	}
	
	/*****************************************************************************************************/
	/**********************
	 * Helper functions
	 *************************************************************/
	/*****************************************************************************************************/

	/**
	 * 
	 * @param word
	 * @param rType
	 * @return rank of word in index by rType
	 */
	private int getRankForWordByType(String word, RankedWord.rankType rType) {
		Map<String, Integer> ranks = new HashMap<String, Integer>();
		for (String filename : index.keySet()) {
			try {
				ranks.put(filename, getRankForWordInFile(filename, word));
			} catch (FileIndexException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		RankedWord rankedWord = new RankedWord(word, ranks);
		return rankedWord.getRankByType(rType);
	}

	/**
	 * 
	 * @param k
	 * @param rType
	 * @return all words in index so that rankType(word) < k
	 */
	private List<String> getWordsBelowByRankType(int k,
			RankedWord.rankType rType) {
		List<String> result = new ArrayList<String>();
		for (String word : words) {
			if (getRankForWordByType(word, rType) <= k) {
				result.add(word);
			}
		}
		Collections.sort(result);
		return result;
	}
}
