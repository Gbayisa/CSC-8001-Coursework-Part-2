package sortedArrayListCoursework;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * 
 * @author Gideon Bayisa
 *
 */
public class ConsoleIO {

	private static SortedArrayList<Event> listOfEvents = new SortedArrayList<>();
	private static SortedArrayList<Client> listOfClients = new SortedArrayList<>();

	/**
	 * 
	 * @param args
	 * @throws FileNotFoundException
	 */
	public static void main(String[] args) throws FileNotFoundException {

		inputEvents("List.txt");
		inputClients("List.txt");
		Scanner input = new Scanner(System.in);

		String option;

		do {
			InputData();
			option = input.next();

			switch (option) {

			case "e":
				System.out.println(listOfEvents);
				break;

			case "c":
				System.out.println(listOfClients);
				break;

			case "b":
				System.out.println("please enter the first name");
				String clientFirstName = input.next();
				System.out.println("please enter the last name");
				String clientLastName = input.next();

				int clientIndex = searchClient(clientFirstName, clientLastName);

				if (clientIndex == -1) {
					System.out.println("Client not found.");
				} else {
					System.out.println("Which event would you like to book?");
					String eventName = input.next();

					int eventIndex = searchEvent(eventName);
					if (eventIndex == -1) {
						System.out.println("Event not found");
					} else {
						System.out.println("How many Tickets would you like to book?");
						try {
							int noOfTicketsBooked = input.nextInt();
							buy(eventName, clientIndex, eventIndex, noOfTicketsBooked);
						} catch (Exception e) {
							System.out.println("Please input an Integer!");
						}

					}
				}
				break;
			case "r":
				System.out.println("r - to update the stored data when a registered client cancels/returns tickets.");

				System.out.println("please enter the first name");
				String firstName = input.next();
				System.out.println("please enter the last name");
				String lastName = input.next();

				int removeClientIndex = searchClient(firstName, lastName);

				if (removeClientIndex == -1) {
					System.out.println("Client not found.");
				} else {
					System.out.println("Which event would you like to cancel a ticket for?");
					String cancelEventName = input.next();

					int removeEventIndex = searchEvent(cancelEventName);
					if (removeEventIndex == -1) {
						System.out.println("Event not found");
					} else {
						System.out.println("How many tickets would you like to cancel?");
						try {
							int noOfTicketsCancelled = input.nextInt();
							remove(cancelEventName, removeClientIndex, removeEventIndex, noOfTicketsCancelled);
						} catch (Exception e) {
							System.out.println("please input an Integer!");
						}
					}
					break;
				}
			}
		} while (!option.equals("f"));
		input.close();

	}

	/**
	 * This method buys tickets for Clients for up to 3 events. If there are not
	 * enough tickets left, that information is printed to console, additionally a
	 * letter is printed as a text file. The sorted ArrayList of Clients tries to
	 * find the eventName key, and updates the amount of Tickets allocated to the
	 * Client accordingly. The sorted ArrayList of Events then sets a new value for
	 * the number of events left.
	 * 
	 * @param eventName
	 * @param clientIndex
	 * @param eventIndex
	 * @param ticketQuantity
	 * @throws FileNotFoundException
	 */
	public static void buy(String eventName, int clientIndex, int eventIndex, int ticketQuantity)
			throws FileNotFoundException {

		int updatedNoOfEvents = listOfEvents.get(eventIndex).getnoOfEvents() - ticketQuantity;

		if (updatedNoOfEvents < 0) {

			System.out.println("Not enough tickets left");
			PrintWriter letterFile = new PrintWriter("Letter.txt");
			letterFile.println("Dear " + listOfClients.get(clientIndex).getFirstName()
					+ ",\nWe regret to inform you that there are not enough tickets left for "
					+ listOfEvents.get(eventIndex).getEventName() + ".\nKind Regards,\nThe TicketOffice");
			letterFile.close();
		} else if (listOfClients.get(clientIndex).getTicketInformation().containsKey(eventName)) {

			int initialValue = listOfClients.get(clientIndex).getTicketInformation().get(eventName);
			int updatedValue = initialValue + ticketQuantity;
			listOfClients.get(clientIndex).getTicketInformation().replace(eventName, initialValue, updatedValue);
			listOfEvents.get(eventIndex).setnoOfEvents(updatedNoOfEvents);

		} else {
			if (listOfClients.get(clientIndex).getTicketInformation().size() == 3) {
				System.out.println("Max 3 events per person");

			} else {
				listOfClients.get(clientIndex).getTicketInformation().put(eventName, ticketQuantity);
				listOfEvents.get(eventIndex).setnoOfEvents(updatedNoOfEvents);
			}
		}
	}

