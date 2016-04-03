package uk.ac.reading.sis05kol.mooc;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

import java.util.Random;

/**
 * Created by Thilina on 2016-04-03.
 */
public class EnemyShipManager {
    GameView gameView;


    private Bitmap ship1Image;
    private Bitmap ship2Image;
    private Bitmap ship3Image;

    private EnemyShip[] ships = new EnemyShip[3];

    private EnemyShip enemyShip;

    public EnemyShipManager(GameView gameView) {
        this.gameView = gameView;
        ship1Image = BitmapFactory.decodeResource(gameView.getContext().getResources(),R.drawable.yellow_car);
        ship2Image = BitmapFactory.decodeResource(gameView.getContext().getResources(),R.drawable.truck);
        ship3Image = BitmapFactory.decodeResource(gameView.getContext().getResources(),R.drawable.pickup);

        for (int i = 0; i <ships.length ; i++) {

            enemyShip = new EnemyShip(ship1Image,GameView.screenWidth/7,GameView.screenWidth,GameView.screenHeight);


            enemyShip.setSpeedY(450);
            enemyShip.setPosX(new Random().nextInt((int) GameView.screenWidth));
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

    public boolean inCollision(Ship playerShip) {
        for (int i = 0; i <ships.length ; i++) {
            if (ships[i].inCollision(playerShip)){
                return true;
            }
        }
        return false;
    }
}
