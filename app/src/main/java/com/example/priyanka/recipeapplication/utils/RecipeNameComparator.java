package com.example.priyanka.recipeapplication.utils;

import com.example.priyanka.recipeapplication.model.Recipe;

import java.util.Comparator;

public class RecipeNameComparator implements Comparator<Recipe> {
    @Override
    public int compare(Recipe o1, Recipe o2) {
        return o1.getName().compareTo(o2.getName());
    }
}
