package com.example.luism.foodcomposition.ui.foodgroup;

import com.example.luism.foodcomposition.ui.base.BaseView;

import java.util.List;

interface FGView extends BaseView {

    void onDataLoaded(List<Food> listItems);

    void onListItemClicked(int id);

    void onClearData();

    void showLoading();

    void hideLoading();
}
