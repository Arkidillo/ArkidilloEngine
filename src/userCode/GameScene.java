package userCode;

import core.*;
/**
 * Created by Devin on 12/25/2016.
 */
public class GameScene extends Scene{

    public static Sprite sprite;

    @Override
    public void onCreate(){
       sprite = new Sprite(101,101,"Untitled.jpg",this);
       //Kernel.addListeningScene(this);
    }

    @Override
    public void update(){
        if(Kernel.keyListener.isKeyPressed(39)){
            sprite.setLocation(sprite.x + 5, sprite.y);
        }

        if(Kernel.keyListener.isKeyPressed(38)){
            sprite.setLocation(sprite.x, sprite.y - 5);
        }

        if(Kernel.keyListener.isKeyPressed(40)){
            sprite.setLocation(sprite.x, sprite.y + 5);
        }

        if(Kernel.keyListener.isKeyPressed(37)){
            sprite.setLocation(sprite.x - 5, sprite.y);
        }
    }

    @Override
    public void keyPressed(int keyCode){
        /*if (keyCode == 39) {
            sprite.setLocation(sprite.x + 10, sprite.y);
        }*/
    }


}