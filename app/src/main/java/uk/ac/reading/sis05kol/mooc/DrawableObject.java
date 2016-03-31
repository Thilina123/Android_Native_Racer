package uk.ac.reading.sis05kol.mooc;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.util.DisplayMetrics;

/**
 * Created by Thilina on 2016-03-29.
 */
public class DrawableObject {
    private Bitmap image;

    private float width;
    private float height;

    private float posX;
    private float posY;

    public float getScreenWidth() {
        return screenWidth;
    }

    public float getScreenHeight() {
        return screenHeight;
    }

    public Bitmap getImage() {
        return image;
    }

    private float screenWidth;
    private float screenHeight;

    public void setImage(Bitmap image) {
        this.image = image;
    }

    public DrawableObject(Bitmap image, float width, float canvasWidth, float canvasHeight) {
        this.image = image;
        this.width =width;
        this.screenWidth = canvasWidth;
        this.screenHeight = canvasHeight;
        this.height = width*image.getHeight()/image.getWidth();
    }
    public float getWidth() {
        return width;
    }


    public float getHeight() {
        return height;
    }


    public float getPosX() {
        return posX;
    }

    public void setPosX(float posX) {
        this.posX = posX;
    }

    public float getPosY() {
        return posY;
    }

    public void setPosY(float posY) {
        this.posY = posY;
    }

    public void draw(Canvas canvas){
        canvas.drawBitmap(image, posX-width/2, posY, null);
    }

    public void update(float secondsElapsed){

    }
    public boolean inCollision(DrawableObject other){

        if (Math.abs(other.getPosX()-getPosX())<(other.getWidth()+getWidth())/2){
            if (Math.abs(other.getPosY()-getPosY())<(other.getHeight()+getHeight())/2){
                return true;
            }
        }

        return false;

    }

}
