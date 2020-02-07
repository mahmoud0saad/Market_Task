package com.mahmoudsaad.market.ui.activity.products;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.material.tabs.TabLayout;
import com.mahmoudsaad.market.R;
import com.mahmoudsaad.market.data.entites.CategoryResponse;
import com.mahmoudsaad.market.data.viewmodels.products.ProductsViewModel;
import com.mahmoudsaad.market.databinding.ActivityProductsBinding;
import com.mahmoudsaad.market.ui.activity.base.BaseActivity;
import com.mahmoudsaad.market.ui.adapters.TabProductAdapter;
import com.squareup.picasso.Picasso;

public class ProductsActivity extends BaseActivity {
    private static final String TAG = "ProductsActivity";

    private ActivityProductsBinding mProductsBinding;
    private ProductsViewModel mProductsViewModel;

    private CategoryResponse mCategoryResponse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mProductsBinding= DataBindingUtil.setContentView(this,R.layout.activity_products);

        mProductsViewModel= ViewModelProviders.of(this).get(ProductsViewModel.class);

        mCategoryResponse=mProductsViewModel.prepareCategoryResponse(getIntent());

        intiToolbar();

        intiTablayout();
    }

    private void intiTablayout() {
        if (mCategoryResponse!=null) {
            mProductsBinding.productTablayout.addTab(mProductsBinding.productTablayout.newTab().setText(mCategoryResponse.getName()));
            mProductsBinding.productTablayout.addTab(mProductsBinding.productTablayout.newTab().setText(mCategoryResponse.getName()));
        }


        TabProductAdapter tabProductAdapter=new TabProductAdapter(getSupportFragmentManager(),mCategoryResponse);
        mProductsBinding.productViewpager.setAdapter(tabProductAdapter);

        mProductsBinding.productViewpager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mProductsBinding.productTablayout));
        mProductsBinding.productTablayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mProductsBinding.productViewpager));
        mProductsBinding.productTablayout.setupWithViewPager(mProductsBinding.productViewpager);


    }

    private void intiToolbar() {
        setSupportActionBar(mProductsBinding.productToolbar);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar!=null) {
            actionBar.setTitle("");
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        if (mCategoryResponse!=null) {

            Picasso.get()
                    .load(mCategoryResponse.getCategoryImg())
                    .into(mProductsBinding.toolbarImageView);

            mProductsBinding.titleToolbar.setText(mCategoryResponse.getName());
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.product_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return false;
    }

}
