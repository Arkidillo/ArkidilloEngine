package core;

import java.util.ArrayList;

/**
 * Created by Devin on 12/25/2016.
 */
public abstract class Scene {

    public ArrayList<Sprite> spritesInScene = new ArrayList<>();
    public ArrayList<Sprite> collideableSprites = new ArrayList<>();
    public ArrayList<Sprite> checkCollideableSprites = new ArrayList<>();

    public abstract void update();

    public abstract void onCreate();

    public void keyPressed(int keyCode){
    }

    public void keyReleased(int keyCode){
    }

    public void keyTyped(int keyCode){
    }

    public void onCollision(Sprite sprite1, Sprite sprite2){    //Users will override this based on what they want to happen during a collision. They can use sprite.getName() to check what actual object collided with what
    }

    public void removeSprite(Sprite sprite){
        spritesInScene.remove(sprite);
        Kernel.gui.remove(sprite);
    }

}
