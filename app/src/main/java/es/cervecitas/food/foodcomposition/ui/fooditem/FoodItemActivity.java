package es.cervecitas.food.foodcomposition.ui.fooditem;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import es.cervecitas.food.foodcomposition.R;
import es.cervecitas.food.foodcomposition.app.FoodCompositionApplication;

public class FoodItemActivity extends AppCompatActivity implements FoodView {

    public static final String ARG_FOOD_ID = "food_id";

    @Inject
    FoodPresenter presenter;

//    @BindView(R.id.tvName)
//    TextView tvName;

    @BindView(R.id.rvItemList)
    RecyclerView rvItemList;

    @BindView(R.id.activityFoodCollapsingToolbarLayout)
    CollapsingToolbarLayout appBarLayout;

    private int id = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_item);

        ((FoodCompositionApplication) getApplication()).getAppComponent().inject(this);

        ButterKnife.bind(this);

        id = getIntent().getIntExtra(ARG_FOOD_ID, 0);

        rvItemList.setAdapter(new FoodAdapter(new ArrayList<FoodValue>(), this));
        rvItemList.setLayoutManager(new LinearLayoutManager(this));
        rvItemList.setHasFixedSize(true);
    }

    @Override
    protected void onStart() {
        super.onStart();

        presenter.setView(this);
    }

    @Override
    protected void onResume() {
        super.onResume();

        presenter.getData(id);
    }

    @Override
    protected void onStop() {
        super.onStop();

        presenter.cleanup();
    }

    @Override
    public void onDataLoaded(Food food) {
        rvItemList.setAdapter(new FoodAdapter(food.getFoodvalue(), this));
        rvItemList.getAdapter().notifyDataSetChanged();
        if (appBarLayout != null) {
            appBarLayout.setTitle(food.getF_ori_name());
        }
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

}
