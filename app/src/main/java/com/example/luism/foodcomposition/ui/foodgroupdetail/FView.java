package com.example.luism.foodcomposition.ui.foodgroupdetail;

import com.example.luism.foodcomposition.ui.base.BaseView;

import java.util.List;

public interface FView extends BaseView {

    void onDataLoaded(List<Food> listItems);

    void onClearData();

    void showLoading();

    void hideLoading();
}
