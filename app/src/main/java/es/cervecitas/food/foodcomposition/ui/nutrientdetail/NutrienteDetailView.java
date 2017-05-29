package es.cervecitas.food.foodcomposition.ui.nutrientdetail;

import java.util.List;

interface NutrienteDetailView {

    void onDataLoaded(List<Food> foodList);

    void showLoading();

    void hideLoading();

    void showLoadingError();

    void hideLoadingError();
}
