package es.cervecitas.food.foodcomposition.ui.nutrientdetail;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import es.cervecitas.food.foodcomposition.app.FoodCompositionApplication;
import es.cervecitas.food.foodcomposition.network.BedcaApi;
import es.cervecitas.food.foodcomposition.pojo.Food;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.RequestBody;

public class NutrienteDetailPresenterImpl implements NutrienteDetailPresenter {

    @Inject
    BedcaApi bedcaApi;

    private NutrienteDetailView view;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    public NutrienteDetailPresenterImpl(Context context) {
        ((FoodCompositionApplication) context).getAppComponent().inject(this);
    }

    @Override
    public void setView(NutrienteDetailView view) {
        this.view = view;
    }

    @Override
    public void getFoodByNutrient(int id) {
        view.showLoading();

        String condition1 =
                "<condition>" +
                        "<cond1><atribute1 name=\"c_id\"/></cond1>" +
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
                .getFoodByNutrient(RequestBody.create(MediaType.parse("text/xml"), query))
                .subscribeOn(Schedulers.io())
                .onErrorReturn(new Function<Throwable, NutrientDetailResponse>() {
                    @Override
                    public NutrientDetailResponse apply(@NonNull Throwable throwable) throws Exception {
                        return new NutrientDetailResponse();
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<NutrientDetailResponse>() {
                    @Override
                    public void accept(@NonNull NutrientDetailResponse nutrientDetailResponse) throws Exception {
                        List<Food> foodList = new ArrayList<>();

                        if (nutrientDetailResponse.getFoodResponse() != null) {
                            foodList = nutrientDetailResponse.getFoodResponse();
                        }

                        view.onDataLoaded(foodList);
                        view.hideLoading();
                    }
                }));


    }

    private String getHeaders() {
        return "<?xml version=\"1.0\" encoding=\"utf-8\"?>";
    }

    private String getQueryType() {
        return "<type level=\"1a\"/>";
    }

    private String getSelection() {
        return "<selection>" +
                "<atribute name=\"f_id\"/>" +
                "<atribute name=\"f_ori_name\"/>" +
                "<atribute name=\"c_ori_name\"/>" +
                "<atribute name=\"best_location\"/>" +
                "<atribute name=\"v_unit\"/>" +
                "</selection>";
    }

    private String getOrder() {
        return "<order ordtype=\"DESC\">" +
                "<atribute3 name=\"best_location\"/>" +
                "</order>";
    }

    @Override
    public void cleanUp() {
        compositeDisposable.clear();
        view = null;
    }
}
