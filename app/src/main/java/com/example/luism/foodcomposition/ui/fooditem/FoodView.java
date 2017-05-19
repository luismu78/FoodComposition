package com.example.luism.foodcomposition.ui.fooditem;

import com.example.luism.foodcomposition.ui.base.BaseView;

public interface FoodView extends BaseView {

    void onDataLoaded(Food foodList);

    void showLoading();

    void hideLoading();
}
