import java.util.Arrays;
import org.jvnet.inflector.Noun;
public class ScheduleScorer
{
	
	Worker help = new Worker();
	Preferences pref = new Preferences(true, 240, 240, 1, 3, true, 3);
	
	public int[] sortedNotableTimes;
	public StringBuilder log = new StringBuilder();
	
	/**
	 * Scores a given schedule according to arbitrary set of rules, detailed in documentation
	 * Input taken as Module[][]. See next line. 
	 * Use convertModuleArrayToSchedule method in Worker class to convert Module[] to Module[][] 
	 * 
	 * @param schedule		Module[][] which represents prospective schedule
	 * @return				Score for given prospective schedule. Higher is better.
	 */
	public double scoreSchedule(Meeting[][] schedule)
	{
		log.setLength(0);
		double score = 0;
		String periodOfDay = "";
		String days[] = new String[] {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday"};
		
		switch (pref.avoidTime)
		{
		case 1:
			periodOfDay = "morning";
			break;
		case 2:
			periodOfDay = "afternoon";
			break;
		case 3:
			periodOfDay = "evening";
			break;
		default:
			;
		}
		log.append("Schedule Report:\n");

		for (int i = 0;i < 5;i++)
		{
			log.append("\n"+days[i]+"\n");
			//1. Checking for lunch break
			if (pref.lunchBreak)
			{
				if (!this.checkLunchBreak(schedule[i]))
				{
					log.append("No lunch break on " + days[i] + "\n");
					score -= 15;
				}
			}
			
			//2. Checking for classes at disfavored Time of Day
			int badClasses = this.classesAtTimeOfDay(schedule[i]);
			if (badClasses > 0)
			{
				score -= badClasses * 7.5;
				log.append(badClasses + ((badClasses == 1) ? " class" : " classes") + " in the " + periodOfDay + " on " + days[i] + "\n");
			}
			
			//3. Calculating Maximum Minutes in a Row
			int maxMinsInRow = this.maxMinutesInRow(schedule[i]);
			if (maxMinsInRow > pref.maxMinutesInARow)
			{
				score -= (maxMinsInRow - pref.maxMinutesInARow) / 10;
				log.append(maxMinsInRow + " minutes in a row on " + days[i] + "\n");
			}
			
			//4. Calculating Minutes in class each day
			int maxMinsInDay = this.minutesInDay(schedule[i]);
			if (maxMinsInDay > pref.maxMinutesInADay)
			{
				score -= (maxMinsInDay - pref.maxMinutesInADay) / 10;
				log.append(maxMinsInDay + " minutes on " + days[i] + "\n");
			}
			
			//5. Checking existence of Breaks as per user preference
			if (pref.avoidBreaksBetweenClasses > 0)
			{
				int shortBreaks = this.countShortAndTotalBreaks(schedule[i])[0];
				int allBreaks = this.countShortAndTotalBreaks(schedule[i])[1];

				if ((pref.avoidBreaksBetweenClasses == 1 || pref.avoidBreaksBetweenClasses == 3) && shortBreaks > 0)
				{
					score -= shortBreaks * 5;
					log.append(shortBreaks + " short " + ((shortBreaks == 1) ? "break ": "breaks") + " on " + days[i] + "\n");
				}
				if (pref.avoidBreaksBetweenClasses > 1 && allBreaks > 0)

				{
					score -= 2.5 * allBreaks;
					log.append(allBreaks + ((allBreaks==1) ? " break" : " breaks") + " on " + days[i] + "\n");
				}
			}
			
			/*
			//6. Checking for unwalkable classes
			int unwalkableClasses = this.countUnwalkableClasses(schedule[i]).length;
			if ((unwalkableClasses>0))
			{
				score -= 50;
				log.append(unwalkableClasses+ " unwalkable classes " + " on " + days[i] + "\n");
			}
			*/
			
			//7. Check for day with minimum classes
			if (pref.dayWithMinimum <= 2)
			{

				if (countClassesOnADay(schedule[i]) <= pref.dayWithMinimum)
				{
					score += 10 * (3 - pref.dayWithMinimum);
					log.append(countClassesOnADay(schedule[i]) + " classes on " + days[i] + "\n");
				}

			}	
		}
		
		//8. Checking classes near weekend
		if (pref.extendWeekend)
		{
			int mondayClasses = countClassesOnADay(schedule[0]);
			int fridayClasses = countClassesOnADay(schedule[4]);
			if (mondayClasses > 0)
			{
				score -= mondayClasses * 5;
				log.append(mondayClasses + " classes on Monday\n");
			}
			if (fridayClasses > 0)
			{
				score -= fridayClasses * 5;
				log.append(fridayClasses + " classes on Friday\n");
			}
		}
		
		return score;
		
	}

	/**
	 * Calculates the number of minutes in class on a given day
	 * 
	 * @param scheduleForOneDay		Module[] containing all classes on a single day
	 * @return						Number of minutes in class during the given day
	 */
	public int minutesInDay(Meeting[] scheduleForOneDay)
	{
		int minutesBusyInDay = 0;
		for (int i = 0; i < scheduleForOneDay.length; i++)
		{
			minutesBusyInDay += scheduleForOneDay[i].duration;
		}
		return minutesBusyInDay;
	}

	/**
	 * Calculates the maximum number of minutes in a row on a given day
	 * 
	 * @param scheduleForOneDay		Module[] containing all classes on a single day
	 * @return						Maximum number of minutes in a row during the given day
	 */
	public int maxMinutesInRow(Meeting[] scheduleForOneDay)
	{
		int maxMinutesInRow = 0;
		int count = 0;
		int[] notableTimes = new int[scheduleForOneDay.length * 2];
		for (int i = 0; i < scheduleForOneDay.length; i++)
		{
			notableTimes[count] = scheduleForOneDay[i].startTime;
			notableTimes[count + 1] = scheduleForOneDay[i].endTime;
			count += 2;
		}
		Arrays.sort(notableTimes);

		for (int i = 0; i < notableTimes.length / 2 - 1; i++)
		{
			if (notableTimes[2 * (i + 1)] - notableTimes[2 * i + 1] <= 15)
			{
				notableTimes = help.removeFromArray(notableTimes, 2 * i + 1);
				i--; //We don't want i to be incremented because we have removed 2 elements
			}
		}
		for (int i = 0; i < notableTimes.length / 2; i++)
		{
			if (notableTimes[2 * (i) + 1] - notableTimes[2 * i] > maxMinutesInRow)
				maxMinutesInRow = notableTimes[2 * (i) + 1] - notableTimes[2 * i];
		}
		return maxMinutesInRow;

	}

	/**
	 * Checks whether there is a lunch break on the given day
	 * 
	 * @param scheduleForOneDay		Module[] containing all classes on a single day
	 * @return						boolean represents whether there is a break for lunch on the given day
	 */
	public boolean checkLunchBreak(Meeting[] scheduleForOneDay)
	{
		int minutesBusyDuringLunchTime = 0;
		for (int i = 0; i < scheduleForOneDay.length; i++)
		{
			if (scheduleForOneDay[i].endTime > 660 && scheduleForOneDay[i].startTime < 840)//660 is decimal for 1100, 840 is decimal for 1400
			{
				minutesBusyDuringLunchTime += Math.min(scheduleForOneDay[i].endTime, 840) - Math.max(scheduleForOneDay[i].startTime, 660);
			}
		}
		//System.out.println(minutesBusyDuringLunchTime);
		if (minutesBusyDuringLunchTime >= 100)
			return false;
		else
			return true;

	}

	/**
	 * Calculates the number of classes during disfavored time of day on the given day
	 * 
	 * @param scheduleOnDay		Module[] containing all classes on a single day
	 * @return					Number of classes during disfavored time of day on the given day
	 */
	public int classesAtTimeOfDay(Meeting[] scheduleOnDay) //Returns number of bad classes
	{
		int countOfClassesInUndesirableTime = 0;
		Meeting checkAgainstThisModule;
		switch (pref.avoidTime)
		{
		default:
			return 0;
		case 1:
			checkAgainstThisModule = new Meeting("MTWRF", "0800", "1059");
			break;
		case 2:
			checkAgainstThisModule = new Meeting("MTWRF", "1100", "1559");
			break;
		case 3:
			checkAgainstThisModule = new Meeting("MTWRF", "1600", "2200");
			break;
		}

		for (int i = 0; i < scheduleOnDay.length; i++)
		{

			if (help.checkConflict(checkAgainstThisModule, scheduleOnDay[i]))
			{
				countOfClassesInUndesirableTime++;
			}
		}
		return countOfClassesInUndesirableTime;
	}

	/**
	 * Counts the number of short breaks and total breaks between classes on the given day
	 * Short break: Between 20 and 90 minutes
	 * All breaks:  More than 20 minutes
	 * 
	 * @param scheduleForOneDay		Module[] containing all classes on a single day
	 * @return						int[] containing count of short breaks and total breaks, in that order
	 */
	public int[] countShortAndTotalBreaks(Meeting[] scheduleForOneDay)
	{

		int[] countBreaks = new int[2];
		int countShortBreaks = 0;
		int countAllBreaks = 0;
		int breakBetweenClasses = 0;

		int count = 0;
		int[] notableTimes = new int[scheduleForOneDay.length * 2];
		for (int i = 0; i < scheduleForOneDay.length; i++)
		{
			notableTimes[count] = scheduleForOneDay[i].startTime;
			notableTimes[count + 1] = scheduleForOneDay[i].endTime;
			count += 2;
		}
		Arrays.sort(notableTimes);

		for (int i = 0; i < notableTimes.length / 2 - 1; i++)
		{
			breakBetweenClasses = notableTimes[2 * (i + 1)] - notableTimes[2 * i + 1];

			if (breakBetweenClasses > 20 && breakBetweenClasses < 90)
				countShortBreaks++;
			if (breakBetweenClasses > 20)
				countAllBreaks++;
		}

		countBreaks[0] = countShortBreaks;
		countBreaks[1] = countAllBreaks;
		return countBreaks;
	}
	
	public int[] countUnwalkableClasses(Meeting[] scheduleForOneDay) //Optimise this method
	{
		DistanceTimeMatrix matrix = new DistanceTimeMatrix();
		double[] travelInfo = new double[2];
		int[] howLateAreYouGoingToBe = new int[10];
		int count = 0;
		Meeting[] scheduleForOneDay2 = help.sortByTimeScheduleForOneDay(scheduleForOneDay);

		for (int i = 0;i<scheduleForOneDay2.length-1;i++)
		{
			try{
				travelInfo = matrix.getTravelTimeAndDistance("http://www.mapquestapi.com/directions/v2/route?key=Fmjtd%7Cluurn9u7ng%2Cbx%3Do5-9wznl0&outFormat=json&routeType=pedestrian&enhancedNarrative=true&locale=en_US&from=1304+w+springfield+avenue+urbana&to=201+n+goodwin+avenue+Urbana");
			}
			catch (Exception e){
				System.out.println(e.getMessage());
			}
			
			if (travelInfo[0] > scheduleForOneDay2[i+1].startTime-scheduleForOneDay2[i].endTime)
			{
				System.out.print(scheduleForOneDay2[i].building + " to ");
				System.out.println(scheduleForOneDay2[i+1].building + "  "+travelInfo[0] + " > " + scheduleForOneDay2[i+1].startTime + " - "+ scheduleForOneDay2[i].endTime);
				howLateAreYouGoingToBe[count] = (int)(travelInfo[0] - (scheduleForOneDay2[i+1].startTime-scheduleForOneDay2[i].endTime));
				count++;
			}
		}
		howLateAreYouGoingToBe = Arrays.copyOfRange(howLateAreYouGoingToBe, 0, count+1);
		return howLateAreYouGoingToBe;
	}
	
	public int countClassesOnADay(Meeting[] scheduleForOneDay)
	{
		return scheduleForOneDay.length;
	}

}
