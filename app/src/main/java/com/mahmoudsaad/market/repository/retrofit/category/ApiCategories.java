package com.mahmoudsaad.market.repository.retrofit.category;

import android.content.Context;


import com.mahmoudsaad.market.data.entites.CategoryResponse;
import com.mahmoudsaad.market.repository.retrofit.ApiConfig;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ApiCategories {
    private static ApiCategories apiServices;
    private static Retrofit retrofit;

    public static synchronized ApiCategories open(Context context){
        if (retrofit==null){
            retrofit= ApiConfig.getRetrofit(context);
        }
        if (apiServices==null){
            apiServices=new ApiCategories();
        }
        return apiServices;
    }

    public Observable<Response<List<CategoryResponse>>> getCategory(){

        ApiInterfaceCategories apiInterfaceCategories =retrofit.create(ApiInterfaceCategories.class);

        return apiInterfaceCategories.getCategory()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
