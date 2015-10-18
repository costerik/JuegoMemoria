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

    public long addEntry(String id, String name, String lastName){
        long index;
        ContentValues data=new ContentValues();
        data.put(JuegoDB.ScoreEntry.COLUMN_NAME_FIRST_NAME,name);
        data.put(JuegoDB.ScoreEntry.COLUMN_NAME_LAST_NAME,lastName);

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
                String data=" id :"+cursor.getString(1)+ " Name: "+cursor.getString(2)+" LastName: "+cursor.getString(3);
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

    public boolean updateElement(String id,String name,String lastName){
        ContentValues cv=new ContentValues();
        cv.put(JuegoDB.ScoreEntry.COLUMN_NAME_FIRST_NAME, name);
        cv.put(JuegoDB.ScoreEntry.COLUMN_NAME_LAST_NAME, lastName);
        return mDatabase.update(JuegoDB.ScoreEntry.TABLE_NAME, cv, JuegoDB.ScoreEntry._ID + "=" + id, null)>0;
    }
}
