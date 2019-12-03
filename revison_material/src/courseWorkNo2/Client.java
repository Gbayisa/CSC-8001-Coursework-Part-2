package courseWorkNo2;

import java.util.HashMap;

public class Client implements Comparable<Client> {

	private int noOfRegisteredClients;
	private String firstName;
	private String lastName;

	// ArrayList<Client> clients = new ArrayList<Client>();

	HashMap<String, Integer> client = new HashMap<String, Integer>();

	public Client(int noOfRegisteredClients, String firstName, String lastName) {
		super();
		this.noOfRegisteredClients = noOfRegisteredClients;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public int getNoOfRegisteredClients() {
		return noOfRegisteredClients;
	}

	public void setNoOfRegisteredClients(int noOfRegisteredClients) {
		this.noOfRegisteredClients = noOfRegisteredClients;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

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

//	public int compareTo1(Client that) {
//		// TODO Auto-generated method stub
//		return Integer.compare(this.noOfRegisteredClients, that.noOfRegisteredClients);
//
//	}

	public String toString() {
		return noOfRegisteredClients + " " + firstName;
	}

}