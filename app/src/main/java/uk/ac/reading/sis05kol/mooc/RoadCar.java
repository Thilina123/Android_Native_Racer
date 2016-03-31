package uk.ac.reading.sis05kol.mooc;

import android.graphics.Bitmap;

/**
 * Created by Thilina on 2016-03-30.
 */
public class RoadCar extends Vehicle{

    public RoadCar(Bitmap image, float width, float canvasWidth, float canvasHeight) {
        super(image, width, canvasWidth, canvasHeight);
    }

    @Override
    public void update(float secondsElapsed){
        setPosY(getPosY()+getSpeedY()*secondsElapsed);
//        System.out.println(getPosY()+" : "+GameView.screenHeight);
        if (getPosY()>GameView.screenHeight*2){
            setPosY(-GameView.screenHeight);
        }
    }

}
