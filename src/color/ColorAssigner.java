package color;

import FileIO.KMLWriter;
import flightData.Flight;

import java.awt.*;
import java.util.LinkedList;

public class ColorAssigner {
    /**
     * Main driver for testing. Currently generates a few dummy flights and saves it to a KML file in this working
     * directory.
     * @param args
     */
    public static void main(String[] args) {
        Flight[] flights = KMLWriter.getTestFlights();

        int i = 0;
        for (Flight f : flights) {
            f.setPathColor(new Color(getColor(i)));
            i++;
        }

        KMLWriter k = new KMLWriter(flights);
        k.toFile("TestFile.kml");
    }

    /**
     * Returns the color that should be assigned to the nth flight.
     * @param n the nth flight
     * @return A color in RGB color scheme.
     */
    public static int getColor(int n) {
        return Color.HSBtoRGB((float) getHue(n), 1, 1);
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

        return num/denom ;
    }
}