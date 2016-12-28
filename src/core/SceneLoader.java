package core;

import userCode.*;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Devin on 12/25/2016.
 */
public class SceneLoader {

    public static void main(String [] Arkidillo) {
        Kernel kernel = new Kernel(); //Choose your initial scene to be loaded here. (Here we chose "GameScene" as our starting scene.
        Thread t = new Thread(kernel);
        t.start();

        Kernel.showScene(new GameScene());
    }
}
