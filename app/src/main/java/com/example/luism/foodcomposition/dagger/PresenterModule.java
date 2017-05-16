package com.example.luism.foodcomposition.dagger;

import android.content.Context;

import com.example.luism.foodcomposition.ui.foodgroup.FGPresenter;
import com.example.luism.foodcomposition.ui.foodgroup.FGPresenterImpl;
import com.example.luism.foodcomposition.ui.foodgroupdetail.FPresenter;
import com.example.luism.foodcomposition.ui.foodgroupdetail.FPresenterImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class PresenterModule {

    @Provides
    @Singleton
    FGPresenter provideFGPresenter(Context context) {
        return new FGPresenterImpl(context);
    }

    @Provides
    @Singleton
    FPresenter provideFPresenter(Context context) {
        return new FPresenterImpl(context);
    }
}
