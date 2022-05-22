// Owen O'Connor
// CSC 205
// Assignment 23

package airplane;

import java.util.Scanner;

/**
 * Builds a graph and allows user to test whether there are airline routes between cities 
 * on the graph.
 * @author owenoconnor
 * @since 12/5/21
 */
public class Airline {

	static final int NY=1;
	static final int MIAMI=2;
	static final int CHICAGO=3;
	static final int LA=4;
	static final int DENVER=5;
	static final int SAN_FRANCISCO=6;
	static final int DALLAS=7;
	static final int SAN_DIEGO=0;
	
	static boolean route = false;
	
	public static void main(String[] args) {

		/// Build the Graph ------------------------------------
		
		Graph usa = new Graph(8);  // build a graph with 8 cities
		
		usa.setLabel(NY,"New York");
		usa.setLabel(MIAMI,"Miami");
		usa.setLabel(CHICAGO,"Chicago");
		usa.setLabel(LA,"Los Angeles");
		usa.setLabel(DENVER,"Denver");
		usa.setLabel(SAN_FRANCISCO,"San Francisco");
		usa.setLabel(DALLAS,"Dallas");
		usa.setLabel(SAN_DIEGO,"San Diego");
	
		
		usa.addEdge(NY, MIAMI);
		usa.addEdge(NY, CHICAGO);
		usa.addEdge(NY, DENVER);
		usa.addEdge(NY, DALLAS);
		usa.addEdge(MIAMI, DALLAS);
		usa.addEdge(DALLAS, LA);
		usa.addEdge(DALLAS, SAN_DIEGO);
		usa.addEdge(SAN_DIEGO, LA);
		usa.addEdge(CHICAGO, SAN_FRANCISCO);
		usa.addEdge(CHICAGO, DENVER);
		usa.addEdge(DENVER, SAN_FRANCISCO);
		usa.addEdge(DENVER, LA);
		usa.addEdge(SAN_FRANCISCO, LA);
		

		/// User program ------------------------------------
	while (true) {	 
		Scanner input = new Scanner(System.in);
		
		System.out.println("Enter a source and a destination city from the menu.");
		System.out.println("I will tell you if there is a route to get from the source to the destination.");
		System.out.printf("San Diego = 0%nNew York = 1%nMiami = 2%nChicago = 3%nLos Angeles = 4%nDenver = 5%nSan Francisco = 6%nDallas = 7%nEXIT = -1%n");
		System.out.println("Enter the Starting City:");
		int start = input.nextInt();
		if (start == -1) break;
		System.out.println("Enter the Destination City:");
		int destination = input.nextInt();
		if (destination == -1) break;
		
		checkCheck(usa, start, destination);
		
	}
		System.out.println( "Goodbye!");	
		
		
	} // End Main
	
	/**
	 * Checks whether there is a route between two cities and prints the result.
	 * @param g the graph
	 * @param start int code for the starting city
	 * @param destination int code for the destination city
	 */
	public static void checkCheck(Graph g, int start, int destination) {
		g.checkRoute(g, start, destination);
		if (route) {
			System.out.printf("There is a route from %s to %s.%n", g.getLabel(start), g.getLabel(destination));
		}
		else {
			System.out.printf("There are no routes from %s to %s.%n", g.getLabel(start), g.getLabel(destination));
		}
		System.out.println();
		route = false;
	}
	

}