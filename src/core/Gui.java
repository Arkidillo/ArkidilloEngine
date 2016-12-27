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
        panel = new JPanel();
        this.width = 1920;     //Set the height and width of the screen here from the constructor parameter.
        this.height = 1080;

        //frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setMinimumSize(new Dimension(1920, 1080));
        frame.setUndecorated(true);

        //frame.setSize(width, height);   //Set the window's size, set close on X, set resizable to false, set the title of the window.
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setFocusable(true);
        frame.setLocationRelativeTo(null);

        panel.setMinimumSize(new Dimension(1920, 1080));
        panel.setFocusable(true);
        panel.setLayout(null);
        frame.getContentPane().add(panel);   //Adds the panel to the frame.

        frame.setVisible(true);
        panel.setVisible(true);
    }

    public void addKeyListener(KeyListener listener){
        frame.addKeyListener(listener);
        //or panel.addKeyListener(listener)?
    }

    public void add(int x, int y, String fileName){
        JLabel label = new JLabel(new ImageIcon(".\\assets\\" + fileName));
        label.setLocation(x, y);
        panel.add(label);
        //frame.pack();
        //frame.setSize(1920, 1080);
    }

}
