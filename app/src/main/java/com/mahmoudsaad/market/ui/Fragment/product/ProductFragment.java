package com.mahmoudsaad.market.ui.Fragment.product;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mahmoudsaad.market.R;
import com.mahmoudsaad.market.data.entites.ProductsItem;
import com.mahmoudsaad.market.data.interfaces.ProductClick;
import com.mahmoudsaad.market.ui.activity.detail.DetailActivity;
import com.mahmoudsaad.market.ui.adapters.RecyclerProductAdapter;

import java.util.List;

public class ProductFragment extends Fragment implements ProductClick {

    private List<ProductsItem> mProductsItems;
    private RecyclerView productRecyclerView;

    public ProductFragment(List<ProductsItem> productsItems){
        mProductsItems=productsItems;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView=inflater.inflate(R.layout.fragment_products,container,false);

        productRecyclerView=rootView.findViewById(R.id.product_recycler_view);

        GridLayoutManager gridLayoutManager=new GridLayoutManager(getContext(),2);

        RecyclerProductAdapter adapter=new RecyclerProductAdapter(this);

        productRecyclerView.setAdapter(adapter);

        productRecyclerView.setLayoutManager(gridLayoutManager);

        adapter.setmItemList(mProductsItems);

        return rootView;
    }

    @Override
    public void onClick(ProductsItem productsItem) {
        Intent intent=new Intent(getActivity(), DetailActivity.class);
        intent.putExtra(getString(R.string.intent_product_item_key),productsItem);
        startActivity(intent);
    }
}
