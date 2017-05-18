package com.example.luism.foodcomposition.ui.fooditem;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

@Root(name = "componentList", strict = false)
public class ComponentList {

    @ElementList(inline = true, required = false)
    private List<Component> components;
}
