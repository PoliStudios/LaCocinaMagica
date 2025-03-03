package com.polistudios.lacocinamagica.database.dao;

import androidx.room.Dao;
import androidx.room.Entity;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.polistudios.lacocinamagica.database.entity.CategoryList;

import java.util.List;

@Dao
public interface CategoryListDAO {
    @Query("SELECT * FROM CategoryList")
    List<CategoryList> getAll();

    @Query("SELECT * FROM CategoryList WHERE categoryID LIKE :categoryID")
    List<CategoryList> getByCategoryID(int categoryID);

    @Query("SELECT * FROM CategoryList WHERE recipeID LIKE :recipeID")
    List<CategoryList> getByRecipeID(int recipeID);

    @Insert
    void insert(CategoryList categoryList);

    @Update
    void update(CategoryList categoryList);
}
