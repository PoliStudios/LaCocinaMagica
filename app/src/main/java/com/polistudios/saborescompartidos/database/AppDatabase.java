package com.polistudios.saborescompartidos.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.polistudios.saborescompartidos.database.dao.CategoryDAO;
import com.polistudios.saborescompartidos.database.dao.CategoryListDAO;
import com.polistudios.saborescompartidos.database.dao.RecipeDAO;
import com.polistudios.saborescompartidos.database.entity.Category;
import com.polistudios.saborescompartidos.database.entity.CategoryList;
import com.polistudios.saborescompartidos.database.entity.Recipe;

@Database(entities = {Recipe.class, Category.class, CategoryList.class}, version = 2, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase
{
    public abstract RecipeDAO recipeDAO();
    public abstract CategoryDAO categoryDAO();
    public abstract CategoryListDAO categoryListDAO();
}

