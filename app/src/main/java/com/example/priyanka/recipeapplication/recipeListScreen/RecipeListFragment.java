package com.example.priyanka.recipeapplication.recipeListScreen;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.priyanka.recipeapplication.R;
import com.example.priyanka.recipeapplication.model.Recipe;
import com.example.priyanka.recipeapplication.network.RecipeClient;
import com.example.priyanka.recipeapplication.utils.RecipeIDComparator;
import com.example.priyanka.recipeapplication.utils.RecipeNameComparator;
import com.example.priyanka.recipeapplication.utils.RecipeUtils;

import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RecipeListFragment extends Fragment {
    private RecipeListAdapter mRecipeListAdapter;

    private ProgressBar mLoader;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_recipe_list, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mLoader = view.findViewById(R.id.progress_bar);
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);

        mRecipeListAdapter = new RecipeListAdapter(getActivity());
        recyclerView.setAdapter(mRecipeListAdapter);

        // set has option menu true
        setHasOptionsMenu(true);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // check for internet connection
        if (RecipeUtils.hasNetworkConnection(getActivity())) { // yes
            // call api
            makeApiCall();

        } else { // no
            // check local data
            tryToLoadDataFromLocal();
        }
    }

    private void makeApiCall() {

        mLoader.setVisibility(View.VISIBLE);

        Call<List<Recipe>> call = RecipeClient.getRecipeApi().getRecipes();
        call.enqueue(new Callback<List<Recipe>>() {
            @Override
            public void onResponse(Call<List<Recipe>> call, Response<List<Recipe>> response) { // success
                mLoader.setVisibility(View.GONE);

                // show data
                showData(response.body());

                // store response into shared preference
                RecipeUtils.storeRecipeApiResponse(getActivity(), response);
            }

            @Override
            public void onFailure(Call<List<Recipe>> call, Throwable t) { // failure
                mLoader.setVisibility(View.GONE);
                // check local data
                tryToLoadDataInCaseOfApiFailure();
            }
        });
    }

    private void showData(List<Recipe> list) {
        if (mRecipeListAdapter != null && list != null) {
            mRecipeListAdapter.setDataList(list);
            mRecipeListAdapter.notifyDataSetChanged();
        }
    }

    private void tryToLoadDataFromLocal() {
        // retrieve
        List<Recipe> list = RecipeUtils.retrieveRecipeApiResponse(getActivity());

        if (list != null && list.size() != 0) {
            // show data
            showData(list);

        } else {
            Toast.makeText(getActivity(), "No Internet Connection", Toast.LENGTH_SHORT).show();
        }
    }

    private void tryToLoadDataInCaseOfApiFailure() {
        // retrieve
        List<Recipe> list = RecipeUtils.retrieveRecipeApiResponse(getActivity());

        if (list != null && list.size() != 0) {
            // show data
            showData(list);

        } else {
            Toast.makeText(getActivity(), "Server Error", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        getActivity().getMenuInflater().inflate(R.menu.menu_main, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getTitle().toString()) {
            case "By Id":
                sortByRecipeIDAndShow(mRecipeListAdapter.getDataList());
                break;

            case "By Name":
                sortByRecipeNameAndShow(mRecipeListAdapter.getDataList());
                break;
        }

        return true;
    }

    private void sortByRecipeIDAndShow(List<Recipe> recipeList) {
        if (recipeList != null) {
            Collections.sort(recipeList, new RecipeIDComparator());
            showData(recipeList);
        }
    }

    private void sortByRecipeNameAndShow(List<Recipe> recipeList) {
        if (recipeList != null) {
            Collections.sort(recipeList, new RecipeNameComparator());
            showData(recipeList);
        }
    }
}
