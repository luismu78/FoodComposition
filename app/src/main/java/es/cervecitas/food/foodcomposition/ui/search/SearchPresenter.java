package es.cervecitas.food.foodcomposition.ui.search;

public interface SearchPresenter {

    void setView(SearchView view);

    void getSearchResults(String s);

    void cleanup();
}
