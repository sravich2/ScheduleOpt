import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
 

public class InfoSandbox2 {

	public static void main(String[] args) throws MalformedURLException, IOException {
		InfoRetriever2 ir = new InfoRetriever2();
		long time = System.currentTimeMillis();
		ir.getSessionCoursesInfo("spring", "2015");
		System.out.println(System.currentTimeMillis()-time);
	}
	//@author tushar
	public static boolean checkDept(String dept)
	{
		boolean check=false;
		TextIO.readFile("dept.txt");
		String[] list= new String[200];
		int i=0;
		while (!TextIO.eof())
		{
			String line=TextIO.getln();
			line=line.trim();
			if (line.equals("<td>"))
				list[i++]=TextIO.getln().trim();
			TextIO.getln();
			TextIO.getln();
		}
		
		for (int a=0; a<list.length;a++)
			if (dept.equals(list[a]))
				check=true;
		return check;
	}

}
