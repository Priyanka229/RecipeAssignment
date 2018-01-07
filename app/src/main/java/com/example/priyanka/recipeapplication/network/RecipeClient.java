package com.example.priyanka.recipeapplication.network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RecipeClient {
    private static final String BASE_URL = "https://d17h27t6h515a5.cloudfront.net/";
    private static Retrofit mRetrofit;

    private static Retrofit getRecipeRetrofit() {
        if (mRetrofit == null) {
            Gson gson = new GsonBuilder()
                    .setLenient()
                    .create();
            mRetrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
        }

        return mRetrofit;
    }

    public static RecipeApi getRecipeApi() {

        Retrofit retrofit = getRecipeRetrofit();
        RecipeApi recipeApi = retrofit.create(RecipeApi.class);

        return recipeApi;
    }
}
