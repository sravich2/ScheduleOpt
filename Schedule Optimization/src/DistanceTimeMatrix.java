
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.*;
import java.util.Arrays;

import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.ParseException;
import org.json.simple.parser.JSONParser;

import org.json.*;
public class DistanceTimeMatrix {
	
	
	public double[] getTravelTimeAndDistance(String urlString) throws Exception {
		Worker help = new Worker();
		double[] values = new double[2];
		
		BufferedReader reader = null;
	    try {
	        URL url = new URL(urlString);
	        reader = new BufferedReader(new InputStreamReader(url.openStream()));
	        
	        String travelTime = "";
	        String distance = "";
	        
	        String line = "";
	        for (line = reader.readLine();line != null; line = reader.readLine())
	        {
	        	if (line.indexOf("km")>-1 || (line.indexOf("mi")>-1 && line.indexOf("min") == -1)){
	        		values[1]= help.parseJSONDistanceToInteger((line.substring(28,31)));
	        	}
	        	if (line.indexOf("mins") > -1)
	        		values[0] = (help.parseJSONTimeToInteger(line.substring(28, line.indexOf("mins")+4)));
	        }
	        //System.out.println(distance);
	        //System.out.println(travelTime);
	        
	        return values;
	    } finally {
	        if (reader != null)
	            reader.close();
	    }
	}
}