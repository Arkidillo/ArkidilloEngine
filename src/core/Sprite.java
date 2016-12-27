package core;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;

/**
 * Created by Devin on 12/25/2016.
 */
public class Sprite extends GameObject implements Runnable{

    int collisionId;

    public Sprite(String fileName){
        super();
        Kernel.gui.add(x, y, fileName);
        Kernel.currentScene.sceneData.add(this);
    }

    public Sprite(int x, int y, String fileName){
        super(x,y);
        Kernel.gui.add(x, y, fileName);
        //paintComponent(g);

        Kernel.currentScene.sceneData.add(this);
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
