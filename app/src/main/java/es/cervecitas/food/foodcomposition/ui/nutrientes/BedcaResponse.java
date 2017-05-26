package es.cervecitas.food.foodcomposition.ui.nutrientes;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

import es.cervecitas.food.foodcomposition.ui.foodgroupdetail.Food;

@Root(name = "F_ListItems", strict = false)
public class BedcaResponse {

    @ElementList(name = "foodresponse", inline = true)
    private List<Nutrient> foodResponse;

    public List<Nutrient> getFoodResponse() {
        return foodResponse;
    }
}
