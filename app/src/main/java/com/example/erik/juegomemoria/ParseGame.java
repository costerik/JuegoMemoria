package com.example.erik.juegomemoria;

import android.app.Application;

import com.parse.Parse;

/**
 * Created by Ing. Erik Ahumada on 02/11/2015.
 */
public class ParseGame extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Parse.enableLocalDatastore(this);
        Parse.initialize(this, "izD9VtSH1ueVfWX0y8Eycy4nW88Caml9LKqtKaAd", "wfxY8I330MmaPPZVr86iUpFohJ6QUyuVdY3jhKwr");
    }
}
