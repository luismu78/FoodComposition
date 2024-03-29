package es.cervecitas.food.foodcomposition.ui.search;

import java.util.List;

import es.cervecitas.food.foodcomposition.pojo.Food;

public interface SearchView {

    void onSearchDataLoaded(List<Food> foods);

    void showSearchError();

    void hideSearchError();
}
