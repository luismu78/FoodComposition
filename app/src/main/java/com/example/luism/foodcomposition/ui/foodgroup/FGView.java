package com.example.luism.foodcomposition.ui.foodgroup;

import com.example.luism.foodcomposition.ui.base.BaseView;

public interface FGView extends BaseView {

    void onDataLoaded(FG_ListItems listItems);

    void onListItemClicked(int id);

    void onClearData();

    void showLoading();

    void hideLoading();
}
