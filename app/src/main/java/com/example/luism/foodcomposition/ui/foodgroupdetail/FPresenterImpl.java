package com.example.luism.foodcomposition.ui.foodgroupdetail;

import android.content.Context;

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

        bedcaApi
                .getFoodGroupDetail(RequestBody.create(MediaType.parse("text/xml"), "<?xml version=\"1.0\" encoding=\"utf-8\"?><foodquery><type level=\"1\"/><selection><atribute name=\"f_id\"/><atribute name=\"f_ori_name\"/><atribute name=\"langual\"/><atribute name=\"f_eng_name\"/><atribute name=\"f_origen\"/><atribute name=\"edible_portion\"/></selection><condition><cond1><atribute1 name=\"foodgroup_id\"/></cond1><relation type=\"EQUAL\"/><cond3>" + id + "</cond3></condition><condition><cond1><atribute1 name=\"f_origen\"/></cond1><relation type=\"EQUAL\"/><cond3>BEDCA</cond3></condition><order ordtype=\"ASC\"><atribute3 name=\"f_ori_name\"/></order></foodquery>"))
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

    @Override
    public void cleanup() {
        compositeDisposable.clear();
    }
}
