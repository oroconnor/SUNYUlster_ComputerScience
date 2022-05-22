//Owen O'Connor - CSC180 - Assignment 6

package addressbooks;

import java.io.Serializable;

/** @author owenoconnor
*  @since 03/05/21
*  Address class for storing contact info. For use in AddressBookS.java */
public class Address implements Serializable {
	private String lastName;
	private String firstName;
	private String phoneNumber;
	private String emailAddress;
	
	/**initializes with empty default values */
	public Address() {
		this("","","","");
	}
	
	/**initializes with provided values
	 * @param lastName Last name of contact
	 * @param firstName First name of contact
	 * @param phoneNumber Phone number of contact
	 * @param emailAddress Email Address of contact
	 *  */
	public Address(String lastName, String firstName, String phoneNumber, String emailAddress) {
		this.setLastName(lastName);
		this.setFirstName(firstName);
		this.setPhoneNumber(phoneNumber);
		this.setEmailAddress(emailAddress);
	}

	/** getter for Last Name*/
	public String getLastName() {
		return lastName;
	}

	/** setter for Last Name*/
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/** getter for First Name*/
	public String getFirstName() {
		return firstName;
	}
	
	/** setter for First Name*/
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	/** getter for Phone Number*/
	public String getPhoneNumber() {
		return phoneNumber;
	}
	
	/** setter for Phone Number*/
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	/** getter for Email Address*/
	public String getEmailAddress() {
		return emailAddress;
	}

	/** setter for Email Address*/
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	
	
	
}
