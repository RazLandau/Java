package il.ac.tau.cs.sw1.ex8.histogram;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

/**
 * HashMap implementation of Histogram
 * @author Raz
 *
 * @param <T>
 */
public class HashMapHistogram<T extends Comparable<T>>
		implements IHistogram<T> {

	HashMap<T, Integer> histogram = new HashMap<T, Integer>();

	@Override
	public void addItem(T item) {
		histogram.put(item,
				histogram.containsKey(item) ? histogram.get(item) + 1 : 1);
	}

	@Override
	public void addItemKTimes(T item, int k) throws IllegalKValue {
		if (k < 0) {
			throw new IllegalKValue(k);
		}
		while (k-- > 0) {
			addItem(item);
		}
	}

	@Override
	public int getCountForItem(T item) {
		Integer result = histogram.get(item);
		return result == null ? 0 : result;
	}

	@Override
	public void addAll(Collection<T> items) {
		for (T item : items) {
			addItem(item);
		}
	}

	@Override
	public void clear() {
		histogram.clear();
	}

	@Override
	public Set<T> getItemsSet() {
		return histogram.keySet();
	}

	@Override
	public Iterator<T> iterator() {
		return new HashMapHistogramIterator<T>(histogram);
	}
	
	/**
	 * 
	 * @return number of items in HashMapHistogram
	 */
	public int size(){
		return histogram.size();
	}
}
