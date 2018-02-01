package Server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

import Client.socket_Client;

public class socket_Server {

	private static ServerSocket server_Socket = null;
	private static Socket socket = null;
	private static int count = 0;
	private InetAddress ipAddress = null;
	private int portNo=0;

	public socket_Server(int portNumber, int maxQueue, InetAddress localAddress) {
		try {
			if (portNumber != 0) {
				this.ipAddress=localAddress;
				this.portNo=portNumber;
				server_Socket = new ServerSocket(portNumber, maxQueue, localAddress);
				System.out.println("\nSuccessfully Established connection with Server");
				System.out.println("\nWaiting fto listen from CLient...... ");
			} else {
				System.err.println("\nCannot Connect to client with Port "+portNumber);
				System.exit(0);
			}
		} catch (IOException e) {
			new Exception("Failed in establishing connection to server " + e);
		}
	}

	public void accept() {
		try {
			socket = server_Socket.accept();
			System.out.println("\nSuccessfuly accepted Client Request of Ip Address " + ipAddress);
			System.out.println("\nListening for connection on port..... "+ portNo);
			count++;
		} catch (IOException e) {
			new Exception("Failed to accept Server Connection " + e);
		}
	}

	public void performAction() {

		Scanner input;
		ArrayList<Integer> integer = new ArrayList<>();
		try {
			input = new Scanner(socket.getInputStream());
			PrintWriter output = new PrintWriter(socket.getOutputStream(), true);
			if (input.hasNextInt()) {
				for (int i = 0; i < socket_Client.getNumberofInputs(); i++) {
					integer.add(input.nextInt());
				}
				selectOperator(integer);

			} else {
				System.err.println(" Error in Calculating");
				System.exit(0);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void selectOperator(ArrayList<Integer> integer) {
		int result = 0,input = 0;
		Scanner userInput = new Scanner(System.in);
		do{
		System.out.println("\nPlease Select the Arthitemetic Operation you want to perform");
		System.out.println("\n1. Addition 2. Subtraction 3. Multiplication 4. Division 5.Modulus");
		try{
			input=userInput.nextInt();
		}
		catch(Exception e){
			System.err.println("Please enter Specified Integer Numbers only");
			System.exit(0);
		}
			switch (input) {
		case 1:
			System.out.println("Selected Operation is Addition");
			for (int i = 0; i < socket_Client.getNumberofInputs() - 1; i++) {
				if (i > 0) {
					result = result + integer.get(i + 1);
				} else {
					result = integer.get(i) + integer.get(i + 1);
				}
			}
			System.out.println("Result is " + result);
			break;
		case 2:
			System.out.println("Selected Operation is Subtraction");
			for (int i = 0; i < socket_Client.getNumberofInputs() - 1; i++) {
				if (i > 0) {
					result = result - integer.get(i + 1);
				} else {
					result = integer.get(i) - integer.get(i + 1);
				}
			}
			System.out.println("Subtraction Result is " + result);
			break;
		case 3:
			System.out.println("Selected Operation is Addition");
			for (int i = 0; i < socket_Client.getNumberofInputs() - 1; i++) {
				if (i > 0) {
					result = result * integer.get(i + 1);
				} else {
					result = integer.get(i) * integer.get(i + 1);
				}
			}
			System.out.println("Multiplication Result is " + result);
			break;
		case 4:
			System.out.println("Selected Operation is Division");
			for (int i = 0; i < socket_Client.getNumberofInputs() - 1; i++) {
				if (i > 0) {
					result = result / integer.get(i + 1);
				} else {
					result = integer.get(i) / integer.get(i + 1);
				}
			}
			System.out.println("Result is " + result);
			break;
		case 5:
			System.out.println("Selected Operation is Modulus");
			for (int i = 0; i < socket_Client.getNumberofInputs() - 1; i++) {
				if (i > 0) {
					result = result % integer.get(i + 1);
				} else {
					result = integer.get(i) % integer.get(i + 1);
				}
			}
			System.out.println("Result is " + result);
			break;
		default:
			System.out.println("End of Arithemetic Operations");
			break;
		}
		}while(result>0);
	}

	public static int getClientCount() {
		return count;
	}

	public void close() {
		try {
			server_Socket.close();
			count--;
		} catch (IOException e) {
			new Exception("Failed to close Server Socket Connection " + e);
		}
	}
	
}
