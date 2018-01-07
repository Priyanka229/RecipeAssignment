package com.example.priyanka.recipeapplication.recipeDetailScreen;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.priyanka.recipeapplication.R;
import com.example.priyanka.recipeapplication.model.Recipe;
import com.example.priyanka.recipeapplication.pref.RecipePreference;

public class RecipeDetailFragment extends Fragment {
    private RecipeDetailAdapter mRecipeDetailAdapter;
    private Recipe mRecipe;
    private boolean likeDislikeFlag;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_recipe_detail, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // set image
        ImageView imageView = view.findViewById(R.id.image_iv);
        if (!TextUtils.isEmpty(mRecipe.getImage())) {
            Glide.with(getActivity())
                    .load(mRecipe.getImage())
                    .into(imageView);
        } else {
            imageView.setImageResource(R.drawable.no_image_available);
        }

        // set text
        TextView textView = view.findViewById(R.id.text_tv);
        textView.setText(mRecipe.getName());

        // recycler view and adapter
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);

        mRecipeDetailAdapter = new RecipeDetailAdapter(getActivity());
        mRecipeDetailAdapter.setDataList(mRecipe.getIngredients());

        recyclerView.setAdapter(mRecipeDetailAdapter);


        // like/dislike
        final ImageView likeDislikeIV = view.findViewById(R.id.favorite_iv);
        boolean status = RecipePreference.getRecipeLikeDisLikeStatus(getActivity(), String.valueOf(mRecipe.getId()));
        likeDislikeFlag = status;

        if (status) {
            likeDislikeIV.setImageResource(R.drawable.thumbs_up);
        } else {
            likeDislikeIV.setImageResource(R.drawable.thumbs_down);
        }
        likeDislikeIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (likeDislikeFlag) {
                    likeDislikeFlag = false;
                    likeDislikeIV.setImageResource(R.drawable.thumbs_down);
                    RecipePreference.disLikeRecipe(getActivity(), String.valueOf(mRecipe.getId()));
                } else {
                    likeDislikeFlag = true;
                    likeDislikeIV.setImageResource(R.drawable.thumbs_up);
                    RecipePreference.likeRecipe(getActivity(), String.valueOf(mRecipe.getId()));
                }
            }
        });

    }

    public void setRecipe(Recipe recipe) {
        this.mRecipe = recipe;
    }
}
