package es.cervecitas.food.foodcomposition.ui.foodgroupdetail;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import es.cervecitas.food.foodcomposition.app.FoodCompositionApplication;
import es.cervecitas.food.foodcomposition.network.BedcaApi;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.RequestBody;

import es.cervecitas.food.foodcomposition.pojo.Food;

public class FPresenterImpl implements FPresenter {

    @Inject
    BedcaApi bedcaApi;

    private FView view;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    public FPresenterImpl(Context context) {
        ((FoodCompositionApplication) context).getAppComponent().inject(this);
    }

    @Override
    public void setView(FView view) {
        this.view = view;
    }

    @Override
    public void getFood(int id) {
        view.showLoading();

        String condition1 =
                "<condition>" +
                "<cond1><atribute1 name=\"foodgroup_id\"/></cond1>" +
                "<relation type=\"EQUAL\"/>" +
                "<cond3>" + id + "</cond3>" +
                "</condition>";
        String condition2 =
                "<condition>" +
                "<cond1><atribute1 name=\"f_origen\"/></cond1>" +
                "<relation type=\"EQUAL\"/>" +
                "<cond3>BEDCA</cond3>" +
                "</condition>";

        String query = getHeaders() + "<foodquery>" + getQueryType() + getSelection() + condition1 + condition2 + getOrder() + "</foodquery>";

        compositeDisposable.add(bedcaApi
                .getFoodGroupDetail(RequestBody.create(MediaType.parse("text/xml"), query))
                .subscribeOn(Schedulers.io())
                .onErrorReturn(new Function<Throwable, F_ListItems>() {
                    @Override
                    public F_ListItems apply(@NonNull Throwable throwable) throws Exception {
                        return new F_ListItems();
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<F_ListItems>() {
                    @Override
                    public void accept(@NonNull F_ListItems f_listItems) throws Exception {
                        List<Food> foods = new ArrayList<>();

                        if (f_listItems.getFoodResponse() != null) {
                            foods = f_listItems.getFoodResponse();
                        }

                        view.onDataLoaded(foods);
                        view.hideLoading();
                    }
                }));

    }

    private String getHeaders() {
        return "<?xml version=\"1.0\" encoding=\"utf-8\"?>";
    }

    private String getQueryType() {
        return "<type level=\"1\"/>";
    }

    private String getSelection() {
        return "<selection>" +
                "<atribute name=\"f_id\"/>" +
                "<atribute name=\"f_ori_name\"/>" +
                "<atribute name=\"f_origen\"/>" +
                "</selection>";
    }

    private String getOrder() {
        return "<order ordtype=\"ASC\">" +
                "<atribute3 name=\"f_ori_name\"/>" +
                "</order>";
    }

    @Override
    public void cleanup() {
        compositeDisposable.clear();
        view = null;
    }
}
