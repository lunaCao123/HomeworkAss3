package com.example.catcrazeapp;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Cat.class},version = 1)
public abstract class CatDatabase extends RoomDatabase {

    public abstract CatDAO catDAO();
    private static CatDatabase instance;

    public static CatDatabase getInstance(Context context){
        if(instance == null){
            instance = Room.databaseBuilder(context, CatDatabase.class, "catDb")
                    .allowMainThreadQueries().build();
        }
        return instance;
    }
}