package com.example.priyanka.recipeapplication.recipeListScreen;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.priyanka.recipeapplication.MainActivity;
import com.example.priyanka.recipeapplication.R;
import com.example.priyanka.recipeapplication.model.Recipe;
import com.example.priyanka.recipeapplication.recipeDetailScreen.RecipeDetailFragment;

import java.util.List;

public class RecipeListAdapter extends RecyclerView.Adapter<RecipeListAdapter.RecipeListViewHolder> {
    private Context mContext;
    private LayoutInflater mLayoutInflater;
    private List<Recipe> mDataList;

    public RecipeListAdapter(Context context) {
        this.mContext = context;
        this.mLayoutInflater = LayoutInflater.from(context);
    }

    public void setDataList(List<Recipe> list) {
        this.mDataList = list;
    }

    public List<Recipe> getDataList() { return this.mDataList; }

    @Override
    public RecipeListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mLayoutInflater.inflate(R.layout.adapter_recipe_list_item, parent, false);
        return new RecipeListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecipeListViewHolder holder, int position) {
        if (holder != null) {
            final Recipe recipe = mDataList.get(position);
            if (recipe != null) {
                // set name
                holder.mTextView.setText(recipe.getName());

                // set image
                if (!TextUtils.isEmpty(recipe.getImage())) {
                    Glide.with(mContext)
                            .load(recipe.getImage())
                            .into(holder.mImgView);
                } else {
                    holder.mImgView.setImageResource(R.drawable.no_image_available);
                }

                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // load the fragment
                        if (mContext instanceof MainActivity) {
                            RecipeDetailFragment recipeDetailFragment = new RecipeDetailFragment();
                            recipeDetailFragment.setRecipe(recipe);

                            ((MainActivity) mContext).getSupportFragmentManager()
                                    .beginTransaction()
                                    .setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_right)
                                    .replace(R.id.main_container, recipeDetailFragment)
                                    .addToBackStack(null)
                                    .commit();
                        }
                    }
                });
            }
        }
    }

    @Override
    public int getItemCount() {
        int count;
        if (mDataList != null) {
            count = mDataList.size();
        } else {
            count = 0;
        }

        return count;
    }

    class RecipeListViewHolder extends RecyclerView.ViewHolder {
        private ImageView mImgView;
        private TextView mTextView;

        public RecipeListViewHolder(View itemView) {
            super(itemView);

            mImgView = itemView.findViewById(R.id.image_iv);
            mTextView = itemView.findViewById(R.id.text_tv);
        }
    }
}
