package es.cervecitas.food.foodcomposition.ui.foodgroupdetail;

import es.cervecitas.food.foodcomposition.ui.base.BaseView;

import java.util.List;

public interface FView extends BaseView {

    void onDataLoaded(List<Food> listItems);

    void onClearData();

    void showLoading();

    void hideLoading();
}
