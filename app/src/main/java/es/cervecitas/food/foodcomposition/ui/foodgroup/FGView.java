package es.cervecitas.food.foodcomposition.ui.foodgroup;

import es.cervecitas.food.foodcomposition.ui.base.BaseView;

import java.util.List;

interface FGView extends BaseView {

    void onDataLoaded(List<Food> listItems);

    void showLoading();

    void hideLoading();
}
