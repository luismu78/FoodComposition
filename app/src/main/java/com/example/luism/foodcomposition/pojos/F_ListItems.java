package com.example.luism.foodcomposition.pojos;

import com.example.luism.foodcomposition.pojos.F.Food;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

@Root(strict = false)
public class F_ListItems {

    @ElementList(name = "foodresponse", inline = true)
    private List<Food> foodResponse;

    public List<Food> getFoodResponse() {
        return foodResponse;
    }
}
