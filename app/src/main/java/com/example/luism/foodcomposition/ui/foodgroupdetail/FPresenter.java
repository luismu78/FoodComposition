package com.example.luism.foodcomposition.ui.foodgroupdetail;

public interface FPresenter {

    void setView(FView view);

    void getFood(int id);

    void cleanup();
}
