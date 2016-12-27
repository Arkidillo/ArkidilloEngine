package core;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;

/**
 * Created by Devin on 12/25/2016.
 */
public class Sprite extends GameObject implements Runnable{

    int collisionId;
    public Scene scene;

    public Sprite(String fileName, Scene scene){
        super();
        this.scene = scene;
        Kernel.gui.add(this);
        scene.sceneData.add(this, getX(), getY());
    }

    public Sprite(int x, int y, String fileName, Scene scene){
        super(x, y);
        this.scene = scene;
        this.setIcon(new ImageIcon(".\\assets\\" + fileName));
        Kernel.gui.add(this);
        scene.sceneData.add(this, x, y);
    }

    @Override
    public void run(){
        update();
        //draw();
    }


    public void draw(){
        //paintComponent(g);
        repaint();
        //g.drawImage(image, x, y, null);
    }
}
