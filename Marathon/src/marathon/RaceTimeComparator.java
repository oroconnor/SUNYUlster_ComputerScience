package marathon;

//import java.util.Comparator;

public class RaceTimeComparator{

	
	public static int comparing(RaceTime rt1, RaceTime rt2) {
		int hourDiff = rt1.getHour() - rt2.getHour();
		if (hourDiff != 0) { return hourDiff;}
		
		int minuteDiff = rt1.getMinute() - rt2.getMinute();
		if (minuteDiff != 0) { return minuteDiff;}
		
		int secondDiff = rt1.getSecond() - rt2.getSecond();
		return secondDiff;
	}

}