	/**
	 * This method removes the allocated event for a specific Client. If the
	 * eventName key is found, the method removes the amount of tickets allocated to
	 * the specific Client, and reassigns the amount of tickets left. Additionally
	 * checks are in place to stop Clients refunding more tickets then they have
	 * bought.
	 * 
	 * @param removeEventName
	 * @param removeClientIndex
	 * @param removeEventIndex
	 * @param noOfTicketsCancelled
	 */
	public static void remove(String removeEventName, int removeClientIndex, int removeEventIndex,
			int noOfTicketsCancelled) {

		int updatedNoOfEvents = listOfEvents.get(removeEventIndex).getnoOfEvents() + noOfTicketsCancelled;

		if (listOfClients.get(removeClientIndex).getTicketInformation().containsKey(removeEventName)) {

			int initialValue = listOfClients.get(removeClientIndex).getTicketInformation().get(removeEventName);
			int updatedValue = initialValue - noOfTicketsCancelled;

			if (initialValue > noOfTicketsCancelled) {
				listOfClients.get(removeClientIndex).getTicketInformation().replace(removeEventName, initialValue,
						updatedValue);
				listOfEvents.get(removeEventIndex).setnoOfEvents(updatedNoOfEvents);

			} else if (initialValue == noOfTicketsCancelled) {
				listOfClients.get(removeClientIndex).getTicketInformation().remove(removeEventName);
				listOfEvents.get(removeEventIndex).setnoOfEvents(updatedNoOfEvents);
			}

			else {
				System.out.println("You have not bought that many tickets");
			}
		} else {
			System.out.println("The client has not bought this event");
		}

	}

	/**
	 * Counts number of events in the file. Takes the eventName and number of
	 * tickets and adds them to the sorted ArrayList of events.
	 * 
	 * @param fileName
	 * @return Adds events to SortedArrayList
	 * @throws FileNotFoundException
	 */
	private static List<Event> inputEvents(String fileName) throws FileNotFoundException {

		Scanner inFile = new Scanner(new FileReader(fileName));

		int number = inFile.nextInt();

		for (int i = 0; i < number; i++) {
			inFile.nextLine();
			String name = inFile.next();

			while (!inFile.hasNextInt()) {
				name = name + " " + inFile.next();
			}

			int tickets = inFile.nextInt();
			Event ev = new Event(name, tickets);

			listOfEvents.add(ev);
		}
		inFile.close();
		return listOfEvents;
	}

	/**
	 * Uses a skip line function, then counts number of clients in the file. Reads
	 * first and lastnames and adds them to the sorted ArrayList of clients.
	 * 
	 * @param fileName
	 * @return Adds Clients to Sorted ArrayList
	 * @throws FileNotFoundException
	 */
	private static List<Client> inputClients(String fileName) throws FileNotFoundException {

		Scanner inFile = new Scanner(new FileReader(fileName));

		int number = inFile.nextInt();
		skipLines(inFile, (number * 2) + 1);
		int customerCount = inFile.nextInt();

		for (int i = 0; i < customerCount; i++) {
			inFile.nextLine();

			String firstName = inFile.next();
			String lastName = inFile.next();
			Map<String, Integer> ticketEmpty = new HashMap<>();

			Client cl = new Client(firstName, lastName, ticketEmpty);

			listOfClients.add(cl);
		}

		inFile.close();
		return listOfClients;
	}

	/**
	 * Searches for a specific client in the sorted ArrayList of clients, then
	 * attempts to match client names.
	 * 
	 * @param firstName
	 * @param lastName
	 * @return Returns -1 if event is not found.
	 */
	private static int searchClient(String firstName, String lastName) {

		int index = -1;
		for (int i = 0; i < listOfClients.size(); i++) {
			if (listOfClients.get(i).getFirstName().equals(firstName)
					&& listOfClients.get(i).getLastName().equals(lastName)) {
				index = i;
			}
		}
		return index;
	}

	/**
	 * Searches for a specific event in the sorted ArrayList of events, then
	 * attempts to match event names.
	 * 
	 * @param eventName
	 * @return Returns -1 if event is not found.
	 */
	private static int searchEvent(String eventName) {

		int index = -1;
		for (int i = 0; i < listOfEvents.size(); i++) {
			if (listOfEvents.get(i).getEventName().equals(eventName)) {
				index = i;
			}
		}
		return index;
	}

	/**
	 * The method reads the integer value at the top of the the file and skips the
	 * according amount of lines.
	 * 
	 * @param inFile  Scanner
	 * @param lineNum
	 */
	private static void skipLines(Scanner inFile, int lineNum) {
		for (int i = 0; i < lineNum; i++) {
			if (inFile.hasNextLine())
				inFile.nextLine();
		}
	}

	/**
	 * Method to present user with 5 options on the console.
	 */
	public static void InputData() {
		System.out.println("f - to finish running the program.");
		System.out.println("e - to display on the screen the information about all the events.");
		System.out.println("c - to display on the screen the information about all the clients.");
		System.out.println("b - to update the stored data when tickets are bought by one of the registered clients.");
		System.out.println("r - to update the stored data when a registered client cancels/returns tickets.");
	}

}
