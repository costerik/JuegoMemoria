package com.example.erik.juegomemoria;

import android.widget.Button;

import java.util.Random;

/**
 * Created by erik on 16/08/15.
 */
public class TableGame {
    private Button buttons[];
    private Button btnCheck[];
    private int check[];
    private int pairs [];

    public TableGame(int n){
        switch(n){
            case 1:
                buttons= new Button[4];
                pairs= new int[4];
                break;
            case 2:
                buttons= new Button[16];
                pairs= new int[16];
                break;
            default:
                buttons= new Button[32];
                pairs= new int[32];
                break;
        }
        check= new int [2];
        btnCheck=new Button[2];
    }

    public Button [] tiedBtns(){
        return this.buttons;
    }

    private void fillPairs(){
        int i=0;
        int limit=buttons.length/2;
        while(i<this.pairs.length){
            //pairs[0]=
        }
    }
}
