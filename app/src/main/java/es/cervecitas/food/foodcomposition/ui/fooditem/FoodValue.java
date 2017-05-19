package es.cervecitas.food.foodcomposition.ui.fooditem;

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
    private String best_location;

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

    public int getC_id() {
        return c_id;
    }

    public String getC_ori_name() {
        return c_ori_name;
    }

    public String getC_eng_name() {
        return c_eng_name;
    }

    public String getALC() {
        return ALC;
    }

    public int getComponentgroup_id() {
        return componentgroup_id;
    }

    public String getGlos_esp() {
        return glos_esp;
    }

    public String getGlos_ing() {
        return glos_ing;
    }

    public String getCg_descripcion() {
        return cg_descripcion;
    }

    public String getBest_location() {
        return best_location;
    }

    public String getV_unit() {
        return v_unit;
    }

    public String getMoex() {
        return moex;
    }

    public String getStdv() {
        return stdv;
    }

    public String getMin() {
        return min;
    }

    public String getMax() {
        return max;
    }

    public String getG() {
        return g;
    }

    public String getU_descripcion() {
        return u_descripcion;
    }

    public String getU_description() {
        return u_description;
    }

    public String getValue_type() {
        return value_type;
    }

    public String getVt_descripcion() {
        return vt_descripcion;
    }

    public String getVt_description() {
        return vt_description;
    }

    public String getMu_id() {
        return mu_id;
    }

    public String getMu_descripcion() {
        return mu_descripcion;
    }

    public String getMu_description() {
        return mu_description;
    }

    public int getRef_id() {
        return ref_id;
    }

    public String getAt_descripcion() {
        return at_descripcion;
    }

    public String getAt_description() {
        return at_description;
    }

    public String getPt_descripcion() {
        return pt_descripcion;
    }

    public String getPt_description() {
        return pt_description;
    }

    public int getMethod_id() {
        return method_id;
    }

    public String getMt_descripcion() {
        return mt_descripcion;
    }

    public String getMt_description() {
        return mt_description;
    }

    public String getM_descripcion() {
        return m_descripcion;
    }

    public String getM_description() {
        return m_description;
    }

    public String getM_nom_esp() {
        return m_nom_esp;
    }

    public String getM_nom_ing() {
        return m_nom_ing;
    }

    public String getMhd_descripcion() {
        return mhd_descripcion;
    }

    public String getMhd_description() {
        return mhd_description;
    }
}
