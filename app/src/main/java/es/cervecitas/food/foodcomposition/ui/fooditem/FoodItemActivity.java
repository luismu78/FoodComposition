package es.cervecitas.food.foodcomposition.ui.fooditem;

import android.os.Bundle;
import android.support.annotation.Nullable;
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

    private int id = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_item);

        ((FoodCompositionApplication) getApplication()).getAppComponent().inject(this);

        ButterKnife.bind(this);

        id = getIntent().getIntExtra(ARG_FOOD_ID, 0);

        rvItemList.setAdapter(new FoodAdapter(new ArrayList<FoodValue>(), "", this));
        rvItemList.setLayoutManager(new LinearLayoutManager(this));
        rvItemList.setHasFixedSize(true);

        presenter.setView(this);
    }

    @Override
    protected void onResume() {
        super.onResume();

        presenter.getData(id);
    }

    @Override
    public void onDataLoaded(Food food) {
        rvItemList.setAdapter(new FoodAdapter(food.getFoodvalue(), food.getF_ori_name(), this));
        rvItemList.getAdapter().notifyDataSetChanged();
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

}
