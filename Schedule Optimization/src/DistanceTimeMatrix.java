
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Arrays;
public class DistanceTimeMatrix {
	
	
	public double[] getTravelTimeAndDistance(String urlString) {
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
	        System.out.println(values[0]);
	        System.out.println(values[1]);
	        
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
	
}