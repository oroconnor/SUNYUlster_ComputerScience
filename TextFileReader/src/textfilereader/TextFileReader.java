package textfilereader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileInputStream;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.StringTokenizer;

public class TextFileReader {
	
	public static void main(String[] args) {		// TODO Auto-generated method stub
		
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
				 String[] tokens=str.split(",");
				 if (tokens.length>2)
				    {
				    webSiteURL = tokens[0];
				    webSiteDescription = tokens[1];
				    System.out.printf("%nSite %s%n",webSiteURL);
				    System.out.printf("Description %s%n",webSiteDescription);
				    System.out.println("Key Words:");
				    for (int i=2;i<tokens.length; i++)
					   System.out.printf("%s ",tokens[i]);
			       }
			 }
		}
	    catch (IOException ioException){
	    	System.out.println("Unable to read test file ");
	    }
		
		
		// keep reading each line of the text file
		/* while (input.hasNext()){
			webSiteURL = input.readLine();
			webSiteDescription = input.next();
			System.out.printf("Site %s%n",webSiteURL);
			System.out.printf("Description %s%n",webSiteDescription);
			// Parse out the Key Words:
			System.out.println("Key Words:");
			StringTokenizer st2 = new StringTokenizer(input.next(), ",");

			while (st2.hasMoreElements()) {
				System.out.println(st2.nextElement());
			}
		} */

	}
}