package core;

import java.util.ArrayList;

/**
 * Created by Devin on 12/25/2016.
 */
public class SceneData {
    public ArrayList<GameObject> gameObjects = new ArrayList<>();
    public Cell[][] map;

    public void add(Sprite obj){
        gameObjects.add(obj);
    }
}