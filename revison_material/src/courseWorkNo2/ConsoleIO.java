package courseWorkNo2;

import java.util.ArrayList;
import java.util.List;

public class ConsoleIO {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<Event> events = new ArrayList<Event>();

		copy(events, events);
		printList(events);

	}

	private static void copy(List<? extends Event> source, List<? super Event> destination) {

		for (Event event : source)
			destination.add(event);
		// TODO Auto-generated method stub

	}

	private static <E> void printList(List<E> a) {
		// TODO Auto-generated method stub
		for (E elem : a)
			System.out.println(elem + " ");
		System.out.println();
	}

}
