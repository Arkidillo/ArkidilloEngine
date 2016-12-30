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
    public Image defaultImage;
    public String fileName;

    public int x;
    public int y;
    public int width;
    public int height;
    public boolean needToRedraw;
    public boolean collideable;
    public boolean checkCollisions;
    public Scene scene;
    public boolean animate;
    public HashMap<Integer, Animation> animations = new HashMap<>();
    public ArrayList<Integer> animationIds = new ArrayList<>(); //Animation ID -1 is reserved for default.
    public int currentAnimation;

    //TODO: Collision detection
    //TODO: Fix create sprite from sprite method/ Why isn't GameScene working?
    //TODO: Add simple physics (velocity + maybe acceleration?)

    public Sprite(int x, int y, String fileName, Scene s){
        scene = s;
        setImage(fileName);
        defaultImage = image;
        Kernel.gui.add(this);
        scene.spritesInScene.add(this);
        setLocation(x,y);
    }


    public Sprite(Sprite s){
        defaultImage = s.defaultImage;
        fileName = s.fileName;
        image = s.image;
        x = s.x;
        y = s.y;
        width = s.width;
        height = s.height;
        needToRedraw = s.needToRedraw;
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
        needToRedraw = true;

    }


    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
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
        if(checkCollisions == true){
            checkCollisions();
        }
    }
    public void setCollideable(boolean collideable){
        this.collideable = collideable;
        scene.addCollideableSprite(this);
    }

    public void setCheckCollisions(boolean checkCollisions){
        this.checkCollisions = checkCollisions;
    }

    @Override
    public void setLocation(int x, int y){
        this.x = x;
        this.y = y;
        //super.setLocation(x,y);
        needToRedraw = true;
    }

    public void setImage(String fileName){
        this.fileName = fileName;
        image = ImageLoader.loadImage(fileName);
        width = image.getWidth(null);
        height = image.getHeight(null);
        needToRedraw = true;
    }

    public void redraw(){   //Redraws the sprite on the next frame.
        needToRedraw = true;
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
        image = ImageLoader.resizeImage(width, height, image);
        this.width = width;
        this.height = height;

        needToRedraw = true;
    }

    public void resizeDefaultImage(int width, int height){
        image = ImageLoader.resizeImage(width, height, image);
        defaultImage = image;
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

    public void resetAnimation(int animationId){
        animations.get(animationId).resetAnimation();
        currentAnimation = animationId;
    }

    public void selectAnimation(int animationId){   //Select which animation you would like to preform.
        currentAnimation = animationId;
        image = animations.get(currentAnimation).animationFrames.get(0);
        width = image.getWidth(null);
        height = image.getHeight(null);
        needToRedraw = true;
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
            }

            width = defaultImage.getWidth(null);
            height = defaultImage.getHeight(null);
            currentAnimation = -1;

            needToRedraw = true;
        }
    }

    public void keyPressed(int keyCode){    //Users can override these methods if they want to do something with litening sprites.
    }

    public void keyReleased(int keyCode){
    }

    public void keyTyped(int keyCode){
    }

    public void checkCollisions(){
        ArrayList<Sprite> collideableSprite = scene.collideableSprites;
        Sprite currSprite;

        for (int i = 0; i < collideableSprite.size(); i++){
            currSprite = collideableSprite.get(i);
            if(currSprite.x == x && currSprite.y == y){
                scene.onCollision(this, currSprite);
            }
        }
    }

}
