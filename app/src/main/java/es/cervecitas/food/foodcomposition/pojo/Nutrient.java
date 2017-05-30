package es.cervecitas.food.foodcomposition.pojo;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "food", strict = false)
public class Nutrient {

    @Element(name = "cg_id", required = false)
    private int cg_id;

    @Element(name = "c_id", required = false)
    private int c_id;

    @Element(name = "c_ori_name", required = false)
    private String c_ori_name;

    @Element(name = "glos_esp", required = false)
    private String glos_esp;

    public int getCg_id() {
        return cg_id;
    }

    public int getC_id() {
        return c_id;
    }

    public String getC_ori_name() {
        return c_ori_name;
    }

    public String getGlos_esp() {
        return glos_esp;
    }
}
