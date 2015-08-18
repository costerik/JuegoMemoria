package com.example.erik.juegomemoria;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button btn [] = new Button[16];
    private TextView tv ;
    private int pairs [];//{1,4,6,5,3,8,1,5,7,2,6,7,3,8,4,2};
    private int pairsII []= new int[16];
    private int check [] = new int[2];
    private Button checkBtn [] =new Button[2];
    private int i=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv=(TextView)findViewById(R.id.textView);
        for(int i=0;i<16;i++)
            btn[i]=(Button)findViewById(R.id.button+i);

        for(int i=0;i<16;i++)
            btn[i].setOnClickListener(this);

        pairs=pairsResult(pairsII);
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
    public static int randInt(int min, int max) {
        // Usually this can be a field rather than a method variable
        Random rand = new Random();
        // nextInt is normally exclusive of the top value,
        // so add 1 to make it inclusive
        int randomNum = rand.nextInt((max - min) + 1) + min;
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
		/*System.out.println("------------------------------------");
		for(int in=0;in<auxTemp.length;in++){
			System.out.println("i: "+in+" => "+auxTemp[in]);
		}
		System.out.println("------------------------------------");*/
        int indice2=0;
        for(int indice=0;indice<auxTemp.length;indice++){
            if(auxTemp[i]!=0){

            }
            if(pair[indice]!=0){
                OPair[indice]=pair[indice];
            }else if(pair[indice]==0){
                if(auxTemp[indice2]!=0){
                    OPair[indice]=auxTemp[indice2];
                    indice2++;
                }
            }
        }

		/*for(int in=0;in<pair.length;in++){
			System.out.println("i: "+in+" => "+pair[in]);
		}
		System.out.println("------------------------------------");*/
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
        if(i<2) {
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
        }else{
            if(i==2) {
                if (check[0] == check[1] && checkBtn[0].getId() != checkBtn[1].getId()) {
                    checkBtn[0].setVisibility(View.INVISIBLE);
                    checkBtn[1].setVisibility(View.INVISIBLE);
                } else {
                    checkBtn[0].setText("");
                    checkBtn[1].setText("");
                }
                i=0;
            }
        }
        if(gameOver(btn)){
            tv.setText("YOU WIN!!!");
        }
    }
}
