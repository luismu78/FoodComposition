package es.cervecitas.food.foodcomposition.ui.fooditem;

import es.cervecitas.food.foodcomposition.ui.base.BaseView;

public interface FoodView extends BaseView {

    void onDataLoaded(Food foodList);

    void showLoading();

    void hideLoading();
}
