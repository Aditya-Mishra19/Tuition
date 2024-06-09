package com.example.tuitioncenter;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class MyDBHandler extends SQLiteOpenHelper {
    public MyDBHandler(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table appointments(name varchar(10),phno varchar(20),city varchar(30),course varchar(50),dat varchar(20),tim varchar(30))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public void insertRecord(String n,String nu,String c,String su,String d,String t){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("insert into appointments values(?,?,?,?,?,?)",new String[]{n,nu,c,su,d,t});
        db.close();
    }
    public String displayUserRecord(String n){
        String rec = "";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cr = db.rawQuery("select * from appointments where name = ?",new String[]{n});
        while (cr.moveToNext()){
            String na = cr.getString(0);
            String nu = cr.getString(1);
            String c = cr.getString(2);
            String su = cr.getString(3);
            String d = cr.getString(4);
            String t = cr.getString(5);
            rec+=na+"\t"+nu+"\t"+c+"\t"+su+"\t"+d+"\t"+t+"\n";
        }
        db.close();
        return  rec;
    }
    public int displayCityRecord(String c){
        int ct =0;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cr = db.rawQuery("select * from appointments where city = ?",new String[]{c});
        while(cr.moveToNext())
            ct++;
        db.close();
        return ct;
    }
    public String displayDateRecord(String d){
        String rec = "";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cr = db.rawQuery("select * from appointments where dat = ?",new String[]{d});
        while (cr.moveToNext()){
            String na = cr.getString(0);
            String nu = cr.getString(1);
            String c = cr.getString(2);
            String su = cr.getString(3);
            String da = cr.getString(4);
            String t = cr.getString(5);
            rec+=na+"\t"+nu+"\t"+c+"\t"+su+"\t"+da+"\t"+t+"\n";
        }
        db.close();
        return  rec;
    }
}

