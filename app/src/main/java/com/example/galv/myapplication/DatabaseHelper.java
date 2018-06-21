package com.example.galv.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME= "result.db";
    private static final String TABLE_NAME= "result_table";
    private static final String COL1= "ID";
    private static final String COL2= "FNAME";
    private static final String COL3= "LNAME";
    private static final String COL4= "STAGE";

    private static final String NUM_OF_SCORES_TO_SHOW = "10";
  // public long theSCORE;
    //public static final String CO3= "ADDRESS";

    public DatabaseHelper(Context context) {
        super(context, TABLE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
    String createTable = "CREATE TABLE " + TABLE_NAME + "(ID INTEGER KEY AUTOINCREMENT, "+
            "FNAME TEXT, LNAME TEXT, STAGE TEXT)"; //+ COL1_SCORE+"POINT)"??
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP IF TABLE EXISTS " + TABLE_NAME);
        onCreate(db);
    }
 //???
    public Cursor highestScores(){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM "+TABLE_NAME+" DESC LIMIT " + NUM_OF_SCORES_TO_SHOW; ///" ORDER BY "+COL1_SCORE +
        Cursor data = db.rawQuery(query,null);
        return data;
    }

    public boolean addData(String name, String lastName,String stage ) {   //, long score
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(COL2,name);
        contentValues.put(COL3,lastName);
        contentValues.put(COL4,stage);
       // contentValues.put(COL1_SCORE,score);

        long result=db.insert(TABLE_NAME,null,contentValues);

            if(result==-1) {//return -1 id error
                return false;
            }
                else{
                    return true;
            }

        }
        public Cursor getListContents() {
            SQLiteDatabase db = this.getWritableDatabase();
            Cursor data= db.rawQuery("SELECT * FROM " + TABLE_NAME+" ORDER BY "
                    +COL1+ " DESC", null);
            return data;
        }
    }


