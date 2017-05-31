package es.cervecitas.food.foodcomposition.ui.fooditem;

import android.content.Context;

import javax.inject.Inject;

import es.cervecitas.food.foodcomposition.R;
import es.cervecitas.food.foodcomposition.app.FoodCompositionApplication;
import es.cervecitas.food.foodcomposition.network.BedcaApi;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.RequestBody;

public class FoodPresenterImpl implements FoodPresenter {

    @Inject
    BedcaApi bedcaApi;

    @Inject
    Context context;

    private FoodView view;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    public FoodPresenterImpl(Context context) {
        ((FoodCompositionApplication) context).getAppComponent().inject(this);
    }

    @Override
    public void setView(FoodView view) {
        this.view = view;
    }

    @Override
    public void getData(int id) {
        view.showLoading();

        compositeDisposable.add(
                bedcaApi
                        .getFoodItemDetail(
                                RequestBody.create(
                                        MediaType.parse("text/xml"),
                                        getHeaders() + getSelection() + getProjection(id) + getOrderBy()))
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Consumer<FoodItemResponse>() {
                            @Override
                            public void accept(@NonNull FoodItemResponse foodItemResponse) throws Exception {
                                view.onDataLoaded(foodItemResponse.getFood().get(0));
                                view.hideLoading();
                            }
                        }));
    }

    @Override
    public void cleanup() {
        compositeDisposable.clear();
        view = null;
    }

    private String getHeaders() {
        return
                "<?xml version=\"1.0\" encoding=\"utf-8\"?><foodquery><type level=\"2\"/>";
    }

    private String getSelection() {
        return "<selection>" +
                "<atribute name=\"" + "c_id" + "\"/>" +
                "<atribute name=\"" + "c_ori_name" + "\"/>" +
                "<atribute name=\"" + context.getString(R.string.f_ori_name) + "\"/>" +
                "<atribute name=\"" + context.getString(R.string.cg_descripcion) + "\"/>" +
                "<atribute name=\"best_location\"/>" +
                "<atribute name=\"v_unit\"/>" +
                "<atribute name=\"" + context.getString(R.string.mu_descripcion) + "\"/>" +
                "</selection>";
    }

    private String getProjection(int id) {
        return
                "<condition>" +
                "<cond1><atribute1 name=\"f_id\"/></cond1><relation type=\"EQUAL\"/><cond3>" + id + "</cond3>" +
                "</condition>" +
                "<condition>" +
                "<cond1><atribute1 name=\"publico\"/></cond1><relation type=\"EQUAL\"/><cond3>1</cond3>" +
                "</condition>";
    }

    private String getOrderBy() {
        return
                "<order ordtype=\"ASC\"><atribute3 name=\"componentgroup_id\"/></order></foodquery>";
    }
}
