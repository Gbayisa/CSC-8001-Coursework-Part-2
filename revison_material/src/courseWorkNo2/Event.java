package courseWorkNo2;

import java.util.ArrayList;

public class Event implements Comparable<Event> {

	private int noOfEvents;
	private String eventInfo;

	ArrayList<Event> events = new ArrayList<Event>();

	public Event(int noOfEvents, String eventInfo) {
		super();
		this.noOfEvents = noOfEvents;
		this.eventInfo = eventInfo;
	}

	public void addToListEvent(Event newEvent) {
		events.add(newEvent);
	}

	public int getnoOfEvents() {
		return noOfEvents;
	}

	public void setnoOfEvents(int noOfEvents) {
		this.noOfEvents = noOfEvents;
	}

	public String getEventInfo() {
		return eventInfo;
	}

	public void setEventInfo(String eventInfo) {
		this.eventInfo = eventInfo;
	}

	public int compareTo1(Event e) {
		int lnCmp = eventInfo.compareTo(e.eventInfo);
		if (lnCmp != 0)
			return lnCmp;
		else
			return 0;

	}

	@Override
	public int compareTo(Event that) {
		// TODO Auto-generated method stub
		return Integer.compare(this.noOfEvents, that.noOfEvents);
	}

	public String toString() {
		return noOfEvents + " " + eventInfo;
	}

}