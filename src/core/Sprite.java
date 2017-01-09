package core;

import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Devin on 12/30/2016.
 */
public class Sprite extends JLabel implements Runnable {
    public Scene scene;
    public ImageIcon defaultImage;
    public ImageIcon image;
    public boolean needToRepaint;

    public boolean collideable;
    public boolean checkCollisions;
    public boolean animate;
    public HashMap<Integer, Animation> animations = new HashMap<>();
    public ArrayList<Integer> animationIds = new ArrayList<>(); //Animation ID -1 is reserved for default.
    public int currentAnimation;

    public double speed;
    public int directionAngle;  //Radians

    public int waitX;
    public int waitY;
    public int currentWaitX;
    public int currentWaitY;
    public int velX;
    public int velY;

    //TODO: Simple physics - change velocity to pixels/ second
    //TODO: Add z-axis using JLayeredPanel

    public Sprite(int x, int y, String fileName, Scene s){
        scene = s;
        defaultImage = new ImageIcon(".\\assets\\" + fileName);
        image = defaultImage;
        setIcon(image);
        setSize(getIcon().getIconWidth(), getIcon().getIconHeight());
        Kernel.gui.add(this);
        scene.spritesInScene.add(this);
        setLocation(x,y);
    }

    public Sprite(Sprite s){
        defaultImage = s.defaultImage;
        image = s.image;
        setIcon(image);
        setSize(getIcon().getIconWidth(), getIcon().getIconHeight());
        scene = s.scene;
        animate = s.animate;
        currentAnimation = s.currentAnimation;
        speed = s.speed;
        directionAngle = s.directionAngle;

        waitX = s.waitX;
        waitY = s.waitY;
        currentWaitX = s.currentWaitX;
        currentWaitY = s.currentWaitY;
        velX = s.velX;
        velY = s.velY;

        if(s.checkCollisions){
            setCheckCollisions(true);
        }
        if(s.collideable){
            setCollideable(true);
        }

        Animation newAnim;
        Integer currId;

        for(int i = 0; i < s.animationIds.size(); i++){
            currId = s.animationIds.get(i);
            animationIds.add(currId);
            newAnim = new Animation(s.animations.get(currId), this);
            animations.put(currId, newAnim);
        }

        Kernel.gui.add(this);
        scene.spritesInScene.add(this);
        setLocation(s.getX(), s.getY());
    }

    public void update(){
    }

    public void run(){
        update();
        if(animate){
            animations.get(currentAnimation).nextAnimationFrame();
        }
        if (needToRepaint){
            repaint();
            needToRepaint = false;
        }
        if(speed > 0.000001){
            simulate();
        }
    }

    @Override
    public void setLocation(int x, int y){
        super.setLocation(x, y);
        needToRepaint = true;
    }

    public void setImage(String fileName){
        image = new ImageIcon(".\\assets\\" + fileName);
        setIcon(image);
        setSize(getIcon().getIconWidth(), getIcon().getIconHeight());
        needToRepaint = true;
    }

    public void setCollideable(boolean collideable){
        this.collideable = collideable;
        if (collideable)
            scene.collideableSprites.add(this);
        else
            scene.collideableSprites.remove(this);
    }

    public void setCheckCollisions(boolean checkCollisions){
        this.checkCollisions = checkCollisions;
        if (checkCollisions)
            scene.checkCollideableSprites.add(this);
        else
            scene.checkCollideableSprites.remove(this);
    }

    public void redraw(){   //Redraws the sprite on the next frame.
        needToRepaint = true;
    }

    public void addAnimation(String[] f, int delayFrames, Sprite sprite, int animationId){//Defines a set of images for which to use in the animation
        animations.put(animationId, new Animation(f, delayFrames, sprite, animationId));
        animationIds.add(animationId);
    }

    public void addAnimation(String[] f, int[] delayFrames, Sprite sprite, int animationId){//Defines a set of images for which to use in the animation
        animations.put(animationId, new Animation(f, delayFrames, sprite, animationId));
        animationIds.add(animationId);
    }

    public void nextFrame(int animationId){//Sets the image to the next frame of the animation.
        animations.get(animationId).nextFrame();
    }

    public void nextAnimationFrame(int animationId){ //Advances the animation by 1 frame.
        animations.get(animationId).nextAnimationFrame();
        currentAnimation = animationId;
    }

    public void startAnimation(int animationId){
        Animation currAnim = animations.get(animationId);
        if(currAnim.animationDelays.size() != 0 && currAnim.animationDelays.size() != currAnim.animationFrames.size()){
            System.out.println("WARNING: Number of frames, and number of frame specific delays are not equal!");
        }
        currAnim.startAnimation();
        animate = true;
        currentAnimation = animationId;
    }

