package es.cervecitas.food.foodcomposition.ui.search;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import butterknife.BindView;
import es.cervecitas.food.foodcomposition.R;
import es.cervecitas.food.foodcomposition.ui.foodgroupdetail.Food;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Cancellable;
import io.reactivex.functions.Predicate;

public class SearchActivity extends AppCompatActivity implements SearchView, SearchAdapter.SearchAdapterClickListener {

//    @BindView(R.id.tvSearch)
//    EditText tvSearch;

    @BindView(R.id.rvAlimentos)
    RecyclerView rvAlimentos;

    @BindView(R.id.llNoData)
    LinearLayout llNoData;

    @BindView(R.id.tvNoData)
    TextView tvNoData;

    @Inject
    SearchPresenter presenter;

    private Disposable searchDisposable;

//    // Observes the search button click
//    private Observable<String> createSearchButtonObservable() {
//        return Observable.create(new ObservableOnSubscribe<String>() {
//            @Override
//            public void subscribe(@NonNull final ObservableEmitter<String> emiter) throws Exception {
//                imgSearch.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        emiter.onNext(etSearch.getText().toString());
//                    }
//                });
//
//                emiter.setCancellable(new Cancellable() {
//                    @Override
//                    public void cancel() throws Exception {
//                        imgSearch.setOnClickListener(null);
//                    }
//                });
//            }
//        });
//    }
//
//    // Observes the changes in the search text
//    private Observable<String> createSearchTextChangeObservable() {
//        Observable<String> textChangeObservable = Observable.create(new ObservableOnSubscribe<String>() {
//            @Override
//            public void subscribe(@NonNull final ObservableEmitter<String> emiter) throws Exception {
//
//                // When an observer subscribes create a TextWatcher
//                final TextWatcher watcher = new TextWatcher() {
//                    @Override
//                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//                    }
//
//                    @Override
//                    public void onTextChanged(CharSequence s, int start, int before, int count) {
//                        emiter.onNext(s.toString());
//                    }
//
//                    @Override
//                    public void afterTextChanged(Editable s) {
//
//                    }
//                };
//
//                etSearch.addTextChangedListener(watcher);
//
//                emiter.setCancellable(new Cancellable() {
//                    @Override
//                    public void cancel() throws Exception {
//                        etSearch.removeTextChangedListener(watcher);
//                    }
//                });
//            }
//        });
//
//        return textChangeObservable
//                .filter(new Predicate<String>() {
//                    @Override
//                    public boolean test(@NonNull String s) throws Exception {
//                        return s.length() >= 3;
//                    }
//                })
//                .debounce(700, TimeUnit.MILLISECONDS);
//    }

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
