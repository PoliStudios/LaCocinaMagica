package com.polistudios.saborescompartidos.database.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class CategoryList {
    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo
    public int categoryID;

    @ColumnInfo
    public int recipeID;

    public CategoryList(int categoryID, int recipeID) {
        this.categoryID = categoryID;
        this.recipeID = recipeID;
    }
}
