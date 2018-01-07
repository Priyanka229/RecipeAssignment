package com.example.priyanka.recipeapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.priyanka.recipeapplication.recipeListScreen.RecipeListFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.main_container, new RecipeListFragment())
                .commit();
    }
}
