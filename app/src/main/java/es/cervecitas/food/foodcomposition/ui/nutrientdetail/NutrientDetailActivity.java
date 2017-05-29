package es.cervecitas.food.foodcomposition.ui.nutrientdetail;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import es.cervecitas.food.foodcomposition.R;
import es.cervecitas.food.foodcomposition.app.FoodCompositionApplication;

public class NutrientDetailActivity extends Activity
        implements NutrienteDetailView {

    public static final String ARG_NUTRIENT_ID = "nutrientId";

    private int id = 0;

    @Inject
    NutrienteDetailPresenter presenter;

    @BindView(R.id.rvNutrientes)
    RecyclerView rvNutrientes;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nutriente_list);

        ((FoodCompositionApplication) getApplication()).getAppComponent().inject(this);

        ButterKnife.bind(this);

        id = getIntent().getIntExtra(ARG_NUTRIENT_ID, 0);

        Log.d("HOLA", getClass().getSimpleName() + " - id: " + id);
    }

    @Override
    protected void onStart() {
        super.onStart();

        presenter.setView(this);
    }

    @Override
    protected void onResume() {
        super.onResume();

        presenter.getFoodByNutrient(id);
    }

    @Override
    protected void onStop() {
        super.onStop();

        presenter.cleanUp();
    }

    @Override
    public void onDataLoaded(List<Food> foodList) {
        Log.d("HOLA", "nutrientDetailResponse.getFoodResponse().size(): " + foodList.size());

        for (Food food : foodList) {
            Log.d("HOLA", food.getF_id() + " " + food.getF_ori_name() + " - " + food.getC_ori_name() + ": " + food.getBest_location() + " " + food.getV_unit());
        }
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showLoadingError() {

    }

    @Override
    public void hideLoadingError() {

    }
}
