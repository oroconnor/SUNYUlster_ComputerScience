//Owen O'Connor - CSC 180 - assignment #8

package marathon;

/** Runner object, stores runner data for Marathon.java
 *  @author owenoconnor
 *  @since 03/25/21
 */
public class Runner {
	private String name;
	private RaceTime startTime;
	private RaceTime endTime;
	
	public Runner(String name, RaceTime startTime, RaceTime endTime) {
		this.setName(name);
		this.setStartTime(startTime);
		this.setEndTime(endTime);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	public RaceTime getStartTime() {
		return startTime;
	}

	public void setStartTime(RaceTime startTime) {
		this.startTime = startTime;
	}

	public RaceTime getEndTime() {
		return endTime;
	}

	public void setEndTime(RaceTime endTime) {
		this.endTime = endTime;
	}

	public double getDuration() {
		//calculates durations every time its called in case race times change
		double durHour = this.endTime.getHour() - this.startTime.getHour();
		double durMinute = this.endTime.getMinute() - this.startTime.getMinute();
		double durSecond = this.endTime.getSecond() - this.startTime.getSecond();
		double duration = durHour + (((durSecond/60) + durMinute) / 60);
		return duration;
		
	}


}
