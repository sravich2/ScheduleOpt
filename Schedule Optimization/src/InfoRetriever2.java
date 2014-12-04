/**
 * 
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * @author Sachin
 *
 */
//http://www.mkyong.com/java/how-to-create-xml-file-in-java-dom/
//http://www.mkyong.com/java/how-to-read-xml-file-in-java-dom-parser/
public class InfoRetriever2{
	public int val;
	StringBuffer baseUrl = new StringBuffer("http://courses.illinois.edu/cisapp/explorer/schedule/");
	DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	DocumentBuilder dBuilder;

	public void getSessionCoursesInfo(Document doc, String session, String year)
	{
		StringBuffer sessionUrl = new StringBuffer(baseUrl);
		sessionUrl.append(year + "/" + session + ".xml");
		try
		{
			dBuilder = dbFactory.newDocumentBuilder();
			Document sessionInfo = dBuilder.parse((sessionUrl.toString()));
			NodeList deptList = sessionInfo.getElementsByTagName("subject");

			for (int i = 0; i < deptList.getLength(); i++)
			{
				Node dept = deptList.item(i);
				if (dept.getNodeType() == Node.ELEMENT_NODE)
				{
					Element currentDept = (Element) dept;
					String Dept = currentDept.getAttribute("id");
					String deptName = currentDept.getTextContent();
					System.out.println(deptName + "\n\n");
					getDepartmentCoursesInfo(Dept, year, session);
				}
			}
		} catch (IOException ioe)
		{
			System.out.println(ioe.getMessage());
		} catch (SAXException saxe)
		{
			System.out.println(saxe.getMessage());
		} catch (ParserConfigurationException pce)
		{
			System.out.println(pce.getMessage());
		}
	}

	public void getDepartmentCoursesInfo(Document doc, String dept, String year, String session)
	{
		StringBuffer deptUrl = new StringBuffer(baseUrl);
		deptUrl.append(year + "/" + session + "/" + dept + ".xml");
		try
		{
			dBuilder = dbFactory.newDocumentBuilder();
			Document deptInfo = dBuilder.parse(deptUrl.toString());
			NodeList coursesOffered = deptInfo.getElementsByTagName("course");

			for (int i = 0; i < coursesOffered.getLength(); i++)
			{
				Node course = coursesOffered.item(i);
				if (course.getNodeType() == Node.ELEMENT_NODE)
				{
					Element currentCourse = (Element) course;
					String courseNum = (currentCourse.getAttribute("id"));
					System.out.println(courseNum + "\n\n");
					getCourseInfo(dept, courseNum, year, session);
				}
			}

		} catch (IOException ioe)
		{
			System.out.println(ioe.getMessage());
		} catch (SAXException saxe)
		{
			System.out.println(saxe.getMessage());
		} catch (ParserConfigurationException pce)
		{
			System.out.println(pce.getMessage());
		}

	}

	public void getCourseInfo(Document doc, String dept, String courseNum, String year, String session)
	{

		StringBuffer courseUrl = new StringBuffer(baseUrl);
		courseUrl.append(year + "/" + session + "/" + dept + "/" + courseNum);
		StringBuffer url = new StringBuffer(courseUrl);
		url.append(".xml");
		//String XML = getPageXML(url.toString());
		//System.out.println(XML);

		ArrayList<String> CRNList = new ArrayList<String>();
		ArrayList<String> sectionName = new ArrayList<String>();

		try
		{
			dbFactory = DocumentBuilderFactory.newInstance();
			dBuilder = dbFactory.newDocumentBuilder();

			Document courseInfo = dBuilder.parse(url.toString());
			//optional, but recommended
			//read this - http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
			courseInfo.getDocumentElement().normalize();

			NodeList sectionList = courseInfo.getElementsByTagName("section");

			for (int i = 0; i < sectionList.getLength(); i++)
			{
				Node currentSection = sectionList.item(i);

				if (currentSection.getNodeType() == Node.ELEMENT_NODE)
				{
					Element sectionElement = (Element) currentSection;
					CRNList.add(sectionElement.getAttribute("id"));
					sectionName.add(sectionElement.getTextContent());
				}
			}

		} catch (IOException ioe)
		{
			System.out.println(ioe.getLocalizedMessage());
		} catch (ParserConfigurationException pce)
		{
			System.out.println(pce.getMessage());
		} catch (SAXException saxe)
		{
			System.out.println(saxe.getMessage());
		}

		for (int i = 0; i < CRNList.size(); i++)
		{
			//System.out.println("Section: " + sectionName.get(i) + "\n");
			url = new StringBuffer(courseUrl);
			url.append("/" + CRNList.get(i) + ".xml");
			try
			{
				Document currentSectionInfo = dBuilder.parse(url.toString());

				NodeList meeting = currentSectionInfo.getElementsByTagName("meeting");
				Element current = (Element) meeting.item(0);
				/*System.out.println("CRN: "+CRNList.get(i));
				System.out.println("Type: " + current.getElementsByTagName("type").item(0).getTextContent());
				System.out.println("Status: " + currentSectionInfo.getElementsByTagName("enrollmentStatus").item(0).getTextContent());
				System.out.println("Time Range: " + current.getElementsByTagName("start").item(0).getTextContent() + 
						" - " + current.getElementsByTagName("end").item(0).getTextContent());
				System.out.println("Date Range: " + currentSectionInfo.getElementsByTagName("startDate").item(0).getTextContent() + 
						" - " + currentSectionInfo.getElementsByTagName("endDate").item(0).getTextContent()); 
				System.out.println("Days: " + current.getElementsByTagName("daysOfTheWeek").item(0).getTextContent());
				System.out.println("Building name: " + current.getElementsByTagName("buildingName").item(0).getTextContent()+"\n\n");*/
			} catch (SAXException saxe)
			{
				System.out.println(saxe.getMessage());
			} catch (IOException ioe)
			{
				System.out.println(ioe.getMessage());
			}

		}
}
}