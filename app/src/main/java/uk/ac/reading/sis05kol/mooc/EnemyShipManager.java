package uk.ac.reading.sis05kol.mooc;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.os.Handler;

import java.util.Random;

/**
 * Created by Thilina on 2016-04-03.
 */
public class EnemyShipManager {
    GameView gameView;


    private Bitmap ship1Image;
    private Bitmap ship2Image;
    private Bitmap ship3Image;
    private Bitmap bulletImage;

    private EnemyShip[] ships = new EnemyShip[5];

    private EnemyShip enemyShip;


    private int score;

    public EnemyShipManager(GameView gameView) {
        this.gameView = gameView;
        ship1Image = BitmapFactory.decodeResource(gameView.getContext().getResources(),R.drawable.yellow_car);
        ship2Image = BitmapFactory.decodeResource(gameView.getContext().getResources(),R.drawable.truck);
        ship3Image = BitmapFactory.decodeResource(gameView.getContext().getResources(),R.drawable.pickup);
        bulletImage = BitmapFactory.decodeResource(gameView.getContext().getResources(),R.drawable.bullet);

        for (int i = 0; i <ships.length ; i++) {

            enemyShip = new EnemyShip(ship1Image,GameView.screenWidth/7,GameView.screenWidth,GameView.screenHeight,bulletImage);


            enemyShip.setSpeedY(450);
//            enemyShip.setPosX(new Random().nextInt((int) GameView.screenWidth));
            enemyShip.setPosX(GameView.screenWidth/2);
//            enemyShip.setPosY(new Random().nextInt((int) GameView.screenHeight));
            enemyShip.setPosY(-new Random().nextInt(500));
            System.out.println(enemyShip.getPosY()+"..............");
            enemyShip.setPosY(-300);

            Bitmap[] bmps= new Bitmap[2];
            bmps[1]= ship2Image;
            bmps[0]= ship3Image;

            float[] speeds=new float[2];
            speeds[0] = 400;
            speeds[1] = 350;

            enemyShip.SetAlternatives(bmps,speeds);
            enemyShip.setupBullets();

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

    public boolean inCollision(PlayerShip playerShip) {
        Bullet[] bullets = playerShip.getBullets();
        for (int i = 0; i <ships.length ; i++) {
            for (int j = 0; j < bullets.length; j++) {
                if (ships[i].inCollision(bullets[j])){
                    ships[i].Randomize();
                    ChangeScore();
                }
                if (ships[i].getBullet().inCollision(bullets[j])){
                    ships[i].setupBullets();
                }
            }
            if (ships[i].inCollision(playerShip) || ships[i].getBullet().inCollision(playerShip)){
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
