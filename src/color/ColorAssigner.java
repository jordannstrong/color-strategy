package color;

import flightData.Flight;

public class ColorAssigner {
    public static void main(String[] args) {
        System.out.println("Hello.");
    }

    /*max = maximumDistance;
    min = minimumDistance;
    lMax = 100;
    lMin = 0;
    aMax = 127;
    aMin = -128;
    bMax = 127;
    bMin = -128;*/

    private float color(Flight f1, Flight f2) {
    }

    public String LABtoRGB(double L, double A, double B)
    {
        L = (L+16)/116;
        A = (A/500) + L;
        B = L-(B/200);

        if ( Math.pow(L,3) > 0.008856 )
        {
            L = Math.pow(L,3);
        }
        else
        {
            L = ( L - 16 / 116 ) / 7.787;
        }
        if ( Math.pow(A,3) > 0.008856 )
        {
            A = Math.pow(A,3);
        }
        else
        {
            A = ( A - 16 / 116 ) / 7.787
        }
        if ( Math.pow(B,3) > 0.008856 )
        {
            B = Math.pow(B,3);
        }
        else
        {
            B = ( B - 16 / 116 ) / 7.787;
        }

        double R = L * 95.047;
        double G = A * 100.000;
        B = B * 108.883;

        String RGB = Long.toHexString(Double.doubleToRawLongBits(R)) +
                Long.toHexString(Double.doubleToRawLongBits(G)) +
                Long.toHexString(Double.doubleToRawLongBits(B));

        return RGB;
    }
}