package com.example.erik.juegomemoria;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class History extends AppCompatActivity {
    private TextView tvNumGame;
    private TextView tvPuntaje;
    private ListView dataList;
    private ArrayList<String> list;
    private ScoreDAO score;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        tvNumGame=(TextView)findViewById(R.id.textView1);
        tvPuntaje=(TextView)findViewById(R.id.textView2);
        SharedPreferences sharedPref= PreferenceManager.getDefaultSharedPreferences(this);
        int matchs=sharedPref.getInt(MainActivity.MATCHS, 0);
        int numGame=sharedPref.getInt(MainActivity.NUM_GAME, 0);
        tvPuntaje.setText("Puntaje: "+matchs);
        tvNumGame.setText("# Juego "+numGame);
        score=new ScoreDAO(getApplicationContext());
        dataList=(ListView)findViewById(R.id.score_list);
        mostrarElems();
    }

    public void mostrarElems(){
        list=score.getData();
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1, android.R.id.text1,list);
        dataList.setAdapter(adapter);
    }
}