package com.example.luism.foodcomposition.ui.foodgroup;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.example.luism.foodcomposition.R;

public class FGActivity extends AppCompatActivity implements FGView {

    private boolean twoPane;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_list);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(getTitle());

        if (findViewById(R.id.item_detail_container) != null) {
            twoPane = true;
        }
    }

    @Override
    public void onDataLoaded(FG_ListItems listItems) {

    }

    @Override
    public void onListItemClicked(int id) {

    }

    @Override
    public void onClearData() {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }
}
