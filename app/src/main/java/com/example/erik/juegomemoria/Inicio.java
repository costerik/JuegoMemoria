package com.example.erik.juegomemoria;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.parse.Parse;

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

        Parse.enableLocalDatastore(this);
        //Share
        Parse.initialize(this, "XYbFuSLcnz3Ro5eVn2QJzvdLvmgquoFDArBfUb4N", "4S9QKHzcznA5LtoVVq11dIFE0GAHzacqYnvMCuHi");
        //personal
        //Parse.initialize(this, "zkr1IUMGij72ywEhDqz3VcDNWqYSlyh2Vi3hiPWj", "dgEpX9zfCspuWx7g2BhLCElYY4gdmrfQrXC6PZLe");
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
