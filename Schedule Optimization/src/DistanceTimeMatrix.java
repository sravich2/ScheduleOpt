
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Arrays;
public class DistanceTimeMatrix {
	
	
	public static double[] getTravelTimeAndDistance(String urlString) {
		Worker help = new Worker();
		double[] values = new double[2];
		
		BufferedReader reader = null;
	    try {
	        URL url = new URL(urlString);
	        reader = new BufferedReader(new InputStreamReader(url.openStream()));
	        
	        String travelTime = "";
	        String distance = "";
	        
	        String line = "";
	        line = reader.readLine();
	        values[0] = Double.valueOf(line.substring(line.indexOf("distance")+10, line.indexOf("distance")+19).split(",")[0]);
	        values[1] = Double.valueOf(line.substring(line.indexOf("time")+6, line.indexOf("time")+13).split(",")[0]);
	        
	        return values;
	    } catch (IOException e)
	    {
	    	System.out.println(e.getMessage());
	    	
	    } finally {
	        if (reader != null)
	        	try{
	        		reader.close();	
	        	}
	            catch (IOException e)
	            {
	            	System.out.println(e.getMessage());
	            }
	    }
	    return null;
	}
	public static void main(String[] args)
	{
		double[] matrix = getTravelTimeAndDistance("http://www.mapquestapi.com/directions/v2/route?key=Fmjtd%7Cluurn9u7ng%2Cbx%3Do5-9wznl0&outFormat=json&routeType=pedestrian&enhancedNarrative=true&locale=en_US&from=1304+w+springfield+avenue+urbana&to=201+n+goodwin+avenue+Urbana");
		System.out.println(Arrays.toString(matrix));
	}
	
}