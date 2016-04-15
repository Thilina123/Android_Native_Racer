package uk.ac.reading.sis05kol.mooc;

import android.graphics.Bitmap;
import android.graphics.Canvas;

/**
 * Created by Thilina on 2016-03-29.
 */
public class Road extends DrawableObject {

    private float speedX;
    private float speedY;

    public Road(Bitmap image, float width, float canvasWidth, float canvasHeight) {
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
    public void update(float secondsElapsed) {
        setPosY(getPosY()+getSpeedY()*secondsElapsed);
        if (getPosY()>getHeight()/2+GameView.screenHeight){
            setPosY(GameView.screenHeight/2);
        }
    }

    @Override
    public void draw(Canvas canvas) {
//        super.draw(canvas);
//        System.out.println(getPosX()+" : "+getPosY());
        canvas.drawBitmap(getImage(), getPosX()-getWidth(), getPosY()-getHeight(), null);
        canvas.drawBitmap(getImage(), getPosX()-getWidth(), getPosY()-2*getHeight(), null);
    }
}
