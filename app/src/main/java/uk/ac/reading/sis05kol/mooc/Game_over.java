package uk.ac.reading.sis05kol.mooc;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Game_over extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);
    }


    public void onTryAgainClick(View v) {
        Intent myIntent = new Intent(Game_over.this, MainActivity.class);
        Game_over.this.startActivity(myIntent);

    }


    public void onMainMenuClick(View v) {
        Intent myIntent = new Intent(Game_over.this, welcome.class);
        Game_over.this.startActivity(myIntent);

    }
}
