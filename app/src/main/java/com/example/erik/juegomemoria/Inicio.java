package com.example.erik.juegomemoria;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Inicio extends AppCompatActivity implements View.OnClickListener {
    private TextView txtView;
    private Button btn,btn2,btn3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);
        txtView=(TextView)findViewById(R.id.textView1);
        btn=(Button)findViewById(R.id.btnEasy);
        btn2=(Button)findViewById(R.id.btnMedium);
        btn3=(Button)findViewById(R.id.btnHard);
        btn.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_inicio, menu);
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
    public void onClick(View v) {
        Intent in;
        switch(v.getId()){
            case R.id.btnEasy:
                in=new Intent(this,EasyLevel.class);
                startActivity(in);
                break;
            case R.id.btnMedium:
                in=new Intent(this,MainActivity.class);
                startActivity(in);
                break;
            default:
                in=new Intent(this,HardLevel.class);
                startActivity(in);
                break;
        }
    }
}
