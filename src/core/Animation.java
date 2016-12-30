package core;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Devin on 12/28/2016.
 */
public class Animation {
    public int animationId;
    public Sprite sprite;
    public ArrayList<Image> animationFrames = new ArrayList<>();
    public ArrayList<Integer> animationDelays = new ArrayList<>();
    public int animationDelay;
    public int currentFrame;
    public int currentDelay;

    public Animation(String[] f, int delayFrames, Sprite sprite, int animationId){
        this.sprite = sprite;
        this.animationId = animationId;

        currentFrame = 0;
        for(int i = 0; i < f.length; i++){
            animationFrames.add(ImageLoader.loadImage(f[i]));
        }

        animationDelay = delayFrames;
        currentDelay = delayFrames;
    }

    public Animation(String[] f, int[] delays, Sprite sprite, int animationId){
        this.sprite = sprite;
        this.animationId = animationId;

        currentFrame = 0;
        for(int i = 0; i < f.length; i++){
            animationFrames.add(ImageLoader.loadImage(f[i]));
        }

        for(int i = 0; i < delays.length; i++){
            animationDelays.add(delays[i]);
        }
    }

    public Animation(Animation a, Sprite s){
        animationId = a.animationId;
        for(int i = 0; i < a.animationFrames.size(); i++){
            animationFrames.add(a.animationFrames.get(i));
        }
        if(animationDelays.size() != 0){
            for(int i = 0; i < a.animationDelays.size(); i++){
                animationDelays.add(a.animationDelays.get(i));
            }
        } else {
            animationDelay = a.animationDelay;
        }

        currentFrame = a.currentFrame;
        currentDelay = a.currentDelay;
        sprite = s;
    }

    public void nextAnimationFrame(){
        if (--currentDelay == 0) {  //counts down the delay until you reach 0
            nextFrame(); //Then you go to the next frame in the animation
            if(animationDelays.size() != 0) {   //if you are using the dynamic frame delay, go to the next frame's delay
                currentDelay = animationDelays.get(currentFrame);
            } else {//else, set the delay back up to the given frame delay amount
                currentDelay = animationDelay;
            }
        }
    }

    public void nextFrame(){//Sets the image to the next frame of the animation.
        sprite.image = animationFrames.get((++currentFrame)%animationFrames.size());
        sprite.width = sprite.image.getWidth(null);
        sprite.height = sprite.image.getHeight(null);
        sprite.needToRedraw = true;
    }

    public void resizeAnimation(int width, int height, boolean resizeDefaultImage){  //Resizes all of the images in the current animation image arraylist.
        if(resizeDefaultImage) {
            sprite.resizeDefaultImage(width, height);
        }

        ArrayList<Image> newAnimations = new ArrayList<>();
        for(int i = 0; i < animationFrames.size(); i++){
            newAnimations.add(ImageLoader.resizeImage(width, height, animationFrames.get(i)));  //Add the resized images to a new array, in order of their animation
        }
        for (Image image1 : animationFrames = newAnimations);   //copy all of the new images back into the animationFrames, so the resized images will be used in the animation going further.
    }

    public int getCurrentFrameNumber(){    //This will start at 0!
        return currentFrame;
    }

    public void resetAnimation(){   //Sets the currentAnimation to this animation, and sets it back to the first frame of the animation. Call this when you are just starting an animation from the beginning.
        currentFrame = 0;
        if(animationDelay == 0){
            currentDelay = animationDelays.get(0);
        } else {
            currentDelay = animationDelay;
        }
    }
}
