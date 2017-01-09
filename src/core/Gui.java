package core;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyListener;

/**
 * Created by Devin on 12/30/2016.
 */
public class Gui {
    public JFrame frame;
    public JLayeredPane panel;
    public double width;
    public double height;

    public Gui(){
        frame = new JFrame();   //Create a new JFrame and JPanel. JPanel necessary to actually draw things to.
        panel = new JLayeredPane();
        panel.setPreferredSize(new Dimension(1920, 1080));

        //frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setPreferredSize(new Dimension(1920, 1080));
        frame.setUndecorated(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setFocusable(true);
        //frame.setLocationRelativeTo(null);

        frame.setVisible(true);

        width = frame.getWidth();
        height = frame.getHeight();
        frame.add(panel);
        frame.pack();
    }

    public void addKeyListener(KeyListener listener){
        frame.addKeyListener(listener);
    }

    public void add(Sprite sprite){
        panel.add(sprite);
    }

    public void remove(Sprite sprite){
        panel.remove(sprite);
    }
}