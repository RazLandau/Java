package il.ac.tau.cs.sw1.ex8.wordsRank;

import java.util.Comparator;

import il.ac.tau.cs.sw1.ex8.wordsRank.RankedWord.rankType;


/**
 * Compares word by rankType
 * @author Raz
 *
 */
class RankedWordComparator implements Comparator<RankedWord>{
	
	private rankType cType;
	
	public RankedWordComparator(rankType cType) {
		this.cType = cType;
	}
	
	@Override
	public int compare(RankedWord o1, RankedWord o2) {
		return o1.getRankByType(cType) - o2.getRankByType(cType);
	}	
}
