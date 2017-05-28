package es.cervecitas.food.foodcomposition.ui.nutrientdetail;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "food", strict = false)
public class Food {

    @Element(name = "f_id", required = false)
    private int f_id;

    @Element(name = "f_ori_name", required = false)
    private String f_ori_name;

    @Element(name = "c_ori_name", required = false)
    private String c_ori_name;

    @Element(name = "best_location", required = false)
    private String best_location;

    @Element(name = "v_unit", required = false)
    private String v_unit;

    public int getF_id() {
        return f_id;
    }

    public String getF_ori_name() {
        return f_ori_name;
    }

    public String getC_ori_name() {
        return c_ori_name;
    }

    public String getBest_location() {
        return best_location;
    }

    public String getV_unit() {
        return v_unit;
    }
}
