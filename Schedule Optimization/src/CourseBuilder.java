import java.util.ArrayList;

public class CourseBuilder
{

	Worker help = new Worker();

	public Module[] buildCourseSchedule(Course[] coursesTaken)
	{
		while (true)
		{
			//Module[] finalClasses = new Module[0];
			ArrayList<Module> finalClasses = new ArrayList<Module>();

			for (int i = 0; i < coursesTaken.length; i++)
			{
				ArrayList<Module> modulesForThisCourse = new ArrayList<Module>();
				modulesForThisCourse = (help.chooseRandomModules(coursesTaken[i]));
				//Module[] modulesForThisCourse = help.chooseRandomModules(coursesTaken[i]);
				finalClasses.addAll(modulesForThisCourse);
				//finalClasses = help.addModuleArray(modulesForThisCourse, finalClasses);
			}

			String output = "";
			int size;
			size = finalClasses.size();
			
			outerloop: 
			for (int i = 0; i < size; i++)
			{
				for (int j = 0; j < size; j++)
				{
					if ((i != j) && (help.checkConflict(finalClasses.get(i), finalClasses.get(j))))
					{
						break outerloop;
					}
					if (i == size - 1 && j == size - 1)
					{
						//output = (help.toString(finalClasses.toArray(new Module[size])));
						//System.out.println("FOUND: \n"+ output);
						return finalClasses.toArray(new Module[size]);
					}
				}
			}
		}
		//	return "Didn't get anything";
	}
	
	public Module[] geneticBuild(Module[][] population)
	{
		return null;
	}
	
	public Module[][] generatePopulation(Course[] coursesTaken)
	{
		final int POPULATION_SIZE = 500;
		ArrayList<Module[]> population = new ArrayList<Module[]>(POPULATION_SIZE);
		int count = 0;
		while (count < 1000)
		{
			Module[] prospectiveSchedule = this.buildCourseSchedule(coursesTaken);
			for (int i = 0;i<count;i++)
			{
				if (help.compareModuleArrays(prospectiveSchedule, population.get(i)))
				{
					prospectiveSchedule = this.buildCourseSchedule(coursesTaken);
					i = -1;
				}
			}
			population.add(prospectiveSchedule);
		}
		return population.toArray(new Module[1000][]);
	}
	
	public Module[] geneticMutate(Module[] inputSchedule)
	{
		return null;
	}

}
