package com.example.luism.foodcomposition.ui.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.luism.foodcomposition.app.FoodCompositionApplication;
import com.example.luism.foodcomposition.dagger.AppComponent;

import butterknife.ButterKnife;

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentView());
        ButterKnife.bind(this);

    }

    protected AppComponent getApplicationComponent() {
        return ((FoodCompositionApplication)getApplication()).getAppComponent();
    }

    protected abstract int getContentView();
}
