package com.mahmoudsaad.market.ui.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;


import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.mahmoudsaad.market.R;
import com.mahmoudsaad.market.data.entites.ProductsItem;
import com.mahmoudsaad.market.data.interfaces.ProductClick;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class RecyclerProductAdapter extends RecyclerView.Adapter<RecyclerProductAdapter.ProductViewHolder> {

    private List<ProductsItem> mItemList = new ArrayList<>();
    private ProductClick mProductClick;
    public RecyclerProductAdapter(ProductClick productClick) {
        mProductClick=productClick;

    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View rootView = LayoutInflater.from(parent.getContext())
                .inflate(
                        R.layout.list_item_product,
                        parent,
                        false);


        return new ProductViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        holder.onBind(mItemList.get(position));
    }

    @Override
    public int getItemCount() {
        return mItemList.size();
    }

    public void setmItemList(List<ProductsItem> ProductsItemList) {
        mItemList.addAll(ProductsItemList);
        notifyDataSetChanged();
    }

    class ProductViewHolder extends RecyclerView.ViewHolder {

        private ImageView mProductImageView;
        private TextView mNameTextView,mPriceTextView,mWeightTextView;
        private ImageButton addImageButton;
        private boolean isChecked=false;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);

            mProductImageView=itemView.findViewById(R.id.product_image_view);
            mNameTextView=itemView.findViewById(R.id.name_product_text_view);
            mPriceTextView=itemView.findViewById(R.id.price_product_text_view);
            mWeightTextView=itemView.findViewById(R.id.weight_product_text_view);
            addImageButton=itemView.findViewById(R.id.add_product_image_button);

            mProductImageView.setOnClickListener(v -> mProductClick.onClick(mItemList.get(getAdapterPosition())) );
            addImageButton.setOnClickListener(v -> {
                isChecked=!isChecked;
                if (isChecked){
                    addImageButton.setBackgroundResource(R.drawable.circle_button_active_background);
                }else {
                    addImageButton.setBackgroundResource(R.drawable.circle_button_inactive_background);
                }
            });

        }

        void onBind(ProductsItem productsItem) {

            Picasso.get()
                    .load(productsItem.getProductImg())
                    .into(mProductImageView);

            mNameTextView.setText(productsItem.getName());
            mPriceTextView.setText(productsItem.getPrice());
            mWeightTextView.setText(productsItem.getWeight());
        }


    }


}

