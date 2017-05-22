package es.cervecitas.food.foodcomposition.ui.fooditem;

public interface FoodPresenter {

    void setView(FoodView view);

    void getData(int id);

    void cleanup();
}
