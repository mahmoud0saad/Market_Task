package com.mahmoudsaad.market.data.viewmodels.detail;

import android.app.Application;
import android.content.Intent;
import android.content.res.Resources;

import androidx.annotation.NonNull;

import com.mahmoudsaad.market.R;
import com.mahmoudsaad.market.data.entites.CategoryResponse;
import com.mahmoudsaad.market.data.entites.ProductsItem;
import com.mahmoudsaad.market.data.viewmodels.base.BaseViewModel;

public class DetailViewModel extends BaseViewModel {

    public DetailViewModel(@NonNull Application application) {
        super(application);
    }

    public ProductsItem getProductsItem(Intent intent){
        if (intent==null)return null;

        Resources resourse = getApplication().getResources();
        if (intent.hasExtra(resourse.getString(R.string.intent_product_item_key))){
            return (ProductsItem) intent.getSerializableExtra(resourse.getString(R.string.intent_product_item_key));
        }
        return null;
    }
}
