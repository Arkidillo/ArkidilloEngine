package core;

import userCode.*;
/**
 * Created by Devin on 12/25/2016.
 */
public class SceneLoader {

    public static void main(String [] Arkidillo) {
        Kernel kernel = new Kernel();
        Thread t = new Thread(kernel);
        t.start();

        //Choose your initial scene to be loaded here. (Here we chose "GameScene" as our starting scene.
        Kernel.showScene(new GameScene());
    }
}
