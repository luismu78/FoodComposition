package es.cervecitas.food.foodcomposition.ui.search;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import es.cervecitas.food.foodcomposition.R;
import es.cervecitas.food.foodcomposition.ui.foodgroupdetail.Food;
import io.reactivex.disposables.Disposable;

public class SearchActivity extends AppCompatActivity implements SearchView, SearchAdapter.SearchAdapterClickListener {

    @BindView(R.id.tvSearch)
    EditText tvSearch;

    @BindView(R.id.rvAlimentos)
    RecyclerView rvAlimentos;

    @BindView(R.id.llNoData)
    LinearLayout llNoData;

    @BindView(R.id.tvNoData)
    TextView tvNoData;

    @Inject
    SearchPresenter presenter;

    private Disposable searchDisposable;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alimentos);


    }

    @Override
    public void onSearchDataLoaded(List<Food> foods) {

    }

    @Override
    public void showSearchError() {

    }

    @Override
    public void hideSearchError() {

    }

    @Override
    public void onSearchItemClicked(int id) {

    }
}
