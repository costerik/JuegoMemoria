package com.example.erik.juegomemoria;

import android.graphics.Color;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Build;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;
import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private static final String [] BUTTONS_STATES={"button_1","button_2","button_3","button_4","button_5","button_6",
                                            "button_7","button_8","button_9","button_10","button_11","button_12",
                                            "button_13","button_14","button_15","button_16"};
    private static final String BUTTON_CHECK_TEXT="btnChk1Txt";
    private static final String BUTTON_CHECK_ID="btnID";
    private static final String RANDOM_NUMBERS="randomNumbers";
    private static final String TV_MESSAGE="tvMessage";
    private static final String VALUE_I="valueI";
    private static final String CHECK="check";
    private static final String PAIRS="pairs";
    private Button btn [] = new Button[16];
    private TextView tv ;
    private int pairs [];
    private int pairsII []= new int[16];
    private int check [] = new int[2];
    private Button checkBtn [] =new Button[2];
    private int i=0;
    private int numPairs=0;
    private SoundPool sp;
    int [] sounds=new int[4];
    MediaPlayer mp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv=(TextView)findViewById(R.id.textView);
        for(int i=0;i<16;i++) {
            btn[i] = (Button) findViewById(R.id.button + i);
            btn[i].setBackgroundColor(Color.rgb(randInt(0,240), randInt(0,240), randInt(0,240)));
        }

        for(int i=0;i<16;i++)
            btn[i].setOnClickListener(this);

        pairs=pairsResult(pairsII);
        mp=MediaPlayer.create(this, R.raw.track_03);
        mp.setLooping(true);
        mp.start();
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
    public void onStop(){
        mp.stop();
        super.onStop();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

    @Override
    public void onSaveInstanceState(Bundle saveInstanceState){
        for(int i=0;i<btn.length;i++){
            saveInstanceState.putInt(BUTTONS_STATES[i],btn[i].getVisibility());
        }
        if(checkBtn[0]!=null){
            saveInstanceState.putString(BUTTON_CHECK_TEXT, checkBtn[0].getText().toString());
            saveInstanceState.putInt(BUTTON_CHECK_ID, checkBtn[0].getId());
        }
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
        return true;
    }

    @Override
    public void onClick(View view) {
        sp.play(sounds[0],1,1,1,0,1);
            switch (view.getId()) {
                case R.id.button:
                    btn[0].setText(String.valueOf(pairs[0]));
                    check[i] = pairs[0];
                    checkBtn[i] = btn[0];
                    break;
                case R.id.button2:
                    btn[1].setText(String.valueOf(pairs[1]));
                    check[i] = pairs[1];
                    checkBtn[i] = btn[1];
                    break;
                case R.id.button3:
                    btn[2].setText(String.valueOf(pairs[2]));
                    check[i] = pairs[2];
                    checkBtn[i] = btn[2];
                    break;
                case R.id.button4:
                    btn[3].setText(String.valueOf(pairs[3]));
                    check[i] = pairs[3];
                    checkBtn[i] = btn[3];
                    break;
                case R.id.button5:
                    btn[4].setText(String.valueOf(pairs[4]));
                    check[i] = pairs[4];
                    checkBtn[i] = btn[4];
                    break;
                case R.id.button6:
                    btn[5].setText(String.valueOf(pairs[5]));
                    check[i] = pairs[5];
                    checkBtn[i] = btn[5];
                    break;
                case R.id.button7:
                    btn[6].setText(String.valueOf(pairs[6]));
                    check[i] = pairs[6];
                    checkBtn[i] = btn[6];
                    break;
                case R.id.button8:
                    btn[7].setText(String.valueOf(pairs[7]));
                    check[i] = pairs[7];
                    checkBtn[i] = btn[7];
                    break;
                case R.id.button9:
                    btn[8].setText(String.valueOf(pairs[8]));
                    check[i] = pairs[8];
                    checkBtn[i] = btn[8];
                    break;
                case R.id.button10:
                    btn[9].setText(String.valueOf(pairs[9]));
                    check[i] = pairs[9];
                    checkBtn[i] = btn[9];
                    break;
                case R.id.button11:
                    btn[10].setText(String.valueOf(pairs[10]));
                    check[i] = pairs[10];
                    checkBtn[i] = btn[10];
                    break;
                case R.id.button12:
                    btn[11].setText(String.valueOf(pairs[11]));
                    check[i] = pairs[11];
                    checkBtn[i] = btn[11];
                    break;
                case R.id.button13:
                    btn[12].setText(String.valueOf(pairs[12]));
                    check[i] = pairs[12];
                    checkBtn[i] = btn[12];
                    break;
                case R.id.button14:
                    btn[13].setText(String.valueOf(pairs[13]));
                    check[i] = pairs[13];
                    checkBtn[i] = btn[13];
                    break;
                case R.id.button15:
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
            }
        if(gameOver(btn)){
            tv.setText("YOU WIN!!!");
        }
    }
}
