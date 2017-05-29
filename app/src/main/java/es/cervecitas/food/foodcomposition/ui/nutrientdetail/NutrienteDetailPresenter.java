package es.cervecitas.food.foodcomposition.ui.nutrientdetail;

public interface NutrienteDetailPresenter {

    void setView(NutrienteDetailView view);

    void getFoodByNutrient(int id);

    void cleanUp();
}
