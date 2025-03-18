package com.polistudios.saborescompartidos.database.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.polistudios.saborescompartidos.database.entity.Category;

import java.util.List;

@Dao
public interface CategoryDAO {
    @Query("SELECT * FROM Category")
    List<Category> getAll();

    @Query("SELECT * FROM Category WHERE name LIKE :name")
    List<Category> getByName(String name);

    @Insert
    void insert(Category category);

    @Query("DELETE FROM Category")
    void deleteAll();

    @Update
    void update(Category category);
}
