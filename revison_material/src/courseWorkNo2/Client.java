package sortedArrayListCoursework;

import java.util.Map;

/**
 * 
 * @author Gideon Bayisa
 *
 */
public class Client implements Comparable<Client> {

	private String firstName;
	private String lastName;
	Map<String, Integer> ticketInformation;

	/**
	 * 
	 * @param firstName
	 * @param lastName
	 * @param ticketmap
	 */
	public Client(String firstName, String lastName, Map<String, Integer> ticketmap) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.ticketInformation = ticketmap;
	}

	@Override
	public int compareTo(Client c) {
		int lnCmp = lastName.compareTo(c.lastName);
		if (lnCmp != 0)
			return lnCmp;
		int fnCmp = firstName.compareTo(c.firstName);
		if (fnCmp != 0)
			return fnCmp;
		else
			return 0;
	}

	public String getFirstName() {
		return firstName;
	}

	/**
	 * 
	 * @param firstName
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	/**
	 * 
	 * @param lastName
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Map<String, Integer> getTicketInformation() {
		return ticketInformation;
	}

	/**
	 * 
	 * @param ticketInformation
	 */
	public void setTicketInformation(Map<String, Integer> ticketInformation) {
		this.ticketInformation = ticketInformation;
	}

	@Override
	public String toString() {
		return firstName + " " + lastName + " " + ticketInformation;
	}

}