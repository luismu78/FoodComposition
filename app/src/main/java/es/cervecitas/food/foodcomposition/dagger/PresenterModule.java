package es.cervecitas.food.foodcomposition.dagger;

import android.content.Context;

import es.cervecitas.food.foodcomposition.ui.foodgroup.FGPresenter;
import es.cervecitas.food.foodcomposition.ui.foodgroup.FGPresenterImpl;
import es.cervecitas.food.foodcomposition.ui.nutrientes.NutrientesPresenter;
import es.cervecitas.food.foodcomposition.ui.nutrientes.NutrientsPresenterImpl;
import es.cervecitas.food.foodcomposition.ui.search.SearchPresenter;
import es.cervecitas.food.foodcomposition.ui.search.SearchPresenterImpl;
import es.cervecitas.food.foodcomposition.ui.foodgroupdetail.FPresenter;
import es.cervecitas.food.foodcomposition.ui.foodgroupdetail.FPresenterImpl;
import es.cervecitas.food.foodcomposition.ui.fooditem.FoodPresenter;
import es.cervecitas.food.foodcomposition.ui.fooditem.FoodPresenterImpl;

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

    @Provides
    @Singleton
    FoodPresenter providePresenter(Context context) {
        return new FoodPresenterImpl(context);
    }

    @Provides
    @Singleton
    SearchPresenter provideSearchPresenter(Context context) {
        return new SearchPresenterImpl(context);
    }

    @Provides
    @Singleton
    NutrientesPresenter provideNutrientesPresenter(Context context) {
        return new NutrientsPresenterImpl(context);
    }

}
