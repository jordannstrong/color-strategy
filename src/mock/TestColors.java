package mock;

import color.ColorAssigner;
import color.LABSpace;

import java.awt.*;
import java.awt.color.ColorSpace;
import java.awt.event.*;

public class TestColors extends Frame {

    private final int WINDOW_HEIGHT = 1000;
    private final int WINDOW_WIDTH = 400;

    public static void main(String[] args){
        TestColors testColors = new TestColors();
        testColors.setVisible(true);
    }

    public TestColors(){
        super("Test Colors");
        prepareGUI();
    }

    private void prepareGUI(){
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent) {
                System.exit(0);
            }
        });
    }

    @Override
    public void paint(Graphics g) {
        testLABPicker(g);
    }

    public void testHSLPicker(Graphics g) {
        int height = 50;
        for (int i = 0; i < 1000/height; i++) {
            g.setColor(new Color(ColorAssigner.getColor(i)));
            Rectangle r = new Rectangle(0, height*i, 400, height);
            g.fillRect(0, height*i, 400, height);
        }
    }

    public void testLABPicker(Graphics g) {
        System.out.println("------------------");
        int height = 60;
        ColorSpace ls = new LABSpace();

        /*Color prev = new Color(ColorAssigner.getColor(0));

        g.setColor(prev);
        Rectangle rect = new Rectangle(0, height, 400, height);
        g.fillRect(0, height, 400, height);

        float[] rgb = prev.getRGBColorComponents(null);

        ColorSpace ls = new LABSpace();
        float[] components = ls.fromRGB(rgb);

        float[] toRGB = ls.toRGB(components);
        Color labColor = new Color(toRGB[0], toRGB[1], toRGB[2]);
        g.setColor(labColor);
        Rectangle rect2 = new Rectangle(0, 2*height, 400, height);
        g.fillRect(0, 2*height, 400, height);*/

        Color prevColor = Color.RED;
        for (int i = 0; i < 10; i++) {
            Color newColor = ColorAssigner.getOpposingColor(prevColor, 70);
            g.setColor(newColor);
            Rectangle rect3 = new Rectangle(0, (i)*height, 400, height);
            g.fillRect(0, (i) * height, 400, height);

            prevColor = newColor;
        }
    }
}