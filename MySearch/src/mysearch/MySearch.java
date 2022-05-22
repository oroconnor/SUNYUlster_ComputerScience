// Owen O'Connor
// CSC 205
// Final Project

package mysearch;

import java.util.ArrayList;
import java.util.Collections;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileInputStream;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.StringTokenizer;


public class MySearch {
	/** Reads in text file and allows user to search based on keywords
	 *	@author owenoconnor
	 * @param args
	 */
	public static void main(String[] args) {
	
		// Place to store the websites
		ArrayList<Site> siteArray = new ArrayList<Site>();
		
		// Place to store the keywords
		ArrayList<Keyword> keywordArray = new ArrayList<Keyword>();
		
		// Place to store keywords as they are being inputed
		ArrayList<String> tempkeys = new ArrayList<String>();
		int cursor = 0;
		
		// -------------- 	Read in file and index					 	--------------
		
BufferedReader input;
		
		try{
			 input = new BufferedReader(new FileReader("TestFile.txt"));
		}
		catch(FileNotFoundException fileNotFoundException)
		{
			System.out.println("Unable to read test file");
			return;
		}	 
	
		try{
			 String webSiteURL;
			 String webSiteDescription;
			 String[] webSiteKeywords = new String[20];
			 String str;
			 while ((str = input.readLine()) != null) {
				 String[] tokens=str.split(", ");
				 for (int i=0; i<tokens.length;i++) {
					 tokens[i].trim();
				 }
				 if (tokens.length>2)
				    {
					 
				    webSiteURL = tokens[0];
				    webSiteDescription = tokens[1];
				    siteArray.add(new Site(webSiteURL, webSiteDescription));
				    for (int i=2;i<tokens.length; i++)
				    	tempkeys.add(tokens[i]);
					for (String i : tempkeys) {
						// make new keyword object with each string keyword	
						Keyword newkeyword = new Keyword(i);
						int search = binarySearch(keywordArray, 0, keywordArray.size(), i);

						if (search == -1) {
							// create weblink to the website from that keyword
							newkeyword.addLink(siteArray.get(cursor));
							keywordArray.add(newkeyword);
						}
						else {
							// add weblink to preexisting keyword
							keywordArray.get(search).addLink(siteArray.get(cursor));
						}
						}
	
			       }		 
				 cursor++;
				 tempkeys.clear();
				Collections.sort(keywordArray);
			 }
			 Collections.sort(keywordArray);
		} // End try block
	    catch (IOException ioException){
	    	System.out.println("Unable to read test file ");
	    }
		
		
		// -------------- 	User Input					 	--------------
	
	
		
		
		System.out.println("Welecome to MySearch. Note: Keywords are case sensitive!");
		
		System.out.println("Enter your Query:");
		
		Scanner scanin = new Scanner(System.in);
		   
		String userinput = scanin.nextLine();

		//split the sentence into array of words
		String [] token = userinput.split(" ");
		 for (int i=0; i<token.length;i++) {
			 token[i].trim();
		 }
		
		
		
		// -------------- 	Search and return [retrieval] 			--------------
		
		System.out.println("Results:");

		if (token.length == 1) {
			// search if there is only one keyword entered
			int index = binarySearch(keywordArray,  0, keywordArray.size(), userinput);
			if (index == -1) {
				System.out.println("Sorry. No keywords found that match.");
			}
			else {
			keywordArray.get(index).printLinks();
			}
		}
		else if (token.length > 3) {
				System.out.println("Sorry. Can only take search queries of three words or less.");
		
		}
		else if (token[1].equals("AND")) {
			// search with AND operator
			int index1 = binarySearch(keywordArray, 0, keywordArray.size(), token[0]);
			int index2 = binarySearch(keywordArray,  0, keywordArray.size(),token[2]);
			
			if ((index1 == -1) || (index2 == -1)) {
				System.out.println("Sorry. Both keywords were not found.");
			}
			else {
			ArrayList<Site> a = keywordArray.get(index1).getWeblinks(); // get weblinks from first keyword
			ArrayList<Site> b = keywordArray.get(index2).getWeblinks();// get weblinks from second keyword		
			a.retainAll(b);
			for (Site i : a) {
				System.out.printf("%s  %s%n",i.getDomain(),i.getDescription());
				}
			}
			
		}
		else if (token[1].equals("OR")) {
			// search with OR operator
			int index1 = binarySearch(keywordArray, 0, keywordArray.size(), token[0]);
			int index2 = binarySearch(keywordArray, 0, keywordArray.size(), token[2]);
			
			if ((index1 == -1) & (index2 == -1)) {
				System.out.println("Sorry. No keywords found that match.");
			}
			else {
				
				ArrayList<Site> a = null;
				ArrayList<Site> b = null;
				
				if (index1 != -1) {
					 a = keywordArray.get(index1).getWeblinks(); // get weblinks from first keyword
				}
				if (index2 != -1) {
					b = keywordArray.get(index2).getWeblinks();// get weblinks from second keyword		
				}
				//b.removeAll(a);
				a.addAll(b);
				for (Site i : a) {
					System.out.printf("%s  %s%n",i.getDomain(),i.getDescription());
				}
			}
		}
		else {
			System.out.println("Sorry that search was not entered in a valid format.");
		}
	}

/**
 * Binary Search 
 * @param a the arraylist of keywords to search
 * @param first 
 * @param size total number of keywords to search
 * @param target what you are search for
 * @return either -1 for not found or an int of the index at which target is found
 */
	 public static int binarySearch(ArrayList<Keyword> a,int first, int size, String target)
	   {
		 int middle; 
	      if (size <= 0)
	         return -1;
	      else
	      {
	         middle = first + size/2;
	         if (target.equals(a.get(middle).getName()))
	            return middle;
	         else if (target.compareToIgnoreCase(a.get(middle).getName()) < 0)
	            // The target is less than a[middle], so search before the middle.
	            return binarySearch(a, first, size/2, target);
	         else 
	            // The target must be greater than a[middle], so search after the middle.
	            return binarySearch(a, middle+1, (size-1)/2, target);
	      } 
	   }
	

	 
}