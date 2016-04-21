package mock;

import color.ColorAssigner;

import java.awt.*;
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
            public void windowClosing(WindowEvent windowEvent){
                System.exit(0);
            }
        });
    }

    @Override
    public void paint(Graphics g) {
        int height = 50;
        for (int i = 0; i < 1000/height; i++) {
            g.setColor(new Color(ColorAssigner.getColor(i)));
            Rectangle r = new Rectangle(0, height*i, 400, height);
            g.fillRect(0, height*i, 400, height);
        }
    }
}