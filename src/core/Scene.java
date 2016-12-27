package core;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Created by Devin on 12/25/2016.
 */
public abstract class Scene {

    public SceneData sceneData = new SceneData();

    public abstract void update();

    public abstract void onCreate();

}
