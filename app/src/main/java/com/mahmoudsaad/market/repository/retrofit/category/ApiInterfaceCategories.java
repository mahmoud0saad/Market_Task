package com.mahmoudsaad.market.repository.retrofit.category;



import com.mahmoudsaad.market.data.entites.CategoryResponse;
import com.mahmoudsaad.market.repository.retrofit.ApiConstans;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Response;
import retrofit2.http.GET;



public interface ApiInterfaceCategories {

    @GET(ApiConstans.END_POINT_CATEGORIES )
    Observable<Response<List<CategoryResponse>>> getCategory();

}
