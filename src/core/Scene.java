package core;

/**
 * Created by Devin on 12/25/2016.
 */
public abstract class Scene {

    public SceneData sceneData;

    public Scene(){
        sceneData = new SceneData();
    }

    public abstract void update();

    public abstract void onCreate();

    public void keyPressed(int keyCode){
    }

    public void keyReleased(int keyCode){
    }

    public void keyTyped(int keyCode){
    }

}
