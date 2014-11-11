import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
 

public class InfoSandbox {

	public static void main(String[] args) throws MalformedURLException, IOException {
		InfoRetreiver ir = new InfoRetreiver();
		String url = "http://courses.illinois.edu/cisapp/explorer/schedule/2014/fall/MATH/241.xml";
		String re = "<section id=\"[1-9].*?\" href=\"(.*)\">(.*)<";
		re = "<section id=\"([1-9].*?)\" href=\"(.*?)\">(.*?)<";
		
		System.out.println(check("MATH",233));
		System.out.println(check("CS", 0));
		System.out.println(check("ancd",1));
		String contents = ir.getPageXML(url);
		String sections = contents.substring(contents.indexOf("<sections>")+10);
		//ir.getCourseInfo("HIST",241,2014,"fall");
		//ir.getCourseInfo("CS",241,2014,"fall");
		ir.getCourseInfo("CS",173,2015,"spring");

	}
	//@author tushar
	public static boolean check(String dept, int course)
	{
		dept=dept.toUpperCase();
		boolean check=false;
		TextIO.readFile("dept.txt");
		String[] list= new String[400];
		String baseUrl=new String("https://courses.cites.illinois.edu/schedule/2015/spring/");
		int i=0;
		while (!TextIO.eof())
		{
			String line=TextIO.getln();
			line=line.trim();
			if (line.equals("<td>"))
				list[i++]=TextIO.getln().trim();
			//TextIO.getln();
			//TextIO.getln();
		}
		int a=0;
		while ( a<list.length && !check)
			{
				if (dept.equals(list[a]))
					check=true;
				a++;
			}
		
		if (check)
		{
			check=false;
			String fileName=new String("lib\\classes\\"+dept+".txt");
			TextIO.readFile(fileName);
			String[] classes=new String[100];
			while (!TextIO.eof()&& !check)
			{
				String line=TextIO.getln();
				String temp=dept+" "+course;
				line=line.trim();
				if (line.equals("<td>"))
					check= temp.equals(TextIO.getln().trim());
			}
		}
		return check;
	}

}
