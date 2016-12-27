package core;

import java.util.ArrayList;

/**
 * Created by Devin on 12/25/2016.
 */
public class Cell {
    ArrayList<Sprite> objects = new ArrayList<>();  //Each cell has an arraylist of gameObjects for each xy coordinate

    public void add(Sprite obj){
        objects.add(obj);
    }

    public void remove(Sprite obj){
        objects.remove(obj);
    }

    public boolean contains(Sprite obj){
        return objects.contains(obj);
    }
}
