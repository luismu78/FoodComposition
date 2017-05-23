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
import es.cervecitas.food.foodcomposition.network.BedcaApi;
import es.cervecitas.food.foodcomposition.ui.foodgroupdetail.FDetailActivity;
import es.cervecitas.food.foodcomposition.ui.foodgroupdetail.F_ListItems;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Cancellable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.RequestBody;

public class FGActivity extends AppCompatActivity implements FGView, SearchView, FGAdapter.FGAdapterClickListener {

    @Inject
    FGPresenter presenter;

    @Inject
    SearchPresenter searchPresenter;

    @Inject
    BedcaApi bedcaApi;

    @BindView(R.id.rvItemList)
    RecyclerView rvItemList;

    @BindView(R.id.btnSearch)
    ImageView imgSearch;

    @BindView(R.id.etSearch)
    EditText etSearch;

    private Disposable searchDisposable;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

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
//        searchDisposable = searchObservable
//                .observeOn(AndroidSchedulers.mainThread())
//                .doOnNext(new Consumer<String>() {
//                    @Override
//                    public void accept(@NonNull String s) throws Exception {
//                        showLoading();
//                    }
//                })
//                .observeOn(Schedulers.io())
//                .map(new Function<String, List<String>>() {
//                    @Override
//                    public List<String> apply(@NonNull String s) throws Exception {
//                        return doTheSearch(s);
//                    }
//                })
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Consumer<List<String>>() {
//                    @Override
//                    public void accept(@NonNull List<String> result) throws Exception {
//                        hideLoading();
//                        showResult(result);
//                    }
//                });
    }

//    private List<String> doTheSearch(String s) {
//        Log.d("HOLA", "doTheSearch: " + s);
//
//        String QUERY = "<?xml version=\"1.0\" encoding=\"utf-8\"?><foodquery><type level=\"1\"/><selection><atribute name=\"f_id\"/><atribute name=\"f_ori_name\"/><atribute name=\"langual\"/><atribute name=\"f_eng_name\"/><atribute name=\"f_origen\"/></selection><condition><cond1><atribute1 name=\"f_ori_name\"/></cond1><relation type=\"LIKE\"/><cond3>" + s + "</cond3></condition><condition><cond1><atribute1 name=\"f_origen\"/></cond1><relation type=\"EQUAL\"/><cond3>BEDCA</cond3></condition><order ordtype=\"ASC\"><atribute3 name=\"f_eng_name\"/></order></foodquery>";
//
//        compositeDisposable.add(bedcaApi
//                .getSearchResults(RequestBody.create(MediaType.parse("text/xml"), QUERY))
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Consumer<F_ListItems>() {
//                    @Override
//                    public void accept(@NonNull F_ListItems f_listItems) throws Exception {
//                        Log.d("HOLA", "Query Result: ");
//
//                        for (es.cervecitas.food.foodcomposition.ui.foodgroupdetail.Food food : f_listItems.getFoodResponse()) {
//                            Log.d("HOLA", food.getF_ori_name());
//                        }
//                    }
//                }));
//
//        return new ArrayList<>();
//    }

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

    @Override
    public void onSearchDataLoaded(List<es.cervecitas.food.foodcomposition.ui.foodgroupdetail.Food> foods) {
        for (es.cervecitas.food.foodcomposition.ui.foodgroupdetail.Food food : foods) {
            Log.d("HOLA", food.getF_ori_name());
        }
    }
}
