package es.cervecitas.food.foodcomposition.pojo;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

import es.cervecitas.food.foodcomposition.ui.fooditem.FoodValue;

@Root(name = "food", strict = false)
public class Food {

    @Element(name = "f_id", required = false)
    private int f_id;

    // Nombre del alimento
    @Element(name = "f_ori_name")
    private String f_ori_name;

    // Nombre del componente
    @Element(name = "c_ori_name", required = false)
    private String c_ori_name;

    // valor  va con v_unit
    @Element(name = "best_location", required = false)
    private String best_location;

    // unidad va con best location
    @Element(name = "v_unit", required = false)
    private String v_unit;

    // aun sin utilizar
    @Element(name = "edible_portion", required = false) // NO LO UTILIZO AUN
    private String edible_portion;

    @ElementList(inline = true, required = false)
    private List<FoodValue> foodvalue;

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

    public void setBest_location(String best_location) {
        this.best_location = best_location;
    }

    public String getV_unit() {
        return v_unit;
    }

    public void setV_unit(String v_unit) {
        this.v_unit = v_unit;
    }

    public String getEdible_portion() {
        return edible_portion;
    }

    public List<FoodValue> getFoodvalue() {
        return foodvalue;
    }
}
