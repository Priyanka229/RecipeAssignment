package com.example.priyanka.recipeapplication.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.text.TextUtils;

import com.example.priyanka.recipeapplication.model.Recipe;
import com.example.priyanka.recipeapplication.pref.RecipePreference;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import retrofit2.Response;


public class RecipeUtils {
    public static void storeRecipeApiResponse(Context context, Response<List<Recipe>> response) {
        if (context != null && response != null && response.body() != null) {
            RecipePreference.storeRecipeApiResponse(context, new Gson().toJson(response.body()));
        }
    }

    public static List<Recipe> retrieveRecipeApiResponse(Context context) {
        List<Recipe> response = null;
        if (context != null) {
            String apiResponse = RecipePreference.retrieveRecipeApiResponse(context);
            if (!TextUtils.isEmpty(apiResponse)) {
                Type type = new TypeToken<List<Recipe>>() {}.getType();
                response = new Gson().fromJson(apiResponse, type);
            }
        }

        return response;
    }

    public static boolean hasNetworkConnection(Context context) {
        boolean haveConnectedWifi = false;
        boolean haveConnectedMobile = false;

        if (context == null)
            return false;

        try {
            ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

            for (NetworkInfo ni : cm.getAllNetworkInfo()) {
                if (ni.getType() == ConnectivityManager.TYPE_WIFI && ni.isConnected())
                    haveConnectedWifi = true;
                if (ni.getType() == ConnectivityManager.TYPE_MOBILE && ni.isConnected())
                    haveConnectedMobile = true;
            }

            return haveConnectedWifi || haveConnectedMobile;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
