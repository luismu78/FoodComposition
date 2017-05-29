package es.cervecitas.food.foodcomposition.ui.foodgroup;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "food", strict = false)
class FoodGroup {

    @Element(name = "fg_id")
    private int id;

    @Element(name = "fg_ori_name")
    private String name;

    public FoodGroup() {
    }

    int getId() {
        return id;
    }

    String getName() {
        return name;
    }
}
