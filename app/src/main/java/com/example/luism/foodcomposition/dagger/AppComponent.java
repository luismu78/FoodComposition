package com.example.luism.foodcomposition.dagger;

import com.example.luism.foodcomposition.ui.foodgroup.FGActivity;
import com.example.luism.foodcomposition.ui.foodgroup.FGPresenterImpl;

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
}
