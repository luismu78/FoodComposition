package es.cervecitas.food.foodcomposition.ui.nutrientdetail;

import java.util.List;

import es.cervecitas.food.foodcomposition.ui.fooditem.Food;

public interface NutrienteDetailView {

    void onDataLoaded(List<Food> foodList);

    void showLoading();

    void hideLoading();

    void showLoadingError();

    void hideLoadingError();
}
