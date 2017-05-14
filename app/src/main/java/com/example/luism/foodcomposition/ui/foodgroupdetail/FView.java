package com.example.luism.foodcomposition.ui.foodgroupdetail;

import com.example.luism.foodcomposition.ui.base.BaseView;

public interface FView extends BaseView {

    void onDataLoaded(F_ListItems items);

    void onClearData();

    void showLoading();

    void hideLoading();
}
