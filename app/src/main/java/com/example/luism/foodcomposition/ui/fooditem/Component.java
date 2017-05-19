package com.example.luism.foodcomposition.ui.fooditem;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "component", strict = false)
public class Component {

    @Element(name = "id", required = false)
    private int id;

    @Element(name = "name_esp", required = false)
    private String name_esp;

    @Element(name = "group", required = false)
    private String group;

    @Element(name = "name_ing", required = false)
    private String name_ing;

    public int getId() {
        return id;
    }

    public String getName_esp() {
        return name_esp;
    }

    public String getGroup() {
        return group;
    }

    public String getName_ing() {
        return name_ing;
    }
}