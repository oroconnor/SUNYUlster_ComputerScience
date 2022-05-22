// Owen O'Connor
// CSC 201
// Assignment 13
// 10/29/21


package checkout;

/**
 * A Shopper represents one person who will wait on line and check out at grocery store. 
 * 
 */
public class Shopper {
	private String name;
	private int numItem;
	private int waitTime;
	private boolean atCheckout;
	
	/**
	* Initialize a Shopper.
	* @param name the shopper's name
	* @param numItem max number of items a lane can have
	* 
	**/
	public Shopper(String n, int ni)
	{
		name = n;
		numItem = ni;
	}
	
	public int getItems() {
		return numItem;
	}

	
	/**
	* Reduce the remaining number of items by one
	* @param - none
	* <dt><b>Postcondition:</b><dd>
	*   reduces number of items by one
	**/
	public void reduceItems( )
	{
	 if (numItem > 0) {
		 numItem--;
	 }
	 
	} 
	
	public void incrementWait( )
	{
		 waitTime++;
	 }

	public int getWaitTime() {
		return waitTime;
	}

	public void setWaitTime(int waitTime) {
		this.waitTime = waitTime;
	}

	public String getName() {
		return name;
	}
	
}
