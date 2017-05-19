package es.cervecitas.food.foodcomposition.ui.foodgroup;

public interface FGPresenter {

    void setView(FGView view);

    void getFoodGroups();

    void cleanup();
}
