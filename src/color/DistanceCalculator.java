package color;

import flightData.Flight;

/**
 * Created by Kevin on 4/26/2016.
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
}
