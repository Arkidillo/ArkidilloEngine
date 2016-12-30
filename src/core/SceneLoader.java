package core;

import userCode.*;
/**
 * Created by Devin on 12/25/2016.
 */
public class SceneLoader {

    public static void main(String [] Arkidillo) {
        Kernel kernel = new Kernel(new GameScene());
        Thread t = new Thread(kernel);
        t.start();
    }
}
