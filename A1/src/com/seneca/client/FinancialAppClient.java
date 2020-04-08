package com.seneca.client;
import java.net.*;
import java.util.Scanner;
import java.io.*;
import java.util.*;

import com.seneca.accounts.*;
import com.seneca.business.*;
import com.btp400.*;

public class FinancialAppClient {
	static Bank bank = new Bank();
	public static void main(String[] args) {
		Socket clientSocket;		// TCP/IP socket

		try {

	             /* step 1: connect to the server
	                        IP address/server name: "localhost"
	                        port number:            8000
	              */

		     clientSocket = new Socket( InetAddress.getByName( "localhost" ), 5678 );

	                                      // InetAddress.getByName( "127.0.0.1" )

		     System.out.println( "Connected to " +
			                 clientSocket.getInetAddress().getHostName() );

		         /* step 2: connect input and output streams to the socket
	              */

		     ObjectInputStream  oisFromServer= new ObjectInputStream( 
                     clientSocket.getInputStream() );

		     ObjectOutputStream oosToServer = new ObjectOutputStream( 
                    clientSocket.getOutputStream() );    
		     
		     DataOutputStream dosToServer = new DataOutputStream(
	                                                clientSocket.getOutputStream() );

		     DataInputStream  disFromServer= new DataInputStream(
	                                                 clientSocket.getInputStream() );

		     System.out.println( "I/O streams connected to the socket" );


		     /* step 3: exchange data with the server
	              */
	         /* BufferedReader keyboard = new BufferedReader(
	                                           new InputStreamReader( System.in ) ); */

	         //Scanner keyboard = new Scanner( System.in );
	         
		     while(true) {

					try {
						FinancialApp.loadBank();
						FinancialApp.displayMenu(bank.getName());
						int c = FinancialApp.menuChoice();
						dosToServer.writeInt(c);
						dosToServer.flush();
						
						while (c != 7) {		
							if (c == 1) {
								FinancialApp.openAnAccount();
							} else if (c == 2) {
								FinancialApp.closeAnAccount();
							} else if (c == 3) {
								FinancialApp.depositMoney();
							} else if (c == 4) {
								FinancialApp.withdrawMoney();
							} else if (c == 5) {
								FinancialApp.displayAccounts();
							} else if (c == 6) {
								FinancialApp.displayATaxStatement();
							}	
							FinancialApp.displayMenu(bank.getName());
							c = FinancialApp.menuChoice();
							dosToServer.writeInt(c);
							dosToServer.flush();
						}
						FinancialApp.exitApp();
						
						   System.out.println( "Client: data exchange completed" );

						     /* step 4: close the connection to the server
					              */
						     System.out.println( "Client: closing the connection..." );

						     dosToServer.close();
						     disFromServer.close();
						     clientSocket.close();
						     return;
					}
					catch( EOFException eof ) { // loss of connection

				    System.out.println( "The server has terminated connection!" ); }

					catch(IOException e ) { System.out.println( "I/O errors in data exchange" );
	                                        e.printStackTrace(); 								}
		     }
	             // System.out.println( "Client: data exchange completed" );

		     /* step 4: close the connection to the server
	              */
//		     System.out.println( "Client: closing the connection..." );
//
//		     dosToServer.close();
//		     disFromServer.close();
//		     clientSocket.close();
		}
	    catch( IOException ioe ) { System.out.println( "I/O errors in socket connection" );
	                               ioe.printStackTrace(); }

	    System.out.println( "... the client is going to stop running..." );

	   } // end main
	}


