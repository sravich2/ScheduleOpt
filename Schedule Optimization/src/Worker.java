import java.util.Random;

/**
 * Contains helper methods for use throughout program
 * NOTE: Edit/add Javadoc documentation if you edit/add any methods to this class 
 * @author Sachin
 *
 */
public class Worker
{

	/**
	 * Converts time in 24-hour format (Example: 1400, 2000) to decimal equivalent.
	 * 
	 * @param time string containing time in 24-hour format
	 * @return     time in decimal format
	 */
	public int convertTimeBase60To10(String time)
	{
		int timeIn10;
		int hourIn60 = Integer.valueOf(time.substring(0, 2));
		if(time.substring(time.length()-2).equals("PM") && hourIn60!=12){hourIn60+=12;}
		int minuteIn60 = Integer.valueOf(time.substring(3,5));
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
	public Module[] chooseRandomModules(Course inputCourse)
	{
		Random rand = new Random();
		Module[] finalCourses = new Module[inputCourse.modulesAvailable.length];
		for (int i = 0; i < finalCourses.length; i++)
			finalCourses[i] = inputCourse.modulesAvailable[i][(int) (rand.nextDouble() * this.realLength(inputCourse.modulesAvailable[i]))];
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
			newModuleArray[i] = moduleArr[i];
		}
		newModuleArray[newModuleArray.length - 1] = moduleToAdd;
		return newModuleArray;
	}

	/**
	 * Appends one Module[] to the end of another Module[] 
	 * 
	 * @param addThisModuleArray  Module[] to be appended
	 * @param finalClasses        Module[] to which Module[] addThisModuleArray is to be appended
	 * @return					  Module[] with Module[] addThisModuleArray appended to the end of Module[] finalClasses
	 */
	public Module[] addModuleArray(Module[] addThisModuleArray, Module[] finalClasses)
	{
		Module[] moduleWithAddition = new Module[addThisModuleArray.length + finalClasses.length];
		for (int i = 0; i < finalClasses.length; i++)
		{
			moduleWithAddition[i] = finalClasses[i];
		}
		int i = 0;
		while (i < addThisModuleArray.length && addThisModuleArray[i] != null)
		{
			moduleWithAddition[i + finalClasses.length] = addThisModuleArray[i];
			i++;
		}
		return moduleWithAddition;
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
	
	public String toString(Module[][] schedule)
	{
		StringBuffer output = new StringBuffer();
		
		for (int i = 0;i<5;i++)
		{
			output.append("\n");
			output.append("Day "+(i+1)+": ");
			
			for (int j = 0;j<schedule[i].length;j++)
				output.append(this.convertTimeBase10To60(schedule[i][j].startTime)+" - " + this.convertTimeBase10To60(schedule[i][j].endTime)+" | ");
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
			{
				schedule[0] = this.addModule(arr[i], schedule[0]);
			}
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
		Module output = new Module(String.valueOf(inputModule.days), inputModule.startTime, inputModule.endTime);
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

}
