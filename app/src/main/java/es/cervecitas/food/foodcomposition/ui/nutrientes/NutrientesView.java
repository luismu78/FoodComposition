package es.cervecitas.food.foodcomposition.ui.nutrientes;

import java.util.List;

import es.cervecitas.food.foodcomposition.ui.base.BaseView;
import es.cervecitas.food.foodcomposition.ui.foodgroupdetail.Food;

public interface NutrientesView extends BaseView {

    void onFoodDataLoaded(List<Food> FoodItems);

    void onFilterListLoaded(List<Nutrient> nutrientList);

    void showLoading();

    void hideLoading();

    void showLoadingError();

    void hideLoadingError();
}
