package uk.ac.reading.sis05kol.mooc;

//Other parts of the android libraries that we use
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.os.Handler;
import android.widget.TextView;

public class TheGame extends GameThread{

    //Will store the image of a Car
    private Bitmap playerShipImage;
    private Bitmap roadImage;
    private Bitmap bulletImage;


    private PlayerShip playerShip;
    private Road road;
    private EnemyShipManager enemyShips;
    private MainActivity mainActivity;
    private TextView scoreView;
    private int score;
    private int playerLives = 10;
    private int frameCount;
    private GameView gameView;

    //This is run before anything else, so we can prepare things here
    public TheGame(GameView gameView, MainActivity mainActivity, TextView scoreView) {
        //House keeping
        super(gameView);
        this.gameView=gameView;
        this.mainActivity=mainActivity;
        this.scoreView=scoreView;
        //Prepare the image so we can draw it on the screen (using a canvas)
        playerShipImage = BitmapFactory.decodeResource(gameView.getContext().getResources(),R.drawable.small_red_car);
        roadImage = BitmapFactory.decodeResource(gameView.getContext().getResources(),R.drawable.road);
        bulletImage = BitmapFactory.decodeResource(gameView.getContext().getResources(),R.drawable.bullet);

        playerShip = new PlayerShip(playerShipImage,GameView.screenWidth/7,GameView.screenWidth,GameView.screenHeight,bulletImage);
        road = new Road(roadImage,GameView.screenWidth/2,GameView.screenWidth,GameView.screenHeight);
        System.out.println(mCanvasHeight);

        enemyShips = new EnemyShipManager(gameView);
    }

    //This is run before a new game (also after an old game)
    @Override
    public void setupBeginning() {
        //Initialise speeds
        playerShip.setSpeedX(0);
        playerShip.setSpeedY(0);

        road.setSpeedY(300);
        road.setPosX(GameView.screenWidth/2);
        road.setPosY(GameView.screenHeight/2);


        //Place the Car in the middle of the screen.
        //playerShipImage.Width() and playerShipImage.getHeigh() gives us the height and width of the image of the Car
        playerShip.setPosX(GameView.screenWidth/2);
        playerShip.setPosY(GameView.screenHeight*.8f);
        playerShip.setupBullets();
    }

    @Override
    protected void doDraw(Canvas canvas) {
        //If there isn't a canvas to draw on do nothing
        //It is ok not understanding what is happening here
        if(canvas == null) return;

        super.doDraw(canvas);


        //draw the image of the Car using the X and Y of the Car
        //drawBitmap uses top left corner as reference, we use middle of picture
        //null means that we will use the image without any extra features (called Paint)
        //road.draw(canvas);
        playerShip.draw(canvas);
        enemyShips.draw(canvas);
    }

    //This is run whenever the phone is touched by the user

	@Override
	protected void actionOnTouch(float x, float y) {
		//Increase/decrease the speed of the Car making the Car move towards the touch
        playerShip.setSpeedX(x-playerShip.getPosX());
        playerShip.setSpeedX(playerShip.getSpeedX()*1.5f);
//        playerShip.setSpeedY(y-playerShip.getPosY());
//        if(x>GameView.screenWidth/2) {
//            playerShip.setPosX(playerShip.getPosX()+50);
//        }else {
//            playerShip.setPosX(playerShip.getPosX()-50);
//        }
	}

    @Override
    protected void actionOnTouchLift() {
        playerShip.setSpeedX(0);
    }
    //This is run whenever the phone moves around its axises
//	@Override
//	protected void actionWhenPhoneMoved(float xDirection, float yDirection, float zDirection) {
//		/*
//		Increase/decrease the speed of the Car.
//		If the Car moves too fast try and decrease 70f
//		If the Car moves too slow try and increase 70f
//		 */
//
//		mCarSpeedX = mCarSpeedX + 70f * xDirection;
//		mCarSpeedY = mCarSpeedY - 70f * yDirection;
//	}


    //This is run just before the game "scenario" is printed on the screen
    @Override
    protected void updateGame(float secondsElapsed) {
        //Move the Car's X and Y using the speed (pixel/sec)
        playerShip.update(secondsElapsed);
        //road.update(secondsElapsed);
        enemyShips.update(secondsElapsed);
        frameCount++;
//        if(frameCount%100==0) {
//            score++;
//            ChangeScore(score);
//        }
        CheckCollisions();
    }

    void CheckCollisions(){
        if (enemyShips.inCollision(playerShip)){
            System.out.println("collided");
            playerLives--;
            if(playerLives<0) {
                mainActivity.onGameOver();
            }
//            Intent myIntent = new Intent(activity,Game_over.class);
//            activity.this.startActivity(myIntent);
        }
    }

    private Handler mHandler = new Handler();
    public void ChangeScore(int score) {
        final String str = score+"";
        if (score%100==0){
//            super.changeBackGround();
        }
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                // This gets executed on the UI thread so it can safely modify Views
                scoreView.setText(str);
            }
        });
    }
}

// This file is part of the course "Begin Programming: Build your first mobile game" from futurelearn.com
// Copyright: University of Reading and Karsten Lundqvist
// It is free software: you can redistribute it and/or modify
// it under the terms of the GNU General Public License as published by
// the Free Software Foundation, either version 3 of the License, or
// (at your option) any later version.
//
// It is is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
// GNU General Public License for more details.
//
//
// You should have received a copy of the GNU General Public License
// along with it.  If not, see <http://www.gnu.org/licenses/>.
