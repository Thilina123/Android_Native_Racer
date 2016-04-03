package uk.ac.reading.sis05kol.mooc;

import android.graphics.Bitmap;

/**
 * Created by Thilina on 2016-04-03.
 */
public class Bullet extends Ship {

    private Ship playerShip;
    public Bullet(Bitmap image, float width, float canvasWidth, float canvasHeight,Ship playerShip) {
        super(image, width, canvasWidth, canvasHeight);
        this.playerShip = playerShip;
    }

    public void setPlayerShip(PlayerShip playerShip) {
        this.playerShip = playerShip;
    }

    @Override
    public void update(float secondsElapsed) {
        super.update(secondsElapsed);
        if (getPosY()<0){
            setPosY(playerShip.getPosY());
            setPosX(playerShip.getPosX());
        }
    }
}
