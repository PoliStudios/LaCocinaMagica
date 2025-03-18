package com.polistudios.saborescompartidos.database;

import android.content.Context;

import androidx.room.Room;

public class DatabaseCon
{
public static AppDatabase instance(Context ctx)
    {
        return Room.databaseBuilder(ctx, AppDatabase.class, "db")
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build();
    }
}
