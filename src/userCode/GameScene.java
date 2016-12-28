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
       sprite = new Sprite(0,0,"Untitled.jpg",this);
       sprite2 = new Sprite(1920, 1080, "Untitled.jpg", this);

       for(int i = 0; i < 100; i++){
           new Sprite(i,i,"Untitled.jpg", this);
       }
    }

    @Override
    public void update(){
        if(Kernel.keyListener.isKeyPressed(39)){
            sprite.setLocation(sprite.x + 5, sprite.y);
            sprite2.setLocation(sprite2.x - 5, sprite2.y);
        }

        if(Kernel.keyListener.isKeyPressed(38)){
            sprite.setLocation(sprite.x, sprite.y - 5);
            sprite2.setLocation(sprite2.x, sprite2.y + 5);
        }

        if(Kernel.keyListener.isKeyPressed(40)){
            sprite.setLocation(sprite.x, sprite.y + 5);
            sprite2.setLocation(sprite2.x, sprite2.y - 5);
        }

        if(Kernel.keyListener.isKeyPressed(37)){
            sprite.setLocation(sprite.x - 5, sprite.y);
            sprite2.setLocation(sprite2.x + 5, sprite2.y);
        }
    }
}