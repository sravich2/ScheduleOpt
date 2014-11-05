
public class CourseBuilder {

	Worker help = new Worker();

	public Module[] buildCourseSchedule(Course[] coursesTaken)
	{
	while (true)
	{
		Module[] finalClasses = new Module[0];
		
		for (int i = 0;i<coursesTaken.length;i++)
		{
			Module[] modulesForThisCourse = help.chooseRandomModules(coursesTaken[i]);
			finalClasses = help.addModuleArray(modulesForThisCourse, finalClasses);
		}
		
		String output = "";
		
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
					output = (help.toString(finalClasses));
					//System.out.println("FOUND: \n"+ output);
					return finalClasses;
				}
			}
		}
	}
//	return "Didn't get anything";
	}
		
	
}
