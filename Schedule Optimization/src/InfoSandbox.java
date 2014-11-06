import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
 

public class InfoSandbox {

	public static void main(String[] args) throws MalformedURLException, IOException {
		InfoRetreiver ir = new InfoRetreiver();
		String url = "http://courses.illinois.edu/cisapp/explorer/schedule/2014/fall/MATH/241.xml";
		String re = "<section id=\"[1-9].*?\" href=\"(.*)\">(.*)<";
		re = "<section id=\"([1-9].*?)\" href=\"(.*?)\">(.*?)<";
		
		String contents = ir.getPageXML(url);
		String sections = contents.substring(contents.indexOf("<sections>")+10);
		ir.getCourseInfo("HIST",241,2014,"fall");
		//ir.getCourseInfo("CS",241,2014,"fall");
		ir.getCourseInfo("MATH",241,2015,"spring");

	}

}
