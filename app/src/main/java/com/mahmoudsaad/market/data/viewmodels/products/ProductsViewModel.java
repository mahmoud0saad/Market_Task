package com.mahmoudsaad.market.data.viewmodels.products;

import android.app.Application;
import android.content.Intent;
import android.content.res.Resources;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.mahmoudsaad.market.R;
import com.mahmoudsaad.market.data.entites.CategoryResponse;
import com.mahmoudsaad.market.data.viewmodels.base.BaseViewModel;

public class ProductsViewModel extends BaseViewModel {
    private static final String TAG = "ProductsViewModel";

    private MutableLiveData<CategoryResponse> mCategoryLiveData=new MutableLiveData<>();

    public ProductsViewModel(@NonNull Application application) {
        super(application);
    }

    public CategoryResponse prepareCategoryResponse(Intent intent){
        if (intent==null)return null;

        Resources resourse = getApplication().getResources();
        if (intent.hasExtra(resourse.getString(R.string.category_response_key))){
            return (CategoryResponse) intent.getSerializableExtra(resourse.getString(R.string.category_response_key));
        }
        return null;
    }

}
