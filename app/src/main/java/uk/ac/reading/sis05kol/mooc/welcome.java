package uk.ac.reading.sis05kol.mooc;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class welcome extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
    }

    public void onStartBtnClick(View v) {
        Intent myIntent = new Intent(welcome.this, MainActivity.class);
        welcome.this.startActivity(myIntent);

    }

}
