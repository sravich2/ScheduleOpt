import java.util.Arrays;
import java.util.ArrayList;
/**
 * Contains main method
 * Contains manually entered sample data for classes
 * @author Sachin
 *
 */
public class Driver
{

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		Worker help = new Worker();
		ScheduleScorer scorer = new ScheduleScorer();
		CourseBuilder build = new CourseBuilder();
		
		Course CS100 = new Course(1);
		Course CS125 = new Course(2);
		Course CS173 = new Course(2);
		Course CS196 = new Course(1);
		Course MATH241 = new Course(1);
		Course MUS130 = new Course(2);
		Course CS225 = new Course(2);
		Course CS233 = new Course(2);
		Course MATH415 = new Course(2);
		Course MATH461 = new Course(1);

		CS233.modulesAvailable[0][0] = new Module("MWF", "1000", "1050", "Digital Computer Laboratory Urbana");
		CS233.modulesAvailable[0][1] = new Module("MWF", "1100", "1150", "Digital Computer Laboratory Urbana");
		CS233.modulesAvailable[1][0] = new Module("M", "1300", "1450", "Siebel Center for Comp Sci Urbana");
		CS233.modulesAvailable[1][1] = new Module("M", "1400", "1550", "Siebel Center for Comp Sci Urbana");
		CS233.modulesAvailable[1][2] = new Module("M", "1500", "1650", "Siebel Center for Comp Sci Urbana");
		CS233.modulesAvailable[1][3] = new Module("M", "1600", "1750", "Siebel Center for Comp Sci Urbana");
		CS233.modulesAvailable[1][4] = new Module("T", "1100", "1250", "Siebel Center for Comp Sci Urbana");
		CS233.modulesAvailable[1][5] = new Module("T", "1200", "1350", "Siebel Center for Comp Sci Urbana");
		CS233.modulesAvailable[1][6] = new Module("T", "1300", "1450", "Siebel Center for Comp Sci Urbana");
		CS233.modulesAvailable[1][7] = new Module("T", "1400", "1550", "Siebel Center for Comp Sci Urbana");
		CS233.modulesAvailable[1][8] = new Module("T", "1500", "1650", "Siebel Center for Comp Sci Urbana");
		CS233.modulesAvailable[1][9] = new Module("T", "1600", "1750", "Siebel Center for Comp Sci Urbana");
		CS233.modulesAvailable[1][10] = new Module("T", "1700", "1850", "Siebel Center for Comp Sci Urbana");

		CS225.modulesAvailable[0][0] = new Module("MWF", "1100", "1150", "Electrical & Computer Eng Bldg Urbana");
		CS225.modulesAvailable[0][1] = new Module("MWF", "1300", "1350", "Siebel Center for Comp Sci Urbana");
		CS225.modulesAvailable[1][0] = new Module("W", "1900", "2050", "Siebel Center for Comp Sci Urbana");
		CS225.modulesAvailable[1][1] = new Module("R", "0900", "1050", "Siebel Center for Comp Sci Urbana");
		CS225.modulesAvailable[1][2] = new Module("R", "1100", "1250", "Siebel Center for Comp Sci Urbana");
		CS225.modulesAvailable[1][3] = new Module("R", "1300", "1450", "Siebel Center for Comp Sci Urbana");
		CS225.modulesAvailable[1][4] = new Module("R", "1500", "1650", "Siebel Center for Comp Sci Urbana");
		CS225.modulesAvailable[1][5] = new Module("R", "1700", "1850", "Siebel Center for Comp Sci Urbana");
		CS225.modulesAvailable[1][6] = new Module("R", "1900", "2050", "Siebel Center for Comp Sci Urbana");
		CS225.modulesAvailable[1][7] = new Module("F", "0900", "1050", "Siebel Center for Comp Sci Urbana");
		CS225.modulesAvailable[1][8] = new Module("F", "1100", "1250", "Siebel Center for Comp Sci Urbana");
		CS225.modulesAvailable[1][9] = new Module("F", "1300", "1450", "Siebel Center for Comp Sci Urbana");
		CS225.modulesAvailable[1][10] = new Module("F", "1500", "1650", "Siebel Center for Comp Sci Urbana");
		CS225.modulesAvailable[1][11] = new Module("F", "1700", "1850", "Siebel Center for Comp Sci Urbana");

