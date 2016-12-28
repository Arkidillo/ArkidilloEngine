package core;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

/**
 * Created by Devin on 12/26/2016.
 */
public class KeyboardListener implements KeyListener{

    private boolean[] keys = new boolean[256];
    public ArrayList<GameObject> objectsListening = new ArrayList<>();
    public ArrayList<Scene> scenesListening = new ArrayList<>();

    @Override
    public void keyTyped(KeyEvent e) {
        for(GameObject gameObj: objectsListening){
            gameObj.keyTyped(e.getKeyCode());
        }

        for(Scene scene: scenesListening){
            scene.keyTyped(e.getKeyCode());
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        keys[e.getKeyCode()] = true;

        for(GameObject gameObj: objectsListening){
            gameObj.keyPressed(e.getKeyCode());
        }

        for(Scene scene: scenesListening){
            scene.keyPressed(e.getKeyCode());
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        keys[e.getKeyCode()] = false;

        for(GameObject gameObj: objectsListening){
            gameObj.keyReleased(e.getKeyCode());
        }

        for(Scene scene: scenesListening){
            scene.keyReleased(e.getKeyCode());
        }
    }

    public boolean isKeyPressed(int keyCode){
        return keys[keyCode];
    }

    public void addListeningObject(GameObject o){
        objectsListening.add(o);
    }

    public void addListeningScene(Scene s){
        scenesListening.add(s);
    }
}
