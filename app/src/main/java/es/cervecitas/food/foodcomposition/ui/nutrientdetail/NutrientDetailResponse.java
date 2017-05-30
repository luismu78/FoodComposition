package es.cervecitas.food.foodcomposition.ui.nutrientdetail;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

import es.cervecitas.food.foodcomposition.pojo.Food;

@Root(strict = false)
public class NutrientDetailResponse {

    @ElementList(name = "foodresponse", inline = true)
    private List<Food> foodResponse;

    public List<Food> getFoodResponse() {
        return foodResponse;
    }
}
