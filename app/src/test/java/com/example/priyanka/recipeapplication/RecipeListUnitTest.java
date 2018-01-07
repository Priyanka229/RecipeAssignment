package com.example.priyanka.recipeapplication;

import com.example.priyanka.recipeapplication.model.Recipe;
import com.example.priyanka.recipeapplication.utils.RecipeIDComparator;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static junit.framework.Assert.assertEquals;

@RunWith(JUnit4.class)
public class RecipeListUnitTest {
    @Test
    public void ratingFilteredListTest() {
        List<Recipe> results = new ArrayList<>();
        Recipe recipe = new Recipe();
        recipe.setId(1);
        results.add(recipe);

        recipe = new Recipe();
        recipe.setId(8);
        results.add(recipe);


        recipe = new Recipe();
        recipe.setId(3);
        results.add(recipe);


        recipe = new Recipe();
        recipe.setId(7);
        results.add(recipe);


        List<Recipe> expectedResults = new ArrayList<>();
        recipe = new Recipe();
        recipe.setId(1);
        expectedResults.add(recipe);

        recipe = new Recipe();
        recipe.setId(3);
        expectedResults.add(recipe);


        recipe = new Recipe();
        recipe.setId(7);
        expectedResults.add(recipe);


        recipe = new Recipe();
        recipe.setId(8);
        expectedResults.add(recipe);


        Collections.sort(results, new RecipeIDComparator());

        for (int i = 0; i < results.size(); i++) {
            Recipe result = results.get(i);
            Recipe testResult = expectedResults.get(i);

            assertEquals(result.getId(), testResult.getId());
        }
    }
}
