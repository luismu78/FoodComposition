package es.cervecitas.food.foodcomposition.ui.nutrientes;

import java.util.List;

import es.cervecitas.food.foodcomposition.ui.base.BaseView;

public interface NutrientesView extends BaseView {

    void onFilterListLoaded(List<Nutrient> nutrientList);

    void showLoading();

    void hideLoading();

    void showLoadingError();

    void hideLoadingError();
}
