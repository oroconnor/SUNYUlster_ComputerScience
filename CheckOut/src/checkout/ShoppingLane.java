// Owen O'Connor
// CSC 201
// Assignment 13
// 10/29/21


package checkout;

/**
 * Checkout line class
 * @author owenoconnor
 * @since 10/29/21
 *
 */
public class ShoppingLane {
	private final int SECONDSPERITEM = 1; // Seconds for a single wash
	private int maxItem;
	private int numItems;

/**
* Initialize a Lane.

**/
public ShoppingLane()
{
  int secondsEachItem = SECONDSPERITEM;
  numItems = 0;
}
                 
/**
* Initialize a Lane.
* @param maxItem max number of items a lane can have
**/
public ShoppingLane(int m)
{
  int secondsEachItem = SECONDSPERITEM;
  maxItem = m;
  numItems = 0;
}


/**
* Determine whether this lane is currently busy.
* @param - none
* @return
*   <CODE>true</CODE> if this lane is busy
*   otherwise <CODE>false</CODE>
**/   
public boolean isBusy( )
{
 if (numItems > 0) return true;
 else
	 return false;
}


/**
* Reduce the remaining number of items by one
* @param - none
* <dt><b>Postcondition:</b><dd>
*   reduces number of items by one
**/
public void reduceItems( )
{
 if (numItems > 0) {
	 numItems--;
 }
 
} 

public void setItems(int n) {
	numItems = n;
}

public int getItems() {

	return numItems;
}

}