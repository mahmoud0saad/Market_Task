package com.mahmoudsaad.market.ui.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.mahmoudsaad.market.R;
import com.mahmoudsaad.market.data.entites.CategoryResponse;
import com.mahmoudsaad.market.data.interfaces.CategoryClick;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<CategoryResponse> mItemList = new ArrayList<>();

    private final int CATEGORY_TYPE=1,AD_TYPE=2;

    private CategoryClick mCategoryClick;

    public CategoryAdapter(CategoryClick categoryClick) {
        mCategoryClick=categoryClick;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        if (viewType==CATEGORY_TYPE) {
            View rootView = LayoutInflater.from(parent.getContext())
                    .inflate(
                            R.layout.list_item_category,
                            parent,
                            false);

            return new CategoryViewHolder(rootView);
        }else {
            View rootView = LayoutInflater.from(parent.getContext())
                    .inflate(
                            R.layout.list_item_ad_banner,
                            parent,
                            false);

            return new ADViewHolder(rootView);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        if (getItemViewType(position)==CATEGORY_TYPE){
            ((CategoryViewHolder)holder).onBind(mItemList.get(position-position/3));
        }
    }



    @Override
    public int getItemCount() {
        return mItemList.size()+mItemList.size()/3;
    }

    public void setmItemList(List<CategoryResponse> CategoryResponseList) {
        if (CategoryResponseList==null)return;
        mItemList.addAll(CategoryResponseList);
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {

        return position%3==2?AD_TYPE:CATEGORY_TYPE;
    }

    class CategoryViewHolder extends RecyclerView.ViewHolder {

        ImageView mCategoryImageView;
        TextView mNameCategoryTextView;

        public CategoryViewHolder(@NonNull View itemView) {
            super(itemView);
            mCategoryImageView=itemView.findViewById(R.id.category_image_view);
            mNameCategoryTextView=itemView.findViewById(R.id.category_name_text_view);

            mCategoryImageView.setOnClickListener(v->{

                mCategoryClick.onClick( mItemList.get(getAdapterPosition()-getAdapterPosition()/3));
            });

        }

        void onBind(CategoryResponse categoryResponse) {

            mNameCategoryTextView.setText(categoryResponse.getName());

            Picasso.get()
                    .load(categoryResponse.getCategoryImg())
                    .into(mCategoryImageView);


        }


    }


    class ADViewHolder extends RecyclerView.ViewHolder {

        public ADViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        void onBind() {

        }


    }
}

