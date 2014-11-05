import java.util.Arrays;

public class ScheduleScorer
{

	Worker help = new Worker();
	public int[] sortedNotableTimes;
	public StringBuilder log = new StringBuilder();
	Preferences pref = new Preferences(true, 300, 300, 1, 4);

	public int scoreSchedule(Module[][] schedule)
	{
		log.setLength(0);
		int score = 0;
		//Lunch
		if (pref.lunchBreak)
		{
			for (int i = 0; i < 5; i++)
			{
				if (!this.checkLunchBreak(schedule[i]))
				{
					log.append("No lunch break on Day " + (i + 1) + "\n");
					score -= 10;
				}
			}
		}

		//Classes at avoidable time of day
		for (int i = 0; i < 5; i++)
		{
			int badClasses = this.classesAtTimeOfDay(schedule[i]);
			if (badClasses > 0)
			{
				score -= badClasses * 7.5;
				log.append(badClasses + " classes in period " + pref.avoidClasses + " on Day " + (i + 1) + "\n");
			}
		}

		//Minutes in a row
		for (int i = 0; i < 5; i++)
		{
			int maxMinsInRow = this.maxMinutesInRow(schedule[i]);
			if (maxMinsInRow > pref.maxMinutesInARow)
			{
				score -= (maxMinsInRow - pref.maxMinutesInARow) / 10;
				log.append(maxMinsInRow + " minutes in a row on Day " + (i + 1) + "\n");
			}
		}

		//Minutes in day
		for (int i = 0; i < 5; i++)
		{
			int maxMinsInDay = this.minutesInDay(schedule[i]);
			if (maxMinsInDay > pref.maxMinutesInADay)
			{
				score -= (maxMinsInDay - pref.maxMinutesInADay) / 10;
				log.append(maxMinsInDay + " minutes on Day " + (i + 1) + "\n");
			}
		}

		//Breaks
		if (pref.avoidBreaksBetweenClasses > 0)
		{
			for (int i = 0; i < 5; i++)
			{
				int shortBreaks = this.countShortAndTotalBreaks(schedule[i])[0];
				int allBreaks = this.countShortAndTotalBreaks(schedule[i])[1];

				if ((pref.avoidBreaksBetweenClasses == 2 || pref.avoidBreaksBetweenClasses == 4) && shortBreaks > 0)
				{
					score -= shortBreaks * 5;
					log.append(shortBreaks + " short breaks " + " on Day " + (i + 1) + "\n");
				}
				if (pref.avoidBreaksBetweenClasses > 2 && allBreaks > 0)

				{
					score -= 2 * allBreaks;
					log.append(allBreaks + " breaks " + " on Day " + (i + 1) + "\n");
				}
			}
		}
		//System.out.println(log);
		return score;
	}

	public int minutesInDay(Module[] scheduleForOneDay)
	{
		int minutesBusyInDay = 0;
		for (int i = 0; i < scheduleForOneDay.length; i++)
		{
			minutesBusyInDay += scheduleForOneDay[i].duration;
		}
		return minutesBusyInDay;
	}

	public int maxMinutesInRow(Module[] scheduleForOneDay)
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
		//this.sortedNotableTimes = Arrays.copyOf(notableTimes, notableTimes.length);
		//System.out.println(Arrays.toString(sortedNotableTimes));

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

	public boolean checkLunchBreak(Module[] scheduleForOneDay)
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

	public int classesAtTimeOfDay(Module[] scheduleOnDay) //Returns number of bad classes
	{
		int countOfClassesInUndesirableTime = 0;
		Module checkAgainstThisModule;
		switch (pref.avoidClasses)
		{
		default:
			return 0;
		case 1:
			checkAgainstThisModule = new Module("MTWRF", "0800", "1059");
			break;
		case 2:
			checkAgainstThisModule = new Module("MTWRF", "1100", "1559");
			break;
		case 3:
			checkAgainstThisModule = new Module("MTWRF", "1600", "2200");
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

	public int[] countShortAndTotalBreaks(Module[] scheduleForOneDay)
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
			{
				//System.out.println(breakBetweenClasses);
				countShortBreaks++;
			}
			if (breakBetweenClasses > 20)
				countAllBreaks++;
		}

		countBreaks[0] = countShortBreaks;
		countBreaks[1] = countAllBreaks;
		return countBreaks;
	}

}
