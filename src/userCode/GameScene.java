package userCode;

import core.*;
/**
 * Created by Devin on 12/25/2016.
 */
public class GameScene extends Scene{

    @Override
    public void onCreate(){
        Sprite sprite = new Sprite(101,101,"Untitled.jpg");
    }

    @Override
    public void update(){
        if(Kernel.keyListener.isKeyPressed(10)){
            System.out.println("Enter pressed");
        }
    }


}