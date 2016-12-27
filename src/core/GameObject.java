package core;

import javax.swing.*;
import java.awt.event.KeyEvent;

/**
 * Created by Devin on 12/25/2016.
 */
public class GameObject extends JLabel implements Runnable{
    //public int x;
    //public int y;
    public boolean collide;
    public LISTENING listening;
    public enum LISTENING {
        TYPED, PRESSED, RELEASED
    }

    public void run(){
    }

    public void update(){//Will be overriden by the user if needed.
    }

    public void keyPressed(int keyCode){
    }

    public void keyReleased(int keyCode){
    }

    public void keyTyped(int keyCode){
    }
}
