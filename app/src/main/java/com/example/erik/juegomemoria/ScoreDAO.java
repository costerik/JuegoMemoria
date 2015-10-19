package com.example.erik.juegomemoria;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Ing. Erik Ahumada on 18/10/2015.
 */
public class ScoreDAO {
    private static final String TAG="AndroidDatabase";
    private SQLiteDatabase mDatabase;
    private ScoreGameDbHelper mDbHelper;

    public ScoreDAO(Context context){
        mDbHelper=new ScoreGameDbHelper(context);
        try {
            open();
        } catch (SQLException e) {
            Log.e(TAG, "SQLException on opening database" + e.getMessage());
            e.printStackTrace();
        }
    }

    public void open() throws SQLException {
        mDatabase=mDbHelper.getWritableDatabase();
    }

    public void close(){
        mDbHelper.close();
    }

    public long addEntry(String name, String lastName,String puntuation,String level){
        long index;
        ContentValues data=new ContentValues();
        data.put(JuegoDB.ScoreEntry.COLUMN_NAME_FIRST_NAME,name);
        data.put(JuegoDB.ScoreEntry.COLUMN_NAME_LAST_NAME,lastName);
        data.put(JuegoDB.ScoreEntry.COLUMN_NAME_PUNTUATION,puntuation);
        data.put(JuegoDB.ScoreEntry.COLUMN_NAME_LEVEL,level);

        index=mDatabase.insert(JuegoDB.ScoreEntry.TABLE_NAME,null,data);

        Log.d(TAG, "add data entry returned index " + index);
        return index;
    }

    public ArrayList<String> getData(){
        //mDatabase=mDbHelper.getReadableDatabase();
        ArrayList<String> list=new ArrayList();
        String query="SELECT * FROM "+JuegoDB.ScoreEntry.TABLE_NAME;
        Cursor cursor=mDatabase.rawQuery(query,null);
        if(cursor.moveToFirst()){
            do{
                String data=cursor.getString(1)+" "+cursor.getString(2)+" Score: "+ cursor.getString(3)+" Level: "+cursor.getString(4);
                list.add(data);
            }while(cursor.moveToNext());
        }
        cursor.close();
        return list;
    }

    public boolean deleteTitle(String id)
    {
        return mDatabase.delete(JuegoDB.ScoreEntry.TABLE_NAME, JuegoDB.ScoreEntry._ID + "=" + id, null) > 0;
    }

    public boolean updateElement(String id,String name,String lastName,String score,String level){
        ContentValues cv=new ContentValues();
        cv.put(JuegoDB.ScoreEntry.COLUMN_NAME_FIRST_NAME, name);
        cv.put(JuegoDB.ScoreEntry.COLUMN_NAME_LAST_NAME, lastName);
        cv.put(JuegoDB.ScoreEntry.COLUMN_NAME_PUNTUATION,score);
        cv.put(JuegoDB.ScoreEntry.COLUMN_NAME_LEVEL,level);
        return mDatabase.update(JuegoDB.ScoreEntry.TABLE_NAME, cv, JuegoDB.ScoreEntry._ID + "=" + id, null)>0;
    }
}
