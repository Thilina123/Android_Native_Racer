package uk.ac.reading.sis05kol.mooc;

//Other parts of the android libraries that we use
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.os.Handler;
import android.widget.TextView;

import java.util.Random;

public class TheGame extends GameThread{

    //Will store the image of a Car
    private Bitmap carImage;
    private Bitmap roadImage;
    private Bitmap yellow_car_image;
    private Bitmap truck;
    private Bitmap pickup;


    private Vehicle car;
    private Road road;
    private RoadCarManager roadVehicles;

    private MainActivity mainActivity;
    private TextView scoreView;
    private int score;
    private int frameCount;
    private GameView gameView;
    private Random rnd;
    private int rotation;
    private int rotationChange;

    public  static boolean rotated;

    //This is run before anything else, so we can prepare things here
    public TheGame(GameView gameView, MainActivity mainActivity, TextView scoreView) {
        //House keeping
        super(gameView);
        this.gameView=gameView;
        this.mainActivity=mainActivity;
        this.scoreView=scoreView;
        //Prepare the image so we can draw it on the screen (using a canvas)
        carImage = BitmapFactory.decodeResource(gameView.getContext().getResources(),R.drawable.small_red_car);
        roadImage = BitmapFactory.decodeResource(gameView.getContext().getResources(),R.drawable.road);
        yellow_car_image = BitmapFactory.decodeResource(gameView.getContext().getResources(),R.drawable.yellow_car);
        truck = BitmapFactory.decodeResource(gameView.getContext().getResources(),R.drawable.truck);
        pickup = BitmapFactory.decodeResource(gameView.getContext().getResources(),R.drawable.pickup);

        car = new Vehicle(carImage,GameView.screenWidth/7,GameView.screenWidth,GameView.screenHeight);
        road = new Road(roadImage,GameView.screenWidth/2,GameView.screenWidth,GameView.screenHeight);
        roadVehicles = new RoadCarManager(gameView);
        rnd = new Random();
        System.out.println(mCanvasHeight);
    }

    //This is run before a new game (also after an old game)
    @Override
    public void setupBeginning() {
        //Initialise speeds
        car.setSpeedX(0);
        car.setSpeedY(0);

        road.setSpeedY(300);
        road.setPosX(GameView.screenWidth/2);
        road.setPosY(GameView.screenHeight/2);

//        roadVehicles.setSpeedY(450);
//        roadVehicles.setPosX(GameView.screenWidth/2);
//        roadVehicles.setPosY(-300);

        Bitmap[] bmps= new Bitmap[2];
        bmps[1]= truck;
        bmps[0]=pickup;

        float[] speeds=new float[2];
        speeds[0] = 400;
        speeds[1] = 350;

//        roadVehicles.SetAlternatives(bmps,speeds);
        //Place the Car in the middle of the screen.
        //carImage.Width() and carImage.getHeigh() gives us the height and width of the image of the Car
        car.setPosX(GameView.screenWidth/2);
        car.setPosY(GameView.screenHeight/2);
    }

    @Override
    protected void doDraw(Canvas canvas) {
        //If there isn't a canvas to draw on do nothing
        //It is ok not understanding what is happening here
        if(canvas == null) return;
        canvas.drawColor(Color.GREEN, PorterDuff.Mode.CLEAR);
        if (frameCount%100==0){
            rotationChange=rnd.nextInt(3)-1;

        }
        rotation+=rotationChange;
//        if(rotated) {
            canvas.save();
            canvas.rotate(rotation,canvas.getWidth()/2,canvas.getHeight()/2);
//        }
        super.doDraw(canvas);


        //draw the image of the Car using the X and Y of the Car
        //drawBitmap uses top left corner as reference, we use middle of picture
        //null means that we will use the image without any extra features (called Paint)
        road.draw(canvas);
        car.draw(canvas);
        roadVehicles.draw(canvas);
        if (rotated){
            canvas.restore();
        }
    }

    //This is run whenever the phone is touched by the user

	@Override
	protected void actionOnTouch(float x, float y) {
		//Increase/decrease the speed of the Car making the Car move towards the touch
        car.setSpeedX(x-car.getPosX());
        car.setSpeedX(car.getSpeedX()*1.5f);
//        if(x>GameView.screenWidth/2) {
//            car.setPosX(car.getPosX()+50);
//        }else {
//            car.setPosX(car.getPosX()-50);
//        }
	}

    @Override
    protected void actionOnTouchLift() {
        car.setSpeedX(0);
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
        System.out.println(car.getPosX()+" : ");
        car.update(secondsElapsed);
        road.update(secondsElapsed);
        roadVehicles.update(secondsElapsed);
        frameCount++;
        if(frameCount%100==0) {
            score++;
            ChangeScore(score);
        }
        CheckCollisions();
    }

    void CheckCollisions(){
        if (roadVehicles.inCollision(car)){
            System.out.println("collided");
            car.setPosX(100);
            mainActivity.onGameOver();
//            Intent myIntent = new Intent(activity,Game_over.class);
//            activity.this.startActivity(myIntent);
        }
    }

    private Handler mHandler = new Handler();
    public void ChangeScore(int score) {
        final String str = score+"";
        if (score%10==0){
            super.changeBackGround();
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
