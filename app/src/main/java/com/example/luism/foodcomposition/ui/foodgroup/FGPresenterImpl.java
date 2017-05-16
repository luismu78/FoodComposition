package com.example.luism.foodcomposition.ui.foodgroup;

import android.content.Context;
import android.util.Log;

import com.example.luism.foodcomposition.app.FoodCompositionApplication;
import com.example.luism.foodcomposition.network.BedcaApi;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
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

        Log.d("HOLA", "getFoodGroups ");

        compositeDisposable.add(bedcaApi
                .getFoodGroups(RequestBody.create(MediaType.parse("text/xml"), "<?xml version=\"1.0\" encoding=\"utf-8\"?><foodquery><type level=\"3\"/><selection><atribute name=\"fg_id\"/><atribute name=\"fg_ori_name\"/><atribute name=\"fg_eng_name\"/></selection><order ordtype=\"ASC\"><atribute3 name=\"fg_id\"/></order></foodquery>"))
                .subscribeOn(Schedulers.io())
//                .observeOn(Schedulers.io())
//                .map(new Function<FG_ListItems, List<FoodGroupItemModel>>() {
//                    @Override
//                    public List<FoodGroupItemModel> apply(@NonNull FG_ListItems foodListItems) throws Exception {
//                        List<FoodGroupItemModel> models = new ArrayList<>();
//
//                        Log.d("HOLA", "foodListItems.getFoodResponse().toString(): " + foodListItems.getFoodResponse().toString());
//
//                        for (Food item : foodListItems.getFoodResponse()) {
//                            Log.d("HOLA", item.toString());
//                            models.add(new FoodGroupItemModel(item.getFg_id(), item.getFg_ori_name(), item.getFg_eng_name()));
//                        }
//                        return models;
//                    }
//                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<FG_ListItems>() {
                    @Override
                    public void accept(@NonNull FG_ListItems fg_listItems) throws Exception {


                        view.onDataLoaded(fg_listItems.getFoodResponse());
                        view.hideLoading();
                    }
                }));

//                .subscribe(new Consumer<List<FoodGroupItemModel>>() {
//                    @Override
//                    public void accept(@NonNull List<FoodGroupItemModel> foodListItemModels) throws Exception {
//                        Log.d("HOLA", " ");
//
//                        for (FoodGroupItemModel food : foodListItemModels) {
//                            Log.d("HOLA", "Id: " + food.getId());
//                            Log.d("HOLA", "Name: " + food.getName());
//                            Log.d("HOLA", "Nombre: " + food.getNombre());
//                            Log.d("HOLA", " ");
//                        }
//                        view.showFoodGroupsList(foodListItemModels);
//                        view.hideLoading();
//                    }
//                });
    }

    @Override
    public void cleanup() {
        compositeDisposable.clear();
    }
}
