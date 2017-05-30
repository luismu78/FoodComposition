package es.cervecitas.food.foodcomposition.pojo;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "food", strict = false)
public class FoodGroup {

    @Element(name = "fg_id")
    private int id;

    @Element(name = "fg_ori_name")
    private String name;

    public FoodGroup() {
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
