package com.mahmoudsaad.market.ui.activity.splash;

import android.content.Intent;
import android.os.Bundle;

import com.mahmoudsaad.market.ui.activity.base.BaseActivity;
import com.mahmoudsaad.market.ui.activity.intro.IntroductionActivity;

public class SplashActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        startActivity(new Intent(this, IntroductionActivity.class));

        finish();
    }
}
