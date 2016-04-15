package uk.ac.reading.sis05kol.mooc;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.os.Handler;

import java.util.Random;

/**
 * Created by Thilina on 2016-04-03.
 */
public class RoadCarManager {
    GameView gameView;


    private Bitmap v1Image;
    private Bitmap v2Image;
    private Bitmap v3Image;
    private Bitmap bulletImage;

    private RoadCar[] ships = new RoadCar[3];

    private RoadCar enemyShip;


    private int score;

    public RoadCarManager(GameView gameView) {
        this.gameView = gameView;
        v1Image = BitmapFactory.decodeResource(gameView.getContext().getResources(),R.drawable.yellow_car);
        v2Image = BitmapFactory.decodeResource(gameView.getContext().getResources(),R.drawable.truck);
        v3Image = BitmapFactory.decodeResource(gameView.getContext().getResources(),R.drawable.pickup);
//        bulletImage = BitmapFactory.decodeResource(gameView.getContext().getResources(),R.drawable.bullet);

        for (int i = 0; i <ships.length ; i++) {

            enemyShip = new RoadCar(v1Image,GameView.screenWidth/7,GameView.screenWidth,GameView.screenHeight);


            enemyShip.setSpeedY(450);
//            enemyShip.setPosX(new Random().nextInt((int) GameView.screenWidth));
            enemyShip.setPosX(GameView.screenWidth/2);
//            enemyShip.setPosY(new Random().nextInt((int) GameView.screenHeight));
            enemyShip.setPosY(-new Random().nextInt(500));
            System.out.println(enemyShip.getPosY()+"..............");
            enemyShip.setPosY(-300);

            Bitmap[] bmps= new Bitmap[2];
            bmps[1]= v2Image;
            bmps[0]= v3Image;

            float[] speeds=new float[2];
            speeds[0] = 400;
            speeds[1] = 350;

            enemyShip.SetAlternatives(bmps,speeds);
//            enemyShip.setupBullets();

            ships[i]=enemyShip;
        }


    }

    public void draw(Canvas canvas) {
        for (int i = 0; i <ships.length ; i++) {
            ships[i].draw(canvas);
        }
    }

    public void update(float secondsElapsed) {

        for (int i = 0; i <ships.length ; i++) {
            ships[i].update(secondsElapsed);
        }

    }

    public boolean inCollision(Vehicle playerShip) {
        for (int i = 0; i <ships.length ; i++) {
            if (ships[i].inCollision(playerShip)){
                return true;
            }
        }
        return false;
    }

    private Handler mHandler = new Handler();
    public void ChangeScore() {
        score++;
        final String str = "score : "+score;
        if (score%100==0){
//            super.changeBackGround();
        }
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                // This gets executed on the UI thread so it can safely modify Views
                gameView.getScoreView().setText(str);
            }
        });
    }
}
