package color;

import FileIO.KMLWriter;

import java.awt.*;
import java.awt.color.ColorSpace;
import java.util.LinkedList;
import java.util.Random;

public class ColorAssigner {
    /**
     * Main driver for testing. Currently generates a few dummy flights and saves it to a KML file in this working
     * directory.
     */
    public static void main(String[] args) {

    }

    /**
     * Returns the color that should be assigned to the nth flight.
     * @param n the nth flight
     * @return A color in RGB color scheme.
     */
    public static int getColor(int n) {
        Random rand = new Random();
        return Color.HSBtoRGB((float) getHue(n), 1, 1);//rand.nextFloat() % 0.4f + 0.6f, rand.nextFloat() % 0.4f + 0.6f);
    }

    /*private String color(Flight f1, Flight f2) {
        int lMax = 100;
        int lMin = 0;
        int aMax = 127;
        int aMin = -128;
        int bMax = 127;
        int bMin = -128;

        double dist = DistanceCalculator.minDist( f1, f2 );

        // Brute force a LAB color with a color equidistant to actual distance
        for(int L = lMin; L <= lMax ; L++)
        {
            for(int A = aMin; A <= aMax ; A++)
            {
                for(int B = bMin; B <= bMax ; B++)
                {
                    if(dist == Math.pow( (f1.L - L), 2 ) + Math.pow( (f1.A - A), 2 ) + Math.pow( (f1.B - B), 2 ));
                    {
                        return Conversion.LABtoRGB( L, A, B );
                    }
                }
            }
        }

    }

    public LinkedList<Flight> getColoredFlights()
    {
        return this.flightlist;
    }*/

    /**
     * Method for getting the hue value of the best color for the given iteration number.
     *
     * The hue value is part of the HSL color scheme.
     *
     * The method returns a hue of 0 (red) on n = 0, then its complementary color at a hue of 180 (green) on n = 1,
     * then the next base color at a hue of 90 (yellow) at n = 2, followed by its complement at n = 3, and so on and so
     * forth. Essentially, the color wheel is split into halves, then quarters, then eighths, etc. to ensure that each
     * color is visually distinguishable from the previous color. At a certain point, the colors chosen will no longer
     * be distinguishable from the other colors. We can change the method to use darker/lighter shades, but for now
     * this is a limitation of the program. The best way to ensure the biggest possible range of distinguishable colors
     * is to use the LAB color space and choose the colors based on the distance of the edges.
     *
     * Note that the method does not provide the other 2 values of HSL, Saturation and Lightness.
     * @param n the nth group to color
     * @return hue value (h) of color.
     */
    private static double getHue(int n) {
        if (n == 0) {
            return 0;
        }

        double num;
        double denom;
        int exp;

        double log = Math.log(n)/Math.log(2);

        if (log == Math.floor(log)) { // check if n is a power of 2
            exp = (int) log + 1;
            num = 1;
        }
        else {
            exp = (int) Math.ceil(log);
            int cp = (int) Math.pow(2, Math.floor(log)); // find closest power of 2 from n
            num = 2*(n - cp) + 1;
        }

        denom = (int) Math.pow(2, exp);

        return num/denom;
    }

    /**
     *
     * @param x x
     * @param y y
     * @param z z
     * @param radius radius from the center of the sphere to its surface
     * @return an array containing the x,y,z coordinates of a random point on the surface of the sphere
     *
     */
    public static double[] getPointOnSphere(float x, float y, float z, float radius) {
        ColorSpace labSpace = new LABSpace();
        Random rand = new Random();
        float u = rand.nextFloat(); // random u between 0 and 1
        float v = rand.nextFloat(); // random v between 0 and 1
        float theta = 2 * 3.14159265359f * u;
        float phi = (float) Math.acos(2 * v - 1);

        float x1 = (float) (x + (radius * Math.sin(phi) * Math.cos(theta)));
        float y1 = (float) (y + (radius * Math.sin(phi) * Math.sin(theta)));
        float z1 = (float) (z + (radius * Math.cos(phi)));

        double[] array = new double[] {x1, y1, z1};

        double distance = DistanceCalculator.getDistance(array, new double[] {x, y, z});

        if (distance != radius) {
            return getPointOnSphere(x, y, z, radius);
        }
        return array;

    }

    /**
     *
     * @param c The base color
     * @param distance The COLOR distance desired from the new color to be generated. Smaller values will produce
     *                 a color similar to the input color, larger values will produce a color that is distinct from
     *                 the input color. The input distance should not be more than 150, as this could lead to a stack
     *                 overflow error.
     * @return A random color that is perceptually as distant as the given distance.
     */
    public static Color getOpposingColor(Color c, int distance) {
        if (distance > 150) {
            throw new IllegalArgumentException("Distance should not be more than 150.");
        }

        ColorSpace labspace = new LABSpace();
        float[] coords = labspace.fromRGB(c.getRGBColorComponents(null));
        double[] newCoords = ColorAssigner.getPointOnSphere(coords[0], coords[1], coords[2], distance);
        double beforeDistance = DistanceCalculator.getDistance(floatToDouble(coords), newCoords);
        float[] newColorRGB = labspace.toRGB(doubleToFloat(newCoords));
        Color newColor = new Color(newColorRGB[0], newColorRGB[1], newColorRGB[2]);

        float[] afterCoords = labspace.fromRGB(newColor.getRGBColorComponents(null));
        double afterDistance = DistanceCalculator.getDistance(floatToDouble(coords), floatToDouble(afterCoords));
        if (beforeDistance < afterDistance - 10 || beforeDistance > afterDistance + 10) {
            return getOpposingColor(c, distance);
        }
        return newColor;
    }

    private static double[] floatToDouble(float[] floats) {
        double[] doubles = new double[floats.length];
        for (int i = 0; i < doubles.length; i++) {
            doubles[i] = (double) floats[i];
        }
        return doubles;
    }

    private static float[] doubleToFloat(double[] doubles) {
        float[] floats = new float[doubles.length];
        for (int i = 0; i < floats.length; i++) {
            floats[i] = (float) doubles[i];
        }
        return floats;
    }
}