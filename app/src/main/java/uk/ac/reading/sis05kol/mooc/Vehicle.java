package uk.ac.reading.sis05kol.mooc;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.view.Display;

/**
 * Created by Thilina on 2016-03-28.
 */
public class Vehicle extends DrawableObject {


    //The speed (pixel/second) of the vehicle in direction X and Y
    private float speedX = 0;
    private float speedY = 0;

    //scale related to screen size;
    private float scale= 1;

    public Vehicle(Bitmap image, float width, float canvasWidth, float canvasHeight) {
        super(image, width, canvasWidth, canvasHeight);
    }


    public float getSpeedX() {
        return speedX;
    }

    public void setSpeedX(float speedX) {
        this.speedX = speedX;
    }

    public float getSpeedY() {
        return speedY;
    }

    public void setSpeedY(float speedY) {
        this.speedY = speedY;
    }

    @Override
    public void update(float secondsElapsed){
        setPosX(getPosX() + (secondsElapsed * speedX));
        setPosY(getPosY() + (secondsElapsed * speedY));
    }



}
