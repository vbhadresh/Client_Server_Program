package Connect;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Scanner;

import Client.socket_Client;
import Server.socket_Server;

public class connect {

	private static InetAddress ipAddress = null;
	private static int portNumber = 0;

	public static void main(String args[]) {

		try {
			ipAddress = InetAddress.getLocalHost();
			System.out.println("Local Machine InetAddress is "+ipAddress);
		} catch (UnknownHostException e) {
			new Exception("Failed in retrieving ipAddress" + e);
		}
		Scanner userInput = new Scanner(System.in);
		System.out.println("Please Enter the port Number : ");
		portNumber = userInput.nextInt();
		
		/*
		 * Establishing Server Connection
		 */
		socket_Server server = new socket_Server(portNumber, 2, ipAddress);

		/*
		 * Establishing Client Connection
		 */
		socket_Client client = new socket_Client(ipAddress, portNumber);
		server.accept();
		System.out.println("Please Select either of the 2 options as mentioned below "
				+ "\n 1. Perform Arthimetic operation \t 2. Get Client Count");
		switch (userInput.nextInt()) {
		case 1:
			client.performArthimeticOperations();
			server.performAction();
			break;
		case 2:
			System.out.println("Sending Request to Server to get count of Client's Connected to it..........");
			int clientCount = server.getClientCount();
			System.out.println("Number of Clients connected to Server are/is : " + clientCount);
			break;  
		default:
			System.err.println("Invalid Option");
			System.exit(0);
		}

		/*
		 * Closing Connections
		 */

		client.close();
		server.close();
		toDate();
	}
	
	public static void toDate() {
		String timeStamp = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(Calendar.getInstance().getTime());
		System.out.println(timeStamp + "\nProgrammed by Vaishnavi Bhadresh\n");
	}
	
}