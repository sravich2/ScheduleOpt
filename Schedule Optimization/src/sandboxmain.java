import java.util.Arrays;

public class sandboxmain
{

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{

		Worker help = new Worker();

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

		CS233.modulesAvailable[0][0] = new Module("MWF", "1000", "1050");
		CS233.modulesAvailable[0][1] = new Module("MWF", "1100", "1150");
		CS233.modulesAvailable[1][0] = new Module("M", "1300", "1450");
		CS233.modulesAvailable[1][1] = new Module("M", "1400", "1550");
		CS233.modulesAvailable[1][2] = new Module("M", "1500", "1650");
		CS233.modulesAvailable[1][3] = new Module("M", "1600", "1750");
		CS233.modulesAvailable[1][4] = new Module("T", "1100", "1250");
		CS233.modulesAvailable[1][5] = new Module("T", "1200", "1350");
		CS233.modulesAvailable[1][6] = new Module("T", "1300", "1450");
		CS233.modulesAvailable[1][7] = new Module("T", "1400", "1550");
		CS233.modulesAvailable[1][8] = new Module("T", "1500", "1650");
		CS233.modulesAvailable[1][9] = new Module("T", "1600", "1750");
		CS233.modulesAvailable[1][10] = new Module("T", "1700", "1850");

		CS225.modulesAvailable[0][0] = new Module("MWF", "1100", "1150");
		CS225.modulesAvailable[0][1] = new Module("MWF", "1300", "1350");
		CS225.modulesAvailable[1][0] = new Module("W", "1900", "2050");
		CS225.modulesAvailable[1][1] = new Module("R", "0900", "1050");
		CS225.modulesAvailable[1][2] = new Module("R", "1100", "1250");
		CS225.modulesAvailable[1][3] = new Module("R", "1300", "1450");
		CS225.modulesAvailable[1][4] = new Module("R", "1500", "1650");
		CS225.modulesAvailable[1][5] = new Module("R", "1700", "1850");
		CS225.modulesAvailable[1][6] = new Module("R", "1900", "2050");
		CS225.modulesAvailable[1][7] = new Module("F", "0900", "1050");
		CS225.modulesAvailable[1][8] = new Module("F", "1100", "1250");
		CS225.modulesAvailable[1][9] = new Module("F", "1300", "1450");
		CS225.modulesAvailable[1][10] = new Module("F", "1500", "1650");
		CS225.modulesAvailable[1][11] = new Module("F", "1700", "1850");

		MATH415.modulesAvailable[0][0] = new Module("MWF", "1000", "1050");
		MATH415.modulesAvailable[0][1] = new Module("MWF", "1100", "1150");
		MATH415.modulesAvailable[0][2] = new Module("MWF", "1400", "1450");
		MATH415.modulesAvailable[0][3] = new Module("MWF", "1500", "1550");
		MATH415.modulesAvailable[1][0] = new Module("T", "0800", "0850");
		MATH415.modulesAvailable[1][1] = new Module("T", "0900", "0950");
		MATH415.modulesAvailable[1][2] = new Module("T", "1000", "1050");
		MATH415.modulesAvailable[1][3] = new Module("T", "1100", "1150");
		MATH415.modulesAvailable[1][4] = new Module("T", "1200", "1250");
		MATH415.modulesAvailable[1][5] = new Module("T", "1300", "1350");
		MATH415.modulesAvailable[1][6] = new Module("T", "1400", "1450");
		MATH415.modulesAvailable[1][7] = new Module("T", "1500", "1550");
		MATH415.modulesAvailable[1][8] = new Module("T", "1600", "1650");
		MATH415.modulesAvailable[1][9] = new Module("R", "0800", "0850");
		MATH415.modulesAvailable[1][10] = new Module("R", "0900", "0950");
		MATH415.modulesAvailable[1][11] = new Module("R", "1000", "1050");
		MATH415.modulesAvailable[1][12] = new Module("R", "1100", "1150");
		MATH415.modulesAvailable[1][13] = new Module("R", "1200", "1250");
		MATH415.modulesAvailable[1][14] = new Module("R", "1300", "1350");
		MATH415.modulesAvailable[1][15] = new Module("R", "1400", "1450");
		MATH415.modulesAvailable[1][16] = new Module("R", "1500", "1550");
		MATH415.modulesAvailable[1][17] = new Module("R", "1600", "1650");

		MATH461.modulesAvailable[0][0] = new Module("MWF", "1000", "1050");
		MATH461.modulesAvailable[0][1] = new Module("MWF", "1200", "1250");
		MATH461.modulesAvailable[0][2] = new Module("MWF", "1300", "1350");
		MATH461.modulesAvailable[0][3] = new Module("TR", "0930", "1050");
		MATH461.modulesAvailable[0][4] = new Module("TR", "1230", "1350");

		CS100.modulesAvailable[0][0] = new Module("W", "1600", "1650");

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

		/*
		
		boolean found = false;
		while (!found)
		{
		//	System.out.println();
		Module[] finalClasses = new Module[0];
		
		Module[] CS100Final = help.chooseRandomCourses(CS100);
		finalClasses = help.addModuleArray(CS100Final, finalClasses);
		
		Module[] CS125Final = help.chooseRandomCourses(CS125);
		finalClasses = help.addModuleArray(CS125Final, finalClasses);
		
		Module[] CS173Final = help.chooseRandomCourses(CS173);
		finalClasses = help.addModuleArray(CS173Final, finalClasses);
		
		Module[] CS196Final = help.chooseRandomCourses(CS196);
		finalClasses = help.addModuleArray(CS196Final, finalClasses);
		
		Module[] MATH241Final = help.chooseRandomCourses(MATH241);
		finalClasses = help.addModuleArray(MATH241Final, finalClasses);
		
		Module[] MUS130Final = help.chooseRandomCourses(MUS130);
		finalClasses = help.addModuleArray(MUS130Final, finalClasses);
		
		
		outerloop:
		for (int i = 0;i<finalClasses.length;i++)
		{
			for (int j = 0;j<finalClasses.length;j++)
			{
				if ((i!=j) && (help.checkConflict(finalClasses[i], finalClasses[j])))
				{
					break outerloop;
				}
				if (i == finalClasses.length-1 && j == finalClasses.length-1)
				{
					System.out.println("FOUND");
					System.out.println(help.toString(finalClasses));
					found = true;
			8	}
			}
		}
		

		}*/
		ScheduleScorer scorer = new ScheduleScorer();
		Module[][] bestSchedule = new Module[20][0];
		Module[][] best2DSchedule = new Module[0][0];
		int bestScore = -999;
		StringBuilder[] bestLog = new StringBuilder[20];
		long initialTime = System.currentTimeMillis();
		int countOfExecutions = 0;
		Course[] takingThisSem = new Course[] { CS225, CS233, MATH415, MATH461 };
		CourseBuilder build = new CourseBuilder();
		Module[][] allLegalSchedules = new Module[300000][7];
		allLegalSchedules[0] = build.buildCourseSchedule(takingThisSem);
		int numberOfLegalSchedules = 1;
		int countOfBestSchedule = 0;

		//while (numberOfLegalSchedules < 438000)
		while (System.currentTimeMillis() - initialTime < 10000)
		{
			countOfExecutions++;

			Module[] scheduleForSemester = build.buildCourseSchedule(takingThisSem);

			boolean repeat = true;
			while (repeat == true)
			{
				for (int i = 0; i <= countOfBestSchedule; i++)
				{
					if (help.compareModuleArrays(scheduleForSemester, bestSchedule[i]))
					{
						scheduleForSemester = build.buildCourseSchedule(takingThisSem);
						i = -1;
					}
					if (i == countOfBestSchedule)
						repeat = false;
				}

			}

			/*
			for (int i = 0;i<numberOfLegalSchedules;i++)
			{
				if (System.currentTimeMillis() - initialTime < 120000)
				{
				if (help.compareModuleArrays(scheduleForSemester, allLegalSchedules[i]))
				{
					scheduleForSemester = build.buildCourseSchedule(takingThisSem);
					i = -1;
					//System.out.println("Reset here");	
				}
				}
			} 
			allLegalSchedules[numberOfLegalSchedules] = help.addModuleArray(allLegalSchedules[numberOfLegalSchedules], scheduleForSemester);
			//System.out.println(help.toString(allLegalSchedules[numberOfLegalSchedules]));
			*/

			numberOfLegalSchedules++;
			Module[][] finalSchedule = help.convertModuleArrayToSchedule(scheduleForSemester);

			int currentScore = (scorer.scoreSchedule(finalSchedule));
			//System.out.println(currentScore);
			if (currentScore > bestScore)
			{
				bestSchedule = new Module[200][0];
				bestLog = new StringBuilder[200];
				countOfBestSchedule = 0;
				bestLog[countOfBestSchedule] = new StringBuilder(scorer.log);
				bestSchedule[countOfBestSchedule] = help.deepCopyModuleArray(scheduleForSemester);
				bestScore = currentScore;
				//best2DSchedule = finalSchedule;
			} else if (currentScore == bestScore)
			{
				/*boolean repeat = false;
				for (int i = 0;i<=countOfBestSchedule;i++)
				{
					if (help.compareModuleArrays(scheduleForSemester, bestSchedule[i]))
					{
						repeat = true;
						break;
					}
				}*/
				//if (!repeat)
				{
					countOfBestSchedule++;
					bestLog[countOfBestSchedule] = new StringBuilder(scorer.log);
					bestSchedule[countOfBestSchedule] = help.deepCopyModuleArray(scheduleForSemester);

					//best2DSchedule = finalSchedule;
				}
			}

		}
		System.out.println("Found " + (countOfBestSchedule + 1) + " optimal schedules");
		for (int i = 0; i <= countOfBestSchedule; i++)
		{
			System.out.println(help.toString((bestSchedule[i])));
			System.out.println(bestLog[i]);
		}

		System.out.println(bestScore);

		System.out.println(countOfExecutions);
		System.out.println(numberOfLegalSchedules);

	}

}
