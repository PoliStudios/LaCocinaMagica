package com.polistudios.lacocinamagica.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.polistudios.lacocinamagica.database.entity.Recipe;

import java.util.List;

@Dao
public interface RecipeDAO
{
    @Query("SELECT * FROM Recipe")
    List<Recipe> getAll();

    @Query("SELECT * FROM Recipe WHERE title LIKE :title LIMIT 1")
    Recipe findByTitle(String title);

    @Insert
    void insertAll(Recipe... recipes);

    @Update
    void update(Recipe recipe);

    @Delete
    void delete(Recipe recipe);
}
