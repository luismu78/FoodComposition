package es.cervecitas.food.foodcomposition.ui.foodgroup;

import android.content.Context;
import android.util.Log;

import javax.inject.Inject;

import es.cervecitas.food.foodcomposition.app.FoodCompositionApplication;
import es.cervecitas.food.foodcomposition.network.BedcaApi;
import es.cervecitas.food.foodcomposition.ui.foodgroupdetail.F_ListItems;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.RequestBody;

public class SearchPresenterImpl implements SearchPresenter {

    @Inject
    BedcaApi bedcaApi;

    private SearchView searchView;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    public SearchPresenterImpl(Context context) {
        ((FoodCompositionApplication)context).getAppComponent().inject(this);
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
                .subscribe(new Consumer<F_ListItems>() {
                    @Override
                    public void accept(@NonNull F_ListItems f_listItems) throws Exception {
                        searchView.onSearchDataLoaded(f_listItems.getFoodResponse());
                    }
                }));
    }

    @Override
    public void cleanup() {
        compositeDisposable.clear();
    }
}
