package com.polistudios.lacocinamagica.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.polistudios.lacocinamagica.database.dao.RecipeDAO;
import com.polistudios.lacocinamagica.database.entity.Recipe;

@Database(entities = {Recipe.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase
{
    public abstract RecipeDAO recipeDAO();
}

