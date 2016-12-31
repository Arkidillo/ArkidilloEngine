package core;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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
    public int framesUntilMove;
    public int directionAngle;

    //TODO: Collisions
    //TODO: Simple physics

    public Sprite(int x, int y, String fileName, Scene s){
        scene = s;
        defaultImage = new ImageIcon(".\\assets\\" + fileName);
        image = defaultImage;
        setIcon(image);
        Kernel.gui.add(this);
        setSize(getIcon().getIconWidth(), getIcon().getIconHeight());
        scene.spritesInScene.add(this);
        setLocation(x,y);
        needToRepaint = true;
    }

    public Sprite(Sprite s){
        defaultImage = s.defaultImage;
        image = s.image;
        setIcon(image);
        setLocation(s.getX(), s.getY());
        setSize(getIcon().getIconWidth(), getIcon().getIconHeight());
        scene = s.scene;
        animate = s.animate;
        currentAnimation = s.currentAnimation;

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
        needToRepaint = true;
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
    }

    public void update(){

    }

    public void run(){
        update();
        if (needToRepaint){
            repaint();
            needToRepaint = false;
        }
        if(speed > 0.00001){  //check if velocity is essentially 0
            move(speed);
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
        scene.addCollideableSpriteLabel(this);
    }

    public void setCheckCollisions(boolean checkCollisions){
        this.checkCollisions = checkCollisions;
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
            if (animationIds.size() != 0) {
                image = defaultImage;
                setIcon(image);
            }

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

    public void checkCollisions(){
        ArrayList<Sprite> collideableSprites = scene.collideableSprites;
        Sprite currSprite;

        for (int i = 0; i < collideableSprites.size(); i++){
            currSprite = collideableSprites.get(i);
            if(currSprite.getX() == getX() && currSprite.getY() == getY()){
                scene.onCollision(this, currSprite);
            }
        }
    }

    public void clearIcon(){
        setIcon(null);
    }

    private void move(double speed){
        //The x distance = cos(directionAngle) * velocity.
        //The y distance = sin(directionAngle) * velocity.
        //Each of these would need to be at least 1, thus the velocity (using pythagorean theorem), must be at least root(2)
        if(framesUntilMove == 0) {
            if (speed >= Math.sqrt(2)) {   //if velocity is greater than 1, we can just move that many pixels each frame update, or else we have to wait a couple of frames before moving, because we cannot move a fraction of a pixel.
                double moveX = getX() * Math.cos(directionAngle);
                double moveY = getY() * Math.sin(directionAngle);

                double leftOverX = moveX % 1;
                double leftOverY = moveY % 1;
                double newSpeed = leftOverX * leftOverX + leftOverY * leftOverY;
                setLocation((int) moveX, (int) moveY);
                if (newSpeed > 0.00001) {
                    move(Math.sqrt(newSpeed)); //will recursively call move on the new, left over speed
                    //would need to find the gcd of the leftOVerx and y to make a whole number fraction, then wait that long -> too choppy
                    setLocation(getX() + 1, getY() + 1);//Move by 1 extra pixel if the number was not a whole number, and then will wait the correspond amount of frames to average out the correct speed
                }
            } else {
                framesUntilMove = (int) (1.0 / speed) - 1;
            }
        } else {
            framesUntilMove--;
        }
    }
}
