package com.seneca.server;

import java.net.*;
import java.io.*;

import com.seneca.accounts.*;
import com.seneca.business.*;
import com.btp400.*;

public class FinancialAppServer {
	
	public static void main(String[] args) {
		ServerSocket serverSocket; // TCP socket used for listening

		try {
			/*
			 * step 1: create a server socket port number: 5678
			 */

			serverSocket = new ServerSocket(5678);

			/*
			 * step 2: a loop that listens for connections and creates THREADS with sockets
			 */
			int count = 1;
			while (true) {
				System.out.println("Listening for a connection...");

				Socket socketConnection = serverSocket.accept(); // listen and wait
																	// TCP socket that is connected
																	// to the client
				System.out.println("start a thread for client #" + count);
				System.out.println("\t host name: " + socketConnection.getInetAddress().getHostName()
						+ "\t IP address: " + socketConnection.getInetAddress().getHostAddress());
				Thread t = new HandleClientThread(socketConnection);
				t.start();

				count++;
			}
		} 
		catch (IOException e) {
			e.printStackTrace();
		}

		System.out.println("*** the server is going to stop running ***");

	}
}// end main

	class HandleClientThread extends Thread {

		private Socket connection;

		public HandleClientThread(Socket sock) {
			connection = sock;
		}

		public void run() {

			try {
				/*
				 * connect input and output streams to the socket
				 */
				ObjectOutputStream oosToClient = new ObjectOutputStream(connection.getOutputStream());

				ObjectInputStream oisFromClient = new ObjectInputStream(connection.getInputStream());
				
				DataOutputStream dosToClient = new DataOutputStream(connection.getOutputStream());

				DataInputStream disFromClient = new DataInputStream(connection.getInputStream());

				System.out.println("I/O streams connected to the socket");

				/*
				 * exchange data with ONE client
				 */
				
				try {
					while (true) { // keep on getting data from the client

						// read data from ONE client
						int c = disFromClient.readInt();

						System.out.println("*** receive choice from the client: " + c);

						
						// send the data to THE client
//						dosToClient.writeDouble(area);
//						dosToClient.flush();

						// display the result to the screen
//						System.out.println("\t*** send area to the client: " + area);
					} // end while

				} catch (EOFException eof) {
					System.out.println("*** the CLIENT has terminated connection ***");
				}

				/*
				 * close the connection to the client
				 */
				dosToClient.close();
				disFromClient.close();
				connection.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			System.out.println("*** waiting for new connections ***");


		} // end run
		

	} // end HandleClientThread

	/*
	 * step 4: exchange objects with THE client
	 */
//	Account account;
//
//	try
//	{
//
//		while (true) { // keep on getting data from the client
//
//			// read an object from THE client
//			account = (Account) oisFromClient.readObject(); // casting!
//			String option = (String) oisFromClient.readObject();
//
//			// read data from THE client
//			double amount = disFromClient.readInt();
//
//			if (option.equals("deposit")) {
//				account.deposit(amount);
//			}
//
//			else if (option.equals("withdraw")) {
//				account.withdraw(amount);
//			}
//
//			else if (option.equals("quit")) {
//				oosToClient.close();
//				oisFromClient.close();
//				socketConnection.close();
//				System.out.println("*** THE client has terminated connection ***");
//			}
//
//			// send the object to THE client
//			oosToClient.writeObject(account);
//			oosToClient.flush();
//
//		} // end while
//
//	}
//
//	catch(
//	ClassNotFoundException cnf)
//	{
//		cnf.printStackTrace();
//	}
//	// thrown by readObject()
//
//	catch(
//	EOFException eof)
//	{
//		System.out.println("*** THE client has terminated connection ***");
//	}catch(
//	IOException e)
//	{
//		e.printStackTrace();
//	}

	/*
	 * step 5: close the connection to the client
	 */
//	oosToClient.close();oisFromClient.close();socketConnection.close();}catch(
//	IOException e)
//	{
//		e.printStackTrace();
//	}

	//System.out.println("*** the server is going to stop running ***");


