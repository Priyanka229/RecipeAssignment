package com.example.priyanka.recipeapplication.utils;

import com.example.priyanka.recipeapplication.model.Recipe;

import java.util.Comparator;

public class RecipeIDComparator implements Comparator<Recipe> {
    @Override
    public int compare(Recipe o1, Recipe o2) {
        int returnValue;
        if (o1.getId() < o2.getId()) {
            returnValue = -1;
        } else if (o1.getId() > o2.getId()) {
            returnValue = 1;
        } else {
            returnValue = 0;
        }

        return returnValue;
    }
}
