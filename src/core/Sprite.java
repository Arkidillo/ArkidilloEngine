package core;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by Devin on 12/27/2016.
 */
public class Sprite extends JComponent implements Runnable{
    public Image image;
    public int x;
    public int y;
    public Scene scene;

    public Sprite(int x, int y, String fileName, Scene s){
        this.x = x;
        this.y = y;
        scene = s;
        image = ImageLoader.loadImage(fileName);
        Kernel.gui.add(this);
        scene.spritesInScene.add(this);
    }

    @Override
    public void paintComponent(Graphics g){
        if(image == null) return;

        Graphics2D g2d = (Graphics2D)g;

        g2d.drawImage(image, x, y,null);
        g2d.fillRect(x, y, 10, 10);
        g2d.setColor(Color.RED);
    }

    public void update(){}

    public void run(){
        update();
        repaint();
    }

    @Override
    public void setLocation(int x, int y){
        //scene.sceneData.moveSprite(this.x, this.y, x, y, this);
        this.x = x;
        this.y = y;
    }
}
