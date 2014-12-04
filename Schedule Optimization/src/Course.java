/**
 * Course as a standalone entity BEFORE module selection, therefore contains ALL available modules for course
 * Examples: CS125, CS173
 * @author Sachin
 *
 */
public class Course {

	public int creditHours;
	public int numberOfModules;
	public Module[][] modulesAvailable;
	public String courseName;
	public String[] dates;
	
	public Course(int numberOfModules)
	{
		modulesAvailable = new Module[numberOfModules][30];
	}
	
}
