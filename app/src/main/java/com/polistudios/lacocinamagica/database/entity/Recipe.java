package com.polistudios.lacocinamagica.database.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Recipe
{
    @PrimaryKey public int id;

    @ColumnInfo public int idUser;

    @ColumnInfo public String title;

    @ColumnInfo public String description;

    @ColumnInfo public Difficulty difficulty;

    @ColumnInfo public float time;

    @ColumnInfo public int portions;

    @ColumnInfo public String[] steps;

    @ColumnInfo public int idIngredientList;

    public enum Difficulty
    {
        Easy,
        Medium,
        Hard
    }

    public Recipe(int id, int idUser, String title, String description, Difficulty difficulty, float time, int portions, String[] steps, int idIngredientList) {
        this.id = id;
        this.idUser = idUser;
        this.title = title;
        this.description = description;
        this.difficulty = difficulty;
        this.time = time;
        this.portions = portions;
        this.steps = steps;
        this.idIngredientList = idIngredientList;
    }
}