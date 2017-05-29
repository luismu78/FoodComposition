package es.cervecitas.food.foodcomposition.ui.search;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import es.cervecitas.food.foodcomposition.R;
import es.cervecitas.food.foodcomposition.app.FoodCompositionApplication;
import es.cervecitas.food.foodcomposition.pojo.Food;
import es.cervecitas.food.foodcomposition.ui.fooditem.FoodItemActivity;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Cancellable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Predicate;

public class SearchActivity extends AppCompatActivity implements SearchView, SearchAdapter.SearchAdapterClickListener {

    // Search

    @BindView(R.id.searchLayout)
    RelativeLayout rlSearch;

    @BindView(R.id.etSearch)
    TextView etSearch;

    @BindView(R.id.imgSearch)
    ImageView imgSearch;

    // Results

    @BindView(R.id.rvAlimentos)
    RecyclerView rvAlimentos;

    // No data

    @BindView(R.id.llNoData)
    LinearLayout llNoData;

    @BindView(R.id.tvNoData)
    TextView tvNoData;

    @Inject
    SearchPresenter presenter;

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
        setContentView(R.layout.activity_alimentos);

        ((FoodCompositionApplication) getApplication()).getAppComponent().inject(this);

        ButterKnife.bind(this);

        rvAlimentos.setAdapter(new SearchAdapter(new ArrayList<Food>(), this));
        rvAlimentos.setLayoutManager(new LinearLayoutManager(this));
        rvAlimentos.setHasFixedSize(false);


    }

    @Override
    protected void onStart() {
        super.onStart();

        presenter.setView(this);

        Observable<String> searchButtonClickStream = createSearchButtonObservable();
        Observable<String> searchTextObservable = createSearchTextChangeObservable();

        Observable<String> searchObservable = Observable.merge(searchButtonClickStream, searchTextObservable);

        searchDisposable = searchObservable
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(@NonNull String s) throws Exception {
                        presenter.getSearchResults(s);
                    }
                });
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
    public void onSearchDataLoaded(List<Food> foods) {
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) rlSearch.getLayoutParams();

        if (foods.size() == 0) {
            showSearchError();
            rvAlimentos.setAdapter(new SearchAdapter(new ArrayList<Food>(), this));
            rvAlimentos.getAdapter().notifyDataSetChanged();
        } else {
            hideSearchError();

//            layoutParams.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);
            layoutParams.removeRule(RelativeLayout.CENTER_IN_PARENT);
            rlSearch.setLayoutParams(layoutParams);

            rvAlimentos.setAdapter(new SearchAdapter(foods, this));
            rvAlimentos.getAdapter().notifyDataSetChanged();
        }
    }

    @Override
    public void showSearchError() {
        llNoData.setVisibility(View.VISIBLE);
        tvNoData.setText("Sin resultados");
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
