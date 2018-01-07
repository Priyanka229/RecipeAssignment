package com.example.priyanka.recipeapplication.recipeDetailScreen;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.priyanka.recipeapplication.R;
import com.example.priyanka.recipeapplication.model.Ingredient;
import com.example.priyanka.recipeapplication.model.Recipe;

import java.util.List;

public class RecipeDetailAdapter extends RecyclerView.Adapter<RecipeDetailAdapter.RecipeDetailViewHolder> {
    private Context mContext;
    private LayoutInflater mLayoutInflater;
    private List<Ingredient> mDataList;


    public RecipeDetailAdapter(Context context) {
        this.mContext = context;
        this.mLayoutInflater = LayoutInflater.from(context);
    }

    public void setDataList(List<Ingredient> list) {
        this.mDataList = list;
    }

    @Override
    public RecipeDetailViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mLayoutInflater.inflate(R.layout.adapter_detail_item, parent, false);
        return new RecipeDetailViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecipeDetailViewHolder holder, int position) {
        if (holder != null) {
            Ingredient ingredient = mDataList.get(position);
            if (ingredient != null) {
                holder.mText1View.setText(ingredient.getIngredient());
                holder.mText2View.setText(String.valueOf(ingredient.getQuantity()));
                holder.mText3View.setText(ingredient.getMeasure());
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

    class RecipeDetailViewHolder extends RecyclerView.ViewHolder {
        private TextView mText1View;
        private TextView mText2View;
        private TextView mText3View;

        public RecipeDetailViewHolder(View itemView) {
            super(itemView);

            mText1View = itemView.findViewById(R.id.text1_tv);
            mText2View = itemView.findViewById(R.id.text2_tv);
            mText3View = itemView.findViewById(R.id.text3_tv);
        }
    }
}
