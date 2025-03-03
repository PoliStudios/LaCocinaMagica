package com.polistudios.lacocinamagica.database.entity;

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
}
