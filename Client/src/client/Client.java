//CSC180 - Owen O'Connor
// Just built and tested. Worked on my computer. Added javadoc


package client;

import java.io.*;
import java.net.*;

/** @author owenoconnor
 *  code provided by Professor John Sheehan
 *  @since 04/22/21
 *  Java Program allows client to connect to server and display a message */
public class Client {
	
	private static Socket client;
	private static ObjectInputStream input;
	private static ObjectOutputStream output;
	/** 
	 * Main program for client side
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
		connectToServer();
		sendData();
		processConnection();
		client.close();
		}
		catch (IOException ex) {
    		ex.printStackTrace();
    	}
	}
	
	/**
	 * connects to server
	 */
    public static void connectToServer() {
    	try {
    	//client = new Socket(InetAddress.getByName("localhost"), 12345);
            //found my computer's IP address: "192.168.1.5" So this is where I'd put it if I had another
            //computer to test it on
        client = new Socket("127.0.0.1", 12345);

    	}
    	catch (IOException ex) {
    		ex.printStackTrace();
    	}
    }
    
    /**
     * transmits the message
     */
    public static void sendData()  {
    	// put any text we write on the network into a stream
    	try {
    	output = new ObjectOutputStream(client.getOutputStream());
      	output.writeObject("CLIENT: Hello World from Owen!"); // changed name
    	output.flush(); // sends the message
    	}
    	catch (IOException ex) {
    		ex.printStackTrace();
    	}
    }
    /**
     * get a message and print it
     */
    public static void processConnection() {
    ;
    	try {
    	   input = new ObjectInputStream(client.getInputStream());
    	   String message = (String) input.readObject();
    	   System.out.printf("%s\n",message);
    	}
    	catch (IOException ex) {
    		ex.printStackTrace();
    	} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    	
}

