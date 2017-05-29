package es.cervecitas.food.foodcomposition.ui.nutrientes;

public interface NutrientesPresenter {

    void setView(NutrientesView view);

    void getFilterList();

    void cleanup();
}
