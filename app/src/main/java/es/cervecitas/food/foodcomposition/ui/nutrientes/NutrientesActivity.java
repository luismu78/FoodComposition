package es.cervecitas.food.foodcomposition.ui.nutrientes;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import es.cervecitas.food.foodcomposition.R;
import es.cervecitas.food.foodcomposition.app.FoodCompositionApplication;
import es.cervecitas.food.foodcomposition.ui.nutrientdetail.NutrientDetailActivity;

public class NutrientesActivity extends Activity implements NutrientesView, NutrientesAdapter.ClickListener {

    @Inject
    NutrientesPresenter nutrientsPresenter;

    @BindView(R.id.rvNutrientes)
    RecyclerView rvNutrientes;

    // Error

    @BindView(R.id.llNoData)
    LinearLayout llNoData;

    @BindView(R.id.imgNoData)
    ImageView imgNoData;

    @BindView(R.id.tvNoData)
    TextView tvNoData;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nutriente_list);

        ((FoodCompositionApplication) getApplication()).getAppComponent().inject(this);

        ButterKnife.bind(this);

        rvNutrientes.setAdapter(new NutrientesAdapter(new ArrayList<Nutrient>(), this));
        rvNutrientes.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected void onStart() {
        super.onStart();

        nutrientsPresenter.setView(this);
    }

    @Override
    protected void onResume() {
        super.onResume();

        nutrientsPresenter.getFilterList();
    }

    @Override
    protected void onStop() {
        super.onStop();

        nutrientsPresenter.cleanup();
    }

    @Override
    public void onFilterListLoaded(List<Nutrient> nutrientList) {
        if (nutrientList.size() == 0) {
            showLoadingError();
            rvNutrientes.setAdapter(new NutrientesAdapter(new ArrayList<Nutrient>(), this));
            rvNutrientes.getAdapter().notifyDataSetChanged();
        } else {
            hideLoadingError();
            rvNutrientes.setAdapter(new NutrientesAdapter(nutrientList, this));
            rvNutrientes.getAdapter().notifyDataSetChanged();
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

    @Override
    public void onListItemClicked(int id) {
        Intent intent = new Intent(this, NutrientDetailActivity.class);
        intent.putExtra(NutrientDetailActivity.ARG_NUTRIENT_ID, id);
        startActivity(intent);
    }
}
