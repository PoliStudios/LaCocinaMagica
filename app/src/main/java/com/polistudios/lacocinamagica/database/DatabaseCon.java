package com.polistudios.lacocinamagica.database;

import android.content.Context;

import androidx.room.Room;

public class DatabaseCon
{
    public AppDatabase instance(Context ctx)
    {
        return Room.databaseBuilder(ctx, AppDatabase.class, "db")
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build();
    }
}
