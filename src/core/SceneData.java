package core;

import java.util.ArrayList;

/**
 * Created by Devin on 12/25/2016.
 */
public class SceneData {
    public ArrayList<GameObject> gameObjects = new ArrayList<>();
    public Cell[][] map = new Cell[Kernel.gui.width][Kernel.gui.height];

    public SceneData(){
        for (int i = 0; i < map.length; i++){
            for (int j = 0; j < map[0].length; j++){
                map[i][j] = new Cell();
            }
        }
    }

    public void add(GameObject obj, int x, int y){
        gameObjects.add(obj);
        map[x][y].add(obj);
    }
}