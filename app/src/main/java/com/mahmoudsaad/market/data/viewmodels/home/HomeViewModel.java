package com.mahmoudsaad.market.data.viewmodels.home;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.mahmoudsaad.market.data.entites.CategoryResponse;
import com.mahmoudsaad.market.data.viewmodels.base.BaseViewModel;
import com.mahmoudsaad.market.repository.retrofit.category.ApiCategories;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import retrofit2.Response;

public class HomeViewModel extends BaseViewModel {
    private static final String TAG = "HomeViewModel";

    private MutableLiveData<List<CategoryResponse>> categoriesLiveData=new MutableLiveData<>();

    public HomeViewModel(@NonNull Application application) {
        super(application);
    }

    public MutableLiveData<List<CategoryResponse>> getCategories(){
        ApiCategories.open(getApplication()).getCategory().subscribe(new Observer<Response<List<CategoryResponse>>>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Response<List<CategoryResponse>> categoryResponseResponse) {

                categoriesLiveData.setValue(categoryResponseResponse.body());
            }

            @Override
            public void onError(Throwable e) {
                categoriesLiveData.setValue(null);
                Log.i(TAG, "onError: "+e);
            }

            @Override
            public void onComplete() {

            }
        });

        return categoriesLiveData;
    }
}
