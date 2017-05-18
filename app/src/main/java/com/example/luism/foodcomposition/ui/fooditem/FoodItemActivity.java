package com.example.luism.foodcomposition.ui.fooditem;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.luism.foodcomposition.R;
import com.example.luism.foodcomposition.app.FoodCompositionApplication;

import javax.inject.Inject;

public class FoodItemActivity extends AppCompatActivity {

    public static final String ARG_FOOD_ID = "food_id";

    @Inject
    FoodPresenter presenter;

    private int id = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_item);

        ((FoodCompositionApplication) getApplication()).getAppComponent().inject(this);

        id = getIntent().getIntExtra(ARG_FOOD_ID, 0);

        Log.d("HOLA", "id: " + id);
    }

    @Override
    protected void onResume() {
        super.onResume();

        presenter.getData(id);
    }
}
