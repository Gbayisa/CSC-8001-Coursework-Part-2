package courseWorkNo2;

import java.util.ArrayList;

public class SortedArrayList<E extends Comparable<E>> extends ArrayList<E> {

	public SortedArrayList() {
		super();
	}

	@Override
	public boolean add(E e) {
		int i = this.size() - 1;

		for (i = 0; i < size(); i++) {

			// if (this.get(i).compareTo(get((int) e)) < 0 ) {
			super.add(e);
			if (e.compareTo(get(i)) < 0) {

			}

		}
		return true;

	}
}

//        while (i > 0) {
//            parent = this.getParentIndex(i);
//
//            if (this.get(i).compareTo(this.get(parent)) < 0) {
//                this.swap(i, parent);
//            } else {
//                break;
//            }
//        }
//        return true;
//    }

//	ArrayList<T> list = new ArrayList<>();
//
//	public void addElement(Comparable<String> string) {
//		list.add((T) string);
//	}
//
//	public void removeElement(T element) {
//		list.remove(element);
//	}
//
//	public String toString() {
//		return list.toString();
//	}
//
//	public T get(int i) {
//		// TODO Auto-generated method stub
//		return list.get(i);
//	}
//
//	@Override
//	public int compareTo(T o) {
//		// TODO Auto-generated method stub
//		return 0;
//	}
//}
/// if id is equal - return 0
// if id is less than value - return -1
// if id is more than value - return 1