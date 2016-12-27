package core;

import java.util.ArrayList;

/**
 * Created by Devin on 12/25/2016.
 */
public class Cell {
    ArrayList<GameObject> objects = new ArrayList<>();  //Each cell has an arraylist of gameObjects for each xy coordinate

    public void add(GameObject obj){
        objects.add(obj);
    }
}
