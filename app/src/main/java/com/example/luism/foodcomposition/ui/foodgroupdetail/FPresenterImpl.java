package com.example.luism.foodcomposition.ui.foodgroupdetail;

import android.content.Context;
import android.util.Log;

import com.example.luism.foodcomposition.app.FoodCompositionApplication;
import com.example.luism.foodcomposition.network.BedcaApi;
import com.example.luism.foodcomposition.ui.foodgroup.*;

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
    public void getFood() {
        view.showLoading();

        bedcaApi
                .getFoodGroupDetail(RequestBody.create(MediaType.parse("text/xml"), "<?xml version=\"1.0\" encoding=\"utf-8\"?><foodquery><type level=\"1\"/><selection><atribute name=\"f_id\"/><atribute name=\"f_ori_name\"/><atribute name=\"langual\"/><atribute name=\"f_eng_name\"/><atribute name=\"f_origen\"/><atribute name=\"edible_portion\"/></selection><condition><cond1><atribute1 name=\"f_ori_name\"/></cond1><relation type=\"BEGINW\"/><cond3>a</cond3></condition><condition><cond1><atribute1 name=\"f_origen\"/></cond1><relation type=\"EQUAL\"/><cond3>BEDCA</cond3></condition><order ordtype=\"ASC\"><atribute3 name=\"f_ori_name\"/></order></foodquery>"))
                .subscribeOn(Schedulers.io())
//                .observeOn(Schedulers.io())
//                .map(new Function<FoodListItems, List<FoodListItemModel>>() {
//                    @Override
//                    public List<FoodListItemModel> apply(@NonNull FoodListItems foodListItems) throws Exception {
//                        List<FoodListItemModel> models = new ArrayList<>();
//
//                        for (Food item : foodListItems.getFoodResponse()) {
//                            models.add(new FoodListItemModel(item.getF_id(), item.getF_ori_name(), item.getF_eng_name()));
//                        }
//                        return models;
//                    }
//                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<F_ListItems>() {
                    @Override
                    public void accept(@NonNull F_ListItems f_listItems) throws Exception {
                        for (Food item : f_listItems.getFoodResponse()) {
                            Log.d("HOLA", "Id: " + item.getF_id());
                            Log.d("HOLA", "Name: " + item.getF_eng_name());
                            Log.d("HOLA", "Nombre: " + item.getF_ori_name());
                            Log.d("HOLA", " ");
                        }

                        view.onDataLoaded(f_listItems.getFoodResponse());
                        view.hideLoading();
                    }
                });


//                .subscribe(new Consumer<List<FoodListItemModel>>() {
//                    @Override
//                    public void accept(@NonNull List<FoodListItemModel> foodListItemModels) throws Exception {
//                        Log.d("HOLA", " ");
//
//                        for (FoodListItemModel food : foodListItemModels) {
//                            Log.d("HOLA", "Id: " + food.getId());
//                            Log.d("HOLA", "Name: " + food.getName());
//                            Log.d("HOLA", "Nombre: " + food.getNombre());
//                            Log.d("HOLA", " ");
//                        }
//                        view.showFoodList(foodListItemModels);
//                        view.hideLoading();
//                    }
//                });

    }

    @Override
    public void cleanup() {

    }
}
