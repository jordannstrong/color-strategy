package color;

import java.awt.*;
import java.awt.color.ColorSpace;

public class LABSpace extends ColorSpace {


    private static final ColorSpace CIEXYZ =
            ColorSpace.getInstance(ColorSpace.CS_CIEXYZ);
    private static final double N = 4.0 / 29.0;


    @Override
    public float[] fromRGB(float[] rgbvalue) {
        float[] xyz = CIEXYZ.fromRGB(rgbvalue);
        return fromCIEXYZ(xyz);
    }

    @Override
    public float getMaxValue(int component) {
        return 128f;
    }

    @Override
    public float getMinValue(int component) {
        return (component == 0) ? 0f : -128f;
    }


    @Override
    public float[] toCIEXYZ(float[] colorvalue) {
        double i = (colorvalue[0] + 16.0) * (1.0 / 116.0);
        double X = fInv(i + colorvalue[1] * (1.0 / 500.0));
        double Y = fInv(i);
        double Z = fInv(i - colorvalue[2] * (1.0 / 200.0));
        return new float[]{(float) X, (float) Y, (float) Z};
    }

    private static double fInv(double x) {
        if (x > 6.0 / 29.0) {
            return x * x * x;
        } else {
            return (108.0 / 841.0) * (x - N);
        }
    }

    @Override
    public float[] fromCIEXYZ(float[] colorvalue) {
        return new float[0]; //TODO:
    }

    @Override
    public float[] toRGB(float[] colorvalue) {
        float[] xyz = toCIEXYZ(colorvalue);
        return CIEXYZ.toRGB(xyz);
    }

    LABSpace() {
        super(ColorSpace.TYPE_Lab, 3);
    }

    private static double f(double x) {
        if (x > 216.0 / 24389.0) {
            return Math.cbrt(x);
        } else {
            return (841.0 / 108.0) * x + N;
        }
    }

}




