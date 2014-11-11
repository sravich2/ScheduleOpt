public class sandbox
{
	public static void main(String[] args)
	{
		DistanceTimeMatrix time = new DistanceTimeMatrix();
		double[] random = time.getTravelTimeAndDistance("http://www.mapquestapi.com/directions/v2/route?key=Fmjtd%7Cluurn9u7ng%2Cbx%3Do5-9wznl0&outFormat=json&routeType=pedestrian&enhancedNarrative=true&locale=en_US&from=1304+w.+springfield+urbana&to=201+n.+goodwin+Urbana");
	}
}
