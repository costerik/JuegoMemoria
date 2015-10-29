package com.example.erik.juegomemoria;

import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

public class History extends AppCompatActivity {
    //private TextView tvNumGame;
    private TextView tvPuntaje;
    private ListView dataList;
    private ArrayList<String> list;
    //private ScoreDAO score;
    private ArrayList values;
    private List<ParseObject> ob;
    private String level;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        tvPuntaje=(TextView)findViewById(R.id.textView1);
        SharedPreferences sharedPref= PreferenceManager.getDefaultSharedPreferences(this);
        int matchs=sharedPref.getInt(MainActivity.MATCHS, 0);
        int numGame=sharedPref.getInt(MainActivity.NUM_GAME, 0);
        tvPuntaje.setText("Puntaje");
        //tvNumGame.setText("# Juego "+numGame);
        //score=new ScoreDAO(getApplicationContext());
        dataList=(ListView)findViewById(R.id.score_list);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            level = extras.getString("LEVEL");
        }
        //mostrarElems();
        new GetData().execute();
    }

    /*public void mostrarElems(){
        list=score.getData();
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1, android.R.id.text1,list);
        dataList.setAdapter(adapter);
    }*/

    private class GetData extends AsyncTask<Void,Void,Void>{

        @Override
        protected Void doInBackground(Void... params) {
            values= new ArrayList<String>();
            try{
                ParseQuery<ParseObject> query=new ParseQuery<ParseObject>("Memoria");
                ob = query.find();
                Log.e("GETDATA", "????????");
                Log.e("LEVEL",level);
                for (ParseObject dato : ob){
                    if(String.valueOf(dato.get("nivel")).compareTo(level)==0) {
                        values.add(new Data(dato.get("name"), dato.get("puntos"), dato.get("nivel")).toString());
                    }
                    /*values.add(dato.get("name"));
                    values.add(dato.get("puntos"));
                    values.add(dato.get("nivel"));*/
                }
                Log.e("GETDATA",""+values.size());
            } catch (com.parse.ParseException e) {
                Log.e("Error",e.getMessage());
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result){
            ArrayAdapter<String> adapter=new ArrayAdapter<String>(History.this,android.R.layout.simple_list_item_1,android.R.id.text1,values);
            dataList.setAdapter(adapter);
        }
    }

    public class Data{
        Object name,nivel,puntos;
        public Data(Object name,Object puntos,Object nivel){
            this.name=name;
            this.puntos=puntos;
            this.nivel=nivel;
        }

        public String toString(){
            return name+" "+puntos+" "+nivel;
        }

    }
}