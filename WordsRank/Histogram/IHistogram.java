package il.ac.tau.cs.sw1.ex8.histogram;

import java.util.Collection;
import java.util.Set;

/**
 * Data structure that hold number of appearances for each item
 * @author Raz
 *
 * @param <T>
 */
public interface IHistogram<T> extends Iterable<T>{
	
	/**
	 * Adds 1 count of item to Histogram
	 * @param item
	 */
	public void addItem(T item);
	
	/**
	 * Add k counts of item to Histogram
	 * @param item
	 * @param k
	 * @throws IllegalKValue
	 */
	public void addItemKTimes(T item, int k) throws IllegalKValue;
	
	/**
	 * 
	 * @param item
	 * @return number of counts of item in Histogram
	 */
	public int getCountForItem(T item);
	
	/**
	 * Add full collection to Histogram
	 * @param items
	 */
	public void addAll(Collection<T> items);
	
	/**
	 * Clear Histogram completely
	 */
	public void clear();
	
	/**
	 * 
	 * @return all items in Histogram
	 */
	public Set<T> getItemsSet();
}
