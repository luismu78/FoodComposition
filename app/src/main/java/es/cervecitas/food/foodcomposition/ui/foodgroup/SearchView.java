package es.cervecitas.food.foodcomposition.ui.foodgroup;

import java.util.List;

import es.cervecitas.food.foodcomposition.ui.foodgroupdetail.Food;

public interface SearchView {

    void onSearchDataLoaded(List<Food> foods);

    void showSearchError();

    void hideSearchError();
}
