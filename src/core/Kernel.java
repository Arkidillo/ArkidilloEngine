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
        gui = new Gui();
        frameDelay = 16; //Frame delay in milliseconds, set to ~60 frames per second.
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
        if (currentScene != null) {
            ArrayList<Sprite> collideables = currentScene.collideableSprites;
            ArrayList<Sprite> detectSprites = currentScene.checkCollideableSprites;
            Sprite body1;
            Sprite body2;
            int x1, x2, y1, y2, width1, width2, height1, height2;

            for (int i = 0; i < detectSprites.size(); i++) {
                body1 = detectSprites.get(i);
                x1 = body1.getX();
                y1 = body1.getY();
                width1 = body1.getWidth();
                height1 = body1.getHeight();
                for (int j = 0; j < collideables.size(); j++) {
                    body2 = collideables.get(j);
                    if (!body2.equals(body1)) {
                        x2 = body2.getX();
                        y2 = body2.getY();
                        width2 = body2.getWidth();
                        height2 = body2.getHeight();

                        if (x1 + width1 == x2 && (Helper.isBetween(y1, y2, y2 + height1) || Helper.isBetween(y2, y1, y1 + height1) || y1 == y2))
                            currentScene.onCollision(body1, body2);
                        else if (y1 + height1 == y2 && (Helper.isBetween(x1, x2, x2 + width2) || Helper.isBetween(x2, x1, x1 + width1) || x1 == x2))
                            currentScene.onCollision(body1, body2);
                        else if (y1 + height1 == y2 && x1 + width1 == x2)
                            currentScene.onCollision(body1, body2);
                        else if (y1 == y2 + height2 && x1 + width1 == x2)
                            currentScene.onCollision(body1, body2);
                        else if (x1 == x2 && y1 == y2)
                            currentScene.onCollision(body1, body2);
                        else if (Helper.isBetween(x2, x1, x1 + width1) && (Helper.isBetween(y2, y1, y1 + height1) || Helper.isBetween(y2 + height2, y1, y1 + height1)))
                            currentScene.onCollision(body1, body2);
                        if (!body2.checkCollisions) {
                            Sprite temp = body1;
                            body1 = body2;
                            body2 = temp;

                            if (x1 + width1 == x2 && (Helper.isBetween(y1, y2, y2 + height1) || Helper.isBetween(y2, y1, y1 + height1) || y1 == y2))
                                currentScene.onCollision(body1, body2);
                            else if (y1 + height1 == y2 && (Helper.isBetween(x1, x2, x2 + width2) || Helper.isBetween(x2, x1, x1 + width1) || x1 == x2))
                                currentScene.onCollision(body1, body2);
                            else if (y1 + height1 == y2 && x1 + width1 == x2)
                                currentScene.onCollision(body1, body2);
                            else if (y1 == y2 + height2 && x1 + width1 == x2)
                                currentScene.onCollision(body1, body2);
                            else if (x1 == x2 && y1 == y2)
                                currentScene.onCollision(body1, body2);
                            else if (Helper.isBetween(x2, x1, x1 + width1) && (Helper.isBetween(y2, y1, y1 + height1) || Helper.isBetween(y2 + height2, y1, y1 + height1)))
                                currentScene.onCollision(body1, body2);

                        }
                    }
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
