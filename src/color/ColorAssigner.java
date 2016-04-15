package color;

import flightData.Flight;

import java.util.LinkedList;

public class ColorAssigner {
    public static void main(String[] args) {
        System.out.println("Hello.");
    }

    private String color(Flight f1, Flight f2) {
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
    }
}