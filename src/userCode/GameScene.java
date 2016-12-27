package userCode;

import core.*;
/**
 * Created by Devin on 12/25/2016.
 */
public class GameScene extends Scene{

    public Sprite sprite;

    @Override
    public void onCreate(){
       sprite = new Sprite(101,101,"Untitled.jpg",this);
       Kernel.addListeningScene(this);
    }

    @Override
    public void update(){
        if(Kernel.keyListener.isKeyPressed(10)){
            System.out.println("Enter pressed");
        }
    }

    @Override
    public void keyPressed(int keyCode){
        if (keyCode == 39) {
            sprite.setLocation(sprite.getX() + 5, sprite.getY());
        }
    }


}