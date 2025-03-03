package com.polistudios.lacocinamagica.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.polistudios.lacocinamagica.database.dao.CategoryDAO;
import com.polistudios.lacocinamagica.database.dao.CategoryListDAO;
import com.polistudios.lacocinamagica.database.dao.RecipeDAO;
import com.polistudios.lacocinamagica.database.entity.Category;
import com.polistudios.lacocinamagica.database.entity.CategoryList;
import com.polistudios.lacocinamagica.database.entity.Recipe;

@Database(entities = {Recipe.class, Category.class, CategoryList.class}, version = 2, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase
{
    public abstract RecipeDAO recipeDAO();
    public abstract CategoryDAO categoryDAO();
    public abstract CategoryListDAO categoryListDAO();
}

