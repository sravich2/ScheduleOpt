
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
public class DistanceTimeMatrix {
	
	
	public static double[] getTravelTimeAndDistance(String fromAddress, String toAddress) {
		Worker help = new Worker();
		double[] values = new double[2];

		JSONParser parser = new JSONParser();

		String urlString = "http://www.mapquestapi.com/directions/v2/route?key=Fmjtd%7Cluurn9u7ng%2Cbx%3Do5-9wznl0&outFormat=json&routeType=pedestrian&locale=en_US&from="
				+ help.parseLocationToURLFormat(fromAddress) + "&to=" + help.parseLocationToURLFormat(toAddress);
		long time = System.currentTimeMillis();
		
			try
			{
				URL url = new URL(urlString);
				BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
				String s = reader.readLine();

				Object obj = parser.parse(s);

				JSONObject jObject = (JSONObject) obj;
				JSONObject route = (JSONObject) jObject.get("route");
				values[0] = (double) route.get("distance");
				values[1] = (double) (((long) route.get("time")));
				reader.close();
			} catch (ParseException pe)
			{
				System.out.println("position: " + pe.getPosition());
				System.out.println(pe);
			} catch (IOException ioe)
			{
				System.out.println(ioe.getMessage());
			}

		
		
		/*long time = System.currentTimeMillis();
		for (int i = 0;i<10;i++){
	    try {
	    	
	    	String urlString = "http://www.mapquestapi.com/directions/v2/route?key=Fmjtd%7Cluurn9u7ng%2Cbx%3Do5-9wznl0&outFormat=json&routeType=pedestrian&locale=en_US&from="+help.parseLocationToURLFormat(fromAddress)+"&to="+help.parseLocationToURLFormat(toAddress);
	    	//System.out.println(urlString);
	    	URL url = new URL(urlString);
	        BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
	        
	        String travelTime = "";
	        String distance = "";
	        
	        String line = "";
	        line = reader.readLine();
	        //System.out.println(line);
	        values[0] = Double.valueOf(line.substring(line.indexOf("distance")+10, line.indexOf("distance")+19).split(",")[0]);
	        values[1] = Double.valueOf(line.substring(line.indexOf("time")+6, line.indexOf("time")+13).split(",")[0]);
	        reader.close();
	        
	    } catch (IOException e)
	    {
	    	System.out.println(e.getMessage());
	    }    	}
		System.out.println(System.currentTimeMillis()-time);
		*/
		
	    /*
	finally {
	        if (reader != null)
	        	try{
	        		reader.close();	
	        	}
	            catch (IOException e)
	            {
	            	System.out.println(e.getMessage());
	            }
	    }*/
	    return values;
	}
	public static void main(String[] args)
	{
		Worker help = new Worker();
		
		double[] matrix = getTravelTimeAndDistance("607 S. MATHEWS URBANA IL", "1110 W. MAIN URBANA IL");
		System.out.println(Arrays.toString(matrix));
		
	}
	
}