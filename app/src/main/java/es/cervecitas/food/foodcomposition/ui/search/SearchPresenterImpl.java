package es.cervecitas.food.foodcomposition.ui.search;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import es.cervecitas.food.foodcomposition.app.FoodCompositionApplication;
import es.cervecitas.food.foodcomposition.network.BedcaApi;
import es.cervecitas.food.foodcomposition.ui.foodgroupdetail.F_ListItems;
import es.cervecitas.food.foodcomposition.ui.foodgroupdetail.Food;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.RequestBody;

public class SearchPresenterImpl implements SearchPresenter {

    @Inject
    BedcaApi bedcaApi;

    private SearchView searchView;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    public SearchPresenterImpl(Context context) {
        ((FoodCompositionApplication) context).getAppComponent().inject(this);
    }


    @Override
    public void setView(SearchView view) {
        searchView = view;
    }

    @Override
    public void getSearchResults(String s) {

        String QUERY = "<?xml version=\"1.0\" encoding=\"utf-8\"?><foodquery><type level=\"1\"/><selection><atribute name=\"f_id\"/><atribute name=\"f_ori_name\"/><atribute name=\"langual\"/><atribute name=\"f_eng_name\"/><atribute name=\"f_origen\"/></selection><condition><cond1><atribute1 name=\"f_ori_name\"/></cond1><relation type=\"LIKE\"/><cond3>" + s + "</cond3></condition><condition><cond1><atribute1 name=\"f_origen\"/></cond1><relation type=\"EQUAL\"/><cond3>BEDCA</cond3></condition><order ordtype=\"ASC\"><atribute3 name=\"f_eng_name\"/></order></foodquery>";

        compositeDisposable.add(bedcaApi
                .getSearchResults(RequestBody.create(MediaType.parse("text/xml"), QUERY))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .onErrorReturn(new Function<Throwable, F_ListItems>() {
                    @Override
                    public F_ListItems apply(@NonNull Throwable throwable) throws Exception {
                        return new F_ListItems();
                    }
                })
                .subscribe(new Consumer<F_ListItems>() {
                    @Override
                    public void accept(@NonNull F_ListItems f_listItems) throws Exception {
                        List<Food> foodList = new ArrayList<>();

                        if (f_listItems.getFoodResponse() != null) {
                            foodList = f_listItems.getFoodResponse();
                        }

                        searchView.onSearchDataLoaded(foodList);
                    }
                }));
    }

    @Override
    public void cleanup() {
        compositeDisposable.clear();
        searchView = null;
    }
}
