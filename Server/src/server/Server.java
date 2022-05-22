//CSC180 - Owen O'Connor
// Just built and tested. Worked on my computer. Added javadoc

package server;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;


/** @author owenoconnor
 *  code provided by Professor John Sheehan
 *  @since 04/22/21
 *  Java Program allows client to connect to server and transmit a message */
public class Server {
	
	private static ServerSocket server;
	private static Socket connection;
	private static ObjectInputStream input;
	private static ObjectOutputStream output;

	/** 
	 * Main program for server side
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		try
		{
			server = new ServerSocket(12345, 100); //create socket
	    
			while (true)
			{
				waitForConnection();
				getStreams();
				processConnection();
			}
		}
		catch (EOFException eofException)
			{
		    System.out.printf("\nServer terminated connection");
			}
		catch (IOException ioException)
		{
			ioException.printStackTrace();
		}
	}
	
	/** 
	 * server waits for connection
	 * @throws IOException
	 */
	private static void waitForConnection() throws IOException
	{
		System.out.printf("Waiting for Connection");
		connection = server.accept();
		System.out.printf("Connection Received from %s\n",connection.getInetAddress().getHostName());
	}
	
	/**
	 * get input and output streams
	 * @throws IOException
	 */
	private static void getStreams() throws IOException
	{
	   output = new ObjectOutputStream(connection.getOutputStream());
	   output.flush();
	   
	   System.out.printf("Message Received!\n");
	}
	
	/**
	 * get a message and print it
	 * @throws IOException
	 */
	private static void processConnection() throws IOException
	{
	   String message = "Connection successful!";
	   input = new ObjectInputStream(connection.getInputStream());
    	do
    	{
    		try
    		{
    			message = (String) input.readObject();
    			System.out.printf("%s\n",message);
    		}
    		catch (ClassNotFoundException classNotFoundException)
    		{
    			System.out.printf("Unknown Message Type\n");
    		}
    	} while (!message.equals("CLIENT>>> TERMINATE:"));
	}
}