package core;

import com.sun.deploy.panel.JavaPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyListener;
import java.util.Objects;

/**
 * Created by Devin on 12/25/2016.
 */
public class Gui {
    public JFrame frame;
    public JPanel panel;
    public int width;
    public int height;

    public Gui(){
        frame = new JFrame();   //Create a new JFrame and JPanel. JPanel necessary to actually draw things to.
        panel = new JPanel(null);
        this.width = 1000;     //Set the height and width of the screen here from the constructor parameter.
        this.height = 800;

        //frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setUndecorated(false);

        //frame.setMinimumSize(new Dimension(width, height));   //Set the window's size, set close on X, set resizable to false, set the title of the window.
        //frame.setPreferredSize(new Dimension(width, height));
        frame.setSize(new Dimension(width, height));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setFocusable(false);
        frame.setLocationRelativeTo(null);

        panel.setSize(new Dimension(width, height));
        panel.setFocusable(true);
        frame.add(panel);   //Adds the panel to the frame.

        frame.setVisible(true);
        panel.setVisible(true);
    }

    public void addKeyListener(KeyListener listener){
        //frame.addKeyListener(listener);
        panel.addKeyListener(listener);
    }

    public void add(JLabel label){
        panel.add(label);
        //frame.pack();
        //frame.setSize(1920, 1080);
    }

}
