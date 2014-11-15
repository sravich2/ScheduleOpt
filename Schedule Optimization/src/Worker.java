import java.util.Random;
import java.util.ArrayList;

/**
 * Contains helper methods for use throughout program
 * NOTE: Edit/add Javadoc documentation if you edit/add any methods to this class 
 * @author Sachin
 *
 */
public class Worker
{

	/**
	 * Converts sexagesimal time to decimal equivalent.
	 * Works for both military time and AM/PM format 
	 * 
	 * @param time string containing time in 24-hour format
	 * @return     time in decimal format
	 */
	public int convertTimeBase60To10(String time)
	{
		int timeIn10;
		int hourIn60 = Integer.valueOf(time.substring(0, 2));
		if(time.substring(time.length()-2).equals("PM") && hourIn60!=12){hourIn60+=12;}
		int minuteIn60 = Integer.valueOf(time.substring(2));
		timeIn10 = hourIn60 * 60 + minuteIn60;
		return timeIn10;
	}

	/**
	 * Converts time in decimal format to 24-hour format (Example: 1400, 2000)
	 * 
	 * @param time time in decimal format
	 * @return	   string containing time in 24-hour format
	 */
	public String convertTimeBase10To60(int time)
	{
		String timeIn60;
		int hourIn60 = Integer.valueOf(time) / 60;
		int minuteIn60 = Integer.valueOf(time) % 60;
		timeIn60 = String.valueOf(hourIn60) + String.format("%02d", minuteIn60);
		return timeIn60;
	}
	/**
	 * Checks for time conflicts between 2 Modules.
	 * Checks truth of statement: "There is a conflict between modules module1 and module2"
	 * 
	 * @param module1
	 * @param module2
	 * @return         boolean that represents existence of conflict
	 */
	public boolean checkConflict(Module module1, Module module2)
	{
		boolean daysClash = false;
		outerloop: for (int i = 0; i < module1.days.length; i++)
		{
			for (int j = 0; j < module2.days.length; j++)
			{
				if (module1.days[i] == module2.days[j])
				{
					daysClash = true;
					break outerloop;
				}
			}
		}

		if (daysClash)
		{
			if (Math.max(module1.startTime, module2.startTime) <= Math.min(module1.endTime, module2.endTime))
				return true;
		}
		return false;
	}

	/**
	 * Choose random Modules for given Course
	 * NOTE: Needs optimization and efficiency boosts
	 * 
	 * @param inputCourse  Course for which modules are to be chosen
	 * @return			   Module[] containing random selection of Modules for given Course
	 */
	public ArrayList<Module> chooseRandomModules(Course inputCourse)
	{
		Random rand = new Random();
		//Module[] finalCourses = new Module[inputCourse.modulesAvailable.length];
		ArrayList<Module> finalCourses = new ArrayList<Module>(inputCourse.modulesAvailable.length);
		for (int i = 0; i < inputCourse.modulesAvailable.length; i++)
			finalCourses.add(inputCourse.modulesAvailable[i][(int) (rand.nextDouble() * this.realLength(inputCourse.modulesAvailable[i]))]);
		return finalCourses;
	}

	/**
	 * Add a single Module to a Module[]
	 * 
	 * @param moduleToAdd Module to be added
	 * @param moduleArr   Module[] to which Module moduleToAdd is to be added
	 * @return            Module[] with addition of Module moduleToAdd
	 */
	public Module[] addModule(Module moduleToAdd, Module[] moduleArr)
	{
		Module[] newModuleArray = new Module[moduleArr.length + 1];
		for (int i = 0; i < moduleArr.length; i++)
		{
			newModuleArray[i] = this.deepCopyModule(moduleArr[i]);
		}
		newModuleArray[newModuleArray.length - 1] = this.deepCopyModule(moduleToAdd);
		return newModuleArray;
	}

	/**
	 * Outputs readable representation of given Module[]
	 * Represented as list of modules, not as day-wise schedule
	 * Debugging tool
	 * 
	 * @param listOfClasses  Module[] whose representation is sought
	 * @return				 String containing representation of Module[] listOfClasses
	 */
	public String toString(Module[] listOfClasses)
	{
		String abc = "";
		int i = 0;
		while (i < listOfClasses.length && listOfClasses[i] != null)
		{
			abc += " | " + String.valueOf(listOfClasses[i].days) + " | " + this.convertTimeBase10To60(listOfClasses[i].startTime) + " | " + this.convertTimeBase10To60(listOfClasses[i].endTime)
					+ " ||| \n";
			i++;
		}
		return abc;
	}
	
	/**
	 * Prints out time table representation of schedule 
	 * 
	 * @param schedule	Module[] representing schedule to be printed
	 * @return			String containing time table representation of schedule
	 */
	public String toString(Module[][] schedule)
	{
		StringBuffer output = new StringBuffer();
		
		output.append("   Monday         Tuesday          Wednesday         Thursday         Friday\n");
		for (int i = 0;i<5;i++)
		{
			schedule[i] = this.sortByTimeScheduleForOneDay(schedule[i]);
		}
		outerloop:
		for (int i = 0;i<8;i++)
		{
			boolean foundClass = false;
			for (int j = 0;j<5;j++)
			{
				if (i<schedule[j].length)
				{
					output.append(this.convertTimeBase10To60(schedule[j][i].startTime) + " - " + this.convertTimeBase10To60(schedule[j][i].endTime) + "      ");
					foundClass = true;
				}
				
				else
				{
					output.append("                 ");
				}

				
			}
			if (!foundClass)
				break outerloop;
			output.append("\n");
		}
		return output.toString();
	}

