package com.example.proiect_mtdl;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class Database extends SQLiteOpenHelper {

    public Database(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String qry1 = "CREATE TABLE users(username text, name text, email text, password text, address text, specialization text, interests text, skills text)";
        sqLiteDatabase.execSQL(qry1);

        String qry2= "CREATE TABLE admins(username text, password text)";
        sqLiteDatabase.execSQL(qry2);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
    public void register(String username, String name,String email, String password){
        ContentValues cv = new ContentValues();
        cv.put("username", username);
        cv.put("name", name);
        cv.put("email", email);
        cv.put("password", password);
        SQLiteDatabase db = getWritableDatabase();
        db.insert("users", null, cv);
        db.close();
    }

    public void updateProfile(String name, String address, String specialization, String interests, String skills) {
        ContentValues cv = new ContentValues();
        cv.put("name", name);
        cv.put("address", address);
        cv.put("specialization", specialization);
        cv.put("interests", interests);
        cv.put("skills", skills);
        SQLiteDatabase db = getWritableDatabase();
        db.update("users", cv, null, null);
        db.close();
    }

    public int login(String username, String password){
        int result = 0;

        String[] str = new String[2];
        str[0] = username;
        str[1] = password;
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM users WHERE username=? AND password=?", str);
        if(c.moveToFirst()){
            result = 1;
        }
        Cursor d = db.rawQuery("SELECT * FROM admins WHERE username=? AND password=?", str);
        if(d.moveToFirst()){
            result = 2;
        }
        c.close();
        db.close();
        return result;
    }

    public ProfileData getProfileData() {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM users", null);
        ProfileData profileData = null;

        if (cursor.moveToFirst()) {
            int nameIndex = cursor.getColumnIndex("name");
            int addressIndex = cursor.getColumnIndex("address");
            int specializationIndex = cursor.getColumnIndex("specialization");
            int interestsIndex = cursor.getColumnIndex("interests");
            int skillsIndex = cursor.getColumnIndex("skills");

            if (nameIndex >= 0 && addressIndex >= 0 && specializationIndex >= 0 && interestsIndex >= 0 && skillsIndex >= 0) {
                String name = cursor.getString(nameIndex);
                String address = cursor.getString(addressIndex);
                String specialization = cursor.getString(specializationIndex);
                String interests = cursor.getString(interestsIndex);
                String skills = cursor.getString(skillsIndex);

                profileData = new ProfileData(name, address, specialization, interests, skills);
            }
        }

        cursor.close();
        db.close();

        return profileData;
    }

//    public int login_admin(String username, String password){
//        int result = 0;
//
//        String[] str = new String[2];
//        str[0] = username;
//        str[1] = password;
//        SQLiteDatabase db = getReadableDatabase();
//        Cursor c = db.rawQuery("SELECT * FROM admins WHERE username=? AND password=?", str);
//        if(c.moveToFirst()){
//            result = 1;
//        }
//        c.close();
//        db.close();
//        return result;
//    }
//
//    public void register_admin(String username, String password){
//        ContentValues cv = new ContentValues();
//        cv.put("username", username);
//        cv.put("password", password);
//        SQLiteDatabase db = getWritableDatabase();
//        db.insert("admins", null, cv);
//        db.close();
//    }

}
