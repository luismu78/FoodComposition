package es.cervecitas.food.foodcomposition.ui.foodgroup;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "food", strict = false)
class Food {

    @Element(name = "fg_id")
    private int fg_id;

    @Element(name = "fg_ori_name")
    private String fg_ori_name;

    @Element(name = "fg_eng_name")
    private String fg_eng_name;

    public Food() {
    }

    int getFg_id() {
        return fg_id;
    }

    String getFg_ori_name() {
        return fg_ori_name;
    }

    String getFg_eng_name() {
        return fg_eng_name;
    }
}
