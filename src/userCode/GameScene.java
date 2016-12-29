package userCode;

import core.*;
/**
 * Created by Devin on 12/25/2016.
 */
public class GameScene extends Scene{

    public Sprite sprite;
    boolean flag = false;

    @Override
    public void onCreate(){
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

    }

    @Override
    public void update(){
        flag = false;

        if(Kernel.keyListener.isKeyPressed(39)){
            sprite.setLocation(sprite.x + 2, sprite.y);
            if(sprite.currentAnimation == 1) {
                sprite.advanceAnimation(1);
            } else {
                sprite.resetAnimation(1);
            }
            flag = true;
            /*if(!sprite.fileName.equals("linkRight.png")){
                sprite.setImage("linkRight.png");
            }*/
        }

        if(Kernel.keyListener.isKeyPressed(38)){
            sprite.setLocation(sprite.x, sprite.y - 2);
            if(sprite.currentAnimation == 2) {
                sprite.advanceAnimation(2);
            } else {
                sprite.resetAnimation(2);
            }

            flag = true;
            /*if(!sprite.fileName.equals("linkBack.png")){
                sprite.setImage("linkBack.png");
            }*/
        }

        if(Kernel.keyListener.isKeyPressed(40)){
            sprite.setLocation(sprite.x, sprite.y + 2);
            if(sprite.currentAnimation == 3) {
                sprite.advanceAnimation(3);
            } else {
                sprite.resetAnimation(3);
            }

            flag = true;
            /*if(!sprite.fileName.equals("linkFront.png")){
                sprite.setImage("linkFront.png");
            }*/
        }

        if(Kernel.keyListener.isKeyPressed(37)){
            sprite.setLocation(sprite.x - 2, sprite.y);
            if(sprite.currentAnimation == 4) {
                sprite.advanceAnimation(4);
            } else {
                sprite.resetAnimation(4);
            }

            flag = true;
            /*if(!sprite.fileName.equals("linkLeft.png")){
                sprite.setImage("linkLeft.png");
            }*/
        }

        if(flag == false){
            sprite.resetAllAnimations();
            sprite.resetToDefault();
        }
        System.out.println(sprite.currentAnimation);
    }
}