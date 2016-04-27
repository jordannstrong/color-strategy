package color;

import flightData.Flight;

/**
 * Created by Kevin on 4/26/2016.
 *
 * Class that handles mathematical calculations involved in finding the distance between flights and coordinate tuples.
 */
public class DistanceCalculator {
    /**
     * Returns the Euclidean distance between two points in n-dimensional space. Coordinate tuples must have the same
     * length.
     * @param pointA set of coordinates A
     * @param pointB set of coordinates B
     * @return The Euclidean distance between two points.
     */
    public static float getDistance(float[] pointA, float[] pointB) {
        if (pointA.length != pointB.length) {
            throw new IllegalArgumentException("Points must be in the same dimension to calculate distance.");
        }
        float sum = 0;
        for (int i = 0; i < pointA.length; i++) {
            sum += Math.pow(pointA[i] - pointB[i], 2);
        }
        return (float) Math.sqrt(sum);
    }

    public static float getFlightDistance(Flight f1, Flight f2) {
        return 0; //TODO
    }

    // Returns true only if the slope of AB is less than the slope of AC (if ABC are arranged counterclockwise).
    private boolean ccw(double[] pointA, double[] pointB, double[] pointC) {
        return (pointC[1]-pointA[1])*(pointB[0]-pointA[0]) > (pointB[1]-pointA[1])*(pointC[0]-pointA[0]);
    }

    /**
     *
     * @param f1 Flight 1
     * @param f2 Flight 2
     * @return True only if the line segments formed by the flights' start and end points intersect.
     */
    private boolean intersect(Flight f1, Flight f2) {
        double[] a1 = f1.getStartCoordinate();
        double[] b1 = f1.getEndCoordinate();
        double[] a2 = f2.getStartCoordinate();
        double[] b2 = f2.getEndCoordinate();

        return ccw(a1, a2, b2) != ccw(b1, a2, b2) && ccw(a1, b1, a2) != ccw(a1, b1, b2);
    }
}
