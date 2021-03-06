package mock;

import color.ColorAssigner;
import color.LABSpace;

import java.awt.*;
import java.awt.color.ColorSpace;
import java.awt.event.*;

public class TestColors extends Frame {

    private final int WINDOW_HEIGHT = 1000;
    private final int WINDOW_WIDTH = 400;
    private Color[] colors;
    private Color c;

    public static void main(String[] args){
        //TestColors testColors = new TestColors();
        //testColors.setVisible(true);
    }

    public TestColors(Color[] colors){
        super("Test Colors");
        this.colors = colors;
        prepareGUI();
        setVisible(true);
    }

    public TestColors(Color c) {
        this.c = c;
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
        showColor(c, g);
    }

    public void testHSLPicker(Graphics g) {
        int height = 50;
        for (int i = 0; i < 1000/height; i++) {
            //g.setColor(new Color(ColorAssigner.getColor(i)));
            Rectangle r = new Rectangle(0, height*i, 400, height);
            g.fillRect(0, height*i, 400, height);
        }
    }

    public void testLABPicker(Graphics g) {
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

            for (int i = 2; i < colors.length+2; i++) {
                Color newColor = colors[i-2];
                g.setColor(newColor);
                Rectangle rect3 = new Rectangle(0, i *height + 10, 400, height);
                g.fillRect(0, i *height + 10, 400, height);
            }

    }
    public static void showColor(Color color, Graphics g) {
        g.setColor(color);
        Rectangle rect3 = new Rectangle(0, 10, 400, 60);
        g.fillRect(0, 10, 400, 60);
    }


}