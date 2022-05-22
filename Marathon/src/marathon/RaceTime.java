//Owen O'Connor - CSC 180 - assignment #8

package marathon;

/** RaceTime object that structures race times for Marathon.java
 *  @author owenoconnor
 *  @since 03/25/21
 */
public class RaceTime {
	private int hour;
	private int minute;
	private int second;
	/**
	 * Time construction for hours minutes and seconds
	 * @param hour current hours in the time
	 * @param minute  current minute in the time
	 * @param second current second in the time
	 */
	public RaceTime(int hour, int minute, int second) {
		//validates for valid time inputs
		if (hour < 0 || hour >= 24) {
			throw new IllegalArgumentException("Sorry. Hour entered not valid.");	
		}
		if (minute < 0 || minute >= 60) {
			throw new IllegalArgumentException("Sorry. Minute entered not valid.");	
		}
		if (second < 0 || second >= 60) {
			throw new IllegalArgumentException("Sorry. Seconds entered not valid.");	
		}
		
		this.setHour(hour);
		this.setMinute(minute);
		this.setSecond(second);
		}	

	public int getHour() {
		return hour;
	}

	public void setHour(int hour) {
		this.hour = hour;
	}

	public int getMinute() {
		return minute;
	}

	public void setMinute(int minute) {
		this.minute = minute;
	}

	public int getSecond() {
		return second;
	}

	public void setSecond(int second) {
		this.second = second;
	}
	
	public String toString() {
		return String.format("%d:%02d:%02d", getHour(), getMinute(), getSecond());
	}
	
}
