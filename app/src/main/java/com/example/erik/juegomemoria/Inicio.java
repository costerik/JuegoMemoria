package com.example.erik.juegomemoria;

import android.app.DialogFragment;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.parse.Parse;

public class Inicio extends AppCompatActivity implements View.OnClickListener,DataDialogFragment.NoticeDialogListener {
    private TextView txtView;
    private Button btn,btn2,btn3;
    private String name,lastName;
    static String ID_NAME="Erik Ahumada";

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


        //personal
        //Parse.initialize(this, "zkr1IUMGij72ywEhDqz3VcDNWqYSlyh2Vi3hiPWj", "dgEpX9zfCspuWx7g2BhLCElYY4gdmrfQrXC6PZLe");
        showNoticeDialog();
    }

    @Override
    public void onClick(View v) {
        Intent in;
        switch(v.getId()){
            case R.id.btnEasy:
                in=new Intent(this,EasyLevel.class);
                in.putExtra("NAME",name);
                in.putExtra("LAST_NAME",lastName);
                startActivity(in);
                break;
            case R.id.btnMedium:
                in=new Intent(this,MainActivity.class);
                in.putExtra("NAME",name);
                in.putExtra("LAST_NAME",lastName);
                startActivity(in);
                break;
            default:
                in=new Intent(this,HardLevel.class);
                in.putExtra("NAME",name);
                in.putExtra("LAST_NAME",lastName);
                startActivity(in);
                break;
        }
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

    public void showNoticeDialog() {
        // Create an instance of the dialog fragment and show it
        DialogFragment dialog = new DataDialogFragment();
        dialog.show(getFragmentManager(), "DataDialogFragment");
    }
}
