package com.mahmoudsaad.market.ui.adapters;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.mahmoudsaad.market.data.entites.CategoryResponse;
import com.mahmoudsaad.market.ui.Fragment.product.ProductFragment;

public class TabProductAdapter extends FragmentPagerAdapter  {
    private static final String TAG = "TabProductAdapter";

    private CategoryResponse mCategoryResponse;

    public TabProductAdapter(@NonNull FragmentManager fm, CategoryResponse categoryResponse) {
        super(fm);
        mCategoryResponse=categoryResponse;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {

        return new ProductFragment(mCategoryResponse.getProducts());
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mCategoryResponse.getName();
    }
}
