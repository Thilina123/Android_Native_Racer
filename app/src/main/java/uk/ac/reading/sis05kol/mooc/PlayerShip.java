package uk.ac.reading.sis05kol.mooc;

import android.graphics.Bitmap;
import android.graphics.Canvas;

/**
 * Created by Thilina on 2016-04-03.
 */
public class PlayerShip extends Ship {

    private Bullet[] bullets;

    public PlayerShip(Bitmap image, float width, float canvasWidth, float canvasHeight,Bitmap bulletImage) {
        super(image, width, canvasWidth, canvasHeight);
        bullets = new Bullet[3];
        for (int i = 0; i <bullets.length ; i++) {
            bullets[i]= new Bullet(bulletImage,width/3,canvasWidth,canvasHeight,this);
        }
    }

    public void setupBullets(){

        for (int i = 0; i <bullets.length ; i++) {

            bullets[i].setPosY(GameView.screenHeight/3*i);
            bullets[i].setPosX(getPosX());
            bullets[i].setSpeedY(-800);
        }
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        for (int i = 0; i <bullets.length ; i++) {
            bullets[i].draw(canvas);
        }

    }

    @Override
    public void update(float secondsElapsed) {
        super.update(secondsElapsed);
        for (int i = 0; i <bullets.length ; i++) {
            bullets[i].update(secondsElapsed);
        }
        if (getSpeedX()>0){
            setSpeedX(getSpeedX()-10);
            return;
        }
        if (getSpeedX()<0){
            setSpeedX(getSpeedX()+10);
            return;
        }
        setSpeedX(0);
    }

    public Bullet[] getBullets() {
        return bullets;
    }
}
