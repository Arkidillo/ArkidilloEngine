package userCode;

import core.*;
/**
 * Created by Devin on 12/25/2016.
 */
public class GameScene extends Scene{

    public Sprite sprite;
    public final int NUM_SPRITES = 10;
    Sprite[] sprites = new Sprite[NUM_SPRITES];
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

       int resizedWidth = sprite.width * 2;
       int resizedHeight = sprite.height * 2;

       sprite.resizeAnimation(resizedWidth, resizedHeight, true, 1);
       sprite.resizeAnimation(resizedWidth, resizedHeight, false, 2);
       sprite.resizeAnimation(resizedWidth, resizedHeight, false, 3);
       sprite.resizeAnimation(resizedWidth, resizedHeight, false, 4);

        for(int i = 0; i < NUM_SPRITES; i++){
            sprites[i] = new Sprite(sprite);
            sprites[i].setLocation(i*32  + 32, sprite.y);
        }

        Kernel.addListeningScene(this);

        //End load screen.
    }

    @Override
    public void update(){
        flag = false;

        if(Kernel.keyListener.isKeyPressed(39)){
            sprite.setLocation(sprite.x + 2, sprite.y);
            for(int i = 0; i < NUM_SPRITES; i++){
                sprites[i].setLocation(sprites[i].x + 2, sprites[i].y);
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
            sprite.setLocation(sprite.x, sprite.y - 2);
            for(int i = 0; i < NUM_SPRITES; i++){
                sprites[i].setLocation(sprites[i].x, sprites[i].y - 2);
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
            sprite.setLocation(sprite.x, sprite.y + 2);
            for(int i = 0; i < NUM_SPRITES; i++){
                sprites[i].setLocation(sprites[i].x, sprites[i].y + 2);
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
            sprite.setLocation(sprite.x - 2, sprite.y);
            for(int i = 0; i < NUM_SPRITES; i++){
                sprites[i].setLocation(sprites[i].x - 2, sprites[i].y);
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
        }
    }
}