	/**
	 * Calculates number of non-null references in Module[]
	 * 
	 * @param input Module[] whose length is to be found
	 * @return		number of non-null references in Module[] input
	 */
	public int realLength(Module[] input)
	{
		int i = 0;
		while (input[i] != null)
			i++;
		return i;
	}

	/**
	 * Converts Module[] containing list of Modules to Module[][] containing day-wise references to Modules
	 * First dimension ranges from 0 to 4, represents days of the working week
	 * 
	 * @param arr  Module[] whose day-wise representation is sought
	 * @return	   Module[][] containing day-wise representation of Module[] arr
	 */
	public Module[][] convertModuleArrayToSchedule(Module[] arr)
	{
		Module[][] schedule = new Module[5][0];
		for (int i = 0; i < arr.length; i++)
		{
			if (String.valueOf(arr[i].days).contains("M"))
				schedule[0] = this.addModule(arr[i], schedule[0]);
			if (String.valueOf(arr[i].days).contains("T"))
				schedule[1] = this.addModule(arr[i], schedule[1]);
			if (String.valueOf(arr[i].days).contains("W"))
				schedule[2] = this.addModule(arr[i], schedule[2]);
			if (String.valueOf(arr[i].days).contains("R"))
				schedule[3] = this.addModule(arr[i], schedule[3]);
			if (String.valueOf(arr[i].days).contains("F"))
				schedule[4] = this.addModule(arr[i], schedule[4]);
		}

		return schedule;
	}

	/**
	 * Removes elements of Module[] at given index and the index following it
	 * 
	 * @param inputArray  Module[] from which elements are to be removed
	 * @param k			  Index of first removal
	 * @return			  Module[] with elements at k and k+1 removed
	 */
	public int[] removeFromArray(int[] inputArray, int k) //Removes elements at k and k + 1
	{
		int[] newArray = new int[inputArray.length - 2];
		for (int i = 0; i < k; i++)
		{
			newArray[i] = inputArray[i];
		}
		for (int i = k; i < newArray.length; i++)
		{
			newArray[i] = inputArray[i + 2];
		}
		return newArray;
	}

	/**
	 * Compares two Module[] (compares contents, not memory locations)
	 * 
	 * @param module1
	 * @param module2
	 * @return			boolean representing whether module1 and module2 are equal
	 */
	public boolean compareModuleArrays(Module[] module1, Module[] module2)
	{
		if (module1.length != module2.length)
			return false;

		for (int i = 0; i < module1.length; i++)
		{
			if (module1[i].equals(module2[i]) == false)
			{
				return false;
			}
		}

		return true;
	}

	/**
	 * Creates a deep copy of Module
	 * 
	 * @param inputModule	Module whose deep copy is needed
	 * @return				Module which is a deep copy of inputModule
	 */
	public Module deepCopyModule(Module inputModule)
	{
		Module output = new Module(String.valueOf(inputModule.days), inputModule.startTime, inputModule.endTime, inputModule.building);
		return output;
	}

	/**
	 * Creates a deep copy of Module[]
	 * 
	 * @param inputArray	Module[] whose deep copy is needed
	 * @return				Module[] which is a deep copy of inputArray
	 */
	public Module[] deepCopyModuleArray(Module[] inputArray)
	{
		Module[] outputArray = new Module[inputArray.length];
		for (int i = 0; i < outputArray.length; i++)
		{
			outputArray[i] = this.deepCopyModule(inputArray[i]);
		}
		return outputArray;
	}
	
	/**
	 * Converts string representing location to a format usable in URLs
	 * Primarily for making calls to the Distance Matrix API
	 * 
	 * @param locationString	String representing location
	 * @return					String representing location in URL format
	 */
	public String parseLocationToURLFormat(String locationString)
	{
		StringBuffer buffer = new StringBuffer();
		buffer.append(locationString.toLowerCase());
		while (buffer.indexOf(" ") > -1){
			buffer.setCharAt(buffer.indexOf(" "), '+');
		}
		return buffer.toString();
	}
	

	/**
	 * Sorts the classes on a day in chronological order
	 * 
	 * @param scheduleForOneDay		Module[] representing schedule on a day
	 * @return						Module[] representing same schedule with classes in chronological order
	 */
	public Module[] sortByTimeScheduleForOneDay(Module[] scheduleForOneDay)
	{
		Module[] finalSchedule = new Module[0];
		
		Module[] scheduleForOneDay2 = this.deepCopyModuleArray(scheduleForOneDay);
		
		int nextIndex = 0;
		int earliest = 9999;
		int count = 0;
		while (count < scheduleForOneDay2.length)
		{
			earliest = 9999;
			nextIndex = 0;
		for (int i = 0;i<scheduleForOneDay2.length;i++)
		{
			if (scheduleForOneDay2[i].startTime < earliest)
			{
				nextIndex = i;
				earliest = scheduleForOneDay2[i].startTime;
			}
		}
		
		finalSchedule = this.addModule(scheduleForOneDay2[nextIndex], finalSchedule);
		
		scheduleForOneDay2[nextIndex].startTime = 9999;
		count++;
		}
		
		return finalSchedule;
	}
}
