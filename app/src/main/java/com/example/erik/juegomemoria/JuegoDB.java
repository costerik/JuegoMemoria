package com.example.erik.juegomemoria;

import android.provider.BaseColumns;

/**
 * Created by Ing. Erik Ahumada on 18/10/2015.
 */
public class JuegoDB {
    public JuegoDB(){}

    public static abstract class ScoreEntry implements BaseColumns{
        public static final String TABLE_NAME="Score";
        public static final String COLUMN_NAME_FIRST_NAME="firstName";
        public static final String COLUMN_NAME_LAST_NAME="lastName";
        public static final String COLUMN_NAME_PUNTUATION="puntuation";
    }
}
