package com.example.luism.foodcomposition.ui.fooditem;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "foodvalue", strict = false)
public class FoodValue {

    @Element(name = "c_id")
    private int c_id;

    @Element(name = "c_ori_name")
    private String c_ori_name;

    @Element(name = "c_eng_name")
    private String c_eng_name;

    @Element(name = "ALC", required = false)
    private String ALC;

    @Element(name = "componentgroup_id", required = false)
    private int componentgroup_id;

    @Element(name = "glos_esp", required = false)
    private String glos_esp;

    @Element(name = "glos_ing", required = false)
    private String glos_ing;

    @Element(name = "cg_descripcion", required = false)
    private String cg_descripcion;

    @Element(name = "best_location", required = false)
    private int best_location;

    @Element(name = "v_unit", required = false)
    private String v_unit;

    @Element(name = "moex", required = false)
    private String moex;

    @Element(name = "stdv", required = false)
    private String stdv;

    @Element(name = "min", required = false)
    private String min;

    @Element(name = "max", required = false)
    private String max;

    @Element(name = "g", required = false)
    private String g;

    @Element(name = "u_descripcion", required = false)
    private String u_descripcion;

    @Element(name = "u_description", required = false)
    private String u_description;

    @Element(name = "value_type", required = false)
    private String value_type;

    @Element(name = "vt_descripcion", required = false)
    private String vt_descripcion;

    @Element(name = "vt_description", required = false)
    private String vt_description;

    @Element(name = "mu_id", required = false)
    private String mu_id;

    @Element(name = "mu_descripcion", required = false)
    private String mu_descripcion;

    @Element(name = "mu_description", required = false)
    private String mu_description;

    @Element(name = "ref_id", required = false)
    private int ref_id;

    @Element(name = "at_descripcion", required = false)
    private String at_descripcion;

    @Element(name = "at_description", required = false)
    private String at_description;

    @Element(name = "pt_descripcion", required = false)
    private String pt_descripcion;

    @Element(name = "pt_description", required = false)
    private String pt_description;

    @Element(name = "method_id", required = false)
    private int method_id;

    @Element(name = "mt_descripcion", required = false)
    private String mt_descripcion;

    @Element(name = "mt_description", required = false)
    private String mt_description;

    @Element(name = "m_descripcion", required = false)
    private String m_descripcion;

    @Element(name = "m_description", required = false)
    private String m_description;

    @Element(name = "m_nom_esp", required = false)
    private String m_nom_esp;

    @Element(name = "m_nom_ing", required = false)
    private String m_nom_ing;

    @Element(name = "mhd_descripcion", required = false)
    private String mhd_descripcion;

    @Element(name = "mhd_description", required = false)
    private String mhd_description;

}
