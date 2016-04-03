package uk.ac.reading.sis05kol.mooc;

import android.graphics.Bitmap;

import java.util.Random;

/**
 * Created by Thilina on 2016-03-30.
 */
public class EnemyShip extends Ship {

    private Bitmap[] alternativeTextures;
    private float[] alternativeSpeeds;

    public EnemyShip(Bitmap image, float width, float canvasWidth, float canvasHeight) {
        super(image, width, canvasWidth, canvasHeight);
    }

    public void SetAlternatives(Bitmap[] images, float[] speeds){
        this.alternativeTextures =images;
        this.alternativeSpeeds = speeds;
    }

    @Override
    public void update(float secondsElapsed){
        setPosY(getPosY()+getSpeedY()*secondsElapsed);
//        System.out.println(getPosY()+" : "+GameView.screenHeight);
        if (getPosY()>GameView.screenHeight*2){
            Randomize();
            setPosY(-new Random().nextInt(500));
        }
    }
    public void Randomize(){
        Random r= new Random();

        int rnd= (new Random()).nextInt(alternativeSpeeds.length);
        setImage(alternativeTextures[rnd]);
        setSpeedY(alternativeSpeeds[rnd]);
        setPosX(r.nextInt((int) GameView.screenWidth));
    }

}
