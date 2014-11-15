import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
 

public class InfoSandbox {

	public static void main(String[] args) throws MalformedURLException, IOException {
		InfoRetreiver ir = new InfoRetreiver();
		String url = "http://courses.illinois.edu/cisapp/explorer/schedule/2014/fall/MATH/241.xml";
		String re = "<section id=\"[1-9].*?\" href=\"(.*)\">(.*)<";
		re = "<section id=\"([1-9].*?)\" href=\"(.*?)\">(.*?)<";
		
		System.out.println(check("MATH",241));
		System.out.println(check("CS", 0));
		System.out.println(check("ancd",1));
		String contents = ir.getPageXML(url);
		String sections = contents.substring(contents.indexOf("<sections>")+10);
		//ir.getCourseInfo("HIST",241,2014,"fall");
		//ir.getCourseInfo("CS",241,2014,"fall");
		long currentTime = System.currentTimeMillis();
		ir.getCourseInfo("ENG",100,2014,"fall");
		System.out.println((System.currentTimeMillis()-currentTime));
	}
	//@author tushar
	public static boolean check(String dept, int course)
	{
		dept=dept.toUpperCase();
		boolean check=false;
		TextIO.readFile("dept.txt");
		
		while (!TextIO.eof()&& !check)
		{
			check=dept.equals(TextIO.getln());
		}
		
		if (check)
		{
			check=false;
			String fileName=new String("lib\\classes\\"+dept+".txt");
			TextIO.readFile(fileName);
			while (!TextIO.eof()&& !check)
			{
				String temp=new String(dept+" "+course);
				check=temp.equals(TextIO.getln());
			}
		}
		return check;
	}

}
