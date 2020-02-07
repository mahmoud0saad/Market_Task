package com.mahmoudsaad.market.ui.activity.home;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.text.Html;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mahmoudsaad.market.R;
import com.mahmoudsaad.market.data.entites.CategoryResponse;
import com.mahmoudsaad.market.data.interfaces.CategoryClick;
import com.mahmoudsaad.market.data.utils.Utils;
import com.mahmoudsaad.market.data.viewmodels.home.HomeViewModel;
import com.mahmoudsaad.market.databinding.ActivityHomeBinding;
import com.mahmoudsaad.market.ui.activity.base.BaseActivity;
import com.mahmoudsaad.market.ui.activity.products.ProductsActivity;
import com.mahmoudsaad.market.ui.adapters.CategoryAdapter;
import com.mahmoudsaad.market.ui.adapters.ImageSliderAdapter;

import java.io.Serializable;
import java.util.List;

import okhttp3.internal.Util;

public class HomeActivity extends BaseActivity implements CategoryClick {
    private static final String TAG = "HomeActivity";

    private ActivityHomeBinding mHomeBinding;
    private HomeViewModel mHomeViewModel;

    private CategoryAdapter mCategoryAdapter;

    private int [] imageResourse={
            R.drawable.slider_image1,
            R.drawable.slider_image2,
            R.drawable.slider_image3,
            R.drawable.slider_image4
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mHomeBinding= DataBindingUtil.setContentView(this,R.layout.activity_home);

        mHomeViewModel= ViewModelProviders.of(this).get(HomeViewModel.class);

        intiViews();

        loadCategories();

        mHomeBinding.internetImageView.setOnClickListener(v -> loadCategories());
    }

    private void loadCategories() {
        if (Utils.isConnected(this)){
            mHomeBinding.internetImageView.setVisibility(View.GONE);
        }else {
            mHomeBinding.internetImageView.setVisibility(View.VISIBLE);
        }

        mHomeBinding.categoryShimmer.setVisibility(View.VISIBLE);
        mHomeBinding.categoryShimmer.startShimmer();

        mHomeViewModel.getCategories().observe(this,resultList->{
            mCategoryAdapter.setmItemList(resultList);
            mHomeBinding.categoryShimmer.stopShimmer();
            mHomeBinding.categoryShimmer.setVisibility(View.GONE);

        });
    }

    private void intiViews() {
        initImageSlider();

        initCategoryRecycler();

        intiToolbar();

    }

    private void intiToolbar() {
        setSupportActionBar(mHomeBinding.homeToolbar);
        mHomeBinding.titleToolbar.setText(getString(R.string.home_title));

        ActionBar actionBar = getSupportActionBar();
        if (actionBar!=null)
            actionBar.setTitle("");
    }

    private void initCategoryRecycler() {
        mCategoryAdapter =new CategoryAdapter(this);

        GridLayoutManager gridLayoutManager=new GridLayoutManager(this,2);

        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                if (position%3==2){
                    return 2;
                }
                return 1;
            }
        });

        mHomeBinding.categoriesRecyclerView.setNestedScrollingEnabled(false);

        mHomeBinding.categoriesRecyclerView.setLayoutManager(gridLayoutManager);

        mHomeBinding.categoriesRecyclerView.setAdapter(mCategoryAdapter);
    }

    private void initImageSlider() {



        ImageSliderAdapter imageSliderAdapter=new ImageSliderAdapter(imageResourse);

        mHomeBinding.productSliderViewPager.setAdapter(imageSliderAdapter);

        addBottomDots(0);

        mHomeBinding.productSliderViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                addBottomDots(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        Handler handler=new Handler();
        Runnable runnable=new Runnable() {
            @Override
            public void run() {
                int currentItem=mHomeBinding.productSliderViewPager.getCurrentItem();

                mHomeBinding.productSliderViewPager.setCurrentItem(++currentItem==imageResourse.length?0:currentItem);

                handler.postDelayed(this,5000);
            }
        };

        handler.postDelayed(runnable ,5000);

    }

    private void addBottomDots(int currentPage) {
        TextView[] dots = new TextView[imageResourse.length];

        mHomeBinding.introLayoutDots.removeAllViews();
        for (int i = 0; i < dots.length; i++) {
            dots[i] = new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226;"));
            dots[i].setTextSize(20);
            dots[i].setBackgroundResource(currentPage==i?
                    R.drawable.image_slider_dot_active_background :
                    R.drawable.image_slider_dot_inactive_background);
            dots[i].setTextColor(Color.TRANSPARENT);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );
            params.setMargins(5, 5, 5, 5);
            dots[i].setLayoutParams(params);
            mHomeBinding.introLayoutDots.addView(dots[i]);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.home_menu,menu);
        return true;
    }

    @Override
    public void onClick(CategoryResponse categoryResponse) {

        Intent intent=new Intent(this, ProductsActivity.class);

        intent.putExtra(getString(R.string.category_response_key), categoryResponse);

        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_NEW_TASK |Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED );

        startActivity(intent);

    }
}
