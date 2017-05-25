package es.cervecitas.food.foodcomposition.ui.foodgroupdetail;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import es.cervecitas.food.foodcomposition.R;
import es.cervecitas.food.foodcomposition.app.FoodCompositionApplication;
import es.cervecitas.food.foodcomposition.app.Utils;
import es.cervecitas.food.foodcomposition.ui.foodgroup.FGActivity;
import es.cervecitas.food.foodcomposition.ui.fooditem.FoodItemActivity;
import es.cervecitas.food.foodcomposition.ui.search.SearchAdapter;
import es.cervecitas.food.foodcomposition.ui.search.SearchPresenter;
import es.cervecitas.food.foodcomposition.ui.search.SearchView;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Cancellable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Predicate;

public class FDetailActivity extends AppCompatActivity
        implements FView, SearchView, FAdapter.FAdapterClickListener, SearchAdapter.SearchAdapterClickListener {

    public static final String ARG_ITEM_ID = "item_id";

    private int id = 0;

    @Inject
    FPresenter presenter;

    @Inject
    SearchPresenter searchPresenter;

    @BindView(R.id.rvFoodItems)
    RecyclerView rvFoodItems;

    // Search

    @BindView(R.id.btnSearch)
    ImageView imgSearch;

    @BindView(R.id.etSearch)
    EditText etSearch;

    // Error

    @BindView(R.id.llNoData)
    LinearLayout llNoData;

    @BindView(R.id.imgNoData)
    ImageView imgNoData;

    @BindView(R.id.tvNoData)
    TextView tvNoData;

    @BindView(R.id.subNoData)
    TextView subNoData;

    private Disposable searchDisposable;

    // Observes the search button click
    private Observable<String> createSearchButtonObservable() {
        return Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(@NonNull final ObservableEmitter<String> emiter) throws Exception {
                imgSearch.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        emiter.onNext(etSearch.getText().toString());
                    }
                });

                emiter.setCancellable(new Cancellable() {
                    @Override
                    public void cancel() throws Exception {
                        imgSearch.setOnClickListener(null);
                    }
                });
            }
        });
    }

    // Observes the changes in the search text
    private Observable<String> createSearchTextChangeObservable() {
        Observable<String> textChangeObservable = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(@NonNull final ObservableEmitter<String> emiter) throws Exception {
                final TextWatcher watcher = new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        emiter.onNext(s.toString());
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                };

                etSearch.addTextChangedListener(watcher);

                emiter.setCancellable(new Cancellable() {
                    @Override
                    public void cancel() throws Exception {
                        etSearch.removeTextChangedListener(watcher);
                    }
                });
            }
        });

        return textChangeObservable
                .filter(new Predicate<String>() {
                    @Override
                    public boolean test(@NonNull String s) throws Exception {
                        return s.length() >= 3;
                    }
                })
                .debounce(700, TimeUnit.MILLISECONDS);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_detail);

        ((FoodCompositionApplication) getApplication()).getAppComponent().inject(this);

        ButterKnife.bind(this);

        id = getIntent().getIntExtra(ARG_ITEM_ID, 0);

        rvFoodItems = (RecyclerView) findViewById(R.id.rvFoodItems);
        rvFoodItems.setAdapter(new FAdapter(this, new ArrayList<Food>(), this));
        rvFoodItems.setLayoutManager(new LinearLayoutManager(this));
        rvFoodItems.setHasFixedSize(true);

        presenter.setView(this);
        searchPresenter.setView(this);
    }

    @Override
    protected void onStart() {
        super.onStart();

        Observable searchButtonClickStream = createSearchButtonObservable();
        Observable searchTextObservable = createSearchTextChangeObservable();

        Observable<String> searchObservable = Observable.merge(searchButtonClickStream, searchTextObservable);

        searchDisposable = searchObservable
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(@NonNull String s) throws Exception {
                        searchPresenter.getSearchResults(s);
                    }
                });
    }

    @Override
    public void onResume() {
        super.onResume();

        presenter.getFood(id);

        Utils.hideKeyboard(this, etSearch.getWindowToken());
    }

    @Override
    protected void onStop() {
        super.onStop();

        if (!searchDisposable.isDisposed()) {
            searchDisposable.dispose();
        }

        presenter.cleanup();
    }

    @Override
    public void onDataLoaded(List<Food> listItems) {
        if (listItems.size() == 0) {
            showLoadingError();
            rvFoodItems.setAdapter(new FAdapter(this, listItems, this));
            rvFoodItems.getAdapter().notifyDataSetChanged();
        } else {
            hideLoadingError();
            rvFoodItems.setAdapter(new FAdapter(this, listItems, this));
            rvFoodItems.getAdapter().notifyDataSetChanged();
        }
    }

    @Override
    public void showLoadingError() {
        llNoData.setVisibility(View.VISIBLE);
        tvNoData.setText("Error leyendo del servidor");
        subNoData.setText("Inténtelo mas tarde");
    }

    @Override
    public void hideLoadingError() {
        llNoData.setVisibility(View.GONE);
    }

    @Override
    public void onClearData() {

    }

    @Override
    public void showLoading() {
        Log.d("HOLA", "===== LOADING ====");
    }

    @Override
    public void hideLoading() {
        Log.d("HOLA", "===== LOADED ====");
    }

    @Override
    public void onListItemClicked(int id) {
        Intent intent = new Intent(this, FoodItemActivity.class);
        intent.putExtra(FoodItemActivity.ARG_FOOD_ID, id);
        startActivity(intent);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == android.R.id.home) {
            navigateUpTo(new Intent(this, FGActivity.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onSearchDataLoaded(List<Food> foods) {
        if (foods.size() == 0) {
            showSearchError();
            rvFoodItems.setAdapter(new SearchAdapter(this, new ArrayList<Food>(), this));
            rvFoodItems.getAdapter().notifyDataSetChanged();
        } else {
            hideSearchError();
            rvFoodItems.setAdapter(new SearchAdapter(this, foods, this));
            rvFoodItems.getAdapter().notifyDataSetChanged();
        }
    }

    @Override
    public void showSearchError() {
        llNoData.setVisibility(View.VISIBLE);
        tvNoData.setText("No hay resultados");
        subNoData.setText("");
    }

    @Override
    public void hideSearchError() {
        llNoData.setVisibility(View.GONE);
    }

    @Override
    public void onSearchItemClicked(int id) {
        Intent intent = new Intent(this, FoodItemActivity.class);
        intent.putExtra(FoodItemActivity.ARG_FOOD_ID, id);
        startActivity(intent);
    }
}
