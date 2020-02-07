package com.mahmoudsaad.market.ui.adapters;

import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

public class ImageSliderAdapter extends PagerAdapter {

    int []mResourseImage;

    public ImageSliderAdapter(int [] resourseImage){
        mResourseImage=resourseImage;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        ImageView imageView=new ImageView(container.getContext());

        imageView.setImageResource(mResourseImage[position]);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);

        container.addView(imageView);

        return imageView;

    }

    @Override
    public int getCount() {
        return mResourseImage.length;
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
