package com.mahmoudsaad.market.ui.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

public class IntroSliderAdapter extends PagerAdapter {

    int []mLayout;

    public IntroSliderAdapter(int [] layouts){
        mLayout=layouts;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        View rootView= LayoutInflater.from(container.getContext()).inflate(mLayout[position],container,false);

        container.addView(rootView);

        return rootView;

    }

    @Override
    public int getCount() {
        return mLayout.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view==object;
    }
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        View view = (View) object;
        container.removeView(view);
    }
}
