package color;

import flightData.Flight;

/**
 * Created by Kevin on 4/26/2016.
 *
 * Class that handles mathematical calculations involved in finding the distance between flights and coordinate tuples.
 */
public class DistanceCalculator {

    /**
     * Main driver for testing purposes.
     */
    public static void main(String[] args) {
    }

    /**
     * Returns the Euclidean distance between two points in n-dimensional space. Coordinate tuples must have the same
     * length.
     * @param pointA set of coordinates A
     * @param pointB set of coordinates B
     * @return The Euclidean distance between two points.
     */
    public static double getDistance(double[] pointA, double[] pointB) {
        if (pointA.length != pointB.length) {
            throw new IllegalArgumentException("Points must be in the same dimension to calculate distance.");
        }
        double sum = 0;
        for (int i = 0; i < pointA.length; i++) {
            sum += Math.pow(pointA[i] - pointB[i], 2);
        }
        return Math.sqrt(sum);
    }

    public static double getFlightDistance(Flight f1, Flight f2) {
        double dist = getMinDist(f1, f2);
        return dist == 0 ? angleBetweenLines(f1, f2) / (Math.PI/2) : dist + 1;
    }

    // Returns true only if the slope of AB is less than the slope of AC (if ABC are arranged counterclockwise).
    private static boolean ccw(double[] pointA, double[] pointB, double[] pointC) {
        return (pointC[1]-pointA[1])*(pointB[0]-pointA[0]) > (pointB[1]-pointA[1])*(pointC[0]-pointA[0]);
    }

    /**
     *
     * @param f1 Flight 1
     * @param f2 Flight 2
     * @return True only if the line segments formed by the flights' start and end points intersect.
     */
    private static boolean intersect(Flight f1, Flight f2) {
        double[] a1 = f1.getStartCoordinate();
        double[] b1 = f1.getEndCoordinate();
        double[] a2 = f2.getStartCoordinate();
        double[] b2 = f2.getEndCoordinate();

        if (a1[0] == a2[0] && a1[1] == a2[1] || a1[0] == b2[0] && a1[1] == b2[1] || b1[0] == a2[0] && b1[1] == a2[1] ||
                b1[0] == b2[0] && b1[1] == b2[1]) {
            return true;
        } //if they share a point, they intersect

        return ccw(a1, a2, b2) != ccw(b1, a2, b2) && ccw(a1, b1, a2) != ccw(a1, b1, b2);
    }

    /**
     * @param f1 flight 1
     * @param f2 flight 2
     * @return the minimum angle between two flight tracks in radians as a positive number.
     */
    private static double angleBetweenLines(Flight f1, Flight f2) {
        double angle1 = Math.atan2(f1.getStartCoordinate()[1] - f1.getEndCoordinate()[1],
                f1.getStartCoordinate()[0] - f1.getEndCoordinate()[0]);
        double angle2 = Math.atan2(f2.getStartCoordinate()[1] - f2.getEndCoordinate()[1],
                f2.getStartCoordinate()[0] - f2.getEndCoordinate()[0]);

        double angle = Math.abs(angle1 - angle2);
        angle = angle > Math.PI/2 ? Math.PI - angle : angle;
        return angle;
    }

    private static double getMinDist(Flight f1, Flight f2) {
        double dist = 9999999;
        double[][] f1c = f1.getCoordinateList();
        double[][] f2c = f2.getCoordinateList();

        for (int i = 0; i < f1c.length; i++) {
            for (int j = 0; j < f2c.length; j++) {
                dist = Math.min(dist, getDistance(f1c[i], f2c[j]));
            }
        }
        return dist;
    }
}
