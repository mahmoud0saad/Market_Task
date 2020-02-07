package com.mahmoudsaad.market.ui.activity.detail;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.mahmoudsaad.market.R;
import com.mahmoudsaad.market.data.entites.ProductsItem;
import com.mahmoudsaad.market.data.viewmodels.detail.DetailViewModel;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

public class DetailActivity extends AppCompatActivity {
    private static final String TAG = "DetailActivity";

    TextView productNameTextView;

    private DetailViewModel mDetailViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        productNameTextView=findViewById(R.id.name_product_detail_text_view);

        mDetailViewModel= ViewModelProviders.of(this).get(DetailViewModel.class);

        ProductsItem productsItem=mDetailViewModel.getProductsItem(getIntent());

        if (productsItem!=null)
            productNameTextView.setText(productsItem.getName());

        intiToolbar();

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

    private void intiToolbar() {
        setSupportActionBar(findViewById(R.id.product_detail_toolbar));

        ActionBar actionBar = getSupportActionBar();
        if (actionBar!=null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

    }
}
