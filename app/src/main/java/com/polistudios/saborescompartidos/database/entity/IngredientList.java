package com.polistudios.saborescompartidos.database.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class IngredientList {
    @PrimaryKey
    public int id;

    @ColumnInfo
    public int idRecipe;

    @ColumnInfo
    public int idIngredient;

    @ColumnInfo
    public int quantity;

    @ColumnInfo
    public String unit;

    public IngredientList(int id, int idRecipe, int idIngredient, int quantity, String unit) {
        this.id = id;
        this.idRecipe = idRecipe;
        this.idIngredient = idIngredient;
        this.quantity = quantity;
        this.unit = unit;
    }
}
