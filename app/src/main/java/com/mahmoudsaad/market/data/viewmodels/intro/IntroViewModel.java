package com.mahmoudsaad.market.data.viewmodels.intro;

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

public class IntroViewModel extends BaseViewModel {
    private static final String TAG = "IntroViewModel";


    public IntroViewModel(@NonNull Application application) {
        super(application);
    }



}
