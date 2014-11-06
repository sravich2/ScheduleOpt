
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.*;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.ParseException;
import org.json.simple.parser.JSONParser;

import org.json.*;
public class DistanceTimeMatrix {
	

	private static String readUrl(String urlString) throws Exception {
	    BufferedReader reader = null;
	    try {
	        URL url = new URL(urlString);
	        reader = new BufferedReader(new InputStreamReader(url.openStream()));
	        StringBuffer buffer = new StringBuffer();
	        String line = "";
	        for (line = reader.readLine();line != null; line = reader.readLine())
	        {
	        	if (line.indexOf("km")>-1)
	        		buffer.append("Distance: "+line.substring(28,line.indexOf("km")+2));
	        	if (line.indexOf("mins") > -1)
	        	buffer.append("\nTravel time : "+line.substring(28, line.indexOf("mins")+4));
	        	
	        }
	        
	        
	        return buffer.toString();
	    } finally {
	        if (reader != null)
	            reader.close();
	    }
	}

  public static void main(String[] args) throws Exception {
	  
	  String s = readUrl("https://maps.googleapis.com/maps/api/distancematrix/json?origins=noyes+laboratory&destinations=champaign+walmart&mode=walking");
	  System.out.println(s);
	  
	 
	  
	  
  }
}