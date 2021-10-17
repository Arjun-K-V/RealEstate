package com.domain.realestate;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    public static final String KEY_USERNAME = "username";
    public static final String KEY_EMAIL = "email";
    public static final String KEY_PASSWORD = "password";

    private static final String DATABASE_NAME = "LOGIN";
    private static final String TABLE_NAME = "userinfo";
    private static final int DATABASE_VERSION = 1;

    //Create Table
    private static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "(username text,email text,password text)";
    //Drop Table
    private static final String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;



    public DBHelper(@Nullable Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL(DROP_TABLE);
        onCreate(sqLiteDatabase);
    }
    public Boolean insertData(String username,String email,String password){
        SQLiteDatabase myDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_USERNAME,username);
        contentValues.put(KEY_EMAIL,email);
        contentValues.put(KEY_PASSWORD,password);
        long result = myDB.insert(TABLE_NAME,null,contentValues);

        if(result == -1){
            return false;
        }else{
            return true;
        }

    }
    public Boolean checkusername(String username){
        SQLiteDatabase myDB = this.getWritableDatabase();
        Cursor cursor = myDB.rawQuery("SELECT * FROM "+ TABLE_NAME,null);
        if(cursor.getCount()>0){
            return true;
        }else{
            return false;
        }
    }
    public Boolean checkUsernamePassword(String username,String password){
        SQLiteDatabase myDB = this.getWritableDatabase();
        Cursor cursor = myDB.rawQuery("SELECT username,password FROM "+TABLE_NAME+" WHERE username = ? AND password = ?",new String[]{username,password});
        if(cursor.getCount()>0){
            return true;
        }else{
            return false;
        }
    }
}
