package es.cervecitas.food.foodcomposition.ui.nutrientes;

import android.content.Context;
import android.util.Log;

import javax.inject.Inject;

import es.cervecitas.food.foodcomposition.app.FoodCompositionApplication;
import es.cervecitas.food.foodcomposition.network.BedcaApi;
import es.cervecitas.food.foodcomposition.ui.foodgroupdetail.F_ListItems;
import es.cervecitas.food.foodcomposition.ui.foodgroupdetail.Food;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.RequestBody;

public class NutrientsPresenterImpl implements NutrientesPresenter {

    @Inject
    BedcaApi bedcaApi;

    private NutrientesView view;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    public NutrientsPresenterImpl(Context context) {
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

        bedcaApi
                .getNutrientNames(RequestBody.create(MediaType.parse("text/xml"), query))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<BedcaResponse>() {
                    @Override
                    public void accept(@NonNull BedcaResponse bedcaResponse) throws Exception {
                        view.onFilterListLoaded(bedcaResponse.getFoodResponse());
                        view.hideLoading();
                    }
                });
    }

//    @Override
//    public void getFoods(int filter) {
////        view.showLoading();
//
//        String condition1 =
//                "<condition>" +
//                "<cond1><atribute1 name=\"c_id\"/></cond1>" +
//                "<relation type=\"EQUAL\"/>" +
//                "<cond3>409</cond3>" +
//                "</condition>";
//
//        String condition2 =
//                "<condition>" +
//                "<cond1><atribute1 name=\"f_origen\"/></cond1>" +
//                "<relation type=\"EQUAL\"/>" +
//                "<cond3>BEDCA</cond3>" +
//                "</condition>";
//
//        String query = getHeaders() + "<foodquery>" + getQueryType1a() + getSelectionFoods() + condition1 + condition2 + getOrderFoods() + "</foodquery>";
//
//        bedcaApi
//                .getByNutrient(RequestBody.create(MediaType.parse("text/xml"), query))
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Consumer<F_ListItems>() {
//                    @Override
//                    public void accept(@NonNull F_ListItems f_listItems) throws Exception {
//                        Log.d("HOLA", "f_listItems.getFoodResponse().size(): " + f_listItems.getFoodResponse().size());
//                        for (Food food : f_listItems.getFoodResponse()) {
//                            Log.d("HOLA", "name: " + food.getF_ori_name());
//                            Log.d("HOLA", food.getC_ori_name() + " " + food.getBest_location() + food.getV_unit());
//                        }
//                        Log.d("HOLA", "----------------------------------------");
//                    }
//                });
//    }

    @Override
    public void cleanup() {

    }

    private String getHeaders() {
        return "<?xml version=\"1.0\" encoding=\"utf-8\"?>";
    }

    private String getQueryType1a() {
        return "<type level=\"1a\"/>";
    }

    private String getQueryType3h() {
        return "<type level=\"3h\"/>";
    }

    private String getSelectionFoods() {
        return "<selection>" +
                "<atribute name=\"f_id\"/>" +
                "<atribute name=\"f_ori_name\"/>" +
                "<atribute name=\"c_ori_name\"/>" +
                "<atribute name=\"best_location\"/>" +
                "<atribute name=\"v_unit\"/>" +
                "</selection>";
    }

    private String getSelectionFilter() {
        return "<selection>" +
                "<atribute name=\"cg_id\"/>" +
                "<atribute name=\"c_id\"/>" +
                "<atribute name=\"c_ori_name\"/>" +
//                "<atribute name=\"glos_esp\"/>" +
                "</selection>";
    }

    private String getOrderFoods() {
        return "<order ordtype=\"DESC\">" +
                "<atribute3 name=\"best_location\"/>" +
                "</order>";
    }

    private String getFilterOrder() {
        return "<order ordtype=\"ASC\">" +
                "<atribute3 name=\"c_id\"/>" +
                "</order>";
    }
}
