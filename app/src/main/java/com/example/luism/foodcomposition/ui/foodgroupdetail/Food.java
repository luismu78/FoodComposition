package com.example.luism.foodcomposition.ui.foodgroupdetail;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "food", strict = false)
public class Food {

    @Element(name = "f_id", required = false)
    private int f_id;

    @Element(name = "f_ori_name", required = false)
    private String f_ori_name;

    @Element(name = "langual", required = false)
    private String langual;

    @Element(name = "f_eng_name", required = false)
    private String f_eng_name;

    @Element(name = "f_origen", required = false)
    private String f_origen;

    @Element(name = "edible_portion", required = false)
    private String edible_portion;

    public Food() {
    }

    public int getF_id() {
        return f_id;
    }

    public String getF_ori_name() {
        return f_ori_name;
    }

    public String getLangual() {
        return langual;
    }

    public String getF_eng_name() {
        return f_eng_name;
    }

    public String getF_origen() {
        return f_origen;
    }

    public String getEdible_portion() {
        return edible_portion;
    }

}