package com.example.proyectofinal.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String TAG = "DatabaseHelper";

    public DatabaseHelper(@Nullable Context context){
        super(context, "name.db", null, 21);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table if not exists User (id integer primary key autoincrement, username text, password text)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists User");
        onCreate(db);
    }

    public long registerUser(UserData data){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", data.getUsername());
        contentValues.put("password", data.getPassword());
        long user = sqLiteDatabase.insert("User", null, contentValues);
        if(user != 1){
            Log.e(TAG, "registerUser: Usuario registrado correctamente");
        } else {
            Log.e(TAG, "registerUser: error al registrar usuario...");
        }
        sqLiteDatabase.close();
        return user;
    }

    public void loginUser(UserData data){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select * from User", null, null);
        while (cursor.moveToNext()){
            String username = cursor.getString(1);
            String password = cursor.getString(2);

            if(username.equals(data.getUsername()) && password.equals(data.getPassword())){
                Log.e(TAG, "LoginUser: Usuario loggeado exitosamente.");
            } else {
                Log.e(TAG, "LoginUser: Error en el login de Usuario.");
            }
        }
        cursor.close();
        sqLiteDatabase.close();
    }
}
