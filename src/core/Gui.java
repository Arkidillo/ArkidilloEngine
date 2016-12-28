package core;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyListener;

/**
 * Created by Devin on 12/25/2016.
 */
public class Gui {
    public JFrame frame;
    public double width;
    public double height;

    public Gui(){
        frame = new JFrame();   //Create a new JFrame and JPanel. JPanel necessary to actually draw things to.

        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setUndecorated(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setFocusable(true);
        frame.setLocationRelativeTo(null);

        frame.setVisible(true);

        width = frame.getWidth();
        height = frame.getHeight();
    }

    public void addKeyListener(KeyListener listener){
        frame.addKeyListener(listener);
    }

    public void add(Sprite sprite){
        frame.getContentPane().add(sprite);
        frame.paintComponents(sprite.getGraphics());
    }

}
