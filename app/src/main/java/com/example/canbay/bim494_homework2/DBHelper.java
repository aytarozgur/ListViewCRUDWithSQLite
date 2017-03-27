package com.example.canbay.bim494_homework2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.Date;

/**
 * Created by canbay on 23.03.2017.
 */

public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "mydatabase";
    public static final String TABLE_NAME = "customers";
    public static final String COLUMN0 = "id";
    public static final String COLUMN1 = "namesurname";
    public static final String COLUMN2 = "birthdate";
    public static final String COLUMN3 = "credit";



    public DBHelper(Context context){
        super(context,DATABASE_NAME,null,14);

    }
    @Override
    public void onCreate(SQLiteDatabase db) {

        String createTable = "CREATE TABLE IF NOT EXISTS "+ TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                " NAMESURNAME TEXT , BIRTHDATE TEXT, CREDIT TEXT)";

        db.execSQL(createTable);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME);
        onCreate(db);
    }

    public boolean insertCustomer(String namesurname,String birthdate,String credit){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COLUMN1,namesurname);
        values.put(COLUMN2, birthdate);
        values.put(COLUMN3,credit);
        db.insert(TABLE_NAME,null,values);
        return true;
    }

    public Cursor getData(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM customers",null);
        return res;
    }

    public int getCursorCount(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM customers",null);
        return res.getCount();
    }

    public boolean updateCustomer(int id,String namesurname,String birthdate,String credit){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN1,namesurname);
        values.put(COLUMN2, birthdate);
        values.put(COLUMN3,credit);
       int i= db.update(TABLE_NAME,values,COLUMN0 + "=" + id ,null);
        return i>0;
    }
    public boolean deleteCustomer(int id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, COLUMN0 + "=" + id, null) > 0;
    }
}
