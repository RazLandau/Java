package il.ac.tau.cs.sw1.ex8.histogram;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;

/**
 * Iterator for HashMapHistogramIterator, ordered first by appearances and second by item
 * @author Raz
 *
 * @param <T>
 */
public class HashMapHistogramIterator<T extends Comparable<T>>
		implements Iterator<T> {

	List<HashMap.Entry<T, Integer>> sortedHistogram = new LinkedList<HashMap.Entry<T, Integer>>();
	Iterator<HashMap.Entry<T,Integer>> iterator;
	
	public HashMapHistogramIterator(HashMap<T, Integer> histogram){
		sortedHistogram.addAll(histogram.entrySet());
		Collections.sort(sortedHistogram, new HashMapHistogramComparator());
		iterator = sortedHistogram.iterator();
	}

	@Override
	public boolean hasNext() {
		return iterator.hasNext();
	}

	@Override
	public T next() {
		return iterator.next().getKey();
	}

	@Override
	public void remove() {
		throw new UnsupportedOperationException();
	}

	/**
	 * Comparator for HashMapHistogram, first by value(number of appearances)
	 * second by keys
	 * @author Raz
	 *
	 */
	private class HashMapHistogramComparator
			implements Comparator<HashMap.Entry<T, Integer>> {

		@Override
		public int compare(Entry<T, Integer> o1, Entry<T, Integer> o2) {
			if(o1.getValue() == o2.getValue()){
				return o1.getKey().compareTo(o2.getKey());
			}
			return o2.getValue() - o1.getValue();
		}

	}
}
