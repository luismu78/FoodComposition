package com.example.luism.foodcomposition.ui.fooditem;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

@Root(strict = false)
public class FoodItemResponse {

    @ElementList(name = "food", inline = true, required = false)
    private List<Food> food;

    @ElementList(name = "componentList", inline = true, required = false)
    private List<ComponentList> componentList;

    public List<Food> getFood() {
        return food;
    }

    public List<ComponentList> getComponentList() {
        return componentList;
    }
}
