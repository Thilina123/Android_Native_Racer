package uk.ac.reading.sis05kol.mooc;

//Other parts of the android libraries that we use
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.widget.TextView;

public class TheGame extends GameThread{

    //Will store the image of a Car
    private Bitmap carImage;
    private Bitmap roadImage;
    private Bitmap yellow_car_image;
    private Bitmap truck;
    private Bitmap pickup;


    private Vehicle car;
    private Road road;
    private RoadCar roadVehicle;
    private MainActivity mainActivity;
    private TextView scoreView;
    private int score;

    //This is run before anything else, so we can prepare things here
    public TheGame(GameView gameView, MainActivity mainActivity, TextView scoreView) {
        //House keeping
        super(gameView);
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
        roadVehicle = new RoadCar(yellow_car_image,GameView.screenWidth/7,GameView.screenWidth,GameView.screenHeight);
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

        roadVehicle.setSpeedY(450);
        roadVehicle.setPosX(GameView.screenWidth/2);
        roadVehicle.setPosY(-300);

        Bitmap[] bmps= new Bitmap[2];
        bmps[1]= truck;
        bmps[0]=pickup;

        float[] speeds=new float[2];
        speeds[0] = 400;
        speeds[1] = 350;

        roadVehicle.SetAlternatives(bmps,speeds);
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

        super.doDraw(canvas);

        //draw the image of the Car using the X and Y of the Car
        //drawBitmap uses top left corner as reference, we use middle of picture
        //null means that we will use the image without any extra features (called Paint)
        road.draw(canvas);
        car.draw(canvas);
        roadVehicle.draw(canvas);
    }

    //This is run whenever the phone is touched by the user

	@Override
	protected void actionOnTouch(float x, float y) {
		//Increase/decrease the speed of the Car making the Car move towards the touch
//        car.setSpeedX(x-car.getPosX());
//        car.setSpeedY(y-car.getPosY());
        if(x>GameView.screenWidth/2) {
            car.setPosX(car.getPosX()+50);
        }else {
            car.setPosX(car.getPosX()-50);
        }
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
        car.update(secondsElapsed);
        road.update(secondsElapsed);
        roadVehicle.update(secondsElapsed);
        score++;
//        scoreView.setText(score);
        CheckCollisions();
    }

    void CheckCollisions(){
        if (roadVehicle.inCollision(car)){
            System.out.println("collided");
            car.setPosX(100);
            mainActivity.onGameOver();
//            Intent myIntent = new Intent(activity,Game_over.class);
//            activity.this.startActivity(myIntent);
        }
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
