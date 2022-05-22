// Owen O'Connor
// CSC 205
// Final Project

package mysearch;

import java.util.ArrayList;

/**
 * Object storing a keyword, and which sites that keyword should link to 
 * @author owenoconnor
 *
 */
public class Keyword implements Comparable<Keyword> {

		private String name;
		private ArrayList<Site> weblinks = new ArrayList<Site>();
		
/**
 * Constructor
 * @param keyname the actual keyword
 */
		public Keyword(String keyname)
		{
			name = keyname;
			
		}
		 /**
		  * Getter for the actual keyword 
		  * @return the keyword (no extra spaces)
		  */
		public String getName() {
			return name.trim();
		}
		
		/**
		 * Getter for the list of sites that this keyword links to
		 * @return
		 */
		public ArrayList<Site> getWeblinks() {
			return weblinks;
		}
		
		/**
		 * Add additional sites for this keyword to link to
		 * @param s the site being added
		 */
		public void addLink(Site s) {
			weblinks.add(s);
		}
		
		/**
		 * Print function for the list of sites that this keyword links to
		 */
		public void printLinks() {
			for (Site i : weblinks) {
				System.out.printf("%s  %s%n", i.getDomain(), i.getDescription());
			}
		}
		
// Allow Keyword objects to be sorted for quicker search later
	@Override
		public int compareTo(Keyword o) {
			if (this.getName().compareToIgnoreCase(o.getName()) < 0) {
				return -1;
			}
			else if (this.getName().compareToIgnoreCase(o.getName()) > 0) {
				return 1;
			}
			else return 0;
		}

	
	
		}
		

