package es.cervecitas.food.foodcomposition.ui.foodgroup;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

@Root(strict = false)
public class FG_ListItems {

    @ElementList(name = "foodresponse", inline = true)
    private List<FoodGroup> foodResponse;

    List<FoodGroup> getFoodResponse() {
        return foodResponse;
    }
}
