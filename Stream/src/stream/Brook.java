//CSC180 - Final Project - Owen O'Connor

package stream;

import java.util.*;
import java.lang.Math;

/** @author owenoconnor
 *  @since 04/15/21
 *  Java Object that stores Stream data and performs statistical calculation */
public class Brook {
	private String name;
	private ArrayList<Double> index;
	private ArrayList<Double> temp;
	private ArrayList<Double> turbidity;
	private List<Double> summary;
	
	/**
	 * Constructor: an instance that stores data on a particular stream
	 * @param name name of stream as labeled in filename
	 * @param index unique index of observations	
	 * @param temp monthly temperature observations
	 * @param turbidity monthly turbidity observations
	 */
	public Brook(String name, ArrayList<Double> index, ArrayList<Double> temp, ArrayList<Double> turbidity) {
		this.setName(name);
		this.setIndex(index);
		this.setTemp(temp);
		this.setTurbidity(turbidity);
		this.setSummary();
	}

	/**
	 * turbidity setter
	 * @param turbidity
	 */
	private void setTurbidity(ArrayList<Double> turbidity) {
		this.turbidity = turbidity;
	}
	
	/**
	 * turbidity getter
	 * @return
	 */
	public ArrayList<Double> getTurbidity() {
		return turbidity;
	}

	/**
	 * Calculates average for summary statistics
	 * @param list
	 * @return
	 */
	public static double avg(ArrayList<Double> list) {
		     double sum = 0; 

		     for (int i = 0; i < list.size(); i++)
		         sum = sum + list.get(i);
		     return sum / list.size();
		}

	/**
	 * Calculates mode for summary statistics
	 * @param list
	 * @return
	 */
	public static double mode(ArrayList<Double> list) {
		/* Credit: inspiration from Chandu yadav, TutorialsPoint.com
		 Altered and expanded to test for multi-modal */
		      int maxCount = 0;
		      double maxValue = 0;
		      boolean multimodal = false;

		      for (int i = 0; i < list.size(); ++i) {
		         int count = 0;
		         for (int j = 0; j < list.size(); ++j) {
		            if (list.get(i).equals(list.get(j))) {
		            ++count;
		            }
		         }

		         if (count > maxCount) {
		            maxCount = count;
		            maxValue = (double) list.get(i);
		            multimodal = false;
		         }
		         if (count == maxCount && !list.get(i).equals(maxValue))  {
		        	multimodal = true; // Flag if there is more than one mode
		         }
		         
		      }
		      if (multimodal) {
		    	  return -999;
		      }
		      else {
		      return maxValue;
		      }
		   }

	/**
	 * Calculates standard deviation for summary statistics
	 * @param list
	 * @return
	 */
	public static double sd(ArrayList<Double> list) {
		double avg = avg(list);
		double distance = 0;
		
		for (int i = 0; i < list.size(); i++) {
			double r = Math.pow(list.get(i) - avg, 2);
			distance = distance + r;
		}
		double var = distance / (list.size() - 1);
		double sd = Math.sqrt(var);
		return sd;
	}
	
	/**
	 * name getter
	 * @return
	 */
	public String getName() {
		return name;
	}

	/**
	 * name setter
	 * @param name
	 */
	public void setName(String name) {
		this.name = name.substring(0, name.length() - 4); // Cut ".csv" off of the stream name
	}

	/**
	 * index getter
	 * @return
	 */
	public ArrayList<Double> getIndex() {
		return index;
	}

	/**
	 * index setter
	 * @param index
	 */
	public void setIndex(ArrayList<Double> index) {
		this.index = index;
	}

	/**
	 * temp getter
	 * @return
	 */
	public ArrayList<Double> getTemp() {
		return temp;
	}

	/**
	 * temp setter
	 * @param temp
	 */
	public void setTemp(ArrayList<Double> temp) {
		this.temp = temp;
	}

	/**
	 * summary getter
	 * @return
	 */
	public List<Double> getSummary() {
		return summary;
	}

	/**
	 * summary setter
	 */
	public void setSummary() {
		List<Double> summary = new ArrayList<Double>();
		summary.add(avg(temp)); // Temperature average
		summary.add((Double) Collections.min(temp)); // Temperature min
		summary.add((Double) Collections.max(temp)); // Temperature max
		summary.add(mode(temp)); // Temperature mode
		summary.add(sd(temp)); // Temperature standard deviation
		summary.add(avg(turbidity)); // Turbidity average
		summary.add((Double) Collections.min(turbidity)); // Turbidity min
		summary.add((Double) Collections.max(turbidity)); // Turbidity max
		summary.add(mode(turbidity)); // Turbidity mode
		summary.add(sd(turbidity)); // Turbidity standard deviation
		this.summary = summary;
	}
	
	/**
	 * Calculates the s test statistic for Mann-Kendall test
	 * @param x data that is being tested
	 * @return
	 */
	public double sCalc(ArrayList<Double> x) {
		int s = 0;
		int length = x.size(); 
		for (int i=1; i<length; i++) {
			for (int j=0; j<i; j++) {
				double z =(double)x.get(i) - (double)x.get(j);
				int sign = 0;
				if (z < 0) {sign = -1;}
				if (z > 0) {sign = 1;}
				s += sign;
			}
		}	
		return s;
	}
	
	/**
	 * The Mann-Kendall test
	 * @param c user choice of which column to test
	 */
	public void mannKendall(int c) {
		
		ArrayList<Double> choice = null;
		if (c == 1) 
			choice = this.temp;
		if (c == 2) 
			choice = this.turbidity;
	
		int n = choice.size();
		double s = sCalc(choice);
		double t = s / (n*(n-1)/2);
		double sd = sd(choice);
		// Calculate z score for test
		double z;
		if (s == 0) {
			z = (s + 0)/sd;
		}
		else if (s > 0) {
			z = (s + 1)/sd;
		}
		else {
			z = (s - 1)/sd;
		}
		// Calculate significance level of p value
		String psig;
		if (z < -1.96 || z > 1.96) {
			psig = "p value below .05";
		}
		if (z < -2.576 || z > 2.576) {
			psig = "p value below .01";
		}
		else {
			psig = "p value above .05";
		}
		
		
		System.out.printf("%s%n%s%s%n%n%s%.2f%n%s%.2f%n%s%.2f%n%s%s%n%n",
				"Mann Kendall Test Results:", "Stream Name: ", this.getName(),
				"S = ",s,"T = ",t,"Z = ",z,"P = ", psig);
	}
	
	
} // End Brook class