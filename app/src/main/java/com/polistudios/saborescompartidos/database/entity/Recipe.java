package com.polistudios.saborescompartidos.database.entity;

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

    @ColumnInfo public String categories;

    @ColumnInfo public Difficulty difficulty;

    @ColumnInfo public float rating;

    @ColumnInfo public float time;

    @ColumnInfo public int portions;

    @ColumnInfo public String steps;

    @ColumnInfo public String ingredients;

    public enum Difficulty
    {
        Easy,
        Medium,
        Hard
    }

    public Recipe(int idUser, String title, String description, String categories, Difficulty difficulty, float rating, float time, int portions, String steps, String ingredients) {
        this.idUser = idUser;
        this.title = title;
        this.description = description;
        this.categories = categories;
        this.difficulty = difficulty;
        this.rating = rating;
        this.time = time;
        this.portions = portions;
        this.steps = steps;
        this.ingredients = ingredients;
    }
}