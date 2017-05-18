package com.example.luism.foodcomposition.ui.fooditem;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

@Root(name = "food", strict = false)
public class Food {

    @Element(name = "f_id", required = false)
    private int f_id;

    @Element(name = "f_ori_name", required = false)
    private String f_ori_name;

    @Element(name = "f_eng_name", required = false)
    private String f_eng_name;

    @Element(name = "edible_portion", required = false)
    private String edible_portion;

    @ElementList(inline = true, required = false)
    private List<FoodValue> foodvalue;
}
