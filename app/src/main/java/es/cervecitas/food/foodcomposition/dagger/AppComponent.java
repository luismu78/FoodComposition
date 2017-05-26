package es.cervecitas.food.foodcomposition.dagger;

import javax.inject.Singleton;

import dagger.Component;
import es.cervecitas.food.foodcomposition.ui.foodgroup.FGActivity;
import es.cervecitas.food.foodcomposition.ui.foodgroup.FGPresenterImpl;
import es.cervecitas.food.foodcomposition.ui.nutrientes.NutrientesActivity;
import es.cervecitas.food.foodcomposition.ui.nutrientes.NutrientsPresenterImpl;
import es.cervecitas.food.foodcomposition.ui.search.SearchActivity;
import es.cervecitas.food.foodcomposition.ui.search.SearchPresenterImpl;
import es.cervecitas.food.foodcomposition.ui.foodgroupdetail.FDetailActivity;
import es.cervecitas.food.foodcomposition.ui.foodgroupdetail.FPresenterImpl;
import es.cervecitas.food.foodcomposition.ui.fooditem.FoodItemActivity;
import es.cervecitas.food.foodcomposition.ui.fooditem.FoodPresenterImpl;

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

    void inject(FDetailActivity target);

    void inject(FoodItemActivity target);

    void inject(FoodPresenterImpl target);

    void inject(SearchPresenterImpl target);

    void inject(SearchActivity target);

    void inject(NutrientsPresenterImpl target);

    void inject(NutrientesActivity target);
}
