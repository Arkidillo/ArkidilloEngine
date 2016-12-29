package userCode;

import core.*;
/**
 * Created by Devin on 12/25/2016.
 */
public class GameScene extends Scene{

    public Sprite sprite;
    public Sprite sprite2;

    @Override
    public void onCreate(){
       sprite = new Sprite(0,0,"linkFront.png", this);

       String[] frames = {"linkFront.png", "linkFront2.png"};

       sprite.addAnimation(frames,30,sprite,1);
       sprite.resizeAnimation(sprite.width * 2, sprite.height * 2, true, 1);
       sprite.startAnimation(1);

    }

    @Override
    public void update(){
        if(Kernel.keyListener.isKeyPressed(39)){
            sprite.setLocation(sprite.x + 2, sprite.y);
            /*if(!sprite.fileName.equals("linkRight.png")){
                sprite.setImage("linkRight.png");
            }*/
        }

        if(Kernel.keyListener.isKeyPressed(38)){
            sprite.setLocation(sprite.x, sprite.y - 2);
            /*if(!sprite.fileName.equals("linkBack.png")){
                sprite.setImage("linkBack.png");
            }*/
        }

        if(Kernel.keyListener.isKeyPressed(40)){
            sprite.setLocation(sprite.x, sprite.y + 2);
            //sprite.advanceAnimation(1);
            /*if(!sprite.fileName.equals("linkFront.png")){
                sprite.setImage("linkFront.png");
            }*/
        }

        if(Kernel.keyListener.isKeyPressed(37)){
            sprite.setLocation(sprite.x - 2, sprite.y);
            /*if(!sprite.fileName.equals("linkLeft.png")){
                sprite.setImage("linkLeft.png");
            }*/
        }
    }
}