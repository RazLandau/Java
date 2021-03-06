package il.ac.tau.cs.sw1.ex8.wordsRank;

import java.util.Collections;
import java.util.Map;

class RankedWord{
	
	public static enum rankType {average, min, max};

	
	private String word;
	private Map<String, Integer> ranksForFile;
	private int average;
	private int min;
	private int max;
	
	public RankedWord(String word, Map<String, Integer> ranks){
		this.word = word;
		this.ranksForFile = ranks;
		this.min = Collections.min(ranksForFile.values()); // retrieve the minimal value in a collection
		this.max = Collections.max(ranksForFile.values()); // retrieve the maxinum value in a collection
		int sum = 0;
		for (Integer rank : ranksForFile.values()){
			sum += rank;
		}
		this.average = (int)Math.round(((double)sum)/ranksForFile.size());
	}

	public String getWord() {
		return word;
	}

	public Map<String, Integer> getRanksForFile() {
		return ranksForFile;
	}

	public int getRankByType(rankType rType){
		// This is how you use an enum in a switch/case block
		switch(rType){
		case average:
			return average;
		case min:
			return min;
		default: //case max
			return max;
		}
	}

	@Override
	public String toString() {
		return "RankedWord [word=" + word + ", ranksForFile=" + ranksForFile + ", average=" + average + ", min="
				+ min + ", max=" + max + "]";
	}
}
