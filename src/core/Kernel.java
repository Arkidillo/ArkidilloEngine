package core;

/**
 * Created by Devin on 12/25/2016.
 */
public class Kernel implements Runnable{

    public static Scene currentScene;
    public static Gui gui;
    public static int frameDelay;
    public static KeyboardListener keyListener = new KeyboardListener();

    public Kernel(){
        frameDelay = 16; //Frame delay in milliseconds, set to ~60 frames per second.
        gui = new Gui();
        gui.addKeyListener(keyListener);
    }

    public void run(){
        long timeStart;

        while(true){
            timeStart = System.currentTimeMillis();
            update();

            Helper.sleep((int)(frameDelay-(System.currentTimeMillis() - timeStart)));
        }
    }

    public static void showScene(Scene scene){
        currentScene = scene;
        scene.onCreate();
    }

    public void update(){   //Called once per frame update, master update function to call update of all other objects in the scene.
        if(currentScene != null && currentScene.sceneData != null) {
            for (GameObject obj : currentScene.sceneData.gameObjects) {    //Goes through all sprites, and start a new thread to update all positions.
                Thread t = new Thread(obj);
                t.start();
            }
        }

        //for() Check for collisions next.
    }


}
