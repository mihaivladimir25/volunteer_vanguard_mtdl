package com.example.proiect_voluntariat;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class Database extends SQLiteOpenHelper {

    public Database(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String qry1 = "CREATE TABLE users(username text, name text, password text, address text, specialization text, interests text, skills text, points int)";
        sqLiteDatabase.execSQL(qry1);

        String qry2 = "CREATE TABLE admins(username text, password text)";
        sqLiteDatabase.execSQL(qry2);

        String qry3 = "CREATE TABLE products(name text, description text, price real, image blob)";
        sqLiteDatabase.execSQL(qry3);

        String qry4 = "CREATE TABLE benef(username text, name text, password text, address text, field text, description text)";
        sqLiteDatabase.execSQL(qry4);

        String qry5 = "CREATE TABLE events(name text, description text, points int)";
        sqLiteDatabase.execSQL(qry5);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void register(String username, String password, String name, String address, String specialization, String interests, String skills) {
//    public void register(String username, String password) {
        ContentValues cv = new ContentValues();
        cv.put("username", username);
        cv.put("password", password);
        cv.put("name", name);
        cv.put("address", address);
        cv.put("specialization", specialization);
        cv.put("interests", interests);
        cv.put("skills", skills);
        SQLiteDatabase db = getWritableDatabase();
        db.insert("users", null, cv);
//        db.insert("admins", null, cv);
        db.close();
    }

    public int login(String username, String password) {
        int result = 0;
        String[] str = new String[2];
        str[0] = username;
        str[1] = password;
        SQLiteDatabase db = getReadableDatabase();

        Cursor c = db.rawQuery("SELECT * FROM users WHERE username=? AND password=?", str);
        if (c.moveToFirst()) {
            result = 1; // User login
        } else {
            Cursor d = db.rawQuery("SELECT * FROM admins WHERE username=? AND password=?", str);
            if (d.moveToFirst()) {
                result = 2; // Admin login
            } else {
                Cursor e = db.rawQuery("SELECT * FROM benef WHERE username=? AND password=?", str);
                if (e.moveToFirst()) {
                    result = 3; // Beneficiary login
                }
                e.close();
            }
            d.close();
        }
        c.close();
        db.close();
        return result;
    }

    public void addUser(String username, String password) {
        ContentValues cv = new ContentValues();
        cv.put("username", username);
        cv.put("password", password);
        cv.put("points", 0);
        SQLiteDatabase db = getWritableDatabase();
        db.insert("users", null, cv);
        db.close();
    }

    public void addBeneficiary(String username, String password, String name, String address, String field, String description) {
        ContentValues cv = new ContentValues();
        cv.put("username", username);
        cv.put("name", name);
        cv.put("password", password);
        cv.put("address", address);
        cv.put("field", field);
        cv.put("description", description);
        SQLiteDatabase db = getWritableDatabase();
        db.insert("benef", null, cv);
        db.close();
    }

    public void deleteUser(String username) {
        SQLiteDatabase db = getWritableDatabase();
        db.delete("users", "username=?", new String[]{username});
        db.close();
    }

    public boolean updatePassword(String username, String newPassword) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("password", newPassword);

        int rowsAffected = db.update("users", values, "username = ?", new String[]{username});

        db.close();

        return rowsAffected > 0;
    }

    public void addProduct(String name, String description, double price, byte[] image) {
        ContentValues cv = new ContentValues();
        cv.put("name", name);
        cv.put("description", description);
        cv.put("price", price);
        cv.put("image", image);
        SQLiteDatabase db = getWritableDatabase();
        db.insert("products", null, cv);
        db.close();
    }

    public List<Product> getAllProducts() {
        List<Product> productList = new ArrayList<>();

        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM products", null);

        if (cursor.moveToFirst()) {
            do {
                String name = cursor.getString(cursor.getColumnIndex("name"));
                String description = cursor.getString(cursor.getColumnIndex("description"));
                double price = cursor.getDouble(cursor.getColumnIndex("price"));
                byte[] image = cursor.getBlob(cursor.getColumnIndex("image"));

                Product product = new Product(name, description, price, image);
                productList.add(product);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return productList;
    }

    public void addEvent(String name, String description, int points) {
        ContentValues cv = new ContentValues();
        cv.put("name", name);
        cv.put("description", description);
        cv.put("points", points);
        SQLiteDatabase db = getWritableDatabase();
        db.insert("events", null, cv);
        db.close();
    }

    public Cursor query(String table, String[] columns, String selection,
                        String[] selectionArgs, String groupBy, String having, String orderBy) {
        SQLiteDatabase db = getReadableDatabase();
        return db.query(table, columns, selection, selectionArgs, groupBy, having, orderBy);
    }


}
