/**
 * Framework for storing and working with user preferences
 * @author Sachin
 * 
 */
public class Preferences
{

	public boolean lunchBreak;
	public int maxMinutesInADay;
	public int maxMinutesInARow;
	public int avoidClasses; 			 //0 - No preference, 1 - morning, 2 - afternoon, 3 - evening
	public int avoidBreaksBetweenClasses;//0 - No preference, 1 - Avoid short breaks, 2 - Avoid long breaks, 3 - Avoid all breaks

	public Preferences(boolean lunch, int inDay, int inRow, int avoid, int avoidShortBreaks)
	{
		this.lunchBreak = lunch;
		this.maxMinutesInADay = inDay;
		this.maxMinutesInARow = inRow;
		this.avoidClasses = avoid;
		this.avoidBreaksBetweenClasses = avoidShortBreaks;
	}

}
