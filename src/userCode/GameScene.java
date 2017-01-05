package userCode;

import core.*;

/**
 * Created by Devin on 12/30/2016.
 */
public class GameScene extends Scene{
    public Sprite sprite;
    public final int DIM_SIZE = 2;
    public int NUM_SPRITES = DIM_SIZE * DIM_SIZE;
    Sprite[] sprites = new Sprite[NUM_SPRITES];
    int wait;
    boolean flag = false;

    @Override
    public void onCreate(){
        //Start load screen.
        sprite = new Sprite(0,0,"linkFront.png", this);

        String[] frames = {"linkFront.png", "linkFront2.png"};
        String[] framesLeft = {"linkLeft.png", "linkLeft2.png"};
        String[] framesRight = {"linkRight.png", "linkRight2.png"};
        String[] framesBack = {"linkBack.png", "linkBack2.png"};

        sprite.addAnimation(framesRight,30,sprite,1);
        sprite.addAnimation(framesBack, 30, sprite, 2);
        sprite.addAnimation(frames,30,sprite,3);
        sprite.addAnimation(framesLeft,30,sprite,4);

        int resizedWidth = sprite.getWidth() * 2;
        int resizedHeight = sprite.getHeight() * 2;

        sprite.resizeAnimation(resizedWidth, resizedHeight, true, 1);
        sprite.resizeAnimation(resizedWidth, resizedHeight, false, 2);
        sprite.resizeAnimation(resizedWidth, resizedHeight, false, 3);
        sprite.resizeAnimation(resizedWidth, resizedHeight, false, 4);

        sprite.setCollideable(true);
        sprite.setName("defaultSprite");

        int distance = 10;

        for(int i = 0; i < DIM_SIZE; i++) {
            for (int j = 0; j < DIM_SIZE; j++) {
                sprites[j + i * DIM_SIZE] = new Sprite(sprite);
                sprites[j + i * DIM_SIZE].setLocation(i * distance + distance + 100, j * distance + distance + 100);
                sprites[j + i * DIM_SIZE].setName("sprite: " +  (j + i * DIM_SIZE));
        }
        }

        //removeSprite(sprite);
        sprite.setLocation(sprite.getX(), sprite.getY());
        sprite.setCheckCollisions(true);
        //End load screen.
    }

    @Override
    public void update(){
        flag = false;

        if(Kernel.keyListener.isKeyPressed(39)){
            sprite.setLocation(sprite.getX() + 2, sprite.getY());
            if(sprite.currentAnimation == 1) {
                sprite.nextAnimationFrame(1);
            } else {
                sprite.resetAnimation(1);
                sprite.selectAnimation(1);
            }
            flag = true;
        }

        if(Kernel.keyListener.isKeyPressed(38)){
            sprite.setLocation(sprite.getX(), sprite.getY() - 2);
            if(sprite.currentAnimation == 2) {
                sprite.nextAnimationFrame(2);
            } else {
                sprite.resetAnimation(2);
                sprite.selectAnimation(2);
            }
            flag = true;
        }

        if(Kernel.keyListener.isKeyPressed(40)){
            sprite.setLocation(sprite.getX(), sprite.getY() + 2);
            if(sprite.currentAnimation == 3) {
                sprite.nextAnimationFrame(3);
            } else {
                sprite.resetAnimation(3);
                sprite.selectAnimation(3);
            }
            flag = true;
        }

        if(Kernel.keyListener.isKeyPressed(37)){
            sprite.setLocation(sprite.getX() - 2, sprite.getY());
            if(sprite.currentAnimation == 4) {
                sprite.nextAnimationFrame(4);
            } else {
                sprite.resetAnimation(4);
                sprite.selectAnimation(4);
            }
            flag = true;
        }

        if(flag == false){
            sprite.resetToDefault();
        }
        /*flag = false;

        if(Kernel.keyListener.isKeyPressed(39)){
            sprite.setLocation(sprite.getX() + 2, sprite.getY());
            for(int i = 0; i < NUM_SPRITES; i++){
                sprites[i].setLocation(sprites[i].getX() + 2, sprites[i].getY());
            }
            if(sprite.currentAnimation == 1) {
                sprite.nextAnimationFrame(1);
                for(int i = 0; i < NUM_SPRITES; i++){
                    sprites[i].nextAnimationFrame(1);
                }
            } else {
                sprite.resetAnimation(1);
                sprite.selectAnimation(1);
                for(int i = 0; i < NUM_SPRITES; i++){
                    sprites[i].resetAnimation(1);
                    sprites[i].selectAnimation(1);
                }
            }
            flag = true;
        }

        if(Kernel.keyListener.isKeyPressed(38)){
            sprite.setLocation(sprite.getX(), sprite.getY() - 2);
            for(int i = 0; i < NUM_SPRITES; i++){
                sprites[i].setLocation(sprites[i].getX(), sprites[i].getY() - 2);
            }
            if(sprite.currentAnimation == 2) {
                sprite.nextAnimationFrame(2);
                for(int i = 0; i < NUM_SPRITES; i++){
                    sprites[i].nextAnimationFrame(2);
                }
            } else {
                sprite.resetAnimation(2);
                sprite.selectAnimation(2);
                for(int i = 0; i < NUM_SPRITES; i++){
                    sprites[i].resetAnimation(2);
                    sprites[i].selectAnimation(2);
                }
            }

            flag = true;
        }

        if(Kernel.keyListener.isKeyPressed(40)){
            sprite.setLocation(sprite.getX(), sprite.getY() + 2);
            for(int i = 0; i < NUM_SPRITES; i++){
                sprites[i].setLocation(sprites[i].getX(), sprites[i].getY() + 2);
            }
            if(sprite.currentAnimation == 3) {
                sprite.nextAnimationFrame(3);
                for(int i = 0; i < NUM_SPRITES; i++){
                    sprites[i].nextAnimationFrame(3);
                }
            } else {
                sprite.resetAnimation(3);
                sprite.selectAnimation(3);
                for(int i = 0; i < NUM_SPRITES; i++){
                    sprites[i].resetAnimation(3);
                    sprites[i].selectAnimation(3);
                }
            }

            flag = true;
        }

        if(Kernel.keyListener.isKeyPressed(37)){
            sprite.setLocation(sprite.getX() - 2, sprite.getY());
            for(int i = 0; i < NUM_SPRITES; i++){
                sprites[i].setLocation(sprites[i].getX() - 2, sprites[i].getY());
            }
            if(sprite.currentAnimation == 4) {
                sprite.nextAnimationFrame(4);
                for(int i = 0; i < NUM_SPRITES; i++){
                    sprites[i].nextAnimationFrame(4);
                }
            } else {
                sprite.resetAnimation(4);
                sprite.selectAnimation(4);
                for(int i = 0; i < NUM_SPRITES; i++){
                    sprites[i].resetAnimation(4);
                    sprites[i].selectAnimation(4);
                }
            }

            flag = true;
        }

        if(flag == false){
            sprite.resetToDefault();
            for(int i = 0; i < NUM_SPRITES; i++){
                sprites[i].resetToDefault();
            }
        }*/
    }

    @Override
    public void onCollision(Sprite body1, Sprite body2){
        System.out.println("Collided: " + body1.getName() + " + " + body2.getName());
        if(body1.getName() == "defaultSprite"){
            body1.setLocation(800, 800);
        } else if (body2.getName() == "defaultSprite"){
            body2.setLocation(800, 800);
        }
    }
}
