package com.example.galv.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;



public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME= "result.db";
    public static final String TABLE_NAME= "result_table";
    public static final String COL1= "NAME";
    public static final String SCORE= "SCORE";
    public int theSCORE;
    //public static final String CO3= "ADDRESS";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
    String createTable = "CREATE TABLE " + TABLE_NAME + " (NAME, SCORE)";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP IF TABLE EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    @Override
    public boolean addData(String name, int score) {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(COL1,name);
        contentValues.put(SCORE,score);

    }
}
