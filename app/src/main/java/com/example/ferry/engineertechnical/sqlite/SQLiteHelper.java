package com.example.ferry.engineertechnical.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.ferry.engineertechnical.DataFavorite;


public class SQLiteHelper extends SQLiteOpenHelper {

    public SQLiteHelper(Context context, String name, Integer ver) {
        super(context, name, null, ver);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DataFavorite.CREATE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DataFavorite.DROP_TABLE);
        db.execSQL(DataFavorite.CREATE_TABLE);

    }
}