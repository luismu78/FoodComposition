package es.cervecitas.food.foodcomposition.ui.foodgroup;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
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
import es.cervecitas.food.foodcomposition.ui.foodgroupdetail.FDetailActivity;
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

public class FGActivity extends AppCompatActivity
        implements FGView, SearchView, FGAdapter.FGAdapterClickListener, SearchAdapter.SearchAdapterClickListener {

    @Inject
    FGPresenter presenter;

    @Inject
    SearchPresenter searchPresenter;

    @BindView(R.id.rvItemList)
    RecyclerView rvItemList;

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

                // When an observer subscribes create a TextWatcher
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
        setContentView(R.layout.activity_group_list);

        ((FoodCompositionApplication) getApplication()).getAppComponent().inject(this);

        ButterKnife.bind(this);

        rvItemList.setAdapter(new FGAdapter(this, new ArrayList<Food>(), this));
        rvItemList.setLayoutManager(new LinearLayoutManager(this));
        rvItemList.setHasFixedSize(true);

        presenter.setView(this);
        searchPresenter.setView(this);
    }

    @Override
    protected void onStart() {
        super.onStart();

        Observable<String> searchButtonClickStream = createSearchButtonObservable();
        Observable<String> searchTextObservable = createSearchTextChangeObservable();

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
    protected void onResume() {
        super.onResume();

        presenter.getFoodGroups();

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
            rvItemList.setAdapter(new FGAdapter(this, new ArrayList<Food>(), this));
            rvItemList.getAdapter().notifyDataSetChanged();
        } else {
            hideLoadingError();
            rvItemList.setAdapter(new FGAdapter(this, listItems, this));
            rvItemList.getAdapter().notifyDataSetChanged();
        }

    }

    @Override
    public void onListItemClicked(int id) {
            Intent intent = new Intent(this, FDetailActivity.class);
            intent.putExtra(FDetailActivity.ARG_ITEM_ID, id);
            startActivity(intent);
    }

    @Override
    public void showLoading() {
//        swipeRefreshLayout.setRefreshing(true);
    }

    @Override
    public void hideLoading() {
//        swipeRefreshLayout.setRefreshing(false);
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
    public void onSearchDataLoaded(List<es.cervecitas.food.foodcomposition.ui.foodgroupdetail.Food> foods) {
        if (foods.size() == 0) {
            showSearchError();
            rvItemList.setAdapter(new SearchAdapter(this, new ArrayList<es.cervecitas.food.foodcomposition.ui.foodgroupdetail.Food>(), this));
            rvItemList.getAdapter().notifyDataSetChanged();
        } else {
            hideSearchError();
            rvItemList.setAdapter(new SearchAdapter(this, foods, this));
            rvItemList.getAdapter().notifyDataSetChanged();
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
    public void onSearchItemClicked(final int id) {
        Intent intent = new Intent(this, FoodItemActivity.class);
        intent.putExtra(FoodItemActivity.ARG_FOOD_ID, id);
        startActivity(intent);
    }
}