		MATH415.modulesAvailable[0][0] = new Module("MWF", "1000", "1050", "Main Library Urbana");
		MATH415.modulesAvailable[0][1] = new Module("MWF", "1100", "1150", "Main Library Urbana");
		MATH415.modulesAvailable[0][2] = new Module("MWF", "1400", "1450", "Mumford Hall Urbana");
		MATH415.modulesAvailable[0][3] = new Module("MWF", "1500", "1550", "Mumford Hall Urbana");
		MATH415.modulesAvailable[1][0] = new Module("T", "0800", "0850", "Altgeld Hall Urbana");
		MATH415.modulesAvailable[1][1] = new Module("T", "0900", "0950", "Altgeld Hall Urbana");
		MATH415.modulesAvailable[1][2] = new Module("T", "1000", "1050", "Altgeld Hall Urbana");
		MATH415.modulesAvailable[1][3] = new Module("T", "1100", "1150", "Altgeld Hall Urbana");
		MATH415.modulesAvailable[1][4] = new Module("T", "1200", "1250", "Everitt Elec & Comp Engr Lab");
		MATH415.modulesAvailable[1][5] = new Module("T", "1300", "1350", "Henry Administration Bldg Urbana");
		MATH415.modulesAvailable[1][6] = new Module("T", "1400", "1450", "Altgeld Hall Urbana");
		MATH415.modulesAvailable[1][7] = new Module("T", "1500", "1550", "Altgeld Hall Urbana");
		MATH415.modulesAvailable[1][8] = new Module("T", "1600", "1650", "Henry Administration Bldg Urbana");
		MATH415.modulesAvailable[1][9] = new Module("R", "0800", "0850", "Altgeld Hall Urbana");
		MATH415.modulesAvailable[1][10] = new Module("R", "0900", "0950", "Altgeld Hall Urbana");
		MATH415.modulesAvailable[1][11] = new Module("R", "1000", "1050", "Altgeld Hall Urbana");
		MATH415.modulesAvailable[1][12] = new Module("R", "1100", "1150", "Altgeld Hall Urbana");
		MATH415.modulesAvailable[1][13] = new Module("R", "1200", "1250", "Altgeld Hall Urbana");
		MATH415.modulesAvailable[1][14] = new Module("R", "1300", "1350", "Altgeld Hall Urbana");
		MATH415.modulesAvailable[1][15] = new Module("R", "1400", "1450", "Illini Hall Urbana");
		MATH415.modulesAvailable[1][16] = new Module("R", "1500", "1550", "Altgeld Hall Urbana");
		MATH415.modulesAvailable[1][17] = new Module("R", "1600", "1650", "Altgeld Hall Urbana");

		MATH461.modulesAvailable[0][0] = new Module("MWF", "1000", "1050", "Altgeld Hall Urbana");
		MATH461.modulesAvailable[0][1] = new Module("MWF", "1200", "1250", "Mechanical Engineering Bldg Urbana");
		MATH461.modulesAvailable[0][2] = new Module("MWF", "1300", "1350", "Altgeld Hall Urbana");
		MATH461.modulesAvailable[0][3] = new Module("TR", "0930", "1050", "Altgeld Hall Urbana");
		MATH461.modulesAvailable[0][4] = new Module("TR", "1230", "1350", "Altgeld Hall Urbana");

		CS100.modulesAvailable[0][0] = new Module("W", "1600", "1650", "abc");

		CS125.modulesAvailable[0][0] = new Module("MWF", "1500", "1550");
		CS125.modulesAvailable[0][1] = new Module("MWF", "1400", "1450");
		CS125.modulesAvailable[1][0] = new Module("T", "0900", "1050");
		CS125.modulesAvailable[1][1] = new Module("T", "1100", "1250");
		CS125.modulesAvailable[1][2] = new Module("T", "1300", "1450");
		CS125.modulesAvailable[1][3] = new Module("T", "1500", "1650");
		CS125.modulesAvailable[1][4] = new Module("T", "1700", "1850");
		CS125.modulesAvailable[1][5] = new Module("T", "1900", "2050");

