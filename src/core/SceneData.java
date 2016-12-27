package core;

import java.util.ArrayList;

/**
 * Created by Devin on 12/25/2016.
 */
public class SceneData {
    public ArrayList<Sprite> sprites = new ArrayList<>();
    public Cell[][] map = new Cell[3*Kernel.gui.width][3*Kernel.gui.height];
    public int width;
    public int height;

    public SceneData(){
        width = Kernel.gui.width;
        height = Kernel.gui.height;

        for (int i = 0; i < map.length; i++){
            for (int j = 0; j < map[0].length; j++){
                map[i][j] = new Cell();
            }
        }
    }

    public void add(Sprite obj){
        sprites.add(obj);
        map[width + obj.x][height + obj.y].add(obj);
    }

    public Cell getCell(int x, int y){
        return map[x+Kernel.gui.width][y+Kernel.gui.height];
    }

    public void moveSprite(int x1, int y1, int x2, int y2, Sprite obj){
        if(map[width + x1][height + y1].contains(obj)) {
            map[width + x1][height + y1].remove(obj);
        }

        map[width + x2][height + y2].add(obj);
    }
}