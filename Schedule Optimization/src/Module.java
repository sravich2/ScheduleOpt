import java.util.Arrays;


/**
 * Module, defined as a component of any course.
 * Examples: Lecture, Discussion, Laboratory
 * @author sravich2
 * 
 */

public class Module {
	
	
	public Worker help = new Worker();
	
	public int CRN;
	public String moduleName;
	public String section;
	public char[] days;
	public String building; //Pull from CSV file.
	public String instructor; //Check from CSV as and when needed
	public int startTime;
	public int endTime;
	public int duration; 
	
	public Module(String days, String startTime, String endTime, String location)
	{
		this.days = days.toCharArray();
		this.startTime = help.convertTimeBase60To10(startTime);
		this.endTime = help.convertTimeBase60To10(endTime);
		this.duration = this.endTime-this.startTime;
		this.building = location;
	}
	
	public Module(String days, String startTime, String endTime)
	{
		this.days = days.toCharArray();
		this.startTime = help.convertTimeBase60To10(startTime);
		this.endTime = help.convertTimeBase60To10(endTime);
		this.duration = this.endTime-this.startTime;
	}
	
	public Module(String days, int startTime, int endTime)
	{
		this.days = days.toCharArray();
		this.startTime = (startTime);
		this.endTime = (endTime);
		this.duration = this.endTime-this.startTime;
	}
	
	public Module(String days, int startTime, int endTime, String building)
	{
		this.days = days.toCharArray();
		this.startTime = (startTime);
		this.endTime = (endTime);
		this.duration = this.endTime-this.startTime;
		this.building = building;
	}
	
	public boolean equals(Module checkModule)
	{
		if (Arrays.equals(this.days, checkModule.days)&& this.startTime == checkModule.startTime && this.endTime == checkModule.endTime)
			return true;
		return false;
	}
	
}
