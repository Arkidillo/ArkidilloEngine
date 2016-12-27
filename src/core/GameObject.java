package core;

import javax.swing.*;
import java.awt.event.KeyEvent;

/**
 * Created by Devin on 12/25/2016.
 */
public class GameObject extends JComponent implements Runnable{
    public int x;
    public int y;
    public boolean collide;
    public LISTENING listening;
    public enum LISTENING {
        TYPED, PRESSED, RELEASED
    }

    public GameObject(int x, int y){
        this.x = x;
        this.y = y;
    }

    public GameObject(){
        x = 0;
        y = 0;
    }

    public void run(){
    }

    public void update(){//Will be overriden by the user if needed.
    }
}
