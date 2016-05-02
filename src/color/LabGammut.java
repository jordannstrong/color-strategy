package color;

import javax.vecmath.Vector3d;
import java.awt.color.ColorSpace;
import java.util.ArrayList;

/**
 * Created by Kevin on 5/1/2016.
 *
 * Abstraction of the portion of the L*A*B* Color space that is perceivable by humans. Used as a closed container in
 * which points representing colors be contained inside of.
 */
public class LabGammut {

    public LabGammut() {
        double angleres = 20;
        double radiusInc = 1;
        double maxRadius = 1000;

        ArrayList<ArrayList<Double>> slicesA = new ArrayList<>();
        ArrayList<ArrayList<Double>> slicesB = new ArrayList<>();
        ArrayList<ArrayList<Double>> slicesL = new ArrayList<>();

        for (int l = 0; l < 120; l += 20) { // iterate through different slices of the gammut to form walls
            ArrayList<Double> sliceA = new ArrayList<>();
            ArrayList<Double> sliceB = new ArrayList<>();
            ArrayList<Double> sliceL = new ArrayList<>();

            for (int i = 0; i < angleres + 1; i++) {
                double angle = 2*i*Math.PI/angleres;
                double radius = 0;
                double validA = 0;
                double validB = 0;

                while (radius < maxRadius) {
                    radius += radiusInc;

                    double a = radius * Math.cos(angle);
                    double b = radius * Math.sin(angle);
                    ColorSpace labSpace = new LABSpace();
                    float[] colorValue = new float[] {(float) l, (float) a, (float) b};
                    float[] rgb = labSpace.toRGB(colorValue);
                    float cr = rgb[0];
                    float cg = rgb[1];
                    float cb = rgb[2];
                    // check if the coordinates are a displayable color
                    if (cr >= 0 && cr <= 1 && cg >= 0 && cg <= 1 && cb >= 0 && cb <=1) {
                        validA = a;
                        validB = b;
                    }
                    else {
                        break;
                    }
                    sliceA.add(validA/25);
                    sliceB.add(validB/25);
                    sliceL.add(l/25.0);
                }
                slicesA.add(sliceA);
                slicesB.add(sliceB);
                slicesL.add(sliceL);
            }

            for (int i = 0; i < slicesL.size()-1; i++) {

                for (int j = 0; j < slicesL.get(i).size()-1; j++) {
                    Vector3d v1 = new Vector3d(slicesA.get(i).get(j), slicesL.get(i).get(j), slicesB.get(i).get(j));
                    Vector3d v2 = new Vector3d(slicesA.get(i).get(j+1),slicesL.get(i).get(j+1),slicesB.get(i).get(j+1));
                    Vector3d v3 = new Vector3d(slicesA.get(i+1).get(j+1),slicesL.get(i+1).get(j+1),slicesB.get(i+1).get(j+1));
                    Vector3d v4 = new Vector3d(slicesA.get(i+1).get(j),slicesL.get(i+1).get(j),slicesB.get(i+1).get(j));
                }
            }
        }
    }

}
