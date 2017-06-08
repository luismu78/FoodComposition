package es.cervecitas.food.foodcomposition.ui.nutrientes;

import android.content.Context;

import javax.inject.Inject;

import es.cervecitas.food.foodcomposition.app.FoodCompositionApplication;
import es.cervecitas.food.foodcomposition.network.BedcaApi;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.RequestBody;

public class NutrientesPresenterImpl implements NutrientesPresenter {

    @Inject
    BedcaApi bedcaApi;

    private NutrientesView view;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    public NutrientesPresenterImpl(Context context) {
        ((FoodCompositionApplication) context).getAppComponent().inject(this);
    }

    @Override
    public void setView(NutrientesView view) {
        this.view = view;
    }

    @Override
    public void getFilterList() {
        view.showLoading();

        String condition1 =
                "<condition>" +
                        "<cond1><atribute1 name=\"publico\"/></cond1>" +
                        "<relation type=\"EQUAL\"/>" +
                        "<cond3>1</cond3>" +
                        "</condition>";

        String query = getHeaders() + "<foodquery>" + getQueryType3h() + getSelectionFilter() + condition1 + getFilterOrder() + "</foodquery>";

        compositeDisposable.add(bedcaApi
                .getNutrientNames(RequestBody.create(MediaType.parse("text/xml"), query))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<BedcaResponse>() {
                    @Override
                    public void accept(@NonNull BedcaResponse bedcaResponse) throws Exception {
                        view.onFilterListLoaded(bedcaResponse.getFoodResponse());
                        view.hideLoading();
                    }
                }));
    }

    @Override
    public void cleanup() {
        compositeDisposable.clear();
    }

    private String getHeaders() {
        return "<?xml version=\"1.0\" encoding=\"utf-8\"?>";
    }

    private String getQueryType3h() {
        return "<type level=\"3h\"/>";
    }

    private String getSelectionFilter() {
        return "<selection>" +
                "<atribute name=\"cg_id\"/>" +
                "<atribute name=\"c_id\"/>" +
                "<atribute name=\"c_ori_name\"/>" +
//                "<atribute name=\"glos_esp\"/>" +
                "</selection>";
    }

    private String getFilterOrder() {
        return "<order ordtype=\"ASC\">" +
                "<atribute3 name=\"c_ori_name\"/>" +
                "</order>";
    }
}
