package Client;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

import Server.socket_Server;

public class socket_Client {
	
	Socket client_Socket= null;
	private static int numofInputs=0;
	
	public socket_Client(InetAddress ipAddress,int portNumber){
			try {
				client_Socket= new Socket(ipAddress, portNumber);
				System.out.println("\nSuccessfully Established connection with Client");
				System.out.println("\nSending Request to Server...... ");
			} catch (IOException e) {
				new Exception("Failed in establishing connection to client "+e);
			}
		}
	
	public void performArthimeticOperations(){
		try {
			PrintWriter output = new PrintWriter(client_Socket.getOutputStream(), true);
			Scanner userEntry = new Scanner(System.in);
			int[] inputValue;
			
			boolean result=false;
			 do {
				 try{
					 System.out.println("\nPlease enter the number of inputs you want to perform Arthimetic Operation");
					 numofInputs=userEntry.nextInt();
				 }
				 catch(Exception e){
					 System.err.println("Please enter Integer Numbers only");
					 System.exit(0);
				 }
				 inputValue = new int[numofInputs];
				 for(int i=0;i<numofInputs;i++){ 
					 System.out.println("Enter the input "+ (i+1));
					 inputValue[i]=userEntry.nextInt();
					 output.println(inputValue[i]);
				 }
				 inputValue=null;
			 }while (inputValue!=null);
		} catch (IOException e) {
			new Exception("Failed to input values in Client"+e);
		}
		}
        
	
	public void close(){
		try {
			client_Socket.close();
		} catch (IOException e) {
			new Exception("Failed to close Client Socket Connection "+ e);
		}
	}
	
	public static int getNumberofInputs(){
		return numofInputs;
	}
	
}

