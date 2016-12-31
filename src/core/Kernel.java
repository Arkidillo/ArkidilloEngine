package core;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;

/**
 * Created by Devin on 12/25/2016.
 */
public class Kernel implements Runnable{

    public static Scene currentScene;
    public static Gui gui;
    public static int frameDelay;
    public ArrayList<Thread> updateThreads = new ArrayList<>();
    public static KeyboardListener keyListener = new KeyboardListener();

    public Kernel(Scene scene){
        frameDelay = 16; //Frame delay in milliseconds, set to ~60 frames per second.
        gui = new Gui();
        gui.addKeyListener(keyListener);
        scene.onCreate();
        currentScene = scene;
    }

    public void run(){
        long timeStart;
        long sleepTime;

        while(true){
            timeStart = System.currentTimeMillis();
            update();


            if ((sleepTime = frameDelay+(timeStart - System.currentTimeMillis())) > 0){
                Helper.sleep(sleepTime);
            } else {
                System.out.println("less than 60 fps!");
                System.out.println("FPS: " + (1000.0/(System.currentTimeMillis() - timeStart)));
                Helper.sleep(0);
            }
        }
    }

    public static void showScene(Scene scene){
        scene.onCreate();
        currentScene = scene;
    }

    public void update(){   //Called once per frame update, master update function to call update of all other objects in the scene.
        if(currentScene != null) {
            currentScene.update();
            try {
                if (currentScene.spritesInScene != null) {
                    for (Sprite obj : currentScene.spritesInScene) {    //Goes through all sprites, and start a new thread to update all positions.
                        Thread t = new Thread(obj);
                        updateThreads.add(t);
                        t.start();
                    }
                }
            } catch (ConcurrentModificationException e){

            }
        }
        for(Thread t: updateThreads){
            try {
                t.join();
            } catch (InterruptedException e){
                System.out.println("WARNING: Thread join interrupted!");
                e.printStackTrace();
            }
        }
        ArrayList<Sprite> collideables = currentScene.collideableSprites;
        Sprite body1;
        Sprite body2;

        for(int i = 0; i < collideables.size(); i++){
            body1 = collideables.get(i);
            for(int j = 0; j < collideables.size(); j++){
                body2 = collideables.get(j);
                if(body1.getX() == body2.getX() && body1.getY() == body2.getY()){
                    currentScene.onCollision(body1, body2);
                }
            }
        }
    }

    public static void addListeningSprite(Sprite o){
        keyListener.addListeningSprite(o);
    }

    public static void addListeningScene(Scene s){
        keyListener.addListeningScene(s);
    }


}
