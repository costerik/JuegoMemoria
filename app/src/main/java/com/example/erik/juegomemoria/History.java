package com.example.erik.juegomemoria;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class History extends AppCompatActivity {
    private TextView tvNumGame;
    private TextView tvPuntaje;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        tvNumGame=(TextView)findViewById(R.id.textView1);
        tvPuntaje=(TextView)findViewById(R.id.textView2);
        SharedPreferences sharedPref= PreferenceManager.getDefaultSharedPreferences(this);
        int matchs=sharedPref.getInt(MainActivity.MATCHS, 0);
        int numGame=sharedPref.getInt(MainActivity.NUM_GAME,0);
        tvPuntaje.setText("Puntaje: "+matchs);
        tvNumGame.setText("# Juego "+numGame);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_history, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
