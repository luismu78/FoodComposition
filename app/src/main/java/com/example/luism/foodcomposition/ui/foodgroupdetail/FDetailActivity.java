package com.example.luism.foodcomposition.ui.foodgroupdetail;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.example.luism.foodcomposition.R;
import com.example.luism.foodcomposition.ui.foodgroup.FGActivity;

public class FDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_detail);

        Toolbar toolbar = (Toolbar) findViewById(R.id.detail_toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        if (savedInstanceState == null) {
            // Create the detail fragment and add it to the activity
            Bundle args = new Bundle();
            args.putString(FDetailFragment.ARG_ITEM_ID, getIntent().getStringExtra(FDetailFragment.ARG_ITEM_ID));
            FDetailFragment fragment = new FDetailFragment();
            fragment.setArguments(args);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.item_detail_container, fragment)
                    .commit();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == android.R.id.home) {
            navigateUpTo(new Intent(this, FGActivity.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
