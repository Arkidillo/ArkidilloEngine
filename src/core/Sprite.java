package core;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Devin on 12/27/2016.
 */
public class Sprite extends JComponent implements Runnable{
    public Image image;
    public String fileName;
    public ArrayList<String> animationFrames = new ArrayList<>();
    public int animationDelay;
    public ArrayList<Integer> animationDelays = new ArrayList<Integer>();
    public int currentFrame;
    public int currentDelay;
    public boolean animate;
    public int x;
    public int y;
    public int width;
    public int height;
    private boolean needToRedraw;
    public Scene scene;

    //TODO: Resizable sprites
    //TODO: Collision detection
    //TODO: Animations - Multiple animations based on a name?

    public Sprite(int x, int y, String fileName, Scene s){
        this.x = x;
        this.y = y;
        this.fileName = fileName;
        scene = s;
        image = ImageLoader.loadImage(fileName);
        Kernel.gui.add(this);
        scene.spritesInScene.add(this);
        width = image.getWidth(null);
        height = image.getHeight(null);
        needToRedraw = true;
    }

    @Override
    public void paintComponent(Graphics g){
        if(image == null) return;

        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(image, x, y, null);
        g2d.fillRect(x, y, 1, 1);
        g2d.setColor(Color.RED);

    }

    public void update(){}

    public void run(){
        update();
        if (animate == true) {
            if (--currentDelay == 0) {  //counts down the delay until you reach 0
                nextFrame(); //Then you go to the next frame in the animation
                if(animationDelays.size() != 0) {   //if you are using the dynamic frame delay, go to the next frame's delay
                    currentDelay = animationDelays.get(currentFrame);
                } else {//else, set the delay back up to the given frame delay amount
                    currentDelay = animationDelay;
                }
            }
        }
        if(needToRedraw == true) {
            repaint();
            needToRedraw = false;
        }
    }

    @Override
    public void setLocation(int x, int y){
        //scene.sceneData.moveSprite(this.x, this.y, x, y, this);
        this.x = x;
        this.y = y;
        needToRedraw = true;
    }

    public void setImage(String fileName){
        this.fileName = fileName;
        image = ImageLoader.loadImage(fileName);
        width = image.getWidth(null);
        height = image.getHeight(null);
        needToRedraw = true;
    }

    public void setAnimationFrames(String[] f){//Defines a set of images for which to use in the animation
        animationFrames.clear();
        currentFrame = 0;
        for(int i = 0; i < f.length; i++){
            animationFrames.add(f[i]);
        }
    }

    public void setAnimationDelay(int frames){//Sets a constant delay through all the images
        animationDelay = frames;
        currentDelay = frames;
        animationDelays.clear();    //Makes sure EITHER a single delay is used, or frame specific delays are used, not both!
    }

    public void setAnimationDelays(int[] delays){//Sets a different delay for each frame. The first delay is used after the first frame, the next delay is used after the next frame, and so forth.
        animationDelays.clear();
        for(int i = 0; i < delays.length; i++){
            animationDelays.add(delays[i]);
        }
    }

    public void nextFrame(){//Sets the image to the next frame of the animation.
        setImage(animationFrames.get((++currentFrame)%animationFrames.size()));
    }

    public void advanceAnimation(){ //Advances the animation by 1 frame.
        if (--currentDelay == 0) {  //counts down the delay until you reach 0
            nextFrame(); //Then you go to the next frame in the animation
            if(animationDelays.size() != 0) {   //if you are using the dynamic frame delay, go to the next frame's delay
                currentDelay = animationDelays.get(currentFrame);
            } else {//else, set the delay back up to the given frame delay amount
                currentDelay = animationDelay;
            }
        }
    }

    public void startAnimation(){
        if(animationDelays.size() != 0 && animationDelays.size() != animationFrames.size()){
            System.out.println("WARNING: Number of frames, and number of frame specific delays are not equal!");
        }
        animate = true;
    }

    public void stopAnimation(){
        animate = false;
    }
}
