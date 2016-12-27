package core;

import javax.swing.*;
import java.awt.event.KeyListener;

/**
 * Created by Devin on 12/25/2016.
 */
public class Gui {
    public JFrame frame;
    public int width;
    public int height;

    public Gui(){
        frame = new JFrame();   //Create a new JFrame and JPanel. JPanel necessary to actually draw things to.
        this.width = 1000;     //Set the height and width of the screen here from the constructor parameter.
        this.height = 800;

        //frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setUndecorated(false);

        //frame.setMinimumSize(new Dimension(width, height));   //Set the window's size, set close on X, set resizable to false, set the title of the window.
        //frame.setPreferredSize(new Dimension(width, height));
        frame.setSize(width, height);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setFocusable(true);
        frame.setLocationRelativeTo(null);

        frame.setVisible(true);
    }

    public void addKeyListener(KeyListener listener){
        frame.addKeyListener(listener);
        //panel.addKeyListener(listener);
        //frame.getContentPane().addKeyListener(listener);
    }

    public void add(Sprite sprite){
        //frame.getContentPane().add(sprite);
        frame.getContentPane().add(sprite);
        frame.paintComponents(sprite.getGraphics());
    }

}
