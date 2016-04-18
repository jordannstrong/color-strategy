package color;

import flightData.Flight;

import java.util.List;

/**
 * Distance calculator to tell which flights are close together.
 * Is now being treated as an extra feature, so it will remain as an empty
 * class for now.
 */

public class DistanceCalculator
{
	/*private List<Flight> flightList;

	public static void main(String[] args) {
		System.out.println("Hello.");
	}

	/*private double distance(Flight f1, Flight f2) {
		double dLat = Math.abs(f2.getLatitude()-f1.getLatitude());
		double dLon = Math.abs(f2.getLongitude()-f1.getLongitude());
		double distance = (dLon * dLon) + (dLat * dLat);
		return Math.sqrt(distance);
	}*/


	public List<Flight> getSortedFlights()
	{
		return this.flightList;
	}

	/**
	 *
	 * @return The minimum distance between two points on two different flight
	 * tracks.
	 */
	private double minDist(Flight f1, Flight f2)
	{
		double startDistance =  distance(f1.getStartCoordinate(),
				f2.getStartCoordinate());
		double endDistance   =  distance(f1.getEndCoordinate(),
				f2.getEndCoordinate());
		return Math.min(startDistance, endDistance);
	}

	/**
	 *
	 * @param pointA
	 * @param pointB
	 * @return Euclidean distance between two points.
	 */
	private double distance(double[] pointA, double[] pointB)
	{
		if (pointA.length != 2 || pointB.length != 2)
		{
			throw new IllegalArgumentException();
		}

		return Math.sqrt(Math.pow(pointB[0]-pointA[0], 2) +
				Math.pow(pointB[1] - pointA[1], 2));
	}
}