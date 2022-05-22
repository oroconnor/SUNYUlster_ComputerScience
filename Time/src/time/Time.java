package time;

/** 
 * Time class represents hours, minutes and seconds
 * @author owenoconnor
 *
 * @since 1/25/21
 */

public class Time {
	private int hour; //0-23
	private int minute; //0-59
	private int second; //0-59
	
	/**
	 * Time construction for hours only, sets minutes and seconds to 0
	 * @param hour current hours in the time
	 */
	public Time(int hour) {
		this(hour,0,0);
	}
	
	/**
	 * Time construction for hours and minutes only, sets seconds to 0
	 * @param hour current hours in the time
	 * @param minute  current minute in the time
	 */
	public Time(int hour, int minute) {
		this(hour,minute,0);
	}
	
	/**
	 * Time construction for hours minutes and seconds
	 * @param hour current hours in the time
	 * @param minute  current minute in the time
	 * @param second current second in the time
	 */
	public
	public void Time(int hour, int minute, int second) {
		this.hour = hour;
		this.minute = minute;
		this.second = second;
		}	

	}

	
	
	
