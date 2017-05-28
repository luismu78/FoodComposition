package es.cervecitas.food.foodcomposition.ui.nutrientdetail;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import es.cervecitas.food.foodcomposition.R;

public class NutrientDetailActivity extends Activity {

    private int id = 0;
    public static final String ARG_NUTRIENT_ID = "nutrientId";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nutriente_list);

        id = getIntent().getIntExtra(ARG_NUTRIENT_ID, 0);

        Log.d("HOLA", getClass().getSimpleName() + " - id: " + id);

    }
}
