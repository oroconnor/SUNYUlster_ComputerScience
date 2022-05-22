//Owen O'Connor - CSC 180 - assignment #8

package marathon;

import java.util.Comparator;
/** Comparator so that Collections sorting can sort the Runner objects for Marathon.java
 *  orders by fastest race duration to slowest
 *  @author owenoconnor
 *  @since 03/25/21
 */
public class RunnerComparator implements Comparator<Runner> {

	@Override
	public int compare(Runner r1, Runner r2) {
		int diff = (int)((r1.getDuration() - r2.getDuration())*1000);
		return diff;
	}


	}

