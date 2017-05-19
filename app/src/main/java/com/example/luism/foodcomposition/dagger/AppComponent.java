package com.example.luism.foodcomposition.dagger;

import com.example.luism.foodcomposition.ui.foodgroup.FGActivity;
import com.example.luism.foodcomposition.ui.foodgroup.FGPresenterImpl;
import com.example.luism.foodcomposition.ui.foodgroupdetail.FDetailFragment;
import com.example.luism.foodcomposition.ui.foodgroupdetail.FPresenterImpl;
import com.example.luism.foodcomposition.ui.fooditem.FoodItemActivity;
import com.example.luism.foodcomposition.ui.fooditem.FoodPresenterImpl;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(
        modules = {
                AppModule.class,
                NetworkModule.class,
                PresenterModule.class
        })
public interface AppComponent {

    void inject(FGPresenterImpl target);

    void inject(FGActivity target);

    void inject(FPresenterImpl target);

    void inject(FDetailFragment target);

    void inject(FoodItemActivity target);

    void inject(FoodPresenterImpl target);
}
