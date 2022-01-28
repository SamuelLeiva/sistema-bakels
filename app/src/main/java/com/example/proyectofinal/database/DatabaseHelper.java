package com.example.proyectofinal.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String TAG = "DatabaseHelper";
    private static final int DATABASE_VERSION = 22;

    public DatabaseHelper(@Nullable Context context){
        super(context, "name.db", null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table if not exists User (id integer primary key autoincrement, username text, password text)");
        db.execSQL("create table if not exists Product (id integer primary key autoincrement, name text, brand text, category text, stock number, price number)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists User");
        db.execSQL("drop table if exists Product");
        onCreate(db);
    }

    public boolean findUser(UserData data){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select * from User", null, null);
        while (cursor.moveToNext()){
            String username = cursor.getString(1);
            //String password = cursor.getString(2);

            if(username.equals(data.getUsername())){
                Log.e(TAG, "LoginUser: Usuario ya existe.");
                cursor.close();
                sqLiteDatabase.close();
                return true;
            } /*else {
                Log.e(TAG, "LoginUser: Error en el login de Usuario.");
                cursor.close();
                sqLiteDatabase.close();
                return false;
            }*/
        }
        cursor.close();
        sqLiteDatabase.close();

        return false;
    }

    public long registerUser(UserData data){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", data.getUsername());
        contentValues.put("password", data.getPassword());
        long user = sqLiteDatabase.insert("User", null, contentValues);
        if(user != -1){
            Log.e(TAG, "registerUser: Usuario registrado correctamente");
        } else {
            Log.e(TAG, "registerUser: error al registrar usuario...");
        }
        sqLiteDatabase.close();
        return user;
    }

    //public long userExists(UserData data)

    public boolean loginUser(UserData data){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select * from User", null, null);
        while (cursor.moveToNext()){
            String username = cursor.getString(1);
            String password = cursor.getString(2);

            if(username.equals(data.getUsername()) && password.equals(data.getPassword())){
                Log.e(TAG, "LoginUser: Usuario loggeado exitosamente.");
                cursor.close();
                sqLiteDatabase.close();
                return true;
            } /*else {
                Log.e(TAG, "LoginUser: Error en el login de Usuario.");
                cursor.close();
                sqLiteDatabase.close();
                return false;
            }*/
        }
        cursor.close();
        sqLiteDatabase.close();

        return false;
    }

    /* *** Products *** */
    public long insertProducts(ProductData data) {

        long product = 0;
        try {
            SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();

            ContentValues values = new ContentValues();
            values.put("name", data.getName());
            values.put("brand", data.getBrand());
            values.put("category" , data.getCategory());
            values.put("stock", data.getStock());
            values.put("price", data.getPrice());

            product = sqLiteDatabase.insert("Product", null, values);
            if (product != -1){
                Log.e(TAG, "insertProducts: productos insertados exitosamente.");
            }
        } catch (Exception ex) {
            Log.e(TAG, "insertProducts: error al insertar productos." +ex.getMessage());
        }

        return product;
    }

    public ArrayList<ProductData> getProducts() {

        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();

        ArrayList<ProductData> productList = new ArrayList<>();
        ProductData product = null;
        Cursor cursorProductos = null;

        cursorProductos = sqLiteDatabase.rawQuery("select * from Product", null );

        if(cursorProductos.moveToFirst()) {
            do {
                product = new ProductData();
                product.setId(cursorProductos.getInt(0));
                product.setName(cursorProductos.getString(1));
                product.setBrand(cursorProductos.getString(2));
                product.setCategory(cursorProductos.getString(3));
                product.setStock(cursorProductos.getInt(4));
                product.setPrice(cursorProductos.getDouble(5));
                productList.add(product);
            } while (cursorProductos.moveToNext());
        }

        cursorProductos.close();
        return productList;
    }


}
