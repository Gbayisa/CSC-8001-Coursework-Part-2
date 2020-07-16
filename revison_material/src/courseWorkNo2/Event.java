package sortedArrayListCoursework;

/**
 * 
 * @author Gideon Bayisa
 *
 */
public class Event implements Comparable<Event> {

	private String eventName;
	private int noOfTickets;

	/**
	 * 
	 * @param eventName
	 * @param noOfTickets
	 */
	public Event(String eventName, int noOfTickets) {
		super();
		this.eventName = eventName;
		this.noOfTickets = noOfTickets;
	}

	@Override
	public int compareTo(Event e) {
		int lnCmp = eventName.compareTo(e.eventName);
		if (lnCmp != 0)
			return lnCmp;
		else
			return 0;
	}

	public int getnoOfEvents() {
		return noOfTickets;
	}

	/**
	 * 
	 * @param noOfEvents
	 */
	public void setnoOfEvents(int noOfEvents) {
		this.noOfTickets = noOfEvents;
	}

	public String getEventName() {
		return eventName;
	}

	/**
	 * 
	 * @param eventName
	 */
	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	@Override
	public String toString() {
		return eventName + " " + noOfTickets;
	}

}