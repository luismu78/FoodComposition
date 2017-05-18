package com.example.luism.foodcomposition.ui.fooditem;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.luism.foodcomposition.R;

public class FoodItemActivity extends AppCompatActivity {

    public static final String ARG_FOOD_ID = "food_id";

    private int id = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_item);

        id = getIntent().getIntExtra(ARG_FOOD_ID, 0);

        Log.d("HOLA", "id: " + id);
    }
}
