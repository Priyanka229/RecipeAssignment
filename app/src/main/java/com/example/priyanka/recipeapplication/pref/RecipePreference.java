package com.example.priyanka.recipeapplication.pref;

import android.content.Context;
import android.content.SharedPreferences;

public class RecipePreference {
    private static final String PREFS_NAME = "recipe_preferences";

    private final static String RECIPE_API_RESPONSE_KEY = "recipe_api_response";

    private static SharedPreferences getSharedPreferences(Context context) {
        return context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
    }

    public static void storeRecipeApiResponse(Context context, String apiResponse) {
        getSharedPreferences(context)
                .edit()
                .putString(RECIPE_API_RESPONSE_KEY, apiResponse)
                .apply();
    }

    public static String retrieveRecipeApiResponse(Context context) {
        return getSharedPreferences(context)
                .getString(RECIPE_API_RESPONSE_KEY, "");
    }

    public static void likeRecipe(Context context, String recipeId) {
        getSharedPreferences(context)
                .edit()
                .putBoolean("RecipeId" + recipeId, true)
                .apply();
    }

    public static void disLikeRecipe(Context context, String recipeId) {
        getSharedPreferences(context)
                .edit()
                .putBoolean("RecipeId" + recipeId, false)
                .apply();
    }

    public static boolean getRecipeLikeDisLikeStatus(Context context, String recipeId) {
        return getSharedPreferences(context)
                .getBoolean("RecipeId" + recipeId, false);
    }
}
