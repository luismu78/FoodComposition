package es.cervecitas.food.foodcomposition.ui.foodgroupdetail;

import es.cervecitas.food.foodcomposition.ui.base.BaseView;

import java.util.List;

import es.cervecitas.food.foodcomposition.pojo.Food;

public interface FView extends BaseView {

    void onDataLoaded(List<Food> listItems);

    void onClearData();

    void showLoading();

    void hideLoading();

    void showLoadingError();

    void hideLoadingError();
}