		CS173.modulesAvailable[0][0] = new Module("R", "1100", "1215");
		CS173.modulesAvailable[0][1] = new Module("R", "0930", "1045");
		CS173.modulesAvailable[1][0] = new Module("R", "1400", "1450");
		CS173.modulesAvailable[1][1] = new Module("R", "1500", "1550");
		CS173.modulesAvailable[1][2] = new Module("R", "1600", "1650");
		CS173.modulesAvailable[1][3] = new Module("R", "1700", "1750");
		CS173.modulesAvailable[1][4] = new Module("F", "0900", "0950");
		CS173.modulesAvailable[1][5] = new Module("F", "1000", "1050");

		CS196.modulesAvailable[0][0] = new Module("W", "1900", "2100");

		MUS130.modulesAvailable[0][0] = new Module("MW", "1300", "1350");
		MUS130.modulesAvailable[1][0] = new Module("R", "1600", "1650");
		MUS130.modulesAvailable[1][1] = new Module("F", "1400", "1450");
		MUS130.modulesAvailable[1][2] = new Module("F", "1600", "1650");
		MUS130.modulesAvailable[1][3] = new Module("T", "1400", "1450");

		MATH241.modulesAvailable[0][0] = new Module("MTWR", "1400", "1450");
		MATH241.modulesAvailable[0][1] = new Module("MTWR", "1300", "1350");


		CourseBuilder2 cb2 = new CourseBuilder2();
		ArrayList<Course> coursesTaken = new ArrayList<Course>();
		coursesTaken.add(CS233);
		coursesTaken.add(CS225);
		coursesTaken.add(MATH415);
		coursesTaken.add(MATH461);
		long initialTime = System.currentTimeMillis();
		ArrayList<Module[]> allSchedules = ((cb2.getAllSchedulesFromCourses(coursesTaken)));
		
		ArrayList<Module[]> bestSchedule = new ArrayList<Module[]>();
		String[] scheduleReadable = new String[500];
		
		double bestScore = -999;
		int countOfBestSchedule = 0;
		int countOfExecutions = 0;
		
		StringBuilder[] bestLog = new StringBuilder[3000];
		
		while (countOfExecutions < allSchedules.size())
		{
			
			//System.out.println(help.toString(allSchedules.get(countOfExecutions)));
			Module[] scheduleForSemester = allSchedules.get(countOfExecutions);
			Module[][] finalSchedule = help.convertModuleArrayToSchedule(scheduleForSemester);
			
			//System.out.println(help.toString(finalSchedule));
			double currentScore = (scorer.scoreSchedule(finalSchedule));
			
			if (currentScore > bestScore)
			{
				bestSchedule.clear();
				scheduleReadable = new String[1000];
				bestLog = new StringBuilder[1000];
				countOfBestSchedule = 0;
				
				bestLog[countOfBestSchedule] = new StringBuilder(scorer.log);
				bestSchedule.add(help.deepCopyModuleArray(scheduleForSemester));
				scheduleReadable[countOfBestSchedule] = help.toString(finalSchedule);
				bestScore = currentScore;
				
				countOfBestSchedule++;
			} 
			else if (currentScore == bestScore)
			{
				{
					bestLog[countOfBestSchedule] = new StringBuilder(scorer.log);
					//bestSchedule[countOfBestSchedule] = help.deepCopyModuleArray(scheduleForSemester);
					bestSchedule.add(help.deepCopyModuleArray(scheduleForSemester));
					scheduleReadable[countOfBestSchedule] = help.toString(finalSchedule);
					countOfBestSchedule++;
				}
			}
			countOfExecutions++;
			
		}
		
		System.out.println("Found " + (countOfBestSchedule-1) + " optimal schedules\n");
		System.out.println("--------------------------------------------------------------------------------");
		for (int i = 0; i < countOfBestSchedule; i++)
		{
			System.out.println(scheduleReadable[i]);
			System.out.println(bestLog[i]);
			System.out.println("--------------------------------------------------------------------------------");
			
		}

		System.out.println("Score: " + bestScore);
		System.out.println("Number of runs: " + countOfExecutions);
		System.out.println("Runtime: "+ ((System.currentTimeMillis() -initialTime)/Double.valueOf(1000)) + " seconds");
		
	}

}