package es.cervecitas.food.foodcomposition.ui.fooditem;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

import es.cervecitas.food.foodcomposition.pojo.Food;

@Root(strict = false)
public class FoodItemResponse {

    @ElementList(name = "food", inline = true, required = false)
    private List<Food> food;

    public List<Food> getFood() {
        return food;
    }
}