    public void stopAnimation(){
        animate = false;
    }

    public void resizeCurrentImage(int width, int height){
        image =  new ImageIcon(ImageLoader.resizeImage(width, height, image.getImage()));
        setIcon(image);
        setSize(getIcon().getIconWidth(), getIcon().getIconHeight());


        needToRepaint = true;
    }

    public void resizeDefaultImage(int width, int height){
        defaultImage = new ImageIcon(ImageLoader.resizeImage(width, height, defaultImage.getImage()));
        setIcon(defaultImage);
        image = defaultImage;
        setSize(getIcon().getIconWidth(), getIcon().getIconHeight());

        needToRepaint = true;
    }

    public void resizeAnimation(int width, int height, boolean resizeDefaultImage, int animationId){  //Resizes all of the images in the current animation image arraylist.
        animations.get(animationId).resizeAnimation(width, height, resizeDefaultImage);
    }

    public int getCurrentFrameNumber(int animationId){    //This will start at 0!
        return animations.get(animationId).getCurrentFrameNumber();
    }

    public void resetAnimation(int animationId){
        animations.get(animationId).resetAnimation();
        currentAnimation = animationId;
    }

    public void selectAnimation(int animationId){   //Select which animation you would like to preform.
        currentAnimation = animationId;
        image = animations.get(currentAnimation).animationFrames.get(0);
        setIcon(image);
        setSize(getIcon().getIconWidth(), getIcon().getIconHeight());
        needToRepaint = true;
    }

    public void removeAnimation(int animationId){
        animationIds.remove((Integer)animationId);
        animations.remove(animationId);
    }

    public void resetAllAnimations(){
        for(int i = 0; i < animationIds.size(); i++){
            animations.get(animationIds.get(i)).resetAnimation();
        }
        resetToDefault();
    }

    public void resetToDefault(){
        if(!image.equals(defaultImage)) {
            image = defaultImage;
            setIcon(image);


            setSize(getIcon().getIconWidth(), getIcon().getIconHeight());
            currentAnimation = -1;

            needToRepaint = true;
        }
    }

    public void keyPressed(int keyCode){    //Users can override these methods if they want to do something with litening sprites.
    }

    public void keyReleased(int keyCode){
    }

    public void keyTyped(int keyCode){
    }

    public void clearIcon(){
        setIcon(null);
    }

    public void setVelocityPixelsPerFrame(int pixelsPerFrame, int radians){
        if(pixelsPerFrame != 0) {
            speed = pixelsPerFrame;
            directionAngle = radians;
            velX = (int) (Math.round(Math.cos(directionAngle) * speed));
            velY = (int) (Math.round(Math.sin(directionAngle) * speed));

            waitX = 0;
            waitY = 0;
            currentWaitX = 0;
            currentWaitY = 0;
        } else {
            speed = 0;
        }
    }

    public void setVelocityFramesPerPixel(int framesPerPixel, int radians){
        if(framesPerPixel != 0) {
            directionAngle = radians;
            speed = 1.0 / framesPerPixel;  //= pixels per second

            double xComponent = Math.cos(radians) * speed;  //gets the pixels per second in the x and y directions
            double yComponent = Math.sin(radians) * speed;

            if (xComponent > 0.00001) {
                double framesPerPixelX = 1 / xComponent; //gets seconds per pixel in the x and y directions.
                waitX = (int) Math.round(framesPerPixelX);
                velX = 1;   //After the number of frames calculated here ^, move 1 pixel
            } else {
                velX = 0;
                waitX = 0;
            }

            if (yComponent > 0.00001) {
                double framesPerPixelY = 1 / yComponent;
                waitY = (int) Math.round(framesPerPixelY);
                velY = 1;
            } else {
                velY = 0;
                waitY = 0;
            }

            currentWaitX = waitX;
            currentWaitY = waitY;
        } else {
            speed = 0;
        }
    }

    private void move(){
        setLocation(getX() + velX, getY() + velY);
    }

    private void simulate(){
        if(currentWaitX == 0 && currentWaitY == 0 && !(velX == 0 && velY == 0)){
            move();
            currentWaitX = waitX;
            currentWaitY = waitY;
            return;
        }

        if (currentWaitY == 0 && velY != 0){
            setLocation(getX(), getY() + velY);
            currentWaitY = waitY;
        } else {
            currentWaitY--;
        }

        if(currentWaitX == 0 && velX != 0) {
            setLocation(getX() + velX, getY());
            currentWaitX = waitX;
        } else {
            currentWaitX--;
        }
    }

    public void onCollision(Sprite body1, Sprite body2){    //The user can overwrite this for any custom children of the Sprite class, to handle collisions for that object
    }

    public void setZPosition(int z){
        Kernel.gui.panel.setLayer(this, z);
    }
}
