package es.cervecitas.food.foodcomposition.ui.foodgroup;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import es.cervecitas.food.foodcomposition.R;
import es.cervecitas.food.foodcomposition.app.FoodCompositionApplication;
import es.cervecitas.food.foodcomposition.ui.foodgroupdetail.FDetailActivity;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Cancellable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;

public class FGActivity extends AppCompatActivity implements FGView, FGAdapter.FGAdapterClickListener {

    @Inject
    FGPresenter presenter;

    @BindView(R.id.rvItemList)
    RecyclerView rvItemList;

    @BindView(R.id.btnSearch)
    ImageView imgSearch;

    @BindView(R.id.etSearch)
    EditText etSearch;

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
                        return s.length() >= 2;
                    }
                })
                .debounce(1000, TimeUnit.MILLISECONDS);
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
    }

    @Override
    protected void onStart() {
        super.onStart();

        Observable<String> searchButtonClickStream = createSearchButtonObservable();
        Observable<String> searchTextObservable = createSearchTextChangeObservable();

        Observable<String> searchObservable = Observable.merge(searchButtonClickStream, searchTextObservable);

        searchDisposable = searchObservable
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(new Consumer<String>() {
                    @Override
                    public void accept(@NonNull String s) throws Exception {
                        showLoading();
                    }
                })
                .observeOn(Schedulers.io())
                .map(new Function<String, List<String>>() {
                    @Override
                    public List<String> apply(@NonNull String s) throws Exception {
                        return doTheSearch(s);
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<String>>() {
                    @Override
                    public void accept(@NonNull List<String> result) throws Exception {
                        hideLoading();
                        showResult(result);
                    }
                });
    }



    private List<String> doTheSearch(String s) {
        Log.d("HOLA", "doTheSearch: " + s);

        return new ArrayList<>();
    }

    private void showResult(List<String> result) {
        Log.d("HOLA", "showResult");
    }

    @Override
    protected void onResume() {
        super.onResume();

        presenter.getFoodGroups();
    }

    @Override
    protected void onStop() {
        super.onStop();

        if (!searchDisposable.isDisposed()) {
            searchDisposable.dispose();
        }

        presenter.cleanup();
    }

//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//
//    }

    @Override
    public void onDataLoaded(List<Food> listItems) {
        rvItemList.setAdapter(new FGAdapter(this, listItems, this));
        rvItemList.getAdapter().notifyDataSetChanged();
    }

    @Override
    public void onListItemClicked(int id) {
            Intent intent = new Intent(this, FDetailActivity.class);
            intent.putExtra(FDetailActivity.ARG_ITEM_ID, id);
            startActivity(intent);
    }

    @Override
    public void onClearData() {

    }

    @Override
    public void showLoading() {
//        swipeRefreshLayout.setRefreshing(true);
    }

    @Override
    public void hideLoading() {
//        swipeRefreshLayout.setRefreshing(false);
    }

}
