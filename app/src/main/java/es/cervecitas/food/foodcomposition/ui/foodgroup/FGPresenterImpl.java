package es.cervecitas.food.foodcomposition.ui.foodgroup;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import es.cervecitas.food.foodcomposition.app.FoodCompositionApplication;
import es.cervecitas.food.foodcomposition.network.BedcaApi;
import es.cervecitas.food.foodcomposition.pojo.FoodGroup;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.RequestBody;

public class FGPresenterImpl implements FGPresenter {

    @Inject
    BedcaApi bedcaApi;

    private FGView view;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    public FGPresenterImpl(Context context) {
        ((FoodCompositionApplication) context).getAppComponent().inject(this);
    }


    @Override
    public void setView(FGView view) {
        this.view = view;
    }

    @Override
    public void getFoodGroups() {
        view.showLoading();

        String query = getHeaders() + "<foodquery>" + getQueryType() + getSelection() + getOrder() + "</foodquery>";

        compositeDisposable.add(bedcaApi
                .getFoodGroups(RequestBody.create(MediaType.parse("text/xml"), query))
                .subscribeOn(Schedulers.io())
                .onErrorReturn(new Function<Throwable, FG_ListItems>() {
                    @Override
                    public FG_ListItems apply(@NonNull Throwable throwable) throws Exception {
                        return new FG_ListItems();
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<FG_ListItems>() {
                    @Override
                    public void accept(@NonNull FG_ListItems fg_listItems) throws Exception {
                        List<FoodGroup> foodList = new ArrayList<>();

                        if (fg_listItems.getFoodResponse() != null) {
                            foodList = fg_listItems.getFoodResponse();
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
        return "<type level=\"3\"/>";
    }

    private String getSelection() {
        return "<selection>" +
                "<atribute name=\"fg_id\"/>" +
                "<atribute name=\"fg_ori_name\"/>" +
                "</selection>";
    }

    private String getOrder() {
        return "<order ordtype=\"ASC\">" +
                "<atribute3 name=\"fg_id\"/>" +
                "</order>";
    }

    @Override
    public void cleanup() {
        compositeDisposable.clear();
        view = null;
    }
}
