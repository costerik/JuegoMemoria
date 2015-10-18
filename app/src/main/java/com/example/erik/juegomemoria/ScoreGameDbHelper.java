package com.example.erik.juegomemoria;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Ing. Erik Ahumada on 18/10/2015.
 */
public class ScoreGameDbHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION=1;
    public static final String DATA_BASE_NAME="ScoreGame.db";
    private static final String TEXT_TYPE=" TEXT";
    public static final String COMMA_SEP=",";
    private static final String SQL_CREATE_ENTRIES=
            "CREATE TABLE " + JuegoDB.ScoreEntry.TABLE_NAME+" ( "+
                    JuegoDB.ScoreEntry._ID + " INTEGER PRIMARY KEY, " +
                    JuegoDB.ScoreEntry.COLUMN_NAME_FIRST_NAME+TEXT_TYPE+COMMA_SEP+
                    JuegoDB.ScoreEntry.COLUMN_NAME_LAST_NAME+TEXT_TYPE+COMMA_SEP+
                    JuegoDB.ScoreEntry.COLUMN_NAME_PUNTUATION+TEXT_TYPE+")";

    private static final String SQL_DELETE_ENTRIES=
            "DROP TABLE IF EXISTS "+ JuegoDB.ScoreEntry.TABLE_NAME;

    public ScoreGameDbHelper(Context context){
        super(context,DATA_BASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }
}
