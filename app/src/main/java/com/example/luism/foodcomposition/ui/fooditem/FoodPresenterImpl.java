package com.example.luism.foodcomposition.ui.fooditem;

import android.content.Context;
import android.util.Log;

import com.example.luism.foodcomposition.app.FoodCompositionApplication;
import com.example.luism.foodcomposition.network.BedcaApi;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.RequestBody;

public class FoodPresenterImpl implements FoodPresenter {

    @Inject
    BedcaApi bedcaApi;

    private FoodView view;

    public FoodPresenterImpl(Context context) {
        ((FoodCompositionApplication)context).getAppComponent().inject(this);
    }

    @Override
    public void setView(FoodView view) {
        this.view = view;
    }

    @Override
    public void getData(int id) {
//        view.showView();

        Log.d("HOLA", "+++++++++++++++++++ id: " + id);

        bedcaApi
                .getFoodItemDetail(RequestBody.create(MediaType.parse("text/xml"), "<?xml version=\"1.0\" encoding=\"utf-8\"?><foodquery><type level=\"2\"/><selection><atribute name=\"f_id\"/><atribute name=\"f_ori_name\"/><atribute name=\"f_eng_name\"/><atribute name=\"sci_name\"/><atribute name=\"langual\"/><atribute name=\"foodexcode\"/><atribute name=\"mainlevelcode\"/><atribute name=\"codlevel1\"/><atribute name=\"namelevel1\"/><atribute name=\"codsublevel\"/><atribute name=\"codlevel2\"/><atribute name=\"namelevel2\"/><atribute name=\"f_des_esp\"/><atribute name=\"f_des_ing\"/><atribute name=\"photo\"/><atribute name=\"edible_portion\"/><atribute name=\"f_origen\"/><atribute name=\"c_id\"/><atribute name=\"c_ori_name\"/><atribute name=\"c_eng_name\"/><atribute name=\"eur_name\"/><atribute name=\"componentgroup_id\"/><atribute name=\"glos_esp\"/><atribute name=\"glos_ing\"/><atribute name=\"cg_descripcion\"/><atribute name=\"cg_description\"/><atribute name=\"best_location\"/><atribute name=\"v_unit\"/><atribute name=\"moex\"/><atribute name=\"stdv\"/><atribute name=\"min\"/><atribute name=\"max\"/><atribute name=\"v_n\"/><atribute name=\"u_id\"/><atribute name=\"u_descripcion\"/><atribute name=\"u_description\"/><atribute name=\"value_type\"/><atribute name=\"vt_descripcion\"/><atribute name=\"vt_description\"/><atribute name=\"mu_id\"/><atribute name=\"mu_descripcion\"/><atribute name=\"mu_description\"/><atribute name=\"ref_id\"/><atribute name=\"citation\"/><atribute name=\"at_descripcion\"/><atribute name=\"at_description\"/><atribute name=\"pt_descripcion\"/><atribute name=\"pt_description\"/><atribute name=\"method_id\"/><atribute name=\"mt_descripcion\"/><atribute name=\"mt_description\"/><atribute name=\"m_descripcion\"/><atribute name=\"m_description\"/><atribute name=\"m_nom_esp\"/><atribute name=\"m_nom_ing\"/><atribute name=\"mhd_descripcion\"/><atribute name=\"mhd_description\"/></selection><condition><cond1><atribute1 name=\"f_id\"/></cond1><relation type=\"EQUAL\"/><cond3>" + id + "</cond3></condition><condition><cond1><atribute1 name=\"publico\"/></cond1><relation type=\"EQUAL\"/><cond3>1</cond3></condition><order ordtype=\"ASC\"><atribute3 name=\"componentgroup_id\"/></order></foodquery>"))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<FoodItemResponse>() {
                    @Override
                    public void accept(@NonNull FoodItemResponse foodItemResponse) throws Exception {
                        Log.d("HOLA", "-----------------------------------------");
                        Log.d("HOLA", "foodItemResponse.getFood().size(): " + foodItemResponse.getFood().size());
                        for (Food f : foodItemResponse.getFood()) {
                            Log.d("HOLA", "f.getF_id(): " + f.getF_id());
                            Log.d("HOLA", "f.getF_eng_name(): " + f.getF_eng_name());
                            Log.d("HOLA", "f.getF_ori_name(): " + f.getF_ori_name());
                            Log.d("HOLA", "f.getFoodvalue().size(): " + f.getFoodvalue().size());
                            for (FoodValue fv : f.getFoodvalue()) {
                                // <cg_descripcion>Proximales</cg_descripcion>
                                Log.d("HOLA", "    fv.getC_eng_name(): " + fv.getC_eng_name());
                                Log.d("HOLA", "    fv.getC_ori_name(): " + fv.getC_ori_name());
                                Log.d("HOLA", "    fv.getBest_location(): " + fv.getBest_location());
                                Log.d("HOLA", "    fv.getV_unit(): " + fv.getV_unit());
                            }
                        }
                        Log.d("HOLA", "foodItemResponse.getComponentList().size(): " + foodItemResponse.getComponentList().size());
                    }
                });
    }
}
