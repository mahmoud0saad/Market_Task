package com.mahmoudsaad.market.ui.activity.intro;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mahmoudsaad.market.R;
import com.mahmoudsaad.market.data.viewmodels.intro.IntroViewModel;
import com.mahmoudsaad.market.databinding.ActivityIntroductionBinding;
import com.mahmoudsaad.market.ui.activity.base.BaseActivity;
import com.mahmoudsaad.market.ui.activity.home.HomeActivity;
import com.mahmoudsaad.market.ui.adapters.IntroSliderAdapter;

public class IntroductionActivity extends BaseActivity {

    private ActivityIntroductionBinding mMainBinding;
    private IntroViewModel mIntroViewModel;

    int [] layouts={
            R.layout.slide_first,
            R.layout.slide_second,
            R.layout.slide_third
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mMainBinding= DataBindingUtil.setContentView(this,R.layout.activity_introduction);

        mIntroViewModel = ViewModelProviders.of(this).get(IntroViewModel.class);


        intiIntroductionSlider();

    }

    private void intiIntroductionSlider() {


        IntroSliderAdapter introSliderAdapter=new IntroSliderAdapter(layouts);

        mMainBinding.sliderIntroductionViewPager.setAdapter(introSliderAdapter);

        addBottomDots(0);

        mMainBinding.sliderIntroductionViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                addBottomDots(position);

                if (position==layouts.length-1){
                    mMainBinding.introGotItButton.setVisibility(View.VISIBLE);
                }else {
                    mMainBinding.introGotItButton.setVisibility(View.GONE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        mMainBinding.introGotItButton.setOnClickListener(v->{
            Intent intent=new Intent(this,HomeActivity.class);

            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_NEW_TASK |Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED );

            startActivity(intent);

            finish();
        });
    }

    private void addBottomDots(int currentPage) {
        TextView[] dots = new TextView[layouts.length];

        mMainBinding.introLayoutDots.removeAllViews();
        for (int i = 0; i < dots.length; i++) {
            dots[i] = new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226;"));
            dots[i].setTextSize(25);
            dots[i].setBackgroundResource(currentPage==i?
                    R.drawable.intro_slider_dot_active_background :
                    R.drawable.intro_slider_dot_inactive_background);
            dots[i].setTextColor(Color.TRANSPARENT);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );
            params.setMargins(5, 5, 5, 5);
            dots[i].setLayoutParams(params);
            mMainBinding.introLayoutDots.addView(dots[i]);
        }



    }
}
