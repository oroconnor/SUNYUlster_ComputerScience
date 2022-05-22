// Owen O'Connor
// CSC 205
// Final Project

package mysearch;

/**
 * Object storing data about a website
 * @author owenoconnor
 *
 */
public class Site {
	
	private String domain;
	private String description;
	
	/**
	 * Constructor
	 * @param domainname name of the website
	 * @param descript the description that will be stored with this website
	 */
	public Site(String domainname, String descript )
	{
	   
		domain = domainname;
	    description = descript;
	}
	
	/**
	 * Getter for the name of the website
	 * @return the name of the website
	 */
	public String getDomain() {
		return domain;
	}
	
	/**
	 * Getter for the description of the website
	 * @return the description of the website
	 */
	public String getDescription() {
		return description;
	}
	
	
}
