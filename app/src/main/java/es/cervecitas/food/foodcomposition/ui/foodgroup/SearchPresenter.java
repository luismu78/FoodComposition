package es.cervecitas.food.foodcomposition.ui.foodgroup;

public interface SearchPresenter {

    void setView(SearchView view);

    void getSearchResults(String s);

    void cleanup();
}
