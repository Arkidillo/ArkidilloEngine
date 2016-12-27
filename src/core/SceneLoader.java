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

        /*JFrame frame = new JFrame();
        Sprite2 sprite = new Sprite2(100, 100, "Untitled.jpg", null);

        frame.setSize(1000, 800);
        frame.setResizable(false);
        frame.setFocusable(true);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.getContentPane().add(sprite);


        frame.setVisible(true);*/
    }
}
