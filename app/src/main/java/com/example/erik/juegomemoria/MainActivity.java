package com.example.erik.juegomemoria;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btn [] = new Button[16];
    private int pairs []= {1,4,6,5,3,8,1,5,7,2,6,7,3,8,4,2};
    private int check [] = new int[2];
    private Button checkBtn [] =new Button[2];
    private int i=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        for(int i=0;i<16;i++)
            btn[i]=(Button)findViewById(R.id.button+i);

        for(int i=0;i<16;i++)
            btn[i].setOnClickListener(this);
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
    public void onClick(View view) {
        if(i==2) {
            if (check[0] == check[1]) {
                checkBtn[0].setVisibility(View.INVISIBLE);
                checkBtn[1].setVisibility(View.INVISIBLE);
            } else {
                checkBtn[0].setText("");
                checkBtn[1].setText("");
            }
            i=0;
        }

        switch(view.getId()){
            case R.id.button:
                btn[0].setText(String.valueOf(pairs[0]));
                Toast.makeText(this, " b1: "+btn[0].getId()+"; view: "+view.getId(),Toast.LENGTH_SHORT).show();
                check[i]=pairs[0];
                checkBtn[i]=btn[0];
                break;
            case R.id.button2:
                btn[1].setText(String.valueOf(pairs[1]));
                Toast.makeText(this, " b2: "+btn[1].getId()+"; view: "+view.getId(),Toast.LENGTH_SHORT).show();
                check[i]=pairs[1];
                checkBtn[i]=btn[1];
                break;
            case R.id.button3:
                btn[2].setText(String.valueOf(pairs[2]));
                Toast.makeText(this, " b3: "+btn[2].getId()+"; view: "+view.getId(),Toast.LENGTH_SHORT).show();
                check[i]=pairs[2];
                checkBtn[i]=btn[2];
                break;
            case R.id.button4:
                btn[3].setText(String.valueOf(pairs[3]));
                Toast.makeText(this, " b4: "+btn[3].getId()+"; view: "+view.getId(),Toast.LENGTH_SHORT).show();
                check[i]=pairs[3];
                checkBtn[i]=btn[3];
                break;
            case R.id.button5:
                btn[4].setText(String.valueOf(pairs[4]));
                Toast.makeText(this, " b5: "+btn[4].getId()+"; view: "+view.getId(),Toast.LENGTH_SHORT).show();
                check[i]=pairs[4];
                checkBtn[i]=btn[4];
                break;
            case R.id.button6:
                btn[5].setText(String.valueOf(pairs[5]));
                Toast.makeText(this, " b6: "+btn[5].getId()+"; view: "+view.getId(),Toast.LENGTH_SHORT).show();
                check[i]=pairs[5];
                checkBtn[i]=btn[5];
                break;
            case R.id.button7:
                btn[6].setText(String.valueOf(pairs[6]));
                Toast.makeText(this, " b7: "+btn[6].getId()+"; view: "+view.getId(),Toast.LENGTH_SHORT).show();
                check[i]=pairs[6];
                checkBtn[i]=btn[6];
                break;
            case R.id.button8:
                btn[7].setText(String.valueOf(pairs[7]));
                Toast.makeText(this, " b8: "+btn[7].getId()+"; view: "+view.getId(),Toast.LENGTH_SHORT).show();
                check[i]=pairs[7];
                checkBtn[i]=btn[7];
                break;
            case R.id.button9:
                btn[8].setText(String.valueOf(pairs[8]));
                Toast.makeText(this, " b9: "+btn[8].getId()+"; view: "+view.getId(),Toast.LENGTH_SHORT).show();
                check[i]=pairs[8];
                checkBtn[i]=btn[8];
                break;
            case R.id.button10:
                btn[9].setText(String.valueOf(pairs[9]));
                Toast.makeText(this, " b10: "+btn[9].getId()+"; view: "+view.getId(),Toast.LENGTH_SHORT).show();
                check[i]=pairs[9];
                checkBtn[i]=btn[9];
                break;
            case R.id.button11:
                btn[10].setText(String.valueOf(pairs[10]));
                Toast.makeText(this, " b11: "+btn[10].getId()+"; view: "+view.getId(),Toast.LENGTH_SHORT).show();
                check[i]=pairs[10];
                checkBtn[i]=btn[10];
                break;
            case R.id.button12:
                btn[11].setText(String.valueOf(pairs[11]));
                Toast.makeText(this, " b12: "+btn[11].getId()+"; view: "+view.getId(),Toast.LENGTH_SHORT).show();
                check[i]=pairs[11];
                checkBtn[i]=btn[11];
                break;
            case R.id.button13:
                btn[12].setText(String.valueOf(pairs[12]));
                Toast.makeText(this, " b13: "+btn[12].getId()+"; view: "+view.getId(),Toast.LENGTH_SHORT).show();
                check[i]=pairs[12];
                checkBtn[i]=btn[12];
                break;
            case R.id.button14:
                btn[13].setText(String.valueOf(pairs[13]));
                Toast.makeText(this, " b14: "+btn[13].getId()+"; view: "+view.getId(),Toast.LENGTH_SHORT).show();
                check[i]=pairs[13];
                checkBtn[i]=btn[13];
                break;
            case R.id.button15:
                btn[14].setText(String.valueOf(pairs[14]));
                Toast.makeText(this, " b15: "+btn[14].getId()+"; view: "+view.getId(),Toast.LENGTH_SHORT).show();
                check[i]=pairs[14];
                checkBtn[i]=btn[14];
                break;
            default:
                btn[15].setText(String.valueOf(pairs[15]));
                Toast.makeText(this, " b16: "+btn[15].getId()+"; view: "+view.getId(),Toast.LENGTH_SHORT).show();
                check[i]=pairs[15];
                checkBtn[i]=btn[15];
                break;
        }
        i++;
        //if(view.getId()==b1.getId()) Toast.makeText(this, " b1: "+b1.getId()+"; view: "+view.getId(),Toast.LENGTH_SHORT).show();
    }
}
