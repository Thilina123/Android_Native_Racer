package uk.ac.reading.sis05kol.mooc;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import java.util.Random;

/**
 * Created by Thilina on 2016-03-30.
 */
public class EnemyShip extends Ship {

    private Bitmap[] alternativeTextures;
    private float[] alternativeSpeeds;
    private Bullet bullet;

    public EnemyShip(Bitmap image, float width, float canvasWidth, float canvasHeight, Bitmap bulletImage) {
        super(image, width, canvasWidth, canvasHeight);
        bullet =new Bullet(bulletImage,width/3,canvasWidth,canvasHeight,this);
    }

    public void SetAlternatives(Bitmap[] images, float[] speeds){
        this.alternativeTextures =images;
        this.alternativeSpeeds = speeds;
    }

    public Bullet getBullet() {
        return bullet;
    }

    public void setupBullets(){
        bullet.setSpeedY(800);
        bullet.setPosX(getPosX());
        bullet.setPosY(getPosY());
    }

    @Override
    public void update(float secondsElapsed){
        setPosY(getPosY()+getSpeedY()*secondsElapsed);
        bullet.update(secondsElapsed);
        if (bullet.getPosY()>GameView.screenHeight){
            setupBullets();
        }
//        System.out.println(getPosY()+" : "+GameView.screenHeight);
        if (getPosY()>GameView.screenHeight*2){
            Randomize();
        }
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        bullet.draw(canvas);
    }

    public void Randomize(){
        Random r= new Random();

        int rnd= (new Random()).nextInt(alternativeSpeeds.length);
        setImage(alternativeTextures[rnd]);
        setSpeedY(r.nextInt(600));
        setPosX(r.nextInt((int) GameView.screenWidth));
        setPosY(-new Random().nextInt(500));
    }

}
