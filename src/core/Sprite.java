package core;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Devin on 12/27/2016.
 */
public class Sprite extends JComponent implements Runnable{
    public Image image;
    public String fileName;

    public int x;
    public int y;
    public int width;
    public int height;
    public boolean needToRedraw;
    public Scene scene;
    public boolean animate;
    public HashMap<Integer, Animation> animations = new HashMap<>();
    public int currentAnimation;

    //TODO: Resizable sprites
    //TODO: Collision detection

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
            animations.get(currentAnimation).nextAnimationFrame();
        }
        if(needToRedraw == true) {
            repaint();
            needToRedraw = false;
        }
    }

    @Override
    public void setLocation(int x, int y){
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

    public void addAnimation(String[] f, int delayFrames, Sprite sprite, int animationId){//Defines a set of images for which to use in the animation
        animations.put(animationId, new Animation(f, delayFrames, sprite, animationId));
    }

    public void addAnimation(String[] f, int[] delayFrames, Sprite sprite, int animationId){//Defines a set of images for which to use in the animation
        animations.put(animationId, new Animation(f, delayFrames, sprite, animationId));
    }

    public void nextFrame(int animationId){//Sets the image to the next frame of the animation.
        animations.get(animationId).nextFrame();
    }

    public void advanceAnimation(int animationId){ //Advances the animation by 1 frame.
        animations.get(animationId).advanceAnimation();
        currentAnimation = animationId;
    }

    public void startAnimation(int animationId){
        Animation currAnim = animations.get(animationId);
        if(currAnim.animationDelays.size() != 0 && currAnim.animationDelays.size() != currAnim.animationFrames.size()){
            System.out.println("WARNING: Number of frames, and number of frame specific delays are not equal!");
        }
        animate = true;
        currentAnimation = animationId;
    }

    public void stopAnimation(){
        animate = false;
    }

    public void resizeSprite(int width, int height){
        image = ImageLoader.resizeImage(width, height, image);
        this.width = width;
        this.height = height;

        needToRedraw = true;
    }

    public void resizeAnimation(int width, int height, boolean resizeDefaultImage, int animationId){  //Resizes all of the images in the current animation image arraylist.
        animations.get(animationId).resizeAnimation(width, height, resizeDefaultImage);
    }

    public int getCurrentFrameNumber(int animationId){    //This will start at 0!
        return animations.get(animationId).getCurrentFrameNumber();
    }

    public void resetAnimtion(int animationId){
        animations.get(animationId).resetAnimation();
        currentAnimation = animationId;
    }
}
