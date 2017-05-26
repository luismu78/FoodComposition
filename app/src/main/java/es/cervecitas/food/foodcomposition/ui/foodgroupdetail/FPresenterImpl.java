package es.cervecitas.food.foodcomposition.ui.foodgroupdetail;

import android.content.Context;

import es.cervecitas.food.foodcomposition.app.FoodCompositionApplication;
import es.cervecitas.food.foodcomposition.network.BedcaApi;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.RequestBody;

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

        bedcaApi
                .getFoodGroupDetail(RequestBody.create(MediaType.parse("text/xml"), query))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<F_ListItems>() {
                    @Override
                    public void accept(@NonNull F_ListItems f_listItems) throws Exception {
                        view.onDataLoaded(f_listItems.getFoodResponse());
                        view.hideLoading();
                    }
                });

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
