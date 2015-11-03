package com.example.erik.juegomemoria;

import android.app.DialogFragment;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.Parse;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class HardLevel extends AppCompatActivity implements View.OnClickListener, SensorEventListener,DataDialogFragment.NoticeDialogListener{
    private static final String [] BUTTONS_STATES={"button_1","button_2","button_3","button_4","button_5","button_6",
            "button_7","button_8","button_9","button_10","button_11","button_12", "button_13","button_14","button_15",
            "button_16"};
    private static final String BUTTON_CHECK_TEXT="btnChk1Txt";
    private static final String BUTTON_CHECK_ID="btnID";
    private static final String RANDOM_NUMBERS="randomNumbers";
    private static final String TV_MESSAGE="tvMessage";
    private static final String VALUE_I="valueI";
    private static final String CHECK="check";
    private static final String PAIRS="pairs";
    protected static final String MATCHS="match";
    protected static final String NUM_GAME="numGame";
    private static final String INTENTOS="intentos";
    private Button btn [] = new Button[16];
    private Button btnHistory;
    private TextView tv ;
    private int pairs [];
    private int pairsII []= new int[16];
    private int check [] = new int[2];
    private Button checkBtn [] =new Button[2];
    private int i=0;
    private int intentos=0;
    private int numPairs=0;
    private SoundPool sp;
    int [] sounds=new int[4];
    int vecesJugadas;
    private MediaPlayer mp;
    private SharedPreferences sharePref;
    private SensorManager sensorManager;
    private Sensor sensor;
    private ArrayList<ObjectAxis> list;
    private static ScoreDAO score;
    private String name,lastName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hard_level);
        score=new ScoreDAO(getApplicationContext());
        //Here is all related with Sensor
        list =new ArrayList<ObjectAxis>();

        Bundle extras=getIntent().getExtras();
        name=extras.getString("NAME");
        lastName=extras.getString("LAST_NAME");

        sensorManager=(SensorManager)getSystemService(Context.SENSOR_SERVICE);
        if(sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)!=null){
            sensor=sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        }

        //

        tv=(TextView)findViewById(R.id.textViewHard);
        for(int i=0;i<16;i++) {
            Log.i("VALOR BUTTONS",""+R.id.buttonhard+i);
            btn[i] = (Button) findViewById(R.id.buttonhard + i);
            btn[i].setBackgroundColor(Color.rgb(randInt(0,240), randInt(0,240), randInt(0,240)));
        }

        for(int i=0;i<16;i++)
            btn[i].setOnClickListener(this);

        btnHistory=(Button)findViewById(R.id.button37hard);
        btnHistory.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HardLevel.this,History.class);
                intent.putExtra("LEVEL", "Dificil");
                startActivity(intent);
            }
        });
        sharePref= PreferenceManager.getDefaultSharedPreferences(this);
        if(sharePref.getInt(NUM_GAME,0)!= 0){
            vecesJugadas=sharePref.getInt(NUM_GAME,0);
            vecesJugadas++;
        }else{
            vecesJugadas=1;
        }
        SharedPreferences.Editor e= sharePref.edit();
        e.putInt(NUM_GAME,vecesJugadas);
        e.commit();

        pairs=pairsResult(pairsII);
        //mp=MediaPlayer.create(this, R.raw.track_03);
        //mp.setLooping(true);

        if(Build.VERSION.SDK_INT >=Build.VERSION_CODES.LOLLIPOP){
            AudioAttributes aa = new AudioAttributes.Builder()
                    .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                    .setUsage(AudioAttributes.USAGE_GAME)
                    .build();


            sp = new SoundPool.Builder().setMaxStreams(10)
                    .setAudioAttributes(aa)
                    .build();

            sounds[0] = sp.load(this, R.raw.track_01, 1);
            sounds[1] = sp.load(this, R.raw.track_02, 1);
            sounds[2]=sp.load(this,R.raw.track_03,1);
        }else{
            sp=new SoundPool(10, AudioManager.STREAM_MUSIC,1);
            sounds[0]=sp.load(this,R.raw.track_01,1);
            sounds[1]=sp.load(this,R.raw.track_02,1);
            sounds[2]=sp.load(this,R.raw.track_03,1);
        }
        //sp.play(sounds[2],1,1,1,-1,1);
        if(savedInstanceState != null){
            i=savedInstanceState.getInt(VALUE_I);
            check[0]=savedInstanceState.getInt(CHECK);
            numPairs=savedInstanceState.getInt(PAIRS);
            intentos=savedInstanceState.getInt(INTENTOS);
            for(int i=0;i<btn.length;i++) {
                if(savedInstanceState.getInt(BUTTONS_STATES[i])==View.INVISIBLE){
                    btn[i].setVisibility(View.INVISIBLE);
                }else{
                    btn[i].setVisibility(View.VISIBLE);
                }
                if(savedInstanceState.getInt(BUTTON_CHECK_ID)==btn[i].getId()){
                    checkBtn[0]=btn[i];
                    checkBtn[0].setText(savedInstanceState.getString(BUTTON_CHECK_TEXT));
                }
            }
            pairs=savedInstanceState.getIntArray(RANDOM_NUMBERS);
            tv.setText(savedInstanceState.getString(TV_MESSAGE));
        }
    }

    @Override
    public void onPause(){
        super.onPause();
        sensorManager.unregisterListener(this);
    }

    @Override
    public void onResume(){
        mp=MediaPlayer.create(this, R.raw.track_03);
        mp.setLooping(true);
        mp.start();
        sensorManager.registerListener(this, sensor, sensorManager.SENSOR_DELAY_GAME);
        super.onResume();
    }

    @Override
    public void onStop(){
        mp.stop();
        mp.release();
        super.onStop();
    }

    @Override
    public void onRestart(){
        super.onRestart();
    }

    public void onSaveInstanceState(Bundle saveInstanceState){
        for(int i=0;i<btn.length;i++){
            saveInstanceState.putInt(BUTTONS_STATES[i],btn[i].getVisibility());
        }
        if(checkBtn[0]!=null){
            saveInstanceState.putString(BUTTON_CHECK_TEXT, checkBtn[0].getText().toString());
            saveInstanceState.putInt(BUTTON_CHECK_ID, checkBtn[0].getId());
        }
        saveInstanceState.putInt(INTENTOS,intentos);
        saveInstanceState.putIntArray(RANDOM_NUMBERS, pairs);
        saveInstanceState.putString(TV_MESSAGE,tv.getText().toString());
        saveInstanceState.putInt(VALUE_I,i);
        saveInstanceState.putInt(CHECK,check[0]);
        saveInstanceState.putInt(PAIRS,numPairs);
        super.onSaveInstanceState(saveInstanceState);
    }

    public static int randInt(int min, int max) {
        // Usually this can be a field rather than a method variable
        Random rand = new Random();
        // nextInt is normally exclusive of the top value,
        // so add 1 to make it inclusive
        int randomNum = rand.nextInt((max - min) + 1) + min;
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return randomNum;
    }

    public int [] pairsResult(int [] tableSize){
        int i=0;
        int p=1;
        int t=0;
        int k=0;
        int [] OPair=new int[tableSize.length];
        int [] pair=new int[tableSize.length];
        int [] auxTemp=new int[tableSize.length];
        while(i<tableSize.length){
            int j=0;
            while(j<2){
                int v=randInt(0,tableSize.length-1);
                if(pair[v]!=0){
                    auxTemp[k]=p;
                    k++;
                }
                while(pair[v]==0 && t!=v || v==0 && t==0 && pair[v]==0){
                    pair[v]=p;
                    t=v;
                    //v=v=randInt(0,(tableSize.length/2)-1);
                }
                j++;
            }
            if(i<(tableSize.length/2)-1){
                p++;
            }else{
                break;
            }
            i++;
        }
        int indice2=0;
        for(int indice=0;indice<auxTemp.length;indice++){
            if(pair[indice]!=0){
                OPair[indice]=pair[indice];
            }else if(pair[indice]==0){
                if(auxTemp[indice2]!=0){
                    OPair[indice]=auxTemp[indice2];
                    indice2++;
                }
            }
        }
        return OPair;
    }

    public Boolean gameOver(Button [] buttons){
        for(int i=0; i<buttons.length;i++){
            if(buttons[i].getVisibility()==View.VISIBLE) {
                return false;
            }
        }
        score.addEntry(name,lastName,""+intentos,"Dificil");
        new SendData().execute();
        return true;
    }

    public Boolean checkShake(ArrayList<ObjectAxis> l){
        if(l.size()>10){
            for(int i=0;i<l.size()-1;i++){
                if(l.get(i+1).getX()-l.get(i).getX()>10F || l.get(i+1).getY()-l.get(i).getY()>10F || l.get(i+1).getZ()-l.get(i).getZ()>10F){
                    l.clear();
                    return true;
                }
            }
            l.clear();
        }
        return false;
    }

    @Override
    public void onClick(View v) {
        sp.play(sounds[0],1,1,1,0,1);
        switch (v.getId()) {
            case R.id.buttonhard:
                btn[0].setText(String.valueOf(pairs[0]));
                check[i] = pairs[0];
                checkBtn[i] = btn[0];
                break;
            case R.id.button2hard:
                btn[1].setText(String.valueOf(pairs[1]));
                check[i] = pairs[1];
                checkBtn[i] = btn[1];
                break;
            case R.id.button3hard:
                btn[2].setText(String.valueOf(pairs[2]));
                check[i] = pairs[2];
                checkBtn[i] = btn[2];
                break;
            case R.id.button4hard:
                btn[3].setText(String.valueOf(pairs[3]));
                check[i] = pairs[3];
                checkBtn[i] = btn[3];
                break;
            case R.id.button5hard:
                btn[4].setText(String.valueOf(pairs[4]));
                check[i] = pairs[4];
                checkBtn[i] = btn[4];
                break;
            case R.id.button6hard:
                btn[5].setText(String.valueOf(pairs[5]));
                check[i] = pairs[5];
                checkBtn[i] = btn[5];
                break;
            case R.id.button7hard:
                btn[6].setText(String.valueOf(pairs[6]));
                check[i] = pairs[6];
                checkBtn[i] = btn[6];
                break;
            case R.id.button8hard:
                btn[7].setText(String.valueOf(pairs[7]));
                check[i] = pairs[7];
                checkBtn[i] = btn[7];
                break;
            case R.id.button9hard:
                btn[8].setText(String.valueOf(pairs[8]));
                check[i] = pairs[8];
                checkBtn[i] = btn[8];
                break;
            case R.id.button10hard:
                btn[9].setText(String.valueOf(pairs[9]));
                check[i] = pairs[9];
                checkBtn[i] = btn[9];
                break;
            case R.id.button11hard:
                btn[10].setText(String.valueOf(pairs[10]));
                check[i] = pairs[10];
                checkBtn[i] = btn[10];
                break;
            case R.id.button12hard:
                btn[11].setText(String.valueOf(pairs[11]));
                check[i] = pairs[11];
                checkBtn[i] = btn[11];
                break;
            case R.id.button13hard:
                btn[12].setText(String.valueOf(pairs[12]));
                check[i] = pairs[12];
                checkBtn[i] = btn[12];
                break;
            case R.id.button14hard:
                btn[13].setText(String.valueOf(pairs[13]));
                check[i] = pairs[13];
                checkBtn[i] = btn[13];
                break;
            case R.id.button15hard:
                btn[14].setText(String.valueOf(pairs[14]));
                check[i] = pairs[14];
                checkBtn[i] = btn[14];
                break;
            default:
                btn[15].setText(String.valueOf(pairs[15]));
                check[i] = pairs[15];
                checkBtn[i] = btn[15];
                break;
        }
        i++;
        if(i==2) {
            if (check[0] == check[1] && checkBtn[0].getId() != checkBtn[1].getId()) {
                sp.play(sounds[1],1,1,1,0,1);
                numPairs++;
                checkBtn[0].setVisibility(View.INVISIBLE);
                checkBtn[1].setVisibility(View.INVISIBLE);
                tv.setTextSize(40);
                tv.setText("Pairs: "+numPairs);
            } //else {
            Handler h= new Handler();
            h.postDelayed(new Runnable() {
                @Override
                public void run() {
                    checkBtn[0].setText("");
                    checkBtn[1].setText("");
                }
            }, 100);
            //}
            i=0;
            intentos++;
            tv.setTextSize(40);
            tv.setText("Pairs: "+numPairs+" Tries: "+intentos);
        }
        if(gameOver(btn)){
            tv.setText("YOU WIN!!!");
        }
        SharedPreferences.Editor editor= sharePref.edit();
        editor.putInt(MATCHS, numPairs);
        editor.commit();
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        list.add(new ObjectAxis(event.values[0], event.values[1], event.values[2]));
        if(checkShake(list)){
            Snackbar.make(findViewById(android.R.id.content),"Shake baby",Snackbar.LENGTH_SHORT).show();
            Intent in=new Intent(HardLevel.this,HardLevel.class);
            startActivity(in);
            finish();
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    public void showNoticeDialog() {
        // Create an instance of the dialog fragment and show it
        DialogFragment dialog = new DataDialogFragment();
        dialog.show(getFragmentManager(), "DataDialogFragment");
    }

    @Override
    public void onDialogPositiveClick(DialogFragment dialog) {
        name =((EditText)dialog.getDialog().findViewById(R.id.edtText)).getText().toString();
        lastName =((EditText)dialog.getDialog().findViewById(R.id.edtText2)).getText().toString();
        Snackbar.make(findViewById(android.R.id.content), name + " " + lastName, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void onDialogNegativeClick(DialogFragment dialog) {
        finish();
    }

    /*
       Parse Classes
    */
    private class SendData extends AsyncTask<Void,Void,Void> {
        @Override
        protected Void doInBackground(Void... params) {
            /*ParseObject testObject=new ParseObject("ScoreTable");
            testObject.put("FullName",name+" "+lastName);
            testObject.put("Score",intentos);
            testObject.put("Level","Hard");*/

            ParseObject testObject=new ParseObject("memoria");
            testObject.put("idname",Inicio.ID_NAME);
            testObject.put("name",name+" "+lastName);
            testObject.put("puntos",""+intentos);
            testObject.put("nivel","Dificil");


            testObject.saveInBackground();
            return null;
        }
    }
}
