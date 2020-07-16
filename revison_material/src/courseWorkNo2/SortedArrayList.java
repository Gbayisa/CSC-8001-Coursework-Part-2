package sortedArrayListCoursework;

import java.util.ArrayList;

/**
 * 
 * @author Gideon Bayisa
 *
 * @param <E>
 */
public class SortedArrayList<E extends Comparable<E>> extends ArrayList<E> {
	/**
	 * Generic Sorted ArrayList class that utilises the inherited compare to
	 * functionality.
	 */
	public SortedArrayList() {
		super();

	}

	@Override
	public boolean add(E e) {

		for (int i = 0; i < size(); i++) {
			if ((e).compareTo(get(i)) < 0) {
				super.add(i, e);
				return true;
			}
		}
		return super.add(e);
	}

}
