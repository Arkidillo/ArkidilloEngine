package core;

/**
 * Created by Devin on 12/25/2016.
 */


public class Helper {


    public static void sleep(int milli){
        try {
            Thread.sleep(milli);
        } catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
